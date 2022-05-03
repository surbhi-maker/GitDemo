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
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy mkhairner
 */
public class Reg_BMT_Document_And_Upload extends BaseWebDriver {
	
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
	@Documentation(step = "Go to Legal Documents and Upload page", expected = "Application should load Legal Documents and Upload page")
	public void goToLegalDocumentAndUploadPage() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Legal Document and Upload");
		OrderingBOBusinessMaintainedTablesPage.verifyButtonsOnLduPage();
	}
	
	@Test(dependsOnMethods = "goToLegalDocumentAndUploadPage")
	@Documentation(step = "Verify Pagination of  Legal Documents and Upload page", expected = "Pagination should work as expected for Legal Documents and Upload page")
	public void verifyPagination() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.verifyPagenation();		
	}
	
	@Test(dependsOnMethods = "verifyPagination")
	@Documentation(step = "Verify last updated column is sorted in Legal Documents and Upload page", expected = "Last updated column should be sorted in descending order")
	public void verifyLastUpdatedDateIsSorted() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.checkLastUpdatedDateAreInDescendingOrder();
	}
	
	@Test(dependsOnMethods = "verifyLastUpdatedDateIsSorted")
	@Documentation(step = "Verify Columns in Legal Documents and Upload page", expected = "All Columns should be displayed as per the mockup")
	public void verifyColumns() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.verifyTableColumnsLegalDocumentAndUploadPage();
	}
	
	@Test(dependsOnMethods = "verifyColumns")
	@Documentation(step = "Verify erros while creating and updating records", expected = "All errors should be displayed as expected")
	public void verifyErrors() throws Throwable {
		OrderingBOBusinessMaintainedTablesPage.verifyAddNewRecordDocumentTypeandCategoryErrorMessages();
		OrderingBOBusinessMaintainedTablesPage.verifyAddNewPageEndDateErrorMessage();
		OrderingBOBusinessMaintainedTablesPage.verifyCorpCodeError("1791", "CSV File", "Acknowledgement");
		OrderingBOBusinessMaintainedTablesPage.verifyDocumentUploadError("FA");
	}
		
	@Test(alwaysRun = true, dependsOnMethods = "verifyErrors")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
	
}