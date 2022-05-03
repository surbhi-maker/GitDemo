package com.element.fleet.ordering.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.ExcelUtil;
import com.element.fleet.ordering.enums.OrderingBOQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingWIPOrdersPageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;

public class OrderingWIPOrdersPage {	
	private static List<String> checkedColumnsList;
	private static List<String> visibleColumnsList;
	private static LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();

	/**
	 * This method waits for the Work in progress orders page to load
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForWorkInProcessOrdersToLoad() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_HEADING_ID);
		BrowserVerify.verifyElementIsPresent(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_HEADING_ID);
		BrowserVerify.verifyElementEnabled(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_HEADING_ID);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CORP_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CLIENT_TEXT_FIELD_XPATH);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_UNIT_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDER_TYPE_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_VIN_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_FLEET_SPEC_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_YEAR_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_MANUFACTURER_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_MAKE_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_MODEL_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TRIM_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDERID_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_BODY_XPATH);
	}
	
	/**
	 * This method compares UI provided headings list with user provided heading list
	 * @lastModifiedBy shisingh
	 * @param colName
	 * @throws Exception
	 */
	public static void verifyColumnHeadings() throws Exception {
		System.out.println("User provided column: " + CommonPage.getTestData("CustomColumn1"));
		List<String> userProvidedColName = Arrays.asList(CommonPage.getTestData("CustomColumn1").split("\\|"));
		List<String> uiColName = new ArrayList<>();
		BrowserAction.getElements(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_HEADINGS_CSS).stream().forEach(e->uiColName.add(e.getAttribute("innerText").replaceAll("\\n", " ")));
		System.out.println("UI provided column: " + uiColName);
		Assert.assertEquals(uiColName, userProvidedColName, "UI heading list not matched with provided heading list");
	}

	public static void clickOnTheFirstWIPRecordIfAvailable() throws Exception {
		List<WebElement> tableRows = BrowserAction.getElements(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_XPATH);
		if((tableRows.size()==1)&&(tableRows.get(0).getText().contains("No Results Found")))
			System.out.println("No element available to click\n"+BrowserAction.getElementText(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_XPATH));
		else {
			tableRows.get(0).click();
			OrderingCommonPage.checkAlertPopUp();
			OrderingHomePage.clickCancelOrderPage();
		}
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	public static void enterTextInAllSearchField() throws Exception {
		OrderingWIPOrdersPage.enterTextInCorpSearchField(CommonPage.getTestData("CorpCode"));
		OrderingWIPOrdersPage.enterTextInClientSearchField(CommonPage.getTestData("ClientNumber"));
		OrderingWIPOrdersPage.enterTextInUnitSearchField(CommonPage.getTestData("CustomColumn2"));
		OrderingWIPOrdersPage.enterTextInOrderTypeSearchField(CommonPage.getTestData("OrderType"));
		OrderingWIPOrdersPage.enterTextInVINSearchField(CommonPage.getTestData("VinNumberSearch"));
		OrderingWIPOrdersPage.enterTextInFleetSpecOrConfigIDSearchField(CommonPage.getTestData("FleetSpecSearch"));
		OrderingWIPOrdersPage.enterTextInYearSearchField(CommonPage.getTestData("Year"));
		OrderingWIPOrdersPage.enterTextInManufacturerSearchField(CommonPage.getTestData("Manufacturer"));
		OrderingWIPOrdersPage.enterTextInMakeSearchField(CommonPage.getTestData("Make"));
		OrderingWIPOrdersPage.enterTextInModelSearchField(CommonPage.getTestData("Model"));
		OrderingWIPOrdersPage.enterTextInTrimSearchField(CommonPage.getTestData("Trim"));
		OrderingWIPOrdersPage.enterTextInOrderIDSearchField(CommonPage.getTestData("OrderId"));
	}
	
	public static void verifyTextInAllSearchFieldIsBlank() throws Exception {
		Assert.assertEquals(OrderingWIPOrdersPage.getTextFromCorpSearchField(), "", "Text in Corp field is not blank");
		Assert.assertEquals(OrderingWIPOrdersPage.getTextFromClientSearchField(), "", "Text in Client field is not blank");
		Assert.assertEquals(OrderingWIPOrdersPage.getTextFromUnitSearchField(), "", "Text in Unit field is not blank");
		Assert.assertEquals(OrderingWIPOrdersPage.getTextFromOrderTypeSearchField(), "", "Text in Order type field is not blank");
		Assert.assertEquals(OrderingWIPOrdersPage.getTextFromVINSearchField(), "", "Text in VIN field is not blank");
		Assert.assertEquals(OrderingWIPOrdersPage.getTextFromFleetSpecOrConfigIDSearchField(), "", "Text in Fleet spec field is not blank");
		Assert.assertEquals(OrderingWIPOrdersPage.getTextFromManufacturerSearchField(), "", "Text in Manufacturer field is not blank");
		Assert.assertEquals(OrderingWIPOrdersPage.getTextFromMakeSearchField(), "", "Text in Corp Make is not blank");
		Assert.assertEquals(OrderingWIPOrdersPage.getTextFromModelSearchField(), "", "Text in Corp Model is not blank");
		Assert.assertEquals(OrderingWIPOrdersPage.getTextFromTrimSearchField(), "", "Text in Corp Trim is not blank");
		Assert.assertEquals(OrderingWIPOrdersPage.getTextFromOrderIDSearchField(), "", "Text in Corp Order ID is not blank");
	}
	
	/**
	 * This method will click on Reset button
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickResetButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_RESET_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_RESET_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_RESET_BUTTON_CSS);
		BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_RESET_BUTTON_CSS).click();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	public static void enterTextInCorpSearchField(String corp) throws Exception {
		if(Objects.isNull(corp)) {
			throw new OrderingErrorOccured("Corp column is blank in test data");
		} else {
			System.out.println("Corp: " + corp);
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CORP_TEXT_FIELD_XPATH).sendKeys(corp);
		}
	}

	public static void enterTextInClientSearchField(String client) throws Exception {
		if(Objects.isNull(client)) {
			throw new OrderingErrorOccured("Client column is blank in test data");
		} else {
			System.out.println("Client: " + client);
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CLIENT_TEXT_FIELD_XPATH).sendKeys(client);			
		}
	}

	public static void enterTextInUnitSearchField(String unit) throws Exception {
		if(Objects.isNull(unit)) {
			throw new OrderingErrorOccured("UnitNumber column is blank in test data");
		} else {
			System.out.println("Unit number: " + unit);
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_UNIT_TEXT_FIELD_XPATH).sendKeys(unit);
		}
	}

	public static void enterTextInOrderTypeSearchField(String orderType) throws Exception {
		if(Objects.isNull(orderType)) {
			throw new OrderingErrorOccured("Order type column is blank in test data");
		} else {
			System.out.println("OrderType: " + orderType);
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDER_TYPE_TEXT_FIELD_XPATH).sendKeys(orderType);
		}
	}

	public static void enterTextInVINSearchField(String vin) throws Exception {
		if(Objects.isNull(vin)) {
			throw new OrderingErrorOccured("VIN column is blank in test data");
		} else {
			System.out.println("VIN: " + vin);
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_VIN_TEXT_FIELD_XPATH).sendKeys(vin);
		}
	}

	public static void enterTextInFleetSpecOrConfigIDSearchField(String fleetSpec) throws Exception {
		if(Objects.isNull(fleetSpec)) {
			throw new OrderingErrorOccured("FleetSpec column is blank in test data");
		} else {
			System.out.println("Fleet Spec: " + fleetSpec);
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_FLEET_SPEC_TEXT_FIELD_XPATH).sendKeys(fleetSpec);
		}
	}

	public static void enterTextInYearSearchField(String year) throws Exception {
		if(Objects.isNull(year)) {
			throw new OrderingErrorOccured("Year column is blank in test data");
		} else {
			System.out.println("Year: " + year);
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_YEAR_TEXT_FIELD_XPATH).sendKeys(year);
		}
	}

	public static void enterTextInManufacturerSearchField(String manufacturer) throws Exception {
		if(Objects.isNull(manufacturer)) {
			throw new OrderingErrorOccured("Manufacturer column is blank in test data");
		} else {
			System.out.println("Manufacturer: " + manufacturer);
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_MANUFACTURER_TEXT_FIELD_XPATH).sendKeys(manufacturer);
		}
	}
	
	public static void enterTextInMakeSearchField(String make) throws Exception {
		if(Objects.isNull(make)) {
			throw new OrderingErrorOccured("Make column is blank in test data");
		} else {
			System.out.println("Make: " + make);
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_MAKE_TEXT_FIELD_XPATH).sendKeys(make);
		}
	}

	public static void enterTextInModelSearchField(String model) throws Exception {
		if(Objects.isNull(model)) {
			throw new OrderingErrorOccured("Model column is blank in test data");
		} else {
			System.out.println("Model: " + model);
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_MODEL_TEXT_FIELD_XPATH).sendKeys(model);
		}
	}

	public static void enterTextInTrimSearchField(String trim) throws Exception {
		if(Objects.isNull(trim)) {
			throw new OrderingErrorOccured("Trim column is blank in test data");
		} else {
			System.out.println("Trim: " + trim);
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TRIM_TEXT_FIELD_XPATH).sendKeys(trim);
		}
	}

	public static void enterTextInOrderIDSearchField(String orderID) throws Exception {
		if(Objects.isNull(orderID)) {
			throw new OrderingErrorOccured("OrderID column is blank in test data");
		} else {
			System.out.println("Order ID: " + orderID);
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDERID_TEXT_FIELD_XPATH).sendKeys(orderID);
		}
	}
	
	public static String getTextFromCorpSearchField() throws Exception {
		return BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CORP_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromClientSearchField() throws Exception {
		return BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CLIENT_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromUnitSearchField() throws Exception {
		return BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_UNIT_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromOrderTypeSearchField() throws Exception {
		return BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDER_TYPE_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromVINSearchField() throws Exception {
		return BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_VIN_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromFleetSpecOrConfigIDSearchField() throws Exception {
		return BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_FLEET_SPEC_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromManufacturerSearchField() throws Exception {
		return BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_MANUFACTURER_TEXT_FIELD_XPATH).getAttribute("value");
	}
	
	public static String getTextFromMakeSearchField() throws Exception {
		return BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_MAKE_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromModelSearchField() throws Exception {
		return BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_MODEL_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromTrimSearchField() throws Exception {
		return BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TRIM_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromOrderIDSearchField() throws Exception {
		return BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDERID_TEXT_FIELD_XPATH).getAttribute("value");
	}
	
	public static void setVisibleColumnHeadingsFromTable() throws Exception {
		List<WebElement> columnHeadingsFromWIPTable = BrowserAction.getElements(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_HEADING_ROWS_XPATH);
		visibleColumnsList = new ArrayList<>();
		columnHeadingsFromWIPTable.stream().filter(e->!e.getText().equals("")).forEach(e->visibleColumnsList.add(e.getText().replaceAll("\n", " ")));
	}
	
	public static void compareSelectedAndVisibleColumns() throws Exception {
		Assert.assertTrue(checkedColumnsList.containsAll(visibleColumnsList), "Selected and visible columns are not same");
	}
	
	public static void setCheckedColumns() throws Exception {
		List<WebElement> columnsList = BrowserAction.getElements(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTIONS_XPATH);
		checkedColumnsList = new ArrayList<>();
		for(WebElement columnsOption: columnsList) {
			if(columnsOption.getAttribute("class").equals("active")?true:false){
				checkedColumnsList.add(columnsOption.getAttribute("textContent").trim());
			}
		}
	}
	
	/**
	 * This method will click on toggle columns button
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickToggleColumnsButton() throws Exception {
		BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_BUTTON_CSS).click();
	}
	
	public static void clickRequiredColumnOption(String column) throws Exception {
		WebElement containerWebElement = BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTIONS_CONTAINER_UL_XPATH);
		switch(column) {
			case "Fleet Spec/Config ID":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_FLEET_SPEC_XPATH.getValue())).click();
				break;
			case "Trim":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_TRIM_XPATH.getValue())).click();
				break;
			case "Order ID":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_ORDER_ID_XPATH.getValue())).click();
				break;
			case "First Name":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_FIRST_NAME_XPATH.getValue())).click();
				break;
			case "Corp":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_CORP_XPATH.getValue())).click();
				break;
			case "Make":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_MAKE_XPATH.getValue())).click();
				break;
			case "Unit":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_UNIT_XPATH.getValue())).click();
				break;
			case "Last Updated Date":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_LAST_UPDATED_DATE_XPATH.getValue())).click();
				break;
			case "Order Type":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_ORDER_TYPE_XPATH.getValue())).click();
				break;
			case "Year":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_YEAR_XPATH.getValue())).click();
				break;
			case "Last Updated By":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_LAST_UPDATED_BY_XPATH.getValue())).click();
				break;
			case "Manufacturer":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_MANUFACTURER_XPATH.getValue())).click();
				break;
			case "Model":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_MODEL_XPATH.getValue())).click();
				break;
			case "VIN":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_VIN_XPATH.getValue())).click();
				break;
			case "Client":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_CLIENT_XPATH.getValue())).click();
				break;
			case "Last Name":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_LAST_NAME_XPATH.getValue())).click();
				break;
			default: throw new InvalidSwitchCaseException(column+" is invalid option");
		}
	}
	
	/**
	 * This method enters orderid in the search field and validate only searched order id is displayed.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void searchOrderID() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDER_TEXT_BOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDER_TEXT_BOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDER_TEXT_BOX_XPATH);
		System.out.println("Order id: " + CommonPage.getElementOrderObject().getDriverTabObject().getOrderID());
		BrowserAction.enterFieldValue(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDER_TEXT_BOX_XPATH, CommonPage.getElementOrderObject().getDriverTabObject().getOrderID());
		BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_SEARCH_BTN_XPATH).click();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_ORDERID_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_ORDERID_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_ORDERID_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_ORDERID_XPATH.getValue()), 1));
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_ORDERID_XPATH), CommonPage.getElementOrderObject().getDriverTabObject().getOrderID(), "Order id on the WIP page not matched");
	}
	
	/**
	 * This method click on the searched order.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnSearchedOrderID() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_ORDERID_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_ORDERID_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_ORDERID_XPATH);
		BrowserAction.click(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_ORDERID_XPATH);
	}
	
	/**
	 * This method will verify searched data
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void searchAndVerifyTheRecords(String columnName, String searchText) throws Exception {
		if(Objects.isNull(columnName)||Objects.isNull(searchText)) {
			throw new OrderingErrorOccured("Column name or searchTest is empty");
		} else {
			System.out.println("Search text: " + searchText + "Column name: " + columnName);
			OrderingWIPOrdersPage.searchOrderByColumnName(columnName, searchText);
			OrderingWIPOrdersPage.verifySearchedRecords(columnName, searchText);
			OrderingWIPOrdersPage.clickResetButton();
		}
	}
	
	public static void searchOrderByColumnName(String columnName, String searchText) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		WebDriverAction.clear(BrowserAccess.getLocator(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_SEARCH_FIELDS_VALUE_XPATH.name(), String.format(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_SEARCH_FIELDS_VALUE_XPATH.toString(),columnName)));
		System.out.println(columnName + " value:" + searchText);
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_SEARCH_FIELDS_VALUE_XPATH.name(), String.format(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_SEARCH_FIELDS_VALUE_XPATH.toString(),columnName)), searchText);
		BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_SEARCH_BTN_XPATH).click();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	public static void verifySearchedRecords(String columnName, String searchText) throws Exception {
		if(result.isEmpty()) {
			OrderingWIPOrdersPage.buildSearchOptionData();
		}
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_XPATH)));
		List<WebElement> elementsList = WebDriverAccess.getElements(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_SEARCHED_RECORDS_RESULT_XPATH.name(), String.format(
				OrderingBOQueuePageEnum.ORDERING_BO_SEARCHED_RECORDS_RESULT_XPATH.toString(),
				result.get(columnName))));
		for (WebElement element : elementsList) { 
			Assert.assertEquals(element.getAttribute("innerText").trim().toLowerCase(), searchText.trim().toLowerCase(), "Searched record is not matching");
		}
	}
	
	public static void buildSearchOptionData() throws Exception {
		String label;
		String attribute;
		List<WebElement> elementsList=BrowserAccess.getElements(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_SEARCH_FIELDS_LABEL_XPATH);
		for (WebElement element : elementsList) {
			label = element.getText().trim();
			if (label != null && !label.equalsIgnoreCase("")) {
				attribute = WebDriverAccess.getElementAttributeValue(BrowserAccess.getLocator(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_SEARCH_FIELDS_VALUE_XPATH.name(),
						String.format(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_SEARCH_FIELDS_VALUE_XPATH.toString(),label)), "name");
				result.put(label, attribute);
			}
		}
	}


	/**
	 * This method will verify rejected orders in
	 * @lastModifiedBy Sweety Agrawal
	 */
	public static void verifyRejectedReasonByMouseHover() throws Exception {
		BrowserAction.click(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_STATUS_SORTING_ICON_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_REJECTED_ORDER_STATUS_XPATH)));
		String rejectedStatus = WebDriverAccess.getDriver().findElement(By.xpath("//tr[@class='queueData high-light-red odd'][1]//td[9]")).getText();
		Assert.assertEquals(rejectedStatus,"Saved- Rejected Approval", "status doesn't match");
		BrowserAction.hoverOverElement(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_REJECTED_ORDER_STATUS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_REJECTED_STATUS_REASON_XPATH);
		String color=WebDriverAccess.getDriver().findElement(By.xpath("//tbody//tr[1]")).getCssValue("background-color");
		System.out.println(color);
		String hex = Color.fromString(color).asHex();
		Assert.assertEquals(hex,"#d9534f", "Invalid color, color should be red for the rejected orders ");
		}

	/**
	 * This method verifies the export functionality.
	 * @lastModifiedBy Sweety Agrawal
	 * @param wip
	 * @param className
	 * @throws Exception
	 */
	public static void verifyExportCSV(String wip, String className) throws Exception {
		OrderingWIPOrdersPage.clickExportButton(wip, className);
		OrderingCommonPage.checkAlertPopUp();
		OrderingWIPOrdersPage.verifyCSVData(className, wip);
	}
	
	
	/**
	 * This method clicks on the export button on the BO ManagerOrderPreferences page.
	 * @lastModifiedBy Sweety Agrawal
	 * @param wip
	 * @param className
	 * @throws Exception
	 */
	public static void clickExportButton(String wip, String className) throws Exception {
		WebDriverAccess.getElement(By.xpath(String.format(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_EXPORT_BUTTON_XPATH.toString(), wip))).isDisplayed();
		WebDriverAccess.getElement(By.xpath(String.format(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_EXPORT_BUTTON_XPATH.toString(), wip))).isEnabled();
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		WebDriverAccess.getElement(By.xpath(String.format(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_EXPORT_BUTTON_XPATH.toString(), wip))).click();
		OrderingCommonPage.checkAlertPopUp();
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"), className);
	}
	
	/**
	 * This method verifies the CSV Column Heading Values with Grid Column Heading Values.
	 * @lastModifiedBy Sweety Agrawal
	 * @param fileNameStartsWith
	 * @param wip
	 * @throws Exception
	 */
	public static void verifyCSVData(String fileNameStartsWith, String wip) throws Exception {
		List<String> csvRows = ExcelUtil.getCSVRows(System.getProperty("user.dir") + "\\target\\" + fileNameStartsWith + ".csv");
		System.out.println("CSV Rows List : " + csvRows);
		List<String> gridColumnHeadingNamesList = getColumnHeadingList(wip);
		int csvRowsCount = csvRows.size();
		if (csvRowsCount == 0) {
			System.out.println("Table does not contains any row.");
		} else {
			String csvColumnHeadingRow = csvRows.get(0);
			String[] csvColumnHeadingSplittedRowValues;
			csvColumnHeadingSplittedRowValues = csvColumnHeadingRow.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			List<String> csvColumnHeadingList = Arrays.asList(csvColumnHeadingSplittedRowValues);
			System.out.println("CSV Column Heading Values :" + csvColumnHeadingList);
			System.out.println("Grid Column Heading Values :" + gridColumnHeadingNamesList);
			csvColumnHeadingList.containsAll(gridColumnHeadingNamesList);
		}
	}
	
	/**
	 * This method returns all the column headings of the table.
	 * @lastModifiedBy Sweety Agrawal
	 * @param wip
	 * @throws Exception
	 */
	public static List<String> getColumnHeadingList(String wip) throws Exception {
		List<String> gridColumnHeadingList = null;
		gridColumnHeadingList = WebDriverAccess.getElementsText(By.xpath(String.format(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_GRID_VIEW_LIST_TABLE_HEADING_XPATH.toString(), wip)));
		gridColumnHeadingList.remove(0);
		System.out.println("Column Names:" + gridColumnHeadingList);
		return gridColumnHeadingList;
	} 
	
	/**
	 * This method verifies that searched order has a rejected status
	 * @lastModifiedBy Hector_Jimenez
	 * @throws Exception
	 */
	public static void verifyRejectedStatusLabel() throws Exception {
		String orderStateText = BrowserAction.getElementText(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDERSTATE_LABEL_XPATH);
		Assert.assertEquals(orderStateText.contains("Saved- Rejected Approval"), true, "This order should be in rejected state");
	} 
	
	/**
	 * This method verifies that user can cancel a rejected order
	 * @lastModifiedBy Hector_Jimenez
	 * @throws Exception
	 */
	public static void cancelRejectedOrder() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_CHECKBOX_NAME);
		BrowserAction.click(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_CHECKBOX_NAME);
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_CANCEL_BUTTON_XPATH);
		BrowserAction.click(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_CANCEL_BUTTON_XPATH);
		OrderingWIPOrdersPage.handleCancelPopup("Yes");
		OrderingWIPOrdersPage.verifyNoRecordsDisplay();
		
	} 
	/**
	 * This method verifies that when searching no records are displayed
	 * @lastModifiedBy Hector_Jimenez
	 * @throws Exception
	 */
	public static void verifyNoRecordsDisplay() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_NORESULTS_LABEL_XPATH);
		Assert.assertEquals(BrowserAction.getElementText(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_NORESULTS_LABEL_XPATH).equalsIgnoreCase("No Results Found"), true, "After cancelling rejected orders it should get deleted from WIP queue");
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method handles the cancel popup
	 * @lastModifiedBy Hector_Jimenez
	 * @param String action ("Yes" / "No")
	 * @throws Exception
	 */
	public static void handleCancelPopup(String action) throws Exception {
	switch (action) {
		case "Yes":
			BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_CANCELORDERS_MODAL_ID);
			BrowserAction.click(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_ACCEPT_BUTTON_MODAL_ID);
			BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_TEXT_AREA_MODAL_XPATH);
			BrowserAction.enterFieldValue(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_TEXT_AREA_MODAL_XPATH, "Reject sample test");
			BrowserAction.click(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_ACCEPT_BUTTON_MODAL_ID);
			BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_SUCCESS_CANCEL_MODAL_XPATH);
			WebElement successCancelModal = BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_SUCCESS_CANCEL_MODAL_ID);
			WebElement acceptButton = successCancelModal.findElement(By.id("modal-accept-btn"));
			BrowserWait.waitUntilElementIsDisplayed(acceptButton);
			acceptButton.click();
			break;
		case "No":
			BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_CANCELORDERS_MODAL_ID);
			BrowserAction.click(OrderingWIPOrdersPageEnum.ORDERING_FO_WIP_ORDER_CANCEL_BUTTON_MODAL_ID);
			OrderingCommonPage.verifyNoAlertPopUpDispalyed();
			break;
		default: throw new InvalidSwitchCaseException(action + " is an invalid option");
		}

	} 
	
	/**
	 * This method verifies the cancel button on WIP Queue page
	 * @throws Throwable 
	 * @lastModifiedBy skathule
	 */
	public static void verifyCancelButton() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CHECKBOX_NAME);
		BrowserVerify.verifyElementEnabled(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CHECKBOX_NAME);
		BrowserVerify.verifyElementIsPresent(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CHECKBOX_NAME);
		BrowserAction.click(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CHECKBOX_NAME);
		PDFReporter.takeExtraScreenshot();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CANCEL_BUTTON_XPATH)));	
	}
	
	/**
	 * This method clicks on the cancel button of WIPOrder page.
	 * @throws Throwable 
	 * @lastModifiedBy skathule
	 */
	public static void cancelOrderFromWIPQueue() throws Throwable {
		verifyCancelButton();
		BrowserAction.click(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CANCEL_BUTTON_XPATH);
		CommonPage.clickElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CANCEL_POP_UP_ACCEPT_XPATH);
		PDFReporter.takeExtraScreenshot();
		CommonPage.clickElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CANCEL_POP_UP_ACCEPT_COMMENTS_XPATH);
		CommonPage.clickElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_CANCEL_SUCCESS_POPUP_ACCEPT_XPATH);
	}
	
	/**
	 * This method verifies the cancelled order present or not in WIP page
	 * @lastModifiedBy skathule
	 * @param OrderId
	 * @throws Exception
	 */
	public static void verifyCancelledOrderInWIPQueue(String columnName,String searchText) throws Exception {
		BrowserAction.clickandClear(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDER_TEXT_BOX_XPATH);
		System.out.println("Searching order with order ID:: "+CommonPage.getElementOrderObject().getDriverTabObject().getOrderID());
		BrowserAction.enterFieldValue(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDER_TEXT_BOX_XPATH, CommonPage.getElementOrderObject().getDriverTabObject().getOrderID());
		BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_SEARCH_BTN_XPATH).click();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_EMPTY_ROWS_XPATH.getValue()),1));	
		BrowserAction.clickandClear(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_ORDER_TEXT_BOX_XPATH);
	}
	
	/**
	 * This method verifies the WIP page records.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void wipPageRecords() throws Exception {
		OrderingBOQueuePage.changePageRecordSize("100");
		OrderingBOQueuePage.changePageRecordSize("500");	
	}
	
	/**
	 * This method will enter text in log number field
	 * @lastModifiedBy SAgrawal
	 */
	public static void enterTextInLogNumberSearchFieldFO(String logNumber) throws Exception {
		if(Objects.isNull(logNumber)) {
			throw new OrderingErrorOccured("Log Number column is blank in test data");
		} else {
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_LOGNUMBER_TEXT_FIELD_XPATH).clear();
			System.out.println("Log Number: " + logNumber);
			BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_LOGNUMBER_TEXT_FIELD_XPATH).sendKeys(logNumber);
		}
	}
	
	/**
	 * This method will click on search button
	 * @lastModifiedBy SAgrawal
	 */
	public static void clickOnSearch() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_SEARCH_BTN_XPATH);
		BrowserAction.click(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_SEARCH_BTN_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
}
