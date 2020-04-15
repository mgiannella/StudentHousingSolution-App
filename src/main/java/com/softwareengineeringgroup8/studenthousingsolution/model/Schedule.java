package com.softwareengineeringgroup8.studenthousingsolution.model;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="scheduleid")
    private int id;

    @OneToOne
    @JoinColumn(name="landlordid" , referencedColumnName = "userid")
    private User landlord;

    @Column(name="availableDays")
    private List<Integer> days;

    @Column(name="availableTimes")
    private List<Time> times;









}
