package com.element.fleet.ordering.enums;

public enum OrderingBOBatchProcessingPageEnum {

	ORDERING_BO_BO_UPLOAD_FILES_TABLE_ID("upload-files"),
	ORDERING_BO_BO_LABEL_TITLE_XPATH("//div[@class='panel-heading']//following::span[@class='label-title']"),
	ORDERING_BO_BO_LABEL_TITLE_EXPECTED("List of Uploaded Batch Ordering Files"),
	OREDRING_BO_BO_TEMPLATE_DROPDOWN_NAME("downloadType"),
	ORDERING_BO_BO_DOWNLOAD_BUTTON_CSS("div.btn.download-template"),
	ORDERING_BO_BO_UPLOAD_FILE_ID("upload-file-label"),
	ORDERING_BO_BO_SEND_CSS("button.btn.upload-submit"),
	ORDERING_BO_BO_TYPE_OF_UPLOAD_NAME("typeOfUpload"),
	ORDERING_BO_BATCH_PROCESSING_FILE_XPATH("//table[@id='batch-table']//tbody//tr[1]//td[5]//a"),
	ORDERING_BO_RESULT_FILE_DOWNLOAD_XPATH("//table[@id='batch-table']//tbody//tr[1]//td[12]//a"),
	ORDERING_BO_BATCH_UPLOAD_LABEL_ID("upload-file-label"),
	ORDERING_BO_BATCH_STATUS_XPATH("//table[@id='batch-table']//tbody//tr[1]//td[7]"),
	;

	private String value;

	OrderingBOBatchProcessingPageEnum(String value) {
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
