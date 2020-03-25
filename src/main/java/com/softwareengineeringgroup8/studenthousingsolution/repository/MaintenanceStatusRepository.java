package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceStatusRepository extends JpaRepository <MaintenanceStatus, Integer>{
   @Query("SELECT u FROM MaintenanceStatus u WHERE u.statusDesc = ?1")
   MaintenanceStatus findByStatusDesc(String statusDesc);
}
