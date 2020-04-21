package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.HousingAgreement;
import com.softwareengineeringgroup8.studenthousingsolution.model.LeaseUpdate;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.repository.HousingAgreementRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class HousingAgreementService {

    @Autowired
    private HousingAgreementRepository agreementRepository;
    @Autowired
    private PropertiesRepository propRepository;

    public HousingAgreement getHousingAgreement(Properties property){ return agreementRepository.findByProperty(property);}

    public void uploadLease(LeaseUpdate data){
        Properties prop = propRepository.findById(data.getId());
        if(agreementRepository.existsByProperty(prop)){
            throw new ValidationException("Lease exists");
        }
        Date start = Date.valueOf(data.getStartDate());
        Date end = Date.valueOf(data.getEndDate());
        HousingAgreement lease = new HousingAgreement(prop, data.getLease(), start, end);
        // code to send notifications to all members of tenant group

        agreementRepository.save(lease);
    }

    public void deleteLease(int propID){
        Properties prop = propRepository.findById(propID);
        HousingAgreement lease = agreementRepository.findByProperty(prop);
        agreementRepository.delete(lease);
    }
}
