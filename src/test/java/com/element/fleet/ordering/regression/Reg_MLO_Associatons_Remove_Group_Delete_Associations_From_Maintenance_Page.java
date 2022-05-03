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
 * @lastModifiedBy sweety
 */
public class Reg_MLO_Associatons_Remove_Group_Delete_Associations_From_Maintenance_Page extends BaseWebDriver{
	
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
	public void goToAssociationsMenu() throws Exception {
		OrderingBOManagerOrderPreferencesPage.moveToSection("Associations");
	}
	
	@Test(dependsOnMethods = "goToAssociationsMenu")
	@Documentation(step = "Add association from maintenanace page", expected = "Add association should be successful")
	public static void addAssociationFromMaintanance() throws Exception {
		OrderingBOManagerOrderPreferencesPage.verifyAddFromMaintenancePage("Associations");
	}
	
	@Test(dependsOnMethods = "addAssociationFromMaintanance")
	@Documentation(step = "Adds fleet spec group to existing associations", expected = "Fleet spec addition should be succesful")
	public static void addFleetSpecAndDriverGrpFromMaintanance() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Manager Order Preferences");
		OrderingBOManagerOrderPreferencesPage.waitForManagerOrderPreferencesPageToLoaded();
		OrderingBOManagerOrderPreferencesPage.moveToSection("Associations");
		OrderingBOManagerOrderPreferencesPage.searchAndClickAssociation("associations-queue");
		OrderingBOManagerOrderPreferencesPage.selectGroupFromAssocMaintanancePage("Fleet Spec Groups");
		OrderingBOManagerOrderPreferencesPage.selectGroupFromAssocMaintanancePage("Driver Groups");
	}
	
	@Test(dependsOnMethods = "addFleetSpecAndDriverGrpFromMaintanance")
	@Documentation(step = "deletes driver/fleetspec group to existing association", expected = "Driver/fleetspec group should be deleted succesfully")
	public static void deleteFleetSpecAndDrivercGrpFromMaintanance() throws Exception {
		OrderingBOManagerOrderPreferencesPage.deleteGroupFromAssocMaintanancePage("Fleet Spec Groups");
		OrderingBOManagerOrderPreferencesPage.deleteGroupFromAssocMaintanancePage("Driver Groups");
	}
	
	@Test(dependsOnMethods = "deleteFleetSpecAndDrivercGrpFromMaintanance")
	@Documentation(step = "Deletes association from maintenance page", expected = "Association should be added succesfully")
	public static void deleteAssociationFromMaintanance() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Manager Order Preferences");
		OrderingBOManagerOrderPreferencesPage.waitForManagerOrderPreferencesPageToLoaded();
		OrderingBOManagerOrderPreferencesPage.moveToSection("Associations");
		OrderingBOManagerOrderPreferencesPage.verifyDeleteRuleFromMaintenancePage("Associations");
	}

	@Test(dependsOnMethods = "deleteAssociationFromMaintanance", alwaysRun = true)
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		CommonPage.testEnded();
	}

}