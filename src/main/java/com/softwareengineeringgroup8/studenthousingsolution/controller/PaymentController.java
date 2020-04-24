package com.softwareengineeringgroup8.studenthousingsolution.controller;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.ChargeRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.PaymentRecordRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.PendingPaymentRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.StripeLandlordRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserRoles;
import com.softwareengineeringgroup8.studenthousingsolution.repository.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.StripeClient;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Charge;
import com.stripe.model.Token;

import javassist.NotFoundException;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
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

import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.*;
import javassist.NotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@ApiModel(description="Handles all Charges made in Checkout page")
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
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private TenantGroupMembersRepository tenantGroupMembersRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LandlordAccountsRepository landlordAccountsRepository;
    @Autowired
    private NotificationService notificationService;


/*
    public PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }*/

    @Autowired
    PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }


    @GetMapping("/viewTenantProperties")
    @ApiOperation(value = "View Tenant's Properties")
    public List<Properties> listProperties(@RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);

            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT)) {
                return null;
                //;
            }

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


    @GetMapping("/viewLandlordProperties")
    @ApiOperation(value = "View Landlord's Properties")
    public List<Properties> landlordProperties(@RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);

            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                return null;
                //;
            }

            List<Properties> properties = propertiesRepository.findByLandlord(user);

            return properties;
        } catch (Error | NotFoundException e) {

            System.out.println(e);
            return null;
        }
    }


    @GetMapping("/viewTenants/{propId}")
    @ApiOperation(value = "View Tenants on Property")
    public List<User> viewTenants(@PathVariable("propId") int propId, @RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);

            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                return null;
                //;
            }

            //Properties properties = propertyService.getPropertyByLandlord(user);
            Properties properties = propertiesRepository.findByPropertyID(propId);
            TenantGroups tenantGroup = properties.getGroup();
            //User leadTenant=tenantGroup.getLeadTenant();
            List<User> tenantGroupMembers = tenantGroupMembersRepository.findByGroup(tenantGroup);

            //TenantGroups tenantGroup=properties.getGroup();
            //User tenantGroupMembers=tenantGroup.getLeadTenant();

            //properties.getGroup();

            return tenantGroupMembers;
        } catch (Error | NotFoundException e) {

            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/getTenantsInfo/{tenantId}")
    @ApiOperation(value = "View selected Tenant info")
    public User getTenant(@PathVariable("tenantId") int tenantId, @RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);

            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                return null;
                //;
            }

            //Properties properties = propertyService.getPropertyByLandlord(user);


            //TenantGroups tenantGroup=properties.getGroup();
            //User tenantGroupMembers=tenantGroup.getLeadTenant();

            //properties.getGroup();

            User tenant = userRepository.findById(tenantId);

            return tenant;
        } catch (Error | NotFoundException e) {

            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/viewPaymentsOnTenants")
    @ApiOperation(value = "View Payments on Tenant side")
    public List<PaymentRecord> viewPaymentsOnTenants(@RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT)) {
                return null;
            }
            return pendingPaymentService.getPaymentRecordByUser(user);
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/viewPaymentsOnLandlord/{propId}")
    @ApiOperation(value = "View Payments on Landlord side")
    public List<PaymentRecord> viewPaymentsOnLandlord(@PathVariable("propId") int propId,@RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                return null;
            }
            /*
            List<List<PaymentRecord>> paymentRecord = new ArrayList<List<PaymentRecord>>();
            List<Properties> properties = propertyService.getPropertiesByLandlord(user);
            int numOfProperties = properties.size();
            for (int i = 0; i < numOfProperties; i++) {
                Properties prop = properties.get(i);
                paymentRecord.add(paymentRecordRepository.findByProperties(prop));
            }
             */

            Properties prop= propertiesRepository.findByPropertyID(propId);

            return paymentRecordRepository.findByProperties(prop);

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Display Pending Payment Request")
    public ArrayList<String> displayCharge(@PathVariable("id") int paymentRecordId, @RequestHeader("Authorization") String str) throws ValidationException {
        try {
            User tenant = userPermissionService.loadUserByJWT(str);


            PaymentRecord paymentRecord = pendingPaymentService.getPaymentRecordById(paymentRecordId);

            ArrayList<String> pendingPayment = new ArrayList<String>();
            int payId = paymentRecord.getId();
            PaymentType payTypeId = paymentRecord.getPaymentTypeId();
            BigDecimal amount = paymentRecord.getPaymentAmount();

            Date dueDate = paymentRecord.getPaymentDueDate();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");


            String pay_id = String.valueOf(payId);
            String paymentDescription = payTypeId.getpTypeDesc();
            String a = amount.toString();
            String dDate = df.format(dueDate);

            //Did unnecessary substring, do a.length-2
            String a1 = a.substring(0, a.length() - 2);
            //String a2=a.substring(0, a1.length()-1);

            pendingPayment.add(pay_id);
            pendingPayment.add(paymentDescription);
            pendingPayment.add(a1);
            //pendingPayment.add(a2);
            pendingPayment.add(dDate);


            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return null;

            }
            return pendingPayment;

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;

        }
    }


    @PostMapping("/create-charge/{id}")
    @ApiOperation(value = "Complete Pending Payment Request")
    public Boolean createCharge(@PathVariable("id") int paymentRecordId, @RequestHeader("Authorization") String str, @RequestBody ChargeRequest req) throws StripeException {
        try {
            User tenant = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return false;
                //;
            }
            //listingService.createListingRequest(request,landlord);

            //took out tenant id for now  tenant,
            //int pId= Integer.parseInt(paymentRecordId);
            PaymentRecord paymentRecord = pendingPaymentService.getPaymentRecordById(paymentRecordId);
            Date paymentDate = paymentRecord.getPaymentDate();

            Properties prop=paymentRecord.getProp();
           // String propLocation=prop.get
            User landLord=prop.getLandlord();

            PaymentType paymentType =paymentRecord.getPaymentTypeId();
            String paymentTypeDecription= paymentType.getpTypeDesc();



            if (paymentDate != null) {
                return false;
            } else {
                String chargeId = stripeClient.createCharge(req.getName_card(), req.getEmail(), req.getCard_num(), req.getMonthNum(), req.getYearNum(), req.getCcv(), req.getFirstName(), req.getLastName(), req.getAddress(), req.getCity(), req.getState(), req.getZip(), req.getCountry(), req.getPhone(), paymentRecord);

                Charge charge =Charge.retrieve(chargeId);
                String chargeURL=charge.getReceiptUrl();
                String transferId = stripeClient.transferCharge(paymentRecord);
                if (chargeId == null || transferId == null) {
                    //return "An error occurred while trying to create a charge.";
                    return false;
                }else{
                    String description="Your tenant" + " " + tenant.getFullname() + " " + "has fulfilled"+ " " +"their"+ " "+ paymentTypeDecription+ " " + "payment.";
                    Boolean notification= notificationService.createNotification(landLord, description, "PAYMENT", "");
                    return true;
            /*
                    if (notification==false){
                        return "A notification cannot be sent";
                    }
            */
                    //return chargeURL;
            }

                /*
                else {
                    Boolean notification= notificationService.createNotification()
                }
*/
                //return "Success! Your charge id is " + chargeId + " Your transaction id: " + transferId;
            }

            //return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
            //return "error";
        }
    }

    @GetMapping("/get-receipt-URL/{id}")
    @ApiOperation(value="Shows payment Receipt")
    public String getReceipt(@PathVariable("id") int paymentRecordId,@RequestHeader("Authorization") String str)throws StripeException{
        try {
            User tenant = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return null;
                //;
            }

            PaymentRecord paymentRecord = pendingPaymentService.getPaymentRecordById(paymentRecordId);

            String chargeId= paymentRecord.getChargeID();

            Charge charge=Charge.retrieve(chargeId);

            String receiptURL=charge.getReceiptUrl();

            return receiptURL;



        } catch(Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }


    @PostMapping("/create-landlord acct")
    @ApiOperation(value = "Create Landlord's Stripe Account")
    public Boolean createLandAcct(@RequestBody StripeLandlordRequest req, @RequestHeader("Authorization") String str) throws StripeException {
        try {
            User landlord = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return false;
                //;
            }
            //listingService.createListingRequest(request,landlord);
            String accountId = stripeClient.createLandlordAcct(req.getEmail(), landlord, req.getAccount_holder_name(), req.getRouting_number(),req.getAccount_number());

            //return "Success! Your account has been created;
            String descriptionAccount="You have successfully created a payment account!";
            Boolean notification= notificationService.createNotification(landlord, descriptionAccount, "PAYMENT", "");

            if(accountId==null){
                return false;
            }

            return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
            //return "error";
        }

    }

    @GetMapping("/get-verification-link")
    @ApiOperation(value="Prints out verification link")
    public String getVerificationLink(@RequestHeader("Authorization") String str)throws StripeException{
        try {
            User landlord = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return null;
            }

            LandlordAccounts landlordAccounts = landlordAccountsRepository.findByUser(landlord);
            String stripeAcct = landlordAccounts.getStripeID();

            String updateURL=stripeClient.verificationLink(stripeAcct);


            return updateURL;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return "false";
        }
    }

    /*
    @GetMapping("/check-stripe-acct")
    @ApiOperation(value = "Checks Stripe Account")
    public String checkStripeAcct(@RequestHeader("Authorization") String str) throws StripeException {
        try {
            User landlord = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return "false";
                //;
            }
            //listingService.createListingRequest(request,landlord);
            LandlordAccounts landlordAccounts = landlordAccountsRepository.findByUser(landlord);
            String stripeAcct = landlordAccounts.getStripeID();

            if (stripeAcct != null) {
                return "View Payments";
            } else {
                return "No Stripe Account found, please follow link to create a Stripe Account";
            }

            //return "Success! Your account has been created;

            return stripeAcct;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return "false";
            //return "error";
        }
    }
    */

    @GetMapping("/check-restriction")
    @ApiOperation(value = "Checks Restriction on Stripe Account")
    public String checkRestriction(@RequestHeader("Authorization") String str) throws StripeException {
        try {
            User landlord = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return "false";
                //;
            }
            LandlordAccounts landlordAccounts = stripeClient.getByUser(landlord);
            String stripeAcct = landlordAccounts.getStripeID();
            String checkVerify = stripeClient.checkRestrictionAccount(stripeAcct);



            return checkVerify;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return "false";
        }
    }


    @PostMapping("/create-payment request/{tenantId}")
    @ApiOperation(value = "Create Pending Payment Request")
    public Boolean createPaymentRequest(@PathVariable("tenantId") int tenantId, @RequestBody PendingPaymentRequest req, @RequestHeader("Authorization") String str) throws StripeException {
        try {
            User landlord = userPermissionService.loadUserByJWT(str);
            User tenant=userRepository.findById(tenantId);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return false;
                //;
            }
            Boolean payRequest = pendingPaymentService.createPaymentRequest(req.getPropID(), tenantId, req.getAmount(), req.getPtype(), req.getDueDate());
            if (payRequest == false) {
                return false;
            }else{
                String description="Your landlord" + " " + landlord.getFullname() + " " + "has requested a payment.";
                Boolean notification= notificationService.createNotification(tenant, description, "PAYMENT", "");
                return true;
            }
            //return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
        }
    }
}

















