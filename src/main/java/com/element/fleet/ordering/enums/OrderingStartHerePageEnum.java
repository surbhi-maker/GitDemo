package com.element.fleet.ordering.enums;

public enum OrderingStartHerePageEnum {

	ORDERING_START_HERE_UNIT_NUMBER_TEXTBOX_CSS("div.modalContentWrapper input#mainTabUnitNoId"),
	ORDERING_START_HERE_UNIT_NUMBER_AUTO_CSS("#start-form > div > div.col-md-6.col-lg-6.auto-assigned-wrapper.pull-right"),
	ORDERING_START_HERE_UNIT_NUMBER_AUTO_NAME("autoassigned"),
	ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID("orderSaveNextId"),
	ORDERING_START_HERE_CORP_CODE_ID("corp__id"),
	ORDERING_START_HERE_CLIENT_NAME_CSS("div.order-header-data.client-name p.order-data"),
	ORDERING_START_HERE_CLIENT_BREAKDOWN_TEXT_ID("clientBkdnTextId"),
	ORDERING_START_HERE_ORDER_TYPE_FACTORY_ORDER_RADIO_BUTTON_ID("orderType__FO"),
	ORDERING_START_HERE_ORDER_TYPE_STOCK_ORDER_RADIO_BUTTON_ID("orderType__ST"),
	ORDERING_START_HERE_ORDER_LOCATE_VEHICLE_DROPDOWN_CSS("select.vehicleLocate"),
	ORDERING_START_HERE_CUSTOMER_PRE_APPROVED_PRICE_ID("customerApprovedPrice"),
	ORDERING_START_HERE_ORDER_TYPE_DEALER_ORDER_RADIO_BUTTON_ID("orderType__DO"),
	ORDERING_START_HERE_POOL_ORDER_YES_RADIO_BUTTON_ID("pool-yes"),
	ORDERING_START_HERE_POOL_ORDER_NO_RADIO_BUTTON_ID("pool-no"),
	ORDERING_START_HERE_POOL_ORDER_DROPDOWN_ID("pool__type__selection"),
	ORDERING_START_HERE_STOCK_ORDER_FIRST_NAME_CSS("input[name='client[firstName]']"),
	ORDERING_START_HERE_STOCK_ORDER_LAST_NAME_CSS("input[name='client[lastName]']"),
	ORDERING_START_HERE_STOCK_ORDER_PHONE_ID("clientPhone"),
	ORDERING_START_HERE_STOCK_ORDER_PHONE_EXT_NAME("client[phoneExtension]"),
	ORDERING_START_HERE_STOCK_ORDER_EMAIL_CSS("input[name='client[email]']"),
	ORDERING_START_HERE_USED_UNIT_NO_RADIO_BUTTON_ID("add_used_unit__no"),
	ORDERING_START_HERE_USED_UNIT_YES_RADIO_BUTTON_ID("add_used_unit__yes"),
	ORDERING_START_HERE_USED_UNIT_SEARCH_TEXTBOX_ID("bh-search-unit"),
	ORDERING_START_HERE_USED_UNIT_SEARCH_SUGGESTED_RESULT_XPATH("(//div[@data-status='4'])[1]"),
	ORDERING_START_HERE_USED_UNIT_WHO_TO_SELL_USED_UNIT_DROPDOWN_CSS("select[id='whoToSell']"),
    ORDERING_START_HERE_USED_UNIT_COMPETITOR_LIST_DROPDOWN_CSS("select[id='compSel']"),
    ORDERING_START_HERE_DEALER_ORDER_WHO_WILL_LOCATE_VEHICLE_XPATH("//div[@id='vehicle-Located-group']//select"),
    ORDERING_START_HERE_USED_UNIT_FLEET_NAME_CSS("span.fleetName"),
    ORDERING_START_HERE_USED_UNIT_UNIT_NUMBER_CSS("span.unitNo"),
    ORDERING_START_HERE_USED_UNIT_YEAR_CSS("span.year"),
    ORDERING_START_HERE_USED_UNIT_MAKE_CSS("span.make"),
    ORDERING_START_HERE_USED_UNIT_MODAL_CSS("span.model"),
    ORDERING_START_HERE_USED_UNIT_TRIM_CSS("span.trim"),
    ORDERING_START_HERE_USED_UNIT_VIN_CSS("span.vin"),
    ORDERING_START_HERE_USED_UNIT_COPY_USED_UNIT_DATA_YES_XPATH("//input[@id='add_used_unit__yes' and @name='UsedAddUnit']"),
    ORDERING_START_HERE_SAVE_AND_EXIT_BUTTON_ID("orderSaveExitId"),
    ORDERING_CANCEL_BUTTON_XPATH("//div[@id='createOrder__content']/div[1]/div[1]/div[1]"),
    ORDERING_CANCEL_POP_UP_OK_BUTTON_XPATH("//*[@id=\"createOrder__content\"]/div[4]/div[3]/button[1]/i[1]"),
    ORDERING_CANCEL_POP_UP_STAY_BUTTON_XPATH("//*[@id=\"createOrder__content\"]/div[4]/div[3]/button[2]/i[1]"),
    ORDERING_CANCEL_POP_UP_CROSS_ICON_XPATH("//*[@id=\"createOrder__content\"]/div[4]/div[1]/i[1]"),
    ORDERING_START_HERE_ORDER_TYPE_SECTION_ID("section__start-type"),
    ORDERING_START_HERE_USED_UNIT_INFORMATION_SECTION_ID("section__start-usedUnit"),
    ORDERING_START_HERE_REMOVE_USED_UNIT_ID("section__start-type"),
    ORDERING_START_HERE_USED_NO_OPTION_XPATH("//label[contains(text(),'No')]"),
    ORDERING_START_HERE_USED_UNIT_NO_XPATH("//label[contains(text(),'Add Used Unit?')]//following::input[@name='addUsedUnit'][2]"),
    ORDERING_START_HERE_CREATE_ORDER_TABS_XPATH("//ul[@id='createOrder-menu']//li"),
	;
	
	private String value;

	OrderingStartHerePageEnum(String value) {
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
