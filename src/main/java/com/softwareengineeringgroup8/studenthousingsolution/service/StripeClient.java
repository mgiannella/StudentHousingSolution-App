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
import java.util.List;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

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
    public String createCharge(String email, String card_num, String monthNum, String yearNum, String ccv, User tenant) throws StripeException {

        Map<String, Object> customerParameter = new HashMap<String, Object>();
        String CustomerId = null;

        customerParameter.put("description", "Customer for " + email);
        customerParameter.put("email", email);

        Customer newCustomer = Customer.create(customerParameter);

        Map<String, Object> cardParam = new HashMap<String, Object>();
        cardParam.put("number", card_num);
        cardParam.put("exp_month", monthNum);
        cardParam.put("exp_year", yearNum);
        cardParam.put("cvc", ccv);

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


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<TenantGroups> tglist = tenantGroupsService.getGroupByTenant(tenant);
        List<Properties> propList = propertiesRepository.findByTenantGroup(tglist.get(0));

        Properties x = propList.get(0);

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


            PaymentRecord paymentRecord = new PaymentRecord(new Date(new java.util.Date().getTime()), x, tenant, paymentTypeRepository.findBypTypeDesc(ptypeDesc), new BigDecimal(1245));

            paymentRecordRepository.save(paymentRecord);

            return chargeId;

        }
    }


