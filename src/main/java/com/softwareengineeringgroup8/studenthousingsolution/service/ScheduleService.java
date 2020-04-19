package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.repository.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.validation.Validation;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class ScheduleService {
    @Autowired
    private PropertiesRepository propertiesRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPermissionService userPermissionService;


    public void createSchedule(ScheduleRequest request, User landlord) {

         List<String> eventDates = request.getEventDates();
         List<Timestamp> meetingTimes = new ArrayList<Timestamp>();

         for (int i=0; i<eventDates.size();i++) {
             Timestamp addEvent = Timestamp.valueOf(eventDates.get(i));
             meetingTimes.add(addEvent);
         }
         Collections.sort(meetingTimes);

         List<Schedule> LLSched = new ArrayList<Schedule>();
         for (int i=0;i<meetingTimes.size();i++) {
             LLSched.add(new Schedule(landlord,null,meetingTimes.get(i),null));
             scheduleRepository.save(LLSched.get(i));
         }




        }





//edit schedule (landlord)
    public void editSchedule() {

    }




    public ScheduleTenantTimes ListTimes(User landlord) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            List<Schedule> scheds = scheduleRepository.findByLandlord(landlord);
            List<LocalDateTime> listEverything = new ArrayList<>();
            for (int i=0; i<scheds.size();i++) {
                Timestamp add = scheds.get(i).getMeetingTimes();
                LocalDateTime ldt = add.toLocalDateTime();
                listEverything.add(ldt);
            }

            List<String> update = new ArrayList<>();
            for (int i=0; i<listEverything.size();i++) {
                if (listEverything.get(i).isAfter(now) && scheds.get(i).getTenant()==null) {
                    LocalDateTime change = listEverything.get(i);
                    DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = change.format(newFormat);
                    update.add(formattedDate);
                }
            }

            if (update.isEmpty()==true) {
                throw new ValidationException("No available times");
            }

            else {
                ScheduleTenantTimes bookingTimes = new ScheduleTenantTimes(landlord,update);
                return bookingTimes;
            }
        }



    public void makeBooking(ScheduleBooking booking, User tenant) {
        Timestamp meeting = Timestamp.valueOf(booking.getMeetingTime());


        int id = booking.getLandlordid();
        User landlord = userRepository.findById(id);

        int propid = booking.getPropertyid();
        Properties property = propertiesRepository.findById(propid);

        User l2 = property.getLandlord();

        if (!(l2.equals(landlord))) {
            throw new ValidationException("Property is not this landlord's house.");
        }

        List<Schedule> scheds = scheduleRepository.findByLandlord(landlord);

        boolean foundEqual = false;

        for (int i=0; i<scheds.size();i++) {
            if (meeting.equals(scheds.get(i).getMeetingTimes())) {
                if (scheds.get(i).getTenant() != null) {
                    throw new ValidationException("booking already taken");
                }
                Schedule schedule = scheds.get(i);
                schedule.setProps(property);
                schedule.setTenant(tenant);
                scheduleRepository.save(schedule);
                foundEqual=true;
                break;
            }
        }

        if (!foundEqual) {
            throw new ValidationException("Could not find available time that matched with selected time");
        }




    }


    public ScheduleDashboard displayAllBookedEvents(User user) {
        if (userPermissionService.assertPermission(user, UserRoles.ROLE_TENANT)) { //if tenant
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            List<Schedule> scheds = scheduleRepository.findByTenant(user);
            List<LocalDateTime> listEverything = new ArrayList<>();
            for (int i=0; i<scheds.size();i++) {
                Timestamp add = scheds.get(i).getMeetingTimes();
                LocalDateTime ldt = add.toLocalDateTime();
                listEverything.add(ldt);
            }

            List<String> update = new ArrayList<>();
            List<String> eventLocations = new ArrayList<>();
            List<Integer> id = new ArrayList<>();
            for (int i=0; i<listEverything.size();i++) {
                if (listEverything.get(i).isAfter(now)) {
                    LocalDateTime change = listEverything.get(i);
                    DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = change.format(newFormat);
                    update.add(formattedDate);


                    Properties properties = scheds.get(i).getProps();
                    PropertyLocations location = properties.getLocation();
                    String address = location.getAddress();
                    String city = location.getCity();
                    String state = location.getState();
                    String zip = location.getZip();
                    eventLocations.add(address+", "+city+", "+state+" "+zip);


                    int addID = scheds.get(i).getScheduleid();
                    id.add(addID);
                }
            }
            if (update.isEmpty()==true) {
                throw new ValidationException("Tenant has no meetings coming up");
            }


            ScheduleDashboard dashboard = new ScheduleDashboard(id,user,update,eventLocations);
            return dashboard;

        }

        else { //if landlord
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            List<Schedule> scheds = scheduleRepository.findByLandlord(user);
            List<LocalDateTime> listEverything = new ArrayList<>();
            for (int i=0; i<scheds.size();i++) {
                Timestamp add = scheds.get(i).getMeetingTimes();
                LocalDateTime ldt = add.toLocalDateTime();
                listEverything.add(ldt);
            }

            List<String> update = new ArrayList<>();
            List<String> eventLocations = new ArrayList<>();
            List<Integer> id = new ArrayList<>();
            for (int i=0; i<listEverything.size();i++) {
                if (listEverything.get(i).isAfter(now) && scheds.get(i).getTenant()!=null) {
                    LocalDateTime change = listEverything.get(i);
                    DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = change.format(newFormat);
                    update.add(formattedDate);


                    Properties properties = scheds.get(i).getProps();
                    PropertyLocations location = properties.getLocation();
                    String address = location.getAddress();
                    String city = location.getCity();
                    String state = location.getState();
                    String zip = location.getZip();
                    eventLocations.add(address+", "+city+", "+state+" "+zip);


                    int addID = scheds.get(i).getScheduleid();
                    id.add(addID);
                }
            }
            if (update.isEmpty()==true) {
                throw new ValidationException("Landlord has no meetings coming up");
            }


            ScheduleDashboard dashboard = new ScheduleDashboard(id,user,update,eventLocations);
            return dashboard;
        }


    }


    public ScheduleView findByLandlord(User landlord) {
            try{
                List<Schedule> scheds = scheduleRepository.findByLandlord(landlord);
                List<LocalDate> date = new ArrayList<LocalDate>();
                List<LocalTime> time = new ArrayList<LocalTime>();
                //List<Timestamp> fakeNews = new ArrayList<Timestamp>();
                for (int i =0;i<scheds.size();i++) {
                    Timestamp add = scheds.get(i).getMeetingTimes();
                    LocalDateTime ldt = add.toLocalDateTime();
                    LocalDate ld = ldt.toLocalDate();
                    date.add(ld);
                    LocalTime lt = ldt.toLocalTime();
                    time.add(lt);
                }
                ScheduleView view = new ScheduleView();
                view.setStartDate(date.get(0));
                view.setEndDate(date.get(date.size()-1));
                int weekStart = date.get(0).getDayOfMonth();
                int weekEnd = weekStart+7;
                List<LocalTime> weekTimes = new ArrayList<LocalTime>();
                List<DayOfWeek> weekDays = new ArrayList<DayOfWeek>();

                for (int i=0; i<date.size();i++) {
                    int whatDate = date.get(i).getDayOfMonth();
                    if (whatDate>=weekEnd) {
                        break;
                    }
                    DayOfWeek whatDay = date.get(i).getDayOfWeek();
                    weekDays.add(whatDay);
                    weekTimes.add(time.get(i));
                }

                List<Integer> daysAsInts = new ArrayList<Integer>();
                for (int i=0;i<weekDays.size();i++) {
                    daysAsInts.add(weekDays.get(i).getValue());
                }


                view.setDays(daysAsInts);
                view.setTimes(weekTimes);



                return view;
            }catch(Exception e){
                throw new ValidationException("Couldn't find Times By Id");
            }

    }



    public Boolean existsByLandlord(User landlord) {
        try{
            return scheduleRepository.existsByLandlord(landlord);
        }catch(Exception e){
            throw new ValidationException("Couldn't find Schedule By ID");
        }

    }


}