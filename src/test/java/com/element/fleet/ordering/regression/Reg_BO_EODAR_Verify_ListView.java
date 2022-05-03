package com.element.fleet.ordering.regression;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy Hector_Jimenez
 */
public class Reg_BO_EODAR_Verify_ListView extends BaseWebDriver {
	
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
	@Documentation(step = "Login to BO application", expected = "BO Home Page should display") 
	public void loginToBO() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openBOApplication();
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "loginToBO")
	@Documentation(step = "Navigate to Business Maintained Tables", expected = "BMT screen should be displayed")
	public void goToBusinessMaintainedTablesPage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToBusinessMaintainedTablesPage")
	@Documentation(step = "Navigate to Element Ordering Dealer Assignment Rules page", expected = "EODAR screen should be displayed")
	public void goToEODARPage() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Element Ordering Dealer Assignment Rules");
	}
	
	@Test(dependsOnMethods = "goToEODARPage")
	@Documentation(step = "Verify the table headers are listed as per the requirement", expected = "Headers should be present and in correct order")
	public void verifyTableColumnsEodarScreen() throws Exception {
		String [] expectedColumns = {"Corp", "Rule ID", "Rule Name", "Start Date", "End Date", "Make", 
				"Diverse Dealer", "Dealer Code", "Dealer Name", "Always Use/Never Use", "Last updated by", "Last updated on"};
		OrderingBOBusinessMaintainedTablesPage.verifyColumnHeadersValues(expectedColumns);		
	}
	
	@Test(dependsOnMethods = "verifyTableColumnsEodarScreen")
	@Documentation(step = "Verify the rules are sorted as per Corp column", expected = "Rules should be listed as per Corp column")
	public void verifyDefaultSortPerCorpColumn() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.verifyDefaultSortEodarScreen();
	}

	@Test(dependsOnMethods = "verifyDefaultSortPerCorpColumn")
	@Documentation(step = "Verify CSV export functionality", expected = "CSV should be downloaded")
	public void verifyExportFunctionality() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.verifyExportFuntionality();
	}
	
	@Test(dependsOnMethods = "verifyExportFunctionality")
	@Documentation(step = "Verify columns in downloaded CSV file", expected = "CSV downloaded should contain expected columns")
	public void verifyCSVFileDownloaded() throws Throwable {
		String fileName = "Element Ordering Dealer Assignment Rules-Table-" + 	CommonPage.getCurrentDataIn("yyyyMMdd") + ".csv";
		String [] expectedCSVHeaders = {"Corp", "Rule ID", "Rule Name", "Start Date", "End Date", "Make", 
				"Diverse Dealer", "Dealer Code", "Dealer Name", "Always Use/Never Use", "Last updated by", "Last updated on"};
		OrderingBOBusinessMaintainedTablesPage.verifyHeadersFromExportCSV(fileName, expectedCSVHeaders);
	}
	
	@Test(dependsOnMethods = "verifyCSVFileDownloaded")
	@Documentation(step = "Verify search functionality", expected = "Search should display record when giving valid data")
	public void verifySearchFunctionality() throws Exception {
		int ruleIdColumnNumber = 1;
		OrderingBOBusinessMaintainedTablesPage.verifySearchRuleFunctionality(ruleIdColumnNumber);
	}
	
	@Test(dependsOnMethods = "verifySearchFunctionality")
	@Documentation(step = "Verify filter remains active when entering edit view screen", expected = "Filter should remain active when entering and leaving edit view screen")
	public void verifyFilterIsActive() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyFilterIsActiveWhenEnteringEditView();
	}
	
	@Test(dependsOnMethods = "verifyFilterIsActive")
	@Documentation(step = "Verify Clear Filters functionality", expected = "Search should reset to default values when pressing clear button")
	public void verifyClearFunctionality() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyClearFilterFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyClearFunctionality")
	@Documentation(step = "Verify the toggle value labels", expected = "Values of toggle should be the expected values")
	public void verifyToggleValues() throws Throwable {
		String [] expectedValues = {"Corp", "Rule ID", "Rule Name", "Start Date", "End Date", 
				"Make", "Diverse Dealer", "Dealer Code", "Dealer Name", "Always Use/Never Use", "Last updated by", "Last updated on"};
		String [] elementToggleText = {"Corp", "Rule ID", "Rule Name", "Start Date", "End Date", 
				"Make", "Diverse Dealer", "Dealer Code", "Dealer Name", "Always Use/Never Use", "Last updated by", "Last updated on"};
		OrderingBOBusinessMaintainedTablesPage.verifyToggleValues(expectedValues, elementToggleText);
	}
	
	@Test(dependsOnMethods = "verifyToggleValues")
	@Documentation(step = "Press 'Toggle Columns' button and filter data by each option", expected = "Each column should be removed when deselecting the option from toggle")
	public void verifyToggleColumnsFunctionality() throws Exception {
		String[] columnValues = { "corpCode", "ruleGroupId", "ruleName", "effDate","endDate", "make",
				"diverseDealer", "dealerCode", "dealerName", "alwaysNever", "lastUpdateBy", "lastUpdateDate" };
		String [] toggleOptions = {"Corp", "Rule ID", "Rule Name", "Start Date", "End Date", "Make", "Diverse Dealer",
				"Dealer Code", "Dealer Name", "Always Use/Never Use", "Last updated by", "Last updated on"};
		OrderingBOBusinessMaintainedTablesPage.verifyToggleColumnsFunctionality(columnValues, toggleOptions );
	}
	
	@Test(dependsOnMethods = "verifyToggleColumnsFunctionality")
	@Documentation(step = "Once exiting EODAR screen and returning, table should reset to default values", expected = "EODAR screen should show default search criteria")
	public void verifyResetFunctionality() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyResetFunctionalityWhenExitingBMTScreen("Element Ordering Dealer Assignment Rules");
	}
	
	@Test(dependsOnMethods = "verifyResetFunctionality")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}	
	
}