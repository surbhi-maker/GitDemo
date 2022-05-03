package com.element.fleet.ordering.enums;

public enum OrderingBOMassUpdatesPageEnum {
	
	ORDERING_BO_MU_MASS_ORDERS_TABLE_ID("mass-orders"),
	ORDERING_BO_MU_LABELTITLE_XPATH("//div[@class='panel-heading']//following::h3[@class='panel-title']"),
	ORDERING_BO_MU_LABEL_TITLE_EXPECTED("Mass Orders"),
	;
	
	private String value;

	private OrderingBOMassUpdatesPageEnum(String value) {
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

