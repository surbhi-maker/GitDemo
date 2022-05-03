package com.element.fleet.ordering.enums;

public enum OrderingFeatureToggleEnum {
	
    FEATURE_TOGGLE_ADD_NEW_XPATH("//i[@class='fa fa-plus-circle']"),
    FEATURE_TOGGLE_FEATURE_NAME_CSS("input#featureName"),
    FEATURE_TOGGLE_FEATURE_DESCRIPTION_CSS("input#featureDescription"),
    FEATURE_TOGGLE_FEATURE_VALUE_CSS("input#featureValue"),
    FEATURE_TOGGLE_TOGGLE_NAME_CSS("input#toggleName0"),
    FEATURE_TOGGLE_TOGGLE_DESCRIPTION_CSS("input#toggleDescription0"),
    FEATURE_TOGGLE_TOGGLE_VALUE_CSS("input#toggleAttributeValue0"),
    FEATURE_TOGGLE_ADD_TOGGLE_XPATH("//a[@class='add pull-right']"),
    FEATURE_TOGGLE_TOOGLE_ACTION_XPATH("//select[@id='toggleActionCode0']"),
    FEATURE_TOGGLE_FEATURE_TYPE_XPATH("//select[@id='featureTypeCode']"),
    FEATURE_TOGGLE_TOGGLE_TYPE_XPATH("//select[@id='toggleTypeCode0']"),
    FEATURE_TOGGLE_TOGGLE_EXPORT_XPATH("//div[@class='btn blue btn-shared queue-export']"),
    FEATURE_TOGGLE_DELETEBTN_XPATH("//div[@id='delete-entity']"),
    FEATURE_TOGGLE_DELETE_YES_BUTTON_XPATH("//button[@class='modal-ok-btn']"),
    FEATURE_TOGGLE_DELETION_MESSAGE_XPATH("//div[contains(text(),'Record was successfully deleted')]"),
    FEATURE_TOGGLE_ACTIVE_FEATURE_XPATH("//label[@for='active']"),
    FEATURE_TOGGLE_FEATURE_SAVE_XPATH("//div[@id='save-entity']"),
    FEATURE_TOGGLE_ACTIVE_TOGGLE_XPATH("//label[@for='active0']"),
    FEATURE_TOGGLE_MAINT_SCR_FO_XPATH("//div[@class='modal-content']"),
    FEATURE_TOGGLE_SETTING_ICON_XPATH("#mainContainer>header>nav>div>div>div.wrapnavbar__right-section>div>ul>li.settings__exect"),
	FEATURE_TOGGLE_SEARCH_FEATURE_TOGGLE_XPATH("//table[@id='queue-table']//span[text() = '%s']");

	private String value;

	OrderingFeatureToggleEnum(String value) {
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
