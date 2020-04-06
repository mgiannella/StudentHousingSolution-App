package com.softwareengineeringgroup8.studenthousingsolution.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softwareengineeringgroup8.studenthousingsolution.model.ReviewRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserRoles;
import com.softwareengineeringgroup8.studenthousingsolution.service.ReviewsService;
import com.softwareengineeringgroup8.studenthousingsolution.service.StripeClient;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiModel;
import com.softwareengineeringgroup8.studenthousingsolution.model.PaymentResponse;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;


@RestController
@ApiModel(description="Handles reviews")
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/reviews")
public class ReviewsController {
    @Autowired
    private UserPermissionService userPermissionService;
    @Autowired
    private ReviewsService reviewsService;
    @Autowired
    ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }


    @PostMapping("/create-review")
    public Boolean createReview(@RequestBody ReviewRequest req, @RequestHeader("Authorization") String str) throws Exception {
        try {
            User tenant = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return false;

            }

            // connect with review service
            Boolean acceptReview=reviewsService.createReview(req.getReviewDescription(),req.getCleanlinessRating(),req.getSecurityRating(), req.getCommunicatingRating(), req.getLocationRating(), req.getTotalRating(), tenant);
            if (acceptReview==false){
                return false;
            }
            return true;

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;

        }
    }
}