package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.io.Serializable;

@ApiModel(description = "Contains the information to register a user")
public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    @Size(min=6, max=25)
    @ApiModelProperty(notes="username", example="testuser", required = true)
    private String username;
    @Size(min=8, max=30)
    @ApiModelProperty(notes="Password, must contain at least 1 capital letter and 1 number", example="Secretword1", required = true)
    private String password;
    @Size(max=100)
    @ApiModelProperty(notes="Email address", example="testuser@gmail.com", required = true)
    private String email;
    @Size(min=10, max=10)
    @ApiModelProperty(notes="Phone Number, with no dashes or spaces", example="5555555555", required = true)
    private String phone;
    @Size(min=2, max=2)
    @ApiModelProperty(notes="Two digit country code", example="01", required = true)
    private String phoneCode;
    @Size(min=1, max=50)
    @ApiModelProperty(notes="First Name", example="Test", required = true)
    private String firstName;
    @Size(min=1, max=50)
    @ApiModelProperty(notes="Last Name", example="User", required = true)
    private String lastName;
    @Size(min=6, max=8)
    @ApiModelProperty(notes="Type of User", example="Tenant", required = true)
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