package com.element.fleet.ordering.enums;

public enum OrderingBOUpfitSpecPageEnum {
	
	ORDERING_BO_UPFIT_SPEC_PAGE_ID("main-section"),
	ORDERING_BO_UPFIT_SPEC_PAGE_SUPPLIERS_SECTION_ID("supplier-section"),
	ORDERING_BO_UPFIT_SPEC_PAGE_LINE_ITEMS_ID("standard-line-item-section"),
	ORDERING_BO_UPFIT_SPEC_PAGE_OPTIONAL_LINE_ITEMS_ID("optional-line-item-section"),
	ORDERING_BO_UPFIT_SPEC_PAGE_SAVEBTN_XPATH("//div[@id='saveUpfit']"),
	ORDERING_BO_UPFIT_SPEC_PAGE_SAVEBTN_EXPECTED("Save"),
	ORDERING_BO_UPFIT_SPEC_PAGE_MANAGEGRPSBTN_XPATH("//div[@id='manageGroup']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_MANAGEGRPSBTN_EXPECTED("Manage Groups"),
    ORDERING_BO_UPFIT_SPEC_PAGE_ELEMENTAPRVBTN_XPATH("//div[@id='elementApprove']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_ELEMENTAPRVBTN_EXPECTED("Element Approve"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SUBSCREENTITLE_EXPECTED("Upfit Spec Maintenance"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SUPPLIERSTITLE_XPATH("//div[@id='upfit-spec-suppliers']//child::h3"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SUPPLIERSTITLE_EXPECTED("Suppliers"),
    ORDERING_BO_UPFIT_SPEC_PAGE_LINEITEMSTITLE_EXPECTED("Line Items"),
    ORDERING_BO_UPFIT_SPEC_PAGE_LINEITEMSTITLE_XPATH("//div[@id='standard-line-item-section']//child::h3"),
    ORDERING_BO_UPFIT_SPEC_PAGE_LINEITEMSOPTIONAL_XPATH("//div[@id='optional-line-item-section']//child::h4"),
    ORDERING_BO_UPFIT_SPEC_PAGE_LINEITEMSOPTIONAL_EXPECTED("Optional Items"),
    ORDERING_BO_UPFIT_SPEC_PAGE_TITLE_EXPECTED("Queues"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SUBTITLE_EXPECTED("- Upfit Spec"),
    ORDERING_BO_UPFIT_SPEC_PAGE_EXPORT_EXPECTED("Export"),
    ORDERING_BO_UPFIT_SPEC_PAGE_ADDREMOVE_EXPECTED("Add/Remove"),
    ORDERING_BO_UPFIT_SPEC_PAGE_EXPORTPDF_XPATH("//div[@id='exportPDF']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_EXPORTPDF_EXPECTED("Export to PDF"),
    ORDERING_BO_UPFIT_SPEC_PAGE_ADDREMOVEOPTIONSELECT_XPATH ("//div[@class='listContainer dropdown-menu drpdwnCol']//label[contains(text(),'%s')]"),
    ORDERING_BO_UPFIT_SPEC_PAGE_COLUMNLISTNAME_XPATH("(//table[@class='table datatable queue-table dataTable no-footer'])[1]//following-sibling::th[not(contains(@style,'display: none'))]"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SELECTALL_XPATH("//label[@for='selectAllColumn']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_CLOSECROSS_XPATH("//a[@title='Close']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SAVEAS_XPATH("//a[@title='Save As']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SAVEVIEW_XPATH("//a[@class='saveBtn pull-right']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_INPUTNAME_XPATH("//input[@placeholder='Type in Name']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SAVEASOK_XPATH("//button[@class='btnSaveAsOk']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_VIEWDRPDWN_XPATH("//button[@class='dropdown-toggle btndropdown primaryText']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_CONTROLSLINK_XPATH("//button[@class='filterControls']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_CONTROLS_OPTIONS_XPATH("//a[@data-f-name='?']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_CONTROLS_OPTIONSEARCH_XPATH("//a[@data-f-name='?']//following::input[1]"),
    ORDERING_BO_UPFIT_SPEC_PAGE_CONTROLS_APPLYBTN_XPATH("//button[@class='btnApply applyButton']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_EXPORTCSV_XPATH("//a[@class='exportExcelBtn pull-right']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_POP_UP_ID("dialogpopSaveAs"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVE_OPTION_ID("popSave"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SELECT_VIEWS_ID("btnDropdownViews"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVE_OPTION_OK_CLASS("btnSaveOk"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVEAS_OPTION_ID("popSaveAs"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVEAS_NAME_ID("popInput"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVEAS_SAVE_BUTTON_CLASS("btnSaveAsOk"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVE_OPTION_CANCEL_XPATH("//button[@title='Click Cancel']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_XPATH("//a[@class='addRemoveBtn pull-right']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_XPATH("//label[@for='selectAllColumn']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS("closeBtn"),
    ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_COLUMN_LIST_XPATH("//label[@class='spnOptions']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_HEADER_COLUMN_LIST_XPATH("//div[@class='dataTables_scrollHeadInner']//th"),
    ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_COLUMN_LAST_COLUMN_XPATH("//div[@class='dataTables_scrollHeadInner']//th[24]"),
    ORDERING_BO_UPFIT_SPEC_PAGE_TITLE_NAME_XPATH("//h1[@id='queue-page-title']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_FILTER_BUTTON_CLASS("filterControls"),
    ORDERING_BO_UPFIT_SPEC_PAGE_NAME_APPLY_XPATH("//button[contains(text(),'Apply')]"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SEARCH_BY_XPATH("//a[@data-f-name='#SearchBy$']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_SEARCH_BY_SEARCH_TEXT_XPATH("//a[@data-f-name='#SearchBy$']//following::input[1]"),
    ORDERING_BO_UPFIT_SPEC_PAGE_UPFIT_NAME_LIST_XPATH("//td[@data-name='#ColumnDataName$']//span"),
    ORDERING_BO_UPFIT_SPEC_PAGE_APPLIED_CONTROLS_XPATH("//span[@class='removeSelected']"),
    ORDERING_BO_UPFIT_SPEC_PAGE_PAGINATION_NAME("queue-table_length"),
    ORDERING_BO_UPFIT_SPEC_PAGE_STANDARD_VIEW_DROPDOWN_ID("btnDropdownViews"),
    ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_CLOSE_XPATH("//div[@class='close pull-right']//i"),
    ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_QUOTE_NUMBER_ID("quote-number"),
    ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_QUOTE_DATE_XPATH("//input[@name='quote-date']"),
    ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_SAVE_QUOTE_ID("saveUpfit"),
    ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_BTN_XPATH("//div[@id='manageGroup']"),
    ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_DATA_TABLES_EMPTY_XPATH("//table[@id='group-table']//td[@class='dataTables_empty']"),
    ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_DELETE_GROUP_XPATH("//table[@id='group-table']//i[@class='fa fa-trash']"),
    ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_TEXTBOX_XPATH("//table[@id='group-table']//input[contains(@class,'inline')]"),
    ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_ADD_XPATH("//table[@id='group-table']//i[@class='fa fa-plus']"),
    ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_RULE_DROPDOWN_XPATH("//table[@id='group-table']//select[contains(@id,'upfitSpec')]"),
    ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_GROUP_DROPDOWN_XPATH("//table[@id='item-table']//select[contains(@id,'group')]"),
    ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_SUBMIT_BTN_XPATH("//button[@id='confirm-submit']"),
    ORDERING_BO_UPFIT_SPEC_SEARCH_LIST_XPATH("//div[@id='filter-content-accordion']//a")
    ;

	private String value;

	OrderingBOUpfitSpecPageEnum(String value) {
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





