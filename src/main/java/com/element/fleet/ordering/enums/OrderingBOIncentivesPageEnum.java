package com.element.fleet.ordering.enums;

public enum OrderingBOIncentivesPageEnum {
	
	ORDERING_BO_BMT_INCENTIVES_SEARCH_FIELD_ID("standard"),
	ORDERING_BO_BMT_INCENTIVES_SUBTITLEEXPECTED("Incentives"),
	ORDERING_BO_BMT_INCENTIVES_SUBSCREENSUBTITLEEXPECTED("Incentive Table"),	
	;
	
	private String value;

	OrderingBOIncentivesPageEnum(String value) {
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
