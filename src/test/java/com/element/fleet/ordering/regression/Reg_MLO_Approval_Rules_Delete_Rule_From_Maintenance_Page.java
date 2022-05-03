package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy Usha
 */
public class Reg_MLO_Approval_Rules_Delete_Rule_From_Maintenance_Page extends BaseWebDriver {

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
	@Documentation(step = "Go to Manager Order Preferences  page", expected = " Manager Order Preferences page should be loaded")
	public void goToManagerOrderPreferencesPage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Manager Order Preferences");
		OrderingBOManagerOrderPreferencesPage.waitForManagerOrderPreferencesPageToLoaded();
	}
	
	@Test(dependsOnMethods = "goToManagerOrderPreferencesPage")
	@Documentation(step = "Enter Fleet ID", expected = "Fleet ID should be entered and should load Fleet Spec Groups section")
	public void selectFleetID() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterCorpAndClientAndSelectClient();
	}
	             
	@Test(dependsOnMethods = "selectFleetID")
	@Documentation(step = "Go to Approval Rules tab", expected = "Approval Rules Page should be loaded")
	public void goToApprovalRulesSection() throws Exception {
		OrderingBOManagerOrderPreferencesPage.moveToSection("Approval Rules");
	}
	
	@Test(dependsOnMethods = "goToApprovalRulesSection")
	@Documentation(step = "Click on AddRule Button", expected = "Add New Rule Approval Page should be loaded")
	public static void goToAddRuleApprovalsPage() throws Exception {
		OrderingBOManagerOrderPreferencesPage.clickOnAddRuleButton();
		OrderingBOManagerOrderPreferencesPage.addApprovalRulesPageLoaded();
	}
	
	@Test(dependsOnMethods = "goToAddRuleApprovalsPage")
	@Documentation(step = "Enter Approval Rule", expected = "Approval Rule details entered")
	public static void enterApprovalRuleDetails() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterApprovalRuleDetails();
	}	

	@Test(dependsOnMethods = "enterApprovalRuleDetails")
	@Documentation(step = "Search and select rules to be deleted", expected = "Rules should be selected")
	public void searchAndSelectApprovalRule() throws Exception {
		OrderingBOManagerOrderPreferencesPage.clickSaveButton();
		OrderingBOManagerOrderPreferencesPage.verifyPopUpMessage("Approval Rules - Save");
		OrderingBOManagerOrderPreferencesPage.popUpAction("Yes");
		OrderingBOHomePage.selectSideMenuOption("Manager Order Preferences");
		OrderingBOManagerOrderPreferencesPage.waitForManagerOrderPreferencesPageToLoaded();
		OrderingBOManagerOrderPreferencesPage.moveToSection("Approval Rules");
		OrderingBOManagerOrderPreferencesPage.searchAndSelectApprovalRule("approval-rules-queue", CommonPage.getTestData("ApprovalRuleName"));
	}
	
	@Test(dependsOnMethods = "searchAndSelectApprovalRule")
	@Documentation(step = "Click on AddRule Button", expected = "Add New Rule Approval Page should be loaded")
	public static void deleteRuleFromMaintenancepage() throws Exception {
		OrderingBOManagerOrderPreferencesPage.clickOnAnyExistingRule("section-approval-rules", "Approval Rules");
		OrderingBOManagerOrderPreferencesPage.clickOnDeleteButtonFromMaintenancePage();
		OrderingBOManagerOrderPreferencesPage.verifyPopUpMessage("Approval Rules - Delete From Maintenance View");
		OrderingBOManagerOrderPreferencesPage.popUpAction("Yes");
		OrderingBOManagerOrderPreferencesPage.checkAlertPopUpConfimationMessage("Delete From Maintenance View");
	}
	
	@Test(dependsOnMethods = "deleteRuleFromMaintenancepage")
	@Documentation(step = "Verify Approval Rule has been deleted", expected = "Approval Rule is deleted")
	public static void verifyApprovalRuleDeleted() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Manager Order Preferences");
		OrderingBOManagerOrderPreferencesPage.waitForManagerOrderPreferencesPageToLoaded();
		OrderingBOManagerOrderPreferencesPage.moveToSection("Approval Rules");
		OrderingBOManagerOrderPreferencesPage.verifyApprovalRuleIsDeleated("approval-rules-queue", CommonPage.getTestData("ApprovalRuleName"));
	}
	
	@Test(alwaysRun = true, dependsOnMethods = "verifyApprovalRuleDeleted")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
	
}
