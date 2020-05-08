/*
written by: Sneh Shah
tested by: Sneh Shah
debugged by: Sneh Shah
*/
package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Date;
import javax.validation.constraints.Size;

public class MaintenanceUpdateData implements Serializable {

    @Size(min=6, max=11)
    @ApiModelProperty(required = true, notes = "statusID")
    private String statusDesc;

    public MaintenanceUpdateData(){

    }

    public String getStatusDesc() { return statusDesc; }

    public void setStatusDesc(String statusDesc) { this.statusDesc = statusDesc; }

    public MaintenanceUpdateData(String statusDesc){
        this.statusDesc = statusDesc;
    }
}
