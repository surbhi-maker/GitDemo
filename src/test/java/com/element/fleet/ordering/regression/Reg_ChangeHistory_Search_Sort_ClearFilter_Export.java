package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOChangeHistoryPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingSummaryPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy akandkonde
 */
public class Reg_ChangeHistory_Search_Sort_ClearFilter_Export extends BaseWebDriver{

	@BeforeClass
	@Parameters({"xcelerateURL", "username", "applicationURL", "applicationBOURL", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String username, String applicationURL, String applicationBOURL,String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime,ITestContext context) throws Exception {
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
	@Documentation(step = "Go to Change History page", expected = "Change History Page should load and the title should match")
	public void verifylabelsOnChangeHistoryPage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Change History");
		OrderingBOChangeHistoryPage.verifyChangeHistoryPageLoaded();
		OrderingBOChangeHistoryPage.verifyChangeHistoryPageTitle();
	}
	
	@Test(dependsOnMethods = "verifylabelsOnChangeHistoryPage")
	@Documentation(step = "Verify that correct table columns are displayed", expected = "Table columns should be displayed correctly")
	public void verifyTableColumns() throws Exception {
		OrderingBOChangeHistoryPage.verifyTableColumnsOnLanding();
	}
	
	@Test(dependsOnMethods = "verifyTableColumns")
	@Documentation(step = "Verify all records have deatiled history link", expected = "All record should have detail history link")
	public void verifyAllRecordsHaveDetailHistoryLink() throws Exception {
		OrderingBOChangeHistoryPage.verifyLinkPresentInHistoryColumn();
	}
	
	@Test(dependsOnMethods = "verifyAllRecordsHaveDetailHistoryLink")
	@Documentation(step = "Verify that the records are in descending order", expected = "Records Should be in decending order")
	public void verifyRecordsAreInDescendingOrder() throws Exception {
		OrderingBOChangeHistoryPage.checkInsertDateAreInDescendingOrder();
	}
	
	@Test(dependsOnMethods = "verifyRecordsAreInDescendingOrder")
	@Documentation(step = "Search, verify and clear filter", expected = "Records Should be displayed according to the search parameters and then filters should be cleared")
	public void searchAndVerifyRecordsAndThenClearFilter() throws Exception {
		OrderingBOChangeHistoryPage.verifySearchandClearFunctionality(CommonPage.getTestData("CustomColumn2"), CommonPage.getTestData("CustomColumn3"), CommonPage.getTestData("CustomColumn4"), OrderingBOChangeHistoryPage.getTodaysAndYesterdaysDate().get(1), OrderingBOChangeHistoryPage.getTodaysAndYesterdaysDate().get(0), CommonPage.getTestData("CustomColumn7"));
	}

	@Test(dependsOnMethods = "searchAndVerifyRecordsAndThenClearFilter")
	@Documentation(step = "Verify pagination Functionality", expected = "Pagination functionality should be working fine")
	public void verifyPagination() throws Exception {
		OrderingBOChangeHistoryPage.verifyPagenation();
	}
	   
	@Test(dependsOnMethods = "verifyPagination")
	@Documentation(step = "Go to On DIO Queue page and verify Export functionality of Queue Table", expected = "Application should Export Queue table Data")
	public void verifyExportTableData() throws Throwable {
		OrderingSummaryPage.clickExportButton("ChangeHistory", this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "verifyExportTableData")
	@Documentation(step = "Verify sorting functionality of the page", expected = "Sorting functionality of the page is verified")
	public void verifyColumnSortedInDescAscOrder() throws Exception {
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("Business Obj", "ChangeHistory");
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("Business Obj", "Ascending", "ChangeHistory");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("Business Obj", "ChangeHistory");
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("Business Obj", "Descending", "ChangeHistory");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("Updated In", "ChangeHistory");
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("Updated In", "Ascending", "ChangeHistory");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("Updated In", "ChangeHistory");
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("Updated In", "Descending", "ChangeHistory");
	}	 

	@Test(dependsOnMethods = "verifyColumnSortedInDescAscOrder", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}
