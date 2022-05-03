package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingManagerApprovalMaintenancePageEnum;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingManagerApprovalMaintenancePage;
import com.element.fleet.ordering.page.OrderingManagerApprovalQueuePage;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/*
 * PreCondition: Client 1750 should exist
 * Year: 2020
 * Make: Ford
 * Manufacturer: Ford
 * Approval Type: Sequential
 * Note: Approval rule Ford_2020
 * @lastModifiedBy usha
 */
public class Reg_MLO_MAM_Super_Admin_Verify_Search_Functionality extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({ "xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath",
			"orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username,
			String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime,
			ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}

	@Test(alwaysRun = true)
	@Documentation(step = "Create order with logger user", expected = "Orders with logger user is created and selects client from CSV file")
	public void createOrdersWithLoggerUser() throws Throwable {
		OrderingBOManagerOrderPreferencesPage.createOrderSkipBilling(this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "createOrdersWithLoggerUser")
	@Documentation(step = "Go to first record of MAM screen", expected = "First record should be selected")
	public void closeSummaryPageAndGoToMyMAM() throws Throwable {
		OrderingLoginPage.openFOApplication();
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingManagerApprovalQueuePage.orderingSideMenuOptionLoadedExternalUser();
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Maintenance");
		OrderingManagerApprovalMaintenancePage.waitForManagerApprovalMaintenanceToLoad();
		OrderingManagerApprovalMaintenancePage.clickOnTheFirstRecord();
	}

	@Test(dependsOnMethods = "closeSummaryPageAndGoToMyMAM")
	@Documentation(step = "Search by log number", expected = "Order should display")
	public void searchByLogNumber() throws Throwable {
		OrderingManagerApprovalMaintenancePage.enterLogNumber(CommonPage.getTestData("LoggerLogNumber"+1));
		OrderingManagerApprovalMaintenancePage.clickSearchButtonInMaintenance();
		OrderingManagerApprovalMaintenancePage.verifyLogNumber(CommonPage.getTestData("LoggerLogNumber"+1));
	}

	@Test(dependsOnMethods = "searchByLogNumber")
	@Documentation(step = "Search by Fleet and unit number", expected = "Given order should display")
	public void searchByFleetAndUnit() throws Throwable {
		String fleetNumber = CommonPage.getTestData("ClientNumber");
		String unitNumber = CommonPage.getElementOrderObject().getStartHereTabObject().getNewUnitNumber();
		OrderingManagerApprovalMaintenancePage.enterFleetNumber(fleetNumber);
		OrderingManagerApprovalMaintenancePage.enterUnitNumber(unitNumber);
		OrderingManagerApprovalMaintenancePage.clickSearchButtonInMaintenance();
		OrderingManagerApprovalMaintenancePage.verifyFleetNumber(fleetNumber);
		OrderingManagerApprovalMaintenancePage.verifyUnitNumber(unitNumber);
	}

	@Test(dependsOnMethods = "searchByFleetAndUnit")
	@Documentation(step = "Leave empty search fields", expected = "No results should display")
	public void invalidSearchEmptyFieds() throws Throwable {
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingManagerApprovalMaintenancePage.clickSearchButtonWithoutVerifyingAlerts();
		OrderingManagerApprovalMaintenancePage.verifyNoResultsFound();
	}

	@Test(dependsOnMethods = "invalidSearchEmptyFieds")
	@Documentation(step = "Enter only fleet number", expected = "No results should display")
	public void invalidSearchOnlyFleetNumber() throws Throwable {
		String fleetNumber = CommonPage.getTestData("ClientNumber");
		OrderingManagerApprovalMaintenancePage.enterFleetNumber(fleetNumber);
		OrderingManagerApprovalMaintenancePage.clickSearchButtonWithoutVerifyingAlerts();
		OrderingManagerApprovalMaintenancePage.verifyNoResultsFound();
		BrowserAction.clear(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_TEXTBOX_ID);
	}

	@Test(dependsOnMethods = "invalidSearchOnlyFleetNumber")
	@Documentation(step = "Enter only unit number", expected = "No results should display")
	public void invalidSearchOnlyUnitNumber() throws Throwable {
		String unitNumber = CommonPage.getElementOrderObject().getStartHereTabObject().getNewUnitNumber();
		OrderingManagerApprovalMaintenancePage.enterUnitNumber(unitNumber);
		OrderingManagerApprovalMaintenancePage.clickSearchButtonWithoutVerifyingAlerts();
		OrderingManagerApprovalMaintenancePage.verifyNoResultsFound();
		BrowserAction.clear(OrderingManagerApprovalMaintenancePageEnum.ORDERING_MANAGER_APPROVAL_MAINTENANCE_SCREEN_FLEET_TEXTBOX_ID);
	}

	@Test(dependsOnMethods = "invalidSearchOnlyUnitNumber")
	@Documentation(step = "Log out", expected = "Application should be logged out properly")
	public void verifySuperUserLogOut() throws Exception {
		OrderingManagerApprovalMaintenancePage.moveBackToManagerApprovalMaintenancePage();
		OrderingHomePage.verifyLogOutFunctionality();
	}

	@Test(alwaysRun = true, dependsOnMethods = "verifySuperUserLogOut")
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		CommonPage.testEnded();
	}

}