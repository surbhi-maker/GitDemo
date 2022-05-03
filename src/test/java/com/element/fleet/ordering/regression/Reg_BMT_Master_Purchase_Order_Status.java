package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOMasterPurchaseOrderStatusPage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy Akshay Kandkonde
 */
public class Reg_BMT_Master_Purchase_Order_Status extends BaseWebDriver {
	
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
	@Documentation(step = "Go to Master Purchase Order Status page", expected = "Maste Purchase Order Status Page should be opened")
	public void goToMasterPurchaseOrderStatusPage() throws Throwable {
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Master Purchase Order Status");
		OrderingBOMasterPurchaseOrderStatusPage.verifyPageTitle();
		OrderingBOMasterPurchaseOrderStatusPage.verifyAutoAckAndDealerCutoffLabelFromSearchSection();
		OrderingBOMasterPurchaseOrderStatusPage.verifyDealerCutoffSearchFieldPlacedAfterViewExternalSearchField();
	}
	
	@Test(dependsOnMethods = "goToMasterPurchaseOrderStatusPage")
	@Documentation(step = "Search and delete existing record if available ", expected = "Record should be deleted if available")
	public void deleteExistingRecordIfAvailable() throws Throwable {
		OrderingBOMasterPurchaseOrderStatusPage.deleteExistingRecordIfAvailable(CommonPage.getTestData("CustomColumn1"));
		OrderingBOMasterPurchaseOrderStatusPage.deleteExistingRecordIfAvailable(CommonPage.getTestData("CustomColumn2"));
	}
	
	@Test(dependsOnMethods = "deleteExistingRecordIfAvailable")
	@Documentation(step = "Go to On MasterPO Status BMT page and verify Toogle and Queue Table columns", expected = "Application should show the Queue table columnd are same as toggle columns On Master PO Status BMT page")
	public void verifyToggleColumnsAndTableColumns() throws Throwable {
		OrderingBOQueuePage.verifyTableColumnsOnLanding();
		OrderingBOQueuePage.verifyQueueTableColumns();
		OrderingBOQueuePage.removeColumnsFromQueueTable("Auto Ack","Dealer Cutoff");
		OrderingBOQueuePage.verifyQueueTableColumns();
		OrderingBOQueuePage.addColumnInQueueTable("Auto Ack","Dealer Cutoff");
		OrderingBOQueuePage.verifyQueueTableColumns();
		OrderingBOMasterPurchaseOrderStatusPage.verifyDealerCutoffToggleColumnPlacedAfterViewExternalToggleColumn();
		OrderingBOMasterPurchaseOrderStatusPage.verifyDealerCutoffTableColumnPlacedAfterViewExternalTableColumn();
	}
	
	@Test(dependsOnMethods = "verifyToggleColumnsAndTableColumns")
	@Documentation(step = "Click on Add New button and Go to Master PO Status page and fill all data", expected = "Add Master PO Status page should be opened with all fields")
	public void verifyAddMasterPOStatusFunctionality() throws Throwable {
		OrderingBOMasterPurchaseOrderStatusPage.clickOnAddNewButtonAndVerifyTitle();
		OrderingBOMasterPurchaseOrderStatusPage.verifyDealerCutoffTogglePlacedAfterViewExternalToggleFromAddNewPage();
		OrderingBOMasterPurchaseOrderStatusPage.verifyDealerCutoffToggleDefaultValueFromAddNewPage();
		OrderingBOMasterPurchaseOrderStatusPage.selectMandatoryFieldFromAddNewPage();
	}
	
	@Test(dependsOnMethods = "verifyAddMasterPOStatusFunctionality")
	@Documentation(step = "Verify newly added Master PO status record in table", expected = "Newly Added record should be present")
	public void verifyAddedMasterPOStatus() throws Throwable {
		OrderingBOMasterPurchaseOrderStatusPage.clickOnSave();
		OrderingBOMasterPurchaseOrderStatusPage.searchWithParameter("Description", CommonPage.getTestData("CustomColumn1"));
		OrderingBOMasterPurchaseOrderStatusPage.verifyAllColumnData("Add");
	}
	
	@Test(dependsOnMethods = "verifyAddedMasterPOStatus")
	@Documentation(step = "Click on new added PO status record and edit some fields and save", expected = "PO status should be updated")
	public void verifyEditMasterPOStatus() throws Throwable {
		OrderingBOMasterPurchaseOrderStatusPage.editAddedMasterPOStatus();
		OrderingBOMasterPurchaseOrderStatusPage.clickOnSave();
		OrderingBOMasterPurchaseOrderStatusPage.searchWithParameter("Description", CommonPage.getTestData("CustomColumn2"));
		OrderingBOMasterPurchaseOrderStatusPage.verifyAllColumnData("Edit");
	}
	
	@Test(dependsOnMethods = "verifyEditMasterPOStatus")
	@Documentation(step = "Open added PO status record and click on delete button", expected = "PO status record should be deleted")
	public void verifyDeleteMasterPOStatus() throws Throwable {	
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Master Purchase Order Status");
		OrderingBOMasterPurchaseOrderStatusPage.verifyPageTitle();
		OrderingBOMasterPurchaseOrderStatusPage.searchWithParameter("Description", CommonPage.getTestData("CustomColumn4"));
		OrderingBOMasterPurchaseOrderStatusPage.deleteAddedMasterPOStatus();
		OrderingBOMasterPurchaseOrderStatusPage.verifyDeletePopUp();
		OrderingBOMasterPurchaseOrderStatusPage.searchWithParameter("Description", CommonPage.getTestData("CustomColumn4"));
		OrderingBOMasterPurchaseOrderStatusPage.verifyDeletedRecord();
	}
	
	@Test(dependsOnMethods = "verifyDeleteMasterPOStatus")
	@Documentation(step = "Click on export and verify column headers order from csv", expected = "Csv should be downloaded and columns header should be in proper order")
	public void verifyExportAndColumnsOrdersFromCSV() throws Throwable {
		OrderingBOMasterPurchaseOrderStatusPage.clickOnExport();
		OrderingBOMasterPurchaseOrderStatusPage.verifyExportedCSVAndHeadersOrder();
	}
	
	@Test(dependsOnMethods = "verifyExportAndColumnsOrdersFromCSV", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}
