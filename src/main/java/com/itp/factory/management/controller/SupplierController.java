package com.itp.factory.management.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.domain.Supplier;
import com.itp.factory.management.resource.SuccessAndErrorDetailsResource;
import com.itp.factory.management.resource.SupplierAddResource;
import com.itp.factory.management.resource.SupplierUpdateResource;
import com.itp.factory.management.service.SupplierService;

/**
 * Supplier Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   06-09-2020                            Anushka        Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping("/suppliers")
public class SupplierController extends MessagePropertyBase{
	@Autowired
	private SupplierService supplierService;
	
	//get all Suppliers
	@GetMapping(value ="/all")
	public ResponseEntity<Object> getAllSuppliers(){
			SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
			List<Supplier>isPresentSupplier = supplierService.getAll();
			if(!isPresentSupplier.isEmpty()) {
				return new ResponseEntity<>((Collection<Supplier>)isPresentSupplier,HttpStatus.OK);
			}else {
				responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
				return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
			}
			
	}
	/**
	 * get supplier by ID
	 * @param @pathVariable {id}
	 * @return Optional
	 **/
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Object> getSupplierById(@PathVariable(value = "id", required = true) Long id){
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Supplier> isPresentSupplier = supplierService.getById(id);
		if(isPresentSupplier.isPresent()) {
			return new ResponseEntity<>(isPresentSupplier.get(), HttpStatus.OK);
		}
		else {
			responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	/**
	 * get Supplier by name
	 * @param @pathVariable {name}
	 * @return List
	 **/
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getSupplierByName(@PathVariable(value = "name", required = true) String name){
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Supplier> isPresentSupplier = supplierService.getByName(name);
		if(isPresentSupplier.isPresent()) {
			return new ResponseEntity<>(isPresentSupplier.get(), HttpStatus.OK);
		}
		else {
			responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	/**
     * save Supplier
     * @param @RequestBody{SupplierAddResource}
     * @return SuccessAndErrorDetailsDto
     */
	@PostMapping("/add")
	public ResponseEntity<Object> addSupplier(@Valid @RequestBody SupplierAddResource supplierAddResource){
		Supplier Supplier = supplierService.addSupplier(supplierAddResource);
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_CREATED), Long.toString(Supplier.getId()));
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}
	
	/**
     * update Supplier
     * @param @RequestBody{SupplierUpdateResource}
     * @return SuccessAndErrorDetailsDto
     */
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Object> updateSupplier(@PathVariable(value = "id", required = true) Long id, 
		@Valid @RequestBody SupplierUpdateResource supplierUpdateResource){
		SuccessAndErrorDetailsResource successAndErrorDetailsResource=new SuccessAndErrorDetailsResource();
		Optional<Supplier>isPresentSupplier = supplierService.getById(id);		
		if(isPresentSupplier.isPresent()) {
			supplierUpdateResource.setId(id.toString());
			Supplier Supplier = supplierService.updateSupplier(supplierUpdateResource);
			successAndErrorDetailsResource = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_UPDATED), Long.toString(Supplier.getId()));
			return new ResponseEntity<>(successAndErrorDetailsResource,HttpStatus.OK);
		}
		else {
			successAndErrorDetailsResource.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	/**
	 * delete supplier by ID
	 * @param @pathVariable {id}
	 **/
	@DeleteMapping(value = "/id/{id}")
	public ResponseEntity<Object> deleteSupplierById(@PathVariable(value = "id", required = true) Long id){
		SuccessAndErrorDetailsResource successAndErrorDetailsResource=new SuccessAndErrorDetailsResource();
		Optional<Supplier>isPresentSupplier = supplierService.getById(id);		
		if(isPresentSupplier.isPresent()) {
			supplierService.deleteSupplier(id);
			successAndErrorDetailsResource = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_DELETED), id.toString());
			return new ResponseEntity<>(successAndErrorDetailsResource,HttpStatus.OK);
		}
		else {
			successAndErrorDetailsResource.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	
}
