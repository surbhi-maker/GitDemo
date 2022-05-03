package com.element.fleet.ordering.regression;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
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
 * @lastModifiedBy Akshay Kandkonde
 */
public class Reg_EditOrder_Stock_AutoUnit_FleetSpec_DifferentDealer extends BaseWebDriver {
	
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
	@Documentation(step = "Enter the required client number to the application",expected = "The application display the required client") 
	public void changeClientBreakdown() throws Exception {
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
	}

	@Test(dependsOnMethods = "changeClientBreakdown")
	@Documentation(step = "Select order type on create order page", expected = "Order type should be Factory Order") 
	public void selectOrderType() throws Exception {
		OrderingStartHerePage.moveToCreateOrderPage();
	}

	@Test(dependsOnMethods = "selectOrderType")
	@Documentation(step = "Enter Driver data on create order page", expected = "Driver data should be provided on create order page.") 
	public void inputDriverData() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDriverPage.waitForDriverPage();
		OrderingDriverPage.enterDriverData();
	}
	
	@Test(dependsOnMethods = "inputDriverData")
	@Documentation(step = "Navigate to Vehicle tab, select build from scratch radio button,Select internal/External color", expected ="Application should be navigated to Vehicle tab and build from scratch and internal/External color should be selected") 
	public void goToVehiclePage() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingVehiclePage.enterDetailsVehicleData();
	}
	
	@Test(dependsOnMethods = "goToVehiclePage") 
	@Documentation(step = "Select Lease as contracting option", expected = "The application should be able to select leasing as contracting option")
	public void selectBillingLease() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingBillingAndRegistrationPage.enterBillingAndRegistrationDetails();
	}

	@Test(dependsOnMethods = "selectBillingLease")
	@Documentation(step = "Select from displayed dealer", expected = "Dealer should be select from displayed option")
	public void selectDealer() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDealerPage.selectDealer();
	}

	@Test(dependsOnMethods = "selectDealer")
	@Documentation(step = "Validate the summary page ", expected = "Summary Page details should be validated")
	public void validateSummaryPage() throws Exception {
		OrderingStartHerePage.clickSaveAndNextForStockWithElementLocate();
	}

	@Test(dependsOnMethods="validateSummaryPage")
	@Documentation(step = "Submit the Order ", expected = "The Order should be submitted") 
	public void submitOrder() throws Exception {
		OrderingSummaryPage.clickSubmit();
		OrderingSummaryPage.confirmSubmit();
		OrderingSummaryPage.postSummaryPageAction();
		OrderingSummaryPage.closeOrderSummary();
	}
	
	@Test(dependsOnMethods="submitOrder")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly") 
	public void verifyLogOutFunctionality() throws Exception {
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
	@Documentation(step = "Go to On Order Queue page", expected = "Application should load On Order Queue page")
	public void goToOnOrderQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoCreatedOrder();
	}
	
	@Test(dependsOnMethods = "goToOnOrderQueuePage")
	@Documentation(step = "Edit Approval section in BO", expected = "Approval information should be edited in BO ")
	public void addApprovalInformation() throws Throwable {
		OrderingBOOrdMaintPage.selectApprovalInformation();
	}	
	
	@Test(dependsOnMethods = "addApprovalInformation")
	@Documentation(step = "Edit General order section in BO", expected = "General order information should be edited in BO ")
	public void editGeneralOrderInformation() throws Throwable {
		OrderingBOOrdMaintPage.editGeneralInformation();
	}	
	
	@Test(dependsOnMethods = "editGeneralOrderInformation")
	@Documentation(step = "Edit Driver information section in BO", expected = "Driver Information should be edited in BO ")
	public void editDriverInformation() throws Throwable {
		OrderingBOOrdMaintPage.editDriverInfoInBO();
	}
	
	@Test(dependsOnMethods = "editDriverInformation")
	@Documentation(step = "Edit vehicle information section in BO", expected = "Vehicle Information should be edited in BO ")
	public void editVehicleInformation() throws Throwable {
		OrderingBOOrdMaintPage.editVehicleSpecificationInfo();
	}
	
	@Test(dependsOnMethods = "editVehicleInformation")
	@Documentation(step = "Add DIO information in DIO section in BO", expected = "DIO Information should be added in BO")
	public void addDIOInformation() throws Throwable {
		OrderingBOOrdMaintPage.addDIOInformation();
	}
	
	@Test(dependsOnMethods = "addDIOInformation")
	@Documentation(step = "Add Upfit in upfit information section in BO", expected = "Upfit Information should be added in BO")
	public void addUpfitInformation() throws Throwable {
		OrderingBOOrdMaintPage.addUpfitInformation();
	}
	
	@Test(dependsOnMethods = "addUpfitInformation")
	@Documentation(step = "Edit Billing information section in BO", expected = "Billing Information should be edited in BO")
	public void editBillingInformation() throws Throwable {
		OrderingBOOrdMaintPage.editBillingInformation();
	}
	
	@Test(dependsOnMethods = "editBillingInformation")
	@Documentation(step = "Edit Billing information section in BO", expected = "Title and License Information should be edited in BO")
	public void editTitleAndLicenseInformation() throws Throwable {
		OrderingBOOrdMaintPage.editTitleAndLicense();
	}
	
	@Test(dependsOnMethods = "editTitleAndLicenseInformation")
	@Documentation(step = "Edit Dealer information section in BO", expected = "Dealer Information should be edited in BO")
	public void editDealerInformation() throws Throwable {
		OrderingBOOrdMaintPage.enterDeliveringOrderingDealerCode();
	}
	
	@Test(dependsOnMethods = "editDealerInformation")
	@Documentation(step = "Verify General order section in BO", expected = "General order information should be verified in BO")
	public void verifyGeneralOrderInformation() throws Throwable {
		OrderingBOOrdMaintPage.clickSaveOrder();
		OrderingBOOrdMaintPage.verifyGeneralInformation();
	}

	@Test(dependsOnMethods = "verifyGeneralOrderInformation")
	@Documentation(step = "Verify Approval section in BO", expected = "Approval information should be verified in BO")
	public void verifyApprovalInformation() throws Throwable {
		OrderingBOOrdMaintPage.verifyApprovalInformation();
	}
	
	@Test(dependsOnMethods = "verifyApprovalInformation")
	@Documentation(step = "Verify Driver information section in BO", expected = "Driver information should be verified in BO")
	public void verifyDriverInformation() throws Throwable {
		OrderingBOOrdMaintPage.verifyEditedDriverInfoInBO();
	}
	
	@Test(dependsOnMethods = "verifyDriverInformation")
	@Documentation(step = "Verify vehicle information section in BO", expected = "Vehicle information should be verified in BO")
	public void verifyVehicleInformation() throws Throwable {
		OrderingBOOrdMaintPage.verifyVehicleSpecificationInfo(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "verifyVehicleInformation")
	@Documentation(step = "Verify DIO information section in BO", expected = "DIO information should be verified in BO")
	public void verifyDIOInformation() throws Throwable {
		OrderingBOOrdMaintPage.verifyDIOInformation();
	}
	
	@Test(dependsOnMethods = "verifyDIOInformation")
	@Documentation(step = "Verify upfit information section in BO", expected = "Upfit information should be verified in BO")
	public void verifyUpfitInformation() throws Throwable {
		OrderingBOOrdMaintPage.verifyUpfitInformation();
		OrderingBOOrdMaintPage.cancelPOAndVerify();
	}
	
	@Test(dependsOnMethods = "verifyUpfitInformation")
	@Documentation(step = "Verify Billing information section in BO", expected = "Billing information should be verified in BO")
	public void verifyBillingInformation() throws Throwable {
		OrderingBOOrdMaintPage.verifyBillingInformation();
	}
	
	@Test(dependsOnMethods = "verifyBillingInformation")
	@Documentation(step = "Verify Title and License information section in BO", expected = "Title and License information should be verified in BO")
	public void verifyTitleAndLicenseInformation() throws Throwable {
		OrderingBOOrdMaintPage.verifyTitleAndLicense();
	}
	
	@Test(dependsOnMethods = "verifyTitleAndLicenseInformation")
	@Documentation(step = "Verify Dealer information section in BO", expected = "Dealer information should be verified in BO")
	public void verifyDealerInformation() throws Throwable {
		OrderingBOOrdMaintPage.verifyDeliveringOrderingDealerCode();
	}
	
	@Test(dependsOnMethods = "verifyDealerInformation")
	@Documentation(step = "Edit General Order information section in BO and save", expected = "General Order Information section in BO should be edited and saved")
	public void addAndVerifyGeneralInfo() throws Throwable {
		OrderingBOOrdMaintPage.editAndVerifyGeneralInformation();
	}
	
	@Test(dependsOnMethods = "addAndVerifyGeneralInfo")
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