package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;

@Entity
@Table(name="PropertyDescriptions")
public class PropertyDescriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="descriptionid")
    private int id;

    @Column(name="desccontent")
    private String descContent;

    public PropertyDescriptions(String descContent){
        this.descContent = descContent;
    }

    public int getId(){return id;}

    public String getDescContent() {
        return descContent;
    }

    public void setDescContent(String descContent) {
        this.descContent = descContent;
    }
}
