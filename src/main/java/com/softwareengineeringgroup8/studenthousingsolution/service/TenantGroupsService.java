package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserRoles;
import com.softwareengineeringgroup8.studenthousingsolution.repository.TenantGroupMembersRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.TenantGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.Validation;
import java.util.List;

@Component
public class TenantGroupsService {
    @Autowired
    private TenantGroupsRepository tenantGroupsRepository;

    @Autowired
    private TenantGroupMembersRepository tenantGroupMembersRepository;

    @Autowired
    private UserPermissionService userPermissionService;

    public boolean inGroup(User user, TenantGroups group){
        List<TenantGroups> groups = tenantGroupMembersRepository.findTenantGroupByMember(user);
        if(groups.contains(user)){
            return true;
        }
        return false;
    }

    public TenantGroups createGroup(User leadTenant, String groupName){
        // Can only be a part of a maximum of 2 groups

        // Creates Group with them as the lead tenant

        // Adds them to Members table with false signed lease and false on invited, but true on subscribed
        return null;
    }

    public boolean inviteUser(User inviter, TenantGroups group, User invitee){
        // Check to see that inviter is a part of group

        // Check to make sure invitee isn't in 2 groups already

        // Add tenant to group

        // return true if it happened, false if it got caught up anywhere
        return true;
    }

    public void leaveGroup(User user, TenantGroups group) throws ValidationException{
        // Checks to make sure user is in group

        // Checks if lease is signed

        // If lease is signed, have to be removed by leadTenant

        // Removes them from group

    }

    public void deleteUser(User lead, TenantGroups group, User delete) throws ValidationException {
        // Checks user is in group

        // Checks lead is leadTenant of group

        // Removes person from group
    }

    public void deleteGroup(User lead, TenantGroups group) throws ValidationException {
        // if they are leader

        // if group is currently assigned a property don't delete

    }

    public void changeLeader(User lead, User newLead, TenantGroups group) throws ValidationException {
        // if lead is lead of group

        // if newLead is in group

        // make newLead leader
    }

    public List<TenantGroups> getGroupByTenant(User user) throws ValidationException {
        // search tenantgroups and find group
        try{
            return tenantGroupMembersRepository.findTenantGroupByMember(user);
        }catch(Error e) {
            throw new ValidationException("Couldn't get tenants for group");
        }
    }

    public List<User> getTenantsByGroup(TenantGroups group) throws ValidationException {
        try{
            return tenantGroupMembersRepository.findByGroup(group);
        }catch(Error e) {
            throw new ValidationException("Couldn't get tenants for group");
        }
    }

}

