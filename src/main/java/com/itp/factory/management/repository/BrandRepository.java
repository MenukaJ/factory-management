package com.itp.factory.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itp.factory.management.domain.Brand;
import com.itp.factory.management.enums.CommonStatus;


/**
 * Brand Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   09-07-2020                            MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
	
	Optional <Brand> findByName(String name);
	
	List <Brand> findByStatus(CommonStatus status);
	
	Optional <Brand> findByNameAndId(String name, Long id);
}
