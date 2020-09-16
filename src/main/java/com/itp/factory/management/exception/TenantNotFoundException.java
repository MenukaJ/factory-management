package com.itp.factory.management.exception;

public class TenantNotFoundException extends RuntimeException{
	public TenantNotFoundException(String exception) {
		super(exception);
	}
}
