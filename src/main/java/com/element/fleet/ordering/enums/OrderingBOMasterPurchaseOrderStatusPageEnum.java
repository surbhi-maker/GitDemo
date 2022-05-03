package com.element.fleet.ordering.enums;

public enum OrderingBOMasterPurchaseOrderStatusPageEnum {
	
	ORDERING_BO_BMT_MASTERPURCHASE_BUTTON_XPATH("//div[contains(text(),' Master Purchase Order Status')]"),
	ORDERING_BO_MP_OS_DESCRIPTION_TEXT_XPATH("//input[@name='description']"),
	ORDERING_BO_MP_OS_ADDNEW_MANUFACTURER_DROPDOWN_XPATH("(//label[contains(text(),'Manufacturer')])[3]//following::select[1]"),
	ORDERING_BO_MP_OS_ADDNEW_MAKE_DROPDOWN_XPATH("(//label[contains(text(),'Make')])[3]//following::select[1]"),
	ORDERING_BO_MP_OS_ADDNEW_MAJOR_CODE_DROPDOWN_XPATH("(//label[contains(text(),'Major Code')])[3]//following::select[1]"),
	ORDERING_BO_MP_OS_ADDNEW_MINOR_CODE_TEXT_XPATH("(//label[contains(text(),'Minor Code')])[3]//following::input[1]"),
	ORDERING_BO_MP_OS_ADDNEW_DESCRIPTION_TEXT_XPATH("(//label[contains(text(),'Description')])[5]//following::input[1]"),
	ORDERING_BO_MP_OS_ADDNEW_ACTIVE_INACTIVE_TOGGLE_BUTTON_XPATH("//label[contains(text(),'Active / Inactive')]//following::label[1]"),
	ORDERING_BO_MP_OS_WAIT_MAKE_DROPDOWN_XPATH("(//select[@id='make']//following::option[1])[1]"),
	ORDERING_BO_MP_OS_VALIDATION_CORP_CODE_XPATH("//td[@data-name='corpCode']"),
	ORDERING_BO_MP_OS_VALIDATION_MANUFACTURER_XPATH("//td[@data-name='manufacturer']"),
	ORDERING_BO_MP_OS_VALIDATION_MAKE_XPATH("//td[@data-name='make']"),
	ORDERING_BO_MP_OS_VALIDATION_MAJOR_CODE_XPATH("//td[@data-name='majorCode']"),
	ORDERING_BO_MP_OS_VALIDATION_MINOR_CODE_XPATH("//td[@data-name='minorCode']"),
	ORDERING_BO_MP_OS_VALIDATION_DESCRIPTION_XPATH("//td[@data-name='description']"),
	ORDERING_BO_MP_OS_VALIDATION_ACTUAL_ESTIMATED_XPATH("//td[@data-name='actulaEstimatedIndicator']"),
	ORDERING_BO_MP_OS_VALIDATION_STORE_ONCE_INDICATOR_XPATH("//td[@data-name='storeOnceIndicator']"),
	ORDERING_BO_MP_OS_VALIDATION_AUTO_ACKNOWLEDGE_XPATH("//td[@data-name='autoAknowledgeIndicator']"),
	ORDERING_BO_MPOS_PAGE_TITLE_ID("queue-page-title"),
	ORDERING_BO_MPOS_PAGE_AUTO_ACK_LABEL_XPATH("//input[@name='autoAknowledgeIndicator']//following-sibling::label"),
	ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_LABEL_XPATH("//input[@name='dealerAssignCutoffIndicator']//following-sibling::label"),
	ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_SEARCH_FIELD_XPATH("//input[@name='clientVisibleIndicator']//following::input[1][@name='dealerAssignCutoffIndicator']"),
	ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_TOGGLE_COLUMN_XPATH("//label[@title='View External']//following::label[1][@title='Dealer Cutoff']"),
	ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_TABLE_COLUMN_XPATH("//th[@data-name='clientVisibleIndicator']//following::th[@data-name='dealerAssignCutoffIndicator']"),
	ORDERING_BO_MPOS_PAGE_ADD_NEW_PAGE_TITLE_XPATH("//div[@class='subscreen-title']"),
	ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_ADD_NEW_XPATH("//div[@id='div-isClientVisible']//following::div[@id='div-dealerAssignCutoffIndicator']"),
	ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_TOGGLE_ADD_NEW_XPATH("//div[@id='div-dealerAssignCutoffIndicator']//label[@for='dealerAssignCutoffIndicator']"),
	ORDERING_BO_MPOS_PAGE_ADD_NEW_MANUFACTURER_ID("manufacturer"),
	ORDERING_BO_MPOS_PAGE_ADD_NEW_MAJOR_CODE_ID("majorCode"),
	ORDERING_BO_MPOS_PAGE_ADD_NEW_DESCRIPTION_ID("description"),
	ORDERING_BO_MPOS_PAGE_ADD_NEW_SAVE_ID("save-entity"),
	ORDERING_BO_MPOS_PAGE_SEARCH_DATA_NAME_XPATH("//section[@id='standard']//label[contains(text(),'$FieldName#')]//preceding-sibling::input"),
	ORDERING_BO_MPOS_PAGE_COLUMN_DATA_XPATH("//tbody//tr[1]//td[@data-name='$DataName#']"),
	ORDERING_BO_MPOS_PAGE_SEARCH_BUTTON_XPATH("//div[@data-target='search']"),
	ORDERING_BO_MPOS_PAGE_DELETE_BUTTON_ID("delete-entity"),
	ORDERING_BO_MPOS_PAGE_DELETE_POPUP_LABEL_CSS("span[class='message']"),
	ORDERING_BO_MPOS_PAGE_DELETE_POPUP_MESSAGE_XPATH("//div[@class='modal-body new-modal__body']//div"),
	ORDERING_BO_MPOS_PAGE_DELETE_POPUP_NO_XPATH("//button[@class='button button--secondary btn-spacing']"),
	ORDERING_BO_MPOS_PAGE_DELETE_POPUP_YES_XPATH("//button[@class='button button--primary btn-spacing js-button-primary']"),
	ORDERING_BO_MPOS_PAGE_EMPTY_TABLE_XPATH("//td[@class='dataTables_empty']"),
	ORDERING_BO_MPOS_PAGE_EXPORT_XPATH("//div[@class='btn blue btn-shared queue-export']"),
	ORDERING_BO_MPOS_PAGE_CLEAR_FILTER_XPATH("//div[@class='btn green btn-shared clear-filters']"),
	ORDERING_BO_MPOS_PAGE_FIRST_TD_VALUE_XPATH("//table[@id='queue-table']//td[1]"),
	;
	
	private String value;

	OrderingBOMasterPurchaseOrderStatusPageEnum(String value) {
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
