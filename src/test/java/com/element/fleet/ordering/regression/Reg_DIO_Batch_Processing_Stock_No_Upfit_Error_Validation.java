package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBatchProcessingPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy UshaNaidu
 */
public class Reg_DIO_Batch_Processing_Stock_No_Upfit_Error_Validation extends BaseWebDriver {

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

	@Test(alwaysRun=true)
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
	@Documentation(step = "Navigate to Batch Ordering Page and upload/download given batch order from resources folder", expected = "User should be abe to navigate to Batch Ordering Page and upload batch order")
	public void uploadBatchOrderData() throws Throwable {
		OrderingBOHomePage.selectSideMenuOption("Batch Processing");
		OrderingBOHomePage.selectBatchProcessingOptions("Batch Ordering");
		OrderingBOBatchProcessingPage.batchOrderingPageLoaded();
		OrderingBOBatchProcessingPage.processBatchOrderingUploadExistingFileFromResources(this.getClass().getSimpleName());
		OrderingBOBatchProcessingPage.waitUntilBatchIsProcessed();
		OrderingBOBatchProcessingPage.downloadResultFile(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "uploadBatchOrderData")
	@Documentation(step = "Read the errors from erros file downloaded and add them to a list", expected = "Errors should be displayed when Data is not correct in the file")
	public void readActualErrorFromFileDownloaded() throws Throwable {
		OrderingBOBatchProcessingPage.getActualErrorColumn();
	}
	
	@Test(dependsOnMethods = "readActualErrorFromFileDownloaded")
	@Documentation(step = "Move expected error file from resources to target", expected = "File should be moved successfully")
	public void moveExpectedErrorsFile() throws Throwable {
		OrderingBOBatchProcessingPage.moveErrorMessagesFileToTarget(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "moveExpectedErrorsFile")
	@Documentation(step = "Read errors in expected errors file and add them to a list", expected = "File should contain expected errors")
	public void readExpectedErrors() throws Throwable {
		OrderingBOBatchProcessingPage.getExpectedErrorColumn();
	}
	
	@Test(dependsOnMethods = "readExpectedErrors")
	@Documentation(step = "Compare the actual errors vs expected errors from xlsx files", expected = "Errors should match in the two files")
	public void compareErrorsInFiles() throws Throwable {
		OrderingBOBatchProcessingPage.verifyErrorResults();
	}

	@Test(dependsOnMethods = "compareErrorsInFiles", alwaysRun = true)
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Throwable {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}

}