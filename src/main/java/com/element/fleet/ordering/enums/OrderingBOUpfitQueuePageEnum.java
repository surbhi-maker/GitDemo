package com.element.fleet.ordering.enums;

public enum OrderingBOUpfitQueuePageEnum {
	
	ORDERING_BO_UPFIT_QUEUE_TITLE_EXPECTED("Queues"),
	ORDERING_BO_UPFIT_QUEUE_SUBTITLE_EXPECTED("- Upfit Queue"),
	;
	
	private String value;

	private OrderingBOUpfitQueuePageEnum(String value) {
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
