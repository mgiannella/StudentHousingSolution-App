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

    //CREATE//
    ///ONLY FOR TENANTS///
    @PutMapping("/create")
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

    //only for landlords
    @PostMapping("/update")
    @ApiOperation(value = "Update Maintenance Request")
    public Boolean updateRequest(@RequestHeader("Authorization") String authString, @RequestBody MaintenanceUpdateData data) {
        try {
            User landlord = userPermissionService.loadUserByJWT(authString);
            Properties prop = propertyService.getPropertyByLandlord(landlord);
            List<MaintenanceRequest> requests = mrService.getRequestByProperty(prop);
            MaintenanceRequest request = requests.get(0);
            mrService.updateMaintenanceRequest(request, data);
            return true;
        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
        }
    }

    /*
    @RequestMapping(value = "/send", method = RequestMethod.POST) //only for tenants
    public String sendRequest(@ModelAttribute("request") MaintenanceRequest request) {
        service.save(request);
        return "";
    }

    //update Maintenance Request//
    ///ONLY FOR LANDLORDS///
    @RequestMapping("/manageRequests")
    public ResponseEntity<?> manageRequest(@PathVariable(name = "requestID") int requestID){
        ModelAndView mav = new ModelAndView("manage_request");
        //
        MaintenanceRequest request = service.get(requestID);
        mav.addObject("request", request);
        return ResponseEntity.ok(new MaintenanceRequest());
    }
    */



}
