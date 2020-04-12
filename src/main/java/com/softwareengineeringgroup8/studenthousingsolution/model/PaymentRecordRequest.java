package com.softwareengineeringgroup8.studenthousingsolution.model;
import java.io.Serializable;

public class PaymentRecordRequest implements Serializable{
    private int paymentRecordId;

    public PaymentRecordRequest(){}


    public int getPaymentRecordId() {
        return paymentRecordId;
    }

    public void setPaymentRecordId(int paymentRecordId) {
        this.paymentRecordId = paymentRecordId;
    }


    public PaymentRecordRequest(int paymentRecordId){
        this.paymentRecordId = paymentRecordId;
    }
}
