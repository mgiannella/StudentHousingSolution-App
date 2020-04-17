package com.softwareengineeringgroup8.studenthousingsolution.repository;


        import com.softwareengineeringgroup8.studenthousingsolution.model.Schedule;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;
        import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

}
