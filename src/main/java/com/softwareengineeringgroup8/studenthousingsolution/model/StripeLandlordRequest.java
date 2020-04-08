package com.softwareengineeringgroup8.studenthousingsolution.model;

import java.io.Serializable;
import java.util.Date;

public class StripeLandlordRequest implements Serializable {

    private String email;

    public StripeLandlordRequest() {

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StripeLandlordRequest(String email){
        this.email=email;

    }

}
