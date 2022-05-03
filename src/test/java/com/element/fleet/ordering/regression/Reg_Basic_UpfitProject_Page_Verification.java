package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingBOUpfitProjectPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class Reg_Basic_UpfitProject_Page_Verification extends BaseWebDriver {

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
	public void navigateToQueuesPageAndVerify() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.queuePageLabelValidation();
	}

	@Test(dependsOnMethods = "navigateToQueuesPageAndVerify")
	@Documentation(step = "Navigate and verify upfit/ project page", expected = "Upfit/Project page should be displayed.")
	public void navigateToUpfitProjectPageAndVerify() throws Exception {
		OrderingBOQueuePage.gotoUpfitProjectPage();
		OrderingBOUpfitProjectPage.waitForUpfitProjectPage();
		OrderingBOUpfitProjectPage.upfitProjectPageHeadingLabelValidation();
		OrderingBOUpfitProjectPage.upfitProjectPageBasicComponentsValidation();
	}

	@Test(alwaysRun = true, dependsOnMethods = "navigateToQueuesPageAndVerify")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}

}
