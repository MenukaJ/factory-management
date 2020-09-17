package com.itp.factory.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itp.factory.management.domain.Brand;
import com.itp.factory.management.resource.CommonAddResource;
import com.itp.factory.management.resource.CommonUpdateResource;


/**
 * Brand Service
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020  						   MenukaJ        Created
 *    
 ********************************************************************************************************
 */


@Service
public interface BrandService {

	/**
	 * 
	 * Find all Brand
	 * @author MenukaJ
	 * @return -JSON array of all Brand
	 * 
	 * */
	public List<Brand> getAll();
	
	/**
	 * 
	 * Find Brand by ID
	 * @author MenukaJ
	 * @return -JSON array of Brand
	 * 
	 * */
	public Optional<Brand> getById(Long id);
	
	/**
	 * 
	 * Find Brand by name
	 * @author MenukaJ
	 * @return -JSON array of Brand
	 * 
	 * */
	public Optional<Brand> getByName(String name);
	
	/**
	 * 
	 * Find Brand by status
	 * @author MenukaJ
	 * @return -JSON array of Brand
	 * 
	 * */
	public List<Brand> getByStatus(String status);
	
	/**
	 * 
	 * Insert Brand
	 * @author MenukaJ
	 * @param  - CommonAddResource
	 * @return - Successfully saved
	 * 
	 * */
	public Brand addBrand(CommonAddResource commonAddResource);

	/**
	 * 
	 * Update Brand
	 * @author MenukaJ
	 * @param  - CommonUpdateResource
	 * @return - Successfully saved
	 * 
	 * */
	public Brand updateBrand(CommonUpdateResource commonUpdateResource);
	
	/**
	 * 
	 * Delete Brand
	 * @author MenukaJ
	 * 
	 * */
	public void deleteBrand(Long id);
}
