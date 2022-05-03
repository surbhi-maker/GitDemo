package com.element.fleet.ordering.page;

import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.ExcelUtil;
import com.element.fleet.ordering.enums.OrderingBOManagerOrderPreferencesPageEnum;
import com.element.fleet.ordering.enums.OrderingBOQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingElementDeliveringDealerAssignmentRulesPageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;

public class OrderingBOElementDeliveringDealerAssignmentRulesPage {
	static LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
	static String ruleName;

	/**
	 * This method search and verify records on EDDAR pages
	 * @lastModifiedBy sagrawal
	 * @param column name
	 * @param search text
	 * @throws Exception
	 */
	public static void searchAndVerifyRecords(String columnName, String searchText) throws Exception {
		searchOrderByColumnName(columnName, searchText);
		verifySearchedRecords(columnName, searchText);
	}

	/**
	 * This method validates labels and buttons EDDAR pages
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void bmtEDDARLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TITLE_XPATH), "Element Delivering Dealer Assignment Rules","Queues queue label did not match with the expected string");
		List<String> elementslist = BrowserAction.getElementsText(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_COLUMN_NAME_XPATH);
		System.out.println("column values: "+elementslist );
	}
	
	/**
	 * This method Searches the record according to search option field on EDDAR pages
	 * @lastModifiedBy sagrawal
	 * @param column name
	 * @param search text
	 * @throws Exception
	 */
	public static void searchOrderByColumnName(String columnName, String searchText) throws Exception {
		System.out.println(columnName + ": " + searchText);
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_GENERIC_LABEL_XPATH.name(),String.format(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_GENERIC_LABEL_XPATH.toString(), columnName)),searchText);
		clickSearchButton();
		waitUntilCompletePageLoad();
		WebDriverAction.clear(BrowserAccess.getLocator(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SEARCH_FIELDS_VALUE_XPATH.toString(), columnName)));
	}

	/**
	 * This method verify searched record on EDDAR pages
	 * @lastModifiedBy sagrawal
	 * @param column name
	 * @param search text
	 * @throws Exception
	 */
	public static void verifySearchedRecords(String columnName, String searchText) throws Exception {
		List<WebElement> elementslist = WebDriverAccess.getElements(BrowserAccess.getLocator(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_SEARCHED_RECORD_XPATH.name(),String.format(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_SEARCHED_RECORD_XPATH.toString(),result.get(columnName))));
		for (WebElement element : elementslist) {
			Assert.assertEquals(element.getText().trim(), searchText.trim(), "Searched record is not matching");
		}
	}

	/**
	 * This method Clicks on Search Option Button on EDDAR pages
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void clickSearchButton() throws Exception {
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SEARCH_BUTTON_XPATH);
	}
	
	/**
	 * This method will let the execution wait until EDDAR pages gets loaded completely
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void waitUntilCompletePageLoad() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TITLE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_CORP_LABEL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_COLUMN_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SEARCH_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_CLEAR_FILTER_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_EXPORT_BUTTON_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_NEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_CHECK_ALL_XPATH);
	}	

	/**
	 * This method clicks on Reset
	 * @param columnname
	 * @param searchText
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void verifyClearFilter(String columnName, String searchText) throws Exception {
		waitUntilCompletePageLoad();
		clickClearFilterButton();
		WebElement ele = WebDriverAccess.getElement(BrowserAccess.getLocator(
				OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_GENERIC_LABEL_XPATH
						.name(),
				String.format(
						OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_GENERIC_LABEL_XPATH
								.toString(),
						columnName)));
		assertTrue(ele.getText().trim().equals(""), "Search Field " + columnName + " is not cleared ");
	}
	
	/**
	 * This method Clicks on Clear Filter button on EDDAR pages
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void clickClearFilterButton() throws Exception {
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_CLEAR_FILTER_BUTTON_XPATH);
	}
	
	/**
	 * This method verifies correct Columns are present in the table after landing
	 * @throws Exception
	 * @lastModifiedBy sagrawal
	 */
	public static void verifyTableColumnsOnLanding() throws Exception {
		String label;
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn3").split("\\|"));
		ArrayList<String> tableColumns = new ArrayList<String>();
		List<WebElement> tableElements = BrowserAccess.getElements(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_COLUMN_NAME_XPATH);
		System.out.println(tableElements);
		for (WebElement element : tableElements) {
			label = element.getAttribute("innerText").trim();
			if (label != null && !label.equalsIgnoreCase("")) {
				if (label.contains("\n")) {
					label = label.replace("\n", " ");
				}
				tableColumns.add(label);
			}
		}
		if (tableColumns.contains("Rule ID"))
			tableColumns.remove("Rule ID");
		if (tableColumns.contains("Corp"))
			tableColumns.remove("Corp");
		assertTrue(expectedColumnNames.equals(tableColumns), "Table Coulmns are not matching with Test Data");
	}

	/**
	 * This method Verifies Column names of EDDAR table from EDDAR pages
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void verifyEDDARTableColumns() throws Exception {
		clickToggleColumnButton();
		String label;
		ArrayList<String> toggleColumns = new ArrayList<>();
		List<WebElement> elementslist = BrowserAccess.getElements(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_ACTIVE_COLUMN_LABEL_XPATH);
		for (WebElement element : elementslist) {
			System.out.println("elementslist: "+elementslist);
			label = element.getAttribute("innerText").trim();
			toggleColumns.add(label);
		}
		ArrayList<String> tableColumns = new ArrayList<>();
		List<WebElement> tableElements = BrowserAccess.getElements(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_ACTIVE_COLUMN_LABEL_XPATH);
		for (WebElement element : tableElements) {
			label = element.getAttribute("innerText").trim();
			if (label != null && !label.equalsIgnoreCase("")) {
				if (label.contains("\n")) {
					label = label.replace("\n", " ");
				}
				tableColumns.add(label);
			}
		}
		if (tableColumns.contains("Corp"))
			tableColumns.remove("Corp");
		if (tableColumns.contains("City"))
			tableColumns.remove("City");
		closeToggleColumn();
		assertTrue(toggleColumns.equals(tableColumns), "Table Coulmns are not matching with Toggle Column selection");
	}

	/**
	 * This method clicks Toggle Column dropdown button on EDDAR pages
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	private static void clickToggleColumnButton() throws Exception {
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_COLUMN_BUTTON_XPATH);	
	}
	
	/**
	 * This method closes on Toggle Column dropdown button on EDDAR pages
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void closeToggleColumn() throws Exception {
		WebElement togglElement = BrowserAccess.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_COLUMN_BUTTON_XPATH);
		String isExpanded = togglElement.getAttribute("aria-expanded");
		if ("true".equalsIgnoreCase(isExpanded)) {
			BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_COLUMN_BUTTON_XPATH);
		}
	}
	
	/**
	 * This method Verifies Unchecks(Removes) the columns from Toggle Column
	 * dropdown on EDDAR pages
	 * @param toggle columns
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void removeColumnsFromEDDARTable(String... toggleColumns) throws Exception {
		if (toggleColumns.length != 0) {
			clickToggleColumnButton();
			for (int i = 0; i < toggleColumns.length; i++) {
				WebElement element = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_REMOVE_COMLUMN_XPATH.name(),String.format(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_REMOVE_COMLUMN_XPATH.toString(),toggleColumns[i])));
				if (toggleColumns[i].equalsIgnoreCase(element.getText().trim())) {
					String value = element.getAttribute("class");
					if ("active".equalsIgnoreCase(value))
						element.click();
				}
			}
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			closeToggleColumn();
		}
	}
	
	
	/**
	 * This method Verifies checks(Adds) the columns from Toggle Column dropdown on EDDAR pages
	 * @lastModifiedBy sagrawal
	 * @param toggle columns
	 * @throws Exception
	 */
	public static void addColumnInQueueTable(String... toggleColumns) throws Exception {
		if (toggleColumns.length != 0) {
			clickToggleColumnButton();
			for (int i = 0; i < toggleColumns.length; i++) {
				WebElement element = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_REMOVE_COMLUMN_XPATH.name(),String.format(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_REMOVE_COMLUMN_XPATH.toString(),toggleColumns[i])));
				if (toggleColumns[i].equalsIgnoreCase(element.getText().trim())) {
					String value = element.getAttribute("class");
					if ("".equalsIgnoreCase(value));
					element.click();
				}
			}
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			closeToggleColumn();
		}
	}
	
	/**
	 * This method verifies the Pagination of data present in EDDAR tables on EDDAR pages
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void verifyPagenation() throws Exception {
		waitUntilCompletePageLoad();
		int pagenateOfNum = Integer.parseInt(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEOFTXT_XPATH).replace("/", "").trim());
		int pagenatePageNum = Integer.parseInt(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim());
		if (pagenateOfNum == pagenatePageNum) {
			Assert.assertEquals(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim(),BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEOFTXT_XPATH).replace("/", "").trim(),"More pages are available");
		} else if (pagenateOfNum == 0) {
			System.out.println("No data avialable to display");
		} else if (pagenateOfNum > pagenatePageNum) {
			BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_LASTPAGINATEBTN_XPATH);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			OrderingCommonPage.checkAlertPopUp();
			Assert.assertEquals(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEOFTXT_XPATH).replace("/", "").trim(),BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim(),"Pagenation to last  page failed");
			BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_FIRSTPAGINATEBTN_XPATH);
			waitUntilCompletePageLoad();
			for (int i = 1; i < 2;) {
				++i;
				BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_NEXTPAGINATEBTN_XPATH);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.checkAlertPopUp();
				Assert.assertEquals(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim(),Integer.toString(i), "Pagenation to next page failed");
			}
			for (int i = 2; i > 1;) {
				--i;
				BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PREVIOUSPAGINATEBTN_XPATH);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.checkAlertPopUp();
				Assert.assertEquals(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim(),Integer.toString(i), "Pagenation to next page failed");
			}
		}
		List<WebElement> recordSize = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_RECORDSIZE_DRPDWN_XPATH);
		Assert.assertEquals(recordSize.get(0).getText().trim(), "100", "Value in record size dropdown is not 100");
		Assert.assertEquals(recordSize.get(1).getText().trim(), "500", "Value in record size dropdown is not 100");
	}
	
	/**
	 * This method clicks on the export pdf button on the EDDAR page
	 * @lastModifiedBy sagrawal
	 * @param section
	 * @param className
	 * @throws Exception
	 */
	public static void clickExportButton(String section, String className) throws Exception {
		switch(section) {
			case "EDDAR":
				exportAndMoveCSV(section, className);
				break;
			default : throw new InvalidSwitchCaseException(section + " invalid option entered.");
			}
		}
		
	/**
	 * Common method to export and move the export to target folder
	 * @throws Exception
	 * @param section
	 * @param className
	 * @lastModifiedBy sagrawal
	 */
	public static void exportAndMoveCSV(String section, String className) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_EXPORT_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_EXPORT_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_EXPORT_BUTTON_CSS);
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_EXPORT_BUTTON_CSS);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), section, className);
	}
	
	/** 
	 * This method clicks on EDDAR header headings
	 * @lastModifiedBy sagrawal
	 * @param columnHeading
	 * @param queue
	 * @throws Exception
	 */
	public static void clickColumnTableHeading(String columnHeading, String section) throws Exception {
		try{
			getColumnTableHeading(columnHeading,section).click();
			OrderingCommonPage.checkGlobalSpinnerPopUp();
		} catch(ElementNotInteractableException e) {
			WebElement scrollArea = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_SCROLL_BODY_CSS);
			((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollLeft = arguments[0].offsetWidth", scrollArea);
			getColumnTableHeading(columnHeading,section).click();
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			scrollArea = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_SCROLL_BODY_CSS);
			((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollLeft = arguments[0].offsetWidth", scrollArea);
		}
	}
	
	/**
	 * This method comparing all checked toggle checkboxes labels with all visible table column headings
	 * @lastModifiesBy sagrawal
	 * @throws Exception
	 */
	public static void compareAllToggleCheckedboxesWithVisibleTableColumns() throws Exception {
		List<String> checkedColumnsList;
		List<String> tableHeadersList;
		checkedColumnsList = BrowserAccess.getElementsText(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_ACTIVE_COLUMN_LABEL_XPATH);
		tableHeadersList = BrowserAction.getElementsText(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_COLUMN_NAME_XPATH);
		System.out.println(checkedColumnsList);
		System.out.println(tableHeadersList);
		tableHeadersList.remove(0);
		for (int i = 0; i < checkedColumnsList.size(); i++) {
			String checkedColumnText = checkedColumnsList.get(i);
			String tableHeading = tableHeadersList.get(i);
			String ignoringSpacesIncheckedColumnText = checkedColumnText.replaceAll("\\s+", "");
			String ignoringSpacesIntableHeading = tableHeading.replaceAll("\\s+", "");
			Assert.assertEquals(ignoringSpacesIncheckedColumnText, ignoringSpacesIntableHeading);
		}
	}
	
	/**
	 * This method unchecking few toggle checkboxes from toggle button.
	 * @lastModifiesBy sagrawal
	 * @throws Exception
	 */
	public static void uncheckFewToggleCheckboxes() throws Exception{
		List <WebElement> deselectedCheckboxesList = BrowserAccess.getElements(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_COLUMN_CHECKBOX_XPATH);
		for(int i=0;i<deselectedCheckboxesList.size()-3;i++){
			deselectedCheckboxesList.get(i).click();
		}
	}
	
	/**
	 * This method comparing checked toggle checkboxes labels with visible table column headings.
	 * @lastModifiesBy sagrawal
	 * @throws Exception
	 */
	public static void compareToggleCheckedboxesWithVisibleTableHeadings() throws Exception {
		List<String> checkedColumnsList;
		List<String> tableHeadersList;
		checkedColumnsList = BrowserAccess.getElementsText(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TOGGLE_ACTIVE_COLUMN_LABEL_XPATH);
		tableHeadersList = BrowserAction.getElementsText(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_COLUMN_NAME_XPATH);
		tableHeadersList.remove(0);
		for (int i = 0; i < checkedColumnsList.size(); i++) {
			String checkedColumnText = checkedColumnsList.get(i);
			String tableHeading = tableHeadersList.get(i);
			String ignoringSpacesIncheckedColumnText = checkedColumnText.replaceAll("\\s+", "");
			String ignoringSpacesIntableHeading = tableHeading.replaceAll("\\s+", "");
			Assert.assertEquals(ignoringSpacesIncheckedColumnText, ignoringSpacesIntableHeading);
		}
	}
	
	/** 
	 * This method gets table header headings
	 * @lastModifiedBy sagrawal
	 * @param columnHeading
	 * @param section
	 * @return
	 * @throws Exception
	 */
	public static WebElement getColumnTableHeading(String columnHeading, String section) throws Exception {
	 WebElement columnTableHeadingWebElement=null;
		switch(columnHeading) {
		case "Corp": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_CORP_XPATH);
			break;
		case "Rule_ID": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_RULE_ID_XPATH);
	    	break;
		case "Rule_Name": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_RULE_NAME_XPATH);
	    	break;
		case "Start_Date": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_START_DATE_XPATH);
			break;
		case "End_Date": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_END_DATE_XPATH);
			break;
		case "State/Province": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_STATE_XPATH);
    		break;
		case "County": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_COUNTY_XPATH);
			break;
		case "City": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_CITY_XPATH);
			break;
		case "Zip/Postal_Code": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_ZIP_XPATH);
			break;
		case "Make": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_MAKE_XPATH);
			break;
		case "Model": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_MODEL_XPATH);
			break;
		case "Product_Class": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_PRODUCT_CLASS_XPATH);
			break;
		case "Max_Distance": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_MAX_DISTANCE_XPATH);
			break;
		case "Max_Courtesy": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_MAX_COURTESY_DELIVERY_XPATH);
			break;
		case "Dealer_Code": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_DEALER_CODE_XPATH);
			break;
		case "Dealer_Name": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_DEALER_NAME_XPATH);
			break;
		case "Always_Use/Never_Use": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_ALWAYS_USE_NEVER_USE_XPATH);
			break;
		case "Last_Updated_By": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_LAST_UPDATED_BY_XPATH);
			break;
		case "Last_Updated_On": columnTableHeadingWebElement = BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_TABLE_LAST_UPDATED_ON_XPATH);
			break;
		default: throw new InvalidSwitchCaseException(columnHeading + " is a invalid work in progress column");
		}
		return columnTableHeadingWebElement;	
		}
		
	/**
	 * This method compares the ui column values with csv column values in the given sorting order.
	 * @lastModifiedBy sagrawal
	 * @param columnDataLabel
	 * @param order
	 * @param section
	 * @throws Exception
	 */
	public static void compareOrderOfColumn(String columnDataLabel, String order, String section) throws Exception {
		List<String> queueColumnDataList = getColumnTableData(columnDataLabel, section).stream().map( e -> {return e.getText().trim();} ).collect(Collectors.toCollection(ArrayList::new));
		String columnHeading = getColumnTableHeading(columnDataLabel, section).getText().replaceAll("\\n", " ");
		List<String> csvQueueColumnDataList = ExcelUtil.getCSVMapData().get(columnHeading);
		if(order.equals("Descending")) {
			csvQueueColumnDataList = csvQueueColumnDataList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
			csvQueueColumnDataList = csvQueueColumnDataList.subList(0, queueColumnDataList.size() );
		} else {
			csvQueueColumnDataList = csvQueueColumnDataList.stream().sorted().collect(Collectors.toList());
			csvQueueColumnDataList = csvQueueColumnDataList.subList(0, queueColumnDataList.size() );
		}
		System.out.println("Column heading: " + columnHeading);
		System.out.println("CSV column value: " + csvQueueColumnDataList);
		System.out.println("UI column value: " + queueColumnDataList);
		Assert.assertEquals(queueColumnDataList, csvQueueColumnDataList, columnHeading + " column values are not in sorted order");
	}

	/** 
	 * This method column table data. 
	 * @lastModifiedBy sagrawal
	 * @param section
	 * @param className
	 * @throws Exception 
	 */	
	public static List<WebElement> getColumnTableData(String columnName , String section) throws Exception {
		List<WebElement> columnTableListWebElement = null;
		if(section.equalsIgnoreCase("EDDAR")) {
			switch(columnName) {
			case "Last_Updated_On": 
				columnTableListWebElement = BrowserAction.getElements(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_LAST_UPDATED_ON_COLUMN_TABLE_DATA_XPATH);
				break;
			case "Start Date":
				columnTableListWebElement = BrowserAction.getElements(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_MAKE_COLUMN_TABLE_DATA_XPATH);
				break;
			default: throw new Exception(columnName + " is a invalid column");	
			}
		}

		return columnTableListWebElement;
	}

	public static void clickAddRule() throws Exception {
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_NEW_BUTTON_XPATH);
	}

	/**
	 * This method verifies all the elements of EDDAR is displayed or not.
	 * @lastModifiedBy sagrawal 
	 * @throws Exception
	 */
	public static void addEDDARPageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_HEADING_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_PAGE_CORP_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_RULE_NAME_XPATH);		
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_MAX_COURTESY_DELIVERY_FEE_XPATH);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_MAX_DELIVERY_DISTANCE_XPATH);	
	}

	/**
	 * This method enters new EDDAR Details.
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void enterEDDARDetails() throws Exception {
		OrderingBOElementDeliveringDealerAssignmentRulesPage.enterRuleName();
		OrderingBOElementDeliveringDealerAssignmentRulesPage.enterDetails();
	}

	private static void enterDetails() throws Exception {
		BrowserAction.enterFieldValue(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_MAX_COURTESY_DELIVERY_FEE_XPATH, CommonPage.getTestData("CustomColumn1"));
		BrowserAction.enterFieldValue(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_MAX_DELIVERY_DISTANCE_XPATH, CommonPage.getTestData("CustomColumn2"));
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_START_DATE_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_START_DATE_SELECTED_DATE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECT_STATE_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECT_STATE_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECTED_STATE_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECT_STATE_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECT_COUNTRY_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECT_COUNTRY_POPUP_HEADING_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECTED_COUNTRY_FROM_POPUP_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECT_COUNTRY_POPUP_OK_BUTTON_XPATH);
		BrowserAction.enterFieldValue(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_MAKE_XPATH, CommonPage.getTestData("CustomColumn5"));
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SAVE_BUTTON_XPATH);
		System.out.println("save button clicked");
	}

	/** 
	 * This method validates that rule has been saved successfully
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void validateSaveRule() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SAVE_POPUP_MESSAGE_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SAVE_POPUP_MESSAGE_XPATH);
	}

	/** 
	 * This method enters rule name
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void enterRuleName() throws Exception {
		ruleName = "Rule" + CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("RuleName", ruleName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_RULE_NAME_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_RULE_NAME_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_RULE_NAME_XPATH);
		BrowserAction.clickandClear(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_RULE_NAME_XPATH);
		System.out.println("Rule Name: " + CommonPage.getTestData("RuleName"));
		BrowserAction.enterFieldValue(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_RULE_NAME_XPATH, CommonPage.getTestData("RuleName"));
	}

	/**
	 * This method validates add EDDAR
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void validateAddEDDARPage() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_FLEET_SPEC_GROUPS_CSS), "Fleet Spec Groups", "Fleet Spec Groups heading validation failed");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_DRIVER_GROUPS_CSS), "Driver Groups", "Driver Groups heading validation failed");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_ASSOCIATIONS_CSS), "Associations", "Associations heading validation failed");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_APPROVAL_RULES_CSS), "Approval Rules", "Approval Rules heading validation failed");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_APPROVER_COMMUNICATIONS_RULES_CSS), "Approver Communications", "Approver Communications heading validation failed");
	}

	/**
	 * This method validates whether rule has been added successfully
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void validateRuleCreated() throws Exception {
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_BACK_TO_QUEUE_VIEW_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_RULE_NAME_LABEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_RULE_NAME_LABEL_XPATH);
		BrowserAction.enterFieldValue(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_RULE_NAME_LABEL_XPATH, CommonPage.getTestData("RuleName"));
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SEARCH_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SEARCHED_RULE_QUEUE_VIEW_XPATH);
		
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SEARCHED_RULE_QUEUE_VIEW_XPATH), CommonPage.getTestData("RuleName") , "Approver Communications heading validation failed");
	}

	/**
	 * This method validates whether it clear filters on exiting
	 * @lastModifiedBy dpatil
	 * @throws Exception
	 */
	public static void verifyClearFiltersOnExitingApplication() throws Exception {
		BrowserAction.enterFieldValue(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_CORP_LABEL_XPATH, "FA");
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SEARCH_BUTTON_XPATH);
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Element Delivering Dealer Assignment Rules");
		String text = BrowserAction.getElementText(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_CORP_LABEL_XPATH);
		System.out.println("text is: "+text);
		if(text.isEmpty()) {
			System.out.println("Filter got cleared as expected");
		}else {
			System.out.println("Filters not cleared");
		}
	}


	/**
	 * This method validates rule has been deleted successfully
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void deleteRule() throws Exception {	
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SEARCHED_RULE_CHECKBOX_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_DELETE_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_DELETE_CONFIRMATION_POPUP_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_DELETE_CONFIRMATION_POPUP_XPATH), "Are you sure you want to delete this entry?" , "wrong message");
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_DELETE_CONFIRMATION_POPUP_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_DELETE_CONFIRMATION_POPUP_OK_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SUCCESSFUL_DELETION_CONFIRMATION_XPATH);
		String deleteConfirmationMessage= BrowserAction.getElementText(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SUCCESSFUL_DELETION_CONFIRMATION_XPATH);
		System.out.println("Confirmation Message for Delete Rule:" + deleteConfirmationMessage);
	}	

	/**
	 * This method validates dealer text box is cleared on removing state from maintenance page.
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void validateDealerTextBoxIsCleared() throws Exception {
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SEARCHED_RULE_QUEUE_VIEW_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_HEADING_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_HEADING_XPATH), "Element Delivering Dealer Assignment Rule" , "Maintentence page not getting open");
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_REMOVE_STATE_XPATH);
		String textInDealer = BrowserAction.getElementText(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_DEALER_XPATH);
		System.out.println("text in dealer is: "+textInDealer);
		if(textInDealer.isEmpty()) {
			System.out.println("Dealer text box got cleared on removing state as expected");
		}else {
			System.out.println("Dealer text box is not cleared on removing state");
		}
	}

	/**
	 * This method delete rule from the maintenance page
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void validateDeleteRuleFromMaintenancePage() throws Exception {
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_DELETE_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_MAINTENANCE_PAGE_DELETE_CONFIRMATION_POPUP_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_MAINTENANCE_PAGE_DELETE_CONFIRMATION_POPUP_XPATH), "Are you sure you want to delete the rule?" , "wrong message");
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_MAINTENANCE_PAGE_DELETE_CONFIRMATION_POPUP_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_DELETE_CONFIRMATION_POPUP_OK_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_MAINTENANCE_SUCCESSFUL_DELETION_CONFIRMATION_XPATH);
		String deleteConfirmationMessage= BrowserAction.getElementText(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_MAINTENANCE_SUCCESSFUL_DELETION_CONFIRMATION_XPATH);
		System.out.println("Delete rule confirmation message:" + deleteConfirmationMessage);
	}

	/**
	 * This method validates deleted rule does not exist
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void verifyDeletedRuleDoesNotExist() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_NO_RECORD_FOUND_XPATH), "No data available in table" , "Rule is present and not deleted succesfully");
	}

	/**
	 * This method select required options for business maintained tables
	 * @latsModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void selectBusinessMaintainedTableOptions(String option) throws Exception {
		Object element = null;
		switch(option) {
		case "Element Delivering Dealer Assignment Rules": element = OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SIDE_MENU_XPATH;
		      break;
		default: throw new InvalidSwitchCaseException(option + "is a invalid Batch Processing option");
		}
		System.out.println("Business Maintained Tables: " + option);
		BrowserAction.click(element);
		OrderingCommonPage.checkAlertPopUp();	
	}
}

	

