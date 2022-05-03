package com.element.fleet.ordering.regression;

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
 * @lastModifiedBy vinay
 */
public class Reg_Business_Maintained_Tables extends BaseWebDriver {

	String corpCode="FA";
	String clientNumber="1791"; 
	String additionalOptionDesc="Desc";
	String optionPrice="120"; 
	String estActPrice="A";
	String estActPriceUpdated="E"; 
	String ecCode="Striping"; 
	String productClass="Equipment"; 
	String productClassUpdated="Forklift"; 
	String brandProvider="Autotest"; 
	String dioDescription="testDesc"; 
	String brandProviderUpdated="AutotestUpdated"; 
	String ecCodeDIO="Body";
	String maximumPrice="100"; 
	String manufacturer="Jaguar";
	String rentalIndicator="Y"; 
	String year="2020";
	String model="F-TYPE"; 
	String emissionCode="E1791";
	String emissionReigon="California";
	String updatedEmissionCode="EU1791";
	String modelCode="0210";
	String rangeStart="1780";
	String featureName="AAATestToggle";
	String featureDescription="TestToggle";
	String featureType="UI";
	String featureValue="{ \"face\": \"test\" }";
	String parentGroup="UI_Group";
	String updatedFeatureValue="{​\"firstName\":\"John\"}";
	String role="Manager";
	String primaryUserFullName="Adeniran Mathew (madenir)";
	String primaryUser="Adeniran Mathew";
	String incentive="IC1791";
	String incentiveDesc="TestDesc";
	String incentiveDescUpdated="TestDescUpdated";
	String majorCode="AA";
	String trim="Checkered Flag Limited Edition||2dr All-wheel Drive Convertible";
	String pricingScheme="pricingScheme";
	String schedulerName="Volvo Order Transmission";
	String bridging="Bridging";
	String runType="Bridge";
	String cornExpression="* */5 * * * ?";
	String method="POST";
	String cornExpressionUpdated="* */10 * * * ?";
	String frequency="every 10 minutes";
	String maximumPriceUpdated="100.00";
	
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
	}
	
	@Test(dependsOnMethods = "verifylabelsOnBusinessMaintainedTablesPage")
	@Documentation(step = "Go to Additional Options page", expected = "Application should load Additional Options page")
	public void goToAdditionalOptionsPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Additional Options");
		OrderingBOBusinessMaintainedTablesPage.additionalOptionsPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfAdditionalOptionExists(corpCode, clientNumber, clientNumber);
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createAdditionalOption(corpCode, clientNumber, clientNumber, additionalOptionDesc, optionPrice, estActPrice, ecCode);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedAdditionalOption(corpCode, clientNumber, clientNumber);
		OrderingBOBusinessMaintainedTablesPage.verifyTheValuesOfRecordsCreated(corpCode, clientNumber, clientNumber, additionalOptionDesc, optionPrice, estActPrice, ecCode);
		OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueLink();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedAdditionalOption(corpCode, clientNumber, clientNumber);
		OrderingBOBusinessMaintainedTablesPage.updateAdditionalOption(estActPriceUpdated, corpCode, clientNumber, clientNumber);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createAdditionalOption(corpCode, clientNumber, clientNumber, additionalOptionDesc, optionPrice, estActPriceUpdated, ecCode);
		OrderingBOBusinessMaintainedTablesPage.searchCreatedAdditionalOption(corpCode, clientNumber, clientNumber);
		OrderingBOBusinessMaintainedTablesPage.verifyDuplicateEntryIsAddedAsNewRow();
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.deleteAdditionalOption(corpCode, clientNumber, clientNumber);
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToAdditionalOptionsPage")
	@Documentation(step = "Go to Asset Code page", expected = "Application should load Asset Code page")
	public void goToAssetCodePage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Asset Code");
		OrderingBOBusinessMaintainedTablesPage.assetCodePageLoaded();
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfAssetCodeExists(clientNumber, clientNumber, productClass);
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfAssetCodeExists(clientNumber, clientNumber, productClassUpdated);
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createAssetCode(clientNumber, clientNumber, productClass);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedAssetCode(clientNumber, clientNumber, productClass);
		OrderingBOBusinessMaintainedTablesPage.verifyTheValuesOfRecordCreatedAssetCode(clientNumber, clientNumber, productClass);
		OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueLink();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedAssetCode(clientNumber, clientNumber, productClass);
		OrderingBOBusinessMaintainedTablesPage.updateAssetCode(clientNumber, clientNumber, productClassUpdated);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.duplicateAssetCodeCannotBeAdded(clientNumber, clientNumber, productClassUpdated);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedAssetCode(clientNumber, clientNumber, productClassUpdated);
		OrderingBOBusinessMaintainedTablesPage.deleteAssetCode(clientNumber, clientNumber, productClassUpdated);
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();	
	}

	@Test(dependsOnMethods = "goToAssetCodePage")
	@Documentation(step = "Go to Dealer Installed Options page", expected = "Application should load Dealer Installed Options page")
	public void goToDealerInstalledOptionsPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Dealer Installed Options");
		OrderingBOBusinessMaintainedTablesPage.dealerInstalledOptionsPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfDIOExists(clientNumber, brandProvider, dioDescription);
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfDIOExists(clientNumber, brandProviderUpdated, dioDescription);
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createDIO(clientNumber, brandProvider, dioDescription, ecCodeDIO, maximumPrice);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedDIO(clientNumber, brandProvider, dioDescription);
		OrderingBOBusinessMaintainedTablesPage.verifyTheValuesOfRecordCreatedDIO(clientNumber, brandProvider, dioDescription, ecCodeDIO, maximumPriceUpdated);
		OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueLink();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedDIO(clientNumber, brandProvider, dioDescription);
		OrderingBOBusinessMaintainedTablesPage.updateDIO(clientNumber, brandProviderUpdated, dioDescription);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.verifyDuplicatDIOCannotBeAdded(clientNumber, brandProviderUpdated, dioDescription, ecCodeDIO, maximumPrice);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedDIO(clientNumber, brandProviderUpdated, dioDescription);
		OrderingBOBusinessMaintainedTablesPage.deleteDIO(clientNumber, brandProviderUpdated, dioDescription);
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToDealerInstalledOptionsPage")
	@Documentation(step = "Go to Element FIN/FAN codes page", expected = "Application should load Element FIN/FAN Codes page")
	public void goToElementFINFANCodesPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Element FIN/FAN Codes");
		OrderingBOBusinessMaintainedTablesPage.elementFINFANCodesPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfBMTFinFanExists(corpCode, manufacturer, clientNumber);
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createBMTFinFan(corpCode, manufacturer, clientNumber);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedBMTFinFan(corpCode, manufacturer, clientNumber);
		OrderingBOBusinessMaintainedTablesPage.verifyTheValuesOfRecordCreatedBMTFinFan(corpCode, manufacturer, clientNumber);
		OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueLink();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.verifyDuplicateBMTFinFanCannotBeAdded(corpCode, manufacturer, clientNumber);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedBMTFinFan(corpCode, manufacturer, clientNumber);
		OrderingBOBusinessMaintainedTablesPage.updateBMTFinFan(corpCode, manufacturer, clientNumber, rentalIndicator);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.deleteBMTFinFan(corpCode, manufacturer, clientNumber);
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToElementFINFANCodesPage")
	@Documentation(step = "Go to Emissions page", expected = "Application should load Emissions page")
	public void goToEmissionsPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Emissions");
		OrderingBOBusinessMaintainedTablesPage.emissionsPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfEmissionExists(corpCode, year, manufacturer, manufacturer, model);
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createEmissions(corpCode, year, manufacturer, manufacturer, model, emissionCode, emissionReigon);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.checkAlert();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedEmission(corpCode, year, manufacturer, manufacturer, model);
		OrderingBOBusinessMaintainedTablesPage.verifyTheValuesOfRecordCreatedEmission(corpCode, year, manufacturer, manufacturer, model, emissionCode, emissionReigon);
		OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueLink();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.verifyDuplicateEmissionCannotBeAdded(corpCode, year, manufacturer, manufacturer, model, emissionCode, emissionReigon);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedEmission(corpCode, year, manufacturer, manufacturer, model);
		OrderingBOBusinessMaintainedTablesPage.updateEmission(corpCode, year, manufacturer, manufacturer, model, updatedEmissionCode);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.deleteEmission(corpCode, year, manufacturer, manufacturer, model);
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToEmissionsPage")
	@Documentation(step = "Go to Factory Order range page", expected = "Application should load Factory Order Range page")
	public void goToFactoryOrderRangePage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Factory Order Range");
		OrderingBOBusinessMaintainedTablesPage.factoryOrderNumberRangeTablePageLoaded();
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfFActoryOrderRangeExists(corpCode, manufacturer, manufacturer, modelCode);
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createFactoryOrderRange(corpCode, manufacturer, manufacturer, modelCode, clientNumber);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.checkAlert();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedFactoryOrderRange(corpCode, manufacturer, manufacturer, modelCode);
		OrderingBOBusinessMaintainedTablesPage.verifyTheValuesOfRecordCreatedFactoryOrderRange(corpCode, manufacturer, manufacturer, modelCode, clientNumber);
		OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueLink();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.verifyDuplicateFactoryOrderRangeCannotBeAdded(corpCode, manufacturer, manufacturer, modelCode, clientNumber);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedFactoryOrderRange(corpCode, manufacturer, manufacturer, modelCode);
		OrderingBOBusinessMaintainedTablesPage.updateFactoryOrderRange(corpCode, manufacturer, manufacturer, modelCode, rangeStart);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.deleteFactoryOrderRange(corpCode, manufacturer, manufacturer, modelCode);
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToFactoryOrderRangePage")
	@Documentation(step = "Go to Feature Toggle page", expected = "Application should load Feature Toggle page")
	public void goToFeatureTogglePage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Feature Toggle");
		OrderingBOBusinessMaintainedTablesPage.featureTogglesPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.deleteFeatureToggle(featureName);
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createFeatureToggles(featureName, featureDescription, featureType, featureValue, parentGroup);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.checkAlert();
		OrderingBOBusinessMaintainedTablesPage.verifyTheValuesOfRecordCreatedFeatureToggles(featureName, featureDescription, featureType, parentGroup);
		OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueLink();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.verifyDuplicateFeatureToggleCannotBeAdded(featureName, featureDescription, featureType, updatedFeatureValue, parentGroup);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.updateFeatureToggle(featureName, "TestToggleUpdated", featureType, updatedFeatureValue, parentGroup);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.deleteFeatureToggle(featureName);
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToFeatureTogglePage")
	@Documentation(step = "Go to Fleet Assignment page", expected = "Application should load Fleet Assignment page")
	public void goToFleetAssignmentPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Fleet Assignment");
		OrderingBOBusinessMaintainedTablesPage.fleetAssignmentPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfFleetAssignmentExists(corpCode, clientNumber, role, primaryUser);
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createFleetAssignment(corpCode, clientNumber, role, primaryUser);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.checkAlert();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedFleetAssignment(corpCode, clientNumber, role, primaryUser);
		OrderingBOBusinessMaintainedTablesPage.verifyTheValuesOfRecordCreatedFleetAssignment(corpCode, clientNumber, role, primaryUserFullName);
		OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueLink();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.verifyDuplicateFleetAssignmentCannotBeAdded(corpCode, clientNumber, role, primaryUser);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.deleteFleetAsignment(corpCode, clientNumber, role, primaryUser);
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToFleetAssignmentPage")
	@Documentation(step = "Go to Incentives page", expected = "Application should load Incentives page")
	public void goToIncentivesPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Incentives");
		OrderingBOBusinessMaintainedTablesPage.incentivesPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfIncentiveExists(corpCode, clientNumber, year, manufacturer, incentive);
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createIncentive(corpCode, clientNumber, year, manufacturer,manufacturer, incentive, incentiveDesc);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.checkAlert();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedIncentive(corpCode, clientNumber, year, manufacturer, incentive);
		OrderingBOBusinessMaintainedTablesPage.verifyTheValuesOfRecordCreatedIncentive(corpCode, clientNumber, year, manufacturer, incentive, incentiveDesc);
		OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueLink();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.verifyDuplicateIncentiveCannotBeAdded(corpCode, clientNumber, year, manufacturer,manufacturer, incentive, incentiveDesc);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.updateIncentive(corpCode, clientNumber, year, manufacturer, incentive, incentiveDescUpdated);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.deleteIncentive(corpCode, clientNumber, year, manufacturer, incentive);
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToIncentivesPage")
	@Documentation(step = "Go to Master Purchase Order Status page", expected = "Application should load Master Purchase Order Status page")
	public void goToMasterPurchaseOrderStatusPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Master Purchase Order Status");
		OrderingBOBusinessMaintainedTablesPage.masterPurchaseOrderStatusPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfMPOSExists(corpCode, manufacturer, manufacturer, majorCode, incentiveDesc);
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfMPOSExists(corpCode, manufacturer, manufacturer, majorCode, incentiveDescUpdated);
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createMPOS(corpCode, manufacturer, manufacturer, majorCode, incentiveDesc);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.checkAlert();
		OrderingBOBusinessMaintainedTablesPage.searchCreatedMPOS(corpCode, manufacturer, manufacturer, majorCode, incentiveDesc);
		OrderingBOBusinessMaintainedTablesPage.verifyTheValuesOfRecordCreatedMPOS(corpCode, manufacturer, manufacturer, majorCode, incentiveDesc);
		OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueLink();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.verifyDuplicateMPOSCannotBeAdded(corpCode, manufacturer, manufacturer, majorCode, incentiveDesc);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.updateMPOS(corpCode, manufacturer, manufacturer, majorCode, incentiveDescUpdated);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.deleteMPOS(corpCode, manufacturer, manufacturer, majorCode, incentiveDescUpdated);
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToMasterPurchaseOrderStatusPage")
	@Documentation(step = "Go to Order Hold page", expected = "Application should load Order Hold page")
	public void goToOrderHoldPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Order Hold");
		OrderingBOBusinessMaintainedTablesPage.orderHoldPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfOrderHoldExists(corpCode, year, manufacturer, manufacturer, model);
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createOrderHold(corpCode, year, manufacturer, manufacturer, model);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.checkAlert();
		OrderingBOBusinessMaintainedTablesPage.searchOrderHold(corpCode, year, manufacturer, manufacturer, model);
		OrderingBOBusinessMaintainedTablesPage.verifyTheValuesOfRecordCreatedOrderHold(corpCode, year, manufacturer, manufacturer, model);
		OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueLink();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.verifyDuplicateOrderHoldCanBeAdded(corpCode, year, manufacturer, manufacturer, model);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.deleteOrderHold(corpCode, year, manufacturer, manufacturer, model);
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}

	@Test(dependsOnMethods = "goToOrderHoldPage")
	@Documentation(step = "Go to Price Protection page", expected = "Application should load Price Protection page")
	public void goToPriceProtectionPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Price Protection");
		OrderingBOBusinessMaintainedTablesPage.priceProtectionPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfPriceProtectionExists(corpCode, clientNumber, year, manufacturer, manufacturer, model);
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createPriceProtection(corpCode, clientNumber, year, manufacturer, manufacturer, model, trim);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.checkAlert();
		OrderingBOBusinessMaintainedTablesPage.searchPriceProtection(corpCode, clientNumber, year, manufacturer, manufacturer, model);
		OrderingBOBusinessMaintainedTablesPage.verifyTheValuesOfRecordCreatedPriceProtection(corpCode, clientNumber, year, manufacturer, manufacturer, model, trim);
		OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueLinkPriceProtection();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.verifyDuplicatePriceProtectionCannotBeAdded(corpCode, clientNumber, year, manufacturer, manufacturer, model, trim);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.updatePriceProtection(corpCode, clientNumber, year, manufacturer, manufacturer, model, "Invoice,MSRP");
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.deletePriceProtection(corpCode, clientNumber, year, manufacturer, manufacturer, model);
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToPriceProtectionPage")
	@Documentation(step = "Go to Scheduler page", expected = "Application should load Scheduler page")
	public void goToSchedulerPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Scheduler");
		OrderingBOBusinessMaintainedTablesPage.schedulerPageLoaded();
		OrderingBOBusinessMaintainedTablesPage.searchAndDeleteIfSchedulerExists(schedulerName, bridging);
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.createScheduler(runType, schedulerName, cornExpression, method);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.checkAlert();
		OrderingBOBusinessMaintainedTablesPage.searchScheduler(schedulerName, bridging);
		OrderingBOBusinessMaintainedTablesPage.verifyTheValuesOfRecordCreatedScheduler(runType, schedulerName, cornExpression, method);
		OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueLink();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		OrderingBOBusinessMaintainedTablesPage.verifyDuplicateSchedulerCannotBeAdded(runType, schedulerName, cornExpression, method);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.updateScheduler(schedulerName, bridging, cornExpressionUpdated, frequency);
		PDFReporter.takeExtraScreenshot();
		OrderingBOBusinessMaintainedTablesPage.deleteScheduler(schedulerName, bridging);
		PDFReporter.takeExtraScreenshot();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();	
	}

	@Test(alwaysRun = true, dependsOnMethods = "goToSchedulerPage")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}

}