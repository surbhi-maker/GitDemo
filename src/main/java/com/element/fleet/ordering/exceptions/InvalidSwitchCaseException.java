package com.element.fleet.ordering.exceptions;

public class InvalidSwitchCaseException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE = "Invalid case entered";
	
	public InvalidSwitchCaseException() {
		super(ERROR_MESSAGE);
    }
	
	public InvalidSwitchCaseException(String errorMessage) {
		super(errorMessage);
    }
}
