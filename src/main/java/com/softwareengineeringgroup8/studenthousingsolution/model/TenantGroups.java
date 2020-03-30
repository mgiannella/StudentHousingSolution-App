package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="tenantgroups")
public class TenantGroups {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="groupid")
    private int id;

    @ManyToOne
    @JoinColumn(name="leadtenantid", referencedColumnName = "userid")
    private User leadTenant;

    @Column(name="groupname")
    private String name;

    public TenantGroups() {
    }

    public TenantGroups(int id, User leadTenant, String name) {
        this.id = id;
        this.leadTenant = leadTenant;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getLeadTenant() {
        return leadTenant;
    }

    public void setLeadTenant(User leadTenant) {
        this.leadTenant = leadTenant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantGroups that = (TenantGroups) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
