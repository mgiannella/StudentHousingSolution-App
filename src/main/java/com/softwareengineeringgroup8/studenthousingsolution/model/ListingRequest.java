package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.io.Serializable;

//@ApiModel(description="How a user's username and password are sent to the application")
public class ListingRequest implements Serializable {

    //@ApiModelProperty(notes="Username", example="testuser", required = true)
    //@Size(min=1, max=25)
    private String title;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private double price;

 /*   @ApiModelProperty(notes="Phone Number, with no dashes or spaces", example="5555555555", required = true)
    private String phone;
    @Size(min=2, max=2)
    */

    @ApiModelProperty(notes="number of bedrooms in the house", example="3",required=true)
    private int numBedrooms;
    private int numBathrooms;
    //dateTime
    private boolean hasAC;
    private int parkingspots;
    private boolean hasLaundry;
    private boolean allowPets;
    private boolean allowSmoking;


    public ListingRequest(String title, String address, String city, String state, String zipCode, double price, int numBedrooms, int numBathrooms, boolean hasAC, int parkingspots, boolean hasLaundry, boolean allowPets, boolean allowSmoking) {
        this.title = title;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.price = price;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.hasAC = hasAC;
        this.parkingspots = parkingspots;
        this.hasLaundry = hasLaundry;
        this.allowPets = allowPets;
        this.allowSmoking = allowSmoking;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumBedrooms() {
        return numBedrooms;
    }

    public void setNumBedrooms(int numBedrooms) {
        this.numBedrooms = numBedrooms;
    }

    public int getNumBathrooms() {
        return numBathrooms;
    }

    public void setNumBathrooms(int numBathrooms) {
        this.numBathrooms = numBathrooms;
    }

    public boolean isHasAC() {
        return hasAC;
    }

    public void setHasAC(boolean hasAC) {
        this.hasAC = hasAC;
    }

    public int getParkingspots() {
        return parkingspots;
    }

    public void setParkingspots(int parkingspots) {
        this.parkingspots = parkingspots;
    }

    public boolean isHasLaundry() {
        return hasLaundry;
    }

    public void setHasLaundry(boolean hasLaundry) {
        this.hasLaundry = hasLaundry;
    }

    public boolean isAllowPets() {
        return allowPets;
    }

    public void setAllowPets(boolean allowPets) {
        this.allowPets = allowPets;
    }

    public boolean isAllowSmoking() {
        return allowSmoking;
    }

    public void setAllowSmoking(boolean allowSmoking) {
        this.allowSmoking = allowSmoking;
    }



    //@ApiModelProperty(notes="Password", example="Secretword1", required = true)
    //@Size(min=1, max=30)
    //private String password;

    public ListingRequest()
    {
    }



}