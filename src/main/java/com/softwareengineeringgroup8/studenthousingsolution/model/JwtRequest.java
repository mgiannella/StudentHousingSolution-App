package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.io.Serializable;
@ApiModel(description="How a user's username and password are sent to the application")
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    @ApiModelProperty(notes="Username", example="testuser", required = true)
    @Size(min=1, max=25)
    private String username;
    @ApiModelProperty(notes="Password", example="Secretword1", required = true)
    @Size(min=1, max=30)
    private String password;

    public JwtRequest()
    {

    }

    public JwtRequest(String username, String password) {

        this.setUsername(username);

        this.setPassword(password);

    }

    public String getUsername() {

        return this.username;

    }

    public void setUsername(String username) {

        this.username = username;

    }

    public String getPassword() {

        return this.password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

}