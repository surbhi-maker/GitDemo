package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingBOOrdMaintPage;
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
 * @lastModifiedBy damodhar
 */
public class Reg_PO_Factory_Order_With_Upfitter_Does_Not_Have_Communication_Preference extends BaseWebDriver {

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
	public void testLaunchBOURL() throws Exception {
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Exception {
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}

	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Go to On BMT page and hold Sweeper Scheduler", expected = "User should be able to hold Upfit PO Sweeper Scheduler")
	public void holdUpFitPOSweeper() throws Exception {
		OrderingBOHomePage.orderingBOSideMenuLoaded();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Scheduler");
		OrderingBOBusinessMaintainedTablesPage.searchScheduler("Upfit Purchase Orders");
		OrderingBOBusinessMaintainedTablesPage.holdScheduler();
	}
	
	@Test(dependsOnMethods = "holdUpFitPOSweeper")
	@Documentation(step = "Go to On Order Queue page and filter out the log number", expected = "Application should load the ordermaintenance screen for the log number filtered")
	public void goToOnOrderQueuePage() throws Exception {
		OrderingBOQueuePage.gotoCreatedOrder();
		OrderingBOOrdMaintPage.waitForOrdMaintPage();
	}
	
	@Test(dependsOnMethods = "goToOnOrderQueuePage")
	@Documentation(step = "Navigate to Upfit section and verify Suppress PO Switch on Upfit Add PO window", expected = "Suppress PO Switch should be present on Upfit Add PO Window")
	public void verifyUpfitSuppressPOSwitch() throws Exception {
		OrderingBOOrdMaintPage.goToUpfitSection();
		OrderingBOOrdMaintPage.clickAddPO();
		OrderingBOOrdMaintPage.validatePresenceOfSuppressPOSwitchOnUpfitAddPOWindow();
	}
	
	@Test(dependsOnMethods = "verifyUpfitSuppressPOSwitch")
	@Documentation(step = "Add an upfit", expected = "Upfit shoud be added")
	public void addAdhocUpfit() throws Throwable {
		OrderingBOOrdMaintPage.addAdhocUpfit();
		OrderingBOOrdMaintPage.clickSave();
		OrderingBOOrdMaintPage.goToUpfitSection();
		OrderingBOOrdMaintPage.isUpfitLineItemAdded();
		OrderingBOOrdMaintPage.clickUpfitLineItem();
	}
	
	@Test(dependsOnMethods = "addAdhocUpfit")
	@Documentation(step = "Check error if there is any soft error", expected ="Resolve all the soft errors")
	public void resolveAllTheSoftError() throws Throwable {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("General Order");
		OrderingBOOnOrderQueuePage.resolveSoftErrors();
	}

	@Test(dependsOnMethods = "resolveAllTheSoftError")	
	@Documentation(step = "Approve Order", expected = "Order should be approved")
	public void approveOrder() throws Exception {
		OrderingBOOrdMaintPage.enterFONumberAndDate();
		OrderingBOOnOrderQueuePage.approveOrder();
	}
	
	@Test(dependsOnMethods = "approveOrder")
	@Documentation(step = "acknowledge the Order", expected = "User should be able to Acknowledge the Order")
	public void acknowledgeOrder() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("New Vehicle Specification");
		OrderingBOOrdMaintPage.clickOnAcknowledgeButton();
	}
	
	@Test(dependsOnMethods = "acknowledgeOrder")
	@Documentation(step = "Validate Suppress PO Switch and Send PO Now Button", expected = "Suppress PO Switch should be selected and Send PO Now button should not be displayed")
	public void validateSuppressPOAndSendPONow() throws Exception {
		OrderingBOOrdMaintPage.goToUpfitSection();
		OrderingBOOrdMaintPage.clickUpfitLineItem();
		OrderingBOOrdMaintPage.validateSuppressPOSwitchIsNotSelected();
		OrderingBOOrdMaintPage.validateSendPONowButtonIsDisplayed();
	}
	
	@Test(dependsOnMethods = "validateSuppressPOAndSendPONow")
	@Documentation(step = "Validate user is able to toggle Suppress PO switch and valdiate warning message", expected = "User should be able to toggle suppress PO switch and warning message should be displayed as expected")
	public void toggleSuppressPOAndValidateWarningMessage() throws Exception {
		OrderingBOOrdMaintPage.toggleSuppressPO();
		OrderingBOOrdMaintPage.validateSuppressPOWarningPopupDisplayed();
		OrderingBOOrdMaintPage.validateSuppressPOWarningMessage("Preference Do Not Exist");
		OrderingBOOrdMaintPage.clickYesSuppressPOWarningPopup();
		OrderingBOOrdMaintPage.validateSendPONowButtonIsNotDisplayed();
	}

	@Test(dependsOnMethods = "toggleSuppressPOAndValidateWarningMessage")
	@Documentation(step = "Validate user is able to toggle Suppress PO switch and Send PO email", expected = "User should be able to toggle suppress PO switch and end PO email")
	public void toggleSuppressPOAndSendPO() throws Exception {
		OrderingBOOrdMaintPage.toggleSuppressPO();
		OrderingBOOrdMaintPage.validateSendPONowButtonIsDisplayed();
		OrderingBOOrdMaintPage.clickSendPONowButton();
		OrderingBOOrdMaintPage.isEmailPopupDisplayed();
		OrderingBOOrdMaintPage.clickSendButtonOnEmailPopup((JavascriptExecutor)driver);
	}
	
	@Test(dependsOnMethods = "toggleSuppressPOAndSendPO")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
}
