package com.element.fleet.ordering.enums;

public enum OrderingBOStockPageEnum {
	
	ORDERING_BO_QUEUES_STOCK_SUBTITLE_EXPECTED("- Stock"),
	;
	
	private String value;

	private OrderingBOStockPageEnum(String value) {
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
