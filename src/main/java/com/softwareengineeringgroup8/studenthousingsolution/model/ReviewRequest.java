package com.softwareengineeringgroup8.studenthousingsolution.model;
import com.stripe.model.Review;

import java.io.Serializable;

public class ReviewRequest implements Serializable{
    private String reviewDescription;
    private float cleanlinessRating;
    private float securityRating;
    private float communicationRating;
    private float locationRating;
    private float totalRating;

    public ReviewRequest(){
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

    public float getCommunicationRating() { return communicationRating; }

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


    private int prop;

    public int getProp() {
        return prop;
    }

    public void setProp(int prop) {
        this.prop = prop;
    }

    public ReviewRequest(String reviewDescription, float cleanlinessRating,float securityRating,float communicationRating,float locationRating,float totalRating, int prop){
        this.reviewDescription=reviewDescription;
        this.cleanlinessRating=cleanlinessRating;
        this.securityRating=securityRating;
        this.communicationRating=communicationRating;
        this.locationRating=locationRating;
        this.totalRating=totalRating;
        this.prop = prop;

    }
}
