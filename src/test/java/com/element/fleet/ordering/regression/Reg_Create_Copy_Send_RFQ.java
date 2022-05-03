package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOAcknowledgmentQueuePage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingBOUpfitProjectPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingSummaryPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

/**
 * @lastModifiedBy Nikhil Mishra
 */
public class Reg_Create_Copy_Send_RFQ extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({ "username", "applicationURL", "applicationBOURL", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String username, String applicationURL, String applicationBOURL, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
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
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor)WebDriverAccess.getDriver());
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Go to On Request and Project Queue page", expected = "Application should load On Request and Project Queue page")
	public void gotoAcknowledgmentQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Upfit_Project");
	}
	
	@Test(dependsOnMethods = "gotoAcknowledgmentQueuePage")
	@Documentation(step = "Go to On Project detail screen page", expected = "Application should load On Project detail screen page")
	public void gotoProjectDetailPage() throws Throwable {
		OrderingBOUpfitProjectPage.gotoCreateRfqPage();	
	}
	
	@Test(dependsOnMethods = "gotoProjectDetailPage")
	@Documentation(step = "Save RFQ", expected = "RFQ should be saved")
	public void saveSendAndCopyRfq() throws Throwable {
		OrderingBOUpfitProjectPage.validatingMandatoryFields();
		OrderingBOUpfitProjectPage.fillRfqPageDetails();
		OrderingBOUpfitProjectPage.saveAndSendRfq();
		OrderingBOUpfitProjectPage.copyRfq();
		OrderingBOUpfitProjectPage.cancelRfq();
		OrderingBOUpfitProjectPage.checkRfqQueue();
		
	}
	@Test(dependsOnMethods = "saveSendAndCopyRfq", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
}
