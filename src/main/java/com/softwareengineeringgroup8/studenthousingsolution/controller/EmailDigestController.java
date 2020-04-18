package com.softwareengineeringgroup8.studenthousingsolution.controller;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import java.util.List;

@RestController
@ApiModel(description="Handles all Email Digest Requests")
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/digest")
public class EmailDigestController {
    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private EmailDigestService emailDigestService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    UserService userService;

    @GetMapping("/api/subscribers")
    @ApiOperation(value = "Get all Newsletter Subscribers", notes = "Returns a list of all subscribers")
    public List<EmailSubscribers> getSubscribers(@RequestParam("key") String apiKey) throws ValidationException {
        try {
            return emailDigestService.returnAllSubscribers(apiKey);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @GetMapping("/unsubscribe")
    @ApiOperation(value = "Unsubscribe from Newsletter", notes = "Removes you from EmailDigest email list")
    public boolean unsubscribe(@RequestHeader("Authorization") String authString) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (user == null){
                throw new ValidationException("User does not exist");
            }
            emailDigestService.unsubscribeDigest(user);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @PostMapping("/subscribe")
    @ApiOperation(value="Subscribe to Newsletter", notes="Subscribes to email digest")
    public boolean subscribe(@RequestHeader("Authorization") String authString, @RequestBody EmailDigestRequest edr) throws ValidationException {
        try {
            User user = userPermissionService.loadUserByJWT(authString);
            if (user == null){
                throw new ValidationException("User does not exist");
            }
            emailDigestService.subscribeDigest(user, edr);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    @GetMapping("/api/subscribers/{id}/properties")
    @ApiOperation(value="Get Properties by Subscriber", notes="Returns properties that fit subscribers query from previous week")
    public List<Properties> getProperties(@RequestParam("key") String apiKey, @PathVariable("id") int id) throws ValidationException {
        try{
            User user = userService.getUserById(id);
            if(user == null){
                throw new ValidationException("User does not exist");
            }
            EmailAmenities ea = emailDigestService.getSubscribersPrefs(user);
            if(ea == null){
                throw new ValidationException("User is not subscribed.");
            }
            return emailDigestService.getPropsForSubscriber(ea);
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }
}
