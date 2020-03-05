package com.softwareengineeringgroup8.studenthousingsolution.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L; // not sure about this value

    private final String jwtToken;

    public JwtResponse(String jwtToken) {

        this.jwtToken = jwtToken;

    }

    public String getToken() {

        return this.jwtToken;

    }

}