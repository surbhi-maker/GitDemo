package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBatchProcessingDriverGroupPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy Sweety
 */
public class Reg_MLO_Batch_Processing_Verification extends BaseWebDriver {

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
		CommonPage.testStarted();
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Exception {
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Validation of Batch process side menu options title", expected = "Title validation should be succcessful")
	public void titleValidationBatchProcessingMenuOptions() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Batch Processing");
		OrderingBOHomePage.batchProcessingSideMenuOptionsLoaded();
	}
	
	@Test(dependsOnMethods = "titleValidationBatchProcessingMenuOptions")
	@Documentation(step = "Go to Create Driver Group page", expected = "Create Driver Group page should be loaded")
	public void goToCreateDriverGroupPage() throws Exception {
		OrderingBOHomePage.selectBatchProcessingOptions("Driver Group Maintenance");
		OrderingBOBatchProcessingDriverGroupPage.batchProcessingDriverGroupMaintenanceUploadOptionlValidation();
	}
	
	@Test(dependsOnMethods = "goToCreateDriverGroupPage")
	@Documentation(step = "Download Create Driver Group Batch Template", expected = "Create Driver Group Batch Template should be downloaded successfully")
	public void downloadAddBatchTemplate() throws Throwable {
		OrderingBOBatchProcessingDriverGroupPage.downloadDriverGroupMaintenanceTemplate("DriverGroupMaintenance-mass update Template");
		OrderingBOBatchProcessingDriverGroupPage.downloadResult(this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "downloadAddBatchTemplate")
	@Documentation(step = "Add Data in Create Driver Group Batch Excel Template", expected = "Data should get added successfully")
	public void addDataInCreateDriverGroupBatchTemplate() throws Exception {
		OrderingBOBatchProcessingDriverGroupPage.downloadDriverGroupMassUpdateTemplate();
	}
	
	@Test(dependsOnMethods = "addDataInCreateDriverGroupBatchTemplate")
	@Documentation(step = "Upload Create Driver Group Batch Template", expected = "Create Driver Group Batch Template should be uploaded successfully")
	public void uploadAddBatchTemplate() throws Exception {
		OrderingBOBatchProcessingDriverGroupPage.uploadDriverGroupMaintenanceTemplate(driver);
	}		
	
	@Test(dependsOnMethods = "uploadAddBatchTemplate")
	@Documentation(step = "Go to Manager Order Preferences  page", expected = " Manager Order Preferences page should be loaded")
	public void goToManagerOrderPreferencesPage() throws Exception {
		System.out.println("Navigate to Manager Order Preferences page");
		OrderingBOHomePage.selectSideMenuOption("Manager Order Preferences");
		OrderingBOManagerOrderPreferencesPage.waitForManagerOrderPreferencesPageToLoaded();
	}
	
	@Test(dependsOnMethods = "goToManagerOrderPreferencesPage")
	@Documentation(step = "Enter corp and client and then verify page", expected = "Corp and client have been entered and page is verified")
	public void enterCorpAndClientAndVerify() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterCorpAndClientAndSelectClient();
	}
	
	@Test(dependsOnMethods = "enterCorpAndClientAndVerify")
	@Documentation(step = "Go to Driver Groups tab", expected = "Driver Groups section should be loaded")
	public void goToDriverGroupsMenu() throws Exception {
		System.out.println("Clicking on Driver Groups menu");
		OrderingBOManagerOrderPreferencesPage.moveToSection("Driver Groups");
	}	

	@Test(dependsOnMethods = "goToDriverGroupsMenu")
	@Documentation(step = "Search and verify created Driver Group", expected = "Created Driver Group should be returned on search")
	public void logoutBOSuperUser() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}	
	
	@Test(dependsOnMethods = "logoutBOSuperUser")
	@Documentation(step = "Search and verify created Driver Group", expected = "Created Driver Group should be returned on search")
	public void loginBOWithExternalUser() throws Exception {
		OrderingLoginPage.openBOApplication();
		
		OrderingBOHomePage.orderingBOHomePageLoaded();
		OrderingBOBatchProcessingDriverGroupPage.verifyExternalUserCannotAccessBatchProcessing();
		
	}	
	
	@Test(alwaysRun = true, dependsOnMethods = "loginBOWithExternalUser")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
	
}