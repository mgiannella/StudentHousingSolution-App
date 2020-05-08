//written by: Michael Giannella
//tested by: Michael Giannella
//debugged by: Michael Giannella
package com.softwareengineeringgroup8.studenthousingsolution.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="Notifications")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="NotificationId")
    private int id;

    @OneToOne
    @JoinColumn(name="userid", referencedColumnName = "userid")
    @JsonIgnore
    private User user;

    @Column(name="notification")
    private String desc;

    @ManyToOne
    @JoinColumn(name="notificationtypeid")
    private NotificationType type;

    @Column(name="alertdate")
    private Timestamp alertDT;

    public Notifications() {
    }

    public Notifications(User user, String desc, NotificationType type, Timestamp alertDT) {
        this.user = user;
        this.desc = desc;
        this.type = type;
        this.alertDT = alertDT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Timestamp getAlertDT() {
        return alertDT;
    }

    public void setAlertDT(Timestamp alertDT) {
        this.alertDT = alertDT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notifications that = (Notifications) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
