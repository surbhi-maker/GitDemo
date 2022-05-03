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
public class Reg_BO_CODAR_Verify_Edit_Functionality extends BaseWebDriver {
	
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
	@Documentation(step = "Navigate to Customer Ordering Dealer Assignment Rules page", expected = "CODAR screen should be displayed")
	public void goToCODARPage() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Customer Ordering Dealer Assignment Rules");
	}

	@Test(dependsOnMethods = "goToCODARPage")
	@Documentation(step = "Verify the Always/Never use field is set to ´Always´ by default", expected = "Always should be default selected")
	public void verifyTheAlwaysNeverField() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickAddNewRuleButton();
		OrderingBOBusinessMaintainedTablesPage.verifyAlwaysIsSelectedByDefault();
	}
	
	@Test(dependsOnMethods = "verifyTheAlwaysNeverField")
	@Documentation(step = "Verify Error message when leaving required fields empty", expected = "Error should be displayed when leaving required fields empty")
	public void verifyErrorDisplayedWhenDataIsMissing() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyErrorIsShownWithMissingData();
		OrderingBOBusinessMaintainedTablesPage.cancelEditOperation();
	}
	
	
	@Test(dependsOnMethods = "verifyErrorDisplayedWhenDataIsMissing")
	@Documentation(step = "Add a new CODAR Rule", expected = "Rule should be created successfully")
	public void createNewRule() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.addNewCODARRule();
	}
	
	@Test(dependsOnMethods = "createNewRule")
	@Documentation(step = "Searched the New Rule created", expected = "New Rule should be displayed in table")
	public void searchRule() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.searchCODARRule();
	}
	
	@Test(dependsOnMethods = "searchRule")
	@Documentation(step = "Select Rule to open edit view screen", expected = "Rule edit view screen should display")
	public void openRuleInEditViewMode() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.enterEditViewFirstRecord();
	}
	
	@Test(dependsOnMethods = "openRuleInEditViewMode")
	@Documentation(step = "Click save button without having made any change", expected = "Success message should display")
	public void verifySuccessMessageWhenSavingWitoutChanges() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnSaveButton();
		OrderingBOBusinessMaintainedTablesPage.verifySuccessMessageIsDisplayedCodarScreen();
	}
	
	@Test(dependsOnMethods = "verifySuccessMessageWhenSavingWitoutChanges")
	@Documentation(step = "Verify error displayed when editing rule id", expected = "Error should be displayed when editing rule id")
	public void verifyErrorWhenEditingRuleId() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.enterEditViewFirstRecord();
		OrderingBOBusinessMaintainedTablesPage.verifyErrorWhenEditingRuleId();
	}
	
	@Test(dependsOnMethods = "verifyErrorWhenEditingRuleId")
	@Documentation(step = "Cancel Edit operation and leave edit view screen", expected = "Changes should not be saved")
	public void cancelEditOperation() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.cancelEditOperation();
	}
	
	@Test(dependsOnMethods = "cancelEditOperation")
	@Documentation(step = "Fill Required info to create Rule", expected = "Info should be send to create rule")
	public void fillRequiredInfoCodarRule() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.fillRequiredInfoCodarRule();
	}
	
	@Test(dependsOnMethods = "fillRequiredInfoCodarRule")
	@Documentation(step = "Verify error displayed when selecting invalid end date", expected = "Error should be displayed when end date is less than start date")
	public void verifyErrorWhenSelectingInvalidEndDate() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.selectDate("invalid");
	}

	@Test(dependsOnMethods = "verifyErrorWhenSelectingInvalidEndDate")
	@Documentation(step = "Cancel Create new rule operation", expected = "Rule should not be created should not be saved")
	public void cancelCreateRule() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.cancelEditOperation();
	}

	@Test(dependsOnMethods = "cancelCreateRule", alwaysRun = true)
	@Documentation(step = "Delete Rule Before Ending Test From CODAR Home page", expected = "Rule should deleted")
	public void deleteRule() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.searchCODARRule();
		OrderingBOBusinessMaintainedTablesPage.deleteSelectedRule("From Main Screen");
	}

	@Test(dependsOnMethods = "deleteRule")
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