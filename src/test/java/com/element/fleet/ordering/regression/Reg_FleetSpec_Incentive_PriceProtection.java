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
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy pdhole
 */
public class Reg_FleetSpec_Incentive_PriceProtection extends BaseWebDriver{

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
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void testLaunchURL() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openFOApplication();
	}

	@Test(dependsOnMethods = "testLaunchURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginApplication() throws Exception {
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.orderingHomePageLabelValidation();
	}

	@Test(dependsOnMethods = "testLoginApplication")
	@Documentation(step = "Change the client", expected = "Client should be changed")
	public void changeClientBreakdown() throws Exception {
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();	
		CommonPage.initializeElementOrderObject();
	}

	@Test(dependsOnMethods = "changeClientBreakdown")
	@Documentation(step = "navigate to search fleet spec page", expected = "system should navigate user to navigate to search fleet spec page")
	public void navigateToSearchFleetSpec() throws Throwable {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.selectVehicleConfigurationMenuOption("Search for Fleet Specifications");
	}

	@Test (dependsOnMethods = "navigateToSearchFleetSpec")
	@Documentation(step = "Search price and config spec", expected = "system should Search price and config spec")
	public void searchPCSpec() throws Throwable {
		OrderingVehicleConfigFleetSpecsPage.clickOnClearFilters();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Fleet Spec Id", CommonPage.getTestData("FleetSpecSearch"));
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}

	@Test (dependsOnMethods = "searchPCSpec")
	@Documentation(step = "Validate Price protection Icon", expected = "system should Validate Price protection Icon")
	public void verifyPriceProtectionIconOnSearchPage() throws Throwable {
		OrderingVehicleConfigFleetSpecsPage.verifyPriceProtectionTableCellIsDisplayed(1,"Price Protected");
	}
	@Test(dependsOnMethods = "verifyPriceProtectionIconOnSearchPage")
	@Documentation(step = "validate incentives Price Protection in Summary widget", expected = "Price Protection icon should be present in Invoice & MSRP tab of summary widget, incentives should be present for the selected spec")
	public void validatePriceProtection() throws Exception{
		OrderingVehicleConfigFleetSpecsPage.selectSpecFromTable(1);
		OrderingFOFleetSpecDetailsPage.incentiveVerification("Required", "Available");
		OrderingFOFleetSpecDetailsPage.verifyInvoiceTabSummaryWidgetPriceProtection();
		OrderingFOFleetSpecDetailsPage.verifyMSRPTabSummaryWidgetPriceProtection(); 
	}

	@Test(dependsOnMethods = "validatePriceProtection")
	@Documentation(step = "validate color of Base and destination fee text", expected = "color of base and destination fee text should be green")
	public void varifyBaseAndDestinationFeePriceLabelAreInGreen() throws Exception{
		OrderingFOFleetSpecDetailsPage.verifyColorForBaseAndDestinationFeeLabels("#82C341"); 
	}

	@Test(alwaysRun = true, dependsOnMethods = "varifyBaseAndDestinationFeePriceLabelAreInGreen")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
	
}