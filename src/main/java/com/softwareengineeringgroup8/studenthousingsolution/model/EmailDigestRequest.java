//written by: Michael Giannella
//tested by: Michael Giannella
//debugged by: Michael Giannella
package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class EmailDigestRequest implements Serializable {

    private SearchFilterBedroom bedrooms;

    private SearchFilterBathroom bathrooms;

    private SearchFilterPrice price;

    private SearchFilterSleeps sleeps;

    @Size(min=0, max=10)
    @ApiModelProperty(notes="Zip Code", example="08901", required = true)
    private String zip;

    private EmailDigestRequest(){

    }

    public SearchFilterBedroom getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(SearchFilterBedroom bedrooms) {
        this.bedrooms = bedrooms;
    }

    public SearchFilterBathroom getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(SearchFilterBathroom bathrooms) {
        this.bathrooms = bathrooms;
    }

    public SearchFilterPrice getPrice() {
        return price;
    }

    public void setPrice(SearchFilterPrice price) {
        this.price = price;
    }

    public SearchFilterSleeps getSleeps() {
        return sleeps;
    }

    public void setSleeps(SearchFilterSleeps sleeps) {
        this.sleeps = sleeps;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
