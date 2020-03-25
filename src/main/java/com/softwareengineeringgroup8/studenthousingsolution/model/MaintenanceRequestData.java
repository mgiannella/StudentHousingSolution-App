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

    public MaintenanceRequestData(){

    }

    public MaintenanceRequestData(String statusDesc, Date date, String notes){
        this.statusDesc = statusDesc;
        this.date = date;
        this.notes = notes;
    }

    public String getStatusDesc() { return statusDesc; }

    public void setStatusDesc(String statusID) { this.statusDesc = statusDesc; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }


}
