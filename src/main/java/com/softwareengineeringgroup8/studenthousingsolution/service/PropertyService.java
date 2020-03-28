package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertiesRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertyDescriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PropertyService {
    @Autowired
    private PropertiesRepository propertyRepository;

    public List<Properties> getAll(){
        return propertyRepository.findAll();
    }

    public Properties getPropertyByGroup(TenantGroups group){ return propertyRepository.findByGroup(group); }

    public Properties getPropertyByLandlord(User landlord){ return propertyRepository.findByLandlord(landlord); }
}
