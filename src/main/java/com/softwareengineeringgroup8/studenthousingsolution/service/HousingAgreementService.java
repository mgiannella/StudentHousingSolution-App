package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.model.HousingAgreement;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.repository.HousingAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HousingAgreementService {

    @Autowired
    private HousingAgreementRepository agreementRepository;

    public HousingAgreement getHousingAgreement(Properties property){ return agreementRepository.findByProperty(property);}
}
