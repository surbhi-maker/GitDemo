package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingManagerApprovalQueuePage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/*
 * PreCondition: Client 1750 should exist
 * Year: 2020
 * Make: Ford
 * Manufacturer: Ford
 * Approval Type: Non Sequential
 * Note: Approval rule Ford_2020
 * @lastModifiedBy Hector_Jimenez 
 *
 */
public class Reg_MLO_Individual_Approval_Verify_Export extends BaseWebDriver {
	
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
	@Documentation(step = "Verify superuser navigates to MAM screen", expected = "MAM screen should be visible for super user")
	public void navigateToMAMScreenWithSuperUser() throws Throwable {
		CommonPage.testStarted();
		OrderingLoginPage.openFOApplication();
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Queue");
		OrderingManagerApprovalQueuePage.waitForManagerApprovalQueueToLoad();
	}
	
	@Test(dependsOnMethods = "navigateToMAMScreenWithSuperUser")
	@Documentation(step = "Verify Export after selecting one order, ORD-22216, ", expected = "Excel file should be downloaded and contain expected columns" )
	public void verifyExcelExportFile() throws Throwable {
		OrderingManagerApprovalQueuePage.verifyExportFuntionality();
		OrderingManagerApprovalQueuePage.verifyHeadersFromExportCSV();
	}

	@Test(dependsOnMethods = "verifyExcelExportFile")
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