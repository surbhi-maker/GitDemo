package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingVehicleConfigFleetSpecsPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy lokesh
 */
public class Reg_FleetSpecification_ManageView extends BaseWebDriver {
	
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
	@Parameters("applicationURL")
	@Documentation(step = "Open browser and enter Front Office Application URL", expected ="Login page of the Front Office Application should get displayed") 
	public void testLaunchURL(String applicationURL) throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openFOApplication();
	}
	
	@Test(dependsOnMethods = "testLaunchURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginApplication() throws Exception {
		
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.orderingHomePageLabelValidation();
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
		CommonPage.initializeElementOrderObject();	
	}
	
	@Test(dependsOnMethods = "testLoginApplication")
	@Documentation(step = "Click on Vehicle cofig Link and Search for Fleet Specs Link", expected = "User should land on the search page for fleet specs") 
	public void goToFleetSpecsPage() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickVehicleConfigurationFleetSpecsAndSearchFleetSpecsLink();
	}

	@Test(dependsOnMethods = "goToFleetSpecsPage")
	@Documentation(step = "Navigate to Manage View Pop-up", expected = " Manage Pop-up should be displayed.")
	public void navigateToManageViewPopUpScreen() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.navigateToManageViewPopUpScreen();	
	}
	
	@Test(dependsOnMethods = "navigateToManageViewPopUpScreen")
	@Documentation(step = "Verify Standard View", expected = " Standard should not changed or deleted.")
	public void verifyStandardView() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyStandardView();	
	}
	
	@Test(dependsOnMethods = "verifyStandardView")
	@Documentation(step = "Verify Manage View", expected = " Selected Coloumn should be reflect in table.")
	public void verifyManageView() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyManageView();	
	}
	
	@Test(dependsOnMethods = "verifyManageView")
	@Documentation(step = "Delete newly created Custom view", expected = " Custom view  should be deleted.")
	public void deleteCustomView() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.deleteCustomView();	
	}
	
	@Test(dependsOnMethods = "deleteCustomView")
	@Documentation(step = "navigate to search price and config screen", expected = " Price and Config page should display.")
	public void navigateToSearchPriceandConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.navigateToSearchPriceandConfig();	
	}
	
	@Test(dependsOnMethods = "navigateToSearchPriceandConfig")
	@Documentation(step = "Verify Manage View in Price and Config", expected = " Selected Coloumn should be reflect in table.")
	public void verifyManageViewPriceConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyManageViewPriceConfig();	
	}
	
	@Test(dependsOnMethods = "verifyManageViewPriceConfig")
	@Documentation(step = "Delete newly created Custom view", expected = " Custom view  should be deleted.")
	public void deleteCustomViewPriceConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.deleteCustomViewPriceConfig();	
	}
	
	@Test(alwaysRun = true, dependsOnMethods = "deleteCustomViewPriceConfig")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
	
}