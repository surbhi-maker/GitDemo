package com.element.fleet.ordering.enums;

public enum OrderingBODealerInstalledOptionsPageEnum {
	
	ORDERING_BO_BMT_DIO_SEARCH_FIELD_ID("standard"),
	ORDERING_BO_BMT_DIO_SUBTITLEEXPECTED("Dealer Installed Options"),
	ORDERING_BO_BMT_DIO_SUBSCREENSUBTITLEEXPECTED("Dealer Installed Option Maintenance"),
	ORDERING_BO_BMT_DIO_ADD_XPATH("//div[contains(@class, 'addNew')]"),
	ORDERING_BO_BMT_DIO_ADD_DIO_OPTION_CODE_ID("optionCode"),
	ORDERING_BO_BMT_DIO_ADD_DIO_BRAND_PROVIDER_ID("supplierName"),
	ORDERING_BO_BMT_DIO_ADD_DIO_OPTION_DESCRIPTION_ID("optionShortDesc"),
	ORDERING_BO_BMT_DIO_ADD_DIO_EXTENDED_DESCRIPTION_ID("extendedDescription"),
	ORDERING_BO_BMT_DIO_ADD_DIO_EC_CODE_XPATH("//select[@id='equipCtgyId']"),
	ORDERING_BO_BMT_DIO_ADD_DIO_POST_PROD_IND_CSS("#postProductionInd+label"),
	ORDERING_BO_BMT_DIO_ADD_DIO_MAX_PRICE_AMOUNT_ID("maxPriceAmount"),
	ORDERING_BO_BMT_DIO_ADD_DIO_SUPPLIER_TO_SHIP_IND_CSS("#supplierToShipIndicator+label"),
	ORDERING_BO_BMT_DIO_ADD_DIO_EFF_START_DATE_ID("effDate"),
	ORDERING_BO_BMT_DIO_ADD_DIO_EFF_END_DATE_ID("endDate"),
	ORDERING_BO_BMT_DIO_ADD_DIO_SAVE_ID("save-entity"),
	ORDERING_BO_BMT_DIO_ADD_DIO_DELETE_ID("delete-entity"),
	ORDERING_BO_BMT_DIO_ADD_DIO_BACK_TO_QUEUE_VIEW_ID("browser-back"),
	ORDERING_BO_BMT_DIO_OPTION_CODE_FILTER_NAME("optionCode"),
	ORDERING_BO_BMT_DIO_BRAND_PROVIDER_FILTER_NAME("providerName"),
	ORDERING_BO_BMT_ADD_DIO_CONFIRM_DELETE_DIO_XPATH("//div[@id='new-modal']//button[contains(text(), 'Yes')]"),
	ORDERING_BO_BMT_NVAC_WARNING_CSS("div.infomodal-body>div>p"),
	ORDERING_BO_DIO_CLOSE_WARNING_CSS("button.modal-close-btn"),
	ORDERING_BO_DIO_OK_WARNING_CSS("button.modal-ok-btn"),
	ORDERING_BO_DIO_ADD_DIO_QUANTITY_XPATH("//table[@id='dio-table']//tr[%s]//td[2]//input[@type='text']"),
	ORDERING_BO_DIO_ADD_DIO_OPTION_CODE_XPATH("//table[@id='dio-table']//tr[%s]//td[3]//input[@type='text']"),
	ORDERING_BO_DIO_ADD_DIO_OPTION_DESCRIPTION_XPATH("//table[@id='dio-table']//tr[%s]//td[5]//input[@type='text']"),
	ORDERING_BO_DIO_ADD_DIO_PRICE_XPATH("//table[@id='dio-table']//tr[%s]//td[7]//input[@type='text']"),
	ORDERING_BO_DIO_ADD_DIO_LINE_OPTION_XPATH("//table[@id='dio-table']//tr[%s]//div[@class='dropdown']"),
	ORDERING_BO_DIO_DELETE_SINGLE_DIO_XPATH("//table[@id='dio-table']//tr[%s]//div[@class='dropdown']//div//a[@class='single-delete']"),
	ORDERING_BO_DIO_CONFIRM_DELETE_DIO_XPATH("//div[@id='new-modal']//button[contains(text(), 'Yes')]"),
	ORDERING_BO_DIO_CHECKBOX_XPATH("//table[@id='dio-table']//tr[%s]//td[1]//input[@type='checkbox']"),
	ORDERING_BO_DIO_SAVE_CSS("button#save-dioInfo"),
	ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH("//table[@id='queue-table']/tbody/tr"),
	;
	
	private String value;

	OrderingBODealerInstalledOptionsPageEnum(String value) {
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
