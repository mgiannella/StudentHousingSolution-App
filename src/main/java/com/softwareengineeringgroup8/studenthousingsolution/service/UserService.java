package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.RegisterRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserType;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;
    // create a user with the registration values
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
    // delete user
    public void deleteUser(User user) throws ValidationException{
        if (!userRepository.existsByUsername(user.getUsername())){
            throw new ValidationException("User doesn't exist");
        }
        userRepository.deleteById(user.getId());
    }
    // update a user with new information
    public void updateUser(User user, RegisterRequest changes){
        user.setEmail(changes.getEmail());
        user.setFullname(changes.getFirstName(), changes.getLastName());
        user.setPhone(changes.getPhoneCode() + changes.getPhone());
        UserType type = userTypeRepository.findByType(changes.getUserType());
        user.setType(type);
        userRepository.save(user);
    }
    // change password
    public boolean changePassword(User user, String oldPass, String newPass){
        if(!new BCryptPasswordEncoder().matches(oldPass, user.getPassword())){
            return false;
        }
        user.setPassword(new BCryptPasswordEncoder().encode(newPass));
        userRepository.save(user);
        return true;
    }
    // returns user with certain userId
    public User getUserById(int id) throws ValidationException{
        try{
            return userRepository.findById(id);
        }catch(Exception e){
            throw new ValidationException("Couldn't find User By Id");
        }
    }
}
