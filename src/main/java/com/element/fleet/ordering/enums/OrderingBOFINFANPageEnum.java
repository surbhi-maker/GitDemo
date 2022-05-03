package com.element.fleet.ordering.enums;

public enum OrderingBOFINFANPageEnum {
	
	ORDERING_BO_BMT_FINFAN_SEARCH_FIELD_ID("standard"),
	ORDERING_BO_BMT_FINFAN_SUBTITLEEXPECTED("FIN/FAN Codes"),
	ORDERING_BO_BMT_FINFAN_SUBSCREENSUBTITLEEXPECTED("FIN/FAN Codes"),
	;
	
	private String value;

	private OrderingBOFINFANPageEnum(String value) {
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
