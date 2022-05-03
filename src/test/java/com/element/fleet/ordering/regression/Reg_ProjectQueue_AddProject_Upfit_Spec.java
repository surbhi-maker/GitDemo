package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnProjectQueuePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

/**
 * @lastModifiedBy shisingh
 */
public class Reg_ProjectQueue_AddProject_Upfit_Spec extends BaseWebDriver{
	
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
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Exception {
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor) WebDriverAccess.getDriver());
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}

	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Go to Project Queue page", expected = "Application should load Project Queue page")
	public void goToProjectQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Project");
	}

	@Test(dependsOnMethods = "goToProjectQueuePage")
	@Documentation(step = "Click on Create new project and fill the details", expected = "Application should be able to enter fill all fields data ")
	public void createNewProjectAndFillData() throws Exception {
		OrderingBOOnProjectQueuePage.createAndSelectProject();
	}

	@Test(dependsOnMethods = "createNewProjectAndFillData")
	@Documentation(step = "Go to quote queue page and click on Add New Quote", expected = "Application should be able to click on add new quote queue")
	public void addQuote() throws Throwable {
		OrderingBOOnProjectQueuePage.createQuote();
	}

	@Test(dependsOnMethods = "addQuote")
	@Documentation(step = "Go to convert upfit fleet spec page and fill all data", expected = "Application should be able to navigate to convert upfit fleet spec page and all data should be filled")
	public void convertToUpfitSpec() throws Exception {
		OrderingBOOnProjectQueuePage.convertToUpfitSpecsFrmPrjQuotePage();
	}
	
	@Test(dependsOnMethods = "convertToUpfitSpec")
	@Documentation(step = "Go to convert upfit fleet spec page and fill all data", expected = "Application should be able to navigate to convert upfit fleet spec page and all data should be filled")
	public void manageGroupsVerification() throws Exception {
		OrderingBOOnProjectQueuePage.addAndVerifyGroupAdded("None or One");
		OrderingBOOnProjectQueuePage.clickOnElementApproveLink();
	}
	
	@Test(dependsOnMethods = "manageGroupsVerification")
	@Documentation(step = "Click on Attach to fleet spec button", expected = "Application should be able to click on Attach to fleet spec button and fleet")
	public void verifyDetachFleetSpec() throws Exception {
		OrderingBOOnProjectQueuePage.attachFleetSpec();
		OrderingBOOnProjectQueuePage.detachFleetSpec();
		OrderingBOOnProjectQueuePage.verifyFleetSpecDetached();
		OrderingBOOnProjectQueuePage.clickOnElementApproveLink();
		OrderingBOOnProjectQueuePage.validateUpfitSpecStatus("Element Approved");
	}
	
	@Test(dependsOnMethods = "verifyDetachFleetSpec")
	@Documentation(step = "Click on Attach to fleet spec button", expected = "Application should be able to click on Attach to fleet spec button and fleet")
	public void verifyAttachFleetSpec() throws Exception {
		OrderingBOOnProjectQueuePage.attachFleetSpec();
		OrderingBOOnProjectQueuePage.verifyAttachedFleetSpec();
		OrderingBOOnProjectQueuePage.clickOnClientApproveLink();
		OrderingBOOnProjectQueuePage.validateUpfitSpecStatus("Customer Approved");
	}
	
	@Test(dependsOnMethods = "verifyAttachFleetSpec", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}

