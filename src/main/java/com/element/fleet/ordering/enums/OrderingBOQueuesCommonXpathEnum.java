package com.element.fleet.ordering.enums;import com.ge.capital.rainbow.browser.BrowserAction;

public enum OrderingBOQueuesCommonXpathEnum {
	ORDERING_BO_QUEUES_COMMON_TITLE_XPATH("//h1[@class='title']"),
	ORDERING_BO_QUEUES_COMMON_SUBTITLE_XPATH("//h1[@class='subtitle']"),
	ORDERING_BO_QUEUES_COMMON_SAVEBTN_XPATH("//a[@class='saveBtn pull-right']"),
	ORDERING_BO_QUEUES_COMMON_EXPORTBTN_XPATH("//a[@class='exportExcelBtn pull-right']"),
	ORDERING_BO_QUEUES_COMMON_ADDREMOVEBTN_XPATH("//a[@class='addRemoveBtn pull-right']"),
	ORDERING_BO_QUEUES_COMMON_SUBSCREENTITLE_XPATH("//div[@class='subscreen-title']"),
	ORDERING_BO_QUEUES_COMMON_TITLE_EXPECTED("Queues"),
	ORDERING_BO_QUEUES_COMMON_EXPORT_EXPECTED("Export"),
	ORDERING_BO_QUEUES_COMMON_ADDREMOVE_EXPECTED("Add/Remove"),
	ORDERING_BO_QUEUES_COMMON_SAVE_EXPECTED("Save"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_TITLE_XPATH("//h2[text()='Request Queue']"),
	ORDERING_BO_QUEUES_PROJECTQUEUE_TITLE_XPATH("//h2[text()='Project Queue']"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHRECORD_XPATH("//tr[@class='data-table__row new-entry-color odd']"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_LOG_XPATH("//input[@id='logNumberReq-input']"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHBUTTON_XPATH("(//button[@id='search-by-filters-btn'])[2]"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_ADVANCEDSEARCH_XPATH("(//button[@id='toggle-advanced'])[1]"),
	ORDERING_BO_QUEUES_NEWREQUEST_LABEL_XPATH("//div[@class='alert new-requests-notification']"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS_XPATH("(//button[@id='clear-all-filters-btn'])[1]"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS2_XPATH("(//button[@id='clear-all-filters-btn'])[2]"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHFIELDSTEXT_XPATH("(//div[@class='table-filters'])[1]//div[contains(@class,'table-filters__field field')]//input[@name]"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_FILTERICON_XPATH("(//button[@id='toggle-filter'])"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_LOGFIELD_XPATH("(//label[text()='Log'])"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHFIELDS_XPATH("(//div[@class='table-filters'])[1]//div[contains(@class,'table-filters__field field')]"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_COLUMNS_XPATH("//div[@class='dataTables_scrollHeadInner']//tr[@class='data-table__header-row']//th[@aria-controls='request-queue-table'][@aria-label]"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_COLUMNDATASTART_XPATH("(//th[@class='data-table__header-cell dt-left sorting'])[23]"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_COLUMNDATAMIDDLE_XPATH("(//th[@class='data-table__header-cell dt-left sorting'])[32]"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_COLUMNDATALAST_XPATH("(//th[@class='data-table__header-cell dt-left sorting'])[42]"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_CANCELBUTTON_XPATH("//button[@id='cancel']"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_CORPCODETEXT_XPATH("//input[@id='corpCodeReq-input']"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_CLIENTNUMBERTEXT_XPATH("//input[@id='clientNumberReq-input']"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_CLIENTTEXT_XPATH("//input[@id='clientNumber-input']"),
	ORDERING_BO_QUEUES_PROJECTQUEUE_CLIENTTEXT_XPATH("//input[@id='clientNumber-input']"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_ORIGINTEXT_XPATH("//input[@id='originReq-input']"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_TABLEDATACORPCODE_XPATH("//td[@data-name='corpCodeReq']"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_TABLEDATACLINTNUMBER_XPATH("//td[@data-name='clientNumberReq']"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_TABLEDATAORIGIN_XPATH("//td[@data-name='originReq']"),
	ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_BUTTON_XPATH("//*[@id='create-project']"),
	ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_DESCRIPTION_XPATH("//*[@id='client-description']"),
	ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_PROJECT_NAME_XPATH("//*[@id='project-name']"),
	ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_UNIT_NUMBER_XPATH("//*[@id='unit-number']"),
	ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_CLIENT_SMART_SEARCH_XPATH("//*[@id='client-smart-search']"),
	ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_TASK_TYPE_DROPDOWN_XPATH("//*[@id='current-view-dropdown']"),
	ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_DUE_DATE_XPATH("//*[@id='client-change-due-date']"),
	ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_DUE_DATE_CALENDER_BUTTON_XPATH("//*[@class='due-date-icon fa fa-calendar']"),
	ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_SUGGESTED_SMART_SEARCH_CLIENT_XPATH("//*[@id='project-section-form']//div[1]//div[5]//div//span//div//div//div//strong"),
	ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_ADD_BUTTON_XPATH("//*[@id='project-add-unit']"),
	ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_SAVE_BUTTON_XPATH("//*[@id='modal-save-btn']"),
	ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_CANCEL_BUTTON_XPATH("//*[@id='modal-cancel-btn']"),
	ORDERING_BO_PROJECT_QUEUE_SEARCH_PROJECT_NAME_INPUT_FIELD_XPATH("(//*[@id='projectName-input'])[2]"),
	ORDERING_BO_PROJECT_QUEUE_CLEAR_FILTERS_BUTTON_XPATH("(//*[@id='clear-all-filters-btn'])[2]"),
	ORDERING_BO_PROJECT_QUEUE_SEARCH_BUTTON_XPATH("(//*[@id='search-by-filters-btn'])[2]"),
	ORDERING_BO_PROJECT_QUEUE_PROJECTNAMESEARCH_BUTTON_XPATH("(//*[@id='project-queue-table']/tbody/tr[1]/td[9]"),
	ORDERING_BO_PROJECT_QUEUE_CREAT_PROJECT_PROJECT_NAME_MANDATORY_ERROR_MESSAGE_XPATH("(//*[@class='validation-error-wrapper bottom'])[1]"),
	ORDERING_BO_PROJECT_QUEUE_CREAT_PROJECT_DUE_DATE_MANDATORY_ERROR_MESSAGE_XPATH("(//*[@class='validation-error-wrapper bottom'])[2]"),
	ORDERING_BO_PROJECT_QUEUE_CREAT_PROJECT_TASK_TYPE_MANDATORY_ERROR_MESSAGE_XPATH("(//*[@class='validation-error-wrapper bottom'])[3]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_LOGNUMBER_COLUMN_VALUE_XPATH("//*[@id='request-queue-table']/tbody/tr[1]/td[3]"),
	ORDERING_BO_UPFIT_REQUEST_DETAIL_SCREEN_LOGNUMBER_ID_VALUE_XPATH("//*[@id='fix-row-info']/div[1]/div/span"),
	ORDERING_BO_REQUEST_QUEUE_HISTORY_BUTTON_XPATH("//*[@id='history-order']"),
	ORDERING_BO_REQUEST_QUEUE_SAVE_ORDER_BUTTON_XPATH("//*[@id='save-order']"),
	ORDERING_BO_REQUEST_QUEUE_RELEASE_BUTTON_XPATH("//*[@id='release-order']"),
	ORDERING_BO_REQUEST_QUEUE_CANCEL_BUTTON_XPATH("//*[@id='cancel']"),
	ORDERING_BO_REQUEST_QUEUE_ATTACHMENT_BUTTON_XPATH("//*[@id='mainContainer']/header/nav/div/div/div[2]/div/button"),
	ORDERING_BO_REQUEST_QUEUE_SIGNOUT_BUTTON_XPATH("//*[@id='mainContainer']/header/nav/div/div/div[2]/div/div[8]/form/input"),
	ORDERING_BO_REQUEST_QUEUE_GENERALORDER_BUTTON_XPATH("//*[@id='generalInfoTabId']"),
	ORDERING_BO_REQUEST_QUEUE_APPROVALINDICATOR_BUTTON_XPATH("//*[@id='approvalIndicatorsTabId']"),
	ORDERING_BO_REQUEST_QUEUE_DRIVERINFO_BUTTON_XPATH("//*[@id='driverInfoTabId']"),
	ORDERING_BO_REQUEST_QUEUE_VEHICLEINFO_BUTTON_XPATH("//*[@id='vehicleInfoTabId']"),
	ORDERING_BO_REQUEST_QUEUE_DEALEROPTION_BUTTON_XPATH("//*[@id='dioInfoTabId']"),
	ORDERING_BO_REQUEST_QUEUE_UPFIT_BUTTON_XPATH("//*[@id='upfitInfoTabId']"),
	ORDERING_BO_REQUEST_QUEUE_BILLING_BUTTON_XPATH("//*[@id='billingInfoTabId']"),
	ORDERING_BO_REQUEST_QUEUE_TITLELISE_BUTTON_XPATH("//*[@id='titleInfoTabId']"),
	ORDERING_BO_REQUEST_QUEUE_INSURANCE_BUTTON_XPATH("//*[@id='insuranceInfoTabId']"),
	ORDERING_BO_REQUEST_QUEUE_DEALER_BUTTON_XPATH("//*[@id='dealerInfoTabId']"),
	ORDERING_BO_REQUEST_QUEUE_CUSTOM_BUTTON_XPATH("//*[@id='customFieldsInfoTabId']"),
	ORDERING_BO_REQUEST_QUEUE_LOGNUMBER_LABEL_XPATH("//*[@id='fix-row-info']/div[1]/label"),
	ORDERING_BO_REQUEST_QUEUE_FLEET_LABEL_XPATH("//*[@id='fix-row-info']/div[2]/label"),
	ORDERING_BO_REQUEST_QUEUE_UNIT_LABEL_XPATH("//*[@id='fix-row-info']/div[3]/label"),
	ORDERING_BO_REQUEST_QUEUE_VIN_LABEL_XPATH("//*[@id='fix-row-info']/div[4]/label"),
	ORDERING_BO_REQUEST_QUEUE_FO_NUMBER_LABEL_XPATH("//*[@id='fix-row-info']/div[5]/label"),
	ORDERING_BO_REQUEST_QUEUE_PO_NUMBER_LABEL_XPATH("//*[@id='fix-row-info']/div[6]/label"),
	ORDERING_BO_REQUEST_QUEUE_ORDER_ID_LABEL_XPATH("//*[@id='fix-row-info']/div[7]/div"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_APPROVAL_QUOTE_XPATH("//*[@id='approvalIndicatorsTabId']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_CLIENTNUMBER_COLUMN_VALUE_XPATH("//*[@id='request-queue-table']/tbody/tr[2]/td[5]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_POPUP_YES_BUTTON_VALUE_XPATH("//*[@id='new-modal']/div/div/div[4]/button[2]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_POPUP_BUTTON_FLEET_VALUE_XPATH("//*[@id='saveUnlock']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_VEHICLE_TAB_XPATH("//*[@id='unit__specsvehicle']/div[1]/div[2]/ul/li[1]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_STANDARD_EQIP_XPATH("//*[@id='unit__specsvehicle']/div[1]/div[2]/ul/li[2]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_FEES_VALUE_XPATH("//*[@id='unit__specsvehicle']/div[1]/div[2]/ul/li[4]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_LINKED_CLIENT_VALUE_XPATH("//*[@id='unit__specsvehicle']/div[1]/div[2]/ul/li[5]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_GEO_LOC_VALUE_XPATH("//*[@id='unit__specsvehicle']/div[1]/div[2]/ul/li[6]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_CANCEL_VALUE_XPATH("//*[@id='manage__fleetSpecsSection']/div[1]/div[1]/div[1]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_POPUP_SCREEN_XPATH("//button[@id='leaveUnlock']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_POPUP_2_SCREEN_XPATH("//*[@id='to-queue']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ADVANCE_WORKFLOW_LOGNUMBER_VALUE_XPATH("//*[@id='advanced-workflow']/div[1]/div[2]/div[1]/ul/li[2]/span[2]"),
	ORDERING_BO_QUEUES_REQUEST_ADVANCE_ORDERSUMMARY_SCREEN_XPATH("//*[@id='advanced-workflow']/div[3]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_CHECKBOX_XPATH("//*[@id='request-queue-table_wrapper']/div[2]/div/div[3]/div[2]/div/table/tbody/tr[1]/td/label"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_THREE_DOTS_XPATH("//*[@id='row-action-menu-0']/div/div/button"),
	ORDERING_BO_QUEUES_REQUESTQUEUE_PROJECT_ID_COLUMN_XPATH("//*[@id='request-queue-table_wrapper']/div[2]/div/div[2]/div[1]/div/table/thead/tr/th[22]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_DEFAULT_VIEW_BUTTON_XPATH("//*[@id='default-view-button']/span"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_STANDARD_VIEW_BUTTON_XPATH("//*[@id='queue-actions']/div/div/div[2]/span/ul/li[1]/a"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_XPATH("//*[@id='row-action-menu-0']/div/div/ul[1]/li[2]"), 
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_PROJECT_NAME_XPATH("//*[@id='create-newproject-text']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_TASK_TYPE_XPATH("//*[@id='assign-project-task-type']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_DESCRIPTION_XPATH("//*[@id='assign-prjct-desc']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_DELETE_ACTION_XPATH("//*[@id='assign-project-table']/thead/tr/th[1]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_LOGNUMBER_XPATH("//*[@id='assign-project-table']/thead/tr/th[3]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_CLIENT_XPATH("//*[@id='assign-project-table']/thead/tr/th[4]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_UNIT_XPATH("//*[@id='assign-project-table']/thead/tr/th[5]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_SAVE_XPATH("//*[@id='modal-save-btn']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_SAVE_MESSAGE_XPATH("//*[@id='noty_bar_cd0c2df3-00e4-416e-a2a5-42592f3ac4d6']/div[1]/div[2]"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGNUSER_XPATH("//*[@id='row-action-assign-user-0']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_NEWASSIGNUSER_FIELD_XPATH("//*[@id='new-assigned']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_NEW_USER_FIELD_XPATH("//*[@id='assign-user-form']/div[2]/div/span/div"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_COMMENT_XPATH("//*[@id='row-action-comments-0']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_COMMENT_TYPE_XPATH("//*[@id='noteTypeCd-select']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_COMMENT_SOURCE_XPATH("//*[@id='source-select']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_XPATH("//*[@id='add-new-button']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_TYPE_XPATH("//*[@id='ss-primary-dropdown']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_SECTION_XPATH("//*[@id='comment']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_SAVE_XPATH("//*[@id='primary-button']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ROWACTION_SENDBACK_XPATH("//*[@id='row-action-send-back-0']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ROWACTION_SENDBACK_POPUP_XPATH("//*[@id='new-modal']/div/div"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_SENDBACK_COMMENT_SECTION_XPATH("//*[@id='notes']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_SENDBACK_SEND_BUTTON_SECTION_XPATH("//*[@id='send-modal-button']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_USER_SAVE_XPATH("//*[@id='modal-save-btn']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_DESTINATION_XPATH("//*[@id='PURCHASE_ORDER_MS']/span/div/ul/li[2]/a/label"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_NEW_USER_SAVE_BUTTON_XPATH("//*[@id='modal-save-btn']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_POPUP_XPATH("//*[@id='comment-add']/div/div"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ROWACTION_CHANGE_DUE_DATE_POPUP_XPATH("//*[@id='new-modal']/div/div"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_ROWACTION_CHANGE_DUE_DATE_XPATH("//*[@id='row-action-change-due-date-0']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_CHANGE_DUE_DATE_CALENDER_BUTTON_XPATH("//*[@id='change-due-date-form']/div/div/button/span"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_CHANGE_DUE_DATE_SAVE_BUTTON_XPATH("//*[@id='modal-save-btn']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_CHECKBOX1_XPATH("//*[@id='request-queue-table_wrapper']/div[2]/div/div[3]/div[2]/div/table/tbody/tr[2]/td/label"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_BULKACTION_RELEASE_XPATH("//*[@id='bulk-release-btn']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_BULKACTION_ASSIGNUSER_XPATH("//*[@id='bulk-assign-user-btn']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_BULKACTION_SENDBACK_XPATH("//*[@id='bulk-send-back-btn']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_BULKACTION_ASSIGN_PROJECT_XPATH("//*[@id='bulk-assign-project-btn']"),
	ORDERING_BO_UPFIT_REQUEST_QUEUE_BULKACTION_CHANGE_DUE_DATE_XPATH("//*[@id='bulk-change-due-date-btn']"),
	
	
	
	
	
	;

	private String value;

	private OrderingBOQueuesCommonXpathEnum(String value) {
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
