package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ScheduleView
{
    @ApiModelProperty(notes="days available",example="",required=true)
    private List<Integer> days;

    @ApiModelProperty(notes="times available",example="",required=true)
    private List<LocalTime> times;

    @ApiModelProperty(notes="start date",example="",required=true)
    private LocalDate startDate;

    @ApiModelProperty(notes="end date",example="",required=true)
    private LocalDate endDate;

    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public List<LocalTime> getTimes() {
        return times;
    }

    public void setTimes(List<LocalTime> times) {
        this.times = times;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ScheduleView(List<Integer> days, List<LocalTime> times, LocalDate startDate, LocalDate endDate) {
        this.days = days;
        this.times = times;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ScheduleView() {

    }
}
