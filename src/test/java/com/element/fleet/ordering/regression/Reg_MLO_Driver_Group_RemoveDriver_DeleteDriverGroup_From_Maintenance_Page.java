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
 * @lastModifiedBy Sweety
 */
public class Reg_MLO_Driver_Group_RemoveDriver_DeleteDriverGroup_From_Maintenance_Page extends BaseWebDriver {

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
	public void goToDriverGroupsSection() throws Exception {
		OrderingBOManagerOrderPreferencesPage.moveToSection("Driver Groups");
	}	
	
	@Test(dependsOnMethods = "goToDriverGroupsSection")
	@Documentation(step = "Go to Driver Group tab", expected = "Driver Group Page should be loaded")
	public void goToDriverGroupsMenu() throws Exception {
		OrderingBOManagerOrderPreferencesPage.clickOnAddGroupButton();
		OrderingBOManagerOrderPreferencesPage.addDriverGroupPageLoaded();
	}	
	
	@Test(dependsOnMethods = "goToDriverGroupsMenu")
	@Documentation(step = "Enter Driver Group details", expected = "Driver Group details entered")
	public static void enterDriverGroupDetails() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterDriverGroupDetails();
	}
	
	@Test(dependsOnMethods = "enterDriverGroupDetails")
	@Documentation(step = "Save Driver Group details", expected = "Driver Group details saved")
	public static void saveNewDriverGroup() throws Exception {
		OrderingBOManagerOrderPreferencesPage.clickOnSaveDriverGroupDetails();
		OrderingBOManagerOrderPreferencesPage.checkAlertPopUpConfimationMessage("Driver Group - Save From Maintenance View");
		OrderingBOManagerOrderPreferencesPage.addDriverGroupPageLoaded();
	}
	
	@Test(dependsOnMethods = "saveNewDriverGroup")
	@Documentation(step = "Go back to the manager order preference page and click driver group tab", expected = "Driver Group section should get loaded")
	public void navigateBackToDriverGroupListViewPage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Manager Order Preferences");
		OrderingBOManagerOrderPreferencesPage.waitForManagerOrderPreferencesPageToLoaded();
		OrderingBOManagerOrderPreferencesPage.moveToSection("Driver Groups");
		OrderingBOManagerOrderPreferencesPage.searchSelectClickOnTheDriverGroup();
		OrderingBOManagerOrderPreferencesPage.addDriverGroupPageLoaded();
	}
	
	@Test(dependsOnMethods = "navigateBackToDriverGroupListViewPage")
	@Documentation(step = "Click on Add button and enter new Driver Group details ", expected = "New Driver Group details is enetered")
	public void deleteDriverGroupFromMaintenanceView() throws Exception {
		OrderingBOManagerOrderPreferencesPage.clickDeleteButtonFromDriverGroupMaintenencePage();
		OrderingBOManagerOrderPreferencesPage.verifyPopUpMessage("Driver Group - Delete From Maintenance View");
		OrderingBOManagerOrderPreferencesPage.popUpAction("Yes");
		OrderingBOManagerOrderPreferencesPage.checkAlertPopUpConfimationMessage("Driver Group - Delete From Maintenance View");
		OrderingBOManagerOrderPreferencesPage.verifyNoDriverGroupPresent();
	}
	
	@Test(dependsOnMethods = "deleteDriverGroupFromMaintenanceView", alwaysRun = true)
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		CommonPage.testEnded();
	}
	
}