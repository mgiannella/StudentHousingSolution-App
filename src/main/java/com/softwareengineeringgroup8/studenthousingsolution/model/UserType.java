//written by: Michael Giannella
//tested by: Michael Giannella
//debugged by: Michael Giannella
package com.softwareengineeringgroup8.studenthousingsolution.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="UserType")
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usertypeid")
    private int id;

    @Column(name="usertypedesc")
    private String userTypeDesc;

    public int getId() {
        return id;
    }

    public String getUserTypeDesc() {
        return userTypeDesc;
    }

    public void setUserTypeDesc(String userTypeDesc) {
        this.userTypeDesc = userTypeDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserType userType = (UserType) o;
        return id == userType.id &&
                Objects.equals(userTypeDesc, userType.userTypeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userTypeDesc);
    }
}
