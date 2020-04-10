package com.softwareengineeringgroup8.studenthousingsolution.controller;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroupMembers;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserRoles;
import com.softwareengineeringgroup8.studenthousingsolution.service.PropertyService;
import com.softwareengineeringgroup8.studenthousingsolution.service.TenantGroupsService;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import java.util.List;

@RestController
@ApiModel(description="Handles all TenantGroups Requests")
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/groups")
public class TenantGroupsController {
    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private TenantGroupsService tenantGroupsService;

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/view/invites")
    @ApiOperation(value="Display inviations", notes="Get all of your pending invites")
    public List<TenantGroupMembers> viewInvites(@RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT))
                throw new ValidationException("User is not a tenant");

            return tenantGroupsService.viewInvitations(user);
        }catch(Exception e){
           System.out.println(e);
           throw new ValidationException(e.toString());
        }
    }

    @GetMapping("/view/groups")
    @ApiOperation(value="Display Groups", notes="Get all of your subscribed group")
    public List<TenantGroups> viewGroups(@RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT))
                throw new ValidationException("User is not a tenant");
            return tenantGroupsService.getGroupByTenant(user);
        }catch(Exception e){
            System.out.println(e);
            throw new ValidationException(e.toString());
        }
    }

}
