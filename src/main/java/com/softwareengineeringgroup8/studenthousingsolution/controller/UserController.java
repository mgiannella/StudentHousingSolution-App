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


    final private UserRepository userRepository;
    final private UserTypeRepository userTypeRepository;
    final private JwtUserDetailsService jwtUserDetailsService;
    final private UserPermissionService userPermissionService;
//    private HashData hashData = new HashData();

    public UserController(UserRepository userRepository, UserTypeRepository userTypeRepository, JwtUserDetailsService jwtUserDetailsService, UserPermissionService userPermissionService) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.userPermissionService = userPermissionService;
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
        String username = registerRequest.getUsername();
        if (userRepository.existsByUsername(registerRequest.getUsername())){
            throw new ValidationException("Username exists");

        }
        String encodedPassword = new BCryptPasswordEncoder().encode(registerRequest.getPassword());
        String firstName = registerRequest.getFirstName();
        String lastName = registerRequest.getLastName();
        String phone = registerRequest.getPhone();
        String phoneCode = registerRequest.getPhoneCode();
        String email = registerRequest.getEmail();
        UserType type = userTypeRepository.findByType(registerRequest.getUserType());
        userRepository.save(new User(username, encodedPassword, email, firstName, lastName, phone, phoneCode,type));
        return true;
    }

}
