package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;

@Repository
public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Integer>{
    @Query("SELECT u FROM MaintenanceRequest u WHERE u.prop = ?1")
    List<MaintenanceRequest> findByProperty(Properties prop);
    @Query("SELECT u FROM MaintenanceRequest u WHERE u.requestID = ?1")
    MaintenanceRequest findByRequestId(int requestID);
}
