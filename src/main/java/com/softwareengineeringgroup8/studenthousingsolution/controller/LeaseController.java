/*
written by: Sneh Shah
tested by: Sneh Shah
debugged by: Sneh Shah
*/

package com.softwareengineeringgroup8.studenthousingsolution.controller;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.HousingAgreementService;
import com.softwareengineeringgroup8.studenthousingsolution.service.PropertyService;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin(origins="http://localhost:3000")
@ApiModel(description="Housing Agreement")
@RequestMapping("/lease")
public class LeaseController {

    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private HousingAgreementService agreementService;

    @PostMapping("/uploadLease")
    @ApiOperation(value = "Upload Lease")
    public Boolean leaseUpload(@RequestHeader("Authorization") String authString, @RequestBody LeaseUpdate lease){
        try{
            User landlord = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return false;
            }
            agreementService.uploadLease(lease);
            return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false; }
    }

    @DeleteMapping("/deleteLease/{propertyID}")
    @ApiOperation(value = "Delete Lease")
    public Boolean deleteLease(@RequestHeader("Authorization") String authString, @PathVariable("propertyID") int propertyID){
        try{
            User landlord = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return false;
            }
            agreementService.deleteLease(propertyID);
            return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false; }
    }

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

    @PostMapping("/signLease")
    @ApiOperation(value = "Tenants Signing Lease")
    public Boolean leaseSign(@RequestHeader("Authorization") String authString, @RequestBody SignLease lease){
        try {
            User tenant = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return false;
            }
            agreementService.signLease(tenant, lease);
            return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }
    @GetMapping("/viewTenants/{propertyID}")
    @ApiOperation(value="View Tenants")
    public List<TenantGroupMembers> viewTenants(@RequestHeader("Authorization") String authString, @PathVariable("propertyID") int propertyID){
        try{
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                throw new ValidationException("User is not a landlord");
            }
            return agreementService.getTenants(propertyID);
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/signedLease/{propertyID}")
    @ApiOperation(value="Check if tenant signed lease")
    public Boolean leaseCheck(@RequestHeader("Authorization") String authString, @PathVariable("propertyID") int propertyID) {
        try {
            User tenant = userPermissionService.loadUserByJWT(authString);
            if(!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                throw new ValidationException("User is not a tenant");
            }
            return agreementService.leaseSigned(tenant, propertyID);
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            throw new ValidationException("Error");
        }
    }

    @GetMapping("/signedTenants/{propertyID}")
    @ApiOperation(value = "Tenants Who Signed Lease")
    public List<TenantGroupMembers> signedTenants(@RequestHeader("Authorization") String authString, @PathVariable int propertyID){
        try {
            User landlord = userPermissionService.loadUserByJWT(authString);
            if(!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                throw new ValidationException("User is not a tenant");
            }
            return agreementService.getTenants(propertyID);
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            throw new ValidationException("Error");
        }
    }
}
