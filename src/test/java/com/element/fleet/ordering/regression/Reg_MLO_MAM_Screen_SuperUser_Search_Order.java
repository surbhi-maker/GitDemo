package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingManagerApprovalMaintenancePage;
import com.element.fleet.ordering.page.OrderingManagerApprovalQueuePage;
import com.element.fleet.ordering.page.OrderingMyOrdersPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/*
 * PreCondition: This test case will work only when there is a approval rule with below details:
 * Year: 2020
 * Approval Type: Sequential
 * Note: Used Approval Rule with above details is MAM_2020
 * @lastModifiedBy usha naidu
 */
public class Reg_MLO_MAM_Screen_SuperUser_Search_Order extends BaseWebDriver {
	
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
		OrderingBOManagerOrderPreferencesPage.createOrdersWithLoggerUser(this.getClass().getSimpleName());
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
	public void verifySearchOrderWithSperUser() throws Throwable {
		OrderingLoginPage.openCustomFOApplication(CommonPage.getTestData("SuperUser"));
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Maintenance");
		OrderingManagerApprovalMaintenancePage.waitForManagerApprovalMaintenanceToLoad();
		OrderingManagerApprovalMaintenancePage.clickOnTheFirstRecord();
		OrderingManagerApprovalMaintenancePage.enterLogNumber(CommonPage.getTestData("LoggerLogNumber"+1));
		OrderingManagerApprovalMaintenancePage.clickSearchButtonInMaintenance();
		OrderingManagerApprovalMaintenancePage.verifyLogNumber(CommonPage.getTestData("LoggerLogNumber"+1));
		PDFReporter.takeExtraScreenshot();
	}
		
	@Test(dependsOnMethods = "verifySearchOrderWithSperUser")
	@Documentation(step = "Super User Approve the order", expected = "Order should be approved by Super User and order should be removed from list")
	public void moveToManagerApprovalMaintenancePage() throws Throwable {
		OrderingManagerApprovalMaintenancePage.moveBackToManagerApprovalMaintenancePage();
		OrderingManagerApprovalMaintenancePage.waitForManagerApprovalMaintenanceToLoad();
		OrderingManagerApprovalQueuePage.verifyNoOrdersInManagerApprovalQueue(); 
		System.out.println("Record is not present as expected");
	}
	
	@Test(dependsOnMethods="moveToManagerApprovalMaintenancePage")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly") 
	public void verifySuperUserLogOut() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifySuperUserLogOut", alwaysRun = true)
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		CommonPage.testEnded();
	}
	
}