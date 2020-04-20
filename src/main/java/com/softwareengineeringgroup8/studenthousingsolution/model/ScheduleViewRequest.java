package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ScheduleViewRequest
{
    @ApiModelProperty(notes="view start date",example="",required=true)
    private String viewStart;

    @ApiModelProperty(notes="view end date",example="",required=true)
    private String viewEnd;

    public String getViewStart() {
        return viewStart;
    }

    public void setViewStart(String viewStart) {
        this.viewStart = viewStart;
    }

    public String getViewEnd() {
        return viewEnd;
    }

    public void setViewEnd(String viewEnd) {
        this.viewEnd = viewEnd;
    }

    public ScheduleViewRequest(String viewStart, String viewEnd) {
        this.viewStart = viewStart;
        this.viewEnd = viewEnd;
    }

    public ScheduleViewRequest(){

    }
}
