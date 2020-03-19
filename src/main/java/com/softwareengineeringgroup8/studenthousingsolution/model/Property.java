package com.softwareengineeringgroup8.studenthousingsolution.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="Properties")

public class Property {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="propertyid")
    private int propertyID;

    @Column(name="landlordid")
    private int landlordID;

    @Column(name="tenantgroupid")
    private int tenantgroupID;

    @Column(name="propertytitle")
    private String propertyTitle;

    @OneToOne
    @JoinColumn(name="amenityid")
    private int amenityID;

    @OneToOne
    @JoinColumn(name="descriptionid")
    private int descriptionID;

    @OneToOne
    @JoinColumn(name="locationid")
    private int locationID;

    @Column(name="pageviews")
    private int numPageviews;

    @Column(name="leadtenantid")
    private String leadtenantID;

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }

    public void setAmenityID(int amenityID) {
        this.amenityID = amenityID;
    }

    public void setDescriptionID(int descriptionID) {
        this.descriptionID = descriptionID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public int getLandlordID() {
        return landlordID;
    }

    public int getTenantgroupID() {
        return tenantgroupID;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public int getAmenityID() {
        return amenityID;
    }

    public int getDescriptionID() {
        return descriptionID;
    }

    public int getLocationID() {
        return locationID;
    }

    public int getNumPageviews() {
        return numPageviews;
    }

    public String getLeadtenantID() {
        return leadtenantID;
    }
}
