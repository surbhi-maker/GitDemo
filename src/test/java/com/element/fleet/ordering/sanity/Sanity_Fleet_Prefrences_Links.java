package com.element.fleet.ordering.sanity;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingFOFleetPreferencesPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy pdhole
 */
public class Sanity_Fleet_Prefrences_Links extends BaseWebDriver {
	
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
	@Documentation(step = "Navigate to Fleet Prefrences", expected ="The application should land on ordering fleet preferences page") 
	public void	navigateToChangeFleetPrefrencesIcon() throws Exception {
		OrderingHomePage.selectSideMenuOption("Fleet Preferences");
		OrderingCommonPage.checkAlertPopUp();
		OrderingHomePage.fleetPrefrencesPageLoaded();
		OrderingFOFleetPreferencesPage.fleetPreferencesLabelValidation();
	}
	
	@Test(alwaysRun = true, dependsOnMethods = "navigateToChangeFleetPrefrencesIcon")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
	
}
