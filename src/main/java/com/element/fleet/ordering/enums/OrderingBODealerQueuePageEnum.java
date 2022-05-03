package com.element.fleet.ordering.enums;

public enum OrderingBODealerQueuePageEnum {

	ORDERING_BO_MANUAL_DEALER_Q_XPATH("//td[@class='actions']//a[contains(text(),'Manual')]"),
	ORDERING_BO_DEALER_SEARCH_Q_XPATH("//input[@id='smart-dealer-search']"),
	ORDERING_BO_DEALER_FIRST_SEARCHED_DEALER_CSS("div.tt-menu.tt-open div.tt-suggestion.tt-selectable.dealer-sugg-item:nth-child(1)"),
	ORDERING_BO_DEALER_SEARCH_BUTTON_Q_XPATH("//button[@class='searchbtn']"),
	ORDERING_BO_DEALER_SELCTION_ROW_XPATH("//table[@id='manual-table']/tbody/tr"),
	ORDERING_BO_DEALER_SELCTION_Q_XPATH("//table[@id='manual-table']//tr[1]//td[5][contains(text(),'A')]"),
	ORDERING_BO_MANUAL_DEALER_DEALER_TABLE_ROW_XPATH("//div[@id='manual-table']/div[@id='manual-table_wrapper']//table[@id='manual-table']/tbody/tr[1]/td[1]"),
	ORDERING_BO_MANUAL_DEALER_SELECTED_DEALER_CSS("div.selected-dealer > span.dealer-name[style='display: inline-block;']"),
	ORDERING_BO_DEALER_SAVE_BUTTON_Q_XPATH("//div[contains(text(),'Save')]"),
	ORDERING_BO_MANUAL_DEALER_DELIVERING_DEALER_RADIO_BUTTON_XPATH("//label[@for='delivering-dealer']//preceding-sibling::input[@name='ordering-dealer']"),
	ORDERING_BO_MANUAL_DEALER_ORDERING_DEALER_RADIO_BUTTON_XPATH("//label[@for='ordering-dealer']//preceding-sibling::input[@name='ordering-dealer']"),
	ORDERING_BO_DEALER_ASSIGNED_MESSAGE_XPATH("//div[contains(text(),'Dealer assignment saved successfully')]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_XPATH("//div[@class='m-label']"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_DEALER_NAME_LIST_XPATH("//th[contains(text(),'Dealer Name')]//following::tr//td[1]"),
	ORDERING_BO_DEALER_CITY_SEARCH_NAME("city_nm"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_DEALER_CITY_LIST_XPATH("//th[contains(text(),'Dealer Name')]//following::tr//td[3]"),
	ORDERING_BO_DEALER_STATE_SEARCH_ID("st_prov_abbr"),
	ORDERING_BO_DEALER_ZIP_CODE_SEARCH_NAME("supplier_postcode"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_ACTIVE_DEALERS_TOGGLE_XPATH("//label[contains(text(),'Active Dealers')]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_ACTIVE_DEALERS_LIST_XPATH("//th[contains(text(),'Dealer Name')]//following::tr//td[6]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_DEALER_NAME_XPATH("//h3[contains(text(),'Manual Dealer Assignment')]//following::input[1]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_DEALER_ADDRESS_1_XPATH("//label[contains(text(),'Address Line 1')]//following::input[1]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_ZIP_XPATH("//label[contains(text(),'Zip')]//following::input[1]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_ORDERING_DEALER_SEARCH_TOGGLE_XPATH("//label[contains(text(),'Ordering Dealer Search?')]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_LOG_NUMBER_XPATH("//span[contains(text(),'Log')]//following::span[1]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_CORP_CD_XPATH("//span[contains(text(),'Corp CD')]//following::span[1]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_FLEET_XPATH("//span[contains(text(),'Fleet')]//following::span[1]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_UNIT_XPATH("//span[contains(text(),'Unit')]//following::span[1]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_CLOSE_BUTTON_CSS("div.close-icon"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_MANUAL_BUTTON_XPATH("//a[@class='row-action manual']"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_INFO_WRAPPER_XPATH("//div[@class='manual-wrapper col-md-12']"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_SAVE_BUTTON_XPATH("//div[@class='btn_save green save disabled pull-right']"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_SEARCH_BUTTON_CLASS("searchbtn"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_TABLE_LENGTH_SELECT_NAME("manual-table_length"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_LAST_ELEMENT_TABLE_XPATH("//th[contains(text(),'Email')]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_HEADERS_TABLE_XPATH("//tr[@class='data-table__header-row']//th"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_FIRST_ELEMENT_TABLE_XPATH("//th[contains(text(),'Dealer Name')]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_ORDERING_DEALER_RADIO_XPATH("//input[@value='ordering-dealer']"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DELIVERING_DEALER_RADIO_XPATH("//input[@value='delivering-dealer']"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_DISTANCE_CELL_HEADER_XPATH("//div[@id='manual-table_wrapper']//div[@class='dataTables_scrollHead']/div/table/thead/tr/th[10]"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_DISTANCE_CELL_XPATH("//td[@data-name='Distance']"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DEALERNAME_CELL_XPATH("//td[@data-name='Dealer Name']"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DEALERCODE_CELL_XPATH("//td[@data-name='MFR Dealer Code']"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_ACTIVEDEALER_SWITCH_XPATH("//label[@for='active-dealers']"),
	ORDERING_BO_DEALER_SEARCH_BUTTON_XPATH("//div[@class='btn green btn-shared search']"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PRODUCTCLASS_SWITCH_NAME("product-code"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID("radius"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_ZIPCODE_TEXTFIELD_NAME("supplier-postcode"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_LABEL_XPATH("//div[@id='show-radius']//label"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_INFO_LABELS_XPATH("//div[@class='row dealer']//span[@class='data']"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_STATE_DROPDOWN_ID("state-label"),
	ORDERING_BO_DEALER_LOGNUMBER_XPATH("//td[@data-name='logNumber']"),
	ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_STATE_OPTION_XPATH("//li[@data-pcode='CA']"),
	;

	private String value;

	OrderingBODealerQueuePageEnum(String value) {
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
