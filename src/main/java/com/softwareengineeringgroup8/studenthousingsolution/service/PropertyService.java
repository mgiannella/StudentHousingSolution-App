package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertiesRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertyDescriptionsRepository;
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


    public Properties getPropertyByGroup(TenantGroups group){ return propertyRepository.findByGroup(group); }

    public Properties getPropertyByLandlord(User landlord){ return propertyRepository.findByLandlord(landlord); }

    public boolean create() {
        List<PropertyPhotos> photosList = new ArrayList<PropertyPhotos>();
        photosList.add(new PropertyPhotos(1,"yourmomshouse"));
        photosList.add(new PropertyPhotos(2,"yoursistersbutt"));
        Amenities x = amenitiesRepository.findById(1);
        propertyRepository.save(new Properties(userRepository.findById(4), "Words", x, propertyDescriptionsRepository.findById(1), propertyLocationsRepository.findById(1), 0,photosList));
        return true;
    }

}
