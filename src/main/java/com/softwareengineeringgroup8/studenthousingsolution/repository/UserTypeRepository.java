//written by: Michael Giannella
//tested by: Michael Giannella
//debugged by: Michael Giannella
package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Integer> {

    @Query("SELECT u FROM UserType u WHERE u.userTypeDesc = ?1")
    UserType findByType(String type);


}