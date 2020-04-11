package com.softwareengineeringgroup8.studenthousingsolution.model;

import java.io.Serializable;
import java.util.Date;

public class PendingPaymentRequest implements Serializable{

    private int propID;
    private int tenantID;
    private String amount;
    private String ptype;
    private String dueDate;

    public int getPropID() {
        return propID;
    }

    public void setPropID(int propID) {
        this.propID = propID;
    }

    public int getTenantID() {
        return tenantID;
    }

    public void setTenantID(int tenantID) {
        this.tenantID = tenantID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public PendingPaymentRequest(){}

    public PendingPaymentRequest(int propID, int tenantID, String amount, String ptype, String dueDate){
        this.propID = propID;
        this.tenantID = tenantID;
        this.amount = amount;
        this.ptype = ptype;
        this.dueDate = dueDate;
    }

}
