package com.element.fleet.ordering.page;

import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOOnOrderQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingBOQuotePageEnum;
import com.element.fleet.ordering.enums.OrderingBOUpfitSpecPageEnum;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;
import com.ge.capital.rainbow.webdriver.WebDriverWaits;

public class OrderingBOUpfitSpecPage {
	
	public static void waitForUpfitSpecDetailsPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_HEADING_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_HEADING_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_HEADING_CSS);	
	}
	
	public static void upfitSpecDetailsPageHeadingLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_HEADING_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_HEADING_CSS), "Upfit Spec Maintenance", "Upfit Spec Maintenance label did not match with the expected string");
	}
	
	public static void waitForEditQuotesPageToLoad() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SUPPLIERS_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_LINE_ITEMS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_OPTIONAL_LINE_ITEMS_ID);
	}
	
	public static void clickOnTheFirstOptionOfListIfAvailable(WebDriver driver) throws Exception {
		List<WebElement> tableRows = BrowserAction.getElements(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH);
		if((tableRows.size()==1)&&tableRows.get(0).getText().contains("No data available in table")) {
			System.out.println("No element available to click\n"+BrowserAction.getElementText(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH));
			OrderingBOQueuePage.gotoQueuesPage();
		} else {
			tableRows.get(0).click();
			OrderingCommonPage.checkAlertPopUp();
			OrderingBOUpfitSpecPage.waitForEditQuotesPageToLoad();
		}				
	}
	
	public static void exportUpfitSpec(String className) throws Exception {
		OrderingSummaryPage.clickExportButton("UpfitSpec", className);
	}
	
	public static void verifySaveFunctionality() throws Exception {
		OrderingBOUpfitSpecPage.verifySaveWithStandardFunctionality();
		OrderingBOUpfitSpecPage.verifySaveAsViewFunctionality();
		OrderingBOUpfitSpecPage.verifySaveWithSaveAsFunctionality();
	}
	
	public static void verifySaveWithStandardFunctionality() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVEVIEW_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVEVIEW_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVE_OPTION_ID);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVE_OPTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVE_OPTION_OK_CLASS);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVE_OPTION_OK_CLASS);
		OrderingBOUpfitSpecPage.verifyStandardViewErrorAlertPopUpDispalyed();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVE_OPTION_CANCEL_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVE_OPTION_CANCEL_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.invisibilityOfElementLocated(By.id(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_POP_UP_ID.getValue())));
		if(!"Standard View".equals(BrowserAction.getElementText(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SELECT_VIEWS_ID)))
			throw new OrderingErrorOccured("In drop down Standard View is not found when we save view");
	}
	
	/**
	 * This method verifies alert pop based on colour and text.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyStandardViewErrorAlertPopUpDispalyed() throws Exception {
		List<WebElement> errorElement = new WebDriverWait(WebDriverAccess.getDriver(), 3).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 1));
		String alertPopUpBackgroundColour = Color.fromString(errorElement.get(0).getCssValue("background-color")).asHex();
		String alertPopUpText = errorElement.get(0).getText();
		if(alertPopUpBackgroundColour.equals("#bf2e2e") && alertPopUpText.equals("Standard View cannot be saved")) {
			System.out.println("Expected error pop up: " + alertPopUpText);
		} else {
			System.out.println("Alert pop up background colour: " + alertPopUpBackgroundColour);
			System.out.println("Alert pop up text: " + alertPopUpText);
			throw new OrderingErrorOccured("Error pop up is not displayed");
		}
	}	

	public static void verifySaveAsViewFunctionality() throws Exception {
		String viewName = CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("ViewName", viewName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVEAS_OPTION_ID);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVEAS_OPTION_ID);
		System.out.println("View name: " + viewName);
		BrowserAction.enterFieldValue(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVEAS_NAME_ID, CommonPage.getTestData("ViewName"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVEAS_SAVE_BUTTON_CLASS);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVEAS_SAVE_BUTTON_CLASS);
		OrderingCommonPage.checkAlertPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.id(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_POP_UP_ID.getValue()), 0));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.id("btnDropdownViews"), 1));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SELECT_VIEWS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SELECT_VIEWS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SELECT_VIEWS_ID);
		if(!new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.textToBePresentInElement(BrowserAction.getElement(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SELECT_VIEWS_ID), CommonPage.getTestData("ViewName"))))
			throw new OrderingErrorOccured("Save as name not found on View drop down");
	}
	
	public static void verifySaveWithSaveAsFunctionality() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVEVIEW_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVEVIEW_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVE_OPTION_ID);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVE_OPTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVE_OPTION_OK_CLASS);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_SAVE_OPTION_OK_CLASS);
		OrderingCommonPage.checkAlertPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.id(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_POP_UP_ID.getValue()), 0));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.id("btnDropdownViews"), 1));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SELECT_VIEWS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SELECT_VIEWS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SELECT_VIEWS_ID);
		if(!CommonPage.getTestData("ViewName").equals(BrowserAction.getElementText(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SELECT_VIEWS_ID)))
			throw new OrderingErrorOccured("In drop down Save as name is not found when we save saved View");
	}
	
	public static void verifyAddRemoveFunctionality() throws Exception {
		OrderingBOUpfitSpecPage.addAndRemoveAndVerifySelectAllColumns();
		OrderingBOUpfitSpecPage.addAndRemoveAndVerifyDeselectAllColumns();
		OrderingBOUpfitSpecPage.addAndRemoveAndVerifySelectFourColumns();
		OrderingBOUpfitSpecPage.addAndRemoveAndSelectAllColumns();
	}
	
	public static void addAndRemoveAndVerifySelectAllColumns() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_XPATH);
		ArrayList<String> addRemoveColumnList = addRemoveColumnList();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
		ArrayList<String> projectHeaderColumnList  = projectHeaderColumnList();
		for(int i = 0 ; i < addRemoveColumnList.size() ; i++ ) {
			if(!addRemoveColumnList.get(i).equals(projectHeaderColumnList.get(i))) {
				throw new OrderingErrorOccured("Add/Remove functionality is not working properly when we select All Columns");
			}
		}
	}

	public static void addAndRemoveAndVerifyDeselectAllColumns() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
		ArrayList<String> projectHeaderColumnList = projectHeaderColumnList();
		for(int i = 0 ; i < projectHeaderColumnList.size() ; i++) {
			if(projectHeaderColumnList.isEmpty())
				throw new OrderingErrorOccured("Add/Remove functionality is not working properly when we deselect All Columns");
		}
	}

	public static void addAndRemoveAndVerifySelectFourColumns() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_COLUMN_LIST_XPATH);
		List<WebElement> columnList = BrowserAction.getElements(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_COLUMN_LIST_XPATH);
		int count = 0;
		for(WebElement str : columnList) {
			count++;
			str.click();
			if(count == 4)
				break;
		}
		ArrayList<String> addRemoveColumnList= addRemoveColumnList();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
		ArrayList<String> projectHeaderColumnList  = projectHeaderColumnList();
		for(int i = 0 ; i < projectHeaderColumnList.size()-1 ; i++ ) {
			if(!addRemoveColumnList.get(i).trim().equals(projectHeaderColumnList.get(i).trim())) {
				throw new OrderingErrorOccured("Add/Remove functionality is not working properly when we select first four columns");
			}
		}
	}
	
	public static ArrayList<String> addRemoveColumnList() throws Exception {
		ArrayList<String> columnListStr = new ArrayList<>(); 
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_COLUMN_LIST_XPATH);
		List<WebElement> columnList = BrowserAction.getElements(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_COLUMN_LIST_XPATH);
		for(WebElement str : columnList) {
			if(str.getText() != null)
				columnListStr.add(str.getText());
		}
		return columnListStr;
	}

	
	public static ArrayList<String> projectHeaderColumnList() throws Exception {
		ArrayList<String> columnListStr = new ArrayList<>(); 
		int count = 0;
		List<WebElement> columnList = BrowserAccess.getElements(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_HEADER_COLUMN_LIST_XPATH);
		for(WebElement str : columnList) {
			if(count == 14)
				BrowserAction.hoverOverElement(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_COLUMN_LAST_COLUMN_XPATH);
			if(!str.getText().equals("")) {
				columnListStr.add(str.getText().trim().replace("\n", " "));
				count++;
			}
		}
		for(int i=0 ;i < columnListStr.size(); i++) {
			if(columnListStr.get(i).trim().equals("")) 
				columnListStr.remove(i);
		}
		return columnListStr;
	}

	public static void addAndRemoveAndSelectAllColumns() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
	}
	
	public static void verifyQueueTitle(String queuePageName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_TITLE_NAME_XPATH);
		String actualTitl = BrowserAction.getElement(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_TITLE_NAME_XPATH).getText();
		assertEquals(actualTitl, queuePageName, "Queue page title is not matching");
	}
	
	public static void verifyFields() throws Exception {
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_CONTROLSLINK_XPATH), "Controls link is not available on page");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVEVIEW_XPATH), "Save link is not available on page");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_EXPORTCSV_XPATH), "Export link is not available on page");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADD_REMOVE_XPATH), "Add/Remove link is not available on page");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_STANDARD_VIEW_DROPDOWN_ID), "Standard View field is not available on page");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_PAGINATION_NAME), "Pagination field is not available on page");
	}
	
	public static void verifyQueueTitleAndFields(String queuePageName) throws Exception {
		verifyQueueTitle(queuePageName);
		verifyFields();
	}
	
	public static void verifyPaginationFunctionality() throws Exception {			
		Select slt = new Select(BrowserAction.getElement(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_PAGINATION_NAME));
		if(!(slt.getFirstSelectedOption().getText().equals("100")))
			throw new OrderingErrorOccured("Unable to match dropdown 1st option");
		BrowserAction.selectDropdownOptionByText(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_PAGINATION_NAME, "500");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_PAGINATION_NAME);
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_PAGINATION_NAME);
		BrowserVerify.verifyElementEnabled(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_PAGINATION_NAME);
	}
	
	public static void clickOnControlLink() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_FILTER_BUTTON_CLASS);
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_FILTER_BUTTON_CLASS);
		BrowserVerify.verifyElementEnabled(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_FILTER_BUTTON_CLASS);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.className("noty_body"), 0));
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_FILTER_BUTTON_CLASS);
	}
	
	public static void clickOnApplyButton() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_NAME_APPLY_XPATH);
		WebElement applyElement = BrowserAccess.getElement(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_NAME_APPLY_XPATH);
		((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].click();", applyElement);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}

	public static void clickOnSearchByColumnNameAndVerifyColumnData(String searchBy, String searchText) throws Exception {
		clickOnControlLink();
		clickOnSearchByColumnName(searchBy, searchText);
		clickOnApplyButton();
		verifyColumnData(searchBy, searchText);
		checkAppliedControls();
	}
	
	public static void verifyUpfitSpecMaintenancePage(String searchBy, String searchText) throws Exception {
		clickOnControlLink();
		clickOnSearchByColumnName(searchBy, searchText);
		clickOnApplyButton();
		clickOnFirstSearch(searchBy, searchText);
		upfitSpecDetailsPageHeadingLabelValidation();
		editAndVerifyUpfitSpecMaintenancePage();
		verifyManageGroupsFunctionality();
		closeMaintenancePage();
		checkAppliedControls();
	}
	
	public static void editAndVerifyUpfitSpecMaintenancePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_QUOTE_NUMBER_ID);
		BrowserAction.clickandClear(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_QUOTE_NUMBER_ID);
		String quoteNum = CommonPage.randomAlphaNumericString();
		System.out.println("Quote number: " + quoteNum);
		BrowserAction.enterFieldValue(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_QUOTE_NUMBER_ID, quoteNum);		
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_QUOTE_DATE_XPATH);
		BrowserAction.clickandClear(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_QUOTE_DATE_XPATH);		
		System.out.println("Quote Date: " + CommonPage.getCurrentDataIn("MM/dd/yyyy"));
		BrowserAction.enterFieldValue(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_QUOTE_DATE_XPATH, CommonPage.getCurrentDataIn("MM/dd/yyyy"));		
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_SAVE_QUOTE_ID);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_SAVE_QUOTE_ID);
		OrderingBOUpfitSpecPage.verifyUpfitSaveSuccessfulPopUpDispalyed();
		String quoteNumber = BrowserAction.getElementAttributeValue(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_QUOTE_NUMBER_ID,"value");
		assertEquals(quoteNumber, quoteNum, "Edited quote number is not matching");
	}
	
	/**
	 * This method verifies upfit saved pop based on colour and text.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyUpfitSaveSuccessfulPopUpDispalyed() throws Exception {
		List<WebElement> popUpElement = new WebDriverWait(WebDriverAccess.getDriver(), 3).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 1));
		String alertPopUpBackgroundColour = Color.fromString(popUpElement.get(0).getCssValue("background-color")).asHex();
		String alertPopUpText = popUpElement.get(0).getText();
		if(alertPopUpBackgroundColour.equals("#82c341") && alertPopUpText.equals("Upfit fleet spec saved successfully")) {
			System.out.println("Success pop up: " + alertPopUpText);
		} else {
			System.out.println("Alert pop up background colour: " + alertPopUpBackgroundColour);
			System.out.println("Alert pop up text: " + alertPopUpText);
			throw new OrderingErrorOccured("Error pop up is not displayed");
		}
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	public static void closeMaintenancePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_CLOSE_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MAINTENANCE_PAGE_CLOSE_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	public static void clickOnSearchByColumnName(String searchBy, String searchText) throws Exception {
		String searchByColumnName = OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SEARCH_BY_XPATH.getValue();
		searchByColumnName = searchByColumnName.replace("#SearchBy$", searchBy);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(searchByColumnName));
		CommonPage.loadXMLParameterToTestData("ColumnDataName", WebDriverAction.getElementAttributeValue(By.xpath(searchByColumnName), "data-param-name"));
		WebDriverAction.click(By.xpath(searchByColumnName));
		String searchByInputText = OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SEARCH_BY_SEARCH_TEXT_XPATH.getValue();
		searchByInputText = searchByInputText.replace("#SearchBy$", searchBy);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(searchByInputText));
		System.out.println("Search by: " + searchText);
		WebDriverAction.enterFieldValue(By.xpath(searchByInputText), searchText);
	}

	public static void verifyColumnData(String columnName, String searchText) throws Exception {
		ArrayList<String> columnListStr = new ArrayList<>();
		String columnValueXpath = OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_UPFIT_NAME_LIST_XPATH.getValue();
		columnValueXpath = columnValueXpath.replace("#ColumnDataName$", CommonPage.getTestData("ColumnDataName"));
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(columnValueXpath));
		List<WebElement> columnDataList = WebDriverAccess.getElements(By.xpath(columnValueXpath));
		for(WebElement ele : columnDataList) {
			columnListStr.add(ele.getText());
		}
		for(String str : columnListStr) {
			if(!str.contains(searchText))
				throw new OrderingErrorOccured("column data is not matching with searched data"+searchText);
		}
	}
	
	public static void clickOnFirstSearch(String columnName, String searchText) throws Exception {
		String columnValueXpath = OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_UPFIT_NAME_LIST_XPATH.getValue();
		columnValueXpath = columnValueXpath.replace("#ColumnDataName$", CommonPage.getTestData("ColumnDataName"));
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(columnValueXpath));
		List<WebElement> columnDataList = WebDriverAccess.getElements(By.xpath(columnValueXpath));
		for(WebElement ele : columnDataList) {
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(ele)).click();
			break;
		}
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	public static void checkAppliedControls() throws Exception {
		if(BrowserAccess.getElement(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_APPLIED_CONTROLS_XPATH).isDisplayed() == true) {
			List<WebElement> controlsList = BrowserAccess.getElements(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_APPLIED_CONTROLS_XPATH);
			for(WebElement ele : controlsList) {
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(ele)).click();
			}
		}
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method verifies manage group functionality of upfit spec queue
	 * @throws Exception 
	 * @lastModifiedBy vikumar
	 */
	public static void verifyManageGroupsFunctionality() throws Exception {
		openManageGroups();
		if(!BrowserAccess.getElements(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_DATA_TABLES_EMPTY_XPATH).isEmpty()) {
			addGroupAndAssignLineItem();
		}
		else {
			unassignLineItemsAndDeleteGroups();
			openManageGroups();
			addGroupAndAssignLineItem();
		}	
	}
	/**
	 * This method adds new upfit spec group and assigns a line item to it
	 * @throws Exception 
	 * @lastModifiedBy vikumar
	 */
	public static void addGroupAndAssignLineItem() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_ADD_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_ADD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_TEXTBOX_XPATH);
		BrowserAction.clickandClear(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_TEXTBOX_XPATH);
		String groupName = CommonPage.randomAlphaNumericString();
		System.out.println("Group name: " + groupName);
		BrowserAction.enterFieldValue(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_TEXTBOX_XPATH, groupName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_RULE_DROPDOWN_XPATH);
		BrowserAction.selectDropdownOptionByText(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_RULE_DROPDOWN_XPATH, "One or Many");
		OrderingCommonPage.checkAlertPopUp();
		BrowserAction.selectDropdownOptionByText(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_GROUP_DROPDOWN_XPATH, groupName);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_SUBMIT_BTN_XPATH);
		OrderingCommonPage.checkAlertPopUp();
	}
	/**
	 * This method unassigns upfit spec group for line items and then delete the existing groups
	 * @throws Exception 
	 * @lastModifiedBy vikumar
	 */
	public static void unassignLineItemsAndDeleteGroups() throws Exception{
		List<WebElement> lineItemsDrpDwnEleList=BrowserAccess.getElements(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_GROUP_DROPDOWN_XPATH);
		for(WebElement lineItemDropDownEle:lineItemsDrpDwnEleList) {
			Select select = new Select(lineItemDropDownEle);
			select.selectByVisibleText("- No Group -");
		}
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_SUBMIT_BTN_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		openManageGroups();
		while(!BrowserAccess.getElements(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_DELETE_GROUP_XPATH).isEmpty()){
			BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_DELETE_GROUP_XPATH);
			OrderingCommonPage.checkAlertPopUp();
		}
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_SUBMIT_BTN_XPATH);
		OrderingCommonPage.checkAlertPopUp();	
	}
	/**
	 * This method opens manage group section
	 * @throws Exception 
	 * @lastModifiedBy vikumar
	 */
	public static void openManageGroups() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_BTN_XPATH);
		BrowserAction.click(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_BTN_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_MANAGE_GROUPS_ADD_XPATH);
	}
	
	/**
	 * This method verifies correct control labels are present and displayed
	 * @throws Exception 
	 * @lastModifiedBy vikumar
	 */
	public static void verifyControlLabelsArePresent() throws Exception {
		List<String> expectedControlFieldNames = Arrays.asList(CommonPage.getTestData("CustomColumn3").split("\\|"));
		ArrayList<String> actualControlFieldsNames = new ArrayList<>();
		List<WebElement> controlFieldElementList=BrowserAccess.getElements(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_SEARCH_LIST_XPATH);
		for(WebElement e: controlFieldElementList) {
			BrowserWait.waitUntilElementIsDisplayed(e);
			actualControlFieldsNames.add(e.getAttribute("innerText").replace("\n", "").replace("\u00a0","").replace("+", "").trim());	
		}
		expectedControlFieldNames.sort(Comparator.naturalOrder());
		System.out.println("Expected control fields: " + expectedControlFieldNames);
		actualControlFieldsNames.sort(Comparator.naturalOrder());
		System.out.println("Actual control fields: " + actualControlFieldsNames);
		Assert.assertTrue(expectedControlFieldNames.equals(actualControlFieldsNames), "Fields name mismatched");		
	}
}