package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

//@ApiModel(description="How a user's username and password are sent to the application")
public class ListingRequest implements Serializable {


    @Size(min=1, max=150)
    @ApiModelProperty(notes="title", example="House on Hamilton Street", required = true)
    private String title;

    @Size(min=6, max=25)
    @ApiModelProperty(notes="username", example="71 Delafield Street", required = true)
    private String address;

    @Size(min=1,max=50)
    @ApiModelProperty(notes="city", example="New Brunswick", required = true)
    private String city;

    @Size(min=2, max=2)
    @ApiModelProperty(notes="state abbreviation", example="NJ", required = true)
    private String state;

    @Size(min=5, max=5)
    @ApiModelProperty(notes="Zip Code", example="08904", required = true)
    private String zipCode;


    @ApiModelProperty(notes="Monthly rent for house", example="4500", required = true)
    private double price;


    @ApiModelProperty(notes="Number of bedrooms in the house", example="3",required=true)
    private int numBedrooms;

    @ApiModelProperty(notes="Number of bathrooms in the house",example="2.5",required=true)
    private double numBathrooms;

    @ApiModelProperty(notes="Date the property was last renovated",example="Format of date",required=true)
    private Date renovationDate;

    @ApiModelProperty(notes="Does the house have air conditioning",example="true",required=true)
    private boolean hasAC;

    @ApiModelProperty(notes="Number of parking spots",example="5",required=true)
    private int parkingspots;

    @ApiModelProperty(notes="Does the house have a laundry machine",example="true",required=true)
    private boolean hasLaundry;

    @ApiModelProperty(notes="Are pets allowed on the property",example="true",required=true)
    private boolean allowPets;

    @ApiModelProperty(notes="Is smoking allowed on the property",example="true",required=true)
    private boolean allowSmoking;


    public ListingRequest(String title, String address, String city, String state, String zipCode, double price, Date renovationDate, int numBedrooms, double numBathrooms, boolean hasAC, int parkingspots, boolean hasLaundry, boolean allowPets, boolean allowSmoking) {
        this.title = title;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.price = price;
        this.renovationDate=renovationDate;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.hasAC = hasAC;
        this.parkingspots = parkingspots;
        this.hasLaundry = hasLaundry;
        this.allowPets = allowPets;
        this.allowSmoking = allowSmoking;
    }

   public Date getRenovationDate() {
        return renovationDate;
    }

    public void setRenovationDate(Date renovationDate) {
        this.renovationDate = renovationDate;
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

    public double getNumBathrooms() {
        return numBathrooms;
    }

    public void setNumBathrooms(int doubleBathrooms) {
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