/*
 * Copyright (c) 2018. LOLC Technology Services Ltd.
 * Author: Ranjith Kodikara
 * Date: 12/12/18 10:45
 */

package com.itp.factory.management.core;

import java.lang.reflect.Field;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.itp.factory.management.exception.CodeCannotChangeException;
import com.itp.factory.management.exception.CodeUniqueException;
import com.itp.factory.management.exception.NoRecordFoundException;
import com.itp.factory.management.exception.OtherCommonException;
import com.itp.factory.management.exception.OtherException;
import com.itp.factory.management.exception.TenantNotFoundException;
import com.itp.factory.management.exception.UserNotFound;
import com.itp.factory.management.exception.ValidateRecordException;
import com.itp.factory.management.resource.CategoryAddResource;
import com.itp.factory.management.resource.CategoryUpdateResource;
import com.itp.factory.management.resource.SuccessAndErrorDetailsResource;
import com.itp.factory.management.resource.ValidateResource;




/**
 * 
 * This will return the relevant object based on the caught exception
 * 
 * @author menukaj
 * @since 2018-12-13 s
 * @version 1.0
 * 
 */
@RestControllerAdvice
public class BaseResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger_bre = LoggerFactory.getLogger(BaseResponseEntityExceptionHandler.class);
	private String tenantNotFound="common.tenant-not-found";
	private String commonError="common.error";
	private String eventIdNotFound = "eventIdNotFound";
	private String commonInternalServerError="common.internal-server-error";
	private String userNotFound="common.user-not-found";
	
	@Autowired
	private Environment environment;

	
	@Override 
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) { 
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty("common.invalid-url-pattern"));
		successAndErrorDetailsDataObject.setDetails(ex.getMessage());
		//TenantHolder.clear();
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.NOT_FOUND);
	}
//	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty("common.argument-type-mismatch"));
		successAndErrorDetailsDataObject.setDetails(ex.getMessage());
		//TenantHolder.clear();
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({TenantNotFoundException.class})
	public ResponseEntity<Object> tenantNotFoundException(TenantNotFoundException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty(tenantNotFound));
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	@ExceptionHandler({UserNotFound.class})
	public ResponseEntity<Object> userNotFoundException(UserNotFound ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsResource = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsResource.setMessages(environment.getProperty(userNotFound));
		return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	@ExceptionHandler({CannotCreateTransactionException.class})
	public ResponseEntity<Object> cannotCreateTransactionException(CannotCreateTransactionException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty(tenantNotFound));
		//TenantHolder.clear();
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler({CodeUniqueException.class})
	public ResponseEntity<Object> codeUniqueException(CodeUniqueException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty("common.code-duplicate"));
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler({NoRecordFoundException.class})
	public ResponseEntity<Object> noRecordFoundException(NoRecordFoundException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty("common.record-not-found"));
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler({CodeCannotChangeException.class})
	public ResponseEntity<Object> codeCannotChangeException(CodeCannotChangeException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty("common.code-cannot-change"));
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler({OtherCommonException.class})
	public ResponseEntity<Object> otherCommonException(OtherCommonException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(ex.getMessage());
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler({OtherException.class})
	public ResponseEntity<Object> otherException(OtherException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(ex.getMessage());
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler({ValidateRecordException.class})
	public ResponseEntity<Object> validateRecordException(ValidateRecordException ex, WebRequest request) {
		try {
		ValidateResource typeValidation = new ValidateResource();
		Class validationDetailClass = typeValidation.getClass();
		Field sField = validationDetailClass.getDeclaredField(ex.getField());
		sField.setAccessible(true);
		sField.set(typeValidation, ex.getMessage());
		//TenantHolder.clear();
		return new ResponseEntity<>(typeValidation, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e) {
			
			logger_bre.error(MessageFormat.format(environment.getProperty("common.error"), this.getClass().getSimpleName()).concat(e.getMessage()));
			SuccessAndErrorDetailsResource successAndErrorDetailsDto = new SuccessAndErrorDetailsResource();
			successAndErrorDetailsDto.setMessages(environment.getProperty("common.internal-server-error"));
			successAndErrorDetailsDto.setDetails(e.getMessage());
			//TenantHolder.clear();
			return new ResponseEntity<>(successAndErrorDetailsDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override 
	 protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) { 
		
		try {
			 //TenantHolder.clear();
			 Field sField=null;
			 String fieldName=null;
			 Integer index=null;
			 Integer atPoint=null;
			 Integer index1=null;
			 Integer atPoint1=null;
			String className=ex.getBindingResult().getObjectName();
			switch(className){ 
        		
        	case "categoryAddResource": 
        		CategoryAddResource  categoryAddResource = new CategoryAddResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField =  categoryAddResource.getClass().getDeclaredField(error.getField());
		            sField.setAccessible(true);
		            sField.set(categoryAddResource.getClass().cast(categoryAddResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(categoryAddResource, HttpStatus.UNPROCESSABLE_ENTITY);
        	case "categoryUpdateResource": 
        		CategoryUpdateResource categoryUpdateResource = new CategoryUpdateResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					fieldName=error.getField();
                            sField =  categoryUpdateResource.getClass().getDeclaredField(error.getField());
                            sField.setAccessible(true);
                            sField.set(categoryUpdateResource, error.getDefaultMessage());
				}
				return new ResponseEntity<>(categoryUpdateResource, HttpStatus.UNPROCESSABLE_ENTITY);
				
//        	case "eligibilityAddResource": 
//        		EligibilityAddResource  eligibilityAddResource = new EligibilityAddResource();
//				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//					sField =  eligibilityAddResource.getClass().getDeclaredField(error.getField());
//		            sField.setAccessible(true);
//		            sField.set(eligibilityAddResource.getClass().cast(eligibilityAddResource), error.getDefaultMessage());
//				}
//				return new ResponseEntity<>(eligibilityAddResource, HttpStatus.UNPROCESSABLE_ENTITY);
//        	case "eligibilityUpdateResource": 
//        		EligibilityUpdateResource eligibilityUpdateResource = new EligibilityUpdateResource();
//				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//					fieldName=error.getField();
//                            sField =  eligibilityUpdateResource.getClass().getDeclaredField(error.getField());
//                            sField.setAccessible(true);
//                            sField.set(eligibilityUpdateResource, error.getDefaultMessage());
//				}
//				return new ResponseEntity<>(eligibilityUpdateResource, HttpStatus.UNPROCESSABLE_ENTITY);
//								
//				//END by SahanAm for FX-3958/FX-4074 on 03-07-2020 
//				
	        	default:   
	        		return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
	        }
		} catch (Exception e) {
			LoggerRequest.getInstance().logCommonError(e.getMessage());
			SuccessAndErrorDetailsResource successAndErrorDetailsResource = new SuccessAndErrorDetailsResource();
			successAndErrorDetailsResource.setMessages(environment.getProperty(commonInternalServerError));
			successAndErrorDetailsResource.setDetails(e.getMessage());
			//TenantHolder.clear();
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }				

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> exception(Exception ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		logger_bre.error(MessageFormat.format(environment.getProperty(commonError), this.getClass().getSimpleName()).concat(ex.getMessage()));
		successAndErrorDetailsDataObject.setMessages(environment.getProperty(commonInternalServerError));
		successAndErrorDetailsDataObject.setDetails(ex.getMessage());
		//TenantHolder.clear();
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.INTERNAL_SERVER_ERROR);
	}
//	
//	@ExceptionHandler({InvalidServiceIdException.class})
//	public ResponseEntity<Object> invalidServiceIdException(InvalidServiceIdException ex, WebRequest request) {
//		ValidateResource validateResource=new ValidateResource();
//		/*RecoveryNotificationsResource notificationsResource=null;
//		RecoveryNotificationsDetailResource notificationsDetailResource=null;
//		*/
//		switch(ex.getServiceEntity()) 
//        { 
//            		
//        /*	case RECOVERY_NOTIFICATIONS:
//        		notificationsResource=new RecoveryNotificationsResource();
//        		notificationsResource.setNotificationTypeId(ex.getMessage());
//        		break;
//        	case ALERT_TYPES:
//        		notificationsResource=new RecoveryNotificationsResource();
//        		notificationsResource.setAlertTypeId(ex.getMessage());
//        		break;
//        	case PERIOD:
//        		notificationsResource=new RecoveryNotificationsResource();
//        		notificationsResource.setPeriodId(ex.getMessage());
//        		break;
//        	case MODULEPRIORITY:
//        		notificationsResource=new RecoveryNotificationsResource();
//        		notificationsResource.setModulePriority(ex.getMessage());
//        		break;
//        	case SUB_PRODUCT_CODE:
//        		notificationsDetailResource=new RecoveryNotificationsDetailResource();
//        		notificationsDetailResource.setSubProductId(ex.getMessage());
//        		break;
//        		*/
//            default: 
// 
//        }
//		// ain karapan
//		return null; 
//		
//		/*if(notificationsResource!=null) {
//			return new ResponseEntity<>(notificationsResource, HttpStatus.UNPROCESSABLE_ENTITY);
//		}else {
//			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
//		}*/
//		
//    }
//	
//	
//	@ExceptionHandler({OptimisticLockException.class})
//	public ResponseEntity<Object> optimisticLockException(OptimisticLockException ex, WebRequest request) {
//		try {
//			ValidateResource typeValidation = new ValidateResource();
//			
//			Class validationDetailClass = typeValidation.getClass();
//			Field sField = validationDetailClass.getDeclaredField(ex.getField());
//			sField.setAccessible(true);
//			sField.set(typeValidation, ex.getMessage());
//			
//			//TenantHolder.clear();
//			return new ResponseEntity<>(typeValidation, HttpStatus.UNPROCESSABLE_ENTITY);
//		} catch (Exception e) {
//			logger.error(MessageFormat.format(environment.getProperty("common.error"), this.getClass().getSimpleName()).concat(e.getMessage()));
//			
//			CommonResponseHandler successAndErrorDetailsDto = new CommonResponseHandler();
//			successAndErrorDetailsDto.setMessages(environment.getProperty("common.internal-server-error"));
//			successAndErrorDetailsDto.setDetails(e.getMessage());
//			
//			//TenantHolder.clear();
//			return new ResponseEntity<>(successAndErrorDetailsDto, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	/*
//	protected RecoveryNotificationsResource addRecoveryNotificationsValidation(List<FieldError> fieldErrors) throws NoSuchFieldException, IllegalAccessException{
//		Field sField=null;
//		RecoveryNotificationsResource addNotesRequest=new RecoveryNotificationsResource();
//		
//		for (FieldError error : fieldErrors) {
//			if(error.getField().startsWith("createdUser")) {
//				 sField =  addNotesRequest.getClass().getDeclaredField(error.getField());
//				 sField.setAccessible(true);
//				 sField.set(addNotesRequest, error.getDefaultMessage());
//			 }else {
//				 sField =  addNotesRequest.getClass().getSuperclass().getDeclaredField(error.getField());
//				 sField.setAccessible(true);
//				 sField.set(addNotesRequest.getClass().getSuperclass().cast(addNotesRequest), error.getDefaultMessage());
//			 }
//		 }
//		 
//		return addNotesRequest;
//	}
//	*/
}
