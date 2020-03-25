package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="MaintenanceRequests")
public class MaintenanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MaintenanceRequestID")
    private int requestID;

    @Column(name="PropertyID")
    private int propertyID;

    @Column(name="RequestDate")
    private Date date;

    @Column(name="MaintenanceNotes")
    private String notes;

    @ManyToOne
    @JoinColumn(name="MaintenanceStatusID")
    private MaintenanceStatus status;


    public MaintenanceRequest(){

    }

    public int getRequestID() { return this.requestID; }
    public void setRequestID(int requestID) { this.requestID = requestID; }

    public int getPropertyID() { return this.propertyID; }
    public void setPropertyID(int propertyID) { this.propertyID = propertyID; }

    public Date getDate() { return this.date; }
    public void setDate(Date date) { this.date = date; }

    public String getNotes() { return this.notes; }
    public  void setNotes(String notes) { this.notes = notes; }

    public MaintenanceStatus getStatus() { return status; }

    public void setStatus(MaintenanceStatus status) { this.status = status; }

    public MaintenanceRequest(MaintenanceStatus status, int propertyID, Date date, String notes){
        this.status= status;
        this.propertyID = propertyID;
        this.date = date;
        this.notes = notes;
    }




}
