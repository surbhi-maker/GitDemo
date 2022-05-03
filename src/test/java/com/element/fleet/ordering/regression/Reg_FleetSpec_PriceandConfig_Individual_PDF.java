package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingCommonPage;
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
public class Reg_FleetSpec_PriceandConfig_Individual_PDF extends BaseWebDriver {

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
	@Parameters({ "username" })
	@Documentation(step ="Enter the valid username and Password to login to the Front Office Application and select client from home page", expected= "The Front Office Application home page should be displayed after successful login")
	public void testLoginApplication(String username) throws Exception {
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
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.clickonAdvancedSearchButton();
		OrderingVehicleConfigFleetSpecsPage.waitForVehicleConfigFleetSpecsPage();
	}
	
	@Test(dependsOnMethods = "goToFleetSpecsPage")
	@Documentation(step = "Click on Vehicle cofig and click on Add Fleet Specs Link", expected = "User should land on the ADD fleet specs page")
	public void goToAddFleetSpecsPage() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickVehicleConfigurationAddFleetSpecsLink();
	}
	  
	@Test(dependsOnMethods = "goToAddFleetSpecsPage")
	@Documentation(step = "Enter vehicle make and model name in serach criteria", expected = "User should be able to enter vehicle make name and model name")
	public void goToViewFleetSpecsPage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.waitForViewFleetSpecsPage();
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
	@Documentation(step = "Click on the individual action 3 dots for spec and click on export to PDF", expected = "User should be abel to see Model POP up with client view selection")
	public void selectclientviewandverify() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyclientviewonPDFModelPopUp();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("fleetsearchclientview",this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "selectclientviewandverify")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void SearchCraetedfleetspecfordriverviewverification() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnExporttoPDF();
	}

	@Test(dependsOnMethods = "SearchCraetedfleetspecfordriverviewverification")
	@Documentation(step = "User clicks on Driver View", expected = "User should be abel to see driver view with standard Net equipment and two pricig scheems")
	public void selectDriverviewandverify() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyDriverviewonPDFModelPopUp();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("searchpagefleetdriverview",this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "selectDriverviewandverify")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void SearchCraetedfleetspecforpricingviewverification() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnExporttoPDF();
	}

	@Test(dependsOnMethods = "SearchCraetedfleetspecforpricingviewverification")
	@Documentation(step = "User clicks on Pricing View and validate the view", expected = "User should be abel to see pricing view with standard Net equipment and fees and adjustment checkboxes and two pricig scheems")
	public void selectpricingviewandverify() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyPricingviewonPDFModelPopUp();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("searchpagefleetpricingview",this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "selectpricingviewandverify")
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
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void SearchCraetedfleetspecforcustomedriverviewverification() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit();
		OrderingVehiclePage.checklockingmessagevisible();
		OrderingFOFleetSpecDetailsPage.exporttopdf();
	}

	@Test(dependsOnMethods = "SearchCraetedfleetspecforcustomedriverviewverification")
	@Documentation(step = "User clicks on Pricing View and validate the view", expected = "User should be abel to see pricing view with standard Net equipment and fees and adjustment checkboxes and two pricig scheems")
	public void selectdriverviewwithcustomizeselection() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.selectcustomizedriverview();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("detailspagefleetdriverview",this.getClass().getSimpleName());
		OrderingVehicleConfigFleetSpecsPage.clickCancelOnVehicleConfigurationPage();
	}

	@Test(dependsOnMethods = "selectdriverviewwithcustomizeselection")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void SearchCraetedfleetspecforcustomepricingiewverification() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit();
		OrderingVehiclePage.checklockingmessagevisible();
		OrderingFOFleetSpecDetailsPage.exporttopdf();
	}

	@Test(dependsOnMethods = "SearchCraetedfleetspecforcustomepricingiewverification")
	@Documentation(step = "User clicks on Pricing View and validate the view", expected = "User should be abel to see pricing view with standard Net equipment and fees and adjustment checkboxes and two pricig scheems")
	public void selectpricingviewwithcustomizeselection() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.selectcustomizepricingview();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("detailspagefleetpricingview",this.getClass().getSimpleName());
		OrderingVehicleConfigFleetSpecsPage.clickCancelOnVehicleConfigurationPage();
	}

	@Test(dependsOnMethods = "selectpricingviewwithcustomizeselection")
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
	@Documentation(step = "Click on the individual action 3 dots for spec and click on export to PDF", expected = "User should be abel to see Model POP up with client view selection")
	public void selectclientviewandverifyPriceandConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyclientviewonPDFModelPopUp();
		OrderingVehicleConfigFleetSpecsPage.verifyclientviewAvailabelalloptions();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("searchpagep&cclientview",this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "selectclientviewandverifyPriceandConfig")
	@Documentation(step = "search previously created p&c spec with name/id", expected = "Search should result in previously created p&c spec/s")
	public void SearchCraetedPriceandConfigspecfordriverviewverification() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnExporttoPDF();
	}

	@Test(dependsOnMethods = "SearchCraetedPriceandConfigspecfordriverviewverification")
	@Documentation(step = "User clicks on Driver View", expected = "User should be abel to see driver view with standard Net equipment and two pricig scheems")
	public void selectDriverviewandverifyPriceandConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyDriverviewonPDFModelPopUp();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("searchpagep&cdriverview",this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "selectDriverviewandverifyPriceandConfig")
	@Documentation(step = "search previously created p&c spec with name/id", expected = "Search should result in previously created p&c spec/s")
	public void SearchCraetedfleetspecforpricingviewverificationPriceandConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnExporttoPDF();
	}

	@Test(dependsOnMethods = "SearchCraetedfleetspecforpricingviewverificationPriceandConfig")
	@Documentation(step = "User clicks on Pricing View and validate the view", expected = "User should be abel to see pricing view with standard Net equipment and fees and adjustment checkboxes and two pricig scheems")
	public void selectpricingviewandverifyPriceandConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyPricingviewonPDFModelPopUp();
		OrderingVehicleConfigFleetSpecsPage.verifypricingviewAvailabelalloptions();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("searchpagep&cpricingview",this.getClass().getSimpleName());
	}	

	@Test(dependsOnMethods = "selectpricingviewandverifyPriceandConfig")
	@Documentation(step = "search previously created p&c spec with name/id", expected = "Search should result in previously created p&c spec/s")
	public void SearchCraetedPriceandConfigspecforcustomeclientviewverification() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit();
		OrderingVehiclePage.checklockingmessagevisible();
		OrderingFOFleetSpecDetailsPage.exporttopdf();
	}

	@Test(dependsOnMethods = "SearchCraetedPriceandConfigspecforcustomeclientviewverification")
	@Documentation(step = "User clicks on Pricing View and validate the view", expected = "User should be abel to see pricing view with standard Net equipment and fees and adjustment checkboxes and two pricig scheems")
	public void selectclientviewwithcustomizeselectionPriceandConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.selectcustomizeclientview();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("detailpagep&cclientview",this.getClass().getSimpleName());
		OrderingVehicleConfigFleetSpecsPage.clickCancelOnVehicleConfigurationPage();
	}

	@Test(dependsOnMethods = "selectclientviewwithcustomizeselectionPriceandConfig")
	@Documentation(step = "search previously created p&c spec with name/id", expected = "Search should result in previously created p&c spec/s")
	public void SearchCraetedPriceandConfigspecforcustomedriverviewverification() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit();
		OrderingVehiclePage.checklockingmessagevisible();
		OrderingFOFleetSpecDetailsPage.exporttopdf();
	}

	@Test(dependsOnMethods = "SearchCraetedPriceandConfigspecforcustomedriverviewverification")
	@Documentation(step = "User clicks on Pricing View and validate the view", expected = "User should be abel to see pricing view with standard Net equipment and fees and adjustment checkboxes and two pricig scheems")
	public void selectdriverviewwithcustomizeselectionPriceandConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.selectcustomizedriverview();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("detailpagep&cdriverview",this.getClass().getSimpleName());
		OrderingVehicleConfigFleetSpecsPage.clickCancelOnVehicleConfigurationPage();
	}

	@Test(dependsOnMethods = "selectdriverviewwithcustomizeselectionPriceandConfig")
	@Documentation(step = "search previously created p&c spec with name/id", expected = "Search should result in previously created p&c spec/s")
	public void SearchCraetedPriceandConfigspecforcustomepricingiewverification() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit();
		OrderingVehiclePage.checklockingmessagevisible();
		OrderingFOFleetSpecDetailsPage.exporttopdf();
	}

	@Test(dependsOnMethods = "SearchCraetedPriceandConfigspecforcustomepricingiewverification")
	@Documentation(step = "User clicks on Pricing View and validate the view", expected = "User should be abel to see pricing view with standard Net equipment and fees and adjustment checkboxes and two pricig scheems")
	public void selectpricingviewwithcustomizeselectionPriceandConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.selectcustomizepricingview();
		OrderingVehicleConfigFleetSpecsPage.PDFExportverification("detailpagep&cpricingview",this.getClass().getSimpleName());
		OrderingVehicleConfigFleetSpecsPage.clickCancelOnVehicleConfigurationPage();
	}

	@Test(alwaysRun = true, dependsOnMethods = "selectpricingviewwithcustomizeselectionPriceandConfig")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}

}