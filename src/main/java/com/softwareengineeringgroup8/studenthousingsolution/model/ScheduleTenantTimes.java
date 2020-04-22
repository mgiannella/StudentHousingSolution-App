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

public class ScheduleTenantTimes
{
    @ApiModelProperty(notes="landlord",example="",required=true)
    private User landlord;

    @ApiModelProperty(notes="list of available datetime",example="",required=true)
    private List<String> times;

    public User getLandlord() {
        return landlord;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public ScheduleTenantTimes(User landlord, List<String> times) {
        this.landlord = landlord;
        this.times = times;
    }

    public ScheduleTenantTimes() {

    }
}
