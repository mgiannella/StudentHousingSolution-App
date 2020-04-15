package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.JoinColumn;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name="Events")
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="eventid")
    private int id;

    @ManyToOne
    @JoinColumn(name="propertyid")
    private Properties property;

    @Column(name="eventDate")
    private Date eventDate;

    @Column(name="eventTime")
    private Time eventTime;

    @Column(name="eventName")
    private String eventName;

    @Column(name="eventDesc")
    private String eventDesc;

    @ManyToOne
    @JoinColumn(name="landlordID", referencedColumnName = "userid")
    private User landlord;

    @ManyToOne
    @JoinColumn(name="tenantid", referencedColumnName = "userid")
    private User tenant;



    public Properties getProperty() {
        return property;
    }
    public void setProperty(Properties property) {
        this.property = property;
    }

    public Date getEventDate() {
        return eventDate;
    }
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getEventTime() {
        return eventTime;
    }
    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }
    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public User getLandlord() {
        return landlord;
    }
    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public User getTenant() {
        return tenant;
    }
    public void setTenant(User tenant) {
        this.tenant = tenant;
    }


    public Event(Properties property, Date eventDate, Time eventTime, String eventName, String eventDesc, User landlord, User tenant) {
        this.property = property;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.landlord = landlord;
        this.tenant = tenant;
    }

    public Event(){

    }














}
