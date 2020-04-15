package com.softwareengineeringgroup8.studenthousingsolution.service;

import java.sql.Date;

import java.sql.Timestamp;
import java.util.List;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;

import java.util.Calendar;
import java.util.List;
import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MRService {

    @Autowired
    private MaintenanceRequestRepository mrRepository;
    @Autowired
    private MaintenanceStatusRepository statusRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertiesRepository propertiesRepository;
    @Autowired
    private TenantGroupMembersRepository tgmRepository;

    public List<MaintenanceRequest> getRequestByProperty(Properties prop){ return mrRepository.findByProperty(prop); }

    public void createMaintenanceRequest(User tenant, MaintenanceRequestData data){
        MaintenanceStatus status = new MaintenanceStatus("pending", 1);
        long millis = System.currentTimeMillis();
        Timestamp date = new java.sql.Timestamp(millis);
        String notes = data.getNotes();
        int id = data.getId();
        Properties prop = propertiesRepository.findById(id);
        List<TenantGroups> tenantGroupsList = tgmRepository.findTenantGroupByMember(tenant);
        for(int i = 0; i < tenantGroupsList.size(); i++){
            if(tenantGroupsList.get(i) == prop.getGroup()){
                mrRepository.save(new MaintenanceRequest(status, prop, date, notes, tenant));
                return;
            }
        }
        throw new ValidationException("Property is not owned by Tenant");

    }


    public void updateMaintenanceRequest(MaintenanceRequest request, MaintenanceUpdateData data) {
        String statusDesc = data.getStatusDesc();
        MaintenanceStatus status;
        if (statusDesc.equals("in progress")) {
            status = new MaintenanceStatus("in progress", 2);
        } else if (statusDesc.equals("resolved")) {
            status = new MaintenanceStatus("resolved", 3);
        } else {
            status = new MaintenanceStatus("denied", 4);
        }
        request.setStatus(status);
        mrRepository.save(request);

    }

    public MaintenanceRequest getRequestById(int id) throws ValidationException {
        try{
            return mrRepository.findByRequestId(id);
        }catch(Exception e){
            throw new ValidationException("Couldn't find Request By Id");
        }
    }


}
