package com.softwareengineeringgroup8.studenthousingsolution.controller;


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
            User tenant = userPermissionService.loadUserByJWT(authString);
            List<TenantGroups> tenantGroupsList = tenantGroupsService.getGroupByTenant(tenant);
            TenantGroups tenantGroup = tenantGroupsList.get(0);
            Properties prop = propertyService.getPropertyByGroup(tenantGroup);
            mrService.createMaintenanceRequest(tenant, prop, data);
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
            User landlord = userPermissionService.loadUserByJWT(authString);
            Properties prop = propertyService.getPropertyByLandlord(landlord);
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
            User landlord = userPermissionService.loadUserByJWT(authString);
            Properties prop = propertyService.getPropertyByLandlord(landlord);
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
