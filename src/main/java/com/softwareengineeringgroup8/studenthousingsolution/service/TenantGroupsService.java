package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroupMembers;
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

    @Autowired
    private PropertyService propertyService;

    // returns true if user is in the group
    public boolean inGroup(User user, TenantGroups group){
        if(tenantGroupMembersRepository.findTenantGroupMembersByUserAndGroup(user, group).equals(null)){
            return false;
        }

        return true;
    }

    public TenantGroups createGroup(User leadTenant, String groupName) throws ValidationException{
        // Can only be a part of a maximum of 2 groups
        List<TenantGroups> groupsList = tenantGroupMembersRepository.findTenantGroupByMember(leadTenant);
        if(groupsList.size() > 1){
            throw new ValidationException("Group Limit Exceeded, leave a group and try again");
        }
        // Creates Group with them as the lead tenant
        TenantGroups group = new TenantGroups(leadTenant, groupName);
        TenantGroupMembers leader = new TenantGroupMembers(group, leadTenant, false, false, true);
        tenantGroupsRepository.save(group);
        // Adds them to Members table with false signed lease and false on invited, but true on subscribed
        tenantGroupMembersRepository.save(leader);
        return group;
    }

    public void inviteUser(User inviter, TenantGroups group, User invitee) throws ValidationException{
        // Check to see that inviter is a part of group
        if(inGroup(invitee, group)){
            throw new ValidationException("User already part of group");
        }
        if(!group.getLeadTenant().equals(inviter)){
            throw new ValidationException("Lead tenant needs to invite");
        }
        if(!tenantGroupMembersRepository.findInvitesByUserAndGroup(invitee, group).equals(null))
            throw new ValidationException("Already sent an invite to this user");
        // Check to make sure invitee isn't in 2 groups already
        List<TenantGroups> groupsList = tenantGroupMembersRepository.findTenantGroupByMember(invitee);
        if(groupsList.size() > 1){
            throw new ValidationException("Group Limit Exceeded, leave a group and try again");
        }
        // Add tenant to group
        tenantGroupMembersRepository.save(new TenantGroupMembers(group, invitee, false, true, false));
    }

    public void leaveGroup(User remover, User user, TenantGroups group) throws ValidationException{
        // Checks to make sure user is in group
        if(!inGroup(user,group)){
            throw new ValidationException("User not a part of group");
        }
        // Checks if remover is lead tenant
        TenantGroupMembers member = tenantGroupMembersRepository.findTenantGroupMembersByUserAndGroup(user, group);
        if(group.getLeadTenant().equals(user)) {
            tenantGroupMembersRepository.delete(member);
        }else{
            throw new ValidationException("Remover is not lead tenant");
        }
    }

    public void deleteUser(User lead, TenantGroups group, User delete) throws ValidationException {
        // Checks user is in group
        if(!inGroup(delete, group)){
            throw new ValidationException("User to be deleted is not in group");
        }
        // Checks lead is leadTenant of group
        if(!group.getLeadTenant().equals(lead)){
           throw new ValidationException("User that deletes needs to be lead tenant");
        }
        // Removes person from group
        tenantGroupMembersRepository.delete(tenantGroupMembersRepository.findTenantGroupMembersByUserAndGroup(delete, group));
    }

    public void deleteGroup(User lead, TenantGroups group) throws ValidationException {
        // if they are leader
        if(!group.getLeadTenant().equals(lead)){
            throw new ValidationException("User that deletes needs to be lead tenant");
        }
        // if group is currently assigned a property don't delete
        if(!propertyService.getPropertyByGroup(group).equals(null)){
            throw new ValidationException("Group is assigned to a property");
        }

        List<TenantGroupMembers> userList = tenantGroupMembersRepository.findTenantGroupMembersByGroup(group);
        for(int i = 0; i < userList.size(); i++){
            tenantGroupMembersRepository.delete(userList.get(i));
        }

        tenantGroupsRepository.delete(group);

    }

    public void changeLeader(User lead, User newLead, TenantGroups group) throws ValidationException {
        // if lead is lead of group
        if(!group.getLeadTenant().equals(lead)){
            throw new ValidationException("Needs to be initiated by lead tenant");
        }
        // if newLead is in group
        if(!inGroup(newLead, group)){
            throw new ValidationException("New leader needs to be in the group");
        }
        // make newLead leader
        group.setLeadTenant(newLead);
        tenantGroupsRepository.save(group);
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

    public void declineInvitation(User user, TenantGroups group) throws ValidationException {
        TenantGroupMembers member = tenantGroupMembersRepository.findInvitesByUserAndGroup(user, group);
        if(member.equals(null)){
            throw new ValidationException("Member did not have an invitation to that group");
        }
        tenantGroupMembersRepository.delete(member);
    }

    public void acceptInvitation(User user, TenantGroups group) throws ValidationException {
        TenantGroupMembers member = tenantGroupMembersRepository.findInvitesByUserAndGroup(user,group);
        if(member.equals(null)){
            throw new ValidationException("Member did not have an invitation to that group");
        }
        member.setSubscribed(true);
        member.setInvited(false);
        tenantGroupMembersRepository.save(member);
    }

    public void signLease(User user, TenantGroups group) throws ValidationException {
        TenantGroupMembers member = tenantGroupMembersRepository.findTenantGroupMembersByUserAndGroup(user,group);
        if(member.equals(null)){
            throw new ValidationException("Member is not a part of that group");
        }
        member.setSignedLease(true);
        tenantGroupMembersRepository.save(member);
    }

    public List<TenantGroupMembers> viewInvitations(User user) {
        return tenantGroupMembersRepository.findInvitesByUser(user);
    }
}