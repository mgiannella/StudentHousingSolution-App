package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tenantgroups")
//@IdClass(TenantGroupsId.class)
public class TenantGroups implements Serializable {

    @EmbeddedId
    private TenantGroupsId tenantGroupsId;

    @Column(name="islead")
    private boolean isLead;

    @Column(name="signedlease")
    private boolean signedLease;

    public TenantGroups(){

    }

    public TenantGroups(TenantGroupsId tenantGroupsId, boolean isLead, boolean signedLease) {
        this.tenantGroupsId = tenantGroupsId;
        this.isLead = isLead;
        this.signedLease = signedLease;
    }

    public TenantGroupsId getTenantGroupId() {
        return tenantGroupsId;
    }

    public void setTenantGroupId(TenantGroupsId tenantgroupid) {
        this.tenantGroupsId = tenantgroupid;
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
