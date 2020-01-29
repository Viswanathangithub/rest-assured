package com.automation.api.exception;

public class ApiException extends RuntimeException {

	/**
	 * API Exception - User Defined Exception
	 * 
	 */
	private static final long serialVersionUID = 24L;

	public ApiException(Throwable clause) {
		super(clause);
	}

	public ApiException(String message) {
		super(message);
	}

}
