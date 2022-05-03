package com.element.fleet.ordering.sanity;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOAddUpfitPurchaseOrderPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOInvoiceEntryPage;
import com.element.fleet.ordering.page.OrderingBOOrderMaintenancePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy pdhole
 */
public class Sanity_Edit_Order extends BaseWebDriver {	
	
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
		CommonPage.testStarted();
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Exception {
		OrderingBOHomePage.orderingBOHomePageLoaded();
		OrderingBOHomePage.boHomePageLabelValidation();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Go to Edit Order side menu options", expected = "Edit Order side menu options should be loaded")
	public void verifyEditOrderSideMenuOptions() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Edit Order");
		OrderingBOHomePage.editOrderSideMenuOptionsLoaded();
		OrderingBOHomePage.editOrderSideMenuLabelValidation();
	}
	
	@Test(dependsOnMethods = "verifyEditOrderSideMenuOptions")
	@Documentation(step = "Go to Order Maintenance page", expected = "Order Maintenance page should be loaded")
	public void goToOrderMaintenancePage() throws Exception {
		OrderingBOHomePage.selectEditOrderOptions("Order Maintenance");
		OrderingBOOrderMaintenancePage.orderMaintenancePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToOrderMaintenancePage")
	@Documentation(step = "Go to Add Upfit Purchase Order page", expected = "Add Upfit Purchase Order page should be loaded")
	public void goToAddUpfitPurchaseOrderPage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Edit Order");
		OrderingBOHomePage.editOrderSideMenuOptionsLoaded();
		OrderingBOHomePage.selectEditOrderOptions("Add Upfit Purchase Order");
		OrderingBOAddUpfitPurchaseOrderPage.addUpfitPurchaseOrderPageLoaded();
		OrderingBOAddUpfitPurchaseOrderPage.addUpfitPurchaseOrderLabelValidation();
	}
	
	@Test(dependsOnMethods = "goToAddUpfitPurchaseOrderPage")
	@Documentation(step = "Go to Invoice Entry page", expected = "Invoice Entry page should be loaded")
	public void goToInvoiceEntryPage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Edit Order");
		OrderingBOHomePage.editOrderSideMenuOptionsLoaded();
		OrderingBOHomePage.selectEditOrderOptions("Invoice Entry");
		OrderingBOInvoiceEntryPage.addUpfitPurchaseOrderPageLoaded();
		OrderingBOInvoiceEntryPage.invoiceEntryLabelValidation();
	}
	
	@Test(alwaysRun = true, dependsOnMethods = "goToInvoiceEntryPage")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
	
}
