package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.Amenities;
import com.softwareengineeringgroup8.studenthousingsolution.model.SearchFilterRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities,Integer> {
    Amenities findById(int id);

    @Query("SELECT a FROM Amenities a WHERE (a.price>= :#{#filter.price.min} and a.price<= :#{#filter.price.max})"
            + "and (a.sleeps>= :#{#filter.sleeps.min} and a.sleeps<= :#{#filter.sleeps.max}) "
            + "and (a.price>= :#{#filter.price.min} and a.price<= :#{#filter.price.max}) "
            + "and (a.numBedrooms>= :#{#filter.bedrooms.min} and a.numBedrooms<= :#{#filter.bedrooms.max}) "
            + "and (a.numBathrooms>= :#{#filter.bathrooms.min} and a.numBathrooms <= :#{#filter.bathrooms.max}) "
            + "and (a.hasAC >= :#{#filter.hasAC}) "
            + "and (a.hasLaundry >= :#{#filter.hasLaundry}) "
            + "and (a.parkingSpots >= :#{#filter.parking.min} and a.parkingSpots <= :#{#filter.parking.max}) "
            + "and (a.petsAllowed >= :#{#filter.petsAllowed}) "
            + "and (a.smokingAllowed >= :#{#filter.smokingAllowed}) "
            + "and (a.waterUtility >= :#{#filter.waterUtility}) "
            + "and (a.gasElectricUtil >= :#{#filter.gasElectricUtil}) "
            + "and (a.isFurnished >= :#{#filter.furnished}) "
            + "and (a.hasAppliances >= :#{#filter.hasAppliances}) "
            + "and (a.isTrashPickedUpl >= :#{#filter.trashPickedUpl}) "
            + "and (a.hasHeat >= :#{#filter.hasHeat})")
    List<Amenities> filterSearch(@Param("filter")SearchFilterRequest values);
}