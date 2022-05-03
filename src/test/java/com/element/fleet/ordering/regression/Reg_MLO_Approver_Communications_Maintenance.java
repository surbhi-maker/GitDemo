package com.element.fleet.ordering.regression;
	
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

/**
 * @lastModifiedBy Sweety
 */
public class Reg_MLO_Approver_Communications_Maintenance extends BaseWebDriver {

	@BeforeClass
	@Parameters({ "xcelerateURL", "username", "applicationURL", "applicationBOURL", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
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
    @Documentation(step = "Launch Back office and navigate to Manager_Order_Prefrences  ", expected = "The application should load On Order queue page")
    @Parameters({ "applicationBOURL", "username" })
    public void testLaunchBOURL(String applicationBOURL, String username) throws Exception {
        CommonPage.testStarted();
        WebDriverAccess.getDriver().get(applicationBOURL);
        WebDriverAccess.getDriver().manage().window().maximize();
        CommonPage.getBrowserInfo(WebDriverAccess.getDriver());
        OrderingLoginPage.waitForBOUserLoginPage();
        OrderingLoginPage.enterBOUsername(username);
        OrderingLoginPage.enterBOPassword(CommonPage.getCredetialsData(username));
    }
    
    @Test(dependsOnMethods = "testLaunchBOURL")
    @Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
    @Parameters({ "username", "OrderingDataFilePath", "CredentialFileSheetname" })
    public void testLoginBOApplication() throws Exception {       
        OrderingBOHomePage.orderingBOHomePageLoaded();
    }
    
    @Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Go to Manager Order Preferences  page", expected = " Manager Order Preferences page should be loaded")
	public void goToManagerOrderPreferencesPage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Manager Order Preferences");
		OrderingBOManagerOrderPreferencesPage.waitForManagerOrderPreferencesPageToLoaded();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
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
		OrderingBOManagerOrderPreferencesPage.clickSaveButton();
		OrderingBOManagerOrderPreferencesPage.verifyPopUpMessage("Approval Rules - Save");
		OrderingBOManagerOrderPreferencesPage.popUpAction("Yes");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
    @Test(dependsOnMethods = "enterApprovalRuleDetails")
    @Documentation(step = "Go to Manager Order Preferences side menu tab", expected = "Manager Order Preferences side menu options should be loaded")
    public void goToApproverCommunicationsMenu() throws Exception {
    	OrderingBOHomePage.selectSideMenuOption("Manager Order Preferences");
    	OrderingBOManagerOrderPreferencesPage.moveToSection("Approver Communications");                       
    }
         
	@Test(dependsOnMethods = "goToApproverCommunicationsMenu")
	@Documentation(step = "Verify Approver Communications navigation to maintenance", expected = "All the elements should be loaded")
	public void navigateToApproverCommunicationsMaintenance() throws Exception {
		OrderingBOManagerOrderPreferencesPage.searchAndClickApproverCommRule("approver-communications-queue", CommonPage.getTestData("ApprovalRuleName"));
	}

	@Test(dependsOnMethods = "navigateToApproverCommunicationsMaintenance")
	@Documentation(step = "Verify Approver Communications List", expected = "All the elements should be loaded")
	public void verifyBackToApproverCommunicationMaintenance() throws Exception {
		OrderingBOManagerOrderPreferencesPage.clickPreferenceToggleButton("Approved Orders Daily");
		OrderingBOManagerOrderPreferencesPage.clickSaveButton();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOManagerOrderPreferencesPage.clickApproverCommBackButton();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOManagerOrderPreferencesPage.fleetSpecGroupsSectionValidation();
	}
		
	@Test(dependsOnMethods = "verifyBackToApproverCommunicationMaintenance", alwaysRun = true)
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		CommonPage.testEnded();
	}
}		
