package com.itp.factory.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itp.factory.management.domain.AttributeValue;
import com.itp.factory.management.resource.AttributeValueAddResource;
import com.itp.factory.management.resource.AttributeValueUpdateResource;


/**
 * AttributeValue Service
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020  						   MenukaJ        Created
 *    
 ********************************************************************************************************
 */


@Service
public interface AttributeValueService {

	/**
	 * 
	 * Find all AttributeValue
	 * @author MenukaJ
	 * @return -JSON array of all AttributeValue
	 * 
	 * */
	public List<AttributeValue> getAll();
	
	/**
	 * 
	 * Find AttributeValue by ID
	 * @author MenukaJ
	 * @return -JSON array of AttributeValue
	 * 
	 * */
	public Optional<AttributeValue> getById(Long id);
	
	/**
	 * 
	 * Find AttributeValue by name
	 * @author MenukaJ
	 * @return -JSON array of AttributeValue
	 * 
	 * */
	public Optional<AttributeValue> getByValue(String value);
	
	/**
	 * 
	 * Find AttributeValue by status
	 * @author MenukaJ
	 * @return -JSON array of AttributeValue
	 * 
	 * */
	public List<AttributeValue> getByStatus(String status);
	
	/**
	 * 
	 * Insert AttributeValue
	 * @author MenukaJ
	 * @param  - AttributeValueAddResource
	 * @return - Successfully saved
	 * 
	 * */
	public AttributeValue addAttributeValue(AttributeValueAddResource attributeValueAddResource);

	/**
	 * 
	 * Update AttributeValue
	 * @author MenukaJ
	 * @param  - AttributeValueUpdateResource
	 * @return - Successfully saved
	 * 
	 * */
	public AttributeValue updateAttributeValue(AttributeValueUpdateResource attributeValueUpdateResource);
	
	/**
	 * 
	 * Delete AttributeValue
	 * @author MenukaJ
	 * 
	 * */
	public void deleteAttributeValue(Long id);
}
