//written by: Michael Giannella
//tested by: Michael Giannella
//debugged by: Michael Giannella
package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TenantGroupsRepository extends JpaRepository<TenantGroups, Integer> {
    TenantGroups findById(int id);

    @Query("SELECT tg FROM TenantGroups tg WHERE tg.leadTenant = ?1")
    List<TenantGroups> findGroupByLeadMember(User user);

}
