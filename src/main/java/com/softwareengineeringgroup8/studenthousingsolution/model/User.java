package com.softwareengineeringgroup8.studenthousingsolution.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

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

    @Column(name="countrycode")
    private String phoneCode;

    @Column(name="photoid")
    private int photoID;

    @Column(name="usertypeid")
    private int userTypeID;


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
        this.phoneCode = phoneNumber.substring(0,3);
    }

    public int getPhotoID(){
        return photoID;
    }

    public void setPhotoID(int photoID){
        // create new photo and then setID, this is placeholder rn
        this.photoID = photoID;
    }

    public User(String username, String password, String email, String firstName, String lastName, String phone, String phoneCode) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.phoneCode = phoneCode;
        this.userTypeID = 1;
    }

    public String getFullname() {
        return firstName + ' ' + lastName;
    }

    public void setFullname(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}