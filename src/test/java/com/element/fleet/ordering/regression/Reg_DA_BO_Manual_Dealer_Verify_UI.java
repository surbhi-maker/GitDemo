package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBODealerQueuePage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy hjimenez
 */
public class Reg_DA_BO_Manual_Dealer_Verify_UI extends BaseWebDriver {
	
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
	@Documentation(step = "Go to On Queue page", expected = "Application should load On Order Queue page")
	public void goToOnOrderQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
	}
	
	@Test(dependsOnMethods = "goToOnOrderQueuePage")
	@Documentation(step = "Open Dealer Queue", expected = "Dealer Queue screen should display")
	public void openDealerQueueScreen() throws Throwable {
		OrderingBOQueuePage.gotoQueue("Dealer");
	}
	
	@Test(dependsOnMethods = "openDealerQueueScreen")	
	@Documentation(step = "Click on manual button of first order", expected = "Manual Dealer Assignment screen should open")
	public void clickOnManualButtonFirstOrder() throws Exception {
		OrderingBODealerQueuePage.searchWithParamsDealerQueue("orderType", "Factory");
		OrderingBODealerQueuePage.clickManualButtonOnQueue();
		OrderingBODealerQueuePage.waitForManualDealerAssignmentPage();		
	}
	
	@Test(dependsOnMethods = "clickOnManualButtonFirstOrder")	
	@Documentation(step = "Verify the position of manual dealer assignment header", expected = "Header should be placed on top")
	public void verifyHeaderPosition() throws Exception {
		OrderingBODealerQueuePage.verifyMDAHeaderPosition();
	}
	
	@Test(dependsOnMethods = "verifyHeaderPosition")	
	@Documentation(step = "Verify Active Switch is enabled by default in  green color", expected = "Active switch should be default enabled in green color")
	public void verifyActiveSwitch() throws Exception {
		OrderingBODealerQueuePage.verifyActiveSwitch();
	}
	
	@Test(dependsOnMethods = "verifyActiveSwitch")	
	@Documentation(step = "Verify id Radius is Present", expected = "Radius Text Field should be present")
	public void verifyRadius() throws Exception {
		OrderingBODealerQueuePage.verifyRadius();
	}
	
	@Test(dependsOnMethods = "verifyRadius")	
	@Documentation(step = "Verify id Radius is Present", expected = "9999 should be the max value user can input")
	public void verifyRadiusMaximumValue() throws Exception {
		OrderingBODealerQueuePage.verifyRadiusMaximumValue("10000");
	}

	@Test(dependsOnMethods = "verifyRadiusMaximumValue")	
	@Documentation(step = "Verify Search After Modifying Radius Value", expected = "Dealers should show in a radius of no more than 9999")
	public void verifyRadiusAfterModifyingSearch() throws Exception {
		OrderingBODealerQueuePage.verifySearchAfterModifyingRadiusValue("9999");
	}
	
	@Test(dependsOnMethods = "verifyRadiusAfterModifyingSearch")	
	@Documentation(step = "Verify the buttons in Manual Dealer Assignment Screen", expected = "Save, Search and Close buttons should display")
	public void verifyButtonsMDAScreen() throws Exception {
		OrderingBODealerQueuePage.verifyMDAButtons();
	}
	
	@Test(dependsOnMethods = "verifyButtonsMDAScreen")	
	@Documentation(step = "Verify the length dropdown", expected = "100, 500 should be the only two values in the dropdown")
	public void verifyTableLengthValues() throws Exception {
		String expectedValues = "[100, 500]";
		OrderingBODealerQueuePage.verifySearchAfterModifyingRadiusValue("49");
		OrderingBODealerQueuePage.verifySelectLengthValues(expectedValues);
	}
	
	@Test(dependsOnMethods = "verifyTableLengthValues")	
	@Documentation(step = "Verify Delivering Dealer Headers in table", expected = "Expected Headers should display in table")
	public void verifyDealerTableHeaders() throws Exception {
		String[] expectedHeaders = {"Dealer Name", "MFR Dealer Code", "Address", "Tier" , "Active" , "Fleet Always Never Use" , "Element Always Never Use",
				"Phone" , "CD Fee" , "Distance" , "Title" , "Call First" , "COD" , "COD Taxes" , "Sales Tax" , "DIO" ,
				"Storage Fee" , "Used Storage Fee" , "VolumeYTD" , "Used Unit", "Email", "Last Used Date"};
		OrderingBODealerQueuePage.verifyTableHeadersDealerQueue(expectedHeaders);
	}
	
	@Test(dependsOnMethods = "verifyDealerTableHeaders")	
	@Documentation(step = "Verify Delivering Dealer Table is sorted by Distance by default", expected = "Distance should be the default sorting method")
	public void verifyDeliveringDealerTableDefaultSorting() throws Exception {
		OrderingBODealerQueuePage.searchDeliveringDealer();
		OrderingBODealerQueuePage.verifyDeliveringDealerDefaultSorting();
	}
	
	@Test(dependsOnMethods = "verifyDeliveringDealerTableDefaultSorting")	
	@Documentation(step = "Verify Ordering Dealer Table headers", expected = "Expected Headers should display in table")
	public void verifyOrderingDealerTableHeaders() throws Exception {
		String[] expectedHeaders = {"Dealer Name", "MFR Dealer Code", "Address", "Tier" , "Active" , "Fleet Always Never Use",
				"Element Always Never Use", "Diverse"};
		OrderingBODealerQueuePage.searchOrderingDealer();
		OrderingBODealerQueuePage.verifyTableHeadersOrderingDealer(expectedHeaders);
	}
	
	@Test(dependsOnMethods = "verifyOrderingDealerTableHeaders")	
	@Documentation(step = "Verify Radius Search is not present in Ordering Dealer", expected = "Radius Textbox should not be displayed")
	public void verifyRadiusNotDisplayedInOrderingDealer() throws Exception {
		OrderingBODealerQueuePage.verifyRadiusInputNotPresent();
	}
	
	@Test(dependsOnMethods = "verifyRadiusNotDisplayedInOrderingDealer")	
	@Documentation(step = "Verify Ordering Dealer Table is sorted by Dealer Name", expected = "Dealer Name should be the default sorting method")
	public void verifyOrderingDealerTableDefaultSorting() throws Exception {
		OrderingBODealerQueuePage.searchOrderingDealer();
		OrderingBODealerQueuePage.verifyOrderingDealerDefaultSorting();
	}
	
	@Test(dependsOnMethods = "verifyOrderingDealerTableDefaultSorting")	
	@Documentation(step = "Select a Stock Order from Dealer Queue Screen", expected = "Manual Dealer Assignment screen should display")
	public void selectStockOrder() throws Exception {
		OrderingBODealerQueuePage.navigateBackToDealerQueue();
		OrderingBODealerQueuePage.searchWithParamsDealerQueue("orderType", "Stock");
		OrderingBODealerQueuePage.selectFirstOrderIfResults();
		OrderingBODealerQueuePage.waitForManualDealerAssignmentPage();
	}
	
	@Test(dependsOnMethods = "selectStockOrder")	
	@Documentation(step = "Verify Active Switch is enabled by default in  green color in Manual Assignment Stock Order", expected = "Active switch should be default enabled in green color")
	public void verifyActiveSwitchStockOrder() throws Exception {
		OrderingBODealerQueuePage.verifyActiveSwitch();
	}
	
	@Test(dependsOnMethods = "verifyActiveSwitchStockOrder")	
	@Documentation(step = "Select a Dealer Order from Dealer Queue Screen", expected = "Manual Dealer Assignment screen should display")
	public void selectDealerOrder() throws Exception {
		OrderingBODealerQueuePage.navigateBackToDealerQueue();
		OrderingBODealerQueuePage.searchWithParamsDealerQueue("orderType", "Dealer");
		OrderingBODealerQueuePage.selectFirstOrderIfResults();
		OrderingBODealerQueuePage.waitForManualDealerAssignmentPage();
	}
	
	@Test(dependsOnMethods = "selectDealerOrder")	
	@Documentation(step = "Verify Dealer Search", expected = "User should be able to search by dealer name and dealer code")
	public void verifyDealerSearch() throws Exception {
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBODealerQueuePage.verifyDealerSearch();
		OrderingBODealerQueuePage.searchByZipCode();
		OrderingBODealerQueuePage.searchByState();
	}
	
	@Test(dependsOnMethods = "verifyDealerSearch")	
	@Documentation(step = "Verify Dealer Field is ignored while searching by state", expected = "Dealer Field should be ignored if search by state")
	public void verifyDealerFieldIsIgnoredInSearch() throws Exception {
		OrderingBODealerQueuePage.verifyDealerFieldIsIgnoredSearchByState();
	}
	
	@Test(dependsOnMethods = "verifyDealerFieldIsIgnoredInSearch")	
	@Documentation(step = "Verify Dealer Field is ignored while searching by zip code", expected = "Dealer Field should be ignored if search by zip code")
	public void verifyDealerFieldIsIgnoredInSearchZipCode() throws Exception {
		OrderingBODealerQueuePage.verifyDealerFieldIsIgnoredSearchByZipCode();
	}
	
	@Test(dependsOnMethods = "verifyDealerFieldIsIgnoredInSearchZipCode")	
	@Documentation(step = "Log out from BO", expected = "App should be logged out")
	public void logOutFromBO() throws Exception {
		OrderingBODealerQueuePage.navigateBackToDealerQueue();
		OrderingBOHomePage.verifyLogOutFunctionality();
	}	
	
	@Test(dependsOnMethods = "verifyDealerFieldIsIgnoredInSearchZipCode", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}
