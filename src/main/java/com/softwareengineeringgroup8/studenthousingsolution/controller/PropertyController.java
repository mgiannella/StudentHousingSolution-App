package com.softwareengineeringgroup8.studenthousingsolution.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@ApiModel(description="Handles all Property Requests")
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/property")
public class PropertyController {
    final private PropertyService propertyService;
    final private UserPermissionService userPermissionService;
    final private TenantGroupsService tenantGroupsService;
    final private UserService userService;

    public PropertyController(PropertyService propertyService, UserPermissionService userPermissionService, TenantGroupsService tenantGroupsService, UserService userService){
        this.propertyService = propertyService;
        this.userPermissionService = userPermissionService;
        this.tenantGroupsService = tenantGroupsService;
        this.userService = userService;
    }

    @GetMapping("/view/{id}")
    @ApiOperation(value="Property Information", notes="Gets all information about a property")
    @JsonView(PropertyView.ViewProperty.class)
    public Properties viewProperty(@PathVariable("id") int id) throws ValidationException {
        try{
            return propertyService.getById(id);
        }catch(Error e){
            throw new ValidationException("Invalid input, try again.");
        }
    }

    @GetMapping("/view")
    @ApiOperation(value="Search By Zip Code", notes="Gets all properties within a certain zip code")
    @JsonView(PropertyView.Search.class)
    public List<Properties> searchByZip(@RequestParam("zip") String zipcode) throws ValidationException{
        try{
            return propertyService.getByZip(zipcode);
        }catch(Error e){
            throw new ValidationException("Invalid input, try again.");
        }
    }

    @PostMapping("/search")
    @ApiOperation(value="Search By Filters and Zip Code", notes="Gets all properties within a certain zip code with filters applied")
    @JsonView(PropertyView.Search.class)
    public List<Properties> searchByZip(@RequestBody SearchFilterRequest req) throws ValidationException{
        try{
            return propertyService.filterSearch(req);
        }catch(Error e){
            throw new ValidationException("Invalid input, try again.");
        }
    }

}

