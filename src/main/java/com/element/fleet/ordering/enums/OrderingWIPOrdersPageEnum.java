package com.element.fleet.ordering.enums;

public enum OrderingWIPOrdersPageEnum {	

	ORDERING_WIPORDERS_HEADING_ID("searchHeading"),
	ORDERING_WIPORDERS_RESET_BUTTON_CSS("div.btn.pc-action.reset"),
	ORDERING_WIPORDERS_LOGNUMBER_TEXT_FIELD_XPATH("//input[@name='logNumber']"),
	ORDERING_WIPORDERS_CORP_TEXT_FIELD_XPATH("//input[@name='corpCode']"),
	ORDERING_WIPORDERS_CLIENT_TEXT_FIELD_XPATH("//input[@name='clientNumber']"),
	ORDERING_WIPORDERS_UNIT_TEXT_FIELD_XPATH("//input[@name='unitNumber']"),
	ORDERING_WIPORDERS_ORDER_TYPE_TEXT_FIELD_XPATH("//input[@name='orderType']"),
	ORDERING_WIPORDERS_VIN_TEXT_FIELD_XPATH("//input[@name='vin']"),
	ORDERING_WIPORDERS_FLEET_SPEC_TEXT_FIELD_XPATH("//input[@name='unitSpecId']"),
	ORDERING_WIPORDERS_YEAR_TEXT_FIELD_XPATH("//input[@name='year']"),
	ORDERING_WIPORDERS_MANUFACTURER_TEXT_FIELD_XPATH("//input[@name='manufacturer']"),
	ORDERING_WIPORDERS_MAKE_TEXT_FIELD_XPATH("//input[@name='make']"),
	ORDERING_WIPORDERS_MODEL_TEXT_FIELD_XPATH("//input[@name='model']"),
	ORDERING_WIPORDERS_TRIM_TEXT_FIELD_XPATH("//input[@name='trim']"),
	ORDERING_WIPORDERS_ORDERID_TEXT_FIELD_XPATH("//input[@name='orderId']"),
	ORDERING_WIPORDERS_TABLE_BODY_XPATH("//table[@id='queue-table']/tbody"),
	ORDERING_WIPORDERS_TABLE_ROW_XPATH("//table[@id='queue-table']/tbody/tr"),
	ORDERING_WIPORDERS_TOGGLE_BUTTON_CSS("button.multiselect.dropdown-toggle.btn.btn-default.pc-action"),
	ORDERING_WIPORDERS_TOGGLE_OPTIONS_CONTAINER_UL_XPATH("//ul[@class='multiselect-container dropdown-menu']"),
	ORDERING_WIPORDERS_TOGGLE_OPTIONS_XPATH("//ul[@class='multiselect-container dropdown-menu']/li"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_ORDER_ID_XPATH(".//label[@title='Order ID']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_CORP_XPATH(".//label[@title='Corp']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_CLIENT_XPATH(".//label[@title='Client']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_UNIT_XPATH(".//label[@title='Unit']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_ORDER_TYPE_XPATH(".//label[@title='Order Type']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_FIRST_NAME_XPATH(".//label[@title='Last Name']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_LAST_NAME_XPATH(".//label[@title='First Name']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_VIN_XPATH(".//label[@title='VIN']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_YEAR_XPATH(".//label[@title='Year']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_MANUFACTURER_XPATH(".//label[@title='Manufacturer']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_MAKE_XPATH(".//label[@title='Make']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_MODEL_XPATH(".//label[@title='Model']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_TRIM_XPATH(".//label[@title='Trim']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_FLEET_SPEC_XPATH(".//label[@title='Fleet Spec/Config ID']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_LAST_UPDATED_BY_XPATH(".//label[@title='Last Updated By']/input"),
	ORDERING_WIPORDERS_TOGGLE_OPTION_LAST_UPDATED_DATE_XPATH(".//label[@title='Last Updated Date']/input"),
	ORDERING_WIPORDERS_ORDER_TEXT_BOX_XPATH("//input[@name='orderId']"),
	ORDERING_WIPORDERS_SEARCH_BTN_XPATH("//div[contains(@class,'btn pc-action search')]"),
	ORDERING_WIPORDERS_TABLE_ROW_ORDERID_XPATH("//table[@id='queue-table']/tbody/tr/td[3]"),
	ORDERING_WIPORDERS_TABLE_QUEUE_ROW_XPATH("//table[@id='queue-table']/tbody/tr"),
	ORDERING_WIPORDERS_TABLE_HEADING_ROWS_XPATH("//div[@id='queue-table_wrapper']//div[@class='dataTables_scrollHead']//table/thead/tr/th"),
	ORDERING_WIPORDERS_SEARCH_FIELDS_VALUE_XPATH("//span[text()='%s']/..//input"),
	ORDERING_WIPORDERS_SEARCH_FIELDS_LABEL_XPATH("//div[@class='col-sm-2 filter-field']//span"),
	ORDERING_WIPORDERS_ORDERID_COLUMN_TABLE_DATA_XPATH("//div[@id='queue-table_wrapper']//div[@class='dataTables_scrollBody']//td[@data-name='orderId']"),
	ORDERING_WIPORDERS_ORDERID_COLUMN_TABLE_HEADING_XPATH("//th[@data-name='orderId']"),
	ORDERING_WIPORDERS_CLIENTNUMBER_COLUMN_TABLE_DATA_XPATH("//div[@id='queue-table_wrapper']//div[@class='dataTables_scrollBody']//td[@data-name='clientNumber']"),
	ORDERING_WIPORDERS_CLIENTNUMBER_COLUMN_TABLE_HEADING_XPATH("//th[@data-name='clientNumber']"),
	ORDERING_WIPORDERS_ORDERTYPE_COLUMN_TABLE_DATA_XPATH("//div[@id='queue-table_wrapper']//div[@class='dataTables_scrollBody']//td[@data-name='orderType']"),
	ORDERING_WIPORDERS_ORDERTYPE_COLUMN_TABLE_HEADING_XPATH("//th[@data-name='orderType']"),	
	ORDERING_WIPORDERS_HEADINGS_CSS("div#queue-table_wrapper.dataTables_wrapper div.dataTables_scrollHead th:not([style*='display: none;']):not([data-name='checkbox'])"),
	ORDERING_FO_WIP_ORDER_STATUS_SORTING_ICON_XPATH("//div[@class='dataTables_scrollHead']//th[9]"),
	ORDERING_FO_WIP_ORDER_STATUS_XPATH("//tr[1]//td[9]"),
	ORDERING_FO_WIP_ORDER_STATUS_RED_COLORED_XPATH("//tr[@class='queueData high-light-red odd']"),
	ORDERING_FO_WIP_REJECTED_ORDER_STATUS_XPATH("//tr[@class='queueData high-light-red odd'][1]"),
	ORDERING_FO_WIP_REJECTED_STATUS_REASON_XPATH("//tr[@class='queueData high-light-red odd'][1]//td[21]"),
	ORDERING_FO_WIP_GRID_VIEW_LIST_TABLE_HEADING_XPATH("//div[@class='dataTables_scrollHeadInner']//th"),
	ORDERING_FO_WIP_EXPORT_BUTTON_XPATH("//button[@class='action-bar__button queue-export export']"),
	ORDERING_FO_WIP_ORDERSTATE_LABEL_XPATH("//td[@data-name='displayOrderState']"),
	ORDERING_FO_WIP_ORDER_CHECKBOX_NAME("order-check"),
	ORDERING_FO_WIP_ORDER_CANCEL_BUTTON_XPATH("//button[@class='action-bar__button cancel-orders']"),
	ORDERING_FO_WIP_ORDER_CANCELORDERS_MODAL_ID("cancel-orders-modal"),
	ORDERING_FO_WIP_ORDER_ACCEPT_BUTTON_MODAL_ID("modal-accept-btn"),
	ORDERING_FO_WIP_ORDER_TEXT_AREA_MODAL_XPATH("//textarea[@class='comments-text']"), 
	ORDERING_FO_WIP_ORDER_SUCCESS_CANCEL_MODAL_XPATH("//div[@class='modal-body new-modal__body']//strong"), 
	ORDERING_FO_WIP_ORDER_SUCCESS_CANCEL_MODAL_ID("success-order-cancelation-modal"), 
	ORDERING_FO_WIP_ORDER_NORESULTS_LABEL_XPATH("//td[contains(text(),'No Results Found')]"), 
	ORDERING_WIPORDERS_CHECKBOX_NAME("order-check"),
	ORDERING_WIPORDERS_CANCEL_BUTTON_XPATH("//button[@class='action-bar__button cancel-orders']"),
	ORDERING_WIPORDERS_CANCEL_POP_UP_XPATH("//div[@class='modal-content']"),
	ORDERING_WIPORDERS_CANCEL_POP_UP_ACCEPT_XPATH("//button[@id='modal-accept-btn']"),
	ORDERING_WIPORDERS_CANCEL_SUCCESS_POPUP_ACCEPT_XPATH("//div[@id='success-order-cancelation-modal']//button[@id='modal-accept-btn']"),
	ORDERING_WIPORDERS_CANCEL_POP_UP_ACCEPT_COMMENTS_XPATH("//div[@class='modal fade in']//button[@id='modal-accept-btn']"),
	ORDERING_WIPORDERS_TABLE_EMPTY_ROWS_XPATH("//td[@class='dataTables_empty']"),
	ORDERING_FO_WIP_ORDER_CANCEL_BUTTON_MODAL_ID("modal-close-btn"),
	;

	private String value;
	
	OrderingWIPOrdersPageEnum(String value) {
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
