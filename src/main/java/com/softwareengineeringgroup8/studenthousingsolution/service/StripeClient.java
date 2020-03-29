package com.softwareengineeringgroup8.studenthousingsolution.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.stripe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;


@Service
public class StripeClient {


    @Autowired
    StripeClient() {
        Stripe.apiKey = "sk_test_OmrxXx3SrMP0kubI9Mmkm5rP00UhLqD8c7";
    }

    public String transferCharge(String email)throws StripeException{

        String transactionId = null;

        try {
            Map<String, Object> transferParam = new HashMap<>();
            transferParam.put("amount", 1245 * 100);
            transferParam.put("currency", "usd");
            transferParam.put("description", "Payment transferred from" + email);
            transferParam.put("destination", "acct_1GOGv2BFw11Oh6lO");
            transferParam.put("transfer_group", "testTrans");
            Transfer transfer = Transfer.create(transferParam);

            transactionId= transfer.getId();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return transactionId;
    }

    //Creates customer with a card
    public String createCharge(String email, String card_num, String monthNum, String yearNum, String ccv) throws StripeException {

       /*  Map<String, Object> customerParameter = new HashMap<String, Object>();
        String CustomerId=null;

        try{
            customerParameter.put("description", "Customer for " + email);
            customerParameter.put("email", email);

            Customer newCustomer = Customer.create(customerParameter);

            Map <String, Object> cardParam = new HashMap<String, Object> ();
            cardParam.put("number", card_num);
            cardParam.put("exp_month", monthNum);
            cardParam.put("exp_year", yearNum);
            cardParam.put("cvc", cvc);

            Map <String, Object> tokenParam = new HashMap<String, Object>();
            tokenParam.put("source", cardParam);

            Token token = Token.create(tokenParam);

            Map <String, Object> source = new HashMap<String, Object>();
            source.put("source", token.getId());

            customerParameter.put("source", token.getId());

            CustomerId = newCustomer.getId();
        }catch (Exception ex){
            ex.printStackTrace();
        }



        return CustomerId;
    }



    //Charges customer
    public String chargeCustomer(String email) {
        //Stripe.apiKey = "sk_test_OmrxXx3SrMP0kubI9Mmkm5rP00UhLqD8c7";

       // Double a= Double.valueOf(amount);
        //a= a*100;
        //String amount_$= Double.toString(a);


        String ChargeId =null;
        try {
            Map<String, Object> chargeParam = new HashMap<String, Object>();
            chargeParam.put("amount", 1245 * 100);// fixed test amount for demo
            chargeParam.put("currency", "usd");
            chargeParam.put("description", "Charge for " + email);


            Charge charge = Charge.create(chargeParam);

            ChargeId = charge.getId();

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return ChargeId;
    }

    public String Charge(String email, String card_num, String monthNum, String yearNum, String cvc) throws StripeException {
        Map<String, Object> customerParameter = new HashMap<String, Object>();
        String CustomerId = null;


        customerParameter.put("description", "Customer for " + email);
        customerParameter.put("email", email);

        Customer newCustomer = Customer.create(customerParameter);

        Map<String, Object> cardParam = new HashMap<String, Object>();
        cardParam.put("number", card_num);
        cardParam.put("exp_month", Integer.valueOf(monthNum));
        cardParam.put("exp_year", Integer.valueOf(yearNum));
        cardParam.put("cvc", cvc);

        Map<String, Object> tokenParam = new HashMap<String, Object>();
        //tokenParam.put("source", cardParam);
        tokenParam.put("card", cardParam);
        Token token = Token.create(tokenParam);

        Map<String, Object> source = new HashMap<String, Object>();
        source.put("source", token.getId());

        customerParameter.put("source", token.getId());

        CustomerId = newCustomer.getId();


        String ChargeId =null;
        try {
            Map<String, Object> chargeParam = new HashMap<String, Object>();
            chargeParam.put("amount", 1245 * 100);// fixed test amount for demo
            chargeParam.put("currency", "usd");
            chargeParam.put("description", "Charge for " + email);
            chargeParam.put("customer", CustomerId);
            chargeParam.put("source", token.getId());


            Charge charge = Charge.create(chargeParam);

            ChargeId = charge.getId();

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return ChargeId;
    }

}
*/
        Map<String, Object> customerParameter = new HashMap<String, Object>();
        String CustomerId=null;

        customerParameter.put("description", "Customer for " + email);
        customerParameter.put("email", email);

        Customer newCustomer = Customer.create(customerParameter);

        Map <String, Object> cardParam = new HashMap<String, Object> ();
        cardParam.put("number", card_num);
        cardParam.put("exp_month", monthNum);
        cardParam.put("exp_year", yearNum);
        cardParam.put("cvc", ccv);

        Map <String, Object> tokenParam = new HashMap<String, Object>();
        tokenParam.put("card", cardParam);

        Token token = Token.create(tokenParam);

        Map <String, Object> source = new HashMap<String, Object>();
        source.put("source", token.getId());


        CustomerId = newCustomer.getId();

        newCustomer.getSources().create(source);
        //charging a customer
        String chargeId=null;
        try {

            //Stripe.apiKey = "sk_test_OmrxXx3SrMP0kubI9Mmkm5rP00UhLqD8c7";
            Map<String, Object> chargeParam = new HashMap<String, Object>();
            chargeParam.put("amount", 1245 * 100);
            chargeParam.put("currency", "usd");
            chargeParam.put("customer", CustomerId);



            //create a charge
            Charge charge = Charge.create(chargeParam);
            chargeId=charge.getId();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return chargeId;
    }
}
/*
    @Value("${stripe.keys.private")
    private String API_SECRET_KEY;

//Creates customer
    public Customer createCustomer(String token, String email) throws Exception {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("email", email);
        //customerParams.put("source", token);
        return Customer.create(customerParams);
    }

    private Customer getCustomer(String id) throws Exception {
        return Customer.retrieve(id);
    }
//Charges a new card
    public Charge chargeNewCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
// Goes to default card
    public Charge chargeCustomerCard(String customerId, int amount) throws Exception {
        String sourceCard = getCustomer(customerId).getDefaultSource();
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
       // chargeParams.put("source", sourceCard);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }

    public Transfer transferPayment(String customerId, int amount) throws Exception {
        Map<String, Object> transferParams = new HashMap<>();
        transferParams.put("amount", amount);
        transferParams.put("currency", "usd");
        transferParams.put("destination", "acct_1GOGv2BFw11Oh6lO");
        transferParams.put("transfer_group", "testTrans");

        Transfer transfer = Transfer.create(transferParams);

        return transfer;
    }

    public String createCharge(String email, String token, int amount) {
        String id = null;
        try {
            Stripe.apiKey = "sk_test_OmrxXx3SrMP0kubI9Mmkm5rP00UhLqD8c7";
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", amount);
            chargeParams.put("currency", "usd");
            chargeParams.put("description", "Charge for " + email);
            //chargeParams.put("source", token); // ^ obtained with Stripe.js

            //create a charge
            Charge charge = Charge.create(chargeParams);
            id = charge.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }
*/
