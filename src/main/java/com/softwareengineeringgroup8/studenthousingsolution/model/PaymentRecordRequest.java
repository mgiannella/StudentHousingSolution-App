package com.softwareengineeringgroup8.studenthousingsolution.model;
import java.io.Serializable;

public class PaymentRecordRequest implements Serializable{
    private String paymentRecordId;

    public PaymentRecordRequest(){}


    public String getPaymentRecordId() {
        return paymentRecordId;
    }

    public void setPaymentRecordId(String paymentRecordId) {
        this.paymentRecordId = paymentRecordId;
    }


    public PaymentRecordRequest(String paymentRecordId){
        this.paymentRecordId = paymentRecordId;
    }
}
