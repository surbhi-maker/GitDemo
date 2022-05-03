package com.element.fleet.ordering.sanity;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy pdhole
 */
public class Sanity_Business_Maintained_Tables extends BaseWebDriver {

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
		OrderingBOHomePage.boHomePageLabelValidation();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Go to Business Maintained Tables page", expected = "Labels on Business Maintained Tables page should be verified")
	public void verifylabelsOnBusinessMaintainedTablesPage() throws Exception {
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLabelValidation();
	}
	
	@Test(dependsOnMethods = "verifylabelsOnBusinessMaintainedTablesPage")
	@Documentation(step = "Go to Additional Options page", expected = "Application should load Additional Options page")
	public void goToAdditionalOptionsPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Additional Options");
		OrderingBOBusinessMaintainedTablesPage.additionalOptionsPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.additionalOptionsPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Additional Options");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToAdditionalOptionsPage")
	@Documentation(step = "Go to Asset Code page", expected = "Application should load Asset Code page")
	public void goToAssetCodePage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Asset Code");
		OrderingBOBusinessMaintainedTablesPage.assetCodePageLoaded();
		OrderingBOBusinessMaintainedTablesPage.assetCodePageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Asset Code");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToAssetCodePage")
	@Documentation(step = "Go to Dealer Installed Options page", expected = "Application should load Dealer Installed Options page")
	public void goToDealerInstalledOptionsPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Dealer Installed Options");
		OrderingBOBusinessMaintainedTablesPage.dealerInstalledOptionsPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.dealerInstalledOptionsPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Dealer Installed Options");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToDealerInstalledOptionsPage")
	@Documentation(step = "Go to Element FIN/FAN codes page", expected = "Application should load Element FIN/FAN Codes page")
	public void goToElementFINFANCodesPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Element FIN/FAN Codes");
		OrderingBOBusinessMaintainedTablesPage.elementFINFANCodesPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.elementFINFANCodesPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Element FIN/FAN Codes");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToElementFINFANCodesPage")
	@Documentation(step = "Go to Emissions page", expected = "Application should load Emissions page")
	public void goToEmissionsPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Emissions");
		OrderingBOBusinessMaintainedTablesPage.emissionsPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.emissionsPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Emissions");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToEmissionsPage")
	@Documentation(step = "Go to Factory Order range page", expected = "Application should load Factory Order Range page")
	public void goToFactoryOrderRangePage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Factory Order Range");
		OrderingBOBusinessMaintainedTablesPage.factoryOrderNumberRangeTablePageLoaded();
		OrderingBOBusinessMaintainedTablesPage.factoryOrderNumberRangeTablePageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Factory Order Range");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToFactoryOrderRangePage")
	@Documentation(step = "Go to Feature Toggle page", expected = "Application should load Feature Toggle page")
	public void goToFeatureTogglePage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Feature Toggle");
		OrderingBOBusinessMaintainedTablesPage.featureTogglesPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.featureTogglesPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Feature Toggle");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToFeatureTogglePage")
	@Documentation(step = "Go to FIN/FAN page", expected = "Application should load FIN/FAN page")
	public void goToFINFANPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("FIN/FAN");
		OrderingBOBusinessMaintainedTablesPage.finfanCodesPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.finfanCodesPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("FIN/FAN");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToFINFANPage")
	@Documentation(step = "Go to Fleet Assignment page", expected = "Application should load Fleet Assignment page")
	public void goToFleetAssignmentPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Client Assignment");
		OrderingBOBusinessMaintainedTablesPage.fleetAssignmentPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.fleetAssignmentPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Client Assignment");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToFleetAssignmentPage")
	@Documentation(step = "Go to Hard-coded Instructions page", expected = "Application should load Hard-coded Instructions page")
	public void goToHardCodedInstructionsPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Hard-coded Instructions");
		OrderingBOBusinessMaintainedTablesPage.hardcodedInstructionsPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.hardcodedInstructionsPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Hard-coded Instructions");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToHardCodedInstructionsPage")
	@Documentation(step = "Go to Incentives page", expected = "Application should load Incentives page")
	public void goToIncentivesPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Incentives");
		OrderingBOBusinessMaintainedTablesPage.incentivesPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.incentivesPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Incentives");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToIncentivesPage")
	@Documentation(step = "Go to Master Purchase Order Status page", expected = "Application should load Master Purchase Order Status page")
	public void goToMasterPurchaseOrderStatusPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Master Purchase Order Status");
		OrderingBOBusinessMaintainedTablesPage.masterPurchaseOrderStatusPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.masterPurchaseOrderStatusPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Master Purchase Order Status");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToMasterPurchaseOrderStatusPage")
	@Documentation(step = "Go to Order Hold page", expected = "Application should load Order Hold page")
	public void goToOrderHoldPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Order Hold");
		OrderingBOBusinessMaintainedTablesPage.orderHoldPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.orderHoldPagelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Order Hold");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToOrderHoldPage")
	@Documentation(step = "Go to Price Protection page", expected = "Application should load Price Protection page")
	public void goToPriceProtectionPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Price Protection");
		OrderingBOBusinessMaintainedTablesPage.priceProtectionPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.priceProtectionPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Price Protection");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToPriceProtectionPage")
	@Documentation(step = "Go to Scheduler page", expected = "Application should load Scheduler page")
	public void goToSchedulerPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Scheduler");
		OrderingBOBusinessMaintainedTablesPage.schedulerPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.schedulerPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Scheduler");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();	
	}

	@Test(dependsOnMethods = "goToSchedulerPage")
	@Documentation(step = "Go to Sweeper Results page", expected = "Application should load Sweeper Results page")
	public void goToSweeperResultsPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Sweeper Results");
		OrderingBOBusinessMaintainedTablesPage.sweeperResultsPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.sweeperResultsPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Sweeper Results");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToSweeperResultsPage")
	@Documentation(step = "Go to Transporters page", expected = "Application should load Transporters page")
	public void goToTransportersPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Transporters");
		OrderingBOBusinessMaintainedTablesPage.transportersPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.transportersPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Transporters");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToTransportersPage")
	@Documentation(step = "Go to Upfitter Preferences page", expected = "Application should load Upfitter Preferences page")
	public void goToUpfitterPreferencesPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Upfitter Preferences");
		OrderingBOBusinessMaintainedTablesPage.upfitterPreferencesPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.upfitterPreferencesPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Upfitter Preferences");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToUpfitterPreferencesPage")
	@Documentation(step = "Go to Year Make Model page", expected = "Application should load Year Make Model page")
	public void goToYearMakeModelPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Year Make Model");
		OrderingBOBusinessMaintainedTablesPage.yearMakeModelPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.yearMakeModelPageValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.searchOnYearMakeModelPage(CommonPage.getTestData("Corp").trim(),CommonPage.getTestData("SearchField").trim(),CommonPage.getTestData("Manufacturer").trim());
		OrderingBOBusinessMaintainedTablesPage.clickOnTheFirstOptionOfListIfAvailable("Year Make Model");
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(alwaysRun = true, dependsOnMethods = "goToYearMakeModelPage")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}

}
