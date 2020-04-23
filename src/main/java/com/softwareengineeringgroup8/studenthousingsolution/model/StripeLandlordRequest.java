package com.softwareengineeringgroup8.studenthousingsolution.model;

import java.io.Serializable;
import java.util.Date;

public class StripeLandlordRequest implements Serializable {

    private String email;
    private String account_holder_name;
    private String routing_number;
    private String account_number;

    public StripeLandlordRequest() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount_holder_name() {
        return account_holder_name;
    }

    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }

    public String getRouting_number() {
        return routing_number;
    }

    public void setRouting_number(String routing_number) {
        this.routing_number = routing_number;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public StripeLandlordRequest(String email,String account_holder_name, String routing_number, String account_number){
        this.email=email;
        this.account_holder_name= account_holder_name;
        this.routing_number=routing_number;
        this.account_number=account_number;


    }

}
