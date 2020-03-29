package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.Amenities;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.model.PropertyLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertiesRepository extends JpaRepository<Properties, Integer> {

    @Query("Select p FROM Properties p WHERE (p.amenities IN ?1) and (p.location in ?2)")
    List<Properties> findByAmenityAndLocation(List<Amenities> a, List<PropertyLocations> pl);

}
