package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="propertylocations")
public class PropertyLocations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="locationid")
    private int id;

    @Column(name="streetaddress")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="stateabbrev")
    private String state;

    @Column(name="zipcode")
    private String zip;

    public PropertyLocations(String address, String city, String state, String zip){
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public int getId() {return this.id;}
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
