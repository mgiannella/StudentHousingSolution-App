package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.Amenities;
import com.softwareengineeringgroup8.studenthousingsolution.model.Notifications;
import com.softwareengineeringgroup8.studenthousingsolution.model.SearchFilterRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotificationsRepository extends JpaRepository<Notifications,Integer> {

}