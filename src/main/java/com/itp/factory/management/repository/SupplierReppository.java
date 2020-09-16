package com.itp.factory.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.itp.factory.management.domain.Supplier;
import com.itp.factory.management.enums.CommonStatus;

/**
 * Supplier Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   06-09-2020                            Anushka        Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface SupplierReppository extends JpaRepository<Supplier ,Long> {
		
	Optional <Supplier> findByName(String name);
	
	List <Supplier> findById(String id);
	
	Optional <Supplier> findByNameAndId(String name, Long id);
}
