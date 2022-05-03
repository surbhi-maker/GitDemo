package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy mkhairner
 */
public class Reg_BMT_CDDAR_User_RoleSpecific_Testing extends BaseWebDriver  {
	
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
	@Documentation(step = "Go to Customer Delivering Dealer Assignment Rules page", expected = "Application should load On Customer Delivering Dealer Assignment Rules page")
	public void gotoCustomerDeliveringDealerAssignmentRulesPage() throws Throwable {
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Customer Delivering Dealer Assignment Rules");	
	}
	
	@Test(dependsOnMethods = "gotoCustomerDeliveringDealerAssignmentRulesPage")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality")
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
	@Test(dependsOnMethods = "testEnded")
	@Documentation(step = "Open Back office page with IT Production", expected = "Back office page should be opened and visible the Customer Delivering Dealer Assignment Rules")
	public void testITProductionRole() throws Throwable {
		OrderingLoginPage.openCustomBOApplication(CommonPage.getTestData("CustomColumn3"));
		OrderingBOHomePage.orderingBOHomePageLoaded();
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Customer Delivering Dealer Assignment Rules");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "testITProductionRole")
	@Documentation(step = "Open Back office page with Factory Resolution Role", expected = "Back office page should be opened and visible the Customer Delivering Dealer Assignment Rules")
	public void testFactoryResolutionRole() throws Throwable {
		OrderingLoginPage.openCustomBOApplication(CommonPage.getTestData("CustomColumn1"));
		OrderingBOHomePage.orderingBOHomePageLoaded();
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Customer Delivering Dealer Assignment Rules");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "testFactoryResolutionRole")
	@Documentation(step = "Open Back office page with Regular Ordering Role", expected = "Back office page should be opened and visible the Customer Delivering Dealer Assignment Rules")
	public void dealerTeamMemberRole() throws Throwable {
		OrderingLoginPage.openCustomBOApplication(CommonPage.getTestData("CustomColumn4"));		
		OrderingBOHomePage.orderingBOHomePageLoaded();
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Customer Delivering Dealer Assignment Rules");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.verifyLogOutFunctionality();
	}

	@Test(dependsOnMethods = "testFactoryResolutionRole")
	@Documentation(step = "Open Back office page with Regular Ordering Role", expected = "Back office page should be opened and visible the Customer Delivering Dealer Assignment Rules")
	public void testRegularOrderingRole() throws Throwable {
		OrderingLoginPage.openCustomBOApplication(CommonPage.getTestData("CustomColumn2"));		
		OrderingBOHomePage.orderingBOHomePageLoaded();
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Customer Delivering Dealer Assignment Rules");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
}