//written by: Srinivasniranjan Nukala
//tested by: Srinivasniranjan Nukala
//debugged by: Srinivasniranjan Nukala
package com.softwareengineeringgroup8.studenthousingsolution.model;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.context.PayloadApplicationEvent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.sql.Date;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;


@Entity
@Table(name="PaymentRecord")
public class PaymentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "PaymentRecordID")
    private int id;


    @Column(name="PaymentDate")
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name= "PropertyID")
    private Properties prop;

    @ManyToOne
    @JoinColumn(name="tenantID", referencedColumnName = "userid")
    private User tenant;

    @ManyToOne
    @JoinColumn(name ="PaymentTypeID")
    private PaymentType paymentTypeId;


    @Column(name="PaymentAmount")
    private BigDecimal paymentAmount;

    @Column(name="PaymentDueDate")
    private Date paymentDueDate;

    @Column(name="ChargeID")
    private String chargeID;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public PaymentType getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(PaymentType paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getPaymentDueDate() { return paymentDueDate; }

    public void setPaymentDueDate(Date paymentDueDate) { this.paymentDueDate = paymentDueDate; }

    public String getChargeID() {
        return chargeID;
    }

    public void setChargeID(String chargeID) {
        this.chargeID = chargeID;
    }

    public PaymentRecord() { }


    public PaymentRecord(Date paymentDate, Properties prop, User tenant, PaymentType paymentTypeId, BigDecimal paymentAmount, Date paymentDueDate, String chargeID) {
        this.paymentDate = paymentDate;
        this.prop = prop;
        this.tenant = tenant;
        this.paymentTypeId = paymentTypeId;
        this.paymentAmount = paymentAmount;
        this.paymentDueDate = paymentDueDate;
        this.chargeID=chargeID;
    }
}
