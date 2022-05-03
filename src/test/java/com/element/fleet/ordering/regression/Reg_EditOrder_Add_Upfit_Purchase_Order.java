package com.element.fleet.ordering.regression;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOAddUpfitPurchaseOrderPage;
import com.element.fleet.ordering.page.OrderingBOEditOrderPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOrdMaintPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy Akshay Kandkonde
 */
public class Reg_EditOrder_Add_Upfit_Purchase_Order extends BaseWebDriver {
	
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
	@Documentation(step = "Go to edit order and then to add upfit purchase order page", expected = "Application should load Add upfit purchase order page")
	public void goToAddUpfitPurchaseOrderPage() throws Throwable {
		OrderingBOHomePage.selectSideMenuOption("Edit Order");
		OrderingBOHomePage.editOrderSideMenuOptionsLoaded();
		OrderingBOHomePage.selectEditOrderOptions("Add Upfit Purchase Order");
		OrderingBOAddUpfitPurchaseOrderPage.addUpfitPurchaseOrderPageLoaded();
		OrderingBOEditOrderPage.fillAddUpfitPODetailsAndClickOnSave();
		OrderingBOEditOrderPage.verifySearchData();
	}
	
	@Test(dependsOnMethods = "goToAddUpfitPurchaseOrderPage")
	@Documentation(step = "Go to order maintenance page", expected = "Application should navigate to order maintenance page")
	public void goToOrderMaintenancePage() throws Throwable {
		OrderingBOEditOrderPage.gotoCreatedOrder();
	}
	
	@Test(dependsOnMethods = "goToOrderMaintenancePage")
	@Documentation(step = "verify PO id and name", expected = "PO id and name should be matched")
	public void verifyPO() throws Throwable {
		OrderingBOOrdMaintPage.verifyUpfitPO();
	}
	
	@Test(dependsOnMethods = "verifyPO")
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