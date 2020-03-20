package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Amenities")
public class Amenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="amenityid")
    private int amenityId;

    @Column(name="price")
    private int price;

    @Column(name="bedrooms")
    private int numBedrooms;

    @Column(name="bathrooms")
    private int numBathrooms;

    @Column(name="renovationdate")
    private Date renovationDate;

    @Column(name="airconditioning")
    private boolean hasAC;

    @Column(name="parkingspots")
    private int parkingSpots;

    @Column(name="laundry")
    private boolean hasLaundry;

    @Column(name="pets")
    private boolean petsAllowed;

    @Column(name="smoking")
    private boolean smokingAllowed;

    public Amenities(int price, int numBedrooms, int numBathrooms, Date renovationDate, boolean hasAC, int parkingSpots, boolean hasLaundry, boolean petsAllowed, boolean smokingAllowed) {
        this.price = price;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.renovationDate = renovationDate;
        this.hasAC = hasAC;
        this.parkingSpots = parkingSpots;
        this.hasLaundry = hasLaundry;
        this.petsAllowed = petsAllowed;
        this.smokingAllowed = smokingAllowed;
    }

    public int getAmenityId() {
        return amenityId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public Date getRenovationDate() {
        return renovationDate;
    }

    public void setRenovationDate(Date renovationDate) {
        this.renovationDate = renovationDate;
    }

    public boolean isHasAC() {
        return hasAC;
    }

    public void setHasAC(boolean hasAC) {
        this.hasAC = hasAC;
    }

    public int getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(int parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public boolean isHasLaundry() {
        return hasLaundry;
    }

    public void setHasLaundry(boolean hasLaundry) {
        this.hasLaundry = hasLaundry;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public boolean isSmokingAllowed() {
        return smokingAllowed;
    }

    public void setSmokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }
}