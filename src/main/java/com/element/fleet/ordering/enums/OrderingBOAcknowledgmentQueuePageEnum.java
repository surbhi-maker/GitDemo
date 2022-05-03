package com.element.fleet.ordering.enums;

public enum OrderingBOAcknowledgmentQueuePageEnum {

	ORDERING_ACK_QUEUE_LABEL1_XPATH("//h1[1]"),
	ORDERING_ACK_QUEUE_TABLE_LINKS_COLUMNS_LIST_XPATH("//table[@id='queue-table']//tbody//tr//td[@data-val='Acknowledgement']//span"),
	ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_LABEL_XPATH("//div[@class='subscreen-title']"),
	ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH("//*[contains(text(),'$fieldLabel#')]"),
	ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ACKNOWLEDGMENT_BUTTON_ID("acknowledgementButton"),
	ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_PDF_BUTTON_ID("downloadPdf"),
	ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_SIGNOUT_BUTTON_XPATH("//form[@action='/logout']"),
	ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ERROR_MESSAGE_LABEL_XPATH("//h3"),
	;

	private String value;

	private OrderingBOAcknowledgmentQueuePageEnum(String value) {
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
