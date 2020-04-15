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


    public Boolean createReview(String reviewDescription, float cleanlinessRating, float securityRating, float communicationRating, float locationRating, float totalRating, User tenant, int propID) throws Exception{

        String clR= String.valueOf(cleanlinessRating);
        String sR= String.valueOf(securityRating);
        String cR= String.valueOf(communicationRating);
        String lR= String.valueOf(locationRating);
        String tR= String.valueOf(totalRating);

        try {

            if (clR == null || sR == null || cR == null || lR == null || tR == null) {
                return false;
            }
            else if(cleanlinessRating > 5 || securityRating > 5|| communicationRating > 5 || locationRating > 5|| totalRating> 5){
                return false;
            }

        }catch (Exception ex) {
            ex.printStackTrace();

        }

        Properties property= propertiesRepository.findByPropertyID(propID);

        Reviews reviews = new Reviews(property, tenant,reviewDescription, cleanlinessRating, securityRating, communicationRating,  locationRating,  totalRating );
        reviewsRepository.save(reviews);

        return true;


    }

    public Boolean deleteReview(int reviewId){


        Reviews reviews= reviewsRepository.findById(reviewId);
        reviewsRepository.delete(reviews);

        return true;
    }

    public Boolean updateReview(int reviewId, String reviewDescription, float cleanlinessRating, float securityRating, float communicationRating, float locationRating, float totalRating) throws Exception{

        String clR= String.valueOf(cleanlinessRating);
        String sR= String.valueOf(securityRating);
        String cR= String.valueOf(communicationRating);
        String lR= String.valueOf(locationRating);
        String tR= String.valueOf(totalRating);

        try {

            if (clR == null || sR == null || cR == null || lR == null || tR == null) {
                return false;
            }
            else if(cleanlinessRating > 5 || securityRating > 5|| communicationRating > 5 || locationRating > 5|| totalRating> 5){
                return false;
            }

        }catch (Exception ex) {
            ex.printStackTrace();

        }

        Reviews reviews= reviewsRepository.findById(reviewId);


        reviews.setCleanlinessRating(cleanlinessRating);
        reviews.setCommunicationRating(communicationRating);
        reviews.setSecurityRating(securityRating);
        reviews.setLocationRating(locationRating);
        reviews.setTotalRating(totalRating);
        reviews.setReviewDescription(reviewDescription);

        reviewsRepository.save(reviews);

        return true;


    }
}