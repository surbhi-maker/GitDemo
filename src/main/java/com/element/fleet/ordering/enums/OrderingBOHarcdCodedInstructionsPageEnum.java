package com.element.fleet.ordering.enums;

public enum OrderingBOHarcdCodedInstructionsPageEnum {
	
	ORDERING_BO_HCI_SUBTITLEEXPECTED("Hard-coded Instructions"),
	ORDERING_BO_HCI_SUBSCREENSUBTITLEEXPECTED("Hard-coded Instructions"),	
    ;
	
	private String value;

	private OrderingBOHarcdCodedInstructionsPageEnum(String value) {
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
