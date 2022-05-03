package com.element.fleet.ordering.enums;

public enum OrderingBOElementFINFANCodesPageEnum {
	
	ORDERING_BO_BMT_ELEMENTFINFAN_SUBTITLEEXPECTED("Element FIN/FAN Codes"),
	ORDERING_BO_BMT_ELEMENTFINFAN_SUBSCREENSUBTITLEEXPECTED("Element FIN/FAN Codes"),
	;
	
	private String value;

	OrderingBOElementFINFANCodesPageEnum(String value) {
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
