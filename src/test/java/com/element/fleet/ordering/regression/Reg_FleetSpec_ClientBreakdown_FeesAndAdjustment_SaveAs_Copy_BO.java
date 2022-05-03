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
import com.element.fleet.ordering.page.OrderingVehiclePage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

/**
 * @lastModifiedBy lpadaliya
 */

public class Reg_FleetSpec_ClientBreakdown_FeesAndAdjustment_SaveAs_Copy_BO extends BaseWebDriver {

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
	@Documentation(step = "Click on Vehicle cofig and click on Add Fleet Specs Link", expected = "User should land on the ADD fleet specs page")
	public void goToAddFleetSpecsPage() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickVehicleConfigurationAddFleetSpecsLink();
	}

	@Test(dependsOnMethods = "goToAddFleetSpecsPage")
	@Documentation(step = "Enter vehicle make and model name in serach criteria", expected = "User should be able to enter vehicle make name and model name")
	public void goToViewFleetSpecsPage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.waitForViewFleetSpecsPage();
		OrderingVehicleConfigFleetSpecsPage.enterValueInClientTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInYearTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInMakeTextBox();
	}

	@Test(dependsOnMethods = "goToViewFleetSpecsPage")
	@Documentation(step = "Select first searched vehicle and verify retail only flag", expected = "User should be able see retail only flag and select first searched vehicle after that vehicle details page should be loaded")
	public void selectFirstSearchedvehicle() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.selectFirstVehicleAfterSearch();
		OrderingVehiclePage.waitForVehicleSectionInformationPage();
	}

	@Test(dependsOnMethods = "selectFirstSearchedvehicle")
	@Documentation(step = "Verify Fees and Adjustments are present in spec page", expected = "User should be able see retail only flag") 
	public void feesAdjustmentsTabConfigPage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.validateFessAdjustment();
	}

	@Test(dependsOnMethods = "feesAdjustmentsTabConfigPage")
	@Documentation(step = "Verify contract type dropdown are present in spec page", expected = "User should be able see retail only flag") 
	public void contractTypeDropDownConfigPage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.validateContarctType();
	}

	@Test(dependsOnMethods = "contractTypeDropDownConfigPage")
	@Documentation(step = "Verify custom adjustment functionality in spec page", expected = "User should be able verify custom adjustment functionality in spec page") 
	public void customAdjustmentValidation() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.validateCustomAdjustment();
	}

	@Test(dependsOnMethods = "customAdjustmentValidation")
	@Documentation(step = "Click on Save button,Enter Fleet Spec name on the POP Up and click on save", expected = "Fleet Spec search screen should be visible")
	public void saveCreatedPriceAndConfigSpec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		OrderingVehicleConfigFleetSpecsPage.validateClientBreakdown();
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameAndNotes("Test"+CommonPage.randomAlphaNumericString()));
		OrderingVehicleConfigFleetSpecsPage.validateConfigCreatedMessage();
	}

	@Test(dependsOnMethods = "saveCreatedPriceAndConfigSpec")
	@Documentation(step = "search previously created Fleet Spec with name/id", expected = "Search should result in previously created Fleet Spec")
	public void searchCraetedPriceAndConfigSpec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}

	@Test(dependsOnMethods = "searchCraetedPriceAndConfigSpec")
	@Documentation(step = "Select copy Fleet Spec action for Created Fleet Spec", expected = "Fleet Spec details page should be visible to User")
	public void goToPriceAndConfigDetailsPage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnCopy();
	}

	@Test(dependsOnMethods = "goToPriceAndConfigDetailsPage")
	@Documentation(step = "Validate the copy Fleet Spec pop up for Created Fleet Spec", expected ="Copy Fleet Spec pop up page should be visible to User") 
	public void goToCopyPriceConfigPopUpPage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.validateCopyPriceAndConfigPopUpSave();
		OrderingVehicleConfigFleetSpecsPage.validateFeesAmountReflected();
	}

	@Test(dependsOnMethods = "goToCopyPriceConfigPopUpPage")
	@Documentation(step = "Click on Save button,Enter Fleet Spec name on the POP Up and click on save", expected = "Fleet Spec search screen should be visible")
	public void saveCopyCreatedPriceAndConfigSpec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		OrderingVehicleConfigFleetSpecsPage.validateClientBreakdown();
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterCopyFleetSpecsNameAndNotes("Test"+CommonPage.randomAlphaNumericString()));
		OrderingVehicleConfigFleetSpecsPage.validateConfigCreatedMessage();
	}

	@Test(dependsOnMethods = "saveCopyCreatedPriceAndConfigSpec")
	@Documentation(step = "search previously created Fleet Spec with name/id", expected = "Search should result in previously created Fleet Spec")
	public void searchCopyPriceAndConfigSpec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}

	@Test(dependsOnMethods = "searchCopyPriceAndConfigSpec")
	@Documentation(step = "Select copy Fleet Spec action for Created Fleet Spec", expected = "Fleet Spec details page should be visible to User")
	public void gotoCopyPriceAndConfigDetailsPage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit();
	}

	@Test(dependsOnMethods = "gotoCopyPriceAndConfigDetailsPage")
	@Documentation(step = "Click on Save button,Enter Fleet Spec name on the POP Up and click on save", expected = "Fleet Spec search screen should be visible")
	public void saveAsPriceAndConfigSpec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveAsButton();
		OrderingVehicleConfigFleetSpecsPage.validateClientBreakdownSaveAs();
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterCopyFleetSpecsNameAndNotes("Test"+CommonPage.randomAlphaNumericString()));
		OrderingVehicleConfigFleetSpecsPage.validateConfigCreatedMessage();
	}

	@Test(dependsOnMethods = "saveAsPriceAndConfigSpec")
	@Documentation(step = "search previously created Fleet Spec with name/id", expected = "Search should result in previously created Fleet Spec")
	public void searchEditedPriceAndConfigSpec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}

	@Test(alwaysRun = true, dependsOnMethods = "searchEditedPriceAndConfigSpec")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
}