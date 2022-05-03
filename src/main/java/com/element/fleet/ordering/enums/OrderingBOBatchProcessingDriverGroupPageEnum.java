package com.element.fleet.ordering.enums;

public enum OrderingBOBatchProcessingDriverGroupPageEnum {

	ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_SELECT_UPLOAD_TEMPLATE_XPATH("//select[@name='typeOfUpload']//option[contains(text(),'Select Template')]"),
	ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_UPLOAD_TEMPLATE_XPATH("//select[@name='typeOfUpload']//option[contains(text(),'Select Template')]"),
	ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_SELECT_DOWNLOAD_TEMPLATE_XPATH("//select[@name='downloadType']//option[contains(text(),'Select Template')]"),
	ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_DOWNLOAD_TEMPLATE_XPATH("//select[@name='downloadType']//option[contains(text(),'Select Template')]"),
	ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_LABEL_TITLE_XPATH("//span[@class='label-title']"),
	ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_LABEL_TITLE_XPATH("//span[@class='label-title']"),
	ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_SELECT_DOWNLOAD_TEMPLATE_DROPDOWN_XPATH("//select[@name='downloadType']"),
	ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_DOWNLOAD_TEMPLATE_DROPDOWN_XPATH("//select[@name='downloadType']"),
	ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_TEMPLATE_XPATH("//select[@name='downloadType']//option[contains(text(),'Driver Groups- Add')]"),
	ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_TEMPLATE_XPATH("//select[@name='downloadType']//option[contains(text(),'Driver Groups- Mass Update')]"),
	ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_DOWNLOAD_TEMPLATE_BUTTON_XPATH("//div[@class='btn download-template']"),
	ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_DOWNLOAD_TEMPLATE_BUTTON_XPATH("//div[@class='btn download-template']"),
	ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_SELECT_UPLOAD_TEMPLATE_DROPDOWN_XPATH("//select[@name='typeOfUpload']"),
	ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_UPLOAD_TEMPLATE_DROPDOWN_XPATH("//select[@name='typeOfUpload']"),
	ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUPS_ADD_TEMPLATE_XPATH("//select[@name='typeOfUpload']//option[contains(text(),'Driver Groups- Add')]"),
	ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_MASS_UPDATE_TEMPLATE_XPATH("//select[@name='typeOfUpload']//option[contains(text(),'Driver Groups- Mass Update')]"),
	ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUPS_SEND_BUTTON_XPATH("//button[@class='btn upload-submit']"),
	ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SEND_BUTTON_XPATH("//button[@class='btn upload-submit']"),
	ORDERING_MLO_DRIVER_GROUPS_SEARCH_BUTTON_XPATH("//div[@id='driver-groups-queue']//span[@class='fa fa-search']"),
	ORDERING_MLO_DRIVER_GROUP_NAME_XPATH("//div[@id='section-driver-groups']//td[3]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_NAME_SEARCH_FIELD_XPATH("//input[@name='driverGroupName']"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_NAME_SEARCH_BUTTON_XPATH("//div[@id='driver-groups-queue']//div[@class='btn green btn-shared search']"),
	ORDERING_BO_BATCH_PROCESSING_INVALID_RECORDS_COUNT_XPATH("//tbody/tr[1]/td[10]"),
	ORDERING_BO_BATCH_PROCESSING_ERROR_RECORDS_COUNT_XPATH("//tbody/tr[1]/td[11]"),
	ORDERING_BO_BATCH_PROCESSING_ERROR_TEMPLATE_DOWNLOAD_XPATH("//tbody/tr[1]/td[12]/a[1]/i[1]"),
	ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_TABLE_DATA_XPATH("//div[@id='driver-groups-summary']//td[@class='dataTables_empty']"),
	ORDERING_BO_BATCH_PROCESSING_UPLOAD_FILE_NAME_AND_PATH_XPATH("//input[@id='upload-file']"),
	;
	private String value;

	OrderingBOBatchProcessingDriverGroupPageEnum(String value) {
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