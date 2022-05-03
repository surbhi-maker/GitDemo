package com.element.fleet.ordering.enums;

public enum OrderingBillingAndRegistrationPageEnum {

	ORDERING_BILLING_SECTION_LABEL_XPATH("//div[@id='section__billing']//p/..//label"),
	ORDERING_BILLING_SECTION_VALUE_XPATH("//div[@id='section__billing']//label[contains(text(),'%s')]/..//p"),
	ORDERING_BILLING_AND_REGISTRATION_BILLING_NEW_VEHICLE_BREAKDOWN_XPATH("//div[@id='section__billing']//input[@id='new-vehicle-breakdown']"),
	ORDERING_BILLING_AND_REGISTRATION_BILLING_LEASE_TERM_DROPDOWN_XPATH("//div[@id='section__billing']//select[@id='leaseTerm']"),
	ORDERING_BILLING_AND_REGISTRATION_BILLING_ASSET_ID_XPATH("//div[@id='section__billing']//input[@id='client_unitNum']"),
	ORDERING_BILLING_AND_REGISTRATION_BILLING_CLIENT_PURCHASE_ORDER_NUMBER_XPATH("//div[@id='section__billing']//input[@id='purchase_num']"),
	ORDERING_BILLING_AND_REGISTRATION_TLANDR_WHO_TO_TITLE_XPATH("//div[@id='titleLicenseReg__section']//select[@id='whoToTitle']"),
	ORDERING_BILLING_AND_REGISTRATION_TLANDR_PLATE_TYPE_XPATH("//div[@id='titleLicenseReg__section']//select[@id='plateType']"),
	ORDERING_BILLING_AND_REGISTRATION_BILLING_SECTION_ID("billing__section"),
	ORDERING_BILLING_AND_REGISTRATION_CONTRACT_TYPE_DROPDOWN_ID("conType"),
	ORDERING_BILLING_AND_REGISTRATION_CONTRACT_TYPE_DROPDOWN_XPATH("//select[@id='conType']"),
	ORDERING_BILLING_AND_REGISTRATION_LEASE_TERM_DROPDOWN_NAME("leaseTerm"),
	ORDERING_BILLING_AND_REGISTRATION_FUNDING_INDEX_DROPDOWN_NAME("fundingIndex"),
	ORDERING_BILLING_AND_REGISTRATION_PRODUCT_CLASS_ID("billingProductClassId"),
	ORDERING_BILLING_AND_REGISTRATION_WHO_TO_TITLE_ID("whoToTitle"),
	ORDERING_BILLING_AND_REGISTRATION_PLATE_TYPE_ID("plateType"),
	ORDERING_BILLING_AND_REGISTRATION_TITLE_SECTION_ID("titleLicenseReg__section"),
	ORDERING_BILLING_AND_REGISTRATION_BILLING_NEW_VEHICLE_BREAKDOWN_SEARCH_BUTTON_ID("new-vcl-bkdwn-clik"),
	ORDERING_VEHICLE_BREAK_DOWN_SEARCH_ID("new-vcl-bkdwn-clik"),
	ORDERING_VEHICLE_BREAK_DOWN_XPATH("//span[@data-to-level='2']"),
	ORDERING_VEHICLE_BREAK_DOWN_LIST_XPATH("//span[@data-to-level='%s']"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_VEHICLETEXT_XPATH("//b[contains(., 'Vehicle' )]/parent::p"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_NAME_XPATH("//p[contains(text(),'Title')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_OWNER_XPATH ("//p[contains(text(),'Title Information')]/following::p[1]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_ADDRESS_XPATH("//p[contains(text(),'Title Information')]/following::p[2]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_CITYSTATEZIP_XPATH("//p[contains(text(),'Title Information')]/following::p[3]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_FEDERALID_XPATH("//p[contains(string(),'Title')]/../../p[contains(string(),'Federal')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_STATEID_XPATH("//p[contains(string(),'Title')]/../../p[contains(string(),'State')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_TAX_EXEMPTION_NUMBER_XPATH("//p[contains(string(), 'Title')]/../../p[contains(string(),'Tax')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_FEDERALID_LABEL_XPATH("//p[contains(string(),'Title')]/../..//strong[contains(text(),'Federal')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_STATEID_LABEL_XPATH("//p[contains(string(),'Title')]/../..//strong[contains(text(),'State')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_TAX_EXEMPTION_NUMBER_LABEL_XPATH("////p[contains(string(),'Title')]/../..//strong[contains(text(),'Tax')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_NAME_XPATH("//p[contains(text(),'Registration')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_OWNER_XPATH ("//p[contains(text(),'Registration Information')]/following::p[1]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_ADDRESS_XPATH("//p[contains(text(),'Registration Information')]/following::p[2]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_CITYSTATEZIP_XPATH("//p[contains(text(),'Registration Information')]/following::p[3]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_FEDERALID_XPATH("//p[contains(string(), 'Registration')]/../../p[contains(string(),'Federal')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_STATEID_XPATH("//p[contains(string(), 'Registration')]/../../p[contains(string(),'State')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_TAX_EXEMPTION_NUMBER_XPATH("//p[contains(string(), 'Registration')]/../../p[contains(string(),'Tax')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_FEDERALID_LABEL_XPATH("//p[contains(string(), 'Registration')]/../..//strong[contains(text(),'Federal')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_STATEID_LABEL_XPATH("//p[contains(string(), 'Registration')]/../..//strong[contains(text(),'State')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_TAX_EXEMPTION_NUMBER_LABEL_XPATH("//p[contains(string(), 'Registration')]/../..//strong[contains(text(),'Tax')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_NAME_XPATH("//p[contains(text(),'Lienholder')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_ADDRESS_XPATH("//p[contains(text(),'Lienholder Information')]/following::p[2]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_CITYSTATEZIP_XPATH("//p[contains(text(),'Lienholder Information')]/following::p[3]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_FEDERALID_XPATH("//p[contains(string(), 'Lienholder')]/../../p[contains(string(),'Federal')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_STATEID_XPATH("//p[contains(string(), 'Lienholder')]/../../p[contains(string(),'State')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_TAX_EXEMPTION_NUMBER_XPATH("//p[contains(string(), 'Lienholder')]/../../p[contains(string(),'Tax')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_FEDERALID_LABEL_XPATH("//p[contains(string(), 'Lienholder')]/../..//strong[contains(text(),'Federal')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_STATEID_LABEL_XPATH("//p[contains(string(), 'Lienholder')]/../..//strong[contains(text(),'State')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_TAX_EXEMPTION_NUMBER_LABEL_XPATH("//p[contains(string(), 'Lienholder')]/../..//strong[contains(text(),'Tax')]"),
	ORDERING_BILLING_AND_REGISTRATION_TANDL_HEADER_XPATH("//div[@id='section__titleReg']/div[1]"),
	ORDERING_BILLING_AND_REGISTRATION_TLANDR_SECTIONS_HEADER_XPATH("//div[@class='header_reg']");

	private String value;

	OrderingBillingAndRegistrationPageEnum(String value) {
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