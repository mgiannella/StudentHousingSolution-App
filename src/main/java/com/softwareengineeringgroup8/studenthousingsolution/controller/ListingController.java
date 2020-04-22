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

import javax.validation.Valid;
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

    @Autowired
    private NotificationService notificationService;



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
            if (landlord == null) {
                throw new ValidationException("User could be found.");

            }
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
            if (landlord == null) {
                throw new ValidationException("User could be found.");

            }

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

    @GetMapping("/view/{propertyid}")
    @ApiOperation(value="View Listing Data")
    public Properties viewListingData(@PathVariable("propertyid") int propertyid, @RequestHeader("Authorization") String authString) {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (user == null) {
                throw new ValidationException("User could be found.");

            }

            Properties property=propertyService.getPropertyById(propertyid);

            if (userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                if (property.getLandlord().getId()==user.getId()) {
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
            if (user == null) {
                throw new ValidationException("User could be found.");

            }
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
            if (user == null) {
                throw new ValidationException("User could be found.");

            }
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
            if (user == null) {
                throw new ValidationException("User could be found.");

            }
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
            if (user == null) {
                throw new ValidationException("User could be found.");

            }
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                return false;
            }
                Properties property = propertyService.getPropertyById(propertyid);
                TenantGroups group = tenantGroupsService.findById(groupid);
                property.setGroup(group);
                propertiesRepository.save(property);


                notificationService.createNotification(user, "You have rented your property " + property.getLocation().getAddress() + " to " + group.getName() + ".", "GENERAL", "");
                List<User> groupMembers = tenantGroupsService.getTenantsByGroup(group);
                for (int i = 0; i<groupMembers.size();i++) {
                    notificationService.createNotification(groupMembers.get(i), "Your group has now rented " + property.getLocation().getAddress() + ".", "GENERAL", "");
                }

            return true;

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
        }
    }


    @GetMapping("/rent/{propertyid}")
    @ApiOperation(value = "View Tenant Group on property", notes="View tenant group on property")
    public TenantGroups viewRentTenant(@PathVariable("propertyid") int propertyid, @RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (user == null) {
                throw new ValidationException("User could be found.");
            }
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                throw new ValidationException("User is not a landlord.");
            }

            TenantGroups tenantGroup = propertyService.getPropertyById(propertyid).getGroup();
            if (tenantGroup==null) {
                throw new ValidationException("No tenant group residing at property.");
            }

            return tenantGroup;

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }


    @PostMapping("/rent/{pid}")
    @ApiOperation(value = "Take out a tenant group", notes="Delete tenant group from property")
    public Boolean unrentProperty(@PathVariable("pid") int pid, @RequestBody int groupid, @RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (user == null) {
                throw new ValidationException("User could be found.");
            }
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                throw new ValidationException("User is not a landlord.");
            }
            TenantGroups tenantGroup = propertyService.getPropertyById(pid).getGroup();
            if (tenantGroup==null) {
                throw new ValidationException("No tenant group residing at property.");
            }

            Properties prop = propertyService.getPropertyById(pid);
            if (!prop.getGroup().equals(tenantGroup)) {
                throw new ValidationException("Group you are trying to delete does not reside at this property");
            }

            prop.setGroup(null);
            propertiesRepository.save(prop);


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
