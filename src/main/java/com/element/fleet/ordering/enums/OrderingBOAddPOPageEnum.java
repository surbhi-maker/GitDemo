package com.element.fleet.ordering.enums;

public enum OrderingBOAddPOPageEnum {

	ORDERING_BO_ADD_PO_SCREEN_ID("main-section"),	
	ORDERING_BO_ADD_PO_UPFIT_SPEC_SEARCH_ID("upfit-spec-search"),
	ORDERING_BO_ADD_PO_SAVE_ID("save"),
	ORDERING_BO_ADD_PO_FIRST_SEARCH_RESULT_CSS("div.tt-suggestion.tt-selectable empty"),
	ORDERING_BO_ADD_PO_UPFIT_SPEC_NAME_ID("upfit-spec-name"),	
	;

	private String value;

	OrderingBOAddPOPageEnum(String value) {
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
