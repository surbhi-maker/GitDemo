package com.element.fleet.ordering.enums;

public enum OrderingBOAssetCodePageEnum {

	ORDERING_BO_BMT_AC_SEARCH_FIELD_ID("standard"),
	ORDERING_BO_BMT_ASSETCODE_ACDESCRIPTIONTXT_XPATH("//input[@name='assetCodeDescription']"),
	ORDERING_BO_BMT_ASSETCODE_ADDNEW_ASSETCODETXT_XPATH("//input[@id='assetCode']"),
	ORDERING_BO_BMT_ASSETCODE_ADDNEW_DESCRIPTIONTXT_XPATH("//input[@id='assetCodeDesc']"),
	ORDERING_BO_BMT_ASSETCODE_ADDNEW_PRODUCTCLASSDRPDWN_XPATH("//select[@id='productClassValueId']"),
	ORDERING_BO_BMT_ASSETCODE_ADDNEW_PRODUCTSUBCLASSDRPDWN_XPATH("//select[@id='productSubClassValueId']"),
	ORDERING_BO_ASSETCODE_ADDNEW_WAIT_PRODSUBCLASSDRPDWN_XPATH("//option[contains(text(),'2- ELECTRIC NARROW AISLE')]"),
	ORERING_BO_ASSETCODE_VALIDATION_ASSETCODEDESCRIPTION_XPATH("(//td[@data-name='assetCodeDescription'])[1]"),
	ORERING_BO_ASSETCODE_VALIDATION_PRODUCTCLASS_XPATH("(//td[@data-name='productClassCode'])[1]"),
	ORERING_BO_ASSETCODE_VALIDATION_PRODUCTSUBCLASS_XPATH("(//td[@data-name='productSubclassCode'])[1]"),
	ORERING_BO_ASSETCODE_VALIDATION_ASSETCODE_XPATH("(//td[@data-name='assetCode'])[1]"),
    ;	

	private String value;

	OrderingBOAssetCodePageEnum(String value) {
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
