package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOUpfitterPreferencesPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy damodhar
 */
public class Reg_BMT_Upfitter_Preferences_Validation extends BaseWebDriver {

	@BeforeClass
	@Parameters({"xcelerateURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Throwable {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}

	@Test(alwaysRun=true)
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void testLaunchBOURL() throws Throwable {
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Throwable {
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Validate user can change upfitter preference from no preference to Do not send", expected = "User should be able to change upfitter preference from no preference to Do not send")
	public void changeUpfitterPreferenceFromNoPreferenceToDoNotSend() throws Throwable {
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Upfitter Preferences");
		OrderingBOUpfitterPreferencesPage.changeUpfitterPreference(CommonPage.getTestData("UpfitterSearch"), "No Preference", "Do Not Send");
	}
	
	@Test(dependsOnMethods = "changeUpfitterPreferenceFromNoPreferenceToDoNotSend")
	@Documentation(step = "Validate user can change upfitter preference from Do not send to PDF", expected = "User should be able to change upfitter preference from Do not send to PDF")
	public void changeUpfitterPreferenceFromDoNotSendToPDF() throws Throwable {
		OrderingBOUpfitterPreferencesPage.changeUpfitterPreference(CommonPage.getTestData("UpfitterSearch"), "Do Not Send", "PDF");
	}
	
	@Test(dependsOnMethods = "changeUpfitterPreferenceFromDoNotSendToPDF")
	@Documentation(step = "Validate user can change upfitter preference from PDF to Electronic File", expected = "User should be able to change upfitter preference from PDF to Electronic file")
	public void changeUpfitterPreferenceFromPDFToElectronicFile() throws Throwable {
		OrderingBOUpfitterPreferencesPage.changeUpfitterPreference(CommonPage.getTestData("UpfitterSearch"), "PDF", "Electronic File");
	}
	
	@Test(dependsOnMethods = "changeUpfitterPreferenceFromPDFToElectronicFile")
	@Documentation(step = "Validate user can change upfitter from preference Electronic File to Do not send", expected = "User should be able to change upfitter preference from Electronic file to Do not send")
	public void changeUpfitterPreferenceFromElectronicFileToDoNotSend() throws Throwable {
		OrderingBOUpfitterPreferencesPage.changeUpfitterPreference(CommonPage.getTestData("UpfitterSearch"), "Electronic File", "Do Not Send");
	}
	
	@Test(dependsOnMethods = "changeUpfitterPreferenceFromElectronicFileToDoNotSend")
	@Documentation(step = "Validate user can change upfitter preference from Do not send to No Preference", expected = "User should be able to change upfitter preference from Do not send to No Preference")
	public void changeUpfitterPreferenceFromDoNotSendToNoPreference() throws Throwable {
		OrderingBOUpfitterPreferencesPage.changeUpfitterPreference(CommonPage.getTestData("UpfitterSearch"), "Do Not Send", "No Preference");
	}
	
	@Test(dependsOnMethods = "changeUpfitterPreferenceFromDoNotSendToNoPreference")
	@Documentation(step = "Validate user can change upfitter preference from No Preference to PDF", expected = "User should be able to change upfitter preference from No Preference to PDF")
	public void changeUpfitterPreferenceFromNoPreferenceToPDF() throws Throwable {
		OrderingBOUpfitterPreferencesPage.changeUpfitterPreference(CommonPage.getTestData("UpfitterSearch"), "No Preference", "PDF");
	}
	
	@Test(dependsOnMethods = "changeUpfitterPreferenceFromNoPreferenceToPDF")
	@Documentation(step = "Validate user can change upfitter preference from PDF to No Preference", expected = "User should be able to change upfitter preference from PDF to No Preference")
	public void changeUpfitterPreferenceFromPDFToNoPreference() throws Throwable {
		OrderingBOUpfitterPreferencesPage.changeUpfitterPreference(CommonPage.getTestData("UpfitterSearch"), "PDF", "No Preference");
	}
	
	@Test(dependsOnMethods = "changeUpfitterPreferenceFromPDFToNoPreference")
	@Documentation(step = "Validate user can change upfitter preference from No Preference to Electronic File", expected = "User should be able to change upfitter preference from No Preference to Electronic File")
	public void changeUpfitterPreferenceFromNoPreferenceToElectronicFile() throws Throwable {
		OrderingBOUpfitterPreferencesPage.changeUpfitterPreference(CommonPage.getTestData("UpfitterSearch"), "No Preference", "Electronic File");
	}
	
	@Test(dependsOnMethods = "changeUpfitterPreferenceFromNoPreferenceToElectronicFile")
	@Documentation(step = "Validate user can change upfitter preference from Electronic File to No Preference", expected = "User should be able to change upfitter preference from Electronic File to No Preference")
	public void changeUpfitterPreferenceFromElectronicFileToNoPreference() throws Throwable {
		OrderingBOUpfitterPreferencesPage.changeUpfitterPreference(CommonPage.getTestData("UpfitterSearch"), "Electronic File", "No Preference");
	}
	
	@Test(dependsOnMethods = "changeUpfitterPreferenceFromElectronicFileToNoPreference")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Throwable {
		CommonPage.testEnded();
	}
	
}