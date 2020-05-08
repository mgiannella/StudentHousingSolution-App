//written by: Michael Giannella
//tested by: Michael Giannella
//debugged by: Michael Giannella
package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="emailsubscribers")
public class EmailSubscribers {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="EDAmenityID")
    private EmailAmenities emailAmenities;

    @OneToOne
    @JoinColumn(name="userid")
    private User user;

    public EmailSubscribers(){

    }

    public EmailSubscribers(EmailAmenities emailAmenities, User user) {
        this.emailAmenities = emailAmenities;
        this.user = user;
    }

    public EmailAmenities getEmailAmenities() {
        return emailAmenities;
    }

    public void setEmailAmenities(EmailAmenities emailAmenities) {
        this.emailAmenities = emailAmenities;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailSubscribers that = (EmailSubscribers) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
