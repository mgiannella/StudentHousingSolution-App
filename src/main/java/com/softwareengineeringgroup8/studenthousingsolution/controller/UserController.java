package com.softwareengineeringgroup8.studenthousingsolution.controller;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.service.JwtUserDetailsService;
import com.softwareengineeringgroup8.studenthousingsolution.service.TenantGroupsService;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@ApiModel(description="Handles all User Management Requests")
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/user")
public class UserController {

    final private JwtUserDetailsService jwtUserDetailsService;
    final private UserPermissionService userPermissionService;
    final private UserService userService;
    final private TenantGroupsService tenantGroupsService;
//    private HashData hashData = new HashData();

    public UserController(JwtUserDetailsService jwtUserDetailsService, UserPermissionService userPermissionService, UserService userService, TenantGroupsService tenantGroupsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.userPermissionService = userPermissionService;
        this.userService = userService;
        this.tenantGroupsService = tenantGroupsService;
    }

    @GetMapping("/delete")
    @ApiOperation(value="Delete User", notes="Call with JWT in Authorization Header and will delete current user")
    public boolean deleteUser(@RequestHeader("Authorization") String authString) throws ValidationException{
        try{
            User x = userPermissionService.loadUserByJWT(authString);
            userService.deleteUser(x);
            return true;
        }catch(NotFoundException e){
            throw new ValidationException("Invalid JWT Token, re-authenticate");
        }
    }

    @PostMapping("/register")
    @ApiOperation(value="Register User", notes="Call in to register a new user and persist them in the database")
    public Boolean register(@RequestBody RegisterRequest registerRequest) throws NoSuchAlgorithmException {
        try {
            userService.createUser(registerRequest);
            return true;
        }catch(Error e){
            System.out.println(e);
            return false;
        }
    }

    @GetMapping("/")
    @ApiOperation(value="My Account", notes="Returns all information about a user")
    public User myAccount(@RequestHeader("Authorization") String authString) throws ValidationException {
        try{
            User x = userPermissionService.loadUserByJWT(authString);
            if(x == null) {
                throw new ValidationException("No user with this JWT");
            }
            x.setPassword(""); // clears password, so that the bcrypted password isn't sent to user
            return x;
        }catch(NotFoundException e){
            throw new ValidationException("Invalid JWT Token, re-authenticate");
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value="Update Account Information", notes="Updates information about a user, not including password")
    public User updateAccount(@PathVariable("id") int id, @RequestHeader("Authorization") String authString, @RequestBody RegisterRequest registerRequest) throws ValidationException {
        try{
            User x = userPermissionService.loadUserByJWT(authString);
            User y = userService.getUserById(id);
            if(y == null || x == null || !y.getUsername().equals(x.getUsername())){
                throw new ValidationException("JWT does not match ID, check ID/JWT");
            }
            userService.updateUser(y, registerRequest);
            y = userService.getUserById(id);
            y.setPassword("");
            return y;
        }catch(NotFoundException e){
            throw new ValidationException("Invalid JWT Token, re-authenticate");
        }

    }

    @PutMapping("/{id}/password")
    @ApiOperation(value="Change Password", notes="Updates user's password")
    public User changePassword(@PathVariable("id") int id, @RequestHeader("Authorization") String authString, @RequestParam String oldPass, @RequestParam String newPass){
        try{
            User x = userPermissionService.loadUserByJWT(authString);
            User y = userService.getUserById(id);
            if(y == null || x == null || !y.getUsername().equals(x.getUsername())){
                throw new ValidationException("JWT does not match ID, check ID/JWT");
            }
            userService.changePassword(y, oldPass, newPass);
            y = userService.getUserById(id);
            y.setPassword("");
            return y;
        }catch(NotFoundException e){
            throw new ValidationException("Invalid JWT Token, re-authenticate");
        }
    }

    @GetMapping("/test")
    public boolean test(){
        try{
            tenantGroupsService.createTenantGroup(userService.getUserById(1));
            return true;
        }catch(ValidationException e){
            return false;
        }
    }
}
