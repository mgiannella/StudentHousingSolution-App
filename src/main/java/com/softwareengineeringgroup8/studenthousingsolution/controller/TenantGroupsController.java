package com.softwareengineeringgroup8.studenthousingsolution.controller;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.PropertyService;
import com.softwareengineeringgroup8.studenthousingsolution.service.TenantGroupsService;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validation;
import java.util.List;

@RestController
@ApiModel(description="Handles all TenantGroups Requests")
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/groups")
public class TenantGroupsController {
    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private TenantGroupsService tenantGroupsService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private UserService userService;

    @GetMapping("/invites/view")
    @ApiOperation(value = "Display inviations", notes = "Get all of your pending invites")
    public List<TenantGroupMembers> viewInvites(@RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT))
                throw new ValidationException("User is not a tenant");

            return tenantGroupsService.viewInvitations(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @GetMapping("/view")
    @ApiOperation(value = "Display Groups", notes = "Get all of your subscribed group")
    public List<TenantGroupResponse> viewGroups(@RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT))
                throw new ValidationException("User is not a tenant");
            return tenantGroupsService.getGroupAndMembersByTenant(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @GetMapping("/{id}/view")
    @ApiOperation(value = "View Members/Invites of Group", notes = "View Members/Invites of a Group (id)")
    public List<TenantGroupMembers> viewMembers(@RequestHeader("Authorization") String authString, @PathVariable("id") int id) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT))
                throw new ValidationException("User is not a tenant");
            TenantGroups group = tenantGroupsService.findById(id);
            if (group == null) {
                throw new ValidationException("Group doesn't exist with that ID");
            }
            if (!tenantGroupsService.inGroup(user, group)) {
                throw new ValidationException("User not in group");
            }
            return tenantGroupsService.findByGroup(group);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @PostMapping("/{id}/invite")
    @ApiOperation(value = "Invite to Group", notes = "Invite a member to group (id), needs to be done by lead tenant")
    public boolean inviteMember(@RequestHeader("Authorization") String authString, @PathVariable("id") int id, @RequestBody int userId) throws ValidationException {
        try {
            User inviter = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(inviter, UserRoles.ROLE_TENANT)) {
                throw new ValidationException("User is not a tenant");
            }
            TenantGroups group = tenantGroupsService.findById(id);
            if (group == null) {
                throw new ValidationException("Group is not valid");
            }
            User invitee = userService.getUserById(userId);
            if (invitee == null) {
                throw new ValidationException("Invitee is not a valid user");
            }
            tenantGroupsService.inviteUser(inviter, group, invitee);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create TenantGroup", notes = "Create a new TenantGroup")
    public TenantGroups createGroup(@RequestHeader("Authorization") String authString, @RequestBody String name) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT))
                throw new ValidationException("User is not a tenant");
            TenantGroups group = tenantGroupsService.createGroup(user, name);

            return group;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @GetMapping("/{id}/delete")
    @ApiOperation(value = "Delete TenantGroup", notes = "Delete TenantGroup by id, must be intiated by lead member")
    public boolean deleteGroup(@RequestHeader("Authorization") String authString, @PathVariable("id") int id) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT))
                throw new ValidationException("User is not a tenant");
            TenantGroups group = tenantGroupsService.findById(id);
            if (group == null) {
                throw new ValidationException("Group doesn't exist with that ID");
            }
            tenantGroupsService.deleteGroup(user, group);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
            //return false;
        }
    }

    @GetMapping("/{id}/users/{uid}/remove")
    @ApiOperation(value = "Remove User from Group", notes = "Remove tenant from group, must be initiated by lead member")
    public boolean removeUser(@RequestHeader("Authorization") String authString, @PathVariable("id") int id, @PathVariable("uid") int userId) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT))
                throw new ValidationException("User is not a tenant");
            TenantGroups group = tenantGroupsService.findById(id);
            if (group == null) {
                throw new ValidationException("Group doesn't exist with that ID");
            }
            User deletee = userService.getUserById(userId);
            if (deletee == null) {
                throw new ValidationException("User to be deleted doesn't exist");
            }
            tenantGroupsService.deleteUser(user, group, deletee);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @PostMapping("/{id}/users/{uid}/makelead")
    @ApiOperation(value = "Change Leader", notes = "Make user (uid) the new leader of group (id), must be initiated by lead member")
    public boolean changeLead(@RequestHeader("Authorization") String authString, @PathVariable("id") int id, @PathVariable("uid") int userId) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT))
                throw new ValidationException("User is not a tenant");
            TenantGroups group = tenantGroupsService.findById(id);
            if (group == null) {
                throw new ValidationException("Group doesn't exist with that ID");
            }
            User newLead = userService.getUserById(userId);
            if (newLead == null) {
                throw new ValidationException("User to be leader doesn't exist");
            }
            tenantGroupsService.changeLeader(user, newLead, group);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }
    @GetMapping("/{id}/leave")
    @ApiOperation(value="Leave Group", notes="Leaves group that user is a part of")
    public boolean leaveGroup(@RequestHeader("Authorization") String authString, @PathVariable("id") int id) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if(!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT))
                throw new ValidationException("User is not a tenant");
            TenantGroups group = tenantGroupsService.findById(id);
            if(group == null){
                throw new ValidationException("Group doesn't exist with that ID");
            }
            tenantGroupsService.leaveGroup(user, group);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @GetMapping("/invites/{id}/join")
    @ApiOperation(value="Join Group", notes="Join Group that you've been invited to")
    public boolean joinGroup(@RequestHeader("Authorization") String authString, @PathVariable("id") int id) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if(!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT))
                throw new ValidationException("User is not a tenant");
            TenantGroups group = tenantGroupsService.findById(id);
            if(group == null){
                throw new ValidationException("Group doesn't exist with that ID");
            }
            tenantGroupsService.acceptInvitation(user, group);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @GetMapping("/invites/{id}/decline")
    @ApiOperation(value="Decline Group Invite", notes="Decline invitation for group that you've been invited to")
    public boolean declineInvite(@RequestHeader("Authorization") String authString, @PathVariable("id") int id) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if(!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT))
                throw new ValidationException("User is not a tenant");
            TenantGroups group = tenantGroupsService.findById(id);
            if(group == null){
                throw new ValidationException("Group doesn't exist with that ID");
            }
            tenantGroupsService.declineInvitation(user, group);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @PostMapping("/users/search")
    @ApiOperation(value="Search By Email", notes="Search users by email address")
    public List<User> searchEmail(@RequestHeader("Authorization") String authString, @RequestBody String email) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if(!userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT))
                throw new ValidationException("User is not a tenant");
            List<User> userList = userService.findByEmail(email);
            return userList;
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }
}
