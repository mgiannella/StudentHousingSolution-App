package com.softwareengineeringgroup8.studenthousingsolution.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="PropertyDescriptions")
public class Description {

    @Column(name="descriptionid")
    private int descriptionID;

    @Column(name="desccontent")
    private int descContent;

    public Description(){
    }
    public int getDescriptionID() {
        return descriptionID;
    }

    public void setDescriptionID(int descriptionID) {
        this.descriptionID = descriptionID;
    }

    public int getDescContent() {
        return descContent;
    }

    public void setDescContent(int descContent) {
        this.descContent = descContent;
    }

    public Description(int descContent) {
        this.descContent = descContent;
    }
}
