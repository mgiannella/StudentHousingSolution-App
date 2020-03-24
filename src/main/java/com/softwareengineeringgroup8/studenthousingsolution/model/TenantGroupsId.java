package com.softwareengineeringgroup8.studenthousingsolution.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TenantGroupsId implements Serializable {

    @Column(name="tenantgroupid")
    private int groupId;

    @OneToOne
    @JoinColumn(name="tenantid")
    private User tenant;

    public TenantGroupsId() {

    }

    public TenantGroupsId(int groupId, User tenant){
        this.groupId = groupId;
        this.tenant = tenant;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
        return groupId == that.groupId &&
                tenant.equals(that.tenant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, tenant);
    }
}
