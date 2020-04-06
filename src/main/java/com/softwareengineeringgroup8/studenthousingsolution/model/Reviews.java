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
    private int cleanlinessRating;

    @Column(name="Security Rating")
    private int securityRating;

    @Column(name="CommunicatingRating")
    private int communicatingRating;

    @Column(name="LocationRating")
    private int locationRating;

    @Column(name="TotalRating")
    private int totalRating;


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

    public int getCleanlinessRating() {
        return cleanlinessRating;
    }

    public void setCleanlinessRating(int cleanlinessRating) {
        this.cleanlinessRating = cleanlinessRating;
    }

    public int getSecurityRating() {
        return securityRating;
    }

    public void setSecurityRating(int securityRating) {
        this.securityRating = securityRating;
    }

    public int getCommunicatingRating() {
        return communicatingRating;
    }

    public void setCommunicatingRating(int communicatingRating) {
        this.communicatingRating = communicatingRating;
    }

    public int getLocationRating() {
        return locationRating;
    }

    public void setLocationRating(int locationRating) {
        this.locationRating = locationRating;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public Reviews(Properties prop,User tenant,String reviewDescription, int cleanlinessRating, int securityRating, int communicatingRating, int locationRating, int totalRating ){
        this.prop= prop;
        this.tenant = tenant;
        this.reviewDescription=reviewDescription;
        this.cleanlinessRating=cleanlinessRating;
        this.securityRating=securityRating;
        this.communicatingRating=communicatingRating;
        this.locationRating=locationRating;
        this.totalRating=totalRating;
    }
}
