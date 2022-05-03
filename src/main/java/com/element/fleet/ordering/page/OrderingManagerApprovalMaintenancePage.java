package com.element.fleet.ordering.page;

import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingManagerApprovalMaintenancePageEnum;
import com.element.fleet.ordering.enums.OrderingManagerApprovalQueuePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;

public class OrderingManagerApprovalMaintenancePage {

	/**
	 * This method waits for the Manager Approval Maintenance page to load.
	 * 
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForManagerApprovalMaintenanceToLoad() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_HEADING_ID);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_HEADING_ID);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_HEADING_ID);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_FILTER_SECTION_CSS);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_FILTER_TABLE_HEAD_CSS);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_FILTER_TABLE_BODY_CSS);
	}

	/**
	 * This method validates for label on the Manager Approval Maintenance page.
	 * 
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void validateLabelOnManagerApprovalMaintenancePage() throws Exception {
		CommonPage.assertLabelHighlight(
				BrowserAction.getElement(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_HEADING_ID),
				"Manager Approval Maintenance", "Manager Approval Maintenance page heading not matched");
	}

	/**
	 * This method clicks on the first element of the row if available.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnTheFirstRecordIfAvailable() throws Exception {
		List<WebElement> tableRows = BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_TABLE_ROW_XPATH);
		if ((tableRows.size() == 1) && (tableRows.get(0).getText().contains("No Results Found")))
			System.out.println("No element available to click\n" + BrowserAction.getElementText(
					OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_TABLE_ROW_XPATH));
		else {
			tableRows.get(0).click();
			OrderingCommonPage.checkAlertPopUp();
			OrderingManagerApprovalMaintenancePage.waitForManagerApprovalMaintenanceScreenToLoad();
			OrderingManagerApprovalMaintenancePage.validateLabelOnManagerApprovalMaintenanceScreenPage();
			OrderingManagerApprovalMaintenancePage.moveBackToManagerApprovalMaintenancePage();
		}
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}

	/**
	 * This method clicks on the first element of the row.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnTheFirstRecord() throws Exception {
		List<WebElement> tableRows = BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_TABLE_ROW_XPATH);
		tableRows.get(0).click();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingManagerApprovalMaintenancePage.waitForManagerApprovalMaintenanceScreenToLoad();
	}

	/**
	 * This method open single order on Manager Approval Maintenance screen.
	 * 
	 * @throws Exception
	 * @lastModifiedBy shisingh
	 */
	public static void openOrderInManagerApprovalMaintenance() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime")))
				.until(ExpectedConditions.numberOfElementsToBe(By.xpath(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_TABLE_ROW_XPATH
								.getValue()),
						1));
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_TABLE_ROW_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingManagerApprovalMaintenancePage.waitForManagerApprovalMaintenanceScreenToLoad();
	}

	/**
	 * This method approves or reject order from Manager Approval Maintenance
	 * screen.
	 * 
	 * @lastModifiedBy shisingh
	 * @param action
	 * @throws Throwable
	 */
	public static void aproveOrRejectOrder(String action) throws Throwable {
		switch(action) {
			case "Approve - From All Approver":
				OrderingManagerApprovalMaintenancePage.checkAllApprovers();
				OrderingManagerApprovalMaintenancePage.clickApproveButton();
				OrderingManagerApprovalMaintenancePage.clickOnPopUp();
				OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Single Logger Approval");
				break;
			case "Reject - From All Approver":
				OrderingManagerApprovalMaintenancePage.checkAllApprovers();
				OrderingManagerApprovalMaintenancePage.clickRejectButton();
				OrderingManagerApprovalMaintenancePage.clickOnPopUp();
				OrderingManagerApprovalQueuePage.verifyPopUpMessage("Bulk Logger Reject", "");
				OrderingManagerApprovalQueuePage.bulkOrderRejectAlertPopUp("Yes");
				OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Single Logger Reject");
				break;
			case "Approve - On Behalf Of Super User":
				OrderingManagerApprovalMaintenancePage.selectAllApprovers();
				OrderingManagerApprovalMaintenancePage.clickApproveButton();
				OrderingManagerApprovalMaintenancePage.clickOnPopUp();
				OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Single Logger Approval");
				break;
			case "Reject - On Behalf Of Super User":
				OrderingManagerApprovalMaintenancePage.selectAllApprovers();
				OrderingManagerApprovalMaintenancePage.clickRejectButton();
				OrderingManagerApprovalMaintenancePage.clickOnPopUp();
				OrderingManagerApprovalQueuePage.verifyPopUpMessage("Bulk Logger Reject", "");
				OrderingManagerApprovalQueuePage.bulkOrderRejectAlertPopUp("Yes");
				OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Single Logger Reject");
				break;
			default: throw new OrderingErrorOccured(action + " is a invalid action"); 
		}
	}

	/**
	 * This method clicks on Approve button.
	 * 
	 * @throws Exception
	 * @lastModifiedBy shisingh
	 */
	public static void clickApproveButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVE_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVE_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVE_BUTTON_CSS);
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVE_BUTTON_CSS);
	}

	/**
	 * This method clicks on Reject button.
	 * 
	 * @throws Exception
	 * @lastModifiedBy shisingh
	 */
	public static void clickRejectButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_BUTTON_CSS);
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_BUTTON_CSS);
	}

	/**
	 * This method clicks on Cancel button.
	 * 
	 * @throws Exception
	 * @lastModifiedBy shisingh
	 */
	public static void clickCancelButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_CANCEL_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_CANCEL_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_CANCEL_BUTTON_CSS);
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_CANCEL_BUTTON_CSS);
	}

	/**
	 * This method clicks on Approve button.
	 * 
	 * @throws Exception
	 * @lastModifiedBy usha naidu
	 */
	public static void clickOnApproveButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_APPROVE_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_APPROVE_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_APPROVE_BUTTON_CSS);
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_APPROVE_BUTTON_CSS);
	}
	
	/**
	 * This method adds delegate last name to all approvers.
	 * 
	 * @throws Exception
	 * @lastModifiedBy shisingh
	 */
	public static void addDelegate() throws Exception {
		if(!Objects.isNull(CommonPage.getTestData("ApproverUserIDs"))) {
			String[] approvalUserIDs = CommonPage.getTestData("ApproverUserIDs").split("\\|");
			for (int i = 1; i < approvalUserIDs.length; ++i) {
				System.out.println("Delegate LastName: " + CommonPage.getTestData("ApproverLastName"));
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(By.xpath(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_DELEGATE_LAST_NAME_TEXTFIELD_XPATH.getValue().replaceAll("approver-id", CommonPage.getTestData("ApproverID"+i))))).sendKeys(CommonPage.getTestData("ApproverLastName"));
				List<WebElement> srchDelegateUserLists = new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_DELEGATE_LAST_NAME_SEARCH_OPTIONS_XPATH.getValue().replaceAll("approver-id", CommonPage.getTestData("ApproverID"+i))), 0));
				List<String> srchDelegateUserNameLists = new ArrayList<>();
				int j = 0;
				for (WebElement srchDelegateUserList : srchDelegateUserLists) {
					String srchDelegateUser = srchDelegateUserList.getText();
					srchDelegateUserNameLists.add(srchDelegateUser);
					if (srchDelegateUser.contains(CommonPage.getTestData("ApproverLastName"))) {
						srchDelegateUserList.click();
						++j;
						break;
					}
				}
				if (j == 0) {
					throw new OrderingErrorOccured(
							"Searched dealer is not present in list " + srchDelegateUserNameLists);
				}
				if (!WebDriverAccess.getDriver().findElement(By.xpath(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_DELEGATE_SELECTED_USER_XPATH
								.getValue().replaceAll("approver-id", CommonPage.getTestData("ApproverID" + i))))
						.getText().contains(CommonPage.getTestData("ApproverLastName"))) {
					throw new OrderingErrorOccured(
							"Delegate user is not selected " + CommonPage.getTestData("ApproverLastName"));
				}
			}
		}
	}

	/**
	 * This method removes delegate last name from all approvers.
	 * 
	 * @throws Exception
	 * @lastModifiedBy shisingh
	 */
	public static void removeDelegate() throws Exception {
		if(!Objects.isNull(CommonPage.getTestData("ApproverUserIDs"))) {
			String[] approvalUserIDs = CommonPage.getTestData("ApproverUserIDs").split("\\|");
			for (int i = 1; i < approvalUserIDs.length; ++i) {
				WebDriverAccess.getDriver().findElement(By.xpath(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_DELEGATE_SELECTED_USER_DELETE_XPATH
								.getValue().replaceAll("approver-id", CommonPage.getTestData("ApproverID" + i))))
						.click();
				if (!WebDriverAccess.getDriver().findElement(By.xpath(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_DELEGATE_SELECTED_USER_XPATH
								.getValue().replaceAll("approver-id", CommonPage.getTestData("ApproverID" + i))))
						.getText().replaceAll("\\n", "").trim().equals("")) {
					throw new OrderingErrorOccured("Delegate user is not removed");
				}
			}
		}
	}

	/**
	 * This method checks the approvers.
	 * @throws Exception
	 * @lastModifiedBy shisingh
	 */
	public static void checkAllApprovers() throws Exception {
		if(!Objects.isNull(CommonPage.getTestData("ApproverUserIDs"))) {
			String[] approvalUserIDs = CommonPage.getTestData("ApproverUserIDs").split("\\|");
			for (int i = 1; i < approvalUserIDs.length; ++i) {	
				WebDriverAccess.getDriver().findElement(By.xpath(	
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVER_CHECKBOX_BUTTON_XPATH	
						.getValue().replaceAll("approver-id", CommonPage.getTestData("ApproverID" + i)))).click();	
			}	
		}
	}
	
	/**
	 * This method checks all the approvers.
	 * 
	 * @throws Exception
	 * @lastModifiedBy usha naidu
	 */
	public static void selectAllApprovers() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVER_SELECT_ALL_CHECKBOX_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVER_SELECT_ALL_CHECKBOX_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVER_SELECT_ALL_CHECKBOX_BUTTON_XPATH);
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVER_SELECT_ALL_CHECKBOX_BUTTON_XPATH);
	}
	
	public static void clickOnPopUp() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_BUTTON_ID);
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_BUTTON_ID);
	}

	/**
	 * This method waits for the Manager Approval Maintenance Screen page to load.
	 * 
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForManagerApprovalMaintenanceScreenToLoad() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_HEADING_XPATH);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_HEADING_XPATH);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_TABLE_ID);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_TABLE_ID);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_TABLE_ID);
	}

	/**
	 * This method validates for label on the Manager Approval Maintenance page.
	 * 
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void validateLabelOnManagerApprovalMaintenanceScreenPage() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_HEADING_XPATH),
				"Manager Approval Maintenance Screen", "Manager Approval Maintenance Screen page heading not matched");
	}

	/**
	 * This method clicks on the back button and takes action on the pop up element
	 * on the Manager Approval Maintenance Screen page.
	 * 
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void moveBackToManagerApprovalMaintenancePage() throws Exception {
		OrderingManagerApprovalMaintenancePage.clickOnBackButton();
	}

	/**
	 * This method clicks on the back button and takes action on the pop up element
	 * on the Manager Approval Maintenance Screen page.
	 * 
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void moveBackToManagerApprovalMaintenancePage(String action) throws Exception {
		OrderingManagerApprovalMaintenancePage.clickOnBackButton();
	}

	/**
	 * This method clicks on the back button on the Manager Approval Maintenance
	 * Screen page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnBackButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_BACK_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_BACK_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_BACK_BUTTON_ID);
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_BACK_BUTTON_ID);
	}

	/**
	 * This method clicks on the save button on the Manager Approval Maintenance
	 * Screen page.
	 * 
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnSaveButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SAVE_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SAVE_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SAVE_BUTTON_ID);
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SAVE_BUTTON_ID);
	}

	/**
	 * This method takes the action on the pop up element depending on the value you
	 * have provided.
	 * 
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void popUpAction(String action) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_BODY_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_BODY_CSS);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_BODY_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_SAVE_AND_LEAVE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_LEAVE_WITHOUT_SAVING_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_CANCEL_BUTTON_ID);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_BODY_CSS),
				"You are about to leave this page. Your information will be lost", "Pop up message not verified");
		switch (action) {
		case "Save & Leave":
			BrowserAction.click(
					OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_SAVE_AND_LEAVE_BUTTON_ID);
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime")))
					.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 1));
			String alertPopUpText = new WebDriverWait(WebDriverAccess.getDriver(),
					new Long(CommonPage.getTestData("WaitTime")))
							.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.noty_bar"))).getText();
			Assert.assertEquals(alertPopUpText, "Approvers have been successfully updated.",
					"Order details in Order Maintenance page approval pop up message is incorrect");
			OrderingCommonPage.verifyNoAlertPopUpDispalyed();
			break;
		case "Leave without saving":
			BrowserAction.click(
					OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_LEAVE_WITHOUT_SAVING_BUTTON_ID);
			break;
		case "Cancel":
			BrowserAction.click(
					OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_CANCEL_BUTTON_ID);
			break;
		default:
			throw new InvalidSwitchCaseException(action + "is a invalid switch case");
		}
	}

	/**
	 * This method verifes that no order is dispalyed in the table.
	 * 
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyNoOrdersAreDisplayedInTable() throws Exception {
		int singleOrderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		for (int j = 1; j <= singleOrderCount; ++j) {
			OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber" + j));
			OrderingMyOrdersPage.clickOnSearch();
			OrderingManagerApprovalQueuePage.verifyNoOrderInManagerApprovalQueue();
		}
	}

	/**
	 * This method re submit the order.
	 * 
	 * @throws Throwable
	 * @lastModifiedBy shisingh
	 */
	public static void reSubmitOrder() throws Throwable {
		OrderingCommonPage.checkAlertPopUp();
		OrderingStartHerePage.waitForStartHerepage();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingCommonPage.checkAlertPopUp();
		OrderingDriverPage.waitForDriverPage();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehiclePage.waitForVehiclePage();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingCommonPage.checkAlertPopUp();
		OrderingBillingAndRegistrationPage.waitForBillingAndRegistrationPage();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingCommonPage.checkAlertPopUp();
		OrderingDealerPage.waitForDealerPage();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingCommonPage.checkAlertPopUp();
		OrderingSummaryPage.waitForSummaryPage();
		OrderingSummaryPage.clickSubmit();
		OrderingSummaryPage.confirmSubmit();
		OrderingCommonPage.checkAlertPopUp();
		OrderingSummaryPage.waitForPopUpResultBox();
		OrderingBOManagerOrderPreferencesPage.validateManagerApprovalSubmissionMessage();
		OrderingSummaryPage.verifySuccessfulSubmissionPopUp();
		OrderingSummaryPage.waitForSummaryPage();
		OrderingBOManagerOrderPreferencesPage.validateOrderStatus();
		PDFReporter.takeExtraScreenshot();
		OrderingHomePage.clickOnCloseSummaryPage();
	}

	/**
	 * This method Searchesand verifies  the record according to search option field on Approval Maintenance pages
	 * @lastModifiedBy mkariciharla
	 * @throws Exception
	 */
	public static void searchAndVerifyRecords(String columnName, String searchText) throws Exception {
		searchOrderByColumnName(columnName, searchText);
		verifySearchedRecords(columnName, searchText);
	}

	/**
	 * This method Searches the record according to search option field on Approval
	 * Maintenance pages
	 * 
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void clickSearchButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_BUTTON_XPATH);
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_BUTTON_XPATH);
	}

	/**
	 * This method Searches the record according to search option field on Approval
	 * Maintenance pages
	 * 
	 * @lastModifiedBy mkariciharla
	 * @throws Exception
	 */
	public static void searchOrderByColumnName(String columnName, String searchText) throws Exception {
		System.out.println(columnName + ": " + searchText);
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_INPUT_XPATH
						.name(),
				String.format(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_INPUT_XPATH
								.toString(),
						columnName)),
				searchText);
		OrderingManagerApprovalMaintenancePage.clickSearchButton();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		WebDriverAction.clear(BrowserAccess.getLocator(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_INPUT_XPATH
						.name(),
				String.format(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_INPUT_XPATH
								.toString(),
						columnName)));
	}

	/**
	 * This method verifies the record according to search option field on Approval
	 * Maintenance pages
	 * 
	 * @lastModifiedBy mkariciharla
	 * @throws Exception
	 */
	public static void verifySearchedRecords(String columnName, String searchText) throws Exception {
		List<WebElement> elementslist = WebDriverAccess.getElements(BrowserAccess
				.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_SEARCHED_RECORDS_RESULT_XPATH.name(), String.format(
						OrderingBOQueuePageEnum.ORDERING_BO_SEARCHED_RECORDS_RESULT_XPATH.toString(), columnName)));
		for (WebElement element : elementslist) {
			Assert.assertEquals(element.getText().trim(), searchText.trim(), "Searched record is not matching");
		}
	}

	/**
	 * This method clicks on Reset
	 * 
	 * @lastModifiedBy mkariciharla
	 * @throws Exception
	 */
	public static void clickOnReset() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_RESET_BUTTON_XPATH);
		BrowserVerify.verifyElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_RESET_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_RESET_BUTTON_XPATH);
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_RESET_BUTTON_XPATH);
	}

	/**
	 * This method clicks on Reset
	 * 
	 * @lastModifiedBy mkariciharla
	 * @throws Exception
	 */
	public static void verifyReset(String columnName, String searchText) throws Exception {
		OrderingManagerApprovalMaintenancePage.searchOrderByColumnName(columnName, searchText);
		OrderingManagerApprovalMaintenancePage.clickOnReset();
		WebElement ele = WebDriverAccess.getElement(BrowserAccess.getLocator(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_INPUT_XPATH
						.name(),
				String.format(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_INPUT_XPATH
								.toString(),
						columnName)));
		assertTrue(ele.getText().trim().equals(""), "Search Field " + columnName + " is not cleared ");
	}

	/**
	 * This method verifies the Export functionality of data of queue table on Queue
	 * pages
	 * 
	 * @lastModifiedBy usha naidu
	 * @throws Exception
	 */
	public static void verifyExportFuntionality(String className) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_EXPORT_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_EXPORT_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_EXPORT_BUTTON_XPATH);
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_EXPORT_BUTTON_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"),className);
	}

	/**
	 * This method selects search type
	 * 
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void selectSearchType(String searchType) throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCHOPTION_BUTTON_XPATH);
		BrowserVerify.verifyElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCHOPTION_BUTTON_XPATH);
		Actions action = new Actions(WebDriverAccess.getDriver());
		action.moveToElement(WebDriverAction.getElement(By.xpath(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCHOPTION_BUTTON_XPATH
						.getValue())))
				.perform();
		WebElement ele = WebDriverAccess.getElement(BrowserAccess.getLocator(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCHTYPE_DRPDWN_XPATH
						.name(),
				String.format(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCHTYPE_DRPDWN_XPATH
								.toString(),
						searchType)));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime")))
				.until(ExpectedConditions.elementToBeClickable(ele));
		action.moveToElement(ele).click().build().perform();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime")));
	}

	/**
	 * This method changes approval type dropdown value.
	 * 
	 * @lastModifiedBy shisingh
	 * @param approvalType
	 * @throws Exception
	 */
	public static void changeApprovalType(String approvalType) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVAL_TYPE_DROPDOWN_ID);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVAL_TYPE_DROPDOWN_ID);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVAL_TYPE_DROPDOWN_ID);
		System.out.println("Approval Type: " + approvalType);
		BrowserAction.selectDropdownOptionByText(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVAL_TYPE_DROPDOWN_ID,
				approvalType);
	}

	/**
	 * This method enters log number in maintenance page
	 * 
	 * @lastModifiedBy shisingh
	 * @param logNumber
	 * @throws Exception
	 */
	public static void enterLogNumber(String logNumber) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LOGNUMBER_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LOGNUMBER_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LOGNUMBER_TEXTFIELD_ID);
		BrowserAction.clickandClear(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LOGNUMBER_TEXTFIELD_ID);
		System.out.println("Log Number: " + logNumber);
		BrowserAction.enterFieldValue(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LOGNUMBER_TEXTFIELD_ID,
				logNumber);
	}

	/**
	 * This method clicks on search button in maintenance page
	 * 
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickSearchButtonInMaintenance() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SEARCH_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SEARCH_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SEARCH_BUTTON_ID);
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SEARCH_BUTTON_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}

	/**
	 * This method clicks on search button in maintenance page
	 * 
	 * @lastModifiedBy shisingh
	 * @param logNumber
	 * @throws Exception
	 */
	public static void verifyLogNumber(String logNumber) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LOGNUMBER_LABEL_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LOGNUMBER_LABEL_CSS);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LOGNUMBER_LABEL_CSS);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime")))
				.until(ExpectedConditions.textToBePresentInElement(WebDriverAction.getElement(By.cssSelector(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LOGNUMBER_LABEL_CSS
								.getValue())),
						logNumber));
	}

	public static void verifyRejectReason() throws Exception {
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_EXPECTED_APPROVER_CHECKBOX_XPATH);
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_BUTTON_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_YES_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_NO_BUTTON_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_MESSAGE_XPATH), "Are you sure you want to reject this order?", "Pop up message not verified");
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_NO_BUTTON_XPATH);
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_BUTTON_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_YES_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_NO_BUTTON_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_MESSAGE_XPATH), "Are you sure you want to reject this order?", "Pop up message not verified");
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_YES_BUTTON_XPATH);
		WebElement rejectReasonInputField = BrowserAction.getElement(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_REASON_TEXT_BOX_XPATH); 
		String rejectReasonText = "I am typing more than two hundred characters to verify whether it is excepting more than two hundred characters or not. The expected result should be that it must not accept more than two hundred chacters in the rejected reason input field.";
		rejectReasonInputField.sendKeys(rejectReasonText);
		System.out.println("Reject reason entered into textbox: " + rejectReasonText);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVER_SELECT_ALL_CHECKBOX_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVER_SELECT_ALL_CHECKBOX_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVER_SELECT_ALL_CHECKBOX_BUTTON_XPATH);
		
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVER_SELECT_ALL_CHECKBOX_BUTTON_XPATH);
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_BUTTON_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_YES_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_NO_BUTTON_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_MESSAGE_XPATH),
				"Are you sure you want to reject this order?", "Pop up message not verified");
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_YES_BUTTON_XPATH);
		rejectReasonInputField = BrowserAction.getElement(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_REASON_TEXT_BOX_XPATH);
		rejectReasonInputField.sendKeys(rejectReasonText);
		System.out.println("Reject reason entered into textbox: " + rejectReasonText);
		String typedValue = rejectReasonInputField.getAttribute("value");
		int size = typedValue.length();
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_YES_BUTTON_XPATH);
		if (size >= 200) {
			BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_REASON_FIELD_MAX_CHAR_LIMIT_POPUP_XPATH);
			System.out.println("Max character functionality is working fine.");
		} else {
			System.out.println("field invput value contain less than 200 char");
		}
		BrowserAction.clear(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_REASON_TEXT_BOX_XPATH);
		rejectReasonInputField.sendKeys("Now Typing less than 200 chars.");
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_YES_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_CONFIRMATION_XPATH);
		String approvalStatus = new WebDriverWait(WebDriverAccess.getDriver(),
				new Long(CommonPage.getTestData("WaitTime"))).until(
						ExpectedConditions.elementToBeClickable(By.xpath("//tr[@class='table-row-disabled']//td[4]")))
						.getText();
		Assert.assertEquals(approvalStatus, "Rejected", "Reject unsuccessful");
	}

	/**
	 * This method verifies record on manager approval maintenance page
	 * @lastModifiedBy sweety Agrawal
	 * @param logNumber
	 * @throws Exception
	 */
	public static void verifyRecordOnMAMPage(String logNumber) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_PAGE_LOG_NUMBER_TEXT_FIELD_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_PAGE_LOG_NUMBER_TEXT_FIELD_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_PAGE_LOG_NUMBER_TEXT_FIELD_XPATH);
		BrowserAction.clickandClear(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_PAGE_LOG_NUMBER_TEXT_FIELD_XPATH);
		System.out.println("Log Number: " + logNumber);
		BrowserAction.enterFieldValue(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_PAGE_LOG_NUMBER_TEXT_FIELD_XPATH, logNumber);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_BUTTON_XPATH);
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SEARCH_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_PAGE_LOG_NUMBER_TEXT_FIELD_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_PAGE_LOG_NUMBER_TEXT_FIELD_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_PAGE_LOG_NUMBER_TEXT_FIELD_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime")))
				.until(ExpectedConditions.textToBePresentInElement(WebDriverAction.getElement(By.cssSelector(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_PAGE_LOG_NUMBER_TEXT_FIELD_XPATH.getValue())), logNumber));
	}

	/**
	 * This method verifies the headers of the queue table are present and are
	 * spelled correctly
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyTableHeadersTextFromMAMQueueTable() throws Exception {
		String actualLogNumber = CommonPage.verifyElementTextPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LOGNUMBER_TABLE_HEADER_XPATH);
		Assert.assertEquals(actualLogNumber.replaceAll("\n", " "), "Log Number", "Text does not match");
		String actualFleet = CommonPage.verifyElementTextPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_TABLE_HEADER_XPATH);
		Assert.assertEquals(actualFleet.replaceAll("\n", " "), "Client", "Text does not match");
		String actualState = CommonPage.verifyElementTextPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_STATE_TABLE_HEADER_XPATH);
		Assert.assertEquals(actualState.replaceAll("\n", " "), "State", "Text does not match");
		String actualTotalApprovers = CommonPage.verifyElementTextPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_TOTAL_TABLE_HEADER_XPATH);
		Assert.assertEquals(actualTotalApprovers.replaceAll("\n", " "), "Total Approvers", "Text does not match");
		String actualUnit = CommonPage.verifyElementTextPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_UNIT_TABLE_HEADER_XPATH);
		Assert.assertEquals(actualUnit.replaceAll("\n", " "), "Unit", "Text does not match");
		String actualApprovalType = CommonPage.verifyElementTextPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVALTYPE_TABLE_HEADER_XPATH);
		Assert.assertEquals(actualApprovalType.replaceAll("\n", " "), "Approval Type", "Text does not match");
		String actualCorp = CommonPage.verifyElementTextPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_CORP_TABLE_HEADER_XPATH);
		Assert.assertEquals(actualCorp.replaceAll("\n", " "), "Corp", "Text does not match");
		String actualNumberPendingApproval = CommonPage.verifyElementTextPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_PENDINGAPPROVAL_TABLE_HEADER_XPATH);
		Assert.assertEquals(actualNumberPendingApproval.replaceAll("\n", " "), "Pending", "Text does not match");
		String actualLastApprovalDate = CommonPage.verifyElementTextPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LASTAPPROVALDATE_TABLE_HEADER_XPATH);
		Assert.assertEquals(actualLastApprovalDate.replaceAll("\n", " "), "Last Approval Received Date",
				"Text does not match");
		String actualLastApprovalUser = CommonPage.verifyElementTextPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LASTAPPROVALUSER_TABLE_HEADER_XPATH);
		Assert.assertEquals(actualLastApprovalUser.replaceAll("\n", " "), "Last Approval Received User",
				"Text does not match");
		String actualTotalRejected = CommonPage.verifyElementTextPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECTED_TABLE_HEADER_XPATH);
		Assert.assertEquals(actualTotalRejected.replaceAll("\n", " "), "Rejected", "Text does not match");
		String actualTotalApproved = CommonPage.verifyElementTextPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVED_TABLE_HEADER_XPATH);
		Assert.assertEquals(actualTotalApproved.replaceAll("\n", " "), "Approved", "Text does not match");
	}

	/**
	 * This method verifies that the priority for approvals is displayed in
	 * sequential order, e.g 1,2,3,4,5 expected e.g 1,2,3,5,6,7,7 not expected
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyIfPriorityDisplayInSequentialOrder() throws Exception {
		ArrayList<Integer> priorityValuesOnChart = new ArrayList<Integer>();
		for (String priorityValue : BrowserAction.getElementsText(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_PRIORITY_VALUE_XPATH)) {
			priorityValuesOnChart.add(Integer.parseInt(priorityValue));
		}
		boolean isItSequential = true;
		for (int i = 0; i < priorityValuesOnChart.size() - 1; i++) {
			if (priorityValuesOnChart.get(0) != 1) {
				isItSequential = false;
				break;
			}
			if (priorityValuesOnChart.get(i) + 1 != priorityValuesOnChart.get(i + 1)) {
				isItSequential = false;
			}
		}
		Assert.assertEquals(isItSequential, true, "Priority is not sequential " + priorityValuesOnChart.toString());
	}

	/**
	 * This method sorts the queue table by column name, now it only supports
	 * Pending and Approved Columns update as needed
	 * @lastModifiedBy hjimenez
	 * @param columnName ("Pending" , "Approved")
	 * @throws Exception
	 */
	public static void sortQueueTableByColumn(String columnName) throws Exception {
		switch (columnName) {
		case "Pending":
			BrowserAction.click(
					OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_PENDINGAPPROVAL_TABLE_HEADER_XPATH);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			BrowserWait.waitUntilElementIsDisplayed(
					OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_TABLE_ROW_XPATH);
			break;
		case "Approved":
			BrowserAction.click(
					OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVED_TABLE_HEADER_XPATH);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			BrowserWait.waitUntilElementIsDisplayed(
					OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_TABLE_ROW_XPATH);
			break;
		default:
			throw new OrderingErrorOccured(columnName + " is a invalid action");
		}
	}

	/**
	 * This method verifies that for sequential approvers, the first approver and
	 * only the first Approver is active
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyApproveStatusInSequentialOrder() throws Exception {
		int counter = 0;
		boolean isItASingleActiveStatus = true;
		for (WebElement status : BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SELECT_STATUS_CLASS)) {
			Select statusSelect = new Select(status);
			String currentValue = statusSelect.getFirstSelectedOption().getText();
			if (currentValue.equalsIgnoreCase("Active")) {
				counter++;
				if (counter > 1) {
					isItASingleActiveStatus = false;
				}
			}
		}
		Assert.assertEquals(isItASingleActiveStatus, true, "There should be only a single record with Active status");
	}

	/**
	 * This method verifies that all approvers status is Active when approval type
	 * is non sequential
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyApproveStatusForNonSequentialApprovers() throws Exception {
		OrderingManagerApprovalMaintenancePage.changeApprovalType("Non-Sequential");
		int counter = 0;
		boolean isActiveStatusForAllAppovers = true;
		for (WebElement status : BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SELECT_STATUS_CLASS)) {
			Select statusSelect = new Select(status);
			String currentValue = statusSelect.getFirstSelectedOption().getText();
			if (!currentValue.equalsIgnoreCase("Active")) {
				counter++;
				if (counter > 0) {
					isActiveStatusForAllAppovers = false;
				}
			}
		}
		Assert.assertEquals(isActiveStatusForAllAppovers, true, "All status should be Active");
	}

	/**
	 * This method verifies the priority for non sequential approvers, this should
	 * always be 1
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyPriorityForNonSequentialApprovers() throws Exception {
		OrderingManagerApprovalMaintenancePage.changeApprovalType("Non-Sequential");
		ArrayList<Integer> priorityValuesOnChart = new ArrayList<Integer>();
		for (String priorityValue : BrowserAction.getElementsText(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_PRIORITY_VALUE_XPATH)) {
			priorityValuesOnChart.add(Integer.parseInt(priorityValue));
		}
		boolean isPriorityOne = true;
		for (int i = 0; i < priorityValuesOnChart.size(); i++) {
			if (priorityValuesOnChart.get(i) != 1) {
				isPriorityOne = false;
			}
		}
		Assert.assertEquals(isPriorityOne, true, "Priority should always be 1 for non sequentials approvers");
	}

	/**
	 * This method verifies different elements on MAM approvers screen, log number,
	 * corp, fleet, state, unit labels back and save buttons, log number, fleet,
	 * unit textboxes, and table headers
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyDetailsOfMAMApproversScreen() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LOGNUMBER_LABEL_CSS);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_CORP_LABEL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_LABEL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_STATE_LABEL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_UNIT_LABEL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_BACK_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SAVE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LOGNUMBER_TEXTFIELD_ID);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_TEXTBOX_ID);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_UNIT_TEXTBOX_ID);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVAL_TYPE_DROPDOWN_ID);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_CORP_DROPDOWN_ID);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVE_BUTTON_CSS);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_BUTTON_CSS);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_BULKACTIONS_LABEL_ID);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_DELEGATE_HEADER_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_EXPAPPROVERS_HEADER_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVALEXP_HEADER_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_INTERNAL_HEADER_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_PRIORITY_HEADER_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_STATUS_HEADER_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SUBSCRIPTION_HEADER_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SEARCH_BUTTON_ID);
	}

	/**
	 * This methods enters the log number on the MAM approvers screen 
	 * @lastModifiedBy hjimenez
	 * @param logNumber 
	 * @throws Exception
	 */
	public static void enterLogNumberOnMAMQueueScreen(String logNumber) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_LOGNUMBER_TEXT_FIELD_NAME);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_LOGNUMBER_TEXT_FIELD_NAME);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_LOGNUMBER_TEXT_FIELD_NAME);
		BrowserAction.clickandClear(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_LOGNUMBER_TEXT_FIELD_NAME);
		System.out.println("Log Number: " + logNumber);
		BrowserAction.enterFieldValue(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_LOGNUMBER_TEXT_FIELD_NAME,
				logNumber);
	}

	/**
	 * This methods clicks the search button on the MAM queue screen
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void clickSearchButtonInMAMQueueScreen() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_SEARCH_BUTTON_XPATH);
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_SEARCH_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}

	/**
	 * This methods verifies that the log number entered matches the result given in
	 * MAM queue screen
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyLogNumberInMAMQueueScreen(String logNumber) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_LOGNUMBER_TDRESULT_XPATH);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_LOGNUMBER_TDRESULT_XPATH);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_LOGNUMBER_TDRESULT_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime")))
				.until(ExpectedConditions.textToBePresentInElement(WebDriverAction.getElement(By.xpath(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_LOGNUMBER_TDRESULT_XPATH
								.getValue())),
						logNumber));
	}
	/**
	 * This methods selects all approvers and completes the status
	 * @lastModifiedBy hjimenez
	 * @throws Throwable
	 */
	public static void approveAllExpectedApprovers() throws Throwable {
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_SELECTALL_CHECKBOX_XPATH);
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVE_BUTTON_ID);
		OrderingManagerApprovalMaintenancePage.handleConfirmationApprovalPopUp("Yes");
	}

	/**
	 * This methods verifies that the edit boxes are not present after user has
	 * completed the approvals, this way it validates that user cannot edit details
	 * after completion
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyUserIsNotAbleToEditDetailsAfterCompletedApproval() throws Exception {
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_LASTNAME_TEXTFIELD_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_FIRSTNAME_TEXTFIELD_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_DATE_DROPDOWN_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SELECT_STATUS_CLASS);
	}

	/**
	 * This methods saves changes done, then searches the log number again and
	 * verifies order is saved
	 * @lastModifiedBy hjimenez
	 * @param logNumber
	 * @throws Exception
	 */
	public static void verifySystemSaveChanges(String logNumber) throws Exception {
		OrderingManagerApprovalMaintenancePage.clickOnBackButton();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingManagerApprovalMaintenancePage.clickOnTheFirstRecord();
		OrderingManagerApprovalMaintenancePage.enterLogNumber(logNumber);
		System.out.println("Log Number entered:" + logNumber);
		OrderingManagerApprovalMaintenancePage.clickSearchButtonInMaintenance();
		OrderingManagerApprovalMaintenancePage.verifyLogNumber(logNumber);
	}

	/**
	 * This methods clicks the option save and leave once in approvers screen, and
	 * verifies the popup
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void saveAndLeavePopUpAction(String action) throws Exception {
		switch (action) {
		case "Save & Leave":
			BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_SAVE_AND_LEAVE_BUTTON_ID);
			BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_SAVE_AND_LEAVE_BUTTON_ID);
			String alertPopUpText = new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.noty_bar"))).getText();
			Assert.assertEquals(alertPopUpText, "Approvers have been successfully updated.", "Order details in Order Maintenance page approval pop up message is incorrect");
			OrderingCommonPage.verifyNoAlertPopUpDispalyed();
			break;
		default:
			throw new InvalidSwitchCaseException(action + "is a invalid switch case");
		}
	}

	/**
	 * This methods verifies that the approve button is disabled when there is at
	 * least 1 rejected approval
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyApproveButtonIsDisabledWhenRejectedApprovalExist() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS).get(0));
		BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS).get(0).click();
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVE_BUTTON_ID);
		OrderingManagerApprovalMaintenancePage.handleConfirmationApprovalPopUp("Yes");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS).get(0));
		BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS).get(0).click();
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_REJECT_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_REASON_TEXT_BOX_XPATH);
		BrowserAction.getElement(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_REASON_TEXT_BOX_XPATH).sendKeys("This an example for testing");
		System.out.println("Reject reason: This is an example for testing" );
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_YES_BUTTON_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS).get(0));
		BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS).get(0).click();
		Assert.assertEquals(BrowserAction.getElement(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVE_BUTTON_ID).isEnabled(), false, "Once an approver rejected, approve button should get disabled");
	}

	/**
	 * This methods verifies that the labels lognumber/corp/unit/fleet are not
	 * editable
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyLabelsAreNotEditable() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_CLIENTINFOAREA_DIV_XPATH);
		boolean containsInputTag = BrowserAction.getElement(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_CLIENTINFOAREA_DIV_XPATH)
				.getAttribute("innerHTML").contains("<input");
		Assert.assertEquals(containsInputTag, false,
				"there is an input tag inside, these labels should not be editable");
	}

	/**
	 * This methods verifies that user cannot delete an approver, it is achieved by
	 * checking there is no a delete button in the bulk actions area
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyThereIsNoDeleteActionInBulkSection() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_BULKACTIONS_LABEL_ID);
		boolean containsDeleteOrRemoveText = BrowserAction.getElement(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_BULKACTIONS_LABEL_ID)
				.getAttribute("innerHTML").contains("delete")
				|| BrowserAction.getElement(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_BULKACTIONS_LABEL_ID)
						.getAttribute("innerHTML").contains("remove");
		Assert.assertEquals(containsDeleteOrRemoveText, false,
				"there is an unexpected delete/remove action, please verify user cannot delete an approver");
	}

	/**
	 * This methods verifies that default sorting is log number in ascending way
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyDefaultSortOrder() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_LOGNUMBER_TDRESULT_XPATH);
		List<WebElement> logNumbers = BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_LOGNUMBER_TDRESULT_XPATH);
		ArrayList<Integer> allLogNumbers = new ArrayList<Integer>();
		for (int i = 0; i < logNumbers.size(); i++) {
			Integer logNumber = Integer.parseInt(logNumbers.get(i).getText());
			allLogNumbers.add(logNumber);
		}
		for (int i = 0; i < allLogNumbers.size() - 1; i++) {
			boolean isLogNumberAscending = allLogNumbers.get(i) > allLogNumbers.get(i + 1);
			Assert.assertEquals(isLogNumberAscending, true,
					"Log Number is not sorted by default, Log Numbers are not sorted in ascending order..."
							+ allLogNumbers.get(i) + " is smaller than " + allLogNumbers.get(i + 1));
		}
	}

	/**
	 * This methods verifies that user cannot approve multiple approvals without
	 * following the sequential order displayed, e.g user tries to approve Approver
	 * 1 and Approver 3 at the same time
	 * @lastModifiedBy hjimenez
	 * @throws Throwable
	 */
	public static void verifyMultipleApprovalFailsIfSelectionIsNotSequential() throws Throwable {
		OrderingManagerApprovalMaintenancePage.changeApprovalType("Sequential");
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SAVE_BUTTON_ID);
		OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Save Order");
		BrowserWait.waitUntilMoreThanOneElementPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS);
		BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS).get(0).click();
		BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS).get(2).click();
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVE_BUTTON_ID);
		OrderingManagerApprovalMaintenancePage.handleConfirmationApprovalPopUp("Yes");
		new WebDriverWait(WebDriverAccess.getDriver(), 10)
				.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 1));
		String popUpErrorText = BrowserAction.getElementText(
				OrderingManagerApprovalQueuePageEnum.ORDERING_MANAGER_APPROVAL_QUEUE_ALERT_POP_UP_MODALAREA_CSS);
		Assert.assertEquals(popUpErrorText.contains("Cannot approve orders that are not in approver's queue"), true,
				"Popup was not expected, users should not be able to skip approvers when approving...popuptext: "
						+ popUpErrorText);
	}

	/**
	 * This methods verifies that after users completes an approval, the next user
	 * status should get active,
	 * @lastModifiedBy hjimenez
	 * @throws Throwable
	 */
	public static void verifyApproveCompletedNextApprovalIsActive() throws Throwable {
		int index = 1;
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_SELECTALL_CHECKBOX_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_SELECTALL_CHECKBOX_XPATH);
		WebElement firstPendingApprover = BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SELECT_STATUS_CLASS)
				.get(index);
		Select selectFirstPendingApprover = new Select(firstPendingApprover);
		String beforeApprovingFirstElementStatus = selectFirstPendingApprover.getFirstSelectedOption().getText();
		System.out.println("before completing first approver, pending approver is: " + beforeApprovingFirstElementStatus);
		OrderingManagerApprovalMaintenancePage.changeApprovalType("Sequential");
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SAVE_BUTTON_ID);
		OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Save Order");
		BrowserWait.waitUntilMoreThanOneElementPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS);
		BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS)
				.get(0).click();
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVE_BUTTON_ID);
		OrderingManagerApprovalMaintenancePage.handleConfirmationApprovalPopUp("Yes");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		WebElement firstPendingApproverAfter = BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SELECT_STATUS_CLASS)
				.get(index - 1);
		Select selectfirstPendingApproverAfter = new Select(firstPendingApproverAfter);
		String afterApprovingFirstElementStatus = selectfirstPendingApproverAfter.getFirstSelectedOption().getText();
		System.out.println("after completing first approver, pending approver is: " + afterApprovingFirstElementStatus);
		Assert.assertEquals(afterApprovingFirstElementStatus, "Active",
				"After first approving is completed, next approver status should get active automatically");
	}

	/**
	 * This methods changes the approval expected date for the second user and
	 * verifies it is displayed correctly after saving the change
	 * @lastModifiedBy hjimenez
	 * @throws Throwable
	 */
	public static void verifyUserCanChangeApprovalDate() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_FIRSTUSERDATE_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_FIRSTUSERDATE_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_FIRSTUSERDATE_TEXTFIELD_ID);
		BrowserAction.clickandClear(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_FIRSTUSERDATE_TEXTFIELD_ID);
		System.out.println("End Date: " + CommonPage.getCurrentDataIn("MM/dd/yyyy", "2"));
		BrowserAction.enterFieldValue(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_FIRSTUSERDATE_TEXTFIELD_ID,
				CommonPage.getCurrentDataIn("MM/dd/yyyy", "5"));
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SELECTEDDATE_DATE_XPATH);
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SAVE_BUTTON_ID);
		OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Save Order");
		BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS).get(0).click();
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVE_BUTTON_ID);
		OrderingManagerApprovalMaintenancePage.handleConfirmationApprovalPopUp("Yes");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		String actualDate = BrowserAction.getElement(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SECONDUSERDATEAPPROVED_LABEL_XPATH).getText();
		Assert.assertEquals(actualDate, CommonPage.getCurrentDataIn("MM/dd/yyyy", "5"),"Date did not update correctly");
	}

	/**
	 * This methods verifies that after users cancels an approval, next approver
	 * status gets active,
	 * @lastModifiedBy hjimenez
	 * @throws Throwable
	 */
	public static void verifyNextStatusIsActiveAfterCancelApproval() throws Throwable {
		int index = 1;
		WebElement firstPendingApprover = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SELECT_STATUS_CLASS).get(index);
		Select selectFirstPendingApprover = new Select(firstPendingApprover);
		String beforeCancellingFirstElementStatus = selectFirstPendingApprover.getFirstSelectedOption().getText();
		System.out.println("before completing first approver, pending approver is: " + beforeCancellingFirstElementStatus);
		WebElement newerFirstPendingApprover = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SELECT_STATUS_CLASS).get(index);
		Select newerSelectFirstPendingApprover = new Select(newerFirstPendingApprover);
		newerSelectFirstPendingApprover.selectByVisibleText("Cancelled");
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SAVE_BUTTON_ID);
		OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Save Order");
		BrowserWait.waitUntilMoreThanOneElementPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS);
		BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS).get(0).click();
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVE_BUTTON_ID);
		OrderingManagerApprovalMaintenancePage.handleConfirmationApprovalPopUp("Yes");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		WebElement firstPendingApproverAfter = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SELECT_STATUS_CLASS).get(index - 1);
		Select selectfirstPendingApproverAfter = new Select(firstPendingApproverAfter);
		String afterCancellingFirstElementStatus = selectfirstPendingApproverAfter.getFirstSelectedOption().getText();
		System.out.println("after completing first approver, pending approver is: " + afterCancellingFirstElementStatus);
		Assert.assertEquals(afterCancellingFirstElementStatus, "Active","After first approving is Cancelled, next approver status should get active automatically");
	}

	/**
	 * This methods verifies that user cannot enter any date which is not more than
	 * 3 business days from now
	 * @lastModifiedBy hjimenez
	 * @throws Throwable
	 */
	public static void verifyUserCanSelectDateAfter3DaysFromCurrentDate() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_FIRSTUSERDATE_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_FIRSTUSERDATE_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_FIRSTUSERDATE_TEXTFIELD_ID);
		for (int i = 0; i < 4; i++) {
			BrowserAction.clickandClear(
					OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_FIRSTUSERDATE_TEXTFIELD_ID);
			BrowserAction.enterFieldValue(
					OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_FIRSTUSERDATE_TEXTFIELD_ID,
					CommonPage.getCurrentDataIn("MM/dd/yyyy", "" + i + ""));
			int size = BrowserAction.getElements(
					OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SELECTEDDATE_DATE_XPATH).size();
			if (i == 3) {
				Assert.assertEquals(size, 1,
						"Only 3 days from now into the future should be disabled, 4th day should be enabled");
			}
		}
	}

	/**
	 * This methods verifies that after changing approval type from non-sequental to
	 * sequential, users are displayed in the same way
	 * @lastModifiedBy hjimenez
	 * @throws Throwable
	 */
	public static void verifyUsersAreDisplayedAfterChangingApprovalType() throws Throwable {
		OrderingManagerApprovalMaintenancePage.changeApprovalType("Non-Sequential");
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SAVE_BUTTON_ID);
		OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Save Order");
		OrderingCommonPage.checkAlertPopUp();
		List<WebElement> usersInNonSequentialOrder = BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_USERNAMES_LABEL_CLASS);
		ArrayList<String> usersNamesNonSequential = new ArrayList<String>();
		for (WebElement user : usersInNonSequentialOrder) {
			usersNamesNonSequential.add(user.getText());
		}
		System.out.println(usersNamesNonSequential.toString());
		OrderingManagerApprovalMaintenancePage.changeApprovalType("Sequential");
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SAVE_BUTTON_ID);
		OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Save Order");
		OrderingCommonPage.checkAlertPopUp();
		List<WebElement> usersInSequentialOrder = BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_USERNAMES_LABEL_CLASS);
		ArrayList<String> usersNamesSequential = new ArrayList<String>();
		for (WebElement user : usersInSequentialOrder) {
			usersNamesSequential.add(user.getText());
		}
		System.out.println(usersNamesSequential.toString());
		Assert.assertEquals(usersNamesNonSequential, usersNamesSequential," After changing approval type from non sequential to sequential, users are not displayed in the same way");
	}
	/**
	 * This methods verifies that Active, pending and cancelled are the only values
	 * in the status dropdown
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void verifyDropdown3Values() throws Exception {
		List<WebElement> statusDropDownElement = BrowserAction.getElements(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SELECT_STATUS_CLASS);
		for (WebElement status : statusDropDownElement) {
			String statusInDropDownValues = status.getText().trim().replaceAll("[\\s|\n|]", "");
			Assert.assertEquals(statusInDropDownValues, "ActivePendingCancelled", "Active, Pending and Cancelled should be the only 3 status");
		}
	}
	
	/**
	 * This methods sorts columns in MAM screen, and verifies the global spinner is shown only when expected
	 * Sorting should happen in all columns but last received user and last approval received date
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void verifySortingMAMScreen() throws Exception {
		List<WebElement> globalSpinner = null;
		List<OrderingManagerApprovalMaintenancePageEnum> tableSortingHeaders = new ArrayList<>();
		tableSortingHeaders.add(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LOGNUMBER_TABLE_HEADER_XPATH);
		tableSortingHeaders.add(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_CORP_TABLE_HEADER_XPATH);
		tableSortingHeaders.add(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_TABLE_HEADER_XPATH);
		tableSortingHeaders.add(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_UNIT_TABLE_HEADER_XPATH);
		tableSortingHeaders.add(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVALTYPE_TABLE_HEADER_XPATH);
		tableSortingHeaders.add(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_STATE_TABLE_HEADER_XPATH);
		tableSortingHeaders.add(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_TOTAL_TABLE_HEADER_XPATH);
		tableSortingHeaders.add(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_PENDINGAPPROVAL_TABLE_HEADER_XPATH);
		tableSortingHeaders.add(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVED_TABLE_HEADER_XPATH);
		tableSortingHeaders.add(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECTED_TABLE_HEADER_XPATH);
		tableSortingHeaders.add(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LASTAPPROVALDATE_TABLE_HEADER_XPATH);
		tableSortingHeaders.add(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LASTAPPROVALUSER_TABLE_HEADER_XPATH);
		for (OrderingManagerApprovalMaintenancePageEnum tableHeader : tableSortingHeaders) {
			BrowserWait.waitUntilElementIsDisplayed(tableHeader);
			BrowserAction.click(tableHeader);
			globalSpinner = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_GLOBALSPINNER_CSS);
			if (globalSpinner.size() > 0) {
				System.out.println("Sorting working on " + tableHeader.toString());
			}
			if (tableHeader.equals(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LASTAPPROVALDATE_TABLE_HEADER_XPATH) ||tableHeader.equals(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_LASTAPPROVALUSER_TABLE_HEADER_XPATH)) {
				Assert.assertEquals(globalSpinner.size(), 0, "for last approval date and last approver user sorting should be disabled");
			}
			OrderingCommonPage.checkGlobalSpinnerPopUp();
		}	
	}
	
	/**
	 * This methods selects all records, clicks approves and clicks "NO" in the confirmation popup, and verifies 
	 * the action was not approved
	 * @lastModifiedBy hjimenez
	 * @throws Throwable
	 */
	public static void verifyRecordsAreNotApproved() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS);
		int numberOfApprovalCkBoxes = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS).size();
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_SELECTALL_CHECKBOX_XPATH);
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVE_BUTTON_ID);
		OrderingManagerApprovalMaintenancePage.handleConfirmationApprovalPopUp("No");
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		int numberOfApprovalCkbAfterCanceling = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_APPROVALCHECK_CHECKBOX_CLASS).size();
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_QUEUE_SELECTALL_CHECKBOX_XPATH);
		Assert.assertEquals(numberOfApprovalCkBoxes, numberOfApprovalCkbAfterCanceling, "Approval should have been cancelled because user did not confirm the approval");
	}
	/**
	 * This methods cancels the approval in the pop up displayed
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void handleConfirmationApprovalPopUp(String action) throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		switch (action) {
		case "Yes":
			if(BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH).size() > 0) {
				BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_YES_BUTTON_ID);
			}
			break;
		case "No":
			if(BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH).size() > 0) {
				BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_CANCEL_BUTTON_ID);
			}
			break;
		default:
			throw new InvalidSwitchCaseException(action + "is a invalid switch case");
		}
	}
	/**
	 * This methods verifies if subscription and internal switches are read only
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void verifySwitchesAreReadOnly() throws Exception {
		int numberOfSwitches = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_TOGGLESWITCHES_XPATH).size();
		List <WebElement> toggleSwitches =  BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_TOGGLESWITCHES_XPATH); 
		for (int i = 0; i < numberOfSwitches; i++) {
			boolean isDisabled = toggleSwitches.get(i).getAttribute("innerHTML").contains("disabled");
			Assert.assertEquals(isDisabled, true, "Subscription and Internal switches should be disabled, at " + i + " position");
	}
	}
	/**
	 * This methods verifies wether user is internal or external
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void verifyIfUserIsExternalOrInternal() throws Exception {
		int numberOfSiwtches = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_INTERNALSWITCH_XPATH).size(); 
		List <WebElement> toggleSwitchColor = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_INTERNALSWITCH_XPATH);
		for (int i = 0; i < numberOfSiwtches; i++) {
	    if (getElementColor(toggleSwitchColor, i).equals("#82c341")) {
	    	System.out.println("external user");
	    }
	    else if (getElementColor(toggleSwitchColor, i).equals("#a8a8a8")){
	    	System.out.println("internal user");
	    }
	    else {
	    	System.err.println("Color was not expected, check if the internat switch displays correctly");
	    }
		}
	}
	/**
	 * This methods gets the color of an element by index and returns a String of the hex value
	 * @lastModifiedBy hjimenez
	 * @param List <> Webelement, index
	 * @throws Exception
	 * @throws Throwable
	 */
	public static String getElementColor(List <WebElement> element, int index) {
		String color = element.get(index).getCssValue("background-color");
		return Color.fromString(color).asHex();
	}
	/**
	 * This methods changes values, clicks leave without saving and verifies values were not updated
	 * @lastModifiedBy hjimenez
	 * @param logNumber
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void verifyValuesDidNotUpdate(String logNumber) throws Exception {
		OrderingManagerApprovalMaintenancePage.changeApprovalType("Sequential");
		Select approvaltypeDropdown = new Select(BrowserAction.getElement(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVAL_TYPE_DROPDOWN_ID));
		String approvalTypeBefore = approvaltypeDropdown.getFirstSelectedOption().getText();
		System.out.println(approvalTypeBefore + " selected");
		Select statusSelect = new Select(BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SELECT_STATUS_CLASS).get(0));
		statusSelect.selectByVisibleText("Cancelled");
		Select statusSelectAfter = new Select(BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SELECT_STATUS_CLASS).get(0));
		String statusBefore = statusSelectAfter.getFirstSelectedOption().getText();
		System.out.println(statusBefore + " selected");
		OrderingManagerApprovalMaintenancePage.moveBackToManagerApprovalMaintenancePage();
		OrderingManagerApprovalMaintenancePage.popUpAction("Leave without saving");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingManagerApprovalMaintenancePage.enterLogNumberOnMAMQueueScreen(logNumber);
		OrderingManagerApprovalMaintenancePage.clickSearchButtonInMAMQueueScreen();
		OrderingManagerApprovalMaintenancePage.clickOnTheFirstRecord();
		Select approvalTypeDropdownAfter = new Select(BrowserAction.getElement(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVAL_TYPE_DROPDOWN_ID));
		String approvalTypeAfter = approvalTypeDropdownAfter.getFirstSelectedOption().getText();
		Select statusDropdownAfter = new Select( BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SELECT_STATUS_CLASS).get(0));
		String statusAfter = statusDropdownAfter.getFirstSelectedOption().getText();
		Assert.assertEquals(approvalTypeAfter, "Non-Sequential", "Mismatch looks like the info was actually saved, when it should not have");
		Assert.assertEquals(statusAfter, "Active", "Mismatch looks like the info was actually saved, when it should not have");
	}
	/**
	 * This methods verifies wether user subscription is active or not
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void verifyIfSubscriptionIsOnOff() throws Exception {
		int numberOfSiwtches = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SUBSCRIPTIONSWITCH_XPATH).size(); 
		List <WebElement> toggleSwitchColor = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SUBSCRIPTIONSWITCH_XPATH);
		for (int i = 0; i < numberOfSiwtches; i++) {
	    if (getElementColor(toggleSwitchColor, i).equals("#82c341")) {
	    	System.out.println("Subscription on");
	    }
	    else if (getElementColor(toggleSwitchColor, i).equals("#a8a8a8")){
	    	System.out.println("Subscription off");
	    }
	    else {
	    	System.err.println("Color was not expected, check if the internat switch displays correctly");
	    }
		}
	}
	
	/**
	 * This methods verifies expected approver approved order and the status is complete
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void verifyExpectedApproverApprovedTheOrder() throws Exception {
		int singleOrderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		for(int j=1; j<=singleOrderCount; ++j) {
		OrderingManagerApprovalMaintenancePage.enterLogNumberOnMAMQueueScreen(CommonPage.getTestData("LoggerLogNumber"+j));
		OrderingManagerApprovalMaintenancePage.clickSearchButtonInMAMQueueScreen();
		OrderingManagerApprovalMaintenancePage.clickOnTheFirstRecord();
		OrderingManagerApprovalMaintenancePage.assertApproverApprovedOrder();
		OrderingManagerApprovalMaintenancePage.moveBackToManagerApprovalMaintenancePage();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		}
	}
	
	/**
	 * This methods verifies that the given approver has approved the order 
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void assertApproverApprovedOrder() throws Exception {
		String[] approvalUserIDs = CommonPage.getTestData("ApproverUserIDs").split("\\|");
		String approverId = approvalUserIDs[1];
		String approverOnScreen = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVED_TABLERAW_XPATH).get(1).getText();
		String orderStatus = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVED_TABLERAW_XPATH).get(3).getText();
		Assert.assertEquals(approverOnScreen.contains(approverId), true, "Expected approver should have approved the order" + approverId + "-" + approverOnScreen);
		Assert.assertEquals(orderStatus.equalsIgnoreCase("Complete"), true, "Status order should be complete for this expected approver: " + approverId + "-" +orderStatus);
	}
	
	/**
	 * This method enters fleet number in maintenance page
	 * @lastModifiedBy hjimenez
	 * @param fleetNumber
	 * @throws Exception
	 */
	public static void enterFleetNumber(String fleetNumber) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_TEXTBOX_ID);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_TEXTBOX_ID);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_TEXTBOX_ID);
		BrowserAction.clickandClear(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_TEXTBOX_ID);
		System.out.println("Fleet Number: " + fleetNumber);
		BrowserAction.enterFieldValue(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_TEXTBOX_ID,
				fleetNumber);
	}
	
	/**
	 * This method enters unit number in maintenance page
	 * @lastModifiedBy hjimenez
	 * @param unitNumber
	 * @throws Exception
	 */
	public static void enterUnitNumber(String unitNumber) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_UNIT_TEXTBOX_ID);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_UNIT_TEXTBOX_ID);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_UNIT_TEXTBOX_ID);
		BrowserAction.clickandClear(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_UNIT_TEXTBOX_ID);
		System.out.println("Unit Number: " + unitNumber);
		BrowserAction.enterFieldValue(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_UNIT_TEXTBOX_ID,
				unitNumber);
	}
	
	/**
	 * This method verifies fleet number
	 * @lastModifiedBy hjimenez
	 * @param fleetNumber
	 * @throws Exception
	 */
	public static void verifyFleetNumber(String fleetNumber) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_LABEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_LABEL_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_LABEL_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.textToBePresentInElement(WebDriverAction.getElement(By.xpath(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_LABEL_XPATH.getValue())), fleetNumber));
	}
	
	/**
	 * This method verifies unit number
	 * @lastModifiedBy hjimenez
	 * @param unitNumber
	 * @throws Exception
	 */
	public static void verifyUnitNumber(String unitNumber) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_UNIT_LABEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_UNIT_LABEL_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_UNIT_LABEL_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.textToBePresentInElement(WebDriverAction.getElement(By.xpath(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_UNIT_LABEL_XPATH.getValue())), unitNumber));
	}
	
	/**
	 * This method verifies No Results Found
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyNoResultsFound() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_NORESULTS_TABLERAW_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_NORESULTS_TABLERAW_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_NORESULTS_TABLERAW_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.textToBePresentInElement(WebDriverAction.getElement(By.xpath(
						OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_NORESULTS_TABLERAW_XPATH.getValue())), "No Results Found"));
	}
	
	/**
	 * This method clicks on search button in maintenance page without checking for alert popup
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void clickSearchButtonWithoutVerifyingAlerts() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SEARCH_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SEARCH_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SEARCH_BUTTON_ID);
		BrowserAction.click(
				OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SEARCH_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method approves order for all approvers except the approver specified in data file, to leave order ready for final approval
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void approveAllApproversExceptOne() throws Exception {
		int singleOrderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		for (int j = 1; j <= singleOrderCount; ++j) {
			OrderingManagerApprovalMaintenancePage.enterLogNumberOnMAMQueueScreen(CommonPage.getTestData("LoggerLogNumber" + j));
			OrderingManagerApprovalMaintenancePage.clickSearchButtonInMAMQueueScreen();
			OrderingManagerApprovalMaintenancePage.clickOnTheFirstRecord();
			String[] approvalUserIDs = CommonPage.getTestData("ApproverUserIDs").split("\\|");
			List<WebElement> rows = BrowserAction.getElements(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_NONSEQUENTIAL_TABLEROW_XPATH);
			int i = 0;
			for (WebElement row : rows) {
				List<WebElement> users = row.findElements(By.xpath("//td[@data-user-role='approver']//div[@class='user-data']"));
				String userName = users.get(i).getText();
				if (!userName.contains(approvalUserIDs[1])) {
					row.findElements(By.xpath("//input[@class='approval-check']")).get(i).click();
				}
				i++;
			}
			OrderingManagerApprovalMaintenancePage.clickApproveButton();
			OrderingManagerApprovalMaintenancePage.handleConfirmationApprovalPopUp("Yes");
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			OrderingManagerApprovalMaintenancePage.moveBackToManagerApprovalMaintenancePage();
			OrderingCommonPage.checkGlobalSpinnerPopUp();
		}
	}
	/**
	 * This method clicks the first checkbox on maintenance screen
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void selectFirstCheckBox() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_ORDER_CHECKBOX_NAME);
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_ORDER_CHECKBOX_NAME);
	}
	
	/**
	 * This method cancels an order from the list of orders displayed
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void cancelOrder() throws Exception {
		OrderingManagerApprovalMaintenancePage.enterLogNumberOnMAMQueueScreen(CommonPage.getTestData("LoggerLogNumber"+1));
		OrderingManagerApprovalMaintenancePage.clickSearchButtonInMAMQueueScreen();
		OrderingManagerApprovalMaintenancePage.verifyLogNumberInMAMQueueScreen(CommonPage.getTestData("LoggerLogNumber"+1));
		OrderingManagerApprovalMaintenancePage.selectFirstCheckBox();
		OrderingManagerApprovalMaintenancePage.clickCancelButton();
		OrderingManagerApprovalMaintenancePage.handleConfirmationApprovalPopUp("Yes");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method approves an order from the list of orders displayed
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void approveOrder() throws Exception {
		OrderingManagerApprovalMaintenancePage.enterLogNumberOnMAMQueueScreen(CommonPage.getTestData("LoggerLogNumber"+1));
		OrderingManagerApprovalMaintenancePage.clickSearchButtonInMAMQueueScreen();
		OrderingManagerApprovalMaintenancePage.verifyLogNumberInMAMQueueScreen(CommonPage.getTestData("LoggerLogNumber"+1));
		OrderingManagerApprovalMaintenancePage.selectFirstCheckBox();
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVE_BUTTON_XPATH);
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_APPROVE_BUTTON_XPATH);
		OrderingManagerApprovalMaintenancePage.handleConfirmationApprovalPopUp("Yes");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method will select all approvers and reject the order
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void rejectAllApprovers() throws Exception {
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_EXPECTED_APPROVER_CHECKBOX_XPATH);
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_BUTTON_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_APPROVER_POPUP_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_YES_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_NO_BUTTON_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_MESSAGE_XPATH), "Are you sure you want to reject this order?", "Pop up message not verified");
		WebElement rejectReasonInputField = BrowserAction.getElement(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_REASON_TEXT_BOX_XPATH); 
		String rejectReasonText = "Reject reason example test";
		rejectReasonInputField.sendKeys(rejectReasonText);
		System.out.println("Reject reason entered into textbox: " + rejectReasonText);
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_POPUP_YES_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_REJECT_CONFIRMATION_XPATH);
	}
	
}
