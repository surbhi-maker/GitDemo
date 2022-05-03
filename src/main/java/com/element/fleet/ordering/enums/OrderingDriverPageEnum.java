package com.element.fleet.ordering.enums;

public enum OrderingDriverPageEnum {

	ORDERING_DRIVER_ORDER_ID_LABEL_ID("order__id"),
	ORDERING_DRIVER_UNIT_NUMBER_LABEL_ID("unit_No"),
	ORDERING_DRIVER_INFORMATION_FIRST_NAME_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='firstName']"),
	ORDERING_DRIVER_INFORMATION_MIDDLE_NAME_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='middleName']"),
	ORDERING_DRIVER_INFORMATION_LAST_NAME_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='lastName']"),
	ORDERING_DRIVER_INFORMATION_EMPLOYEE_ID_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='employedId']"),
	ORDERING_DRIVER_INFORMATION_EMAIL_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='email']"),
	ORDERING_DRIVER_INFORMATION_PRIMARY_PHONE_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='phoneNumber']"),
	ORDERING_DRIVER_INFORMATION_BREAKDOWN_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='bkdn']"),
	ORDERING_DRIVER_INFORMATION_ADDRESS1_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='addressLine1']"),
	ORDERING_DRIVER_INFORMATION_ADDRESS2_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='addressLine2']"),
	ORDERING_DRIVER_INFORMATION_COUNTY_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='county']"),
	ORDERING_DRIVER_INFORMATION_COUNTRY_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='country']"),
	ORDERING_DRIVER_INFORMATION_CITY_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='city']"),
	ORDERING_DRIVER_INFORMATION_STATE_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='stateProvinceCode']"),
	ORDERING_DRIVER_INFORMATION_ZIPCODE_LABEL_XPATH("//div[@id='section__driver-information']//p[@id='postcode']"),
	ORDERING_GARAGING_ADDRESS_ADDRESS1_INPUT_XPATH("//div[@id='section__garaging-address']//input[@id='addressLine1']"),
	ORDERING_GARAGING_ADDRESS_ADDRESS2_INPUT_XPATH("//div[@id='section__garaging-address']//input[@id='addressLine2']"),
	ORDERING_GARAGING_ADDRESS_COUNTY_INPUT_XPATH("//div[@id='section__garaging-address']//input[@id='county']"),
	ORDERING_GARAGING_ADDRESS_COUNTRY_INPUT_XPATH("//div[@id='section__garaging-address']//input[@id='country']"),
	ORDERING_GARAGING_ADDRESS_CITY_INPUT_XPATH("//div[@id='section__garaging-address']//input[@id='city']"),
	ORDERING_GARAGING_ADDRESS_STATE_DROPDOWN_XPATH("//div[@id='section__garaging-address']//select[@id='stateProvince']"),
	ORDERING_GARAGING_ADDRESS_ZIPCODE_INPUT_XPATH("//div[@id='section__garaging-address']//input[@id='postalCode']"),
	ORDERING_DELIVERY_ADDRESS_CHECKBOX_XPATH("//div[@id='section__delivery-address']//input[@id='addDelivery']"),
	ORDERING_DELIVERY_ADDRESS_FIRST_NAME_TEXTBOX_XPATH("//div[@id='section__delivery-address']//input[@id='firstName']"),
	ORDERING_DELIVERY_ADDRESS_MIDDLE_NAME_TEXTBOX_XPATH("//div[@id='section__delivery-address']//input[@id='middleName']"),
	ORDERING_DELIVERY_ADDRESS_LAST_NAME_TEXTBOX_XPATH("//div[@id='section__delivery-address']//input[@id='lastName']"),
	ORDERING_DELIVERY_ADDRESS_PHONE_TEXTBOX_XPATH("//div[@id='section__delivery-address']//input[@id='phone']"),
	ORDERING_DELIVERY_ADDRESS_EXTENSION_TEXTBOX_XPATH("//div[@id='section__delivery-address']//input[@id='phExt']"),
	ORDERING_DELIVERY_ADDRESS_ADDRESS1_TEXTBOX_XPATH("//div[@id='section__delivery-address']//input[@id='addressLine1']"),
	ORDERING_DELIVERY_ADDRESS_ADDRESS2_TEXTBOX_XPATH("//div[@id='section__delivery-address']//input[@id='addressLine2']"),
	ORDERING_DELIVERY_ADDRESS_COUNTY_TEXTBOX_XPATH("//div[@id='section__delivery-address']//input[@id='county']"),
	ORDERING_DELIVERY_ADDRESS_COUNTRY_TEXTBOX_XPATH("//div[@id='section__delivery-address']//input[@id='country']"),
	ORDERING_DELIVERY_ADDRESS_CITY_TEXTBOX_XPATH("//div[@id='section__delivery-address']//input[@id='city']"),
	ORDERING_DELIVERY_ADDRESS_STATE_DROPDOWN_XPATH("//div[@id='section__delivery-address']//select[@id='stateProvince']"),
	ORDERING_DELIVERY_ADDRESS_ZIPCODE_XPATH("//div[@id='section__delivery-address']//input[@id='postalCode']"),
	ORDERING_DELIVERY_ADDRESS_FILL_CITY_STATE_COUNTY_BUTTON_ID("fillCityDeliveryAddId"),
	ORDERING_CUSTOM_DRIVER_FIELDS_INPUT_LAST_ODOMETER_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data01Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_JOB_TITLE_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data02Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_MANAGERS_NAME_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data03Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_MANAGERS_EMAIL_ADDRESS_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data04Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_MANAGERS_PHONE_NUMBER_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data05Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_DRIVER_FIELD_6_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data06Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_DRIVER_FIELD_7_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data07Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_DRIVER_FIELD_8_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data08Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_DRIVER_FIELD_9_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data09Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_DRIVER_FIELD_10_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data10Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_DRIVER_FIELD_11_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data11Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_DRIVER_FIELD_12_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data12Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_DRIVER_FIELD_13_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data13Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_DRIVER_FIELD_14_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data14Name']"),
	ORDERING_CUSTOM_DRIVER_FIELDS_DRIVER_NOTE_LABEL_XPATH("//div[@id='section__custom-driver-fields']//p[@data-cdcolofname='data26Name']"),
	ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID("bh-search-unit"),
	ORDERING_DRIVER_CANCEL_EDIT_DRIVER_ID("cancelDvr"),
	ORDERING_DRIVER_EDIT_DRIVER_ID("editDvr"),
	ORDERING_EDIT_DRIVER_ADDRESS2_ID("driver-address2"),
	ORDERING_DRIVER_DELIVERY_ADDRESS_ID("addDelivery"),
	ORDERING_DRIVER_FIRST_NAME_XPATH("//form[@id='driver-information']//p[@id='firstName']"),
	ORDERING_DLVRY_DLR_STATE_DRPDOWN_XPATH("//form[@id='form-delivery-address']//select[@id='stateProvince']"),
	ORDERING_DRIVER_DELIVERY_ADDRESS_SECTION_ID("form-delivery-address"),
	ORDERING_EDIT_DRIVER_SAVE_CSS("div.pull-left.btn.save"),
	ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_SUGGESTION_FIRST_XPATH("(//div[@class='tt-suggestion tt-selectable sugg-item'])[1]"),
	ORDERING_DRIVER_ORDER_ID("order__id"),
	ORDERING_DRIVER_PAGE_ATTACHMENT_BUTTON_ID("orderAttachmentId"),
	ORDERING_DRIVER_UPLOAD_NEW_ATTACHMENT_BUTTON_XPATH("//div[@id='createOrder__content']//a[text()='Upload New Attachment']"),
	ORDERING_SELECT_CATEGORY_DROPDOWN_ID("document-category"),
	ORDERING_SELECT_FILETOBEUPLOADED_ID("upload-file"),
	ORDERING_UPLOAD_BUTTON_XPATH("//button[@class='btn upload-submit' and @type='submit']"),
	ORDERING_CLOSE_ATTACHMENT_PAGE_BUTTON_XPATH("//i[@class='fa fa-close close']"),
	ORDERING_ATTACHMENT_ID_XPATH("//table[@id='comp-file-table']//td[@class='sorting_1']"),
	ORDERING_DRIVER_TAB_LABEL_XPATH("//a[@title='Driver']//span[@class='tab-title']"),
	ORDERING_DRIVER_FIRST_NAME_LABEL_XPATH("//p[@id='firstName']//preceding-sibling::label[@for='firstName']"),
	ORDERING_DRIVER_LAST_NAME_LABEL_XPATH("//p[@id='lastName']//preceding-sibling::label[@for='lastName']"),
	;

	private String value;
	
	OrderingDriverPageEnum(String value) {
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
