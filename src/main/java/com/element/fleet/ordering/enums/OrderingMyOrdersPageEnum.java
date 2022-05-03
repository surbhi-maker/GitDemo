package com.element.fleet.ordering.enums;

public enum OrderingMyOrdersPageEnum {
	
	ORDEING_MY_ORDERS_HEADING_ID("searchHeading"),
	ORDERING_MY_ORDERS_SEARCH_BUTTON_XPATH("//i[@class='fa fa-search']"),
	ORDERING_MY_ORDERS_SEARCH_FIELDS_VALUE_XPATH("//input[@name='%s']"),
	ORDEING_MY_ORDERS_LOG_NUMBER_TEXT_FIELD_XPATH("//input[@name='logNumber']"),
	ORDEING_MY_ORDERS_CORP_TEXT_FIELD_XPATH("//input[@name='corpCode']"),
	ORDERING_MY_ORDERS_CLIENT_TEXT_FIELD_XPATH("//input[@name='clientNumber']"),
	ORDERING_MY_ORDERS_UNIT_TEXT_FIELD_XPATH("//input[@name='unitNumber']"),
	ORDERING_MY_ORDERS_ORDER_TYPE_TEXT_FIELD_XPATH("//input[@name='orderType']"),
	ORDERING_MY_ORDERS_VIN_TEXT_FIELD_XPATH("//input[@name='vin']"),
	ORDERING_MY_ORDERS_FLEET_SPEC_TEXT_FIELD_XPATH("//input[@name='unitSpecId']"),
	ORDERING_MY_ORDERS_YEAR_TEXT_FIELD_XPATH("//input[@name='year']"),
	ORDERING_MY_ORDERS_MANUFACTURER_TEXT_FIELD_XPATH("//input[@name='manufacturer']"),
	ORDERING_MY_ORDERS_MAKE_TEXT_FIELD_XPATH("//input[@name='make']"),
	ORDERING_MY_ORDERS_MODEL_TEXT_FIELD_XPATH("//input[@name='model']"),
	ORDERING_MY_ORDERS_TRIM_TEXT_FIELD_XPATH("//input[@name='trim']"),
	ORDERING_MY_ORDERS_ORDERID_TEXT_FIELD_XPATH("//input[@name='orderId']"),
	ORDEING_MY_ORDERS_TABLE_BODY_XPATH("//table[@id='queue-table']/tbody"),
	ORDEING_MY_ORDERS_TABLE_ROW_XPATH("//table[@id='queue-table']/tbody/tr"),
	ORDERING_MY_ORDERS_TABLE_CLOUMN_HEADING_XPATH("//tr[@class='main-row']/th"),
	ORDERING_MY_ORDER_PAGE_ORDER_STATUS_XPATH("//label[contains(text(),'Order Status')]//following-sibling::p"),
	ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_POOL_TYPE_XPATH("//label[contains(text(),'Pool Type')]//following-sibling::p"),
	ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_CLIENT_UNIT_XPATH("//label[contains(text(),'Client/Unit')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_LOG_NUMBER_XPATH("//label[contains(text(),'Log Number')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_TYPE_XPATH("//label[contains(text(),'Log Number')]//following::p[2]"),
	ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_ID_XPATH("//label[contains(text(),'Order ID')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_PO_DATE_XPATH("//label[contains(text(),'Factory Order Date')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_VIN_XPATH("//label[contains(text(),'VIN')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_CLIENT_CONTACT_INFO_CONTACT_NAME_XPATH("//label[contains(text(),'Contact Name :')]//following-sibling::span"),
	ORDERING_MY_ORDER_PAGE_CLIENT_CONTACT_INFO_PHONE_XPATH("//label[contains(text(),'Phone :')]//following-sibling::span"),
	ORDERING_MY_ORDER_PAGE_CLIENT_CONTACT_INFO_EMAIL_XPATH("//label[contains(text(),'Email :')]//following-sibling::span"),
	ORDERING_MY_ORDER_PAGE_DEALER_TAB_XPATH("//div[@data-target='#dealerInfo']//span[1]"),
	ORDERING_MY_ORDER_PAGE_DEALER_NAME_XPATH("//div[@id='dealerInfo']//label[contains(text(),'Name')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_DEALER_CODE_XPATH("//div[@id='dealerInfo']//label[contains(text(),'Manufacturer Dealer Code')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_DEALER_CD_FEE_XPATH("//div[@id='dealerInfo']//label[contains(text(),'Courtesy Delivery Fee')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_DEALER_ADDRESS_XPATH("//span[contains(text(),'Delivering Dealer')]//following::label[contains(text(),'Address')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_DEALER_ADDRESS("PO BOX 52048 SHREVEPORT LA US 71105"),
	ORDERING_MY_ORDER_PAGE_UPFITSPECNAME_XPATH("//label[contains(text(),'Upfit Spec')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_SUPPLIERNAME_XPATH("//label[contains(text(),' Supplier Name ')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_SEARCHBTN_XPATH("//i[@class='fa fa-search']"),
	ORDERING_MY_ORDER_PAGE_LOGNUMBERHYPERLINK_XPATH("//td[@data-name='logNumber']"),
	ORDERING_MY_ORDER_PAGE_UPFITTINGLINK_XPATH("//div[@data-target='#vehicleUpfitInfo']"),
	ORDERING_MY_ORDER_PAGE_DIOLINK_XPATH("//div[contains(text(),'Dealer Install Options')]"),
	ORDERING_MY_ORDER_PAGE_DIOQUANTITY_XPATH("//td[@class='quantity sorting_1']"),
	ORDERING_MY_ORDER_PAGE_DIOOPTIONCODE_XPATH("//td[@class='optionCode']"),
	ORDERING_MY_ORDER_PAGE_DIODESCRIPTION_XPATH("//td[@class='optionDesc']"),
	ORDERING_MY_ORDER_PAGE_DIOMAXPRICE_XPATH("//td[@class='maxPriceAmt text-right']"),
	ORDERING_MY_ORDER_PAGE_TITLEREGISTRATIONLINK_XPATH("//div[@data-target='#titleRegInfo']"),
	ORDERING_MY_ORDER_PAGE_TITLEOWNER_XPATH("//label[contains(text(),'Title Owner')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_REGISTEROWNER_XPATH("//label[contains(text(),'Registered Owner')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_LIENHOLDER_XPATH("//label[contains(text(),'Lienholder Name')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_PLATETYPE_XPATH("//label[contains(text(),'Plate Type')]//following::p[1]"),
	ORDERING_MY_ORDER_PAGE_WHOTOTILE_XPATH("//label[contains(text(),'Who to Title')]//following::p[1]"),
	ORDERING_MY_ORDERS_RESET_BUTTON_CSS("div.btn.pc-action.reset"),
	ORDERING_MY_ORDERS_EXPORT_BUTTON_CSS(".export"),
	ORDERING_MY_ORDERS_LOGNUMBERR_COLUMN_TABLE_HEADING_XPATH("//th[@data-name='logNumber']"),
	ORDERING_MY_ORDERS_CLIENTNUMBER_COLUMN_TABLE_HEADING_XPATH("//th[@data-name='clientNumber']"),
	ORDERING_MY_ORDERS_MANUFACTURER_COLUMN_TABLE_HEADING_XPATH("//th[@data-name='manufacturer']"),
	ORDERING_MY_ORDERS_TOGGLE_BUTTON_CSS("button.multiselect.dropdown-toggle.btn.btn-default.pc-action"),
	ORDERING_MYORDERS_TOGGLE_OPTIONS_CONTAINER_UL_XPATH("//ul[@class='multiselect-container dropdown-menu']"),
	ORDERING_MYORDERS_TOGGLE_OPTIONS_XPATH("//ul[@class='multiselect-container dropdown-menu']/li"),
	ORDERING_MYORDERS_TOGGLE_OPTION_LOG_XPATH(".//label[@title='Log']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_CORP_XPATH(".//label[@title='Corp Code']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_CLIENT_XPATH(".//label[@title='Client Number']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_UNIT_XPATH(".//label[@title='Unit Number']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_ORDER_TYPE_XPATH(".//label[@title='Order Type']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_DRIVER_LAST_NAME_XPATH(".//label[@title='Driver Last Name']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_DRIVER_FIRST_NAME_XPATH(".//label[@title='Driver First Name']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_VIN_XPATH(".//label[@title='VIN']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_YEAR_XPATH(".//label[@title='Year']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_MANUFACTURER_XPATH(".//label[@title='Manufacturer']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_MAKE_XPATH(".//label[@title='Make']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_MODEL_XPATH(".//label[@title='Model']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_TRIM_XPATH(".//label[@title='Trim']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_FLEET_SPEC_XPATH(".//label[@title='Spec ID']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_LAST_UPDATED_BY_XPATH(".//label[@title='Last Updated UserId']/input"),
	ORDERING_MYORDERS_TOGGLE_OPTION_LAST_UPDATED_DATE_XPATH(".//label[@title='Last Updated Date']/input"),
	ORDERING_MYORDERS_HEADING_ID("searchHeading"),
	ORDERING_MYORDERS_CORP_TEXT_FIELD_XPATH("//input[@name='corpCode']"),
	ORDERING_MYORDERS_CLIENT_TEXT_FIELD_XPATH("//input[@name='clientNumber']"),
	ORDERING_MYORDERS_UNIT_TEXT_FIELD_XPATH("//input[@name='unitNumber']"),
	ORDERING_MYORDERS_ORDER_TYPE_TEXT_FIELD_XPATH("//input[@name='orderType']"),
	ORDERING_MYORDERS_VIN_TEXT_FIELD_XPATH("//input[@name='vin']"),
	ORDERING_MYORDERS_FLEET_SPEC_TEXT_FIELD_XPATH("//input[@name='unitSpecId']"),
	ORDERING_MYORDERS_YEAR_TEXT_FIELD_XPATH("//input[@name='year']"),
	ORDERING_MYORDERS_MANUFACTURER_TEXT_FIELD_XPATH("//input[@name='manufacturer']"),
	ORDERING_MYORDERS_MAKE_TEXT_FIELD_XPATH("//input[@name='make']"),
	ORDERING_MYORDERS_MODEL_TEXT_FIELD_XPATH("//input[@name='model']"),
	ORDERING_MYORDERS_TRIM_TEXT_FIELD_XPATH("//input[@name='trim']"),
	ORDERING_MYORDERS_TABLE_BODY_XPATH("//table[@id='queue-table']/tbody"),
	ORDERING_MYORDERS_HEADINGS_CSS("div#queue-table_wrapper.dataTables_wrapper div.dataTables_scrollHead th:not([style*='display: none;']):not([data-name='checkbox'])"),
	ORDERING_MYORDERS_TABLE_LAST_COLUMN_XPATH("//th[@class='sorting_disabled'][18]"),
	ORDERING_MYORDERS_LOG_NUMBER_TEXT_FIELD_XPATH("//input[@name='logNumber']"),
	ORDERING_MYORDERS_LOG_NUMBER_COLUMN_HEADER_XPATH("//th[@data-name='logNumber']"),
	ORDERING_MYORDERS_UNIT_NUMBER_COLUMN_HEADER_XPATH("//th[@data-name='unitNumber']"),
	ORDERING_MYORDERS_CLIENT_NUMBER_COLUMN_HEADER_XPATH("//th[@data-name='clientNumber']"),
	ORDERING_MYORDERS_CORP_CODE_COLUMN_HEADER_XPATH("//th[@data-name='corpCode']"),
	ORDERING_MYORDERS_COLUMN_HEADER_NAME_XPATH("//th[@data-name='#dataName$']"),
	ORDERING_MYORDERS_COLUMN_DATA_LIST_XPATH("//td[@data-name='#dataName$']"),
	ORDERING_MYORDERS_SEARCH_FIELDS_XPATH("//span[contains(text(),'#searchField$')]//following-sibling::input"),
	ORDERING_MYORDERS_ROW_CSS("table#queue-table tr.queueData"),
	ORDERING_MYORDERS_SEARCH_FIELDS_LIST_XPATH("//div[@class='col-sm-2 filter-field']"),
	;

	private String value;

	OrderingMyOrdersPageEnum(String value) {
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