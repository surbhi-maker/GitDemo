package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
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

/**
 * @lastModifiedBy Damodhar
 */
public class Reg_DIO_Capping_Team_Member_BO_Flow extends BaseWebDriver {

	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath,this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}

	@Test(alwaysRun = true)
	@Documentation(step = "Open browser and enter Application url", expected ="Login page of the application should get displayed") 
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
	@Documentation(step = "Select Factory Order as order type on create order page", expected = "Order type should be Factory Order") 
	public void selectOrderType() throws Exception {
		OrderingStartHerePage.moveToCreateOrderPage();
	}
	
	@Test(dependsOnMethods = "selectOrderType")
	@Documentation(step = "Enter Driver data on create order page", expected = "Driver data should be provided on create order page.") 
	public void inputDriverData() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDriverPage.enterDriverData();
	}
	
	@Test(dependsOnMethods = "inputDriverData")
	@Documentation(step = "Select vehicle through fleet spec", expected = "Vehicle should be selected from fleet spec") 
	public void selectVehicleFleetSpec() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingVehiclePage.enterDetailsVehicleData();
	}
	
	@Test(dependsOnMethods = "selectVehicleFleetSpec") 
	@Documentation(step = "Select Lease as contracting option", expected = "The application should be able to select leasing as contracting option")
	public void selectBillingLease() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingBillingAndRegistrationPage.enterBillingAndRegistrationDetails();
	}
	
	@Test(dependsOnMethods = "selectBillingLease")
	@Documentation(step = "Verify if Element dealer is assigned", expected = "Element Dealer should be auto selected")
	public void selectDealer() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDealerPage.selectDealer();
	}

	@Test(dependsOnMethods = "selectDealer")
	@Documentation(step = "Validate the summary page ", expected = "Summary Page details should be validated")
	public void validateSummaryPage() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingSummaryPage.preSummaryPageActions(this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods="validateSummaryPage")
	@Documentation(step = "Submit the Order ", expected = "The Order should be submitted") 
	public void submitOrder() throws Exception {
		OrderingSummaryPage.clickSubmit();
		OrderingSummaryPage.confirmSubmit();
		OrderingSummaryPage.postSummaryPageActions(this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods="submitOrder")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly") 
	public void verifyLogOutFunctionality() throws Exception {
		OrderingHomePage.clickOnCloseSummaryPage();
		OrderingHomePage.verifyLogOutFunctionality();
	}

	@Test(dependsOnMethods="verifyLogOutFunctionality")
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void testLaunchBOURL() throws Throwable {
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Throwable {
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Validate Capping Team Member can only access On Order queue", expected = "Capping Team Member should only have access On Order queue")
	public void validateQueuesAccessible() throws Throwable {
		OrderingBOHomePage.selectSideMenuOption("Queues");
		OrderingBOQueuePage.verifyOnlyOnOrderQueueIsVisibleToCappingUser();
	}
	
	@Test(dependsOnMethods = "validateQueuesAccessible")
	@Documentation(step = "validate user is able to search order and navigate to it", expected = "User should be able to search order and navigate to it")
	public void searchAndNavigateToOrder() throws Throwable {
		OrderingBOQueuePage.verifyCappingUserIsAbleToSearchOrderAndNavigateToIt();
	}
	
	@Test(dependsOnMethods = "searchAndNavigateToOrder")	
	@Documentation(step = "Validate user is able to click on all headers", expected = "User should be able to click on all headers")
	public void clickOnAllHeadersAndCheckFieldsAreReadOnly() throws Throwable {
		OrderingBOOnOrderQueuePage.clickOnAllHeaders();
	}
	
	@Test(dependsOnMethods = "clickOnAllHeadersAndCheckFieldsAreReadOnly")
	@Documentation(step = "Scroll to Dealer Installed Options", expected = "User should be able to scroll to Dealer Installed Options")
	public void scrollToDealerInstalledOptions() throws Throwable {
		OrderingBOOnOrderQueuePage.validateUserCanScrollToDIOSection();
	}
	
	@Test(dependsOnMethods = "scrollToDealerInstalledOptions")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Throwable {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Throwable {
		CommonPage.testEnded();
	}
	
}