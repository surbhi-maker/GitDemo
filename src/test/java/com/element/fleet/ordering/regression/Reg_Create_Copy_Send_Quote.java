package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnProjectQueuePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingBOUpfitProjectPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class Reg_Create_Copy_Send_Quote extends BaseWebDriver {
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
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor) WebDriverAccess.getDriver());
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}

	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Go to Project Queue page", expected = "Application should load Project Queue page")
	public void goToProjectQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Upfit Spec");
	}

	@Test(dependsOnMethods = "goToProjectQueuePage")
	@Documentation(step = "Click on Create new project and fill the details", expected = "Application should be able to enter fill all fields data ")
	public void selectProjectFromRequestQueue() throws Exception {
		OrderingBOOnProjectQueuePage.selectProject();
	}

	@Test(dependsOnMethods = "selectProjectFromRequestQueue")
	@Documentation(step = "Go to quote queue page and click on Add New Quote", expected = "Application should be able to click on add new quote queue")
	public void createQuote() throws Throwable {
		OrderingBOOnProjectQueuePage.clickCreateQuote();
	}
	
	@Test(dependsOnMethods = "createQuote")
	@Documentation(step = "Save Quote", expected = "Quote should be saved")
	public void saveSendAndCopyQuote() throws Throwable {
		OrderingBOUpfitProjectPage.validatingMandatoryFields();
		//OrderingBOUpfitProjectPage.fillRfqPageDetails();
		//OrderingBOUpfitProjectPage.saveAndSendRfq();
		//OrderingBOUpfitProjectPage.copyRfq();
		//OrderingBOUpfitProjectPage.cancelRfq();
		//OrderingBOUpfitProjectPage.checkRfqQueue();
		
	}

}
