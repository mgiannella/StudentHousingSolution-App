//written by: Srinivasniranjan Nukala
//tested by: Srinivasniranjan Nukala
//debugged by: Srinivasniranjan Nukala
package com.softwareengineeringgroup8.studenthousingsolution.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.repository.*;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
//import com.stripe.model.*;
import com.stripe.model.Account;
import com.stripe.model.Account.Capabilities;
import com.stripe.model.AccountLink;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Address;
import com.stripe.model.BankAccount;
import com.stripe.model.Card;
import com.stripe.model.CustomerCollection;
import com.stripe.model.ExternalAccountCollection;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentSourceCollection;
import com.stripe.model.StripeObject;
import com.stripe.model.Token;
import com.stripe.model.Transfer;
import com.stripe.param.AccountCreateParams;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.net.InetAddress;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;

import static java.lang.String.valueOf;



@Component
public class StripeClient {


    @Autowired
    private PaymentRecordRepository paymentRecordRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TenantGroupsService tenantGroupsService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertiesRepository propertiesRepository;
    @Autowired
    private LandlordAccountsRepository landlordAccountsRepository;

    StripeClient() {
        Stripe.apiKey = "sk_test_OmrxXx3SrMP0kubI9Mmkm5rP00UhLqD8c7";
    }



    public String createLandlordAcct(String email, User landlord, String account_holder_name, String routing_number, String account_number ) throws StripeException {

        String accountId = null;
        String bankId= null;

        //creating a landlord account
        ArrayList<Object> requestedCapabilities =new ArrayList<>();
        requestedCapabilities.add("card_payments");
        requestedCapabilities.add("transfers");

        Map<String, Object> params = new HashMap<>();
        params.put("type", "custom");
        params.put("country", "US");
        params.put("email", email);
        params.put("requested_capabilities", Arrays.asList("card_payments", "transfers"));

        Account account = Account.create(params);
        accountId=account.getId();


        //Accepting terms of agreement
        Account acct = Account.retrieve(accountId);

        Map<String, Object> tosAcceptanceParams = new HashMap<>();
        tosAcceptanceParams.put("date", (long) System.currentTimeMillis() / 1000L);
        tosAcceptanceParams.put("ip", "137.36.251.66"); // We're not using a proxy

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("tos_acceptance", tosAcceptanceParams);

        acct.update(parameter);

        //Connects a bank account
        Account a= Account.retrieve(accountId);
        Map<String, Object> tokenParam = new HashMap<>();
        Map <String, Object> bankAccountParam=new HashMap<>();

        bankAccountParam.put("country", "US");
        bankAccountParam.put("currency", "usd");
        bankAccountParam.put("account_holder_name", account_holder_name);
        bankAccountParam.put("account_holder_type", "individual");
        bankAccountParam.put("routing_number", routing_number );
        bankAccountParam.put("account_number", account_number);
        tokenParam.put("bank_account", bankAccountParam);
        Token token = Token.create(tokenParam);

        String bankToken=token.getId();

        Map<String, Object> acctParams = new HashMap<>();
        acctParams.put("external_account", bankToken);

        BankAccount bankAccount = (BankAccount) a.getExternalAccounts().create(acctParams);


        try {


            bankId=bankAccount.getId();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        if(accountId!=null|| bankId!=null) {
            LandlordAccounts landlordAccounts = new LandlordAccounts(landlord, accountId);
            landlordAccountsRepository.save(landlordAccounts);
        }else{
            return null;
        }
        return accountId;
    }

    public String verificationLink(String acctID) throws StripeException {
        String verification_link=null;
        Map<String, Object> params = new HashMap<>();
        params.put("account", acctID);
        params.put("failure_url", "http://localhost:3000/login");
        params.put("success_url", "http://localhost:3000/login");
        params.put("type", "custom_account_verification");

        AccountLink accountLink = AccountLink.create(params);

        //URL link verification for personal information
        try {


            verification_link=accountLink.getUrl();


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return verification_link;
    }

    public String checkRestrictionAccount(String acctID) throws StripeException {
        Account account = Account.retrieve(acctID);
        Account.Capabilities a = account.getCapabilities();
        String checkCardPayments=null;
        String checkTransfers = null;


        try{
            checkCardPayments = a.getCardPayments();
            checkTransfers = a.getTransfers();

        }catch(Exception ex) {
            ex.printStackTrace();
        }

        if ((checkCardPayments.equals("inactive")) & (checkTransfers.equals("inactive"))) {
            return"Your Payment Account is Restricted.";
        } else if ((checkCardPayments.equals("pending")) & (checkTransfers.equals("pending"))) {
            return"Your Account is pending, please wait one-two business days for verification.";
        } else if ((checkCardPayments.equals("active")) & (checkTransfers.equals("active"))) {
            return "Congratulations, your Account is now active!";
        }
        else{
            return "There is an error in your account. Please contact customer service.";
        }




    }


    public String transferCharge(PaymentRecord paymentRecord) throws StripeException {
        Properties prop=paymentRecord.getProp();
        User Landlord= prop.getLandlord();
        LandlordAccounts account=landlordAccountsRepository.findByUser(Landlord);

        String accountId=account.getStripeID();



        BigDecimal amount= paymentRecord.getPaymentAmount();
        Double amountToDouble=amount.doubleValue();
        amountToDouble=amountToDouble*100;
        Long amountToLong=amountToDouble.longValue();


        String transactionId = null;

        Map<String, Object> transferParam = new HashMap<>();
        transferParam.put("amount", amountToLong);
        transferParam.put("currency", "usd");
        //transferParam.put("description", "Payment transferred from" + email);
        transferParam.put("destination", accountId);
        transferParam.put("transfer_group", "testTrans");
        Transfer transfer = Transfer.create(transferParam);
        try {


            transactionId = transfer.getId();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return transactionId;
    }

    //Creates customer with a card
    //took out tenant id for now User tenant,
    public String createCharge(String name_card, String email, String card_num, String monthNum, String yearNum, String ccv, String firstName, String lastName, String address, String city, String state, String zip, String country, String phone, PaymentRecord paymentRecord) throws StripeException {



        BigDecimal amount= paymentRecord.getPaymentAmount();

        Double amountToDouble=amount.doubleValue();

        amountToDouble=amountToDouble*100;

        Long amountToLong=amountToDouble.longValue();

        //Integer amountToInteger=amountToLong.intValue();





        Map<String, Object> customerParameter = new HashMap<String, Object>();

        String CustomerId = null;

        customerParameter.put("description", "Customer for " + email);
        customerParameter.put("email", email);
        customerParameter.put("name", firstName+ " " + lastName);
        customerParameter.put("phone", phone);


        Customer newCustomer = Customer.create(customerParameter);

        Map<String, Object> cardParam = new HashMap<String, Object>();
        cardParam.put("number", card_num);
        cardParam.put("exp_month", monthNum);
        cardParam.put("exp_year", yearNum);
        cardParam.put("cvc", ccv);
        cardParam.put("name", name_card);
        cardParam.put("address_state", state);
        cardParam.put("address_city", city);
        cardParam.put("address_country", country);
        cardParam.put("address_zip", zip);
        cardParam.put("address_line1", address);



        Map<String, Object> tokenParam = new HashMap<String, Object>();
        tokenParam.put("card", cardParam);

        Token token = Token.create(tokenParam);

        Map<String, Object> source = new HashMap<String, Object>();
        source.put("source", token.getId());


        CustomerId = newCustomer.getId();

        newCustomer.getSources().create(source);


        //charging a customer
        String chargeId = null;
        //Stripe.apiKey = "sk_test_OmrxXx3SrMP0kubI9Mmkm5rP00UhLqD8c7";
        Map<String, Object> chargeParam = new HashMap<String, Object>();
        chargeParam.put("amount", amountToLong);
        chargeParam.put("currency", "usd");
        chargeParam.put("customer", CustomerId);


        //create a charge
        Charge charge = Charge.create(chargeParam);
        try {


            chargeId = charge.getId();

        }catch (Exception ex) {
            ex.printStackTrace();

        }


        if(chargeId!=null){
            paymentRecord.setPaymentDate(new Date(new java.util.Date().getTime()));
            paymentRecord.setChargeID(chargeId);
            paymentRecordRepository.save(paymentRecord);
        }


        return chargeId;

    }

      public LandlordAccounts getByUser(User landLord){
        return landlordAccountsRepository.findByUser(landLord);
      }

}


