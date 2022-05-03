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
public class Reg_MLO_MAQ_SuperUser_Search_Export_Reset_ToggleColumns extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({ "xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
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
	@Documentation(step = "Navigate to Manager Approvals", expected ="Manager Approvals page loaded") 
	public void navigateToManagerApprovalPage() throws Exception {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingBOQueuePage.verifySideMenuOptions();
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Queue");
	}
	
	@Test(dependsOnMethods = "navigateToManagerApprovalPage")
	@Documentation(step = "Navigate to Manager Approvals", expected ="Manager Approvals page loaded") 
	public void verifyApprovalStatus() throws Exception {
		OrderingBOQueuePage.verifyQueueApprovalStatusValues();
	}
		
	@Test(dependsOnMethods = "verifyApprovalStatus")
	@Documentation(step = "Go to On Manager Approval  and verify Toogle and Queue Table columns", expected = "Application should show the Queue table columnd are same as toggle columns On Manager Approval Maintenancepage")
	public void verifyToggleColumnsAndTableColumns() throws Throwable {
		OrderingBOQueuePage.verifyQueueTableColumns();
		OrderingBOQueuePage.removeColumnsFromQueueTable("Upfit","Corp", "Year");
		OrderingBOQueuePage.verifyQueueTableColumns();
		OrderingBOQueuePage.addColumnInQueueTable("Upfit","Corp", "Year");
		OrderingBOQueuePage.verifyQueueTableColumns();
	}
	
	@Test(dependsOnMethods = "verifyToggleColumnsAndTableColumns")
	@Documentation(step = "Go to On Manager Approval  and verify Toogle and Queue Table columns", expected = "Application should show the Queue table columnd are same as toggle columns On Manager Approval Maintenancepage")
	public void verifySearchFunctionality() throws Throwable {
		OrderingManagerApprovalMaintenancePage.searchAndVerifyRecords("clientNumber", "9990");
		OrderingManagerApprovalMaintenancePage.selectSearchType("advanced");
		OrderingManagerApprovalMaintenancePage.searchAndVerifyRecords("productCode", "LT");
	}
	
	@Test(dependsOnMethods = "verifySearchFunctionality")
	@Documentation(step = "Go to On Manager Approval  and verify reset", expected = "Fields should be cleared")
	public void verifyResetFunctionality() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyReset("productCode", "LT");
	}
	
	@Test(dependsOnMethods = "verifyResetFunctionality")
	@Documentation(step = "Go to On DIO Queue page and verify Export functionality of Queue Table", expected = "Application should Export Queue table Data")
	public void verifyExportTableData() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyExportFuntionality(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "verifyExportTableData")
	@Documentation(step = "Click on Logout button from FO", expected = "User should be logged out successfully from FO") 
	public void logOutFunctionality() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}

	@Test(dependsOnMethods = "logOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}

}