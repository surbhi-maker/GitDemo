package com.element.fleet.ordering.enums;

public enum OrderingBOHomePageEnum {

	ORDERING_BO_SIDE_MENU_ID("side-menu"),
	ORDERING_BO_QUEUES_MENU_XPATH("//div[@id='side-menu']//span[contains(text(),'Queues')]"),
	ORDERING_BO_EDIT_ORDER_MENU_XPATH("//div[@id='side-menu']//span[contains(text(),'Edit Order')]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_MENU_XPATH("//div[@id='side-menu']//span[contains(text(),'Manager Order Preferences')]"),
	ORDERING_BO_CODES_TABLES_MENU_XPATH("//div[@id='side-menu']//span[contains(text(),'Codes')]"),
	ORDERING_BO_BATCH_PROCESSING_MENU_XPATH("//div[@id='side-menu']//span[contains(text(),'Batch Processing')]"),
	ORDERING_BO_BUSINESS_MAINTAINED_TABLES_MENU_XPATH("//div[@id='side-menu']//span[contains(text(),'Business')]"),
	ORDERING_BO_CHANGE_HISTORY_MENU_XPATH("//div[@id='side-menu']//span[contains(text(),'Change')]"),
	ORDERING_BO_EDIT_ORDER_MENU_HEADING_CLASS("nav-header"),
	ORDERING_BO_EO_ORDER_MAINTENANCE_XPATH("//ul[@id='order']//span[text()='Order Maintenance']"),
	ORDERING_BO_EO_ADD_UPFIT_PURCHASE_ORDER_XPATH("//ul[@id='order']//span[text()='Add Upfit Purchase Order']"),
	ORDERING_BO_EO_INVOICE_ENTRY_XPATH("//ul[@id='order']//span[text()='Invoice Entry']"),
	ORDERING_BO_BATCH_PROCESSING_MENU_HEADING_CLASS("nav-header"),
	ORDERING_BO_BP_MASS_UPDATES_XPATH("//ul[@id='batch']//span[text()='Mass Updates']"),
	ORDERING_BO_BP_BATCH_ORDERING_XPATH("//ul[@id='batch']//span[text()='Batch Ordering']"),
	ORDERING_BO_BP_CREATE_DRIVER_GROUP_XPATH("//ul[@id='batch']//span[text()='Create Driver Group']"),
	ORDERING_BO_BP_DRIVER_GROUP_MAINTENANCE_XPATH("//ul[@id='batch']//span[text()='Driver Group Maintenance']"),
	ORDERING_BACK_OFFICE_LOGOUT_BUTTON_XPATH("//input[@class='btn btn-danger']"),
	ORDERING_BACK_OFFICE_LOGOUT_MESSAGE_XPATH("//h2[contains(@class, 'logout-message')]"),
	ORDERING_BACK_OFFICE_WELCOME_MESSAGE1_XPATH("//span[@id='headerLandingId']"),
	ORDERING_BACK_OFFICE_WELCOME_MESSAGE1_LAST1_CHARACTER_CSS("span#headerLandingId > span:nth-child(8)"),
	ORDERING_BACK_OFFICE_WELCOME_MESSAGE1_LAST2_CHARACTER_CSS("span#headerLandingId > span:nth-child(13)"),
	ORDERING_BACK_OFFICE_WELCOME_MESSAGE1_LAST3_CHARACTER_CSS("span#headerLandingId > span:nth-child(24)"),
	ORDERING_BACK_OFFICE_WELCOME_MESSAGE2_XPATH("//span[@class='letters'][@id='headerLandingId']//following::span[@class='letters']"),
	ORDERING_BACK_OFFICE_WELCOME_MESSAGE2_LAST1_CHARACTER_CSS("div#landing-wrapper > h1.sub-title > span > span.letters > span:nth-child(2)"),
	ORDERING_BACK_OFFICE_WELCOME_MESSAGE2_LAST2_CHARACTER_CSS("div#landing-wrapper > h1.sub-title > span > span.letters > span:nth-child(9)"),
	ORDERING_BACK_OFFICE_WELCOME_MESSAGE2_LAST3_CHARACTER_CSS("div#landing-wrapper > h1.sub-title > span > span.letters > span:nth-child(20)"),
	;

	private String value;

	OrderingBOHomePageEnum(String value) {
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
