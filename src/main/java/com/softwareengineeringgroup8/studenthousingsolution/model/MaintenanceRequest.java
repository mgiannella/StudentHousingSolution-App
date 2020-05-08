/*
written by: Sneh Shah
tested by: Sneh Shah
debugged by: Sneh Shah
*/

package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="MaintenanceRequests")
public class MaintenanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MaintenanceRequestID")
    private int requestID;

    @ManyToOne
    @JoinColumn(name="PropertyID")
    private Properties prop;

    @Column(name="RequestDate")
    private Timestamp date;

    @Column(name="MaintenanceNotes")
    private String notes;

    @ManyToOne
    @JoinColumn(name="MaintenanceStatusID")
    private MaintenanceStatus status;

    @ManyToOne
    @JoinColumn(name="tenantID", referencedColumnName = "userid")
    private User tenant;

    public MaintenanceRequest(){
    }


    public int getRequestID() { return this.requestID; }
    public void setRequestID(int requestID) { this.requestID = requestID; }

    public Properties getProperty() { return this.prop; }
    public void setProperty(Properties propertyID) { this.prop = prop; }


    public Timestamp getDate() { return this.date; }
    public void setDate(Timestamp date) { this.date = date; }

    public String getNotes() { return this.notes; }
    public  void setNotes(String notes) { this.notes = notes; }

    public MaintenanceStatus getStatus() { return status; }
    public void setStatus(MaintenanceStatus status) { this.status = status; }

    public User getTenant() { return tenant; }
    public void setTenant(User tenant) { this.tenant = tenant; }

    public MaintenanceRequest(MaintenanceStatus status, Properties prop, Timestamp date, String notes, User tenant){

        this.status = status;
        this.prop = prop;
        this.date = date;
        this.notes = notes;
        this.tenant = tenant;

    }




}
