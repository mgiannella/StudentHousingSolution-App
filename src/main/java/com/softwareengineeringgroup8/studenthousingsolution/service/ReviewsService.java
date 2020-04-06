package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.repository.*;
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

@Component
public class ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TenantGroupsService tenantGroupsService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertiesRepository propertiesRepository;


    public Boolean createReview(String reviewDescription, int cleanlinessRating, int securityRating, int communicatingRating, int locationRating, int totalRating, User tenant) throws Exception{



        try{
            //if ()
            return false;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        List<TenantGroups> tglist = tenantGroupsService.getGroupByTenant(tenant);
        List<Properties> propList = propertiesRepository.findByTenantGroup(tglist.get(0));

        Properties x = propList.get(0);

        Reviews reviews = new Reviews( x, tenant,reviewDescription, cleanlinessRating, securityRating, communicatingRating,  locationRating,  totalRating  );

        return true;
    }
}