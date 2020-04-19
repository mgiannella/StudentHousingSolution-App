package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.repository.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
             LLSched.add(new Schedule(landlord,null,meetingTimes.get(i),null));
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


    public Boolean existsByLandlord(User landlord) {
        try{
            return scheduleRepository.existsByLandlord(landlord);
        }catch(Exception e){
            throw new ValidationException("Couldn't find Schedule By ID");
        }

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


}