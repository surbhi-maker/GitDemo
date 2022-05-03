package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/*
 * @lastModifiedBy hjimenez 
 */
public class Reg_MyQueue_On_Hold_Queue extends BaseWebDriver {
	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
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
	@Documentation(step = "log in to FO with external user", expected = "FO Home page should display")
	public void logInWithExternalUser() throws Throwable {
		String userId = CommonPage.getTestData("externalUserIDs");
		OrderingLoginPage.openOrderingWithExternalUser(userId);
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown();
		OrderingHomePage.changeClient();
	}
	
	@Test(dependsOnMethods = "logInWithExternalUser")
	@Documentation(step = "Click On Hold Specs", expected = "On Hold Specs should be loaded")
	public void clickOnHoldSpecs() throws Exception {
		OrderingHomePage.clickOnHoldSpecsTab();
	}
	
	@Test(dependsOnMethods = "clickOnHoldSpecs")
	@Documentation(step = "Validate Columns in table from on hold specs tab", expected = "Expected columns should be displayed")
	public void validateOnHoldSpecsTableColumns() throws Exception {
		String [] expectedHeaders = CommonPage.getTestData("expectedHeaders").split("\\|");
		int startIndex = Integer.parseInt(CommonPage.getTestData("ColumnStartValue"));
		int lastIndex = Integer.parseInt(CommonPage.getTestData("ColumnLastValue"));
		OrderingHomePage.clearFilters();
		OrderingHomePage.verifyColumnHeadersValues(expectedHeaders, startIndex, lastIndex);
	}
	
	@Test(dependsOnMethods = "validateOnHoldSpecsTableColumns")
	@Documentation(step = "Validate Default Sorting method", expected = "Columns should be sorted by Pending on Hold Approval column")
	public void validateDefaultSortingMethod() throws Exception {
		OrderingHomePage.verifyDefaultSorting();
	}
	
	@Test(dependsOnMethods = "validateDefaultSortingMethod")
	@Documentation(step = "Validate Default Search inputs", expected = "Expected search inputs should be displayed")
	public void validateSearchInputs() throws Exception {
		OrderingHomePage.clickOnHoldSpecsAdvancedSearch();
		OrderingHomePage.verifySearchInputsOnHoldSpecsTab();
	}
	
	@Test(dependsOnMethods = "validateSearchInputs")
	@Documentation(step = "Validate Search Functionality", expected = "Search fields display should  work as expected")
	public void validateSearchFunctionality() throws Exception {
		int numberOfValues = Integer.parseInt(CommonPage.getTestData("NumberOfValues"));
		OrderingHomePage.verifySearchFunctionalityOnHoldSpecs(numberOfValues);
	}
	
	@Test(dependsOnMethods = "validateSearchFunctionality")
	@Documentation(step = "Verify CSV export functionality", expected = "CSV should be downloaded")
	public void verifyExportFunctionality() throws Throwable {
		OrderingHomePage.verifyExportFuntionality();
	}
	
	@Test(dependsOnMethods = "verifyExportFunctionality")
	@Documentation(step = "Verify columns in downloaded CSV file", expected = "CSV downloaded should contain expected columns")
	public void verifyCSVFileDownloaded() throws Throwable {
		String fileName = "On Hold Specs.csv";
		String []expectedCSVHeaders = CommonPage.getTestData("CSVHeaders").split("\\|");
		OrderingHomePage.verifyHeadersFromExportCSV(fileName, expectedCSVHeaders);
	}
	
	@Test(dependsOnMethods = "verifyCSVFileDownloaded")
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
