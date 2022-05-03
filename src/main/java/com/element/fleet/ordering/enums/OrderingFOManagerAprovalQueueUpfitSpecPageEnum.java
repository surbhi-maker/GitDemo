package com.element.fleet.ordering.enums;

public enum OrderingFOManagerAprovalQueueUpfitSpecPageEnum {
	
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_BUTTON_CSS("button.multiselect.dropdown-toggle.btn.btn-default.pc-action"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTIONS_CONTAINER_UL_XPATH("//ul[@class='multiselect-container dropdown-menu']"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTIONS_XPATH("//ul[@class='multiselect-container dropdown-menu']/li"),	
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_UPFITSPEC_ID_XPATH(".//label[@title='Upfit Spec ID']/input"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_SPECNAME_XPATH(".//label[@title='Spec Name']/input"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_CLIENT_XPATH(".//label[@title='Client Number']/input"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_SPECTYPENAME_XPATH(".//label[@title='Spec Type Name']/input"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_INTERNALIND_XPATH(".//label[@title='Internal Ind']/input"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_AUTOIND_XPATH(".//label[@title='Auto Ind']/input"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_PRIORITY_XPATH(".//label[@title='priority']/input"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_EXPECTDATE_XPATH(".//label[@title='Expect Date']/input"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_CREATEDBYUSER_XPATH(".//label[@title='Created by User']/input"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_CREATEBYPROG_XPATH(".//label[@title='Created by Programfacturer']/input"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_CREATEBYDATE_XPATH(".//label[@title='Created on Date']/input"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_UPDATEDBYUSER_XPATH(".//label[@title='Updated by User']/input"),
	ORDERING_MANAGER_APPROVAL_UPFITSPEC_TABLE_HEADING_ROWS_XPATH("//div[@id='queue-table_wrapper']//div[@class='dataTables_scrollHead']//table/thead/tr/th")
	;
	
	private String value;
	
	private OrderingFOManagerAprovalQueueUpfitSpecPageEnum(String value) {
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
