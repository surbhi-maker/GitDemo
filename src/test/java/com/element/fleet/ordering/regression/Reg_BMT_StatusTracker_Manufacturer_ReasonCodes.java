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
 * @lastModifiedBy pdhole
 */
public class Reg_BMT_StatusTracker_Manufacturer_ReasonCodes extends BaseWebDriver {

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
	@Documentation(step = "Go to On Status Tracker Setting page", expected = "Application should load Status Tracker Setting page")
	public void goToReasonCodesSection() throws Throwable {
		OrderingBMTStatusTrackerPage.clickOnStatusTrackerSectionTabs("Reason Codes");
		OrderingBMTStatusTrackerPage.verifyStatusTrackerPageElements();
	}
	
	@Test(dependsOnMethods = "goToReasonCodesSection")
	@Documentation(step = "Go to On Status Tracker Setting page", expected = "Application should load Status Tracker Setting page")
	public void addNewReasonCodesFunctionality() throws Throwable {
		OrderingBMTStatusTrackerPage.clickAddNewButton();
		OrderingBMTStatusTrackerPage.waitForAddNewRecordPage();
		OrderingBMTStatusTrackerPage.addNewReasonCodes();
		OrderingBMTStatusTrackerPage.searchRecords("Internal Code", CommonPage.getTestData("InternalCode"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.verifySelectedReasonCodesFromTable();
	}
	
	@Test(dependsOnMethods = "addNewReasonCodesFunctionality")
	@Documentation(step = "Go to On Status Tracker Setting page", expected = "Application should load Status Tracker Setting page")
	public void updateReasonCodesFunctionality() throws Throwable {
		OrderingBMTStatusTrackerPage.searchRecords("Internal Code", CommonPage.getTestData("InternalCode"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.updateReasonCodes();
		OrderingBMTStatusTrackerPage.searchRecords("Internal Code", CommonPage.getTestData("InternalCode"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.verifySelectedReasonCodesFromTable();
	}
	
	@Test(dependsOnMethods = "updateReasonCodesFunctionality")
	@Documentation(step = "Go to On Status Tracker Setting page", expected = "Application should load Status Tracker Setting page")
	public void deleteReasonCodesFunctionality() throws Throwable {
		OrderingBMTStatusTrackerPage.searchRecords("Internal Code", CommonPage.getTestData("InternalCode"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.deleteReasonCodesAndVerify();
	}
	
	@Test(dependsOnMethods = "deleteReasonCodesFunctionality")
	@Documentation(step = "Search Functionality on Status Tracker Setting page", expected = "Application should search the records on Status Tracker Setting page")
	public void searchFunctionality() throws Throwable {
		OrderingBMTStatusTrackerPage.searchRecords("Reason Code Category", "Manufacturer Codes");
		OrderingBMTStatusTrackerPage.verifySearchedRecords(true);
	}
	
	@Test(dependsOnMethods = "searchFunctionality")
	@Documentation(step = "Clear Filter and Pagination Functionality on Status Tracker Setting page", expected = "Application should be able to Clear Filters on Status Tracker Setting page")
	public void clearFilterAndPagenationFunctionality() throws Throwable {
		OrderingBMTStatusTrackerPage.verifyClearFilterFunctionality();
		OrderingBMTStatusTrackerPage.verifyPaginationFunctionality();
	}
	
	@Test(dependsOnMethods = "clearFilterAndPagenationFunctionality")
	@Documentation(step = "Click on Export button", expected = "File should be exported with file name '-queue.csv' ")
	public void verifyExportFunctionality() throws Exception {
		OrderingBMTStatusTrackerPage.verifyExportCSV("reason-codes-queue", this.getClass().getSimpleName());
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