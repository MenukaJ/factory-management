package com.itp.factory.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itp.factory.management.domain.Attributes;
import com.itp.factory.management.resource.CommonAddResource;
import com.itp.factory.management.resource.CommonUpdateResource;


/**
 * Attributes Service
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020  						   MenukaJ        Created
 *    
 ********************************************************************************************************
 */


@Service
public interface AttributesService {

	/**
	 * 
	 * Find all Attributes
	 * @author MenukaJ
	 * @return -JSON array of all Attributes
	 * 
	 * */
	public List<Attributes> getAll();
	
	/**
	 * 
	 * Find Attributes by ID
	 * @author MenukaJ
	 * @return -JSON array of Attributes
	 * 
	 * */
	public Optional<Attributes> getById(Long id);
	
	/**
	 * 
	 * Find Attributes by name
	 * @author MenukaJ
	 * @return -JSON array of Attributes
	 * 
	 * */
	public Optional<Attributes> getByName(String name);
	
	/**
	 * 
	 * Find Attributes by status
	 * @author MenukaJ
	 * @return -JSON array of Attributes
	 * 
	 * */
	public List<Attributes> getByStatus(String status);
	
	/**
	 * 
	 * Insert Attributes
	 * @author MenukaJ
	 * @param  - CommonAddResource
	 * @return - Successfully saved
	 * 
	 * */
	public Attributes addAttributes(CommonAddResource commonAddResource);

	/**
	 * 
	 * Update Attributes
	 * @author MenukaJ
	 * @param  - CommonUpdateResource
	 * @return - Successfully saved
	 * 
	 * */
	public Attributes updateAttributes(CommonUpdateResource commonUpdateResource);
	
	/**
	 * 
	 * Delete Attributes
	 * @author MenukaJ
	 * 
	 * */
	public void deleteAttributes(Long id);
}
