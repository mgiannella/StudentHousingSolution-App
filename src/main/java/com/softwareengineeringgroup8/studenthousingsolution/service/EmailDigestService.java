package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.repository.EmailAmenitiesRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.EmailSubscribersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailDigestService {

    @Autowired
    private EmailAmenitiesRepository emailAmenitiesRepository;

    @Autowired
    private EmailSubscribersRepository emailSubscribersRepository;

    @Value("${serverApiKeyEmailDigest}")
    private String serverApiKey;

    public List<EmailSubscribers> returnAllSubscribers(String apiKey) throws ValidationException {
        if(!apiKey.equals(serverApiKey)){
            throw new ValidationException("Invalid API Key supplied!");
        }
        try{
            return emailSubscribersRepository.findAll();
        }catch(Exception e){
            throw new ValidationException("Could not retrieve list of email subscribers");
        }
    }

    public void subscribeDigest(User user, EmailDigestRequest edr) throws ValidationException{
        try{
            EmailAmenities amen = new EmailAmenities(edr.getBedrooms().getMin(), edr.getBedrooms().getMax(), edr.getBathrooms().getMin(), edr.getBathrooms().getMax(), edr.getPrice().getMin(), edr.getPrice().getMax(), edr.getSleeps().getMin(), edr.getSleeps().getMax(), edr.getZip());
            emailAmenitiesRepository.save(amen);
            EmailSubscribers sub = new EmailSubscribers(amen,user);
            emailSubscribersRepository.save(sub);
        }catch(Exception e){
            throw new ValidationException("Couldn't subscribe to digest, try again.");
        }
    }

    public void unsubscribeDigest(User user) throws ValidationException{
        if(!emailSubscribersRepository.existsByUser(user)){
            throw new ValidationException("Not subscribed to email digest");
        }
        try{
            EmailSubscribers es = emailSubscribersRepository.findByUser(user);
            // this might not work, might have to do another query, try it first though
            emailAmenitiesRepository.delete(es.getEmailAmenities());
            emailSubscribersRepository.delete(es);
        }catch(Exception e){
            throw new ValidationException("Couldn't unsubscribe from digest, try again.");
        }
    }

}
