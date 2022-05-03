package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOAdditionalOptionsPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy mkhairner
 */
public class Reg_BMT_CDDR_Search_Sort_ToggleColumn_ClearFilter_Export extends BaseWebDriver {
	
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
	@Documentation(step = "Go to Customer Delivering Dealer Assignment Rules page", expected = "Application should load On Customer Delivering Dealer Assignment Rules page")
	public void gotoCustomerDeliveringDealerAssignmentRulesPage() throws Throwable {
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Customer Delivering Dealer Assignment Rules");	
	}
	
	@Test(dependsOnMethods = "gotoCustomerDeliveringDealerAssignmentRulesPage")
	@Documentation(step = "Go to Customer Delivering Dealer Assignment Rules page verify label,button,columns and search field", expected = "Application should be able to show results according to Assert conditation for label,buttons,columns and search field CDDAR page")
	public void verifyCustomerDeliveringDealerLabelsAndButtons() throws Throwable {
		OrderingBOQueuePage.verifyTableColumnsOnLanding();
		PDFReporter.takeExtraScreenshot();
		OrderingBOQueuePage.verifySearchFieldsArePresent();
		PDFReporter.takeExtraScreenshot();
		OrderingBOQueuePage.verifyCDDARButtton();
	}
	
	@Test(dependsOnMethods = "verifyCustomerDeliveringDealerLabelsAndButtons")
	@Documentation(step = "Go to On Queue page verify search and sort functionality", expected = "Application should be able to show results according to search and sort columns of Queue page")
	public void verifyCustomerDeliveringDealerPageSearchFunctionality() throws Throwable {	
		OrderingBOQueuePage.searchAndVerifyRecords("Standard Search", CommonPage.getTestData("CustomColumn4"), "CA");
	}

	@Test(dependsOnMethods = "verifyCustomerDeliveringDealerPageSearchFunctionality")
	@Documentation(step = "Go to On Queue page and verify clear filter functionality", expected = "Application should remove the filter values")
	public void verifyClearFilterQueueTable() throws Throwable {
		OrderingBOQueuePage.verifyClearFilterFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyClearFilterQueueTable")
	@Documentation(step = "Go to On Customer Delivering Dealer Assignment Rules page and verify Toogle and Queue Table columns", expected = "Application should show the Customer Delivering Dealer Assignment Rules table columnd are same as toggle columns On DIO Queue page")
	public void verifyToggleColumnsAndTableColumns() throws Throwable {
		OrderingBOQueuePage.verifyTableColumnsOnLanding();
		OrderingBOQueuePage.verifyQueueTableColumns();
		OrderingBOQueuePage.removeColumnsFromQueueTable(CommonPage.getTestData("CustomColumn4"), CommonPage.getTestData("CustomColumn5"), CommonPage.getTestData("CustomColumn6") ,CommonPage.getTestData("CustomColumn7"));
		PDFReporter.takeExtraScreenshot();
		OrderingBOQueuePage.verifyQueueTableColumns();
		OrderingBOQueuePage.addColumnInQueueTable(CommonPage.getTestData("CustomColumn4"), CommonPage.getTestData("CustomColumn5"), CommonPage.getTestData("CustomColumn6") ,CommonPage.getTestData("CustomColumn7"));
		PDFReporter.takeExtraScreenshot();
		OrderingBOQueuePage.verifyQueueTableColumns();
	}
	
	@Test(dependsOnMethods = "verifyToggleColumnsAndTableColumns")
	@Documentation(step = "Go to verify Pagination", expected = "Application should load Queue page and pagenation should be validated")
	public void verifyPaginationTitleRegQueue() throws Exception {
		OrderingBOQueuePage.verifyPagenation();
	}
	
	@Test(dependsOnMethods = "verifyPaginationTitleRegQueue")
	@Documentation(step = "Go to On Customer Delivering Dealer Assignment Rules Queue page and verify Export functionality of Queue Table", expected = "Application should Export Queue table Data")
	public void verifyExportTableData() throws Throwable {
		OrderingBOAdditionalOptionsPage.validateColumnsInExportedCSVOnCDDAPage(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "verifyExportTableData")
	@Documentation(step = "Verify sorting functionality of the page", expected = "Sorting functionality of the page is verified")
	public void verifyColumnSortedInDescAscOrder() throws Exception {
		OrderingBOOnOrderQueuePage.clickColumnTableHeading(CommonPage.getTestData("CustomColumn4"), "CustomerDeliveringDealerAssignmentRulesQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading(CommonPage.getTestData("CustomColumn4"), "CustomerDeliveringDealerAssignmentRulesQueue" );
		OrderingBOOnOrderQueuePage.compareOrderOfColumn(CommonPage.getTestData("CustomColumn4"), "Ascending" , "CustomerDeliveringDealerAssignmentRulesQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading(CommonPage.getTestData("CustomColumn5"), "CustomerDeliveringDealerAssignmentRulesQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading(CommonPage.getTestData("CustomColumn5"), "CustomerDeliveringDealerAssignmentRulesQueue" );
		OrderingBOOnOrderQueuePage.compareOrderOfColumn(CommonPage.getTestData("CustomColumn5"), "Ascending" , "CustomerDeliveringDealerAssignmentRulesQueue");
	}
	
	@Test(dependsOnMethods = "verifyColumnSortedInDescAscOrder", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}
