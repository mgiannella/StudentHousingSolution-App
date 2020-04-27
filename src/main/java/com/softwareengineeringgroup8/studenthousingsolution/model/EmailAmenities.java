package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="EmailAmenities")
public class EmailAmenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EDAmenityID")
    private int id;

    @Column(name="minbed")
    private int minBed;

    @Column(name="maxbed")
    private int maxBed;

    @Column(name="minbath")
    private float minBath;

    @Column(name="maxbath")
    private float maxBath;

    @Column(name="minprice")
    private BigDecimal minPrice;

    @Column(name="maxprice")
    private BigDecimal maxPrice;

    @Column(name="minsleeps")
    private int minSleeps;

    @Column(name="maxsleeps")
    private int maxSleeps;

    @Column(name="zip")
    private String zip;

    private EmailAmenities(){

    }

    public EmailAmenities(int minBed, int maxBed, float minBath, float maxBath, BigDecimal minPrice, BigDecimal maxPrice, int minSleeps, int maxSleeps, String zip) {
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

    public int getId(){
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAmenities that = (EmailAmenities) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
