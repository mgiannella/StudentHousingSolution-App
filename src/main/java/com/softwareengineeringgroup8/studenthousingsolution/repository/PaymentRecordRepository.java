package com.softwareengineeringgroup8.studenthousingsolution.repository;

        import com.softwareengineeringgroup8.studenthousingsolution.model.PaymentRecord;
        import com.softwareengineeringgroup8.studenthousingsolution.model.User;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;
        import java.util.List;

@Repository
public interface PaymentRecordRepository extends JpaRepository<PaymentRecord,Integer> {
       @Query("SELECT u FROM PaymentRecord u WHERE u.tenant = ?1")
        List<PaymentRecord> findByUser(User tenant);

       @Query("SELECT u FROM PaymentRecord u WHERE u.id = ?1")
        PaymentRecord findByPaymentRecordID(int id);

}
