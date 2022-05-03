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
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class Reg_BasicRequest_detail_page_TabVerification extends BaseWebDriver {
	@BeforeClass
	@Parameters({ "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath,
		String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}

	@Test(alwaysRun = true)
	@Documentation(step = "Open application url and login", expected = "Home page of the application should get displayed")
	public void testLaunchURLAndLogin() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openBOApplication();
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor)WebDriverAccess.getDriver());
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}

	@Test(dependsOnMethods = "testLaunchURLAndLogin")
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
	@Documentation(step = "Navigate to Request Quoue", expected = " Request Queue Screen should be displayed.")
	public void navigateToRequestQueueScreen() throws Exception {
		OrderingBOQueuePage.scrollDownToRequestQueue();
	}

	@Test(dependsOnMethods = "navigateToRequestQueueScreen")
	@Documentation(step = "To validate Clear Filters in RequestQueue", expected = "All the basic and advanced search fields should be displayed.")
	public void ToValidateClearFilters() throws Exception {
		OrderingBOQueuePage.validateClearFilters();
	}

	@Test(dependsOnMethods = "ToValidateClearFilters")
	@Documentation(step = "To validate Origin filters is working in RequestQueue", expected = "Fields should be displayed.")
	public void searchOrigin() throws Exception {
		OrderingBOQueuePage.searchOrigin();
	}

	@Test(dependsOnMethods = "searchOrigin")
	@Documentation(step = "To validate Request Detail Page", expected = "Fields should be displayed.")
	public void validateRequestDetailPage() throws Exception {
		OrderingBOQueuePage.validateRequestDetailPage();
	}

	@Test(alwaysRun = true, dependsOnMethods = "validateRequestDetailPage")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}

}