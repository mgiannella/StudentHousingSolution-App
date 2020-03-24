package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroupsId;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TenantGroupsRepository extends JpaRepository<TenantGroups, TenantGroupsId> {
    // Just here for testing, final methods not here yet, need to be thought out
    List<TenantGroups> findTenantGroupsByTenant(User tenant);
}
