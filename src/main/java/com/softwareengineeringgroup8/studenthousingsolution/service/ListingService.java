package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.model.ListingRequest;
import com.softwareengineeringgroup8.studenthousingsolution.repository.ListingRepository;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.lang.reflect.Array;
import java.util.ArrayList;

@Component
public class ListingService {

    @Autowired
    private ListingRepository repo;

    public void create(ListingRequest request) {
        String title = request.getTitle();
        String address = request.getAddress();
        String city = request.getCity();
        String state = request.getState();
        String zipCode = request.getZipCode();
        double price = request.getPrice();
        int numBedrooms = request.getNumBedrooms();
        int numBathrooms = request.getNumBathrooms();
        boolean hasAC=request.isHasAC();
        int parkingspots = request.getParkingspots();
        boolean hasLaundry=request.isHasLaundry();
        boolean allowPets = request.isAllowPets();
        boolean allowSmoking = request.isAllowSmoking();

        repo.save(new ListingRequest(title,address,city,state,zipCode,price,numBedrooms,numBathrooms, hasAC,parkingspots,hasLaundry,allowPets,allowSmoking));
    }


}
