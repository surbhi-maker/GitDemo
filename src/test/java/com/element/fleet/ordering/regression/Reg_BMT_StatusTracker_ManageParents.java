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

public class Reg_BMT_StatusTracker_ManageParents extends BaseWebDriver {

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
	@Documentation(step = "Go to manage parents sub menu page", expected = "User should be navigated to manage parents sub menu page")
	public void navigateToManageParentsTab() throws Throwable {
		OrderingBMTStatusTrackerPage.clickOnStatusTrackerSectionTabs(CommonPage.getTestData("ManageParents"));
		OrderingBMTStatusTrackerPage.verifyStatusTrackerPageElements();
		OrderingBMTStatusTrackerPage.verifyStatusTrackerSettingsTabPresent(CommonPage.getTestData("ManageParents"));
	}

	@Test(dependsOnMethods = "navigateToManageParentsTab")
	@Documentation(step = "Try to add a new parent with a child", expected = "User should be able to create a parent with a child ")
	public void verifyAddNewParentFunctionality() throws Throwable {
		OrderingBMTStatusTrackerPage.addNewParent("Parent-"+CommonPage.randomNumberInRange(999999));
		OrderingBMTStatusTrackerPage.searchRecords("Parent Name", CommonPage.getTestData("ParentName"));
		OrderingBMTStatusTrackerPage.verifySearchedRecords(true);
	}

	@Test(dependsOnMethods = "verifyAddNewParentFunctionality")
	@Documentation(step = "Try to update existing parent with childrens", expected = "User should be able to update existing parent with new childrens")
	public void updateExistingParent() throws Throwable {
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.updateExistingParent();
	}

	@Test(dependsOnMethods = "updateExistingParent")
	@Documentation(step = "verify manage parent elements displayed", expected = "User should be navigated to manage parents sub menu page and manage parents page elements should be displayed")
	public void verifyExistingParentError() throws Throwable {
		OrderingBMTStatusTrackerPage.searchRecords("Parent Name", CommonPage.getTestData("ParentName"));
		OrderingBMTStatusTrackerPage.verifySearchedRecords(true);
		OrderingBMTStatusTrackerPage.createNewParentAndVerifyExistingParentError();
	}

	@Test(dependsOnMethods = "verifyExistingParentError")
	@Documentation(step = "Go to manage parents sub menu page and verify existing parent deleted", expected = "User should be navigated to manage parents sub menu page and existing parent deleted")
	public void verifyDeleteParentFunctionality() throws Throwable {
		OrderingBMTStatusTrackerPage.searchRecords("Parent Name", CommonPage.getTestData("ParentName"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.deleteExistingParentAndVerify();
	}

	@Test(dependsOnMethods = "verifyDeleteParentFunctionality")
	@Documentation(step = "Clear Filter and Pagination Functionality on Status Tracker Setting page", expected = "Application should be able to Clear Filters on Status Tracker Setting page")
	public void clearFilterAndPagenationFunctionality() throws Throwable {
		OrderingBMTStatusTrackerPage.verifyClearFilterFunctionality();
		OrderingBMTStatusTrackerPage.verifyPaginationFunctionality();
	}

	@Test(dependsOnMethods = "clearFilterAndPagenationFunctionality")
	@Documentation(step = "Click on Export button", expected = "File should be exported with file name '-queue.csv' ")
	public void verifyExportFunctionality() throws Exception {
		OrderingBMTStatusTrackerPage.verifyExportCSV("manage-parents-queue", this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "verifyExportFunctionality")
	@Documentation(step = "Verify Search and Toggle Filter Functionality", expected = "User should be Search and verify Toggle Filter")
	public void verifySeachAndToggleFilters() throws Throwable {
		OrderingBMTStatusTrackerPage.verifySearchAndToggleFilters();
	}
	
	@Test(dependsOnMethods="verifySeachAndToggleFilters", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
		CommonPage.testEnded();
	}

}