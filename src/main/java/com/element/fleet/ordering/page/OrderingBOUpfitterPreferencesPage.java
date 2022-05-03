package com.element.fleet.ordering.page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOAdditionalOptionsPageEnum;
import com.element.fleet.ordering.enums.OrderingBOBusinessMaintainedTablesEnum;
import com.element.fleet.ordering.enums.OrderingBOOnOrderQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingBOUpfitterPreferencesPageEnum;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingBOUpfitterPreferencesPage {

	/**
	 * This method adds New Upfitter preference
	 * @param upfitterID
	 * @param upfitterPreference 
	 * @lastModifiedBy dpatil
	 * @throws Exception
	 */
	public static void addNewUpfitterPreference(String upfitterID, String upfitterPreference) throws Throwable {
		CommonPage.clickElement(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_UPFITTER_PREFERENCES_ADD_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		BrowserAction.clickandClear(OrderingBOUpfitterPreferencesPageEnum.ordering_BO_BMT_ADD_UPFITTER_PREFERENCE_SUPPLIER_FIELD_ID);
		System.out.println("Upfitter entered is:: "+upfitterID);
		CommonPage.enterTextToInputField(OrderingBOUpfitterPreferencesPageEnum.ordering_BO_BMT_ADD_UPFITTER_PREFERENCE_SUPPLIER_FIELD_ID, upfitterID);
		if(BrowserAction.getElementText(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_UPFIT_PREFERENCE_ADD_UPFITTERSPECSEARCH_FIRSTOPTION_CSS).contains(upfitterID)) {
			BrowserAction.click(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_UPFIT_PREFERENCE_ADD_UPFITTERSPECSEARCH_FIRSTOPTION_CSS);
		}else {
			throw new OrderingErrorOccured("Not able to find upfitter");
		}
		CommonPage.clickElement(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_UPFITER_PREFERENCE_SEARCH_BUTTON_ID);
		CommonPage.clickElement(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_UPFITER_PREFERENCE_ADD_LINE_ITEM_XPATH);
		System.out.println("Communication type entered:: "+CommonPage.getTestData("CustomColumn1"));
		CommonPage.selectDropdownValue(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_ADD_UPFITTER_PREFERENCE_COMMUNICATION_TYPE_ID, CommonPage.getTestData("CustomColumn1"));
		System.out.println("Communication Preference Selected:: "+upfitterPreference);
		CommonPage.selectDropdownValue(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_ADD_UPFITTER_PREFERENCE_PREFERENCE_ID, upfitterPreference);
		CommonPage.selectDropdownValue(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_ADD_UPFITTER_PREFERENCE_COMMUNICATION_TYPE_NEWADD_ID, CommonPage.getTestData("CustomColumn2"));
		System.out.println("Communication Preference Selected:: "+upfitterPreference);
		CommonPage.selectDropdownValue(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_ADD_UPFITTER_PREFERENCE_PREFERENCE_NEWADD_ID, upfitterPreference);
		CommonPage.clickElement(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_ADD_UPFITTER_PREFERENCES_SAVE_ID);
		OrderingCommonPage.checkAlertPopUp();
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
	}
	
	/**
	 * This method navigates back to Upfitter preferences page 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void backToQueueView() throws Throwable {
		CommonPage.clickElement(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_UPFITTER_PREFERENCES_BACK_TO_QUEUE_VIEW_ID);
	}
	
	/**
	 * This method delete upfitter preference 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void deleteUpfitterPreference() throws Throwable {
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_AO_DELETE_ID);
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_CONFIRM_DELETE_AO_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS), "Record was successfully deleted");
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
	}
	
	/**
	 * This method checks if upfitter entry is present in upfitter preferences table
	 * @param upfitterID
	 * @return if upfitter present in preference table
	 * @lastModifiedBY djawale
	 * @throws Throwable 
	 */
	public static boolean isUpfitterPreferenceExists(String upfitterID) throws Throwable {
		OrderingBOUpfitterPreferencesPage.searchUpfitterUsingID(upfitterID);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH.getValue()), 1));
		String rowValue = CommonPage.getFieldValue(OrderingBOUpfitterPreferencesPageEnum.Ordering_BO_BMT_UPFITTER_PREFERENCES_SEARCH_RESULT_ROW_XPATH);
		if(!rowValue.equals("No data available in table")) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * This method search upfitter preference entry in upfitter preferences table
	 * @param upfitterID
	 * @lastModifiedBy djawale
	 * @throws Throwable
	 */
	public static void searchUpfitterUsingID(String upfitterID) throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_SEARCH_UPFITTER_ID_NAME);
		BrowserAction.clickandClear(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_SEARCH_UPFITTER_ID_NAME);
		System.out.println("Searching using option code:: "+upfitterID);
		CommonPage.enterTextToInputField(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_SEARCH_UPFITTER_ID_NAME, upfitterID);
		CommonPage.clickElement(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_SEARCH_BUTTON_CSS);
	}
	
	/**
	 * This method deletes upfitter preference
	 * @param upfitterID
	 * @lastModifiedBY djawale
	 * @throws Throwable 
	 */
	public static void deleteUpfitterPreferenceIfExist(String upfitterID) throws Throwable {
		if(OrderingBOUpfitterPreferencesPage.isUpfitterPreferenceExists(upfitterID)) {
			CommonPage.clickElement(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_UPFITTER_PREFERENCES_SEARCHRESULT_XPATH);
			CommonPage.clickElement(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_UPFITTER_PREFERENCES_ADD_UPFITTER_PREFERENCES_DELETE_ID);
			CommonPage.clickElement(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_UPFITTER_PREFERENCES_CONFIRM_DELETE_XPATH);
			OrderingCommonPage.checkAlertPopUp();
			BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH.getValue()), 1));
			String rowValue = CommonPage.getFieldValue(OrderingBOUpfitterPreferencesPageEnum.Ordering_BO_BMT_UPFITTER_PREFERENCES_SEARCH_RESULT_ROW_XPATH);
			if(!rowValue.equals("No data available in table")) {
				throw new OrderingErrorOccured("Upfitter Preference is not deleted");
			}
		}
	}

	/**
	 * This method changes upfitter preference from no preference to expectedPreference
	 * @param upfitterID
	 * @param expectedPreference
	 * @param currentPreference
	 * @throws Throwable
	 * @lastModifiedBy djawale
	 */
	public static void changeUpfitterPreference(String upfitterID, String currentPreference, String expectedPreference) throws Throwable{
		if(currentPreference.contains("No Preference")) {
			OrderingBOUpfitterPreferencesPage.deleteUpfitterPreferenceIfExist(upfitterID);
			OrderingBOUpfitterPreferencesPage.addNewUpfitterPreference(upfitterID,expectedPreference);
		}else if(expectedPreference.contains("No Preference")) {
			OrderingBOUpfitterPreferencesPage.deleteUpfitterPreferenceIfExist(upfitterID);
		}else {
			OrderingBOUpfitterPreferencesPage.updateUpfitterPreference(upfitterID, expectedPreference);
		}
	}
	
	/** This method updates the upfitter communication preference to desired
	 * @lastModifiedBy djawale
	 * @param upfitterName
	 * @param desiredCommunicationPreference
	 * @throws Exception
	 */
	public static void updateUpfitterPreference(String upfitterID, String desiredCommunicationPreference) throws Exception {
		BrowserAction.clickandClear(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_SEARCH_UPFITTER_ID_NAME);
		System.out.println("Entered Upfitter:: "+upfitterID);
		CommonPage.enterTextToInputField(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_SEARCH_UPFITTER_ID_NAME, upfitterID);
		System.out.println("Entered COmmunication Type:: "+CommonPage.getTestData("CustomColumn1"));
		BrowserAction.clickandClear(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_UPFITTER_PREFERENCES_COMMUNICATION_NAME);
		CommonPage.enterTextToInputField(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_UPFITTER_PREFERENCES_COMMUNICATION_NAME, CommonPage.getTestData("CustomColumn1"));
		CommonPage.clickElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		CommonPage.clickElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		System.out.println("Selecting preference:: "+desiredCommunicationPreference);
		CommonPage.selectDropdownValue(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_ADD_UPFITTER_PREFERENCE_PREFERENCE_ID, desiredCommunicationPreference);
		CommonPage.clickElement(OrderingBOUpfitterPreferencesPageEnum.ORDERING_BO_BMT_ADD_UPFITTER_PREFERENCES_SAVE_ID);
		OrderingCommonPage.checkAlertPopUp();
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
	}
}
