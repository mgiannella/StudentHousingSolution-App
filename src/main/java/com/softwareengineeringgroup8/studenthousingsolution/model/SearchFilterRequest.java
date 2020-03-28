package com.softwareengineeringgroup8.studenthousingsolution.model;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@ApiModel(description = "Contains the information that a user sends when filtering search")
public class SearchFilterRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private SearchFilterBedroom bedrooms;

    private SearchFilterBathroom bathrooms;

    private SearchFilterParkingSpace parking;

    private SearchFilterPrice price;

    private SearchFilterSleeps sleeps;

    @ApiModelProperty(notes="Does the property have central air conditioning?", example="true", required=false)
    private boolean hasAC;

    @ApiModelProperty(notes="Does the property have on-site laundry?", example="true", required=false)
    private boolean hasLaundry;

    @ApiModelProperty(notes="Does the property allow pets?", example="true", required=false)
    private boolean petsAllowed;

    @ApiModelProperty(notes="Can the tenant smoke in the property?", example="true", required=false)
    private boolean smokingAllowed;

    @ApiModelProperty(notes="Does the tenant have to pay the water bill?", example="true", required=false)
    private boolean waterUtility;

    @ApiModelProperty(notes="Does the tenant have to pay gas and electricity?", example="true", required=false)
    private boolean gasElectricUtil;

    @ApiModelProperty(notes="Does the property have all the furniture it needs?", example="true", required=false)
    private boolean isFurnished;

    @ApiModelProperty(notes="Does the property have appliances already?", example="true", required=false)
    private boolean hasAppliances;

    @ApiModelProperty(notes="Does the property offer trash pickup by town or landlord?", example="true", required=false)
    private boolean isTrashPickedUpl;

    @ApiModelProperty(notes="Does the property have heat?", example="true", required=false)
    private boolean hasHeat;

    public SearchFilterRequest() {
    }

    public SearchFilterSleeps getSleeps() {
        return sleeps;
    }

    public void setSleeps(SearchFilterSleeps sleeps) {
        this.sleeps = sleeps;
    }

    public SearchFilterBedroom getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(SearchFilterBedroom bedrooms) {
        this.bedrooms = bedrooms;
    }

    public SearchFilterBathroom getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(SearchFilterBathroom bathrooms) {
        this.bathrooms = bathrooms;
    }

    public SearchFilterParkingSpace getParking() {
        return parking;
    }

    public void setParking(SearchFilterParkingSpace parking) {
        this.parking = parking;
    }

    public SearchFilterPrice getPrice() {
        return price;
    }

    public void setPrice(SearchFilterPrice price) {
        this.price = price;
    }

    public boolean isHasAC() {
        return hasAC;
    }

    public void setHasAC(boolean hasAC) {
        this.hasAC = hasAC;
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
        isFurnished = furnished;
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
        isTrashPickedUpl = trashPickedUpl;
    }

    public boolean isHasHeat() {
        return hasHeat;
    }

    public void setHasHeat(boolean hasHeat) {
        this.hasHeat = hasHeat;
    }
}