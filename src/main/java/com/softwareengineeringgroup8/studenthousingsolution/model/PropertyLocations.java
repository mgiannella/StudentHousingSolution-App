package com.softwareengineeringgroup8.studenthousingsolution.model;

package com.softwareengineeringgroup8.studenthousingsolution.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
@Entity
@Table(name="PropertyLocations")
public class PropertyLocations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="locationid")
    private int locID;

    @Column(name="streetaddress")
    private String street;

    @Column(name="city")
    private String city;

    @Column(name="stateAbbrev")
    private String state;




    @Column(name="zipCode")
    private String zipCode;


    public PropertyLocations() {

    }


    public int getLocID() {
        return locID;
    }

    public void setLocID(int locID) {
        this.locID = locID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public PropertyLocations(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }




}




