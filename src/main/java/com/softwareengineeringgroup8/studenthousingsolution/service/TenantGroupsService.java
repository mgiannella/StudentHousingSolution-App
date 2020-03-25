package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroupsId;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserRoles;
import com.softwareengineeringgroup8.studenthousingsolution.repository.TenantGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class TenantGroupsService {
    @Autowired
    private TenantGroupsRepository tenantGroupsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserPermissionService userPermissionService;

    public void createTenantGroup(User user) throws ValidationException {
        int maxId = tenantGroupsRepository.maxTenantGroupId(); // try set and catch set to 0
        int tenantGroupId = maxId + 1;
        if(userPermissionService.assertPermission(user, UserRoles.ROLE_LANDLORD)){
            throw new ValidationException("Cannot create a tenant group with a landlord");
        }
        tenantGroupsRepository.save(new TenantGroups(tenantGroupId, user, true, false ));
    }

    public void addMember(TenantGroups lead, User user) throws ValidationException{
        tenantGroupsRepository.save(new TenantGroups(lead.getTenantGroupId(), user, false, false));
    }
}
