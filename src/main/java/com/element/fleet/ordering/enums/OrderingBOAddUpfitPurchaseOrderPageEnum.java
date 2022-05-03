package com.element.fleet.ordering.enums;

public enum OrderingBOAddUpfitPurchaseOrderPageEnum {

	ORDERING_BO_AUPO_ORDER_SECTION_ID("order-section"),
	ORDERING_BO_AUPO_SUBSCREEN_TITLE_XPATH("//div[@class='subscreen-title']"),
	ORDERING_BO_AUPO_SAVEBTN_XPATH("//div[@id='save']"),
	ORDERING_BO_AUPO_CANCELBTN_XPATH("//div[@id='cancel']"),
	ORDERING_BO_AUPO_ORDERSEARCHINP_XPATH("//input[@name='order-search']"),
	ORDERING_BO_AUPO_ADDPOROW_XPATH("//a[@class='addPoAddRow']"),
	ORDERING_BO_AUPO_LOGNO_XPATH("//span[@id='log-no']"),
	ORDERING_BO_AUPO_OPTIONCODE_XPATH("//input[@id='optionCode0']"),
	ORDERING_BO_AUPO_OPTIONDESCRIPTION_XPATH("//input[@id='optionDescription0']"),
	ORDERING_BO_AUPO_EXTENDEDDESCRIPTION_XPATH("//input[@id='extendedDescription0']"),
	ORDERING_BO_AUPO_OPTIONPRICE_XPATH("//input[@id='amount0']"),
	ORDERING_BO_AUPO_OPTIONCATEGORY_XPATH("//select[@id='optionCategory0']"),
	ORDERING_BO_AUPO_SELECTORDERSEARCH_XPATH("(//div[@class='tt-suggestion tt-selectable empty'])[2]"),
	ORDERING_BO_AUPO_ESTIMATEDLEADTIME_XPATH("//input[@id='lead-time']"),
	ORDERING_BO_AUPO_WORKTYPE_XPATH("//select[@id='work-type']"),
	ORDERING_BO_AUPO_FIRSTROW_XPATH("//table[@id='poo-table']//following::tbody//following::td[1]"),
	ORDERING_BO_AUPO_NOTIFYMSG_XPATH("//div[contains(@class,'noty-msg')]"),
	ORDERING_BO_AUPO_NORESULTS_TABLE_XPATH("//table[@id='poo-table']//td[@class='dataTables_empty']"),
	ORDERING_BO_AUPO_CODE_INPUT_NAME("orderPoOptionsDTO[0][optionCode]:string"),
	ORDERING_BO_AUPO_CATEGORY_SELECT_NAME("orderPoOptionsDTO[0][optionCategory]:string"),
	ORDERING_BO_AUPO_DESCRIPTION_INPUT_NAME("orderPoOptionsDTO[0][optionDescription]:string"),
	ORDERING_BO_AUPO_EXTENDEDDESCRIPTION_INPUT_NAME("orderPoOptionsDTO[0][extendedDescription]:string"),
	ORDERING_BO_AUPO_COST_INPUT_NAME("orderPoOptionsDTO[0][estimatedOptionAmt][amount]:number"),
	ORDERING_FO_ORD_MAINT_UPFITTAB_XPATH("//*[@id=\"upfitInfoTab\"]/a"),
	ORDERING_FO_AUPO_ADDUPFITPURCHASEORDER_BUTTON_XPATH("//button[@id='addUpfitPO']"),
	ORDERING_FO_AUPO_ADDLINEITEM_BUTTON_XPATH("//button[@id='addAdhocLinePOItem']"),
	ORDERING_FO_AUPO_CODE_XPATH("//input[@name='partNumber']"),
	ORDERING_FO_UPFIT_CATEGORY_XPATH("//select[@id='categoryList']"),
	ORDERING_FO_UPFIT_DESC_XPATH("//input[@name='partName']"),
	ORDERING_FO_UPFIT_OPTION_PRICE_XPATH("//input[@name='clientNegotiatedPrice']"),
	;

	private String value;

	OrderingBOAddUpfitPurchaseOrderPageEnum(String value) {
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
