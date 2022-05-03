package com.element.fleet.ordering.enums;

public enum OrderingBOBillingQueuePageEnum {
	
	ORDERING_BO_BILLING_COLUMN_LOG_DATA_LIST_XPATH("//div[@class='dataTables_scrollHead']//th[contains(text(),'Log')]//following::tr//td[2]"),
	ORDERING_BO_BILLING_COLUMN_CLIENT_DATA_LIST_XPATH("//div[@class='dataTables_scrollHead']//th[contains(text(),'Log')]//following::tr//td[4]"),
	ORDERING_BO_BILLING_COLUMN_UNIT_DATA_LIST_XPATH("//div[@class='dataTables_scrollHead']//th[contains(text(),'Log')]//following::tr//td[5]"),
	ORDERING_BO_BILLING_COLUMN_PRODUCT_CLASS_DATA_LIST_XPATH("//div[@class='dataTables_scrollHead']//th[contains(text(),'Log')]//following::tr//td[11]"),
	ORDERING_BO_BILLING_COLUMN_CONTRACT_TYPE_DATA_LIST_XPATH("//div[@class='dataTables_scrollHead']//th[contains(text(),'Log')]//following::tr//td[12]"),
	ORDERING_BO_BILLING_COLUMN_LEASE_TERM_DATA_LIST_XPATH("//div[@class='dataTables_scrollHead']//th[contains(text(),'Log')]//following::tr//td[13]"),
	ORDERING_BO_BILLING_CLEAR_FILTER_BUTTON_XPATH("//span[contains(text(),'Clear Filters')]"),
	ORDERING_BO_BILLING_TOGGLE_COLUMN_BUTTON_XPATH("//button//span[contains(text(),'Toggle Columns')]"),
	ORDERING_BO_BILLING_TOGGLE_COLUMNS_LIST_XPATH("//li[@class='active']//label"),
	ORDERING_BO_BILLING_TABLE_COLUMNS_LIST_XPATH("//th[@data-name='checkbox']//following::th"),
	ORDERING_BO_BILLING_TABLE_LST_COLUMNS_XPATH("//th[@data-name='checkbox']//following::th[contains(text(),'Aging')]"),
	ORDERING_BO_BILLING_TOGGLE_COLUMNS_UNCHECK_LIST_XPATH("//li[@class='']//label//input"),
	ORDERING_BO_BILLING_EXPORT_BUTTON_XPATH("//div[@class='btn blue btn-shared queue-export']//span"),
	ORDERING_BO_BILLING_TABLE_LENGTH_XPATH("//select[@name='queue-table_length']"),
	ORDERING_BO_BILLING_TABLE_LOG_HEADER_XPATH("//div[@class='dataTables_scrollHead']//th[contains(text(),'Log')]"),
	ORDERING_BO_BILLING_TABLE_CLIENT_HEADER_XPATH("//div[@class='dataTables_scrollHead']//th[contains(text(),'Client')]"),
	ORDERING_BO_BILLING_TABLE_UNIT_HEADER_XPATH("//div[@class='dataTables_scrollHead']//th[@data-name='unitNumber']"),
	ORDERING_BO_BILLING_TABLE_FACTORY_ORDER_DATE_HEADER_XPATH("//div[@class='dataTables_scrollHead']//th[@data-name='foDate']"),
	ORDERING_BO_BILLING_TABLE_CHECKBOX_XPATH("//table[@id='queue-table']//tr[1]//th[@data-name='logNumber']//following::tr//td[1]//input"),
	ORDERING_BO_BILLING_RELEASE_BUTTON_XPATH("//div[@class='btn custom-action green btn-shared releaseOrder']//span"),
	ORDERING_BO_PAGE_TITLE_XPATH("//h1[@class='subtitle']"),	
	;
	
	private String value;

	OrderingBOBillingQueuePageEnum(String value) {
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
