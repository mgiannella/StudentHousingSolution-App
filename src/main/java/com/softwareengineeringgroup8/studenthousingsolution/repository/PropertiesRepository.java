package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface PropertiesRepository extends JpaRepository<Properties, Integer> {


    @Query("SELECT prop FROM Properties prop WHERE prop.group = ?1")
    Properties findByGroup(TenantGroups group);

    @Query("SELECT prop FROM Properties prop WHERE prop.landlord = ?1")
    List<Properties> findByLandlord(User landlord);

    @Query("SELECT prop FROM Properties prop WHERE prop.id = ?1")
    Properties findById(int id);

    @Query("SELECT prop FROM Properties prop WHERE prop.id = ?1")
    Properties findByPropertyID(int id);

    @Query("SELECT p FROM Properties p LEFT JOIN" +
    " Properties p2 ON p.id > p2.id AND p.amenities = p2.amenities " +
    " WHERE (p2.amenities is null) and (p.group is null) and (p.location in ?2) and (p.amenities in ?1)")
    List<Properties> findByAmenityAndLocation(List<Amenities> a, List<PropertyLocations> pl);

    @Query("SELECT p FROM Properties p LEFT JOIN" +
            " Properties p2 ON p.id > p2.id AND p.amenities = p2.amenities " +
            " WHERE (p2.amenities is null) and (p.group is null) and (p.location in ?1)")
    List<Properties> findByLocations(List<PropertyLocations> pl);

    @Query("Select p FROM Properties p WHERE p.group = ?1")
    List<Properties> findByTenantGroup(TenantGroups tg);

    @Query("SELECT p FROM Properties p LEFT JOIN" +
            " Properties p2 ON p.id > p2.id AND p.amenities = p2.amenities " +
            " WHERE (p2.amenities is null) and (p.group is null) and (p.amenities.price>= :#{#ea.minPrice} and p.amenities.price<= :#{#ea.maxPrice})"
            + "and (p.amenities.sleeps>= :#{#ea.minSleeps} and p.amenities.sleeps<= :#{#ea.maxSleeps}) "
            + "and (p.amenities.numBedrooms>= :#{#ea.minBed} and p.amenities.numBedrooms<= :#{#ea.maxBed}) "
            + "and (p.amenities.numBathrooms>= :#{#ea.minBath} and p.amenities.numBathrooms <= :#{#ea.maxBath}) "
            + "and(p.uploadTS >= :startDT and p.uploadTS <= :endDT) "
            + "and (p.location.zip = :#{#ea.zip})")
    List<Properties> emailDigestSearch(@Param("ea") EmailAmenities ea, @Param("startDT")Timestamp startDT, @Param("endDT")Timestamp endDT);
}