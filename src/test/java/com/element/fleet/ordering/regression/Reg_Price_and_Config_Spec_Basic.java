package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingFOFleetSpecDetailsPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingVehicleConfigFleetSpecsPage;
import com.element.fleet.ordering.page.OrderingVehiclePage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy pdhole
 */
public class Reg_Price_and_Config_Spec_Basic extends BaseWebDriver {
	
	
	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "username", "boUserName" ,"orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username,String boUserName,String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
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
	@Documentation(step = "Click on Vehicle cofig Link and Search for Price and Config Specs Link", expected = "User should land on the search page for Price and Config specs")
	public void goToPriceandConfigSpecsPage() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickVehicleConfigurationsSavedPriceAndConfigurationLink();
		OrderingVehicleConfigFleetSpecsPage.clickonAdvancedSearchButton();
		OrderingVehicleConfigFleetSpecsPage.waitForVehicleConfigFleetSpecsPage();
	}
	
	@Test(dependsOnMethods = "goToPriceandConfigSpecsPage")
	@Documentation(step = "Click on export on  Search for PriceandConfig Specs screen", expected = "User should be able to see exported excel") 
	public void exporttoexcelsearhdataverification() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyExportToExcelSearchPage("serachpagequeuepriceandconfigresults",this.getClass().getSimpleName());
	}
	
	
	@Test(dependsOnMethods = "exporttoexcelsearhdataverification")
	@Documentation(step = "Click on Vehicle cofig and click on Add Price and Config Specs Link", expected = "User should land on the Price and Config specs page") 
	public void goToAddPriceandConfigSpecsPage() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickVehicleConfigurationAddPriceandConfigSpecsLink();		
	}
	
	@Test(dependsOnMethods = "goToAddPriceandConfigSpecsPage")
	@Documentation(step = "Enter vehicle make and model name in serach criteria", expected = "User should be able to enter vehicle make name and model name") 
	public void goToViewPriceandConfigSpecsPage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.waitForViewFleetSpecsPage();
		OrderingVehicleConfigFleetSpecsPage.enterValueInYearTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInMakeTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInModelTextBox();
	}

	@Test(dependsOnMethods = "goToViewPriceandConfigSpecsPage")
	@Documentation(step = "Select first searched vehicle", expected = "User should be able select first searched vehicle after that vehicle details page should be loaded")
	public void selectPriceandConfigSearchedvehicle() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.selectFirstVehicleAfterSearch();
		OrderingVehiclePage.waitForVehicleSectionInformationPage();
	}
	
	@Test(dependsOnMethods = "selectPriceandConfigSearchedvehicle")
	@Documentation(step = "Select DPO and verify standard equipment section", expected = "On selection DPO option type and option cost should be updated standard equipment section should be visible with 8 sections") 
	public void dpoandstandardequipmentsectionverification() throws Exception {
		OrderingFOFleetSpecDetailsPage.dpoVerification();
		OrderingFOFleetSpecDetailsPage.standardEquipmentTabVerification();
	}
	
	@Test(dependsOnMethods = "dpoandstandardequipmentsectionverification")
	@Documentation(step = "Click on Save dropdown and save from list shown", expected = "User should be able to enter Price and Config spec name, save and search the same spec")
	public void enterPriceandConfigSpecNamAndDescription() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameAndNotesspec("Test"+CommonPage.randomAlphaNumericString()));
		OrderingVehicleConfigFleetSpecsPage.verifyGrowlMessageNewPriceAndConfig();
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}
	
	@Test(dependsOnMethods = "enterPriceandConfigSpecNamAndDescription")
	@Documentation(step = "Select Edit action for Created Price and Config Spec", expected = "Price and Config spec details page should be visible to User")
	public void editCreatedPriceandConfigSpec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit();
		OrderingVehiclePage.checklockingmessagevisible();
		OrderingFOFleetSpecDetailsPage.existingDpoVerification();
	}
	
	@Test(dependsOnMethods = "editCreatedPriceandConfigSpec")
	@Documentation(step = "Click on SAVE dropsown and select Save As option", expected = "User should be able to enter Price and Config spec name, save and search the same spec") 
	public void enterEditPriceandConfigSpecNamAndDescription() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveAsButton();
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameAndNotesspec("Test"+CommonPage.randomAlphaNumericString()));
		OrderingVehicleConfigFleetSpecsPage.verifyGrowlMessageUpdatePriceAndConfigt();
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}
	
	@Test(alwaysRun = true, dependsOnMethods = "enterEditPriceandConfigSpecNamAndDescription")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
}