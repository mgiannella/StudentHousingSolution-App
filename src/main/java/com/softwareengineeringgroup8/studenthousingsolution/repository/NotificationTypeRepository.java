package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.NotificationType;
import com.softwareengineeringgroup8.studenthousingsolution.model.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationType,Integer> {

}