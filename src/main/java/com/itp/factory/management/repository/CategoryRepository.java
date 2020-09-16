package com.itp.factory.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itp.factory.management.domain.Category;
import com.itp.factory.management.enums.CommonStatus;


/**
 * Insurance Type Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   09-07-2020   FX-1429       FX-4131   MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Optional <Category> findByName(String name);
	
	List <Category> findByStatus(CommonStatus status);
	
	Optional <Category> findByNameAndId(String name, Long id);
}
