package com.element.fleet.ordering.enums;

public enum OrderingFOPriceAndConfigPageEnum {

	ORDERING_PRICECONFIG_PRICECONFIGSTATUSARCHIVED_XPATH("//td[contains(text(),'Archived')]"),
	ORDERING_PRICECONFIG_PRICECONFIGSAVEDSTATUS_XPATH("//td[contains(text(),'Price Config Saved')]"),
	;
	
	private String value;

	OrderingFOPriceAndConfigPageEnum(String value) {
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
