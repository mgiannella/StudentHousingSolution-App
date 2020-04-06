package com.softwareengineeringgroup8.studenthousingsolution.model;
import com.stripe.model.Review;

import java.io.Serializable;

public class ReviewRequest implements Serializable{
    private String reviewDescription;
    private int cleanlinessRating;
    private int securityRating;
    private int communicatingRating;
    private int locationRating;
    private int totalRating;

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

    public int getCommunicatingRating() { return communicatingRating; }

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

    public ReviewRequest(String reviewDescription, int cleanlinessRating,int securityRating,int communicatingRating,int locationRating,int totalRating){
        this.reviewDescription=reviewDescription;
        this.cleanlinessRating=cleanlinessRating;
        this.securityRating=securityRating;
        this.communicatingRating=communicatingRating;
        this.locationRating=locationRating;
        this.totalRating=totalRating;

    }
}
