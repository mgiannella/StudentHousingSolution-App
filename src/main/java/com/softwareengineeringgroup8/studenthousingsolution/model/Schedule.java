//written by: Rishi Shah
//tested by: Rishi Shah
//debugged by: Rishi Shah

package com.softwareengineeringgroup8.studenthousingsolution.model;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int scheduleid;

    @OneToOne
    @JoinColumn(name="landlordid" , referencedColumnName = "userid")
    private User landlord;

    @OneToOne
    @JoinColumn(name="propertyid")
    private Properties props;

    @OneToOne
    @JoinColumn(name="tenantid" , referencedColumnName = "userid")
    private User tenant;

    @Column(name="MeetingTime")
    private Timestamp meetingTimes;


    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public int getScheduleid(){ return scheduleid;}


    public User getLandlord() {
        return landlord;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }


    public Timestamp getMeetingTimes() {
        return meetingTimes;
    }

    public void setMeetingTimes(Timestamp meetingTimes) {
        this.meetingTimes = meetingTimes;
    }




    public Schedule() {
    }




    public Schedule(User landlord, Properties props, Timestamp meetingTimes, User tenant) {
        this.landlord = landlord;
        this.meetingTimes = meetingTimes;
        this.props=props;
        this.tenant=tenant;


    }
}
