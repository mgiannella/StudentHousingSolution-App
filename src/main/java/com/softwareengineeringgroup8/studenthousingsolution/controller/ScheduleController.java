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

import javax.validation.Valid;
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

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping("/landlordview")
    @ApiOperation(value="Landlord view of schedule", notes="View Schedule")
    public ScheduleView landlordViewSchedule(@RequestParam String viewStart,@RequestParam String viewEnd, @RequestHeader("Authorization") String str) throws ValidationException {
        try {
            User landlord = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(landlord, UserRoles.ROLE_LANDLORD)) {
                throw new ValidationException("You are not a landlord.");
            }

            if (scheduleService.existsByLandlord(landlord)==true) {
                return scheduleService.findByLandlord(viewStart, viewEnd,landlord);
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
    @ApiOperation(value="Create/Edit Schedule",notes="Create/edit schedule.")
    public Boolean newSchedule(@RequestBody ScheduleRequest request, @RequestHeader("Authorization") String str) throws ValidationException {
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
    @ApiOperation(value="List Times for Making Reservation",notes="Create new reservation.")
    public ScheduleTenantTimes listTimes(@RequestHeader("Authorization") String str, @PathVariable("landlordid") int landlordid) throws ValidationException {
        try {
            User tenant = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                throw new ValidationException("You are not a tenant.");
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




    @PostMapping("/makebooking")
    @ApiOperation(value="Make a booking",notes="Create new reservation.")
    public Boolean makeBooking(@RequestBody ScheduleBooking booking, @RequestHeader("Authorization") String str) throws ValidationException {
        try {
            User tenant = userPermissionService.loadUserByJWT(str);
            if (!userPermissionService.assertPermission(tenant, UserRoles.ROLE_TENANT)) {
                return false;
            }
            scheduleService.makeBooking(booking,tenant);
            return true;


        }

        catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }


    @GetMapping("/dashboards")
    @ApiOperation(value="View User Dashboard",notes="Dashboard display.")
    public ScheduleDashboard viewDashboard(@RequestHeader("Authorization") String str) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(str);


            return scheduleService.displayAllBookedEvents(user);

        }

        catch (Error | NotFoundException e) {
            System.out.println(e);
            return null;
        }
    }


    @PostMapping("/{id}")  //SEND NOTIFICATION HERE; also this isnt really a delete its just making prop and tenant fields null
    @ApiOperation(value="Delete Event",notes="Delete event for tenant and landlord")
    public Boolean deleteEvent(@RequestHeader("Authorization") String str, @PathVariable("id") int id) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(str);
            Schedule schedule = scheduleRepository.findById(id);
            if (schedule.getTenant() == null || schedule.getProps()==null) {
                throw new ValidationException("This event cannot be deleted as it is not a reserved booking.");
            }

            schedule.setTenant(null);
            schedule.setProps(null);
            scheduleRepository.save(schedule);
            return true;

        }
        catch (Error | NotFoundException e) {
            System.out.println(e);
            return false;
        }
    }
}





