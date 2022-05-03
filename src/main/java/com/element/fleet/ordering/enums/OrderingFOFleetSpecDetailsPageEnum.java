package com.element.fleet.ordering.enums;

public enum OrderingFOFleetSpecDetailsPageEnum {
	
	FLEET_SPEC_DETAILS_PAGE_HEADER_XPATH("//label[@class='unitInfo__makeNModel'][1]"),
	FLEET_SPEC_DETAILS_PAGE_HEADER_MAKE_MODEL_TRIM_DATA_XPATH("//label[@class='unitInfo__makeNModel'][1]"),
	FLEET_SPEC_DETAILS_PAGE_HEADER_CLIENT_NO_ID("clientNo"),
	FLEET_SPEC_DETAILS_PAGE_HEADER_PRIMARY_CLIENT_NO_CLASS("primary-client"),
	FLEET_SPEC_DETAILS_PAGE_HEADER_CLIENT_NAME_ID("clientName"),
	FLEET_SPEC_DETAILS_PAGE_FLEETSPEC_ID_NAME_XPATH	("//label[contains(text(),'Spec ID | Spec Name')]//following-sibling::div"),
	FLEET_SPEC_DETAILS_PAGE_FLEETSPEC_STATUS_XPATH	("//label[contains(text(),'Status')]//following-sibling::div"),
	FLEET_SPEC_DETAILS_PAGE_HEADER_INITIAL_ODERR_DATE_XPATH("//label[contains(text(),'Initial Order Date')]//following-sibling::div"),
	FLEET_SPEC_DETAILS_PAGE_HEADER_START_OF_PRODUCTION_XPATH("//label[contains(text(),'Start of Production')]//following-sibling::div"),
	FLEET_SPEC_DETAILS_PAGE_HEADER_ORDERS_DUE_AT_ELEMENT_XPATH("//label[contains(text(),'Orders Due at Element')]//following-sibling::div"),
	FLEET_SPEC_DETAILS_PAGE_HEADER_LAST_DAY_TRANSMIT_XPATH("//label[contains(text(),'Last Day to Transmit')]//following-sibling::div"),
	FLEET_SPEC_DETAILS_PAGE_HEADER_END_OF_PRODUCTION_XPATH("//label[contains(text(),'End of Production')]//following-sibling::div"),
	FLEET_SPEC_DETAILS_PAGE_VEHICLE_TAB_CSS("li.vehicleTabfirst > a"),
	FLEET_SPEC_DETAILS_PAGE_STANDARD_NET_EQUIPMENT_TAB_CSS("li.standardequipment-tabcontent > a"),
	FLEET_SPEC_DETAILS_PAGE_LINK_CLIENT_TAB_CSS("li.linked-clientstab > a"),
	FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_TAB_CSS("li.geoLocationTab > a"),
	FLEET_SPEC_DETAILS_PAGE_UPFITTING_TAB_CSS("li.upfitting-tab-content > a"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_INVOICE_TAB_ID("invoice"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_MSRP_TAB_ID("MSRP"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_PRICE_PROTECTION_IMAGE_XPATH("//img[@class='price-protected-img'][1]"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_BASE_PRICE_LABEL_XPATH("//span/b[contains(text(),'Base Price')]"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_BASE_MSRP_PRICE_XPATH("//td[@class='pull-right base-MSRP textBold spaceright']"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_BASE_INVOICE_PRICE_XPATH("//td[@class='pull-right base-invoice textBold spaceright']"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_DESTINATION_FEE_LABEL_XPATH("//span/b[contains(text(),'Destination Fee')]"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_DESTINATION_FEE_PRICE_XPATH("//td[@class='pull-right dest-fee textBold spaceright']"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_OPTION_COST_LABEL_XPATH("//td[contains(text(),'Incentives cost')]"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_OPTION_COST_MSRP_PRICE_XPATH("//td[@class='pull-right js-msrp-option-cost textBold spaceright']"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_OPTION_COST_INVOICE_PRICE_XPATH("//td[@class='pull-right js-invoice-option-cost textBold spaceright']	"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_SUB_TOTAL_1_PRICE_LABEL_XPATH("//table[@class='invoice-pane']//tr[@class='subtotal']/td[contains(text(),'Sub-total')]"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_SUB_TOTAL_1_MSRP_PRICE_XPATH("//td[@class='pull-right subtotalvalue js-msrp-subtotal subtotalfinal']"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_SUB_TOTAL_1_INVOICE_PRICE_XPATH("//td[@class='pull-right subtotalvalue js-invoice-subtotal subtotalfinal']"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_TOTAL_PRICE_LABEL_CSS("div.total"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_TOTAL_INVOICE_PRICE_XPATH("//span[@class='pull-right js-incentive-total invoice-total-summary']"),
	FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_TOTAL_MSRP_PRICE_XPATH("//span[@class='pull-right js-msrp-total msrp-total-summary']"),
	FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_TAB_XPATH("//li[@class='geoLocationTab']"),
	FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_CHECK_ALL_XPATH("//input[@class='checkall']"),
	FLEET_SPEC_DETAILS_PAGE_GEOGRAPHIC_AVAILABILITY_WORLDWIDE_XPATH("//div[@class='worldwide']"),
	FLEET_SPEC_DETAILS_PAGE_GEO_SELECTED_LOCATIONS_XPATH("//div[@class='regions']/ul/li[@style='opacity: 0.9;']"),
	FLEET_SPEC_SELECTED_ITEM_GEOGRAPHIC_AVAILABILITY_XPATH("//div[@class='col-sm-1 selecteditem']"),
	FLEET_SPEC_CAN_NOT_BE_SAVED_MODEL_WINDOW_XPATH("//span[@class='message']"),
	FLEET_SPEC_DETAILS_NOGEO_LOCATION_SELECTED_ERROR_TEXT("//div[@class='col-sm-11 wearningText']"),
	FLEET_SPEC_CAN_NOT_BE_SAVED_MODEL_WINDOW_CONTINUE_BUTTON_XPATH("//button[@class='button button--secondary modal-close-btn']"),
	FLEET_SPEC_DETAILS_PAGE_GEO_LOCATIONS_XPATH("//div[@class='regions']/ul/li/input"),
	FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_PDF_ID("export-to-menu"),
	FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_PDF_LINK_XPATH("//a[@class='link export-to-pdf']"),
	FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_EXCEL_LINK_XPATH("//a[@class='link spec-export-excel']"),
	FLEET_SPEC_DETAILS_APPROVE_BUTTON_ID("approve-btn"),
	FLEET_SPEC_TABLE_XPATH("//table[@id='scratch-table']/tbody/tr"),
	FLEET_SPEC_DETAILS_PAGE_EXTERIOR_COLORS_INVOICE_XPATH("//ul[@id='0082']/li[10]"),
	FLEET_SPEC_DETAILS_PAGE_INTERIOR_COLORS_INVOICE_XPATH("//ul[@id='0083']/li[10]"),
	FLEET_SPEC_DETAILS_PAGE_EXTERIOR_COLORS_OPTIONTYPE_XPATH("//ul[@id='0082']/li[2]/select[not(@disabled)]"),
	FLEET_SPEC_DETAILS_PAGE_INTERIOR_COLORS_OPTIONTYPE_XPATH("//ul[@id='0083']/li[2]/select"),
	FLEET_SPEC_DETAILS_PAGE_EXTERIOR_COLORS_SHOWHIDE_XPATH("//ul[@id='0082']/li[3]"),
	FLEET_SPEC_DETAILS_PAGE_INTERIOR_COLORS_SHOWHIDE_XPATH("//ul[@id='0083']/li[3]"),
	FLEET_SPEC_DETAILS_PAGE_EXTERIOR_COLORS_OPTIONTYPE_CHANGE_WARNING_XPATH("//div[@id='dpo-confirm']/div/div/div[1]"),
	FLEET_SPEC_DETAILS_PAGE_EXTERIOR_COLORS_OPTIONTYPE_CHANGE_WARNING_CLOSE_XPATH("//button[@id='modal-close-btn']"),
	FLEET_SPEC_TABLE_ADDITIONAL_OPTIONS_HEADER_XPATH("//h3[contains(text(),'Additional Options')]"),
	FLEET_SPEC_DETAILS_DPO_CALCULATOR_XPATH("//div[@id='specs__vehicle__left']/div[4]"),
	FLEET_SPEC_DETAILS_DPO_CALCULATOR_THRESHOLD_BOX_XPATH("//div[@id='specs__vehicle__left']/div[4]/table/tbody/tr[1]/td"),
	FLEET_SPEC_DETAILS_PAGE_INTERIOR_COLORS_OPTIONTYPE_CHANGE_WARNING_CLOSE_XPATH("(//button[@id='modal-close-btn'])[2]"),
	FLEET_SPEC_DETAILS_SEARCH_FILTER_XPATH("//div[@id='scratch-table_filter']/label/input"),
	FLEET_SPEC_DETAILS_PAGE_INCENTIVE_OPTIONSTATE_XPATH("//select[contains(@class,'incentive-custom-dropdown-button selection__')]"),
	;

	private String value;

	private OrderingFOFleetSpecDetailsPageEnum(String value) {
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
