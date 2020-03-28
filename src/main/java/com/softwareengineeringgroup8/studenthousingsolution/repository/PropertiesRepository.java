package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertiesRepository extends JpaRepository<Properties, Integer> {

    @Query("SELECT prop FROM Properties prop WHERE prop.group = ?1")
    Properties findByGroup(TenantGroups group);
}
