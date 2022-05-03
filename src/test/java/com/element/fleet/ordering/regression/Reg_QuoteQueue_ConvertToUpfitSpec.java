package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingBOOnProjectQueuePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingBOQuotePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy shisingh
 */
public class Reg_QuoteQueue_ConvertToUpfitSpec extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "username", "boUserName" ,"orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username,String boUserName,String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
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
	@Documentation(step = "Go to On project Queue page", expected = "Application should load On project Queue page")
	public void gotoProjectQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Project");
		OrderingBOOnOrderQueuePage.waitForProjectQueuePage();
	}
	
	@Test(dependsOnMethods = "gotoProjectQueuePage")
	@Documentation(step = "Create and select the project", expected = "Creation and selection of project should be successful")
	public void createProject() throws Throwable {
		OrderingBOOnProjectQueuePage.createAndSelectProject();
	}
	
	@Test(dependsOnMethods = "createProject")
	@Documentation(step = "Create Quote", expected = "Quote creation should be succesful")
	public void createQuote() throws Throwable {
		OrderingBOOnProjectQueuePage.createQuote();
		OrderingBOOnProjectQueuePage.getQuoteId();
		OrderingBOOnProjectQueuePage.closeProjectDetails();
	}
	
	@Test(dependsOnMethods = "createQuote")
	@Documentation(step = "Go to Quote queue", expected = "Application should load quote Queue page")
	public void gotoQuoteQueuePage() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Quote");
	}
	
	@Test(dependsOnMethods = "gotoQuoteQueuePage")
	@Documentation(step = "Convert Quote to Upfit Spec", expected = "Quote Converstion to Upfit Spec should be succesful")
	public void convertQuoteToUpfitSpec() throws Exception {
		OrderingBOQuotePage.convertToUpfitSpec("Quote ID");
	}
	
	@Test(dependsOnMethods = "convertQuoteToUpfitSpec")
	@Documentation(step = "Go to Upfit Spec queue", expected = "Application should load Upfit Spec Queue page")
	public void gotoUpfitSpecQueuePage() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Upfit Spec");
	}
	
	@Test(dependsOnMethods = "gotoUpfitSpecQueuePage")
	@Documentation(step = "Verify converted quote in upfit SPec", expected = "converted quote should be present in Upfit Spec")
	public void verifyInUpfitSpecQueue() throws Exception {
		OrderingBOQueuePage.addControls("Upfit Spec Name", CommonPage.getTestData("UpfitName"));
		OrderingBOQueuePage.verifyDataOfControlsFilter("Upfit Spec Name", CommonPage.getTestData("UpfitName"));	
	}
		
	@Test(dependsOnMethods = "verifyInUpfitSpecQueue", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
}
