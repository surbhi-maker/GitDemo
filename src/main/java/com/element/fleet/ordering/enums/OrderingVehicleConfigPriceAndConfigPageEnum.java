package com.element.fleet.ordering.enums;

public enum OrderingVehicleConfigPriceAndConfigPageEnum {

	ORDERING_VEHICLE_CONFIG_SPEC_SAVE_DROPDOWN_ID("dropdown-menu-save"),
	ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_BUTTON_XPATH("//a[@id='spec-btn-save-list']"),
	ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_ACTION_XPATH("//button[@class='text-button text-button--overflow js-row-menu-button']"),
	ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH("//input[@id='conf-save-name']"),
	ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_XPATH("//button[@id='modal-accept-btn']"),
	ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_ID("modal-close-btn"),
	ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH("//button[@id='search-by-filters-btn']"),
	ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH("//select[@name='fromScratch[vcle__make]']//following-sibling::span//input"),
	ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH("//select[@name='fromScratch[vcle__modelDesc]']//following-sibling::span//input"),
	ORDERING_VEHICLE_CONFIG_FLEET_SPEC_VEHICLE_TABLE_ID("scratch-table_wrapper"),
	ORDERING_VEHICLE_CONFIG_FLEET_SPEC_SELECT_SEARCHED_VEHICLE_XPATH("//td[@class='sorting_1']"),
	ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_AUDIT_STATUUS_ID("audit-pending-0"),
	ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS("js-fleet-status"),
	ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_ELEMENT_REVIEW_STATUS_ID("element-reviewed-approved-0"),
	ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_STATUS_ID("pending-customer-approval-0"),
	ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_ADD_EXTERNAL_APPROVER_XPATH("//label[@data-toggle='tooltip']"),
	ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_MODEL_POPUP_SUBMIT_BUTTON_ID("modal-ok-btn"),
	;

	private String value;

	OrderingVehicleConfigPriceAndConfigPageEnum(String value) {
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
