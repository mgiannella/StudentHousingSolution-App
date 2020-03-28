package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class SearchFilterPrice implements Serializable {
    @ApiModelProperty(notes="Minimum price", example="1000", required=false)
    private BigDecimal min;
    @ApiModelProperty(notes="Maximum price", example="3000", required=false)
    private BigDecimal max;

    public SearchFilterPrice() {

    }

    public SearchFilterPrice(BigDecimal min, BigDecimal max) {
        this.min = min;
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }
}
