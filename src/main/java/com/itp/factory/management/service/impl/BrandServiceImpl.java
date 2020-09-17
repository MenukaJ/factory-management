package com.itp.factory.management.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.core.LogginAuthentcation;
import com.itp.factory.management.domain.Brand;
import com.itp.factory.management.enums.CommonStatus;
import com.itp.factory.management.exception.ValidateRecordException;
import com.itp.factory.management.repository.BrandRepository;
import com.itp.factory.management.resource.CommonAddResource;
import com.itp.factory.management.resource.CommonUpdateResource;
import com.itp.factory.management.service.BrandService;


/**
 * Brand Service Impl
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
public class BrandServiceImpl extends MessagePropertyBase implements BrandService {
	
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public List<Brand> getAll() {
		return brandRepository.findAll();
	}

	@Override
	public Optional<Brand> getById(Long id) {
		Optional<Brand> isPresentBrand = brandRepository.findById(id);
		if (isPresentBrand.isPresent()) {
			return Optional.ofNullable(isPresentBrand.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Brand> getByName(String name) {
		Optional<Brand> isPresentBrand = brandRepository.findByName(name);
		if (isPresentBrand.isPresent()) {
			return Optional.ofNullable(isPresentBrand.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public List<Brand> getByStatus(String status) {
		return brandRepository.findByStatus(CommonStatus.valueOf(status));
	}

	@Override
	public Brand addBrand(CommonAddResource commonAddResource) {
		Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        
        if(LogginAuthentcation.getInstance().getUserName() == null || LogginAuthentcation.getInstance().getUserName().isEmpty())
        	throw new ValidateRecordException(environment.getProperty("common.not-null"), "username");
        
        Optional<Brand> isPresentBrand = brandRepository.findByName(commonAddResource.getName());
        if (isPresentBrand.isPresent()) {
        	throw new ValidateRecordException(environment.getProperty("common.unique"), "name");
		}
        
        Brand brand = new Brand();
        brand.setName(commonAddResource.getName());
        brand.setStatus(CommonStatus.valueOf(commonAddResource.getStatus()));
        brand.setCreatedDate(currentTimestamp);
        brand.setCreatedUser(LogginAuthentcation.getInstance().getUserName());
        brand = brandRepository.save(brand);
    	return brand;
	}

	@Override
	public Brand updateBrand(CommonUpdateResource commonUpdateResource) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		
		if (LogginAuthentcation.getInstance().getUserName()==null || LogginAuthentcation.getInstance().getUserName().isEmpty())
			throw new ValidateRecordException(getEnvironment().getProperty(COMMON_NOT_NULL), "username");
		
		Optional<Brand> isPresentBrand = brandRepository.findById(Long.parseLong(commonUpdateResource.getId()));
		if (!isPresentBrand.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
		
		if(!isPresentBrand.get().getVersion().equals(Long.parseLong(commonUpdateResource.getVersion())))
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "version");
		
		Optional<Brand> isPresentBrandName = brandRepository.findByName(commonUpdateResource.getName());
		if (isPresentBrandName.isPresent() && isPresentBrandName.get().getId() != isPresentBrand.get().getId())			
			throw new ValidateRecordException(environment.getProperty("common.unique"), "name");
		
		Brand brand = isPresentBrand.get();
		brand.setName(commonUpdateResource.getName());
		brand.setStatus(CommonStatus.valueOf(commonUpdateResource.getStatus()));
		brand.setModifiedDate(currentTimestamp);
		brand.setModifiedUser(LogginAuthentcation.getInstance().getUserName());
		brand = brandRepository.saveAndFlush(brand);
    	return brand;
	}

	@Override
	public void deleteBrand(Long id) {
		Optional<Brand> isPresentBrand = brandRepository.findById(id);
		if (!isPresentBrand.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
		else
			brandRepository.deleteById(id);	
	}
	
}
