package com.softwareengineeringgroup8.studenthousingsolution.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceUpdateData;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertiesRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserRepository;
import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceRequest;
import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceRequestData;
import com.softwareengineeringgroup8.studenthousingsolution.model.MaintenanceStatus;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertiesRepository propertiesRepository;

    public List<MaintenanceRequest> listAll(){
        return mrRepository.findAll();
    }

    public void createMaintenanceRequest(User tenant, Properties prop, MaintenanceRequestData data){
        //statusRepository.save(new MaintenanceStatus("pending", 1));
        //statusRepository.save(new MaintenanceStatus("in progress", 2));
        //statusRepository.save(new MaintenanceStatus("resolved",3));
        //statusRepository.save(new MaintenanceStatus("denied",4));

        MaintenanceStatus status = new MaintenanceStatus("pending", 1); //
        long millis = System.currentTimeMillis();
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String notes = data.getNotes();
        mrRepository.save(new MaintenanceRequest(status, prop, date, notes, tenant));
    }

    /*
    public void updateMaintenanceRequest(int id, Properties prop, MaintenanceUpdateData data){
        //List<MaintenanceRequest> requests = mrRepository.findByProperty(propertyID);
        String statusDesc = data.getStatusDesc();
        MaintenanceStatus status;
        if(statusDesc.equals("in progress")){
            status = new MaintenanceStatus("in progress", 2);
        }else if(statusDesc.equals("resolved")){
            status = new MaintenanceStatus("resolved", 3);
        }else{
            status = new MaintenanceStatus("denied", 4);
        }
        return requests;
        //request.setStatus(status);
    }



    /*public MaintenanceRequest getRequestById(int id) throws ValidationException {
        try{
            return mrRepository.findById(id);
        }catch(Exception e){
            throw new ValidationException("Couldn't find Request By Id");
        }
    }

     */


}
