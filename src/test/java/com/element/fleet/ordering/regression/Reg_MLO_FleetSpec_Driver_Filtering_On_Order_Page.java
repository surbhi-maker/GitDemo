package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingStartHerePage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/*
 * PreCondition: This test case will work only when Association with at least two drivers and two fleet specs attached:
 * Fleet Spec's: F045264,F045537
 * Driver's : A1399784,A1564832
 * Client: 9990
 * OrderLogger : Orderint2
 * Note: Used Association with above details is AUTO_DO_NOT_DELETE
 * @lastModifiedBy shivamsr
 */
public class Reg_MLO_FleetSpec_Driver_Filtering_On_Order_Page extends BaseWebDriver {

	@BeforeClass
	@Parameters({ "xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
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
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void testLaunchURL() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openCustomFOApplication(CommonPage.getTestData("OrderLogger"));
	}
	
	@Test(dependsOnMethods = "testLaunchURL")
	@Documentation(step = "Enter the required client number to the application",expected = "The application display the required client") 
	public void changeClientBreakdown() throws Exception {	
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
	}

	@Test(dependsOnMethods = "changeClientBreakdown")
	@Documentation(step = "Select order type on create order page", expected = "Order type should be Factory Order") 
	public void selectOrderType() throws Exception {
		OrderingBOManagerOrderPreferencesPage.moveToCreateOrderPage();
	}

	@Test(dependsOnMethods = "selectOrderType")
	@Documentation(step = "Verifies only the drivers added are present", expected = "Only the drivers added in association should be present") 
	public void verifyAssocDriversPresent() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingBOManagerOrderPreferencesPage.verifyAssocDriversPresentOnFODriverPage();
	}
	
	@Test(dependsOnMethods = "verifyAssocDriversPresent")
	@Documentation(step = "Verifies only the Fleet specs added are present", expected = "Only the fleet specs added in association should be present") 
	public void verifyAssocFleetspecsArePresent() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingBOManagerOrderPreferencesPage.verifyAssocFleetSpecPresentOnFODriverPage();
	}
	
	@Test(dependsOnMethods = "verifyAssocFleetspecsArePresent", alwaysRun = true)
	@Documentation(step = "Verifies only the Fleet specs added are present", expected = "Only the fleet specs added in association should be present") 
	public void endTest() throws Exception {
		CommonPage.testEnded();
	}
	
}