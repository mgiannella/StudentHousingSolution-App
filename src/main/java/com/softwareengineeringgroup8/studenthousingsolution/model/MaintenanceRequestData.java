package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Date;
import javax.validation.constraints.Size;

public class MaintenanceRequestData implements Serializable{

    /*@Size(min = 2, max = 2)
    @ApiModelProperty(required = true, notes = "month")
    private String month;

    @Size(min = 2, max = 2)
    @ApiModelProperty(required = true, notes = "day")
    private String day;

    @Size(min = 4, max = 4)
    @ApiModelProperty(required = true, notes = "year")
    private String year;

     */

    @ApiModelProperty(required = true, notes = "notes from tenant")
    private String notes;

    public MaintenanceRequestData(){
    }

    public MaintenanceRequestData(String month, String day, String year, String notes){
        //this.month = month;
        //this.day = day;
        //this.year = year;
        this.notes = notes;

    }

    /*public String getMonth() { return month; }

    public void setMonth(String month) { this.month = month; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    public String getDay() { return day; }

    public void setDay(String day) { this.day = day; }

     */

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }


}
