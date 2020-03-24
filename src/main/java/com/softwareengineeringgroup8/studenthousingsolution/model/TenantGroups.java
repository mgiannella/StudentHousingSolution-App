package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;

@Entity
@Table(name="propertylocations")
public class TenantGroups {

    @EmbeddedId
    private TenantGroupsId tenantGroupsId;

    @Column(name="iskead")
    private boolean isLead;

    @Column(name="SignedLease")
    private boolean signedLease;

    public TenantGroups() {

    }

    public TenantGroups(TenantGroupsId tenantGroupsId, boolean isLead, boolean signedLease){
        this.tenantGroupsId = tenantGroupsId;
        this.isLead = isLead;
        this.signedLease = signedLease;
    }

    public TenantGroupsId getTenantGroupsId() {
        return tenantGroupsId;
    }

    public void setTenantGroupsId(TenantGroupsId tenantGroupsId) {
        this.tenantGroupsId = tenantGroupsId;
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
