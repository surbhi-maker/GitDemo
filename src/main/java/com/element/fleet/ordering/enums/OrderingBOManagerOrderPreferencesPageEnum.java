package com.element.fleet.ordering.enums;

public enum OrderingBOManagerOrderPreferencesPageEnum {

	ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_FLEET_SPEC_GROUPS_CSS("nav#navbar-preferences a[href='#section-fleet-spec-groups']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_DRIVER_GROUPS_CSS("nav#navbar-preferences a[href='#section-driver-groups']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_ASSOCIATIONS_CSS("nav#navbar-preferences a[href='#section-associations']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_APPROVAL_RULES_CSS("nav#navbar-preferences a[href='#section-approval-rules']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_APPROVER_COMMUNICATIONS_RULES_CSS("nav#navbar-preferences a[href='#section-approver-communications']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_CORP_DROPDOWN_XPATH("//select[@id='search-corp']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_TEXTFILED_XPATH("//input[@id='search-client']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_SUGGESTION_CSS("div.tt-suggestion"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_SPECS_GROUP_LABEL_XPATH("//div[@id='section-fleet-spec-groups']//h2"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_LABEL_XPATH("//div[@id='section-driver-groups']//h2"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_LABEL_XPATH("//div[@id='section-associations']//h2"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_LABEL_XPATH("//div[@id='section-approval-rules']//h2"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_COMMUNICATIONS_LABEL_XPATH("//div[@id='section-approver-communications']//h2"),	
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_DELETE_XPATH("//div[@class='btn custom-action green btn-shared bulkDelete']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_DELETE_XPATH("//div[@id='fleet-spec-groups-queue']//div[@class='btn custom-action green btn-shared bulkDelete']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_BUTTON_XPATH("//div[@class='btn green btn-shared addRule']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_XPATH("//div[@id='%s']//span[@class='fa fa-search']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_DELETE_XPATH("//div[@id='approval-rules-queue']//span[contains(text(),'Delete')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_APPROVAL_RULES_HEADING_XPATH("//h1[contains(text(),'Approval Rules')]"),	
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_RULE_NAME_CSS("input#group-name"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_START_DATE_LABEL_XPATH("//label[contains(text(),'Start Date:')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_END_DATE_LABEL_XPATH("//label[contains(text(),'End Date:')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_START_DATE_TEXTBOX_XPATH("//input[@id='start-date']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_START_DATE_XPATH("//a[contains(@class,'ui-state-highlight')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_END_DATE_TEXTBOX_XPATH("//input[@id='end-date']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_END_DATE_XPATH("//a[contains(@class,'ui-state-highlight')]/ancestor::tr/following-sibling::tr/td[7]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_LIST_OF_AVAILABLE_RULES_XPATH("//button[@class='btn multiselect dropdown-toggle btn-default']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_MAKE_SEARCH_TEXTBOX_XPATH("//input[@placeholder='Search for a make']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_MAKE_SEARCH_TEXTBOX_HOVER_XPATH("//div[@class='tt-suggestion tt-selectable empty']/strong"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_SELECTOPTION_XPATH("//ul[@class='multiselect-container dropdown-menu']/li/child::*/label[contains(text(),'%s')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_SEQUENTIAL_RADIO_BUTTON_XPATH("//input[@id='sequential']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_NON_EQUENTIAL_RADIO_BUTTON_XPATH("//input[@id='no-sequential']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DENOTES_DELEGATE_XPATH("//div[contains(text(),'* Denotes Delegate')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_BUTTON_XPATH("//div[@class='btn add-contact-group']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_SAVE_BUTTON_XPATH("//div[@id='save-group']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_SIGN_OUT_BUTTON_XPATH("//input[@class='btn btn-danger']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_MAINTENANCE_PAGE_DELETE_BUTTON_XPATH("//div[@id='delete-group']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_SEARCHED_CHECKBOX_XPATH("//div[@id='approval-rules-queue']//input[@name='order-check']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_RULE_NAME_INPUTFLD_XPATH("//div[@id='%s']//input[@name='ruleName']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_DELETE_XPATH("//div[@id='associations-queue']//span[contains(text(),'Delete')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_CSS("div#new-modal.modal.fade.in div.modal-content"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_MAINTENANCE_PAGE_CSS("div#new-modal.modal.fade.in div.modal-content"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS("div#new-modal.modal.fade.in div.modal-body > div"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_MAINTENANCE_PAGE_CSS("div#new-modal.modal.fade.in div.modal-body > div"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_YES_XPATH("//div[@id='new-modal']//button[contains(text(),'Yes')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_NO_XPATH("//div[@id='generic-modal']//button[contains(text(),'No')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_YES_MAINTENANCE_PAGE_XPATH("//div[@id='new-modal']//button[@class='modal-ok-btn']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_NO_MAINTENANCE_PAGE_XPATH("//div[@id='new-modal']//button[@class='modal-close-btn']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_CLEAR_FILTERS_XPATH(" //div[@id='fleet-spec-groups-queue']//span[contains(text(),'Clear Filters')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_CLEAR_FILTERS_XPATH("//div[@id='driver-groups-queue']//div[@class='btn green btn-shared clear-filters']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_CLEAR_FILTERS_XPATH("//div[@id='associations-queue']//div[@class='btn green btn-shared clear-filters']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_CLEAR_FILTERS_XPATH("//div[@id='approval-rules-queue']//span[contains(text(),'Clear Filters')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_CLEAR_FILTERS_XPATH("//div[@id='approver-communications-queue']//div[@class='btn green btn-shared clear-filters']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ID_XPATH("//input[@name='associationId']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH("//input[@name='associationName']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ORDER_LOGGER_XPATH("//div[@id='associations-queue']//input[@name='orderLoggerFullName']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH("//label[text()='%s']/parent::div/input[@name='%s']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_TOGGLE_COLUMNS_XPATH("//div[@id='fleet-spec-groups-queue']//div[@class='col-sm-4 col-md-4 col-lg-4 button-wrapper']//div[@class='btn-group']//div[1]//div[1]//button[1]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCE_DRIVER_GROUPS_TOGGLE_COLUMNS_XPATH("//div[@id='driver-groups-queue']//span[@class='multiselect-selected-text'][contains(text(),'Toggle Columns')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_TOGGLE_COLUMNS_XPATH("//div[@id='associations-queue']//span[@class='multiselect-selected-text'][contains(text(),'Toggle Columns')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_TOGGLE_COLUMNS_XPATH("//div[@id='approval-rules-queue']//span[@class='multiselect-selected-text'][contains(text(),'Toggle Columns')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_TOGGLE_COLUMNS_XPATH("//div[@id='approver-communications-queue']//span[@class='multiselect-selected-text'][contains(text(),'Toggle Columns')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_CHECKED_XPATH("//h2[text()='Fleet Spec Groups']/..//div[@class='btn-group open']//ul//li[@class='active']//label"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_GRID_VIEW_LIST_TABLE_HEADING_XPATH("//div[@id='section-fleet-spec-groups']//div[@class='dataTables_scrollHead']//th"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCE_FLEET_SPEC_GROUP_TOGGLE_CHECKBOX_XPATH("//div[@class='btn-group open']//label[@class='checkbox']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_GENERIC_EXPORT_XPATH("//div[@id='%s']//div[@class='btn blue btn-shared queue-export']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_GENERIC_GRID_VIEW_LIST_TABLE_HEADING_XPATH("//div[@id='%s']//div[@class='dataTables_scrollHead']//th"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATION_FIRST_ROW_XPATH("//div[@id='approver-communications-queue']//tr[@class='odd']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_MAINTENANCE_RULE_APPROVE_DAILY_XPATH("//label[contains(@for,'approvedOrderDaily')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_MAINTENANCE_BACK_BUTTON_XPATH("//div[@id='back']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_ASSOCIATION_NAME_TEXTBOX_XPATH("//input[@id='group-name']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_ADD_ASSOCIATION_BUTTON_XPATH(" //span[contains(text(),'Add Association')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_ORDERLOGGER_TEXTBOX_XPATH("//div[@class='field-wrapper group-name']//span/input[@class='contact-search tt-input']"), 
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_ORDERLOGGER_SELECTION_XPATH("//div[@class='tt-suggestion tt-selectable empty']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_SPEC_GROUP_ADD_RULE_ALERT_POP_UP_SAVEANDCLOSE_XPATH("//span[contains(text(),'Save & Close')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_SAVE_GROUP_XPATH("//div[@id='save-group']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SEARCHED_DRIVER_GROUP_CHECKBOX_XPATH("//div[@id='associations-queue']//input[@name='order-check']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMM_SAVEANDCLOSE_XPATH("//span[contains(text(),'Save & Close')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_ADD_GROUP_BUTTON_XPATH("//div[@id='add-group']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_FLEETSPEC_ADD_GROUP_BUTTON_XPATH("//button[@id='btn-view-available-fleet-spec-group']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_DRIVERGRP_ADD_GROUP_BUTTON_XPATH("//button[@id='btn-view-available-driver-group']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_FLEETSPEC_FIRSTROW_CHECKBOX_XPATH("(//table[@id='non-fleet-spec-group-member-table']//tr[1]//input[@type='checkbox'])[1]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_DRIVERGRP_FIRSTROW_CHECKBOX_XPATH("(//table[@id='non-driver-group-member-table']//tr[1]//input[@type='checkbox'])[1]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_FLEETSPEC_NDA_XPATH("//table[@id='non-fleet-spec-group-member-table']//tr[1]//td[1]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_DRIVERGRP_NDA_XPATH("//table[@id='non-driver-group-member-table']//tr[1]//td[1]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SEARCHED_ROW_XPATH("//div[@id='associations-queue']//tr[@class='odd']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_DRIVERGRP_DELETE_XPATH("(//table[@id='driver-group-member-table']//i[@class='fa fa-trash'])[1]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_FLEETSPEC_DELETE_XPATH("(//table[@id='fleet-spec-group-member-table']//i[@class='fa fa-trash'])[1]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_USER_ID_XPATH("//div[@class='contact-group'][position]//input[@class='contact-search-userid tt-input']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADDED_CONTACT_USER_ID_XPATH("//div[@class='contact-group'][position]/div[@class='contacts-wrapper']//span[@class='user-tag']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_USER_ID_HOVER_XPATH("//div[@class='tt-menu tt-open']//div[@class='tt-dataset tt-dataset-contactSearch-userid']//div[@class='user-info']/span[@class='user-id']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_HEADING_XPATH("//h1[contains(text(),'Driver Groups')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_GROUP_NAME_TEXTBOX_ID("group-name"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_ADD_DRIVERS_BUTTON_ID("btn-view-available-specs"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_EMPLOYEE_ID_TEXTBOX_NAME("empId"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_SEARCH_BUTTON_CSS("div.btn.green.btn-shared.search"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_CLEAR_FILTER_BUTTON_CSS("div.btn.green.btn-shared.clear-filters"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_TABLE_ROWS_CSS("table#queue-table >tbody > tr"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_TABLE_ROWS_CHECKBOX_XPATH(".//td[@class='checkbox desc']/input"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_SAVE_BUTTON_ID("save-group"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELD_XPATH("//div[@id='section']//input[@name='textfield']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_BUTTON_XPATH("//div[@id='section']//div[contains(@class,'buttonClass')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_FIRST_ROW_TABEL_XPATH("//div[@id='section']//table[@id='queue-table']/tbody/tr/td[@class='checkbox desc']/input"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_FIRST_ROW_COLUMN_TABEL_XPATH("//div[@id='section']//table[@id='queue-table']/tbody/tr/td[@data-name='column']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_BUTTON_ID("add-group"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_DELETE_BUTTON_ID("delete-group"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_GROUP_ADD_GROUP_HEADING_XPATH("//h1[contains(text(),'Fleet Spec Groups')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_GROUP_NAME_TEXTBOX_XPATH("//form[@id='fleet-spec-maintenance']//input[@id='group-name']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_SPEC_ID_TEXTBOX_XPATH("//form[@id='fleet-spec-maintenance']//input[@id='fleet-spec-id-search']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_SPEC_SELECT_CHECKBOX_XPATH("//input[@class='js-add-checkbox']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_SPEC_SAVE_BUTTON_XPATH("//button[@id='save-group']"),
	ORDERING_FLEET_SPEC_SPEC_ID_XPATH("//table[@id='order-specs-table']//td[@data-spec-id]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_CHECKALL_CHECKBOX_XPATH("//div[@id='approval-rules-queue']//div[@class='dataTables_scrollHead']//input[@name='check-all']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_DELETERULES_BUTTON_XPATH("//div[@class='btn custom-action green btn-shared bulkDelete']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_RULENAME_XPATH("//td[@data-name='ruleName']//span"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_NEXTMONTH_PICKER_XPATH("//a[@class='ui-datepicker-next ui-corner-all']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DAYS_CALENDAR_XPATH("//a[@class='ui-state-default']"),
	;
	
	private String value;

	OrderingBOManagerOrderPreferencesPageEnum(String value) {
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
