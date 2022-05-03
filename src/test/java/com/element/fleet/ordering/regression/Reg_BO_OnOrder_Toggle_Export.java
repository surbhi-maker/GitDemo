package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOAdditionalOptionsPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy mkhairner
 */
public class Reg_BO_OnOrder_Toggle_Export extends BaseWebDriver{

	@BeforeClass
	@Parameters({"xcelerateURL", "username", "applicationURL", "applicationBOURL", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String username, String applicationURL, String applicationBOURL, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
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
	@Documentation(step = "Go to On On-Order Queue page", expected = "Application should load On On-Order Queue page")
	public void onOrderQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("On-Order");
	}	
	
	@Test(dependsOnMethods = "onOrderQueuePage")
	@Documentation(step = "Go to On On-Order Queue page and verify alidate that the Driver Change Status Column has been added to the On-Order Queue", expected = "Application should show the Queue table columnd are same as toggle columns On OnOrder Queue page")
	public void verifyChangeStatusColumn() throws Throwable {
		OrderingBOQueuePage.verifyTableColumnsOnLanding();
		OrderingBOQueuePage.verifyQueueTableColumns();
	}
	
	@Test(dependsOnMethods = "verifyChangeStatusColumn")
	@Documentation(step = "Go to On On-Order Queue page and verify Toogle with Driver Change Status and Queue Table columns", expected = "Application should show the Queue table columnd are same as toggle columns On OnOrder Queue page")
	public void verifyToggleColumnsAndTableColumns() throws Throwable {
		OrderingBOQueuePage.removeColumnsFromQueueTable("Log","Corp", "Year", "Make");
		OrderingBOQueuePage.verifyQueueTableColumns();
		OrderingBOQueuePage.addColumnInQueueTable("Log","Corp", "Year", "Make");
		OrderingBOQueuePage.verifyQueueTableColumns();
	}

	@Test(dependsOnMethods = "verifyToggleColumnsAndTableColumns")
	@Documentation(step = "Go to verify Pagenation", expected = "Application should load Queue page and pagenation should be validated")
	public void verifyPagenationTitleRegqueue() throws Exception {
		OrderingBOQueuePage.verifyPagenation();
	}
	
	@Test(dependsOnMethods = "verifyPagenationTitleRegqueue")
	@Documentation(step = "Go to On on-order Queue page and verify Export functionality with column Driver Change Status column presnet or Not", expected = "Application should Export Queue table Data with present the Driver Change Status column ")
	public void verifyDriverChangeStatus() throws Throwable {
		OrderingBOAdditionalOptionsPage.validateColumnsInExportedCSVOnOrderPage(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "verifyDriverChangeStatus", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}
