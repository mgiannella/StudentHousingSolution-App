/*
written by: Sneh Shah
tested by: Sneh Shah
debugged by: Sneh Shah
*/
package com.softwareengineeringgroup8.studenthousingsolution.model;

public class SignLease {

    private Boolean signed;
    private String date;
    private String initials;
    private int propertyID;

    public Boolean getSigned() { return signed; }

    public void setSigned(Boolean signed) { this.signed = signed; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getInitials() { return initials; }

    public void setInitials(String initials) { this.initials = initials; }

    public int getPropertyID() { return propertyID; }

    public void setPropertyID(int propertyID) { this.propertyID = propertyID; }

    public SignLease(){

    }

    public SignLease(Boolean signed, String date, String initials, int propertyID){
        this.date = date;
        this.signed = signed;
        this.initials = initials;
        this.propertyID = propertyID;
    }


}
