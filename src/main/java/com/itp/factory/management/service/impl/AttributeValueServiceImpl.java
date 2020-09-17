package com.itp.factory.management.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.core.LogginAuthentcation;
import com.itp.factory.management.domain.AttributeValue;
import com.itp.factory.management.domain.Attributes;
import com.itp.factory.management.enums.CommonStatus;
import com.itp.factory.management.exception.ValidateRecordException;
import com.itp.factory.management.repository.AttributeValueRepository;
import com.itp.factory.management.repository.AttributesRepository;
import com.itp.factory.management.resource.AttributeValueAddResource;
import com.itp.factory.management.resource.AttributeValueUpdateResource;
import com.itp.factory.management.service.AttributeValueService;


/**
 * AttributeValue Service Impl
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
public class AttributeValueServiceImpl extends MessagePropertyBase implements AttributeValueService {
	
	@Autowired
	private AttributeValueRepository attributeValueRepository;
	
	@Autowired
	private AttributesRepository attributesRepository;

	@Override
	public List<AttributeValue> getAll() {
		return attributeValueRepository.findAll();
	}

	@Override
	public Optional<AttributeValue> getById(Long id) {
		Optional<AttributeValue> isPresentAttributeValue = attributeValueRepository.findById(id);
		if (isPresentAttributeValue.isPresent()) {
			return Optional.ofNullable(isPresentAttributeValue.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<AttributeValue> getByValue(String value) {
		Optional<AttributeValue> isPresentAttributeValue = attributeValueRepository.findByValue(value);
		if (isPresentAttributeValue.isPresent()) {
			return Optional.ofNullable(isPresentAttributeValue.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public List<AttributeValue> getByStatus(String status) {
		return attributeValueRepository.findByStatus(CommonStatus.valueOf(status));
	}

	@Override
	public AttributeValue addAttributeValue(AttributeValueAddResource attributeValueAddResource) {
		Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        
        if(LogginAuthentcation.getInstance().getUserName() == null || LogginAuthentcation.getInstance().getUserName().isEmpty())
        	throw new ValidateRecordException(environment.getProperty("common.not-null"), "username");
        
        Optional<AttributeValue> isPresentAttributeValue = attributeValueRepository.findByValue(attributeValueAddResource.getValue());
        if (isPresentAttributeValue.isPresent()) {
        	throw new ValidateRecordException(environment.getProperty("common.unique"), "value");
		}
        
        Optional<Attributes> isPresentAttributes = attributesRepository.findById(Long.parseLong(attributeValueAddResource.getAttributesId()));
        if (!isPresentAttributes.isPresent())
        	throw new ValidateRecordException(environment.getProperty(COMMON_INVALID_VALUE), "attributesId");
        if(isPresentAttributes.get().getStatus().equals(CommonStatus.INACTIVE))
        	throw new ValidateRecordException(environment.getProperty(COMMON_INVALID_VALUE), "attributesId");
        
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setAttributes(isPresentAttributes.get());
        attributeValue.setValue(attributeValueAddResource.getValue());
        attributeValue.setStatus(CommonStatus.valueOf(attributeValueAddResource.getStatus()));
        attributeValue.setCreatedDate(currentTimestamp);
        attributeValue.setCreatedUser(LogginAuthentcation.getInstance().getUserName());
        attributeValue = attributeValueRepository.save(attributeValue);
    	return attributeValue;
	}

	@Override
	public AttributeValue updateAttributeValue(AttributeValueUpdateResource attributeValueUpdateResource) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		
		if (LogginAuthentcation.getInstance().getUserName()==null || LogginAuthentcation.getInstance().getUserName().isEmpty())
			throw new ValidateRecordException(getEnvironment().getProperty(COMMON_NOT_NULL), "username");
		
		Optional<AttributeValue> isPresentAttributeValue = attributeValueRepository.findById(Long.parseLong(attributeValueUpdateResource.getId()));
		if (!isPresentAttributeValue.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
		
		if(!isPresentAttributeValue.get().getVersion().equals(Long.parseLong(attributeValueUpdateResource.getVersion())))
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "version");
		
		Optional<AttributeValue> isPresentAttributeValueName = attributeValueRepository.findByValue(attributeValueUpdateResource.getValue());
		if (isPresentAttributeValueName.isPresent() && isPresentAttributeValueName.get().getId() != isPresentAttributeValue.get().getId())			
			throw new ValidateRecordException(environment.getProperty("common.unique"), "value");
		
        Optional<Attributes> isPresentAttributes = attributesRepository.findById(Long.parseLong(attributeValueUpdateResource.getAttributesId()));
        if (!isPresentAttributes.isPresent())
        	throw new ValidateRecordException(environment.getProperty(COMMON_INVALID_VALUE), "attributesId");
        if(isPresentAttributes.get().getStatus().equals(CommonStatus.INACTIVE))
        	throw new ValidateRecordException(environment.getProperty(COMMON_INVALID_VALUE), "attributesId");
		
		AttributeValue attributeValue = isPresentAttributeValue.get();
		
		attributeValue.setValue(attributeValueUpdateResource.getValue());
		attributeValue.setAttributes(isPresentAttributes.get());
		attributeValue.setStatus(CommonStatus.valueOf(attributeValueUpdateResource.getStatus()));
		attributeValue.setModifiedDate(currentTimestamp);
		attributeValue.setModifiedUser(LogginAuthentcation.getInstance().getUserName());
		attributeValue = attributeValueRepository.saveAndFlush(attributeValue);
    	return attributeValue;
	}

	@Override
	public void deleteAttributeValue(Long id) {
		Optional<AttributeValue> isPresentAttributeValue = attributeValueRepository.findById(id);
		if (!isPresentAttributeValue.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
		else
			attributeValueRepository.deleteById(id);	
	}
	
}
