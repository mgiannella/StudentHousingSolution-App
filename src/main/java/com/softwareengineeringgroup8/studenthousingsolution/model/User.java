package com.softwareengineeringgroup8.studenthousingsolution.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userid")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="userpass")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Column(name="phone")
    private String phone;

    @Column(name="countrycode", length=2)
    private String phoneCode;

    @ManyToOne
    @JoinColumn(name="usertypeid")
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

    public String getPhone(){
        return '+'+phoneCode+phone;
    }

    public void setPhone(String phoneNumber){
        this.phone = phoneNumber.substring(3);
        this.phoneCode = phoneNumber.substring(0,2);
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