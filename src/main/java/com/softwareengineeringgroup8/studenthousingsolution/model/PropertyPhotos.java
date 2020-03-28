package com.softwareengineeringgroup8.studenthousingsolution.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="PropertyPhotos")
public class PropertyPhotos {

    @Id
    @Column(name="photoid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(PropertyView.Search.class)
    private int id;

    @Column(name="seqid")
    @JsonView(PropertyView.Search.class)
    private int seq;

    @Column(name="photolocation")
    @JsonView(PropertyView.Search.class)
    private String location;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="propertyid")
    @JsonIgnore
    private Properties property;

    public PropertyPhotos(){

    }

    public PropertyPhotos(int seq, String location, Properties property){
        this.property = property;
        this.seq = seq;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public Properties getProperty() {
        return property;
    }

    public void setProperty(Properties property) {
        this.property = property;
    }*/

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyPhotos that = (PropertyPhotos) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
