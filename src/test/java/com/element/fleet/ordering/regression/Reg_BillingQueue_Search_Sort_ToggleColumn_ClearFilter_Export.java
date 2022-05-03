package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingSummaryPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy pdhole
 */
public class Reg_BillingQueue_Search_Sort_ToggleColumn_ClearFilter_Export extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({"xcelerateURL", "username", "applicationURL", "applicationBOURL", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String username, String applicationURL, String applicationBOURL, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
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
	@Documentation(step = "Go to On creadit Queue page", expected = "Application should load On DIO Queue page")
	public void gotoBillingQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Billing");
	}
	
	@Test(dependsOnMethods = "gotoBillingQueuePage")
	@Documentation(step = "Go to On Queue page verify search and sort functionality", expected = "Application should be able to show results according to search and sort columns of Queue page")
	public void verifyBillingQueuePageSearchAndSortFunctionality() throws Throwable {
		OrderingBOQueuePage.verifyReleaseButton(true);
		OrderingBOQueuePage.verifySearchFieldsArePresent();
		OrderingBOQueuePage.searchAndVerifyRecords("Standard Search", "Client", CommonPage.getTestData("CustomColumn6"));
		OrderingBOQueuePage.searchWithMultipleDataSetAndVerifyRecord("Standard Search", "Client", CommonPage.getTestData("CustomColumn4"));
		OrderingBOQueuePage.verifyDaysAgingDataDefaultOrder();
		OrderingBOQueuePage.searchWithMultipleDataSetAndVerifyRecord("Standard Search", "Product Class", CommonPage.getTestData("CustomColumn5"));
		OrderingBOQueuePage.verifyDaysAgingDataDefaultOrder();
	}
	
	@Test(dependsOnMethods = "verifyBillingQueuePageSearchAndSortFunctionality")
	@Documentation(step = "Go to On Queue page and verify clear filter functionality", expected = "Application should remove the filter values")
	public void verifyClearFilterQueueTable() throws Throwable {
		OrderingBOQueuePage.verifyClearFilterFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyClearFilterQueueTable")
	@Documentation(step = "Go to On DIO Queue page and verify Toogle and Queue Table columns", expected = "Application should show the Queue table columnd are same as toggle columns On DIO Queue page")
	public void verifyToggleColumnsAndTableColumns() throws Throwable {
		OrderingBOQueuePage.verifyTableColumnsOnLanding();
		OrderingBOQueuePage.verifyQueueTableColumns();
		OrderingBOQueuePage.removeColumnsFromQueueTable("Log","Corp", "Year", "Make");
		OrderingBOQueuePage.verifyQueueTableColumns();
		OrderingBOQueuePage.addColumnInQueueTable("Log","Corp", "Year", "Make");
		OrderingBOQueuePage.verifyQueueTableColumns();
		OrderingBOQueuePage.verifyDaysAgingLabelInToggleColumn();
	}
	
	@Test(dependsOnMethods = "verifyClearFilterQueueTable")
	@Documentation(step = "Go to verify Pagenation", expected = "Application should load Queue page and pagenation should be validated")
	public void verifyPagenationTitleRegqueue() throws Exception {
		OrderingBOQueuePage.verifyPagenation();
	}
	
	@Test(dependsOnMethods = "verifyPagenationTitleRegqueue")
	@Documentation(step = "Go to On DIO Queue page and verify Export functionality of Queue Table", expected = "Application should Export Queue table Data")
	public void verifyExportTableData() throws Throwable {
		OrderingSummaryPage.clickExportButton("BillingQueue", this.getClass().getSimpleName());
		OrderingBOQueuePage.verifyCSVHeadersOrder("Billing-Queue");
		OrderingBOQueuePage.verifyClientAndUnitNumberFromCSVIfStartWithZero("Client", "Billing-Queue");
		OrderingBOQueuePage.verifyClientAndUnitNumberFromCSVIfStartWithZero("Unit", "Billing-Queue");
	}
	
	@Test(dependsOnMethods = "verifyExportTableData")
	@Documentation(step = "Verify sorting functionality of the page", expected = "Sorting functionality of the page is verified")
	public void verifyColumnSortedInDescAscOrder() throws Exception {
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("Log", "BillingQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("Log", "BillingQueue" );
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("Log", "Ascending" , "BillingQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("Client", "BillingQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("Client", "BillingQueue" );
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("Client", "Ascending" , "BillingQueue");
	}
	
	@Test(dependsOnMethods = "verifyColumnSortedInDescAscOrder", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}
