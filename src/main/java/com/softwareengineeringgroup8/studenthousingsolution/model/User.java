package com.softwareengineeringgroup8.studenthousingsolution.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userid")
    @JsonView(PropertyView.Compare.class)
    private int id;

    @Column(name="username")
    @JsonView(PropertyView.Compare.class)
    private String username;

    @Column(name="userpass")
    @JsonIgnore
    private String password;

    @Column(name="email")
    @JsonView(PropertyView.ViewProperty.class)
    private String email;

    @Column(name="firstname")
    @JsonView(PropertyView.Compare.class)
    private String firstName;

    @Column(name="lastname")
    @JsonView(PropertyView.Compare.class)
    private String lastName;

    @Column(name="phone")
    @JsonView(PropertyView.ViewProperty.class)
    private String phone;

    @Column(name="countrycode", length=2)
    @JsonView(PropertyView.ViewProperty.class)
    private String phoneCode;

    @ManyToOne
    @JoinColumn(name="usertypeid")
    @JsonView(PropertyView.ExtendedInfo.class)
    private UserType type;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public UserType getType(){ return this.type; }

    public void setType(UserType type) {this.type = type;}

    public User(String username, String password, String email, String firstName, String lastName, String phone, String phoneCode, UserType type) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.phoneCode = phoneCode;
        this.type = type;
    }

    public String getFullname() {
        return firstName + ' ' + lastName;
    }

    public void setFullname(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}