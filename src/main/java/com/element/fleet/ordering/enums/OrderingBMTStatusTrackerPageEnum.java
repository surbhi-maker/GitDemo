package com.element.fleet.ordering.enums;

public enum OrderingBMTStatusTrackerPageEnum {

	ORDERING_BMT_STATUS_TRACKER_TABLE_ROWS_XPATH("//div[@id='queue-table']//tbody//tr"),
	ORDERING_BMT_STATUS_TRACKER_RELATIONSHIP_CHILDRENS_XPATH("//div[@id='queue-table']//td[contains(text(),'%s')]//following-sibling::td[@data-name='clientName']"),
	ORDERING_BMT_STATUS_TRACKER_RELATIONSHIP_SAVE_NEW_RECORD_XPATH("//div[@id='save-relationship']"),
	ORDERING_BMT_STATUS_TRACKER_SELECT_PARENT_NAME_DROPDOWN_XPATH("//select[@id='parentName']"),
	ORDERING_BMT_STATUS_TRACKER_LINKED_CLIENTS_LABELS_XPATH("//div[@id='linked-clients']//input//following-sibling::label"),
	ORDERING_BMT_STATUS_TRACKER_EMPTY_LINKED_CLIENTS_ERROR_XPATH("//div[@id='manage-parent-child-relationship']//div[contains(@class,'validation-error')]//span"),
	ORDERING_BMT_STATUS_TRACKER_SEARCH_CLIENT_SUGGESTION_XPATH("//input[@id='searchClient']"),
	ORDERING_BMT_STATUS_TRACKER_SEARCH_CLIENT_FIRST_SUGGESTION_XPATH("//div[@class='tt-dataset tt-dataset-searchClient']//div[1]//strong"),
	ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_FIRST_PAGE_XPATH("//div[@id='queue-table']//span[@class='first paginate_button']"),
	ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_NEXT_PAGE_XPATH("//div[@id='queue-table']//span[@class='next paginate_button']"),
	ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_PREVIOUS_PAGE_XPATH("//div[@id='queue-table']//span[@class='previous paginate_button']"),
	ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_LAST_PAGE_XPATH("//div[@id='queue-table']//span[@class='last paginate_button']"),
	ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_RECORDS_DROPDOWN_XPATH("//div[@id='queue-table']//select"),
	ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_FIRST_PAGE_NUMBER_XPATH("//div[@id='queue-table']//span[@class='paginate_page']"),
	ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_LAST_PAGE_NUMBER_XPATH("//div[@id='queue-table']//span[@class='paginate_of']"),
	ORDERING_BMT_STATUS_TRACKER_CORP_CODE_TEXTBOX_XPATH("//input[@id='corpCode-input']"),
	ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_LAST_UPDATED_BY_XPATH("//div[@id='div-auditUpdateUser']//label"),
	ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_LAST_UPDATED_DATE_XPATH("//div[@id='div-auditUpdateDate']//label"),
	ORDERING_BMT_STATUS_TRACKER_SETTINGS_LAST_NAVIGATED_TAB_XPATH("//li[@class='active']//a[text()='%s']"),
	ORDERING_BMT_STATUS_TRACKER_ADDNEW_PARENT_NAME_TEXTBOX_XPATH("//input[@id='parentName']"),
	ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_EXISTING_PARENT_ERROR_XPATH("//div[@id='div-parentName']//div[contains(@class, 'validation-error')]//span"),
	ORDERING_BMT_STATUS_TRACKER_SETTINGS_SAVE_MESSAGE_XPATH("//div[@class='noty-msg x-style']"),
	ORDERING_BMT_STATUS_TRACKER_PARENT_NAME_TEXTBOX_XPATH("//input[@id='parentName-input']"),
	ORDERING_BMT_STATUS_TRACKER_SETTINGS_SAVE_MESSAGE_CLOSE_BUTTON_XPATH("//div[@class='noty_close_button']"),
	ORDERING_BMT_STATUS_TRACKER_SETTING_TITLE_XPATH("//h1[@id='queue-page-title']"),
	ORDERING_BMT_STATUS_TRACKER_SETTINGS_TABS_XPATH("//div[@id='tabs-component']//a"),
	ORDERING_BMT_STATUS_TRACKER_SETTINGS_TABS_LABELS_XPATH("//div[@id='tabs-component']//a[text()='%s']"),
	ORDERING_BMT_STATUS_TRACKER_SEARCH_FILTERS_XPATH("//div[@id='search-filters']"),
	ORDERING_BMT_STATUS_TRACKER_SEARCH_FILTERS_LABELS_XPATH("//div[@id='search-filters']//label"),
	ORDERING_BMT_STATUS_TRACKER_SEARCH_FILTERS_TEXTBOX_XPATH("//div[@id='search-filters']//input"),
	ORDERING_BMT_STATUS_TRACKER_FILTER_TOGGLE_BUTTON_XPATH("//button[@id='toggle-filter']"),
	ORDERING_BMT_STATUS_TRACKER_TABLE_XPATH("//div[@id='queue-table']"),
	ORDERING_BMT_STATUS_TRACKER_TABLE_COLUMN_LABEL_XPATH("//div[@id='queue-table']//div[@class='dataTables_scrollHeadInner']//th"),
	ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_FILTERS_XPATH("//div[contains(@class,'table-filters')]//label[text()='%s']//following-sibling::input"),
	ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_BUTTON_XPATH("//button[@id='search-by-filters-btn']"),
	ORDERING_BMT_STATUS_TRACKER_TABLE_CLEARFILTER_BUTTON_XPATH("//button[@id='clear-all-filters-btn']"),
	ORDERING_BMT_STATUS_TRACKER_TABLE_EXPORT_BUTTON_XPATH("//button[@id='export-CSV-button']"),
	ORDERING_BTM_STATUS_TRACKER_ADDNEW_BUTTON_XPATH("//button[@id='add-new-button']"),
	ORDERING_BMT_STATUS_TRACKER_TABLE_RECORDS_XPATH("//div[@id='queue-table']//tbody//td[@data-val='%s']"),
	ORDERING_BMT_STATUS_TRACKER_TABLE_RECORDS_INFO_XPATH("//div[@class='dataTables_info']"),
	ORDERING_BMT_STATUS_TRACKER_TABLE_NAVIGATION_BUTTONS_XPATH("//div[@class='dataTables_paginate paging_input']"),
	ORDERING_BMT_STATUS_TRACKER_TABLE_PAGE_LIST_DROPDOWN_XPATH("//div[@class='dataTables_length']"),
	ORDERING_BMT_STATUS_TRACKER_ADDNEW_EDIT_PAGE_TITLE_XPATH("//div[@class='subscreen-title']"),
	ORDERING_BMT_STATUS_TRACKER_ADDNEW_PAGE_FORM_XPATH("//div[@id='entity-ui']//div[@id='data']"),
	ORDERING_BMT_STATUS_TRACKER_ADDNEW_CORP_CODE_XPATH("//select[@id='corpCode']"),
	ORDERING_BMT_STATUS_TRACKER_ADDNEW_REASON_CODE_CATEGORY_XPATH("//select[@id='reasonCodeCategory']"),
	ORDERING_BMT_STATUS_TRACKER_ADDNEW_INTERNAL_CODE_XPATH("//input[@id='internalCode']"),
	ORDERING_BMT_STATUS_TRACKER_ADDNEW_EXTERNAL_CODE_XPATH("//input[@id='externalCode']"),
	ORDERING_BMT_STATUS_TRACKER_ADDNEW_EXTERNAL_CODE_DESC_XPATH("//input[@id='externalCodeDescription']"),
	ORDERING_BMT_STATUS_TRACKER_LAST_UPDATED_BY_XPATH("//span[@id='lastUpdatedBy']"),
	ORDERING_BMT_STATUS_TRACKER_LAST_UPDATED_DATE_XPATH("//span[@id='lastUpdateDate']"),
	ORDERING_BMT_STATUS_TRACKER_SAVE_NEW_RECORD_XPATH("//div[@id='save-entity']"),
	ORDERING_BMT_STATUS_TRACKER_DELETE_RECORD_XPATH("//div[@id='delete-entity']"),
	ORDERING_BMT_STATUS_TRACKER_DELETE_DIALOGBOX_XPATH("//div[@id='new-modal']//div[@class='modal-content']"),
	ORDERING_BMT_STATUS_TRACKER_DELETE_DIALOGBOX_YES_BUTTON_XPATH("//div[@id='new-modal']//button[contains(@class,'primary')]"),
	ORDERING_BMT_STATUS_TRACKER_CANCEL_RECORD_XPATH("//div[@id='cancel-entity']"),
	ORDERING_BMT_STATUS_TRACKER_CANCEL_DIALOGBOX_XPATH("//div[@id='cancel-modal']//div[@class='modal-content']"),
	ORDERING_BMT_STATUS_TRACKER_CANCEL_DIALOGBOX_YES_BUTTON_XPATH("//div[@id='cancel-modal']//button[contains(@class,'primary')]"),
	ORDERING_BMT_STATUS_TRACKER_DELETE_RELATIONSHIP_RECORD_XPATH("//div[@id='delete-relationship']"),
	ORDERING_BMT_STATUS_TRACKER_DELETE_RELATIONSHIP_DIALOGBOX_XPATH("//div[@id='delete-parent-child-modal']//div[@class='modal-content']"),
	ORDERING_BMT_STATUS_TRACKER_DELETE_RELATIONSHIP_YES_BUTTON_XPATH("//div[@id='delete-parent-child-modal']//button[contains(@class,'primary')]"),
	ORDERING_BMT_STATUS_TRACKER_CANCEL_RELATIONSHIP_RECORD_XPATH("//div[@id='cancel-relationship']"),
	ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_TITLE_XPATH("//div[@id='queue-parameters']//h1"),
	ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_SUBTITLE_XPATH("//div[@id='queue-parameters']//h2"),
	ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_CLIENT_EXCEPTION_TITLE_XPATH("//div[@id='client-exceptions']//h2"),
	ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_SAVE_BUTTON_XPATH("//div[@id='save-parameter-options']"),
	ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_CANCEL_BUTTON_XPATH("//div[@id='cancel-parameter-options']"),
	ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_CHECKBOX_XPATH("//div[@id='queue-parameters']//input[@type='checkbox']"),
	ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_ADD_CLIENT_EXCEPTION_XPATH("//div[@id='client-exceptions']//button[@id='add-client-exception']"),
	ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_DELETE_CLIENT_EXCEPTION_XPATH("//div[@id='client-exceptions']//button[contains(@class, 'delete-exception')]"),
	ORDERING_BMT_STATUS_TRACKER_NUMBER_OF_CLIENT_EXCEPTIONS_XPATH("//div[@id='client-exceptions']//div[@class='client-exceptions__container']"),
	ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_QUEUE_DROPDOWN_XPATH("(//div[@id='client-exceptions']//select)[%s]"),
	ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_TEXTAREA_XPATH("(//div[@id='client-exceptions']//textarea)[%s]"),
	ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_CLIENT_NUMBER_COLUMN_TABLE_HEADING_XPATH("//div[@id='queue-table']//table//tr[1]//th[contains(text(),'Client Number')]"),
	ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_CLIENT_NAME_COLUMN_TABLE_HEADING_XPATH("//div[@id='queue-table']//table//tr[1]//th[contains(text(),'Client Name')]"),
	ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_CLIENT_EXCEPTION_COLUMN_TABLE_HEADING_XPATH("//div[@id='queue-table']//table//tr[1]//th[contains(text(),'Client Exception')]"),
	ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_LAST_UPDATED_BY_COLUMN_TABLE_HEADING_XPATH("//div[@id='queue-table']//table//tr[1]//th[contains(text(),'Last Updated By')]"),
	ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_LAST_UPDATED_DATE_COLUMN_TABLE_HEADING_XPATH("//div[@id='queue-table']//table//tr[1]//th[contains(text(),'Last Updated Date')]"),
	;

	private String value;

	OrderingBMTStatusTrackerPageEnum(String value) {
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