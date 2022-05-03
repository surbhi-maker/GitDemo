package com.element.fleet.ordering.enums;

public enum OrderingBOBizzMaintTablesCommonEnum {
	
	ORDERING_BO_BMT_MENUBTN_XPATH("//div[@id='side-menu']//span[contains(text(),'Business')]"),
	ORDERING_BO_BMT_COMMON_SEARCH_BUTTON_XPATH("//div[@data-target='search']"),
	ORDERING_BO_BMT_COMMON_CLEAR_FILTER_BUTTON_XPATH("//div[@data-target='clear']"),
	ORDERING_BO_BMT_COMMON_ADD_NEW_ENTRY_BUTTON_XPATH("//span[contains(text(),'Add New')]"),
	ORDERING_BO_BMT_COMMON_EXPORT_BUTTON_XPATH("//span[contains(text(),'Export')]"),
	ORDERING_BO_BMT_COMMON_TOGGLE_COLUMNS_DROPDOWN_XPATH("//span[contains(text(),'Toggle Columns')]"),
	ORDERING_BO_BMT_COMMON_SIGN_OUT_XPATH("//input[@value='Sign Out']"),
	ORDERING_BO_BMT_COMMON_NEXT_PAGINATION_BUTTON_XPATH("//span[@class='next paginate_button']"),
	ORDERING_BO_BMT_COMMON_ADD_NEW_SAVE_BUTTON_XPATH("//div[contains(text(),'Save')]"),
	ORDERING_BO_BMT_COMMON_ADD_NEW_CLOSE_BUTTON_XPATH("//div[@class='close']"),
	ORDERING_BO_BMT_COMMON_SUCCESS_MESSAGE_XPATH("//div[contains(text(),'Data has been successfully updated')]"),
	ORDERING_BO_BMT_COMMON_GROWL_MESSAGE_XPATH("//div[@class='growl-message']"),
	ORDERING_BO_BMT_COMMON_UPDATE_DELETE_BUTTON_XPATH("//div[contains(text(),'Delete')]"),
	ORDERING_BO_BMT_COMMON_UPDATE_ADD_BUTTON_XPATH("(//div[contains(text(),'Add')])[2]"),
	ORDERING_BO_BMT_COMMON_DELETE_YES_BUTTON_XPATH("//button[@class='modal-ok-btn']"),
	ORDERING_BO_BMT_COMMON_DELETE_MESSAGE_XPATH("//div[contains(text(),'Record was successfully deleted')]"),
	ORDERING_BO_MP_OS_VALIDATION_LAST_UPDATED_BY_XPATH("//td[@data-name='lastUpdateBy']"),
	ORDERING_BO_BMT_COMMON_FIRST_RECORD_XPATH("(//tr[@class='odd'])[1]"),
	ORDERING_BO_BMT_COMMON_UPDATE_SEARCH_BUTTON_XPATH("//div[contains(text(),'Search')]"),
	ORDERING_BO_BMT_COMMON_SUBTITLE_XPATH("//h1[@class='subtitle']"),
	ORDERING_BO_BMT_COMMON_SUB_SCREEN_SUBTITLE_XPATH("//div[@class='subscreen-title Biz-subscreen-title']"),
	;

	private String value;

	OrderingBOBizzMaintTablesCommonEnum(String value) {
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
