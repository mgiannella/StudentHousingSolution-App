package com.softwareengineeringgroup8.studenthousingsolution.model;

import java.io.Serializable;
import java.util.Date;

public class ChargeRequest implements Serializable {

    private String email;
    private String card_num;
    private String monthNum;
    private String yearNum;
    private String ccv;


    public ChargeRequest() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(String monthNum) {
        this.monthNum = monthNum;
    }

    public String getYearNum() {
        return yearNum;
    }

    public void setYearNum(String yearNum) {
        this.yearNum = yearNum;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    private int paymentAmount;

    private Date paymentDate;

   private String description;

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChargeRequest(String email, String card_num, String monthNum, String yearNum, String ccv, Date paymentDate, String description) {
        this.email = email;
        this.card_num = card_num;
        this.monthNum = monthNum;
        this.yearNum = yearNum;
        this.ccv = ccv;
        this.paymentDate = paymentDate;
        this.description = description;
    }
}


