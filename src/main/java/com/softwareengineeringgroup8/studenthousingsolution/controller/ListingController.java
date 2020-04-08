package com.softwareengineeringgroup8.studenthousingsolution.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;


import com.softwareengineeringgroup8.studenthousingsolution.service.ListingService;
import com.softwareengineeringgroup8.studenthousingsolution.service.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
import com.softwareengineeringgroup8.studenthousingsolution.controller.UserController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@ApiModel(description="User input for Create Listing")
@RequestMapping("/listing")
public class ListingController{

    @Autowired
    private ListingService listingService;

    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private PropertyService propertyService;


    @GetMapping("/viewLandlordProperties")
    @ApiOperation(value="View Landlord Properties",notes="View list of properties that landlord owns")
    public List<Properties> listProperties(@RequestHeader("Authorization") String authString) {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
           List<Properties> props = propertyService.getPropertiesByLandlord(user);
           int size = props.size();
           return props;

        } catch (Error | NotFoundException e) {

            System.out.println(e);
            return null;
        }
    }


    @PostMapping("/create")
    @ApiOperation(value="Create Listing",notes="Create new property listing and store the data in the database.")
    public Boolean newListing(@RequestBody ListingRequest request, @RequestHeader("Authorization") String str) throws ValidationException {
        try {
            User landlord = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return false;
            }
            listingService.createListingRequest(request,landlord);
            //test();
            return true;
        }
        catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
        }
    }

    @GetMapping("/{propertyID}")
    @ApiOperation(value="View Listing Data")
    public Properties viewListingData(@PathVariable("propertyID") int propertyID, @RequestHeader("Authorization") String authString) {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            Properties property=propertyService.getPropertyByID(propertyID);

            if (userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {

                if (property.getLandlord().equals(user)) {
                    return property;
                } else {
                    throw new ValidationException("User isn't a landlord for this property.");
                }
            }
            else {
                throw new ValidationException("User is not a landlord.");
            }

        } catch (Error | NotFoundException e) {

            System.out.println(e);
            return null;
        }


    }


    //update
    @PostMapping("/updateListing")
    @ApiOperation(value = "Update Property")
    public Boolean updateRequest(@RequestHeader("Authorization") String authString, @RequestBody ListingUpdate update) {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                return false;
            }
            listingService.updateListing(update);
            return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
        }
    }





    @GetMapping("/test")
    @JsonView(PropertyView.ExtendedInfo.class)
    public List<Properties> test(){
        try{
            return propertyService.getAll();
        }catch(ValidationException e){
            return null;
        }
    }

}
