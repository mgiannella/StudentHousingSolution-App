//written by: Srinivasniranjan Nukala
//tested by: Srinivasniranjan Nukala
//debugged by: Srinivasniranjan Nukala
package com.softwareengineeringgroup8.studenthousingsolution.model;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.sql.Date;
import java.util.Properties;


@Entity
@Table(name="PaymentType")
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PaymentTypeID")
    private int paymentId;

    @Column(name= "PaymentTypeDesc")
    private String pTypeDesc;


    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getpTypeDesc() {
        return pTypeDesc;
    }

    public void setpTypeDesc(String pTypeDesc) {
        this.pTypeDesc = pTypeDesc;
    }

    public PaymentType() {

    }
}
