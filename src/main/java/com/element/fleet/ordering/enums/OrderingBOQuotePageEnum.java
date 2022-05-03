package com.element.fleet.ordering.enums;

public enum OrderingBOQuotePageEnum {
	
	ORDERING_BO_EDIT_QUOTE_PAGE_HEADING_CSS("#main-section > div.subscreen-title"),
	ORDERING_BO_EDIT_QUOTE_PAGE_ID("main-section"),
	ORDERING_BO_EDIT_QUOTE_PAGE_SUPPLIERS_SECTION_ID("supplier-section"),
	ORDERING_BO_EDIT_QUOTE_PAGE_LINE_ITEMS_ID("line-item-section"),
	ORDERING_BO_EDIT_QUOTE_PAGE_CLOSE_BUTTON_CSS("i.fa.fa-close"),
	ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_XPATH("//div[@id='cancel-popup']//div[@class='modal-content']"),
	ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_MESSAGE_XPATH("//div[@id='cancel-popup']//div[@class='modal-body text-center']"),
	ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_STAY_BUTTON_XPATH("//div[@id='cancel-popup']//button[@id='no-cancel']"),
	ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_YES_BUTTON_XPATH("//div[@id='cancel-popup']//button[@id='yes-cancel']"),
	ORDERING_BO_EDIT_QUOTE_PAGE_CONVERTTOUPFITSPEC_LABEL_VALUE("Convert to Upfit Spec"),
	ORDERING_BO_EDIT_QUOTE_PAGE_CONVERTTOUPFITSPEC_LABEL_XPATH("//div[@id='convert']"),
	ORDERING_BO_EDIT_QUOTE_PAGE_SAVE_LABEL_VALUE("Save"),
	ORDERING_BO_EDIT_QUOTE_PAGE_SAVE_LABEL_XPATH("//div[@id='saveQuote']"),
	ORDERING_BO_EDIT_QUOTE_PAGE_EDITUPFITQUOTE_LABEL_VALUE("Edit Upfit Quote"),
	ORDERING_BO_EDIT_QUOTE_PAGE_EDITUPFITQUOTE_LABEL_XPATH("//div[@class='subscreen-title']"),
	ORDERING_BO_EDIT_QUOTE_PAGE_SUPPLIERS_LABEL_VALUE("Suppliers"),
	ORDERING_BO_EDIT_QUOTE_PAGE_SUPPLIERS_LABEL_XPATH("(//h3)[1]"),
	ORDERING_BO_EDIT_QUOTE_PAGE_CHASSISINFO_LABEL_VALUE("Chassis Information"),
	ORDERING_BO_EDIT_QUOTE_PAGE_CHASSISINFO_LABEL_XPATH("(//h3)[2]"),
	ORDERING_BO_EDIT_QUOTE_PAGE_LINEITEMS_LABEL_VALUE("Line Items"),
	ORDERING_BO_EDIT_QUOTE_PAGE_LINEITEMS_LABEL_XPATH("(//h3)[3]"),
	ORDERING_BO_QUOTES_QUEUE_PAGE_QUOTEID_XPATH("//td[@data-name='quoteId' and @data-val='%s']"),
	;

	private String value;

	OrderingBOQuotePageEnum(String value) {
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