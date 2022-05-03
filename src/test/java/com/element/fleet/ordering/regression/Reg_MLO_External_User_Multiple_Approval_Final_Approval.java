package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingManagerApprovalMaintenancePage;
import com.element.fleet.ordering.page.OrderingManagerApprovalQueuePage;
import com.element.fleet.ordering.page.OrderingMyOrdersPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/*
 * PreCondition: Client 1750 should exist
 * Year: 2020
 * Make: Ford
 * Manufacturer: Ford
 * Approval Type: Non Sequential
 * Note: Approval rule Ford_2020
 * @lastModifiedBy Hector_Jimenez 
 */
public class Reg_MLO_External_User_Multiple_Approval_Final_Approval extends BaseWebDriver {
	@BeforeClass
	@Parameters({ "xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath",
			"orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username,
			String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime,
			ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}
	
	@Test(alwaysRun = true)
	@Documentation(step = "Open FO with super user and create given number of orders", expected = "Orders should be created and submitted")
	public void createOrders() throws Throwable {
		CommonPage.testStarted();
		OrderingBOManagerOrderPreferencesPage.createOrderSkipBilling(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "createOrders")
	@Documentation(step = "Verify superuser navigates to MAM screen", expected = "MAM screen should be visible for super user")
	public void navigateToMAMScreenWithSuperUser() throws Throwable {
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingCommonPage.checkAlertPopUp();
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Maintenance");
		OrderingManagerApprovalMaintenancePage.waitForManagerApprovalMaintenanceToLoad();
	}
	
	@Test(dependsOnMethods = "navigateToMAMScreenWithSuperUser")
	@Documentation(step = "Approves order except for specified approver", expected = "All approvers should be approve order except the one specified in data file")
	public void approveOrdersExceptForGivenApprover() throws Throwable {
	OrderingManagerApprovalMaintenancePage.approveAllApproversExceptOne();
	OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "approveOrdersExceptForGivenApprover")
	@Documentation(step = "Login with approver external user role and Navigate to MAM queue screen", expected = "Manager approval queue screen should display" )
	public void navigateToManageraintenanceQueueScreen() throws Throwable {
		String[] approvalUserIDs = CommonPage.getTestData("ApproverUserIDs").split("\\|");
		OrderingLoginPage.openCustomFOApplication(approvalUserIDs[1]);
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Queue");
		OrderingManagerApprovalQueuePage.waitForManagerApprovalQueueToLoad();
	}
	
	@Test(dependsOnMethods = "navigateToManageraintenanceQueueScreen")
	@Documentation(step = "Expected Approver approves the orders, this will be final approval, ORD-22230", expected = " Orders should be marked as final approval")
	public void verifyExpectedApproverCanAppproveOrder() throws Throwable {
		OrderingManagerApprovalQueuePage.approveOrRejectOrders("Bulk", "Approve");
	}
	
	@Test(dependsOnMethods = "verifyExpectedApproverCanAppproveOrder")
	@Documentation(step = "Navigate to My Orders screen as external user", expected = "My Orders screen should display" )
	public void navigateToMyOrdersScreen() throws Throwable {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.selectOrderingMenuOption("My Orders");
		OrderingMyOrdersPage.waitForMyOrdersToLoad();
	}
	
	@Test(dependsOnMethods = "navigateToMyOrdersScreen")
	@Documentation(step = "Verify order in my orders queue", expected = "Order should display in My Orders queue" )
	public void verifyOrderStatus() throws Throwable {
		OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO((CommonPage.getTestData("LoggerLogNumber" + 1)));
		OrderingMyOrdersPage.clickOnSearch();
		OrderingMyOrdersPage.verifySingleSearchedOrderDisplayed();	
	}
	
	@Test(dependsOnMethods = "verifyOrderStatus")
	@Documentation(step = "LogOut as external user and verify logout message", expected = "Application should be logged out properly")
	public void verifyExternalUserLogOut() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyExternalUserLogOut")
	@Documentation(step = "Login to FO with super user and navigate to MAQ screen", expected = "MAQ screen should display")
	public void navigateToMAQScreenWithSuperUser() throws Throwable {
		OrderingLoginPage.openFOApplication();	
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Queue");
		OrderingManagerApprovalQueuePage.waitForManagerApprovalQueueToLoad();
	}
	
	@Test(dependsOnMethods = "navigateToMAQScreenWithSuperUser")
	@Documentation(step = "Verify order does not display in queue,  ORD-22222", expected = "Order should not display")
	public void verifyOrderIsNotDisplayedInMAQ() throws Throwable {
		OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+1));
		OrderingMyOrdersPage.clickOnSearch();
		OrderingManagerApprovalQueuePage.verifyNoOrderInManagerApprovalQueue();
		PDFReporter.takeExtraScreenshot();
	}
	
	@Test(dependsOnMethods = "verifyOrderIsNotDisplayedInMAQ")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly")
	public void verifySuperUserLogOut() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}

	@Test (alwaysRun = true, dependsOnMethods = "verifySuperUserLogOut" )
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		CommonPage.testEnded();
	}
	
}