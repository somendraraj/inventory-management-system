package com.cleartrip.exception;

public class ApiException extends Exception {

	private static final long serialVersionUID = -4029391352636156853L;
	
	private String errors;

	public ApiException() {
		super("Items not found...into DB...");
	}

	public ApiException(String msg, String cause) {
		super(msg);
		this.errors = cause;
	}

	public String getErrors() {
		return this.errors;
	}
}
