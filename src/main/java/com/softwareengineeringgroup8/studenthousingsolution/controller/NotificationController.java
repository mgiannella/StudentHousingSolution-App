package com.softwareengineeringgroup8.studenthousingsolution.controller;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.Notifications;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.service.NotificationService;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import java.util.List;

@RestController
@ApiModel(description="Handles all Notification Requests")
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/notif")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserPermissionService userPermissionService;

    @GetMapping("/view")
    @ApiOperation(value="View Notifications", notes="Views all notifications that are dated before current time and not deleted")
    public List<Notifications> viewNotifications(@RequestHeader("Authorization") String authString) throws ValidationException {
        try{
            User user = userPermissionService.loadUserByJWT(authString);
            if(user == null){
                throw new ValidationException("User doesn't exist");
            }
            return notificationService.getNotifications(user);
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value="Delete Notification", notes="Deletes notification by Id")
    public boolean deleteNotification(@RequestHeader("Authorization")String authString, @PathVariable("id")int id) throws ValidationException{
        try{
            User user = userPermissionService.loadUserByJWT(authString);
            if(user == null){
                throw new ValidationException("User doesn't exist");
            }
            return notificationService.deleteNotification(user, id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @GetMapping("/view/num")
    @ApiOperation(value="Get Count of Notifications", notes="Returns integer of how many notifications user has waiting for them")
    public int viewNumNotifications(@RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if(user == null){
                throw new ValidationException("User doesn't exist");
            }
            return notificationService.getAmtNotifs(user);
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }
/* Shows how to call into it
    @GetMapping("/test")
    public boolean test(@RequestHeader("Authorization") String authString) throws ValidationException {
        try{
            User user = userPermissionService.loadUserByJWT(authString);
            if(user == null){
                throw new ValidationException("User doesn't exist");
            }
            return notificationService.createNotification(user,"Creating a test Notification", "GENERAL", "2020-04-09 12:00:00");
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }
*/
}
