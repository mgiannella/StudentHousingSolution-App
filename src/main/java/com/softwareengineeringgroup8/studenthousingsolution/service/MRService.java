package com.softwareengineeringgroup8.studenthousingsolution.service;

import java.sql.Date;
import java.util.List;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceRequestData;
import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceStatus;
import com.softwareengineeringgroup8.studenthousingsolution.repository.MaintenanceRequestRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.MaintenanceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MRService {

    @Autowired
    private MaintenanceRequestRepository mrRepository;
    @Autowired
    private MaintenanceStatusRepository statusRepository;

    public List<MaintenanceRequest> listAll(){
        return mrRepository.findAll();
    }

    public void createMaintenanceRequest(MaintenanceRequestData data){
        statusRepository.save(new MaintenanceStatus("pending", 1));
        statusRepository.save(new MaintenanceStatus("in progress", 2));
        statusRepository.save(new MaintenanceStatus("resolved",3));
        statusRepository.save(new MaintenanceStatus("denied",4));

        int propertyID = 5;
        MaintenanceStatus status = new MaintenanceStatus("pending", 1); //
        Date date = data.getDate();
        String notes = data.getNotes();
        mrRepository.save(new MaintenanceRequest(status, propertyID, date, notes));
    }

    public MaintenanceRequest getRequestById(int id) throws ValidationException {
        try{
            return mrRepository.findById(id);
        }catch(Exception e){
            throw new ValidationException("Couldn't find Request By Id");
        }
    }


}
