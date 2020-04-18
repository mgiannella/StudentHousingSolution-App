package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.sql.Time;
import java.util.List;

public class ScheduleRequest
{
    @ApiModelProperty(notes="days available",example="",required=true)
    private List<String> eventDates;


    public List<String> getEventDates() {
        return eventDates;
    }

    public void setEventDates(List<String> eventDates) {
        this.eventDates = eventDates;
    }

    public ScheduleRequest(List<String> eventDates) {
        this.eventDates = eventDates;
    }

    public ScheduleRequest(){

    }

    /*
     @ApiModelProperty(notes="days available",example="",required=true)
    private List<Integer> days;

    @ApiModelProperty(notes="times available",example="",required=true)
    private List<String> times;

    @ApiModelProperty(notes="date range",example="",required=true)
    private List<String> dates;



    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public ScheduleRequest(List<Integer> days, List<String> times, List<String> dates) {
        this.days = days;
        this.times = times;
        this.dates=dates;

    }

     */

}
