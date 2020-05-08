/*
written by: Sneh Shah
tested by: Sneh Shah
debugged by: Sneh Shah
*/

package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Date;
import javax.validation.constraints.Size;

public class MaintenanceRequestData implements Serializable{

    @ApiModelProperty(required = true, notes = "notes from tenant")
    private String notes;

    @ApiModelProperty(required = true, notes = "propertyID")
    private int id;

    public MaintenanceRequestData(){
    }

    public MaintenanceRequestData(String notes){
        this.notes = notes;
    }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}

