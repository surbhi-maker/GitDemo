package com.element.fleet.ordering.regression;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingDriverPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingManagerApprovalMaintenancePage;
import com.element.fleet.ordering.page.OrderingManagerApprovalQueuePage;
import com.element.fleet.ordering.page.OrderingMyOrdersPage;
import com.element.fleet.ordering.page.OrderingWIPOrdersPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy sidheshwar
 */
public class Reg_WIP_Search_Sort_Toggle extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "username", "boUserName" ,"orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username,String boUserName,String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
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
		CommonPage.testStarted();
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
	@Documentation(step = "Enter Fleet ID", expected = "Fleet ID should be entered and should load Fleet Spec Groups section")
	public void selectFleetID() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterCorpAndClientAndSelectClient();
	}
	             
	@Test(dependsOnMethods = "selectFleetID")
	@Documentation(step = "Go to Approval Rules tab", expected = "Approval Rules Page should be loaded")
	public void goToApprovalRulesSection() throws Exception {
		OrderingBOManagerOrderPreferencesPage.moveToSection("Approval Rules");
	}
	
	@Test(dependsOnMethods = "goToApprovalRulesSection")
	@Documentation(step = "Search and verify rule is present or not", expected = "Verified rule is present")
	public void searchAndSelectApprovalRule() throws Throwable {
		OrderingBOManagerOrderPreferencesPage.searchAndSelectApprovalRule("approval-rules-queue", CommonPage.getTestData("ApprovalRuleName"));
		OrderingBOManagerOrderPreferencesPage.loadApproverData();
	}
	
	@Test(dependsOnMethods = "searchAndSelectApprovalRule")
	@Documentation(step = "Click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality")
	@Documentation(step = "Create order with logger user", expected = "Orders with logger user is created") 
	public void createOrdersWithLoggerUser() throws Throwable {
		OrderingLoginPage.openCustomFOApplication(CommonPage.getTestData("OrderLogger"));
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
		OrderingBOManagerOrderPreferencesPage.createOrderWithLoggedInOrder(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods="createOrdersWithLoggerUser")
	@Documentation(step = "Go to my orders page", expected = "Application moved to my order page") 
	public void closeSummaryPageAndGoToMyOrders() throws Exception {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.selectOrderingMenuOption("My Orders");
		OrderingMyOrdersPage.waitForMyOrdersQueueToLoad();
	}
	
	@Test(dependsOnMethods = "closeSummaryPageAndGoToMyOrders")
	@Documentation(step = "Verify orders is not present in My Ordrs section", expected = "Verified orders is not present in My Orders section")
	public void verifyOrderNotPresentInMyOrders() throws Throwable {
		OrderingBOManagerOrderPreferencesPage.verifyOrdersNotDisplayedInMyOrderSection();
	}
	
	@Test(dependsOnMethods="verifyOrderNotPresentInMyOrders")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Order Logger should be logged out") 
	public void logOutFromLoggerUser() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "logOutFromLoggerUser")
	@Documentation(step = "Open Back office page from Super User", expected = "Back office page should be opened from Super User")
	public void launchBOURLFromSuperUser() throws Exception {
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "launchBOURLFromSuperUser")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void loginBOApplication() throws Exception {
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "loginBOApplication")
	@Documentation(step = "Go to On Order Queue page and verify logger order are not displayed", expected = "Application should load On Order Queue page and verified logger order are not displayed ")
	public void goToOnOrderQueueAndVerifyLoggerOrderNotDisplayed() throws Throwable {
		OrderingBOManagerOrderPreferencesPage.verifyOrdersNotDisplayedInOnOrderQueues();
	}
	
	@Test(dependsOnMethods = "goToOnOrderQueueAndVerifyLoggerOrderNotDisplayed")
	@Documentation(step = "Click on Logout button from SU", expected = "Application should be logged out")
	public void verifyBOLogOutUsingSuperUser() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutUsingSuperUser")
	@Documentation(step = "Super User Approve the order", expected = "Order should be approved by Super User and order should be removed from list")
	public void verifyOrderApprovedWithSperUser() throws Throwable {
		OrderingLoginPage.openCustomFOApplication(CommonPage.getTestData("Username"));
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Maintenance");
		OrderingManagerApprovalMaintenancePage.waitForManagerApprovalMaintenanceToLoad();
		OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+1));
		OrderingMyOrdersPage.clickOnSearch();
		OrderingManagerApprovalQueuePage.selectOrderInManagerApprovalQueue();
		OrderingManagerApprovalMaintenancePage.clickCancelButton();
		OrderingManagerApprovalQueuePage.verifyPopUpMessage("Single Logger Cancel", "");
		OrderingManagerApprovalQueuePage.bulkOrderCancelAlertPopUp("Yes");
		OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Bulk Logger Approval");
		PDFReporter.takeExtraScreenshot();
		OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+"1"));
		OrderingMyOrdersPage.clickOnSearch();
		OrderingManagerApprovalQueuePage.verifyNoOrderInManagerApprovalQueue();
	}
	
	@Test(dependsOnMethods="verifyOrderApprovedWithSperUser")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly") 
	public void verifySuperUserLogOut() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifySuperUserLogOut")
	@Documentation(step="Move to work in process section" , expected="Work in process page should be loaded")
	public void moveToWIPPageAndSearchOrder() throws Exception {
		OrderingDriverPage.clickSaveAndExit();
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.selectOrderingMenuOption("Work In Process Orders");
		OrderingWIPOrdersPage.waitForWorkInProcessOrdersToLoad();
		OrderingWIPOrdersPage.searchOrderID();	
	}
	
	@Test(dependsOnMethods = "moveToWIPPageAndSearchOrder")
	@Documentation(step="Move to work in process section" , expected="Work in process page should be loaded")
	public void verifyCancelButtonFunctionality() throws Throwable {
		OrderingWIPOrdersPage.cancelOrderFromWIPQueue();
		OrderingWIPOrdersPage.waitForWorkInProcessOrdersToLoad();		
	}
	
	@Test(dependsOnMethods = "verifyCancelButtonFunctionality")
	@Documentation(step="Move to work in process section" , expected="Work in process page should be loaded")
	public void verifyCancelledOrderInWIPQueuePage() throws Exception {
		OrderingWIPOrdersPage.verifyCancelledOrderInWIPQueue("OrderID",CommonPage.getElementOrderObject().getDriverTabObject().getOrderID());		
	}
	
	@Test(dependsOnMethods = "verifyCancelledOrderInWIPQueuePage")
	@Documentation(step="Search and verify the records" , expected="Records are sarched and verified")
	public void searchAndVerifyTheRecords() throws Exception{
		OrderingWIPOrdersPage.searchAndVerifyTheRecords("Client", CommonPage.getTestData("ClientNumber"));
		OrderingWIPOrdersPage.searchAndVerifyTheRecords("Year", CommonPage.getTestData("Year"));
		OrderingWIPOrdersPage.searchAndVerifyTheRecords("Make", CommonPage.getTestData("Make"));
		OrderingWIPOrdersPage.searchAndVerifyTheRecords("Model", CommonPage.getTestData("Model"));
		OrderingWIPOrdersPage.searchAndVerifyTheRecords("VIN", CommonPage.getTestData("VinNumberSearch"));
		OrderingWIPOrdersPage.wipPageRecords();
	}
	
	@Test(dependsOnMethods = "searchAndVerifyTheRecords")
	@Documentation(step = "Verify all columns are displayed on the page", expected = "Verified all columns are displayed on the page") 
	public void	verifyColumnsArePresent() throws Exception {
		OrderingWIPOrdersPage.verifyColumnHeadings();
	}
	
	@Test(dependsOnMethods = "verifyColumnsArePresent")
	@Documentation(step = "Check reset button functionality", expected = "Reset button functionality is verified") 
	public void enterValuesInSearchFieldsAndVerify() throws Exception {
		OrderingWIPOrdersPage.enterTextInAllSearchField();
		OrderingWIPOrdersPage.clickResetButton();
		OrderingWIPOrdersPage.verifyTextInAllSearchFieldIsBlank();
	}
	
	@Test(dependsOnMethods = "enterValuesInSearchFieldsAndVerify")
	@Documentation(step = "Modify columns", expected = "Columns list in table is modified") 
	public void modifyRequiredColumnsList() throws Exception {		
		OrderingWIPOrdersPage.clickToggleColumnsButton();
		OrderingWIPOrdersPage.clickRequiredColumnOption("First Name");
		OrderingWIPOrdersPage.clickRequiredColumnOption("Unit");
		OrderingWIPOrdersPage.clickRequiredColumnOption("Year");
		//OrderingWIPOrdersPage.clickRequiredColumnOption("Manufacturer");
		OrderingWIPOrdersPage.setCheckedColumns();
		OrderingWIPOrdersPage.clickToggleColumnsButton();
		OrderingWIPOrdersPage.setVisibleColumnHeadingsFromTable();
		OrderingWIPOrdersPage.compareSelectedAndVisibleColumns();
	}
	
	@Test(dependsOnMethods = "modifyRequiredColumnsList")
	@Documentation(step = "Click export button", expected = "CSV file should be downloaded and read") 
	public void verifyExportAndReadAllData() throws Exception {
		OrderingMyOrdersPage.clickOnExportButton(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "verifyExportAndReadAllData")
	@Documentation(step = "Verify sorting functionality of the page", expected = "Sorting functionality of the page is verified")
	public void verifyColumnSortedInDescAscOrder() throws Exception {
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("Order ID", "WIPOrdersQueue");
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("Order ID", "Descending", "WIPOrdersQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("Client", "WIPOrdersQueue");
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("Client", "Descending" , "WIPOrdersQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("Client", "WIPOrdersQueue" );
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("Client", "Ascending" , "WIPOrdersQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("Order Type", "WIPOrdersQueue");
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("Order Type", "Descending" , "WIPOrdersQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("Order Type", "WIPOrdersQueue" );
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("Order Type", "Ascending" , "WIPOrdersQueue");
	}
	
	@Test(dependsOnMethods = "verifyColumnSortedInDescAscOrder", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}
