package com.softwareengineeringgroup8.studenthousingsolution.service;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.repository.*;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Date;

import com.sun.javaws.jnl.PropertyDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.util.stream.Stream;

import javax.validation.Valid;

@Component
public class ListingService {

    @Autowired
    private PropertiesRepository propRepository;
    @Autowired
    private AmenitiesRepository amenRepository;
    @Autowired
    private PropertyDescriptionsRepository descRepository;
    @Autowired
    private PropertyLocationsRepository locRepository;
    @Autowired
    private PropertyPhotosRepository photosRepository;
    @Autowired
    private UserRepository userRepository;

    public void createListingRequest(ListingRequest request, User landlord){
            //add TenantGroup stuff, fix date stuff, add back landlord to properties
             String latitude = request.getLatitude();
             String longitude = request.getLongitude();
             if (locRepository.existsByLatitude(latitude) && locRepository.existsByLongitude(longitude)) {
                 throw new ValidationException("Address already exists.");
             }

            String address = request.getAddress();
            String city = request.getCity();
            String state = request.getState();
            String zip = request.getZipCode();

            String latitude = request.getLatitude();
            String longitude = request.getLongitude();


            PropertyLocations createLocation = new PropertyLocations(address,city,state,zip,latitude,longitude);
            locRepository.save(createLocation);

            String desc = request.getDesc();
            PropertyDescriptions createDesc = new PropertyDescriptions(desc);
            descRepository.save(createDesc);

            BigDecimal price = request.getPrice();
            int numBedrooms = request.getNumBedrooms();
            float numBathrooms = request.getNumBathrooms();

            String renDate = request.getRenovationDate();
            Date renovationDate = Date.valueOf(renDate);

            boolean hasAC = request.isHasAC();
            int parkingSpots = request.getParkingspots();
            boolean hasLaundry = request.isHasLaundry();
            boolean allowPets = request.isAllowPets();
            boolean allowSmoking = request.isAllowSmoking();
            boolean hasWater = request.isHasWater();
            boolean hasGasElec = request.isHasGasElec();
            boolean isFurnished = request.isFurnished();
            boolean hasAppliances = request.isHasAppliances();
            boolean hasTrashPickup = request.isHasTrashPickup();
            boolean hasHeat = request.isHasHeat();
            int sleeps=request.getSleeps();

            System.out.println(numBathrooms);
            Amenities createAmen = new Amenities(price, sleeps, numBedrooms, numBathrooms, renovationDate, hasAC, parkingSpots, hasLaundry, allowPets, allowSmoking,
                    hasWater,hasGasElec,isFurnished,hasAppliances,hasTrashPickup,hasHeat);
            amenRepository.save(createAmen);


            String title=request.getTitle();
            List<PropertyPhotos> photos = new ArrayList<PropertyPhotos>();

            Properties createProp = new Properties(landlord,title, createAmen, createDesc, createLocation, 0,photos);
            for (int i=0; i<request.getPhotos().size();i++) {
                createProp.getPhotos().add(new PropertyPhotos(i+1,request.getPhotos().get(i),createProp));
            }

            /*
            if (!request.getPhotos().equals("")) {
                createProp.getPhotos().add(new PropertyPhotos(1,request.getPhotos(),createProp));
            }
             */
            //photosRepository.save(photos);
            propRepository.save(createProp);
    }



    public void updateListing(Properties property, ListingUpdate update) {
      property.getDescription().setDescContent("Updated");
      propRepository.save(property);

      /* description.setDescContent(update.getDesc());
       descRepository.save(description);




        amenities.setPrice(update.getPrice());
        amenities.setNumBedrooms(update.getNumBedrooms());
        amenities.setNumBathrooms(update.getNumBathrooms());

        String renDate = update.getRenovationDate();
        amenities.setRenovationDate(Date.valueOf(renDate));

        amenities.setHasAC(update.isHasAC());
        amenities.setParkingSpots(update.getParkingspots());
        amenities.setHasLaundry(update.isHasLaundry());
        amenities.setSmokingAllowed(update.isAllowPets());
        amenities.setSmokingAllowed(update.isAllowSmoking());
        amenities.setWaterUtility(update.isHasWater());
        amenities.setGasElectricUtil(update.isHasGasElec());
        amenities.setFurnished(update.isFurnished());
        amenities.setHasAppliances(update.isHasAppliances());
        amenities.setTrashPickedUpl(update.isHasTrashPickup());
        amenities.setHasHeat(update.isHasHeat());
        amenities.setSleeps(update.getSleeps());

        amenRepository.save(amenities);


        property.setTitle(update.getTitle());


        //photosRepository.save(photos);
        propRepository.save(property);
       */
    }







    public Properties getPropertyById(int id) throws ValidationException {
        try{
            return propRepository.findByPropertyID(id);
        }catch(Exception e){
            throw new ValidationException("Couldn't find Property By Id");
        }
    }





    public void updateLease() {
        //if landlord wants to update his listing bc he got tenants, upload lease, etc..
    }

    public void uploadPhoto(ListingRequest request, MultipartFile[] photos ) {
        //photos

    }

    /*
    public Properties gePropertyById(int id) throws ValidationException {

           try{
                return PropertiesRepository.findById(id);
            }catch(Exception e){
                throw new ValidationException("Couldn't find Request By Id");
            }
    }
*/

    }


