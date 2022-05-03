package com.element.fleet.ordering.enums;

public enum OrderingBORFQPageEnum {
	
	ORERING_BO_RFQ_SUBTITLE_EXPECTED("- RFQ"),
	;
	private String value;

	private OrderingBORFQPageEnum(String value) {
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
