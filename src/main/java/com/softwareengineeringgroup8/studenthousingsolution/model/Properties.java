//written by: Michael Giannella
//tested by: Michael Giannella
//debugged by: Michael Giannella
package com.softwareengineeringgroup8.studenthousingsolution.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Properties")
public class Properties {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="propertyid")
    @JsonView(PropertyView.Search.class)
    private int id;

    @OneToOne
    @JoinColumn(name="landlordid", referencedColumnName = "userid")
    @JsonView(PropertyView.Compare.class)
    private User landlord;

    @OneToOne
    @JoinColumn(name="tenantgroupid", referencedColumnName = "groupid")
    @JsonView(PropertyView.ExtendedInfo.class)
    private TenantGroups group;


    @Column(name="propertytitle")
    @JsonView(PropertyView.Search.class)
    private String title;

    @OneToOne
    @JoinColumn(name="amenityid")
    @JsonView(PropertyView.Search.class)
    private Amenities amenities;

    @OneToOne
    @JoinColumn(name="descriptionid")
    @JsonView(PropertyView.ViewProperty.class)
    private PropertyDescriptions description;

    @OneToOne
    @JoinColumn(name="locationid")
    @JsonView(PropertyView.Search.class)
    private PropertyLocations location;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy="property"
    )
    //@JoinColumn(name="propertyid")
    @JsonView(PropertyView.Search.class)
    private List<PropertyPhotos> photos;

    @Column(name="UPLOAD_TS", insertable=false)
    @JsonView(PropertyView.Search.class)
    private Timestamp uploadTS;

    @Column(name="unitnum")
    @JsonView(PropertyView.Search.class)
    private String unitNum;

    public Properties(){

    }

    public Properties(User landlord, String title, Amenities amenities, PropertyDescriptions description, PropertyLocations location, List<PropertyPhotos> photos, String unitNum) {
        this.landlord = landlord;
        this.title = title;
        this.amenities = amenities;
        this.description = description;
        this.location = location;
        this.photos = photos;
        this.unitNum = unitNum;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

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

    public List<PropertyPhotos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PropertyPhotos> photos) {
        this.photos = photos;
    }

    public Timestamp getUploadTS() {
        return uploadTS;
    }

    public void setUploadTS(Timestamp uploadTS) {
        this.uploadTS = uploadTS;
    }

    public String getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
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