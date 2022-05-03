package com.element.fleet.ordering.regression;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingManagerApprovalQueuePage;
import com.element.fleet.ordering.page.OrderingMyOrdersPage;
import com.element.fleet.ordering.page.OrderingWIPOrdersPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy sweety
 */
public class Reg_WIP_Verify_OrderStatus_Rejected extends BaseWebDriver {
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
	@Documentation(step = "Super User Approve the order", expected = " Order should be approved by Super User and order should be removed from list")
	public void verifyOrderRejectedWithSperUser() throws Throwable {
		OrderingLoginPage.openCustomFOApplication(CommonPage.getTestData("Username"));
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Queue");
		OrderingManagerApprovalQueuePage.waitForManagerApprovalQueueToLoad();
		OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+1));
		OrderingMyOrdersPage.clickOnSearch();
		OrderingManagerApprovalQueuePage.selectOrderInManagerApprovalQueue();
		OrderingManagerApprovalQueuePage.clickReject();
		OrderingManagerApprovalQueuePage.verifyPopUpMessage("Single Logger Reject", "");
		OrderingManagerApprovalQueuePage.bulkOrderRejectAlertPopUp("Yes");
		OrderingManagerApprovalQueuePage.verifyAlertPopUpMessage("Single Logger Reject");
		PDFReporter.takeExtraScreenshot();
		OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+1));
		OrderingMyOrdersPage.clickOnSearch();
		OrderingManagerApprovalQueuePage.verifyNoOrderInManagerApprovalQueue();
	}
	
	@Test(dependsOnMethods="verifyOrderRejectedWithSperUser")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly") 
	public void verifySuperUserLogOut() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifySuperUserLogOut")
	@Documentation(step="Select the order in  work in process" , expected="order should be selected from work in process page")
	public void verifySearchFunctionalityInWorkInProcessOrder() throws Exception{
		OrderingLoginPage.openCustomFOApplication(CommonPage.getTestData("Username"));
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.selectOrderingMenuOption("Work In Process Orders");
		OrderingCommonPage.checkAlertPopUp();
		OrderingWIPOrdersPage.waitForWorkInProcessOrdersToLoad();
		OrderingWIPOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+1));
		OrderingWIPOrdersPage.clickOnSearch();
	}
	
	@Test(dependsOnMethods = "verifySearchFunctionalityInWorkInProcessOrder")
	@Documentation(step = "Verify all columns are displayed on the page", expected = "Verified all columns are displayed on the page") 
	public void	verifyColumnsArePresent() throws Exception {
		OrderingWIPOrdersPage.verifyColumnHeadings();
	}
	
	@Test(dependsOnMethods = "verifyColumnsArePresent")
	@Documentation(step = "Verify sorting functionality of the page", expected = "Sorting functionality of the page is verified")
	public void verifyRejectedOrderStatus() throws Exception {
		OrderingWIPOrdersPage.verifyRejectedReasonByMouseHover();
	}
	
	@Test(dependsOnMethods = "verifyRejectedOrderStatus")
	@Documentation(step = "Click on Export button", expected = "File should be exported with file name '-queue.csv' ")
	public void verifyExportFunctionality() throws Exception {
		OrderingWIPOrdersPage.verifyExportCSV("wip", "Work In Process Orders-queue");
	}
	
	@Test(dependsOnMethods="verifyExportFunctionality")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly") 
	public void verifyLogOut() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyLogOut")
	@Documentation(step = "This method ends the test", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
}
