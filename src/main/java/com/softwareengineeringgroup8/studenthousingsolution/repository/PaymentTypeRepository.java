package com.softwareengineeringgroup8.studenthousingsolution.repository;
import com.softwareengineeringgroup8.studenthousingsolution.model.PaymentRecord;
import com.softwareengineeringgroup8.studenthousingsolution.model.PaymentType;
import com.softwareengineeringgroup8.studenthousingsolution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {

    PaymentType findBypTypeDesc(String pTypeDesc);

    @Query("SELECT u FROM PaymentType u WHERE u.id = ?1")
    PaymentType findByPaymentTypeID(int id);

}
