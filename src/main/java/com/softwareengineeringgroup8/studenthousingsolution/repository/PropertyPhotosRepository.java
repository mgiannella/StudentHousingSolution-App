//written by: Michael Giannella
//tested by: Michael Giannella
//debugged by: Michael Giannella
package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.Amenities;
import com.softwareengineeringgroup8.studenthousingsolution.model.PropertyPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PropertyPhotosRepository extends JpaRepository<PropertyPhotos,Integer> {

}