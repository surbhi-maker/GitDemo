package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingBOOnProjectQueuePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingBOUpfitSpecPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

/**
 * @lastModifiedBy shisingh
 */
public class Reg_ProjectQueue_Save_AddRemove_Export extends BaseWebDriver{
	
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
	@Documentation(step = "Go to Project Queue page and verify the fields and table columns", expected = "All the Fields and table columns should be displayed")
	public void verifyProjectQueuePageFields() throws Throwable {
		OrderingBOOnProjectQueuePage.verifyFields();
	}

	@Test(dependsOnMethods = "verifyProjectQueuePageFields")
	@Documentation(step = "Controls links functionality", expected = "Application should display data according to project Name ")
	public void verifyControlLinkFunctionality() throws Exception {
		OrderingBOOnProjectQueuePage.createNewProjectAndFillData();
		OrderingBOOnProjectQueuePage.clickOnAddProjectOkButton();
		OrderingBOOnOrderQueuePage.waitForProjectQueuePage();
		OrderingBOOnProjectQueuePage.searchProjectByName();
	}

	@Test(dependsOnMethods = "verifyControlLinkFunctionality")
	@Documentation(step = "Click on Add and Remove button", expected = "Application should displayed results according to Add and Remove Selection")
	public void verifyAddRemoveFunctioanality() throws Throwable {
		OrderingBOOnProjectQueuePage.addAndRemoveProjectQueueAndVerifySelectAllColumns();
		OrderingBOOnProjectQueuePage.addAndRemoveProjectQueueAndVerifyDeselectAllColumns();
		OrderingBOOnProjectQueuePage.addAndRemoveProjectQueueAndVerifySelectFourColumns();
		OrderingBOOnProjectQueuePage.addAndRemoveProjectQueueSelectAllColumns();
	}

	@Test(dependsOnMethods = "verifyAddRemoveFunctioanality")
	@Documentation(step = "Go to verify Pagenation", expected = "Application should load project queue page and pagenation should be validated")
	public void verifyPagenationProjectQueuePage() throws Exception {
		OrderingBOOnProjectQueuePage.verifyPaginationFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyPagenationProjectQueuePage")
	@Documentation(step = "Go to Project queue page and Verify Export Functioanality", expected = "Application should able to export project queue data in csv")
	public void verifyExportFunctiomnality() throws Exception {
		OrderingBOOnProjectQueuePage.clickProjectExportAndVerify(this.getClass().getSimpleName());
		OrderingBOQueuePage.compareCSVDataWithUIData("Corp","Fleet");
	}

	@Test(dependsOnMethods = "verifyExportFunctiomnality")
	@Documentation(step = "Go to Project queue page and Verify Save Button functioanality", expected = "Application should load project queue page and Save button functionality validated")
	public void verifySaveButtonFunctionality() throws Exception {
		OrderingBOUpfitSpecPage.verifySaveFunctionality();
	}
	
	@Test(dependsOnMethods = "verifySaveButtonFunctionality")
	@Documentation(step = "Click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}
