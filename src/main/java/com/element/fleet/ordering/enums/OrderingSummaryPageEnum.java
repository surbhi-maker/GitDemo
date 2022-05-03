package com.element.fleet.ordering.enums;

public enum OrderingSummaryPageEnum {	

	ORDERING_SUMMARY_GENERAL_ORDER_SECTION_XPATH("//div[@id='summary-tab-startHere']"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_XPATH("//div[@id='summary-tab-titleReg']"),
	ORDERING_SUMMARY_SECTION_EXPAND_BUTTON_CSS("span.pull-right.glyphicon.glyphicon-plus"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_OWNER_NAME_XPATH(".//div[@id='titleRegInfo']/div[1]/div[1]/div[2]/p"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_OWNER_ADDRESS_LINE_XPATH(".//div[@id='titleRegInfo']/div[1]/div[1]/div[3]/p"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_PLATE_TYPE_XPATH(".//div[@id='titleRegInfo']/div[2]/div[1]/p"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTERED_OWNER_NAME_XPATH(".//div[@id='titleRegInfo']/div[1]/div[2]/div[2]/p"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTERED_OWNER_ADDRESS_LINE_XPATH(".//div[@id='titleRegInfo']/div[1]/div[2]/div[3]/p"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTERED_WHO_TO_TITLE_XPATH(".//div[@id='titleRegInfo']/div[2]/div[2]/p"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_LEINHOLDER_NAME_XPATH(".//div[@id='titleRegInfo']/div[1]/div[3]/div[2]/p"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_LEINHOLDER_ADDRESS_LINE_XPATH(".//div[@id='titleRegInfo']/div[1]/div[3]/div[3]/p"),
	ORDERING_SUMMARY_ORDER_ID_TEXT_ID("order__id"), 
	ORDERING_SUMMARY_POST_SUBMIT_ORDERID_ID("generalOrder__orderID"),
	ORDERING_SUMMARY_POST_SUBMIT_CLIENTUNIT_ID("generalOrder__clientNUnit"),
	ORDERING_SUMMARY_EXIT_BUTTON_XPATH("//button[contains(@class, 'close') and contains(text(), 'Exit')]"),
	ORDERING_SUMMARY_SUBMIT_BUTTON_ID("createOrderSubmitId"),
	ORDERING_SUMMARY_SUBMIT_OK_XPATH("//button[@id='modal-ok-btn' and @data-dismiss='modal']"),
	ORDERING_SUMMARY_SUBMIT_CONFIRMATION_POP_LOG_NUMBER_ID("log"),
	ORDERING_SUMMARY_SUBMIT_CONFIRMATION_CLOSE_ID("close-submitted-modal"),
	ORDERING_SUMMARY_PRE_EXPORT_PDF_CSS("button.pull-right.btn.export__pdf"),
	ORDERING_SUMMARY_POST_EXPORT_PDF_CSS(".pull-right.btn.export__pdf"),
	ORDERING_SUMMARY_POST_CLOSE_CSS("div.pull-right.btn.close-modalWrapper-x"),
	ORDERING_SUMMARY_SUBMISSION_POP_UP_CSS("div.submitted-modal"),
	ORDERING_SUMMARY_SUBMISSION_POP_UP_HEADER_CSS("div.submitted-modal > div.modal-header"),
	ORDERING_SUMMARY_SUBMIT_CONFIRMATION_POPUP_MESSAGE_CSS("div.submitted-modal > div.modal-body div#orderMessage"),
	ORDERING_SUMMARY_SUBMIT_CONFIRMATION_POP_BACK_TO_HOME_PAGE_CSS("#createOrder__content > div.submitted-modal > div.modal-footer > a.pull-left"),
	ORDERING_SUMMARY_GENERALORDER_INFO_ID("coreInfo"),
	ORDERING_SUMMARY_DRIVER_TAB_ID("summary-tab-driver"),
	ORDERING_SUMMARY_DRIVER_TAB_CSS("div[data-target='#driverInfo']"),
	ORDERING_SUMMARY_CONTACT_INFORMATION_TAB_CSS("div[data-target='#contactInfo']"),
	ORDERING_SUMMARY_DRIVER_TAB_XPATH("//div[@id='summary-tab-driver']//div[contains(text(),'Driver')]//span"),
	ORDERING_SUMMARY_USEDUNIT_TAB_ID("summary-tab-usedVehicle"),
	ORDERING_SUMMARY_VEHICLE_TAB_ID("summary-tab-vehicleSpec"),
	ORDERING_SUMMARY_VEHICLE_TAB_CSS("div[data-target='#vehicleSpecInfo']"),
	ORDERING_SUMMARY_VEHICLE_TAB_XPATH("//div[@id='summary-tab-vehicleSpec']//div[contains(text(),'New Vehicle Specification')]//span"),
	ORDERING_SUMMARY_DEALER_TAB_ID("summary-tab-dealer"),
	ORDERING_SUMMARY_DEALER_TAB_XPATH("//div[@id='summary-tab-dealer']//span[contains(text(),'Delivering Dealer')]"),
	ORDERING_SUMMARY_BILLING_TAB_ID("summary-tab-billing"),
	ORDERING_SUMMARY_BILLING_TAB_CSS("div[data-target='#billingInfo']"),
	ORDERING_SUMMARY_TITLEREGISTRATION_TAB_ID("summary-tab-titleReg"),
	ORDERING_SUMMARY_TITLEREGISTRATION_TAB_CSS("div[data-target='#titleRegInfo']"),
	ORDERING_SUMMARY_INSURANCE_TAB_ID("summary-tab-insurance"),
	ORDERING_SUMMARY_INSURANCE_TAB_CSS("div[data-target='#insuranceInfo']"),
	ORDERING_SUMMARY_UPFITTING_TAB_CSS("div[data-target='#vehicleUpfitInfo']"),
	ORDERING_SUMMARY_USED_VEHICLE_TAB_CSS("div[data-target='#usedVehicleInfo']"),
	ORDERING_SUMMARY_CONTACT_INFO_TAB_CSS("div[data-target='#contactInfo']"),
	ORDERING_SUMMARY_REQUESTED_DELIVERING_DEALER_TAB_CSS("div[data-target='#dealerInfo']"),
	ORDERING_SUMMARY_STANDARD_EQUPMENT_TAB_ID("summary-tab-standardEquipment"),
	ORDERING_SUMMARY_UPFIT_TAB_ID("summary-tab-vehicleUpfit"),
	ORDERING_SUMMARY_DIO_TAB_ID("summary-tab-VehicleDIO"),
	ORDERING_SUMMARY_DEALER_NAME_ID("supplierName"),
	ORDERING_SUMMARY_DEALER_MANDLRCODE_ID("manufactDNum"),
	ORDERING_SUMMARY_CUSTOM_DRIVER_FIELDS_CSS("div[data-target='#customFields']"),
	ORDERING_SUMMARY_VEHICLE_SECTION_FLEET_SPEC_NAME_XPATH("//label[contains(text(),'Price & Config Name')]//following-sibling::p"),
	ORDERING_SUMMARY_BILLING_LEASE_TERM_ID("billing__leaseTerm"),
	ORDERING_SUMMARY_BILLING_ASSETID_XPATH("//*[@id='billing__clientUnitNumber']"),
	ORDERING_SUMMARY_BILLING_CLIENTPONUMBER_XPATH("//*[@id='billing__clientPoNumber']"),
	ORDERING_SUMMARY_SUCCESSFULLY_SUBMITTED_POP_UP_LOG_NUMBER_ID("generalOrder__logNumber"),
	ORDERING_SUMMARY_SUCCESSFULLY_SUBMITTED_POP_UP_CORP_CD_ID("corp"),
	ORDERING_SUMMARY_SUCCESSFULLY_SUBMITTED_POP_UP_FLEET_ID("fleet"),
	ORDERING_SUMMARY_SUCCESSFULLY_SUBMITTED_POP_UP_UNIT_NUMBER_ID("unit"),
	ORDERING_SUMMARY_GENERAL_INFO_TAB_ID("summary-tab-startHere"),
	ORDERING_SUMMARY_TAB_SECTIONS_XPATH("//div[@id='summary-tab']//div[@id = '%s']"),
	ORDERING_SUMMARY_TOTAL_ELEMENTS_PRESENT_IN_SECTION_XPATH("//div[@id='%s']//label"),
	ORDERING_SUMMARY_ELEMENTS_VALUE_IN_SECTION_XPATH("//div[@id='%s']//label[contains(text(), '%s')]//following-sibling::p"),
	ORDERING_SUMMARY_GENERAL_INFO_STAUS_CSS("div.collapse-sections.SummaryStartHereView div.section__body > div:nth-child(2) > div:nth-child(1) > p"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_VEHICLETEXT_XPATH("//b[contains(., 'Vehicle' )]/parent::p"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_NAME_XPATH("//div[contains(text(),'Title Information')]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_OWNER_XPATH("//div[contains(text(),'Title Information')]/following::p[1]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_FEDERALID_XPATH("//div[contains(string(),'Title Information')]/./div/p[contains(string(),'Federal')]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_STATEID_XPATH("//div[contains(string(),'Title Information')]/./div/p[contains(string(),'State')]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_TAXEXEMPT_XPATH("//div[contains(string(),'Title Information')]/./div/p[contains(string(),'Tax')]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_FEDERALID_LABEL_XPATH("//div[contains(text(),'Title Information')]//..//strong[contains(text(),'Federal')]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_STATEID_LABEL_XPATH("//div[contains(text(),'Title Information')]//..//strong[contains(text(),'State')]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_TAXEXEMPT_LABEL_XPATH("//div[contains(text(),'Title Information')]//..//strong[contains(text(),'Tax')]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTRATION_NAME_XPATH("//div[contains(text(),'Registration Information')]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTRATION_OWNER_XPATH ("//div[contains(text(),'Registration Information')]/following::p[1]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTRATION_FEDERALID_XPATH("//div[contains(string(),'Registration')]/./div/p[contains(string(),'Federal')]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTRATION_STATEID_XPATH("//div[contains(string(),'Registration')]/./div/p[contains(string(),'State')]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTRATION_FEDERALID_LABEL_XPATH("//div[contains(text(),'Registration')]//..//strong[contains(text(),'Federal')]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTRATION_STATEID_LABEL_XPATH("//div[contains(text(),'Registration')]/..//strong[contains(text(),'State')]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_LIENHOLDER_ADDRESS_XPATH("//div[contains(text(),'Lienholder Information')]/following::p[2]"),
	ORDERING_SUMMARY_TITLE_AND_LICENSE_SECTION_STATE_ABBREV_XPATH("//label[contains(text(),'Abbrev')]/following::p[1]"),
	ORDERING_SUMMARY_TITLE_AND_LICENSE_SECTION_STATE_VALUE_XPATH("//label[contains(text(),'State')]/following::p[1]"),
	ORDERING_SUMMARY_CANCEL_BUTTON_XPATH("//div[@class='pull-left btn cancel']"),
	ORDERING_SUMMARY_DIO_SECTION_QUANTITY_XPATH("//td[@class='quantity sorting_1']"),
	ORDERING_SUMMARY_DIO_SECTION_OPTIONCODE_XPATH("//td[@class='optionCode']"),
	ORDERING_SUMMARY_DIO_SECTION_DESCRIPTION_XPATH("//td[@class='optionDesc']"),
	ORDERING_SUMMARY_DIO_SECTION_MAXPRICE_XPATH("//td[@class='maxPriceAmt']"),
	ORDERING_SUMMARY_DIO_SECTION_TABLE_XPATH("//table[@id='dealerInstalledTable']"),
	ORDERING_SUMMARY_DIO_SECTION_XPATH("//div[@id='summary-tab-VehicleDIO']"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_LIENHOLDER_FEDERALID_XPATH("//div[contains(string(),'Lienholder')]/./div/p[contains(string(),'Federal')]"),
	ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_LIENHOLDER_STATEID_XPATH("//div[contains(string(),'Lienholder')]/./div/p[contains(string(),'State')]"),
	ORDERING_SUMMARY_TITLE_AND_LIENHOLDER_SECTION_LIENHOLDER_FEDERALID_LABEL_XPATH("//div[contains(text(),'Lienholder')]/..//strong[contains(text(),'Federal')]"),
	ORDERING_SUMMARY_TITLE_AND_LIENHOLDER_SECTION_LIENHOLDER_STATEID_LABEL_XPATH("//div[contains(text(),'Lienholder')]/..//strong[contains(text(),'State')]"),
	ORDERING_SUMMARY_TITLE_AND_LICENSE_SECTIONS_XPATH("//div[@id='titleRegInfo']//div[@class='col-md-4 inline-block']//p"),
	ORDERING_SUMMARY_GENERAL_ORDER_HEADING_CSS("div#summary-tab-startHere > div.section__header"),
	ORDERING_SUMMARY_DRIVER_HEADING_CSS("div#summary-tab-driver > div.section__header"),
	ORDERING_SUMMARY_NEW_VEHICLE_SPECIFICATION_HEADING_CSS("div#summary-tab-vehicleSpec > div.section__header"),
	ORDERING_SUMMARY_DELIVERING_DEALER_HEADING_CSS("div#summary-tab-dealer > div.section__header"),
	ORDERING_SUMMARY_BILLING_HEADING_CSS("div#summary-tab-billing > div.section__header"),
	ORDERING_SUMMARY_TITLE_AND_LICENSE_HEADING_CSS("div#summary-tab-titleReg > div.section__header"),
	ORDERING_SUMMARY_INSURANCE_HEADING_CSS("div#summary-tab-insurance > div.section__header"),
	ORDERING_SUMMARY_STANDARD_EQUIPMENT_HEADING_CSS("div#summary-tab-standardEquipment > div.section__header"),
	ORDERING_SUMMARY_NEW_VEHICLE_SPECIFICATION_TABLE_ID("DataTables_Table_0_wrapper"),
	ORDERING_SUMMARY_NEW_VEHICLE_SPECIFICATION_TABLE_HEADER_XPATH("//*[@id='DataTables_Table_0']/thead/tr"),
	ORDERING_SUMMARY_NEW_VEHICLE_SPECIFICATION_TABLE_BODY_XPATH("//*[@id='DataTables_Table_0']/tbody"),
	;

	private String value;

	OrderingSummaryPageEnum(String value) {
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
