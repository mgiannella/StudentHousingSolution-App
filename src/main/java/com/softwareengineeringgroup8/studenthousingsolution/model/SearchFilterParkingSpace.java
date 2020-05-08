//written by: Michael Giannella
//tested by: Michael Giannella
//debugged by: Michael Giannella
package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SearchFilterParkingSpace implements Serializable {
    @ApiModelProperty(notes="Minimum amount of parking spaces", example="3", required=true)
    private int min;
    @ApiModelProperty(notes="Maximum amount of parking spaces", example="4", required=true)
    private int max;

    public SearchFilterParkingSpace() {

    }

    public SearchFilterParkingSpace(int min, int max) {
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
