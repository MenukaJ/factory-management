package com.itp.factory.management.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.core.LogginAuthentcation;
import com.itp.factory.management.domain.Attributes;
import com.itp.factory.management.enums.CommonStatus;
import com.itp.factory.management.exception.ValidateRecordException;
import com.itp.factory.management.repository.AttributesRepository;
import com.itp.factory.management.resource.CommonAddResource;
import com.itp.factory.management.resource.CommonUpdateResource;
import com.itp.factory.management.service.AttributesService;


/**
 * Attributes Service Impl
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
public class AttributesServiceImpl extends MessagePropertyBase implements AttributesService {
	
	@Autowired
	private AttributesRepository attributesRepository;

	@Override
	public List<Attributes> getAll() {
		return attributesRepository.findAll();
	}

	@Override
	public Optional<Attributes> getById(Long id) {
		Optional<Attributes> isPresentAttributes = attributesRepository.findById(id);
		if (isPresentAttributes.isPresent()) {
			return Optional.ofNullable(isPresentAttributes.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Attributes> getByName(String name) {
		Optional<Attributes> isPresentAttributes = attributesRepository.findByName(name);
		if (isPresentAttributes.isPresent()) {
			return Optional.ofNullable(isPresentAttributes.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public List<Attributes> getByStatus(String status) {
		return attributesRepository.findByStatus(CommonStatus.valueOf(status));
	}

	@Override
	public Attributes addAttributes(CommonAddResource commonAddResource) {
		Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        
        if(LogginAuthentcation.getInstance().getUserName() == null || LogginAuthentcation.getInstance().getUserName().isEmpty())
        	throw new ValidateRecordException(environment.getProperty("common.not-null"), "username");
        
        Optional<Attributes> isPresentAttributes = attributesRepository.findByName(commonAddResource.getName());
        if (isPresentAttributes.isPresent()) {
        	throw new ValidateRecordException(environment.getProperty("common.unique"), "name");
		}
        
        Attributes attributes = new Attributes();
        attributes.setName(commonAddResource.getName());
        attributes.setStatus(CommonStatus.valueOf(commonAddResource.getStatus()));
        attributes.setCreatedDate(currentTimestamp);
        attributes.setCreatedUser(LogginAuthentcation.getInstance().getUserName());
        attributes = attributesRepository.save(attributes);
    	return attributes;
	}

	@Override
	public Attributes updateAttributes(CommonUpdateResource AttributesUpdateResource) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		
		if (LogginAuthentcation.getInstance().getUserName()==null || LogginAuthentcation.getInstance().getUserName().isEmpty())
			throw new ValidateRecordException(getEnvironment().getProperty(COMMON_NOT_NULL), "username");
		
		Optional<Attributes> isPresentAttributes = attributesRepository.findById(Long.parseLong(AttributesUpdateResource.getId()));
		if (!isPresentAttributes.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
		
		if(!isPresentAttributes.get().getVersion().equals(Long.parseLong(AttributesUpdateResource.getVersion())))
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "version");
		
		Optional<Attributes> isPresentAttributesName = attributesRepository.findByName(AttributesUpdateResource.getName());
		if (isPresentAttributesName.isPresent() && isPresentAttributesName.get().getId() != isPresentAttributes.get().getId())			
			throw new ValidateRecordException(environment.getProperty("common.unique"), "name");
		
		Attributes attributes = isPresentAttributes.get();
		attributes.setName(AttributesUpdateResource.getName());
		attributes.setStatus(CommonStatus.valueOf(AttributesUpdateResource.getStatus()));
		attributes.setModifiedDate(currentTimestamp);
		attributes.setModifiedUser(LogginAuthentcation.getInstance().getUserName());
		attributes = attributesRepository.saveAndFlush(attributes);
    	return attributes;
	}

	@Override
	public void deleteAttributes(Long id) {
		Optional<Attributes> isPresentAttributes = attributesRepository.findById(id);
		if (!isPresentAttributes.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
		else
			attributesRepository.deleteById(id);	
	}
	
}
