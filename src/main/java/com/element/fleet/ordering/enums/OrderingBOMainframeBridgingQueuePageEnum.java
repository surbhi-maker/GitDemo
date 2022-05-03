package com.element.fleet.ordering.enums;

public enum OrderingBOMainframeBridgingQueuePageEnum {
	
	ORDERING_MAINFRAME_BRIDGING_LOG_NUMBER_LIST_XPATH("//td[@data-name='logNo']//span"),
	ORDERING_MAINFRAME_BRIDGING_SEARCH_FIELD_XPATH("//label[contains(text(),'$logNumber#')]//preceding::input[1]"),
	ORDERING_MAINFRAME_BRIDGING_SEARCH_BUTTON_XPATH("//span[@class='fa fa-search']//following::span[1]"),
	ORDERING_MAINFRAME_BRIDGING_QUEUE_XPATH("//div[@id='queues']//h1[1]"),
	ORDERING_MAINFRAME_BRIDGING_TITLE_XPATH("//div[@id='queues']//h1[2]"),
	;

	private String value;

	private OrderingBOMainframeBridgingQueuePageEnum(String value) {
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
