//written by: Rishi Shah
//tested by: Rishi Shah
//debugged by: Rishi Shah

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

public class ScheduleBooking
{
    @ApiModelProperty(notes="landlord",example="",required=true)
    private int landlordid;

    @ApiModelProperty(notes="landlord",example="",required=true)
    private int propertyid;

    @ApiModelProperty(notes="landlord",example="",required=true)
    private String meetingTime;


    public int getLandlordid() {
        return landlordid;
    }

    public void setLandlordid(int landlordid) {
        this.landlordid = landlordid;
    }

    public int getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(int propertyid) {
        this.propertyid = propertyid;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }


    public ScheduleBooking(int landlordid, int propertyid, String meetingTime) {
        this.landlordid = landlordid;
        this.propertyid = propertyid;
        this.meetingTime = meetingTime;
    }

    public ScheduleBooking() {

    }
}
