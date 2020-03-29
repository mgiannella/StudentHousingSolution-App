package com.softwareengineeringgroup8.studenthousingsolution.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="Amenities")
public class Amenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="amenityid")
    @JsonView(PropertyView.ExtendedInfo.class)
    private int amenityId;

    @Column(name="price")
    @JsonView(PropertyView.Search.class)
    private BigDecimal price;

    @Column(name="bedrooms")
    @JsonView(PropertyView.Search.class)
    private int numBedrooms;

    @Column(name="bathrooms")
    @JsonView(PropertyView.Search.class)
    private float numBathrooms;

    @Column(name="renovationdate")
    @JsonView(PropertyView.Compare.class)
    private Date renovationDate;

    @Column(name="airconditioning")
    @JsonView(PropertyView.Compare.class)
    private boolean hasAC;

    @Column(name="parkingspots")
    @JsonView(PropertyView.Compare.class)
    private int parkingSpots;

    @Column(name="laundry")
    @JsonView(PropertyView.Compare.class)
    private boolean hasLaundry;

    @Column(name="pets")
    @JsonView(PropertyView.Compare.class)
    private boolean petsAllowed;

    @Column(name="smoking")
    @JsonView(PropertyView.Compare.class)
    private boolean smokingAllowed;

    @Column(name="waterutil")
    @JsonView(PropertyView.Compare.class)
    private boolean waterUtility;

    @Column(name="gaselectricutil")
    @JsonView(PropertyView.Compare.class)
    private boolean gasElectricUtil;

    @Column(name="furnished")
    @JsonView(PropertyView.Compare.class)
    private boolean isFurnished;

    @Column(name="appliancesincl")
    @JsonView(PropertyView.Compare.class)
    private boolean hasAppliances;

    @Column(name="trashpickup")
    @JsonView(PropertyView.Compare.class)
    private boolean isTrashPickedUpl;

    @Column(name="heat")
    @JsonView(PropertyView.Compare.class)
    private boolean hasHeat;

    public Amenities(){

    }

    public Amenities(BigDecimal price, int numBedrooms, float numBathrooms, Date renovationDate, boolean hasAC, int parkingSpots, boolean hasLaundry, boolean petsAllowed, boolean smokingAllowed,
                     boolean waterUtility, boolean gasElectricUtil, boolean isFurnished, boolean hasAppliances, boolean trashPickedUpl, boolean hasHeat) {

        this.price = price;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.renovationDate = renovationDate;
        this.hasAC = hasAC;
        this.parkingSpots = parkingSpots;
        this.hasLaundry = hasLaundry;
        this.petsAllowed = petsAllowed;
        this.smokingAllowed = smokingAllowed;
        this.waterUtility = waterUtility;
        this.gasElectricUtil = gasElectricUtil;
        this.isFurnished=isFurnished;
        this.hasAppliances = hasAppliances;
        this.isTrashPickedUpl = trashPickedUpl;
        this.hasHeat = hasHeat;
    }








    public int getAmenityId() {
        return amenityId;
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

    public boolean isWaterUtility() {
        return waterUtility;
    }

    public void setWaterUtility(boolean waterUtility) {
        this.waterUtility = waterUtility;
    }

    public boolean isGasElectricUtil() {
        return gasElectricUtil;
    }

    public void setGasElectricUtil(boolean gasElectricUtil) {
        this.gasElectricUtil = gasElectricUtil;
    }

    public boolean isFurnished() {
        return isFurnished;
    }

    public void setFurnished(boolean furnished) {
        this.isFurnished = furnished;
    }

    public boolean isHasAppliances() {
        return hasAppliances;
    }

    public void setHasAppliances(boolean hasAppliances) {
        this.hasAppliances = hasAppliances;
    }

    public boolean isTrashPickedUpl() {
        return isTrashPickedUpl;
    }

    public void setTrashPickedUpl(boolean trashPickedUpl) {
        this.isTrashPickedUpl = trashPickedUpl;
    }

    public boolean isHasHeat() {
        return hasHeat;
    }

    public void setHasHeat(boolean hasHeat) {
        this.hasHeat = hasHeat;
    }
}