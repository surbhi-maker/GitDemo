package com.element.fleet.ordering.enums;

public enum OrderingBOOrderTransmissionQueuePageEnum {
	
	ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH("//td[@data-name='logNumber']//span"),
	ORDERING_ORDER_TRANSMISSION_SEARCH_FIELD_XPATH("//label[contains(text(),'$Log#')]//preceding::input[1]"),
	ORDERING_ORDER_TRANSMISSION_QUEUE_XPATH("//div[@id='queues']//h1[1]"),
	ORDERING_ORDER_TRANSMISSION_TITLE_XPATH("//div[@id='queues']//h1[2]"),
	;

	private String value;

	OrderingBOOrderTransmissionQueuePageEnum(String value) {
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
