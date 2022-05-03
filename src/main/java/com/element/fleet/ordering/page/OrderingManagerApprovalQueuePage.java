package com.element.fleet.ordering.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.ExcelUtil;
import com.element.fleet.ordering.enums.OrderingHomePageEnum;
import com.element.fleet.ordering.enums.OrderingManagerApprovalMaintenancePageEnum;
import com.element.fleet.ordering.enums.OrderingManagerApprovalQueuePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingManagerApprovalQueuePage {
	
	/**
	 * This method waits for the Manager Approval Queue page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForManagerApprovalQueueToLoad() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_HEADING_ID);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_HEADING_ID);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_HEADING_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_FILTER_SECTION_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_FILTER_TABLE_HEAD_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_FILTER_TABLE_BODY_CSS);
	}
	
	/**
	 * This method validates for label on the Manager Approval Maintenance page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void validateLabelOnManagerApprovalMaintenancePage() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_HEADING_ID), "Manager Approval Queue", "Manager Approval Queue page heading not matched");
	}
	
	/**
	 * This method clicks on the first element of the row.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnTheFirstRecordIfAvailable() throws Exception {
		List<WebElement> tableRows = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_TABLE_ROW_XPATH);
		if((tableRows.size()==1)&&(tableRows.get(0).getText().contains("No Results Found")))
			System.out.println("No element available to click\n"+BrowserAction.getElementText(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_TABLE_ROW_XPATH));
		else {
			tableRows.get(0).click();
			OrderingCommonPage.checkAlertPopUp();
			OrderingSummaryPage.waitForSummaryPage();
			OrderingManagerApprovalQueuePage.closeOrderSummaryOnManagerApprovalQueuePage();
		}	
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method clicks on Close button of order on Manager Approval Queue page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void closeOrderSummaryOnManagerApprovalQueuePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_FRONT_OFFICE_CLOSE_SUMMARY_PAGE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_FRONT_OFFICE_CLOSE_SUMMARY_PAGE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_FRONT_OFFICE_CLOSE_SUMMARY_PAGE_CSS);
		BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_FRONT_OFFICE_CLOSE_SUMMARY_PAGE_CSS);
	}
	
	/**
	 * This method approves orders with approver user ids.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void allApproverActionsOnOrders(String action, String approvalAction) throws Throwable {
		if(!Objects.isNull(CommonPage.getTestData("ApproverUserIDs"))) {
			String[] approvalUserIDs = CommonPage.getTestData("ApproverUserIDs").split("\\|");
			boolean isLastOrder = false;
			for(int i=1; i<approvalUserIDs.length; ++i) {
				if(i==(approvalUserIDs.length-1)) {
					isLastOrder = true;
				}
				OrderingManagerApprovalQueuePage.bulkOrSingleActionOnOrder(CommonPage.getTestData("ApproverID"+i), action, approvalAction, isLastOrder);
			}
		}
	}
	
	/**
	 * This method approves or reject order using approver id.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void bulkOrSingleActionOnOrder(String approverUserId, String action, String actionType, boolean isLastOrder) throws Throwable {
		OrderingLoginPage.openCustomFOApplication(approverUserId);
		
		OrderingHomePage.waitForHomePageMLO();
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Queue");
		OrderingManagerApprovalQueuePage.waitForManagerApprovalQueueToLoad();
		switch(action) {
			case "Single":
				OrderingManagerApprovalQueuePage.singleActionType(actionType);
				break;
			case "Bulk":
				OrderingManagerApprovalQueuePage.bulkApproveActionType(actionType, isLastOrder);
				OrderingManagerApprovalQueuePage.verifyNoOrdersInManagerApprovalQueue();
				break;
			default: throw new OrderingErrorOccured(action + " is a invalid action");
		}		
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	/**
	 * This method performs action on order in single flow.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void singleActionType(String actionType) throws Throwable {
		int singleOrderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		for(int j=1; j<=singleOrderCount; ++j) {
			OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+j));
			OrderingMyOrdersPage.clickOnSearch();
			OrderingManagerApprovalQueuePage.selectOrderInManagerApprovalQueue();
			OrderingManagerApprovalQueuePage.performOrderAction(actionType, false);
			OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Single Logger Approval");
			OrderingManagerApprovalQueuePage.verifyPopUpMessage("Single Logger Approval", CommonPage.getTestData("LoggerLogNumber"+j));
			PDFReporter.takeExtraScreenshot();
			OrderingManagerApprovalQueuePage.verifyPopUpMessage("Close Alert Popup", "");
			OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+j));
			OrderingMyOrdersPage.clickOnSearch();
			OrderingManagerApprovalQueuePage.verifyNoOrderInManagerApprovalQueue();
		}
	}
	
	/**
	 * This method verifys that given log numbers are not available in Manager Approval Queue.
	 * @throws Exception 
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void verifyNoOrdersInManagerApprovalQueue() throws Exception {
		int singleOrderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		for(int j=1; j<=singleOrderCount; ++j) {
			OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+j));
			OrderingMyOrdersPage.clickOnSearch();
			OrderingManagerApprovalQueuePage.verifyNoOrderInManagerApprovalQueue();
		}
	}
	
	/**
	 * This method performs action on order in bulk flow.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void bulkApproveActionType(String actionType, boolean isLastOrder) throws Throwable {
		int logOrderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		List<String> logNumbersList = new ArrayList<>();
		for(int j=1; j<=logOrderCount; ++j) {
			logNumbersList.add(CommonPage.getTestData("LoggerLogNumber"+j));
		}
		System.out.println("Log Numbers in List: " + logNumbersList);
		List<WebElement> logRows = BrowserAction.getElements(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_SELECT_MULTIPLE_TABLE_ROW_XPATH);
		String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
				+ "var elementTop = arguments[0].getBoundingClientRect().top;"
				+ "window.scrollBy(0, elementTop-(viewPortHeight/2));";
		for(WebElement logRow: logRows) {
			((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript(scrollElementIntoMiddle, logRow);
			String rowLogNumber = logRow.findElement(By.xpath(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_ROW_XPATH.getValue())).getText().trim();
			if(logNumbersList.isEmpty())
				break;				
			for(String log: logNumbersList) {
				if(log.equals(rowLogNumber)) {
					new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(logRow.findElement(By.xpath(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_CHECKBOX_ROW_XPATH.getValue())))).click();
					logNumbersList.remove(log);
					break;
				}
			}
		}
		if(!(logNumbersList.isEmpty())) {
			throw new OrderingErrorOccured("Only "+ logNumbersList.size() + " order is checked out of " + logOrderCount);
		}
		OrderingManagerApprovalQueuePage.performOrderAction(actionType, isLastOrder);
	}
	
	/**
	 * This method checks the order log is displayed and clicks on it.
	 * @throws Exception 
	 * @lastModifiedBy shisingh
	 */
	public static void selectOrderInManagerApprovalQueue() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_SELECT_SINGLE_TABLE_ROW_XPATH.getValue()), 1));
		BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_SELECT_SINGLE_TABLE_ROW_XPATH);
	}
	
	/**
	 * This method checks the no order log is displayed.
	 * @throws Exception 
	 * @lastModifiedBy shisingh
	 */
	public static void verifyNoOrderInManagerApprovalQueue() {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_SELECT_SINGLE_TABLE_ROW_XPATH.getValue()), 0));
	}
	
	/**
	 * This method is used to click on approve or reject button.
	 * @throws Throwable 
	 * @lastModifiedBy  shisingh
	 */
	public static void performOrderAction(String action, boolean isLastOrder) throws Throwable {
		switch(action) {
			case "Approve":
				OrderingManagerApprovalQueuePage.clickApprove();
				OrderingManagerApprovalQueuePage.verifyPopUpMessage("Bulk Logger Approval - Confirmation", "");
				OrderingManagerApprovalQueuePage.bulkOrderApprovalAlertPopUp("Yes");
				OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Bulk Logger Approval");
				if(isLastOrder) {
					OrderingManagerApprovalQueuePage.verifyPopUpMessage("Bulk Logger Approval", "");
					PDFReporter.takeExtraScreenshot();
					OrderingManagerApprovalQueuePage.verifyPopUpMessage("Close Alert Popup", "");			
				} else {
					PDFReporter.takeExtraScreenshot();
				}
				break;
			case "Reject":
				OrderingManagerApprovalQueuePage.clickReject();
				OrderingManagerApprovalQueuePage.verifyPopUpMessage("Bulk Logger Reject", "");
				OrderingManagerApprovalQueuePage.bulkOrderRejectAlertPopUp("Yes");
				OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Bulk Logger Approval");
				break;
			default: throw new InvalidSwitchCaseException(action + " is invalid action");
		}
	}
	
	/**
	 * This method is click on approve .
	 * @throws Exception 
	 * @throws Throwable 
	 * @lastModifiedBy  shisingh
	 */
	public static void clickApprove() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_APPROVE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_APPROVE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_APPROVE_CSS);
		BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_APPROVE_CSS);	
		System.out.println("Approve button clicked");
	}
	
	/**
	 * This method is click on reject .
	 * @throws Exception 
	 * @throws Throwable 
	 * @lastModifiedBy  shisingh
	 */
	public static void clickReject() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_REJECT_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_REJECT_CSS);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_REJECT_CSS);
		BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_REJECT_CSS);
	}
	
	/**
	 * This methods verifies alert popUpMessage.
	 * @throws Throwable 
	 * @lastModifiedBy ssrivastava
	 */
	public static void verifyAlertPopUpMessage(String section) throws Throwable {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 1));
		String alertPopUpText = new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.noty_bar"))).getText();
		switch(section) {
			case "Single Logger Approval": 
				Assert.assertEquals(alertPopUpText, "Orders were successfully approved and submitted.", "Logger order approval pop up message is incorrect");
				PDFReporter.takeExtraScreenshot();
				break;
			case "Single Logger Reject": 
				Assert.assertEquals(alertPopUpText, "Reject information was saved", "Logger order reject pop up message is incorrect");
				PDFReporter.takeExtraScreenshot();
				break;
			case "Bulk Logger Reject": 
				Assert.assertEquals(alertPopUpText, "Reject information was saved", "Bulk logger order approval pop up message is incorrect");
				PDFReporter.takeExtraScreenshot();
				break;
			case "Bulk Logger Approval": 
				Assert.assertEquals(alertPopUpText, "Bulk request has been queued.", "Bulk logger order approval pop up message is incorrect");
				PDFReporter.takeExtraScreenshot();
				break;
			case "Save Order": 
				Assert.assertEquals(alertPopUpText, "Approvers have been successfully updated.", "Save order pop up message is incorrect");
				PDFReporter.takeExtraScreenshot();
				break;
			default: throw new InvalidSwitchCaseException(section + " is a invalid section");
		}
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This methods verifies popUpMessage.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyPopUpMessage(String section, String logNumber) throws Exception {
		switch(section) {
			case "Close Alert Popup": 
				BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CLOSE_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CLOSE_CSS);
				BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CLOSE_CSS);
				BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CLOSE_CSS);
				break;
			case "Single Logger Approval": 
				BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_MESSAGE_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_MESSAGE_CSS);
				BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_MESSAGE_CSS);
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_MESSAGE_CSS), "Final Approval Received. Orders listed below have been Submitted to Element.", "Single logger order approval pop up message is incorrect");
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_LOGNUMBER_CSS), logNumber, "Single logger order approval pop up log number is incorrect");
				break;
			case "Bulk Logger Approval - Confirmation": 
				BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_MESSAGE_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_MESSAGE_CSS);
				BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_MESSAGE_CSS);
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_MESSAGE_CSS), "Are you sure you want to approve the selected records?", "Bulk logger order approval pop up message is incorrect");
				break;
			case "Bulk Logger Approval": 
				BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_MESSAGE_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_MESSAGE_CSS);
				BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_MESSAGE_CSS);
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_MESSAGE_CSS), "Final Approval Received. Orders listed below have been Submitted to Element.", "Bulk logger order approval pop up message is incorrect");
				List<WebElement> popUpLogRows = BrowserAction.getElements(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_ROW_CSS);
				int logOrderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
				List<String> logNumbersList = new ArrayList<>();
				for(int j=1; j<=logOrderCount; ++j) {
					logNumbersList.add(CommonPage.getTestData("LoggerLogNumber"+j));
				}
				for(WebElement popUpLogRow: popUpLogRows) {
					if(logNumbersList.isEmpty())
						break;
					for(String log: logNumbersList) {
						String rowLogNumber = popUpLogRow.findElement(By.xpath(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_LOGNUMBER_ROW_XPATH.getValue())).getText().trim();
						if(log.equals(rowLogNumber)) {
							logNumbersList.remove(log);
							break;
						}
					}					
				}
				if(!(logNumbersList.isEmpty())) {
					throw new OrderingErrorOccured("Only "+ logNumbersList.size() + " order is verified out of " + logOrderCount);
				}
				break;
			case "Single Logger Reject":
			case "Bulk Logger Reject": 
				BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_MESSAGE_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_MESSAGE_CSS);
				BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_MESSAGE_CSS);
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_MESSAGE_CSS), "Are you sure you want to reject this order?", "Bulk logger order reject pop up message is incorrect");
				break;
			case "Single Logger Cancel": 
				BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CANCEL_MESSAGE_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CANCEL_MESSAGE_CSS);
				BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CANCEL_MESSAGE_CSS);
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CANCEL_MESSAGE_CSS), "Are you sure you want to cancel the selected records?", "Bulk logger order cancel pop up message is incorrect");
				break;
			default: throw new InvalidSwitchCaseException(section + " is a invalid section");
		}
	}
	
	/**
	 * This methods takes action on bulk order approval.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void bulkOrderApprovalAlertPopUp(String action) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_MESSAGE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_MESSAGE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_MESSAGE_CSS);
		switch(action) {
			case "Yes": 
				BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_OK_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_OK_CSS);
				BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_OK_CSS);
				BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_OK_CSS);
				break;
			case "No": 
				BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_CANCEL_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_CANCEL_CSS);
				BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_CANCEL_CSS);
				BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_BULK_ALERT_POP_UP_CANCEL_CSS);
				break;
			default: throw new InvalidSwitchCaseException(action + " is a invalid bulk alert pop action");
		}
	}
	
	/**
	 * This methods takes action on bulk order reject.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void bulkOrderRejectAlertPopUp(String action) throws Exception {
		switch(action) {
			case "Yes": 
				BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_TEXT_AREA_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_TEXT_AREA_CSS);
				BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_TEXT_AREA_CSS);
				String rejectBulkMessage = "ATMessage" + CommonPage.generateRandomNumber();
				BrowserAction.clickandClear(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_TEXT_AREA_CSS);
				System.out.println("Bulk reject message: " + rejectBulkMessage);
				BrowserAction.enterFieldValue(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_TEXT_AREA_CSS, rejectBulkMessage);
				BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_OK_BUTTON_CSS);
				break;
			case "No": 
				BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_CLOSE_BUTTON_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_CLOSE_BUTTON_CSS);
				BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_CLOSE_BUTTON_CSS);
				BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_REJECT_CLOSE_BUTTON_CSS);
				break;
			default: throw new InvalidSwitchCaseException(action + " is a invalid bulk alert pop action");
		}
	}
	
	/**
	 * This methods waits for side menu to be loaded for external user.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void orderingSideMenuOptionLoadedExternalUser() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_TITLE_LABEL_CLASS);
		BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_TITLE_LABEL_CLASS);
		BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_TITLE_LABEL_CLASS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_CREATE_ORDER_LINK_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_BATCH_TEMPLATE_DOWNLOAD_HYPERLINK_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_WORK_IN_PROCESS_ORDERS_HYPERLINK_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_MY_ORDERS_HYPERLINK_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_MANAGER_APPROVAL_QUEUE_HYPERLINK_XPATH);
	}
	
	/**
	 * This methods takes action on bulk order cancel.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void bulkOrderCancelAlertPopUp(String action) throws Exception {
		switch(action) {
			case "Yes": 
				BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CANCEL_OK_BUTTON_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CANCEL_OK_BUTTON_CSS);
				BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CANCEL_OK_BUTTON_CSS);
				BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_CANCEL_OK_BUTTON_CSS);
				break;
			default: throw new InvalidSwitchCaseException(action + " is a invalid bulk alert pop action");
		}
	}
	
	/**
	 * This method enters log number in maintenance Queue page
	 * @lastModifiedBy sagrawal
	 * @param logNumber
	 * @throws Exception
	 */
	public static void enterLogNumber(String logNumber) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_TEXTBOX_XPATH);
		BrowserAction.clickandClear(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_TEXTBOX_XPATH);
		System.out.println("Log Number: " + logNumber);
		BrowserAction.enterFieldValue(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_TEXTBOX_XPATH, logNumber);
	}
	
	/**
	 * This method will click on search button
	 * @lastModifiedBy Sweety Agrawal
	 */
	public static void clickOnSearch() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_SEARCH_BUTTON_XPATH);
		BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_SEARCH_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method will enter text in log number field
	 * @lastModifiedBy Sweety Agrawal
	 */
	public static void enterTextInLogNumberSearchFieldFO(String logNumber) throws Exception {
		if(Objects.isNull(logNumber)) {
			throw new OrderingErrorOccured("Log Number column is blank in test data");
		} else {
			BrowserAction.getElement(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_TEXTBOX_XPATH).clear();
			System.out.println("Log Number: " + logNumber);
			BrowserAction.getElement(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_TEXTBOX_XPATH).sendKeys(logNumber);
		}
	}

	/*
	 * This method clicks on the first element of the row and verifies summary screen is displayed
	 * user remains in summary
	 * @lastModifiedBy hectorJimenez
	 * @throws Exception
	 */
	public static void clickFirstRecordAndValidateSummaryScreen() throws Exception {
		List<WebElement> tableRows = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_TABLE_ROW_XPATH);
		if((tableRows.size()==1)&&(tableRows.get(0).getText().contains("No Results Found")))
			System.out.println("No element available to click\n"+BrowserAction.getElementText(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_TABLE_ROW_XPATH));
		else {
			tableRows.get(0).click();
			OrderingCommonPage.checkAlertPopUp();
			OrderingSummaryPage.waitForSummaryPage();
			WebElement createPDFButton = BrowserAction.getElement(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_CREATEPDF_BUTTON_XPATH);
			Assert.assertEquals(createPDFButton.isDisplayed(), true, "Create PDF button should be present on screen");
		}	
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This methods sorts columns in MAQ screen, and verifies the global spinner is shown 
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void verifySortingMAQScreen() throws Exception {
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		List<WebElement> globalSpinner = null;
		String [] tableHeadersGroup1 = {"logNumber", "corpCode", "clientNumber", "unitNumber", "approvers", "orderType", "upFit", "fleetSpecId", "year", "manufacturer", 
				"make", "model", "trim", "productCode", "estimatedCost"};
		String [] tableHeadersGroup2 = { "vehicleBreakDown", "driverLastName", "driverFirstName", "driverBreakdown",
				"driverState", "garageState", "driverOrder", "lastUpdatedUserId", "lastUpdatedDate", "status"};
		
		for (String tableHeader : tableHeadersGroup1) {
			WebElement header = WebDriverAccess.getElement(By.xpath("//div[@class='dataTables_scrollHead']//th[@data-name='"+tableHeader+"']"));
			header.click();
			globalSpinner = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_GLOBALSPINNER_CSS);
			Assert.assertEquals(globalSpinner.size(), 1, "Global spinner  did not display with this header: " + tableHeader);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
		}
		for (String tableHeader2 : tableHeadersGroup2) {
			WebElement header = WebDriverAccess.getElement(By.xpath("//div[@class='dataTables_scrollHead']//th[@data-name='"+tableHeader2+"']"));
			header.click();
			globalSpinner = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_GLOBALSPINNER_CSS);
			Assert.assertEquals(globalSpinner.size(), 1, "Global spinner  did not display with this header: " + tableHeader2);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			WebElement scrollArea = BrowserAction.getElement(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_TABLE_BODY_CLASS);
	    	((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollLeft = arguments[0].offsetWidth", scrollArea);
		}	 
	}
	/**
	 * This methods clicks cancel button
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void clickCancelButtonSummaryScreen() throws Exception {
		BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_CANCEL_BUTTON_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This methods gets the log number of first displayed record
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static String getLogNumberOfFirstRecord() throws Exception {
		return BrowserAction.getElements(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_ROW_XPATH).get(0).getText();
	}
	
	/**
	 * This methods performs advanced search
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void performAdvancedSearch() throws Exception {
		BrowserAction.hoverOverElement(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_SEARCHOPTIONS_BUTTON_CLASS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ADVANCEDSEARCH_BUTTON_XPATH);
		BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ADVANCEDSEARCH_BUTTON_XPATH);
		OrderingManagerApprovalQueuePage.waitForAdvancedSearchBoxes();
		ArrayList<OrderingManagerApprovalQueuePageEnum> searchBoxes = new ArrayList<>();
		searchBoxes.add(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_PRODUCTCODE_FIELD_XPATH);
		searchBoxes.add(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ESTIMATEDCOST_FIELD_XPATH);
		searchBoxes.add(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_VEHICLEBREAKDOWN_FIELD_XPATH);
		searchBoxes.add(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_DRIVERSTATE_FIELD_XPATH);
		searchBoxes.add(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_GARAGESTATE_FIELD_XPATH);
		searchBoxes.add(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_DRIVERORDER_FIELD_XPATH);
		String [] values = {
				BrowserAction.getElementText(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_PRODUCTCODE_ROW_XPATH),
				BrowserAction.getElementText(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_ESTIMATEDCOST_ROW_XPATH),
				BrowserAction.getElementText(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_VEHICLEBREAKDOWN_ROW_XPATH),
				BrowserAction.getElementText(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_DRIVERSTATE_ROW_XPATH),
				BrowserAction.getElementText(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_GARAGESTATE_ROW_XPATH),
				BrowserAction.getElementText(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_DRIVERORDER_ROW_XPATH),};
		int i = 0;
 		for (OrderingManagerApprovalQueuePageEnum textbox : searchBoxes) {
			BrowserAction.enterFieldValue(textbox, values[i]);
			BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_SEARCH_LOAD_BUTTON_XPATH);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			Assert.assertEquals(BrowserAction.getElements(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_ROW_XPATH).size() > 0, true, "Results should be display for this search");
			BrowserAction.clear(textbox);
			i++;
		}
	}
	/**
	 * This methods waits for advanced search boxes 
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void waitForAdvancedSearchBoxes() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_PRODUCTCODE_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_VEHICLEBREAKDOWN_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ESTIMATEDCOST_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_DRIVERSTATE_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_GARAGESTATE_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_DRIVERORDER_FIELD_XPATH);
	}
	/**
	 * This methods verifies the reset functionality
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void resetSearch() throws Exception {
		int numberOfRowsBeforeReset = BrowserAction.getElements(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_ROW_XPATH).size();
		System.out.println("Rows before Reset: " + numberOfRowsBeforeReset );
		BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_RESET_BUTTON_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		int numberOfRowsAferReset = BrowserAction.getElements(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_ROW_XPATH).size();
		System.out.println("Rows After Reset: " + numberOfRowsAferReset );
		Assert.assertEquals(numberOfRowsBeforeReset < numberOfRowsAferReset, true, "After Reset there should be more rows displayed than 0");
	    BrowserAction.clear(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_LOGNUMBER_TEXTBOX_XPATH);
	}
	/**
	 * This methods verifies the search functionality, searches in all search fields with valid data and verifies there is always at least 1 result shown
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void verifySearchFunctionality() throws Exception {
		String [] searchCategories = { "logNumber", "corpCode", "clientNumber", "unitNumber", "approvers", "orderType", "upFit",
				"fleetSpecId", "year", "manufacturer", "make", "model", "trim", "driverLastName", "driverFirstName", "driverBreakdown"};
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_INPUT_TEXTBOX_XPATH);
		for (String searchCategory : searchCategories) {
			String valueToSendToTextField = WebDriverAccess.getElements(By.xpath("//tr[@class='queueData odd']//td[@data-name='"+searchCategory+"']")).get(0).getText();
			List <WebElement> searchFields = BrowserAction.getElements(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_INPUT_TEXTBOX_XPATH);
			for (WebElement searchField : searchFields) {
				if(searchField.getAttribute("name").equalsIgnoreCase(searchCategory)) {
					if (searchField.getAttribute("name").equalsIgnoreCase("approvers")) {
						String [] approvers = valueToSendToTextField.split("\n");
						valueToSendToTextField = approvers[0];
					}
					searchField.sendKeys(valueToSendToTextField);
					System.out.println("Search text to send: " + valueToSendToTextField);
					BrowserAction.click(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_SEARCH_LOAD_BUTTON_XPATH);
					OrderingCommonPage.checkGlobalSpinnerPopUp();
					Assert.assertTrue(WebDriverAccess.getElements(By.xpath("//tr[@class='queueData odd']//td[@data-name='"+searchCategory+"']")).size() >= 1, "Results should have been displayed for this textbox: " + searchCategory);
				}
				break;
			}
		}
	}
	/**
	 * This method clicks the export button and keeps the file in downloads folder
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyExportFuntionality() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_EXPORT_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_EXPORT_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_EXPORT_BUTTON_XPATH);
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_EXPORT_BUTTON_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	/**
	 * This method verifies the exported CSV contains the expected headers
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyHeadersFromExportCSV() throws Throwable {
		String fileName = System.getProperty("user.home") + "\\Downloads\\" + "Manager Approval Queue.csv";
		String expectedValues = "Log Number,Corp,Client,Unit,Approver 1,Approval 1 Status,Approver 2,Approval 2 Status,Approver 3,Approval 3 Status,Approver 4,Approval 4 Status,Approver 5,Approval 5 Status,Approver 6,Approval 6 Status,Approver 7,Approval 7 Status,Approver 8,Approval 8 Status,Approver 9,Approval 9 Status,Approver 10,Approval 10 Status,Order Type,Upfit,Fleet Spec ID,Year,Manufacturer,Make,Model,Trim,Product Code,Estimated Vehicle Cost,Vehicle Break Down,Driver Last Name,Driver First Name,Driver Break Down,Driver State,Garaging State,Driver Order,Last Updated Userid,Last Updated Date";
		String [] expectedValuesAsList = expectedValues.split(",");
		String [] actualvaluesAsList = ExcelUtil.getCSVRows(fileName).get(0).split(",");
		System.out.println("Actual headers in CSV file: " + ExcelUtil.getCSVRows(fileName).get(0));
		System.out.println("Expected headers: " + expectedValues);
		Assert.assertEquals(expectedValuesAsList, actualvaluesAsList, "Actual and expected headers differ");
	}
	
	/**
	 * This method approves a single order when it is not final resulution
	 * @throws Throwable 
	 * @lastModifiedBy hjimenez
	 */
	public static void approveSingleOrderNotFinal() throws Throwable {
		int singleOrderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		for(int j=1; j<=singleOrderCount; ++j) {
			OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+j));
			OrderingMyOrdersPage.clickOnSearch();
			OrderingManagerApprovalQueuePage.selectOrderInManagerApprovalQueue();
			OrderingManagerApprovalQueuePage.clickApprove();
			BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_INFOSUCCESS_ALERT_CLASS);
			BrowserWait.waitUntilCountOfElementsPresent(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_INFOSUCCESS_ALERT_CLASS, 0);
			PDFReporter.takeExtraScreenshot();
			OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+j));
			OrderingMyOrdersPage.clickOnSearch();
			OrderingManagerApprovalQueuePage.verifyNoOrderInManagerApprovalQueue();
		}
	}
	
	/**
	 * This method verifies that given log number is found on screen
	 * @throws Throwable 
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyLogNumberIsDisplayed() throws Throwable {
		int singleOrderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		for(int j=1; j<=singleOrderCount; ++j) {
			OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+j));
			OrderingMyOrdersPage.clickOnSearch();
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_SELECT_SINGLE_TABLE_ROW_XPATH.getValue()), 1));
		}
	}
	
	/**
	 * This method approves orders with approver user ids.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void approveOrRejectOrders(String action, String approvalAction) throws Throwable {
		if(!(CommonPage.getTestData("ApproverUserIDs")==null)) {
			String[] approvalUserIDs = CommonPage.getTestData("ApproverUserIDs").split("\\|");
			boolean isLastOrder = false;
			for(int i=1; i<approvalUserIDs.length; ++i) {
				if(i==(approvalUserIDs.length-1)) {
					isLastOrder = true;
				}
				switch(action) {
					case "Single":
						OrderingManagerApprovalQueuePage.singleActionType(approvalAction);
						break;
					case "Bulk":
						OrderingManagerApprovalQueuePage.bulkApproveActionType(approvalAction, isLastOrder);
						OrderingManagerApprovalQueuePage.verifyNoOrdersInManagerApprovalQueue();
						break;
					default: throw new OrderingErrorOccured(action + " is a invalid action");
				}
			}
		}
	}
}