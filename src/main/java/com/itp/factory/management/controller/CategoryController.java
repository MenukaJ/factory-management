package com.itp.factory.management.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.domain.Category;
import com.itp.factory.management.enums.CommonStatus;
import com.itp.factory.management.exception.ValidateRecordException;
import com.itp.factory.management.resource.CategoryAddResource;
import com.itp.factory.management.resource.CategoryUpdateResource;
import com.itp.factory.management.resource.SuccessAndErrorDetailsResource;
import com.itp.factory.management.service.CategoryService;
	

/**
 * Category Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020                             MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/category")
@CrossOrigin(origins = "*")
public class CategoryController extends MessagePropertyBase {
	
	@Autowired
	private CategoryService categoryService;
	
	/*
	 * get all category
	 * @param @PathVariable{tenantId}
	 * @param @PathVariable{all}
	 * @return List
	 **/
	@GetMapping("/all")
	public ResponseEntity<Object> getAllCategory(){
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Category>isPresentCategory = categoryService.getAll();
		if(!isPresentCategory.isEmpty()) {
			return new ResponseEntity<>((Collection<Category>)isPresentCategory,HttpStatus.OK);
		}
		else {
			responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * get category by ID
	 * @param @pathVariable{tenantId}
	 * @param @pathVariable {id}
	 * @return Optional
	 **/
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Object> getCategoryById(@PathVariable(value = "id", required = true) Long id){
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Category> isPresentCategory = categoryService.getById(id);
		if(isPresentCategory.isPresent()) {
			return new ResponseEntity<>(isPresentCategory.get(), HttpStatus.OK);
		}
		else {
			responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * get category by name
	 * @param @pathVariable{tenantId}
	 * @param @pathVariable {name}
	 * @return List
	 **/
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getCategoryByName(@PathVariable(value = "name", required = true) String name){
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Category> isPresentCategory = categoryService.getByName(name);
		if(isPresentCategory.isPresent()) {
			return new ResponseEntity<>(isPresentCategory.get(), HttpStatus.OK);
		}
		else {
			responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * get category by status
	 * @param @pathVariable{tenantId}
	 * @param @pathVariable {status}
	 * @return List
	 **/
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getCategoryByStatus(@PathVariable(value = "status", required = true) String status){
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		if(status.equals(CommonStatus.ACTIVE.toString()) || status.equals(CommonStatus.INACTIVE.toString())) {
			List<Category> isPresentCategory = categoryService.getByStatus(status);
			if(!isPresentCategory.isEmpty()) {
				return new ResponseEntity<>(isPresentCategory, HttpStatus.OK);
			}
			else {
				responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
				return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
			}
		}
		else {
			responseMessage.setMessages(RECORD_NOT_FOUND);
	        throw new ValidateRecordException(environment.getProperty("common-status.pattern"), "message");
		}
	}
	
	/**
     * save category
     * @param @PathVariable{tenantId}
     * @param @RequestBody{CategoryAddResource}
     * @return SuccessAndErrorDetailsDto
     */
	@PostMapping("/add")
	public ResponseEntity<Object> addCategory(@Valid @RequestBody CategoryAddResource categoryAddResource){
		Category Category = categoryService.addCategory(categoryAddResource);
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_CREATED), Long.toString(Category.getId()));
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}
	
	/**
     * update category
     * @param @PathVariable{tenantId}
     * @param @RequestBody{CategoryUpdateResource}
     * @return SuccessAndErrorDetailsDto
     */
	@PutMapping(value = "update/{id}")
	public ResponseEntity<Object> updateCategory(@PathVariable(value = "id", required = true) Long id, 
												                 @Valid @RequestBody CategoryUpdateResource categoryUpdateResource){
		SuccessAndErrorDetailsResource successAndErrorDetailsResource=new SuccessAndErrorDetailsResource();
		Optional<Category>isPresentCategory = categoryService.getById(id);		
		if(isPresentCategory.isPresent()) {
			categoryUpdateResource.setId(id.toString());
			Category Category = categoryService.updateCategory(categoryUpdateResource);
			successAndErrorDetailsResource = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_UPDATED), Category.getId().toString());
			return new ResponseEntity<>(successAndErrorDetailsResource,HttpStatus.OK);
		}
		else {
			successAndErrorDetailsResource.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	/**
	 * get category by ID
	 * @param @pathVariable{tenantId}
	 * @param @pathVariable {id}
	 **/
	@DeleteMapping(value = "/id/{id}")
	public ResponseEntity<Object> deleteCategoryById(@PathVariable(value = "id", required = true) Long id){
		SuccessAndErrorDetailsResource successAndErrorDetailsResource=new SuccessAndErrorDetailsResource();
		Optional<Category>isPresentCategory = categoryService.getById(id);		
		if(isPresentCategory.isPresent()) {
			categoryService.deleteCategory(id);
			successAndErrorDetailsResource = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_DELETED), id.toString());
			return new ResponseEntity<>(successAndErrorDetailsResource,HttpStatus.OK);
		}
		else {
			successAndErrorDetailsResource.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
}
