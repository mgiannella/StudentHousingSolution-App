package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

public class ListingUpdate {


        private String title;

        private BigDecimal price;

        private int numBedrooms;

        private float numBathrooms;

        private String renovationDate;

        private boolean hasAC;

        private int parkingspots;

        private boolean hasLaundry;

        private boolean allowPets;

        private boolean allowSmoking;

        private boolean hasWater;

        private boolean hasGasElec;

        private boolean isFurnished;

        private boolean hasAppliances ;

        private boolean hasTrashPickup;

        private String desc;

        private int sleeps;

        private boolean hasHeat;

        private String photos;

        private int propertyID;



        public String address;
        public String city;
        public String state;

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

    public String zipCode;

        public int getPropertyID() { return propertyID;}
        public void setPropertyID(int propertyID) {this.propertyID=propertyID;}

        public String getPhotos() {
            return photos;
        }
        public void setPhotos(String photos) {
            this.photos = photos;
        }


        public int getSleeps() { return sleeps; }
        public void setSleeps(int sleeps) { this.sleeps =sleeps; }

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
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






        public ListingUpdate(int propertyID, String address, String city, String state, String zipCode, String title, String description, BigDecimal price, int numBedrooms, float numBathrooms, String renovationDate, boolean hasAC,
                              int parkingspots, boolean hasLaundry, boolean allowPets, boolean allowSmoking, boolean hasWater,
                              boolean hasGasElec, boolean isFurnished, boolean hasAppliances, boolean hasTrashPickup, boolean hasHeat,
                              int sleeps, String photos) {
            this.propertyID=propertyID;
            this.address=address;
            this.city=city;
            this.state=state;
            this.zipCode=zipCode;
            this.title = title;
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



        public ListingUpdate()
        {
        }


    }

