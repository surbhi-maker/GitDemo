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
public class Reg_FleetSpecification_Header_SummaryWidget_GeoLocation extends BaseWebDriver {
	
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
		OrderingVehicleConfigFleetSpecsPage.enterValueInTrimTextBox();
	}
	
	@Test(dependsOnMethods = "goToViewFleetSpecsPage")
	@Documentation(step = "Verify retail only flag on add fleet spec page",expected = "User should be able see retail only flag") 
	public void RetailOnlyFlagVerificationonaddfleetpage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyretailonlyflag(); 
	}
	
	@Test(dependsOnMethods = "RetailOnlyFlagVerificationonaddfleetpage")
	@Documentation(step ="Select first searched vehicle and verify retail only flag", expected ="User should be able see retail only flag and select first searched vehicle after that vehicle details page should be loaded") 
	public void selectFirstSearchedvehicle() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.selectFirstVehicleAfterSearch();
		OrderingVehiclePage.waitForVehicleSectionInformationPage(); 
	}
	
	@Test(dependsOnMethods = "selectFirstSearchedvehicle")
	@Documentation(step = "Verify retail only flag on fleet spec details page",expected ="User should be able see retail only flag on fleet spec details page") 
	public void RetailOnlyFlagVerificationdetailspage() throws Exception {
		OrderingFOFleetSpecDetailsPage.verifyretailonlyflagondetailspage(); 
	}	
	
	@Test(dependsOnMethods = "RetailOnlyFlagVerificationdetailspage")
	@Documentation(step = "Verify Headere on fleet spec details page", expected ="User should be able see client name number and dates in Header on fleet spec details page") 
	public void FleetSpecHeaderVerifications() throws Exception {
	  OrderingFOFleetSpecDetailsPage.verifyFleetspecdetailheader();
	}
	    
	@Test(dependsOnMethods = "FleetSpecHeaderVerifications")
	@Documentation(step ="Verify summary widget visibility on fleet spec details page", expected ="User should be able see summary widget on fleet spec details page") public
	void Serachwidgetverficationondetailspage() throws Exception {
		OrderingFOFleetSpecDetailsPage.verifyInvoiceTabSummaryWidget();
		OrderingFOFleetSpecDetailsPage.verifyMSRPtabsummarywidget(); 
	}
	 	
	@Test(dependsOnMethods = "Serachwidgetverficationondetailspage")
	@Documentation(step = "Verify summary widget visibility on fleet spec details page", expected = "User should be able see summary widget on fleet spec details page") 
	public void GEOLocationTabVerification() throws Exception {
		OrderingFOFleetSpecDetailsPage.clickOnGEOLocationtab();
		OrderingFOFleetSpecDetailsPage.geoLocationTabVerification();
	}
	
	@Test(dependsOnMethods = "GEOLocationTabVerification")
	@Documentation(step = "Click on Deselect all checkbox,Click on Save button", expected = "User should be able to see that no checkbox would be selected,User should not be able to save the fleet spec until minimum one geographical location is getting selected.") 
	public void NoGeoLocationselectionandSaveErrorVerification() throws Exception {
		OrderingFOFleetSpecDetailsPage.nogeolocationselection();
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		OrderingFOFleetSpecDetailsPage.alertverificationfornogeolocation();
	}
	
	@Test(dependsOnMethods = "NoGeoLocationselectionandSaveErrorVerification")
	@Documentation(step = "Select first 3 checkbox on Geo location tab", expected = "Fleet spec should be save with first 3 checkbox selected ") 
	public void SelectGeoLocationandSave() throws Exception {
		OrderingFOFleetSpecDetailsPage.selectGEOLocatinons();
	}
	
	@Test(dependsOnMethods = "SelectGeoLocationandSave")
	@Documentation(step = "Click on Save button,Enter fleet spec name on the POP Up and click on save", expected = "Fleet spec search screen should be visible") 
	public void SaveCraetedfleetspec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton(); 
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameAndNotes("Test"+CommonPage.randomAlphaNumericString()));
	}
	
	@Test(dependsOnMethods = "SaveCraetedfleetspec")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s") 
	public void SearchCraetedfleetspec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}
	
	@Test(dependsOnMethods = "SearchCraetedfleetspec")
	@Documentation(step = "Click on the spec", expected = "User should be able to see fleet spec details page") 
	public void gotofleetdetailspage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit();
		OrderingVehiclePage.checklockingmessagevisible();
	}
		 
	@Test(dependsOnMethods = "gotofleetdetailspage")
	@Documentation(step = "Verify Header details", expected = "Fleet spec details page should have client id same as logged in client Should have same status as In progress Should have spec name as per search value") 
	public void headerverificationonexistingfleetspec() throws Exception {
		OrderingFOFleetSpecDetailsPage.verifyFleetspecdetailheaderexistingspec();
	}
	
	@Test(dependsOnMethods = "headerverificationonexistingfleetspec")
	@Documentation(step = "Verify GEO Location tab", expected = "Should have 3 checkboxes selected same as created") 
	public void GEOLocatioverificationonexistingspec() throws Exception {
		OrderingFOFleetSpecDetailsPage.clickOnGEOLocationtab();
		OrderingFOFleetSpecDetailsPage.verifygeolocationonexistingspec();	
	}
		
	@Test(dependsOnMethods = "GEOLocatioverificationonexistingspec")
	@Documentation(step = "User clicks on Cancel button on fleet spec details page", expected = "Fleet spec details page should get closed and User should land on Search Result Page") 
	public void closefleetspecdetailspage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickCancelOnVehicleConfigurationPage();	
	}
	
	@Test(alwaysRun = true, dependsOnMethods = "closefleetspecdetailspage")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
	
}