package com.element.fleet.ordering.regression;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingMyOrdersPage;
import com.element.fleet.ordering.page.OrderingSummaryPage;
import com.element.fleet.ordering.page.OrderingWIPOrdersPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy vinay
 */
public class Reg_MyOrders_Queue_Search_Sort_Toggle extends BaseWebDriver {
	
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
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void testLaunchURL() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openFOApplication();
	}
	
	@Test(dependsOnMethods = "testLaunchURL")
	@Documentation(step = "Enter the required client number to the application",expected = "The application display the required client") 
	public void changeClientBreakdown() throws Exception {
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
	}
	
	@Test(dependsOnMethods = "changeClientBreakdown")
	@Documentation(step="Move to My Orders section" , expected="My Orders page should be loaded")
	public void moveToMyOrdersPage() throws Exception{
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.selectOrderingMenuOption("My Orders");
		OrderingMyOrdersPage.waitForMyOrdersQueueToLoad();
	}
	
	@Test(dependsOnMethods = "moveToMyOrdersPage")
	@Documentation(step = "Verify all search fields are displayed on the page", expected = "Verified all search fileds are displayed on the page") 
	public void	verifySearchFieldsArePresent() throws Exception {
		OrderingMyOrdersPage.verifySearchFieldsArePresent();
	}
	
	@Test(dependsOnMethods = "verifySearchFieldsArePresent")
	@Documentation(step = "Verify all columns are displayed on the page", expected = "Verified all columns are displayed on the page") 
	public void	verifyColumnsArePresent() throws Exception {
		OrderingMyOrdersPage.verifyColumnHeadings();
	}
	
	@Test(dependsOnMethods = "verifyColumnsArePresent")
	@Documentation(step="Search and verify the records" , expected="Records are sarched and verified")
	public void searchAndVerifyTheRecords() throws Exception{
		OrderingWIPOrdersPage.searchAndVerifyTheRecords("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingWIPOrdersPage.searchAndVerifyTheRecords("Year", CommonPage.getTestData("Year"));
		OrderingWIPOrdersPage.searchAndVerifyTheRecords("Make", CommonPage.getTestData("Make"));
		OrderingWIPOrdersPage.searchAndVerifyTheRecords("VIN", CommonPage.getTestData("VinNumberSearch"));
	}
	
	@Test(dependsOnMethods = "searchAndVerifyTheRecords")
	@Documentation(step = "Check reset button functionality", expected = "Reset button functionality is verified") 
	public void enterValuesInSearchFieldsAndVerify() throws Exception {
		OrderingMyOrdersPage.enterTextInAllSearchFieldMyOrders();
		OrderingWIPOrdersPage.clickResetButton();
		OrderingMyOrdersPage.verifyTextInAllSearchFieldIsBlankMyOrders();
	}
	
	@Test(dependsOnMethods = "enterValuesInSearchFieldsAndVerify")
	@Documentation(step = "Modify columns", expected = "Columns list in table is modified") 
	public void modifyRequiredColumnsList() throws Exception {		
		OrderingWIPOrdersPage.clickToggleColumnsButton();
		OrderingMyOrdersPage.clickRequiredColumnOptions("Year");
		OrderingMyOrdersPage.clickRequiredColumnOptions("Make");
		OrderingMyOrdersPage.clickRequiredColumnOptions("Model");
		OrderingMyOrdersPage.clickRequiredColumnOptions("Manufacturer");
		OrderingWIPOrdersPage.setCheckedColumns();
		OrderingWIPOrdersPage.clickToggleColumnsButton();
		OrderingWIPOrdersPage.setVisibleColumnHeadingsFromTable();
		OrderingWIPOrdersPage.compareSelectedAndVisibleColumns();
	}
	
	@Test(dependsOnMethods = "modifyRequiredColumnsList")
	@Documentation(step = "Click export button", expected = "CSV file should be downloaded and read") 
	public void verifyExportAndReadAllData() throws Exception {
		OrderingWIPOrdersPage.enterTextInClientSearchField(CommonPage.getTestData("ClientNumber"));
		OrderingWIPOrdersPage.enterTextInOrderTypeSearchField(CommonPage.getTestData("OrderType"));
		OrderingWIPOrdersPage.enterTextInYearSearchField(CommonPage.getTestData("Year"));
		OrderingWIPOrdersPage.enterTextInManufacturerSearchField(CommonPage.getTestData("Manufacturer"));
		OrderingWIPOrdersPage.enterTextInMakeSearchField(CommonPage.getTestData("Make"));
		OrderingWIPOrdersPage.enterTextInModelSearchField(CommonPage.getTestData("Model"));
		OrderingWIPOrdersPage.enterTextInTrimSearchField(CommonPage.getTestData("Trim"));
		OrderingMyOrdersPage.clickOnSearch();
		OrderingSummaryPage.clickExportButton("MyOrders", this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "verifyExportAndReadAllData")
	@Documentation(step = "Verify sorting functionality of the page", expected = "Sorting functionality of the page is verified")
	public void verifyColumnSortedInDescAscOrder() throws Exception {
		OrderingWIPOrdersPage.clickResetButton();
		OrderingMyOrdersPage.searchDataForSorting();
		OrderingMyOrdersPage.verifyAscAndDescColumnsSorting("Corp");
		OrderingMyOrdersPage.verifyAscAndDescColumnsSorting("Unit");
		OrderingMyOrdersPage.verifyAscAndDescColumnsSorting("Client");
		OrderingMyOrdersPage.verifyAscAndDescColumnsSorting("Log");
	}
	
	@Test(dependsOnMethods = "verifyColumnSortedInDescAscOrder")
	@Documentation(step = "Click on Logout button", expected = "Application should be logged out")
	public void verifyFOLogOutFunctionality() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyFOLogOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}