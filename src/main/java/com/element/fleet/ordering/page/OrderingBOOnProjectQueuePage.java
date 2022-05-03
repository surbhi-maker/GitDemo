package com.element.fleet.ordering.page;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.element.fleet.ordering.commonutility.CommonPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.enums.OrderingBOOnOrderProjectPageEnum;
import com.element.fleet.ordering.enums.OrderingBOQueuesCommonXpathEnum;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingBOOnProjectQueuePage {
	
	public static void clickOnAddProjectFromProjectQueuePage() throws Exception	{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_PROJECT_LINK_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_PROJECT_LINK_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_PROJECT_LINK_XPATH);			
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_PROJECT_LINK_XPATH);
	}	
	
	public static void scrollDownToProjectQueue() throws Exception	{
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_PROJECTQUEUE_TITLE_XPATH));
	}

	public static void selectClientFromClientSearchBox() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SEARCH_CLIENT_TEXBOX_NAME);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SEARCH_CLIENT_TEXBOX_NAME);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SEARCH_CLIENT_TEXBOX_NAME);			
		BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SEARCH_CLIENT_TEXBOX_NAME);
		System.out.println("Client number: " + CommonPage.getTestData("ClientNumber"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SEARCH_CLIENT_TEXBOX_NAME, CommonPage.getTestData("ClientNumber"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_SEARCH_CLIENT_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_SEARCH_CLIENT_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_SEARCH_CLIENT_XPATH);
		new Actions(WebDriverAccess.getDriver()).moveToElement(BrowserAction.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_SEARCH_CLIENT_XPATH)).click().build().perform();
	}	
	
	/**
	 * This method Enters project name.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void enterProjectName() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_NAME_TEXBOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_NAME_TEXBOX_ID);			
		BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_NAME_TEXBOX_ID);
		String projectName = "Automation" + CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("ProjectName", projectName);
		System.out.println("Project name: " + CommonPage.getTestData("ProjectName"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_NAME_TEXBOX_ID, CommonPage.getTestData("ProjectName"));	
	}
	
	/**
	 * This method Enters project Description.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void enterProjectDescription() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_DESCRIPTION_TEXBOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_DESCRIPTION_TEXBOX_ID);
		BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_DESCRIPTION_TEXBOX_ID);
		String projectDescription = "Project description: " + CommonPage.randomAlphaNumericString();
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_DESCRIPTION_TEXBOX_ID, projectDescription);
	}
	
	public static void enterProjectNameInProjectNameTextBox(String project) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_NAME_TEXBOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_NAME_TEXBOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_NAME_TEXBOX_ID);			
		BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_NAME_TEXBOX_ID);
		System.out.println("Project Name: "+project);
		CommonPage.loadXMLParameterToTestData("ProjectName", project);
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_NAME_TEXBOX_ID, project);
	}		

	/**
	 * This method selects project due date.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void selectProjectDueDate() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DUE_DATE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DUE_DATE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DUE_DATE_TEXTBOX_XPATH);
		BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DUE_DATE_TEXTBOX_XPATH);
		System.out.println("Due Date: " + CommonPage.getCurrentDataIn("MM/dd/yyyy"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DUE_DATE_TEXTBOX_XPATH, CommonPage.getCurrentDataIn("MM/dd/yyyy"));
	}

	public static void clickOnAddProjectOkButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_PROJECT_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_PROJECT_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_PROJECT_BUTTON_ID);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_PROJECT_BUTTON_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}

	public static void selectFirstProject() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCHED_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCHED_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCHED_XPATH);
		List<WebElement> projectList = BrowserAction.getElements(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECT_NAME_LIST_XPATH);
		for(WebElement list : projectList) {
			if(CommonPage.getTestData("ProjectName").equals(list.getText())) {
				list.click();
			}
		}
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	public static void selectFirstProjectInQueue() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCHED_NEW_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCHED_NEW_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCHED_NEW_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCHED_NEW_XPATH)));
		BrowserAccess.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCHED_NEW_XPATH).click();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CREATE_QUOTE_BUTTON_XPATH);
	}
	
	public static void clickCreateQuote() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CREATE_QUOTE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CREATE_QUOTE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CREATE_QUOTE_BUTTON_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CREATE_QUOTE_BUTTON_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		JavascriptExecutor executor = (JavascriptExecutor)WebDriverAccess.getDriver();
		executor.executeScript("arguments[0].click();", BrowserAccess.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CREATE_QUOTE_SAVE_BUTTON_XPATH));
	}
	
	
	
	public static void waitForProjectDetailsPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_LABEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_LABEL_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_LABEL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_QUOTE_QUEUE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_QUOTE_QUEUE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_QUOTE_QUEUE_XPATH);
	}
	
	public static void projectDetailsPageHeadingLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_LABEL_XPATH), "Project Details", "Project Details label did not match with the expected string");
	}

	public static void clickOnQuoteQueueFromProjectDetailsPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_QUOTE_QUEUE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_QUOTE_QUEUE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_QUOTE_QUEUE_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_QUOTE_QUEUE_XPATH);
	}

	public static void clickOnAddQuoteQueueLink() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CLICK_ADD_QUOTE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CLICK_ADD_QUOTE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CLICK_ADD_QUOTE_BUTTON_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CLICK_ADD_QUOTE_BUTTON_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}

	/**
	 * This method Enters SupplierQuote Number
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void enterSupplierQuoteNumber() throws Exception {
		if(CommonPage.getTestData("SupplierQuoteNumber")== null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ENTER_QUOTE_NUMBER_TEXTBOX_ID);
			BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ENTER_QUOTE_NUMBER_TEXTBOX_ID);
			BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ENTER_QUOTE_NUMBER_TEXTBOX_ID);
			BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ENTER_QUOTE_NUMBER_TEXTBOX_ID);
			String supplierQuoteNumber = CommonPage.generateRandomNumber();
			System.out.println("Supplier quote number: " + supplierQuoteNumber);
			BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ENTER_QUOTE_NUMBER_TEXTBOX_ID, supplierQuoteNumber);
		} else {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ENTER_QUOTE_NUMBER_TEXTBOX_ID);
			BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ENTER_QUOTE_NUMBER_TEXTBOX_ID);
			BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ENTER_QUOTE_NUMBER_TEXTBOX_ID);
			BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ENTER_QUOTE_NUMBER_TEXTBOX_ID);
			System.out.println("Supplier quote number: " + CommonPage.getTestData("SupplierQuoteNumber"));
			BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ENTER_QUOTE_NUMBER_TEXTBOX_ID, CommonPage.getTestData("SupplierQuoteNumber"));
		}
	}
	
	/**
	 * This method Enters Estimated Lead Time in Weeks
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void enterEstimatedLeadTime() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_LEADTIME_TEXT_BOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_LEADTIME_TEXT_BOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_LEADTIME_TEXT_BOX_ID);
		String estLeadTimeInWeeks = CommonPage.generateRandomNumber().substring(0, 1);
		System.out.println("Estimated Lead Time in Weeks: " + estLeadTimeInWeeks);
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_LEADTIME_TEXT_BOX_ID, estLeadTimeInWeeks);
	}
	
	/**
	 * This method Enters SupplierQuote date
	 * @throws Exception
	 */
	public static void enterSupplierQuoteDate() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_QUOTE_DATE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_QUOTE_DATE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_QUOTE_DATE_TEXTBOX_XPATH);
		BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_QUOTE_DATE_TEXTBOX_XPATH);
		System.out.println("Due Date: " + CommonPage.getCurrentDataIn("MM/dd/yyyy"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_QUOTE_DATE_TEXTBOX_XPATH, CommonPage.getCurrentDataIn("MM/dd/yyyy"));
	}

	/**
	 * This method selects quote type
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void quoteTypeSelection() throws Exception {
		if(CommonPage.getTestData("QuoteType") != null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_QUOTE_TYPE_DROPDOWN_ID);
			BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_QUOTE_TYPE_DROPDOWN_ID);
			BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_QUOTE_TYPE_DROPDOWN_ID);
			System.out.println("Quote type: " + CommonPage.getTestData("QuoteType"));
			BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_QUOTE_TYPE_DROPDOWN_ID, CommonPage.getTestData("QuoteType"));
		} else {
			throw new OrderingErrorOccured("Quote Type data is not present");
		}
	}
	
	/**
	 * This method selects upfitter
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void selectUpfitterSupplier() throws Exception {
		if(CommonPage.getTestData("UpfitterSupplierName") != null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFITTER_SEARCH_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFITTER_SEARCH_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFITTER_SEARCH_XPATH);
			BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFITTER_SEARCH_XPATH);
			System.out.println("Upfitter supplier name: " + CommonPage.getTestData("UpfitterSupplierName"));
			BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFITTER_SEARCH_XPATH, CommonPage.getTestData("UpfitterSupplierName"));
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCH_UPFITTER_XPATH);
			BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCH_UPFITTER_XPATH);
		} else {
			throw new OrderingErrorOccured("UpfitterSupplierName data is null");
		}
	}
	
	/**
	 * This method selects installer
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void selectInstallerSupplier() throws Exception {
		if(CommonPage.getTestData("InstallerSupplierName")!=null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_INSTALLER_SEARCH_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_INSTALLER_SEARCH_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_INSTALLER_SEARCH_XPATH);
			BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_INSTALLER_SEARCH_XPATH);
			System.out.println("Installer supplier name: " + CommonPage.getTestData("InstallerSupplierName"));
			BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_INSTALLER_SEARCH_XPATH, CommonPage.getTestData("InstallerSupplierName"));
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCH_INSTALLER_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCH_INSTALLER_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCH_INSTALLER_XPATH);
			BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCH_INSTALLER_XPATH);
		} else {
			throw new OrderingErrorOccured("UpfitterSupplierName data is null");
		}
	}

	/**
	 * This method selects ship to drpdwn value
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void shipToSelection() throws Exception {
		if(CommonPage.getTestData("ShipTo")!=null && ! CommonPage.getTestData("ShipTo").trim().equalsIgnoreCase("Supplier")) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SHIP_TO_DROPDOWN_ID);
			BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SHIP_TO_DROPDOWN_ID);
			BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SHIP_TO_DROPDOWN_ID);
			System.out.println("Ship to: " + CommonPage.getTestData("ShipTo"));
			BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SHIP_TO_DROPDOWN_ID, CommonPage.getTestData("ShipTo"));
		} else if(CommonPage.getTestData("ShipTo").trim().equalsIgnoreCase("Supplier")) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SHIP_TO_DROPDOWN_ID);
			BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SHIP_TO_DROPDOWN_ID);
			BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SHIP_TO_DROPDOWN_ID);
			System.out.println("Ship to: " + CommonPage.getTestData("ShipTo").trim());
			BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SHIP_TO_DROPDOWN_ID, CommonPage.getTestData("ShipTo").trim());
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUOTE_SHIPTOSEARCH_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SHIP_TO_DROPDOWN_ID);
			BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SHIP_TO_DROPDOWN_ID);
			System.out.println("Ship to supplier name: " + CommonPage.getTestData("ShipToSupplierName").trim());
			BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUOTE_SHIPTOSEARCH_XPATH, CommonPage.getTestData("ShipToSupplierName").trim());
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUOTE_SELECT_FIRST_SHIPTOSEARCH_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUOTE_SELECT_FIRST_SHIPTOSEARCH_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUOTE_SELECT_FIRST_SHIPTOSEARCH_XPATH);
			BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUOTE_SELECT_FIRST_SHIPTOSEARCH_XPATH);
		} else {
			throw new OrderingErrorOccured("ShipTo data is null");
		}
	}
	
	/**
	 * This method selects drop ship
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void dropShipSelection() throws Exception {
		if(CommonPage.getTestData("DropShip")!=null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DROP_SHIP_SEARCH_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DROP_SHIP_SEARCH_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DROP_SHIP_SEARCH_XPATH);
			BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DROP_SHIP_SEARCH_XPATH);
			System.out.println("Drop ship: " + CommonPage.getTestData("DropShip"));
			BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DROP_SHIP_SEARCH_XPATH, CommonPage.getTestData("DropShip"));
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCH_DROP_SHIP_XPATH);
			BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCH_DROP_SHIP_XPATH);
		} else {
			throw new OrderingErrorOccured("DropShip data is null");
		}
	}

	/**
	 * This method selects work type
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void workTypeSelection() throws Exception {
		if(CommonPage.getTestData("WorkType")!=null) {
			BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_WORK_TYPE_DROPDOWN_ID, CommonPage.getTestData("WorkType"));
		} else {
			throw new OrderingErrorOccured("WorkType data is null");
		}
	}

	/**
	 * This method selects chassis information
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void selectChassis() throws Exception {
		if(CommonPage.getTestData("ChassisInfo") != null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CHASSIS_SEARCH_TEXTBOX_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CHASSIS_SEARCH_TEXTBOX_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CHASSIS_SEARCH_TEXTBOX_XPATH);
			BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CHASSIS_SEARCH_TEXTBOX_XPATH);
			System.out.println("Chassis Info: " + CommonPage.getTestData("ChassisInfo"));
			BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CHASSIS_SEARCH_TEXTBOX_XPATH, CommonPage.getTestData("ChassisInfo"));
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCH_CHASSIS_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCH_CHASSIS_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCH_CHASSIS_XPATH);
			BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SELECT_FIRST_SEARCH_CHASSIS_XPATH);
		} else {
			throw new OrderingErrorOccured("Chassis Info data is null");
		}
	}

	public static void addLineItems() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_LINE_ITEMS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_LINE_ITEMS_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_LINE_ITEMS_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_LINE_ITEMS_XPATH);
	}
	
	/**
	 * This method enters line items data
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void enterAddLineItemsData() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_CODE_LINE_ITEMS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_CODE_LINE_ITEMS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_CODE_LINE_ITEMS_ID);
		System.out.println("Upfit line item code: " + CommonPage.getTestData("UpfitLineItemCode"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_CODE_LINE_ITEMS_ID, CommonPage.getTestData("UpfitLineItemCode")+CommonPage.randomNumberInRange(2));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CATEGORY_LINE_ITEMS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CATEGORY_LINE_ITEMS_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CATEGORY_LINE_ITEMS_XPATH);
		System.out.println("Upfit line item category: " + CommonPage.getTestData("UpfitLineItemCategory"));
		BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CATEGORY_LINE_ITEMS_XPATH, CommonPage.getTestData("UpfitLineItemCategory"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DESCRIPTION_LINE_ITEMS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DESCRIPTION_LINE_ITEMS_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DESCRIPTION_LINE_ITEMS_XPATH);
		System.out.println("Upfit line item desc: " + CommonPage.getTestData("UpfitLineItemDesc"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_DESCRIPTION_LINE_ITEMS_XPATH, CommonPage.getTestData("UpfitLineItemDesc"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_EXTENDED_DESCRIPTION_LINE_ITEMS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_EXTENDED_DESCRIPTION_LINE_ITEMS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_EXTENDED_DESCRIPTION_LINE_ITEMS_ID);
		System.out.println("Upfit line item ext desc: " + CommonPage.getTestData("UpfitLineItemExtDesc"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_EXTENDED_DESCRIPTION_LINE_ITEMS_ID, CommonPage.getTestData("UpfitLineItemExtDesc"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PRICE_LINE_ITEMS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PRICE_LINE_ITEMS_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PRICE_LINE_ITEMS_XPATH);
		System.out.println("Upfit line item opt price: " + CommonPage.getTestData("UpfitLineItemOptPrice"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PRICE_LINE_ITEMS_XPATH, CommonPage.getTestData("UpfitLineItemOptPrice"));
	}
	
	/**
	 * This method saves Quote
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void clickOnSaveButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SAVE_QUOTE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SAVE_QUOTE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SAVE_QUOTE_XPATH);
		new FluentWait<>(WebDriverAccess.getDriver())
		.pollingEvery(Duration.ofSeconds(1))
		.withTimeout(Duration.ofSeconds(new Long(CommonPage.getTestData("WaitTime"))))
        .ignoring(WebDriverException.class)
        .until(driver -> {
            WebElement saveQuoteButtonElement = driver.findElement(By.xpath(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SAVE_QUOTE_XPATH.getValue()));
            saveQuoteButtonElement.click();
            return saveQuoteButtonElement;
        });
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method saves Quote
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void getQuoteId() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUOTEID_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUOTEID_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUOTEID_XPATH);
		String quoteId = BrowserAction.getElementAttributeValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUOTEID_XPATH, "data-val").trim();
		System.out.println("Quote id: " + quoteId);
		CommonPage.loadXMLParameterToTestData("QuoteId", quoteId);
	}
	
	/**
	 * This method closes project details page
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void closeProjectDetails() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_DETAILS_CLOSE_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_DETAILS_CLOSE_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_DETAILS_CLOSE_BUTTON_CSS);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_DETAILS_CLOSE_BUTTON_CSS);
	}

	public static void goToEditUpfitQuotePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FIRT_SAVED_QUOTE_PROJECT_DETAILS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FIRT_SAVED_QUOTE_PROJECT_DETAILS_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FIRT_SAVED_QUOTE_PROJECT_DETAILS_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FIRT_SAVED_QUOTE_PROJECT_DETAILS_XPATH);
	}

	public static void goToConvertToUpfitFleetSpecPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CONVERT_TO_UPFIT_FLEET_SPEC_LINK_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CONVERT_TO_UPFIT_FLEET_SPEC_LINK_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CONVERT_TO_UPFIT_FLEET_SPEC_LINK_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CONVERT_TO_UPFIT_FLEET_SPEC_LINK_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}

	public static void enterUpfitFleetSpecName() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_NAME_TEXT_BOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_NAME_TEXT_BOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_NAME_TEXT_BOX_ID);
		BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_NAME_TEXT_BOX_ID);
		String upfitName = "Upfit" + CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("UpfitName", upfitName);
		System.out.println("Upfit name: " + CommonPage.getTestData("UpfitName"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_NAME_TEXT_BOX_ID, CommonPage.getTestData("UpfitName"));
	}

	public static void enterUpfitFleetSpecDescription() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_DESCRIPTION_TEXTAREA_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_DESCRIPTION_TEXTAREA_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_DESCRIPTION_TEXTAREA_ID);
		BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_DESCRIPTION_TEXTAREA_ID);
		String upfitSpecDesc= "UpfitSpecDesc" + CommonPage.randomAlphaNumericString();
		System.out.println("Upfit spec desc: " + upfitSpecDesc);
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFIT_FLEET_SPEC_DESCRIPTION_TEXTAREA_ID, upfitSpecDesc);
	}
	
	public static void clickOnConvertUpfitSpecOkButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CONVERT_TO_UPFIT_SPEC_OK_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CONVERT_TO_UPFIT_SPEC_OK_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CONVERT_TO_UPFIT_SPEC_OK_BUTTON_ID);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CONVERT_TO_UPFIT_SPEC_OK_BUTTON_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}

	public static void clickOnElementApproveLink() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ELEMENT_APPROVE_LINK_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ELEMENT_APPROVE_LINK_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ELEMENT_APPROVE_LINK_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ELEMENT_APPROVE_LINK_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();		
	}
	
	public static void clickOnClientApproveLink() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAccess.getLocator(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CLIENT_APPROVE_LINK_XPATH)));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CLIENT_APPROVE_LINK_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CLIENT_APPROVE_LINK_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CLIENT_APPROVE_LINK_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_CLIENT_APPROVE_LINK_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	public static void clickOnCloseUpfitSpecMaintenancePage() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAccess.getLocator(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_CLOSE_UPFIT_SPEC_MAINTENANCE_XPATH)));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_CLOSE_UPFIT_SPEC_MAINTENANCE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_CLOSE_UPFIT_SPEC_MAINTENANCE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_CLOSE_UPFIT_SPEC_MAINTENANCE_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_CLOSE_UPFIT_SPEC_MAINTENANCE_XPATH);
	}
	
	public static void clickOnCloseEditUpfitQuotePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_CLOSE_EDIT_UPFIT_QUOTE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_CLOSE_EDIT_UPFIT_QUOTE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_CLOSE_EDIT_UPFIT_QUOTE_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_CLOSE_EDIT_UPFIT_QUOTE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_CLOSE_EDIT_UPFIT_QUOTE_OK_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_CLOSE_EDIT_UPFIT_QUOTE_OK_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_CLOSE_EDIT_UPFIT_QUOTE_OK_BUTTON_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_PROJECTDETAILS_CLOSE_EDIT_UPFIT_QUOTE_OK_BUTTON_XPATH);
	}

	public static void clickOnAttachToFleetSpecLink() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ATTACH_TO_FLEET_SPEC_LINK_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ATTACH_TO_FLEET_SPEC_LINK_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ATTACH_TO_FLEET_SPEC_LINK_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ATTACH_TO_FLEET_SPEC_LINK_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}

	public static void waitForUpfitSpecFleetSpecAssociationPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_WAIT_FOR_FLEET_SEPC_ASSOCIATION_PAGE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_WAIT_FOR_FLEET_SEPC_ASSOCIATION_PAGE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_WAIT_FOR_FLEET_SEPC_ASSOCIATION_PAGE_XPATH);
	}	

	public static void selectFleetSpecFromFleetSpecificationSearchBox(String fleetName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SEARCH_FLEET_SPECIFICATION_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SEARCH_FLEET_SPECIFICATION_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SEARCH_FLEET_SPECIFICATION_TEXTBOX_XPATH);
		System.out.println("Fleet spec: " + fleetName);
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_SEARCH_FLEET_SPECIFICATION_TEXTBOX_XPATH, fleetName);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FIRST_SEARCH_FLEET_SPECIFICATION_XPATH);
	}
	
	/**
	 * This method clicks on fleet Spec save button
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void clickOnFleetSpecificationSaveButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEET_SPECIFICATION_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEET_SPECIFICATION_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEET_SPECIFICATION_SAVE_BUTTON_XPATH);			
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEET_SPECIFICATION_SAVE_BUTTON_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	} 
	
	public static void searchProjectByName() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_CLASS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_CLASS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_CLASS);
		new WebDriverWait(WebDriverAccess.getDriver(), 10).until(ExpectedConditions.numberOfElementsToBe(By.className("noty_body"), 0));
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_CLASS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_NAME_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_NAME_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_NAME_XPATH);
		((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", BrowserAction.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_NAME_XPATH));
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_NAME_XPATH);
		((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", BrowserAction.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_NAME_SEARCH_ID));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_NAME_SEARCH_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_NAME_SEARCH_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_NAME_SEARCH_ID);
		System.out.println("Project name: " + CommonPage.getTestData("ProjectName"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_NAME_SEARCH_ID, CommonPage.getTestData("ProjectName"));
		((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", BrowserAction.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_NAME_APPLY_XPATH));
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_NAME_APPLY_XPATH);
		WebElement applyElement = BrowserAccess.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_NAME_APPLY_XPATH);
		((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].click();", applyElement);
	}

	public static void clickProjectExportAndVerify(String className) throws Exception {
		OrderingSummaryPage.clickExportButton("Project Queue", className);
	}

	public static ArrayList<String> projectHeaderColumnList() throws Exception {
		ArrayList<String> columnListStr = new ArrayList<>(); 
		int count = 0;
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_HEADER_COLUMN_LIST_XPATH);
		List<WebElement> columnList = BrowserAction.getElements(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_HEADER_COLUMN_LIST_XPATH);
		for(WebElement str : columnList) {
			if(count == 20)
				BrowserAction.hoverOverElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_COLUMN_LAST_COLUMN_XPATH);
			if(!str.getText().equals("")) {
				columnListStr.add(str.getText().trim().replace("\n", " "));
				count++;
			}
		}
		if(!columnListStr.isEmpty()) {
			for(int i=0 ;i < columnListStr.size(); i++) {
				if(columnListStr.get(i).trim().equals("")) 
					columnListStr.remove(i); 
			}
		}
		return columnListStr;
	}

	/**
	 * This method clicks on Add/Remove button and select all columns
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void addAndRemoveProjectQueueAndVerifySelectAllColumns() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
	}

	/**
	 * This method clicks on Add/Remove button and Deselect all columns
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void addAndRemoveProjectQueueAndVerifyDeselectAllColumns() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
		ArrayList<String> projectHeaderColumnList = projectHeaderColumnList(); 
		Assert.assertTrue(projectHeaderColumnList.isEmpty(), "Add/Remove functionality is not working properly when we deselect All Columns");
	}

	/**
	 * This method clicks on Add/Remove button and select First four  columns
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void addAndRemoveProjectQueueAndVerifySelectFourColumns() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_COLUMN_LIST_XPATH);
		List<WebElement> columnList = BrowserAction.getElements(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_COLUMN_LIST_XPATH);
		int count = 0;
		for(WebElement str : columnList) {
			count++;
			str.click();
			if(count == 4)
				break;
		}
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
	}
	
	public static void addAndRemoveProjectQueueSelectAllColumns() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_CLEAR_ALL_CLOSE_BUTTON_CLASS);
	}

	/**
	 * This method creates project and selects it.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void createAndSelectProject() throws Exception {
		OrderingBOOnProjectQueuePage.clickOnAddProjectFromProjectQueuePage();
		OrderingBOOnProjectQueuePage.selectClientFromClientSearchBox();
		OrderingBOOnProjectQueuePage.enterProjectName();
		OrderingBOOnProjectQueuePage.enterProjectDescription();
		OrderingBOOnProjectQueuePage.selectProjectDueDate();
		OrderingBOOnProjectQueuePage.clickOnAddProjectOkButton();
		OrderingBOQueuePage.addControls("Project Name", CommonPage.getTestData("ProjectName"));
		OrderingBOOnProjectQueuePage.selectFirstProject();
	}
	
	/**
	 * This method selects project from project queue
	 * @lastModifiedBy DBhagat
	 * @throws Exception
	 */
	public static void selectProject() throws Exception {
		OrderingBOOnProjectQueuePage.scrollDownToProjectQueue();
		OrderingBOOnProjectQueuePage.validateClearFilters2();
		OrderingBOQueuePage.searchByClientProjectNumber();
		OrderingBOOnProjectQueuePage.selectFirstProjectInQueue();
	}
	
	/**
	 * This method enters data on quote page.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void enterQuoteDetails() throws Exception {
		OrderingBOOnProjectQueuePage.enterSupplierQuoteNumber();
		OrderingBOOnProjectQueuePage.enterEstimatedLeadTime();
		OrderingBOOnProjectQueuePage.enterSupplierQuoteDate();
		OrderingBOOnProjectQueuePage.quoteTypeSelection();
		OrderingBOOnProjectQueuePage.selectUpfitterSupplier();
		OrderingBOOnProjectQueuePage.selectInstallerSupplier();
		OrderingBOOnProjectQueuePage.shipToSelection();
		OrderingBOOnProjectQueuePage.dropShipSelection();
		OrderingBOOnProjectQueuePage.workTypeSelection();
		OrderingBOOnProjectQueuePage.selectChassis();
		OrderingBOOnProjectQueuePage.addLineItems();
		OrderingBOOnProjectQueuePage.enterAddLineItemsData();
	}
	
	/**
	 * This method creates quote in project queue page.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void createQuote() throws Exception {
		OrderingBOOnProjectQueuePage.clickOnQuoteQueueFromProjectDetailsPage();
		OrderingBOOnProjectQueuePage.clickOnAddQuoteQueueLink();
		OrderingBOOnProjectQueuePage.enterQuoteDetails();
		OrderingBOOnProjectQueuePage.clickOnSaveButton();
	}

    /**
	 * This method verifies the fields present on the Project queue page.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyFields() throws Exception {
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_CLASS), "Controls link is not available on page");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_SAVE_BUTTON_XPATH), "Save link is not available on page");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_EXPORT_XPATH), "Export link is not available on page");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_PROJECT_ADD_REMOVE_XPATH), "Add/Remove link is not available on page");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID), "Standard View field is not available on page");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_PROJECT_LINK_XPATH), "Add Project field is not available on page");
	}

	/**
	 * This method creates a new project and enters the required Project details.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void createNewProjectAndFillData() throws Exception {
		OrderingBOOnProjectQueuePage.clickOnAddProjectFromProjectQueuePage();
		OrderingBOOnProjectQueuePage.selectClientFromClientSearchBox();
		OrderingBOOnProjectQueuePage.enterProjectNameInProjectNameTextBox(CommonPage.randomAlphaNumericString());
		OrderingBOOnProjectQueuePage.enterProjectDescription();
		OrderingBOOnProjectQueuePage.selectProjectDueDate();
	}

	/**
	 * This method validate the pagination functionality on Project Queue Page
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyPaginationFunctionality() throws Exception {
		Select slt = new Select(BrowserAction.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUEUE_PAGE_PAGINATION_NAME));
		if (!(slt.getFirstSelectedOption().getText().equals("100")))
			throw new OrderingErrorOccured("Unable to match dropdown 1st option");
		BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUEUE_PAGE_PAGINATION_NAME, "500");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUEUE_PAGE_PAGINATION_NAME);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUEUE_PAGE_PAGINATION_NAME);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_QUEUE_PAGE_PAGINATION_NAME);
	}
	
	/**
	 * This method selects search by option
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void selectFleetSpecSearchBy(String option) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_SEARCHBY_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_SEARCHBY_XPATH);
		BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_SEARCHBY_XPATH, option);
	}
	
	/**
	 * This method attaches fleet spec to Upfit Spec
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void attachFleetSpec() throws Exception {
		OrderingBOOnProjectQueuePage.clickOnAttachToFleetSpecLink();
		OrderingBOOnProjectQueuePage.waitForUpfitSpecFleetSpecAssociationPage();
		OrderingBOOnProjectQueuePage.selectFleetSpecSearchBy(" Fleet Spec ID ");
		OrderingBOOnProjectQueuePage.selectFleetSpecFromFleetSpecificationSearchBox(CommonPage.getTestData("FleetSpecSearch"));
		OrderingBOOnProjectQueuePage.clickOnFleetSpecificationSaveButton();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method verifies that Fleet Spec is attached. 
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void verifyAttachedFleetSpec() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ID_XPATH);
		String fleetSpec = BrowserAccess.getElementText(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ID_XPATH).trim();
		Assert.assertEquals(fleetSpec.substring(0,fleetSpec.lastIndexOf('-')),CommonPage.getTestData("FleetSpecSearch"));
	}

	/**
	 * This method clicks on detach fleet Spec button. 
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void clickDetachFleetSpec() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_DETACH_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_DETACH_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_DETACH_XPATH);	
	}
	
	/**
	 * This method clicks on detach fleet Spec button. 
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void detachFleetSpec() throws Exception {
		OrderingBOOnProjectQueuePage.clickDetachFleetSpec();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_DETACH_SELECTRDIO_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_DETACH_SELECTRDIO_XPATH);
		new FluentWait<>(WebDriverAccess.getDriver())
		.pollingEvery(Duration.ofSeconds(1))
		.withTimeout(Duration.ofSeconds(new Long(CommonPage.getTestData("WaitTime"))))
        .ignoring(WebDriverException.class)
        .until(driver -> {
            WebElement selectFleetSpec = driver.findElement(By.xpath(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_DETACH_SELECTRDIO_XPATH.getValue()));
            selectFleetSpec.click();
            return selectFleetSpec;
        });
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_DETACH_SAVE_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAccess.getElement(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_SAVE_XPATH)));
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_SAVE_XPATH);
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method clicks on detach fleet Spec button. 
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void verifyFleetSpecDetached() throws Exception {
		BrowserVerify.verifyElementIsNotPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ID_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ID_XPATH);
	}
	
	/**
	 * This method converts quote to upfit spec in from project queues 
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void convertToUpfitSpecsFrmPrjQuotePage() throws Exception {
		OrderingBOOnProjectQueuePage.goToEditUpfitQuotePage();
		OrderingBOOnProjectQueuePage.clickOnSaveButton();
		OrderingBOOnProjectQueuePage.goToConvertToUpfitFleetSpecPage();
		OrderingBOOnProjectQueuePage.enterUpfitFleetSpecName();
		OrderingBOOnProjectQueuePage.enterUpfitFleetSpecDescription();
		OrderingBOOnProjectQueuePage.clickOnConvertUpfitSpecOkButton();
		OrderingBOOnProjectQueuePage.clickOnElementApproveLink();
	}
	
	/**
	 * This method adds group
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void addAndSelectGroup(String rule) throws Exception {
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_MANAGEGROUPS_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_MANAGEGRP_ACTIONADD_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_MANAGEGRP_ACTIONADD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_MANAGEGRP_GRPNAME_XPATH);
		BrowserAction.clickandClear(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_MANAGEGRP_GRPNAME_XPATH);
		String groupName="Test"+CommonPage.generateRandomNumber();
		CommonPage.loadXMLParameterToTestData("GroupName", groupName);
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_MANAGEGRP_GRPNAME_XPATH, CommonPage.getTestData("GroupName"));
		BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_MANAGEGRP_SELECTRULE_XPATH, rule);
		OrderingCommonPage.checkAlertPopUp();
		BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_MANAGEGRP_SELECTGROUP_XPATH, CommonPage.getTestData("GroupName"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_MANAGEGROUPSYES_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_MANAGEGROUPSYES_XPATH);
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method verifies group added.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void verifyGroupAdded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_GROUPLABEL_XPATH);
		Assert.assertTrue(BrowserAccess.getElementText(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_GROUPLABEL_XPATH).trim().contains(CommonPage.getTestData("GroupName")), "Group not added");
	}
	
	/**
	 * This method verifies that group is added to upfit spec 
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void addAndVerifyGroupAdded(String rule) throws Exception {
		OrderingBOOnProjectQueuePage.clickOnAddLineItemFleetSpecMain();
		OrderingBOOnProjectQueuePage.addLineItemsOnUpfitSpecPage();
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOOnProjectQueuePage.addAndSelectGroup(rule);
		OrderingBOOnProjectQueuePage.verifyGroupAdded();
	}
	
	/**
	 * This method adds line items details on upfit spec page
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void addLineItemsOnUpfitSpecPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_PARTNUMBER_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_PARTNUMBER_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_PARTNUMBER_XPATH);
		System.out.println("Upfit line item code: " + CommonPage.getTestData("UpfitLineItemCode"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_PARTNUMBER_XPATH, CommonPage.getTestData("UpfitLineItemCode")+CommonPage.generateRandomNumber());
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_CATEGORY_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_CATEGORY_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_CATEGORY_XPATH);
		System.out.println("Upfit line item category: " + CommonPage.getTestData("UpfitLineItemCategory"));
		BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_CATEGORY_XPATH, CommonPage.getTestData("UpfitLineItemCategory"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_PARTNAME_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_PARTNAME_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_PARTNAME_XPATH);
		System.out.println("Upfit line item desc: " + CommonPage.getTestData("UpfitLineItemDesc"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_PARTNAME_XPATH, CommonPage.getTestData("UpfitLineItemDesc"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_EXTDESC_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_EXTDESC_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_EXTDESC_XPATH);
		System.out.println("Upfit line item ext desc: " + CommonPage.getTestData("UpfitLineItemExtDesc"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_EXTDESC_XPATH, CommonPage.getTestData("UpfitLineItemExtDesc"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_PRICE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_PRICE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_PRICE_XPATH);
		System.out.println("Upfit line item opt price: " + CommonPage.getTestData("UpfitLineItemOptPrice"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_PRICE_XPATH, CommonPage.getTestData("UpfitLineItemOptPrice"));
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_SAVEUPFITSPEC_XPATH);
	}
	
	/**
	 * This method adds line items details on upfit spec page
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void clickOnAddLineItemFleetSpecMain() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_XPATH);
		BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_FLEETSPEC_ADDLINEITEM_XPATH);
	}
	
	/**
	 * This method validates fleet spec status
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void validateUpfitSpecStatus(String status) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFITSPEC_STATUS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFITSPEC_STATUS_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFITSPEC_STATUS_XPATH.getValue()),status));
		Assert.assertEquals(BrowserAccess.getElementText(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_UPFITSPEC_STATUS_XPATH), status, "Upfit Spec status dosent match with:" + status);
	}	
	
	/**
	 * This method will click on clear filter button in Project queues page in BO
	 * @lastModifiedBy DBhagat
	 */
	public static void validateClearFilters2() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS2_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS2_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS2_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS2_XPATH),"Clear Filters", "Clear Filter is not matched");
		BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS2_XPATH);
		//List<WebElement> textFields = BrowserAccess.getElements(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS2_XPATH);
		/*for(WebElement textField : textFields) {
			{
				String textfeilds=textField.getText();
                Assert.assertEquals(textfeilds, "", "Value in the search fields is there");
            }
        }*/
	}
		
}