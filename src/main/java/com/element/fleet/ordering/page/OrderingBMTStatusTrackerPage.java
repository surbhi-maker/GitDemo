package com.element.fleet.ordering.page;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.ExcelUtil;
import com.element.fleet.ordering.enums.OrderingBMTStatusTrackerPageEnum;
import com.element.fleet.ordering.enums.OrderingBOOnOrderQueuePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserAssert;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;

public class OrderingBMTStatusTrackerPage {
	
	/**
	 * This method verifies that whether use is able to update existing parent and add some children's to it
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void updateExistingParent() throws Exception {
		CommonPage.forceClearTextField(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_PARENT_NAME_TEXTBOX_XPATH);
		CommonPage.loadXMLParameterToTestData("ParentName", "Parent-"+CommonPage.randomNumberInRange(999999));
		CommonPage.enterTextToInputField(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_PARENT_NAME_TEXTBOX_XPATH, CommonPage.getTestData("ParentName"));
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SAVE_NEW_RECORD_XPATH);
		verifyConfirmationMessage(CommonPage.getTestData("UpdateParentMessage"));
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifies that whether use is able to update existing parent and add some children's to it
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void verifyUpdateParentWithNewChildren() throws Exception {
		List<String> updatedClients = new ArrayList<String>(Arrays.asList(CommonPage.getTestData("UpdatedClients").split("\\|")));
		addParentChilrensRelationshipRecord(updatedClients);
		clickRelationshipSaveButton();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		verifyUserNavigatedToStatusTrackerTab(CommonPage.getTestData("MPCR"));
		updatedClients.addAll(Arrays.asList(CommonPage.getTestData("SearchClients").split("\\|")));
		verifyChildrensAssocitedToParent(CommonPage.getTestData("ParentName"), updatedClients);		
	}

	/**
	 * This method verifies that whether expected list of clients are having actual or updated list of clients
	 * @lastModifiedBy ADhawale
	 * @param actualClients
	 * @param expectedClients
	 * @throws Exception
	 */
	public static void compareActualAndExpectedClients(List<String> actualClients, List<String> expectedClients) throws Exception {
		System.out.println("Actual clients= "+actualClients);
		System.out.println("Expected clients= "+expectedClients);
		BrowserAssert.assertTrue(actualClients.size()==expectedClients.size(), "actual and expected lists are not having same number of elements");
		int counter=0;
		for(int j=0; j<expectedClients.size(); j++) {
			if(expectedClients.get(j).contains(actualClients.get(counter))) {
				System.out.println("matching element found in expected clients list :"+actualClients.get(counter));
				counter++;
			}
		}		
	}
	
	/**
	 * This method verifies that whether use is able to add a child to new parent
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void addNewParentChildRelation() throws Exception {
		clickAddNewButton();
		OrderingCommonPage.checkAlertPopUp();
		List<String> searchClients = Arrays.asList(CommonPage.getTestData("SearchClients").split("\\|"));
		addParentChilrensRelationshipRecord(searchClients);
		List<WebElement> linkedClients = BrowserAccess.getElements(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_LINKED_CLIENTS_LABELS_XPATH);
		compareActualAndExpectedClients(searchClients, createListOfStrings(linkedClients));
		BrowserAssert.assertTrue(searchClients.size()==createListOfStrings(linkedClients).size(), "Childern count doesn't match");	
		clickRelationshipSaveButton();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		verifyChildrensAssocitedToParent(CommonPage.getTestData("ParentName"), searchClients);
	}
	
	/**
	 * This method selects children's on parent and child relationship record page associated to respective parent
	 * @lastModifiedBy ADhawale
	 * @param searchClients
	 * @throws Exception
	 */
	public static void addParentChilrensRelationshipRecord(List<String> searchClients) throws Exception {
		selectCorpCode();
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SELECT_PARENT_NAME_DROPDOWN_XPATH);
		BrowserAction.selectDropdownOptionByText(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SELECT_PARENT_NAME_DROPDOWN_XPATH, CommonPage.getTestData("ParentName"));
		for(String client : searchClients) {
			CommonPage.enterTextToInputField(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SEARCH_CLIENT_SUGGESTION_XPATH, client);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SEARCH_CLIENT_FIRST_SUGGESTION_XPATH);
			BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SEARCH_CLIENT_FIRST_SUGGESTION_XPATH);
		}
	}
	
	/**
	 * This method deletes and  Verifies the Deleted Relationships
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifyDeletedChildParentRelation() throws Exception {
		clickDeleteRelationship();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		searchRecords("Parent Name", CommonPage.getTestData("ParentName"));
		verifySearchedRecords(false);
	}
	
	/**
	 * This method Clicks on Delete Button on Add New Relationship pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickDeleteRelationship() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_DELETE_RELATIONSHIP_RECORD_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_DELETE_RELATIONSHIP_RECORD_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_DELETE_RELATIONSHIP_RECORD_XPATH);
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_DELETE_RELATIONSHIP_RECORD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_DELETE_RELATIONSHIP_DIALOGBOX_XPATH);
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_DELETE_RELATIONSHIP_YES_BUTTON_XPATH);
		
	}
	
	/**
	 * This method Clicks on Delete Button on Add New Relationship pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void removeLinkedClientsFromRelationship() throws Exception {
		List<WebElement> linkedClients = BrowserAccess.getElements(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_LINKED_CLIENTS_LABELS_XPATH);
		for(WebElement client : linkedClients) {
			BrowserWait.waitUntilElementIsDisplayed(client);
			client.click();
		}
		clickRelationshipSaveButton();
		String validationError = BrowserAccess.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_EMPTY_LINKED_CLIENTS_ERROR_XPATH).getText().trim();
		assertEquals(validationError, "Linked clients are required. Please add at least one.", "Error Message is not show for Empty Linked Client List");
		clickCancelRelationship();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method Clicks on Cancel Button on Add New Relationship pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickCancelRelationship() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CANCEL_RELATIONSHIP_RECORD_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CANCEL_RELATIONSHIP_RECORD_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CANCEL_RELATIONSHIP_RECORD_XPATH);
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CANCEL_RELATIONSHIP_RECORD_XPATH);
	}
	
	/**
	 * This method verifies children's present on parent and child relationship record page associated to respective parent
	 * @lastModifiedBy ADhawale
	 * @param parentName
	 * @param searchClients
	 * @throws Exception
	 */
	public static void verifyChildrensAssocitedToParent(String parentName, List<String> searchClients) throws Exception {
		searchRecords("Parent Name", parentName);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		List<WebElement> childrensList = WebDriverAccess.getElements(BrowserAccess.getLocator(
				OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_RELATIONSHIP_CHILDRENS_XPATH.name(), String.format(
				OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_RELATIONSHIP_CHILDRENS_XPATH.toString(), parentName)));
		
		if(childrensList.isEmpty()) {
			System.out.println("No childrens present for given parent = "+parentName);
		} else {
			compareActualAndExpectedClients(searchClients, createListOfStrings(childrensList));
		}
	}
	
	/**
	 * This method create a list of strings and takes WebElements list as a parameter
	 * @lastModifiedBy ADhawale
	 * @return listOfStrings
	 * @throws Exception
	 */
	public static List<String> createListOfStrings(List<WebElement> list) throws Exception {
		List<String> listOfStrings = new ArrayList<String>();
		for(WebElement element : list) {
			listOfStrings.add(element.getText().trim());		    
		}
		return listOfStrings;
	}
	
	/**
	 * This method clicks on save button present on add parent and child relationship record page
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void clickRelationshipSaveButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_RELATIONSHIP_SAVE_NEW_RECORD_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_RELATIONSHIP_SAVE_NEW_RECORD_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_RELATIONSHIP_SAVE_NEW_RECORD_XPATH);
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_RELATIONSHIP_SAVE_NEW_RECORD_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifies all the elements of manage parents page
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void verifyManageParentsElements() throws Exception {
		clickOnStatusTrackerSectionTabs(CommonPage.getTestData("ManageParents"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_BUTTON_XPATH);
		verifyStatusTrackerPageElements();
		BrowserAssert.assertTrue(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_FILTER_TOGGLE_BUTTON_XPATH).isDisplayed(), "Filters link was not displayed");
		WebElement clearFilter=BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_CLEARFILTER_BUTTON_XPATH);
		WebElement searchButton=BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_BUTTON_XPATH);
		BrowserAssert.assertTrue(clearFilter.isDisplayed(), "clear filter button was not displayed");
		BrowserAssert.assertTrue(searchButton.isDisplayed(), "Search button was not displayed");
		BrowserAssert.assertTrue(Color.fromString(clearFilter.getCssValue("color")).asHex().equals("#bf2e2e"), "Clear filter colour is not red");
		BrowserAssert.assertTrue(Color.fromString(searchButton.getCssValue("background-color")).asHex().equals("#789ac1"), "search button background colour is not blue");
		BrowserAssert.assertTrue(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BTM_STATUS_TRACKER_ADDNEW_BUTTON_XPATH).isDisplayed(), "Add new button was not displayed");
		BrowserAssert.assertTrue(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_EXPORT_BUTTON_XPATH).isDisplayed(), "Export button was not displayed");
	}

	/**
	 * This method verifies the functionality of Search and Toggle Filters
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void verifySearchAndToggleFilters() throws Exception {
		searchRecords("Corp", CommonPage.getTestData("CorpCode"));
		verifySearchedRecords(true);
		verifyManageParentsFiltersToggleElements(false);
		verifyManageParentsFiltersToggleElements(true);
		searchRecords("Corp", CommonPage.getTestData("CorpCode"));
		verifySearchedRecords(true);
	}

	/**
	 * This method verifies pagination functionality
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void verifyPaginationFunctionality() throws Exception {
		verifyTableRowsCount("100");
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_LAST_PAGE_NUMBER_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_FIRST_PAGE_NUMBER_XPATH);
		int firstPageNumber=Integer.parseInt(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_FIRST_PAGE_NUMBER_XPATH).getText().trim());
		int nextPageNumber=firstPageNumber+1;
		int lastPageNumber=Integer.parseInt(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_LAST_PAGE_NUMBER_XPATH).getText().replace("/", "").trim());
		BrowserAssert.assertTrue(firstPageNumber==1, "First page number is not equal to 1 in pagination");
		BrowserAssert.assertTrue(nextPageNumber==2, "Next page number is not equal to 2 in pagination");
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_LAST_PAGE_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_FIRST_PAGE_NUMBER_XPATH);
		BrowserAssert.assertTrue(lastPageNumber==Integer.parseInt(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_FIRST_PAGE_NUMBER_XPATH).getText().trim()), "last page number is not correct");
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_FIRST_PAGE_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_FIRST_PAGE_NUMBER_XPATH);
		BrowserAssert.assertTrue(firstPageNumber==Integer.parseInt(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_FIRST_PAGE_NUMBER_XPATH).getText().trim()), "first page number is not correct");
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_NEXT_PAGE_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_FIRST_PAGE_NUMBER_XPATH);
		BrowserAssert.assertTrue(nextPageNumber==Integer.parseInt(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_FIRST_PAGE_NUMBER_XPATH).getText().trim()), "next page number is not correct");		
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_PREVIOUS_PAGE_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_FIRST_PAGE_NUMBER_XPATH);
		BrowserAssert.assertTrue(firstPageNumber==Integer.parseInt(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_FIRST_PAGE_NUMBER_XPATH).getText().trim()), "previous page number is not correct");
	}
	
	/**
	 * This method verifies that whether count of table rows is matching with selected dropdown value
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void verifyTableRowsCount(String count) throws Exception {
		((JavascriptExecutor)WebDriverAccess.getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		BrowserAction.selectDropdownOptionByValue(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_PAGINATION_RECORDS_DROPDOWN_XPATH, count);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserAssert.assertTrue(BrowserAction.getElements(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_ROWS_XPATH).size()==Integer.parseInt(count), "count of records displayed in table is not matching with selected dropdown value");
	}

	/**
	 * This method verifies that whether list of elements are displayed when filters toggle is turned on and vice a versa
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void verifyManageParentsFiltersToggleElements(boolean elementsToBeDisplayed) throws Exception {
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_FILTER_TOGGLE_BUTTON_XPATH);
		if(elementsToBeDisplayed) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_BUTTON_XPATH);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CORP_CODE_TEXTBOX_XPATH);
			BrowserAssert.assertTrue(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_CLEARFILTER_BUTTON_XPATH).isDisplayed(), "Clear filter button was not displayed");
			BrowserAssert.assertTrue(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_BUTTON_XPATH).isDisplayed(), "Search button was not displayed");
		} else {
			BrowserWait.waitUntilElementIsNotDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_BUTTON_XPATH);
			BrowserWait.waitUntilElementIsNotDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CORP_CODE_TEXTBOX_XPATH);
			BrowserAssert.assertFalse(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CORP_CODE_TEXTBOX_XPATH).isDisplayed(), "Corp code textbox was displayed");
			BrowserAssert.assertFalse(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_CLEARFILTER_BUTTON_XPATH).isDisplayed(), "Clear filter button was displayed");
			BrowserAssert.assertFalse(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_BUTTON_XPATH).isDisplayed(), "Search button was displayed");
		}
	}
	
	/**
	 * This method verifies that whether given tab is present on  status tracker settings page
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void deleteExistingParentHavingChildrenAndVerify() throws Exception {
		clickDeleteRecord();
	    String cantDeleteParent = BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_SAVE_MESSAGE_XPATH).getText().trim();
	    System.out.println("Popup message : " + cantDeleteParent);
	    BrowserAssert.assertEquals(cantDeleteParent, CommonPage.getTestData("CannotDeleteParentWithChildrenError"), "Delete parent with children assertion failed");
	    BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_SAVE_MESSAGE_CLOSE_BUTTON_XPATH);
	    clickCancelRecord();
	}

	/**
	 * This method verifies that whether given tab is present on  status tracker settings page
	 * @lastModifiedBy ADhawale
	 * @param tabName
	 * @throws Exception
	 */
	public static void verifyStatusTrackerSettingsTabPresent(String tabName) throws Exception {
		BrowserAssert.assertTrue(WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_TABS_LABELS_XPATH.name(), 
		String.format(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_TABS_LABELS_XPATH.toString(), tabName))).isDisplayed(), tabName+" was not present on page");
	}

	/**
	 * This method verifies that user has been successfully navigated to respective status tracker tab
	 * @lastModifiedBy ADhawale
	 * @param tabName
	 * @throws Exception
	 */
	public static void verifyUserNavigatedToStatusTrackerTab(String tabName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_LAST_NAVIGATED_TAB_XPATH.name(), 
				String.format(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_LAST_NAVIGATED_TAB_XPATH.toString(), tabName))));
	}

	/**
	 * This method adds a new parent
	 * @lastModifiedBy ADhawale
	 * @param parentName
	 * @throws Exception
	 */
	public static void addNewParent(String parentName) throws Exception {
		clickAddNewButton();
		waitForAddNewRecordPage();
		selectCorpCode();
		CommonPage.loadXMLParameterToTestData("ParentName", parentName);
		CommonPage.enterTextToInputField(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_PARENT_NAME_TEXTBOX_XPATH, CommonPage.getTestData("ParentName"));
		clickSaveNewRecord();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}

	/**
	 * This method verify existing parent error message
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void verifyExistingParentNameError() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_EXISTING_PARENT_ERROR_XPATH);
		String existingParentError=BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_MANAGE_PARENT_EXISTING_PARENT_ERROR_XPATH).getText().trim();
		BrowserAssert.assertEquals(existingParentError, CommonPage.getTestData("ExistingParentError"), "Parent error text mismatched or doesn't exists on page");
	}

	/**
	 * This method creates a new parent and verify existing parent error message if there is no parent exists
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void createNewParentAndVerifyExistingParentError() throws Exception {
		addNewParent(CommonPage.getTestData("ParentName"));
		verifyExistingParentNameError();
		clickCancelRecord();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}

	/**
	 * This method deletes a parent and verify that it doesn't exists anymore
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void deleteExistingParentAndVerify() throws Exception {
		waitForAddNewRecordPage();
		clickDeleteRecord();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBMTStatusTrackerPage.searchRecords("Parent Name", CommonPage.getTestData("ParentName"));
		verifySearchedRecords(false);
	}

	/**
	 * This method verify a confirmation message
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void verifyConfirmationMessage(String message) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_SAVE_MESSAGE_XPATH);
		String addParentMessage=BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_SAVE_MESSAGE_XPATH).getText().trim();
		BrowserAssert.assertEquals(addParentMessage, message, "confirmation message text mismatched or doesn't exists on page");
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_SAVE_MESSAGE_CLOSE_BUTTON_XPATH);
	}

	/**
	 * This method waits for the Status Tracker page to load.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void waitForStatusTrackerSettingPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_TABS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_TABS_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SEARCH_FILTERS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SEARCH_FILTERS_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_FILTER_TOGGLE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_FILTER_TOGGLE_BUTTON_XPATH);
	}
	
	/**
	 * This method Clicks on Section/Tab on Status Tracker Setting pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickOnStatusTrackerSectionTabs(String tabName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_TABS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_TABS_XPATH);
		WebElement element = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_TABS_LABELS_XPATH.name(), 
				String.format(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTINGS_TABS_LABELS_XPATH.toString(), tabName)));
		element.click();
	}

	/**
	 * This method Verifies the elements for the Status Tracker page - Reason Codes Tab.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifyStatusTrackerPageElements() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SETTING_TITLE_XPATH), "Status Tracker Settings", "Status Tracker Settings Title is mismatched");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_FILTER_TOGGLE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_FILTER_TOGGLE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_FILTER_TOGGLE_BUTTON_XPATH);
		List<String> filterList = Arrays.asList(CommonPage.getTestData("SearchFilterList").split("\\|"));
		List<WebElement> filterLabelList = BrowserAccess.getElements(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SEARCH_FILTERS_LABELS_XPATH);
		for(int i=0; i<filterLabelList.size(); i++) {
			CommonPage.assertLabelHighlight(filterLabelList.get(i), filterList.get(i), "Expected Search Filter Label : " + filterLabelList.get(i) + "Found : " + filterList.get(i).trim());
		}
		List<String> columnListElement = Arrays.asList(CommonPage.getTestData("ColumnList").split("\\|"));
		List<WebElement> actualColumnList = BrowserAccess.getElements(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_COLUMN_LABEL_XPATH);
		for(int i=0; i<actualColumnList.size(); i++) {
			assertEquals(actualColumnList.get(i).getAttribute("innerText").trim(), columnListElement.get(i).trim(), "Expected Column Name : " + actualColumnList.get(i).getAttribute("innerText").trim() + "Found : " + columnListElement.get(i).trim());
		}
		verifyPaginationElements();
	}

	/**
	 * *
	 * This method Verifies all Buttons on Status Tracker Setting pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifyStatusTrackerPageButtonElements() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_FILTER_TOGGLE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_FILTER_TOGGLE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_FILTER_TOGGLE_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BTM_STATUS_TRACKER_ADDNEW_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BTM_STATUS_TRACKER_ADDNEW_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BTM_STATUS_TRACKER_ADDNEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_BUTTON_XPATH);
	}
	
	/**
	 * This method Pagination Related Elements on Status Tracker Setting pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifyPaginationElements() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_RECORDS_INFO_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_RECORDS_INFO_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_NAVIGATION_BUTTONS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_NAVIGATION_BUTTONS_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_PAGE_LIST_DROPDOWN_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_PAGE_LIST_DROPDOWN_XPATH);
	}
	
	/**
	 * This method Clicks on Search Option Button on Status Tracker Setting pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickSearchButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_BUTTON_XPATH);
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verify the results in table present on Status Tracker Setting pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifySearchedRecords(boolean isAvailable) throws Exception {
		List<WebElement> elementslist = WebDriverAccess.getElements(BrowserAccess.getLocator(
			OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_RECORDS_XPATH.name(), String.format(
			OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_RECORDS_XPATH.toString(), CommonPage.getTestData("SearchText"))));
		if(isAvailable) {
			for(WebElement element : elementslist) {
				assertEquals(CommonPage.getTestData("SearchText"), element.getText().trim(), "Searched Result is not matching with Searched Filter : " + CommonPage.getTestData("SearchText"));
			}
		} else {
			assertTrue(elementslist.isEmpty(), "Records are Available for Searched Text :" + CommonPage.getTestData("SearchText"));
		}
	}

	/**
	 * This method Search the record and verify the results in table present on Status Tracker page.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void searchRecords(String searchFilterName, String searchText) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SEARCH_FILTERS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SEARCH_FILTERS_XPATH);
		System.out.println(searchFilterName + ": " + searchText);
		CommonPage.loadXMLParameterToTestData("SearchText", searchText);
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_FILTERS_XPATH.name(), String.format(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_FILTERS_XPATH.toString(), searchFilterName)), searchText);
		clickSearchButton();
		WebDriverAction.clear(BrowserAccess.getLocator(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_FILTERS_XPATH.name(), String.format(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_SEARCH_FILTERS_XPATH.toString(), searchFilterName)));
	}
	
	/**
	 * This method Select First Row of Reason Codes from table.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void selectFirstRowFromTable() throws Exception {
		WebElement element = WebDriverAccess.getElement(BrowserAccess.getLocator(
				OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_RECORDS_XPATH.name(), String.format(
				OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_RECORDS_XPATH.toString(), CommonPage.getTestData("SearchText"))));
		element.click();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method Verify the selected Reason Code with Created One.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifySelectedReasonCodesFromTable() throws Exception {
		waitForAddNewRecordPage();
		Select corpCodeOption = new Select(BrowserAccess.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_CORP_CODE_XPATH));
		assertEquals(corpCodeOption.getFirstSelectedOption().getText(), CommonPage.getTestData("CorpCode"), "The Corp Code is not matching");
		Select reasonCodeCatagory = new Select(BrowserAccess.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_REASON_CODE_CATEGORY_XPATH));
		assertEquals(reasonCodeCatagory.getFirstSelectedOption().getText(), CommonPage.getTestData("ReasonCodeCategory"), "The Reason Code Category is not matching");
		WebElement actualInternalCode = BrowserAccess.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_INTERNAL_CODE_XPATH);
		assertEquals(actualInternalCode.getAttribute("value").trim(), CommonPage.getTestData("InternalCode"), "The Internal Code is not matching");
		WebElement actualExternalCode = BrowserAccess.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_EXTERNAL_CODE_XPATH);
		assertEquals(actualExternalCode.getAttribute("value").trim(), CommonPage.getTestData("ExternalCode"), "The External Code is not matching");
		WebElement actualExternalCodeDesc = BrowserAccess.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_EXTERNAL_CODE_DESC_XPATH);
		assertEquals(actualExternalCodeDesc.getAttribute("value").trim(), CommonPage.getTestData("ExternalCodeDesc"), "The External Code Description is not matching");
		String lastUpdatedBy = BrowserAccess.getElementText(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_LAST_UPDATED_BY_XPATH).trim();
		assertEquals(lastUpdatedBy, CommonPage.getTestData("Username"), "The Last Updated by Field is not matching");
		clickCancelRecord();
	}
	
	/**
	 * This method Clicks on Clear Filter Button on Status Tracker Setting pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickClearFilterButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_CLEARFILTER_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_CLEARFILTER_BUTTON_XPATH);
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_CLEARFILTER_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method clear filters and verify the filters are cleared on Status Tracker page.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifyClearFilterFunctionality() throws Exception {
		clickClearFilterButton();
		List<WebElement> elementslist = BrowserAccess.getElements(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SEARCH_FILTERS_TEXTBOX_XPATH);
		for(WebElement element : elementslist) {
			assertTrue(element.getText().isEmpty(), "Clear Filter Fucntionality is not working for Search Filter :" + element.getText());
		}
	}
	
	/**
	 * This method waits for Add New Record page to load.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void waitForAddNewRecordPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_EDIT_PAGE_TITLE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_EDIT_PAGE_TITLE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_PAGE_FORM_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_PAGE_FORM_XPATH);
	}
	
	/**
	 * This method Clicks on Add New Button on Status Tracker Setting pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickAddNewButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BTM_STATUS_TRACKER_ADDNEW_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BTM_STATUS_TRACKER_ADDNEW_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BTM_STATUS_TRACKER_ADDNEW_BUTTON_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BTM_STATUS_TRACKER_ADDNEW_BUTTON_XPATH);
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BTM_STATUS_TRACKER_ADDNEW_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method Select Corp Code on Add New Record Page.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void selectCorpCode() throws Exception {
		BrowserVerify.verifyElementDisabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_REASON_CODE_CATEGORY_XPATH);
		BrowserAction.selectDropdownOptionByText(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_CORP_CODE_XPATH, CommonPage.getTestData("CorpCode"));
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_REASON_CODE_CATEGORY_XPATH);
	}
	
	/**
	 * This method Select Reason Code Category on Add New Record Page.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void selectReasonCodeCategory() throws Exception {
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_REASON_CODE_CATEGORY_XPATH);
		BrowserAction.selectDropdownOptionByText(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_REASON_CODE_CATEGORY_XPATH, CommonPage.getTestData("ReasonCodeCategory"));
	}
	
	/**
	 * This method Adds Internal and External Code on Add New Record Page.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void addInternalAndExternalCode() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd");
		String dateFormat = "-" + LocalDate.now().format(formatter);
		String internalCode = "AT_INT-"+ CommonPage.randomAlphaNumericString() + dateFormat;
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_INTERNAL_CODE_XPATH);
		BrowserAction.clickandClear(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_INTERNAL_CODE_XPATH);
		BrowserAction.enterFieldValue(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_INTERNAL_CODE_XPATH, internalCode);
		CommonPage.loadXMLParameterToTestData("InternalCode", internalCode);
		System.out.println("Internal Code : " + internalCode);
		String externalCode = "AT_EXT-"+ CommonPage.randomAlphaNumericString() + dateFormat;
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_EXTERNAL_CODE_XPATH);
		BrowserAction.clickandClear(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_EXTERNAL_CODE_XPATH);
		BrowserAction.enterFieldValue(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_EXTERNAL_CODE_XPATH, externalCode);
		CommonPage.loadXMLParameterToTestData("ExternalCode", externalCode);
		System.out.println("External Code : " + externalCode);
		String externalCodeDesc = "AT_EXTDESC-" + CommonPage.randomAlphaNumericString() + dateFormat;
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_EXTERNAL_CODE_DESC_XPATH);
		BrowserAction.clickandClear(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_EXTERNAL_CODE_DESC_XPATH);
		BrowserAction.enterFieldValue(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_ADDNEW_EXTERNAL_CODE_DESC_XPATH, externalCodeDesc);
		CommonPage.loadXMLParameterToTestData("ExternalCodeDesc", externalCodeDesc);
		System.out.println("External Code Description : " + externalCodeDesc);
	}
	
	/**
	 * This method Clicks on Save New Button on Add New record pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickSaveNewRecord() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SAVE_NEW_RECORD_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SAVE_NEW_RECORD_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SAVE_NEW_RECORD_XPATH);
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_SAVE_NEW_RECORD_XPATH);
	}
	
	/**
	 * This method Clicks on Export csv Button on Status Tracker Setting pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickExportButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_EXPORT_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_EXPORT_BUTTON_XPATH);
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_EXPORT_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifies the export functionality.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void verifyExportCSV(String statusTrackerSection, String className) throws Exception {
		OrderingBMTStatusTrackerPage.clickExportButton();
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"), className);
		verifyCSVData(className, statusTrackerSection);
	}
	
	/**
	 * This method verifies the CSV Column Heading Values with Grid Column Heading Values.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void verifyCSVData(String fileNameStartsWith, String mloSection) throws Exception {
		List<String> csvRows = ExcelUtil.getCSVRows(System.getProperty("user.dir") + "\\target\\" + fileNameStartsWith + ".csv");
		System.out.println("CSV Rows List : " + csvRows);
		List<String> actualColumnList = BrowserAccess.getElementsText(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_COLUMN_LABEL_XPATH);
		if (csvRows.size() == 0) {
			System.out.println("Table does not contains any row.");
		} else {
			String csvColumnHeadingRow = csvRows.get(0);
			String[] csvColumnHeadingSplittedRowValues;
			csvColumnHeadingSplittedRowValues = csvColumnHeadingRow.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			List<String> csvColumnHeadingList = Arrays.asList(csvColumnHeadingSplittedRowValues);
			System.out.println("CSV Column Heading Values :" + csvColumnHeadingList);
			System.out.println("Grid Column Heading Values :" + actualColumnList);
			csvColumnHeadingList.containsAll(actualColumnList);
		}
	}
  
	/**
	 * This method Clicks on Delete Button on Add New record pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickDeleteRecord() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_DELETE_RECORD_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_DELETE_RECORD_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_DELETE_RECORD_XPATH);
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_DELETE_RECORD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_DELETE_DIALOGBOX_XPATH);
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_DELETE_DIALOGBOX_YES_BUTTON_XPATH);
		if(CommonPage.getTestData("DeleteParentMessage")!=null) {
			verifyConfirmationMessage(CommonPage.getTestData("DeleteParentMessage"));
		}
	}

	/**
	 * This method Clicks on Cancel Button on Add New record pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickCancelRecord() throws Exception {
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CANCEL_RECORD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CANCEL_DIALOGBOX_XPATH);
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CANCEL_DIALOGBOX_YES_BUTTON_XPATH);
	}
	
	/**
	 * This method Adds New Reason Codes.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void addNewReasonCodes() throws Exception {
		selectCorpCode();
		selectReasonCodeCategory();
		addInternalAndExternalCode();
		clickSaveNewRecord();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method Update Reason Codes.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void updateReasonCodes() throws Exception {
		addInternalAndExternalCode();
		clickSaveNewRecord();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method Deletes Reason Codes.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void deleteReasonCodesAndVerify() throws Exception {
		clickDeleteRecord();
		searchRecords("Internal Code", CommonPage.getTestData("InternalCode"));
		List<WebElement> elementslist = WebDriverAccess.getElements(BrowserAccess.getLocator(
				OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_RECORDS_XPATH.name(), String.format(
				OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_TABLE_RECORDS_XPATH.toString(), CommonPage.getTestData("InternalCode"))));
		assertTrue(elementslist.isEmpty(), "Records is not Dleted Successfully : " + CommonPage.getTestData("InternalCode")); 
	}
	
	/**
	 * This method waits for Queue parameter Page to Load.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void waitForQueueParameterPageLoad() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_TITLE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_TITLE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_SUBTITLE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_SUBTITLE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_ADD_CLIENT_EXCEPTION_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_ADD_CLIENT_EXCEPTION_XPATH);
	}
	
	/**
	 * This method Verifies Elements for Query parameter Page to Load.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifyQueueParameterPageElements() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_TITLE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_TITLE_XPATH);
		CommonPage.assertLabelHighlight(BrowserAccess.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_TITLE_XPATH), "Queue Parameters & Client Exceptions", "Queue Parameter Page Title Is not Matching");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_SUBTITLE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_SUBTITLE_XPATH);
		CommonPage.assertLabelHighlight(BrowserAccess.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_SUBTITLE_XPATH), "Queue Parameters", "Queue Parameter Page Subtitle Is not Matching");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_CLIENT_EXCEPTION_TITLE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_CLIENT_EXCEPTION_TITLE_XPATH);
		CommonPage.assertLabelHighlight(BrowserAccess.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_CLIENT_EXCEPTION_TITLE_XPATH), "Client Exceptions", "Queue Parameter page - Client Exception Section Title Is not Matching");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_SAVE_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_CANCEL_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_CANCEL_BUTTON_XPATH);
	}
	
	/**
	 * This method Verifies Elements for Query parameter Page to Load.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void selectQueueParametersCheckbox() throws Exception {
		List<WebElement> checkBoxElements = BrowserAccess.getElements(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_CHECKBOX_XPATH);
		for (WebElement element : checkBoxElements) {
			element.click();
		}
	}
	
	/**
	 * This method Adds new Client Exceptions for all queues present on Queue parameter Page to Load.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void addClientExceptionForAllQueues() throws Exception {
		System.out.println("Adding New Client Exception");
		String[] queueNames = CommonPage.getTestData("QueueNames").split("\\|");
		deleteAllExistingClientException();
		CommonPage.loadXMLParameterToTestData("Iterator", "1");
		for(String queue : queueNames) {
			BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_ADD_CLIENT_EXCEPTION_XPATH);
			enterValuesForClientExceptionFields(queue);
		}
		clickOnSaveQueueParams();
	}
	
	/**
	 * This method Adds new Client Exceptions Query parameter Page to Load Delete existing client exceptions first.
	 * We need iterator with dynamic Xpath for Client Exceptions due to multiple Client Exceptions Options
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void enterValuesForClientExceptionFields(String queueName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_NUMBER_OF_CLIENT_EXCEPTIONS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_NUMBER_OF_CLIENT_EXCEPTIONS_XPATH);
		List<WebElement> clientExcsPresent = BrowserAccess.getElements(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_NUMBER_OF_CLIENT_EXCEPTIONS_XPATH);
		System.out.println("Selecting Queue : " + queueName);
		WebDriverAction.selectDropDownOptionByText(BrowserAccess.getLocator(
				OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_QUEUE_DROPDOWN_XPATH.name(), 
				String.format(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_QUEUE_DROPDOWN_XPATH.toString(), 
				CommonPage.getTestData("Iterator"))), queueName);
		WebElement clientExceptionText = WebDriverAccess.getElement(BrowserAccess.getLocator(
				OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_TEXTAREA_XPATH.name(), 
				String.format(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_TEXTAREA_XPATH.toString(), 
				CommonPage.getTestData("Iterator"))));
		String clientException = "AT_CE-"+ CommonPage.randomAlphaNumericString();
		System.out.println("Entering Client Exception no " + clientExcsPresent.size() + " : -> " + clientException);
		WebDriverAction.clickAndClear(clientExceptionText);
		WebDriverAction.enterFieldValue(clientExceptionText, clientException);
		CommonPage.loadXMLParameterToTestData(queueName, clientException);
	}
	
	/**
	 * This method Clicks on Save Queue Parameter Button on Add new Params and Client Exceptions page
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickOnSaveQueueParams() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_SAVE_BUTTON_XPATH);
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_SAVE_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifies the Queue Params and Client Exception added for a Specific Client
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifyQueueParameterAndClientException() throws Exception {
		List<WebElement> clientExcsPresent = BrowserAccess.getElements(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_NUMBER_OF_CLIENT_EXCEPTIONS_XPATH);
		System.out.println("Number of Queue Parameters and Client Exceptions Present : " + clientExcsPresent.size());
		String[] queueNames = CommonPage.getTestData("QueueNames").split("\\|");
		for(int i=1; i<=clientExcsPresent.size(); i ++) {
			WebElement selectedOption =	WebDriverAccess.getElement(BrowserAccess.getLocator(
					OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_QUEUE_DROPDOWN_XPATH.name(), 
					String.format(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_QUEUE_DROPDOWN_XPATH.toString(), i)));
			Select option = new Select(selectedOption);
			assertEquals(option.getFirstSelectedOption().getText().trim(), queueNames[i-1], "Queue Option is not matching");
			WebElement clientExceptionText = WebDriverAccess.getElement(BrowserAccess.getLocator(
					OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_TEXTAREA_XPATH.name(), 
					String.format(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_TEXTAREA_XPATH.toString(), i)));
			assertEquals(clientExceptionText.getAttribute("value").trim(), CommonPage.getTestData(queueNames[i-1]), "Client Exception Comment is not matching");
		}
		clickOnCancelQueueParams();
	}
	
	/**
	 * This method Clicks on Cancel Queue Parameter Button on Add new Params and Client Exceptions page
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickOnCancelQueueParams() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_CANCEL_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_CANCEL_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_CANCEL_BUTTON_XPATH);
		BrowserAction.click(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_CANCEL_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method Updating Client Exceptions Query parameter Page..
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void updateAllClientExceptions() throws Exception {
		System.out.println("Updating Client Exception");
		selectQueueParametersCheckbox();
		List<String> queueNames = Arrays.asList(CommonPage.getTestData("QueueNames").split("\\|"));
		Collections.reverse(queueNames);
		String newQueuenames ="";
		for(int i=1; i<=queueNames.size(); i++) {
			CommonPage.loadXMLParameterToTestData("Iterator", i+"");
			enterValuesForClientExceptionFields(queueNames.get(i-1));
			newQueuenames = newQueuenames + queueNames.get(i-1) + "|";
		}
		CommonPage.loadXMLParameterToTestData("QueueNames", newQueuenames);
		clickOnSaveQueueParams();
	}
	
	/**
	 * This method Delete Client Exceptions Query parameter Page to Load Delete existing client exceptions first.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void deleteAllExistingClientException() throws Exception {
		System.out.println("Deleting Client Exception");
		List<WebElement> deleteExistingException = BrowserAccess.getElements(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_QUEUE_PARAM_PAGE_DELETE_CLIENT_EXCEPTION_XPATH);
		if(!deleteExistingException.isEmpty()) {
			for(WebElement deleteException : deleteExistingException) {
				deleteException.click();
			}
		}
	}
	
	/**
	 * This method Verify Delete Client Exceptions Query parameter Functionality Page to Load Delete existing client exceptions first.
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifyDeleteClientException() throws Exception {
		deleteAllExistingClientException();
		clickOnSaveQueueParams();
		waitForStatusTrackerSettingPage();
		searchRecords("Client Number", CommonPage.getTestData("ClientNumber"));
		selectFirstRowFromTable();
		waitForQueueParameterPageLoad();
		List<WebElement> clientExcsPresent = BrowserAccess.getElements(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_NUMBER_OF_CLIENT_EXCEPTIONS_XPATH);
		assertTrue(clientExcsPresent.isEmpty(), "Client Exceptions Are not deleted : No of Client Exceptions Atill Present - " + clientExcsPresent.size());
		clickOnSaveQueueParams();
	}
	
	/** 
	 * This method clicks on queue header headings
	 * @lastModifiedBy UshaNaidu
	 * @param columnHeading
	 * @param queue
	 * @throws Exception
	 */
	public static void clickColumnTableHeading(String columnHeading) throws Exception {
		try{
			getColumnTableHeading(columnHeading).click();
			OrderingCommonPage.checkGlobalSpinnerPopUp();
		} catch(ElementNotInteractableException e) {
			WebElement scrollArea = BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DATA_TABLE_SCROLL_BODY_CSS);
			((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollLeft = arguments[0].offsetWidth", scrollArea);
			getColumnTableHeading(columnHeading).click();
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			scrollArea = BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DATA_TABLE_SCROLL_BODY_CSS);
			((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollLeft = arguments[0].offsetWidth", scrollArea);
		}
	}
	
	/** 
	 * This method gets Column table header headings
	 * @param columnHeading
	 * @return
	 * @throws Exception
	 * @lastModifiedBy UshaNaidu
	 */
	public static WebElement getColumnTableHeading(String columnHeading) throws Exception {
		WebElement columnTableHeadingWebElement=null;
		switch(columnHeading) {
				case "Client Number": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_CLIENT_NUMBER_COLUMN_TABLE_HEADING_XPATH);
					break;
				case "Client Name": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_CLIENT_NAME_COLUMN_TABLE_HEADING_XPATH);
					break;
				case "Client Exception": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_CLIENT_EXCEPTION_COLUMN_TABLE_HEADING_XPATH);
					break;
				case "Last Updated By": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_LAST_UPDATED_BY_COLUMN_TABLE_HEADING_XPATH);
					break;
				case "Last Updated Date": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBMTStatusTrackerPageEnum.ORDERING_BMT_STATUS_TRACKER_CLIENT_EXCEPTION_LAST_UPDATED_DATE_COLUMN_TABLE_HEADING_XPATH);
					break;
				default: throw new InvalidSwitchCaseException(columnHeading + " is a invalid on order column");		
		}
		return columnTableHeadingWebElement;
	}			
	
}
