package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingBOOnProjectQueuePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingBOQuotePage;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingVehicleConfigFleetSpecsPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy shisingh
 */
public class Reg_Create_Upfit_Spec extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
		//BaseWebDriver.driver = CommonPage.starDebugMode("8082");
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}

	@Test(alwaysRun = true)
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void testLaunchURL() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openFOApplication();
	}
	
	@Test(dependsOnMethods = "testLaunchURL")
	@Documentation(step = "Enter the required client number to the application",expected = "The application display the required client") 
	public void changeClientBreakdown() throws Exception {
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown();
		OrderingHomePage.changeClient();
	}

	@Test(dependsOnMethods = "changeClientBreakdown")
	@Documentation(step = "Validate Vehicle Configuration side section labels", expected = "Vehicle Configuration side section labels validated") 
	public void	validateVehicleConfigurationSectionLabels() throws Throwable {
		OrderingHomePage.selectSideMenuOption("Vehicle Configuration");
		OrderingHomePage.vehicleConfigurationSideSectionIsLoaded();
		OrderingHomePage.selectVehicleConfigurationMenuOption("Search for Fleet Specifications");
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehicleConfigFleetSpecsPage.waitForVehicleConfigFleetSpecsPage();
	}

	@Test(dependsOnMethods = "validateVehicleConfigurationSectionLabels")
	@Documentation(step = "Application should select first searched vehicle", expected = "Application should be able select first searched vehicle") 
	public void searchAddedFleetSpecsAndSelectVehicle() throws Throwable {
		OrderingVehicleConfigFleetSpecsPage.fleetSpecsPageLabelValidation();
		OrderingVehicleConfigFleetSpecsPage.enterFixFleetSpecsNameInSearchBoxAndCickOnSearch();
		OrderingVehicleConfigFleetSpecsPage.selectFirstSearchVehicleForApproval();
		OrderingVehicleConfigFleetSpecsPage.verifyFleetStatusIsWIP();
	}
	
	@Test(dependsOnMethods = "searchAddedFleetSpecsAndSelectVehicle")
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void testLaunchBOURL() throws Exception {
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Exception {
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Go to On project Queue page", expected = "Application should load On project Queue page")
	public void gotoProjectQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Project");
		OrderingBOOnOrderQueuePage.waitForProjectQueuePage();
	}
	
	@Test(dependsOnMethods = "gotoProjectQueuePage")
	@Documentation(step = "Create and select the project", expected = "Creation and selection of project should be successful")
	public void createProject() throws Throwable {
		OrderingBOOnProjectQueuePage.createAndSelectProject();
	}
	
	@Test(dependsOnMethods = "createProject")
	@Documentation(step = "Create Quote", expected = "Quote creation should be succesful")
	public void createQuote() throws Throwable {
		OrderingBOOnProjectQueuePage.createQuote();
		OrderingBOOnProjectQueuePage.getQuoteId();
		OrderingBOOnProjectQueuePage.closeProjectDetails();
	}
	
	@Test(dependsOnMethods = "createQuote")
	@Documentation(step = "Go to Quote queue", expected = "Application should load quote Queue page")
	public void gotoQuoteQueuePage() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Quote");
	}
	
	@Test(dependsOnMethods = "gotoQuoteQueuePage")
	@Documentation(step = "Convert Quote to Upfit Spec", expected = "Quote Converstion to Upfit Spec should be succesful")
	public void convertQuoteToUpfitSpec() throws Exception {
		OrderingBOQuotePage.convertToUpfitSpecs("Quote ID");
	}
	
	@Test(dependsOnMethods = "convertQuoteToUpfitSpec")
	@Documentation(step = "Click on OK button and go Upfit Maintanance Page and click on Element Approve", expected = "Application should be able to click on Ok button and Element Approve should be done")
	public void goToUpfitMaintenancePageAndClickOnElementApprove() throws Exception {
		OrderingBOOnProjectQueuePage.clickOnConvertUpfitSpecOkButton();
		OrderingBOOnProjectQueuePage.clickOnElementApproveLink();
	}

	@Test(dependsOnMethods = "goToUpfitMaintenancePageAndClickOnElementApprove")
	@Documentation(step = "Click on Attach to fleet spec button And Associate fleet", expected = "Application should be able to click on Attach to fleet spec button and fleet should be associated")
	public void goToUpfitSpecFleetSpecAssociationPageAndAssociateFleet() throws Exception {
		OrderingBOOnProjectQueuePage.clickOnAttachToFleetSpecLink();
		OrderingBOOnProjectQueuePage.waitForUpfitSpecFleetSpecAssociationPage();
		OrderingBOOnProjectQueuePage.selectFleetSpecFromFleetSpecificationSearchBox("Test1234");
		OrderingBOOnProjectQueuePage.clickOnFleetSpecificationSaveButton();
		OrderingBOOnProjectQueuePage.clickOnCloseUpfitSpecMaintenancePage();
		OrderingBOOnProjectQueuePage.clickOnCloseEditUpfitQuotePage();
	}

	@Test(dependsOnMethods = "goToUpfitSpecFleetSpecAssociationPageAndAssociateFleet")
	@Documentation(step = "Open browser and enter Application url", expected ="Login page of the application should get displayed") 
	public void launchFOUrl() throws Exception {		
		OrderingLoginPage.openFOApplication();
		
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
	}

	@Test(dependsOnMethods = "launchFOUrl")
	@Documentation(step = "Click on Vehicle cofig and click on fleet spec and Search fleet specs link", expected = "Application should be opened the fleet specs page") 
	public void goToFleetSpecs() throws Throwable {
		OrderingHomePage.selectSideMenuOption("Vehicle Configuration");
		OrderingHomePage.vehicleConfigurationSideSectionIsLoaded();
		OrderingHomePage.vehicleConfigurationSideSectionLabelValidation();
		OrderingHomePage.selectVehicleConfigurationMenuOption("Fleet Specification");
		OrderingHomePage.selectVehicleConfigurationMenuOption("Search for Fleet Specifications");
	}

	@Test(dependsOnMethods = "goToFleetSpecs")
	@Documentation(step = "Click on Add New link and select fleet and click on Element Approve", expected = "Application should be opened the View fleet specs page and fleet should be selected and fleet status changed to Element Approved") 
	public void goToViewFleetSpecsPageSelectFleetAndClickOnElementApprove() throws Throwable {
		OrderingVehicleConfigFleetSpecsPage.waitForVehicleConfigFleetSpecsPage();
		OrderingVehicleConfigFleetSpecsPage.fleetSpecsPageLabelValidation();
		OrderingVehicleConfigFleetSpecsPage.enterFixFleetSpecsNameInSearchBoxAndCickOnSearch();
		OrderingVehicleConfigFleetSpecsPage.selectFirstSearchVehicleForApproval();
		OrderingVehicleConfigFleetSpecsPage.clickOnElementApproveButton();
	}

	@Test(dependsOnMethods = "goToViewFleetSpecsPageSelectFleetAndClickOnElementApprove")
	@Documentation(step = "select fleet and click on Client Approve button", expected = "Fleet status should be changed to Client Approved") 
	public void searchSelectFleetAndClickOnClientAprrove() throws Throwable {
		OrderingVehicleConfigFleetSpecsPage.waitForVehicleConfigFleetSpecsPage();
		OrderingVehicleConfigFleetSpecsPage.enterFixFleetSpecsNameInSearchBoxAndCickOnSearch();
		OrderingVehicleConfigFleetSpecsPage.selectFirstSearchVehicleForApproval();
		OrderingVehicleConfigFleetSpecsPage.clickOnClientApproveButton();
	}
	
	@Test(dependsOnMethods = "searchSelectFleetAndClickOnClientAprrove", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
}
