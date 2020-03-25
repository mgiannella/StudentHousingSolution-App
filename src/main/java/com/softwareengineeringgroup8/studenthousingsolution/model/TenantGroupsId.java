package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TenantGroupsId implements Serializable {

    
    private int tenantgroupid;

    @ManyToOne
    @JoinColumn(name="tenantid")
    private User tenant;

    public TenantGroupsId() {

    }

    public TenantGroupsId(int tenantgroupid, User tenant){
        this.tenantgroupid = tenantgroupid;
        this.tenant = tenant;
    }

    public int getTenantGroupId() {
        return tenantgroupid;
    }

    public void setTenantGroupId(int tenantgroupid) {
        this.tenantgroupid = tenantgroupid;
    }

    public User getTenantId() {
        return tenant;
    }

    public void setTenantId(User tenant) {
        this.tenant = tenant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantGroupsId that = (TenantGroupsId) o;
        return tenantgroupid == that.tenantgroupid &&
                tenant.equals(that.tenant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenantgroupid, tenant);
    }
}
