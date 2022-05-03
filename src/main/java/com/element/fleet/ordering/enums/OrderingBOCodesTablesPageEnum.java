package com.element.fleet.ordering.enums;

public enum OrderingBOCodesTablesPageEnum {
	
	ORDERING_BO_CT_CODES_TABLE_HEADING_XPATH("//div[@id='queues']//h1[@class='subtitle']"),
	ORDERING_BO_CT_CODES_TABLE_XPATH("//li[@class='codesTables']//span"),
	ORDERING_BO_CT_CODES_TABLE_TITLE_MESSAGE_XPATH("//h1[@class='title']"),
	ORDERING_BO_CT_CODES_TABLE_TITLE_MESSAGE_VALUE("Manage Codes"),
	ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_XPATH("//a[@class='add']"),
	ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_TITLE_XPATH("//div[@class='subscreen-title']"),
	ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_TITLE_VALUE("Add New Type"),
	ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_CODE_ID("code-input"),
	ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_NAME_ID("name-input"),
	ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_SOURCE_ID("source-input"),
	ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_BUSINESS_OBJECT_ID("businessObject"),
	ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_DIALECTS_XPATH("//div[@class='dialects-title']//span"),
	ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_DIALECTS_ID("dialect"),
	ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_INTERNAL_LABEL_ID("internalLabel"),
	ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_EXTERNAL_LABEL_ID("externalLabel"),
	ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_SAVE_CLOSE_XPATH("//button[@class='modal-save-close']"),
	ORDERING_BO_CT_CODES_TABLE_SEARCH_XPATH("//div[@id='codesTable_filter']//input"),	
	ORDERING_BO_CT_CODES_TABLE_CODE_COLUMN_XPATH("//th[contains(text(),'Code')][@aria-controls='codesTable']"),
	ORDERING_BO_CT_CODES_TABLE_NAME_COLUMN_XPATH("//th[contains(text(),'Name')][@aria-controls='codesTable']"),
	ORDERING_BO_CT_CODES_TABLE_TYPE_SOURCE_COLUMN_XPATH("//th[contains(text(),'Type Source')][@aria-controls='codesTable']"),
	ORDERING_BO_CT_CODES_TABLE_BUSINESS_OBJECT_COLUMN_XPATH("//th[contains(text(),'Business Object')][@aria-controls='codesTable']"),
	ORDERING_BO_CT_CODES_TABLE_SEARCHED_CODE_LIST_XPATH("//th[contains(text(),'Code')][@aria-controls='codesTable']//following::tr//td[2]"),
	ORDERING_BO_CT_CODES_TABLE_SEARCHED_NAME_LIST_XPATH("//th[contains(text(),'Code')][@aria-controls='codesTable']//following::tr//td[3]"),
	ORDERING_BO_CT_CODES_TABLE_SEARCHED_TYPE_SOURCE_LIST_XPATH("//th[contains(text(),'Code')][@aria-controls='codesTable']//following::tr//td[4]"),
	ORDERING_BO_CT_CODES_TABLE_SEARCHED_BUSINESS_OBJECT_LIST_XPATH("//th[contains(text(),'Code')][@aria-controls='codesTable']//following::tr//td[5]"),
	ORDERING_BO_CT_CODES_TABLE_EDIT_LINK_XPATH("//a[@data-action='edit']"),
	ORDERING_BO_CT_CODES_TABLE_PLUS_SYMBOL_XPATH("//th[contains(text(),'Code')][@aria-controls='codesTable']//following::tr//td[1]//button"),
	ORDERING_BO_CT_CODES_TABLE_CODE_VALUES_TABLE_XPATH("//th[contains(text(),'Code Values')]"),
	ORDERING_BO_CT_CODES_TABLE_ADD_NEW_VALUE_XPATH("//th[@class='items-irregular']//a[@data-target='value']"),
	ORDERING_BO_CT_ADD_NEW_VALUE_CODE_XPATH("//input[@id='code']"),
	ORDERING_BO_CT_ADD_NEW_VALUE_NAME_XPATH("//input[@id='name']"),
	ORDERING_BO_CT_VALUE_EFFECTIVE_DATE_CAL_XPATH("//label[contains(text(),'Effective Date')]//following::button[1]"),	
	ORDERING_BO_CT_VALUE_EFFECTIVE_DATE_XPATH("//td[@class=' ui-datepicker-days-cell-over  ui-datepicker-today']//a"),	
	ORDERING_BO_CT_VALUE_DESCRIPTION_ID("description"),
	ORDERING_BO_CT_CODES_TABLE_TYPE_DIALECTS_SUB_TYPE_XPATH("//label[contains(text(),'Dialects')]//following::select[@id='dialect']"),
	ORDERING_BO_CT_CODES_TABLE_TYPE_SUB_INTERNAL_LABEL_XPATH("//label[contains(text(),'Dialects')]//following::input[@id='internalLabel']"),
	ORDERING_BO_CT_CODES_TABLE_TYPE_SUB_EXTERNAL_LABEL_XPATH("//label[contains(text(),'Dialects')]//following::input[@id='externalLabel']"),
	ORDERING_BO_CT_CODES_TABLE_TYPE_SUB_SEARCH_XPATH("//th[contains(text(),'Code Values')]//preceding::input[@type='search'][1]"),
	ORDERING_BO_CT_CODES_TABLE_SEARCHED_CODE_LIST_SUB_TYPE_XPATH("//th[contains(text(),'Code Values')]//following::th[contains(text(),'Code')]//following::tr//td[2]"),
	ORDERING_BO_CT_CODES_TABLE_SEARCHED_NAME_LIST_SUB_TYPE_XPATH("//th[contains(text(),'Code Values')]//following::th[contains(text(),'Code')]//following::tr//td[3]"),
	ORDERING_BO_CT_CODES_TABLE_EDIT_LINK_SUB_TYPE_XPATH("//th[contains(text(),'Code Values')]//following::a[@data-action='edit']"),
	ORDERING_BO_CT_CODES_TABLE_CODE_HEADER_SUB_TYPE_XPATH("//th[contains(text(),'Code Values')]//following::th[contains(text(),'Code')]"),
	;
	
	private String value;

	OrderingBOCodesTablesPageEnum(String value) {
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
