package com.itp.factory.management.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.core.LogginAuthentcation;
import com.itp.factory.management.domain.Category;
import com.itp.factory.management.enums.CommonStatus;
import com.itp.factory.management.exception.ValidateRecordException;
import com.itp.factory.management.repository.CategoryRepository;
import com.itp.factory.management.resource.CommonAddResource;
import com.itp.factory.management.resource.CommonUpdateResource;
import com.itp.factory.management.service.CategoryService;


/**
 * Category Service Impl
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
public class CategoryServiceImpl extends MessagePropertyBase implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> getById(Long id) {
		Optional<Category> isPresentCategory = categoryRepository.findById(id);
		if (isPresentCategory.isPresent()) {
			return Optional.ofNullable(isPresentCategory.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Category> getByName(String name) {
		Optional<Category> isPresentCategory = categoryRepository.findByName(name);
		if (isPresentCategory.isPresent()) {
			return Optional.ofNullable(isPresentCategory.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public List<Category> getByStatus(String status) {
		return categoryRepository.findByStatus(CommonStatus.valueOf(status));
	}

	@Override
	public Category addCategory(CommonAddResource commonAddResource) {
		Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        
        if(LogginAuthentcation.getInstance().getUserName() == null || LogginAuthentcation.getInstance().getUserName().isEmpty())
        	throw new ValidateRecordException(environment.getProperty("common.not-null"), "username");
        
        Optional<Category> isPresentCategory = categoryRepository.findByName(commonAddResource.getName());
        if (isPresentCategory.isPresent()) {
        	throw new ValidateRecordException(environment.getProperty("common.unique"), "name");
		}
        
        Category category = new Category();
        category.setName(commonAddResource.getName());
        category.setStatus(CommonStatus.valueOf(commonAddResource.getStatus()));
        category.setCreatedDate(currentTimestamp);
        category.setCreatedUser(LogginAuthentcation.getInstance().getUserName());
        category = categoryRepository.save(category);
    	return category;
	}

	@Override
	public Category updateCategory(CommonUpdateResource commonUpdateResource) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		
		if (LogginAuthentcation.getInstance().getUserName()==null || LogginAuthentcation.getInstance().getUserName().isEmpty())
			throw new ValidateRecordException(getEnvironment().getProperty(COMMON_NOT_NULL), "username");
		
		Optional<Category> isPresentCategory = categoryRepository.findById(Long.parseLong(commonUpdateResource.getId()));
		if (!isPresentCategory.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
		
		if(!isPresentCategory.get().getVersion().equals(Long.parseLong(commonUpdateResource.getVersion())))
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "version");
		
		Optional<Category> isPresentCategoryName = categoryRepository.findByName(commonUpdateResource.getName());
		if (isPresentCategoryName.isPresent() && isPresentCategoryName.get().getId() != isPresentCategory.get().getId())			
			throw new ValidateRecordException(environment.getProperty("common.unique"), "name");
		
		Category category = isPresentCategory.get();
		category.setName(commonUpdateResource.getName());
		category.setStatus(CommonStatus.valueOf(commonUpdateResource.getStatus()));
		category.setModifiedDate(currentTimestamp);
		category.setModifiedUser(LogginAuthentcation.getInstance().getUserName());
		category = categoryRepository.saveAndFlush(category);
    	return category;
	}

	@Override
	public void deleteCategory(Long id) {
		Optional<Category> isPresentCategory = categoryRepository.findById(id);
		if (!isPresentCategory.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
		else
			categoryRepository.deleteById(id);	
	}
	
}
