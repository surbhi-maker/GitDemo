package com.element.fleet.ordering.enums;

public enum OrderingBOUpfitProjectPageEnum {
	
	ORDERING_BO_UPFIT_PROJECT_PAGE_HEADING_CSS("#queue-page-title"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_QUEUE_XPATH("(//li[@class='queues'])[1]"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_REQUEST_QUEUE_LABEL_XPATH("//*[@id='request-queue']//h2"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_REQUEST_QUEUE_SEARCH_XPATH("(//*[@id='search-by-filters-btn'])[1]"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_REQUEST_QUEUE_CLEARFILTER_XPATH("(//*[@id='clear-all-filters-btn'])[1]"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_REQUEST_ADVANCED_SEARCH_XPATH("(//*[@id='toggle-advanced'])[1]"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_LABEL_XPATH("//*[@id='project-queuev3']//h2"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_SEARCH_XPATH("(//*[@id='search-by-filters-btn'])[2]"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_CLEARFILTER_XPATH("(//*[@id='clear-all-filters-btn'])[2]"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_ADVANCED_SEARCH_XPATH("(//*[@id='toggle-advanced'])[2]"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_REQUEST_BULK_ACTIONS_XPATH("(//*[@id='queue-actions']/div/div)[1]"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_BULK_ACTIONS_XPATH("(//*[@id='queue-actions']/div/div)[2]"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_CLIENT_FILTER_XPATH("//input[@id='clientNumber-input']"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_PROJECT_ID_FILTER_XPATH("//input[@id='projectID-input']"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_FIRST_ROW_XPATH("//table[@id='project-queue-table']/tbody/tr/td[6]"),
	ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_FIRST_ROW_CHECKBOX_XPATH("//input[@id='data-table__checkbox-0']"),
	ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_CREATE_RFQ_XPATH("//button[@id='btn-create-rfq']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_FOOTER_SAVE_XPATH("//button[@id='footer-save-button']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_FOOTER_SEND_XPATH("//button[@id='footer-send-button']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_FOOTER_CANCEL_XPATH("//button[@id='footer-cancel-button']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_CLIENT_INFORMATION_XPATH("(//button[@class='multiselect dropdown-toggle btn btn-default'])[1]"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_DEALER_NUMBER_XPATH("//input[@id='rfq-upfitter-dlr-no']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_NAME_XPATH("//input[@id='rfq-upfitter-name']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_CLIENT_INFORMATION_SELECT_ALL_XPATH("(//label[@class='checkbox'])[1]"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UNIT_NUMBER_XPATH("(//button[@class='multiselect dropdown-toggle btn btn-default'])[2]"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UNIT_NUMBER_SELECT_ALL_XPATH("(//label[@class='checkbox'])[3]"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_ADD_LINE_ITEM_XPATH("//button[@id='add-option-line-items']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_ASSIGN_UPFITTER_SMART_SEARCH_XPATH("//input[@id='rfq-upfitter-search']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_SMART_SEARCH_RESULT_XPATH("//div[@class='tt-menu tt-open']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_EMAIL_XPATH("//input[@id='rfq-upfitter-contact-email']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_SHIPPING_INTENT_XPATH("//select[@id='quote-shipping-intent-select']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_ASSIGN_FLEET_SPEC_SMART_SEARCH_XPATH("//input[@id='quote-vehicle-info-fleetspec-id']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_INFORMATION_QUANTITY_XPATH("//input[@id='quantity1']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_INFORMATION_DESCRIPTION_XPATH("//textarea[@id='option-description1']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_INSTRUCTION_TO_UPFITTER_XPATH("//textarea[@id='upfitter-instructions']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_RFQ_NAME_XPATH("//input[@id='rfq-name']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_RFQ_DESCRIPTION_XPATH("//textarea[@id='rfq-desc']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_RFQ_POP_UP_SAVE_BUTTON_XPATH("//button[@id='modal-accept-btn']"),
	OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_NOTIFICATION_XPATH("//div[@class='noty-msg x-style']"),
	OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_PROJECT_MANAGMENT_XPATH("(//a[@class='breadcrumbs__link js-breadcrumb-link'])[3]"),
	OORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_RFQ_DROPDOWN_XPATH("(//div[@class='accordion'])[1]"),
	ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_FIRST_RECORD_RFQ_ID_XPATH("(//table[contains(@id,'rfq-upfit-table')]/tbody//td[@data-name='compId'])[1]"),
	ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_FIRST_RECORD_RFQ_STATUS_XPATH("(//table[contains(@id,'rfq-upfit-table')]/tbody//td[@data-name='status'])[1]"),
	ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_FIRST_RECORD_ROW_ACTION_XPATH("(//button[@class='text-button text-button--overflow js-row-menu-button'])[2]"),
	ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_FIRST_RECORD_ROW_ACTION_COPY_XPATH("(//a[contains(@id,'row-action-copy')])[2]"),
	ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_RFQ_ID_HEADER_XPATH("(//div[@id='management-rfq-upfit-table-holder']//th[contains(@class,'data-table__header-cell dt')][contains(@aria-label,'RFQ ID')])[1]"),
			//+ "(//th[contains(@aria-label,'RFQ ID')])[3]"),
	ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_RFQ_UPFIT_TABLE_XPATH("(//h4[@class='order-management__table-heading'])[2]"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_MANDATORY_FIELDS_XPATH("//div[@class='validation-error-wrapper bottom']"),
	ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_CLIENT_MANDATORY_FIELDS_XPATH("//div[@class='validation-error-wrapper bottom js-client-error-message client-error-message']"),
	ORDERING_BO_RFQ_CLEAR_FILTER_BUTTON_XPATH("//button[@id='clear-all-filters-btn']"),
	ORDERING_BO_RFQ_SEARCH_BUTTON_XPATH("//button[@id='search-by-filters-btn']"),
	ORDERING_BO_RFQ_RFQ_ID_FILTER_XPATH("//input[@id='rfqID-input']"),
	
	
	
	
	;

	private String value;

	private OrderingBOUpfitProjectPageEnum(String value) {
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