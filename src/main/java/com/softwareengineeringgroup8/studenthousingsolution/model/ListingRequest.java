package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.softwareengineeringgroup8.studenthousingsolution.model.PropertyPhotos;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.math.BigDecimal;
import java.util.List;

//@ApiModel(description="How a user's username and password are sent to the application")
public class ListingRequest implements Serializable {

    @Size(min=1, max=250)
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
    private BigDecimal price;


    @ApiModelProperty(notes="Number of bedrooms in the house", example="3",required=true)
    private int numBedrooms;

    @ApiModelProperty(notes="Number of bathrooms in the house",example="2.5",required=true)
    private float numBathrooms;

    @ApiModelProperty(notes="Date the property was last renovated",example="Format of date",required=true)
    private String renovationDate;

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

    @ApiModelProperty(notes="Is there running water in the house?", example="true", required=true)
    private boolean hasWater;

    @ApiModelProperty(notes="Is there gas and electricity?", example="true",required=true)
    private boolean hasGasElec;

    @ApiModelProperty(notes="Is the house fully furnished?", example="true",required=true)
    private boolean isFurnished;

    @ApiModelProperty(notes="Does the house come with essential appliances?", example="true",required=true)
    private boolean hasAppliances ;

    @ApiModelProperty(notes="Does the house have trash pickup?", example="true",required=true)
    private boolean hasTrashPickup;

    @ApiModelProperty(notes="Does the house have trash pickup?", example="true",required=true)
    private String desc;

    @ApiModelProperty(notes="How many can sleep in house?", example="3",required=true)
    private int sleeps;

    @ApiModelProperty(notes="Does the house have a heating unit?", example="true",required=true)
    private boolean hasHeat;

    @ApiModelProperty(notes="Add photo",example="hi.jpg",required=true)
    private List<String> photos;

    public List<String> getPhotos() {
        return photos;
    }
    public void setPhotos(List <String> photos) {
        this.photos = photos;
    }

    public String 

    public int getSleeps() { return sleeps; }
    public void setSleeps(int sleeps) { this.sleeps =sleeps; }

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

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumBedrooms() {
        return numBedrooms;
    }
    public void setNumBedrooms(int numBedrooms) {
        this.numBedrooms = numBedrooms;
    }

    public float getNumBathrooms() {
        return numBathrooms;
    }
    public void setNumBathrooms(float numBathrooms) {
        this.numBathrooms = numBathrooms;
    }

    public String getRenovationDate() { return renovationDate; }
    public void setRenovationDate(String renovationDate) {
        this.renovationDate = renovationDate;
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

    public boolean isHasWater() {
        return hasWater;
    }
    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public boolean isHasGasElec() {
        return hasGasElec;
    }
    public void setHasGasElec(boolean hasGasElec) {
        this.hasGasElec = hasGasElec;
    }

    public boolean isFurnished() {
        return isFurnished;
    }
    public void setFurnished(boolean furnished) {
        isFurnished = furnished;
    }

    public boolean isHasAppliances() {
        return hasAppliances;
    }
    public void setHasAppliances(boolean hasAppliances) {
        this.hasAppliances = hasAppliances;
    }

    public boolean isHasTrashPickup() {
        return hasTrashPickup;
    }
    public void setHasTrashPickup(boolean hasTrashPickup) {
        this.hasTrashPickup = hasTrashPickup;
    }

    public boolean isHasHeat() {
        return hasHeat;
    }
    public void setHasHeat(boolean hasHeat) {
        this.hasHeat = hasHeat;
    }


    public String getDesc() { return desc; }
    public void setDesc(String desc) {
        this.desc = desc;
    }



    public ListingRequest(String title, String description, String address, String city, String state, String zipCode,
                          BigDecimal price, int numBedrooms, float numBathrooms, String renovationDate, boolean hasAC,
                          int parkingspots, boolean hasLaundry, boolean allowPets, boolean allowSmoking, boolean hasWater,
                          boolean hasGasElec, boolean isFurnished, boolean hasAppliances, boolean hasTrashPickup, boolean hasHeat,
                          int sleeps, List<String> photos) {
        this.title = title;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.price = price;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.renovationDate = renovationDate;
        this.hasAC = hasAC;
        this.parkingspots = parkingspots;
        this.hasLaundry = hasLaundry;
        this.allowPets = allowPets;
        this.allowSmoking = allowSmoking;
        this.hasWater = hasWater;
        this.hasGasElec = hasGasElec;
        this.isFurnished = isFurnished;
        this.hasAppliances = hasAppliances;
        this.hasTrashPickup = hasTrashPickup;
        this.hasHeat = hasHeat;
        this.desc=description;
        this.sleeps=sleeps;
        this.photos = photos;
    }




    //@ApiModelProperty(notes="Password", example="Secretword1", required = true)
    //@Size(min=1, max=30)
    //private String password;

    public ListingRequest()
    {
    }



}