package com.itp.factory.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itp.factory.management.domain.Store;
import com.itp.factory.management.enums.CommonStatus;


/**
 * Store Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   09-07-2020                            MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	
	Optional <Store> findByName(String name);
	
	List <Store> findByStatus(CommonStatus status);
	
	Optional <Store> findByNameAndId(String name, Long id);
}
