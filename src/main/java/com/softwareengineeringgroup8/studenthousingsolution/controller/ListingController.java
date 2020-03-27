package com.softwareengineeringgroup8.studenthousingsolution.controller;


import com.softwareengineeringgroup8.studenthousingsolution.model.ListingRequest;


import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserRoles;
import com.softwareengineeringgroup8.studenthousingsolution.service.ListingService;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@ApiModel(description="User input for Create Listing")
@RequestMapping("/listing")
public class ListingController{

    @Autowired
    private ListingService listingService;

    @Autowired
    private UserPermissionService userPermissionService;


    @PostMapping("/create")
    @ApiOperation(value="Create Listing",notes="Create new property listing and store the data in the database.")
    public Boolean newListing(@RequestBody ListingRequest request, @RequestHeader("Authorization") String str) throws NoSuchAlgorithmException {
        try {
            User landlord = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return false;
            }
            listingService.createListingRequest(request,landlord);
            return true;
        }
        catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
        }





       /*  try {
            User x = userPermissionService.loadUserByJWT(authString);
            int userID = x.getId();
            //user is a tenant, get propertyID from user
            int propertyID = 5;
            mrService.createMaintenanceRequest(userID, propertyID, data);
            return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
        }

        */
    }

}
