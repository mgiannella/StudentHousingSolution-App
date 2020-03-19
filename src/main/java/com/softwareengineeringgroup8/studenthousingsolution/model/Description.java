package com.softwareengineeringgroup8.studenthousingsolution.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class Description {
    @Entity
    @Table(name="PropertyDescriptions")

    @Column(name="descriptionid")
    private int descriptionID;

    @Column(name="desccontent")
    private int descContent;

}
