package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingBOOrdMaintPage;
import com.element.fleet.ordering.page.OrderingBOOrderTransmissionQueuePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingBillingAndRegistrationPage;
import com.element.fleet.ordering.page.OrderingCommonPage;
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
 * @lastModifiedBy akshay
 */
public class Reg_OEM_GM_InBound extends BaseWebDriver {
	
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
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void launchBOURL() throws Exception {
		OrderingLoginPage.openBOApplication();
	}
	
	@Test(dependsOnMethods = "launchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void loginBOApplication() throws Exception {
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "loginBOApplication")
	@Documentation(step = "Go to scheduler BMT", expected = "Application should be navigated to scheduler BMT")
	public void goToScheduler() throws Exception {
		OrderingBOQueuePage.goToBusinessMaintainedTable();
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Scheduler");
		OrderingBOBusinessMaintainedTablesPage.verifySchedulerTitle();
	}
	
	@Test(dependsOnMethods = "goToScheduler")
	@Documentation(step = "Search process name and set as InActive status", expected = "Process name should be searched and status should be InActive")
	public void searchProcessAndChangeStatusToInActive() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.searchProcessAndSelectCheckbox();
		OrderingBOBusinessMaintainedTablesPage.changeProcessStatusToInActive();
	}
	
	@Test(dependsOnMethods = "searchProcessAndChangeStatusToInActive")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void BOLogOutFunctionality() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "BOLogOutFunctionality")
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
	@Documentation(step = "Validate the summary page ", expected = "Summary Page details should be validated")
	public void validateSummaryPage() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
	}

	@Test(dependsOnMethods="validateSummaryPage")
	@Documentation(step = "Submit the Order ", expected = "The Order should be submitted") 
	public void submitOrder() throws Exception {
		OrderingSummaryPage.clickSubmit();
		OrderingSummaryPage.confirmSubmit();
		OrderingCommonPage.checkAlertPopUp();
		OrderingSummaryPage.waitForPopUpResultBox();
		OrderingSummaryPage.verifySuccessfulSubmissionPopUp();
		OrderingSummaryPage.waitForSummaryPage();
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
	@Documentation(step = "Go to On Order Queue page", expected = "Application should load On Order Queue page")
	public void goToOnOrderQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoCreatedOrder();
		OrderingBOOrdMaintPage.changeDOEToggleAndVerify();
	}
	
	@Test(dependsOnMethods = "goToOnOrderQueuePage")
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
	@Documentation(step = "Go to Scheduler BMT", expected = "Scheuler BMT should be opened")
	public void goToSchedulerPage() throws Throwable {
		OrderingBOOrdMaintPage.goBackToQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOBusinessMaintainedTablesPage.goToSchedulerBMT();
	}
	
	@Test(dependsOnMethods = "goToSchedulerPage")
	@Documentation(step = "Open Process and update the cron", expected = "Cron pattern should be updated")
	public void goToSchedulerAndChangeCron() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.goToSchedulerAndChangeCron();
	}
	
	@Test(dependsOnMethods = "goToSchedulerAndChangeCron")
	@Documentation(step = "Search process name and set status as Active", expected = "Process should be searched and status should be Active")
	public void goToSchedulerAndChangeProcessStatusToActive() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnCancel();
		OrderingBOBusinessMaintainedTablesPage.searchProcessAndSelect();
		OrderingBOBusinessMaintainedTablesPage.changeProcessStatusToActive();
	}
	
	@Test(dependsOnMethods = "goToSchedulerAndChangeProcessStatusToActive")	
	@Documentation(step = "copy Ack file to Win Scp server", expected = "Ack file should be copied")
	public void copyAckFileToWinScpServer() throws Throwable {
		OrderingBOOrdMaintPage.verifyOrderStatus();
		OrderingBOOrderTransmissionQueuePage.copyACKFileToWinScpServer();
	}
	
	@Test(dependsOnMethods = "copyAckFileToWinScpServer")	
	@Documentation(step = "Change ACK cron pattern", expected = "ACK cron pattern should be changed")
	public void changeACKCronPattern() throws Throwable {
		OrderingBOQueuePage.goToBusinessMaintainedTable();
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Scheduler");
		OrderingBOBusinessMaintainedTablesPage.searchBy("Process Name", CommonPage.getTestData("ACKProcessName"));
		OrderingBOBusinessMaintainedTablesPage.selectProcessNameCheckBox(CommonPage.getTestData("ACKProcessName"));
		OrderingBOBusinessMaintainedTablesPage.goToSchedulerAndChangeACKCron();
	}
	@Test(dependsOnMethods = "changeACKCronPattern")	
	@Documentation(step = "Go to scheduler and change Acknowledge process file status to Active", expected = "Acknowledge process file status should be changed to Active")
	public void goToSchedulerAndChangeAckFileStatusToActive() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.clickOnCancel();
		OrderingBOBusinessMaintainedTablesPage.searchBy("Process Name", CommonPage.getTestData("ACKProcessName"));
		OrderingBOBusinessMaintainedTablesPage.selectProcessNameCheckBox(CommonPage.getTestData("ACKProcessName"));
		OrderingBOBusinessMaintainedTablesPage.changeACKProcessStatusToActive();
	}
	
	@Test(dependsOnMethods = "goToSchedulerAndChangeAckFileStatusToActive")	
	@Documentation(step = "Go to order maintenance page and verify order status", expected = "Order status should be verified on order maintenance page")
	public void goToAcknowledgeQueuePage() throws Throwable {
		OrderingBOOrdMaintPage.verifyOrderStatus();
		//OrderingBOOrderTransmissionQueuePage.verifyRecordStatusInDB();
	}
	
	@Test(dependsOnMethods = "goToAcknowledgeQueuePage")	
	@Documentation(step = "Click on Acknowledge button and click on Save and verify", expected = "Order status should be changed to Acknowledge")
	public void goToMaintenancePageChangeACKStatusAndVerify() throws Throwable {
		OrderingBOOrdMaintPage.clickOnAcknowledge();
		OrderingBOOrdMaintPage.verifyAcknowledgeStatusForOEM();
	}
	
	@Test(dependsOnMethods = "goToMaintenancePageChangeACKStatusAndVerify")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		OrderingBOOrderTransmissionQueuePage.replaceLogNumberTextInACKFile(CommonPage.getTestData("Manufacturer"));
		CommonPage.testEnded();
	}
}
