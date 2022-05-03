package com.element.fleet.ordering.enums;

public enum OrderingBOUpfitterPreffPageEnum {
	
	Ordering_BO_UP_UpfitterPreffTxt_Expected("Upfitter Preferences"),
	Ordering_BO_UP_UpfitterPreffMantienanceTxt_Expected("Upfitter Preferences Maintenance"),
	;
	
	private String value;

	private OrderingBOUpfitterPreffPageEnum(String value) {
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
