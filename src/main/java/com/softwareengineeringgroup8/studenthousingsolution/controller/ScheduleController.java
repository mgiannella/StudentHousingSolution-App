package com.softwareengineeringgroup8.studenthousingsolution.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;


import com.softwareengineeringgroup8.studenthousingsolution.repository.ScheduleRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserRepository;
import com.softwareengineeringgroup8.studenthousingsolution.service.ListingService;
import com.softwareengineeringgroup8.studenthousingsolution.service.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
import com.softwareengineeringgroup8.studenthousingsolution.service.HousingAgreementService;
import com.softwareengineeringgroup8.studenthousingsolution.controller.UserController;
import io.swagger.annotations.*;
import javassist.NotFoundException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@ApiModel(description="Schedule Controller")
@RequestMapping("/schedule")
public class ScheduleController{

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private HousingAgreementService agreementService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/landlordview")
    @ApiOperation(value="view schedule", notes="View Schedule")
    public ScheduleView landlordViewSchedule(@RequestHeader("Authorization") String str) throws ValidationException {
        try {
            User landlord = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return null;
            }

            if (scheduleService.existsByLandlord(landlord)==true) {
                return scheduleService.findByLandlord(landlord);
            }
            else {
                    throw new ValidationException("No schedule exists.") ;
                }


        }

        catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }



    @PostMapping("/newschedule")
    @ApiOperation(value="Create Schedule",notes="Create new schedule.")
    public Boolean newListing(@RequestBody ScheduleRequest request, @RequestHeader("Authorization") String str) throws ValidationException {
        try {
            User landlord = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                return false;
            }
            scheduleService.createSchedule(request,landlord);
            //test();
            return true;
        }

        catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
        }
    }


    @GetMapping("/{landlordid}")
    @ApiOperation(value="Booking Times",notes="Create new reservation.")
    public ScheduleTenantTimes listTimes(@RequestHeader("Authorization") String str, @PathVariable("landlordid") int landlordid) throws ValidationException {
        try {
            User tenant = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return null;
            }
            User landlord = userRepository.findById(landlordid);

            if (scheduleService.existsByLandlord(landlord)==true) {
                return scheduleService.ListTimes(landlord);
            }

            else {
                throw new ValidationException("No schedule exists for this landlord.") ;
            }


        }

        catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }


}





