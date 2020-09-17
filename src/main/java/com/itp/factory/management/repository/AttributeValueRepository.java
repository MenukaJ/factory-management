package com.itp.factory.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itp.factory.management.domain.AttributeValue;
import com.itp.factory.management.enums.CommonStatus;


/**
 * AttributeValue Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   09-07-2020                            MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {
	
	List <AttributeValue> findByStatus(CommonStatus status);
	
	Optional <AttributeValue> findByValueAndId(String value, Long id);

	Optional<AttributeValue> findByValue(String value);
}
