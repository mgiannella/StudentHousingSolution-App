package com.softwareengineeringgroup8.studenthousingsolution.controller;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.RegisterRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserRoles;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserType;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserTypeRepository;
import com.softwareengineeringgroup8.studenthousingsolution.service.JwtUserDetailsService;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserPermissionService;
import com.softwareengineeringgroup8.studenthousingsolution.service.UserService;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    final private JwtUserDetailsService jwtUserDetailsService;
    final private UserPermissionService userPermissionService;
    final private UserService userService;
//    private HashData hashData = new HashData();

    public UserController(JwtUserDetailsService jwtUserDetailsService, UserPermissionService userPermissionService, UserService userService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.userPermissionService = userPermissionService;
        this.userService = userService;
    }

    @GetMapping("/testauth")
    public boolean testauthentication(@RequestHeader("Authorization") String authString) throws ValidationException{
        try{
            User x = userPermissionService.loadUserByJWT(authString);
            if(userPermissionService.assertPermission(x, UserRoles.ROLE_LANDLORD)){
                return true;
            }
            return false;
        }catch(NotFoundException e){
            throw new ValidationException("Invalid JWT Token, re-authenticate");
        }
    }

    @PostMapping("/register")
    public Boolean register(@RequestBody RegisterRequest registerRequest) throws NoSuchAlgorithmException {
        try {
            userService.createUser(registerRequest);
            return true;
        }catch(Error e){
            System.out.println(e);
            return false;
        }
    }

}
