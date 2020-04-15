package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.sql.Time;
import java.util.List;

public class ScheduleRequest
{
    @ApiModelProperty(notes="days available",example="",required=true)
    private List<String> availableDays;


    @ApiModelProperty(notes="times available",example="",required=true)
    private List<String> availableTimes;


    public List<String> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(List<String> availableDays) {
        this.availableDays = availableDays;
    }

    public List<String> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(List<String> availableTimes) {
        this.availableTimes = availableTimes;
    }

    public ScheduleRequest(List<String> availableDays, List<String> availableTimes) {
        this.availableDays = availableDays;
        this.availableTimes = availableTimes;
    }

    public ScheduleRequest(){

    }
}
