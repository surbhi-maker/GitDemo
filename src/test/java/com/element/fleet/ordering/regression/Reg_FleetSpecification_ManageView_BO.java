package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingVehicleConfigFleetSpecsPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

/**
 * @lastModifiedBy ksharma
 */

public class Reg_FleetSpecification_ManageView_BO extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({ "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
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
		CommonPage.initializeElementOrderObject();
	}

	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Click on Vehicle cofig and click on Add Fleet Specs Link", expected = "User should land on the ADD fleet specs page")
	public void goToAddFleetSpecsPage() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickBOVehicleConfigurationAddFleetSpecsLink();
	}

	@Test(dependsOnMethods = "goToAddFleetSpecsPage")
	@Documentation(step = "Enter vehicle make and model name in serach criteria", expected = "User should be able to enter vehicle make name and model name")
	public void goToViewFleetSpecsPage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.waitForViewFleetSpecsPage();
		OrderingVehicleConfigFleetSpecsPage.enterValueInYearTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInClientTextBox();
	}
	
	@Test(dependsOnMethods = "goToViewFleetSpecsPage")
	@Documentation(step = "Click on Vehicle cofig Link and Search for Fleet Specs Link", expected = "User should land on the search page for fleet specs") 
	public void goToFleetSpecsPage() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickVehicleConfigurationFleetSpecsAndSearchFleetSpecsLink();
	}
	
	@Test(dependsOnMethods = "goToFleetSpecsPage")
	@Documentation(step = "Verify Manage View", expected = " Selected Coloumn should be reflect in table.")
	public void verifyManageView() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyManageView();	
	}
	
	@Test(dependsOnMethods = "verifyManageView")
	@Documentation(step = "Delete newly created Custom view", expected = " Custom view  should be deleted.")
	public void deleteCustomView() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.deleteCustomViewBO();	
	}
	
	@Test(dependsOnMethods = "deleteCustomView")
	@Documentation(step = "navigate to search price and config screen", expected = " Price and Config page should display.")
	public void navigateToSearchPriceandConfig() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickBOVehicleConfigurationAddPriceandConfigSpecsLink();	
	}
	
	@Test(dependsOnMethods = "navigateToSearchPriceandConfig")
	@Documentation(step = "Enter vehicle make and model name in serach criteria", expected = "User should be able to enter vehicle make name and model name")
	public void goToViewPriceandConfigSpecsPage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.waitForViewFleetSpecsPage();
		OrderingVehicleConfigFleetSpecsPage.enterValueInYearTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInClientTextBox();
	}
	
	@Test(dependsOnMethods = "navigateToSearchPriceandConfig")
	@Documentation(step = "Verify Manage View in Price and Config", expected = " Selected Coloumn should be reflect in table.")
	public void verifyManageViewPriceConfig() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickBOVehicleConfigurationSearchPriceandConfigSpecsLink();
		OrderingVehicleConfigFleetSpecsPage.verifyManageViewPriceConfig();	
	}
	
	@Test(dependsOnMethods = "verifyManageViewPriceConfig")
	@Documentation(step = "Delete newly created Custom view", expected = " Custom view  should be deleted.")
	public void deleteCustomViewPriceConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.deleteCustomViewPriceConfigBO();	
	}
	
	@Test(alwaysRun = true, dependsOnMethods = "deleteCustomViewPriceConfig")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
}