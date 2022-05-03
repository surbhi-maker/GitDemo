package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy akandkonde
 */
public class Reg_BRE_Capping_Smart_Rules_Bulk_Action extends BaseWebDriver {
	
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
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void launchBOURL() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openBOApplication();
	}
	
	@Test(dependsOnMethods = "launchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void loginBOApplication() throws Exception {
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "loginBOApplication")
	@Documentation(step = "Go to scheduler BMT", expected = "Application should be navigated to scheduler BMT")
	public void goToCappingRules() throws Exception {
		OrderingBOQueuePage.goToBusinessMaintainedTable();
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Capping Rules");
		OrderingBOBusinessMaintainedTablesPage.verifyCappingRulesTitleAndSubTitle();
	}
	
	@Test(dependsOnMethods = "goToCappingRules")
	@Documentation(step = "Click on add new button and fill all details", expected = "add new rule page should be opened and data should be filled")
	public void goToAddCappingRulesPage() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnAddButtonAndFillAllDetails();
	}
	
	@Test(dependsOnMethods = "goToAddCappingRulesPage")
	@Documentation(step = "go to view rule page and verify data", expected = "Veiw page should be opened and data should be matched")
	public void goToViewCappingRulePageAndVerifyData() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnSaveButton();
		OrderingCommonPage.checkAlertPopUp();		
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.goToViewPageAndVerifyData();
	}
	
	@Test(dependsOnMethods = "goToViewCappingRulePageAndVerifyData")
	@Documentation(step = "go to edit rule page and update some data", expected = "Edit rule page should be opened and able to edit some data")
	public void goToEditCappingRulesPageAndUpdateSomeData() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.goBackToCappingRulePage();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.goToEditPageAndUpdateRuleData();	
	}
	
	@Test(dependsOnMethods = "goToEditCappingRulesPageAndUpdateSomeData")
	@Documentation(step = "Verify Edit pop up", expected = "Edit pop up should be opened")
	public void verifyEditPopUp() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnSaveButton();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.verifyPopUp("Edit");
	}
	
	@Test(dependsOnMethods = "verifyEditPopUp")
	@Documentation(step = "Go to capping rule page and verify data", expected = "Capping rule page should be opened with correct data")
	public void verifyDataFromTable() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.goToCappingRulePageAndVerifyData();
	}
	
	@Test(dependsOnMethods = "verifyDataFromTable")
	@Documentation(step = "Go to copy rule page and add all details", expected = "Application should be able to add all details on copy rule page")
	public void goToCopyCappingRulePageAndUpdateData() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.goToCopyRulePageAndAddDetails();
	}
	
	@Test(dependsOnMethods = "goToCopyCappingRulePageAndUpdateData")
	@Documentation(step = "Verify copy pop up", expected = "Copy pop up should be verified")
	public void verifyCopyPopUp() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnSaveButton();
		OrderingBOBusinessMaintainedTablesPage.verifyPopUp("Copy");
	}
	
	@Test(dependsOnMethods = "verifyCopyPopUp")
	@Documentation(step = "Click on archive and verify archive pop up", expected = "Archive pop up should be opened and verified")
	public void verifyArchivePopUp() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.goToCappingRulePageAndVerifyCopiedData();
		OrderingBOBusinessMaintainedTablesPage.clickOnRowActionAndVeriyArchivePopUp();
	}
	
	@Test(dependsOnMethods = "verifyArchivePopUp")
	@Documentation(step = "Go to archive rule view page", expected = "View page should be opened for archived rule")
	public void goToArchivedViewCappingRulesPageAndVerifyData() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.goToArchivedRulePageAndVerifyDataInTable();
		OrderingBOBusinessMaintainedTablesPage.clickOnRowActionAndGoToArchivedRuleViewPage();
	}
	
	@Test(dependsOnMethods = "goToArchivedViewCappingRulesPageAndVerifyData")
	@Documentation(step = "Verify unarchive pop up", expected = "Unarchive pop up should be verified")
	public void verifyUnArchivedPopUp() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.goBackToCappingRulePage();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.clickOnRuleTab("Archived Rules");
		OrderingBOBusinessMaintainedTablesPage.verifyUnarchivePopUp();
	}
	
	@Test(dependsOnMethods = "verifyUnArchivedPopUp")
	@Documentation(step = "Verify unarchived rule data in active rule page", expected = "Data should be correct on Active rule page")
	public void verifyUnArchivedTableValue() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyUnarchiveDataInActiveRuleTab();
	}
		
	@Test(dependsOnMethods = "verifyUnArchivedTableValue")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality")
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void testLaunchURL() throws Exception {
		OrderingLoginPage.openFOApplication();
		
	}
	
	@Test(dependsOnMethods = "testLaunchURL")
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void goToCappingRulePage() throws Exception {
		OrderingHomePage.clickOnFleetPolicies();
		OrderingHomePage.verifyFleetPoliciesOptionListTitle();
	}
	
	@Test(dependsOnMethods = "goToCappingRulePage")
	@Documentation(step = "Go to capping rule page in FO and verify tiles and labels", expected = "Capping rule page should be opened with correct titles and label") 
	public void verifyCappingRulePageTableData() throws Exception {
		OrderingHomePage.verifyTitlesAndDataInTablesInFo();
	}
	
	@Test(dependsOnMethods = "verifyCappingRulePageTableData")
	@Documentation(step = "Click on rule and go to rule view page", expected = "Rule view page should be opened") 
	public void verifyCappingRuleDetailsPage() throws Exception {
		OrderingHomePage.clickOnRuleFromTableAndGoToViewPageAndVerifyAllFieldsAndData();
	}
	
	@Test(dependsOnMethods = "verifyCappingRuleDetailsPage")
	@Documentation(step = "Go back to capping rule page and logout FO", expected = "Application should be logged out") 
	public void goBackToCappingRulePage() throws Exception {
		OrderingHomePage.clickOnBackToCappingRuleLink();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "goBackToCappingRulePage")
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void launchBOURLBulkOperation() throws Exception {
		OrderingLoginPage.openBOApplication();
	}
	
	@Test(dependsOnMethods = "launchBOURLBulkOperation")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void loginBOApplicationBulkOperation() throws Exception {
		
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "loginBOApplicationBulkOperation")
	@Documentation(step = "Go to capping rule page and select multiple rules", expected = "Application should be able to select multiple rules")
	public void goToCappingRulesBulkOperation() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.goToCappingRulePageAndSelectMultipleRules();
	}
	
	@Test(dependsOnMethods = "goToCappingRulesBulkOperation")
	@Documentation(step = "Click on bulk copy and fill all details", expected = "Application should be able to fill all details")
	public void goToBulkCopyPageAndVerifyFields() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnBulkCopyAndVerifyAllFieldsFromCopyRulePage();
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOBusinessMaintainedTablesPage.fillAllDetailsBulkCopyRulePage();
	}
	
	@Test(dependsOnMethods = "goToBulkCopyPageAndVerifyFields")
	@Documentation(step = "Verify bulk copy pop up", expected = "Bulk copy pop up should be verified")
	public void goToCappingRulesAndVerifyBulkCopiedRules() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnSaveButton();
		OrderingBOBusinessMaintainedTablesPage.verifyPopUp("Copy");
	}
	
	@Test(dependsOnMethods = "goToCappingRulesAndVerifyBulkCopiedRules")
	@Documentation(step = "Go to capping rule page and select multiple rules", expected = "Application should be able to select multiple rules")
	public void goToBulkCopyPageAndSelectMultipleRule() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.goToCappingRulePageAndSelectCopiedMultipleRules();
	}
	
	@Test(dependsOnMethods = "goToBulkCopyPageAndSelectMultipleRule")
	@Documentation(step = "Click on bulk archive and verify pop up", expected = "Bulk archive pop up should be verified")
	public void clickOnArchiveButtonAndVerifyPopUp() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnBulkArchive(CommonPage.getTestData("RuleType"));
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.verifyPopUp("Archive");
	}
	
	@Test(dependsOnMethods = "clickOnArchiveButtonAndVerifyPopUp")
	@Documentation(step = "Verify archived data in Archived tab", expected = "Archived data should be correct in Archived tab")
	public void clickOnArchiveButtonAndVerifyDataInTable() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.goToArchivedTabAndVerifyArchivedDataInTable();
	}
	
	@Test(dependsOnMethods = "clickOnArchiveButtonAndVerifyDataInTable")
	@Documentation(step = "Verify Unarchived pop up", expected = "Unarchived pop up should be verified")
	public void verifyUnarchiveRulesPopUp() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnBulkUnarchive(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.verifyPopUp("Unarchive");
	}
	
	@Test(dependsOnMethods = "verifyUnarchiveRulesPopUp")
	@Documentation(step = "Verify unarchived rule data in Active tab", expected = "Unarchived data should be correct in Active tab")
	public void verifyUnarchiveRulesInActiveTab() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.goToActiveTabAndVerifyUnarchivedDataInTable();
	}
	
	@Test(dependsOnMethods = "verifyUnarchiveRulesInActiveTab")
	@Documentation(step = "Go to copy with multiple rule and multiple client", expected = "Warning pop should be displayed")
	public void verifyMultipleCopyForMultipleClient() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyWarningPopWhenCopyMultipleRuleWithMultipleClient();
	}
	
	@Test(dependsOnMethods = "verifyMultipleCopyForMultipleClient")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunction() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("OK");
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunction", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}