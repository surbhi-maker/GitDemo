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
 * @lastModifiedBy shivamsr
 */
public class Reg_MLO_Fleet_Spec_Group_Add_Delete_From_Maintenance_Page extends BaseWebDriver {

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
	@Documentation(step = "Go to Fleet Spec tab", expected = "Fleet Spec Page should be loaded")
	public void goToFleetSpecSection() throws Exception {
		OrderingBOManagerOrderPreferencesPage.moveToSection("Fleet Spec Groups");
	}	
	
	@Test(dependsOnMethods = "goToFleetSpecSection")
	@Documentation(step = "Create fleet spec from maintenanc page", expected = "Fleet spec creation should be succesful")
	public void createFleetSpecFrmMatncPage() throws Exception {
		OrderingBOManagerOrderPreferencesPage.verifyAddFromMaintenancePage("Fleet Spec Groups");
	}
	
	@Test(dependsOnMethods = "createFleetSpecFrmMatncPage")
	@Documentation(step = "Delete fleet spec from maintenance page", expected = "Fleet spec should be deleted succesfully")
	public void deleteAndVerifyFleetSpecFrmMaintPage() throws Exception {
		OrderingBOManagerOrderPreferencesPage.verifyDeleteRuleFromMaintenancePage("Fleet Spec Groups");
	}
	
	@Test(dependsOnMethods = "deleteAndVerifyFleetSpecFrmMaintPage", alwaysRun = true)
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		CommonPage.testEnded();
	}
	
}