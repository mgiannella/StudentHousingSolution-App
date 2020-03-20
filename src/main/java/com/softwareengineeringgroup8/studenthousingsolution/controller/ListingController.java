package com.softwareengineeringgroup8.studenthousingsolution.controller;

import com.softwareengineeringgroup8.studenthousingsolution.model.JwtRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.JwtResponse;
import com.softwareengineeringgroup8.studenthousingsolution.model.ListingRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@ApiModel(description="User input for Create Listing")
@RequestMapping("/listing")
public class ListingController{

    @PostMapping("/create")
    public boolean newListing(@RequestBody ListingRequest newListing) {
        return false;
    }


}
