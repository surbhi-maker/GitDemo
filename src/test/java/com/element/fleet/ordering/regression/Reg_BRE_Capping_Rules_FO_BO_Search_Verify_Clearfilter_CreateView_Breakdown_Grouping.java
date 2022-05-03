package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy akandkonde
 */
public class Reg_BRE_Capping_Rules_FO_BO_Search_Verify_Clearfilter_CreateView_Breakdown_Grouping extends BaseWebDriver {
	
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
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void launchBOURL() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openBOApplication();
	}
	
	@Test(dependsOnMethods = "launchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void loginBOApplication() throws Exception {
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "loginBOApplication")
	@Documentation(step = "Go to scheduler BMT", expected = "Application should be navigated to scheduler BMT")
	public void goToCappingRules() throws Exception {
		OrderingBOQueuePage.goToBusinessMaintainedTable();
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Capping Rules");
		OrderingBOBusinessMaintainedTablesPage.verifyCappingRulesTitlesAndLabels();
	}

	@Test(dependsOnMethods = "goToCappingRules")
	@Documentation(step = "Verify vehicle and driver breakdown for capping smart rule", expected = "Driver breakdown and vehicle breakdown should be displayed properly and should be invisible for multiple clients")
	public void verifyBreakDownBOCappingSmart() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyVehicleBreakdown("Capping Smart Rule");
		OrderingBOBusinessMaintainedTablesPage.verifyDriverBreakDown("Capping Smart Rule");
	}
	
	@Test(dependsOnMethods = "verifyBreakDownBOCappingSmart")
	@Documentation(step = "Verify grouping for capping smart rule", expected = "Grouping should work as expected")
	public void verifyGroupingCappingSmart() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyGrouping("Capping Smart Rule");
	}
	
	@Test(dependsOnMethods = "verifyGroupingCappingSmart")
	@Documentation(step = "Search and verify search results and then validate clear filter function", expected = "Search and clear filter function should work as expected")
	public void searchAndVerifyResultsAndClearFilterValidationCappingSmart() throws Exception {
		 OrderingBOBusinessMaintainedTablesPage.searchWithParameters("Capping Smart Rule", "Corp Code", CommonPage.getTestData("Corp"));
		 OrderingBOBusinessMaintainedTablesPage.verifySearchResults("Capping Smart Rule", "Corp Code", CommonPage.getTestData("Corp"));
		 OrderingBOBusinessMaintainedTablesPage.verifyClearFilterFunctionality("Capping Smart Rule");
	}
	
	@Test(dependsOnMethods = "searchAndVerifyResultsAndClearFilterValidationCappingSmart")
	@Documentation(step = "Create default view and verify table columns", expected = "Default view should be created and only the selected columns should be present in te table")
	public void verifyDefaultViewCreationAndVerifyTableColumnsCappingSmart() throws Exception {
		 OrderingBOBusinessMaintainedTablesPage.createView("Capping Smart Rule");
		 OrderingBOBusinessMaintainedTablesPage.deleteCreatedView("Capping Smart Rule");
	}
	
	@Test(dependsOnMethods = "verifyDefaultViewCreationAndVerifyTableColumnsCappingSmart")
	@Documentation(step = "Verify vehicle and driver breakdown for capping smart rule", expected = "Driver breakdown and vehicle breakdown should be displayed properly and should be invisible for multiple clients")
	public void verifyBreakDownBOCapping() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyVehicleBreakdown("Capping Rule");
		OrderingBOBusinessMaintainedTablesPage.verifyDriverBreakDown("Capping Rule");
	}
	
	@Test(dependsOnMethods = "verifyBreakDownBOCapping")
	@Documentation(step = "Verify grouping for capping smart rule", expected = "Grouping should work as expected")
	public void verifyGroupingCapping() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyGrouping("Capping Rule");
	}
	
	@Test(dependsOnMethods = "verifyGroupingCapping")
	@Documentation(step = "Search and verify search results and then validate clear filter function", expected = "Search and clear filter function should work as expected")
	public void searchAndVerifyResultsAndClearFilterValidationCapping() throws Exception {
		 OrderingBOBusinessMaintainedTablesPage.searchWithParameters("Capping Rule", "Corp Code", CommonPage.getTestData("Corp"));
		 OrderingBOBusinessMaintainedTablesPage.verifySearchResults("Capping Rule", "Corp Code", CommonPage.getTestData("Corp"));
		 OrderingBOBusinessMaintainedTablesPage.verifyClearFilterFunctionality("Capping Rule");
	}
	
	@Test(dependsOnMethods = "searchAndVerifyResultsAndClearFilterValidationCapping")
	@Documentation(step = "Create default view and verify table columns", expected = "Default view should be created and only the selected columns should be present in te table")
	public void verifyDefaultViewCreationAndVerifyTableColumnsCapping() throws Exception {
		 OrderingBOBusinessMaintainedTablesPage.createView("Capping Rule");
		 OrderingBOBusinessMaintainedTablesPage.deleteCreatedView("Capping Rule");
	}
	
	@Test(dependsOnMethods = "verifyDefaultViewCreationAndVerifyTableColumnsCapping")
	@Documentation(step = "Search and verify search results and then validate clear filter function", expected = "Search and clear filter function should work as expected")
	public void searchAndVerifyResultsAndClearFilterValidationArchivedCappingSmart() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnArchivedTab();
		 OrderingBOBusinessMaintainedTablesPage.searchWithParameters("Archive Capping Smart Rule", "Corp Code", CommonPage.getTestData("Corp"));
		 OrderingBOBusinessMaintainedTablesPage.verifySearchResults("Archive Capping Smart Rule", "Corp Code", CommonPage.getTestData("Corp"));
		 OrderingBOBusinessMaintainedTablesPage.verifyClearFilterFunctionality("Archive Capping Smart Rule");
	}
	
	@Test(dependsOnMethods = "searchAndVerifyResultsAndClearFilterValidationArchivedCappingSmart")
	@Documentation(step = "Create default view and verify table columns", expected = "Default view should be created and only the selected columns should be present in te table")
	public void verifyDefaultViewCreationAndVerifyTableColumnsArchivedCappingSmart() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.createView("Archive Capping Smart Rule");
		OrderingBOBusinessMaintainedTablesPage.deleteCreatedView("Archive Capping Smart Rule");
	}
	 
	@Test(dependsOnMethods = "verifyDefaultViewCreationAndVerifyTableColumnsArchivedCappingSmart")
	@Documentation(step = "Search and verify search results and then validate clear filter function", expected = "Search and clear filter function should work as expected")
	public void searchAndVerifyResultsAndClearFilterValidationArchivedCapping() throws Exception {
		 OrderingBOBusinessMaintainedTablesPage.searchWithParameters("Archive Capping Rule", "Corp Code", CommonPage.getTestData("Corp"));
		 OrderingBOBusinessMaintainedTablesPage.verifySearchResults("Archive Capping Rule", "Corp Code", CommonPage.getTestData("Corp"));
		 OrderingBOBusinessMaintainedTablesPage.verifyClearFilterFunctionality("Archive Capping Rule");
	}
	
	@Test(dependsOnMethods = "searchAndVerifyResultsAndClearFilterValidationArchivedCapping")
	@Documentation(step = "Create default view and verify table columns", expected = "Default view should be created and only the selected columns should be present in te table")
	public void verifyDefaultViewCreationAndVerifyTableColumnsArchivedCapping() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.createView("Archive Capping Rule");
		OrderingBOBusinessMaintainedTablesPage.deleteCreatedView("Archive Capping Rule");
	}
	 
	@Test(dependsOnMethods = "verifyDefaultViewCreationAndVerifyTableColumnsArchivedCapping")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality")
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void testLaunchURL() throws Exception {
		OrderingLoginPage.openFOApplication();
		OrderingHomePage.waitForHomePage();
	}
	
	@Test(dependsOnMethods = "testLaunchURL")
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void goToFOCappingRulePage() throws Exception {
		OrderingHomePage.clickOnFleetPolicies();
		OrderingHomePage.verifyFleetPoliciesOptionListTitle();
		OrderingHomePage.goToFOCappingRule();
	}
	
	@Test(dependsOnMethods = "goToFOCappingRulePage")
	@Documentation(step = "Search and verify search results and then validate clear filter function", expected = "Search and clear filter function should work as expected")
	public void searchAndVerifyResultsAndClearFilterValidationFOCappingSmart() throws Exception {
		 OrderingBOBusinessMaintainedTablesPage.searchWithParameters("Capping Smart Rule", "Corp Code", CommonPage.getTestData("Corp"));
		 OrderingBOBusinessMaintainedTablesPage.verifySearchResults("Capping Smart Rule", "Corp Code", CommonPage.getTestData("Corp"));
		 OrderingBOBusinessMaintainedTablesPage.verifyClearFilterFunctionality("Capping Smart Rule");
	}
	
	@Test(dependsOnMethods = "searchAndVerifyResultsAndClearFilterValidationFOCappingSmart")
	@Documentation(step = "Create default view and verify table columns", expected = "Default view should be created and only the selected columns should be present in te table")
	public void verifyDefaultViewCreationAndVerifyTableColumnsFOCappingSmart() throws Exception {
		 OrderingBOBusinessMaintainedTablesPage.createView("Capping Smart Rule");
		 OrderingBOBusinessMaintainedTablesPage.deleteCreatedView("Capping Smart Rule");
	}
	
	@Test(dependsOnMethods = "verifyDefaultViewCreationAndVerifyTableColumnsFOCappingSmart")
	@Documentation(step = "Search and verify search results and then validate clear filter function", expected = "Search and clear filter function should work as expected")
	public void searchAndVerifyResultsAndClearFilterValidationFOCapping() throws Exception {
		 OrderingBOBusinessMaintainedTablesPage.searchWithParameters("Capping Rule", "Corp Code",CommonPage.getTestData("Corp"));
		 OrderingBOBusinessMaintainedTablesPage.verifySearchResults("Capping Rule", "Corp Code", CommonPage.getTestData("Corp"));
		 OrderingBOBusinessMaintainedTablesPage.verifyClearFilterFunctionality("Capping Rule");
	}
	
	@Test(dependsOnMethods = "searchAndVerifyResultsAndClearFilterValidationFOCapping")
	@Documentation(step = "Create default view and verify table columns", expected = "Default view should be created and only the selected columns should be present in te table")
	public void verifyDefaultViewCreationAndVerifyTableColumnsFOCapping() throws Exception {
		 OrderingBOBusinessMaintainedTablesPage.createView("Capping Rule");
		 OrderingBOBusinessMaintainedTablesPage.deleteCreatedView("Capping Rule");
	}
	
	@Test(dependsOnMethods="verifyDefaultViewCreationAndVerifyTableColumnsFOCapping")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly") 
	public void verifyFOLogOutFunctionality() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyFOLogOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}
