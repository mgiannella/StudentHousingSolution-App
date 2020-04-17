package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.repository.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class ScheduleService {
    @Autowired
    private PropertiesRepository propertiesRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Autowired
    private UserRepository userRepository;


    public void createSchedule(ScheduleRequest request, User landlord) {

         List<String> eventDates = request.getEventDates();
         List<Timestamp> meetingTimes = new ArrayList<Timestamp>();

         for (int i=0; i<eventDates.size();i++) {
             Timestamp addEvent = Timestamp.valueOf(eventDates.get(i));
             meetingTimes.add(addEvent);
         }

         List<Schedule> LLSched = new ArrayList<Schedule>();
         for (int i=0;i<meetingTimes.size();i++) {
             LLSched.add(new Schedule(landlord,meetingTimes.get(i)));
             scheduleRepository.save(LLSched.get(i));
         }



        /*
         List<String> dates= request.getDates();
         List<Integer> days = request.getDays();
         List<String> times = request.getTimes();


         List<LocalDate> localDates = new ArrayList<LocalDate>();

         for (int i=0;i<dates.size();i++) {
             LocalDate addLocalDate = LocalDate.parse(dates.get(i));
             localDates.add(addLocalDate);
            // System.out.println(localDates.get(i));
         }


         List<Integer> dayConvert= new ArrayList<Integer>();


         for (int i=0;i<dates.size();i++) {
             DayOfWeek d1 = DayOfWeek.from(localDates.get(i));
             int whatDay = d1.getValue();
             dayConvert.add(whatDay);
             //System.out.println(whatDay);
         }

         List<Integer> fulldays = new ArrayList<Integer>();
         List <Timestamp> meetingTimes = new ArrayList<Timestamp>();
         //for start day
         for (int i =0; i<dates.size(); i++) {
             for (int j = 0; j<days.size(); j++) { //
                 int currDay = days.get(i);  //1=mon,2=tues,3=wed,4=thurs,5=fri,6=sat, 7 = sunday
                 if (currDay==dayConvert.get(i)) {
                     Timestamp datetime = Timestamp.valueOf(dates.get(i)+" "+times.get(j));
                     System.out.println(datetime);
                 }
                 if ((j+1)!=days.size()) {
                     if (days.get(j)!=days.get(j+1)) {
                         break;
                     }
                 }
             }
         }
*/

        }








//edit schedule (landlord)
    public void editSchedule() {

    }



}