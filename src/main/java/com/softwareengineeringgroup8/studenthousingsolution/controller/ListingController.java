package com.softwareengineeringgroup8.studenthousingsolution.controller;


import com.softwareengineeringgroup8.studenthousingsolution.model.ListingRequest;

//import com.softwareengineeringgroup8.studenthousingsolution.repository.ListingRepository;
//import com.softwareengineeringgroup8.studenthousingsolution.service.ListingService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@ApiModel(description="User input for Create Listing")
@RequestMapping("/listing")
public class ListingController{

    //@Autowired
   // private ListingService service;



    @PostMapping("/create")
    @ApiOperation(value="Create Listing",notes="Create new property listing and store the data in the database.")
    public Boolean newListing(@RequestBody ListingRequest request) throws NoSuchAlgorithmException {
        String title = request.getTitle();
        String address = request.getAddress();
        String city = request.getCity();
        String state = request.getState();
        String zipCode = request.getZipCode();
        Date renovationDate=request.getRenovationDate();
        double price = request.getPrice();
        int numBedrooms = request.getNumBedrooms();
        double numBathrooms = request.getNumBathrooms();
        boolean hasAC=request.isHasAC();
        int parkingspots = request.getParkingspots();
        boolean hasLaundry=request.isHasLaundry();
        boolean allowPets = request.isAllowPets();
        boolean allowSmoking = request.isAllowSmoking();

        System.out.println(zipCode);
        return false;
       /* try {
            service.create(newListing);
            return true;
        }catch(Error e){
            System.out.println(e);
            return false;
        }

        */
    }

}
