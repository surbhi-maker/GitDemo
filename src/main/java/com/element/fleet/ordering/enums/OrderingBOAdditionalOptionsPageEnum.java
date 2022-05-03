package com.element.fleet.ordering.enums;

public enum OrderingBOAdditionalOptionsPageEnum {
	
	ORDERING_BO_BMT_AO_ADD_XPATH("//div[contains(@class, 'addNew')]"),
	ORDERING_BO_BMT_AO_ADD_AO_CORPCODE_ID("corpCode"),
	ORDERING_BO_BMT_AO_ADD_AO_FLEET_ID("clientNumber"),
	ORDERING_BO_BMT_AO_ADD_AO_YEAR_ID("year"),
	ORDERING_BO_BMT_AO_ADD_AO_MAKE_ID("make"),
	ORDERING_BO_BMT_AO_ADD_AO_MODEL_CSS("select#model"),
	ORDERING_BO_BMT_AO_ADD_AO_OPTION_CODE_ID("code"),
	ORDERING_BO_BMT_AO_ADD_AO_MFR_OPTION_CODE_ID("externalCode"),
	ORDERING_BO_BMT_AO_ADD_AO_OPTION_DESCRIPTION_ID("shortDesc"),
	ORDERING_BO_BMT_AO_ADD_AO_EXTENDED_OPTION_DESCRIPTION_ID("longDesc"),
	ORDERING_BO_BMT_AO_ADD_AO_OPTION_PRICE_ID("invoicePriceAmount"),
	ORDERING_BO_BMT_AO_ADD_AO_EST_ACT_PRICE_ID("invoicePriceIndicator"),
	ORDERING_BO_BMT_AO_ADD_AO_OPTION_MSRP_ID("msrpPriceAmount"),
	ORDERING_BO_BMT_AO_ADD_AO_EC_CODE_ID("equipCategoryCode"),
	ORDERING_BO_BMT_AO_ADD_AO_DIO_INDICATOR_CSS("input#dioIndicator+label"),
	ORDERING_BO_BMT_AO_ADD_AO_POST_PRODUCTION_INDICATOR_CSS("input#postProductionIndicator+label"),
	ORDERING_BO_BMT_AO_ADD_AO_TRANSMIT_FLAG_CSS("input#mfgSendIndicator+label"),
	ORDERING_BO_BMT_AO_ADD_AO_TELEMATICS_FLAG_CSS("input#telematicsIndicator+label"),
	ORDERING_BO_BMT_AO_ADD_AO_TELEMATICS_FLAG_LABEL_CSS("div#div-telematicsIndicator>label"),
	ORDERING_BO_BMT_AO_ADD_AO_STANDARD_OPTIONAL_ID("standardOptionalIndicator"),
	ORDERING_BO_BMT_AO_ADD_AO_START_DATE_ID("effectiveStartDate"),
	ORDERING_BO_BMT_AO_ADD_AO_END_DATE_ID("effectiveEndDate"),
	ORDERING_BO_BMT_ADD_AO_SAVE_ID("save-entity"),
	ORDERING_BO_BMT_DD_AO_BACK_TO_QUEUE_VIEW_ID("browser-back"),
	ORDERING_BO_BMT_ADD_AO_BRAND_PROVIDER_ID("supplierName"),
	ORDERING_BO_BMT_ADD_AO_SUPPLIER_TO_SHIP_CSS("input#supplierShipIndicator+label"),
	ORDERING_BO_BMT_AO_OPTION_CODE_NAME("code"),
	ORDERING_BO_BMT_DIO_ADD_AO_DELETE_ID("delete-entity"),
	ORDERING_BO_BMT_ADD_AO_CONFIRM_DELETE_AO_XPATH("//div[@id='new-modal']//button[contains(text(), 'Yes')]"),
	ORDERING_BO_BMT_ADD_AO_ADD_ID("add-entity"),
	ORDERING_BO_BMT_AO_EXPORT_BUTTON_XPATH("//div[@class='btn blue btn-shared queue-export']//span"),
	;
	
	private String value;

	private OrderingBOAdditionalOptionsPageEnum(String value) {
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
