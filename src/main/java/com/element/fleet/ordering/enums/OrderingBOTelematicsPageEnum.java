package com.element.fleet.ordering.enums;

public enum OrderingBOTelematicsPageEnum {
	
	ORERING_BO_TELEMATICS_FIRST_OPTION_XPATH("//table[@id='queue-table']//tr[@class='odd']//input[1]"),
	ORERING_BO_TELEMATICS_RELEASE_BUTTON_XPATH("//span[contains(text(),'Release')]"),
	ORERING_BO_TELEMATICS_LOGNUMBER_XPATH("//input[@name='logNo']"),
	ORERING_BO_TELEMATICS_SEARCH_BUTTON_XPATH("//span[@class='fa fa-search']"),
	;
	private String value;

	private OrderingBOTelematicsPageEnum(String value) {
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
