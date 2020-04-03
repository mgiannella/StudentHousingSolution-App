package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.PropertyDescriptions;
import com.softwareengineeringgroup8.studenthousingsolution.model.PropertyLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PropertyLocationsRepository extends JpaRepository<PropertyLocations,Integer> {

    List<PropertyLocations> findByZip(String zip);
    List<PropertyLocations> findByLatitude(String latitude);
    List<PropertyLocations> findByLongitude(String longitude);
    PropertyLocations findById(int id);
}