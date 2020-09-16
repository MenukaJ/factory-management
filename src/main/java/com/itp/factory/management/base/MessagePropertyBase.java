package com.itp.factory.management.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public abstract class MessagePropertyBase {
	
	@Autowired
	protected Environment environment;

	public Environment getEnvironment() {
		return environment;
	}
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	/** Common Properties */
	protected static final String RECORD_CREATED = "common.saved";
	protected static final String RECORD_UPDATED = "common.updated";
	protected static final String RECORD_DELETED = "common.deleted";
	public static final String RECORD_NOT_FOUND = "common.record-not-found";
	public static final String TENANT_NOT_FOUND = "common.tenantNotFound";
	public static final String COMMON_INTERNAL_SERVER_ERROR = "common.internal-server-error";
	public static final String COMMON_ERROR = "common.error";
	public static final String COMMON_DUPLICATE = "common.duplicate";
	protected static final String COMMON_STATUS_PATTERN = "common.status-pattern";
	protected static final String COMMON_STATUS_NOT_NULL = "common.status-not-null";
	protected static final String COMMON_CREATEDUSER_NOT_NULL = "common.createduser-not-null";
	protected static final String COMMON_MODIFIEDUSER_NOT_NULL = "common.modifieduser-not-null";
	protected static final String COMMON_NOT_NULL = "common.not-null";
	protected static final String COMMON_NUMERIC_PATTERN = "common.numeric-pattern";
	protected static final String COMMON_INVALID_VALUE = "common.invalid-value";
	protected static final String BAD_REQUEST = "common.bad-request";
	protected static final String SERVICE_NOT_AVAILABLE = "common-service.not-available";
	protected static final String CHILD_RECORD = "common-child-record.available";
	protected static final String CHILD_RECORD_STATUS = "common-child-record-status.available";
	protected static final String PAGEABLE_LENGTH = "common.pageable-length";
	protected static final String NO_RECORD_TO_SAVED = "common.no-record";
	protected static final String INVALID_VERSION = "common-invalid.version";
	
	/** JSON Properties */
	
	
	/** Message Properties */
	

}
