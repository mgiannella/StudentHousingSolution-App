package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Boolean existsByUsername(String username);
    User findByUsername(String username);


}