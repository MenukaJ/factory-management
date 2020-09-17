package com.itp.factory.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itp.factory.management.domain.Store;
import com.itp.factory.management.resource.CommonAddResource;
import com.itp.factory.management.resource.CommonUpdateResource;


/**
 * Store Service
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020  						   MenukaJ        Created
 *    
 ********************************************************************************************************
 */


@Service
public interface StoreService {

	/**
	 * 
	 * Find all Store
	 * @author MenukaJ
	 * @return -JSON array of all Store
	 * 
	 * */
	public List<Store> getAll();
	
	/**
	 * 
	 * Find Store by ID
	 * @author MenukaJ
	 * @return -JSON array of Store
	 * 
	 * */
	public Optional<Store> getById(Long id);
	
	/**
	 * 
	 * Find Store by name
	 * @author MenukaJ
	 * @return -JSON array of Store
	 * 
	 * */
	public Optional<Store> getByName(String name);
	
	/**
	 * 
	 * Find Store by status
	 * @author MenukaJ
	 * @return -JSON array of Store
	 * 
	 * */
	public List<Store> getByStatus(String status);
	
	/**
	 * 
	 * Insert Store
	 * @author MenukaJ
	 * @param  - CommonAddResource
	 * @return - Successfully saved
	 * 
	 * */
	public Store addStore(CommonAddResource commonAddResource);

	/**
	 * 
	 * Update Store
	 * @author MenukaJ
	 * @param  - CommonUpdateResource
	 * @return - Successfully saved
	 * 
	 * */
	public Store updateStore(CommonUpdateResource commonUpdateResource);
	
	/**
	 * 
	 * Delete Store
	 * @author MenukaJ
	 * 
	 * */
	public void deleteStore(Long id);
}
