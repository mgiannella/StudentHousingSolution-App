package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.config.JwtToken;
import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.RegisterRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserRoles;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserType;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserTypeRepository;
import io.jsonwebtoken.ExpiredJwtException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;

    public void createUser(RegisterRequest registerRequest) throws ValidationException{
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
    }

    public void deleteUser(User user) throws NotFoundException{
        
    }
}
