package com.element.fleet.ordering.exceptions;

public class OrderingErrorOccured extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE = "Environmental error occured";
	
	public OrderingErrorOccured() {
		super(ERROR_MESSAGE);
    }
	
	public OrderingErrorOccured(String errorMessage) {
		super(errorMessage);
    }
}
