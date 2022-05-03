package com.element.fleet.ordering.enums;

public enum OrderingBOFleetAssigmentPageEnum {
	ORDERING_BO_FA_SUBTITLEEXPECTED("Fleet Assignment"),
	ORDERING_BO_FA_SUBSCREENSUBTITLEEXPECTED("Fleet Assignment Maintenance"),
	;
	private String value;

	private OrderingBOFleetAssigmentPageEnum(String value) {
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
