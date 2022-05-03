package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingManagerApprovalQueuePage;
import com.element.fleet.ordering.page.OrderingMyOrdersPage;
import com.element.fleet.ordering.page.OrderingWIPOrdersPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/*
 * PreCondition: Client 1791 should exist
 * Year: 2020
 * Make: Ford
 * Manufacturer: Ford
 * Approval Type: Sequential
 * Note: Approval rule Ford_2020
 * @lastModifiedBy Hector_Jimenez 
 */
public class Reg_MLO_MAQ_Validate_Order extends BaseWebDriver {
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
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void testLaunchURL() throws Exception {
		OrderingLoginPage.openFOApplication();
	}
	
	@Test(dependsOnMethods = "testLaunchURL")
	@Documentation(step = "Enter the required client number to the application",expected = "The application display the required client") 
	public void changeClientBreakdown() throws Exception {
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
	}
	
	@Test(dependsOnMethods = "changeClientBreakdown")
	@Documentation(step = "Navigate to Manager Approvals, ORD-22225", expected ="Manager Approvals page loaded") 
	public void navigateToManagerApprovalPage() throws Throwable {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingBOQueuePage.verifySideMenuOptions();
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Queue");
	}
	
	@Test(dependsOnMethods = "navigateToManagerApprovalPage")
	@Documentation(step = "Verify search functionality, ORD-22218", expected ="Search with valid data should show results") 
	public void verifySearchFunctionality() throws Exception {
		OrderingManagerApprovalQueuePage.verifySearchFunctionality();
	}
	
	@Test(dependsOnMethods = "verifySearchFunctionality")
	@Documentation(step = "Verify user is able to reset search, ORD-22206", expected ="Reset button should show all results") 
	public void verifyResetFunction() throws Exception {
		OrderingManagerApprovalQueuePage.resetSearch();
	}
	
	@Test(dependsOnMethods = "verifyResetFunction")
	@Documentation(step = "Verify user is able to search with Advanced search fields, ORD-22204", expected ="User should be able to search using advanced search fields") 
	public void verifyAdvancedSearch() throws Exception {
		OrderingManagerApprovalQueuePage.performAdvancedSearch();
	}
	
	@Test(dependsOnMethods = "verifyAdvancedSearch")
	@Documentation(step = "Clicks the first record displayed and validates summary screen is displayed, ORD-22226, ORD-22208", expected ="Summary screen should be displayed") 
	public void verifyUserLandsOnSummaryScreen() throws Exception {
		OrderingManagerApprovalQueuePage.clickFirstRecordAndValidateSummaryScreen();
		OrderingManagerApprovalQueuePage.clickCancelButtonSummaryScreen();
	}
	
	@Test(dependsOnMethods = "verifyUserLandsOnSummaryScreen")
	@Documentation(step = "Verify Log number should not show records on the page for which order status is Approved, ORD-22193", expected ="Log number should not display in WIP screen") 
	public void verifyApprovedOrderDontDisplayOnWipScreen() throws Exception {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingBOQueuePage.verifySideMenuOptions();
		OrderingHomePage.selectOrderingMenuOption("My Orders");
		OrderingMyOrdersPage.waitForMyOrdersToLoad();
		String logNumber = OrderingManagerApprovalQueuePage.getLogNumberOfFirstRecord();
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingBOQueuePage.verifySideMenuOptions();
		OrderingHomePage.selectOrderingMenuOption("Work In Process Orders");
		OrderingManagerApprovalQueuePage.enterLogNumber(logNumber);
		OrderingManagerApprovalQueuePage.clickOnSearch();
		OrderingWIPOrdersPage.verifyNoRecordsDisplay();
	}
	
	@Test(dependsOnMethods = "verifyApprovedOrderDontDisplayOnWipScreen")
	@Documentation(step = "Logs out user from FO", expected = "Login screen should display") 
	public void logOutFunctionality() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}

	@Test(dependsOnMethods = "logOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}

}