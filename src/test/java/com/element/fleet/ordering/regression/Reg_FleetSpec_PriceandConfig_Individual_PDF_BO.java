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
public class Reg_FleetSpec_PriceandConfig_Individual_PDF_BO extends BaseWebDriver {
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
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameAndNotes("Test" + CommonPage.randomAlphaNumericString()));
	}

	@Test(dependsOnMethods = "SaveCraetedfleetspec")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void SearchCraetedfleetspec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}

	@Test(dependsOnMethods = "SearchCraetedfleetspec")
	@Documentation(step = "Click on the individual action 3 dots for spec", expected = "User should be abel to see Export to PDF Action")
	public void clickonexporttopdf() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnExporttoPDF();
	}

	@Test(dependsOnMethods = "clickonexporttopdf")
	@Documentation(step = "User clicks on Driver View", expected = "User should be abel to see driver view with standard Net equipment and two pricig scheems")
	public void selectDriverviewandverify() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyDriverviewonPDFModelPopUp();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("searchpagefleetdriverview",this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "selectDriverviewandverify")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void SearchCraetedfleetspecforcustomeclientviewverification() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit();
		OrderingVehiclePage.checklockingmessagevisible();
		OrderingFOFleetSpecDetailsPage.exporttopdf();
	}

	@Test(dependsOnMethods = "SearchCraetedfleetspecforcustomeclientviewverification")
	@Documentation(step = "User clicks on Pricing View and validate the view", expected = "User should be abel to see pricing view with standard Net equipment and fees and adjustment checkboxes and two pricig scheems")
	public void selectclientviewwithcustomizeselection() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.selectcustomizeclientview();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("detailspagefleetclientview",this.getClass().getSimpleName());
		OrderingVehicleConfigFleetSpecsPage.clickCancelOnVehicleConfigurationPage();
	}

	@Test(dependsOnMethods = "selectclientviewwithcustomizeselection")
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
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameAndNotes("Test" + CommonPage.randomAlphaNumericString()));
	}

	@Test(dependsOnMethods = "goToAddPriceandConfigSpecsPageandcreate")
	@Documentation(step = "search previously created p&c spec with name/id", expected = "Search should result in previously created p&c spec/s")
	public void SearchCraetedPriceandConfigspec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}

	@Test(dependsOnMethods = "SearchCraetedPriceandConfigspec")
	@Documentation(step = "Click on the individual action 3 dots for spec", expected = "User should be abel to see Export to PDF Action")
	public void clickonexporttopdfPriceandConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnExporttoPDF();
	}
	
	@Test(dependsOnMethods = "clickonexporttopdfPriceandConfig")

	@Documentation(step = "User clicks on Driver View", expected = "User should be abel to see driver view with standard Net equipment and two pricig scheems")
	public void selectDriverviewandverifyPriceConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyDriverviewonPDFModelPopUp();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("searchpagefleetdriverview",this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "selectDriverviewandverifyPriceConfig")
	@Documentation(step = "search previously created price & config spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void SearchCraetedPriceandConfigforcustomeclientviewverification() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit();
		OrderingVehiclePage.checklockingmessagevisible();
		OrderingFOFleetSpecDetailsPage.exporttopdf();
	}

	@Test(dependsOnMethods = "SearchCraetedPriceandConfigforcustomeclientviewverification")
	@Documentation(step = "User clicks on Pricing View and validate the view", expected = "User should be abel to see pricing view with standard Net equipment and fees and adjustment checkboxes and two pricig scheems")
	public void selectclientviewwithcustomizeselectionPriceandConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.selectcustomizeclientview();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("detailspagefleetclientview",this.getClass().getSimpleName());
		OrderingVehicleConfigFleetSpecsPage.clickCancelOnVehicleConfigurationPage();
	}	

	@Test(alwaysRun = true, dependsOnMethods = "selectclientviewwithcustomizeselectionPriceandConfig")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
		
	}

}