package com.softwareengineeringgroup8.studenthousingsolution.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softwareengineeringgroup8.studenthousingsolution.model.ChargeRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserRoles;
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
/*
    public PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }*/

    @Autowired
    PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }


    //@PostMapping("/create-charge")
    //@RequestMapping(value="/create-charge", method=RequestMethod.POST)
    //@ResponseBody
    @PostMapping("/create-charge")
    public String createCharge(@RequestBody ChargeRequest req, @RequestHeader("Authorization") String str) throws StripeException {
        try {
            User tenant = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                //return false;
                return "invalid authentication";
            }
            //listingService.createListingRequest(request,landlord);
            String chargeId= stripeClient.createCharge(req.getEmail(), req.getCard_num(), req.getMonthNum(), req.getYearNum(), req.getCcv(), tenant);

            String transferId=stripeClient.transferCharge(req.getEmail());
            if (chargeId == null) {
                return "An error occurred while trying to create a charge.";
            }

            return "Success! Your charge id is " + chargeId + " Your transaction id: " + transferId;

            //return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            //return false;
            return "error";
        }
    }
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

















