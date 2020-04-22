package com.softwareengineeringgroup8.studenthousingsolution.controller;



import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceRequestData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.NoSuchAlgorithmException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.*;
import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.List;

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


    @GetMapping("/viewTenantProperties")
    @ApiOperation(value =  "View Tenant Properties")
    public List<Properties> listProperties(@RequestHeader("Authorization") String authString){
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            List<Properties> propertiesList = new ArrayList<Properties>();
            List<TenantGroups> tenantGroupsList = tenantGroupsService.getGroupByTenant(user);
            int size = tenantGroupsList.size();
            for (int i = 0; i < size; i++) {
                TenantGroups tg = tenantGroupsList.get(i);
                List<Properties> props = propertyService.getPropertiesByGroup(tg);
                for(int j = 0; j < props.size(); j++) {
                    propertiesList.add(props.get(j));
                }
            }
            return propertiesList;
        } catch (Error | NotFoundException e) {

            System.out.println(e);
            return null;
        }


    }
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
            //List<TenantGroups> tenantGroupsList = tenantGroupsService.getGroupByTenant(user);
            //TenantGroups tenantGroup = tenantGroupsList.get(0);
            //Properties prop = propertyService.getPropertyByGroup(tenantGroup);
            mrService.createMaintenanceRequest(user, data);
            return true;
        } catch (Error | NotFoundException e) {

            System.out.println(e);
            return false;
        }
    }

    @GetMapping("/viewRequests")
    @ApiOperation(value = "View Requests")
    public List<List<MaintenanceRequest>> viewRequest(@RequestHeader("Authorization") String authString){
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                return null;
            }
            List<List<MaintenanceRequest>> requests = new ArrayList<List<MaintenanceRequest>>();
            List<Properties> properties = propertyService.getPropertiesByLandlord(user);
            int numOfProperties = properties.size();
            for(int i = 0; i < numOfProperties; i++){
                Properties prop = properties.get(i);
                requests.add(mrService.getRequestByProperty(prop));
            }
            return requests;
        } catch (Error| NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    //update
    @PostMapping("/{requestID}")
    @ApiOperation(value = "Update Maintenance Request")
    public Boolean updateRequest(@PathVariable("requestID") int requestID, @RequestHeader("Authorization") String authString, @RequestBody MaintenanceUpdateData data) {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)) {
                return false;
            }
            MaintenanceRequest request = mrService.getRequestById(requestID);
            mrService.updateMaintenanceRequest(request, data);
            return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
        }
    }

}
