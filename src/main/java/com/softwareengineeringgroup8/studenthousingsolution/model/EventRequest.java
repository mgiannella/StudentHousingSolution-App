package com.softwareengineeringgroup8.studenthousingsolution.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class EventRequest implements Serializable {


    @ApiModelProperty(notes="eventStart", example="",required=true)
    private String eventDate;


    @ApiModelProperty(notes="eventTime", example="",required=true)
    private String eventTime;

    @ApiModelProperty(notes="name",example="Housing meeting",required=true)
    private String name;


    @ApiModelProperty(notes="desc",example="Quick tour of house",required=true)
    private String desc;


    public String getEventDate() {
        return eventDate;
    }
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }


    public EventRequest(String eventDate, String name, String desc) {
        this.eventDate = eventDate;
        this.name = name;
        this.desc = desc;
    }
}
