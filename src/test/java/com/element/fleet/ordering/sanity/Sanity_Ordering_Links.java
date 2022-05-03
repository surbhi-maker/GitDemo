package com.element.fleet.ordering.sanity;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingManagerApprovalMaintenancePage;
import com.element.fleet.ordering.page.OrderingManagerApprovalQueuePage;
import com.element.fleet.ordering.page.OrderingMyOrdersPage;
import com.element.fleet.ordering.page.OrderingStartHerePage;
import com.element.fleet.ordering.page.OrderingWIPOrdersPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy pdhole
 */
public class Sanity_Ordering_Links extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
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
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginApplication() throws Exception {
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.orderingHomePageLabelValidation();
	}

	@Test(dependsOnMethods = "testLoginApplication")
	@Documentation(step = "Navigate to Create Order", expected ="Create Order page loaded") 
	public void navigateToCreateOrderPage() throws Exception {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.orderingSideSectionLabelValidation();
		OrderingHomePage.selectOrderingMenuOption("Create Order");
		OrderingStartHerePage.waitForStartHerepage();
	}
	
	@Test(dependsOnMethods = "navigateToCreateOrderPage")
	@Documentation(step = "Navigate to Batch Template Download page", expected ="Batch Template Download page loaded") 
	public void batchTemplateDownloadPage() throws Exception {
		OrderingHomePage.clickCancelOrderPage();
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.orderingSideSectionLabelValidation();
		OrderingHomePage.selectOrderingMenuOption("Batch Template Download");
		OrderingHomePage.waitForBatchTemplateDownloadPageToLoad();
		OrderingHomePage.batchTemplatePresent();
		OrderingHomePage.validateBatchProcessingExcelDownload(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "batchTemplateDownloadPage")
	@Documentation(step = "Navigate to Work In Process Orders", expected ="Work In Process Orders page loaded") 
	public void navigateToWorkInProcessOrdersPage() throws Exception {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.orderingSideSectionLabelValidation();
		OrderingHomePage.selectOrderingMenuOption("Work In Process Orders");
		OrderingWIPOrdersPage.waitForWorkInProcessOrdersToLoad();
		OrderingWIPOrdersPage.clickOnTheFirstWIPRecordIfAvailable();
	}
	
	@Test(dependsOnMethods = "navigateToWorkInProcessOrdersPage")
	@Documentation(step = "Navigate to My Orders", expected ="My Orders page loaded") 
	public void navigateToMyOrdersPage() throws Exception {	
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.orderingSideSectionLabelValidation();
		OrderingHomePage.selectOrderingMenuOption("My Orders");
		OrderingMyOrdersPage.waitForMyOrdersToLoad();
		OrderingMyOrdersPage.clickOnTheFirstMyOrdersRecordIfAvailable();
	}
	
	@Test(dependsOnMethods = "navigateToMyOrdersPage")
	@Documentation(step = "Navigate to Manager Approvals Maintenance", expected ="Manager Approvals Maintenance page loaded") 
	public void navigateToManagerApprovalMaintentenancePage() throws Exception {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.orderingSideSectionLabelValidation();
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Maintenance");
		OrderingManagerApprovalMaintenancePage.waitForManagerApprovalMaintenanceToLoad();
		OrderingManagerApprovalMaintenancePage.validateLabelOnManagerApprovalMaintenancePage();
		OrderingManagerApprovalMaintenancePage.clickOnTheFirstRecordIfAvailable();
	}
	
	@Test(dependsOnMethods = "navigateToManagerApprovalMaintentenancePage")
	@Documentation(step = "Navigate to Manager Approval Queue", expected ="Manager Approval Queue page loaded") 
	public void navigateToManagerApprovalQueuePage() throws Exception {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.orderingSideSectionLabelValidation();
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Queue");
		OrderingManagerApprovalQueuePage.waitForManagerApprovalQueueToLoad();
		OrderingManagerApprovalQueuePage.validateLabelOnManagerApprovalMaintenancePage();
		OrderingManagerApprovalQueuePage.clickOnTheFirstRecordIfAvailable();
	}
	
	@Test(alwaysRun = true, dependsOnMethods = "navigateToManagerApprovalQueuePage")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
	
}
