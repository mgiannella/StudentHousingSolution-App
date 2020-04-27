package com.softwareengineeringgroup8.studenthousingsolution.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TenantGroupResponse implements Serializable {

    private TenantGroups group;

    private List<TenantGroupMembers> members;

    private Properties prop;

    public TenantGroupResponse(){

    }

    public TenantGroupResponse(TenantGroups group){
        this.members = new ArrayList<TenantGroupMembers>();
        this.group = group;
    }

    public List<TenantGroupMembers> getMembers(){
        return this.members;
    }

    public void addMember(TenantGroupMembers member){
        this.members.add(member);
    }

    public TenantGroups getGroup(){
        return this.group;
    }

    public void setGroup(TenantGroups group){
        this.group = group;
    }

    public Properties getProp(){return this.prop;}

    public void setProp(Properties prop){this.prop = prop;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantGroupResponse that = (TenantGroupResponse) o;
        return Objects.equals(group, that.group) &&
                Objects.equals(members, that.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, members);
    }
}
