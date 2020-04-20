package com.softwareengineeringgroup8.studenthousingsolution.repository;


        import com.softwareengineeringgroup8.studenthousingsolution.model.*;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import java.sql.Timestamp;
        import java.time.LocalDateTime;
        import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

        Boolean existsByLandlord(User landlord);

        @Query("SELECT s FROM Schedule s WHERE s.landlord = ?1 ORDER BY MeetingTime")
        List<Schedule> findTimesByLandlord(User landlord);

        @Query("Select s FROM Schedule s WHERE s.id = ?1")
        Schedule findById(int id);

        @Query("SELECT s FROM Schedule s WHERE s.tenant = ?1 ORDER BY MeetingTime")
        List<Schedule> findTimesByTenant(User tenant);






}
