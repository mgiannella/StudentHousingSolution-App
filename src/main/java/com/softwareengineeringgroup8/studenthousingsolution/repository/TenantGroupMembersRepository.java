//written by: Michael Giannella
//tested by: Michael Giannella
//debugged by: Michael Giannella
package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroupMembers;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;

import java.util.List;

public interface TenantGroupMembersRepository extends JpaRepository<TenantGroupMembers, Integer> {
    @Query("Select u.tenant from TenantGroupMembers u where (u.group = ?1) AND (u.subscribed = true)")
    List<User> findByGroup(TenantGroups group);

    @Query("SELECT u.group FROM TenantGroupMembers u WHERE (u.tenant = ?1) AND (u.subscribed = true)")
    List<TenantGroups> findTenantGroupByMember(User user);

    @Query("SELECT u FROM TenantGroupMembers u WHERE (u.tenant = ?1) AND (u.subscribed = true) AND (u.group = ?2)")
    TenantGroupMembers findTenantGroupMembersByUserAndGroup(User user, TenantGroups group);

    @Query("SELECT u FROM TenantGroupMembers u WHERE (u.tenant = ?1) AND (u.subscribed = false) AND (u.group = ?2)")
    TenantGroupMembers findInvitesByUserAndGroup(User user, TenantGroups group);

    @Query("SELECT u FROM TenantGroupMembers u WHERE (u.tenant = ?1) AND (u.subscribed = false)")
    List<TenantGroupMembers>findInvitesByUser(User user);

    @Query("Select u from TenantGroupMembers u where (u.group = ?1)")
    List<TenantGroupMembers> findTenantGroupMembersByGroup(TenantGroups group);



}
