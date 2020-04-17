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


    @Column(name="MeetingTime")
    private Timestamp meetingTimes;




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




    public Schedule(User landlord, Timestamp meetingTimes) {
        this.landlord = landlord;
        this.meetingTimes = meetingTimes;

    }
}
