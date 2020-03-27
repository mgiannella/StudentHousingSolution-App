package com.softwareengineeringgroup8.studenthousingsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Boolean existsByUsername(String username);
    User findByUsername(String username);
    User findById(int id);


}