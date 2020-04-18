package com.softwareengineeringgroup8.studenthousingsolution.model;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.context.PayloadApplicationEvent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.sql.Date;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;

@Entity
@Table(name="Reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ReviewID")
    private int id;

    @ManyToOne
    @JoinColumn(name= "PropertyID")
    private Properties prop;

    @ManyToOne
    @JoinColumn(name="tenantID", referencedColumnName = "userid")
    private User tenant;

    @Column(name="ReviewDescription")
    private String reviewDescription;

    @Column(name="CleanlinessRating")
    private float cleanlinessRating;

    @Column(name="SecurityRating")
    private float securityRating;

    @Column(name="CommunicationRating")
    private float communicationRating;

    @Column(name="LocationRating")
    private float locationRating;

    @Column(name="TotalRating")
    private float totalRating;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public float getCleanlinessRating() {
        return cleanlinessRating;
    }

    public void setCleanlinessRating(float cleanlinessRating) {
        this.cleanlinessRating = cleanlinessRating;
    }

    public float getSecurityRating() {
        return securityRating;
    }

    public void setSecurityRating(float securityRating) {
        this.securityRating = securityRating;
    }

    public float getCommunicationRating() {
        return communicationRating;
    }

    public void setCommunicationRating(float communicationRating) {
        this.communicationRating = communicationRating;
    }

    public float getLocationRating() {
        return locationRating;
    }

    public void setLocationRating(float locationRating) {
        this.locationRating = locationRating;
    }

    public float getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(float totalRating) {
        this.totalRating = totalRating;
    }

    public Reviews(){}

    public Reviews(Properties prop,User tenant,String reviewDescription, float cleanlinessRating, float securityRating, float communicationRating, float locationRating, float totalRating ){
        this.prop= prop;
        this.tenant = tenant;
        this.reviewDescription=reviewDescription;
        this.cleanlinessRating=cleanlinessRating;
        this.securityRating=securityRating;
        this.communicationRating=communicationRating;
        this.locationRating=locationRating;
        this.totalRating=totalRating;
    }
}
