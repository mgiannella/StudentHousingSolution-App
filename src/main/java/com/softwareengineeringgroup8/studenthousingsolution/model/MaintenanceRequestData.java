package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Date;
import javax.validation.constraints.Size;

public class MaintenanceRequestData implements Serializable{

    @Size(min=6, max=11)
    @ApiModelProperty(required = true, notes = "statusID")
    private String statusDesc;

    @ApiModelProperty(required = true, notes = "date")
    private Date date;

    @ApiModelProperty(required = true, notes = "notes from tenant")
    private String notes;

    @Size(min=6, max=25)
    @ApiModelProperty(required = true, notes = "username")
    private String username;

    public MaintenanceRequestData(){
    }

    public MaintenanceRequestData(String statusDesc, Date date, String notes, String username){
        this.statusDesc = statusDesc;
        this.date = date;
        this.notes = notes;
        this.username = username;
    }

    public String getStatusDesc() { return statusDesc; }

    public void setStatusDesc(String statusID) { this.statusDesc = statusDesc; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}
