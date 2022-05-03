package com.element.fleet.ordering.page;

import static org.testng.Assert.assertEquals;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import com.element.fleet.ordering.enums.*;
import com.element.fleet.ordering.verification.BillingAndRegistrationTab;
import com.element.fleet.ordering.verification.ElementOrder;
import com.element.fleet.ordering.verification.BillingAndRegistrationTab.Registration;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.DbConnector;
import org.testng.Assert;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.NoIfElseBlockMatchedException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserAssert;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;

public class OrderingBOOrdMaintPage {
	private static String dioQuantity;
	private static String dioPrice;
	private static String newUnit;
	private static String newVIN;
	private static String dataId;
	private static String emailSubjectLine;
	private static String clientUnitNumber;
	private static String poNumber;
	private static String foNumber;
	private static String supplierEmail;
	private static String emailHeader;
	private static String supplierQuoteNumber;
	private static String supplierQuoteDate;
	private static ArrayList<String> actualEmails;
	private static List<String> expectedEmails;
	private static String logNumber;
	private static String attachmentName;
	private static String currentPOStatus;
	private static boolean flag;
	private static String doNotSendWarningMessage="PO Communication for this supplier is set to Do Not Send in upfitter preferences, do you want to send anyway?";
	private static String pdfWarningMessage="PO Communication for this supplier is set to PDF in upfitter preferences, are you sure you want to suppress this PO from being sent out in the future?";
	private static String preferenceDoNotExistWarningMessage="There are no upfitter preferences set up for this supplier, are you sure you want to suppress this PO from being sent out in the future?";
	private static String parentWindowHandle;

	public static void goToApprovalSection() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_APPROVALTAB_LINK);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_APPROVALTAB_LINK);
	}

	/**
	 * This method checks the error list and takes the appropriate action.
	 * @lastModifiedBy shisingh
	 * @throws Throwable
	 */
	public static void checkAndRemoveAllSoftApprovalErrorsIfPresent() throws Throwable {
		List<String> errorList = OrderingBOOrdMaintPage.getErrorList();
		if(!errorList.isEmpty()) {
			for(String softError: errorList) {
				OrderingBOOrdMaintPage.softErrors(softError);
			}
			OrderingBOQueuePage.gotoQueuesPage();
			OrderingBOQueuePage.queuePageLoaded();
			OrderingBOQueuePage.gotoQueue("On-Order");
			OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
			OrderingBOOrdMaintPage.clickOnSingleSearchResult("logNumber");
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			OrderingCommonPage.checkAlertPopUp();
			OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
			if(errorList.contains("Billing")) {
				OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Billing");
				OrderingBOOrdMaintPage.selectLeaseTerm();
				OrderingBOOrdMaintPage.clickSaveOrder();
				OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
			}
			if(errorList.contains("Title")) {
				OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Title, Registration & Lienholder");
				OrderingBOOrdMaintPage.selectPlateType();
				OrderingBOOrdMaintPage.selectWhoToTitle();
				OrderingBOOrdMaintPage.clickSaveOrder();
				OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
			}
			if(errorList.contains("Upfit")) {
				OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Upfit");
				OrderingBOOrdMaintPage.addSupplierToUpfit();
				OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
			}			
			OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Approval");
			OrderingBOOrdMaintPage.validateSoftErrorRemoved();			
		}
	}
	
	/**
	 * This method adds Supplier to all the added upfits if not available.
	 * It clicks on each upfit enters Supplier dealer code and clicks on first searched element.
	 * Then enters estimated lead time and work type.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void addSupplierToUpfit() throws Throwable { 
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFITS_CONTAINER_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFITS_CONTAINER_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFITS_CONTAINER_ID);
		int totalUpfits = BrowserAction.getElements(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFITS_LIST_CSS).size();
		JavascriptExecutor jsExe = (JavascriptExecutor)WebDriverAccess.getDriver();
		for(int i = 0; i<totalUpfits; ++i) {
			OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Upfit");
			jsExe.executeScript("arguments[0].click();", BrowserAction.getElements(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFITS_LIST_CSS).get(i));
			OrderingBOOrdMaintPage.moveToUpfitWindow();
			OrderingBOQueuePage.waitUntilCompletePageLoad();
			System.out.println("Supplier search code: " + CommonPage.getTestData("UpfitSupplierDealerNumber"));
			BrowserAction.enterFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_LINE_ITEM_SUPPLIER_CSS, CommonPage.getTestData("UpfitSupplierDealerNumber"));
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFIT_SUPPLIER_SEARCHED_RESULT_XPATH);
			BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFIT_SUPPLIER_SEARCHED_RESULT_XPATH);
			OrderingBOQueuePage.waitUntilCompletePageLoad();
			System.out.println("Estimated lead weeks: " + CommonPage.getTestData("EstimatedLeadWeeks"));
			CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFIT_ESTIMEATED_LEAD_TIME_WEEK_POS_XPATH, CommonPage.getTestData("EstimatedLeadWeeks"));
			OrderingBOQueuePage.waitUntilCompletePageLoad();
			System.out.println("Work type: " + CommonPage.getTestData("WorkType"));
			CommonPage.selectDropdownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFIT_WORK_TYPE_POS_XPATH, CommonPage.getTestData("WorkType"));
			OrderingBOQueuePage.waitUntilCompletePageLoad();
			OrderingBOOrdMaintPage.clickSave();
			OrderingBOOrdMaintPage.navigateToParentWindow();
			WebDriverAccess.getDriver().navigate().refresh();
			OrderingCommonPage.checkAlertPopUp();
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
		}
	}

	/**
	 * This method checks the error list resolves all error and denies the credit error.
	 * @lastModifiedBy shisingh
	 * @throws Throwable
	 */
	public static void checkAndRemoveAllSoftApprovalErrorsIfPresentAndDenyCreditError() throws Throwable {
		List<String> errorList = OrderingBOOrdMaintPage.getErrorList();
		if(!(errorList.size()==0)) {
			for(String softError: errorList) {
				if(softError.equals("Credit")) {
					softError = "Credit Deny";
				}
				OrderingBOOrdMaintPage.softErrors(softError);
			}
			OrderingBOQueuePage.gotoQueuesPage();
			OrderingBOQueuePage.queuePageLoaded();
			OrderingBOQueuePage.gotoQueue("On-Order");
			OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
			OrderingBOOrdMaintPage.clickOnSingleSearchResult("logNumber");
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			OrderingCommonPage.checkAlertPopUp();
			OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
			OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Approval");
			OrderingBOOrdMaintPage.validateSoftErrorRemoved();	
			OrderingBOOrdMaintPage.verifyCreditErrorDenied();
		}
	}
	
	/**
	 * This method checks if all the error are solved or not.
	 * @lastModifiedBy shisingh
	 * @throws Throwable
	 */
	public static void validateSoftErrorRemoved() throws Throwable {
		List<String> errorList = OrderingBOOrdMaintPage.getErrorList();
		if(!(errorList.size()==0)) {
			throw new OrderingErrorOccured("Soft error not resolved. Errors: " + errorList);
		}
	}
	
	/**
	 * This bring the error list from the approval section
	 * @lastModifiedBy shisingh
	 * @return
	 * @throws Exception
	 */
	public static List<String> getErrorList() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Approval");
		List<String> errorList = new ArrayList<String>();
		List<WebElement> approvalSections = BrowserAction.getElements(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_APPROVALTAB_LIST_ERROR_CSS);
		String creditError = new Select(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_APPROVALTAB_CREDIT_ERROR_NAME)).getFirstSelectedOption().getText().trim();
		if(creditError.trim().equals("F - Credit Check Failed")) {
			errorList.add("Credit");
		}
		for(WebElement approvalSection:approvalSections) {
			if(Color.fromString(approvalSection.getCssValue("background-color")).asHex().equals("#d44b4b")) {
				errorList.add(approvalSection.findElement(By.xpath(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_APPROVALTAB_LIST_ERROR_LABEL_XPATH.getValue())).getText());
			}
		}
		System.out.println("Error list: " + errorList);
		return errorList;
	}
	
	/**
	 * This verifys that credit error is denied.
	 * @throws Exception 
	 * @lastModifiedBy shisingh
	 */
	public static void verifyCreditErrorDenied() throws Exception {
		String creditError = new Select(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_APPROVALTAB_CREDIT_ERROR_NAME)).getFirstSelectedOption().getText().trim();
		Assert.assertEquals(creditError.trim(), "D - Credit Denied", "Credit Error should be denied");
	}
	
	/**
	 * This method resolves the soft error
	 * @throws Exception
	 * @lastModifiedBy shisingh
	 */
	public static void softErrors(String errorName) throws Throwable {
		OrderingBOOrdMaintPage.goBackToQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		switch(errorName) {
			case "Credit":
				OrderingBOQueuePage.gotoQueue("Credit");
				OrderingBOOnOrderQueuePage.waitForCreditQueuePage();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.clickOnSingleSearchResult("checkbox");
				OrderingBOOrdMaintPage.approveSoftError();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.verifyNoOrderLogRowIsDisplayed();
				System.out.println("Credit soft error removed");
				break;
			case "Credit Deny":
				OrderingBOQueuePage.gotoQueue("Credit");
				OrderingBOOnOrderQueuePage.waitForCreditQueuePage();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.clickOnSingleSearchResult("checkbox");
				OrderingBOOrdMaintPage.denySoftError();
				OrderingBOOrdMaintPage.cancelDenySoftErrorPopUp();
				OrderingBOOrdMaintPage.denySoftError();
				OrderingBOOrdMaintPage.approveDenySoftErrorPopUp();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.verifyNoOrderLogRowIsDisplayed();
				System.out.println("Credit soft error denied");
				break;
			case "Insurance":
				OrderingBOQueuePage.gotoQueue("Insurance");
				OrderingBOOnOrderQueuePage.waitForInsuranceQueuePage();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.clickOnSingleSearchResult("checkbox");
				OrderingBOOrdMaintPage.releaseSoftError();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.verifyNoOrderLogRowIsDisplayed();
				System.out.println("Insurance soft error removed");
				break;
			case "Billing":
				OrderingBOQueuePage.gotoQueue("Billing");
				OrderingBOOnOrderQueuePage.waitForBillingQueuePage();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.clickOnSingleSearchResult("checkbox");
				OrderingBOOrdMaintPage.releaseSoftError();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.verifyNoOrderLogRowIsDisplayed();
				System.out.println("Billing soft error removed");
				break;
			case "DIO":
				OrderingBOQueuePage.gotoQueue("DIO");
				OrderingBOOnOrderQueuePage.waitForDIOQueuePage();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.clickOnSingleSearchResult("checkbox");
				OrderingBOOrdMaintPage.releaseSoftError();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.verifyNoOrderLogRowIsDisplayed();
				System.out.println("DIO soft error removed");
				break;
			case "Title":
				OrderingBOQueuePage.gotoQueue("Title & Reg");
				OrderingBOOnOrderQueuePage.waitForTitleAndRegQueuePage();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.clickOnSingleSearchResult("checkbox");
				OrderingBOOrdMaintPage.releaseSoftError();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.verifyNoOrderLogRowIsDisplayed();
				System.out.println("Title & Reg soft error removed");
				break;
			case "Upfit":
				OrderingBOQueuePage.gotoQueue("Upfit");
				OrderingBOOnOrderQueuePage.waitForUpfitQueuePage();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.clickOnSingleSearchResult("checkbox");
				OrderingBOOrdMaintPage.releaseSoftError();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.verifyNoOrderLogRowIsDisplayed();
				System.out.println("Upfit soft error removed");
				break;
			case "Dealer":
				OrderingBOQueuePage.gotoQueue("Dealer");
				OrderingBOOnOrderQueuePage.waitForDealerQueuePage();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.clickOnSingleSearchResult("actions");
				OrderingCommonPage.checkAlertPopUp();
				OrderingBOOrdMaintPage.manualDealerAssignment();
				OrderingBOOnOrderQueuePage.waitForDealerQueuePage();
				OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
				OrderingBOOrdMaintPage.verifyNoOrderLogRowIsDisplayed();
				System.out.println("Dealer soft error removed");
				break;
			default: throw new InvalidSwitchCaseException(errorName + ": Invalid soft error entered");
		}
	}
	
	/**
	 * This method clicks on the first row of the specified column.
	 * @lastModifiedBy shisingh
	 * @param section
	 * @throws Exception
	 */
	public static void clickOnSingleSearchResult(String section) throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH.getValue()), 1));
		List<WebElement> searchResultColumns = BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH).findElements(By.xpath(".//td"));
		if(searchResultColumns.size()==1) {
			throw new OrderingErrorOccured(searchResultColumns.get(0).getText().trim());
		}
		if(section.equals("checkbox")) {
			for(WebElement searchResultColumn:searchResultColumns) {
				if(searchResultColumn.getAttribute("class").equals("checkbox desc")) {
					new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(searchResultColumn.findElement(By.xpath(".//input"))));
					System.out.println("Clicked checkbox");
					searchResultColumn.click();
					break;
				}
			}
		} else if(section.equals("actions")) {
			for(WebElement searchResultColumn:searchResultColumns) {
				if((Objects.isNull(searchResultColumn.getAttribute("data-name"))?"Invalid section":searchResultColumn.getAttribute("data-name")).equals(section)) {
					if((CommonPage.getTestData("BODealerAssignment")==null)||CommonPage.getTestData("BODealerAssignment").equals("Manual")) {
						new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(searchResultColumn));
						System.out.println("Going for Manual dealer assignment");
						searchResultColumn.findElement(By.cssSelector("  a.manual")).click();
					} else {
						new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(searchResultColumn));
						System.out.println("Going for Auto dealer assignment");
						searchResultColumn.findElement(By.cssSelector("  a.auto")).click();
					}
					break;
				}
			}
		} else {
			for(WebElement searchResultColumn:searchResultColumns) {
				if(searchResultColumn.getAttribute("data-name").equals(section)) {
					new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(searchResultColumn));
					searchResultColumn.click();
					break;
				}
			}
		}
	}
	
	/**
	 * This method approves the soft error
	 * @lastModifiedBy shisingh
	 * @throws Throwable
	 */
	public static void approveSoftError() throws Throwable {		
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_APPROVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_APPROVE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_APPROVE_BUTTON_XPATH);
		BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_APPROVE_BUTTON_XPATH);
	}
	
	/**
	 * This method searches the log number and deny the soft error
	 * @lastModifiedBy vikumar
	 * @throws Throwable
	 */
	public static void denySoftError() throws Throwable {		
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DENY_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DENY_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DENY_BUTTON_XPATH);
		BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DENY_BUTTON_XPATH);
	}
	
	/**
	 * This method approves deny soft error pop up
	 * @lastModifiedBy shisingh
	 * @throws Throwable
	 */
	public static void approveDenySoftErrorPopUp() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DENY_ACCEPT_OPTION_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DENY_ACCEPT_OPTION_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DENY_ACCEPT_OPTION_CSS);
		BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DENY_ACCEPT_OPTION_CSS);
	}

	/**
	 * This method cancel deny soft error pop up
	 * @lastModifiedBy shisingh
	 * @throws Throwable
	 */
	public static void cancelDenySoftErrorPopUp() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DENY_CANCEL_OPTION_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DENY_CANCEL_OPTION_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DENY_CANCEL_OPTION_CSS);
		BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DENY_CANCEL_OPTION_CSS);
	}

	/**
	 * This method searches the log number and deny the soft error
	 * @lastModifiedBy shisingh
	 * @throws Throwable
	 */
	public static void releaseSoftError() throws Throwable {		
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_RELEASE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_RELEASE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_RELEASE_BUTTON_XPATH);
		BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_RELEASE_BUTTON_XPATH);
		BrowserVerify.verifyElementDisabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_RELEASE_BUTTON_XPATH);
	}
	
	/**
	 * This method searches for the manual dealer and assigns that dealer to the log.
	 * @throws Throwable 
	 */
	public static void manualDealerAssignment() throws Throwable {
		OrderingBODealerQueuePage.waitForManualDealerAssignmentPage();		
		OrderingBODealerQueuePage.selectDeleveringAndOrderingDealer();
		OrderingBODealerQueuePage.closeManualDealerAssignmentPage();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method searches the log number and verify that no log is displayed
	 * @lastModifiedBy shisingh
	 * @throws Throwable
	 */
	public static void verifyNoOrderLogRowIsDisplayed() throws Throwable {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH.getValue()), 1));
		String rowValue = BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH).findElement(By.xpath(".//td")).getText();
		if(!rowValue.equals("No data available in table")) {
			throw new OrderingErrorOccured("Soft error not removed");
		}
	}
	
	public static void waitForOrdMaintPage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_ID);
	}

	/**
	 * This method clicks on approve button.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */	
	public static void clickApproveButtonVehicle() throws Exception	{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_VEHICLE_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_VEHICLE_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_VEHICLE_APPROVE_BUTTON_ID);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_VEHICLE_APPROVE_BUTTON_ID)));
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_VEHICLE_APPROVE_BUTTON_ID);
	}
	
	/**
	 * This method verifies that order is submitted successfully.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */	
	public static void verifyOrderSubmittedSuccessfully() throws Throwable	{
		String popUpMessage = new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 1)).get(0).getText().trim();
		String popUpBackgroundColour = Color.fromString(new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 1)).get(0).getCssValue("background-color")).asHex();
		if(popUpBackgroundColour.equals("#80bd00")||popUpBackgroundColour.equals("#74af39")||popUpBackgroundColour.equals("#82c341")||popUpBackgroundColour.equals("#4cb4e7")) {
			Assert.assertEquals(popUpMessage, "Order Approved Successfully", "Order approved pop up message is wrong");
			System.out.println("Success pop up: " + popUpMessage);
			PDFReporter.takeExtraScreenshot();
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 0));
		} else {
			System.err.println("Pop up message: " + WebDriverAccess.getDriver().findElement(By.cssSelector("div.noty_bar")).getText());
			System.err.println("Pop up colour: " + popUpBackgroundColour);
			throw new OrderingErrorOccured(WebDriverAccess.getDriver().findElement(By.cssSelector("div.noty_bar")).getText());
		}
	}
	
	/**
	 * This method verify weather Approve button is clicked or not.
	 * When Approve button is clicked a check image is displayed in the button.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyApproveButtonVehicleClicked() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_VEHICLE_APPROVE_BUTTON_AFTER_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_VEHICLE_APPROVE_BUTTON_AFTER_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_VEHICLE_APPROVE_BUTTON_AFTER_XPATH);
	}
	
	/**
	 * This method verify weather Approve button is disabled or not.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */	
	public static void verifyApproveButtonVehicleDisabled() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_VEHICLE_APPROVE_BUTTON_DISABLED_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_VEHICLE_APPROVE_BUTTON_DISABLED_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_VEHICLE_APPROVE_BUTTON_DISABLED_CSS);
	}	

	/**
	 * This method clicks on the save order button.
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickSaveOrder() throws Exception {
		OrderingBOOrdMaintPage.saveOrder();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoValidationError();
	}
	
	/**
	 * This method clicks on the save order button.
	 * @lastModifiedBy ssrivastava
	 * @throws Exception
	 */
	public static void saveOrderOnVehicleTab() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_VEHICLE_TAB_ORDER_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_VEHICLE_TAB_ORDER_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_VEHICLE_TAB_ORDER_ID);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_VEHICLE_TAB_ORDER_ID)));
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_VEHICLE_TAB_ORDER_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoValidationError();
	}

	public static void goToUpfitSection() throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ID);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITSECTION_ID);
	}

	/**
	 * This method clicks on Add PO button and shift control to upfit tab
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickAddPO() throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		Actions actions=new Actions(WebDriverAccess.getDriver());
 		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_ADDPO_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_ADDPO_ID);
		actions.moveToElement(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_ADDPO_ID)).click().build().perform();
		OrderingBOOrdMaintPage.moveToUpfitWindow();
		OrderingBOOrdMaintPage.waitUntilUpfitPageLoaded();
	}

	public static void goBackToQueuesPage() throws Throwable {
		try {
			BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_MENU_CLASS);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORDER_NOT_SAVED_WARNING_POPUP_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORDER_NOT_SAVED_WARNING_POPUP_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORDER_NOT_SAVED_WARNING_POPUP_XPATH);
			CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORDER_NOT_SAVED_WARNING_POPUP_ERROR_MESSAGE_XPATH), "You are about to leave this page. Your information will be lost.", "Leave page warning message did not match with the expected string");
			PDFReporter.takeExtraScreenshot();
			WebElement element = BrowserAccess.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORDER_NOT_SAVED_WARNING_POPUP_OK_BUTTON_XPATH);
			((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].click();", element);
		} catch (TimeoutException e) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLASS);
		} catch (WebDriverException e) {
			BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_DETAILS_CLOSE_BUTTON_CSS);
			try {
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_XPATH);
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_MESSAGE_XPATH), "You are about to leave this page. Your information will be lost.", "Leave page warning message did not match with the expected string");
				PDFReporter.takeExtraScreenshot();
				BrowserAction.click(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_YES_BUTTON_XPATH);			
			} catch (TimeoutException t) {
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_SIDE_MENU_ID);
				BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_MENU_CLASS);
			}			
		}
	}
	
	/**
	 * This method clicks on Acknowledge button
	 * @lastModifiedBy djawale
	 * Added explicit wait to Wait until Acknowledge button is click able also using action class as it is not clicking 
	 * @throws Exception
	 */
	public static void clickOnAcknowledgeButton() throws Exception {
		OrderingBOOrdMaintPage.updateOrderStatus();
	}

	/**
	 * This method will click on Driver tab in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToDriverTabInBO() throws Exception {
		OrderingBOOrdMaintPage.clickOnTabInBOUsingId(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_ID, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will edit all driver information in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void editDriverInfoInBO() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Driver");
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setEmployeeID(CommonPage.randomAlphaNumericString());
		System.out.println("Driver First Name: " + CommonPage.getTestData("DriverFirstName"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_FNAME_NAME, CommonPage.getTestData("DriverFirstName"));
		System.out.println("Driver Middle Name: " + CommonPage.getTestData("DriverMiddleName"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_MNAME_NAME, CommonPage.getTestData("DriverMiddleName"));
		System.out.println("Driver Last Name: " + CommonPage.getTestData("DriverLastName"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_LNAME_NAME, CommonPage.getTestData("DriverLastName"));
		System.out.println("Driver Employee ID: " + CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().getEmployeeID());
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_EMPID_NAME, CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().getEmployeeID());
		System.out.println("Driver Address Line 1: " + CommonPage.getTestData("DriverAddrOne"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_ADDRESS_ONE_NAME, CommonPage.getTestData("DriverAddrOne"));
		System.out.println("Driver Address Line 2: " + CommonPage.getTestData("DriverAddrTwo"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_ADDRESS_TWO_NAME, CommonPage.getTestData("DriverAddrTwo"));
		System.out.println("Driver City: " + CommonPage.getTestData("DriverCity"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_CITY_NAME, CommonPage.getTestData("DriverCity"));
		System.out.println("Driver State: " + CommonPage.getTestData("DriverState"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_STATE_NAME, CommonPage.getTestData("DriverState"));
		System.out.println("Driver County: " + CommonPage.getTestData("DriverCountry"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_COUNTRY_NAME, CommonPage.getTestData("DriverCountry"));
		System.out.println("Driver Zip Code: " + CommonPage.getTestData("DriverZipCode"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_ZIPCODE_NAME, CommonPage.getTestData("DriverZipCode"));
		System.out.println("Driver Email: " + CommonPage.getTestData("ClientContactEmail"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_EMAIL_NAME, CommonPage.getTestData("ClientContactEmail"));
		System.out.println("Driver Phone: " + CommonPage.getTestData("ClientContactPhone"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_PHONE_NAME, CommonPage.getTestData("ClientContactPhone"));
	}


	/**
	 * This method will verify all driver information in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyEditedDriverInfoInBO() throws Exception {
		OrderingBOOrdMaintPage.goToDriverTabInBO();
		OrderingBOOrdMaintPage.waitForDriverTabToLoad();
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_FNAME_NAME, CommonPage.getTestData("DriverFirstName"), "Driver First Name");
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_MNAME_NAME, CommonPage.getTestData("DriverMiddleName"), "Driver Middle name");
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_LNAME_NAME, CommonPage.getTestData("DriverLastName"), "Driver Last name");
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_EMPID_NAME, CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().getEmployeeID(), "Driver Emp Id");
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_ADDRESS_ONE_NAME, CommonPage.getTestData("DriverAddrOne"), "Driver address one");
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_ADDRESS_TWO_NAME, CommonPage.getTestData("DriverAddrTwo"), "Driver address two");
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_CITY_NAME, CommonPage.getTestData("DriverCity"), "Driver city");
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_STATE_NAME, CommonPage.getTestData("DriverState"), "Driver state");
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_COUNTRY_NAME, CommonPage.getTestData("DriverCountry"), "Driver country");
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_ZIPCODE_NAME, CommonPage.getTestData("DriverZipCode"), "Driver zip code");
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_EMAIL_NAME, CommonPage.getTestData("ClientContactEmail"), "Driver Email");
		verifyInputDateFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_PHONE_NAME,CommonPage.getTestData("ClientContactPhone"), "Driver Phone");
	}

	/**
	 * This method validates that order status is changed
	 * @param OrderStatus
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyChangedOrderStatus(String OrderStatus)throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_STATUS_ACTUAL_TEXT_XPATH, CommonPage.getTestData("WaitTime"));
		String actualOrderStatus = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_STATUS_ACTUAL_TEXT_XPATH).getText().trim();
		assertEquals(actualOrderStatus, OrderStatus, "Unable to match order status on Order maintenance screen.");
	}

	/**
	 * This method clicks on save button of PO page in new tab and verifiew pop up message as well
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void clickSave() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_SAVE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_SAVE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_SAVE_XPATH);
		BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_SAVE_XPATH);
		List<WebElement> popUpElement = new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 1));
        String alertPopUpBackgroundColour = Color.fromString(popUpElement.get(0).getCssValue("background-color")).asHex();
        String alertPopUpText = popUpElement.get(0).getText();
        if(alertPopUpBackgroundColour.equals("#82c341") && alertPopUpText.equals("Upfit PO was successfully updated")) {
            System.out.println("Success pop up: " + alertPopUpText);
        } else {
            System.out.println("Alert pop up background colour: " + alertPopUpBackgroundColour);
            System.out.println("Alert pop up text: " + alertPopUpText);
            throw new OrderingErrorOccured("Error pop up is not displayed");
        }
        PDFReporter.takeExtraScreenshot();
        OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
        OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method is used to enter New FO Number.
	 * Note: Only specific to Factory Order
	 * @lastModifiedBy shisingh
	 * @param newFONumber
	 * @throws Exception
	 */
	public static void enterNewFONumber(String newFONumber) throws Exception {
		System.out.println("New FO Number: " + newFONumber);
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_FO_NUMBER_NAME, newFONumber);
	}
	
	public static void enterNewFODate() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_FO_DATE_NAME);
		System.out.println("New Factory Order Date: " + CommonPage.getCurrentDataIn("MM/dd/yyyy"));
		BrowserAction.enterFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_FO_DATE_NAME, CommonPage.getCurrentDataIn("MM/dd/yyyy"));
	}
	
	public static void changeDOEIndicator() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DOE_INDICATOR_TOGGLE_XPATH);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DOE_INDICATOR_TOGGLE_XPATH);
	}
	
	public static void enterAndSelectUsedFleet(String usedFleet) throws Exception {
		clearTextField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_FLEET_ID);
		CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setFleetName(usedFleet);
		System.out.println("Used Fleet: " + usedFleet);
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_FLEET_ID, CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().getFleetName());
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_FLEET_SELECT_OPTION_XPATH);
	}

	public static void clearTextField(Enum<?> fieldtLocatorEnum) throws Exception {
		WebElement ele = BrowserAction.getElement(fieldtLocatorEnum);
		Actions a = new Actions(WebDriverAction.getDriver());
		a.moveToElement(ele).click().keyDown(ele,Keys.CONTROL).sendKeys(ele, "a").keyUp(ele, Keys.CONTROL).sendKeys(ele, Keys.BACK_SPACE).build().perform();
	}
	
	public static void selectWhoToSell(String whoToSell) throws Exception {
		CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setWhoToSellUsedUnit(whoToSell);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_WHO_TO_SELL_DROP_DOWN_NAME);
		System.out.println("Who to Sell: " + whoToSell);
		BrowserAction.selectDropdownOptionByText(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_WHO_TO_SELL_DROP_DOWN_NAME, CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().getWhoToSellUsedUnit());
	}
	
	/**
	 *This method will enter client contact first name
	 * @lastModifiedBy Akshay Kandkonde
	 * @param contactFirstNameInfo
	 * @throws Exception
	 */
	public static void enterContactFirstName(String contactFirstNameInfo) throws Exception {
		CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getClientContactInformationObject().setFirstName(contactFirstNameInfo);
		System.out.println("CCI First Name: " + contactFirstNameInfo);
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_CONTACT_FIRST_NAME_NAME, contactFirstNameInfo);
	}
	
	/**
	 *This method will enter client contact last name
	 * @lastModifiedBy Akshay Kandkonde
	 * @param contactLastNameInfo
	 * @throws Exception
	 */
	public static void enterContactLastName(String contactLastNameInfo) throws Exception {
		CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getClientContactInformationObject().setLastName(contactLastNameInfo);
		System.out.println("CCI Last Name: " + contactLastNameInfo);
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_CONTACT_LAST_NAME_NAME, contactLastNameInfo);
	}
	
	/**
	 *This method will enter client contact phone
	 * @lastModifiedBy Akshay Kandkonde
	 * @param clientContactPhoneInfo
	 * @throws Exception
	 */
	public static void enterClientContactPhone(String clientContactPhoneInfo) throws Exception {
		CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getClientContactInformationObject().setPhone(clientContactPhoneInfo);
		System.out.println("CCI Phone: " + clientContactPhoneInfo);
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_CLIENT_CONTACT_PHONE_NAME, clientContactPhoneInfo);
	}
	
	/**
	 *This method will enter client contact extension
	 * @lastModifiedBy Akshay Kandkonde
	 * @param clientExtensionInfo
	 * @throws Exception
	 */
	public static void enterClientExtension(String clientExtensionInfo) throws Exception {
		CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getClientContactInformationObject().setPhoneExt(clientExtensionInfo);
		System.out.println("CCI Extension: " + clientExtensionInfo);
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_CLIENT_EXTENSION_NAME, clientExtensionInfo);
	}
	
	/**
	 *This method will enter client contact email
	 * @lastModifiedBy Akshay Kandkonde
	 * @param clientContactEmailInfo
	 * @throws Exception
	 */
	public static void enterClientContactEmail(String clientContactEmailInfo) throws Exception {
		CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getClientContactInformationObject().setEmail(clientContactEmailInfo);
		System.out.println("CCI Email: " + clientContactEmailInfo);
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_CLIENT_CONTACT_EMAIL_NAME, clientContactEmailInfo);
	}
	
	/**
	 *This method will enter dealership name
	 * @lastModifiedBy Akshay Kandkonde
	 * @param dealershipNameInfo
	 * @throws Exception
	 */
	public static void enterDealershipName(String dealershipNameInfo) throws Exception {
		CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getDealerContactInformationObject().setDealerShipName(dealershipNameInfo);
		System.out.println("DCI Name: " + dealershipNameInfo);
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DEALERSHIP_NAME_NAME, dealershipNameInfo);
	}
	
	/**
	 *This method will enter dealer first name
	 * @lastModifiedBy Akshay Kandkonde
	 * @param dealerFirstNameInfo
	 * @throws Exception
	 */
	public static void enterDealerFirstName(String dealerFirstNameInfo) throws Exception {
		CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getDealerContactInformationObject().setFirstName(dealerFirstNameInfo);
		System.out.println("DCI First Name: " + dealerFirstNameInfo);
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DEALER_CONTACT_FIRST_NAME_NAME, dealerFirstNameInfo);
	}
	
	/**
	 *This method will enter dealer last name
	 * @lastModifiedBy Akshay Kandkonde
	 * @param dealerLastNameInfo
	 * @throws Exception
	 */
	public static void enterDealerLastName(String dealerLastNameInfo) throws Exception {
		CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getDealerContactInformationObject().setLastName(dealerLastNameInfo);
		System.out.println("DCI Last Name: " + dealerLastNameInfo);
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DEALER_CONTACT_LAST_NAME_NAME, dealerLastNameInfo);
	}
	
	/**
	 *This method will enter dealer contact phone
	 * @lastModifiedBy Akshay Kandkonde
	 * @param dealerContactPhoneInfo
	 * @throws Exception
	 */
	public static void enterDealerContactPhone(String dealerContactPhoneInfo) throws Exception {
		CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getDealerContactInformationObject().setPhone(dealerContactPhoneInfo);
		System.out.println("DCI Phone: " + dealerContactPhoneInfo);
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DEALER_CONTACT_PHONE_NAME, dealerContactPhoneInfo);
	}
	
	/**
	 *This method will enter dealer extension
	 * @lastModifiedBy Akshay Kandkonde
	 * @param dealerExtensionInfo
	 * @throws Exception
	 */
	public static void enterDealerExtension(String dealerExtensionInfo) throws Exception {
		CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getDealerContactInformationObject().setPhoneExt(dealerExtensionInfo);
		System.out.println("DCI Extension: " + dealerExtensionInfo);
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DEALER_EXTENSION_NAME, dealerExtensionInfo);
	}
	
	/**
	 *This method will enter dealer contact email
	 * @lastModifiedBy Akshay Kandkonde
	 * @param dealerContactEmailInfo
	 * @throws Exception
	 */
	public static void enterDealerContactEmail(String dealerContactEmailInfo) throws Exception {
		CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getDealerContactInformationObject().setEmail(dealerContactEmailInfo);
		System.out.println("DCI Email: " + dealerContactEmailInfo);
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DEALER_CONTACT_EMAIL_NAME, dealerContactEmailInfo);
	}
	
	/**
	 * This method adds Adhoc upfit from BO with upfitter name and upfit details from test data sheet
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void addAdhocUpfit() throws Exception {
		CommonPage.enterTextToInputField(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_UPFITTERSPECSEARCHTXTBOX_ID, CommonPage.getTestData("UpfitterSearch"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_UPFITTERSPECSEARCH_FIRSTOPTION_CSS);
		System.out.println("Upfitter Name: "+CommonPage.getTestData("UpfitterSearch"));
		if(BrowserAction.getElementText(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_UPFITTERSPECSEARCH_FIRSTOPTION_CSS).contains(CommonPage.getTestData("UpfitterSearch"))) {
			BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_UPFITTERSPECSEARCH_FIRSTOPTION_CSS);
			CommonPage.enterTextToInputField(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_LEAD_TIME_ID, "3");
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_WORK_TYPE_ID);
			BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_WORK_TYPE_ID, "Supply and Ship");
			CommonPage.enterTextToInputField(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_SUPPLIER_QUOTE_NUMBER_CSS, "3");
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_SUPPLIER_QUOTE_DATE_CSS);
			BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_SUPPLIER_QUOTE_DATE_CSS);
			BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_SUPPLIER_QUOTE_DATE_CSS).sendKeys(Keys.ENTER);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_ADD_PO_CSS);
			BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_ADD_PO_CSS);
			CommonPage.enterTextToInputField(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_OPTION_CODE_ID, CommonPage.getTestData("UpfitLineItemCode"));
			System.out.println("Upfit line item code: "+CommonPage.getTestData("UpfitLineItemCode"));
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_OPTION_CATEGORY_ID);
			BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_OPTION_CATEGORY_ID, CommonPage.getTestData("UpfitLineItemCategory"));
			System.out.println("Upfit line item category: "+CommonPage.getTestData("UpfitLineItemCategory"));
			CommonPage.enterTextToInputField(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_OPTION_DESCRIPTION_ID, CommonPage.getTestData("UpfitLineItemDesc"));
			System.out.println("Upfit line item description: "+CommonPage.getTestData("UpfitLineItemDesc"));
			CommonPage.enterTextToInputField(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_EXTENDED_DESCRIPTION_ID, CommonPage.getTestData("UpfitLineItemExtDesc"));
			System.out.println("Upfit line item extended description: "+CommonPage.getTestData("UpfitLineItemExtDesc"));
			CommonPage.enterTextToInputField(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_OPTION_PRICE_ID, CommonPage.getTestData("UpfitLineItemOptPrice"));
			System.out.println("Upfit line item opt price: "+CommonPage.getTestData("UpfitLineItemOptPrice"));
		} else {
			System.out.println("Not able to find upfitter.");
		}
	}

	/**
	 * This method click on upfit list
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickUpfitList() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_LIST_UPFIT_CLICK_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_LIST_UPFIT_CLICK_XPATH);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_LIST_UPFIT_CLICK_XPATH);
	}

	/**
	 * This method checks if Suppress PO Switch is present on Add Upfit window
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validatePresenceOfSuppressPOSwitchOnUpfitAddPOWindow() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_ADD_UPFIT_SUPPRESS_PO_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_ADD_UPFIT_SUPPRESS_PO_CSS);
	}

	/**
	 * This method checks if upfit line item is added
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void isUpfitLineItemAdded() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_LIST_UPFIT_CLICK_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_LIST_UPFIT_CLICK_XPATH);
		Assert.assertTrue(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_LIST_UPFIT_CLICK_XPATH).isDisplayed(), "Upfit line item not added.");
	}

	/**
	 * This method clicks upfit line item is added
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickUpfitLineItem() throws Exception{
		OrderingCommonPage.checkAlertPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_LIST_UPFIT_CLICK_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_LIST_UPFIT_CLICK_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_LIST_UPFIT_CLICK_XPATH)));
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_LIST_UPFIT_CLICK_XPATH);
		OrderingBOOrdMaintPage.moveToUpfitWindow();
	}

	/**
	 * This method checks presence of Suppress PO switch and CC field on upfit
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validatePresenceOfSuppressPOSwitchAndCCField() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SUPPRESS_PO_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SUPPRESS_PO_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_CC_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_CC_XPATH);
		OrderingBOOrdMaintPage.navigateToParentWindow();
	}

	/**
	 * This method enters email in CC field
	 * @lastModifiedBy djawale
	 * @param emailAddress
	 * @throws Exception
	 */
	public static void enterEmailAddressInCCField(String emailAddress) throws Exception {
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_CC_XPATH, emailAddress);
		System.out.println("Email added in CC field: "+emailAddress);
		BrowserAction.selectDropdownOptionByText(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_UPFIT_WORK_TYPE_XPATH, "Supply and Ship");
	}

	/**
	 * This method validates emails present in CC field
	 * @lastModifiedBy djawale
	 * @param expectedEmail
	 * @throws Exception
	 */
	public static void validateEmailInCCField(String expectedEmail) throws Exception {
		getExpectedEmailAddresses(expectedEmail);
		getActualEmailAddresses();
		Assert.assertTrue((actualEmails.containsAll(expectedEmails)&&expectedEmails.containsAll(actualEmails)), "Email addresses are not getting updated on saving the order");
	}

	/**
	 * This method gets Data ID for upfit
	 * @lastModifiedBy djawale
	 * @return dataID for upfit item
	 * @throws Exception
	 */
	public static String getDataID() throws Exception {
		if(dataId==null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_CC_XPATH);
			dataId=BrowserAction.getElementAttributeValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_CC_XPATH, "data-id");
			return dataId;
		} else
			return dataId;
	}

	/**
	 * This method converts enum of expected strings to List of String
	 * @lastModifiedBy djawale
	 * @param keys
	 */
	public static void getExpectedEmailAddresses(String keys) {
		String[] emails=keys.split(";");
		expectedEmails=new ArrayList<String>();
		expectedEmails=Arrays.asList(emails);
	}
	
	/**
	 * This method gets the emails in CC field using data ID from Database
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void getActualEmailAddresses() throws Exception {
		actualEmails=DbConnector.getDelegateEmailAddress(getDataID());
	}
	
	/**
	 * This method validates that send po now button is not displayed in upfit line item if Suppress PO indicator is on or Order is not acknowledged
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void verifySendPONowButtonIsNotDisplayed() throws Exception {
		try {
			if(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SEND_PO_NOW_CSS).isDisplayed()==true)
				throw new OrderingErrorOccured("Send PO Now Button displayed for Suppress PO On/Order Not Acknowledged");
		} catch (Exception e) {
			return;
		}finally {
			OrderingBOOrdMaintPage.navigateToParentWindow();
		}
	}
	
	/**
	 * This method validates that Send PO Now Button displayed for upfit line item
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void verifySendPONowButtonIsDisplayed() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SEND_PO_NOW_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SEND_PO_NOW_CSS);
	}

	/**
	 * This method clicks on Send PO Now Button 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickSendPONowButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SEND_PO_NOW_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SEND_PO_NOW_CSS);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SEND_PO_NOW_CSS);
	}

	/**
	 * This method validates the elements on Email Popup Loaded with Expected Elements
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateEmailPopUpLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_PO_NUMBER_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_PO_NUMBER_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_TO_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_CC_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_SUBJECT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_DOWNLOAD_ALL_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_ATTACHMENT_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_ADDITIONAL_TEXT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_SEND_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_CANCEL_CSS);
	}
	
	/**
	 * This method collects the data from BO Maint Page to compare with Email Popup
	 * lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void getDataFromBoMaintPageForEmailPopupValidation()throws Exception {
		newUnit = CommonPage.getFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_UNIT_VALUE_XPATH);
		foNumber=CommonPage.getFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_FO_NUM_XPATH);
		clientUnitNumber=CommonPage.getTestData("ClientNumber")+"-"+newUnit;
		newVIN = CommonPage.getFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_VIN_VALUE_XPATH);
		logNumber=CommonPage.getElementOrderObject().getLogNumber();
		goToUpfitSection();
		clickUpfitLineItem();
		String upfitPoNuber=CommonPage.getFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_LINE_ITEM_PO_NUM_CSS);
		StringBuffer sb=new StringBuffer(upfitPoNuber);;
		sb.replace(0, sb.length()-1, "");
		poNumber= logNumber+"-"+sb;
		emailHeader="PO Number "+poNumber;
		currentPOStatus=CommonPage.getFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_LINE_ITEM_CURRENT_PO_STATUS_CSS);
		supplierEmail=CommonPage.getFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_SUPPLIER_EMAIL_XPATH);
		supplierQuoteNumber=CommonPage.getFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_SUPPLIER_QUOTE_NO_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_SUPPLIER_QUOTE_DATE_XPATH);
		supplierQuoteDate=BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_SUPPLIER_QUOTE_DATE_XPATH).getAttribute("value").trim();
	}
	
	/**
	 * This method sets the Expected Email Subject Line
	 * @lastModifiedBy djawale
	 * @param poOption
	 * @throws Exception
	 */
	public static void setEmailSubjectLine(String poOption)throws Exception {
		switch(poOption) {
		case "Send PO": 
			emailSubjectLine="Element Purchase Order, "+CommonPage.getTestData("ClientNumber")+"-"+newUnit+", "+CommonPage.getTestData("ClientName");
			break;
		case "Resend PO": 
			emailSubjectLine="Element Purchase Order, "+CommonPage.getTestData("ClientNumber")+"-"+newUnit+", "+CommonPage.getTestData("ClientName");
			break;
		case "Cancel PO": 
			emailSubjectLine="Cancelled Purchase Order, "+CommonPage.getTestData("ClientNumber")+"-"+newUnit+", "+CommonPage.getTestData("ClientName");
			break;
		case "PO Amendment":
			emailSubjectLine="Revision to Element purchase order, "+CommonPage.getTestData("ClientNumber")+"-"+newUnit+", "+CommonPage.getTestData("ClientName");
		}
	}
	
	/**
	 * This method validates data from BO Maint Page with data on Email Popup such as PO Number, Client no, Unit No, VIN, Factory Order No,
	 * Supplier Email, Supplier Quote Number, Supplier Quote Date
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateDetailsOnEmailPopup()throws Exception {
		String emailPopupHeader = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_PO_NUMBER_CSS).getText().trim();
		assertEquals(emailPopupHeader, emailHeader, "Unable to match email Header on Email Popup");
		String toEmail = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_TO_ID).getAttribute("value");
		assertEquals(toEmail, supplierEmail, "Unable to match supplier email on Email Popup");
		String emailSubject = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_SUBJECT_ID).getAttribute("value").replace(", INC.", ". INC.");
		assertEquals(emailSubject, emailSubjectLine, "Unable to match subjectLine on Email Popup");
		WebDriverAccess.getDriver().switchTo().frame(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_IFRAME_ID.toString());
		String clientUnit = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_CLIENTUNIT_XPATH).getText().trim();
		assertEquals(clientUnit, clientUnitNumber, "Unable to match client-unit number on Email Popup");
		String factoryOrderNum = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_FACTORY_ORDER_XPATH).getText().trim();
		assertEquals(factoryOrderNum, foNumber, "Unable to match factory Order number on Email Popup");
		String vinNumber = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_VIN_XPATH).getText().trim();
		assertEquals(vinNumber, newVIN, "Unable to match VIN number on Email Popup");
		String poNum = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_PO_NUMBER_XPATH).getText().trim();
		assertEquals(poNum, poNumber, "Unable to match VIN number on Email Popup");
		String supplierQuoteNum = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_SUPPLIER_QUOTE_NO_XPATH).getText().trim();
		assertEquals(supplierQuoteNum, supplierQuoteNumber, "Unable to match Supplier Quote number on Email Popup");
		String supplierQuoteDt = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_SUPPLIER_QUOTE_DATE_XPATH).getText().trim();
		assertEquals(supplierQuoteDt, supplierQuoteDate, "Unable to match Supplier Quote Date on Email Popup");
		WebDriverAccess.getDriver().switchTo().defaultContent();
	}

	/**
	 * This method validates if Email Popup Displayed
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void isEmailPopupDisplayed()throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_SEND_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_SEND_ID);
	}
	
	/**
	 * This method validates if Email Popup closed
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void isEmailPopupClosed()throws Exception {
		try {
			if(BrowserAction.getElement((OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_SEND_ID)).isDisplayed()==true)
				throw new Exception("Email popup is not closed");
		} catch (Exception e) {
			System.out.println("Email popup is closed");
			return;
		}	
	}
	
	/**
	 * This method clicks on Send button on email popup
	 * @lastModifiedBy djawale
	 * @param JavascriptExecutor
	 * @throws Exception
	 */
	public static void clickSendButtonOnEmailPopup(JavascriptExecutor js)throws Exception {
		js.executeScript("document.getElementById('send-email').click();\r\n" + 
				"console.log('Clicked');\r\n" + 
				"setTimeout(function(){\r\n" + 
				"    if(document.getElementById('send-email')==null){\r\n" + 
				"		console.log('Element not present');\r\n" + 
				"	} else {\r\n" + 
				"		console.log('Element present');\r\n" + 
				"		document.getElementById('send-email').click();\r\n" + 
				"		console.log('Clicked');\r\n" + 
				"	}\r\n" + 
				"}, 10000);");
	}
	
	/**
	 * This method validates user is able to edit email address in To field on email popup
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateToFieldIsEditable()throws Exception {
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_TO_ID, CommonPage.getTestData("SingleEmailAddress"));
		System.out.println("Email address entered in TO field: "+CommonPage.getTestData("SingleEmailAddress"));
	}

	/**
	 * This method validates user is able to edit email in CC field on email popup
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateCCFieldIsEditable()throws Exception {
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_CC_ID, CommonPage.getTestData("MultipleEmailAddress"));
		System.out.println("Email addresses entered in CC field: "+CommonPage.getTestData("MultipleEmailAddress"));
	}

	/**
	 * This method enter additional details in additional text field on email popup
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void enterAdditionalText()throws Exception {
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_ADDITIONAL_TEXT_ID, CommonPage.getTestData("AdditionalText"));
		System.out.println("Additional text entered: "+CommonPage.getTestData("AdditionalText"));
	}

	/**
	 * This method additional text limit is updated on entering input in additional text field
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateAdditionalTextFieldLimit()throws Exception {
		String additionalTextLimit=CommonPage.getFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_ADDITIONAL_TEXT_LIMIT_ID);
		Assert.assertTrue(additionalTextLimit.contains("4063"), "Additional Text field limit does not change on entering input in additional text field");
	}

	/**
	 * This method confirms the warning popup if displayed
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void confirmWarningPopup()throws Exception {
		Actions actions=new Actions(WebDriverAccess.getDriver());
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_CONFIRM_WARNING_OK_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_CONFIRM_WARNING_OK_XPATH);
		actions.moveToElement(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_CONFIRM_WARNING_OK_XPATH)).click().build().perform();
	}

	/**
	 * This method checks that Send PO Now Button changes to Resend PO Now after sending email
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateSendPONowButtonChange()throws Exception {
		String sendPOButtonText=CommonPage.getFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SEND_PO_NOW_CSS);
		Assert.assertEquals(sendPOButtonText, "Resend PO", "Send PO Now button does not change to Resend PO Now");
	}

	/**
	 * This method validates that emails in CC field are auto populated as per initially sent email
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateEmailInCCField() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_CC_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_CC_ID);
		String ccEmail = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_CC_ID).getAttribute("value").trim();
		Assert.assertEquals(ccEmail, CommonPage.getTestData("MultipleEmailAddress"), "Emails in CC field are not auto populated as expected.");
	}

	/**
	 * This method validates that aditional text sent in initial email is displayed while resending PO Emails
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateAdditionalTextSentInEarlierEmail() throws Exception {
		WebDriverAccess.getDriver().switchTo().frame(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_IFRAME_ID.toString());
		String additionalText = CommonPage.getFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_SEND_PO_FIRST_ADDITIONAL_TEXT_CSS);
		Assert.assertEquals(additionalText, CommonPage.getTestData("AdditionalText"), "Initial additional text is not auto populate as expected");
		WebDriverAccess.getDriver().switchTo().defaultContent();
	}

	/**
	 * This method validates that user is able to download the attachment
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickAndDownloadAttachment() throws Exception {
		attachmentName=CommonPage.getFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_ATTACHMENT_CSS);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_EMAIL_POPUP_ATTACHMENT_CSS);
	}

	/**
	 * This method validates the PDF attached to Po Emails
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateAttachmentPDF() throws Exception {
		String srcPathFile = System.getProperty("user.home")+"\\Downloads\\"+attachmentName;
		File source = new File(srcPathFile);
		PDDocument document = PDDocument.load(source);
		document.getClass();
		if (!document.isEncrypted()) {			
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition(true);
			PDFTextStripper tStripper = new PDFTextStripper();
			String pdfText = tStripper.getText(document);
			Assert.assertTrue(pdfText.contains(poNumber), "PO PDF does not contain PO Number");
			Assert.assertTrue(pdfText.contains(CommonPage.getTestData("ClientNumber")), "PO PDF does not contain Client Number");
			Assert.assertTrue(pdfText.contains(newUnit), "PO PDF does not contain Unit Number");
			source.delete();
		}
	}

	/**
	 * This method getes the current PO Status
	 * @lastModifiedBy djawale
	 * @param expectedPOStatus
	 * @throws Exception
	 */
	public static void setCurrentPOStatus(String expectedPOStatus) throws Exception {
		currentPOStatus=expectedPOStatus;
	}

	/**
	 * This method validates the current PO Status
	 * @lastModifiedBy djawale
	 * @param expectedStatus
	 * @throws Exception
	 */
	public static void validateCurrentPOStatus(String expectedStatus) throws Exception {
		currentPOStatus=CommonPage.getFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_LINE_ITEM_CURRENT_PO_STATUS_CSS);
		Assert.assertTrue(currentPOStatus.contains(expectedStatus), "PO Status is not displayed as expected");
	}

	/**
	 * This method clicks on Cancel Po button
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickCancelPOButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_CANCEL_PO_BUTTON_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_CANCEL_PO_BUTTON_CSS);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_CANCEL_PO_BUTTON_CSS);
	}

	/**
	 * This method clicks Yes on Confirm Cancellation Popup
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void confirmCancellationPopup() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_CANCEL_PO_CONFIRMATION_YES_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_CANCEL_PO_CONFIRMATION_YES_CSS);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_CANCEL_PO_CONFIRMATION_YES_CSS);
		currentPOStatus="Purchase Order Cancelled";
	}

	/**
	 * This method clicks Yes on Confirmation to send PO cancellation to upfitter
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void confirmSendPOCancellation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_SEND_CANCEL_PO_CONFIRMATION_YES_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_SEND_CANCEL_PO_CONFIRMATION_YES_CSS);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_SEND_CANCEL_PO_CONFIRMATION_YES_CSS);
	}

	/**
	 * This method validates that Cancel PO button is not displayed
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void verifyCancelPOButtonIsNotDisplayed() throws Exception {
		flag=BrowserAction.getElement((OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_CANCEL_PO_BUTTON_CSS)).isDisplayed();
		Assert.assertFalse(flag, "Cancel PO Button is displayed.");
	}

	/**
	 * This method validates if Cancel PO button is displayed
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void verifyCancelPOButtonIsDisplayed() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_CANCEL_PO_BUTTON_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_CANCEL_PO_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_CANCEL_PO_BUTTON_CSS);
		OrderingBOOrdMaintPage.navigateToParentWindow();
	}

	/**
	 * This method validates that Resend PO Button displayed for upfit line item
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void verifyResendPOButtonIsDisplayed() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_RESEND_PO_BTN_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_RESEND_PO_BTN_XPATH);
	}

	/**
	 * This method toggles Suppress PO Switch
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void toggleSuppressPO() throws Exception {
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SUPPRESS_PO_CSS);
	}

	/**
	 * This method validates Suppress PO Switch is selected
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateSuppressPOSwitchIsSelected() throws Exception {
		flag=false;
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SUPPRESS_PO_CSS);
		WebElement suppressPO=BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SUPPRESS_PO_CSS);
		if(Color.fromString(suppressPO.getCssValue("background-color")).asHex().equals("#82c341")) 
			flag=true;
		Assert.assertTrue(flag, "Suppress PO Switch is not selected");
	}


	/**
	 * This method validates Suppress PO Switch is not selected
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateSuppressPOSwitchIsNotSelected() throws Exception {
		flag=false;
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SUPPRESS_PO_CSS);
		WebElement suppressPO=BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SUPPRESS_PO_CSS);
		if(Color.fromString(suppressPO.getCssValue("background-color")).asHex().equals("#82c341")) 
			flag=true;
		Assert.assertFalse(flag, "Suppress PO Switch is not selected");
	}

	/**
	 * This method validates Send PO Now Button is displayed
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateSendPONowButtonIsDisplayed() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SEND_PO_NOW_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SEND_PO_NOW_CSS);
	}

	/**
	 * This method validates Send PO Now Button is not displayed
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateSendPONowButtonIsNotDisplayed() throws Exception {
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_SEND_PO_NOW_CSS);
	}

	/**
	 * This method gets suppress PO warning Message
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static String getSuppressPOWarningMessage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERINNG_BO_ORD_MAINT_SUPPRESS_PO_WARNING_MESSAGE_CSS);
		String suppressPOWarningMessage=BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERINNG_BO_ORD_MAINT_SUPPRESS_PO_WARNING_MESSAGE_CSS).getText().trim();
		return suppressPOWarningMessage;
	}

	/**
	 * This method validates suppress po warning messsage
	 * @lastModifiedBy djawale
	 * @param expectedWarningMessage
	 * @throws Exception
	 */
	public static void validateSuppressPOWarningMessage(String upfitterPreference) throws Exception {
		getSuppressPOWarningMessage();
		switch(upfitterPreference) {
			case "Do Not Send": 
				Assert.assertEquals(getSuppressPOWarningMessage(), doNotSendWarningMessage);
				break;
			case "PDF": 
				Assert.assertEquals(getSuppressPOWarningMessage(), pdfWarningMessage);
				break;
			case "Preference Do Not Exist": 
				Assert.assertEquals(getSuppressPOWarningMessage(), preferenceDoNotExistWarningMessage);
				break;
			default: throw new InvalidSwitchCaseException(upfitterPreference+" is a invalid option");
		}
	}

	/**
	 * This method validates warning popup message is displayed
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateSuppressPOWarningPopupDisplayed() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SUPPRESS_PO_WARNING_POPUP_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SUPPRESS_PO_WARNING_POPUP_CSS);
	}

	/**
	 * This method clicks Yes on Warning popup
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickYesSuppressPOWarningPopup() throws Exception {
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SUPPRESS_PO_YES_CSS);
	}

	/**
	 * This method will change order type in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void changeOrderType() throws Exception {
		System.out.println("Order Type: " + CommonPage.getTestData("CustomColumn1"));
		CommonPage.selectDropdownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_CHANGE_ORDER_TYPE_CLASS, CommonPage.getTestData("CustomColumn1"));
		if("Stock".equalsIgnoreCase(CommonPage.getTestData("CustomColumn1"))) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_VEHICLE_LOCATED_DROPDOWN_XPATH);
			CommonPage.selectDropdownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_VEHICLE_LOCATED_DROPDOWN_XPATH, CommonPage.getTestData("CustomColumn3"));
		}
	}

	/**
	 * This method will change order type in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verfiyOrderType() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_VIN_VALUE_XPATH.getValue()), 1));
		CommonPage.verifyDropDownSelectedValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_CHANGE_ORDER_TYPE_CLASS, CommonPage.getTestData("CustomColumn1"), "Order Type");
	}

	/**
	 *This method will edit general info in BO and verify it
	 * @lastModifiedBy shisingh
	 */
	public static void editBOGeneralInfoAndVerify() throws Exception {
		OrderingBOOrdMaintPage.enterVIN();
		OrderingBOOrdMaintPage.changeOrderType();
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.verifyGeneralInfo();
	}

	/**
	 *This method will edit general info in BO and verify it
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void editAndVerifyGeneralInformation() throws Exception {
		OrderingBOOrdMaintPage.goToGeneralOrderTabInBO();
		OrderingBOOrdMaintPage.waitForGeneralInfoTabToLoad();
		OrderingBOOrdMaintPage.changeAndVerifyOrderType();
		OrderingBOOrdMaintPage.addEditRemoveVINAndVerify();
		OrderingBOOrdMaintPage.changeDOEAndVerify();
		OrderingBOOrdMaintPage.addAlterRemoveAndVerifyUsedUnitInformation();
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.waitForMaintenancePage();
		OrderingBOOrdMaintPage.clickCancelOrderButton();
		OrderingBOOrdMaintPage.approveOrRejectCancelOrderStatus("Yes");
		OrderingBOOrdMaintPage.verifyCancelIndicatorOptionYes();
	}	

	/**
	 *This method will edit general info in BO and verify it
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void changeAndVerifyOrderType() throws Exception {
		OrderingBOOrdMaintPage.changeOrderType();
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.waitForMaintenancePage();
		OrderingBOOrdMaintPage.verfiyOrderType();
	}

	/**
	 *This method will edit general info in BO and verify it
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void editGeneralInformation() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("General Order");
		OrderingBOOrdMaintPage.changeFONumberAndDateIfOrderTypeFactory();
		OrderingBOOrdMaintPage.changeVehicleLocatedIfOrderTypeStock();
		OrderingBOOrdMaintPage.enterClientDealerInfo();
	}

	/**
	 *This method will verify general info in BO and verify it
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyGeneralInformation() throws Exception {
		OrderingBOOrdMaintPage.waitForMaintenancePage();
		OrderingBOOrdMaintPage.verifyFONumberAndDateIfOrderTypeFactory();
		OrderingBOOrdMaintPage.verifyVehicleLocatedIfOrderTypeStock();
		OrderingBOOrdMaintPage.verifyClientDealerInfo();
	}

	/**
	 * This method will Add/Alter/Remove used unit information in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void addAlterRemoveAndVerifyUsedUnitInformation() throws Exception {
		OrderingBOOrdMaintPage.enterUsedUnitInformation("Add");
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.waitForMaintenancePage();
		OrderingBOOrdMaintPage.verifyUsedUnitInformation("Add");
		OrderingBOOrdMaintPage.enterUsedUnitInformation("Remove");
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.waitForMaintenancePage();
		OrderingBOOrdMaintPage.verifyUsedUnitInformation("Remove");
	}

	/**
	 * This method will wait to load driver information section in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForGeneralInfoTabToLoad() throws Exception {
		waitForTabToLoadInBOUsingName(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_VIN_TEXT_FIELD_NAME, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will click on Driver tab in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToGeneralOrderTabInBO() throws Exception {
		clickOnTabInBOUsingId(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_GENERALTAB_ID, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will click on tab like driver, General Order, etc tab on maintenance page in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	 public static void clickOnTabInBOUsingId(Enum<?> fieldtLocatorEnum, String waitTime) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(waitTime)).until(ExpectedConditions.numberOfElementsToBe(By.id(fieldtLocatorEnum.toString()), 1));
		BrowserAction.click(fieldtLocatorEnum);
	}

	/**
	 *This method will select all used information available in that section like fleet,usedUnit, etc.
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterUsedUnitInformation(String action) throws Exception {
		switch(action) {
			case "Add":
				OrderingBOOrdMaintPage.enterAndSelectUsedFleet(CommonPage.getTestData("CustomColumn5"));
				OrderingBOOrdMaintPage.enterAndSelectUsedUnitInBO(CommonPage.getTestData("ApplicationURL"));
				OrderingBOOrdMaintPage.selectWhoToSell(CommonPage.getTestData("CustomColumn6"));
				break;
			case "Alter":
				OrderingBOOrdMaintPage.enterAndSelectUsedFleet(CommonPage.getTestData("CustomColumn7"));
				OrderingBOOrdMaintPage.enterAndSelectUsedUnitInBO(CommonPage.getTestData("ApplicationURL"));
				OrderingBOOrdMaintPage.selectWhoToSell(CommonPage.getTestData("CustomColumn8"));
				break;
			case "Remove":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_REMOVE_USED_UNIT_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_REMOVE_USED_UNIT_CSS);
				BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_REMOVE_USED_UNIT_CSS);
				BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_REMOVE_USED_UNIT_CSS);
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_USED_UNIT_REMOVED_XPATH.getValue()), 1));
				break;
			default: throw new InvalidSwitchCaseException(action+" is a invalid option");
		}
	}

	/**
	 *This method will verify all used information available in that section like fleet,usedUnit, etc.
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyUsedUnitInformation(String action) throws Exception {
		switch(action) {
			case "Add":
				CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_FLEET_ID, CommonPage.getTestData("CustomColumn5"), "Used fleet");
				CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_UNIT_ID, CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().getUnitNumber(), "Used Unit number");
				CommonPage.verifyDropDownSelectedValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_WHO_TO_SELL_DROP_DOWN_NAME, CommonPage.getTestData("CustomColumn6"), "Who To Sell");
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_REMOVE_USED_UNIT_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_REMOVE_USED_UNIT_CSS);
				BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_REMOVE_USED_UNIT_CSS);
				break;
			case "Alter":
				CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_FLEET_ID, CommonPage.getTestData("CustomColumn7"), "Used fleet");
				CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_UNIT_ID, CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().getUnitNumber(), "Used Unit number");
				CommonPage.verifyDropDownSelectedValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_WHO_TO_SELL_DROP_DOWN_NAME, CommonPage.getTestData("CustomColumn8"), "Who To Sell");
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_REMOVE_USED_UNIT_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_REMOVE_USED_UNIT_CSS);
				BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_REMOVE_USED_UNIT_CSS);
				break;
			case "Remove":
				CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_FLEET_ID, "", "Used fleet");
				CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_UNIT_ID, "", "Used Unit number");
				CommonPage.verifyDropDownSelectedValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_WHO_TO_SELL_DROP_DOWN_NAME, "Select one...", "Who To Sell");
				try {
					if(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_REMOVE_USED_UNIT_CSS).isDisplayed() == true)
						throw new OrderingErrorOccured("Remove Used Unit button is visible after remove of used unit");
				} catch(Exception e) {
					System.out.println("Remove Used unit button is invisible after remove of used unit");
				}
				break;
			default: throw new InvalidSwitchCaseException(action+" is a invalid option");
		}
	}

	/**
	 * This method will enter and select used unit from used unit text box
	 * @lastModifiedBy Akshay Kandkonde
	 * @param url
	 * @throws Exception
	 */
	public static void enterAndSelectUsedUnitInBO(String url) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_UNIT_ID);
		BrowserAction.clickandClear(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_UNIT_ID);
		CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setUnitNumber(DbConnector.getActiveUsedUnit("FA", CommonPage.getTestData("CustomColumn5")));
		System.out.println("Used Unit: " + CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().getUnitNumber());
		BrowserAction.enterFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_UNIT_ID,CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().getUnitNumber());
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_UNIT_SELECTION_OPTION_XPATH);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_USED_UNIT_INFO_USED_UNIT_SELECTION_OPTION_XPATH);
	}

	/**
	 * This method will click Cancel button.
	 * @lastModifiedBy shisingh
	 */
	public static void clickCancelOrderButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_BUTTON_ID);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_BUTTON_ID);
	}
	/**
	 * This method approves or rejects the order cancel order pop up.
	 * @lastModifiedBy shisingh
	 */
	public static void approveOrRejectCancelOrderStatus(String action) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_ORDER_POPUP_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_ORDER_POPUP_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_ORDER_POPUP_CSS);
		switch(action) {
		case "Yes":
			BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_ORDER_POPUP_YES_CSS);
			break;
		case "No":
			BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_ORDER_POPUP_NO_CSS);
			break;
		default: throw new OrderingErrorOccured(action + "is a invalid option");
		}
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	/**
	 * This method will verify yes option is selected from Cancel indicator drop down
	 * @lastModifiedBy shisingh
	 */
	public static void verifyCancelIndicatorOptionYes() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_BUTTON_DISABLED_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_BUTTON_DISABLED_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_BUTTON_DISABLED_XPATH);
		Assert.assertEquals(WebDriverAccess.getElementText(By.xpath(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_INDICATOR_LABEL_XPATH.getValue().replaceAll("label-heading", "Cancel Indicator"))), "Yes", "Order is not canceled");
	}

	/**
	 * This method will enter FO number and FO date if order type is factory
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void changeFONumberAndDateIfOrderTypeFactory() throws Exception{
		if(CommonPage.getTestData("OrderType").equals("Factory")) {
			OrderingBOOrdMaintPage.enterNewFONumber(CommonPage.getTestData("CustomColumn4"));
			OrderingBOOrdMaintPage.enterNewFODate();
		}
	}

	/**
	 * This method will verify FO number and FO date if order type is factory
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyFONumberAndDateIfOrderTypeFactory() throws Exception{
		if(CommonPage.getTestData("OrderType").equals("Factory")) {
			waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_FO_NUMBER_XPATH, CommonPage.getTestData("WaitTime"));
			assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_FO_NUMBER_XPATH).getText(),
					CommonPage.getTestData("CustomColumn4"), "Actual and excpected value not matching for FO Number");
		}
	}

	/**
	 *This method will wait to load maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void changeDOEAndVerify() throws Exception {
		OrderingBOOrdMaintPage.changeDOEIndicator();
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.waitForMaintenancePage();
		OrderingBOOrdMaintPage.verifyDOEIndicator("On");
		OrderingBOOrdMaintPage.changeDOEIndicator();
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.waitForMaintenancePage();
		OrderingBOOrdMaintPage.verifyDOEIndicator("Off");
	}

	/**
	 *This method will change vehicle located option if order type is stock
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void changeVehicleLocatedIfOrderTypeStock() throws Exception {
		if(CommonPage.getTestData("OrderType").equals("Stock")) {
			System.out.println("Vehicle located: " + CommonPage.getTestData("CustomColumn3"));
			CommonPage.selectDropdownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_VEHICLE_LOCATED_DROPDOWN_XPATH, CommonPage.getTestData("CustomColumn3"));
		}
	}

	/**
	 *This method will verify vehicle located option if order type is stock
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleLocatedIfOrderTypeStock() throws Exception {
		if(CommonPage.getTestData("OrderType").equals("Stock")) {
			System.out.println("Vehicle located: " + CommonPage.getTestData("CustomColumn3"));
			CommonPage.verifyDropDownSelectedValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_VEHICLE_LOCATED_DROPDOWN_XPATH, CommonPage.getTestData("CustomColumn3"), "Vehicle Located");
		}
	}

	/**
	 *This method will wait to load maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForMaintenancePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_ORDER_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_ORDER_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_ORDER_ID);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_ORDER_ID)));
	}

	/**
	 *This method will enter all Client/Dealer info in BO. This method will be applicable for Dealer and Stock order type
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterClientDealerInfo() throws Exception {
		if(!(CommonPage.getTestData("OrderType").equals("Factory"))) {
			OrderingBOOrdMaintPage.enterContactFirstName(CommonPage.getTestData("ClientContactFirstName"));
			OrderingBOOrdMaintPage.enterContactLastName(CommonPage.getTestData("ClientContactLastName"));
			OrderingBOOrdMaintPage.enterClientContactPhone(CommonPage.getTestData("ClientContactPhone"));
			OrderingBOOrdMaintPage.enterClientContactEmail(CommonPage.getTestData("ClientContactEmail"));
			OrderingBOOrdMaintPage.enterClientExtension(CommonPage.getTestData("DealerContactExtension"));
			OrderingBOOrdMaintPage.enterDealershipName(CommonPage.getTestData("DealerShipName"));
			OrderingBOOrdMaintPage.enterDealerFirstName(CommonPage.getTestData("DealerContactFirstName"));
			OrderingBOOrdMaintPage.enterDealerLastName(CommonPage.getTestData("DealerContactLastName"));
			OrderingBOOrdMaintPage.enterDealerContactPhone(CommonPage.getTestData("DealerContactPhone"));
			OrderingBOOrdMaintPage.enterDealerContactEmail(CommonPage.getTestData("DealerContactMail"));
			OrderingBOOrdMaintPage.enterDealerExtension(CommonPage.getTestData("DealerContactExtension"));
		}
	}

	/**
	 *This method will verify all Client/Dealer info in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyClientDealerInfo() throws Exception {
		if(!(CommonPage.getTestData("OrderType").equals("Factory"))) {
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_CONTACT_FIRST_NAME_NAME, CommonPage.getTestData("ClientContactFirstName"), "Contact First Name");
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_CONTACT_LAST_NAME_NAME, CommonPage.getTestData("ClientContactLastName"), "Contact Last Name");
			verifyInputDateFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_CLIENT_CONTACT_PHONE_NAME, CommonPage.getTestData("ClientContactPhone"), "Client Contact Phone");
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_CLIENT_CONTACT_EMAIL_NAME, CommonPage.getTestData("ClientContactEmail"), "Contact Email");
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_CLIENT_EXTENSION_NAME, CommonPage.getTestData("DealerContactExtension"), "Contact Extension");
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DEALERSHIP_NAME_NAME, CommonPage.getTestData("DealerShipName"), "DealerShip Name");
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DEALER_CONTACT_FIRST_NAME_NAME, CommonPage.getTestData("DealerContactFirstName"), "Dealer First Name");
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DEALER_CONTACT_LAST_NAME_NAME, CommonPage.getTestData("DealerContactLastName"), "Dealer Last Name");
			verifyInputDateFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DEALER_CONTACT_PHONE_NAME, CommonPage.getTestData("DealerContactPhone"), "Dealer Contact Phone");
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DEALER_EXTENSION_NAME, CommonPage.getTestData("DealerContactExtension"), "Dealer Contact Extension");
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DEALER_CONTACT_EMAIL_NAME, CommonPage.getTestData("DealerContactMail"), "Dealer Contact Email");
		}
	}

	/**
	 * This method will verify input date value with expected value
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyInputDateFieldValue(Enum<?> fieldtLocatorEnum, String expectedValue, String fieldName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		BrowserVerify.verifyElementIsPresent(fieldtLocatorEnum);
		BrowserVerify.verifyElementEnabled(fieldtLocatorEnum);
		String actualValue = BrowserAction.getElementAttributeValue(fieldtLocatorEnum,"value").replace("-", "");;
		assertEquals(actualValue, expectedValue, "Actual and excpected value not matching for "+fieldName);
	}

	/**
	 *This method will verify DOE Indicator changes or not in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyDOEIndicator(String mode) throws Exception {
		switch(mode) {
			case "On":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DOE_INDICATOR_TOGGLE_XPATH);
				BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DOE_INDICATOR_TOGGLE_XPATH).isSelected();
				break;
			case "Off":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DOE_INDICATOR_TOGGLE_XPATH);
				if(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_DOE_INDICATOR_TOGGLE_XPATH).isSelected())
					throw new OrderingErrorOccured("DOE toggle is selected it should not be selected");
				break;
			default: throw new InvalidSwitchCaseException(mode+" is a invalid option");
		}

	}

	/**
	 *This method will edit/Add/Remove VIN and verify it
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void addEditRemoveVINAndVerify() throws Exception {
		OrderingBOOrdMaintPage.enterVIN();
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.verifyAddEditRemoveVIN("Add");
		OrderingBOOrdMaintPage.editVINNumber();
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.verifyAddEditRemoveVIN("Edit");
		OrderingBOOrdMaintPage.removeVINNumber();
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.verifyAddEditRemoveVIN("Remove");
	}

	/**
	 *This method will verify edited/added/removed VIN in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyAddEditRemoveVIN(String action) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_ORDER_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_ORDER_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_ORDER_ID);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_ORDER_ID)));
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		String vin = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_VIN_VALUE_XPATH).getText();
		switch(action) {
			case "Add":
				assertEquals(vin, CommonPage.getTestData("VinNumberSearch"), "New VIN not added successfully..");
				break;
			case "Edit":
				assertEquals(vin, CommonPage.getTestData("CustomColumn2"), "Not able to edit VIN");
				break;
			case "Remove":
				assertEquals(vin, "", "Not able to remove VIN");
				break;
			default: throw new InvalidSwitchCaseException(action+" is a invalid option");
		}
	}

	/**
	 *This method will enter VIN in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVIN() throws Exception {
		System.out.println("VIN : " + CommonPage.getTestData("VinNumberSearch"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_VIN_TEXT_FIELD_NAME, CommonPage.getTestData("VinNumberSearch"));
	}

	/**
	 *This method will edit VIN in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void editVINNumber() throws Exception {
		System.out.println("VIN : " + CommonPage.getTestData("CustomColumn2"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_VIN_TEXT_FIELD_NAME, CommonPage.getTestData("CustomColumn2"));
	}

	/**
	 *This method will remove VIN in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void removeVINNumber() throws Exception {
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_VIN_TEXT_FIELD_NAME, "                     ");
	}

	/**
	 * This method will verify general info in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyGeneralInfo() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_CHANGE_ORDER_TYPE_CLASS);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_CHANGE_ORDER_TYPE_CLASS);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_CHANGE_ORDER_TYPE_CLASS);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_VIN_VALUE_XPATH.getValue()), 1));
		Select slt = new Select(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_CHANGE_ORDER_TYPE_CLASS));
		assertEquals(slt.getFirstSelectedOption().getText().trim(), CommonPage.getTestData("CustomColumn1"), "Unable to match New Order Type on Order maintenance screen.");
	}

	/**
	 * This method will wait to load driver information section in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForDriverTabToLoad() throws Exception {
		waitForTabToLoadInBOUsingName(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_DRIVER_TAB_DRIVER_FNAME_NAME, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will wait to load tab section like driver section, DIO section on maintenance page in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForTabToLoadInBOUsingName(Enum<?> fieldtLocatorEnum, String waitTime) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		BrowserVerify.verifyElementIsPresent(fieldtLocatorEnum);
		BrowserVerify.verifyElementEnabled(fieldtLocatorEnum);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(waitTime)).until(ExpectedConditions.numberOfElementsToBe(By.name(fieldtLocatorEnum.toString()), 1));
	}

	/**
	 * This method will wait to load tab section like driver section, DIO section on maintenance page in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForTabToLoadInBO(Enum<?> fieldtLocatorEnum, String waitTime) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		BrowserVerify.verifyElementIsPresent(fieldtLocatorEnum);
		BrowserVerify.verifyElementEnabled(fieldtLocatorEnum);
		if(BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.xpath"))
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(waitTime)).until(ExpectedConditions.numberOfElementsToBe(By.xpath(fieldtLocatorEnum.toString()), 1));
		else if(BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.id"))
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(waitTime)).until(ExpectedConditions.numberOfElementsToBe(By.id(fieldtLocatorEnum.toString()), 1));
		else if(BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.name"))
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(waitTime)).until(ExpectedConditions.numberOfElementsToBe(By.name(fieldtLocatorEnum.toString()), 1));
		else if(BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.cssSelector"))
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(waitTime)).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(fieldtLocatorEnum.toString()), 1));
		else if(BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.className"))
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(waitTime)).until(ExpectedConditions.numberOfElementsToBe(By.className(fieldtLocatorEnum.toString()), 1));
		else
			throw new NoIfElseBlockMatchedException();
	}

	/**
	 * This method clicks on Title & Registration Tab on Order page
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void goToTitleAndRegistrationTab() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_TAB_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_TAB_ID);
		BrowserAction.hoverOverElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_TAB_ID);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_TAB_ID);
	}

	/**
	 * This method navigate away from T&R page and back
	 * @lastModifiedBy sbhosale
	 * @throws Exception
	 */
	public static void navigateAndComebackToTitleRegisrtationTab() throws Exception {
		//Cannot use Dynamic wait as we need to wait to Save Order
		Thread.sleep(3000);
		goToApprovalSection();
		goToTitleAndRegistrationTab();
	}

	/**
	 * This method asserts input value matches to Text
	 * @lastModifiedBy sbhosale
	 * @throws Exception
	 */
	public static void verifyInputTextMatchesToTestData(Enum<?> element,String text) throws Exception  {
		Assert.assertEquals((BrowserAction.getElement(element).getAttribute("value").trim()),text);
	}

	/**
	 * This method will select Who to Title option on Title & Registration Section
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectWhoToTitle() throws Exception {
		if(!(CommonPage.getTestData("WhoToTitleBO")==null)) {
			CommonPage.selectDropDownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_SECTION_WHO_TO_TITLE_XPATH, CommonPage.getTestData("WhoToTitleBO"), "Who To Title");
		}
	}

	/**
	 * This method will select plate type option in Title and License tab in BO on maintenance page
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectPlateType() throws Exception {
		if(!(CommonPage.getTestData("PlateTypeBO")==null)) {
			CommonPage.selectDropDownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_SECTION_PLATE_TYPE_XPATH, CommonPage.getTestData("PlateTypeBO"), "Plate Type");
		}
	}

	/**
	 * This method asserts Dropdown value
	 * @lastModifiedBy sbhosale
	 * @throws Exception
	 */
	public static void verifyDropdownTextMatchestoTestData(Enum<?> element,String text) throws Exception {
		Assert.assertEquals(CommonPage.getSelectedvaluefronDropdown(element), text);
	}

	/**
	 * This method asserts All Sections are displayed on Title License Section .
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void assertAllTitleRegisrtationLabelsAreDisplayed() throws Exception {
		BrowserAssert.assertElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_SECTION_TITLELABEL_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_SECTION_REGISTRATIONLABEL_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_SECTION_LIENHOLDERLABEL_XPATH);	
	}

	/**
	 * This method asserts Vehicle Text matches to Vehicle Text on Title License Section .
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyVehicleRegisteredTextMatchesWithBillingPageText(ElementOrder orderTabsData) throws Exception  {
		BillingAndRegistrationTab.Registration registrationDetails = orderTabsData.getBillingAndRegistrationTabObject().getRegistrationSectionObject();
		Assert.assertEquals((CommonPage.getFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_SECTION_VEHICLETEXT_XPATH)),registrationDetails.getVehicleRegisteredText().trim());
		verifyDropdownTextMatchestoTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_SECTION_WHO_TO_TITLE_XPATH,registrationDetails.getWhoToTitle().trim());
		verifyDropdownTextMatchestoTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_SECTION_PLATE_TYPE_XPATH,registrationDetails.getPlateType().trim());
	}

	/**
	 * This method Edits Input fields for Title Section on Registration Section
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void editTitleInfo() throws Exception {
		System.out.println("Updated Title owner:- "+CommonPage.getTestData("EditTitleOwner"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_OWNER_NAME,CommonPage.getTestData("EditTitleOwner"));
		System.out.println("Updated Title Address:- "+CommonPage.getTestData("TitleAddressLine1"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_ADDRESS_NAME,CommonPage.getTestData("TitleAddressLine1"));
		System.out.println("Updated Title City:- "+CommonPage.getTestData("TitleCity"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_CITY_NAME,CommonPage.getTestData("TitleCity"));
		System.out.println("Updated Title Zip Code:- "+CommonPage.getTestData("TitleZipCode"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_ZIP_NAME,CommonPage.getTestData("TitleZipCode"));
		System.out.println("Updated Title State:- "+CommonPage.getTestData("TitleState"));
		CommonPage.selectDropdownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_STATE_XPATH,CommonPage.getTestData("TitleState"));
		System.out.println("Updated State ID:- "+CommonPage.getTestData("StateId"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_STATEID_NAME,CommonPage.getTestData("StateId"));
		System.out.println("Updated Federal ID:- "+CommonPage.getTestData("FederalId"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_FEDERALID_NAME,CommonPage.getTestData("FederalId"));
		System.out.println("Updated Tax Exmp No:- "+CommonPage.getTestData("TaxExemNo"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_TAXEXEMPTNO_NAME,CommonPage.getTestData("TaxExemNo"));
		clickOnSaveOrder();
	}

	/**
	 * This method asserts Input fields are Edited & Saved for Title Section on Registration Section
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyEditedTitleInfoMatches() throws Exception {
		verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_OWNER_NAME,CommonPage.getTestData("EditTitleOwner"));
		verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_ADDRESS_NAME,CommonPage.getTestData("TitleAddressLine1"));
		verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_CITY_NAME,CommonPage.getTestData("TitleCity"));
		verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_ZIP_NAME,CommonPage.getTestData("TitleZipCode"));
		verifyDropdownTextMatchestoTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_STATE_XPATH,CommonPage.getTestData("TitleState"));
		verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_STATEID_NAME,CommonPage.getTestData("StateId"));
		verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_FEDERALID_NAME,CommonPage.getTestData("FederalId"));
		verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_TAXEXEMPTNO_NAME,CommonPage.getTestData("TaxExemNo"));
	}

	/**
	 * This method asserts Input fields for Title Section on Registration Section match with Summary Page in FO
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyTitleDetailsWithCodes(ElementOrder orderTabsData) throws Exception {
		BillingAndRegistrationTab.TitleOwner titleOwner = orderTabsData.getBillingAndRegistrationTabObject().getTitleOwnerObject();
		Assert.assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_NAME_XPATH).getText(),titleOwner.getTitleName());
		verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_OWNER_NAME,titleOwner.getTitleOwner().replaceAll(" ,",","));
		if(!titleOwner.getTitleFederalID().equals("")){
			Assert.assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_FEDERALID_NAME).getAttribute("value").trim(),titleOwner.getTitleFederalID().replaceAll("Federal Identification Number ","").trim());
			System.out.println("Title Federal id matched");
		}

		if(!titleOwner.getTitlestateID().equals("")){
			Assert.assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_STATEID_NAME).getAttribute("value").trim(),titleOwner.getTitlestateID().replaceAll("State Identification Number ","").trim());
			System.out.println(" Title State id matched");
		}

		if(!titleOwner.getTitleTaxExempt().equals("")){
			Assert.assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_TITLE_TAXEXEMPTNO_NAME).getAttribute("value").trim(),titleOwner.getTitleTaxExempt().replaceAll("Tax Exemption Number ","").trim());
			System.out.println("Title Tax id matched");
		}
	}

	/**
	 * This method asserts Input fields for Registration Section on Registration Section match with Summary Page in FO
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyRegistrationDetailsWithCodes(ElementOrder orderTabsData) throws Exception {
		BillingAndRegistrationTab.RegisteredOwner registeredOwner = orderTabsData.getBillingAndRegistrationTabObject().getRegisteredOwnerObject();
		Assert.assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_NAME_XPATH).getText(),registeredOwner.getRegisteredName());
		verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_OWNER_NAME,registeredOwner.getRegisteredOwner().replaceAll(" ,",","));
		if(!registeredOwner.getRegisteredFederalID().equals("")){
			Assert.assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_FEDERALID_NAME).getAttribute("value").trim(),registeredOwner.getRegisteredFederalID().replaceAll("Federal Identification Number ","").trim());
			System.out.println("Registered Federal id matched");
		}

		if(!registeredOwner.getRegisteredStateID().equals("")){
			Assert.assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_STATEID_NAME).getAttribute("value").trim(),registeredOwner.getRegisteredStateID().replaceAll("State Identification Number ","").trim());
			System.out.println("Registered State id matched");
		}
	}

	/**
	 * This method Edits Input fields for Registration Section on Title License Section
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void editRegistrationInfo() throws Exception {
		System.out.println("Updated Registration Owner Name:- "+CommonPage.getTestData("EditedRegistrationOwner"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_OWNER_NAME,CommonPage.getTestData("EditedRegistrationOwner").trim());
		System.out.println("Updated Registration Address Line:- "+CommonPage.getTestData("RegistrationAddressLine1"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_ADDRESS_NAME,CommonPage.getTestData("RegistrationAddressLine1"));
		System.out.println("Updated Registration City:- "+CommonPage.getTestData("RegistrationCity"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_CITY_NAME,CommonPage.getTestData("RegistrationCity"));
		System.out.println("Updated Registration Zip Code:- "+CommonPage.getTestData("RegistrationZipCode"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_ZIP_NAME,CommonPage.getTestData("RegistrationZipCode"));
		System.out.println("Updated Registration State:- "+CommonPage.getTestData("RegistrationState"));
		CommonPage.selectDropdownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_STATE_XPATH,CommonPage.getTestData("RegistrationState"));
		System.out.println("Updated Registration State ID:- "+CommonPage.getTestData("StateId"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_STATEID_NAME,CommonPage.getTestData("StateId"));
		System.out.println("Updated Registration Federal Id:- "+CommonPage.getTestData("FederalId"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_FEDERALID_NAME,CommonPage.getTestData("FederalId"));
		clickOnSaveOrder();
	}

	/**
	 * This method assert Input fields are Edited & Saved for Registration Section on Title License Section
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyEditedRegistrationInfoMatches() throws Exception {
		verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_OWNER_NAME,CommonPage.getTestData("EditedRegistrationOwner").trim());
		verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_ADDRESS_NAME,CommonPage.getTestData("RegistrationAddressLine1"));
		verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_CITY_NAME,CommonPage.getTestData("RegistrationCity"));
		verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_ZIP_NAME,CommonPage.getTestData("RegistrationZipCode"));
        verifyDropdownTextMatchestoTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_STATE_XPATH,CommonPage.getTestData("RegistrationState"));
        verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_STATEID_NAME,CommonPage.getTestData("StateId"));
        verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_REGISTRATION_FEDERALID_NAME,CommonPage.getTestData("FederalId"));
    }

	/**
	 * This method asserts Input fields for Lienholder Section on Registration Section match with Summary Page in FO
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyLienholderDetailsWithCodes(ElementOrder orderTabsData) throws Exception  {
		BillingAndRegistrationTab.LienHolder lienHolder = orderTabsData.getBillingAndRegistrationTabObject().getLienHolderObject();
		if(!lienHolder.getLienHolderAddress().equals("")){
			Assert.assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_ADDRESS_NAME).getAttribute("value").trim(),lienHolder.getLienHolderAddress().trim());
			System.out.println("LienHolder Address matched");
		}
		if(!lienHolder.getLienHolderFederalID().equals("")){
			Assert.assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_FEDERALID_NAME).getAttribute("value").trim(),lienHolder.getLienHolderFederalID().replaceAll("Federal Identification Number ","").trim());
			System.out.println("LienHolder Federal id matched");
		}
		if(!lienHolder.getLienHolderStateID().equals("")){
			Assert.assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_STATEID_NAME).getAttribute("value").trim(),lienHolder.getLienHolderStateID().replaceAll("State Identification Number ","").trim());
			System.out.println("LienHolder State id matched");
		}
	}

	/**
	 * This method Edits Input fields for Lienholder Section on Title License Section
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void editLienholderInfo() throws Exception {
		List<WebElement> tLRSections = BrowserAction.getElements(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TLANDR_SECTIONS_HEADER_XPATH);
		for(WebElement tLRSection: tLRSections) {
			String tLRSectionText = tLRSection.getText();
			if(tLRSectionText.equals("Lienholder Information")) {
		System.out.println("Updated LienHolder Name:- "+CommonPage.getTestData("EditedLienHolderName"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_OWNER_NAME,CommonPage.getTestData("EditedLienHolderName").trim());
		System.out.println("Updated LienHolder Address Line 1:- "+CommonPage.getTestData("EditedLienHolderAddressLine1"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_ADDRESS_NAME,CommonPage.getTestData("EditedLienHolderAddressLine1"));
		System.out.println("Updated LienHolder City:- "+CommonPage.getTestData("LienholderCity"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_CITY_NAME,CommonPage.getTestData("LienholderCity"));
		System.out.println("Updated LienHolder Zip Code:- "+CommonPage.getTestData("LienholderZipCode"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_ZIP_NAME,CommonPage.getTestData("LienholderZipCode"));
		System.out.println("Updated LienHolder State:- "+CommonPage.getTestData("LienholderState"));
		CommonPage.selectDropdownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_STATE_XPATH,CommonPage.getTestData("LienholderState"));
		System.out.println("Updated LienHolder Federal ID:- "+CommonPage.getTestData("FederalId"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_FEDERALID_NAME,CommonPage.getTestData("FederalId"));
		System.out.println("Updated LienHolder State ID:- "+CommonPage.getTestData("StateId"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_STATEID_NAME,CommonPage.getTestData("StateId"));
		clickOnSaveOrder();
			}
		}
	}

	/**
	 * This method assert Input fields are Edited & Saved for Lienholder Section on Title License Section
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyEditedLienholderInfoMatches() throws Exception {
		List<WebElement> tLRSections = BrowserAction.getElements(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TLANDR_SECTIONS_HEADER_XPATH);
		for(WebElement tLRSection: tLRSections) {
			String tLRSectionText = tLRSection.getText();
			if(tLRSectionText.equals("Lienholder Information")) {
				verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_OWNER_NAME,CommonPage.getTestData("EditedLienHolderName").trim());
				verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_ADDRESS_NAME,CommonPage.getTestData("EditedLienHolderAddressLine1"));
				verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_CITY_NAME,CommonPage.getTestData("LienholderCity"));
				verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_ZIP_NAME,CommonPage.getTestData("LienholderZipCode"));
				verifyDropdownTextMatchestoTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_STATE_XPATH,CommonPage.getTestData("LienholderState"));
				verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_FEDERALID_NAME,CommonPage.getTestData("FederalId"));
				verifyInputTextMatchesToTestData(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_LICENSE_SECTION_LIENHOLDER_STATEID_NAME,CommonPage.getTestData("StateId"));
			}
		}
	}

	/**
	 * This method verifies vehicle text from title and registration Page.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyStateAndVehicleText(ElementOrder orderTabsData) throws Exception{
		Registration registrationDetails = orderTabsData.getBillingAndRegistrationTabObject().getRegistrationSectionObject();
		Assert.assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_SECTION_VEHICLETEXT_XPATH).getText().trim(),registrationDetails.getVehicleRegisteredText().trim());
	}
	
	/**
	 * This method verifies all the details from title and registration Page.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyAllTheDetailsOfTitleRegistrationPage() throws Exception {
		assertAllTitleRegisrtationLabelsAreDisplayed();
		verifyTitleDetailsWithCodes(CommonPage.getElementOrderObject());
		verifyRegistrationDetailsWithCodes(CommonPage.getElementOrderObject());
		List<WebElement> tLRSections = BrowserAction.getElements(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TLANDR_SECTIONS_HEADER_XPATH);
		for(WebElement tLRSection: tLRSections) {
			String tLRSectionText = tLRSection.getText();
			if(tLRSectionText.equals("Lienholder Information")) {
				verifyLienholderDetailsWithCodes(CommonPage.getElementOrderObject());
			}
		}
		verifyStateAndVehicleText(CommonPage.getElementOrderObject());
	}

	/**
	 * This method verifies all the edited details and vehicle text from title and registration Page.
	 * @lastModifiedBy sbhosale
	 * @throws Exception
	 */
	public static void verifyAllTheEditedDetailsAndVehicleTextOfTitleRegistrationPage() throws Exception {
		verifyVehicleRegisteredTextMatchesWithBillingPageText(CommonPage.getElementOrderObject());
		verifyEditedTitleInfoMatches();
		verifyEditedRegistrationInfoMatches();
		verifyEditedLienholderInfoMatches();
	}

	/**
	 * This method will enter vehicle year value in Year text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleYear() throws Exception {
		System.out.println("Vehicle Year: " + CommonPage.getTestData("Year"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_YEAR_TEXT_NAME, CommonPage.getTestData("Year"));
	}

	/**
	 * This method will verify vehicle year value from Year text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleYear() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_YEAR_TEXT_NAME, CommonPage.getTestData("Year"), "Vehicle year");
	}

	/**
	 * This method will enter vehicle manufacturer value in Manufacturer text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleManufacturer() throws Exception {
		System.out.println("Vehicle Manufacturer: " + CommonPage.getTestData("Manufacturer"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MANUFACTURER_TEXT_NAME, CommonPage.getTestData("Manufacturer"));
	}

	/**
	 * This method will verify vehicle manufacturer value from Manufacturer text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleManufacturer() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MANUFACTURER_TEXT_NAME, CommonPage.getTestData("Manufacturer"), "Vehicle Manufacturer");
	}

	/**
	 * This method will enter vehicle make value in Make text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleMake() throws Exception {
		System.out.println("Vehicle Make: " + CommonPage.getTestData("Make"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAKE_TEXT_NAME, CommonPage.getTestData("Make"));
	}

	/**
	 * This method will verify vehicle make value from Make text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleMake() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAKE_TEXT_NAME, CommonPage.getTestData("Make"), "Vehicle Make");
	}

	/**
	 * This method will enter vehicle model value in Model text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleModel() throws Exception {
		System.out.println("Vehicle Model: " + CommonPage.getTestData("Model"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MODEL_TEXT_NAME, CommonPage.getTestData("Model"));
	}

	/**
	 * This method will verify vehicle model value from Model text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleModel() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MODEL_TEXT_NAME, CommonPage.getTestData("Model"), "Vehicle Model");
	}

	/**
	 * This method will enter vehicle trim code value in Trim Code text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleTrimCode() throws Exception {
		System.out.println("Vehicle Trim Code: " + CommonPage.getTestData("TrimCode"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TRIM_CODE_TEXT_NAME, CommonPage.getTestData("TrimCode"));
	}

	/**
	 * This method will verify vehicle trim code value from Trim Code text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleTrimCode() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TRIM_CODE_TEXT_NAME, CommonPage.getTestData("TrimCode"), "Vehicle TrimCode");
	}

	/**
	 * This method will enter vehicle trim value in Trim text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleTrim() throws Exception {
		System.out.println("Vehicle Trim: " + CommonPage.getTestData("Trim"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TRIM_TEXT_NAME, CommonPage.getTestData("Trim"));
	}

	/**
	 * This method will verify vehicle trim value from Trim text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleTrim() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TRIM_TEXT_NAME, CommonPage.getTestData("Trim"), "Vehicle Trim");
	}

	/**
	 * This method will enter vehicle trim description value in Trim descr text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleTrimDesc() throws Exception {
		System.out.println("Vehicle Trim Description: " + CommonPage.getTestData("TrimDescription"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TRIM_DESCR_TEXT_NAME, CommonPage.getTestData("TrimDescription"));
	}

	/**
	 * This method will verify vehicle trim description value from Trim descr text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleTrimDesc() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TRIM_DESCR_TEXT_NAME, CommonPage.getTestData("TrimDescription"), "Vehicle Trim Description");
	}

	/**
	 * This method will select vehicle fuel type value from fuel type drop down in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectVehicleFuelType() throws Exception {
		System.out.println("Vehicle Fuel Type: " + CommonPage.getTestData("FuelType"));
		CommonPage.selectDropDownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_FUEL_TYPE_NAME, CommonPage.getTestData("FuelType"), "Vehicle fuel type");
	}

	/**
	 * This method will verify vehicle fuel type value from fuel type text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleFuelType() throws Exception {
		CommonPage.verifyDropDownSelectedValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_FUEL_TYPE_NAME, CommonPage.getTestData("FuelType"), "Vehicle Fuel Type");
	}

	/**
	 * This method will enter vehicle body style code value in body style code text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleBodyStyleCode() throws Exception {
		System.out.println("Vehicle Body Style Code: " + CommonPage.getTestData("BodyStyleCode"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BODY_STYLE_CODE_NAME, CommonPage.getTestData("BodyStyleCode"));
	}

	/**
	 * This method will verify vehicle body style code value from body style code text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleBodyStyleCode() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BODY_STYLE_CODE_NAME, CommonPage.getTestData("BodyStyleCode"), "Vehicle Body Style Code");
	}

	/**
	 * This method will enter vehicle body style description value in body style desc text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleBodyStyleDesc() throws Exception {
		System.out.println("Vehicle Body Style Description: " + CommonPage.getTestData("BodyStyleDescription"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BODY_STYLE_DESCR_NAME, CommonPage.getTestData("BodyStyleDescription"));
	}

	/**
	 * This method will verify vehicle body style description value from body style desc text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleBodyStyleDesc() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BODY_STYLE_DESCR_NAME, CommonPage.getTestData("BodyStyleDescription"), "Vehicle Body Style Description");
	}

	/**
	 * This method will select vehicle product class value from product class drop down in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectVehicleProductClass() throws Exception {
		System.out.println("Vehicle Product Class: " + CommonPage.getTestData("ProductClass"));
		CommonPage.selectDropDownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_PRODUCT_CLASS_NAME, CommonPage.getTestData("ProductClass"), "Vehicle product class");
	}

	/**
	 * This method will verify vehicle product class value from product class text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleProductClass() throws Exception {
		CommonPage.verifyDropDownSelectedValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_PRODUCT_CLASS_NAME, CommonPage.getTestData("ProductClass"), "Vehicle Product Class");
	}

	/**
	 * This method will enter vehicle ACode value in Acode text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleACode() throws Exception {
		System.out.println("Vehicle ACode: " + CommonPage.getTestData("Acode"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_ACODE_NAME, CommonPage.getTestData("Acode"));
	}

	/**
	 * This method will verify vehicle ACode value from Acode text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleACode() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_ACODE_NAME, CommonPage.getTestData("Acode"), "Vehicle ACode");
	}

	/**
	 * This method will enter vehicle transmission value in transmission text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleTransmission() throws Exception {
		System.out.println("Vehicle Transmission: " + CommonPage.getTestData("Transmission"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TRANSMISSION_NAME, CommonPage.getTestData("Transmission"));
	}

	/**
	 * This method will verify vehicle transmission value from transmission text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleTransmission() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TRANSMISSION_NAME, CommonPage.getTestData("Transmission"), "Vehicle Transmission");
	}

	/**
	 * This method will enter vehicle engine value in engine text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleEngine() throws Exception {
		System.out.println("Vehicle Engine: " + CommonPage.getTestData("Engine"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_ENGINE_NAME, CommonPage.getTestData("Engine"));
	}

	/**
	 * This method will verify vehicle engine value from engine text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleEngine() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_ENGINE_NAME, CommonPage.getTestData("Engine"), "Vehicle Engine");
	}

	/**
	 * This method will enter vehicle wheel base value in wheel base text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleWheelBase() throws Exception {
		System.out.println("Vehicle Wheelbase : " + CommonPage.getTestData("WheelBase"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_WHEELBASE_NAME, CommonPage.getTestData("WheelBase"));
	}

	/**
	 * This method will verify vehicle wheel base value from wheel base text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleWheelBase() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_WHEELBASE_NAME, CommonPage.getTestData("WheelBase"), "Vehicle Wheel Base");
	}

	/**
	 * This method will enter vehicle GVWR value in GVWR text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleGVWR() throws Exception {
		System.out.println("Vehicle GVWR: " + CommonPage.getTestData("GVWR"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_GVWR_NAME, CommonPage.getTestData("GVWR"));
	}

	/**
	 * This method will verify vehicle GVWR value from GVWR text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleGVWR() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_GVWR_NAME, CommonPage.getTestData("GVWR"), "Vehicle GVWR");
	}

	/**
	 * This method will enter vehicle seat capacity value in seat capacity text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleSeatCapacity() throws Exception {
		System.out.println("Vehicle Seat Capacity: " + CommonPage.getTestData("SeatCapacity"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_SEAT_CAPACITY_NAME, CommonPage.getTestData("SeatCapacity"));
	}

	/**
	 * This method will verify vehicle seat capacity value from seat capacity text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleSeatCapacity() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_SEAT_CAPACITY_NAME, CommonPage.getTestData("SeatCapacity"), "Vehicle Seat Capacity");
	}

	/**
	 * This method will enter vehicle number of doors value in number of doors text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterVehicleNumberOfDoors() throws Exception {
		System.out.println("Vehicle Number of Doors: " + CommonPage.getTestData("NumberOfDoors"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NUMBER_OF_DOORS_NAME, CommonPage.getTestData("NumberOfDoors"));
	}

	/**
	 * This method will verify vehicle number of doors value from number of doors text box in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleNumberOfDoors() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NUMBER_OF_DOORS_NAME, CommonPage.getTestData("NumberOfDoors"), "Vehicle Number Of Doors");
	}

	/**
	 * This method will click on Vehicle Specification tab in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToVehicleTabInBO() throws Exception {
		clickOnTabInBOUsingId(OrderingBOOrdMaintPageEnum.ORDERING_BO_ON_ORDER_VEHICLE_TAB_ID, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will wait to load vehicle specification section in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForVehicleTabToLoad() throws Exception {
		waitForTabToLoadInBOUsingName(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_YEAR_TEXT_NAME, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will edit vehicle specification info in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void editVehicleSpecificationInfo() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("New Vehicle Specification");
		OrderingBOOrdMaintPage.enterVehicleYear();
		OrderingBOOrdMaintPage.enterVehicleManufacturer();
		OrderingBOOrdMaintPage.enterVehicleMake();
		OrderingBOOrdMaintPage.enterVehicleModel();
		OrderingBOOrdMaintPage.enterVehicleTrimCode();
		OrderingBOOrdMaintPage.enterVehicleTrim();
		OrderingBOOrdMaintPage.enterVehicleTrimDesc();
		OrderingBOOrdMaintPage.selectVehicleFuelType();
		OrderingBOOrdMaintPage.enterVehicleBodyStyleCode();
		OrderingBOOrdMaintPage.enterVehicleBodyStyleDesc();
		OrderingBOOrdMaintPage.selectVehicleProductClass();
		OrderingBOOrdMaintPage.enterVehicleACode();
		OrderingBOOrdMaintPage.enterVehicleTransmission();
		OrderingBOOrdMaintPage.enterVehicleEngine();
		OrderingBOOrdMaintPage.enterVehicleWheelBase();
		OrderingBOOrdMaintPage.enterVehicleGVWR();
		OrderingBOOrdMaintPage.enterVehicleSeatCapacity();
		OrderingBOOrdMaintPage.enterVehicleNumberOfDoors();
	}

	/**
	 * This method will verify edited vehicle specification info in BO
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyVehicleSpecificationInfo(String className) throws Exception {
		OrderingBOOrdMaintPage.verifyPDFAcknowldgeButton(className);
		OrderingBOOrdMaintPage.verifyVehicleYear();
		OrderingBOOrdMaintPage.verifyVehicleManufacturer();
		OrderingBOOrdMaintPage.verifyVehicleMake();
		OrderingBOOrdMaintPage.verifyVehicleModel();
		OrderingBOOrdMaintPage.verifyVehicleTrimCode();
		OrderingBOOrdMaintPage.verifyVehicleTrim();
		OrderingBOOrdMaintPage.verifyVehicleTrimDesc();
		OrderingBOOrdMaintPage.verifyVehicleFuelType();
		OrderingBOOrdMaintPage.verifyVehicleBodyStyleCode();
		OrderingBOOrdMaintPage.verifyVehicleBodyStyleDesc();
		OrderingBOOrdMaintPage.verifyVehicleProductClass();
		OrderingBOOrdMaintPage.verifyVehicleACode();
		OrderingBOOrdMaintPage.verifyVehicleTransmission();
		OrderingBOOrdMaintPage.verifyVehicleEngine();
		OrderingBOOrdMaintPage.verifyVehicleWheelBase();
		OrderingBOOrdMaintPage.verifyVehicleGVWR();
		OrderingBOOrdMaintPage.verifyVehicleSeatCapacity();
		OrderingBOOrdMaintPage.verifyVehicleNumberOfDoors();
	}

	/**
	 * This method will edit and verify edited vehicle specification info in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyPDFAcknowldgeButton(String className) throws Exception {
		OrderingBOOrdMaintPage.goToVehicleTabInBO();
		OrderingBOOrdMaintPage.waitForVehicleTabToLoad();
		OrderingBOOrdMaintPage.clickApproveButtonVehicle();
		OrderingBOOrdMaintPage.verifyApproveButtonVehicleClicked();
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.orderAcknowledgeAndVerifyAcknowledgeStatus();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("New Vehicle Specification");
		OrderingBOOrdMaintPage.waitForVehicleTabToLoad();
		OrderingBOOrdMaintPage.verifyApproveButtonVehicleDisabled();
		OrderingSummaryPage.clickExportButton("PO", className);

	}

	/**
	 * This method will edit and verify edited vehicle specification info in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void editAndVerifyVehicleSpecificationInformation(String className) throws Exception {
		OrderingBOOrdMaintPage.editVehicleSpecificationInfo();
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.goToVehicleTabInBO();
		OrderingBOOrdMaintPage.waitForVehicleTabToLoad();
		OrderingBOOrdMaintPage.verifyVehicleSpecificationInfo(className);
	}

	/**
	 * This method will select Lease term from Billing info in BO on maintenance page
	 * @lastModifiedBy shisingh
	 */
	public static void selectLeaseTerm() throws Exception {
		if(!(CommonPage.getTestData("LeaseTermBO")==null)) {
			CommonPage.selectDropDownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BILLING_LEASE_TERM_NAME, CommonPage.getTestData("LeaseTermBO"), "Lease term");			
		}
	}

	/**
	 * This method will verify lease term from billing info in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyLeaseTerm() throws Exception {
		CommonPage.verifyDropDownSelectedValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BILLING_LEASE_TERM_NAME, CommonPage.getTestData("LeaseTermBO"), "Lease term");
	}

	/**
	 * This method will enter Asset ID in billing info in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterAssetID() throws Exception {
		System.out.println("Asset ID: " + CommonPage.getTestData("AssetIDBO"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BILLING_ASSET_ID_NAME, CommonPage.getTestData("AssetIDBO"));
	}

	/**
	 * This method will verify Asset ID in billing info in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyAssetID() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BILLING_ASSET_ID_NAME, CommonPage.getTestData("AssetIDBO"), "Asset ID");
	}

	/**
	 * This method will enter Client PO Number in billing info in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterClientPONumber() throws Exception {
		System.out.println("Client Purchase Order Number: " + CommonPage.getTestData("ClientPurchaseOrderNumberBO"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BILLING_CLIENT_PO_NUMBER_NAME, CommonPage.getTestData("ClientPurchaseOrderNumberBO"));
	}

	/**
	 * This method will verify Client PO Number in billing info in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyClientPONumber() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BILLING_CLIENT_PO_NUMBER_NAME, CommonPage.getTestData("ClientPurchaseOrderNumberBO"), "Client Purchase Order Number");
	}

	/**
	 * This method will click on Billing tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToBillingTabInBO() throws Exception {
		clickOnTabInBOUsingId(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BILLING_TAB_ID, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will wait to load Billing section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForBillingTabToLoad() throws Exception {
		waitForTabToLoadInBOUsingName(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BILLING_LEASE_TERM_NAME, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will edit billing info in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void editBillingInformation() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Billing");
		OrderingBOOrdMaintPage.selectLeaseTerm();
		OrderingBOOrdMaintPage.enterAssetID();
		OrderingBOOrdMaintPage.enterClientPONumber();
	}

	/**
	 * This method will verify edited billing info in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyBillingInformation() throws Exception {
		OrderingBOOrdMaintPage.goToBillingTabInBO();
		OrderingBOOrdMaintPage.waitForBillingTabToLoad();
		OrderingBOOrdMaintPage.verifyLeaseTerm();
		OrderingBOOrdMaintPage.verifyAssetID();
		OrderingBOOrdMaintPage.verifyClientPONumber();
	}

	/**
	 * This method will click on DIO tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnDIOAdd() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_ADD_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_ADD_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_ADD_BUTTON_ID);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_ADD_BUTTON_ID);
	}

	/**
	 * This method will enter all values of DIO on DIO tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterDIOvalues() throws Exception {
		System.out.println("DIO Option Code: " + CommonPage.getTestData("DioOptCode"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_OPTION_CODE_NAME, CommonPage.getTestData("DioOptCode"));
		System.out.println("DIO Description: " + CommonPage.getTestData("DioOptDescription"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_DESCRIPTION_NAME, CommonPage.getTestData("DioOptDescription"));
		System.out.println("DIO Quantity: " + CommonPage.getTestData("AdhocDioQty"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_QUANTITY_NAME, CommonPage.getTestData("AdhocDioQty"));
		System.out.println("DIO Option Price: " + CommonPage.getTestData("AdhocDioPrice"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_OPTION_PRICE_NAME, CommonPage.getTestData("AdhocDioPrice"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_DPO_XPATH);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_DPO_XPATH);
	}

	/**
	 * This method will verify text from input field
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyTextFromInputField(Enum<?> fieldtLocatorEnum, String fieldInput, String fieldName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		assertEquals(BrowserAction.getElementAttributeValue(fieldtLocatorEnum, "value"), fieldInput, fieldName+" is not matching");
	}


	/**
	 * This method will verify all values of DIO on DIO tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyDIOvalues() throws Exception {
		verifyTextFromInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_OPTION_CODE_NAME, CommonPage.getTestData("DioOptCode"), "Option Code");
		verifyTextFromInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_DESCRIPTION_NAME, CommonPage.getTestData("DioOptDescription"), "Description");
		verifyTextFromInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_QUANTITY_NAME, CommonPage.getTestData("AdhocDioQty"), "Quantity");
		verifyTextFromInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_OPTION_PRICE_NAME, CommonPage.getTestData("AdhocDioPrice"), "Option price");
		verifyToggles(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_DPO_XPATH, "On", "DIO DPO");
		verifyToggles(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_ADHOC_OPTION_XPATH, "On", "DIO Adhoc Option");
	}

	/**
	 * This method will verify toggle enable/disable in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyToggles(Enum<?> fieldtLocatorEnum, String mode, String fieldName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		String toggleColor = null;
		if(BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.xpath"))
			toggleColor = Color.fromString(WebDriverAccess.getDriver().findElement(By.xpath(fieldtLocatorEnum.toString())).getCssValue("background-color")).asHex();
		else if(BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.id"))
			toggleColor = Color.fromString(WebDriverAccess.getDriver().findElement(By.id(fieldtLocatorEnum.toString())).getCssValue("background-color")).asHex();
		else if(BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.name"))
			toggleColor = Color.fromString(WebDriverAccess.getDriver().findElement(By.name(fieldtLocatorEnum.toString())).getCssValue("background-color")).asHex();
		else if(BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.cssSelector"))
			toggleColor = Color.fromString(WebDriverAccess.getDriver().findElement(By.cssSelector(fieldtLocatorEnum.toString())).getCssValue("background-color")).asHex();
		else if(BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.className"))
			toggleColor = Color.fromString(WebDriverAccess.getDriver().findElement(By.className(fieldtLocatorEnum.toString())).getCssValue("background-color")).asHex();
		else
			throw new NoIfElseBlockMatchedException();
		System.out.println("Toggle Color: " + toggleColor);
		switch(mode) {
			case "On":
				if(!(toggleColor.equals("#82c341")))
					throw new OrderingErrorOccured(fieldName+" toggle is not disable");
				break;
			case "Off":
				if(!(toggleColor.equals("#808080")))
					throw new OrderingErrorOccured(fieldName+" toggle is enable");
				break;
			default : throw new OrderingErrorOccured("wrong toggle parameter passed");
		}

	}

	/**
	 * This method will click on DIO tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToDIOTabInBO() throws Exception {
		OrderingBOOrdMaintPage.clickOnTabInBOUsingId(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_TAB_ID, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will wait to load DIO section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForDIOTabToLoad() throws Exception {
		waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_ADD_BUTTON_ID, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will Add and Verify DIO info in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void addDIOInformation() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Dealer Installed Options");
		OrderingBOOrdMaintPage.clickOnDIOAdd();
		OrderingBOOrdMaintPage.enterDIOvalues();
	}

	/**
	 * This method will verify DIO info in BO on maintenance page
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDIOInformation() throws Exception {
		OrderingBOOrdMaintPage.goToDIOTabInBO();
		OrderingBOOrdMaintPage.waitForDIOTabToLoad();
		OrderingBOOrdMaintPage.verifyDIOvalues();
		OrderingBOOrdMaintPage.clickOnDIOAdd();
		OrderingBOOrdMaintPage.enterDuplicateDIOvalues();
		OrderingBOOrdMaintPage.saveOrder();
		OrderingBOOrdMaintPage.verifyDuplicateOptionCodeMessage();
		OrderingBOOrdMaintPage.enterUniqueDIOvalues();
		OrderingBOOrdMaintPage.clickSaveOrder();
	}

	/**
	 * This method will click on Dealer tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToDealerTabInBO() throws Exception {
		clickOnTabInBOUsingId(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_TAB_ID, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will wait to load Dealer section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForDealerTabToLoad() throws Exception {
		waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_DELIVERING_DEALER_CODE_NAME, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will enter delivering dealer code in dealer tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterDeliveringDealerCode() throws Exception {
		System.out.println("Delivering Dealer Code: " + CommonPage.getTestData("DeliveryDealerCode"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_DELIVERING_DEALER_CODE_NAME, CommonPage.getTestData("DeliveryDealerCode"));
	}

	/**
	 * This method will verify delivering dealer details in dealer tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyDeliveringDealerCode() throws Exception {
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_DELIVERING_DEALER_CODE_NAME, CommonPage.getTestData("DeliveryDealerCode"), "Delivering Dealer Code");
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_DELIVERING_DEALER_CDFEE_NAME, CommonPage.getTestData("DeliveryDealerCDFees"), "Delivering Dealer CD Fee");
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_DELIVERING_DEALER_NAME_NAME, CommonPage.getTestData("DeliveryDealerName"), "Delivering Dealer Name");
		CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_DELIVERING_DEALER_PHONE_NAME, CommonPage.getTestData("DeliveryDealerPhone"), "Delivering Dealer Phone");
	}

	/**
	 * This method will enter Ordering dealer code in dealer tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterOrderingDealerCode() throws Exception {
		if(CommonPage.getTestData("OrderType").equals("Factory")) {
			System.out.println("Ordering Dealer Code: " + CommonPage.getTestData("OrderingDealerCode"));
			CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_ORDERING_DEALER_CODE_NAME, CommonPage.getTestData("OrderingDealerCode"));
		}
	}

	/**
	 * This method will verify Ordering dealer details in dealer tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyOrderingDealerCode() throws Exception {
		if(CommonPage.getTestData("OrderType").equals("Factory")) {
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_ORDERING_DEALER_CODE_NAME, CommonPage.getTestData("OrderingDealerCode"), "Ordering Dealer Code");
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_ORDERING_DEALER_NAME_NAME, CommonPage.getTestData("OrderingDealerName"), "Ordering Dealer Name");
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_ORDERING_DEALER_PHONE_NAME, CommonPage.getTestData("OrderingDealerPhone"), "Ordering Dealer Phone");
		}
		else {
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_ORDERING_DEALER_CODE_NAME, CommonPage.getTestData("OrderingDealerCode"), "Ordering Dealer Code");
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_ORDERING_DEALER_NAME_NAME, CommonPage.getTestData("OrderingDealerName"), "Ordering Dealer Name");
			CommonPage.verifyInputTextFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_ORDERING_DEALER_PHONE_NAME, CommonPage.getTestData("OrderingDealerPhone"), "Ordering Dealer Phone");
		}
	}

	/**
	 * This method will enter Delivering/Ordering dealer code in dealer tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterDeliveringOrderingDealerCode() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Dealer");
		OrderingBOOrdMaintPage.enterDeliveringDealerCode();
		OrderingBOOrdMaintPage.enterOrderingDealerCode();
	}

	/**
	 * This method will verify Delivering/Ordering dealer details in dealer tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyDeliveringOrderingDealerCode() throws Exception {
		OrderingBOOrdMaintPage.goToDealerTabInBO();
		OrderingBOOrdMaintPage.waitForDealerTabToLoad();
		OrderingBOOrdMaintPage.verifyDeliveringDealerCode();
		OrderingBOOrdMaintPage.verifyOrderingDealerCode();
	}

	/**
	 * This method will click on Title and License tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToTitleAndLicenseTabInBO() throws Exception {
		clickOnTabInBOUsingId(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TITLE_LICENSE_TAB_ID, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will wait to load Title and License section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForTitleAndLicenseTabToLoad() throws Exception {
		waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TITLE_LICENSE_PLATE_TYPE_NAME, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will verify Who to Title option on Title & Registration Section
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyWhoToTitle() throws Exception {
		CommonPage.verifyDropDownSelectedValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_SECTION_WHO_TO_TITLE_XPATH, CommonPage.getTestData("WhoToTitleBO"), "Who to Title");
	}

	/**
	 * This method will verify plate type option in Title and License tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyPlateType() throws Exception {
		CommonPage.verifyDropDownSelectedValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_SECTION_PLATE_TYPE_XPATH,CommonPage.getTestData("PlateTypeBO"), "Plate Type");
	}

	/**
	 * This method will edit plate type and who to title in Title and License tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void editTitleAndLicense() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Title, Registration & Lienholder");
		CommonPage.verifyDropDownSelectedValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_SECTION_PLATE_TYPE_XPATH, CommonPage.getTestData("PlateType"), "Plate Type");
		CommonPage.verifyDropDownSelectedValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_TITLE_AND_REGISTRATION_SECTION_WHO_TO_TITLE_XPATH, CommonPage.getTestData("WhoToTitleOption"), "Who to Title");
		OrderingBOOrdMaintPage.selectPlateType();
		OrderingBOOrdMaintPage.selectWhoToTitle();
	}

	/**
	 * This method will verify plate type and who to title in Title and License tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyTitleAndLicense() throws Exception {
		OrderingBOOrdMaintPage.goToTitleAndLicenseTabInBO();
		OrderingBOOrdMaintPage.waitForTitleAndLicenseTabToLoad();
		OrderingBOOrdMaintPage.verifyPlateType();
		OrderingBOOrdMaintPage.verifyWhoToTitle();
	}

	/**
	 * This method will click on Upfit tab in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToUpfitTabInBO() throws Exception {
		OrderingBOOrdMaintPage.clickOnTabInBOUsingId(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_IFNO_TAB_ID, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will wait to load Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForUpfitTabToLoad() throws Exception {
		OrderingBOOrdMaintPage.waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_ADD_BUTTON_ID, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will clickon Add button in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnAddButton() throws Exception {
		OrderingBOOrdMaintPage.waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_ADD_BUTTON_ID, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_ADD_BUTTON_ID);
	}

	/**
	 * This method will wait to load add purchase order page in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForAddPOPageToLoad() throws Exception {
		OrderingBOOrdMaintPage.waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SEARCH_ORDER_ID, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will search and select upfit spec in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void searchAndSelectUpfitSpec() throws Exception {
		OrderingBOOrdMaintPage.waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_UPFIT_SPEC_SEARCH_ID, CommonPage.getTestData("WaitTime"));
		System.out.println("Upfit Spec Search: " + CommonPage.getTestData("UpfitSpecBO"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_UPFIT_SPEC_SEARCH_ID, CommonPage.getTestData("UpfitSpecBO"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
	}

	/**
	 * This method will search and select upfitter in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void searchAndSelectUpfitter() throws Exception {
		OrderingBOOrdMaintPage.waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_UPFITTER_SEARCH_ID, CommonPage.getTestData("WaitTime"));
		System.out.println("Upfitter Search: " + CommonPage.getTestData("UpfitterSupplierName"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_UPFITTER_SEARCH_ID, CommonPage.getTestData("UpfitterSupplierName"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
	}

	/**
	 * This method will search and select installer in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void searchAndSelectInstaller() throws Exception {
		OrderingBOOrdMaintPage.waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_INSTALLER_SEARCH_ID, CommonPage.getTestData("WaitTime"));
		System.out.println("Installer: " + CommonPage.getTestData("InstallerSupplierName"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_INSTALLER_SEARCH_ID, CommonPage.getTestData("InstallerSupplierName"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
	}

	/**
	 * This method will select ship to in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectShipTo() throws Exception {
		CommonPage.selectDropDownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SHIP_TO_ID, CommonPage.getTestData("ShipTo"), "Ship To");
	}

	/**
	 * This method will select ship to in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectWorkType() throws Exception {
		CommonPage.selectDropDownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_WORK_TYPE_ID, CommonPage.getTestData("WorkType"), "Work Type");
	}

	/**
	 * This method will search and select drop ship in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void searchAndSelectDropShip() throws Exception {
		OrderingBOOrdMaintPage.waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_DROP_SHIP_SEARCH_NAME, CommonPage.getTestData("WaitTime"));
		System.out.println("Drop Ship: " + CommonPage.getTestData("DropShip"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_DROP_SHIP_SEARCH_NAME, CommonPage.getTestData("DropShip"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SELECT_SEARCH_OPTION_XPATH);
	}

	/**
	 * This method will click on Save in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnSave() throws Exception {
		OrderingBOOrdMaintPage.waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SAVE_ID, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SAVE_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingCommonPage.verifyNoValidationError();
	}

	/**
	 * This method will verify supplier id in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifySupplierId() throws Exception {
		waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SUPPLIER_ID_XPATH, CommonPage.getTestData("WaitTime"));
		assertEquals(BrowserAction.getElement((OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SUPPLIER_ID_XPATH)).getText(), CommonPage.getTestData("UpfitterSupplierName"), "Supplier id is not matching");
	}

	/**
	 * This method will Click on PO in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnPO() throws Exception {
		waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SUPPLIER_ID_XPATH, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SUPPLIER_ID_XPATH);
	}

	/**
	 * This method will Click on Cancel PO button in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnCancelPO() throws Exception {
		waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_CANCEL_PO_XPATH, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_CANCEL_PO_XPATH);
	}

	/**
	 * This method will Click on Cancel PO OK button in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnCancelPOOK() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_CANCEL_PO_OK_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_CANCEL_PO_OK_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_CANCEL_PO_OK_XPATH);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_CANCEL_PO_OK_XPATH);
	}

	/**
	 * This method will Click on Cancel PO OK button in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnProceedCancelPO() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_PROCEED_CANCEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_PROCEED_CANCEL_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_PROCEED_CANCEL_XPATH);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_PROCEED_CANCEL_XPATH);
	}

	/**
	 * This method will select all details for PO in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void addUpfitInformation() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Upfit");
		OrderingBOOrdMaintPage.clickOnAddButton();
		OrderingBOOrdMaintPage.waitForAddPOPageToLoad();
		OrderingBOOrdMaintPage.searchAndSelectUpfitSpec();
		OrderingBOOrdMaintPage.selectWorkType();
		OrderingBOOrdMaintPage.searchAndSelectUpfitter();
		OrderingBOOrdMaintPage.searchAndSelectInstaller();
		OrderingBOOrdMaintPage.selectShipTo();
		OrderingBOOrdMaintPage.searchAndSelectDropShip();
		OrderingBOOrdMaintPage.clickOnSave();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingCommonPage.verifyNoValidationError();
	}

	/**
	 * This method will verify all details for PO in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyUpfitInformation() throws Exception {
		goToUpfitTabInBO();
		waitForUpfitTabToLoad();
		verifySupplierId();
	}

	/**
	 * This method will will cancel PO in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void cencelPO() throws Exception {
		clickOnPO();
		clickOnCancelPO();
		clickOnCancelPOOK();
		clickOnProceedCancelPO();
	}

	/**
	 * This method will verify cancel PO status in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyCencelledPO() throws Exception {
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitForMaintenancePage();
		goToUpfitTabInBO();
		waitForUpfitTabToLoad();
		waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_ORDER_STATUS_XPATH, CommonPage.getTestData("WaitTime"));
		String orderStatus = BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_ORDER_STATUS_XPATH).getText();
		if(!(orderStatus.contains("Cancelled")))
			throw new Exception("PO is not cancelled");
	}

	/**
	 * This method will verify cancel PO status in Upfit section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void cancelPOAndVerify() throws Exception {
		cencelPO();
		verifyCencelledPO();
	}

	/**
	 * This method will select credit indicator option in approval section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectCreditOption() throws Exception {
		CommonPage.selectDropDownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_APPROVAL_CREDIT_ERROR_NAME, "A - Credit Approved", "Credit drop down");
	}

	/**
	 * This method will verify credit indicator option in approval section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyCreditOption() throws Exception {
		String dropDownValue = new Select(WebDriverAccess.getDriver().findElement(By.name(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_APPROVAL_CREDIT_ERROR_NAME.getValue()))).getFirstSelectedOption().getText();
		if(dropDownValue.trim().equals("F - Credit Check Failed")||dropDownValue.trim().equals("D - Credit Denied"))
			throw new AssertionError("Credit dropdown value should be N - No Credit Error or A - Credit Approved");
	}

	/**
	 * This method will verify credit indicator option in approval section in BO on maintenance page
	 * @throws Throwable 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectApprovalInformation() throws Throwable {
		OrderingBOOrdMaintPage.goToApprovalSection();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Approval");
		OrderingBOOrdMaintPage.checkAndRemoveAllSoftApprovalErrorsIfPresentOnMaintenance();
	}

	/**
	 * This method will verify approval info in approval section in BO on maintenance page
	 * @throws Throwable 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyApprovalInformation() throws Throwable {
		goToApprovalSection();
		waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_APPROVAL_CREDIT_ERROR_NAME, CommonPage.getTestData("WaitTime"));
		verifyCreditOption();
		List<String> errorList = OrderingBOOrdMaintPage.getErrorList();
		if(!(errorList.isEmpty())) {
			OrderingBOOrdMaintPage.checkAndRemoveAllSoftApprovalErrorsIfPresentOnMaintenance();
		}
	}

	/**
	 * This method will acknowledge the order in vehicle section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnAcknowledge() throws Exception {
		goToVehicleTabInBO();
		OrderingBOOrdMaintPage.waitForVehicleTabToLoad();
		waitForTabToLoadInBO(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_VEHICLE_ACK_BUTTON_ID, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_VEHICLE_ACK_BUTTON_ID);
	}

	/**
	 * This method will verify acknowledge status in general order section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyAcknowledgeStatus() throws Exception {
		waitForGeneralInfoTabToLoad();
		assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_GENERAL_ORDER_ORDER_STATUS_XPATH).getText().trim(), "AJ0040 - Acknowledged", "Order Status is not changed to Acknowledge");
	}

	/**
	 * This method will verify acknowledge status in general order section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void orderAcknowledgeAndVerifyAcknowledgeStatus() throws Exception {
		if(CommonPage.getTestData("OrderType").equals("Factory")) {
			clickOnAcknowledge();
			verifyAcknowledgeStatus();
		}
	}

	/**
	 * This method will verify upfit po on maintenance page which is added in Add purchase order details page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyPOIdAndName() throws Exception {
		ArrayList<String> supplierIdstr = new ArrayList<String>();
		ArrayList<String> supplierNamestr = new ArrayList<String>();
		List<WebElement> supplierId = BrowserAction.getElements(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SUPPLIER_ID_XPATH);
		List<WebElement> supplierName = BrowserAction.getElements(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_UPFIT_PO_SUPPLIER_NAME_XPATH);
		for(WebElement ele : supplierId) {
			supplierIdstr.add(ele.getText());
		}
		if(!(supplierIdstr.contains(CommonPage.getElementOrderObject().getVehicleTabObject().getUpfitInformationSectionObject().getSpecificAvailableUpfitSpecifications(0).getSupplierId())))
			throw new OrderingErrorOccured("Upfit PO supplier id "+ CommonPage.getElementOrderObject().getVehicleTabObject().getUpfitInformationSectionObject().getSpecificAvailableUpfitSpecifications(0).getSupplierId() +" is not matching");
		for(WebElement ele : supplierName) {
			supplierNamestr.add(ele.getText());
		}
		if(!(supplierNamestr.contains(CommonPage.getElementOrderObject().getVehicleTabObject().getUpfitInformationSectionObject().getSpecificAvailableUpfitSpecifications(0).getSupplierName())))
			throw new OrderingErrorOccured("Upfit PO supplier name "+ CommonPage.getElementOrderObject().getVehicleTabObject().getUpfitInformationSectionObject().getSpecificAvailableUpfitSpecifications(0).getSupplierName() +" is not matching");
	}

	/**
	 * This method will verify upfit po on maintenance page which is added in Add purchase order details page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyUpfitPO() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Upfit");
		OrderingBOOrdMaintPage.waitForUpfitTabToLoad();
		OrderingBOOrdMaintPage.verifyPOIdAndName();
	}
	
	/**
	 * This method checks the error list and takes the appropriate action.
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Throwable
	 */
	public static void checkAndRemoveAllSoftApprovalErrorsIfPresentOnMaintenance() throws Throwable {
		List<String> errorList = OrderingBOOrdMaintPage.getErrorList();
		if(!(errorList.isEmpty())) {
			for(String softError: errorList) {
				OrderingBOOrdMaintPage.softErrors(softError);
			}
			OrderingBOQueuePage.gotoQueuesPage();
			OrderingBOQueuePage.queuePageLoaded();
			OrderingBOQueuePage.gotoQueue("On-Order");
			OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
			OrderingBOOrdMaintPage.clickOnSingleSearchResult("logNumber");
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			OrderingCommonPage.checkAlertPopUp();
			OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();		
		}
	}
	
	/**
	 * This method will verify Order status
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Throwable
	 */
	public static void verifyOrderStatus() throws Throwable {
		OrderingBOQueuePage.gotoCreatedOrder();
		OrderingBOOrdMaintPage.verifyChangedOrderStatus(CommonPage.getTestData("CustomColumn1"));
	}
	
	/**
	 * This method will on DOE toggle and verify
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Throwable
	 */
	public static void changeDOEToggleAndVerify() throws Throwable {
		OrderingBOOrdMaintPage.changeDOEIndicator();
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.waitForMaintenancePage();
		OrderingBOOrdMaintPage.verifyDOEIndicator("On");
	}
	
	/**
	 * This method will verify acknowledge status in general order section in BO on maintenance page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyAcknowledgeStatusForOEM() throws Exception {
		waitForGeneralInfoTabToLoad();
		assertEquals(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_GENERAL_ORDER_ORDER_STATUS_XPATH).getText().trim(), CommonPage.getTestData("CustomColumn2"), "Order Status is not changed to Acknowledge");
	}
	
	 /** This method cancels order
	 * This method updates the details from General Order tab for PO Amendment 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void editGeneralOrderInfo() throws Exception {
		OrderingBOOrdMaintPage.enterNewFONumber(CommonPage.generateRandomNumber());
		OrderingBOOrdMaintPage.enterNewFODate();
		System.out.println("New VIN Text: "+RandomStringUtils.randomAlphanumeric(17));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_NEW_VIN_TEXT_FIELD_NAME, RandomStringUtils.randomAlphanumeric(17));
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method updates the details from New Vehicle Specification tab
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void editNewVehicleSpecification() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("New Vehicle Specification");
		System.out.println("Updated Vehicle Spec Year: "+CommonPage.getTestData("Year"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_YEAR_NAME, CommonPage.getTestData("Year"));
		System.out.println("Updated Vehicle Spec Make: "+CommonPage.getTestData("Make"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_MAKE_NAME, CommonPage.getTestData("Make"));
		System.out.println("Updated Vehicle Spec Model: "+CommonPage.getTestData("Model"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_MODEL_NAME, CommonPage.getTestData("Model"));
		System.out.println("Updated Vehicle Spec Trim: "+CommonPage.getTestData("Trim"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_TRIM_NAME, CommonPage.getTestData("Trim"));
		System.out.println("Updated Vehicle Spec Acode: "+CommonPage.getTestData("Acode"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_ACODE_NAME, CommonPage.getTestData("Acode"));
		System.out.println("Updated Vehicle Spec WheelBase: "+CommonPage.getTestData("WheelBase"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_NEW_VEH_SPEC_WHEELBASE_NAME, CommonPage.getTestData("WheelBase"));
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method updates the details from Title and Registration tab
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void editTitleAndRegistration() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Title, Registration & Lienholder");
		CommonPage.selectDropdownValue(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_TITLEANDREGISTRATION_WHOTOTITLEDRPDWN_XPATH, "Dealer");
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method updates the ufit Information from BO for PO Amendment validation
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void editUpfitInfo() throws Exception {
		System.out.println("Updated supplier: "+CommonPage.getTestData("UpfitterSearch"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_LINE_ITEM_SUPPLIER_TEXT_SEARCH_XPATH, CommonPage.getTestData("UpfitterSearch"));	
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_LINE_ITEM_SUPPLIER_FIRST_SEARCH_XPATH);
		System.out.println("Updated work type: "+CommonPage.getTestData("WorkType"));
		CommonPage.selectDropdownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_LINE_ITEM_SUPPLIER_WORK_TYPE_XPATH, CommonPage.getTestData("WorkType"));
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method validates Send PO appears again on amendment of order
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateOrderAmendment() throws Exception {
		OrderingBOOrdMaintPage.goToUpfitSection();
		OrderingBOOrdMaintPage.clickUpfitLineItem();
		OrderingBOOrdMaintPage.verifySendPONowButtonIsDisplayed();
		OrderingBOOrdMaintPage.verifyResendPOButtonIsDisplayed();
		OrderingBOOrdMaintPage.clickSendPONowButton();
		OrderingBOOrdMaintPage.isEmailPopupDisplayed();
		OrderingBOOrdMaintPage.clickSendButtonOnEmailPopup((JavascriptExecutor)WebDriverAccess.getDriver());
		OrderingBOOrdMaintPage.goToUpfitSection();
		OrderingBOOrdMaintPage.clickUpfitLineItem();
		OrderingBOOrdMaintPage.validateSendPONowButtonChange();
	}
	
	/**
	 * This method cancels order
	 * @throws Exception
	 * @lastModifiedBy mkaricharla
	 */
	public static void cancelOrder() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_CANCEL_ORDER_XPATH);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_CANCEL_ORDER_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_CANCEL_ORDER_YES_XPATH);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_CANCEL_ORDER_YES_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}

	/**
	 * This method reomves Used Unit from Order
	 * @throws Exception
	 * @lastModifiedBy sbhosale
	 */
	public static void removeUsedUnitFromOrder() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_REMOVE_USED_UNIT_ID);
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_REMOVE_USED_UNIT_ID);
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}

	
	/**
	 * This method verifies the error message generated while adding DIO with duplicate option code 
	 *  @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDuplicateOptionCodeMessage() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 1));
		assertEquals(WebDriverAccess.getDriver().findElement(By.cssSelector("div.noty_bar")).getText(), "Duplicate option codes not allowed in Dealer Installed Options.", "Error message is not matching");
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method will enter duplicate DIO option code values
	 * @lastModifiedBy vikumar
	 */
	public static void enterDuplicateDIOvalues() throws Exception {
		System.out.println("DIO Option code entered: "+CommonPage.getTestData("DioOptCode"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_SEC_DIO_OPTION_CODE_NAME, CommonPage.getTestData("DioOptCode"));
		System.out.println("DIO description entered: "+CommonPage.getTestData("DioOptDescription"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_SEC_DIO_DESCRIPTION_NAME, CommonPage.getTestData("DioOptDescription"));
		System.out.println("DIO quantity entered: "+CommonPage.getTestData("AdhocDioQty"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_SEC_DIO_QUANTITY_NAME, CommonPage.getTestData("AdhocDioQty"));
		System.out.println("DIO option price entered: "+CommonPage.getTestData("AdhocDioPrice"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_SEC_DIO_OPTION_PRICE_NAME, CommonPage.getTestData("AdhocDioPrice"));
	}
	
	/**
	 * This method clicks on the save order button.
	 * @lastModifiedBy vikumar
	 * @throws Exception
	 */
	public static void saveOrder() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_ORDER_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_ORDER_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_ORDER_ID);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_ORDER_ID)));
		BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_SAVE_ORDER_ID);
	}
	
	/**
	 * This method will enter unique DIO option code values
	 * @lastModifiedBy vikumar
	 */
	public static void enterUniqueDIOvalues() throws Exception {
		System.out.println("DIO Option code entered: "+CommonPage.getTestData("DioOptCode"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_SEC_DIO_OPTION_CODE_NAME, CommonPage.getTestData("DioOptCode")+"U");
		System.out.println("DIO description entered: "+CommonPage.getTestData("DioOptDescription"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_SEC_DIO_DESCRIPTION_NAME, CommonPage.getTestData("DioOptDescription"));
		System.out.println("DIO quantity entered: "+CommonPage.getTestData("AdhocDioQty"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_SEC_DIO_QUANTITY_NAME, CommonPage.getTestData("AdhocDioQty"));
		System.out.println("DIO option price entered: "+CommonPage.getTestData("AdhocDioPrice"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_SEC_DIO_OPTION_PRICE_NAME, CommonPage.getTestData("AdhocDioPrice"));
	}
   
	/**
	 * This method cancel factory order
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void cancelFactoryOrder() throws Exception {
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CANCEL_ORDER_CSS);
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CONFIRM_CANCEL_FACTORY_ORDER_XPATH);
		WebElement confirmCancel=BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_CONFIRM_CANCEL_FACTORY_ORDER_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(confirmCancel));
		confirmCancel.click();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method enters FO Number Date and save the order
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void enterFONumberAndDate() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("General Order");
		OrderingBOOrdMaintPage.enterNewFONumber(CommonPage.generateRandomNumber());
		OrderingBOOrdMaintPage.enterNewFODate();
		OrderingBOOrdMaintPage.saveOrderOnVehicleTab();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method validate the telematics indicator.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 * @param flag
	 */
	public static void validateTelematicsIndicator(String flag) throws Exception {
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_TELEMATICSINDICATOR_XPATH).trim(), flag);
	}
	
	/**
	 * This method Acknowledges Order.
	 * @lastModifiedBy akandkonde
	 * @throws Exception
	 */
	public static void acknowledgeOrder() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Vehicle");
		OrderingBOOrdMaintPage.clickOnAcknowledge();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}

	/**
	 * This method validate the deleted record from BMT To DIO Section
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verfiyDeletedDIOInDealerInstalledOptionSection() throws Exception {
		OrderingBOQueuePage.gotoCreatedOrder();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Dealer Installed Options");
		if(Objects.isNull(BrowserVerify.verifyElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_SECTION_FIRST_ROW_XPATH))) {
			System.out.println("Deleted DIO Record persists");	
		}
		else {
			throw new OrderingErrorOccured("Error in DIO delete Process");
		}
	}

	/**
	 * This method validate estimate cost value To DIO Section
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyEstimateCostValue() throws Exception {
		System.out.println("Entered DIO quantity:: "+dioQuantity);
		BrowserAction.enterFieldValue(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DIO_QUANTITYTXTBOX_NAME, dioQuantity);
		int quantity= Integer.parseInt(dioQuantity);
		System.out.println("Entered DIO price:: "+dioPrice);
		BrowserAction.enterFieldValue(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DIO_OPTIONPRICETXTBOX_NAME, dioPrice);
		int price =Integer.parseInt(dioPrice);
		int estimated =quantity * price ;
		System.out.println(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_ESTIMATED_COST_NAME).getText());
		System.out.println(estimated);
		Assert.assertEquals(Integer.parseInt(BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_ESTIMATED_COST_NAME).getText()), estimated);
	}

	/**
	 * This method validate ethe adhoc button is OFF
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyAdhocButtonOFF() throws Exception {
		if(!BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_SECTION_ADHOCOPTION_NAME).isSelected())
			System.out.println("Adhoc option button is in OFF mode");
	}

	/**
	 * This method verifies BO supplier information icon & tooltip text.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifySupplierToolTip() throws Exception {
		BrowserAssert.assertElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_SUPPLIERTOOLTIP_ICON_XPATH);
		BrowserAssert.assertEquals((BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_SUPPLIERTOOLTIP_ICON_XPATH)).getAttribute("title"),"If toggled, the supplier is to ship this part to the dealer for installation. If untoggled, the dealer is to order and install this part");
	}

	/**
	 * This method verifies the mandetory fields of BO DIO
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyMandetoryFieldsInDIOBO() throws Exception {
		List<WebElement> requiredField = BrowserAction.getElements(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_TABLE_COLUMN_HEADER_XPATH);
		for(WebElement reqColumns :requiredField) {
			if(reqColumns.getAttribute("innerText").contains("*")){	
				System.out.println("Required Columns:" +reqColumns.getAttribute("innerText"));
			}
		}
	}

	/**
	 * This method verifies All the Existing & New columns added BO DIO.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyDioColumnHeading() throws Exception {
		String label;
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn2").split("\\|"));	
		ArrayList<String> tableColumns = new ArrayList<String>();
		List<WebElement> tableElements = BrowserAccess.getElements(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_TABLE_COLUMN_HEADER_XPATH);
		for (WebElement element : tableElements) {
			label = element.getAttribute("innerText").trim();
			if (label != null && !label.equalsIgnoreCase("")) {
				if (label.contains("\n")) {
					label = label.replace("\n", " ");
				}
				tableColumns.add(label);
			}
		}
		Assert.assertTrue(expectedColumnNames.equals(tableColumns), "Table Coulmns are not matching with Test Data");
	}

	/**
	 * This method verifies DIO field label and text present inside the field In BO
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifySearchDIOLabel()  throws Exception {
		BrowserAssert.assertEquals((BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_SEARCH_DIO_LABEL_XPATH)).getText().trim(), "Search for Dealer Installed Options","DIO label text not matching");
		BrowserAssert.assertEquals((BrowserAction.getElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_SEARCH_DIO_OPTION_XPATH)).getAttribute("placeholder"), "Search by a Option Code, Option Description","DIO Option desc  text not matching");
	}

	/**
	 * This method verifies the no result found error msg In BO
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyNoResultFoundErrorMsg() throws Exception {
		System.out.println("Searching using DIO option:: "+CommonPage.getTestData("CustomColumn3"));
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_XPATH", OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_SEARCH_DIO_OPTION_XPATH.getValue()), CommonPage.getTestData("CustomColumn3"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_SUGGESION_ERROR_XPATH);
		BrowserAssert.assertEquals((BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_SUGGESION_ERROR_XPATH)).getText(),"No results found!");
	}

	/**
	 * This method validate the dio tab info  In BO 
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyBODIOInfo() throws Exception {
		OrderingBOOrdMaintPage.verifySearchDIOLabel();
		OrderingBOOrdMaintPage.verifyNoResultFoundErrorMsg();
		OrderingBOOrdMaintPage.verifyDioColumnHeading();
		OrderingBOOrdMaintPage.verifySupplierToolTip();
		OrderingBOOrdMaintPage.verifyMandetoryFieldsInDIOBO();
		OrderingBOOrdMaintPage.verifyAdhocButtonOFF();
	}

	/**
	 * This method add and edit the dio in dio section in BO.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void addAndEditDio() throws Exception {
		OrderingBOQueuePage.gotoCreatedOrder();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("DIO");
		OrderingBOOrdMaintPage.addNewDIO();
		OrderingBOOrdMaintPage.verifyFieldsAreEditable();
	}

	/**
	 * This method validate the fields are editable
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyFieldsAreEditable() throws Exception {
		System.out.println("Option Code entered in TO field: "+CommonPage.getTestData("DioOptCode"));
		CommonPage.enterTextToInputField(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SECOND_DIO_OPTIONCODETEXTBOX_XPATH, CommonPage.getTestData("DioOptCode"));
		System.out.println("Dio Description entered in TO field: "+CommonPage.getTestData("DioOptDescription"));
		CommonPage.enterTextToInputField(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SECOND_DIO_DESCRIPTIONTXTBOX_XPATH, CommonPage.getTestData("DioOptDescription"));
		OrderingBOOrdMaintPage.clickOnSaveDIOButton();
	}

	/**
	 * This method add the dio in dio section in BO.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void addNewDIO() throws Exception {
		BrowserAction.click(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_DIOADDBTN_XPATH);
		System.out.println("Entering DIO option code:: "+CommonPage.getTestData("DioOptCode"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SECOND_DIO_OPTIONCODETEXTBOX_XPATH, CommonPage.getTestData("DioOptCode"));
		System.out.println("Entering DIO option description:: "+CommonPage.getTestData("DioOptDescription"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SECOND_DIO_DESCRIPTIONTXTBOX_XPATH, CommonPage.getTestData("DioOptDescription"));
		System.out.println("Entering DIO quantity:: "+CommonPage.getTestData("DioQuantity"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SECOND_DIO_QUANTITYTXTBOX_XPATH, CommonPage.getTestData("DioQuantity"));
		System.out.println("Entering DIO price:: "+CommonPage.getTestData("DioPrice"));
		BrowserAction.enterFieldValue(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SECOND_DIO_OPTIONPRICETXTBOX_XPATH, CommonPage.getTestData("DioPrice"));
	} 

    /**
	 * This updates the order status
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void updateOrderStatus() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("General Order");
		System.out.println("Entering order status code:: "+CommonPage.getTestData("CustomColumn1"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_STATUS_TEXT_FIELD_NAME, CommonPage.getTestData("CustomColumn1"));
		System.out.println("Entering date in status date field:: "+CommonPage.getCurrentDataIn("MM/dd/yyyy"));
		CommonPage.enterTextToInputField(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_STATUS_DATE_FIELD_XPATH, CommonPage.getCurrentDataIn("MM/dd/yyyy"));
		System.out.println("Selecting status dropdown value:: "+CommonPage.getTestData("CustomColumn2"));
		CommonPage.selectDropdownValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_OOQ_ORDER_STATUS_STATE_DROPDOWN_NAME, CommonPage.getTestData("CustomColumn2"));
		OrderingBOOrdMaintPage.saveAddStatusSection();
	}
	
	/**
	 * This method validate the dio tab info  In BO 
	 * @lastModifiedBy mkhairnar
	 * @throws Exception
	 */
	public static void verifyBODIOTab() throws Exception {
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DIO_TAB_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_LABEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_SEARCH_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_COUNT_OF_TOTAL_RECORD_COUNT_XPATH);	
	}
	
    /**
     * This method validate Delete button functionality In BO tab 
	 * @lastModifiedBy mkhairnar
	 * @throws Exception
	 */
	public static void verifyDIODelete() throws Exception {
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_BO_FIRST_DIO_XPATH);
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_BO_SECOND_DIO_XPATH);	
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_BO_DELETE_ID);
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BO_YES_XPATH);
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_BO_DIO_SAVE_XPATH);  
	}
	
	/**
	 * This method validate add button functionality In BO tab 
	 * @lastModifiedBy mkhairnar
	 * @throws Exception
	 */
	public static void verifyDIOAdd() throws Exception {
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_BO_Add_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_DIO_COUNT_OF_TOTAL_RECORD_COUNT_XPATH);	
	}
	
	/**
	 * This method will add upfitting details for adhoc upfir while creating a new order in FO
	 * @lastModifiedBy Deepika
	 * @throws Exception
	 */
	public static  void addFOUpfitDetails() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_FO_AUPO_ADDUPFITPURCHASEORDER_BUTTON_XPATH);
			BrowserAction.click(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_FO_AUPO_ADDUPFITPURCHASEORDER_BUTTON_XPATH);
			BrowserAction.click(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_FO_AUPO_ADDLINEITEM_BUTTON_XPATH);
			BrowserAction.enterFieldValue(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_FO_AUPO_CODE_XPATH, CommonPage.getTestData("UpfitLineItemCode"));
			BrowserAction.selectDropdownOptionByText(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_FO_UPFIT_CATEGORY_XPATH, CommonPage.getTestData("UpfitLineItemCategory"));
			BrowserAction.enterFieldValue(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_FO_UPFIT_DESC_XPATH, CommonPage.getTestData("UpfitLineItemDesc"));
			BrowserAction.enterFieldValue(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_FO_UPFIT_OPTION_PRICE_XPATH, CommonPage.getTestData("UpfitLineItemOptPrice"));
			}
	 catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Not able to add UpfitSpec details");
	 }		
	}
	
	/**
	 * This method will navigate user to upfitting tab in fleet spec detail screen while creating new order in FO
	 * @lastModifiedBy Deepika
	 * @throws Exception
	 */
	public static void goToUpfitTab() {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_FO_ORD_MAINT_UPFITTAB_XPATH);
			BrowserAction.click(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_FO_ORD_MAINT_UPFITTAB_XPATH);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_FO_AUPO_ADDUPFITPURCHASEORDER_BUTTON_XPATH);
			Thread.sleep(3000);
		}
		catch(Exception e) {
			System.out.println("Upfit tab not present in FO");
		}
	}
	
	/**
	 * This method Statically removes Dealer Error Till ORD-49335 is fixed
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void removeDealerErrorStatically() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DELIVERING_DEALER_CODE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DELIVERING_DEALER_CODE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DELIVERING_DEALER_CODE_TEXTBOX_XPATH);
		BrowserAction.enterFieldValue(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DELIVERING_DEALER_CODE_TEXTBOX_XPATH, "41-Y531");
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_DEALER_INFO_SAVE_BUTTON_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Approval");
		List<String> errorList = OrderingBOOrdMaintPage.getErrorList();
		if(errorList.contains("Dealer")) {
			throw new OrderingErrorOccured("The Dealer Soft Error is Still Present");
		}
	}
	
	/**
	 * This method shifts the control to upfit Window
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void moveToUpfitWindow() throws Exception {
		parentWindowHandle=WebDriverAccess.getDriver().getWindowHandle();
		Set <String> allWindowHandles=WebDriverAccess.getDriver().getWindowHandles();
		for(String s:allWindowHandles) {
			if(!(s.equals(parentWindowHandle)))
				WebDriverAccess.getDriver().switchTo().window(s);
		}
		OrderingBOOrdMaintPage.waitUntilUpfitPageLoaded();
	}
	
	/**
	 * This method switch control to order maintenance page
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void navigateToParentWindow() throws Exception {
		WebDriverAccess.getDriver().close();
		WebDriverAccess.getDriver().switchTo().window(parentWindowHandle);
		WebDriverAccess.getDriver().navigate().refresh();
	}
	
	/**
	 * This method wait until upfit page is loaded
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void waitUntilUpfitPageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFIT_ESTIMEATED_LEAD_TIME_WEEK_POS_ID);
	}
	
	/**
	 * This method clicks on save button under Add Status section
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void saveAddStatusSection() throws Exception {
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_ADD_STATUS_SAVE_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoValidationError();
	}

	/**
	 * This method clicks on the save order button on Title & license section
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void clickOnSaveOrder() throws Exception {
		goToTitleAndRegistrationTab();
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_TITLE_LICENSE_SAVE_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoValidationError();
	}

	public static void clickOnSaveDIOButton() throws Exception {
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_DIO_SAVE_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoValidationError();
	}
}

