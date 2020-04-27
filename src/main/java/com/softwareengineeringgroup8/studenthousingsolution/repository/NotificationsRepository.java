package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.Amenities;
import com.softwareengineeringgroup8.studenthousingsolution.model.Notifications;
import com.softwareengineeringgroup8.studenthousingsolution.model.SearchFilterRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


@Repository
public interface NotificationsRepository extends JpaRepository<Notifications,Integer> {
    @Query("SELECT n FROM Notifications n WHERE (n.user = ?1) and (n.alertDT < ?2)")
    List<Notifications> getNotificationsByUserAndDate(User u, Timestamp dt);

    @Query("Select COUNT(n) FROM Notifications n WHERE (n.user=?1) and (n.alertDT < ?2)")
    int getNotificationAmountByUserAndDate(User u, Timestamp dt);

    Notifications findById(int id);
}