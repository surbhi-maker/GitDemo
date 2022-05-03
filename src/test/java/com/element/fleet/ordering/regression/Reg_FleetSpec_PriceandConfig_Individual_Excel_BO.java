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
 * @lastModifiedBy ksharma
 */
public class Reg_FleetSpec_PriceandConfig_Individual_Excel_BO extends BaseWebDriver {
	
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
		OrderingVehicleConfigFleetSpecsPage.enterValueInMakeTextBox();
	}

	@Test(dependsOnMethods = "goToViewFleetSpecsPage")
	@Documentation(step = "Select first searched vehicle and verify retail only flag", expected = "User should be able see retail only flag and select first searched vehicle after that vehicle details page should be loaded")
	public void selectFirstSearchedvehicle() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.selectFirstVehicleAfterSearch();
		OrderingVehiclePage.waitForVehicleSectionInformationPage();
	}

	@Test(dependsOnMethods = "selectFirstSearchedvehicle")
	@Documentation(step = "Click on Save button,Enter fleet spec name on the POP Up and click on save", expected = "Fleet spec search screen should be visible")
	public void SaveCraetedfleetspec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameAndNotesNew("Test" + CommonPage.randomAlphaNumericString()));
	}

	@Test(dependsOnMethods = "SaveCraetedfleetspec")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void SearchCraetedfleetspec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}

	@Test(dependsOnMethods = "SearchCraetedfleetspec")
	@Documentation(step = "Click on the individual action 3 dots for spec", expected = "User should be abel to see Export to PDF Action")
	public void clickonexporttoexcelfleetserachpage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.verifyClickOnExportToExcelSearchPage("fleetsearchExcel",this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "clickonexporttoexcelfleetserachpage")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void SearchCraetedfleetspecexporttoexcelfromdetailspage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit();
		OrderingVehiclePage.checklockingmessagevisible();
		OrderingFOFleetSpecDetailsPage.exportToExcel("fleetdetailsExcel",this.getClass().getSimpleName());
		OrderingVehicleConfigFleetSpecsPage.clickCancelOnVehicleConfigurationPage();
	}

	@Test(dependsOnMethods = "SearchCraetedfleetspecexporttoexcelfromdetailspage")
	@Documentation(step = "Click on Vehicle cofig and click on Add PriceandConfig Specs Link", expected = "User should land on the ADD PriceandConfig specs page")
	public void goToAddPriceandConfigSpecsPageandcreate() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickVehicleConfigurationAddPriceandConfigSpecsLink();
		OrderingVehicleConfigFleetSpecsPage.waitForViewFleetSpecsPage();
		OrderingVehicleConfigFleetSpecsPage.enterValueInYearTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInMakeTextBox();
		OrderingVehicleConfigFleetSpecsPage.selectFirstVehicleAfterSearch();
		OrderingVehiclePage.waitForVehicleSectionInformationPage();
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameAndNotesNew("Test" + CommonPage.randomAlphaNumericString()));
	}

	@Test(dependsOnMethods = "goToAddPriceandConfigSpecsPageandcreate")
	@Documentation(step = "search previously created p&c spec with name/id", expected = "Search should result in previously created p&c spec/s")
	public void searchCreatedPriceandConfigspec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}

	@Test(dependsOnMethods = "searchCreatedPriceandConfigspec")
	@Documentation(step = "Click on the individual action 3 dots for spec", expected = "User should be abel to see Export to PDF Action")
	public void clickonexporttoexcelPriceandConfigserachpage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.verifyClickOnExportToExcelSearchPage("priceandconfigsearchExcel",this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "clickonexporttoexcelPriceandConfigserachpage")
	@Documentation(step = "search previously created p&c spec with name/id", expected = "Search should result in previously created p&c spec/s")
	public void clickonexporttoexcelPriceandConfigdetailspage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit();
		OrderingVehiclePage.checklockingmessagevisible();
		OrderingFOFleetSpecDetailsPage.exportToExcel("priceandconfigdetailsExcel",this.getClass().getSimpleName());
		OrderingVehicleConfigFleetSpecsPage.clickCancelOnVehicleConfigurationPage();
	}

	@Test(alwaysRun = true, dependsOnMethods = "clickonexporttoexcelPriceandConfigdetailspage")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
}