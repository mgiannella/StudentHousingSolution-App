//written by: Srinivasniranjan Nukala
//tested by: Srinivasniranjan Nukala
//debugged by: Srinivasniranjan Nukala

package com.softwareengineeringgroup8.studenthousingsolution.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertiesRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.ReviewsRepository;
import com.softwareengineeringgroup8.studenthousingsolution.service.*;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


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
    private PropertiesRepository propertiesRepository;
    @Autowired
    private TenantGroupsService tenantGroupsService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private NotificationService notificationService;

    @Autowired
    ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
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

    @GetMapping("/viewReviews-with-Properties/{propertiesList}")
    @ApiOperation(value = "Returns review information on each property")
    public ArrayList listReviewsWithProperties(@PathVariable("propertiesList") List<Properties> propertiesList, @RequestHeader("Authorization") String str) throws ValidationException{

        try {
            User user = userPermissionService.loadUserByJWT(str);

            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT)) {
                return null;
                //;
            }

            ArrayList arraylist = new ArrayList();


            int size=propertiesList.size();

            for (int i = 0; i < size; i++) {
            int propID=propertiesList.get(i).getId();
            List<Reviews> reviews=reviewsRepository.findByProperty(propertiesList.get(i));
            ArrayList reviewAndProp= new ArrayList();

            reviewAndProp.add(propID);
            reviewAndProp.add(reviews);

            arraylist.add(reviewAndProp);
            }

            return arraylist;
        }catch (Error | NotFoundException e) {

            System.out.println(e);
            return null;
        }

    }

    @GetMapping("/{propId}")
    @ApiOperation(value= "Display selected property")
    public Properties displayProperty(@PathVariable("propId") int propId, @RequestHeader("Authorization") String str) throws ValidationException {
        try {
            User tenant = userPermissionService.loadUserByJWT(str);

            Properties property= propertiesRepository.findByPropertyID(propId);

            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return null;

            }
            return property;

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;

        }
    }

    @GetMapping("display-reviews/{propId}")
    @ApiOperation(value= "Display reviews on selected property")
    public List<Reviews> displayReviews(@PathVariable("propId") int propId/*, @RequestHeader("Authorization") String str*/) throws ValidationException {
        try {

           /* User tenant = userPermissionService.loadUserByJWT(str);



            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return null;

            }
*/
            Properties prop= propertiesRepository.findById(propId);
            List<Reviews> reviews= reviewsRepository.findByProperty(prop);
            return reviews;

        } catch (Error e) {
            System.out.println(e);
            return null;

        }
    }

    @PostMapping("delete review/{reviewId}")
    @ApiOperation(value= "Delete reviews on selected property")
    public Boolean deleteReview(@PathVariable("reviewId") int reviewId, @RequestHeader("Authorization") String str) throws ValidationException{
        try {
            User tenant = userPermissionService.loadUserByJWT(str);

            Reviews reviews = reviewsRepository.findById(reviewId);

            Properties prop=reviews.getProp();

            User landLord=prop.getLandlord();

            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return false;

            }

            Boolean delete=reviewsService.deleteReview(reviewId);

            PropertyLocations propLocation=prop.getLocation();

            String streetAddress= propLocation.getAddress();
            String city= propLocation.getCity();
            String state=propLocation.getState();
            String zipCode=propLocation.getZip();

            if (delete==false){
                return false;
            }
            else{
                String description="A review has been deleted on Property:"+ " " + streetAddress+ ".";
                Boolean notification= notificationService.createNotification(landLord, description, "GENERAL", "");
                return true;
            }


           // return delete;

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;

        }
    }


    @GetMapping("reviews-display-on-dashboard/{propId}")
    @ApiOperation(value= "Display reviews on tenant dashboard")
    public List<Reviews> tenantReviews(@PathVariable("propId") int propId, @RequestHeader("Authorization") String str) throws ValidationException {
        try {

            User tenant = userPermissionService.loadUserByJWT(str);



            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return null;

            }

            Properties prop= propertiesRepository.findById(propId);
            List<Reviews> reviewsList= reviewsRepository.findByPropAndTenant(prop, tenant);
            return reviewsList;

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;

        }
    }




    @GetMapping("display selected review/{reviewId}")
    @ApiOperation("Select a review")
    public Reviews selectReview(@PathVariable("reviewId") int reviewId, @RequestHeader("Authorization")String str)throws Exception {
        try {
            User tenant = userPermissionService.loadUserByJWT(str);

            Reviews review = reviewsRepository.findById(reviewId);

            return review;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }



    @PostMapping("update review/{reviewId}")
    @ApiOperation(value= "Update review on selected property")
    public Boolean updateReview(@PathVariable("reviewId") int reviewId, @RequestBody ReviewRequest req, @RequestHeader("Authorization")String str ) throws Exception {
        try {
            User tenant = userPermissionService.loadUserByJWT(str);

            Reviews reviews = reviewsRepository.findById(reviewId);

            Properties prop=reviews.getProp();

            User landLord=prop.getLandlord();

            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return false;

            }

            String reviewDescription=null;
            if (req.getReviewDescription()!=""){
                reviewDescription=req.getReviewDescription();
            }

            Boolean update=reviewsService.updateReview(reviewId,reviewDescription,req.getCleanlinessRating(),req.getSecurityRating(), req.getCommunicationRating(), req.getLocationRating(), req.getTotalRating());

            PropertyLocations propLocation=prop.getLocation();

            String streetAddress= propLocation.getAddress();
            String city= propLocation.getCity();
            String state=propLocation.getState();
            String zipCode=propLocation.getZip();

            if (update==false){
                return false;
            }
            else{
                String description="A review has been updated on Property:"+ " " + streetAddress+".";
                Boolean notification= notificationService.createNotification(landLord, description, "GENERAL", "");
                return true;
            }
            //return update;

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;

        }
    }


    @PostMapping("/create-review/{propId}")
    @ApiOperation(value= "Write a review")
    public Boolean createReview(@PathVariable("propId") int propId,@RequestBody ReviewRequest req, @RequestHeader("Authorization") String str) throws Exception {
        try {
            User tenant = userPermissionService.loadUserByJWT(str);

            Properties prop= propertiesRepository.findByPropertyID(propId);



            User landLord= prop.getLandlord();

            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return false;

            }

            String reviewDescription=null;
            if (req.getReviewDescription()!=""){
                reviewDescription=req.getReviewDescription();
            }

            // connect with review service
            Boolean acceptReview=reviewsService.createReview(reviewDescription,req.getCleanlinessRating(),req.getSecurityRating(), req.getCommunicationRating(), req.getLocationRating(), req.getTotalRating(), tenant, propId);

            PropertyLocations propLocation=prop.getLocation();

            String streetAddress= propLocation.getAddress();
            String city= propLocation.getCity();
            String state=propLocation.getState();
            String zipCode=propLocation.getZip();

            if (acceptReview==false){
                return false;
            }
            else{
                String description="A review has been created on Property:"+ " " + streetAddress+ ".";
                Boolean notification= notificationService.createNotification(landLord, description, "GENERAL", "");
                return true;
            }
            //return true;

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;

        }
    }
}