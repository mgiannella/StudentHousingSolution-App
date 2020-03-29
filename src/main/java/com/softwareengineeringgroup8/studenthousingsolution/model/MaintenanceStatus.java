package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;

@Entity
@Table(name="MaintenanceStatus")
public class MaintenanceStatus {

    @Id
    @Column(name="MaintenanceStatusID")
    private int statusID;

    @Column(name="MaintenanceStatusDesc")
    private String statusDesc;

    public MaintenanceStatus(){
    }

    public int getStatusID() { return statusID; }

    public void setStatusID(int statusID) { this.statusID = statusID; }

    public void setStatusDescription(String statusDesc) { this.statusDesc = statusDesc; }

    public String getStatusDescription() { return statusDesc; }


    public MaintenanceStatus(String statusDescription, int statusID){
        this.statusDesc = statusDescription;
        this.statusID = statusID;
    }
}