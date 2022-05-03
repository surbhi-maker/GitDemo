package com.element.fleet.ordering.page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOMasterPurchaseOrderStatusPageEnum;
import com.element.fleet.ordering.exceptions.NoIfElseBlockMatchedException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;

public class OrderingBOMasterPurchaseOrderStatusPage {
	
	/** This method will verify page title
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyPageTitle() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_TITLE_ID, CommonPage.getTestData("WaitTime"));
		WebElement webElement = BrowserAction.getElement(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_TITLE_ID);
		CommonPage.assertLabelHighlight(webElement, "Master Purchase Order Status", "Master Purchase Order Status title is not matched");
	}
	
	/** This method will verify search field section label as per passed field name
	 * @param expectedString
	 * @param element
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyLabelFromSearchSection(String expectedString, WebElement element) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(element);
		CommonPage.assertLabelHighlight(element, expectedString, expectedString+": label is not mateched from search section");
	}
	
	/** This method will verify Auto Ack and Dealer Cutoff label from search field section
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyAutoAckAndDealerCutoffLabelFromSearchSection() throws Exception {
		verifyLabelFromSearchSection("Auto Ack", BrowserAction.getElement(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_AUTO_ACK_LABEL_XPATH));
		verifyLabelFromSearchSection("Dealer Cutoff", BrowserAction.getElement(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_LABEL_XPATH));
	}
	
	/** This method will verify Dealer Cutoff search field placed after View External Search field
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyDealerCutoffSearchFieldPlacedAfterViewExternalSearchField() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_SEARCH_FIELD_XPATH);
	}
	
	/** This method will verify Dealer Cutoff toggle column placed after View External toggle column in Toggle Columns list
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyDealerCutoffToggleColumnPlacedAfterViewExternalToggleColumn() throws Exception {
		OrderingBOQueuePage.clickToggleColumnButton();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_SEARCH_FIELD_XPATH);
		OrderingBOQueuePage.clickToggleColumnButton();
	}
	
	/** This method will verify Dealer Cutoff Table column placed after View External Table column
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyDealerCutoffTableColumnPlacedAfterViewExternalTableColumn() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_TABLE_COLUMN_XPATH);
	}
	
	/** This method will click on Add New button and verify page title
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void clickOnAddNewButtonAndVerifyTitle() throws Exception {
		BrowserAction.click(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_SEARCH_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewBtn();
		CommonPage.waitForElementToLoad(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_ADD_NEW_PAGE_TITLE_XPATH, CommonPage.getTestData("WaitTime"));
		WebElement element = BrowserAction.getElement(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_ADD_NEW_PAGE_TITLE_XPATH);
		CommonPage.assertLabelHighlight(element, "Master Purchase Order Status", "Master Purchase Order Status Add New page title is not matching");
	}
	
	/** This method will verify Dealer Cutoff toggle placed after View External toggle from add new page
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyDealerCutoffTogglePlacedAfterViewExternalToggleFromAddNewPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_ADD_NEW_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_TOGGLE_ADD_NEW_XPATH);
	}
	
	/** This method will verify Dealer Cutoff toggle default value ON/OFF from add new page
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyDealerCutoffToggleDefaultValueFromAddNewPage() throws Exception {
		verifyToggleOnOffUsingColor(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_TOGGLE_ADD_NEW_XPATH, "Off");
	}
	
	/**
	 * This method verify the toggle value is ON/OFF as per passed parameter
	 * @param fieldtLocatorEnum
	 * @para onOff
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyToggleOnOffUsingColor (Enum < ? > fieldtLocatorEnum, String onOff) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		BrowserVerify.verifyElementIsPresent(fieldtLocatorEnum);
		BrowserVerify.verifyElementEnabled(fieldtLocatorEnum);
		String popUpBackgroundColour = null;
		if (BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.xpath"))
			popUpBackgroundColour = Color.fromString(WebDriverAccess.getDriver().findElement(By.xpath(fieldtLocatorEnum.toString())).getCssValue("background-color")).asHex();
		else if (BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.id"))
			popUpBackgroundColour = Color.fromString(WebDriverAccess.getDriver().findElement(By.id(fieldtLocatorEnum.toString())).getCssValue("background-color")).asHex();
		else if (BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.name"))
			popUpBackgroundColour = Color.fromString(WebDriverAccess.getDriver().findElement(By.name(fieldtLocatorEnum.toString())).getCssValue("background-color")).asHex();
		else if (BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.cssSelector"))
			popUpBackgroundColour = Color.fromString(WebDriverAccess.getDriver().findElement(By.cssSelector(fieldtLocatorEnum.toString())).getCssValue("background-color")).asHex();
		else if (BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.className"))
			popUpBackgroundColour = Color.fromString(WebDriverAccess.getDriver().findElement(By.className(fieldtLocatorEnum.toString())).getCssValue("background-color")).asHex();
		else 
			throw new NoIfElseBlockMatchedException();
		switch(onOff) {
			case "On":
				if(!popUpBackgroundColour.equals("#82c341"))
					throw new OrderingErrorOccured("Toggle is not ON");
				break;
			case "Off":
				if(!popUpBackgroundColour.equals("#808080"))
					throw new OrderingErrorOccured("Toggle is not OFF");
				break;
			default: throw new OrderingErrorOccured("Invalid choice");
		}
	}
	
	/** This method will select and enter mandatory fields values
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void selectMandatoryFieldFromAddNewPage() throws Exception {
		CommonPage.selectDropDownValue(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_ADD_NEW_MANUFACTURER_ID, CommonPage.getTestData("CustomColumn5"), "Manufacturer");
		CommonPage.selectDropDownValue(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_ADD_NEW_MAJOR_CODE_ID, "AA", "Major Code");
		CommonPage.enterTextToInputField(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_ADD_NEW_DESCRIPTION_ID, CommonPage.getTestData("CustomColumn1"));
		BrowserAction.click(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_TOGGLE_ADD_NEW_XPATH);
	}
	
	/** This method will click on Save button
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void clickOnSave() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_ADD_NEW_SAVE_ID);
		BrowserAction.click(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_ADD_NEW_SAVE_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/** This method will verify column data as per passed column name
	 * @param fieldName
	 * @param exptected
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyColumnData(String fieldName, String exptected) throws Exception {
		String xpath = OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_SEARCH_DATA_NAME_XPATH.getValue().replace("$FieldName#", fieldName);
		String data_name = WebDriverAction.getElementAttributeValue(By.xpath(xpath), "name");
		String xpath1 = OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_COLUMN_DATA_XPATH.getValue().replace("$DataName#", data_name);
		Assert.assertEquals(WebDriverAction.getElementAttributeValue(By.xpath(xpath1), "data-val"), exptected, fieldName+" column data not matched as per added");
	}
	
	/** This method will verify column data as per passed column name
	 * @param fieldName
	 * @param searchValue
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void searchWithParameter(String fieldName, String searchValue) throws Exception {
		String xpath = OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_SEARCH_DATA_NAME_XPATH.getValue().replace("$FieldName#", fieldName);
		WebDriverAction.click(By.xpath(xpath));
		WebDriverAction.clickAndClear(By.xpath(xpath));
		WebDriverAction.enterFieldValue(By.xpath(xpath), searchValue);
		BrowserAction.click(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_SEARCH_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/** This method will verify column data as per passed column name
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyAllColumnData(String action) throws Exception {
		switch(action) {
			case "Add":
				verifyColumnData("Manufacturer", CommonPage.getTestData("CustomColumn5").toUpperCase());
				verifyColumnData("Major Code", "AA");
				verifyColumnData("Description", CommonPage.getTestData("CustomColumn1"));
				verifyColumnData("Dealer Cutoff", "Y");
				break;
			case "Edit":
				verifyColumnData("Manufacturer", CommonPage.getTestData("CustomColumn6").toUpperCase());
				verifyColumnData("Major Code", "AK");
				verifyColumnData("Description", CommonPage.getTestData("CustomColumn2"));
				verifyColumnData("Dealer Cutoff", "N");
				break;
			default: throw new OrderingErrorOccured("Invalid choice");
		}
		verifyColumnData("Corp", "FA");
		verifyColumnData("EST/ACT", "A");
		verifyColumnData("Auto Ack", "N");
		
	}
	
	/** This method will click on added Master PO status record from table for edit
	 * @param fieldName
	 * @param exptected
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void clickOnAddedPOForEdit(String fieldName, String exptected) throws Exception {
		String xpath = OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_SEARCH_DATA_NAME_XPATH.getValue().replace("$FieldName#", fieldName);
		String data_name = WebDriverAction.getElementAttributeValue(By.xpath(xpath), "name");
		String xpath1 = OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_COLUMN_DATA_XPATH.getValue().replace("$DataName#", data_name);
		WebDriverAction.click(By.xpath(xpath1));
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/** This method will edit some data from edit master PO status page
	 * @throws Throwable 
	 * @lastModifiedBy akandkonde
	 */
	public static void editAddedMasterPOStatus() throws Throwable {
		clickOnAddedPOForEdit("Description", CommonPage.getTestData("CustomColumn1")); 
		verifyToggleOnOffUsingColor(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_TOGGLE_ADD_NEW_XPATH, "On");
		CommonPage.selectDropDownValue(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_ADD_NEW_MANUFACTURER_ID, CommonPage.getTestData("CustomColumn6"), "Manufacturer");
		CommonPage.selectDropDownValue(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_ADD_NEW_MAJOR_CODE_ID, "AK", "Major Code"); 
		CommonPage.enterTextToInputField(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_ADD_NEW_DESCRIPTION_ID, CommonPage.getTestData("CustomColumn2")); 
		BrowserAction.click(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_TOGGLE_ADD_NEW_XPATH);
		CommonPage.takeExtraScreenshot();
	}
	
	/** This method will click on delete button for added master PO status record
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void deleteAddedMasterPOStatus() throws Exception {
		OrderingBOMasterPurchaseOrderStatusPage.clickOnAddedPOForEdit("Description", CommonPage.getTestData("CustomColumn2")); 
		verifyToggleOnOffUsingColor(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DEALER_CUTOFF_TOGGLE_ADD_NEW_XPATH, "Off");
		BrowserAction.click(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DELETE_BUTTON_ID);
	}
	
	/** This method will verify deleted record in table
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyDeletedRecord() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_EMPTY_TABLE_XPATH);
		BrowserAction.click(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_CLEAR_FILTER_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/** This method will verify edit pop up
	 * @throws Throwable 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyDeletePopUp() throws Throwable {
		CommonPage.highlightElement(BrowserAction.getElement(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DELETE_POPUP_LABEL_CSS));
		CommonPage.highlightElement(BrowserAction.getElement(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DELETE_POPUP_MESSAGE_XPATH));
		CommonPage.takeExtraScreenshot();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DELETE_POPUP_NO_XPATH);
		BrowserAction.click(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DELETE_POPUP_NO_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DELETE_BUTTON_ID);
		BrowserAction.click(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DELETE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DELETE_POPUP_YES_XPATH);
		BrowserAction.click(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DELETE_POPUP_YES_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		BrowserAction.refresh();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/** This method will click on export button
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void clickOnExport() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_EXPORT_XPATH);
		BrowserAction.click(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_EXPORT_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/*
	 * This method reads csv file
	 * This method also reads excel file
	 * This method gets the row data from the downloaded csv 
	 * @lastmodified Akandkonde
	 */
	public static String getCSVHeaders(String relativeFilePath) throws IOException {
		String csvRows = new String();
		try(BufferedReader br = new BufferedReader(new FileReader(relativeFilePath))){
			String line;
			while ((line = br.readLine()) != null) {
				csvRows = line.trim();
				break;
			}
		}
		return csvRows;
	}
	
	/** This method will verify exported csv as well as expected headers orders from csv
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyExportedCSVAndHeadersOrder() throws Exception {
		CommonPage.returnLatestCSVFileName("Master Purchase Order Status");
		Predicate<File> orderFilter = f -> f.getName().startsWith("Master Purchase Order Status-Table");
		Path parentFolder = Paths.get(System.getProperty("user.home") + "\\Downloads\\");	
		Optional<File> mostRecentFileOrFolder =	
				Arrays.stream(parentFolder.toFile().listFiles())	
						.filter(f -> f.getName().endsWith(".csv"))	
						.filter(orderFilter)	
						.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn3").split("\\|"));
		String CSVColumn = getCSVHeaders(mostRecentFileOrFolder.get().toString());
		List<String> CSVColumnName = Arrays.asList(CSVColumn.split("\\,"));
		System.out.println("CSV Headers:-"+CSVColumnName);
		System.out.println("Expected Column Headers:-"+expectedColumnNames);
		for(int i=0; i<expectedColumnNames.size(); i++)
			Assert.assertEquals(CSVColumnName.get(i).trim(), expectedColumnNames.get(i).trim(),"Columns orders is not matched");
	}
	
	/** This method will delete existing record if available
	 * @throws Throwable 
	 * @lastModifiedBy akandkonde
	 */
	public static void deleteExistingRecordIfAvailable(String recordName) throws Throwable {
		OrderingBOMasterPurchaseOrderStatusPage.searchWithParameter("Description", recordName);
		if(!(BrowserAction.getElement(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_FIRST_TD_VALUE_XPATH).getText().trim().equals("No data available in table"))) {
			OrderingBOMasterPurchaseOrderStatusPage.clickOnAddedPOForEdit("Description", recordName); 
			BrowserAction.click(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_DELETE_BUTTON_ID);
			OrderingBOMasterPurchaseOrderStatusPage.verifyDeletePopUp();
		}
		BrowserAction.click(OrderingBOMasterPurchaseOrderStatusPageEnum.ORDERING_BO_MPOS_PAGE_CLEAR_FILTER_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
}
