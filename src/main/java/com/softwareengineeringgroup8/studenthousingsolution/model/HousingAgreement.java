package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="HousingAgreement")
public class HousingAgreement {

    @Id
    @Column(name="AgreementID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agreementID;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PropertyID")
    private Properties property;

    @Column(name="FileLocation")
    private String location;

    @Column(name="LeaseStart")
    private Date startDate;

    @Column(name="LeaseEnd")
    private Date endDate;

    public HousingAgreement(){
    }

    public HousingAgreement(Properties property, String location, Date startDate, Date endDate){
        this.property = property;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getAgreementID() { return agreementID; }

    public void setAgreementID(int agreementID) { this.agreementID = agreementID; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Properties getProperty() { return property; }

    public void setProperty(Properties property) { this.property = property; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }


}
