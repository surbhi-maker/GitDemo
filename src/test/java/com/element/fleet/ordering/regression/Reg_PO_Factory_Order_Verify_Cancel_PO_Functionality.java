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
public class Reg_PO_Factory_Order_Verify_Cancel_PO_Functionality extends BaseWebDriver {

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
	@Documentation(step = "Add an upfit and validate CC field and Suppress PO switch", expected = "Upfit shoud be added and Suppress PO Switch and CC fields should be present")
	public void verifyUpfitSuppressPOSwitchAndCCField() throws Throwable {
		OrderingBOOrdMaintPage.addAdhocUpfit();
		OrderingBOOrdMaintPage.clickSave();
		OrderingBOOrdMaintPage.goToUpfitSection();
		OrderingBOOrdMaintPage.isUpfitLineItemAdded();
		OrderingBOOrdMaintPage.clickUpfitLineItem();
		OrderingBOOrdMaintPage.validatePresenceOfSuppressPOSwitchAndCCField();
	}
	
	@Test(dependsOnMethods = "verifyUpfitSuppressPOSwitchAndCCField")
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
	@Documentation(step = "validate if Send PO Now Button is displayed for approved order", expected = "Send PO Now button should not be displayed util order is acknowledged")
	public void validateSendPONowButton() throws Exception {
		OrderingBOOrdMaintPage.goToUpfitSection();
		OrderingBOOrdMaintPage.clickUpfitLineItem();
		OrderingBOOrdMaintPage.verifySendPONowButtonIsNotDisplayed();
	}
	
	@Test(dependsOnMethods = "validateSendPONowButton")
	@Documentation(step = "acknowledge the Order", expected = "User should be able to Acknowledge the Order")
	public void acknowledgeOrder() throws Exception {
		OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("New Vehicle Specification");
		OrderingBOOrdMaintPage.clickOnAcknowledgeButton();
	}
	
	@Test(dependsOnMethods = "acknowledgeOrder")	
	@Documentation(step = "Click Send PO Now button and Send PO Email", expected = "User should be able to send Po Email")
	public void clickSendPONowAndSendPOEmail() throws Exception {
		OrderingBOOrdMaintPage.getDataFromBoMaintPageForEmailPopupValidation();
		OrderingBOOrdMaintPage.setEmailSubjectLine("Cancel PO");
		OrderingBOOrdMaintPage.clickSendPONowButton();
		OrderingBOOrdMaintPage.isEmailPopupDisplayed();
		OrderingBOOrdMaintPage.validateCCFieldIsEditable();
		OrderingBOOrdMaintPage.enterAdditionalText();
		OrderingBOOrdMaintPage.clickSendButtonOnEmailPopup((JavascriptExecutor)driver);
		OrderingBOOrdMaintPage.goToUpfitSection();
		OrderingBOOrdMaintPage.clickUpfitLineItem();
		OrderingBOOrdMaintPage.validateSendPONowButtonChange();
	}
	
	@Test(dependsOnMethods = "clickSendPONowAndSendPOEmail")	
	@Documentation(step = "For PO Cancellation validate Details on Email Popup with order details", expected = "For PO Cancellation Email Popup Details should be as expected")
	public void validateEmailPopupDetailsForPOCancellation() throws Exception {
		OrderingBOOrdMaintPage.clickCancelPOButton();
		OrderingBOOrdMaintPage.confirmCancellationPopup();
		OrderingBOOrdMaintPage.confirmSendPOCancellation();
		OrderingBOOrdMaintPage.isEmailPopupDisplayed();
		OrderingBOOrdMaintPage.setCurrentPOStatus("Purchase Order Cancelled");
		OrderingBOOrdMaintPage.validateDetailsOnEmailPopup();
	}
	
	@Test(dependsOnMethods = "validateEmailPopupDetailsForPOCancellation")	
	@Documentation(step = "Validate CC field in email is editable", expected = "CC field in email popup should be editable")
	public void validateCCFieldIsEditable() throws Exception {
		OrderingBOOrdMaintPage.validateCCFieldIsEditable();
	}
	
	@Test(dependsOnMethods = "validateCCFieldIsEditable")	
	@Documentation(step = "Validate user is able to enter additional details in email and check additional details limit", expected = "User should be able to enter additional details and additional deail limit should be updated accordingly")
	public void validateAdditionalTextAndLimit() throws Exception {
		OrderingBOOrdMaintPage.enterAdditionalText();
		OrderingBOOrdMaintPage.validateAdditionalTextFieldLimit();
	}
	
	@Test(dependsOnMethods = "validateAdditionalTextAndLimit")	
	@Documentation(step = "Validate user is able to download the attachment", expected = "User should be able to download the attachment")
	public void downloadAttachment() throws Exception {
		OrderingBOOrdMaintPage.clickAndDownloadAttachment();
	}
	
	@Test(dependsOnMethods = "downloadAttachment")	
	@Documentation(step = "Validate the details in PDF Attachment", expected = "PDF Attachment should have expected details")
	public void validateAttachmentPDF() throws Exception {
		OrderingBOOrdMaintPage.validateAttachmentPDF();
	}
	
	@Test(dependsOnMethods = "validateAttachmentPDF")	
	@Documentation(step = "Validate user is able to send cancellation Email By clicking on Send button", expected = "Cancellation email should be sent on clicking Send button")
	public void validateEmailIsSentOnClickingSendButton() throws Exception {
		OrderingBOOrdMaintPage.clickSendButtonOnEmailPopup((JavascriptExecutor)driver);
	}
	
	@Test(dependsOnMethods = "validateEmailIsSentOnClickingSendButton")	
	@Documentation(step = "Validate PO Status", expected = "PO Status should change to Purchase Order Cancelled")
	public void validatePOStatusChangesOnSendingPO() throws Exception {
		OrderingBOOrdMaintPage.goToUpfitSection();
		OrderingBOOrdMaintPage.clickUpfitLineItem();
		OrderingBOOrdMaintPage.isEmailPopupClosed();
		OrderingBOOrdMaintPage.validateCurrentPOStatus("Purchase Order Cancelled");
	}
	
	@Test(dependsOnMethods = "validatePOStatusChangesOnSendingPO")
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