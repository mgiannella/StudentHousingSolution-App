package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.NotificationType;
import com.softwareengineeringgroup8.studenthousingsolution.model.Notifications;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.repository.NotificationTypeRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Component
public class NotificationService {

    @Autowired
    private NotificationsRepository notificationsRepository;

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    public List<Notifications> getNotifications(User user) throws ValidationException {
        try {
            Timestamp currentDT = new Timestamp(Calendar.getInstance().getTime().getTime());
            return notificationsRepository.getNotificationsByUserAndDate(user, currentDT);
        }catch(Exception e){
            throw new ValidationException("Could not get notifications for user.");
        }
    }

    public int getAmtNotifs(User user) throws ValidationException {
        try {
            Timestamp currentDT = new Timestamp(Calendar.getInstance().getTime().getTime());
            return notificationsRepository.getNotificationAmountByUserAndDate(user, currentDT);
        }catch(Exception e){
            throw new ValidationException("Could not get notification amount for user.");
        }
    }


    // How to use
    // user (self explanatory)
    // desc = notification description (max 250 chars)
    // type = "SCHEDULE", "MAINTENANCE", "PAYMENT", "GROUP", "GENERAL" [one of those]
    // alertDT = "YYYY-MM-DD HH:MM:SS" (Time in 24HR format, ie. 14:00 instead of 2:00 PM) or empty string("") if you want it to be the current time
    // Will return true if successful, or throw exception if not successful
    public boolean createNotification(User user, String desc, String type, String alertDT) throws ValidationException {
        if(desc.length() > 250){
            System.out.println("NOTIFICATION-ERROR: Description string was too long");
            throw new ValidationException("Description string was too long");
        }
        Timestamp alertDate;
        if(alertDT.equals("")){
            alertDate = new Timestamp(Calendar.getInstance().getTime().getTime());
        }else {
            try {
                alertDate = Timestamp.valueOf(alertDT);
            } catch (Exception e) {
                System.out.println("NOTIFICATION-ERROR: Couldn't parse date");
                throw new ValidationException("Couldn't parse date");
            }
        }
        NotificationType notifType;
        try{
            notifType = notificationTypeRepository.getNotificationTypeByType(type);
        }catch(Exception e){
            System.out.println("NOTIFICATION-ERROR: Couldn't get type");
            throw new ValidationException("Couldn't get type");
        }
        try{
            Notifications notif = new Notifications(user, desc, notifType, alertDate);
            notificationsRepository.save(notif);
            return true;
        }catch(Exception e){
            System.out.println("NOTIFICATION-ERROR: Couldn't push notification to DB");
            throw new ValidationException("Couldn't push notification to DB");
        }
    }

    // How to use:
    // Pass in user and id of notification to be deleted
    // will return true if successful or throw exception if it doesn't work
    public boolean deleteNotification(User user, int id) throws ValidationException{
        Notifications notif = notificationsRepository.findById(id);
        if(notif == null){
            System.out.println("NOTIFICATION-ERROR: No notification exists with that ID");
            throw new ValidationException("No notification exists with that ID");
        }
        if(!notif.getUser().equals(user)){
            System.out.println("NOTIFICATION-ERROR: Notification is not assigned to this user.");
            throw new ValidationException("Notification is not assigned to this user");
        }
        try{
            notificationsRepository.delete(notif);
            return true;
        }catch(Exception e){
            System.out.println("NOTIFICATION-ERROR: Couldn't remove notification from DB");
            throw new ValidationException("NOTIFICATION-ERROR: Couldn't remove notification from DB");
        }
    }

}
