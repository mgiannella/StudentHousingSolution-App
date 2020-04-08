package com.softwareengineeringgroup8.studenthousingsolution.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softwareengineeringgroup8.studenthousingsolution.model.PaymentRecord;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PaymentRecordRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PaymentTypeRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertiesRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.stripe.model.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


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

    StripeClient() {
        Stripe.apiKey = "sk_test_OmrxXx3SrMP0kubI9Mmkm5rP00UhLqD8c7";
    }

    public String transferCharge(String email) throws StripeException {

        String transactionId = null;

        try {
            Map<String, Object> transferParam = new HashMap<>();
            transferParam.put("amount", 1245 * 100);
            transferParam.put("currency", "usd");
            transferParam.put("description", "Payment transferred from" + email);
            transferParam.put("destination", "acct_1GOGv2BFw11Oh6lO");
            transferParam.put("transfer_group", "testTrans");
            Transfer transfer = Transfer.create(transferParam);

            transactionId = transfer.getId();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return transactionId;
    }

    //Creates customer with a card
    public String createCharge(String name_card, String email, String card_num, String monthNum, String yearNum, String ccv, String firstName, String lastName, String address, String city, String state, String zip, String country, String phone, User tenant, int propID) throws StripeException {

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
        //Long payamount=null;
        try {

            //Stripe.apiKey = "sk_test_OmrxXx3SrMP0kubI9Mmkm5rP00UhLqD8c7";
            Map<String, Object> chargeParam = new HashMap<String, Object>();
            chargeParam.put("amount", 1245 * 100);
            chargeParam.put("currency", "usd");
            chargeParam.put("customer", CustomerId);


            //create a charge
            Charge charge = Charge.create(chargeParam);
            chargeId = charge.getId();

            //Stores amount into long variable
            //payamount= charge.getAmount();


        }catch (Exception ex) {
           ex.printStackTrace();

        }


        //List<TenantGroups> tglist = tenantGroupsService.getGroupByTenant(tenant);
        //List<Properties> propList = propertiesRepository.findByTenantGroup(tglist.get(0));

        //Properties x=propList.get(0);




            // Gets property description from front end selection
        /*
           if (chargeDescription== "Complete Rent"){
            ptypeDesc="TENANT_MONTHLY"
           }else if(chargeDescription== "Utilities"){
            ptypeDesc="UTILITIES"
          }else if(chargeDescription== "Security Deposit"){
            ptypeDesc="SECURITY_DEPOSIT"
           }else if (chargeDescription == "Amenities"){
           ptypeDesc="AMENITIES_FEE"
           }else {
           ptypeDesc="PARKING_FEE"
           }
           }
         */

            String ptypeDesc = "TENANT_MONTHLY";

            Properties property= propertiesRepository.findByPropertyID(propID);


            PaymentRecord paymentRecord = new PaymentRecord(new Date(new java.util.Date().getTime()), property, tenant, paymentTypeRepository.findBypTypeDesc(ptypeDesc), new BigDecimal(1245));

            paymentRecordRepository.save(paymentRecord);

            return chargeId;

        }

        public String bankCharge(String email, String firstName, String lastName, String address, String city, String state, String zip, String country, String phone, User tenant,String name_bank, String account_num, String routing_num) throws StripeException {
         return null;
        }

    public String createLandlordacct(String email) throws StripeException {

        String accountId = null;
    //creating a landlord account
        try {
            ArrayList<Object> requestedCapabilities =new ArrayList<>();
            requestedCapabilities.add("card_payments");
            requestedCapabilities.add("transfers");

            Map<String, Object> params = new HashMap<>();
            params.put("type", "custom");
            params.put("country", "US");
            params.put("email", "s@gmail.com");
            params.put("requested_capabilities", Arrays.asList("card_payments", "transfers"));

            Account account = Account.create(params);
            accountId=account.getId();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //Accepting terms of agreement
       /* Map<String, Object> tosAcceptanceParams = new HashMap<>();
        tosAcceptanceParams.put("date", (long) System.currentTimeMillis() / 1000L);
        tosAcceptanceParams.put("ip", request.getRemoteAddr()); // Assumes you're not using a proxy

        Map<String, Object> params = new HashMap<>();
        params.put("tos_acceptance", tosAcceptanceParams);

        acct.update(params);

*/
        return accountId;
    }
    }


