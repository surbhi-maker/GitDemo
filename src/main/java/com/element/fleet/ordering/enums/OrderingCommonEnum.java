package com.element.fleet.ordering.enums;

public enum OrderingCommonEnum {
	
	ORDERING_TEXT_XPATH("//span[contains(text(),'Ordering')]"),
	;	
	
	private String value;
	
	OrderingCommonEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
	
}
