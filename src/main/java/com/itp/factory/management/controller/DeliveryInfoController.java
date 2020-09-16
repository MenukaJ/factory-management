package com.itp.factory.management.controller;

import java.io.FileNotFoundException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.domain.DeliveryInfo;
import com.itp.factory.management.resource.DeliveryInfoAddResource;
import com.itp.factory.management.resource.DeliveryInfoUpdateResource;
import com.itp.factory.management.resource.SuccessAndErrorDetailsResource;
import com.itp.factory.management.service.DeliveryInfoService;

import net.sf.jasperreports.engine.JRException;

/**
 * Delivery_Info Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   06-09-2020                            Anushka        Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping("/delivery")
public class DeliveryInfoController extends MessagePropertyBase {

	@Autowired
	private DeliveryInfoService deliveryInfoService;
	
	//get all DeliveriInfo
	@GetMapping(value ="/all")
	public @ResponseBody ResponseEntity<Object> getAllDeliveryInfo(){
			SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
			List<DeliveryInfo>isDelivery = deliveryInfoService.getAll();
			if(!isDelivery.isEmpty()) {
				return new ResponseEntity<>((Collection<DeliveryInfo>)isDelivery,HttpStatus.OK);
			}else {
				responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
				return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
			}
			
	}
	
	/**
	 * get delivery info by ID
	 * @param @pathVariable {id}
	 * @return Optional
	 **/
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Object> getDeliveryInfoById(@PathVariable(value = "id", required = true) Long id){
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<DeliveryInfo> isDelivery = deliveryInfoService.getById(id);
		if(isDelivery.isPresent()) {
			return new ResponseEntity<>(isDelivery.get(), HttpStatus.OK);
		}
		else {
			responseMessage.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
     * save Delivery info
     * @param @RequestBody{SupplierAddResource}
     * @return SuccessAndErrorDetailsDto
     */
	@PostMapping("/add")
	public ResponseEntity<Object> addDeliveriInfo(@Valid @RequestBody DeliveryInfoAddResource deliveryInfoAddResource){
		DeliveryInfo deliveryInfo = deliveryInfoService.addDelivery(deliveryInfoAddResource);
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_CREATED), Long.toString(deliveryInfo.getId()));
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}
	
	/**
     * update Supplier
     * @param @RequestBody{SupplierUpdateResource}
     * @return SuccessAndErrorDetailsDto
     */
	@PutMapping(value = "update/{id}")
	public ResponseEntity<Object> updateDeliveryInfo(@PathVariable(value = "id", required = true) Long id, 
		@Valid @RequestBody DeliveryInfoUpdateResource deliveryInfoUpdateResource){
		SuccessAndErrorDetailsResource successAndErrorDetailsResource=new SuccessAndErrorDetailsResource();
		Optional<DeliveryInfo>isDelivery = deliveryInfoService.getById(id);		
		if(isDelivery.isPresent()) {
			deliveryInfoUpdateResource.setId(id.toString());;
			DeliveryInfo deliveryInfo = deliveryInfoService.updateDelivery(deliveryInfoUpdateResource);
			successAndErrorDetailsResource = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_UPDATED), Long.toString(deliveryInfo.getId()));
			return new ResponseEntity<>(successAndErrorDetailsResource,HttpStatus.OK);
		}
		else {
			successAndErrorDetailsResource.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	/**
	 * delete Delivery Info
	 * @param @pathVariable {id}
	 **/
	@DeleteMapping(value = "/id/{id}")
	public ResponseEntity<Object> deleteDeliveryInfoById(@PathVariable(value = "id", required = true) Long id){
		SuccessAndErrorDetailsResource successAndErrorDetailsResource=new SuccessAndErrorDetailsResource();
		Optional<DeliveryInfo>isDelivery = deliveryInfoService.getById(id);		
		if(isDelivery.isPresent()) {
			deliveryInfoService.deleteDelivery(id);
			successAndErrorDetailsResource = new SuccessAndErrorDetailsResource(getEnvironment().getProperty(RECORD_DELETED), id.toString());
			return new ResponseEntity<>(successAndErrorDetailsResource,HttpStatus.OK);
		}
		else {
			successAndErrorDetailsResource.setMessages(getEnvironment().getProperty(RECORD_NOT_FOUND));
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	

	/**
	 * Generate Delivery Report
	 * @param @pathVariable {format}
	 **/
	
	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return deliveryInfoService.exportReport(format);
	}
	
	
	
}
