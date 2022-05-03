package com.element.fleet.ordering.sanity;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy pdhole
 */
public class Sanity_Manager_Order_Preferences extends BaseWebDriver {
	
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
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void testLaunchBOURL() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Exception {
		OrderingBOHomePage.orderingBOHomePageLoaded();
		OrderingBOHomePage.boHomePageLabelValidation();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Go to Manager Order Preferences  page", expected = " Manager Order Preferences page should be loaded")
	public void goToManagerOrderPreferencesPage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Manager Order Preferences");
		OrderingBOManagerOrderPreferencesPage.waitForManagerOrderPreferencesPageToLoaded();
		OrderingBOManagerOrderPreferencesPage.validateManagerOrderPreferencesPageHeadings();
	}
	
	@Test(dependsOnMethods = "goToManagerOrderPreferencesPage")
	@Documentation(step = "Enter corp and client and then verify page", expected = "Corp and client have been entered and page is verified")
	public void enterCorpAndClientAndVerify() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterCorpAndClientAndSelectClient();
		OrderingBOManagerOrderPreferencesPage.validateManagerApprovalSectionWithDetails();
	}
	
	@Test(alwaysRun = true, dependsOnMethods = "enterCorpAndClientAndVerify")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}

}
