
package com.element.fleet.ordering.enums;

public enum OrderingDealerPageEnum {
	
	ORDERING_DEALER_DEALER_OPTIONS_CSS("div.col-sm-6.col-md-6.col-lg-6>a"),
	ORDERING_DEALER_RECOMMENDED_DEALER_TABLE_ROWS_XPATH("//table[@id='dataTableOtherChoices']//tbody/tr"),
	ORDERING_DEALER_RECOMMENDED_DEALER_RADIO_BUTTON_XPATH(".//input[@class='checkDealerStatus']"),
	ORDERING_DEALER_RECOMMENDED_DEALER_TIER_NUMBER_LABEL_XPATH(".//td[2]"),
	ORDERING_DEALER_RECOMMENDED_DEALER_DEALER_NAME_LABEL_XPATH("//p[@id='supplierName']"),
	ORDERING_DEALER_RECOMMENDED_DEALER_CITY_LABEL_XPATH(".//td[4]"),
	ORDERING_DEALER_RECOMMENDED_DEALER_STATE_LABEL_XPATH(".//td[5]"),
	ORDERING_DEALER_RECOMMENDED_DEALER_ZIP_CODE_LABEL_XPATH(".//td[6]"),
	ORDERING_DEALER_RECOMMENDED_DEALER_COURTESY_DELIVERY_FEE_LABEL_XPATH(".//span[@class='customCenter']"),
	ORDERING_DEALER_RECOMMENDED_DEALER_DRIVING_DISTANCE_LABEL_XPATH("//table[@id='dataTableOtherChoices']//tr[1]//td[8]"),
	ORDERING_DEALER_RECOMMENDED_DEALER_MANUFACTURER_DEALER_CODE_LABEL_XPATH("//p[@id='manufactDNum']"),
	ORDERING_DEALER_RECOMMENDED_DEALER_MAXIMUM_DELIVERY_DISTANCE_LABEL_XPATH("//p[@id='distanceQty']"),
	ORDERING_DEALER_RECOMMENDED_DEALER_ADDRESS_LABEL_XPATH("//p[@id='add1']"),	
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_NAME_XPATH("//div[@id='delivering__section']//input[@name='dealerName']"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_ADDRESS_XPATH("//div[@id='delivering__section']//input[@name='Address']"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_COUNTY_XPATH("//div[@id='delivering__section']//input[@name='dealerCounty']"),
	//ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_COUNTRY_XPATH("//div[@id='delivering__section']//input[@name='dealerCountry']"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_CITY_XPATH("//div[@id='delivering__section']//input[@name='city']"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_STATE_XPATH("//div[@id='delivering__section']//input[@name='dealerState']"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_ZIPCODE_XPATH("//div[@id='delivering__section']//input[@name='dealerZipCode']"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_MANUFACTURER_DEALER_CODE_XPATH("//div[@id='delivering__section']//input[@name='mfgCode']"),	
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_NAME_XPATH("//div[@id='delivering__section']//input[@name='dealerName']"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_ADDRESS_XPATH("//div[@id='delivering__section']//input[@name='Address']"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_COUNTY_XPATH("//div[@id='delivering__section']//input[@name='dealerCounty']"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_COUNTRY_XPATH("//div[@id='delivering__section']//input[@name='country']"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_CITY_XPATH("//div[@id='delivering__section']//input[@name='city']"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_STATE_XPATH("//div[@id='delivering__section']//select[@name='stateProvince']"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_ZIPCODE_XPATH("//div[@id='delivering__section']//input[@name='dealerZipCode']"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_CONTACT_NAME_XPATH("//div[@id='delivering__section']//input[@name='dealerCtcName']"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_PHONE_NUMBER_XPATH("//div[@id='delivering__section']//input[@name='dealerCtcPhone']"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_EMAIL_XPATH("//div[@id='delivering__section']//input[@name='dealerCtcEmail']"),
	ORDERING_DEALER_SUPPLIER_NAME_ID("requestNewDealer"),
	ORDERING_DIFFERENT_DEALER_ID("selectDiffDealer"),
	ORDERING_DIFFERENT_DEALER_ACTIVE_DEALER_CHECKBOX_NAME("activeDealers"),
	ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME("useState"),
	ORDERING_REQUESTED_DEALER_ID("requestNewDealer"),
	ORDERING_REQUESTED_DEALER_FILL_CITY_XPATH("//input[@type='button' and @class='btn-fillCity']"),
	ORDERING_SEARCH_DEALER_BOX_ID("bh-search-dealer"),
	ORDERING_SEARCH_DEALER_RESULT_SET_CSS("div.tt-suggestion.tt-selectable.dealer-sugg-item > p:nth-child(1)"),
	ORDERING_SEARCH_DEALER_RESULT_CONTAINER_CSS("div.tt-menu.tt-open"),
	ORDERING_SEARCH_DEALER_RESULT_SET_CONTAINER_CSS("div.tt-dataset.tt-dataset-dealerList"),
	ORDERING_DEALER_NODEALER_CLASS("no_dealer"),
	ORDERING_DEALER_RECOMMENDED_CSS("table#dataTableOtherChoices > tbody > tr.status > td.statusDNamed > span.block"),
	ORDERING_DEALER_CODE_ID("manufactDNum"),
	ORDERING_DEALER_RECOMMENDED_SUPPLIERNAME_ID("supplierName"),
	ORDERING_DELIVERING_DEALER_ID("section__delivering"),
	ORDERING_DEALER_BACK_TO_DEALER_CHOICE_TEXT("Back to Dealer Choice"),
	ORDERING_DEALER_SELECT_DIFFERENT_DEALER_TEXT("Select a Different Dealer"),
	ORDERING_DEALER_REQUESTED_DEALER_TEXT("Request a New Dealer"),
	ORDERING_SEARCH_DEALER_FILL_CITY_STATE_COUNTY_BUTTON_CSS("input.newDealerInput.btn-fillCity"),
	ORDERING_SEARCH_DEALER_MULTIPLE_ZIP_CSS("div.zip-multiple > div"),
	ORDERING_SEARCH_DEALER_CITY_CSS("input.newDealerInput.dealerCity.city_nm"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_NAME_LABEL_CSS("div.form-group.row div.form-row:nth-child(1) div.form-group.col-md-3:nth-child(1) > label.block"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_ADDRESS_LABEL_CSS("div.form-group.row div.form-row:nth-child(1) div.form-group.col-md-3:nth-child(2) > label.block"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_COUNTY_LABEL_CSS("div.form-group.row div.form-row:nth-child(1) div.form-group.col-md-3:nth-child(3) > label.block"),
	//ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_COUNTRY_LABEL_CSS("div.form-group.row div.ownDealerInfo div.form-group.row div.form-row:nth-child(1) div.form-group.col-md-3:nth-child(4) > label.block"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_CITY_LABEL_CSS("div.form-group.row div.ownDealerInfo div.form-group.row div.form-row:nth-child(2) div.form-group.col-md-3:nth-child(1) > label.block"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_STATE_LABEL_CSS("div.form-group.row div.ownDealerInfo div.form-group.row div.form-row:nth-child(2) div.form-group.col-md-3:nth-child(2) > label.block"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_ZIPCODE_LABEL_CSS("div.form-group.row div.ownDealerInfo div.form-group.row div.form-row:nth-child(2) div.form-group.col-md-3:nth-child(3) > label.block"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_MANUFACTURER_DEALER_CODE_LABEL_CSS("div.form-group.row div.ownDealerInfo div.form-group.row div.form-row:nth-child(2) div.form-group.col-md-3:nth-child(4) > label.block"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_NAME_LABEL_CSS("div.form-group.row:nth-child(1) div.form-row.row:nth-child(1) div.form-group.col-md-3:nth-child(1) > label.block"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_ADDRESS_LABEL_CSS("div.form-group.row:nth-child(1) div.form-row.row:nth-child(1) div.form-group.col-md-3:nth-child(2) > label.block"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_COUNTY_LABEL_CSS("div.form-group.row:nth-child(1) div.form-row.row:nth-child(1) div.form-group.col-md-3:nth-child(3) > label.block"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_COUNTRY_LABEL_CSS("div.form-group.row:nth-child(1) div.form-row.row:nth-child(1) div.form-group.col-md-3:nth-child(4) > label.block"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_CITY_LABEL_CSS("div.form-group.row:nth-child(1) div.form-row.row:nth-child(2) div.form-group.col-md-3:nth-child(1) > label.block"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_STATE_LABEL_CSS("div.form-group.row:nth-child(1) div.form-row.row:nth-child(2) div.form-group.col-md-3:nth-child(2) > label.block"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_ZIPCODE_LABEL_CSS("div.form-group div.ownDealerInfo div.form-group.row:nth-child(1) div.form-row.row:nth-child(2) div.form-group.col-md-3:nth-child(3) > label.block"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_DEALER_CONTACT_NAME_LABEL_CSS("div.form-group.row:nth-child(2) div.form-row.row div.form-group.col-md-3:nth-child(1) > label.block:nth-child(1)"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_PHONE_NUMBER_LABEL_CSS("div.form-group.row:nth-child(2) div.form-row.row div.form-group.col-md-3:nth-child(2) > label.block:nth-child(1)"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_EMAIL_LABEL_CSS("div.form-group.row:nth-child(2) div.form-row.row div.form-group.col-md-3:nth-child(3) > label.block:nth-child(1)"),
	ORDERING_DEALER_BACK_TO_DEALER_CHOICE_XPATH("//a[contains(text(),'Back to Dealer Choice')]"),
	ORDERING_DEALER_TAB_XPATH("//div[@class='order-nav-wrapper']//li//a[@title='Dealer']"),
	ORDERING_DEALER_REQUEST_A_NEW_DEALER_XPATH("//a[@id='requestNewDealer']"),
	ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_XPATH("//a[@id='selectDiffDealer']"),
	;

	private String value;
	
	OrderingDealerPageEnum(String value) {
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
