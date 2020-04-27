package com.softwareengineeringgroup8.studenthousingsolution.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Boolean existsByUsername(String username);
    User findByUsername(String username);
    User findById(int id);



    @Query("Select u FROM User u WHERE u.email LIKE ?1%")
    List<User> searchByEmail(String email);


}