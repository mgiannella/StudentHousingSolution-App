package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tenantgroupmembers")
public class TenantGroupMembers {

    @Id
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="groupid")
    private TenantGroups group;

    @ManyToOne
    @JoinColumn(name="tenantid", referencedColumnName ="userid")
    private User tenant;

    @Column(name="signedlease")
    private boolean signedLease;

    @Column(name="invited")
    private boolean invited;

    @Column(name="subscribed")
    private boolean subscribed;

    public TenantGroupMembers() {
    }

    public TenantGroupMembers(TenantGroups group, User tenant, boolean signedLease, boolean invited, boolean subscribed){
        this.group = group;
        this.tenant = tenant;
        this.signedLease = signedLease;
        this.invited = invited;
        this.subscribed = subscribed;
    }

    public TenantGroups getGroup() {
        return group;
    }

    public void setGroup(TenantGroups group) {
        this.group = group;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public boolean isSignedLease() {
        return signedLease;
    }

    public void setSignedLease(boolean signedLease) {
        this.signedLease = signedLease;
    }

    public boolean isInvited() {
        return invited;
    }

    public void setInvited(boolean invited) {
        this.invited = invited;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantGroupMembers that = (TenantGroupMembers) o;
        return group.equals(that.group) &&
                tenant.equals(that.tenant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, tenant);
    }
}
