package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.config.JwtToken;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.softwareengineeringgroup8.studenthousingsolution.model.UserRoles;
import org.springframework.stereotype.Component;

@Component
public class UserPermissionService {

    @Autowired
    private UserRepository userRepository;

    private final JwtToken jwtTokenUtil;

    public UserPermissionService(JwtToken jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public User loadUserByJWT(String token) throws NotFoundException {
        String jwtToken, username = null;
        if (token != null && token.startsWith("Bearer ")) {

            jwtToken = token.substring(7);

            try {

                username = jwtTokenUtil.getUsernameFromToken(jwtToken);

            } catch (IllegalArgumentException e) {

                System.out.println("Unable to get JWT Token");

            } catch (ExpiredJwtException e) {

                System.out.println("JWT Token has expired");

            }

        } else {
            System.out.println("JWT Token does not begin with Bearer String");

        }
        if(username == null){
            throw new NotFoundException("No user exists with token provided");
        }
        return userRepository.findByUsername(username);

    }

    public boolean assertPermission(User user, UserRoles role){
        if(user.getType().getUserTypeDesc().equals("Landlord") && role == UserRoles.ROLE_LANDLORD){
            return true;
        }
        if(user.getType().getUserTypeDesc().equals("Tenant") && role == UserRoles.ROLE_TENANT){
            return true;
        }
        return false;
    }
}
