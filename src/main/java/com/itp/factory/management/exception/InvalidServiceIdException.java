package com.itp.factory.management.exception;

import com.itp.factory.management.enums.ServiceEntity;

public class InvalidServiceIdException extends RuntimeException {

	private final ServiceEntity serviceEntity;
	
	public InvalidServiceIdException(String exception,ServiceEntity serviceEntity) {
		super(exception);
		this.serviceEntity=serviceEntity;
	}
	
	public ServiceEntity getServiceEntity() {
		return serviceEntity;
	}
}
