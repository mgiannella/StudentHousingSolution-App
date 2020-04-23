package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softwareengineeringgroup8.studenthousingsolution.model.PaymentRecord;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;
import com.softwareengineeringgroup8.studenthousingsolution.model.TenantGroups;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import com.softwareengineeringgroup8.studenthousingsolution.repository.*;
import java.text.SimpleDateFormat;
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
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TenantGroupsService tenantGroupsService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertiesRepository propertiesRepository;
    @Autowired
    private LandlordAccountsRepository landlordAccountsRepository;
    @Autowired
    private PaymentRecordRepository paymentRecordRepository;

    public Boolean createPaymentRequest(int propID, int tenantID, String amount, String ptype, String dueDate)  {

        // Gets property description from front end selection

        try {
            if (ptype == null || dueDate==null || amount==null) {
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Date dDate=Date.valueOf(dueDate);

        Float a = Float.valueOf(amount);
        //Double am=Double.valueOf(a);
        Properties property = propertiesRepository.findByPropertyID(propID);
        User tenant = userRepository.findById(tenantID);


        PaymentRecord paymentRecord = new PaymentRecord(null, property, tenant, paymentTypeRepository.findBypTypeDesc(ptype), new BigDecimal(a),dDate,null);
        paymentRecordRepository.save(paymentRecord);

        if (paymentRecord==null){
            return false;
        }
        return true;

    }


    //public PaymentRecord getByUser(User tenant)

    public List<PaymentRecord> getPaymentRecordByUser(User tenant){
        // List<PaymentRecord> complete = new
        return paymentRecordRepository.findByUser(tenant);}

    public PaymentRecord getPaymentRecordById(int id){
        return paymentRecordRepository.findByPaymentRecordID(id);}
}
