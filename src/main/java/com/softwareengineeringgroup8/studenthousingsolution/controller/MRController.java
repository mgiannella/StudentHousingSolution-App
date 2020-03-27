package com.softwareengineeringgroup8.studenthousingsolution.controller;


import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceRequestData;
import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceUpdateData;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.service.JwtUserDetailsService;
import com.softwareengineeringgroup8.studenthousingsolution.service.MRService;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
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

    //CREATE//
    ///ONLY FOR TENANTS///
    @PutMapping("/create")
    @ApiOperation(value = "Create Maintenance Request")
    public Boolean createRequest(@RequestHeader("Authorization") String authString, @RequestBody MaintenanceRequestData data) throws NoSuchAlgorithmException {
        try {
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
    }

    //only for landlords
    @PostMapping("/update")
    @ApiOperation(value = "Update Maintenance Request")
    public List<MaintenanceRequest> updateRequest(@RequestBody MaintenanceUpdateData data) {
        mrService.updateMaintenanceRequest(data);
        return mrService.listAll();



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
