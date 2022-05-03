package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBillingAndRegistrationPage;
import com.element.fleet.ordering.page.OrderingDealerPage;
import com.element.fleet.ordering.page.OrderingDriverPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingStartHerePage;
import com.element.fleet.ordering.page.OrderingSummaryPage;
import com.element.fleet.ordering.page.OrderingVehiclePage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

/**
 * @lastModifiedBy lpadaliya
 */
public class Reg_Create_Order_FromScratch_DPO_Summary extends BaseWebDriver{
	
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

	@Test(alwaysRun = true)
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void testLaunchURL() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openFOApplication();
	}

	@Test(dependsOnMethods = "testLaunchURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginApplication() throws Exception {
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor)WebDriverAccess.getDriver());
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.orderingHomePageLabelValidation();
	}
	
	@Test(dependsOnMethods = "testLoginApplication")
	@Documentation(step = "Change the client", expected = "Client should be changed")
	public void changeClientBreakdown() throws Exception {
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
	}
	
	@Test(dependsOnMethods = "changeClientBreakdown")
	@Documentation(step = "Navigate to Create Order", expected ="The application should load Create Order page") 
	public void	navigateToCreateOrder() throws Throwable {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.selectOrderingMenuOption("Create Order");
		OrderingStartHerePage.waitForStartHerepage();
	}
	
	@Test(dependsOnMethods = "navigateToCreateOrder")
	@Documentation(step = "Input data to Start Here", expected ="The application should Input data to Start Here") 
	public void	inputValuesForStartHere() throws Throwable {
		OrderingStartHerePage.enterStartHerePageDetails(CommonPage.getTestData("ApplicationURL"));
		OrderingStartHerePage.selectAddUsedUnitNo();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDriverPage.waitForDriverPage();
	}
	
	@Test(dependsOnMethods = "inputValuesForStartHere")
	@Documentation(step = "Select driver", expected ="The application should select driver") 
	public void	selectDriver() throws Throwable {
		OrderingDriverPage.selectDriver();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingVehiclePage.waitForVehiclePage();
	}
	
	@Test(dependsOnMethods = "selectDriver")
	@Documentation(step = "Select vehicle from scratch table", expected ="The application should select vehicle from scratch table") 
	public void	selectVehicleForOrdering() throws Throwable {
		OrderingVehiclePage.selectBuildFromScratchRadio();
		OrderingVehiclePage.waitForBuildFromScratchTable();
		OrderingVehiclePage.selectFirstVehicleFromBuildFromScratchTable();
		OrderingVehiclePage.selectExteriorColor();
		OrderingVehiclePage.confirmColor();
		OrderingVehiclePage.selectInteriorColor();
		OrderingVehiclePage.confirmColor();
		OrderingVehiclePage.verifyVehicleSpecDetailsHeader();
		OrderingVehiclePage.verifyOptionTypeEnabledWithCheckBoxSelected();
		OrderingVehiclePage.verifyOptionTypeAdjustmentTypeAndDriverPrice();
		OrderingVehiclePage.verifyFullInvoicePriceIsPopulatedInDriverPrice();
		OrderingVehiclePage.verifyDriverPriceAdjustment();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingBillingAndRegistrationPage.waitForBillingAndRegistrationPage();
	}
	
	@Test(dependsOnMethods = "selectVehicleForOrdering")
	@Documentation(step = "Select billing and registration details", expected ="The application should select billing and registration details") 
	public void	billingAndRegistration() throws Throwable {
		OrderingBillingAndRegistrationPage.selectLeaseTerm();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDealerPage.waitForDealerPage();
	}
	
	@Test(dependsOnMethods = "billingAndRegistration")
	@Documentation(step = "To select dealer", expected ="The application should select dealer") 
	public void	selectDealer() throws Throwable {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingSummaryPage.waitForSummaryPage();
		OrderingSummaryPage.verifyVehicleSpecificationOnSummaryPage();
		OrderingSummaryPage.verifyColumnHeadings();
		OrderingSummaryPage.verifyDriverPrice();
	}
	
	@Test(dependsOnMethods = "selectDealer")
	@Documentation(step = "Submit an order", expected ="The application should submit the order") 
	public void	submitOrder() throws Throwable {
		OrderingSummaryPage.clickSubmit();
		OrderingSummaryPage.confirmSubmit();	
		OrderingSummaryPage.verifySuccessfulSubmissionPopUp();
	}
	
	@Test(alwaysRun = true, dependsOnMethods = "submitOrder")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
}