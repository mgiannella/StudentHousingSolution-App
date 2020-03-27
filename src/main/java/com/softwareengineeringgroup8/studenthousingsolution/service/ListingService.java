package com.softwareengineeringgroup8.studenthousingsolution.service;


import java.sql.Date;
import java.util.List;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserRepository;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.model.ListingRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.model.Amenities;
import com.softwareengineeringgroup8.studenthousingsolution.model.PropertyLocations;
import com.softwareengineeringgroup8.studenthousingsolution.model.PropertyDescriptions;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertiesRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.AmenitiesRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertyDescriptionsRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertyLocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private UserRepository userRepository;

    public List<Properties> listAll(){
        return propRepository.findAll();
    }

    public void createListingRequest(ListingRequest request){

           /*  MaintenanceStatus status = new MaintenanceStatus("pending", 1); //
            int propertyID = 5;
            Date date = data.getDate();
            String notes = data.getNotes();
            String username = data.getUsername();
            User landlord = userRepository.findByUsername(username);
            mrRepository.save(new MaintenanceRequest(status, propertyID, date, notes, landlord));

            */
    }


    public void updateListing() {

    }

    public Properties gePropertyById(int id) throws ValidationException {

           /* try{
                return mrRepository.findById(id);
            }catch(Exception e){
                throw new ValidationException("Couldn't find Request By Id");
            }
            */
    }


}
}

