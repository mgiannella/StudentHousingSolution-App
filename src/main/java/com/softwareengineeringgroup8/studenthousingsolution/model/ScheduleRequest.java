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


}
