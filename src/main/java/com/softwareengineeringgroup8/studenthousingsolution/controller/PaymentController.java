package com.softwareengineeringgroup8.studenthousingsolution.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softwareengineeringgroup8.studenthousingsolution.model.ChargeRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.PaymentRecordRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.PendingPaymentRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.StripeLandlordRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserRoles;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PaymentRecordRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertiesRepository;
import com.softwareengineeringgroup8.studenthousingsolution.service.StripeClient;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Charge;
import com.stripe.model.Token;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiModel;
import com.softwareengineeringgroup8.studenthousingsolution.model.PaymentResponse;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.NoSuchAlgorithmException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.*;
import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestController
@ApiModel(description="Handles all Charges made in Checkout page")
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/payment")
public class PaymentController {
/*
    @Value("${stripe.keys.private}")
    private String API_PRIVATE_KEY;*/

    @Autowired
    private UserPermissionService userPermissionService;
    @Autowired
    private StripeClient stripeClient;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private TenantGroupsService tenantGroupsService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PendingPaymentService pendingPaymentService;
    @Autowired
    private PropertiesRepository propertiesRepository;
    @Autowired
    private PaymentRecordRepository paymentRecordRepository;

/*
    public PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }*/

    @Autowired
    PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }


    @GetMapping("/viewTenantProperties")
    @ApiOperation(value = "View Tenant Properties")
    public List<Properties> listProperties(@RequestHeader("Authorization") String authString) {
        try {
            User user = userPermissionService.loadUserByJWT(authString);

          /*  if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT)) {
                return null;
                //;
            }*/

            List<Properties> propertiesList = new ArrayList<Properties>();
            List<TenantGroups> tenantGroupsList = tenantGroupsService.getGroupByTenant(user);
            int size = tenantGroupsList.size();
            for (int i = 0; i < size; i++) {
                TenantGroups tg = tenantGroupsList.get(i);
                List<Properties> propList = propertiesRepository.findByTenantGroup(tg);
                propertiesList.add(propList.get(0));
            }
            return propertiesList;
        } catch (Error | NotFoundException e) {

            System.out.println(e);
            return null;
        }


    }

    @GetMapping("/viewTenants")
    @ApiOperation(value = "View Tenants on Property")
    public Properties properties(@RequestHeader("Authorization") String authString) {
        try {
            User user = userPermissionService.loadUserByJWT(authString);

            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                return null;
                //;
            }

            Properties properties = propertyService.getPropertyByLandlord(user);
            //properties.getGroup();

            return properties;
        } catch (Error | NotFoundException e) {

            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/viewPayments")
    @ApiOperation(value= "View Payments")
    public List<PaymentRecord> viewPayments(@RequestHeader("Authorization") String authString){
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT)) {
                return null;
            }
            return pendingPaymentService.getPaymentRecordByUser(user);
        } catch (Error| NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }


    @PostMapping("/{id}")
    @ApiOperation(value= "Complete Pending Payment Request")
    public Boolean createCharge(@PathVariable("id") int paymentRecordId, @RequestHeader("Authorization") String str, @RequestBody ChargeRequest req) throws StripeException {
        try {
            User tenant = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return false;
                //;
            }
            //listingService.createListingRequest(request,landlord);

            //took out tenant id for now  tenant,
            PaymentRecord paymentRecord= pendingPaymentService.getPaymentRecordById(req.getPaymentRecordID());
            String chargeId = stripeClient.createCharge(req.getName_card(), req.getEmail(), req.getCard_num(), req.getMonthNum(), req.getYearNum(), req.getCcv(), req.getFirstName(), req.getLastName(), req.getAddress(), req.getCity(), req.getState(), req.getZip(), req.getCountry(), req.getPhone(), paymentRecord);

            String transferId = stripeClient.transferCharge(req.getEmail());
            if (chargeId == null || transferId==null) {
                //return "An error occurred while trying to create a charge.";
                return false;
            }

            //return "Success! Your charge id is " + chargeId + " Your transaction id: " + transferId;

            return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
            //return "error";
        }
    }

    @PostMapping("/create-landlord acct")
    public Boolean createLandAcct(@RequestBody StripeLandlordRequest req, @RequestHeader("Authorization") String str) throws StripeException {
        try {
            User landlord = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return false;
                //;
            }
            //listingService.createListingRequest(request,landlord);
            String accountId = stripeClient.createLandlordAcct(req.getEmail(),landlord);

            //return "Success! Your account has been created;

            return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
            //return "error";
        }
    }

    @PostMapping("/create-payment request")
    public Boolean createPaymentRequest(@RequestBody PendingPaymentRequest req, @RequestHeader("Authorization") String str) throws StripeException {
        try {
            User landlord = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return false;
                //;
            }
            Boolean payRequest = pendingPaymentService.createPaymentRequest(req.getPropID(), req.getTenantID(), req.getAmount(), req.getPtype(), req.getDueDate());
            if (payRequest==false){
                return false;
            }
            return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
        }
    }

    /*
    @PostMapping("/viewPendingPayments")
    @ApiOperation(value = "View Pending Payments")
    public List<PaymentRecord> viewPendingPayments(@RequestHeader("Authorization") String authString){
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT) {
                return null;
            }

        } catch (Error| NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

     */
}
        /*
        //Creates customer with card
        String customerId = stripeClient.createCustomer(email, card_num, monthNum, yearNum, cvc);

        if (customerId == null) {
            return new PaymentResponse(false, "An error occurred while trying to create a customer.");
        }
*/
        //checks charge

 /*  if (chargeId == null) {
                return new PaymentResponse(false, "An error occurred while trying to create a charge.");
            }

            return new PaymentResponse(true, "Success! Your charge id is " + chargeId + " Your transaction id: " + transferId);
        }*/

















