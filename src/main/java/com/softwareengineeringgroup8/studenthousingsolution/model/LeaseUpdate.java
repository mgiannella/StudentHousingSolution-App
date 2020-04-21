package com.softwareengineeringgroup8.studenthousingsolution.model;

import java.sql.Date;

public class LeaseUpdate {

    private int id;
    private String lease;
    private String startDate;
    private String endDate;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLease() { return lease; }
    public void setLease(String lease) { this.lease = lease; }

    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getStartDate() { return startDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }
    public String getEndDate() { return endDate; }

    public LeaseUpdate(){

    }
    public LeaseUpdate(int id, String lease, String startDate, String endDate){
        this.id = id;
        this.lease = lease;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

