package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softwareengineeringgroup8.studenthousingsolution.model.PaymentRecord;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PaymentRecordRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PaymentTypeRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertiesRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.UserRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;
import java.net.InetAddress;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class PendingPaymentService {
    @Autowired
    private PaymentRecordRepository paymentRecordRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TenantGroupsService tenantGroupsService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertiesRepository propertiesRepository;

    public Boolean createPaymentRequest(int propID, int tenantID, String amount, String ptype) {

        // Gets property description from front end selection
        String ptypeDesc = null;

        if (ptype == "Complete Rent") {
            ptypeDesc = "TENANT_MONTHLY";
        } else if (ptype == "Utilities") {
            ptypeDesc = "UTILITIES";
        } else if (ptype == "Security Deposit") {
            ptypeDesc = "SECURITY_DEPOSIT";
        } else if (ptype == "Amenities") {
            ptypeDesc = "AMENITIES_FEE";
        } else {
            ptypeDesc = "PARKING_FEE";
        }

        Double a = Double.valueOf(amount);
        Properties property = propertiesRepository.findByPropertyID(propID);
        User tenant = userRepository.findById(tenantID);


        try {
            //PaymentRecord paymentRecord = new PaymentRecord(new Date(new java.util.Date().getTime()), property, tenant, paymentTypeRepository.findBypTypeDesc(ptypeDesc), new BigDecimal(a));
            //paymentRecordRepository.save(paymentRecord);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
}
