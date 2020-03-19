package com.softwareengineeringgroup8.studenthousingsolution.model;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="Amenities")
public class Amenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="amenityid")
    private int amenityID;

    @Column(name="price")
    private double price;

    @Column(name="bedrooms")
    private int numBedroom;

    @Column(name="bathrooms")
    private int numBathrooms;

    @Column(name="airconditioning")
    private boolean hasAC; //0 means no, 1 means yes

    @Column(name="parkingspots")
    private int parkingspots;

    @Column(name="laundry")
    private boolean hasLaundry;

    @Column(name="pets")
    private boolean allowPets;

    @Column(name="smoking")
    private boolean allowSmoking;

    public Amenities() {
    }

    public int getAmenityID() {
        return amenityID;
    }

    public void setAmenityID(int amenityID) {
        this.amenityID = amenityID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumBedroom() {
        return numBedroom;
    }

    public void setNumBedroom(int numBedroom) {
        this.numBedroom = numBedroom;
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



    public Amenities(double price, int numBedroom, int numBathrooms, boolean hasAC, int parkingspots, boolean hasLaundry, boolean allowPets, boolean allowSmoking) {
        this.price = price;
        this.numBedroom = numBedroom;
        this.numBathrooms = numBathrooms;
        this.hasAC = hasAC;
        this.parkingspots = parkingspots;
        this.hasLaundry = hasLaundry;
        this.allowPets = allowPets;
        this.allowSmoking = allowSmoking;
    }





}




