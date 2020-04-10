package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.LandlordAccounts;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;



@Repository
public interface LandlordAccountsRepository extends JpaRepository<LandlordAccounts, Integer>{

    //@Query("SELECT StripeID FROM LandlordAccounts l WHERE l.LandlordID=?1")
    //String findBYLandlordID(User landlordID);

}
