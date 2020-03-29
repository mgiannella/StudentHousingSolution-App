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
    @JoinColumn(name = "PaymentTypeID")
    private PaymentType paymentId;


    @Column(name="PaymentAmount")
    private BigDecimal paymentAmount;

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

    public PaymentType getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(PaymentType paymentId) {
        this.paymentId = paymentId;
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

    public PaymentRecord() {

    }

    public PaymentRecord(Date paymentDate, Properties prop, User tenant, PaymentType paymentId, BigDecimal paymentAmount) {
        this.paymentDate = paymentDate;
        this.prop = prop;
        this.tenant = tenant;
        this.paymentId = paymentId;
        this.paymentAmount = paymentAmount;
    }
}
