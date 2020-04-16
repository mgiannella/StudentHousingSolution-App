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
@Table(name="LandlordAccounts")
public class LandlordAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @OneToOne
    @JoinColumn(name="LandlordID", referencedColumnName = "userid")
    private User landlord;


    @Column(name="StripeID")
    private String stripeID;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getLandlord() {
        return landlord;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public String getStripeID() {
        return stripeID;
    }

    public void setStripeID(String stripeID) {
        this.stripeID = stripeID;
    }

    public LandlordAccounts(){}

    public LandlordAccounts(User landlord, String stripeID){
        this.landlord = landlord;
        this.stripeID = stripeID;
    }
}
