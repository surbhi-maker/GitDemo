package com.element.fleet.ordering.regression;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy Hector_Jimenez
 */
public class Reg_BO_EODAR_Verify_UI_Delete_Functionality extends BaseWebDriver {
	
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

	@Test(alwaysRun = true)
	@Documentation(step = "Login to BO application", expected = "BO Home Page should display") 
	public void loginToBO() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openBOApplication();
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "loginToBO")
	@Documentation(step = "Navigate to Business Maintained Tables", expected = "BMT screen should be displayed")
	public void goToBusinessMaintainedTablesPage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToBusinessMaintainedTablesPage")
	@Documentation(step = "Navigate to Element Ordering Dealer Assignment Rules page", expected = "EODAR screen should be displayed")
	public void goToEODARPage() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Element Ordering Dealer Assignment Rules");
	}
	
	@Test(dependsOnMethods = "goToEODARPage")
	@Documentation(step = "Add a new EODAR Rule", expected = "Rule should be created successfully")
	public void createNewRule() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickAddNewRuleButton();
		OrderingBOBusinessMaintainedTablesPage.waitForAddNewRuleScreenEODAR();
		OrderingBOBusinessMaintainedTablesPage.fillRequiredInfoEodarRule();
		OrderingBOBusinessMaintainedTablesPage.saveNewRuleAfterFillingDetails();
	}
	
	@Test(dependsOnMethods = "createNewRule")
	@Documentation(step = "Searched the New Rule created", expected = "New Rule should be displayed in table")
	public void searchRule() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.searchEODARRule();
	}
	
	@Test(dependsOnMethods = "searchRule")
	@Documentation(step = "Verify delete button is disabled", expected = "Delete button should be disabled")
	public void verifyDeleteButtonIsDisabled() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyDeleteButtonIsDisabled();
	}
	
	@Test(dependsOnMethods = "verifyDeleteButtonIsDisabled")
	@Documentation(step = "Verify delete button gets enabled after selecting rule", expected = "Delete button should get enabled after selecting a rule from table")
	public void verifyDeleteButtonGetsEnabled() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyDeleteButtonGetsEnabled();
	}
	
	@Test(dependsOnMethods = "verifyDeleteButtonGetsEnabled")
	@Documentation(step = "Verify user can cancel the delete operation", expected = "rule should not be deleted")
	public void verifyCancelDelete() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.cancelDeleteOperation("From Main Screen");
	}
	
	@Test(dependsOnMethods = "verifyCancelDelete")
	@Documentation(step = "Verify user can delete the selected rule", expected = "rule should be deleted")
	public void verifyDeleteRuleFunctionality() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.deleteSelectedRule("From Main Screen");
	}
	
	@Test(dependsOnMethods = "verifyDeleteRuleFunctionality")
	@Documentation(step = "Add a new EODAR Rule", expected = "Rule should be created successfully")
	public void createNewRuleAgain() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickAddNewRuleButton();
		OrderingBOBusinessMaintainedTablesPage.waitForAddNewRuleScreenEODAR();
		OrderingBOBusinessMaintainedTablesPage.fillRequiredInfoEodarRule();
		OrderingBOBusinessMaintainedTablesPage.saveNewRuleAfterFillingDetails();
	}
	
	@Test(dependsOnMethods = "createNewRuleAgain")
	@Documentation(step = "Searched the New Rule created", expected = "New Rule should be displayed in table")
	public void searchRuleAgain() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyClearFilterFunctionality();
		OrderingBOBusinessMaintainedTablesPage.searchEODARRule();
	}
	
	@Test(dependsOnMethods = "searchRuleAgain")
	@Documentation(step = "Cancel Delete Rule operation from edit view screen", expected = "Rule should not be deleted")
	public void cancelDeleteRuleFromEditView() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickFirstRuleDisplayedOnQueue();
		OrderingBOBusinessMaintainedTablesPage.waitForAddNewRuleScreenEODAR();
		OrderingBOBusinessMaintainedTablesPage.cancelDeleteRuleFromEditViewScreen();
	}
	
	@Test(dependsOnMethods = "cancelDeleteRuleFromEditView")
	@Documentation(step = "Delete Rule from edit view screen", expected = "Rule should  be deleted")
	public void deleteRuleFromEditView() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.deleteRuleFromEditViewScreen();
	}
	
	@Test(dependsOnMethods = "deleteRuleFromEditView")
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