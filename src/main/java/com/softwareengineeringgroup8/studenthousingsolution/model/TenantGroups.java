package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tenantgroups")
@IdClass(TenantGroupsId.class)
public class TenantGroups implements Serializable {

    @Id
    @Column(name="tenantgroupid")
    private int tenantgroupid;

    @Id
    @OneToOne(mappedBy = "userid")
    private User tenant;

    @Column(name="islead")
    private boolean isLead;

    @Column(name="signedlease")
    private boolean signedLease;

    public TenantGroups(int tenantgroupid, User tenant, boolean isLead, boolean signedLease) {
        this.tenantgroupid = tenantgroupid;
        this.tenant = tenant;
        this.isLead = isLead;
        this.signedLease = signedLease;
    }

    public TenantGroups(){

    }

    public int getTenantGroupId() {
        return tenantgroupid;
    }

    public void setTenantGroupId(int tenantgroupid) {
        this.tenantgroupid = tenantgroupid;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public boolean isLead() {
        return isLead;
    }

    public void setLead(boolean lead) {
        isLead = lead;
    }

    public boolean isSignedLease() {
        return signedLease;
    }

    public void setSignedLease(boolean signedLease) {
        this.signedLease = signedLease;
    }
}
