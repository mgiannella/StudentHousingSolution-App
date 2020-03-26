package com.softwareengineeringgroup8.studenthousingsolution.model;


import javax.persistence.*;
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
    @JoinColumns({
            @JoinColumn(name="tenantgroupid", referencedColumnName = "tenantgroupid"),
            @JoinColumn(name="leadtenantid", referencedColumnName = "tenantid")
    })
    private TenantGroups tenantGroup;


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


    public Properties(User landlord, TenantGroups tenantGroup, String title, Amenities amenities, PropertyDescriptions description, PropertyLocations location, int pageViews) {
        this.landlord = landlord;
        this.tenantGroup = tenantGroup;
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

    public TenantGroups getTenantGroup() {
        return tenantGroup;
    }

    public void setTenantGroup(TenantGroups tenantGroup) {
        this.tenantGroup = tenantGroup;
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
}
