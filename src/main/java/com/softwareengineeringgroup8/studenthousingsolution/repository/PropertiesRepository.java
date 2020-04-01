package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.model.Amenities;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.model.PropertyLocations;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertiesRepository extends JpaRepository<Properties, Integer> {


    @Query("SELECT prop FROM Properties prop WHERE prop.group = ?1")
    Properties findByGroup(TenantGroups group);

    @Query("SELECT prop FROM Properties prop WHERE prop.landlord = ?1")
    List<Properties> findByLandlord(User landlord);

    @Query("SELECT prop FROM Properties prop WHERE prop.id = ?1")
    Properties findByPropertyID(int propertyID);

    @Query("Select p FROM Properties p WHERE (p.amenities IN ?1) and (p.location in ?2)")
    List<Properties> findByAmenityAndLocation(List<Amenities> a, List<PropertyLocations> pl);

    @Query("Select p FROM Properties p WHERE p.location in ?1")
    List<Properties> findByLocations(List<PropertyLocations> pl);

    @Query("Select p FROM Properties p WHERE p.group = ?1")
    List<Properties> findByTenantGroup(TenantGroups tg);


}
