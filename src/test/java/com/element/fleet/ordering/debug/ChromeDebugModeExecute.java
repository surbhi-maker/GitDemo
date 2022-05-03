package com.element.fleet.ordering.debug;

import org.openqa.selenium.JavascriptExecutor;
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

public class ChromeDebugModeExecute extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({ "port", "testName", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String port, String testName, String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
		BaseWebDriver.driver = CommonPage.starDebugMode(port);
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, testName);
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
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
	@Parameters({ "username", "OrderingDataFilePath", "CredentialFileSheetname" })
	public void testLoginBOApplication() throws Exception {
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor)driver);
		OrderingBOHomePage.orderingBOHomePageLoaded();
		OrderingBOHomePage.boHomePageLabelValidation();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Validation of Batch process side menu options title", expected = "Title validation should be succcessful")
	public void titleValidationBatchProcessingMenuOptions() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Batch Processing");
		OrderingBOHomePage.batchProcessingSideMenuOptionsLoaded();
		OrderingBOHomePage.batchProcessingSideMenuLabelValidation();
	}
	
	@Test(dependsOnMethods = "titleValidationBatchProcessingMenuOptions")
	@Documentation(step = "Go to Batch Ordering page", expected = "Batch Ordering page should be loaded")
	public void goToBatchOrderingPage() throws Exception {
		OrderingBOHomePage.selectBatchProcessingOptions("Batch Ordering");
		OrderingBOBatchProcessingPage.batchOrderingPageLoaded();
		OrderingBOBatchProcessingPage.batchOrderingPageLabelValidation();
	}
	
	@Test(dependsOnMethods = "goToBatchOrderingPage")
	@Documentation(step = "Go to Create Driver Group page", expected = "Create Driver Group page should be loaded")
	public void goToCreateDriverGroupPage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Batch Processing");
		OrderingBOHomePage.batchProcessingSideMenuOptionsLoaded();
		OrderingBOHomePage.selectBatchProcessingOptions("Create Driver Group");
		OrderingBOBatchProcessingPage.createDriverGroupPageLoaded();
		OrderingBOBatchProcessingPage.createDriverGroupPageLabelValidation();
	}
	
	@Test(dependsOnMethods = "goToCreateDriverGroupPage")
	@Documentation(step = "Go to Driver Group Maintenance page", expected = "Create Driver Group page should be loaded")
	public void goToDriverGroupMaintenancePage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Batch Processing");
		OrderingBOHomePage.batchProcessingSideMenuOptionsLoaded();
		OrderingBOHomePage.selectBatchProcessingOptions("Driver Group Maintenance");
		OrderingBOBatchProcessingPage.driverGroupMaintenancePageLoaded();
		OrderingBOBatchProcessingPage.driverGroupMaintenancePageLabelValidation();	
	}

	@Test(alwaysRun = true, dependsOnMethods = "goToDriverGroupMaintenancePage")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}

}