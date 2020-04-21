package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.PaymentRecord;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.model.Reviews;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews,Integer>{
    @Query("SELECT u FROM Reviews u WHERE u.prop = ?1")
    List<Reviews> findByProperty(Properties prop);

    @Query("SELECT u FROM Reviews u WHERE u.id = ?1")
    Reviews findById(int id);

    @Query("SELECT u FROM Reviews u WHERE (u.prop = ?1) and (u.tenant=?2)")
    List<Reviews> findByIdAndTenant(Properties prop, User tenant);

}
