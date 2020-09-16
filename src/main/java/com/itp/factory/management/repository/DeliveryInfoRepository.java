package com.itp.factory.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itp.factory.management.domain.DeliveryInfo;
import com.itp.factory.management.domain.Supplier;

/**
 * DeliveryInfo Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   06-09-2020                            Anushka        Created
 *    
 ********************************************************************************************************
 */


@Repository
public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfo ,Long>{
	
	
	
	List <Supplier> findById(String id);
	
	
	
	List<DeliveryInfo>findAll();
	
	Optional <DeliveryInfo> findById(Long id);

	
}
