package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SearchFilterRenovationDate implements Serializable {
    @ApiModelProperty(notes="Earliest Renovation String", example="1901-05-01", required=false)
    private String min;
    @ApiModelProperty(notes="Maximum amount of parking spaces", example="2020-03-20", required=false)
    private String max;

    public SearchFilterRenovationDate() {

    }

    public SearchFilterRenovationDate(String min, String max) {
        this.min = min;
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}
