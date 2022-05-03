package com.element.fleet.ordering.enums;

public enum OrderingBOPOStatusPageEnum {
	
	ORDERING_BO_BMT_POS_SEARCH_FIELD_ID("standard"),
	;
	
	private String value;

	private OrderingBOPOStatusPageEnum(String value) {
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
