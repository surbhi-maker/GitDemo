package com.element.fleet.ordering.enums;

public enum OrderingBOYearMakeModelPageEnum {
	
	ORDERING_BO_BMT_YYM_SEARCH_FIELD_ID("standard"),
	Ordering_BO_BMT_YYM_YearMakeModelTxt_Expected("Year Make Model"),
	Ordering_BO_BMT_YYM_YearMakeModelMantienanceTxt_Expected("Year Make Model"),
	;
	
	private String value;

	private OrderingBOYearMakeModelPageEnum(String value) {
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
