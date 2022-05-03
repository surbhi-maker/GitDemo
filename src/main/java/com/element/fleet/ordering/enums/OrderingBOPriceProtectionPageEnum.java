package com.element.fleet.ordering.enums;

public enum OrderingBOPriceProtectionPageEnum {

	ORDERING_BO_BMT_PP_SEARCH_FIELD_ID("standard"),
	ORDERING_BO_BMT_PP_SUBTITLEEXPECTED("Price Protection"),
	ORDERING_BO_BMT_PP_SUBSCREENSUBTITLEEXPECTED("Price Protection"),
	;
	
	private String value;

	private OrderingBOPriceProtectionPageEnum(String value) {
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
