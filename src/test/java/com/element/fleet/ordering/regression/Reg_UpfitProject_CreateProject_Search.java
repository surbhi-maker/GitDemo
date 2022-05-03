package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class Reg_UpfitProject_CreateProject_Search extends BaseWebDriver {

	@BeforeClass
	@Parameters({ "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Throwable {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}

	@Test(alwaysRun=true)
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void testLaunchBOURL() throws Throwable {
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Throwable {
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor)WebDriverAccess.getDriver());
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Navigate and verify Queues page", expected = "Queues page should be displayed")
	public void navigateToQueuesPage() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
	}

	@Test(dependsOnMethods = "navigateToQueuesPage")
	@Documentation(step = "Navigate and verify upfit/ project page", expected = "Upfit/Project page should be displayed.")
	public void navigateToUpfitProjectPage() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.gotoUpfitProjectPage();
	}

	@Test(dependsOnMethods = "navigateToUpfitProjectPage")
	@Documentation(step = "Navigate to Project Quoue", expected = " Project Queue Screen should be displayed.")
	public void navigateToProjectQueueScreen() throws Exception {
		OrderingBOQueuePage.scrollDownToProjectQueue();
	}

	@Test(dependsOnMethods = "navigateToProjectQueueScreen")
	@Documentation(step = "Navigate to Create Project Pop-up", expected = "Create Project Pop-up should be displayed.")
	public void navigateToCreateProjectScreen() throws Exception {
		OrderingBOQueuePage.navigateToCreateProjectScreen();
	}

	@Test(dependsOnMethods = "navigateToCreateProjectScreen")
	@Documentation(step = "Enter details to create Client Project on Create Project Pop-up", expected = "User Should able to enter details to create Client project")
	public void enterDetailsOnCreateProjectPop_up() throws Exception {
		OrderingBOQueuePage.enterDetailsOnCreateProjectPop_up();
	}

	@Test(dependsOnMethods = "enterDetailsOnCreateProjectPop_up")
	@Documentation(step = "Click on save", expected = "Client Project Details should be saved")
	public void saveClientProjectDetails() throws Exception {
		OrderingBOQueuePage.saveClientProjectDetails();
	}

	@Test(dependsOnMethods = "saveClientProjectDetails")
	@Documentation(step = "Search Project in Project Queue", expected = "Project details Should be visible in Project queue")
	public void searchProject() throws Exception {
		OrderingBOQueuePage.searchProject();
	}

	@Test(dependsOnMethods = "searchProject")
	@Documentation(step = "Validating Mandatory field on Create Project Pop-up", expected = "Project Should not be saved without mandatory fields and after click on save without filling mandatory field error should be highlighted.")
	public void validatingMandatoryFieldsOnCreateProject() throws Exception {
		OrderingBOQueuePage.validatingMandatoryFieldsOnCreateProject();
	}

	@Test(alwaysRun = true, dependsOnMethods = "validatingMandatoryFieldsOnCreateProject")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}

}