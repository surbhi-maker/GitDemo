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
public class Reg_PriceAndConfigurationSpecification_Header_SummaryWidget_GeoLocation extends BaseWebDriver {
	
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
	public void navigateToPriceAndConfig() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickVehicleConfigurationFleetSpecsAndSearchFleetSpecsLink();
		OrderingVehicleConfigFleetSpecsPage.clickonAdvancedSearchButton();
		OrderingVehicleConfigFleetSpecsPage.waitForVehicleConfigFleetSpecsPage();
	}
	
	@Test(dependsOnMethods = "navigateToPriceAndConfig")
	@Documentation(step = "Click on Vehicle cofig and click on Add Fleet Specs Link", expected = "User should land on the ADD fleet specs page") 
	public void navigateToAddPriceAndConfig() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickVehicleConfigurationAddPriceandConfigSpecsLink();		
	}
	
	@Test(dependsOnMethods = "navigateToAddPriceAndConfig")
	@Documentation(step = "Enter vehicle make and model name in serach criteria", expected = "User should be able to enter vehicle make name and model name") 
	public void goToViewPriceConfigSpecsPage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.waitForViewFleetSpecsPage();
		OrderingVehicleConfigFleetSpecsPage.enterValueInYearTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInMakeTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInTrimTextBox();
	}
	
	@Test(dependsOnMethods = "goToViewPriceConfigSpecsPage")
	@Documentation(step = "Verify retail only flag on add PriceConfig spec page", expected = "User should be able see retail only flag") 
	public void RetailOnlyFlagVerificationonaddPriceConfigpage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.verifyretailonlyflag();
	}
	
	 @Test(dependsOnMethods = "RetailOnlyFlagVerificationonaddPriceConfigpage")
	  @Documentation(step ="Select first searched vehicle and verify retail only flag", expected ="User should be able see retail only flag and select first searched vehicle after that vehicle details page should be loaded") 
	public void selectFirstSearchedvehicle() throws Exception {
		 OrderingVehicleConfigFleetSpecsPage.selectFirstVehicleAfterSearch();
		 OrderingVehiclePage.waitForVehicleSectionInformationPage(); 
	 }
	 
	 
	@Test(dependsOnMethods = "selectFirstSearchedvehicle")
	@Documentation(step = "Verify retail only flag on Price and Config details page", expected = "User should be able see retail only flag on Price and Config details page") 
	public void RetailOnlyFlagVerificationdetailspage() throws Exception {
		OrderingFOFleetSpecDetailsPage.verifyretailonlyflagondetailspage();
	}
	
	@Test(dependsOnMethods = "RetailOnlyFlagVerificationdetailspage")
	@Documentation(step = "Verify Headere on Price and Config details page", expected = "User should be able see client name number and dates in Header on Price and Config details page") 
	public void PriceConfigHeaderVerifications() throws Exception {
		OrderingFOFleetSpecDetailsPage.verifyFleetspecdetailheader();		 
		 	 
	}
	
	@Test(dependsOnMethods = "PriceConfigHeaderVerifications")
	@Documentation(step ="Verify summary widget visibility on Price and Config details page", expected ="User should be able see summary widget on Price and Config details page")
	public void Serachwidgetverficationondetailspage() throws Exception {
		OrderingFOFleetSpecDetailsPage.verifyInvoiceTabSummaryWidget();
		OrderingFOFleetSpecDetailsPage.verifyMSRPtabsummarywidget(); 
	 }
	 
	@Test(dependsOnMethods = "Serachwidgetverficationondetailspage")
	@Documentation(step = "Click on Save button,Enter Price and Config name on the POP Up and click on save", expected = "Price and Config search screen should be visible") 
	public void SaveCraetedpriceandconfigspec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton(); 
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameAndNotes("Test"+CommonPage.randomAlphaNumericString()));
	}
  
	@Test(dependsOnMethods = "SaveCraetedpriceandconfigspec")
	@Documentation(step = "search previously created Price and Config with name/id", expected = "Search should result in previously created Price and Config") 
	public void SearchCraetedpriceandconfigspec() throws Exception {
	  OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}
	
	@Test(dependsOnMethods = "SearchCraetedpriceandconfigspec")
	@Documentation(step = "Select Edit action for Created Price and Config", expected ="Price and Config details page should be visible to User") 
	public void gotopriceandconfigdetailspage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.clickOnEdit(); 
	}
	 
	@Test(dependsOnMethods = "gotopriceandconfigdetailspage")
	@Documentation(step = "Verify Header details", expected = "Price and Config details page should have client id same as logged in client Should have same status as In progress Should have spec name as per search value")
	public void headerverificationonexistingpriceandconfigspec() throws Exception {
		OrderingFOFleetSpecDetailsPage.verifyFleetspecdetailheaderexistingspec();
	}

	@Test(dependsOnMethods = "headerverificationonexistingpriceandconfigspec")
	@Documentation(step = "User clicks on Cancel button on Price and Config details page", expected = "Price and Config details page should get closed and User should land on Search Result Page")
	public void closepriceandconfigspecdetailspage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickCancelOnVehicleConfigurationPage();
	}
	 
	@Test(alwaysRun = true, dependsOnMethods = "closepriceandconfigspecdetailspage")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
	
}
