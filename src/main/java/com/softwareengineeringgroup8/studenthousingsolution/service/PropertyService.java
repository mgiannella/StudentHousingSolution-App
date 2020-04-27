package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertyDescriptionsRepository;
import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.model.PropertyLocations;
import com.softwareengineeringgroup8.studenthousingsolution.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PropertyService {
    @Autowired
    private PropertiesRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AmenitiesRepository amenitiesRepository;

    @Autowired
    private PropertyLocationsRepository propertyLocationsRepository;

    @Autowired
    private PropertyDescriptionsRepository propertyDescriptionsRepository;

    @Autowired
    private PropertyPhotosRepository propertyPhotosRepository;

    // returns all properties
    public List<Properties> getAll(){
        return propertyRepository.findAll();
    }

    public Properties getById(int id){
        Properties prop=propertyRepository.findById(id);
        if(prop != null && prop.getGroup() == null){
            return prop;
        }
        return null;
    }

    public Properties getPropertyById(int id){
        return propertyRepository.findById(id);
    }

    public Properties getPropertyByGroup(TenantGroups group){ return propertyRepository.findByGroup(group); }

    public List<Properties> getPropertiesByGroup(TenantGroups group){ return propertyRepository.findByTenantGroup(group); }

    public List<Properties> getPropertiesByLandlord(User landlord){ return propertyRepository.findByLandlord(landlord); }
  

    // returns all properties within a certain zip code
    public List<Properties> getByZip(String zip) throws ValidationException{
        try{
            List<PropertyLocations> propertyLocationsList = propertyLocationsRepository.findByZip(zip);
            List<Properties> properties = propertyRepository.findByLocations(propertyLocationsList);
            return properties;
        }catch(Error e){
            throw new ValidationException("Invalid input");
        }
    }

    // returns all properties that fit filters
    public List<Properties> filterSearch(SearchFilterRequest values) throws ValidationException{
        try {
            List<PropertyLocations> propertyLocationsList = propertyLocationsRepository.findByZip(values.getZip());
            List<Amenities> amenitiesList = amenitiesRepository.filterSearch(values);
            List<Properties> properties = propertyRepository.findByAmenityAndLocation(amenitiesList, propertyLocationsList);
            return properties;
        }catch(Error e){
            throw new ValidationException("Invalid input, check filters and try again");
        }
    }

    /* Test Method to show how to create properties
    public boolean create() {
        List<PropertyPhotos> photosList = new ArrayList<PropertyPhotos>();
        Amenities x = amenitiesRepository.findById(1);
        Properties z = new Properties(userRepository.findById(4), "Words", x, propertyDescriptionsRepository.findById(1), propertyLocationsRepository.findById(1), 0,photosList);
        z.getPhotos().add(new PropertyPhotos(1,"name2",z));
        z.getPhotos().add(new PropertyPhotos(2,"name",z));
        propertyRepository.save(z);
        return true;
    }*/
}
