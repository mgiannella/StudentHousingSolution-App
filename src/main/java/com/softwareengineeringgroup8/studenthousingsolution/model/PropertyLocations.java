package com.softwareengineeringgroup8.studenthousingsolution.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="propertylocations")
public class PropertyLocations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="locationid")
    @JsonView(PropertyView.Search.class)
    private int id;

    @Column(name="streetaddress")
    @JsonView(PropertyView.Search.class)
    private String address;

    @Column(name="city")
    @JsonView(PropertyView.Search.class)
    private String city;

    @Column(name="stateabbrev")
    @JsonView(PropertyView.Search.class)
    private String state;

    @Column(name="zipcode")
    @JsonView(PropertyView.Search.class)
    private String zip;

    @JsonView(PropertyView.Search.class)
    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    @JsonView(PropertyView.Search.class)
    private String longitude;


    public PropertyLocations(){
    }

    public PropertyLocations(String address, String city, String state, String zip, String latitude, String longitude){
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;

    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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