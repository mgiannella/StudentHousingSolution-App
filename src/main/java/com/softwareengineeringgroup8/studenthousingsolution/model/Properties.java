package com.softwareengineeringgroup8.studenthousingsolution.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Properties")
public class Properties {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="propertyid")
    private int id;

    @OneToOne
    @JoinColumn(name="landlordid", referencedColumnName = "userid")
    private User landlord;

    @OneToOne
    @JoinColumn(name="tenantgroupid", referencedColumnName = "groupid")
    private TenantGroups group;


    @Column(name="propertytitle")
    private String title;

    @OneToOne
    @JoinColumn(name="amenityid")
    private Amenities amenities;

    @OneToOne
    @JoinColumn(name="descriptionid")
    private PropertyDescriptions description;

    @OneToOne
    @JoinColumn(name="locationid")
    private PropertyLocations location;

    @Column(name="pageviews")
    private int pageViews;

    public Properties(){

    }

    public Properties(User landlord, String title, Amenities amenities, PropertyDescriptions description, PropertyLocations location, int pageViews) {
        this.landlord = landlord;
        this.title = title;
        this.amenities = amenities;
        this.description = description;
        this.location = location;
        this.pageViews = pageViews;
    }

    public int getId() {return id;}

    public User getLandlord() {
        return landlord;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public TenantGroups getGroup() {
        return group;
    }

    public void setGroup(TenantGroups group) {
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Amenities getAmenities() {
        return amenities;
    }

    public void setAmenities(Amenities amenities) {
        this.amenities = amenities;
    }

    public PropertyDescriptions getDescription() {
        return description;
    }

    public void setDescription(PropertyDescriptions description) {
        this.description = description;
    }

    public PropertyLocations getLocation() {
        return location;
    }

    public void setLocation(PropertyLocations location) {
        this.location = location;
    }

    public int getPageViews() {
        return pageViews;
    }

    public void setPageViews(int pageViews) {
        this.pageViews = pageViews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Properties that = (Properties) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
