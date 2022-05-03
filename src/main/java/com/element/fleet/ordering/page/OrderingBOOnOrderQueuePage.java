package com.element.fleet.ordering.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.ExcelUtil;
import com.element.fleet.ordering.enums.OrderingBOBillingQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingBOChangeHistoryPageEnum;
import com.element.fleet.ordering.enums.OrderingBOOnOrderProjectPageEnum;
import com.element.fleet.ordering.enums.OrderingBOOnOrderQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingBOOrdMaintPageEnum;
import com.element.fleet.ordering.enums.OrderingBOQueuePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.element.fleet.ordering.enums.OrderingMyOrdersPageEnum;
import com.element.fleet.ordering.enums.OrderingWIPOrdersPageEnum;
import com.element.fleet.ordering.enums.OrderingVehicleConfigFleetSpecsPageEnum;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingBOOnOrderQueuePage {

	private static int numberOfDIOs;

	/**
	 * This method waits for the queues on the order queue page
	 * @lastModifiedBy
	 * @throws Exception
	 */
	public static void waitForOnOrderQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void onOrderQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "On Order", "On Order queue label heading did not match with the expected string");
	}
	
	public static void waitForAcknowledgementQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void acknowledgementQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Acknowledgment", "Acknowledgment queue label heading did not match with the expected string");
	}
	
	public static void waitForBillingQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void billingQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Billing", "Billing queue label heading did not match with the expected string");
	}
	
	public static void waitForCreditQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
		BrowserVerify.verifyElementDisabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_APPROVE_BUTTON_XPATH);
		BrowserVerify.verifyElementDisabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DENY_BUTTON_XPATH);
	}
	
	public static void creditQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Credit", "Credit queue label heading did not match with the expected string");
	}
	
	public static void waitForDealerQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void dioQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Dealer Installed Options Queue", "DIO queue label heading did not match with the expected string");
	}
	
	public static void waitForDIOQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void dealerQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Dealer", "Dealer queue label heading did not match with the expected string");
	}
	
	public static void waitForDriverChangeQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void driverChangeQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Driver Change", "Driver Change queue label heading did not match with the expected string");
	}
	
	public static void waitForInsuranceQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void insuranceQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Insurance", "Insurance queue label heading did not match with the expected string");
	}
	
	public static void waitForMainframeBridgingQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void mainframeBridgingQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Mainframe Bridging", "Mainframe Bridging queue label heading did not match with the expected string");
	}
	
	public static void waitForOrderTransmissionQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void orderTransmissionQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Order Transmission", "Order Transmission queue label heading did not match with the expected string");
	}
	
	public static void waitForProjectQueuePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_PROJECT_LINK_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_PROJECT_LINK_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_PROJECT_LINK_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_ADD_PROJECT_LINK_XPATH);
	}
	
	public static void projectQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Project", "Project queue label heading did not match with the expected string");
	}

	public static void waitForQuoteQueuePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void quoteQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Quote", "Quote queue label heading did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SAVEBUTTON_XPATH), "Save", "Save button label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORTBUTTON_XPATH), "Export", "Export button label heading did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ADDREMOVEBUTTON_XPATH), "Manage Column View", "Manage Column View button label did not match with the expected string");
	}
	
	public static void waitForRFQQueuePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void rfqQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "RFQ", "RFQ queue label heading did not match with the expected string");
	}
	
	public static void waitForStockQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void stockQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Stock", "Stock queue label heading did not match with the expected string");
	}
	
	public static void waitForSweeperAdminQueuePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void sweeperAdminQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Sweeper Results", "Sweeper Results queue label heading did not match with the expected string");
	}
	
	public static void waitForTitleAndRegQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void titleAndRegQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Title & Registration", "Title & Reg queue label heading did not match with the expected string");
	}
	
	public static void waitForUpfitQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void upfitQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Upfit Queue", "Upfit Queue queue label heading did not match with the expected string");
	}
	
	public static void waitForUpfitSpecQueuePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS);
	}
	
	public static void upfitSpecQueuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CSS), "Upfit Spec", "Upfit Spec queue label heading did not match with the expected string");
	}
	
	/**
	 * This method enter log number on the search field and waits for the requested log number to be displayed
	 * @lastModifiedBy shisingh
	 * @param searchField
	 * @param searchTerm
	 * @throws Exception
	 */
	public static void searchBy(String enteringField, String searchTerm) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID);
		List<WebElement> searchFields = BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHFIELDS_ID).findElements(By.xpath(".//div/input"));
		for(WebElement searchField:searchFields) {
			if(searchField.getAttribute("name").equals(enteringField)) {
				System.out.println("Log number: " + searchTerm);
				searchField.clear();
				searchField.sendKeys(searchTerm);
				break;
			}						
		}
		BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHICON_CSS);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}

	public static void clickOnTheFirstOptionOfListIfAvailable(String section) throws Throwable {
		System.out.println(section + " Maintenance");
		switch(section) {
			case "On-Order": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnOrderQueuePage.verifyOrderDetailsPage();
				}
				break;
			case "Acknowledgment": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
					OrderingBOOnOrderQueuePage.verifyOrderDetailsPage();
				}
				break;
			case "Billing": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
					OrderingBOOnOrderQueuePage.verifyOrderDetailsPage();
				}
				break;
			case "Credit": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
					OrderingBOOnOrderQueuePage.verifyOrderDetailsPage();
				}
				break;
			case "Dealer": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
					OrderingBOOnOrderQueuePage.verifyOrderDetailsPage();
				}
				break;
			case "DIO": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
					OrderingBOOnOrderQueuePage.verifyOrderDetailsPage();
				}
				break;
			case "Driver Change": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
					OrderingBOOnOrderQueuePage.verifyOrderDetailsPage();
				}
				break;
			case "Insurance": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
					OrderingBOOnOrderQueuePage.verifyOrderDetailsPage();
				}
				break;
			case "Mainframe Bridging": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
					OrderingBOOnOrderQueuePage.verifyOrderDetailsPage();
				}
				break;
			case "Order Transmission": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
					OrderingBOOnOrderQueuePage.verifyOrderDetailsPage();
				}
				break;
			case "Project": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnProjectQueuePage.waitForProjectDetailsPage();
					OrderingBOOnProjectQueuePage.projectDetailsPageHeadingLabelValidation();
				}
				break;
			case "Quote": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOQuotePage.waitForQuoteDetailsPage();
					OrderingBOQuotePage.quoteDetailsPageHeadingLabelValidation();
				}
				break;
			case "RFQ": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					throw new OrderingErrorOccured("Currently RFQ page implementation is not available");
				}
				break;
			case "Stock": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
					OrderingBOOnOrderQueuePage.verifyOrderDetailsPage();
				}
				break;
			case "Sweeper Results": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					System.out.println("No pop up will generated after clicking on any record in Sweeper Results");
				}
				break;
			case "Title & Reg": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
					OrderingBOOnOrderQueuePage.verifyOrderDetailsPage();
				}
				break;
			case "Upfit": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
					OrderingBOOnOrderQueuePage.verifyOrderDetailsPage();
				}
				break;
			case "Upfit Spec": 
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingBOUpfitSpecPage.waitForUpfitSpecDetailsPage();
					OrderingBOUpfitSpecPage.upfitSpecDetailsPageHeadingLabelValidation();
				}
				break;
			default: throw new InvalidSwitchCaseException("Invalid page requested");
		}
	}
	
	public static boolean clickOnTheFirstOptionOfListIfAvailable() throws Exception {
		boolean optionPresent = false;
		List<WebElement> tableRows = BrowserAction.getElements(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH);
		if((tableRows.size()==1)&&tableRows.get(0).getText().contains("No data available in table")) {
			System.out.println("No element available to click\n"+BrowserAccess.getElementText(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH));
		}
		else {
			tableRows.get(0).click();
			System.out.println("Clicked on first row");
			OrderingCommonPage.checkAlertPopUp();
			optionPresent = true;
		}
		return optionPresent;
	}
	
	public static boolean clickOnTheFirstRecordFromViewTable() throws Exception {
		String records = BrowserAccess.getElementText(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_NUMBER_OF_RECORDS_VIEWTABLE_XPATH).split(" ")[0];
		if (!"0".equals(records)) {
			WebElement element = BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SAERCHRESULT_ROW_VIEWTABLE_XPATH);
			element.click();
			System.out.println("Clicked on first row");
			OrderingCommonPage.checkAlertPopUp();
			return true;
		} else {
			System.out.println("No Records present in the Table");
			return false;
		}
	} 
	
	public static void resolveSoftErrors() throws Throwable {
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Approval");
		OrderingBOOrdMaintPage.checkAndRemoveAllSoftApprovalErrorsIfPresent();
	}
	
	public static void waitForOrderDetailsPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_GENERALINFOBTN_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_GENERALINFOBTN_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_GENERALINFOBTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_APPROVAL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DRIVERBTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_VEHICLEBTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DIOBTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFITBTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_BILLINGBTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_TITLEREGISTRATIONBTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_INSURANCEBTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DEALERBTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CUSTOMFIELDSBTN_XPATH);
	}
	
	public static void orderDetailsPageHeadingLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_GENERALINFOBTN_XPATH), "General Order", "General Order label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_APPROVAL_XPATH), "Approval", "Approval label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DRIVERBTN_XPATH), "Driver", "Driver label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_VEHICLEBTN_XPATH), "Vehicle", "Vehicle label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DIOBTN_XPATH), "DIO", "DIO label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFITBTN_XPATH), "Upfit", "Upfit label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_BILLINGBTN_XPATH), "Billing", "Billing label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_TITLEREGISTRATIONBTN_XPATH), "Title & License", "Title & License label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_INSURANCEBTN_XPATH), "Insurance", "Insurance label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DEALERBTN_XPATH), "Dealer", "Dealer label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CUSTOMFIELDSBTN_XPATH), "Custom Fields", "Custom Fields label did not match with the expected string");
	}
	
	public static void verifyOrderDetailsPage() throws Throwable {
		OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
		OrderingBOOnOrderQueuePage.orderDetailsPageHeadingLabelValidation();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("General Order");
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Approval");
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Driver");
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Vehicle");
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("DIO");
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Upfit");
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Billing");
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Title & License");
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Insurance");
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Dealer");
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Custom Fields");
	}
	
	/**
	 * This method clicks goes to New Vehicle Specification section approves order and save it and then download the PO PDF.
	 * @lastModifiedBy shisingh
	 * @param className
	 * @throws Throwable 
	 */
	public static void approveOrderAndDownloadPDF(String className) throws Throwable {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Approval");
		OrderingBOOrdMaintPage.clickApproveButtonVehicle();
		OrderingBOOrdMaintPage.verifyOrderSubmittedSuccessfully();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Vehicle");
		OrderingSummaryPage.clickExportButton("PO", className);
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Upfit");
		OrderingSummaryPage.clickExportButton("Upfit", className);
	}
	
	public static void orderDetailsPageSectionClick(String section) throws Exception {
		switch(section) {
			case "General Order": BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_GENERALINFOBTN_XPATH);
				OrderingBOOnOrderQueuePage.orderDetailsPageGeneralOrderSectionLabelValidation();
				break;
			case "Approval": BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_APPROVAL_XPATH);
				OrderingBOOnOrderQueuePage.orderDetailsPageApprovalSectionLabelValidation();
				break;
			case "Driver": BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DRIVERBTN_XPATH);
				OrderingBOOnOrderQueuePage.orderDetailsPageDriverSectionLabelValidation();
				break;
			case "Vehicle": BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_VEHICLEBTN_XPATH);
				OrderingBOOnOrderQueuePage.orderDetailsPageNewVehicleSpecificationSectionLabelValidation();
				break;
			case "DIO": BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DIOBTN_XPATH);
				OrderingBOOnOrderQueuePage.orderDetailsPageDealerInstalledOptionsSectionLabelValidation();
				break;
			case "Upfit": BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFITBTN_XPATH);
				OrderingBOOnOrderQueuePage.orderDetailsPageUpfitSectionLabelValidation();
				break;
			case "Billing": BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_BILLINGBTN_XPATH);
				OrderingBOOnOrderQueuePage.orderDetailsPageBillingSectionLabelValidation();
				break;
			case "Title & License": BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_TITLEREGISTRATIONBTN_XPATH);
				OrderingBOOnOrderQueuePage.orderDetailsPageTitleRegistrationAndLienholderSectionLabelValidation();
				break;
			case "Insurance": BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_INSURANCEBTN_XPATH);
				OrderingBOOnOrderQueuePage.orderDetailsPageInsuranceSectionLabelValidation();
				break;
			case "Dealer": BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DEALERBTN_XPATH);
				OrderingBOOnOrderQueuePage.orderDetailsPageDealerSectionLabelValidation();
				break;
			case "Custom Fields": BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CUSTOMFIELDSBTN_XPATH);
				OrderingBOOnOrderQueuePage.orderDetailsPageCustomFieldsSectionLabelValidation();
				break;
			default: throw new InvalidSwitchCaseException("Invalid BO Order detail section requested");
		}
	}
	
	public static void orderDetailsPageGeneralOrderSectionLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_GENERALORDER_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_GENERALORDER_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_GENERALORDER_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_ADDSTATUS_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DELETESTATUS_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_USEDUNITINFO_CSS);
		new WebDriverWait(WebDriverAccess.getDriver(),10).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_GENERALORDER_CSS)));
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_GENERALORDER_CSS), "General Order", "General Order label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_ADDSTATUS_CSS), "Add Status", "Add Status label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DELETESTATUS_CSS), "Delete Status", "Delete Status label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_USEDUNITINFO_CSS), "Used Unit Information", "Used Unit Information label did not match with the expected string");		
	}
	
	public static void orderDetailsPageApprovalSectionLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_APPROVALTITLE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_APPROVALTITLE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_APPROVALTITLE_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_APPROVALTITLE_CSS), "Approval", "Approval label did not match with the expected string");		
	}

	public static void orderDetailsPageDriverSectionLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DRIVERTITLE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DRIVERTITLE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DRIVERTITLE_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_GARAGINGADDRESSTITLE_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DELIVERYADDRESSTITLE_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DRIVERTITLE_CSS), "Driver", "Driver label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_GARAGINGADDRESSTITLE_CSS), "Garaging Address", "Garaging Address label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DELIVERYADDRESSTITLE_CSS), "Delivery Address", "Delivery Address label did not match with the expected string");		
	}

	public static void orderDetailsPageNewVehicleSpecificationSectionLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_VEHICLETITLE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_VEHICLETITLE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_VEHICLETITLE_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_VEHICLETITLE_CSS), "Vehicle", "Vehicle label did not match with the expected string");		
	}

	public static void orderDetailsPageDealerInstalledOptionsSectionLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DIOTITLE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DIOTITLE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DIOTITLE_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DIOTITLE_CSS), "DIO", "DIO label did not match with the expected string");		
	}

	public static void orderDetailsPageUpfitSectionLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFITTITLE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFITTITLE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFITTITLE_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFITTITLE_CSS), "Upfit", "Upfit label did not match with the expected string");		
	}

	public static void orderDetailsPageBillingSectionLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_BILLINGTITLE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_BILLINGTITLE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_BILLINGTITLE_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DPITITLE_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_BILLINGTITLE_CSS), "Billing", "Garaging Address label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DPITITLE_CSS), "Driver Payment Information", "Driver Payment Information label did not match with the expected string");		
	}
	
	public static void orderDetailsPageTitleRegistrationAndLienholderSectionLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_TITLEANDREGISTRATIONTITLE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_TITLEANDREGISTRATIONTITLE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_TITLEANDREGISTRATIONTITLE_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_TITLEADDRESSTITLE_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_REGISTRATIONADDRESSTITLE_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_TITLEANDREGISTRATIONTITLE_CSS), "Title & License", "Title & License label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_TITLEADDRESSTITLE_CSS), "Title Information", "Title Address label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_REGISTRATIONADDRESSTITLE_CSS), "Registration Information", "Registration Address label did not match with the expected string");
	}
	
	public static void orderDetailsPageInsuranceSectionLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_INSURANCETITLE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_INSURANCETITLE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_INSURANCETITLE_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_INSURANCETITLE_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_INSURANCETITLE_CSS), "Insurance", "Insurance label did not match with the expected string");		
	}
	
	public static void orderDetailsPageDealerSectionLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DEALERTITLE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DEALERTITLE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DEALERTITLE_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DELIVERINGDEALERTITLE_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_ORDERINGGDEALERTITLE_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DEALERTITLE_CSS), "Dealer", "Dealer label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DELIVERINGDEALERTITLE_CSS), "Delivering Dealer", "Delivering Dealer label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_ORDERINGGDEALERTITLE_CSS), "Ordering Dealer", "Ordering Dealer label did not match with the expected string");		
	}
	
	public static void orderDetailsPageCustomFieldsSectionLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CUSTOMFIELDSTITLE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CUSTOMFIELDSTITLE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CUSTOMFIELDSTITLE_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_ORDERINGGDEALERTITLE_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CUSTOMFIELDSTITLE_CSS), "Custom Fields", "Custom Fields label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_CUSTOMDRIVERFIELDSTITLE_CSS), "Custom Driver Fields", "Custom Driver Fields label did not match with the expected string");		
	}
	
	/**
	 * This method converts csv file data into map data.
	 * @lastModifiedBy shisingh 
	 * @param fileNameStartsWith
	 * @throws IOException
	 */
	public static void setCSVToMap(String fileNameStartsWith) throws IOException {
		ExcelUtil.setCSVToMap("\\target\\"+ fileNameStartsWith+".csv");
 	}
	
	/**
	 * This method compares the ui column values with cs column values in the given sorting order.
	 * @lastModifiedBy shisingh
	 * @param columnDataLabel
	 * @param order
	 * @param queue
	 * @throws Exception
	 */
	public static void compareOrderOfColumn(String columnDataLabel, String order, String queue) throws Exception {
		List<String> queueColumnDataList = getColumnTableData(columnDataLabel, queue).stream().map( e -> {return e.getText().trim();} ).collect(Collectors.toCollection(ArrayList::new));
		String columnHeading = getColumnTableHeading(columnDataLabel,queue).getText().replaceAll("\\n", " ");
		List<String> csvQueueColumnDataList = ExcelUtil.getCSVMapData().get(columnHeading);
		if(order.equals("Descending")) {
			csvQueueColumnDataList = csvQueueColumnDataList.stream().sorted(Comparator.reverseOrder()).map(e->e.replace("'", "")).collect(Collectors.toList());
			csvQueueColumnDataList = csvQueueColumnDataList.subList(0, queueColumnDataList.size() );
		} else {
			csvQueueColumnDataList = csvQueueColumnDataList.stream().sorted().map(e->e.replace("'", "")).collect(Collectors.toList());
			csvQueueColumnDataList = csvQueueColumnDataList.subList(0, queueColumnDataList.size() );
		}
		System.out.println("Column heading: " + columnHeading);
		System.out.println("CSV column value: " + csvQueueColumnDataList);
		System.out.println("UI column value: " + queueColumnDataList);
		Assert.assertEquals(queueColumnDataList, csvQueueColumnDataList, columnHeading + " column values are not in sorted order");
	}
	
	/**
	 * This method compares the ui column values with cs column values in the given sorting order.
	 * @lastModifiedBy Akshay Kandkonde
	 * @param columnDataLabel
	 * @param order
	 * @param queue
	 * @throws Exception
	 */
	public static void compareOrderOfColumns(String columnDataLabel, String order, String queue) throws Exception {
		List<String> queueColumnDataList = getColumnTableData(columnDataLabel, queue).stream().map( e -> {return e.getText().trim();} ).collect(Collectors.toCollection(ArrayList::new));
		List<String> newQueueColumnDataList = new ArrayList<String>();
		for(String ele : queueColumnDataList) {
			if(!(ele.equals("")))
				newQueueColumnDataList.add(ele);
		}
		String columnHeading = getColumnTableHeading(columnDataLabel,queue).getText().replaceAll("\\n", " ");
		List<String> csvQueueColumnDataList = ExcelUtil.getCSVMapData().get(columnHeading);
		List<String> newCsvQueueColumnDataList = new ArrayList<String>();
		for(String ele : csvQueueColumnDataList) {
			if(!(ele.equals("")))
				newCsvQueueColumnDataList.add(ele);
		}
		if(order.equals("Descending")) {
			newCsvQueueColumnDataList = newCsvQueueColumnDataList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
			newCsvQueueColumnDataList = newCsvQueueColumnDataList.subList(0, newQueueColumnDataList.size() );
		} else {
			newCsvQueueColumnDataList = newCsvQueueColumnDataList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
			newCsvQueueColumnDataList = newCsvQueueColumnDataList.subList(0, newQueueColumnDataList.size() );
			Collections.reverse(newCsvQueueColumnDataList);
		}
		System.out.println("Column heading: " + columnHeading);
		System.out.println("CSV column value: " + newCsvQueueColumnDataList);
		System.out.println("UI column value: " + newQueueColumnDataList);
		Assert.assertEquals(newQueueColumnDataList, newCsvQueueColumnDataList, columnHeading + " column values are not in sorted order");
	}
	
	/** 
	* This method column table data. 
	* @lastModifiedBy dpatil
	* @throws Exception 
	*/	
	public static List<WebElement> getColumnTableData(String columnName , String queue) throws Exception {
	List<WebElement> columnTableListWebElement = null;
		if(queue.equalsIgnoreCase("OnOrderQueue")){
			switch(columnName) {
				case "Log": columnTableListWebElement = BrowserAction.getElements(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_LOG_COLUMN_TABLE_DATA_XPATH);
					 break;
				case "Audit Insert Time": columnTableListWebElement = BrowserAction.getElements(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_AUDIT_INSERT_TIME_COLUMN_TABLE_DATA_XPATH);
					 break;		
				default: throw new InvalidSwitchCaseException(columnName + " is a invalid column");		
			}
		}		
		if(queue.equalsIgnoreCase("MyOrdersQueue")) {
		    switch(columnName) {
				case "Log": columnTableListWebElement = BrowserAction.getElements(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDERID_COLUMN_TABLE_DATA_XPATH);
					break;
				case "Client": columnTableListWebElement = BrowserAction.getElements(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CLIENTNUMBER_COLUMN_TABLE_DATA_XPATH);
				    break;
				case "Manufacturer": columnTableListWebElement = BrowserAction.getElements(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDERTYPE_COLUMN_TABLE_DATA_XPATH);
				   break;
				default: throw new InvalidSwitchCaseException(columnName + " is a invalid column");		
			}
		}
		if(queue.equalsIgnoreCase("WIPOrdersQueue")) {
		    switch(columnName) {
				case "Order ID": columnTableListWebElement = BrowserAction.getElements(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDERID_COLUMN_TABLE_DATA_XPATH);
					break;
				case "Client": columnTableListWebElement = BrowserAction.getElements(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CLIENTNUMBER_COLUMN_TABLE_DATA_XPATH);
				    break;
				case "Order Type": columnTableListWebElement = BrowserAction.getElements(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDERTYPE_COLUMN_TABLE_DATA_XPATH);
				   break;
				default: throw new InvalidSwitchCaseException(columnName + " is a invalid column");		
		    }
		}
		if(queue.equalsIgnoreCase("FleetSpec")) {
			switch(columnName) {
				case "Last Updated Date": 
					columnTableListWebElement = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_CONFIG_DATE_COLUMN_TABLE_DATA_XPATH);
					break;
				case "Fleet Specs Id": 
				case "Price & Config Id":
					columnTableListWebElement = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_CONFIG_ID_COLUMN_TABLE_DATA_XPATH);
					break;
				default: throw new InvalidSwitchCaseException(columnName + " is a invalid column");	
			}
		}
		if(queue.equalsIgnoreCase("AcknowledgmentQueue")||queue.equalsIgnoreCase("BillingQueue")||queue.equalsIgnoreCase("CreditQueue")||queue.equalsIgnoreCase("DealerQueue")||queue.equalsIgnoreCase("TitleRegQueue")||queue.equalsIgnoreCase("UpfitQueue")||queue.equalsIgnoreCase("InsuranceQueue")||queue.equalsIgnoreCase("DIOQueue")) {
			switch(columnName) {
				case "Log": 
					columnTableListWebElement = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_LOG_COLUMN_TABLE_DATA_XPATH);
					break;
				case "Client":
					columnTableListWebElement = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLIENT_COLUMN_TABLE_DATA_XPATH);
					break;
				case "Corp": 
					columnTableListWebElement = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CORP_COLUMN_TABLE_DATA_XPATH);
					break;
				case "Unit":
					columnTableListWebElement = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UNIT_COLUMN_TABLE_DATA_XPATH);
					break;
				case "Factory Order Date":
					columnTableListWebElement = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_FACTORY_ORDER_DATE_COLUMN_TABLE_DATA_XPATH);
					break;	
				default: throw new InvalidSwitchCaseException(columnName + " is a invalid column");	
			}
		}
		if(queue.equalsIgnoreCase("UpfitSpecQueue")) {
			switch(columnName) {
				case "UpfitSpecID": 
					columnTableListWebElement = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFITSPEC_ID_COLUMN_TABLE_DATA_XPATH);
					break;
				case "CreatedBy": 
					columnTableListWebElement = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CREATED_BY_COLUMN_TABLE_DATA_XPATH);
					break;
				default: throw new InvalidSwitchCaseException(columnName + " is a invalid column");	
			}
		}
		if(queue.equalsIgnoreCase("QuoteQueue")) {
			switch(columnName) {
				case "QuoteID": 
					columnTableListWebElement = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_QUOTE_ID_COLUMN_TABLE_DATA_XPATH);
					break;
				case "CreatedBy": 
					columnTableListWebElement = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CREATED_BY_COLUMN_TABLE_DATA_XPATH);
					break;
				default: throw new InvalidSwitchCaseException(columnName + " is a invalid column");	
			}
		}
		if(queue.equalsIgnoreCase("ChangeHistory")) {
			switch(columnName) {
				case "Business Obj": 
					columnTableListWebElement = BrowserAction.getElements(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_BUSINESS_OBJECT_COLUMN_LIST_XPATH);
					break;
				case "Updated In": 
					columnTableListWebElement = BrowserAction.getElements(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_UPDATEDIN_COLUMN_LIST_XPATH);
					break;
				default: throw new Exception(columnName + " is a invalid column");	
			}
		}
		if(queue.equalsIgnoreCase("CustomerDeliveringDealerAssignmentRulesQueue")) {
			switch(columnName) {
				case "Corp": 
					columnTableListWebElement = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CORP_CDDRTABLE_DATA_COLUMN_HEADER_XPATH);
					break;
				case "Fleet Number": 
					columnTableListWebElement = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_FLEET_ID_CDDRTABLE_DATA_COLUMN_HEADER_XPATH);
					break;
				default: throw new Exception(columnName + " is a invalid column");	
			}
		}
		return columnTableListWebElement;
	}
	
	/** 
	 * This method gets queue header headings
	 * @lastModifiedBy dpatil///////////
	 * @param columnHeading
	 * @param queue
	 * @return
	 * @throws Exception
	 */
	public static WebElement getColumnTableHeading(String columnHeading, String queue) throws Exception {
		WebElement columnTableHeadingWebElement=null;
		switch(queue) {
			case "OnOrderQueue":
				switch(columnHeading) {
					case "Log": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_LOG_COLUMN_TABLE_HEADING_XPATH);
						break;
					case "Audit Insert Time": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_AUDIT_INSERT_TIME_COLUMN_TABLE_HEADING_XPATH);
						break;
					default: throw new InvalidSwitchCaseException(columnHeading + " is a invalid on order column");		
				}
				break;
			case "MyOrdersQueue":
				switch(columnHeading) {
				 	case "Log": columnTableHeadingWebElement = BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_LOGNUMBERR_COLUMN_TABLE_HEADING_XPATH);
				 		break;
				 	case "Client Number": columnTableHeadingWebElement = BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_CLIENTNUMBER_COLUMN_TABLE_HEADING_XPATH);
				 		break;
				 	case "Manufacturer": columnTableHeadingWebElement = BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_MANUFACTURER_COLUMN_TABLE_HEADING_XPATH);
				 		break;
				 	default: throw new InvalidSwitchCaseException(columnHeading + " is a invalid my order queue column");
				}
				break;
			case "WIPOrdersQueue":
				switch(columnHeading) {
					case "Order ID": columnTableHeadingWebElement = BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDERID_COLUMN_TABLE_HEADING_XPATH);
						break;
					case "Client": columnTableHeadingWebElement = BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CLIENTNUMBER_COLUMN_TABLE_HEADING_XPATH);
				    	break;
					case "Order Type": columnTableHeadingWebElement = BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDERTYPE_COLUMN_TABLE_HEADING_XPATH);
				    	break;
					default: throw new InvalidSwitchCaseException(columnHeading + " is a invalid work in progress column");
				}
				break;
			case "FleetSpec":
				switch (columnHeading) {
					case "Last Updated Date": columnTableHeadingWebElement = BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_CONFIG_DATE_COLUMN_TABLE_HEADING_XPATH);
						break;
					case "Fleet Specs Id": 
					case "Price & Config Id":
						columnTableHeadingWebElement = WebDriverAccess.getElement(By.xpath(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_FLEET_SPEC_ID_COLUMN_HEADING_XPATH.getValue().replace("columnHeading", columnHeading)));
						break;
					default: throw new Exception(columnHeading + " is a invalid fleet spec column");
				}
				break;
			case "BillingQueue":
			case "AcknowledgmentQueue":
			case "CreditQueue":
			case "DealerQueue":
			case "TitleRegQueue":
			case "UpfitQueue":
			case "InsuranceQueue":
			case "DIOQueue":
				switch (columnHeading) {
					case "Log": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOBillingQueuePageEnum.ORDERING_BO_BILLING_TABLE_LOG_HEADER_XPATH);
						break;
					case "Client": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOBillingQueuePageEnum.ORDERING_BO_BILLING_TABLE_CLIENT_HEADER_XPATH);
						break;
					case "Corp": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CORP_COLUMN_HEADER_XPATH);
						break;
					case "Unit": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOBillingQueuePageEnum.ORDERING_BO_BILLING_TABLE_UNIT_HEADER_XPATH);
						break;
					case "Factory Order Date": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOBillingQueuePageEnum.ORDERING_BO_BILLING_TABLE_FACTORY_ORDER_DATE_HEADER_XPATH);
						break;
					default: throw new Exception(columnHeading + " is a invalid" +queue+ "queue column");
				}
				break;
			case "UpfitSpecQueue":
				switch (columnHeading) {
					case "UpfitSpecID": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFITSPEC_ID_COLUMN_HEADER_XPATH);
						break;
					case "CreatedBy": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CREATED_BY_COLUMN_HEADER_XPATH);
						break;
					default: throw new Exception(columnHeading + " is a invalid UpfitSpec queue column");
				}
				break;
			case "QuoteQueue":
				switch (columnHeading) {
					case "QuoteID": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_QUOTE_ID_COLUMN_HEADER_XPATH);
						break;
					case "CreatedBy": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CREATED_BY_COLUMN_HEADER_XPATH);
						break;
					default: throw new Exception(columnHeading + " is a invalid Quote queue column");
				}
				break;
			case "ChangeHistory":
				switch (columnHeading) {
					case "Business Obj": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_PAGE_BUSINESS_OBJ_HEADING_XPATH);
						break;
					case "Updated In": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_PAGE_UPDATED_IN_HEADING_XPATH);
						break;
					default: throw new Exception(columnHeading + " is a invalid Quote queue column");
				}
				break;
			case "CustomerDeliveringDealerAssignmentRulesQueue":
				switch (columnHeading) {
					case "Corp": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CORP_CDDR_COLUMN_HEADER_XPATH);
						break;
					case "Fleet Number": columnTableHeadingWebElement = BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_FLEET_ID_CDDR_COLUMN_HEADER_XPATH);
						break;
					default: throw new Exception(columnHeading + " is a invalid" +queue+ "queue column");
			    }
				break;	
			default: throw new InvalidSwitchCaseException("Invalid section name entered");				
		}
		return columnTableHeadingWebElement;	
	}
	
	/** 
	 * This method clicks on queue header headings
	 * @lastModifiedBy shisingh
	 * @param columnHeading
	 * @param queue
	 * @throws Exception
	 */
	public static void clickColumnTableHeading(String columnHeading, String queue) throws Exception {
		try{
			getColumnTableHeading(columnHeading,queue).click();
			OrderingCommonPage.checkGlobalSpinnerPopUp();
		} catch(ElementNotInteractableException e) {
			WebElement scrollArea = BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DATA_TABLE_SCROLL_BODY_CSS);
			((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollLeft = arguments[0].offsetWidth", scrollArea);
			getColumnTableHeading(columnHeading,queue).click();
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			scrollArea = BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DATA_TABLE_SCROLL_BODY_CSS);
			((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollLeft = arguments[0].offsetWidth", scrollArea);
		}
	}
	
	public static void main(String[] args) throws Exception {
		ExcelUtil.setCSVToMap("\\target\\Reg_OOQ_On Order-queue.csv");
		List<String> csvQueueColumnDataList = ExcelUtil.getCSVMapData().get("Audit Insert Time");
		csvQueueColumnDataList = csvQueueColumnDataList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		csvQueueColumnDataList = csvQueueColumnDataList.subList(0, 100);
		System.out.println(csvQueueColumnDataList);
	}
	
	/**
	 * This method approved the order
	 * @lastModifiedBy djawale
	 * @param className
	 * @throws Exception
	 */
	public static void approveOrder() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Approval");
		OrderingBOOrdMaintPage.clickApproveButtonVehicle();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method clicks on all headers on On Order Page and check all fields in sections are read only
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickOnAllHeaders() throws Throwable {
		OrderingBOOnOrderQueuePage.validateAllFieldsInGeneralOrderSectionAreReadOnly();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Approval");
		OrderingBOOnOrderQueuePage.validateAllFieldsInApprovalSectionAreReadOnly();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Driver");
		OrderingBOOnOrderQueuePage.validateAllFieldsInDriverSectioneAreReadOnly();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Vehicle");
		OrderingBOOnOrderQueuePage.validateAllFieldsInNewVehicleSpecificationSectionAreReadOnly();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("DIO");
		OrderingBOOnOrderQueuePage.validateAllFieldsInDealerInstalledOptionSectioneAreReadOnly();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Upfit");
		OrderingBOOnOrderQueuePage.validateAllFieldsInUpfitSectioneAreReadOnly();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Billing");
		OrderingBOOnOrderQueuePage.validateAllFieldsInBillingSectioneAreReadOnly();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Title & License");
		OrderingBOOnOrderQueuePage.validateAllFieldsInTitleLicenseSectioneAreReadOnly();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Insurance");
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Dealer");
		OrderingBOOnOrderQueuePage.validateAllFieldsInDealerSectioneAreReadOnly();
	}
	
	/**
	 * This method validates that all the fields in General Order section are read only for logged in user
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateAllFieldsInGeneralOrderSectionAreReadOnly() throws Throwable {
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_MFR_DAN_TEXT_FIELD_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_VIN_CSS);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UNIT_CSS);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_ORDER_TYPE_CSS);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DOE_INDICATOR_TOGGLE_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_RENTAL_INDICATOR_TOGGLE_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_VEHICLE_USAGE_DROP_DOWN_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_POOL_TYPE_DROP_DOWN_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_USED_DROP_DOWN_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_FLEET_ID);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_UNIT_ID);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_COMPETITOR_TO_NOTIFY_DROP_DOWN_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_WHO_TO_SELL_DROP_DOWN_NAME);
	}
	
	/**
	 * This method validates that all the fields in Approval section are read only for logged in user
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateAllFieldsInApprovalSectionAreReadOnly() throws Throwable {
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_APPROVAL_CREDIT_ERROR_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_INSURANCE_ERROR_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BILLING_ERROR_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_ERROR_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_INSTALL_ERROR_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TITLE_ERROR_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_ERROR_XPATH);
	}
	
	/**
	 * This method validates that all the fields in Driver section are read only for logged in user
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateAllFieldsInDriverSectioneAreReadOnly() throws Throwable {
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_FNAME_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_MNAME_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_LNAME_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_EMPID_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_ADDRESS_ONE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_ADDRESS_TWO_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_CITY_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_STATE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_COUNTRY_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_ZIPCODE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_EMAIL_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_PHONE_NAME);
	}
	
	/**
	 * This method validates that all the fields in New Vehicle Specification section are read only for logged in user
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateAllFieldsInNewVehicleSpecificationSectionAreReadOnly() throws Throwable {
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_YEAR_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_MANUFACTURER_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_MAKE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_MODEL_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_TRIM_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_ACODE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_WHEELBASE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_CUST_FINFAN_NAME);
	}
	
	/**
	 * This method validates that all the fields in Dealer Installed Specification section are read only for logged in user
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateAllFieldsInDealerInstalledOptionSectioneAreReadOnly() throws Throwable {
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_DIO_SEARCH_ID);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_OPTION_CODE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_DPO_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_DESCRIPTION_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_QUANTITY_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_OPTION_PRICE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_ADHOC_OPTION_XPATH);
	}
	
	/**
	 * This method validates that all the fields in Upfit section are read only for logged in user
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateAllFieldsInUpfitSectioneAreReadOnly() throws Throwable {
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_DROP_SHIP_SEARCH_ID);
	}
	
	/**
	 * This method validates that all the fields in Billing section are read only for logged in user
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateAllFieldsInBillingSectioneAreReadOnly() throws Throwable {
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BILLING_ASSET_ID_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BILLING_CLIENT_PO_NUMBER_NAME);
	}
	
	/**
	 * This method validates that all the fields in Title & License section are read only for logged in user
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateAllFieldsInTitleLicenseSectioneAreReadOnly() throws Throwable {
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TITLE_LICENSE_PLATE_TYPE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TITLE_LICENSE_WHO_TO_TITLE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_OWNER_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_ADDRESS_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_CITY_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_ZIP_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_STATE_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_FEDERALID_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_TAXEXEMPTNO_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_OWNER_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_ADDRESS_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_CITY_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_ZIP_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_STATE_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_FEDERALID_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_STATEID_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_OWNER_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_ADDRESS_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_CITY_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_ZIP_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_STATE_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_FEDERALID_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_STATEID_NAME);
	}
	
	/**
	 * This method validates that all the fields in Dealer section are read only for logged in user
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateAllFieldsInDealerSectioneAreReadOnly() throws Throwable {
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_DELIVERING_DEALER_CODE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_DELIVERING_DEALER_CDFEE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_DELIVERING_DEALER_NAME_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_DELIVERING_DEALER_CONTACT_NAME_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_DELIVERING_DEALER_PHONE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_DELIVERING_DEALER_EMAIL_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_ORDERING_DEALER_CODE_NAME);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_ORDERING_DEALER_NAME_NAME);
	}
	
	/**
	 * This method validates that user can scroll to DIO Section
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateUserCanScrollToDIOSection() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_DIO_SECTION_ID);
		WebElement dioSection = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_DIO_SECTION_ID);
		((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", dioSection);
	}
	
	/**
	 * This method validates error message when NVAC User/Truck Engineer/Internal User try to add DIO after order is MSO Received
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateDIOErrorMessage() throws Throwable {
		OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("DIO");
		OrderingBODealerInstalledOptionsPage.clickDIOSearch();
		OrderingBODealerInstalledOptionsPage.validateDIOWarningMessage();
	}
	
	/**
	 * This method returns number of DIOs in current order
	 * @lastModifiedBy djawale
	 * @return number of DIOs in current order
	 * @throws Throwable
	 */
	public static int getNumberOfDIO() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DIO_ROW_CSS);
		numberOfDIOs=BrowserAction.getElements(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DIO_ROW_CSS).size();
		return numberOfDIOs;
	}
	
	/**
	 * This method adds DIO
	 * @lastModifiedBy djawale
	 * @throws Throwable
	 */
	public static void addDIO() throws Throwable {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("DIO");
		OrderingBOOrdMaintPage.clickOnDIOAdd();
		OrderingBOOnOrderQueuePage.getNumberOfDIO();
		OrderingBODealerInstalledOptionsPage.addDIOFromBO(numberOfDIOs);
	}
	
	/**
	 * This method edits DIO
	 * @lastModifiedBy djawale
	 * @throws Throwable
	 */
	public static void editDIO() throws Throwable {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("DIO");
		OrderingBOOnOrderQueuePage.getNumberOfDIO();
		OrderingBODealerInstalledOptionsPage.editDIOFromBO(numberOfDIOs);
	}
	
	/**
	 * This method delete DIO
	 * @lastModifiedBy djawale
	 * @throws Throwable
	 */
	public static void deleteDIO() throws Throwable {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("DIO");
		OrderingBOOnOrderQueuePage.getNumberOfDIO();
		OrderingBODealerInstalledOptionsPage.deleteDIOFromBO(numberOfDIOs);
	}
	
	/**
	 * This method validate the order status is as expected
	 * @param expectedOrderStatus
	 * @throws Exception
	 * @lastModifiedBy djawale
	 */
	public static void verifyChangedOrderStatus(String expectedOrderStatus) throws Throwable {
		CommonPage.waitForElementToLoad(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_STATUS_ACTUAL_TEXT_XPATH, CommonPage.getTestData("WaitTime"));
		String actualOrderStatus = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_STATUS_ACTUAL_TEXT_XPATH).getText().trim().replaceAll("\\r\\n|\\r|\\n", "");
		Assert.assertEquals(actualOrderStatus, expectedOrderStatus, "Unable to match order status on Order maintenance screen.");
	}
	
	/**
	 * This method validate that the telematics Indicator is as expected and not editable
	 * @param expectedTeleamticsIndicatorStatus
	 * @throws Exception
	 * @lastModifiedBy djawale
	 */
	public static void validateTelematicIndicator(String expectedTeleamticsIndicatorStatus)throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_TELEMATICS_INDICATOR_XPATH);
		String actualTelematicsIndicatorStatus=BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_TELEMATICS_INDICATOR_XPATH).getText().trim().replaceAll("\\r\\n|\\r|\\n", "");
		Assert.assertEquals(actualTelematicsIndicatorStatus, expectedTeleamticsIndicatorStatus, "Unable to match Teleamtics Indicator status on Order maintenance screen.");
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_TELEMATICS_INDICATOR_XPATH);
	}

	/**
	 * This method change the order type in BO in general tab
	 * @param OrderType
	 * @throws Exception
	 * @lastModifiedBy skathule
	 */
	public static void changeOrderType(String orderType)throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_ORDER_TYPE_DROPDOWN_XPATH);
		BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_ORDER_TYPE_DROPDOWN_XPATH);
		System.out.println("Selected order type:: "+orderType);
		BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_ORDER_TYPE_DROPDOWN_XPATH, orderType);
		BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_ORDER_TYPE_DROPDOWN_XPATH);
	}	
}
