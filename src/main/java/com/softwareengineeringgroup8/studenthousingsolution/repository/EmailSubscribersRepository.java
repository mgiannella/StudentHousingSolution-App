package com.softwareengineeringgroup8.studenthousingsolution.repository;

import com.softwareengineeringgroup8.studenthousingsolution.model.Amenities;
import com.softwareengineeringgroup8.studenthousingsolution.model.EmailAmenities;
import com.softwareengineeringgroup8.studenthousingsolution.model.EmailSubscribers;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmailSubscribersRepository extends JpaRepository<EmailSubscribers,Integer> {

    public List<EmailSubscribers> findAll();

    @Query("Select es FROM EmailSubscribers es WHERE es.user = ?1")
    public EmailSubscribers findByUser(User user);

    public boolean existsByUser(User user);

}