package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class EmailDigestRequest implements Serializable {

    @ApiModelProperty(notes="Min Beds", example="2", required = true)
    private int minBed;

    @ApiModelProperty(notes="Max Beds", example="4", required = true)
    private int maxBed;

    @ApiModelProperty(notes="Min Bath", example="1.0", required = true)
    private float minBath;

    @ApiModelProperty(notes="Max Bath", example="3.5", required = true)
    private float maxBath;

    @ApiModelProperty(notes="Min Price", example="0", required = true)
    private BigDecimal minPrice;

    @ApiModelProperty(notes="Max Price", example="3500.50", required = true)
    private BigDecimal maxPrice;

    @ApiModelProperty(notes="Min Sleeps", example="1", required = true)
    private int minSleeps;

    @ApiModelProperty(notes="Max Sleeps", example="10", required = true)
    private int maxSleeps;

    @Size(min=0, max=10)
    @ApiModelProperty(notes="Zip Code", example="08901", required = true)
    private String zip;

    private EmailDigestRequest(){

    }

    public EmailDigestRequest(int minBed, int maxBed, float minBath, float maxBath, BigDecimal minPrice, BigDecimal maxPrice, int minSleeps, int maxSleeps, String zip) {
        this.minBed = minBed;
        this.maxBed = maxBed;
        this.minBath = minBath;
        this.maxBath = maxBath;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minSleeps = minSleeps;
        this.maxSleeps = maxSleeps;
        this.zip = zip;
    }

    public int getMinBed() {
        return minBed;
    }

    public void setMinBed(int minBed) {
        this.minBed = minBed;
    }

    public int getMaxBed() {
        return maxBed;
    }

    public void setMaxBed(int maxBed) {
        this.maxBed = maxBed;
    }

    public float getMinBath() {
        return minBath;
    }

    public void setMinBath(float minBath) {
        this.minBath = minBath;
    }

    public float getMaxBath() {
        return maxBath;
    }

    public void setMaxBath(float maxBath) {
        this.maxBath = maxBath;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinSleeps() {
        return minSleeps;
    }

    public void setMinSleeps(int minSleeps) {
        this.minSleeps = minSleeps;
    }

    public int getMaxSleeps() {
        return maxSleeps;
    }

    public void setMaxSleeps(int maxSleeps) {
        this.maxSleeps = maxSleeps;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
