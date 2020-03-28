package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class MaintenanceRequestData implements Serializable{

    @ApiModelProperty(required = true, notes = "notes from tenant")
    private String notes;

    public MaintenanceRequestData(){
    }

    public MaintenanceRequestData(String notes){
        this.notes = notes;
    }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }


}
