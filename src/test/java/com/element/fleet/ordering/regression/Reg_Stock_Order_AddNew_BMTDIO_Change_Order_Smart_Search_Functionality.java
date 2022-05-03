package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBODealerInstalledOptionsPage;
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
 * @lastModifiedBy sidheshwar
 */
public class Reg_Stock_Order_AddNew_BMTDIO_Change_Order_Smart_Search_Functionality extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "username", "boUserName" ,"orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username,String boUserName,String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("BoUserName",boUserName.equals("-")?username:boUserName);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}

	@Test(alwaysRun = true)
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void launchBOURL() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "launchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void loginBOApplication() throws Exception {
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}

	@Test(dependsOnMethods = "loginBOApplication")
	@Documentation(step = "Go to Business Maintained Tables page", expected = "Labels on Business Maintained Tables page should be verified")
	public void navigateToBusinessMaintainedTablesPage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLabelValidation();
	}

	@Test(dependsOnMethods = "navigateToBusinessMaintainedTablesPage")
	@Documentation(step = "Go to Dealer Installed Options page", expected = "Application should load Dealer Installed Options page")
	public void goToDealerInstalledOptionsPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Dealer Installed Options");
		OrderingBOBusinessMaintainedTablesPage.dealerInstalledOptionsPageLoaded();
		OrderingBODealerInstalledOptionsPage.addDIOWithBrandProviderName(true);		
	}

	@Test(dependsOnMethods = "goToDealerInstalledOptionsPage")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality1() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}

	@Test(dependsOnMethods = "verifyBOLogOutFunctionality1")
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void testLaunchURL() throws Exception {
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
	@Documentation(step = "Validate the summary page and DIO Details ", expected = "Summary Page DIO details should be validated")
	public void validateSummaryPageDIODetails() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingSummaryPage.waitForSummaryPage();
		OrderingSummaryPage.verifyDIODetailsOnSummaryPage();
		OrderingSummaryPage.preSummaryPageActions(this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods="validateSummaryPageDIODetails")
	@Documentation(step = "Submit the Order and validate DIO details after submission", expected = "The Order should be submitted and details validated") 
	public void submitOrderAndverifyDIODetails() throws Exception {
		OrderingSummaryPage.clickSubmit();
		OrderingSummaryPage.confirmSubmit();
		OrderingSummaryPage.postSummaryPageActions(this.getClass().getSimpleName());
		OrderingSummaryPage.verifyDIODetailsOnPostSummaryPage();
	}

	@Test(dependsOnMethods="submitOrderAndverifyDIODetails")
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
	@Documentation(step = "Go to On Order Queue page", expected = "Application should load On Order Queue page")
	public void goToOnOrderQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoCreatedOrder();
	}

	@Test(dependsOnMethods = "goToOnOrderQueuePage")
	@Documentation(step = "Go to On Order Queue page", expected = "Application should load On Order Queue page")
	public void validateDIOTabInfo() throws Throwable {
		OrderingBOOrdMaintPage.verifyBODIOInfo();
	}

	@Test(dependsOnMethods = "validateDIOTabInfo")
	@Documentation(step = "Go to Dealer Installed Options page and verify the edited  DIO Details", expected = "Application should load Dealer Installed Options page and details shouldnt match")
	public void verifyEditedDetailsInBO() throws Throwable {
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOHomePage.warningPopUp();
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Dealer Installed Options");
		OrderingBOBusinessMaintainedTablesPage.searchBMTDIO("Option Code", CommonPage.getTestData("OptionCode"));
		OrderingBOBusinessMaintainedTablesPage.verifyEditedDioDetailsInBO();
	}

	@Test(dependsOnMethods="verifyEditedDetailsInBO")
	@Documentation(step = "Delete DIO from BMT ", expected = "Dio successfully deleted")
	public static void deleteBMTDIORecordBO() throws Throwable {
		OrderingBODealerInstalledOptionsPage.deleteDIO();
	}

	@Test(dependsOnMethods="deleteBMTDIORecordBO")
	@Documentation(step = "Add dio and verify fields are editable", expected = "Dio successfully added and verified editable fields")
	public static void addNewDio() throws Throwable {
		OrderingBOOrdMaintPage.addAndEditDio();
	}	

	@Test(dependsOnMethods = "addNewDio")
	@Documentation(step = "Check error if there is any soft error", expected = "Resolve all the soft errors")
	public void resolveAllTheSoftError() throws Throwable {
		OrderingBOOnOrderQueuePage.resolveSoftErrors();
	}
	
	@Test(dependsOnMethods = "resolveAllTheSoftError")	
	@Documentation(step = "Go back to the vehicle queues page and download pdf", expected = "Application should navigate to the vehicle queues page and pdf should be downloaded")
	public void goToVehicleQueuePage() throws Throwable {
		OrderingBOOnOrderQueuePage.approveOrderAndDownloadPDF(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "goToVehicleQueuePage")
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
