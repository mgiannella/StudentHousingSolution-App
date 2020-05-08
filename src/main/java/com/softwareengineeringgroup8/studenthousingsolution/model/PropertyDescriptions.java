//written by: Michael Giannella
//tested by: Michael Giannella
//debugged by: Michael Giannella
package com.softwareengineeringgroup8.studenthousingsolution.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name="PropertyDescriptions")
public class PropertyDescriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(PropertyView.ViewProperty.class)
    @Column(name="descriptionid")
    private int id;

    @Column(name="desccontent")
    @JsonView(PropertyView.ViewProperty.class)
    private String descContent;

    public PropertyDescriptions(){}

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