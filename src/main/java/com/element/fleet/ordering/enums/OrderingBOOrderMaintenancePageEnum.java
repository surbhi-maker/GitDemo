package com.element.fleet.ordering.enums;

public enum OrderingBOOrderMaintenancePageEnum {

	ORDERING_BO_OM_TAB_CONTAINER_CLASS("tabs-container")
	;

	private String value;

	private OrderingBOOrderMaintenancePageEnum(String value) {
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
