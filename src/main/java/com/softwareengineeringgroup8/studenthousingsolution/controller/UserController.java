package com.softwareengineeringgroup8.studenthousingsolution.controller;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserTypeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


    final private UserRepository userRepository;
    final private UserTypeRepository userTypeRepository;

//    private HashData hashData = new HashData();

    public UserController(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    @GetMapping("/testauth")
    public String testauthentication(){
        return "IT works bebe";
    }

    @PostMapping("/register")
    public Boolean register(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
        String username = body.get("username");
        if (userRepository.existsByUsername(username)){
            throw new ValidationException("Username exists");

        }
        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        String firstName = body.get("firstName");
        String lastName = body.get("lastName");
        String phone = body.get("phone");
        String phoneCode = body.get("phoneCode");
        String email = body.get("email");
        String userType = body.get("usertype");
        userRepository.save(new User(username, encodedPassword, email, firstName, lastName, phone, phoneCode,userTypeRepository.findByType("Landlord")));
        return true;
    }

}
