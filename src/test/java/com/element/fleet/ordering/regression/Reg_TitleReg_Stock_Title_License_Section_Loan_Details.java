package com.element.fleet.ordering.regression;

import com.element.fleet.ordering.page.*;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy sidheshwar
 */
public class Reg_TitleReg_Stock_Title_License_Section_Loan_Details extends BaseWebDriver {
	
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
		OrderingDriverPage.enterDriverData();
	}

	@Test(dependsOnMethods = "inputDriverData")
	@Documentation(step = "Select vehicle through fleet spec", expected = "Vehicle should be selected from fleet spec")
	public void selectVehicleFleetSpec() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingVehiclePage.enterDetailsVehicleData();
	}

	@Test(dependsOnMethods = "selectVehicleFleetSpec")
	@Documentation(step = "Select Lease as contract typr and verify all T&L details", expected = "Title & License Section with Vehicle Text is displayed")
	public void verifyVehicleTextAndTLDetails() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingBillingAndRegistrationPage.enterBillingAndRegistrationDetails();
		OrderingBillingAndRegistrationPage.assertAllTitleAndLicenseDetails();
	}

	@Test(dependsOnMethods = "verifyVehicleTextAndTLDetails")
	@Documentation(step = "Verify if Element dealer is assigned", expected = "Element Dealer should be auto selected")
	public void selectDealer() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDealerPage.selectDealer();
	}

	@Test(dependsOnMethods = "selectDealer")
	@Documentation(step = "Verify All the details of title & License sections on Summary page ", expected = "All the details should be matched ")
	public void verifyAllDetailsOfTitleAndLicenseOnSummaryPage() throws Throwable {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingSummaryPage.verifyAllTheDetailsOfTitleRegistrationPage();
	}

	@Test(dependsOnMethods = "verifyAllDetailsOfTitleAndLicenseOnSummaryPage")
	@Documentation(step = "Download the Pres summary pdf and validate the T&L details ", expected = "All the details should be present in PDF")
	public void verifyAllDetailsOfTitleAndLicenseInPrePDF() throws Throwable {
		OrderingSummaryPage.preSummaryPageActions(this.getClass().getSimpleName());
		OrderingSummaryPage.verifyTAndLDetailsInPDF();
		OrderingSummaryPage.verifyLogNumberInPrePDF();
	}
	
	@Test(dependsOnMethods="verifyAllDetailsOfTitleAndLicenseInPrePDF")
	@Documentation(step = "Submit the Order ", expected = "All Title& License Section details on Summary Page should matched on post submition")
	public void verifyAllDetailsOfSummaryPageAfterPostSubmission() throws Exception {
		OrderingSummaryPage.clickSubmit();
		OrderingSummaryPage.confirmSubmit();
		OrderingSummaryPage.postSummaryPageActions(this.getClass().getSimpleName());
		OrderingSummaryPage.verifyAllTheDetailsOfTitleRegistrationPage();
	}

	@Test(dependsOnMethods = "verifyAllDetailsOfSummaryPageAfterPostSubmission")
	@Documentation(step = "Download the Post summary pdf and validate the T&L details ", expected = "All the details should be present in PDF")
	public void verifyAllDetailsOfTitleAndLicenseInPostPDF() throws Throwable {
		OrderingSummaryPage.verifyTAndLDetailsInPDF();
		OrderingSummaryPage.verifyLogNumberInPostPDF();
	}
	
	@Test(dependsOnMethods="verifyAllDetailsOfTitleAndLicenseInPostPDF")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly")
	public void verifyLogOutFunctionality() throws Exception {
		OrderingHomePage.clickOnCloseSummaryPage();
		OrderingHomePage.verifyLogOutFunctionality();
	}

	@Test(dependsOnMethods ="verifyLogOutFunctionality")
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
	@Documentation(step = "Go to Title Registration Tab & verify", expected = "Title & License sections details from FO should match with BO Details")
	public void verfiyDetailsOfFOInBackOffice() throws Exception {
		OrderingBOOrdMaintPage.goToTitleAndRegistrationTab();
		OrderingBOOrdMaintPage.verifyAllTheDetailsOfTitleRegistrationPage();
	}

	@Test(dependsOnMethods = "verfiyDetailsOfFOInBackOffice")
	@Documentation(step = "verify Title Information Section can be Edited", expected = "Title Information Section can be Edited")
	public void verifyTitleInformationSectionCanBeEditedInBO() throws Exception {
		OrderingBOOrdMaintPage. editTitleInfo();
		OrderingBOOrdMaintPage. navigateAndComebackToTitleRegisrtationTab();
		OrderingBOOrdMaintPage.verifyEditedTitleInfoMatches();
	}

	@Test(dependsOnMethods = "verifyTitleInformationSectionCanBeEditedInBO")
	@Documentation(step = "verify Registration Information Section can be Edited", expected = "Registration Information Section can be Edited")
	public void verifyRegistrationInformationSectionCanBeEditedInBO() throws Exception {
		OrderingBOOrdMaintPage.editRegistrationInfo();
		OrderingBOOrdMaintPage.navigateAndComebackToTitleRegisrtationTab();
		OrderingBOOrdMaintPage.verifyEditedRegistrationInfoMatches();

	}

	@Test(dependsOnMethods = "verifyRegistrationInformationSectionCanBeEditedInBO")
	@Documentation(step = "verify Lienholder information Section can be Edited", expected = "Lienholder information Section can be Edited")
	public void verifyLienholderInformationSectionCanBeEditedInBO() throws Exception {
		OrderingBOOrdMaintPage. editLienholderInfo();
		OrderingBOOrdMaintPage. navigateAndComebackToTitleRegisrtationTab();
		OrderingBOOrdMaintPage.verifyEditedLienholderInfoMatches();
	}

	@Test(dependsOnMethods = "verifyLienholderInformationSectionCanBeEditedInBO")
	@Documentation(step = "Search Log ID & Go to Title Registration Tab & verify the vehicle text & saved details", expected = "Title Owner /Registered Owner/Lienholder Details with applicable Title/Reg Codes data & Vehicle Text should match with Edited Details")
	public void  verifyVehicleWillBeTitledAndRegisteredInStatementIsPresentWithStateNameForUSclientInBO() throws Throwable {
		OrderingBOOrdMaintPage.goBackToQueuesPage();
		OrderingBOQueuePage.gotoCreatedOrder();
		OrderingBOOrdMaintPage.goToTitleAndRegistrationTab();
		OrderingBOOrdMaintPage.verifyAllTheEditedDetailsAndVehicleTextOfTitleRegistrationPage();
	}
	@Test(dependsOnMethods = "verifyVehicleWillBeTitledAndRegisteredInStatementIsPresentWithStateNameForUSclientInBO")
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

