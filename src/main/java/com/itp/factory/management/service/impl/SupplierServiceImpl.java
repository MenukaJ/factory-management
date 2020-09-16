package com.itp.factory.management.service.impl;

/**
 * Supplier Service Implementation
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   07-09-2020                            Anushka        Created
 *    
 ********************************************************************************************************
 */

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.core.LogginAuthentcation;
import com.itp.factory.management.domain.Supplier;
import com.itp.factory.management.exception.ValidateRecordException;
import com.itp.factory.management.repository.SupplierReppository;
import com.itp.factory.management.resource.SupplierAddResource;
import com.itp.factory.management.resource.SupplierUpdateResource;
import com.itp.factory.management.service.SupplierService;
/**
 * Supplier Service
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   02-09-2020                            Anushka        Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class SupplierServiceImpl extends MessagePropertyBase implements SupplierService {
	
	@Autowired
	private SupplierReppository supplierRepository;
	
	@Override
	public List<Supplier> getAll() {
		return supplierRepository.findAll();
	}

	@Override
	public Optional<Supplier> getById(Long id) {
		
		Optional<Supplier> currentSupplier = supplierRepository.findById(id);
		if (currentSupplier.isPresent()) {
			return currentSupplier;
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Supplier> getByName(String name) {
		
		Optional<Supplier> isSupplier = supplierRepository.findByName(name);
		if (isSupplier.isPresent()) {
			return Optional.ofNullable(isSupplier.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Supplier addSupplier(SupplierAddResource supplierAddResource) {
	    
		
		if (LogginAuthentcation.getInstance().getUserName()==null || LogginAuthentcation.getInstance().getUserName().isEmpty())
			throw new ValidateRecordException(getEnvironment().getProperty(COMMON_NOT_NULL), "username");
		
        Optional<Supplier> isSupplier = supplierRepository.findByName(supplierAddResource.getName());
        if (isSupplier.isPresent()) 
        	throw new ValidateRecordException(environment.getProperty("common.unique"), "Name");
		
        Supplier supplier = new Supplier();
        supplier.setName(supplierAddResource.getName());
        supplier = supplierRepository.save(supplier);
    	return supplier;
	}
	
	

	@Override
	public Supplier updateSupplier(SupplierUpdateResource supplierUpdateResource) {
		
		
		if (LogginAuthentcation.getInstance().getUserName()==null || LogginAuthentcation.getInstance().getUserName().isEmpty())
			throw new ValidateRecordException(getEnvironment().getProperty(COMMON_NOT_NULL), "username");
		
		Optional<Supplier> isSupplier = supplierRepository.findById(Long.parseLong(supplierUpdateResource.getId()));
		if (!isSupplier.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
		
		
		Optional<Supplier> isSupplierName = supplierRepository.findByName(supplierUpdateResource.getName());
		if (isSupplierName.isPresent() && isSupplierName.get().getId() != isSupplierName.get().getId())			
			throw new ValidateRecordException(environment.getProperty("common.unique"), "name");
		
		Supplier supplier = isSupplier.get();
		supplier.setName(supplierUpdateResource.getName());
		supplier = supplierRepository.saveAndFlush(supplier);
    	return supplier;
	}

	@Override
	public void deleteSupplier(long id) {
		Optional<Supplier> isSupplier = supplierRepository.findById(id);
		if (!isSupplier.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
		else
			supplierRepository.deleteById(id);
		
	}

	
}
