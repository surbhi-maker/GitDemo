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
public class Reg_BMT_StatusTracker_ParentChild_Relationship extends BaseWebDriver {

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
		OrderingBMTStatusTrackerPage.clickOnStatusTrackerSectionTabs("Manage Parents");
		OrderingBMTStatusTrackerPage.verifyStatusTrackerSettingsTabPresent("Manage Parents");
	}

	@Test(dependsOnMethods = "navigateToManageParentsTab")
	@Documentation(step = "Try to add a new parent with a child", expected = "User should be able to create a parent with a child ")
	public void verifyAddNewParentFunctionality() throws Throwable {
		OrderingBMTStatusTrackerPage.addNewParent("Parent-"+CommonPage.randomNumberInRange(999999));
	}
	
	@Test(dependsOnMethods = "verifyAddNewParentFunctionality")
	@Documentation(step = "Navigate to Parent Child Relation tab", expected = "Application should load Parent Child Relation tab")
	public void goToParentChildRelationSection() throws Throwable {
		OrderingBMTStatusTrackerPage.clickOnStatusTrackerSectionTabs("Manage Parent Child Relationship");
		OrderingBMTStatusTrackerPage.verifyStatusTrackerPageElements();
		OrderingBMTStatusTrackerPage.verifyStatusTrackerSettingsTabPresent("Manage Parent Child Relationship");
	}
	
	@Test(dependsOnMethods = "goToParentChildRelationSection")
	@Documentation(step = "Create New Parent Child Relationship and Verify", expected = "Application should able to create parent child relationship")
	public void addNewParentChildRelation() throws Throwable {
		OrderingBMTStatusTrackerPage.addNewParentChildRelation();
	}
	
	@Test(dependsOnMethods = "addNewParentChildRelation")
	@Documentation(step = "Update Parent Child Relationship and Verify", expected = "Application should able to update parent child relationship")
	public void updateParentChildRelations() throws Throwable {
		OrderingBMTStatusTrackerPage.searchRecords("Parent Name", CommonPage.getTestData("ParentName"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.verifyUpdateParentWithNewChildren();
	}
	
	@Test(dependsOnMethods = "updateParentChildRelations")
	@Documentation(step = "Delete Parent Child Relationship and Verify Error message for Linked Client Relation", expected = "Application should not able to delete parent child relationship for Linked Clients")
	public void deleteParentWithExistingChildRelations() throws Throwable {
		OrderingBMTStatusTrackerPage.clickOnStatusTrackerSectionTabs("Manage Parents");
		OrderingBMTStatusTrackerPage.waitForStatusTrackerSettingPage();
		OrderingBMTStatusTrackerPage.searchRecords("Parent Name", CommonPage.getTestData("ParentName"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.deleteExistingParentHavingChildrenAndVerify();
	}
	
	@Test(dependsOnMethods = "deleteParentWithExistingChildRelations")
	@Documentation(step = "Remove Clients from Parent Child Relationship and Verify", expected = "Application should able to Remove Clients from Parent Child Relationship and Verify")
	public void removeClientsFromExistingRelation() throws Throwable {
		OrderingBMTStatusTrackerPage.clickOnStatusTrackerSectionTabs("Manage Parent Child Relationship");
		OrderingBMTStatusTrackerPage.waitForStatusTrackerSettingPage();
		OrderingBMTStatusTrackerPage.searchRecords("Parent Name", CommonPage.getTestData("ParentName"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.removeLinkedClientsFromRelationship();
	}
	
	@Test(dependsOnMethods = "removeClientsFromExistingRelation")
	@Documentation(step = "Delete Parent Child Relationship and Verify", expected = "Application should able to create parent child relationship")
	public void deleteExistingRelation() throws Throwable {
		OrderingBMTStatusTrackerPage.searchRecords("Parent Name", CommonPage.getTestData("ParentName"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.verifyDeletedChildParentRelation();
	}
	
	@Test(dependsOnMethods = "deleteExistingRelation")
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
	
	@Test(dependsOnMethods = "verifySeachAndToggleFilters")
	@Documentation(step = "Go to manage parents sub menu page and verify Delete parent", expected = "User should be navigated to manage parents sub menu page and delete parent")
	public void verifyDeleteParentFunctionality() throws Throwable {
		OrderingBMTStatusTrackerPage.clickOnStatusTrackerSectionTabs("Manage Parents");
		OrderingBMTStatusTrackerPage.searchRecords("Parent Name", CommonPage.getTestData("ParentName"));
		OrderingBMTStatusTrackerPage.selectFirstRowFromTable();
		OrderingBMTStatusTrackerPage.deleteExistingParentAndVerify();
	}
	
	@Test(alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
}