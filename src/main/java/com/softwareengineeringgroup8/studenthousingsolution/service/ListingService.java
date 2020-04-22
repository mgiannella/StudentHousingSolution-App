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
    @Autowired
    private HousingAgreementRepository agreementRepository;
    @Autowired
    private TenantGroupsRepository tenantGroupsRepository;
    @Autowired
    private TenantGroupMembersRepository tenantGroupMembersRepository;




    public void createListingRequest(ListingRequest request, User landlord){
            //add TenantGroup stuff, fix date stuff, add back landlord to properties
             String latitude = request.getLatitude();
             String longitude = request.getLongitude();

             List<String> unitNum = request.getUnitNum();


             if (unitNum.isEmpty()) { //not an apartment
                 if (locRepository.existsByLatitude(latitude) && locRepository.existsByLongitude(longitude)) {
                     throw new ValidationException("Address already exists.");
                 }
             }

            String address = request.getAddress();
            String city = request.getCity();
            String state = request.getState();
            String zip = request.getZipCode();

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

            Amenities createAmen = new Amenities(price, sleeps, numBedrooms, numBathrooms, renovationDate, hasAC, parkingSpots, hasLaundry, allowPets, allowSmoking,
                    hasWater,hasGasElec,isFurnished,hasAppliances,hasTrashPickup,hasHeat);
            amenRepository.save(createAmen);


            String title=request.getTitle();
            List<PropertyPhotos> photos = new ArrayList<PropertyPhotos>();

            if (!unitNum.isEmpty()) {
                for (int i = 0; i < unitNum.size(); i++) {
                    Properties createProp = new Properties(landlord, title, createAmen, createDesc, createLocation, photos, unitNum.get(i));
                    //photosRepository.save(photos);
                    for (int j = 0; j < request.getPhotos().size(); j++) {
                        createProp.getPhotos().add(new PropertyPhotos(j + 1, request.getPhotos().get(j), createProp));
                    }

                    propRepository.save(createProp);
                }
                return;
            }

        Properties createProp = new Properties(landlord, title, createAmen, createDesc, createLocation, photos, null);
        //photosRepository.save(photos);
        for (int i = 0; i < request.getPhotos().size(); i++) {
            createProp.getPhotos().add(new PropertyPhotos(i + 1, request.getPhotos().get(i), createProp));
        }

        propRepository.save(createProp);

    }




    public void updateListing(ListingUpdate update) {

    int id = update.getId();
    Properties property=getPropertyById(id);


    property.getLocation().setAddress(update.getAddress());
    property.getLocation().setCity(update.getCity());
    property.getLocation().setState(update.getState());
    property.getLocation().setZip(update.getZipCode());





     property.getDescription().setDescContent(update.getDesc());


      property.getAmenities().setPrice(update.getPrice());
      property.getAmenities().setNumBedrooms(update.getNumBedrooms());
      property.getAmenities().setNumBathrooms(update.getNumBathrooms());

      String renDate = update.getRenovationDate();
      property.getAmenities().setRenovationDate(Date.valueOf(renDate));
      property.getAmenities().setHasAC(update.isHasAC());
      property.getAmenities().setPetsAllowed(update.isAllowPets());
      property.getAmenities().setParkingSpots(update.getParkingspots());
      property.getAmenities().setHasLaundry(update.isHasLaundry());
      property.getAmenities().setSmokingAllowed(update.isAllowPets());
      property.getAmenities().setSmokingAllowed(update.isAllowSmoking());
      property.getAmenities().setWaterUtility(update.isHasWater());
      property.getAmenities().setGasElectricUtil(update.isHasGasElec());
      property.getAmenities().setFurnished(update.isFurnished());
      property.getAmenities().setHasAppliances(update.isHasAppliances());
      property.getAmenities().setTrashPickedUpl(update.isHasTrashPickup());
      property.getAmenities().setHasHeat(update.isHasHeat());
      property.getAmenities().setSleeps(update.getSleeps());

      //property.setunitnum(update.getunitnum());

      property.setTitle(update.getTitle());

      String unitNum = property.getUnitNum();

      if (unitNum != null) { //if apartments
          List<Properties> props = propRepository.findByAmenityId(property.getAmenities().getAmenityId());
          System.out.println(props.size());
          for (int i = 0; i<props.size();i++) {
              props.get(i).setTitle(property.getTitle());
              List<PropertyPhotos> photos = props.get(i).getPhotos();
              int size = photos.size();
              for (int k = size; k < (update.getPhotos().size() + size); k++) {
                  int j = 0;
                  props.get(i).getPhotos().add(new PropertyPhotos(k + 1, update.getPhotos().get(j), props.get(i)));
                  j++;
              }

             // HousingAgreement lease = new HousingAgreement(props.get(i), update.getLease(), update.getStartDate(), update.getEndDate());
              //agreementRepository.save(lease);
              propRepository.save(props.get(i));
          }
          return;
      }


      //if not apartments
        List<PropertyPhotos> photos = property.getPhotos();
        int size = photos.size();
        for (int i=size; i<(update.getPhotos().size() + size);i++) {
            int j=0;
            property.getPhotos().add(new PropertyPhotos(i+1,update.getPhotos().get(j),property));
            j++;
        }

        //HousingAgreement lease = new HousingAgreement(property, update.getLease(), update.getStartDate(), update.getEndDate());
        //property.setLease(lease);

        /* for (int i=0; i<request.getPhotos().size(); i++) {
            createProp.getPhotos().add(new PropertyPhotos(i+1,request.getPhotos().get(i),createProp));
        }
    */
       //HousingAgreement lease = new HousingAgreement(property, update.getLease(), update.getStartDate(), update.getEndDate());
       //agreementRepository.save(lease);

      propRepository.save(property);



    }


    public void deleteProp(Properties property) {
        propRepository.delete(property);
        PropertyLocations location = property.getLocation();
        locRepository.delete(location);
        Amenities amenities = property.getAmenities();
        amenRepository.delete(amenities);
        PropertyDescriptions description = property.getDescription();
        descRepository.delete(description);
    }



    public List<TenantGroups> showTenantGroups(String username) {
        User tenant = userRepository.findByUsername(username);
        if (tenant.getType().getUserTypeDesc().equals("Landlord")) {
            throw new ValidationException("User being rented to is a landlord. You can only rent out listings to tenants.");
        }
        List<TenantGroups> tenantGroups = tenantGroupMembersRepository.findTenantGroupByMember(tenant);
        List<TenantGroups> leadOfTheseGroups = new ArrayList<>();
        if (!tenantGroups.isEmpty()) {
            for (int i = 0; i < tenantGroups.size(); i++) {
                if (tenantGroups.get(i).getLeadTenant().equals(tenant)) {
                    leadOfTheseGroups.add(tenantGroups.get(i));
                }
            }
            return leadOfTheseGroups;
        }

        else {
            throw new ValidationException("No tenant groups found.");
        }
    }



    public Properties getPropertyById(int id) throws ValidationException {
        try{
            return propRepository.findById(id);
        }catch(Exception e){
            throw new ValidationException("Couldn't find Property By Id");
        }
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


