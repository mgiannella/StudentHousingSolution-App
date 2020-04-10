package com.softwareengineeringgroup8.studenthousingsolution.controller;

import com.softwareengineeringgroup8.studenthousingsolution.service.PropertyService;
import com.softwareengineeringgroup8.studenthousingsolution.service.TenantGroupsService;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
