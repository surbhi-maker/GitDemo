package com.element.fleet.ordering.enums;

public enum OrderingBOSweeperAdminPageEnum {
	
	ORDERING_BO_SWEEPER_ADMIN_SUBTITLE_EXPECTED("- Sweeper Admin"),
	;
	
	private String value;

	private OrderingBOSweeperAdminPageEnum(String value) {
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
