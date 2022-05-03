package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingElementDeliveringDealerAssignmentRulesPageEnum;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOElementDeliveringDealerAssignmentRulesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy Sweety Agrawal
 */
public class Reg_DA_EDDAR_Validate_Search_Sort_ToggleColumn_ClearFilter_Export extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({"xcelerateURL", "username", "applicationURL", "applicationBOURL", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
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
	@Documentation(step = "Go to EDDAR page", expected = "Application should load EDDAR page")
	public void gotoEDDARPage() throws Throwable {
		OrderingBOHomePage.orderingBOSideMenuLoaded();
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Element Delivering Dealer Assignment Rules");
	}
	
	@Test(dependsOnMethods = "gotoEDDARPage")
	@Documentation(step = "Go to EDDAR page and validate label", expected = "all labels should  get populated")
	public void validationOfEDDAR() throws Throwable {
		OrderingBOElementDeliveringDealerAssignmentRulesPage.bmtEDDARLabelValidation();
	}
	
	@Test(dependsOnMethods = "validationOfEDDAR")
	@Documentation(step = "Go to EDDAR page verify search  functionality", expected = "Application should be able to show results according to search in EDDAR page")
	public void verifyEDDARSearchAndSortFunctionality() throws Throwable {
		OrderingBOElementDeliveringDealerAssignmentRulesPage.searchAndVerifyRecords("corp", "FA");
	}
	
	@Test(dependsOnMethods = "verifyEDDARSearchAndSortFunctionality")
	@Documentation(step = "verify clear filter", expected = "Application should remove the filter values")
	public void verifyClearFilterQueueTable() throws Throwable {
		OrderingBOElementDeliveringDealerAssignmentRulesPage.verifyClearFilter("corp", "FA");
	}
	
	@Test(dependsOnMethods = "verifyClearFilterQueueTable")
	@Documentation(step = "Go to EDDAR page and verify Toogle columns", expected = "Application should show the EDDAR table column")
	public void verifyToggleColumnsAndTableColumns() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_COLUMN_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_COLUMN_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_COLUMN_BUTTON_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_COLUMN_BUTTON_XPATH);
		OrderingBOElementDeliveringDealerAssignmentRulesPage.compareAllToggleCheckedboxesWithVisibleTableColumns();
		OrderingBOElementDeliveringDealerAssignmentRulesPage.compareToggleCheckedboxesWithVisibleTableHeadings();
		OrderingBOElementDeliveringDealerAssignmentRulesPage.uncheckFewToggleCheckboxes();
		OrderingBOElementDeliveringDealerAssignmentRulesPage.compareToggleCheckedboxesWithVisibleTableHeadings();
	}
	
	@Test(dependsOnMethods = "verifyToggleColumnsAndTableColumns")
	@Documentation(step = "Go to verify Pagenation", expected = "Application should load Queue page and pagenation should be validated")
	public void verifyPagenationTitleRegqueue() throws Exception {
		OrderingBOQueuePage.verifyPagenation();
	}
	
	@Test(dependsOnMethods = "verifyPagenationTitleRegqueue")
	@Documentation(step = "Go toEDDAR page and verify Export functionality", expected = "Application should Export EDDAR table Data")
	public void verifyExportTableData() throws Throwable {
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_COLUMN_BUTTON_XPATH);
		OrderingBOElementDeliveringDealerAssignmentRulesPage.clickExportButton("EDDAR", this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "verifyExportTableData")
	@Documentation(step = "Verify filters get cleared when go back and come back to same page", expected = "should clear filters on exiting the paf=ge")
	public void clearFiltersOnExit() throws Exception {
		OrderingBOElementDeliveringDealerAssignmentRulesPage.verifyClearFiltersOnExitingApplication();
	}
	
	@Test(dependsOnMethods = "clearFiltersOnExit")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}