package com.itp.factory.management.repository;

import java.util.List;
import java.util.Optional;

import com.itp.factory.management.domain.FactoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itp.factory.management.enums.CommonStatus;

public interface FactoryDetailsRepository extends JpaRepository<FactoryDetails, Long> {

//    Optional <FactoryDetails> findByCompany_name(String name);

//    List <FactoryDetails> findByStatus(String status);
//
//    Optional <FactoryDetails> findByNameAndId(String name, Long id);
}
