package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingManagerApprovalMaintenancePage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy usha
 */
public class Reg_MLO_MAM_Page_Search_Export_Reset_ToggleCoulmns extends BaseWebDriver {

	@BeforeClass
	@Parameters({ "xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
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
	@Documentation(step = "Navigate to Manager Approvals Maintenance", expected ="Manager Approvals Maintenance page loaded") 
	public void navigateToManagerApprovalMaintentenancePage() throws Exception {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Maintenance");
	}
	
	@Test(dependsOnMethods = "navigateToManagerApprovalMaintentenancePage")
	@Documentation(step = "Go to On Manager Approval Maintenance and verify Toogle and Queue Table columns", expected = "Application should show the Queue table columnd are same as toggle columns On Manager Approval Maintenancepage")
	public void verifyToggleColumnsAndTableColumns() throws Throwable {
		OrderingBOQueuePage.verifyQueueTableColumns();
		OrderingBOQueuePage.removeColumnsFromQueueTable("State","Corp", "Approved");
		OrderingBOQueuePage.verifyQueueTableColumns();
		OrderingBOQueuePage.addColumnInQueueTable("State","Corp", "Approved");
		OrderingBOQueuePage.verifyQueueTableColumns();
	}
	
	@Test(dependsOnMethods = "verifyToggleColumnsAndTableColumns")
	@Documentation(step = "Go to On Manager Approval Maintenance and verify Toogle and Queue Table columns", expected = "Application should show the Queue table columnd are same as toggle columns On Manager Approval Maintenancepage")
	public void verifySearchFunctionality() throws Throwable {
		OrderingManagerApprovalMaintenancePage.searchAndVerifyRecords("clientNumber", "9990");
	}
	
	@Test(dependsOnMethods = "verifySearchFunctionality")
	@Documentation(step = "Go to On Manager Approval Maintenance and verify reset", expected = "Fields should be cleared")
	public void verifyResetFunctionality() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyReset("clientNumber", "9990");
	}
	
	@Test(dependsOnMethods = "verifyResetFunctionality")
	@Documentation(step = "Go to On DIO Queue page and verify Export functionality of Queue Table", expected = "Application should Export Queue table Data")
	public void verifyExportTableData() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyExportFuntionality(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "verifyExportTableData", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}