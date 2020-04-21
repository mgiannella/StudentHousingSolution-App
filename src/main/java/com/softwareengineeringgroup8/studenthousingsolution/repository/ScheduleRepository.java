package com.softwareengineeringgroup8.studenthousingsolution.repository;


        import com.softwareengineeringgroup8.studenthousingsolution.model.*;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import java.sql.Timestamp;
        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

        Boolean existsByLandlord(User landlord);

        //DELETE FROM Schedule WHERE MeetingTime BETWEEN '2020-04-27' AND '2020-05-20'
        ///@Query("DELETE s FROM Schedule s WHERE MeetingTime BETWEEN (s.start = ?1 and s.end = ?2)")
        //void deleteByTime(LocalDate start, LocalDate end);


        //SELECT * FROM Schedule WHERE MeetingTime BETWEEN '2020-04-20 08:00:00' AND '2020-04-25 14:00:00' ORDER BY MeetingTime

        @Query("SELECT s FROM Schedule s WHERE s.landlord = ?1 ORDER BY MeetingTime")
        List<Schedule> findTimesByLandlord(User landlord);

        @Query("Select s FROM Schedule s WHERE s.id = ?1")
        Schedule findById(int id);

        @Query("SELECT s FROM Schedule s WHERE s.tenant = ?1 ORDER BY MeetingTime")
        List<Schedule> findTimesByTenant(User tenant);






}
