package com.element.fleet.ordering.enums;

public enum OrderingBODriverChangePageEnum {
	
	ORDERING_BO_DRIVER_CHANGE_SUBTITLE_EXPECTED("- Driver Change"),
	;
	private String value;

	private OrderingBODriverChangePageEnum(String value) {
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
