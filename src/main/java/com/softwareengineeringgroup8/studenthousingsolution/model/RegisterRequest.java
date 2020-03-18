package com.softwareengineeringgroup8.studenthousingsolution.model;

import java.io.Serializable;

public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String phoneCode;

    private String firstName;

    private String lastName;

    private String userType;

    public RegisterRequest() {

    }

    public RegisterRequest(String username, String password, String email, String phone, String phoneCode, String firstName, String lastName, String userType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.phoneCode = phoneCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }



}