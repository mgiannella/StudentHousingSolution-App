/*
written by: Sneh Shah
tested by: Sneh Shah
debugged by: Sneh Shah
*/
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
    @Autowired
    private NotificationService notifService;

    public List<MaintenanceRequest> getRequestByProperty(Properties prop){ return mrRepository.findByProperty(prop); }

    public void createMaintenanceRequest(User tenant, MaintenanceRequestData data) {
        MaintenanceStatus status = new MaintenanceStatus("pending", 1);
        long millis = System.currentTimeMillis();
        Timestamp date = new java.sql.Timestamp(millis);
        String dateString = date.toString();
        String notes = data.getNotes();
        int id = data.getId();
        Properties prop = propertiesRepository.findById(id);
        User landlord = prop.getLandlord();
        PropertyLocations location = prop.getLocation();
        String address = location.getAddress();
        String msg = "You have a new maintenance request for " + address + ".";
        List<TenantGroups> tenantGroupsList = tgmRepository.findTenantGroupByMember(tenant);
        for (int i = 0; i < tenantGroupsList.size(); i++) {
            if (tenantGroupsList.get(i) == prop.getGroup()) {
                mrRepository.save(new MaintenanceRequest(status, prop, date, notes, tenant));
                notifService.createNotification(landlord, msg, "MAINTENANCE", dateString);
                notifService.createNotification(tenant, "Your maintenance request for " + address + " has been sent.", "MAINTENANCE", dateString);
                return;
            }
        }
    }


    public void updateMaintenanceRequest(MaintenanceRequest request, MaintenanceUpdateData data) {
        long millis = System.currentTimeMillis();
        Timestamp date = new java.sql.Timestamp(millis);
        String dateString = date.toString();
        String statusDesc = data.getStatusDesc();
        String msg;
        MaintenanceStatus status;
        Properties prop = request.getProperty();
        PropertyLocations location = prop.getLocation();
        String address = location.getAddress();
        User tenant = request.getTenant();
        if (statusDesc.equals("in progress")) {
            status = new MaintenanceStatus("in progress", 2);
            msg = "Your maintenance request for " + address + " is in progress." ;
        } else if (statusDesc.equals("resolved")) {
            status = new MaintenanceStatus("resolved", 3);
            msg = "Your maintenance request for " + address + " has been resolved";
        } else {
            status = new MaintenanceStatus("denied", 4);
            msg = "Your maintenance request for " + address + " has been denied.";
        }
        request.setStatus(status);

        mrRepository.save(request);
        notifService.createNotification(tenant, msg, "MAINTENANCE", dateString);

    }

    public MaintenanceRequest getRequestById(int id) throws ValidationException {
        try{
            return mrRepository.findByRequestId(id);
        }catch(Exception e){
            throw new ValidationException("Couldn't find Request By Id");
        }
    }


}
