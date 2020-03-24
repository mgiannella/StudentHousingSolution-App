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
    private User tenantId;

    public TenantGroupsId() {

    }

    public TenantGroupsId(int groupId, User tenantId){
        this.groupId = groupId;
        this.tenantId = tenantId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public User getTenantId() {
        return tenantId;
    }

    public void setTenantId(User tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantGroupsId that = (TenantGroupsId) o;
        return groupId == that.groupId &&
                tenantId.equals(that.tenantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, tenantId);
    }
}
