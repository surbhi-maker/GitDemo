package com.element.fleet.ordering.enums;

public enum OrderingBOTitleAndRegistrationPageEnum {
	
	ORDERING_BO_TITLEANDREGISTRATION_SUBTITLE_EXPECTED("- Title & Registration"),
	;
	
	private String value;

	private OrderingBOTitleAndRegistrationPageEnum(String value) {
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
