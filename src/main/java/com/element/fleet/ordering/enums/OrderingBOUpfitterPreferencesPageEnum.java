package com.element.fleet.ordering.enums;

public enum OrderingBOUpfitterPreferencesPageEnum {
	ORDERING_BO_BMT_UPFITTER_PREFERENCES_ADD_XPATH("//div[contains(@class, 'addNew')]"),
	ORDERING_BO_BMT_UPFITTER_PREFERENCES_BACK_TO_QUEUE_VIEW_ID("browser-back"),
	ORDERING_BO_BMT_SEARCH_UPFITTER_ID_NAME("upfitterCode"),
	ORDERING_BO_BMT_SEARCH_BUTTON_CSS("div.btn.green.btn-shared.search"),
	ORDERING_BO_BMT_UPFITTER_PREFERENCES_SEARCHRESULT_XPATH("//*[@id='queue-table']/tbody/tr[1]"),
	ORDERING_BO_BMT_UPFITTER_PREFERENCES_ADD_UPFITTER_PREFERENCES_DELETE_ID("delete-entity"),
	ORDERING_BO_BMT_UPFITTER_PREFERENCES_CONFIRM_DELETE_XPATH("//div[@id='new-modal']//button[contains(text(), 'Yes')]"),
	ordering_BO_BMT_ADD_UPFITTER_PREFERENCE_SUPPLIER_FIELD_ID("elementDlrNo"),
	ORDERING_BO_BMT_UPFIT_PREFERENCE_ADD_UPFITTERSPECSEARCH_FIRSTOPTION_CSS("div.tt-dataset-elementDlrNo>div"),
	ORDERING_BO_BMT_UPFITER_PREFERENCE_ADD_LINE_ITEM_XPATH("//a[contains(text(), 'Add Line Item')]"),
	ORDERING_BO_BMT_UPFITER_PREFERENCE_SEARCH_BUTTON_ID("filter-entity"),
	ORDERING_BO_BMT_ADD_UPFITTER_PREFERENCE_COMMUNICATION_TYPE_ID("communicationId0"),
	ORDERING_BO_BMT_ADD_UPFITTER_PREFERENCE_COMMUNICATION_TYPE_NEWADD_ID("communicationId1"),
	ORDERING_BO_BMT_ADD_UPFITTER_PREFERENCE_PREFERENCE_ID("communicationPreference0"),
	ORDERING_BO_BMT_ADD_UPFITTER_PREFERENCE_PREFERENCE_NEWADD_ID("communicationPreference1"),
	ORDERING_BO_BMT_ADD_UPFITTER_PREFERENCES_SAVE_ID("save-entity"),
	ORDERING_BO_BMT_UPFITTER_PREFERENCES_COMMUNICATION_NAME("codeDescription"),
	Ordering_BO_BMT_UPFITTER_PREFERENCES_SEARCH_RESULT_ROW_XPATH("//table[@id='queue-table']/tbody/tr/td"),
	;
	
	private String value;

	private OrderingBOUpfitterPreferencesPageEnum(String value) {
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
