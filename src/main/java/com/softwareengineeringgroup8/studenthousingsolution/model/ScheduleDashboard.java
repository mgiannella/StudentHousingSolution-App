package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ScheduleDashboard
{
    @ApiModelProperty(notes="scheduled events",example="",required=true)
    private User user;

    @ApiModelProperty(notes="scheduled events",example="",required=true)
    private List<String> events;

    @ApiModelProperty(notes="scheduled events",example="",required=true)
    private List<String> locations;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocation(List<String> locations) {
        this.locations = locations;
    }

    public ScheduleDashboard(User user, List<String> events, List<String> locations) {
        this.user = user;
        this.events = events;
        this.locations= locations;
    }

    public ScheduleDashboard() {

    }
}
