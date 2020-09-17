package com.itp.factory.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itp.factory.management.domain.Category;
import com.itp.factory.management.resource.CommonAddResource;
import com.itp.factory.management.resource.CommonUpdateResource;


/**
 * Category Service
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020  						   MenukaJ        Created
 *    
 ********************************************************************************************************
 */


@Service
public interface CategoryService {

	/**
	 * 
	 * Find all Category
	 * @author MenukaJ
	 * @return -JSON array of all Category
	 * 
	 * */
	public List<Category> getAll();
	
	/**
	 * 
	 * Find Category by ID
	 * @author MenukaJ
	 * @return -JSON array of Category
	 * 
	 * */
	public Optional<Category> getById(Long id);
	
	/**
	 * 
	 * Find Category by name
	 * @author MenukaJ
	 * @return -JSON array of Category
	 * 
	 * */
	public Optional<Category> getByName(String name);
	
	/**
	 * 
	 * Find Category by status
	 * @author MenukaJ
	 * @return -JSON array of Category
	 * 
	 * */
	public List<Category> getByStatus(String status);
	
	/**
	 * 
	 * Insert Category
	 * @author MenukaJ
	 * @param  - CommonAddResource
	 * @return - Successfully saved
	 * 
	 * */
	public Category addCategory(CommonAddResource commonAddResource);

	/**
	 * 
	 * Update Category
	 * @author MenukaJ
	 * @param  - CommonUpdateResource
	 * @return - Successfully saved
	 * 
	 * */
	public Category updateCategory(CommonUpdateResource commonUpdateResource);
	
	/**
	 * 
	 * Delete Category
	 * @author MenukaJ
	 * 
	 * */
	public void deleteCategory(Long id);
}
