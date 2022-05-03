package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBMTCustDeliveryDealerAssigmentPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOElementDeliveringDealerAssignmentRulesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingBOOrdMaintPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy mkhairner
 */
public class Reg_BMT_CDDAR_Maintenance_Page extends BaseWebDriver {

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
	@Documentation(step = "Enter Customer Delivering Dealer Assignment Rules page details", expected = "Customer Delivering Dealer Assignment Rules page details entered")
	public static void enterRuleDetails() throws Exception {
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable();
		OrderingBOBMTCustDeliveryDealerAssigmentPage.clickAddRuleFromMaintenance();
		OrderingBOOrdMaintPage.confirmWarningPopup();	
		OrderingBOBMTCustDeliveryDealerAssigmentPage.enterDetailsForAddRule();
	}
	
	@Test(dependsOnMethods = "enterRuleDetails")
	@Documentation(step = "search created rule on main page", expected = "Rule should be there in the searched record")
	public static void validateCreatedRule() throws Exception {
		OrderingBOBMTCustDeliveryDealerAssigmentPage.validateSaveRule();
	}
	
	@Test(dependsOnMethods = "validateCreatedRule")
	@Documentation(step = "search created rule on main page", expected = "Rule should be there in the searched record")
	public static void validateRule() throws Exception {
		OrderingBOBMTCustDeliveryDealerAssigmentPage.validateRuleCreated();
	}	
	
	@Test(dependsOnMethods = "validateRule")
	@Documentation(step = "click searched record checkbox and delete", expected = "rule should get deleted successfully")
	public static void deleteCreatedRule() throws Exception {
		OrderingBOElementDeliveringDealerAssignmentRulesPage.deleteRule();
	}
	
	@Test(dependsOnMethods = "deleteCreatedRule")
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
