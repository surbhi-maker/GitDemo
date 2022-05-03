package com.element.fleet.ordering.enums;

public enum OrderingBOEditOrderPageEnum {
	
	ORDERING_BO_EDITORDER_ORDMAINTAINANCE_XPATH("//span[contains(text(),'Order Maintenance')]"),
	ORDERING_BO_EDITORDER_ADDUPFITPO_XPATH("//span[contains(text(),'Add Upfit Purchase Order')]"),
	ORDERING_BO_EDITORDER_INVOICENETRY_XPATH("//span[contains(text(),'Invoice Entry')]"),
	ORDERING_BO_EDIT_ORDER_XPATH("//span[contains(text(),'Edit Order')]"),
	ORDERING_BO_EDIT_ORDER_MAINTENANCE_XPATH("//span[contains(text(),'Order Maintenance')]"),
    ORDERING_BO_EDIT_ORDERS_ADD_UPFIT_PURCHASE_ORDER_XPATH("//span[contains(text(),'Add Upfit Purchase Order')]"),
    ORDERING_BO_EDIT_ORDERS_INVOICE_ENTRY_XPATH("//span[contains(text(),'Invoice Entry')]"),
    ORDERING_BO_EDIT_ORDERS_ADD_PO_SEARCH_ORDER_ID("order-search"),
    ORDERING_BO_EDIT_ORDERS_ADD_PO_SEARCH_ORDER_OPTION_XPATH("//div[@class='tt-menu tt-open']//div//div[1]"),
    ORDERING_BO_EDIT_ORDERS_ADD_PO_LOG_NUMBER_XPATH("//label[contains(text(),'Log Number')]//following::span[1]"),
    ORDERING_BO_EDIT_ORDERS_ADD_PO_CORP_XPATH("//label[contains(text(),'Log Number')]//following::span[2]"),
    ORDERING_BO_EDIT_ORDERS_ADD_PO_FLEET_XPATH("//label[contains(text(),'Fleet')]//following::span[1]"),
    ORDERING_BO_EDIT_ORDERS_ADD_PO_UNIT_XPATH("//label[contains(text(),'Unit')]//following::span[1]"),
    ORDERING_BO_EDIT_ORDERS_ADD_PO_VIN_XPATH("//label[contains(text(),'VIN')]//following::span[1]"),
    ORDERING_BO_EDIT_ORDERS_SUPLLIER_ID_ID("dealer-code"),
    ORDERING_BO_EDIT_ORDERS_SUPLLIER_NAME_ID("upfitter-name"),
	;
	
	private String value;

	OrderingBOEditOrderPageEnum(String value) {
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
