package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingManagerApprovalMaintenancePageEnum;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingManagerApprovalMaintenancePage;
import com.element.fleet.ordering.page.OrderingManagerApprovalQueuePage;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/*
 * PreCondition: Client 1750 should exist, approval rule test_ford_2020 should exist
 * Year: 2020
 * Make: Ford
 * Manufacturer: Ford
 * Approval Type: Sequential
 * Note: Approval rule Ford_2020
 * @lastModifiedBy Hector_Jimenez 
 */
public class Reg_MLO_MAM_Approver_Validation_Reject_Approver extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({ "xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath",
			"orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username,
			String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime,
			ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}

	@Test(alwaysRun = true)
	@Documentation(step = "Create order with logger user", expected = "Orders with logger user is created and selects client from CSV file")
	public void createOrdersWithLoggerUser() throws Throwable {
		OrderingBOManagerOrderPreferencesPage.createOrderSkipBilling(this.getClass().getSimpleName());
	}

	@Test  (dependsOnMethods = "createOrdersWithLoggerUser")
	@Documentation(step = "Go to Manager Approval Maintenance screen", expected = "Manager Approval Maintenance should display")
	public void closeSummaryPageAndGoToMyMAM() throws Throwable {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingManagerApprovalQueuePage.orderingSideMenuOptionLoadedExternalUser();
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Maintenance");
		OrderingManagerApprovalQueuePage.waitForManagerApprovalQueueToLoad();
	}

	@Test(dependsOnMethods = "closeSummaryPageAndGoToMyMAM")
	@Documentation(step = "Searches for created order using log number", expected = "new order should exist")
	public void searchLogNumberInMAMQueueScreen() throws Throwable {
		OrderingManagerApprovalMaintenancePage.enterLogNumberOnMAMQueueScreen(CommonPage.getTestData("LoggerLogNumber"+1));
		OrderingManagerApprovalMaintenancePage.clickSearchButtonInMAMQueueScreen();
		OrderingManagerApprovalMaintenancePage.verifyLogNumberInMAMQueueScreen(CommonPage.getTestData("LoggerLogNumber"+1));
	}

	@Test(dependsOnMethods = "searchLogNumberInMAMQueueScreen")
	@Documentation(step = "Verify user is able to navigate to Manager Approval Maintenance Screen, ORD-22167", expected = "Manager Approval Maintenance should display")
	public void verifyUserCanNavigateToMAMApproversScreen() throws Throwable {
		OrderingManagerApprovalMaintenancePage.clickOnTheFirstRecord();
	}

	@Test(dependsOnMethods = "verifyUserCanNavigateToMAMApproversScreen")
	@Documentation(step = "Verify after cancelling an approval, the next approver status is active, ORD-22132", expected = "Status should get active for next approver after cancelling first approval")
	public void verifyAfterCancellingApprovalNextStatusIsActive() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyNextStatusIsActiveAfterCancelApproval();
	}

	@Test(dependsOnMethods = "verifyAfterCancellingApprovalNextStatusIsActive")
	@Documentation(step = "Verifies after completed and rejected some approvers, approve button is disabled, ORD-22170", expected = "Approve button should be disabled")
	public void verifyApproveButtonIsDisabledIfThereAreRejectedApprovals() throws Throwable {
		OrderingManagerApprovalMaintenancePage.changeApprovalType("Sequential");
		BrowserAction.click(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_SAVE_BUTTON_ID);
		OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Save Order");
		OrderingManagerApprovalMaintenancePage.verifyApproveButtonIsDisabledWhenRejectedApprovalExist();
	}

	@Test(dependsOnMethods = "verifyApproveButtonIsDisabledIfThereAreRejectedApprovals")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly")
	public void verifySuperUserLogOut() throws Exception {
		OrderingManagerApprovalMaintenancePage.clickOnBackButton();
		OrderingHomePage.verifyLogOutFunctionality();
	}

	@Test(dependsOnMethods = "verifySuperUserLogOut")
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		CommonPage.testEnded();
	}

}