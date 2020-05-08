//written by: Michael Giannella
//tested by: Michael Giannella
//debugged by: Michael Giannella
package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SearchFilterBedroom implements Serializable {
    @ApiModelProperty(notes="Minimum amount of bedrooms", example="3", required=true)
    private int min;
    @ApiModelProperty(notes="Maximum amount of bedrooms", example="4", required=true)
    private int max;

    public SearchFilterBedroom() {

    }

    public SearchFilterBedroom(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
