package com.softwareengineeringgroup8.studenthousingsolution.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;


import com.softwareengineeringgroup8.studenthousingsolution.service.ListingService;
import com.softwareengineeringgroup8.studenthousingsolution.service.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
import com.softwareengineeringgroup8.studenthousingsolution.controller.UserController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins="http://localhost:3000")
@ApiModel(description="Controller for schedule")
@RequestMapping("/schedule")
public class ScheduleController {
        @Autowired
        private PropertyService propertyService;

        @Autowired
        private UserPermissionService userPermissionService;

        @Autowired
        private ScheduleService scheduleSerivce;


        //createSchedule
    @PostMapping("/createSchedule")
    @ApiOperation(value="Landlord Create Schedule",notes="Landlord chooses available times")
    public Boolean newSchedule(@RequestBody ScheduleRequest sched, @RequestHeader("Authorization") String str) throws ValidationException {
        try {
            User landlord = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return false;
            }
            scheduleSerivce.createSchedule(sched,landlord);
            return true;
        } catch (NotFoundException e) {
            System.out.println(e);
            return false;
        }
    }



    //viewSchedule (tenant) GET

    //viewSchedule (landlord) GET

    //editSchedule (landlord) POST

    //create event (tenant) POST

    //edit event (tenant or landlord) POST

    //delete event (landlord or tenant) DELETE
}
