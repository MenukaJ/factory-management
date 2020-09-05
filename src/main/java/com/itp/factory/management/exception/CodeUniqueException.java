package com.itp.factory.management.exception;

import com.itp.factory.management.enums.CodeType;

public class CodeUniqueException extends RuntimeException{
	
	private static final long serialVersionUID = 1213132313L;
	private final CodeType codeType;
	
	/*public CodeUniqueException (String exception) {
		super(exception);
	}*/
	
	
	public CodeUniqueException(String exception, CodeType codeType) {
		super(exception);
		this.codeType=codeType;
	}
	
	public CodeType getCodeType() {
		return codeType;
	}

}
