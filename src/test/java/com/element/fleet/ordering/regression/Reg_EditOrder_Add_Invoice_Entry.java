package com.element.fleet.ordering.regression;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOInvoiceEntryPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy Akshay Kandkonde
 */
public class Reg_EditOrder_Add_Invoice_Entry extends BaseWebDriver {
	
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
	@Documentation(step = "Go to edit order and move to Invoice Entry page", expected = "Application should load Invoice Entry page")
	public void goToInvoiceEntryPage() throws Throwable {
		OrderingBOHomePage.selectSideMenuOption("Edit Order");
		OrderingBOHomePage.editOrderSideMenuOptionsLoaded();
		OrderingBOHomePage.selectEditOrderOptions("Invoice Entry");
		OrderingBOInvoiceEntryPage.addUpfitPurchaseOrderPageLoaded();
	}
	
	@Test(dependsOnMethods = "goToInvoiceEntryPage")
	@Documentation(step = "Fill all details of invoice entry and click on Save button", expected = "Invoice Entry should be saved")
	public void addInvoiceEntryAndVerify() throws Throwable {
		OrderingBOInvoiceEntryPage.fillAllInvoiceEntryDetailsAndSave();
		OrderingBOInvoiceEntryPage.verifyInvoiceNumberInVendorInvoiceHistoryTable();
	}
	
	@Test(dependsOnMethods = "addInvoiceEntryAndVerify")
	@Documentation(step = "Upload document for invoice entry and download same document", expected = "Document should be uploaded and downloaded")
	public void uploadDocumentDownloadAndVerify() throws Throwable {
		OrderingBOInvoiceEntryPage.uploadfileAndVerifyUploadedfile(CommonPage.getTestData("CustomColumn2"));
		OrderingBOInvoiceEntryPage.clickForDownloadAndVerify(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "uploadDocumentDownloadAndVerify")
	@Documentation(step = "Click on Cancel button", expected = "Adding invoice entry should be cancelled")
	public void verifyCancelButtonFunctionality() throws Throwable {
		OrderingBOInvoiceEntryPage.verifyCancelButtonFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyCancelButtonFunctionality")
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