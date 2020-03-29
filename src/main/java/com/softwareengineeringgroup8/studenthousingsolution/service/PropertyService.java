package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.model.PropertyDescriptions;
import com.softwareengineeringgroup8.studenthousingsolution.model.PropertyLocations;
import com.softwareengineeringgroup8.studenthousingsolution.model.PropertyPhotos;
import com.softwareengineeringgroup8.studenthousingsolution.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public List<Properties> getAll(){
        return propertyRepository.findAll();
    }

    public List<Properties> filterSearch(SearchFilterRequest values){
        List<PropertyLocations> propertyLocationsList = propertyLocationsRepository.findByZip(values.getZip());
        List<Amenities> amenitiesList = amenitiesRepository.filterSearch(values);
        List<Properties> properties = propertyRepository.findByAmenityAndLocation(amenitiesList, propertyLocationsList);
        return properties;
    }
    public boolean create() {
        List<PropertyPhotos> photosList = new ArrayList<PropertyPhotos>();

        Amenities x = amenitiesRepository.findById(1);
        Properties z = new Properties(userRepository.findById(4), "Words", x, propertyDescriptionsRepository.findById(1), propertyLocationsRepository.findById(1), 0,photosList);
        z.getPhotos().add(new PropertyPhotos(1,"name2",z));
        z.getPhotos().add(new PropertyPhotos(2,"name",z));
        propertyRepository.save(z);
        return true;
    }
}
