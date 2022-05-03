package com.element.fleet.ordering.enums;

public enum OrderingBOInvoiceEntryPageEnum {
	
	ORDERING_BO_IE_ORDER_SEARCH_XPATH("//div[@id='invoices']//div[@id='smart-search']"),
	ORDERING_BO_IE_SUBSCREEN_TITLE_XPATH("//h1[contains(text(),'Invoice Entry')]"),
	ORDERING_BO_INVOICE_ENTRY_ORDER_SEARCH_TEXT_FIELD_ID("order-search"),
	ORDERING_BO_INVOICE_ENTRY_SAVE_BUTTON_XPATH("//div[@id='save_invoice']//span"),
	ORDERING_BO_INVOICE_ENTRY_CANCEL_BUTTON_XPATH("//div[@id='cancel_invoice']//span"),
	ORDERING_BO_INVOICE_ENTRY_LIST_LOG_NUMBER_XPATH("//input[@id='order-search']//parent::span//u[contains(text(),'Log No.')]//parent::div"),
	ORDERING_BO_INVOICE_ENTRY_PO_NUMBER_DROP_DOWN_ID("purchaseOrder"),
	ORDERING_BO_INVOICE_ENTRY_PO_NUMBER_DROP_DOWN_OPTION_XPATH("//select[@id='purchaseOrder']//option[2]"),
	ORDERING_BO_INVOICE_ENTRY_INVOICE_RECIEVED_DATE_CALENDAR_SYMBOL_XPATH("//label[contains(text(),'Invoice Received Date')]//following-sibling::div//button"),
	ORDERING_BO_INVOICE_ENTRY_SELECT_INVOICE_RECIEVED_DATE_CSS("input#invoiceReceivedDate + button.datepicker-btn > span"),
	ORDERING_BO_INVOICE_ENTRY_SELECT_TODAY_DATE_CSS("div#ui-datepicker-div[style*='display: block;'] td.ui-datepicker-today > a"),
	ORDERING_BO_INVOICE_ENTRY_SELECT_NEXT_INVOICE_RECIEVED_DATE_XPATH("//td[@class=' ui-datepicker-days-cell-over  ui-datepicker-today']//a//following::td[1]//a"),
	ORDERING_BO_INVOICE_ENTRY_INVOICE_NUMBER_ID("invoiceNum"),
	ORDERING_BO_INVOICE_ENTRY_VENDOR_INVOICE_DATE_CALENDAR_SYMBOL_XPATH("//label[contains(text(),'Vendor Invoice Date')]//following-sibling::div//button"),
	ORDERING_BO_INVOICE_ENTRY_SELECT_VENDOR_INVOICE_DATE_CSS("input#vendorInvoiceDate+ button.datepicker-btn > span"),
	ORDERING_BO_INVOICE_ENTRY_SELECT_NEXT_VENDOR_INVOICE_DATE_XPATH("//td[@class=' ui-datepicker-days-cell-over  ui-datepicker-today']//a//following::td[1]//a"),
	ORDERING_BO_INVOICE_ENTRY_INVOICE_AMOUNT_ID("invoiceAmt"),
	ORDERING_BO_INVOICE_ENTRY_DELIVERY_DATE_CALENDAR_SYMBOL_XPATH("//label[contains(text(),'Delivery Date')]//following-sibling::div//button"),
	ORDERING_BO_INVOICE_ENTRY_SELECT_DELIVERY_DATE_XPATH("//td[@class=' ui-datepicker-days-cell-over  ui-datepicker-today']//a"),
	ORDERING_BO_INVOICE_ENTRY_SELECT_DELIVERY_DATE_CSS("input#deliveryDate+ button.datepicker-btn > span"),
	ORDERING_BO_INVOICE_ENTRY_SELECT_NEXT_DELIVERY_DATE_XPATH("//td[@class=' ui-datepicker-days-cell-over  ui-datepicker-today']//a//following::td[1]//a"),
	ORDERING_BO_INVOICE_ENTRY_INVOICE_NUMBER_LIST_XPATH("//th[contains(text(),'Invoice Number')]//following::tbody//tr//td[2]"),
	ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_XPATH("//th[contains(text(),'Invoice Number')]//following::tbody//tr//td[2][contains(text(),'$InvoiceNumber#')]//following::td[@id='attachmentBtn'][1]//span"),
	ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_BROWSER_BUTTON_XPATH("//th[contains(text(),'Invoice Number')]//following::tbody//tr//td[2][contains(text(),'$InvoiceNumber#')]//following::td[@id='attachmentBtn'][1]//input[@id='upload-file']"),
	ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_SEND_BUTTON_XPATH("//th[contains(text(),'Invoice Number')]//following::tbody//tr//td[2][contains(text(),'$InvoiceNumber#')]//following::td[@id='attachmentBtn'][1]//button"),
	ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_LINK_XPATH("//th[contains(text(),'Invoice Number')]//following::tbody//tr//td[2][contains(text(),'$InvoiceNumber#')]//following::td[@id='attachmentBtn'][1]//a"),
	ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_LIST_COLUMNS_XPATH("//table[@id='invoice-table']//th"),
	ORDERING_BO_INVOICE_ENTRY_COLUMN_NAME_XPATH("//th[contains(text(),'$ColumnName#')]"),
	ORDERING_BO_INVOICE_ENTRY_COLUMN_NAME_SORTING_XPATH("//th[contains(text(),'Invoice Number')]//following::tbody//tr//td[$ColumnCount#]"),
	;
		
	private String value;

	OrderingBOInvoiceEntryPageEnum(String value) {
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
