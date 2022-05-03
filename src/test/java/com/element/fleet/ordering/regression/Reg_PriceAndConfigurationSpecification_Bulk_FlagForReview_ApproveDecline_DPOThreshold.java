package com.element.fleet.ordering.regression;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingFOFleetSpecDetailsPageEnum;
import com.element.fleet.ordering.enums.OrderingVehicleConfigFleetSpecsPageEnum;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingFOFleetSpecDetailsPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingVehicleConfigFleetSpecsPage;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;


/**
 * @lastModifiedBy pdhole
 */
public class Reg_PriceAndConfigurationSpecification_Bulk_FlagForReview_ApproveDecline_DPOThreshold extends BaseWebDriver{

	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "username", "boUserName" ,"orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username,String boUserName,String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}

	@Test(alwaysRun = true)
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void testLaunchURL() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openFOApplication();
	}

	@Test(dependsOnMethods = "testLaunchURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginApplication() throws Exception {
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.orderingHomePageLabelValidation();
	}

	@Test(dependsOnMethods = "testLoginApplication")
	@Documentation(step = "Change the client", expected = "Client should be changed")
	public void changeClientBreakdown() throws Exception {
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();	
	}

	@Test(dependsOnMethods = "changeClientBreakdown")
	@Documentation(step = "navigate to search price and config page", expected = "system should navigate user to search price and config page")
	public void navigateToSearchPriceConfig() throws Throwable {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.selectVehicleConfigurationMenuOption("Search for Saved Price & Configuration");
		CommonPage.initializeElementOrderObject();
	}

	@Test(dependsOnMethods = "navigateToSearchPriceConfig")
	@Documentation(step = "validate Bulk action", expected = "should validate bulk options are enabled/disabled")
	public void validateBulkActions() throws Exception{
		BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_ACTIONS_DOTS_XPATH).get(0).click();
		OrderingVehicleConfigFleetSpecsPage.verifyBulkActions("disabled");
		OrderingVehicleConfigFleetSpecsPage.validatesSingleBulkActionsLable(1, "ROW ACTIONS");
		OrderingVehicleConfigFleetSpecsPage.selectAllSpecFromTable();
		OrderingVehicleConfigFleetSpecsPage.verifyBulkActions("enabled");
		OrderingVehicleConfigFleetSpecsPage.validatesSingleBulkActionsLable(1, "BULK ACTIONS");
	}

	@Test(dependsOnMethods = "validateBulkActions")
	@Documentation(step = "Search with status 'Pending Client Approval'", expected = "Application should display records with status 'Pending Client Approval'")
	public void searchWithPendingClientApprovalAndValidate() throws Exception{
		OrderingVehicleConfigFleetSpecsPage.clickOnClearFilters();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Price & Config Status", CommonPage.getTestData("OrderingStatus"));
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.validateColumnValue(1, "Price & Config Status", "Pending Customer Approval");
	}

	@Test(dependsOnMethods = "searchWithPendingClientApprovalAndValidate")
	@Documentation(step = "Validate Flagged for review", expected = "Application should allow user to search fleet spec and validate flagged for review")
	public void validateFlaggedForReview() throws Exception{
		OrderingVehicleConfigFleetSpecsPage.clickOnClearFilters();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.selectReviewFlag("No");
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		CommonPage.getElementOrderObject().getVehicleTabObject().setPriceAndConfigSpecID(OrderingVehicleConfigFleetSpecsPage.returnColumnValue(1, "Price & Config ID"));
		OrderingVehicleConfigFleetSpecsPage.selectActionFromList(1,"Flag For Review");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.clickOnClearFilters();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Price & Config Id",CommonPage.getElementOrderObject().getVehicleTabObject().getPriceAndConfigSpecID());
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.validateActionPresentOnList(1,"Remove Flag For Review");
		OrderingVehicleConfigFleetSpecsPage.clickOnClearFilters();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.selectReviewFlag("Yes");
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Price & Config Id",CommonPage.getElementOrderObject().getVehicleTabObject().getPriceAndConfigSpecID());
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.validateFlaggedForReviewStatusRedBar(26,1,"#BF2E2E");
	}

	@Test(dependsOnMethods = "validateFlaggedForReview")
	@Documentation(step = "Validate search with Flagged for review as 'Yes'", expected = "Application should allow user to search and validate records with Flagged for review as 'Yes'")
	public void validateSearchWithFlaggedForReviewYes() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnClearFilters();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.selectView("Standard View");
		OrderingVehicleConfigFleetSpecsPage.selectPCReviewFlag("Yes");
		System.out.println("Client Number: "+ CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.validateFlaggedForReviewForAllRows(BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_SEARCH_SPEC_TABLE_COLUMNS_XPATH).size(), "#BF2E2E");
	}

	@Test(dependsOnMethods = "validateSearchWithFlaggedForReviewYes")
	@Documentation(step = "Validate remove Flagged for review", expected = "Application should allow user to remove Flagged for review status")
	public void validateRemoveFlaggedForReview() throws Exception{
		OrderingVehicleConfigFleetSpecsPage.clickOnClearFilters();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Price & Config Id",CommonPage.getElementOrderObject().getVehicleTabObject().getPriceAndConfigSpecID());
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.selectActionFromList(1,"Remove Flag For Review");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.clearStatusDropdown();
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Price & Config Id",CommonPage.getElementOrderObject().getVehicleTabObject().getPriceAndConfigSpecID());
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.validateFlaggedForReviewStatusRedBar(BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_SEARCH_SPEC_TABLE_COLUMNS_XPATH).size(),1,"None");
		OrderingVehicleConfigFleetSpecsPage.validateActionPresentOnList(1,"Flag For Review");
	}

	@Test(dependsOnMethods = "validateRemoveFlaggedForReview")
	@Documentation(step = "Validate buttons for spec with pending client approval", expected = "Application should allow user to validate buttons")
	public void validateButtonsForSpecWithPendingClientApproval() throws Exception{
		OrderingVehicleConfigFleetSpecsPage.clickOnClearFilters();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Price & Config Status", CommonPage.getTestData("OrderingStatus"));
		OrderingVehicleConfigFleetSpecsPage.selectFleetSpecSource("Internal");
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		CommonPage.getElementOrderObject().getVehicleTabObject().setPriceAndConfigSpecID(OrderingVehicleConfigFleetSpecsPage.returnColumnValue(1, "Price & Config ID"));
		OrderingVehicleConfigFleetSpecsPage.selectFleetSpec(1);
		OrderingVehicleConfigFleetSpecsPage.validatePresenceofDeclineAndApproveButtonsForGivenFleetSpec(CommonPage.getElementOrderObject().getVehicleTabObject().getPriceAndConfigSpecID());
	}

	@Test(dependsOnMethods = "validateButtonsForSpecWithPendingClientApproval")
	@Documentation(step = "Validate a pop-up is displayed on click of Decline button on fleet spec page", expected = "a pop-up should displayed on click of Decline button on fleet spec page")
	public void clickDeclineButtonAnvVerifyPopUpIsdisplayed() throws Exception{
		BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DECLINE_BUTTON_XPATH).click();
		OrderingVehicleConfigFleetSpecsPage.validateDeclinePopUp();
		BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DECLINE_POPUP_CANCEL_BUTTON_XPATH).click();
		if(!CommonPage.getElementOrderObject().getVehicleTabObject().getPriceAndConfigSpecID().isEmpty()) {
			OrderingVehicleConfigFleetSpecsPage.validatePresenceofDeclineAndApproveButtonsForGivenFleetSpec(CommonPage.getElementOrderObject().getVehicleTabObject().getPriceAndConfigSpecID());
		}
		BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DETAIL_PAGE_CANCEL_XPATH).click();
		OrderingVehicleConfigFleetSpecsPage.clickonAdvancedSearchButton();
		OrderingVehicleConfigFleetSpecsPage.waitForVehicleConfigFleetSpecsPage();
	}

	@Test(dependsOnMethods = "clickDeclineButtonAnvVerifyPopUpIsdisplayed")
	@Documentation(step = "Validate in fleet spec for DPO for Exterios color where invoice in greater than 0$", expected = "System should allow to validate DPO for exterior color")
	public void validateDPOForExteriorColor() throws Exception{
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickVehicleConfigurationAddPriceandConfigSpecsLink();
		OrderingFOFleetSpecDetailsPage.selectMake();
		OrderingFOFleetSpecDetailsPage.searchOnSpecPage("C AMG GT Coupe");
		OrderingFOFleetSpecDetailsPage.selectFleetSpecAtRow(1);
		OrderingFOFleetSpecDetailsPage.verifyDriverPaidForIntExtColor("Exterior", "Driver Paid", "Client Paid", "");
		OrderingFOFleetSpecDetailsPage.verifyDriverPaidForIntExtColor("Interior", "None", "Client Paid", "");
	}

	@Test(dependsOnMethods = "validateDPOForExteriorColor")
	@Documentation(step = "verify corp level additional options", expected = "System should allow to verify corp level additional options")
	public void verifyCorpLevelAdditionalOptions() throws Exception{
		Assert.assertTrue(BrowserAccess.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_TABLE_ADDITIONAL_OPTIONS_HEADER_XPATH).isDisplayed());
		Assert.assertTrue(BrowserAccess.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_TABLE_ADDITIONAL_OPTIONS_HEADER_XPATH).getText().contains("ADDITIONAL OPTIONS"));
	}

	@Test(dependsOnMethods = "verifyCorpLevelAdditionalOptions")
	@Documentation(step = "verify DPO Threshold", expected = "System should allow to verify DPO Threshold")
	public void verifyDPOThreshold() throws Exception{
		OrderingFOFleetSpecDetailsPage.verifyDPOCalculatorIsDisplayed();
	}

	@Test(alwaysRun = true, dependsOnMethods = "verifyDPOThreshold")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
}