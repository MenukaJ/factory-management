package com.itp.factory.management.exception;

public class CodeCannotChangeException extends RuntimeException {

	private static final long serialVersionUID = 6995L;

	public CodeCannotChangeException (String exception) {
		super(exception);
	}
}
