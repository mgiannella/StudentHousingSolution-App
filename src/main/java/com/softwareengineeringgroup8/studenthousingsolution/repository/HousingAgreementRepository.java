package com.softwareengineeringgroup8.studenthousingsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.softwareengineeringgroup8.studenthousingsolution.model.HousingAgreement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.softwareengineeringgroup8.studenthousingsolution.model.Properties;

@Repository
public interface HousingAgreementRepository extends JpaRepository<HousingAgreement, Integer> {

    boolean existsByProperty(Properties property);

    @Query("SELECT l FROM HousingAgreement l WHERE l.property = ?1")
    HousingAgreement findByProperty(Properties property);
}
