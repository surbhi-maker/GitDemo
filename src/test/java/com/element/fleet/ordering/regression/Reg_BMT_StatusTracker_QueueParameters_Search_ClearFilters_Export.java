package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBMTStatusTrackerPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

/**
 * @lastModifiedBy UshaNaidu
 */
public class Reg_BMT_StatusTracker_QueueParameters_Search_ClearFilters_Export extends BaseWebDriver {

	@BeforeClass
	@Parameters({ "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
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
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor)WebDriverAccess.getDriver());
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Go to On Status Tracker Setting page", expected = "Application should load Status Tracker Setting page")
	public void goToStatusTrackerSettingePage() throws Throwable {
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Status Tracker Setting");
		OrderingBMTStatusTrackerPage.waitForStatusTrackerSettingPage();
	}
	
	@Test(dependsOnMethods = "goToStatusTrackerSettingePage")
	@Documentation(step = "Go to On Query Parameters page", expected = "Application should be redirected to Query Parameters page")
	public void goToQueryParameterSection() throws Throwable {
		OrderingBMTStatusTrackerPage.clickOnStatusTrackerSectionTabs("Queue Parameters & Client Exceptions");
		OrderingBMTStatusTrackerPage.waitForStatusTrackerSettingPage();
		OrderingBMTStatusTrackerPage.verifyStatusTrackerPageElements();
	}
	
	@Test(dependsOnMethods = "goToQueryParameterSection")
	@Documentation(step = "Search Functionality on Query Parameters page", expected = "Application should search the records on Query Parameters page")
	public void searchFunctionality() throws Throwable {
		OrderingBMTStatusTrackerPage.searchRecords("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingBMTStatusTrackerPage.verifySearchedRecords(true);
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
	}
	
	@Test(dependsOnMethods = "searchFunctionality")
	@Documentation(step = "Add Client Exceptions", expected = "Application should Add Client Exceptions on Query Parameters page")
	public void addClientExceptions() throws Throwable {
		OrderingBMTStatusTrackerPage.verifyQueueParameterPageElements();
		OrderingBMTStatusTrackerPage.selectQueueParametersCheckbox();
		OrderingBMTStatusTrackerPage.addClientExceptionForAllQueues();
		OrderingBMTStatusTrackerPage.waitForStatusTrackerSettingPage();
		OrderingBMTStatusTrackerPage.searchRecords("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.waitForQueueParameterPageLoad();
		OrderingBMTStatusTrackerPage.verifyQueueParameterAndClientException();
	}
	
	@Test(dependsOnMethods = "addClientExceptions")
	@Documentation(step = "Update Client Exceptions", expected = "Application should Update Client Exceptions on Query Parameters page")
	public void updateClientExceptions() throws Throwable {
		OrderingBMTStatusTrackerPage.waitForStatusTrackerSettingPage();
		OrderingBMTStatusTrackerPage.searchRecords("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.waitForQueueParameterPageLoad();
		OrderingBMTStatusTrackerPage.updateAllClientExceptions();
		OrderingBMTStatusTrackerPage.waitForStatusTrackerSettingPage();
		OrderingBMTStatusTrackerPage.searchRecords("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.waitForQueueParameterPageLoad();
		OrderingBMTStatusTrackerPage.verifyQueueParameterAndClientException();
	}
	
	@Test(dependsOnMethods = "updateClientExceptions")
	@Documentation(step = "Delete Client Exceptions", expected = "Application should Delete Client Exceptions on Query Parameters page")
	public void deleteClientExceptions() throws Throwable {
		OrderingBMTStatusTrackerPage.waitForStatusTrackerSettingPage();
		OrderingBMTStatusTrackerPage.searchRecords("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.waitForQueueParameterPageLoad();
		OrderingBMTStatusTrackerPage.verifyDeleteClientException();
	}
	
	@Test(dependsOnMethods = "deleteClientExceptions")
	@Documentation(step = "Click on Export button", expected = "File should be exported with file name '-queue.csv' ")
	public void verifyExportFunctionality() throws Exception {
		OrderingBMTStatusTrackerPage.verifyExportCSV("queue-parameters-queue", this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "verifyExportFunctionality")
	@Documentation(step = "Verify Search and Toggle Filter Functionality", expected = "User should be Search and verify Toggle Filter")
	public void verifySeachAndToggleFilters() throws Throwable {
		OrderingBMTStatusTrackerPage.verifySearchAndToggleFilters();
	}
	
	@Test(alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
}