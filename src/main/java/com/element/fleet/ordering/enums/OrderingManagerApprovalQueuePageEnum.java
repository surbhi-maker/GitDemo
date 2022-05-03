package com.element.fleet.ordering.enums;

public enum OrderingManagerApprovalQueuePageEnum {
	
	ORDERING_MANAGER_APPROVAL_QUEUE_HEADING_ID("searchHeading"),
	ORDERING_MANAGER_APPROVAL_QUEUE_FILTER_SECTION_CSS("div.filters-wrapper"),
	ORDERING_MANAGER_APPROVAL_QUEUE_FILTER_TABLE_HEAD_CSS("div.dataTables_scrollHead"),
	ORDERING_MANAGER_APPROVAL_QUEUE_FILTER_TABLE_BODY_CSS("div.dataTables_scrollBody"),
	ORDERING_MANAGER_APPROVAL_QUEUE_TABLE_ROW_XPATH("//table[@id='queue-table']/tbody/tr"),
	ORDERING_FRONT_OFFICE_CLOSE_SUMMARY_PAGE_CSS("div.pull-left.btn.cancel i.fa.fa-ban"),
	ORDERING_MANAGER_APPROVAL_QUEUE_SELECT_MULTIPLE_TABLE_ROW_XPATH("//table[@id='queue-table']/tbody/tr"),
	ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_ROW_XPATH(".//td[@data-name='logNumber']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_CHECKBOX_ROW_XPATH(".//td[@class='checkbox']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_SELECT_SINGLE_TABLE_ROW_XPATH("//table[@id='queue-table']//td//input[@name='order-check']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_BULK_APPROVE_CSS("button.action-bar__button.bulkApproveBtn"),
	ORDERING_MANAGER_APPROVAL_QUEUE_BULK_REJECT_CSS("button.action-bar__button.bulkRejectBtn"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_MESSAGE_CSS("div.approval-list-message-container"),
	ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_MESSAGE_CSS("div#approve-confirm-modal.fade.in div.modal-content > div.modal-body"),
	ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_OK_CSS("div#approve-confirm-modal.fade.in div.modal-footer button#modal-accept-btn"),
	ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_CANCEL_CSS("div#approve-confirm-modal.fade.in div.modal-footer button#modal-close-btn"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_LOGNUMBER_CSS("table.table.approval-list-message-table > tbody > tr > td:nth-child(1) > div"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_ROW_CSS("table.table.approval-list-message-table > tbody > tr"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_LOGNUMBER_ROW_XPATH(".//td/div"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CLOSE_CSS("button#modal-close-btn"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_MESSAGE_CSS("div#modalRejectWarning div.submitMsg > p"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CANCEL_MESSAGE_CSS("div#cancel-confirm-modal div.modal-body > div"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_TEXT_AREA_CSS("div#modalRejectWarning div.submitMsg > textarea#reject-reason"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_OK_BUTTON_CSS("div#modalRejectWarning div.modal-footer> button#modal-accept-btn"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_CLOSE_BUTTON_CSS("div#modalRejectWarning div.modal-footer> button#modal-close-btn"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CANCEL_OK_BUTTON_CSS("div#cancel-confirm-modal div.modal-footer> button#modal-accept-btn"),
	ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_TEXTBOX_XPATH("//input[@name='logNumber']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_SEARCH_BUTTON_XPATH("//div[@class='btn pc-action search']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_MODALAREA_CSS("div.noty_bar"),
	ORDERING_MANAGER_APPROVAL_QUEUE_CREATEPDF_BUTTON_XPATH("//button[@class='pull-right btn export__pdf']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_TABLE_BODY_CLASS("dataTables_scrollBody"),
	ORDERING_MANAGER_APPROVAL_QUEUE_CANCEL_BUTTON_XPATH("//div[@class='pull-left btn cancel']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_SEARCHOPTIONS_BUTTON_CLASS("search-overlay"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ADVANCEDSEARCH_BUTTON_XPATH("//li[@data-target='advanced']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_PRODUCTCODE_FIELD_XPATH("//input[@type='text' and @name='productCode']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_ESTIMATEDCOST_FIELD_XPATH("//input[@type='text' and @name='estimatedCost']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_VEHICLEBREAKDOWN_FIELD_XPATH("//input[@type='text' and @name='vehicleBreakDown']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_DRIVERSTATE_FIELD_XPATH("//input[@type='text' and @name='driverState']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_GARAGESTATE_FIELD_XPATH("//input[@type='text' and @name='garageState']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_DRIVERORDER_FIELD_XPATH("//input[@type='text' and @name='driverOrder']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_SEARCH_LOAD_BUTTON_XPATH("//div[contains(@class, 'btn pc-action search')]"),
	ORDERING_MANAGER_APPROVAL_QUEUE_RESET_BUTTON_XPATH("//div[@class='btn pc-action reset']"),
	ORDERING_MANAGER_APPROVAL_QUEUE_INPUT_TEXTBOX_XPATH("//section[@id='standard']//input[@type='text']"),
	ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_INFOSUCCESS_ALERT_CLASS("noty_body"),
	ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_PRODUCTCODE_ROW_XPATH("//td[@data-name='productCode']"),
	ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_ESTIMATEDCOST_ROW_XPATH("//td[@data-name='estimatedCost']"),
	ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_VEHICLEBREAKDOWN_ROW_XPATH("//td[@data-name='vehicleBreakDown']"),
	ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_DRIVERSTATE_ROW_XPATH("//td[@data-name='driverState']"),
	ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_GARAGESTATE_ROW_XPATH("//td[@data-name='garageState']"),
	ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_DRIVERORDER_ROW_XPATH("//td[@data-name='driverOrder']"),
	;

	private String value;

	private OrderingManagerApprovalQueuePageEnum(String value) {
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
