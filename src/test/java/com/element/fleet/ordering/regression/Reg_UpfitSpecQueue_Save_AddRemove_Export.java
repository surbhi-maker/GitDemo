package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingBOUpfitSpecPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy shivam
 */
public class Reg_UpfitSpecQueue_Save_AddRemove_Export extends BaseWebDriver {

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
	@Documentation(step = "Go to On Upfit Spec Queue page", expected = "Application should load On Upfit Spec Queue page")
	public void gotoUpfitSpecQueuePage() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Upfit Spec");
		OrderingBOQueuePage.waitUntilCompletePageLoad();
	}
	
	@Test(dependsOnMethods = "gotoUpfitSpecQueuePage")
	@Documentation(step = "Go to On Upfit Spec Queue page and verify title", expected = "Application should load On Upfit Spec Queue page and title should match")
	public void verifyUpfitSpecTitleAndFields() throws Throwable {
		OrderingBOUpfitSpecPage.verifyQueueTitleAndFields("Upfit Spec");
		OrderingBOUpfitSpecPage.clickOnControlLink();
		OrderingBOUpfitSpecPage.verifyControlLabelsArePresent();
		OrderingBOUpfitSpecPage.clickOnApplyButton();
	}
	
	@Test(dependsOnMethods = "verifyUpfitSpecTitleAndFields")
	@Documentation(step = "Click on control links and verify correct control labels are displayed", expected = "Correct Control labels should be present")
	public void verifyControlLinkLabels() throws Exception {
		OrderingBOUpfitSpecPage.clickOnControlLink();
		OrderingBOUpfitSpecPage.verifyControlLabelsArePresent();
		OrderingBOUpfitSpecPage.clickOnApplyButton();
	}
	
	@Test(dependsOnMethods = "verifyControlLinkLabels")
	@Documentation(step = "Select project which searched by name and open project queue page", expected = "Application should be select project which is searched by name and Project Queue page should be opened")
	public void verifyControlLinkFunctionality() throws Exception {
		OrderingBOUpfitSpecPage.clickOnSearchByColumnNameAndVerifyColumnData("Upfit Spec Name", CommonPage.getTestData("CustomColumn1"));
		OrderingBOUpfitSpecPage.clickOnSearchByColumnNameAndVerifyColumnData("Fleet", CommonPage.getTestData("CustomColumn2"));
	}
	
	@Test(dependsOnMethods = "verifyControlLinkFunctionality")
	@Documentation(step = "Click on pagination link and selection option", expected = "Application should be able to select pagination option")
	public void verifyUpfitSpecPaginationFunctionality() throws Exception {
		OrderingBOUpfitSpecPage.verifyPaginationFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyUpfitSpecPaginationFunctionality")
	@Documentation(step = "Search Project by name and select Save option with standard view functionality", expected = "Application should be able to search Project by name and validation message should be shown")
	public void verifySaveFunctionality() throws Exception {
		OrderingBOUpfitSpecPage.verifySaveFunctionality();
	}
	
	@Test(dependsOnMethods = "verifySaveFunctionality")
	@Documentation(step = "Click on Export link", expected = "Application should be able to click on export link and Upfit Spec-queue.csv should be downaloded")
	public void verifyExportLinkFunctionality() throws Exception {
		OrderingBOUpfitSpecPage.exportUpfitSpec(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "verifyExportLinkFunctionality")
	@Documentation(step = "Verify sorting functionality of the page", expected = "Sorting functionality of the page is verified")
	public void verifyColumnSortedInDescAscOrder() throws Exception {
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("UpfitSpecID", "UpfitSpecQueue");
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("UpfitSpecID", "Descending" , "UpfitSpecQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("UpfitSpecID", "UpfitSpecQueue" );
		OrderingBOOnOrderQueuePage.compareOrderOfColumn("UpfitSpecID", "Ascending" , "UpfitSpecQueue");
		OrderingBOOnOrderQueuePage.clickColumnTableHeading("CreatedBy", "UpfitSpecQueue");
	}
	
	@Test(dependsOnMethods = "verifyColumnSortedInDescAscOrder")
	@Documentation(step = "Click on Add/Remove button and verify by select All/deselect all/select few columns", expected = "Application should be able to click on Add/Remove button and all columns should be selected All/deselected All")
	public void verifyAddRemoveColumnsFunctionality() throws Exception {
		OrderingBOUpfitSpecPage.verifyAddRemoveFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyAddRemoveColumnsFunctionality")
	@Documentation(step = "Click on upfit spec from table and verify upfit spec maintenance page", expected = "Application able to click on upfit spec and upfit spec maintenance page should be opened")
	public void verifyUpfitSpecMaintenancePage() throws Exception {
		OrderingBOUpfitSpecPage.verifyUpfitSpecMaintenancePage("Upfit Spec Name", CommonPage.getTestData("CustomColumn1"));
	}	
}
