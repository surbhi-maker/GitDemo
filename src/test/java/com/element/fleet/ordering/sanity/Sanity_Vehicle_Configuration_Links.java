package com.element.fleet.ordering.sanity;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingVehicleConfigFleetSpecsPage;
import com.element.fleet.ordering.page.OrderingVehiclePage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy pdhole
 */
public class Sanity_Vehicle_Configuration_Links extends BaseWebDriver {
	
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
	@Documentation(step = "Change the client", expected = "Client should be changed")
	public void changeClientBreakdown() throws Exception {
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
	}
	
	@Test(dependsOnMethods = "changeClientBreakdown")
	@Documentation(step = "Navigate to Fleet Specs page", expected ="The application should load Fleet Specs page") 
	public void	navigateToVehicleConfiguration() throws Throwable {
		OrderingHomePage.selectSideMenuOption("Vehicle Configuration");
		OrderingHomePage.vehicleConfigurationSideSectionIsLoaded();
		OrderingHomePage.vehicleConfigurationSideSectionLabelValidation();
		OrderingHomePage.selectVehicleConfigurationMenuOption("Search for Fleet Specifications");
		OrderingVehicleConfigFleetSpecsPage.clickonAdvancedSearchButton();
		OrderingVehicleConfigFleetSpecsPage.waitForVehicleConfigFleetSpecsPage();
		OrderingVehicleConfigFleetSpecsPage.fleetSpecsPageLabelValidation();
		OrderingVehicleConfigFleetSpecsPage.clickOnTheFirstFleetRecordIfAvailable();
		OrderingVehiclePage.checklockingmessagevisible();
	}
	
	@Test(dependsOnMethods = "navigateToVehicleConfiguration")
	@Documentation(step = "Navigate to Price & Configuration page", expected ="The application should load Price & Configuration page") 
	public void	navigateToPriceAndConfigurationPage() throws Throwable {
		OrderingHomePage.selectSideMenuOption("Vehicle Configuration");
		OrderingHomePage.vehicleConfigurationSideSectionIsLoaded();
		OrderingHomePage.selectVehicleConfigurationMenuOption("Search for Saved Price & Configuration");
		OrderingVehicleConfigFleetSpecsPage.clickonAdvancedSearchButton();
		OrderingVehicleConfigFleetSpecsPage.waitForVehicleConfigFleetSpecsPage();
		OrderingVehicleConfigFleetSpecsPage.priceAndConfigurationPageLabelValidation();
		OrderingVehicleConfigFleetSpecsPage.clickOnTheFirstFleetRecordIfAvailable();
		OrderingVehiclePage.checklockingmessagevisible();
	}
	
	@Test(alwaysRun = true, dependsOnMethods = "navigateToPriceAndConfigurationPage")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}

}
