package com.element.fleet.ordering.enums;

public enum OrderingBOQueuesDIOPageEnum {
	
	ORDERING_BO_QUEQUES_DIO_SUBTITLE_EXPECTED("- Dealer Installed Options Queue"),
	;
	private String value;

	private OrderingBOQueuesDIOPageEnum(String value) {
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
