package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
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
public class Reg_MLO_External_User_Approve_Order_With_SuperUser extends BaseWebDriver {
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
		OrderingHomePage.verifyLogOutFunctionality();
	}

	@Test(dependsOnMethods = "createOrders")
	@Documentation(step = "Navigate to MAM queue screen with given external user", expected = "Manager approval queue screen should display" )
	public void navigateToManageraintenanceQueueScreen() throws Throwable {
		String[] approvalUserIDs = CommonPage.getTestData("ApproverUserIDs").split("\\|");
		OrderingLoginPage.openCustomFOApplication(approvalUserIDs[1]);
		
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Queue");
		OrderingManagerApprovalQueuePage.waitForManagerApprovalQueueToLoad();
	} 
	
	@Test(dependsOnMethods = "navigateToManageraintenanceQueueScreen")
	@Documentation(step = "Verify that given log number is present for external user", expected = " Order should be present in external user MAQ screen")
	public void verifyOrderIsDisplayedInUserQueue() throws Throwable {
		OrderingManagerApprovalQueuePage.verifyLogNumberIsDisplayed();
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyOrderIsDisplayedInUserQueue")
	@Documentation(step = "Verify superuser logs to FO and navigates to MAM screen", expected = "MAM screen should be visible for super user")
	public void navigateToMAMScreenWithSuperUser() throws Throwable {
		OrderingLoginPage.openFOApplication();
		
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Maintenance");
		OrderingManagerApprovalMaintenancePage.waitForManagerApprovalMaintenanceToLoad();
	}
	
	@Test(dependsOnMethods = "navigateToMAMScreenWithSuperUser")
	@Documentation(step = "Super users approves the order", expected = "Order should be approved")
	public void rejectGivenOrder() throws Throwable {
	OrderingManagerApprovalMaintenancePage.approveOrder();
	} 
	
	@Test(dependsOnMethods = "rejectGivenOrder")
	@Documentation(step = "Super user logs out", expected = "Application should be logged out properly")
	public void verifySuperUserLogOut() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifySuperUserLogOut")
	@Documentation(step = "External user logs back and Navigates to MAM queue screen", expected = "Manager approval queue screen should display for external user" )
	public void navigateToMAQScreenWithExternalUser() throws Throwable {
		String[] approvalUserIDs = CommonPage.getTestData("ApproverUserIDs").split("\\|");
		OrderingLoginPage.openCustomFOApplication(approvalUserIDs[1]);
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Queue");
		OrderingManagerApprovalQueuePage.waitForManagerApprovalQueueToLoad();
	}
	
	@Test(dependsOnMethods = "navigateToMAQScreenWithExternalUser")
	@Documentation(step = "External user verifies that order is not present in queue, ORD-22219", expected = "ORder should not display in queue" )
	public void verifyOrderIsNotDisplayedInMAQ() throws Throwable {
		OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+1));
		OrderingMyOrdersPage.clickOnSearch();
		OrderingManagerApprovalQueuePage.verifyNoOrderInManagerApprovalQueue();
		PDFReporter.takeExtraScreenshot();
	}
	
	@Test(dependsOnMethods = "verifyOrderIsNotDisplayedInMAQ")
	@Documentation(step = "External user logs out", expected = "Application should be logged out properly")
	public void verifyExternalUserLogOut() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test (alwaysRun = true, dependsOnMethods = "verifyExternalUserLogOut" )
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		CommonPage.testEnded();
	}
	
}