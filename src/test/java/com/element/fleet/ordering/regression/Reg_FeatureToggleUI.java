package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOFeatureToggle;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy pdhole
 */
public class Reg_FeatureToggleUI extends BaseWebDriver {
	
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
	@Documentation(step = "Go to Business Maintained Tables page", expected = "Labels on Business Maintained Tables page should be verified")
	public void verifylabelsOnBusinessMaintainedTablesPage() throws Exception {
		OrderingBOFeatureToggle.selectSideMenuBMTOption("Business Maintained Tables");
		OrderingBOFeatureToggle.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "verifylabelsOnBusinessMaintainedTablesPage")
	@Documentation(step = "Go to Feature Toggle page", expected = "Application should load Feature Toggle page")
	public void goToOrderingBOFeatureTogglePage() throws Throwable {
		OrderingBOFeatureToggle.selectBusinessMaintatinedTableOption("Feature Toggle");
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOFeatureToggle.featureTogglesPageLoaded();
		OrderingBOFeatureToggle.clickOnExportButton();
	}
		
	@Test(dependsOnMethods = "goToOrderingBOFeatureTogglePage")
	@Documentation(step = "Click on Add new and add the details", expected = "Feature Toggles should get added")
	public void addNewFeature() throws Exception {
		OrderingBOFeatureToggle.clickOnAddToggle();
		OrderingBOFeatureToggle.addFeatureValues();
		OrderingBOFeatureToggle.clickAddToggleButton();
		OrderingBOFeatureToggle.addToogleValues();
		OrderingBOFeatureToggle.checkToggles();
		OrderingBOFeatureToggle.savingFeatureToggle();
	}
	
	@Test(dependsOnMethods = "addNewFeature")
	@Documentation(step = "Verify the Toggle actions", expected = "Toggle actions should get verified sucessfully")
	public void verifyUIFeature() throws Exception {
		OrderingBOFeatureToggle.openNewWindow();
		OrderingBOFeatureToggle.windowHandleSwitch();
		OrderingBOFeatureToggle.checkToggleAppName();
		OrderingBOFeatureToggle.checkToggleAction();
		OrderingBOFeatureToggle.checkIfWantToDisableToggle();
	}
	
	@Test(dependsOnMethods = "verifyUIFeature")
    @Documentation(step = "Check  if the toggle is placed in it's original position", expected = "Feature Toggles should be in it's original state")
    public void isLocatorPresent() throws Exception {
		OrderingBOFeatureToggle.verifyElementIsRelocated(true);
    }
	
	@Test(dependsOnMethods = "isLocatorPresent", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		OrderingBOFeatureToggle.deleteFeatureToggle();
		CommonPage.testEnded();
	}
	
}