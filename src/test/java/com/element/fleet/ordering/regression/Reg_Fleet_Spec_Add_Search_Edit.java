package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingFOFleetSpecDetailsPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingVehicleConfigFleetSpecsPage;
import com.element.fleet.ordering.page.OrderingVehiclePage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

/**
 * @lastModifiedBy lpadaliya
 */

public class Reg_Fleet_Spec_Add_Search_Edit extends BaseWebDriver {
	
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
		CommonPage.initializeElementOrderObject();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Navigate to Add price and Config", expected = "System should navigate user to Price and Config page")
	public void navigateToAddPriceAndConfig() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickVehicleConfigurationAddFleetSpecsLink();		
	}
	
	@Test(dependsOnMethods = "navigateToAddPriceAndConfig")
	@Documentation(step = "Enter vehicle make and model name in serach criteria", expected = "User should be able to enter vehicle make name and model name") 
	public void goToViewFleetSpecsPage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.waitForViewFleetSpecsPage();
		OrderingVehicleConfigFleetSpecsPage.enterValueInClientTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInYearTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInMakeTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInModelTextBox();
	}

	@Test(dependsOnMethods = "goToViewFleetSpecsPage")
	@Documentation(step = "Select first searched vehicle", expected = "User should be able select first searched vehicle after that vehicle details page should be loaded") 
	public void selectFirstSearchedvehicle() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.selectFirstVehicleAfterSearch();
		OrderingVehiclePage.waitForVehicleSectionInformationPage();
	}
	
	@Test(dependsOnMethods = "selectFirstSearchedvehicle")
	@Documentation(step = "Select DPO and verify standard equipment section", expected = "On selection DPO option type and option cost should be updated standard equipment section should be visible with 8 sections") 
	public void dpoAndStandardEquipmentSectionVerification() throws Exception {
		OrderingFOFleetSpecDetailsPage.dpoVerification();
		OrderingFOFleetSpecDetailsPage.standardEquipmentTabVerification();
	}
	
	@Test(dependsOnMethods = "dpoAndStandardEquipmentSectionVerification")
	@Documentation(step = "Click on Save dropdown and save from list shown", expected = "User should be able to enter fleet spec name, save and search the same spec") 
	public void enterFleetSpecNamAndDescription() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameAndNotesspec("Test"+CommonPage.randomAlphaNumericString()));
		OrderingVehicleConfigFleetSpecsPage.verifyGrowlMessageNewFleet();
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}
	
	@Test(dependsOnMethods = "enterFleetSpecNamAndDescription")
	@Documentation(step = "Select Edit action for Created Fleet Spec", expected = "Fleet spec details page should be visible to User")
	public void editCreatedFleetSpec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit();
		OrderingVehiclePage.checklockingmessagevisible();
		OrderingFOFleetSpecDetailsPage.existingDpoVerification();
		OrderingFOFleetSpecDetailsPage.existingDpoUpdate();
	}
	
	@Test(dependsOnMethods = "editCreatedFleetSpec")
	@Documentation(step = "Click on SAVE dropsown and select Save As option", expected = "User should be able to enter fleet spec name, save and search the same spec") 
	public void enterEditFleetSpecNamAndDescription() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveAsButton();
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameAndNotesspec("Test"+CommonPage.randomAlphaNumericString()));
		OrderingVehicleConfigFleetSpecsPage.verifyGrowlMessageUpdatetFleet();
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}
	
	@Test(alwaysRun = true, dependsOnMethods = "enterFleetSpecNamAndDescription")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
}