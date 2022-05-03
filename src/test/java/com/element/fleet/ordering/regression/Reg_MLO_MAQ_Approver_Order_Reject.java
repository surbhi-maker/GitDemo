package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingManagerApprovalQueuePage;
import com.element.fleet.ordering.page.OrderingSummaryPage;
import com.element.fleet.ordering.page.OrderingWIPOrdersPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

/*
 * PreCondition: Client 1750 should exist
 * Year: 2020
 * Make: Ford
 * Manufacturer: Ford
 * Approval Type: Sequential
 * Note: Approval rule Ford_2020
 * @lastModifiedBy Hector_Jimenez 
 */
public class Reg_MLO_MAQ_Approver_Order_Reject extends BaseWebDriver {
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
	@Documentation(step = "Create order with logger user", expected = "Orders with logger user is created and selects client from CSV file")
	public void createOrdersWithLoggerUser() throws Throwable {
		OrderingBOManagerOrderPreferencesPage.createOrderSkipBilling(this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "createOrdersWithLoggerUser")
	@Documentation(step = "Go to Manager Approval Maintenance screen", expected = "Manager Approval Maintenance should display")
	public void closeSummaryPageAndGoToMyMAM() throws Throwable {
		OrderingLoginPage.openFOApplication();
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor)
		WebDriverAccess.getDriver());
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingManagerApprovalQueuePage.orderingSideMenuOptionLoadedExternalUser();
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Queue");
		OrderingManagerApprovalQueuePage.waitForManagerApprovalQueueToLoad();
	}
	
	@Test(dependsOnMethods = "closeSummaryPageAndGoToMyMAM")
	@Documentation(step = "Verify user is able to cancel an order from list view", expected = "operation should be canceled")
	public void verifyUserCanCancelOrder() throws Throwable {
		OrderingManagerApprovalQueuePage.clickFirstRecordAndValidateSummaryScreen();
		OrderingSummaryPage.clickCancelButton();
		OrderingManagerApprovalQueuePage.waitForManagerApprovalQueueToLoad();
	}

	@Test(dependsOnMethods = "verifyUserCanCancelOrder")
	@Documentation(step = "Search log number ", expected = "Order Should display")
	public void searchOrderByLogNumber() throws Throwable {
		OrderingManagerApprovalQueuePage.enterLogNumber(CommonPage.getTestData("LoggerLogNumber" + 1));
		OrderingManagerApprovalQueuePage.clickOnSearch();
		OrderingManagerApprovalQueuePage.selectOrderInManagerApprovalQueue();
	}
	
	@Test(dependsOnMethods = "searchOrderByLogNumber")
	@Documentation(step = "Reject order displayed, ORD-22110", expected = "Order should be rejected")
	public void rejectOrder() throws Throwable {
		OrderingManagerApprovalQueuePage.performOrderAction("Reject", true);
	}
	
	@Test(dependsOnMethods = "rejectOrder")
	@Documentation(step = "Go to work in process tab ", expected = "Work in processs orders screen should display")
	public void navigateToWorkInProgressScreen() throws Throwable {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingManagerApprovalQueuePage.orderingSideMenuOptionLoadedExternalUser();
		OrderingHomePage.selectOrderingMenuOption("Work In Process Orders");
		OrderingWIPOrdersPage.waitForWorkInProcessOrdersToLoad();
		OrderingManagerApprovalQueuePage.enterLogNumber(CommonPage.getTestData("LoggerLogNumber" + 1));
		OrderingManagerApprovalQueuePage.clickOnSearch();
		OrderingWIPOrdersPage.waitForWorkInProcessOrdersToLoad();
	}

	@Test(dependsOnMethods = "navigateToWorkInProgressScreen")
	@Documentation(step = "Verify order has a rejected status, ORD-22110, ORD-22114 ", expected = "Order should be in rejected status")
	public void verifyOrderRejectedStatus() throws Throwable {
		OrderingWIPOrdersPage.verifyRejectedStatusLabel();
	}
	
	@Test(dependsOnMethods = "verifyOrderRejectedStatus")
	@Documentation(step = "Verify user can cancel an order, ORD-22131 ", expected = "should have an option to cancel the order which is rejected earlier")
	public void verifyUserCanCancelRejectedOrder() throws Throwable {
		OrderingWIPOrdersPage.cancelRejectedOrder();
	}

	@Test(dependsOnMethods = "verifyUserCanCancelRejectedOrder")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly")
	public void verifySuperUserLogOut() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test ( dependsOnMethods = "verifySuperUserLogOut", alwaysRun = true )
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		CommonPage.testEnded();
	}
	
}