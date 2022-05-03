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
public class Reg_MyQueue_Price_And_Config_Queue extends BaseWebDriver {
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
	@Documentation(step = "Create Price and Config spec", expected = "Price and Config Spec should be created")
	public void createPriceAndConfigSpec() throws Throwable {
		int specOrders = Integer.parseInt(CommonPage.getTestData("SpecCount"));
		OrderingVehicleConfigPriceAndConfigPage.addNewSpec("Add Price and Configuration", specOrders);
	}
	
	@Test(dependsOnMethods = "createPriceAndConfigSpec")
	@Documentation(step = "Select pending client approval from submenu", expected = "Pending Client Approval option should be selected")
	public void changeOrdersToPendingApproval( ) throws Throwable {
		OrderingVehicleConfigPriceAndConfigPage.changeOrdersToPendingApproval("Price & Config Name");
	}
	
	@Test(dependsOnMethods = "changeOrdersToPendingApproval")
	@Documentation(step = "Verify spec created is displayed on price and spec tab", expected = "Price and config spec should display on table")
	public void verifySpecIsDisplayedOnPriceConfigTab() throws Throwable {
		OrderingHomePage.goBackToHomePage();
		OrderingHomePage.clickPriceAndConfigTab();
		OrderingHomePage.searchPriceConfigName(CommonPage.getTestData("SpecName"));
	}
	
	@Test(dependsOnMethods = "verifySpecIsDisplayedOnPriceConfigTab")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly")
	public void verifySuperUserLogOut() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifySuperUserLogOut")
	@Documentation(step = "Log in with external user which is not an approver ", expected = "Home Screen Should display")
	public void logInToFOwithExternalUserNotApprover() throws Exception {
		String[] userIDs = CommonPage.getTestData("UserIDs").split("\\|");
		OrderingLoginPage.openOrderingWithExternalUser(userIDs[2]);
		OrderingHomePage.waitForHomePage();
	}
	
	@Test(dependsOnMethods = "logInToFOwithExternalUserNotApprover")
	@Documentation(step = "Search Created Price And Config spec", expected = "Price and config spec should NOT display on table")
	public void validateSpecIsNotDisplayed() throws Throwable {
		OrderingHomePage.clickPriceAndConfigTab();
		OrderingHomePage.verifyPriceAndConfigSpecIsNotDisplayed(CommonPage.getTestData("SpecName"));
	}
	
	@Test(dependsOnMethods = "validateSpecIsNotDisplayed")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly")
	public void verifyExternalUserLogOut() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyExternalUserLogOut")
	@Documentation(step = "log in to FO with superuser ", expected = "FO Home page should display")
	public void loginToFOWithSuperUSer() throws Throwable {
		CommonPage.loadXMLParameterToTestData("Username", CommonPage.getTestData("OrderLogger"));
		OrderingLoginPage.openFOApplication();
		OrderingHomePage.waitForHomePage();
	}
	
	@Test(dependsOnMethods = "loginToFOWithSuperUSer")
	@Documentation(step = "Move to price and config tab", expected = "Price and Config Tab should display")
	public void goToPriceAndConfigTab() throws Exception {
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
		OrderingHomePage.clickPriceAndConfigTab();
		OrderingHomePage.clearFilters();
	}

	@Test(dependsOnMethods = "goToPriceAndConfigTab")
	@Documentation(step = "Approve Price Config spec Created", expected = "Price and Config Spec should be approved")
	public void approveSinglePriceAndConfigSpec() throws Throwable {
		OrderingHomePage.searchPriceConfigName(CommonPage.getTestData("SpecName"));
		OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
		OrderingHomePage.approveSingleSpec();
		CommonPage.takeExtraScreenshot();
		OrderingHomePage.verifyPriceAndConfigSpecIsNotDisplayed(CommonPage.getTestData("SpecName"));
	}
	
	@Test(dependsOnMethods = "approveSinglePriceAndConfigSpec")
	@Documentation(step = "Approve Bulk Price Config spec Created", expected = "Price and Config Spec should be approved in Bulked mode")
	public void approveBulkPriceAndConfigSpec() throws Throwable {
		OrderingHomePage.searchPriceConfigName("ATHECSpec");
		OrderingHomePage.clickSelectAllButton();
		OrderingHomePage.approveBulkTab();
		CommonPage.takeExtraScreenshot();
		OrderingHomePage.verifyPriceAndConfigSpecIsNotDisplayed("ATHECSpec");
	}
	
	@Test(dependsOnMethods = "approveBulkPriceAndConfigSpec")
	@Documentation(step = "Create Price and Config spec to be Rejected", expected = "Price and Config Spec should be created")
	public void createPriceAndConfigSpecToBeRejected() throws Throwable {
		int specOrders = Integer.parseInt(CommonPage.getTestData("SpecCount"));
		OrderingVehicleConfigPriceAndConfigPage.addNewSpec("Add Price and Configuration", specOrders);
		OrderingVehicleConfigPriceAndConfigPage.changeOrdersToPendingApproval("Price & Config Name");
		OrderingHomePage.goBackToHomePage();
		OrderingHomePage.clickPriceAndConfigTab();
		OrderingHomePage.clearFilters();
	}
	
	@Test(dependsOnMethods = "createPriceAndConfigSpecToBeRejected")
	@Documentation(step = "Reject Single Price Config spec Created", expected = "Price and Config Spec should be rejected")
	public void rejectSinglePriceAndConfigSpec() throws Throwable {
		System.out.println("Search order to be rejected");
		OrderingHomePage.searchPriceConfigName(CommonPage.getTestData("SpecName"));
		OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
		OrderingHomePage.verifyInvalidFilesErrorInModal();
		OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
		OrderingHomePage.rejectSingleSpec();
		CommonPage.takeExtraScreenshot();
		OrderingHomePage.verifyPriceAndConfigSpecIsNotDisplayed(CommonPage.getTestData("SpecName"));
	}
	
	@Test(dependsOnMethods = "rejectSinglePriceAndConfigSpec")
	@Documentation(step = "Reject Bulk Price Config spec Created", expected = "Price and Config Spec should be rejected in Bulked mode")
	public void rejectBulkPriceAndConfigSpec() throws Throwable {
		OrderingHomePage.searchPriceConfigName("ATHECSpec");
		OrderingHomePage.clickSelectAllButton();
		OrderingHomePage.rejectBulkSpecTab();
		CommonPage.takeExtraScreenshot();
		OrderingHomePage.verifyPriceAndConfigSpecIsNotDisplayed("ATHECSpec");
	}

	@Test (alwaysRun = true, dependsOnMethods = "rejectBulkPriceAndConfigSpec" )
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
		CommonPage.testEnded();
	}
}
