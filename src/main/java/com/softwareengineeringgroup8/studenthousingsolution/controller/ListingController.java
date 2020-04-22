package com.softwareengineeringgroup8.studenthousingsolution.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;


import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertiesRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.TenantGroupsRepository;
import com.softwareengineeringgroup8.studenthousingsolution.service.ListingService;
import com.softwareengineeringgroup8.studenthousingsolution.service.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
import com.softwareengineeringgroup8.studenthousingsolution.service.HousingAgreementService;
import com.softwareengineeringgroup8.studenthousingsolution.controller.UserController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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

    @Autowired
    private HousingAgreementService agreementService;

    @Autowired
    private TenantGroupsService tenantGroupsService;

    @Autowired
    private PropertiesRepository propertiesRepository;



    @GetMapping("/viewLease/{propertyID}")
    @ApiOperation(value="View Lease")
    public HousingAgreement tenantLease(@RequestHeader("Authorization") String authString, @PathVariable("propertyID") int propertyID){
        try{
            User user = userPermissionService.loadUserByJWT(authString);
            Properties prop = propertyService.getPropertyById(propertyID);
            return agreementService.getHousingAgreement(prop);

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }

    }



    @GetMapping("/viewLandlordProperties")
    @ApiOperation(value="View Landlord Properties",notes="View list of properties that landlord owns")
    public List<Properties> listProperties(@RequestHeader("Authorization") String authString) {
        try {
            User landlord = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                throw new ValidationException("User is not a landlord");
            }
           List<Properties> props = propertyService.getPropertiesByLandlord(landlord);
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
                throw new ValidationException("User is not a landlord");
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
    @JsonView(PropertyView.ViewProperty.class)
    public Properties viewListingData(@PathVariable("propertyID") int propertyID, @RequestHeader("Authorization") String authString) {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                throw new ValidationException("User is not a landlord");
            }

            Properties property=propertyService.getById(propertyID);

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
    @ApiOperation(value = "Update Property", notes="updating property")
    public Boolean updateRequest(@RequestBody ListingUpdate update, @RequestHeader("Authorization") String authString) throws ValidationException {
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


    @DeleteMapping("/delete/{deleteID}")
    @ApiOperation(value="Delete Property ")
    public Boolean deleteListing(@PathVariable("deleteID") int deleteID, @RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            Properties property=propertyService.getById(deleteID);

            if (userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {

                if (property.getLandlord().equals(user)) {
                    listingService.deleteProp(property);
                    return true;
                } else {
                    throw new ValidationException("Cannot delete another landlord's property ");
                }
            }
            else {
                throw new ValidationException("User is not a landlord.");
            }

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
        }
    }


    //rent
    @GetMapping("/listtenantgroups")
    @ApiOperation(value = "List Tenant Groups for Lead Tenant", notes="List tenant Groups for lead tenant")
    public List<TenantGroups> ListTenantGroups(@RequestParam String username, @RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                return null;
            }
            return listingService.showTenantGroups(username);

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    //rent
    @PostMapping("/{propertyid}")
    @ApiOperation(value = "Rent Listing", notes="Rent Listing")
    public Boolean rentOutListing(@PathVariable("propertyid") int propertyid, @RequestBody int groupid, @RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                return false;
            }
                Properties property = propertyService.getById(propertyid);
                TenantGroups group = tenantGroupsService.findById(groupid);
                property.setGroup(group);
                propertiesRepository.save(property);
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
