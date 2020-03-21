package com.softwareengineeringgroup8.studenthousingsolution.controller;


import com.softwareengineeringgroup8.studenthousingsolution.model.ListingRequest;

import com.softwareengineeringgroup8.studenthousingsolution.model.RegisterRequest;
import com.softwareengineeringgroup8.studenthousingsolution.repository.ListingRepository;
import com.softwareengineeringgroup8.studenthousingsolution.service.ListingService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@ApiModel(description="User input for Create Listing")
@RequestMapping("/listing")
public class ListingController{

    @Autowired
    private ListingService service;



    @PostMapping("/create")
    @ApiOperation(value="Create Listing",notes="Create new property listing and store the data in the database.")
    public Boolean newListing(@RequestBody ListingRequest newListing) throws NoSuchAlgorithmException {
        try {
            service.create(newListing);
            return true;
        }catch(Error e){
            System.out.println(e);
            return false;
        }
    }

}
