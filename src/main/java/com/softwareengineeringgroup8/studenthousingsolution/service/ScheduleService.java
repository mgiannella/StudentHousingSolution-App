package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleService {
    @Autowired
    private PropertiesRepository propertiesRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;


    //create schedule

    public void createSchedule(ScheduleRequest request, User landlord) {
        List<String> days = request.getAvailableDays();
        List<String> list = request.getAvailableTimes();
        List<Time> times = new ArrayList<Time>();

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            times.set(i, Time.valueOf(str));
        }


        Schedule createSchedule = new Schedule(landlord, days, times);
    }

    //edit schedule
    public void editSchedule() {

    }


    //create event
    public void createEvent(EventRequest request, User tenant) {

    }

    //edit event (EVERYTHING BUT DATE)
    public void editEvent() {


    }


    //delete event
    public void deleteEvent() {
    }
}