package com.softwareengineeringgroup8.studenthousingsolution.controller;


import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.aspectj.weaver.ast.Not;
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
            //int userID = x.getId(); //userid
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
   /* @PostMapping("/update")
    @ApiOperation(value = "Update Maintenance Request")
    public List<MaintenanceRequest> updateRequest(@RequestHeader("Authorization") String authString, @RequestBody MaintenanceUpdateData data) {
        try {
            User x = userPermissionService.loadUserByJWT(authString);
            int userID = x.getId();
            int propertyID = 5;
            return mrService.updateMaintenanceRequest(userID, propertyID, data);

        } catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }
        }

    */











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
