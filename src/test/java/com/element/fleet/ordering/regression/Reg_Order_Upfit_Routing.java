package com.element.fleet.ordering.regression;


import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOrdMaintPage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingBOUpfitProjectPage;
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

public class Reg_Order_Upfit_Routing extends BaseWebDriver{
	//String logNo;
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
		OrderingHomePage.orderingSideSectionLabelValidation();
		OrderingHomePage.selectOrderingMenuOption("Create Order");
		OrderingStartHerePage.waitForStartHerepage();
	}
	
	@Test(dependsOnMethods = "navigateToCreateOrder")
	@Documentation(step = "Input data to Start Here", expected ="The application should Input data to Start Here") 
	public void	inputValuesForStartHere() throws Throwable {
		OrderingStartHerePage.enterUnitNumber();
		OrderingStartHerePage.selectOrderType();
		OrderingStartHerePage.selectPoolOrderNo();
		OrderingStartHerePage.selectAddUsedUnitNo();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDriverPage.waitForDriverPage();
	}
	
	@Test(dependsOnMethods = "inputValuesForStartHere")
	@Documentation(step = "Select driver", expected ="The application should select driver") 
	public void	selectDriver() throws Throwable {
		OrderingDriverPage.selectDriver();
		OrderingDriverPage.clickSaveAndNext();
		OrderingVehiclePage.waitForVehiclePage();
	}
	
	
	@Test(dependsOnMethods = "selectDriver")
	@Documentation(step = "Select vehicle from scratch table", expected ="The application should select vehicle from scratch table") 
	public void	selectVehicleForOrdering() throws Throwable {
		OrderingVehiclePage.selectBuildFromScratchRadio();
		OrderingVehiclePage.waitForBuildFromScratchTable();
		OrderingVehiclePage.selectFirstVehicleFromBuildFromScratchTable();
		OrderingVehiclePage.selectExteriorColor();
		OrderingVehiclePage.selectInteriorColor();
		OrderingBOOrdMaintPage.goToUpfitTab(); 
		OrderingBOOrdMaintPage.addFOUpfitDetails();
		OrderingVehiclePage.clickSaveAndNext();
		OrderingBillingAndRegistrationPage.waitForBillingAndRegistrationPage();
	}
	
	
	
	@Test(dependsOnMethods = "selectVehicleForOrdering")
    @Documentation(step = "Select billing and registration details", expected ="The application should select billing and registration details") 
    public void    billingAndRegistration() throws Throwable {
        OrderingBillingAndRegistrationPage.selectLeaseTerm();
        OrderingBillingAndRegistrationPage.clickSaveAndNext();
        OrderingDealerPage.waitForDealerPage();
    }
	
	@Test(dependsOnMethods = "billingAndRegistration")
    @Documentation(step = "To select dealer", expected ="The application should select dealer") 
    public void selectDealer() throws Throwable {
//      OrderingDealerPage.clickDifferentDealer();
//      OrderingDealerPage.searchAndSelectDealer();
        OrderingDealerPage.clickSaveAndNext();
        OrderingSummaryPage.waitForSummaryPage();
    }
		
	@Test(dependsOnMethods = "selectDealer")
	@Documentation(step = "Submit an order", expected ="The application should submit the order") 
	public void	submitOrder() throws Throwable {
		OrderingSummaryPage.clickSubmit();
		OrderingSummaryPage.confirmSubmit();	
		OrderingSummaryPage.getLogNumber();
	}
	
	
	//@Test(alwaysRun = true)
	@Test(dependsOnMethods = "submitOrder")
	@Documentation(step = "Open application url and login", expected = "Home page of the application should get displayed") 
	public void testLaunchURLAndLogin() throws Exception {
		//CommonPage.testStarted();
		OrderingLoginPage.openBOApplication();
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor)WebDriverAccess.getDriver());
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}

	@Test(dependsOnMethods = "testLaunchURLAndLogin")
	@Documentation(step = "Navigate and verify Queues page", expected = "Queues page should be displayed")
	public void navigateToQueuesPage() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.queuePageLabelValidation();
	}
	
	@Test(dependsOnMethods = "navigateToQueuesPage")
	@Documentation(step = "Navigate and verify upfit/ project page", expected = "Upfit/Project page should be displayed.")
	public void navigateToUpfitProjectPage() throws Exception {
		OrderingBOQueuePage.gotoUpfitProjectPage();
		OrderingBOUpfitProjectPage.waitForUpfitProjectPage();
		OrderingBOUpfitProjectPage.upfitProjectPageHeadingLabelValidation();
		OrderingBOUpfitProjectPage.upfitProjectPageBasicComponentsValidation();
	}
	
	
	@Test(dependsOnMethods = "navigateToUpfitProjectPage")
	@Documentation(step = "Navigate and verify upfit/ project page", expected = "Upfit/Project page should be displayed.")
	public void toVerifyRequestQueue() throws Exception {
		OrderingBOQueuePage.upfitRequestQueueLabelValidation();
	}
	
	// Validate clear filter
	@Test(dependsOnMethods = "toVerifyRequestQueue")
	@Documentation(step = "To validate Clear Filters in RequestQueue", expected = "All the basic and advanced search fields should be cleared.")
	public void ToValidateClearFilters() throws Exception {
		OrderingBOQueuePage.validateClearFilters();
		}
	
	
	//Fill log number
	@Test(dependsOnMethods = "ToValidateClearFilters")
	@Documentation(step = "To validate filters are working in RequestQueue", expected = "Fields should be displayed.")
	public void validateLogNumber() throws Exception {
		OrderingBOQueuePage.validateLogNumber();
		OrderingBOQueuePage.validateBGColor();
		OrderingBOQueuePage.validateBold();
		//OrderingBOQueuePage.clickOnRecord();
		
		}

	
	@Test(alwaysRun = true, dependsOnMethods = "validateLogNumber")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
	
	
}
