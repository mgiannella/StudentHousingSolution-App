package com.softwareengineeringgroup8.studenthousingsolution.repository;


        import com.softwareengineeringgroup8.studenthousingsolution.model.Schedule;
        import com.softwareengineeringgroup8.studenthousingsolution.model.User;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import java.sql.Timestamp;
        import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

        Boolean existsByLandlord(User landlord);


        @Query("Select s FROM Schedule s WHERE s.landlord = ?1")
        List<Schedule> findByLandlord(User landlord);




}
