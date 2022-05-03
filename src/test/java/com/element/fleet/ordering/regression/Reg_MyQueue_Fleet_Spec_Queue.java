package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingVehicleConfigPriceAndConfigPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/*
 * @lastModifiedBy hjimenez
 */
public class Reg_MyQueue_Fleet_Spec_Queue extends BaseWebDriver {
	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
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
	@Documentation(step = "log in to FO ", expected = "FO Home page should display")
	public void logInToFO() throws Throwable {
		OrderingLoginPage.openFOApplication();
		OrderingHomePage.waitForHomePage();
	}
	
	@Test(dependsOnMethods = "logInToFO")
	@Documentation(step = "Change client", expected = "Given client should be selected")
	public void changeClient() throws Exception {
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
	}
	
	@Test(dependsOnMethods = "changeClient")
	@Documentation(step = "Move to Fleet Spec Tab", expected = "Price and Config Spec should be created")
	public void navigateToFleetSpecTab() throws Throwable {
		OrderingHomePage.clickFleetSpecTab();
	}
	
	@Test(dependsOnMethods = "navigateToFleetSpecTab")
	@Documentation(step = "Validate Filters are present on tab", expected = "Expected Filters should be present on tab")
	public void validateExpectedFleetSpecFilters( ) throws Throwable {
		OrderingHomePage.clickOnHoldSpecsAdvancedSearch();
		OrderingHomePage.verifySearchInputsOnFleetSpecTab();
	}
	
	@Test(dependsOnMethods = "validateExpectedFleetSpecFilters")
	@Documentation(step = "Validate Search Functionality", expected = "Search fields display should  work as expected")
	public void validateSearchFunctionality() throws Exception {
		int valuesToBeRemoved = Integer.parseInt(CommonPage.getTestData("ValuesToBeRemoved"));
		OrderingHomePage.clearFilters();
		OrderingHomePage.verifySearchFunctionalityOnHoldSpecs(valuesToBeRemoved);
	}
	
	@Test(dependsOnMethods = "validateSearchFunctionality")
	@Documentation(step = "Validate Columns in table from on hold specs tab", expected = "Expected columns should be displayed")
	public void validateFleetSpecsTableColumns() throws Exception {
		String [] expectedHeaders = CommonPage.getTestData("expectedHeaders").split("\\|");
		int startIndex = Integer.parseInt(CommonPage.getTestData("ColumnStartValue"));
		int lastIndex = Integer.parseInt(CommonPage.getTestData("ColumnLastValue"));
		OrderingHomePage.verifyColumnHeadersValues(expectedHeaders, startIndex, lastIndex);
	}
	
	@Test(dependsOnMethods = "validateFleetSpecsTableColumns")
	@Documentation(step = "Verify CSV export functionality", expected = "CSV should be downloaded")
	public void verifyExportFunctionality() throws Throwable {
		OrderingHomePage.verifyExportFuntionality();
	}
	
	@Test(dependsOnMethods = "verifyExportFunctionality")
	@Documentation(step = "Verify columns in downloaded CSV file", expected = "CSV downloaded should contain expected columns")
	public void verifyCSVFileDownloaded() throws Throwable {
		String fileName = "FleetSpec.csv";
		String []expectedCSVHeaders = CommonPage.getTestData("CSVHeaders").split("\\|");
		OrderingHomePage.verifyHeadersFromExportCSV(fileName, expectedCSVHeaders);
	}
	
	@Test(dependsOnMethods = "verifyCSVFileDownloaded")
	@Documentation(step = "Create Fleet Spec", expected = "Fleet Spec Should be Created")
	public void createFleetSpec() throws Throwable {
		int specOrders = Integer.parseInt(CommonPage.getTestData("SpecCount"));
		OrderingVehicleConfigPriceAndConfigPage.addNewSpec("Add Fleet Specification", specOrders);
	}
	
	@Test(dependsOnMethods = "createFleetSpec")
	@Documentation(step = "Select pending client approval from submenu", expected = "Pending Client Approval option should be selected")
	public void changeOrdersToPendingApproval( ) throws Throwable {
		OrderingVehicleConfigPriceAndConfigPage.changeFleetSpecsToPendingApproval("Fleet Spec Name");
	}
	
	@Test(dependsOnMethods = "changeOrdersToPendingApproval")
	@Documentation(step = "Verify spec created is displayed on price and spec tab", expected = "Price and config spec should display on table")
	public void verifySpecIsDisplayedOnPriceConfigTab() throws Throwable {
		OrderingHomePage.goBackToHomePage();
		OrderingHomePage.clickFleetSpecTab();
		OrderingHomePage.searchFleetSpecName(CommonPage.getTestData("SpecName"));
	}
	
	@Test(dependsOnMethods = "verifySpecIsDisplayedOnPriceConfigTab")
	@Documentation(step = "Approve Fleet Spec Created by superuser", expected = "Price and Config Spec should be approved")
	public void approveSinglePriceAndConfigSpec() throws Throwable {
		OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
		OrderingHomePage.approveSingleSpec();
		OrderingHomePage.verifyFleetSpecIsNotDisplayed(CommonPage.getTestData("SpecName"));
	}
	
	@Test(dependsOnMethods = "approveSinglePriceAndConfigSpec")
	@Documentation(step = "Create fleet spec to be approved for External Approval", expected = "Fleet Spec should be created")
	public void createFleetSpecForExternalApproval() throws Throwable {
		int specOrders = Integer.parseInt(CommonPage.getTestData("SpecCount"));
		OrderingVehicleConfigPriceAndConfigPage.addNewSpec("Add Fleet Specification", specOrders);
		OrderingVehicleConfigPriceAndConfigPage.changeFleetSpecsToPendingApproval("Fleet Spec Name");
	}
	
	@Test(dependsOnMethods = "createFleetSpecForExternalApproval")
	@Documentation(step = "Log Out From FO ", expected = "User should be log out correctly")
	public void logOut() throws Throwable {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "logOut")
	@Documentation(step = "Approve Fleet Spec with External User", expected = "Fleet Spec Created should be approved")
	public void approveFleetSpecWithExternalUser() throws Throwable {
		String[] userIDs = CommonPage.getTestData("UserIDs").split("\\|");
		OrderingLoginPage.openOrderingWithExternalUser(userIDs[1]);
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickFleetSpecTab();
		OrderingHomePage.clearFilters();
		OrderingHomePage.searchFleetSpecName(CommonPage.getTestData("SpecName"));
		OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
		OrderingHomePage.approveSingleSpec();
		OrderingHomePage.verifyFleetSpecIsNotDisplayed(CommonPage.getTestData("SpecName"));
	}
	
	@Test(dependsOnMethods = "approveFleetSpecWithExternalUser")
	@Documentation(step = "Log Out From FO ", expected = "User should be log out correctly")
	public void logOutAsExternalUser() throws Throwable {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "logOutAsExternalUser")
	@Documentation(step = "Create fleet spec to be declined for External Approval", expected = "Fleet Spec should be created")
	public void createFleetSpecForExternalApprovalToDecline() throws Throwable {
		CommonPage.loadXMLParameterToTestData("Username", CommonPage.getTestData("OrderLogger"));
		OrderingLoginPage.openFOApplication();
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
		int specOrders = Integer.parseInt(CommonPage.getTestData("SpecCount"));
		OrderingVehicleConfigPriceAndConfigPage.addNewSpec("Add Fleet Specification", specOrders);
		OrderingVehicleConfigPriceAndConfigPage.changeFleetSpecsToPendingApproval("Fleet Spec Name");
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "createFleetSpecForExternalApprovalToDecline")
	@Documentation(step = "Log in to FO with expecter external approval and decline fleet spec created", expected = "Fleet spec created should be declined")
	public void declineFleetSpecWithExternalUser() throws Throwable {
		String[] userIDs = CommonPage.getTestData("UserIDs").split("\\|");
		OrderingLoginPage.openOrderingWithExternalUser(userIDs[1]);
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickFleetSpecTab();
		OrderingHomePage.clearFilters();
		OrderingHomePage.searchFleetSpecName(CommonPage.getTestData("SpecName"));
		OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
		OrderingHomePage.rejectSingleSpec();
		OrderingHomePage.verifyFleetSpecIsNotDisplayed(CommonPage.getTestData("SpecName"));
		CommonPage.takeExtraScreenshot();
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "declineFleetSpecWithExternalUser")
	@Documentation(step = "Create fleet spec to be decline by External Approval in bulk mode", expected = "Fleet Specs should be created")
	public void createFleetSpecForExternalBulkDecline() throws Throwable {
		CommonPage.loadXMLParameterToTestData("Username", CommonPage.getTestData("OrderLogger"));
		OrderingLoginPage.openFOApplication();
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
		int specCount = Integer.parseInt(CommonPage.getTestData("BulkSpecCount"));
		OrderingVehicleConfigPriceAndConfigPage.addNewSpec("Add Fleet Specification", specCount);
		OrderingVehicleConfigPriceAndConfigPage.changeFleetSpecsToPendingApproval("Fleet Spec Name");
		CommonPage.takeExtraScreenshot();
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "createFleetSpecForExternalBulkDecline")
	@Documentation(step = "Login to FO with external approval and decline fleet specs in bulk mode", expected = "Fleet specs created should be declined")
	public void declineBulkFleetSpecWithExternalUser() throws Throwable {
		String[] userIDs = CommonPage.getTestData("UserIDs").split("\\|");
		OrderingLoginPage.openOrderingWithExternalUser(userIDs[1]);
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickFleetSpecTab();
		OrderingHomePage.clearFilters();
		OrderingHomePage.searchFleetSpecName("ATHECSpec");
		OrderingHomePage.clickSelectAllButton();
		OrderingHomePage.rejectBulkSpecTab();
		OrderingHomePage.verifyFleetSpecIsNotDisplayed("ATHECSpec");
		CommonPage.takeExtraScreenshot();
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "declineBulkFleetSpecWithExternalUser")
	@Documentation(step = "Create fleet spec to be approved for External Approval in bulk mode", expected = "Fleet Spec should be created")
	public void createFleetSpecForExternalBulkApproval() throws Throwable {
		CommonPage.loadXMLParameterToTestData("Username", CommonPage.getTestData("OrderLogger"));
		OrderingLoginPage.openFOApplication();
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
		int specCount = Integer.parseInt(CommonPage.getTestData("BulkSpecCount"));
		OrderingVehicleConfigPriceAndConfigPage.addNewSpec("Add Fleet Specification", specCount);
		OrderingVehicleConfigPriceAndConfigPage.changeFleetSpecsToPendingApproval("Fleet Spec Name");
		CommonPage.takeExtraScreenshot();
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "createFleetSpecForExternalBulkApproval")
	@Documentation(step = "Log in to FO with expecter external approval and approve fleet specs in bulk mode", expected = "Fleet specs created should be approved")
	public void approveBulkFleetSpecWithExternalUser() throws Throwable {
		String[] userIDs = CommonPage.getTestData("UserIDs").split("\\|");
		OrderingLoginPage.openOrderingWithExternalUser(userIDs[1]);
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickFleetSpecTab();
		OrderingHomePage.clearFilters();
		OrderingHomePage.searchFleetSpecName("ATHECSpec");
		OrderingHomePage.clickSelectAllButton();
		OrderingHomePage.approveBulkTab();
		OrderingHomePage.verifyFleetSpecIsNotDisplayed("ATHECSpec");
	}
	
	@Test (alwaysRun = true, dependsOnMethods = "approveBulkFleetSpecWithExternalUser" )
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
		CommonPage.testEnded();
	}
}
