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

@RestController
@ApiModel(description = "Manages maintenance requests")
@RequestMapping("/maintenanceRequests")

public class MRController {

    @Autowired
    private MRService mrService;

    //CREATE//
    ///ONLY FOR TENANTS///
    @PostMapping("/create")
    @ApiOperation(value = "Create Maintenance Request")
    public Boolean createRequest(@RequestBody MaintenanceRequestData data) throws NoSuchAlgorithmException {
        try {
            mrService.createMaintenanceRequest(data);
            return true;
        } catch(Error e){
            System.out.println(e);
            return false;
        }
    }

    //only for landlords
    @PostMapping("/update")
    @ApiOperation(value = "Update Maintenance Request")
    public Boolean updateRequest(){
        return true;
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
