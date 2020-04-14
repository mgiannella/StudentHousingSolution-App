package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.PropertyLocations;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserPhotosRepository extends JpaRepository<UserPhotos,Integer> {
}