//written by: Michael Giannella
//tested by: Michael Giannella
//debugged by: Michael Giannella
package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SearchFilterBathroom implements Serializable {
    @ApiModelProperty(notes="Minimum amount of bathrooms", example="1", required=true)
    private float min;
    @ApiModelProperty(notes="Maximum amount of bathrooms", example="4.5", required=true)
    private float max;

    public SearchFilterBathroom() {

    }

    public SearchFilterBathroom(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }
}
