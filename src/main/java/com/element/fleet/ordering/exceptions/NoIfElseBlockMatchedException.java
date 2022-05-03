package com.element.fleet.ordering.exceptions;

public class NoIfElseBlockMatchedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE = "No if block satisfys the condition";
	
	public NoIfElseBlockMatchedException() {
		super(ERROR_MESSAGE);
    }
	
	public NoIfElseBlockMatchedException(String errorMessage) {
		super(errorMessage);
    }
}
