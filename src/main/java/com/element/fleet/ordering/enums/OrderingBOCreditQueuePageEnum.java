package com.element.fleet.ordering.enums;

public enum OrderingBOCreditQueuePageEnum {

	ORDERING_BO_BILLING_STANDARD_SEARCH_FIELDS_XPATH("//section[@id='standard']//div[contains(@class,'filter-field')]"),
	ORDERING_BO_BILLING_ADVANCED_SEARCH_FIELDS_XPATH("//section[@id='advanced']//div[contains(@class,'filter-field')]"),
	ORDERING_BO_BILLING_SEARCH_OVERLAY_XPATH("//span[@class='search-text']/following-sibling::span[@class='overlay']"),
	ORDERING_BO_BILLING_SEARCH_OPTION_STANDARD_XPATH("//ul[@class='optgroup']//li[@data-target='standard']"),
	ORDERING_BO_BILLING_SEARCH_OPTION_ADVANCED_XPATH("//ul[@class='optgroup']//li[@data-target='advanced']"),
	;	
	
	private String value;

	OrderingBOCreditQueuePageEnum(String value) {
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
