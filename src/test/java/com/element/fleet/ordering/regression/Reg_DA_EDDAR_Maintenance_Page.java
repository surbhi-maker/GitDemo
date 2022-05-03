package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOElementDeliveringDealerAssignmentRulesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy Sweety Agrawal
 */
public class Reg_DA_EDDAR_Maintenance_Page extends BaseWebDriver {
	
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
	@Documentation(step = "Go to Customer Delivering Dealer Assignment Rules page", expected = "Application should load On Customer Delivering Dealer Assignment Rules page")
	public void gotoEDDARPage() throws Throwable {
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Element Delivering Dealer Assignment Rules");	
	}
	
	@Test(dependsOnMethods = "gotoEDDARPage")
	@Documentation(step = "verify whether EDDAR page got loaded", expected = "EDDAR page should be loaded")
	public void validationOfEDDAR() throws Throwable {
	OrderingBOElementDeliveringDealerAssignmentRulesPage.bmtEDDARLabelValidation();
	}
	
	@Test(dependsOnMethods = "validationOfEDDAR")
	@Documentation(step = "click on add rule and validate whether it has been added", expected = " EDDAR rule should be added successfully")
	public void validateAddEDDARRule() throws Throwable {
	OrderingBOElementDeliveringDealerAssignmentRulesPage.clickAddRule();
	OrderingBOElementDeliveringDealerAssignmentRulesPage.addEDDARPageLoaded();
	}
	
	@Test(dependsOnMethods = "validateAddEDDARRule")
	@Documentation(step = "Enter EDDAR details", expected = "EDDAR details entered")
	public static void enterRuleDetails() throws Exception {
		OrderingBOElementDeliveringDealerAssignmentRulesPage.enterEDDARDetails();
	}

	@Test(dependsOnMethods = "enterRuleDetails")
	@Documentation(step = "validate save rule confirmation message", expected = "rule should get added successfully")
	public static void saveRuleValidation() throws Exception {
		OrderingBOElementDeliveringDealerAssignmentRulesPage.validateSaveRule();
	}
	
	@Test(dependsOnMethods = "saveRuleValidation")
	@Documentation(step = "search created rule on main page", expected = "Rule should be there in the searched record")
	public static void validateCreatedRule() throws Exception {
		OrderingBOElementDeliveringDealerAssignmentRulesPage.validateRuleCreated();
	}
	
	@Test(dependsOnMethods = "validateCreatedRule")
	@Documentation(step = "validate maintenance page by searching and selecting created records", expected = "maintenance page has been validated successfully")
	public static void validateMaintenancePage() throws Exception {
		OrderingBOElementDeliveringDealerAssignmentRulesPage.validateDealerTextBoxIsCleared();
	}
		
	@Test(dependsOnMethods = "validateMaintenancePage")
	@Documentation(step = "validate delete rule from maintenance page", expected = "rule should not be there any more")
	public static void deleteCreatedRuleFromMaintenancePage() throws Exception {
		OrderingBOElementDeliveringDealerAssignmentRulesPage.validateDeleteRuleFromMaintenancePage();
		OrderingBOElementDeliveringDealerAssignmentRulesPage.verifyDeletedRuleDoesNotExist();
	}
	
	@Test(dependsOnMethods = "deleteCreatedRuleFromMaintenancePage")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality4() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality4", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}
