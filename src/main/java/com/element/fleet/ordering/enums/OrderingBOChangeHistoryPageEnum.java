package com.element.fleet.ordering.enums;

public enum OrderingBOChangeHistoryPageEnum {
	ORDERING_BO_CHANGE_HISTORY_PAGE_TITLE_XPATH("//h1[@class='subtitle' and text()='History']"),
	ORDERING_BO_CHANGE_HISTORY_SEARCH_BUTTON_XPATH("//button[@id='search-by-filters-btn']"),
	ORDERING_BO_CHANGE_HISTORY_BUSINESS_OBJ_SELECT_XPATH("//select[@id='js-businessObj-multi-select']"),
	ORDERING_BO_CHANGE_HISTORY_CLEAR_FILTER_XPATH("//button[@id='clear-all-filters-btn']"),
	ORDERING_BO_CHANGE_HISTORY_CLEAR_TOGGLE_FILTER_XPATH("//button[@id='toggle-filter']"),
	ORDERING_BO_CHANGE_HISTORY_TABLE_ROW_XPATH("(//tr[@class='data-table__header-row'])[1]"),
	ORDERING_BO_CHANGE_HISTORY_BUSINESS_KEY_LIST_XPATH("(//tr[@class='data-table__header-row'])[1]"),
	ORDERING_BO_CHANGE_HISTORY_EXPORT_BUTTON_XPATH("//button[@id='export-CSV-button']"),
	ORDERING_BO_CHANGE_HISTORY_SCROLL_BODY_XPATH("//div[@class='dataTables_scrollBody']"),
	ORDERING_BO_CHANGE_HISTORY_TIMESTAMP_XPATH("//td[@data-name='timestamp']"),
	ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH("//label[text()='%s']//following-sibling::input"),
	ORDERING_BO_CHANGE_HISTORY_BUSINESS_OBJECT_DROPDOWN_TOGGLE_XPATH("//select[@id='js-businessObj-multi-select']//following-sibling::div//button[@data-toggle='dropdown']"),
	ORDERING_BO_CHANGE_HISTORY_DROPDOWN_TOGGLE_ITEMS_XPATH("//label[@class='checkbox']//input[@value='%s']"),
	ORDERING_BO_CHANGE_HISTORY_CHANGE_DROPDOWN_TOGGLE_XPATH("//select[@id='js-change-multi-select']//following-sibling::div//button[@data-toggle='dropdown']"),
	ORDERING_BO_CHANGE_HISTORY_BUSINESS_OBJECT_COLUMN_LIST_XPATH("//td[@data-name='businessObj']"),
	ORDERING_BO_CHANGE_HISTORY_BUSINESS_KEY_COLUMN_LIST_XPATH("//td[@data-name='businessKey']"),
	ORDERING_BO_CHANGE_HISTORY_USERID_COLUMN_LIST_XPATH("//td[@data-name='userId']"),
	ORDERING_BO_CHANGE_HISTORY_TIMESTAMP_COLUMN_LIST_XPATH("//td[@data-name='timestamp']"),
	ORDERING_BO_CHANGE_HISTORY_CHANGE_COLUMN_LIST_XPATH("//td[@data-name='change']"),
	ORDERING_BO_CHANGE_HISTORY_LAST_PAGINATE_BUTTON_XPATH("//span[@class='last paginate_button']"),
	ORDERING_BO_CHANGE_HISTORY_FIRST_PAGINATE_BUTTON_XPATH("//span[@class='first paginate_button']"),
	ORDERING_BO_CHANGE_HISTORY_NEXT_PAGINATE_BUTTON_XPATH("//span[@class='next paginate_button']"),
	ORDERING_BO_CHANGE_HISTORY_PRE_PAGINATE_BUTTON_XPATH("//span[@class='previous paginate_button']"),
	ORDERING_BO_CHANGE_HISTORY_PAGE_ITEMS_OPTIONS_LIST_XPATH("//select[contains(@name,'table-view')]//child::option"),
	ORDERING_BO_CHANGE_HISTORY_PAGE_COLUMN_HEADING_LIST_XPATH("//div[@class='dataTables_scrollHead']//th[contains(@class,'data-table__header-cell')]"),
	ORDERING_BO_CHANGE_HISTORY_PAGE_EXPORT_XPATH("//button[@id='export-CSV-button']"),
	ORDERING_BO_CHANGE_HISTORY_PAGE_BUSINESS_OBJ_HEADING_XPATH("//th[contains(@aria-label,'Business Obj')]"),
	ORDERING_BO_CHANGE_HISTORY_PAGE_UPDATED_IN_HEADING_XPATH("//th[contains(@aria-label,'Updated In')]"),
	ORDERING_BO_CHANGE_HISTORY_UPDATEDIN_COLUMN_LIST_XPATH("//td[@data-name='updatedIn']"),
	ORDERING_BO_CHANGE_HISTORY_HIST_COLUMN_LIST_XPATH("//td[@data-name='history']/a"),
;
	
	private String value;

	OrderingBOChangeHistoryPageEnum(String value) {
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
