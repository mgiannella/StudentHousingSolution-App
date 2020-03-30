package com.softwareengineeringgroup8.studenthousingsolution.controller;



import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceRequestData;
import com.softwareengineeringgroup8.studenthousingsolution.repository.MaintenanceStatusRepository;
import com.softwareengineeringgroup8.studenthousingsolution.service.MRService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.NoSuchAlgorithmException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@ApiModel(description = "Manages maintenance requests")
@RequestMapping("/maintenanceRequests")

public class MRController {

    @Autowired
    private MRService mrService;
    @Autowired
    private UserPermissionService userPermissionService;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private TenantGroupsService tenantGroupsService;
    @Autowired
    private PropertyService propertyService;

    //create
    @PostMapping("/createRequest")
    @ApiOperation(value = "Create Maintenance Request")
    public Boolean createRequest(@RequestHeader("Authorization") String authString, @RequestBody MaintenanceRequestData data) throws NoSuchAlgorithmException {
        try {

            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT)) {
                return false;
            }
            if(data.getNotes().equals("")){
                return false;
            }
            List<TenantGroups> tenantGroupsList = tenantGroupsService.getGroupByTenant(user);
            TenantGroups tenantGroup = tenantGroupsList.get(0);
            Properties prop = propertyService.getPropertyByGroup(tenantGroup);
            mrService.createMaintenanceRequest(user, prop, data);
            return true;
        } catch (Error | NotFoundException e) {

            System.out.println(e);
            return false;
        }
    }


    @PostMapping("/viewRequests")
    @ApiOperation(value = "View Requests")
    public List<MaintenanceRequest> viewRequest(@RequestHeader("Authorization") String authString){
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                return null;
            }
            Properties prop = propertyService.getPropertyByLandlord(user);
            return mrService.getRequestByProperty(prop);
        } catch (Error| NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    //update
    @PostMapping("/{id}")
    @ApiOperation(value = "Update Maintenance Request")
    public Boolean updateRequest(@PathVariable("id") int id, @RequestHeader("Authorization") String authString, @RequestBody MaintenanceUpdateData data) {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                return false;
            }
            Properties prop = propertyService.getPropertyByLandlord(user);
            List<MaintenanceRequest> requests = mrService.getRequestByProperty(prop);
            MaintenanceRequest request = mrService.getRequestById(id);
            mrService.updateMaintenanceRequest(request, data);
            return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
        }
    }

}
