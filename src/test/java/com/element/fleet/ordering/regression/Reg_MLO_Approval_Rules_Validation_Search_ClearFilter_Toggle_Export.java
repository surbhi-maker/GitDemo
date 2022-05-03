package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy Usha
 */
public class Reg_MLO_Approval_Rules_Validation_Search_ClearFilter_Toggle_Export extends BaseWebDriver {

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
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void testLaunchBOURL() throws Exception {
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Exception {
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Go to Manager Order Preferences  page", expected = " Manager Order Preferences page should be loaded")
	public void goToManagerOrderPreferencesPage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Manager Order Preferences");
		OrderingBOManagerOrderPreferencesPage.waitForManagerOrderPreferencesPageToLoaded();
	}
	
	@Test(dependsOnMethods = "goToManagerOrderPreferencesPage")
	@Documentation(step = "Enter corp and client and then verify page", expected = "Corp and client have been entered and page is verified")
	public void enterCorpAndClientAndVerify() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterCorpAndClientAndSelectClient();
	}
	
	@Test(dependsOnMethods = "enterCorpAndClientAndVerify")
	@Documentation(step = "Go to Approval Rules tab", expected = "Approval Rules Page should be loaded")
	public void goToApprovalRulesMenu() throws Exception {
		OrderingBOManagerOrderPreferencesPage.moveToSection("Approval Rules");
	}	 
			
	@Test(dependsOnMethods = "goToApprovalRulesMenu")
	@Documentation(step = "Select rules to be deleted and Click on Delete Button", expected = "Existing Rules should be deleted")
	public void searchApprovalRule() throws Exception {
		OrderingBOManagerOrderPreferencesPage.searchAndSelectApprovalRule("approval-rules-queue", CommonPage.getTestData("ApprovalRuleName"));
	}
	
	@Test(dependsOnMethods = "searchApprovalRule")
	@Documentation(step = "Select rules to be deleted and Click on Delete Button", expected = "Existing Rules should be deleted")
	public void clearFiltersApprovalRules() throws Exception {		
		OrderingBOManagerOrderPreferencesPage.verifyClearFilters("Approval Rules");
	}
	@Test(dependsOnMethods = "clearFiltersApprovalRules")
	@Documentation(step = "click on toggle column buttion", expected = "All the toggle checkboxes should be loaded")
	public void verifyAllColumnsAreAvailable() throws Exception {
		OrderingBOManagerOrderPreferencesPage.clickOnToggleButton("Approval Rules");
		OrderingBOManagerOrderPreferencesPage.compareAllToggleCheckedboxesWithVisibleTableColumns();
	} 
		
	@Test(dependsOnMethods = "verifyAllColumnsAreAvailable")
	@Documentation(step = "select few columns from toggle", expected = "Columns list in table is modified") 
	public void verifySelectedColumnsareOnlyAvailable() throws Exception {
		OrderingBOManagerOrderPreferencesPage.uncheckFewToggleCheckboxes();
		OrderingBOManagerOrderPreferencesPage.compareToggleCheckedboxesWithVisibleTableHeadings();
	}	
	
	@Test(dependsOnMethods = "verifySelectedColumnsareOnlyAvailable")
	@Documentation(step = "Click on Export button", expected = "File should be exported with file name '-queue.csv' ")
	public void verifyExportFunctionality() throws Exception {
		OrderingBOManagerOrderPreferencesPage.verifyExportCSV("approval-rules-queue", this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "verifyExportFunctionality", alwaysRun = true)
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		CommonPage.testEnded();
	}
			
}