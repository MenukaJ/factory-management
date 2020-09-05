package com.itp.factory.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itp.factory.management.domain.Category;
import com.itp.factory.management.resource.CategoryAddResource;
import com.itp.factory.management.resource.CategoryUpdateResource;


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
	 * @param  - CategoryAddResource
	 * @return - Successfully saved
	 * 
	 * */
	public Category addCategory(CategoryAddResource categoryAddResource);

	/**
	 * 
	 * Update Category
	 * @author MenukaJ
	 * @param  - CategoryUpdateResource
	 * @return - Successfully saved
	 * 
	 * */
	public Category updateCategory(CategoryUpdateResource categoryUpdateResource);
	
	/**
	 * 
	 * Delete Category
	 * @author MenukaJ
	 * 
	 * */
	public void deleteCategory(Long id);
}
