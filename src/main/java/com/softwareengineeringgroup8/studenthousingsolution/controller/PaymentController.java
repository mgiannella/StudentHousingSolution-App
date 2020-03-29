package com.softwareengineeringgroup8.studenthousingsolution.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softwareengineeringgroup8.studenthousingsolution.model.ChargeRequest;
import com.softwareengineeringgroup8.studenthousingsolution.service.StripeClient;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiModel;
import com.softwareengineeringgroup8.studenthousingsolution.model.PaymentResponse;

@RestController
@ApiModel(description="Handles all Charges made in Checkout page")
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/payment")
public class PaymentController {
/*
    @Value("${stripe.keys.private}")
    private String API_PRIVATE_KEY;*/


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
    public PaymentResponse createCharge( @RequestBody ChargeRequest req) throws StripeException
    {

        /*
        //Creates customer with card
        String customerId = stripeClient.createCustomer(email, card_num, monthNum, yearNum, cvc);

        if (customerId == null) {
            return new PaymentResponse(false, "An error occurred while trying to create a customer.");
        }
*/
        //creates charge

        String chargeId = stripeClient.createCharge(req.getEmail(), req.getCard_num(), req.getMonthNum(),req.getYearNum(),req.getCcv());

        String transferId= stripeClient.transferCharge(req.getEmail());

        if (chargeId == null) {
            return new PaymentResponse(false, "An error occurred while trying to create a charge.");
        }

        return new PaymentResponse(true, "Success! Your charge id is " + chargeId + " Your transaction id: " + transferId);
    }


}














