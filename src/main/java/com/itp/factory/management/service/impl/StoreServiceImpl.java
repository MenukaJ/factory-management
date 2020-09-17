package com.itp.factory.management.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.core.LogginAuthentcation;
import com.itp.factory.management.domain.Store;
import com.itp.factory.management.enums.CommonStatus;
import com.itp.factory.management.exception.ValidateRecordException;
import com.itp.factory.management.repository.StoreRepository;
import com.itp.factory.management.resource.CommonAddResource;
import com.itp.factory.management.resource.CommonUpdateResource;
import com.itp.factory.management.service.StoreService;


/**
 * Store Service Impl
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   09-07-2020       						  MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class StoreServiceImpl extends MessagePropertyBase implements StoreService {
	
	@Autowired
	private StoreRepository storeRepository;

	@Override
	public List<Store> getAll() {
		return storeRepository.findAll();
	}

	@Override
	public Optional<Store> getById(Long id) {
		Optional<Store> isPresentStore = storeRepository.findById(id);
		if (isPresentStore.isPresent()) {
			return Optional.ofNullable(isPresentStore.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Store> getByName(String name) {
		Optional<Store> isPresentStore = storeRepository.findByName(name);
		if (isPresentStore.isPresent()) {
			return Optional.ofNullable(isPresentStore.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public List<Store> getByStatus(String status) {
		return storeRepository.findByStatus(CommonStatus.valueOf(status));
	}

	@Override
	public Store addStore(CommonAddResource commonAddResource) {
		Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        
        if(LogginAuthentcation.getInstance().getUserName() == null || LogginAuthentcation.getInstance().getUserName().isEmpty())
        	throw new ValidateRecordException(environment.getProperty("common.not-null"), "username");
        
        Optional<Store> isPresentStore = storeRepository.findByName(commonAddResource.getName());
        if (isPresentStore.isPresent()) {
        	throw new ValidateRecordException(environment.getProperty("common.unique"), "name");
		}
        
        Store store = new Store();
        store.setName(commonAddResource.getName());
        store.setStatus(CommonStatus.valueOf(commonAddResource.getStatus()));
        store.setCreatedDate(currentTimestamp);
        store.setCreatedUser(LogginAuthentcation.getInstance().getUserName());
        store = storeRepository.save(store);
    	return store;
	}

	@Override
	public Store updateStore(CommonUpdateResource commonUpdateResource) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		
		if (LogginAuthentcation.getInstance().getUserName()==null || LogginAuthentcation.getInstance().getUserName().isEmpty())
			throw new ValidateRecordException(getEnvironment().getProperty(COMMON_NOT_NULL), "username");
		
		Optional<Store> isPresentStore = storeRepository.findById(Long.parseLong(commonUpdateResource.getId()));
		if (!isPresentStore.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
		
		if(!isPresentStore.get().getVersion().equals(Long.parseLong(commonUpdateResource.getVersion())))
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "version");
		
		Optional<Store> isPresentStoreName = storeRepository.findByName(commonUpdateResource.getName());
		if (isPresentStoreName.isPresent() && isPresentStoreName.get().getId() != isPresentStore.get().getId())			
			throw new ValidateRecordException(environment.getProperty("common.unique"), "name");
		
		Store store = isPresentStore.get();
		store.setName(commonUpdateResource.getName());
		store.setStatus(CommonStatus.valueOf(commonUpdateResource.getStatus()));
		store.setModifiedDate(currentTimestamp);
		store.setModifiedUser(LogginAuthentcation.getInstance().getUserName());
		store = storeRepository.saveAndFlush(store);
    	return store;
	}

	@Override
	public void deleteStore(Long id) {
		Optional<Store> isPresentStore = storeRepository.findById(id);
		if (!isPresentStore.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
		else
			storeRepository.deleteById(id);	
	}
	
}
