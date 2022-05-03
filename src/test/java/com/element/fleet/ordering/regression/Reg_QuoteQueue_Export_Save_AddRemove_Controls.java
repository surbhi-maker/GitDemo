package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingSummaryPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;


/**
 * @lastModifiedBy shisingh
 */
public class Reg_QuoteQueue_Export_Save_AddRemove_Controls extends BaseWebDriver {
	
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
	@Documentation(step = "Go to On dealer Queue page", expected = "Application should load On Dealer Queue page")
	public void gotoQuoteQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Quote");
		OrderingBOOnOrderQueuePage.waitForQuoteQueuePage();
		OrderingBOOnOrderQueuePage.quoteQueuePageLabelValidation();
	}
	
	@Test(dependsOnMethods = "gotoQuoteQueuePage")
	@Documentation(step = "Go to verify Pagenation", expected = "Application should load Queue page and pagenation should be validated")
	public void verifyPagenationQuotequeue() throws Exception {
		OrderingBOQueuePage.verifyPagenation();
	}
	
	@Test(dependsOnMethods = "verifyPagenationQuotequeue")
	@Documentation(step = "Verify Add Remove", expected = "Add Remove columns should be succesful and verified")
	public void addRemoveColumns() throws Exception {
		OrderingBOQueuePage.removeColumnsFromAddRemoveDrpDwn("Corp","Fleet","Year","Make");
		OrderingBOQueuePage.verifyTableColumns();
		OrderingBOQueuePage.addColumnsFromAddRemoveDrpDwn("Corp","Fleet","Year","Make");
		OrderingBOQueuePage.verifyTableColumns();
	}
	
	@Test(dependsOnMethods = "addRemoveColumns")
	@Documentation(step = "Verify Controls functionality", expected = "Applied controls filter data should be displayed")
	public void verifyControlsFunctionality() throws Exception {
		OrderingBOQueuePage.addControls("Fleet", "1791");
		OrderingBOQueuePage.verifyDataOfControlsFilter("clientNumber", "1791");
	}
	
	@Test(dependsOnMethods = "verifyControlsFunctionality")
	@Documentation(step = "Verify Controls functionality", expected = "Applied controls filter data should be displayed")
	public void verifyCustomViewSave() throws Exception {
		OrderingBOQueuePage.saveCustomView("Year","Make","Supplier");
		OrderingBOQueuePage.selectAndVerifyCustomView();
	}
	
	@Test(dependsOnMethods = "verifyCustomViewSave")
	@Documentation(step = "Go to On DIO Queue page and verify Export functionality of Queue Table", expected = "Application should Export Queue table Data")
	public void verifyExportTableData() throws Throwable {
		OrderingSummaryPage.clickExportButton("Quote-Queue", this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "verifyExportTableData")
	@Documentation(step = "Verify sorting functionality of the page", expected = "Sorting functionality of the page is verified")
	public void verifyColumnSortedInDescAscOrder() throws Exception {
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("CreatedBy", "QuoteQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("CreatedBy", "QuoteQueue" );
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("CreatedBy", "Ascending" , "QuoteQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("QuoteID", "QuoteQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("QuoteID", "QuoteQueue" );
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("QuoteID", "Ascending" , "QuoteQueue");
	}	
		
	@Test(dependsOnMethods = "verifyColumnSortedInDescAscOrder", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
}
