package com.element.fleet.ordering.enums;

public enum OrderingBOFactoryOrderNumberPageEnum {
	
	ORDERING_BO_BMT_FON_SEARCH_FIELD_ID("standard"),
	ORDERING_BO_BMT_FON_SUBTITLEEXPECTED("Factory Order Number Range Table"),
	ORDERING_BO_BMT_FON_SUBSCREENSUBTITLEEXPECTED("Factory Order Range Table"),
	;
	
	private String value;

	private OrderingBOFactoryOrderNumberPageEnum(String value) {
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
