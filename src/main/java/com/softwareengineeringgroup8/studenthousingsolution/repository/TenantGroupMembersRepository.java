package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroupMembers;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantGroupMembersRepository extends JpaRepository<TenantGroupMembers, Integer> {
    
}
