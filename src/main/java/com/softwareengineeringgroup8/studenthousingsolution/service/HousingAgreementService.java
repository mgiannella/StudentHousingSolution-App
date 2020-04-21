package com.softwareengineeringgroup8.studenthousingsolution.service;

import com.softwareengineeringgroup8.studenthousingsolution.exceptions.ValidationException;
import com.softwareengineeringgroup8.studenthousingsolution.model.*;
import com.softwareengineeringgroup8.studenthousingsolution.repository.HousingAgreementRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.PropertiesRepository;
import com.softwareengineeringgroup8.studenthousingsolution.repository.TenantGroupMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

import java.sql.Date;

@Component
public class HousingAgreementService {

    @Autowired
    private HousingAgreementRepository agreementRepository;
    @Autowired
    private PropertiesRepository propRepository;
    @Autowired
    private TenantGroupMembersRepository tgmRepository;
    @Autowired
    private NotificationService notifService;

    public HousingAgreement getHousingAgreement(Properties property){
        if(!agreementRepository.existsByProperty(property)){
            throw new ValidationException("Lease does not exist");
        }
        return agreementRepository.findByProperty(property);
    }

    public void uploadLease(LeaseUpdate data){
        Properties prop = propRepository.findById(data.getId());
        if(agreementRepository.existsByProperty(prop)){
            throw new ValidationException("Lease exists");
        }
        Date start = Date.valueOf(data.getStartDate());
        Date end = Date.valueOf(data.getEndDate());
        HousingAgreement lease = new HousingAgreement(prop, data.getLease(), start, end);
        // code to send notifications to all members of tenant group

        TenantGroups tg = prop.getGroup();
        List<TenantGroupMembers> users = tgmRepository.findTenantGroupMembersByGroup(tg);
        String msg = "Your landlord has uploaded the lease for " + prop.getLocation().getAddress() + ".";
        for(int i = 0; i < users.size(); i++){
            long millis = System.currentTimeMillis();
            Timestamp date = new java.sql.Timestamp(millis);
            String str = date.toString();
            User tenant = users.get(i).getTenant();
            notifService.createNotification(tenant, msg, "GENERAL", str);
        }

        agreementRepository.save(lease);
    }
    //get user from controller when they log in, then get user id, then get tenant group member and then set signed lease to true.
    //send notification to landlord after one tenant signs it
    //send another notification to landlord once the whole group signs the lease
    //get landlord with property ID
    public void signLease(User tenant, SignLease data){
        int propID = data.getPropertyID();
        Properties prop = propRepository.findById(propID);
        HousingAgreement lease = agreementRepository.findByProperty(prop);
        if(!agreementRepository.existsByProperty(prop)){
            throw new ValidationException("Lease does not exist");
        }
        TenantGroups group = prop.getGroup();
        TenantGroupMembers member = tgmRepository.findTenantGroupMembersByUserAndGroup(tenant, group);

        //for notification
        User landlord = prop.getLandlord();
        String msg = tenant.getFullname() + " has signed the lease for " + prop.getLocation().getAddress() +".";
        long millis = System.currentTimeMillis();
        Timestamp date = new java.sql.Timestamp(millis);
        String str = date.toString();
        if(data.getSigned()){
            member.setSignedLease(true);
            tgmRepository.save(member);
            notifService.createNotification(landlord, msg, "GENERAL", str);

        }
    }
    public void deleteLease(int propID){
        Properties prop = propRepository.findById(propID);
        HousingAgreement lease = agreementRepository.findByProperty(prop);
        if(!agreementRepository.existsByProperty(prop)){
            throw new ValidationException("Lease does not exist");
        }
        agreementRepository.delete(lease);
        //changing tenants to unsigned lease
        TenantGroups group = prop.getGroup();
        List<TenantGroupMembers> members = tgmRepository.findTenantGroupMembersByGroup(group);
        for(int i = 0; i < members.size(); i++){
            TenantGroupMembers member = members.get(i);
            member.setSignedLease(false);
            tgmRepository.save(member);
        }
    }
}
