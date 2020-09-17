package com.itp.factory.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itp.factory.management.domain.Attributes;
import com.itp.factory.management.enums.CommonStatus;


/**
 * Attributes Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   09-07-2020   						   MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface AttributesRepository extends JpaRepository<Attributes, Long> {
	
	Optional <Attributes> findByName(String name);
	
	List <Attributes> findByStatus(CommonStatus status);
	
	Optional <Attributes> findByNameAndId(String name, Long id);
}
