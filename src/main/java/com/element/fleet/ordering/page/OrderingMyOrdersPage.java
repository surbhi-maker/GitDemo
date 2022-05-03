package com.element.fleet.ordering.page;

import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingMyOrdersPageEnum;
import com.element.fleet.ordering.enums.OrderingSummaryPageEnum;
import com.element.fleet.ordering.enums.OrderingWIPOrdersPageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;
import com.ge.capital.rainbow.webdriver.WebDriverWaits;

public class OrderingMyOrdersPage {
	
	public static void waitForMyOrdersToLoad() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDEING_MY_ORDERS_HEADING_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDEING_MY_ORDERS_LOG_NUMBER_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDEING_MY_ORDERS_CORP_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDEING_MY_ORDERS_TABLE_BODY_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDEING_MY_ORDERS_TABLE_ROW_XPATH);
	}
	
	public static void waitForMyOrdersQueueToLoad() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_HEADING_ID);
		BrowserVerify.verifyElementIsPresent(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_HEADING_ID);
		BrowserVerify.verifyElementEnabled(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_HEADING_ID);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_CORP_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_CLIENT_TEXT_FIELD_XPATH);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_UNIT_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_ORDER_TYPE_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_VIN_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_FLEET_SPEC_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_YEAR_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_MANUFACTURER_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_MAKE_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_MODEL_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_TRIM_TEXT_FIELD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_TABLE_BODY_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MY_ORDER_PAGE_SEARCHBTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_RESET_BUTTON_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_EXPORT_BUTTON_CSS);
	}
	
	/**
	 * This method verify the post summary general information
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyOrdersGeneralDetails() throws Exception {
		String clientUnitNumber = BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_CLIENT_UNIT_XPATH).getText();
		String logNumber = BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_LOG_NUMBER_XPATH).getText();
		String orderType = BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_TYPE_XPATH).getText();
		String orderId = BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_ID_XPATH).getText();
		assertEquals(clientUnitNumber.trim(), CommonPage.getTestData("ClientNumber")+" / "+CommonPage.getElementOrderObject().getStartHereTabObject().getNewUnitNumber(), "client and unit number is not matched");
		assertEquals(logNumber.trim(), CommonPage.getElementOrderObject().getLogNumber(), "Log number is not matched");
		assertEquals(orderType.trim(), CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderType(), "order type  is not matched");
		assertEquals(orderId.trim(), CommonPage.getElementOrderObject().getDriverTabObject().getOrderID(), "Order id is not matched");
	}
	
	/**
	 * This method verify the post summary general information
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyOrdersGeneralDetailsFromBO() throws Exception {
		String clientUnitNumber = BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_CLIENT_UNIT_XPATH).getText();
		String logNumber = BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_LOG_NUMBER_XPATH).getText();
		String orderType = BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_TYPE_XPATH).getText();
		String orderId = BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_ID_XPATH).getText();
		String vin = BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDER_PAGE_GENERAL_ORDER_VIN_XPATH).getText();
		assertEquals(clientUnitNumber.trim(), CommonPage.getTestData("ClientNumber")+" / "+CommonPage.getElementOrderObject().getStartHereTabObject().getNewUnitNumber(), "Client and Unit Number is not matched");
		assertEquals(logNumber.trim(), CommonPage.getElementOrderObject().getLogNumber(), "Log Number is not matched");
		assertEquals(orderType.trim(), CommonPage.getTestData("CustomColumn1")+" Order", "Order type is not matched");
		assertEquals(orderId.trim(), CommonPage.getElementOrderObject().getDriverTabObject().getOrderID(), "Order Id is not matched");
		assertEquals(vin.trim(), CommonPage.getTestData("VinNumberSearch"), "VIN is not matched");
	}
	
	/**
	 * This method verify the columns heading from table
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyColumnHeadings() throws Exception {
		System.out.println("User provided column: " + CommonPage.getTestData("CustomColumn1"));
		List<String> userProvidedColName = Arrays.asList(CommonPage.getTestData("CustomColumn1").split("\\|"));
		List<String> uiColName = new ArrayList<>();
		new WebDriverWait(WebDriverAccess.getDriver(),new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_HEADINGS_CSS.getValue()), userProvidedColName.size()));
		List<WebElement> columnName = BrowserAction.getElements(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_HEADINGS_CSS);
		int count=0;
		for(WebElement ele : columnName) {
			uiColName.add(ele.getText().replaceAll("\\n", " "));
			count++;
			if(count == 13)
				BrowserAction.hoverOverElement(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_TABLE_LAST_COLUMN_XPATH);
		}
		Assert.assertEquals(uiColName, userProvidedColName, "UI heading list not matched with provided heading list");
	}
	
	/**
	 * This method will enter the search data in search fields
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterTextInAllSearchFieldMyOrders() throws Exception {
		OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LogNumber"));
		OrderingWIPOrdersPage.enterTextInCorpSearchField(CommonPage.getTestData("CorpCode"));
		OrderingWIPOrdersPage.enterTextInClientSearchField(CommonPage.getTestData("ClientNumber"));
		OrderingWIPOrdersPage.enterTextInUnitSearchField(CommonPage.getTestData("UnitNumber"));
		OrderingWIPOrdersPage.enterTextInOrderTypeSearchField(CommonPage.getTestData("OrderType"));
		OrderingWIPOrdersPage.enterTextInVINSearchField(CommonPage.getTestData("VinNumberSearch"));
		OrderingWIPOrdersPage.enterTextInFleetSpecOrConfigIDSearchField(CommonPage.getTestData("FleetSpecSearch"));
		OrderingWIPOrdersPage.enterTextInYearSearchField(CommonPage.getTestData("Year"));
		OrderingWIPOrdersPage.enterTextInManufacturerSearchField(CommonPage.getTestData("Manufacturer"));
		OrderingWIPOrdersPage.enterTextInMakeSearchField(CommonPage.getTestData("Make"));
		OrderingWIPOrdersPage.enterTextInModelSearchField(CommonPage.getTestData("Model"));
		OrderingWIPOrdersPage.enterTextInTrimSearchField(CommonPage.getTestData("Trim"));
	}
	
	/**
	 * This method will verify search field cleared
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyTextInAllSearchFieldIsBlankMyOrders() throws Exception {
		Assert.assertEquals(getTextFromLogNumberSearchField1(), "", "Text in Log Number field is not blank");
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
	}
	
	/** 
	 * This method will get element text
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static String getTextFromLogNumberSearchField1() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_LOG_NUMBER_TEXT_FIELD_XPATH).getAttribute("value");
	}
	
	/**
	 * This method will enter text in log number field
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterTextInLogNumberSearchFieldFO(String logNumber) throws Exception {
		if(Objects.isNull(logNumber)) {
			throw new OrderingErrorOccured("Log Number column is blank in test data");
		} else {
			BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_LOG_NUMBER_TEXT_FIELD_XPATH).clear();
			System.out.println("Log Number: " + logNumber);
			BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_LOG_NUMBER_TEXT_FIELD_XPATH).sendKeys(logNumber);
		}
	}
	
	/**
	 * This method will verify that no orders are available in My Orders section.
	 * @lastModifiedBy shisingh
	 */
	public static void verifyNoOrdersPresentInMyOrdersSection() throws Exception {
		int singleOrderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		for(int j=1; j<=singleOrderCount; ++j) {
			BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_LOG_NUMBER_TEXT_FIELD_XPATH).clear();
			System.out.println("Log Number: " + CommonPage.getTestData("LoggerLogNumber"+j));
			BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_LOG_NUMBER_TEXT_FIELD_XPATH).sendKeys(CommonPage.getTestData("LoggerLogNumber"+j));
			OrderingMyOrdersPage.clickOnSearch();
			OrderingMyOrdersPage.verifyNoSearchedOrderDisplayed();			
		}
	}
	
	/**
	 * This method will provide columns heading name
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static String getColumnNameHeading(String columnName) throws Exception {
		String dataName = null;
			switch(columnName) {
			case "Log": dataName = BrowserAction.getElementAttributeValue(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_LOG_NUMBER_COLUMN_HEADER_XPATH, "data-name");
				break;
			case "Log Number": dataName = BrowserAction.getElementAttributeValue(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_LOG_NUMBER_COLUMN_HEADER_XPATH, "data-name");
				break;
			case "Client": dataName = BrowserAction.getElementAttributeValue(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_CLIENT_NUMBER_COLUMN_HEADER_XPATH, "data-name");
				break;
			case "Client Number": dataName = BrowserAction.getElementAttributeValue(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_CLIENT_NUMBER_COLUMN_HEADER_XPATH, "data-name");
				break;
			case "Unit": dataName = BrowserAction.getElementAttributeValue(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_UNIT_NUMBER_COLUMN_HEADER_XPATH, "data-name");
				break;
			case "Unit Number": dataName = BrowserAction.getElementAttributeValue(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_UNIT_NUMBER_COLUMN_HEADER_XPATH, "data-name");
				break;
			case "Corp": dataName = BrowserAction.getElementAttributeValue(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_CORP_CODE_COLUMN_HEADER_XPATH, "data-name");
				break;
			case "Corp Code": dataName = BrowserAction.getElementAttributeValue(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_CORP_CODE_COLUMN_HEADER_XPATH, "data-name");
				break;
			default: throw new InvalidSwitchCaseException(columnName+" is invalid option");
		}
		return dataName;
	}
	
	/**
	 * This method will verify search field functionality
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void searchFieldFuntionality(String searchField, String searchData) throws Exception {
		String searchFieldName =  OrderingMyOrdersPageEnum.ORDERING_MYORDERS_SEARCH_FIELDS_XPATH.getValue();
		searchFieldName = searchFieldName.replace("#searchField$", searchField);
		WebDriverAction.enterFieldValue(By.xpath(searchFieldName), searchData);
		System.out.println(searchField + ": " + searchData);
	}
	
	/**
	 * This method will click on search button
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnSearch() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_SEARCH_BUTTON_XPATH);
		BrowserAction.click(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_SEARCH_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method will search for specified data
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void searchDataForSorting() throws Exception {
		OrderingMyOrdersPage.searchFieldFuntionality("Make", "BMW");
		OrderingMyOrdersPage.searchFieldFuntionality("Model", "330");
		OrderingMyOrdersPage.clickOnSearch();
	}
	
	/**
	 * This method will verify columns asc and desc order
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyAscAndDescColumnsSorting(String columnName) throws Exception {
		verifyColumnSorting(columnName);
	}
	
	/**
	 * This method will verify column asc desc order
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyColumnSorting(String columnName) throws Exception {
		ArrayList<String> beforeList = new ArrayList<>();
		ArrayList<String> afterList = new ArrayList<>();
		String dataName = getColumnNameHeading(columnName);
		String headerColumnName = OrderingMyOrdersPageEnum.ORDERING_MYORDERS_COLUMN_HEADER_NAME_XPATH.getValue();
		headerColumnName = headerColumnName.replace("#dataName$", dataName);
		String columnDataList = OrderingMyOrdersPageEnum.ORDERING_MYORDERS_COLUMN_DATA_LIST_XPATH.getValue();
		columnDataList = columnDataList.replace("#dataName$", dataName);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(columnDataList));
		List<WebElement> list = WebDriverAction.getElements(By.xpath(columnDataList));
		for(WebElement ele : list) {
			beforeList.add(ele.getText().trim());
		}
		Collections.sort(beforeList);
		Collections.reverse(beforeList);
		WebDriverAction.click(By.xpath(headerColumnName));
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(columnDataList));
		List<WebElement> list1 = WebDriverAccess.getElements(By.xpath(columnDataList));
		for(WebElement ele : list1) {
			afterList.add(ele.getText().trim());
		}
		assertEquals(afterList, beforeList , "Descending sorting is not working for column:-"+columnName);
		Collections.reverse(beforeList);
		Collections.sort(beforeList);
		afterList.clear();
		WebDriverAction.click(By.xpath(headerColumnName));
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(columnDataList));
		List<WebElement> list2 = WebDriverAccess.getElements(By.xpath(columnDataList));
		for(WebElement ele : list2) {
				afterList.add(ele.getText().trim());
		}
		assertEquals(afterList, beforeList , "Ascending sorting is not working for column:-"+columnName);
	}
	
	/**
	 * This method will provide rquired column
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickRequiredColumnOptions(String column) throws Exception {
		WebElement containerWebElement = BrowserAction.getElement(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTIONS_CONTAINER_UL_XPATH);
		switch(column) {
			case "Fleet Spec/Config ID":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_FLEET_SPEC_XPATH.getValue())).click();
				break;
			case "Trim":containerWebElement.findElement(By.xpath(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TOGGLE_OPTION_TRIM_XPATH.getValue())).click();
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
			default: throw new InvalidSwitchCaseException(column+" is invalid option");
		}
	}
	
	/**
	 * This method will wait for summary page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForSummaryPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_POST_SUBMIT_ORDERID_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_POST_EXPORT_PDF_CSS);
	}
	
	public static void clickOnTheFirstMyOrdersRecordIfAvailable() throws Exception {
		List<WebElement> tableRows = BrowserAction.getElements(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_XPATH);
		if((tableRows.size()==1)&&(tableRows.get(0).getText().contains("No Results Found")))
			System.out.println("No element available to click\n"+BrowserAction.getElementText(OrderingWIPOrdersPageEnum.ORDERING_WIPORDERS_TABLE_ROW_XPATH));
		else {
			tableRows.get(0).click();
			OrderingCommonPage.checkAlertPopUp();
			OrderingMyOrdersPage.waitForSummaryPage();
			OrderingSummaryPage.closeOrderSummary();
		}	
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	public static void clickOnSearchedOrder() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MY_ORDER_PAGE_LOGNUMBERHYPERLINK_XPATH);
		BrowserAction.click(OrderingMyOrdersPageEnum.ORDERING_MY_ORDER_PAGE_LOGNUMBERHYPERLINK_XPATH);
	}
	
	/**
	 * This method waits until no order is displayed.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyNoSearchedOrderDisplayed() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_ROW_CSS.getValue()), 0));
	}
	
	/**
	 * This method waits until single order is displayed.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifySingleSearchedOrderDisplayed() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_ROW_CSS.getValue()), 1));
	}
	
	/**
	 * This method waits until single order is displayed in red colour.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifySingleSearchedOrderRowColour() throws Exception {
		String rowColour = Color.fromString(new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_ROW_CSS.getValue()), 1)).get(0).getCssValue("background-color")).asHex();
		if(!rowColour.equals("#d9534f")) {
			System.err.println("Row new colour: " + rowColour);
			throw new OrderingErrorOccured("Rejeted order row colour should be #d9534f adn not " + rowColour);
		}
	}
	
	/**
	 * This method clicks on single order.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickSingleSearchedOrderDisplayed() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_ROW_CSS.getValue()), 1)).get(0).click();
	}
	
	public static void clickSearchButton() throws Exception {
		BrowserAction.click(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_SEARCH_BUTTON_XPATH);
	}
	
	public static void enterTextInLogNumberSearchField(String lognumber) throws Exception {
		BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDEING_MY_ORDERS_LOG_NUMBER_TEXT_FIELD_XPATH).sendKeys(lognumber);
	}

	public static void enterTextInCorpSearchField(String corp) throws Exception {
		BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDEING_MY_ORDERS_CORP_TEXT_FIELD_XPATH).sendKeys(corp);
	}

	public static void enterTextInClientSearchField(String client) throws Exception {
		BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_CLIENT_TEXT_FIELD_XPATH).sendKeys(client);
	}

	public static void enterTextInUnitSearchField(String unit) throws Exception {
		BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_UNIT_TEXT_FIELD_XPATH).sendKeys(unit);
	}

	public static void enterTextInOrderTypeSearchField(String orderType) throws Exception {
		BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_ORDER_TYPE_TEXT_FIELD_XPATH).sendKeys(orderType);
	}

	public static void enterTextInVINSearchField(String vin) throws Exception {
		BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_VIN_TEXT_FIELD_XPATH).sendKeys(vin);
	}

	public static void enterTextInFleetSpecOrConfigIDSearchField(String fleetSpec) throws Exception {
		BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_FLEET_SPEC_TEXT_FIELD_XPATH).sendKeys(fleetSpec);
	}

	public static void enterTextInYearSearchField(String year) throws Exception {
		BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_YEAR_TEXT_FIELD_XPATH).sendKeys(year);
	}

	public static void enterTextInManufacturerSearchField(String manufacturer) throws Exception {
		BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_MANUFACTURER_TEXT_FIELD_XPATH).sendKeys(manufacturer);
	}
	
	public static void enterTextInMakeSearchField(String make) throws Exception {
		BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_MAKE_TEXT_FIELD_XPATH).sendKeys(make);
	}

	public static void enterTextInModelSearchField(String model) throws Exception {
		BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_MODEL_TEXT_FIELD_XPATH).sendKeys(model);
	}

	public static void enterTextInTrimSearchField(String trim) throws Exception {
		BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_TRIM_TEXT_FIELD_XPATH).sendKeys(trim);
	}

	public static void enterTextInOrderIDSearchField(String orderID) throws Exception {
		BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_ORDERID_TEXT_FIELD_XPATH).sendKeys(orderID);
	}
		
	public static String getTextFromLogNumberSearchField() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDEING_MY_ORDERS_LOG_NUMBER_TEXT_FIELD_XPATH).getAttribute("value");
	}
	
	public static String getTextFromCorpSearchField() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDEING_MY_ORDERS_CORP_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromClientSearchField() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_CLIENT_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromUnitSearchField() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_UNIT_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromOrderTypeSearchField() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_ORDER_TYPE_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromVINSearchField() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_VIN_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromFleetSpecOrConfigIDSearchField() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_FLEET_SPEC_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromYearSearchField() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_YEAR_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromManufacturerSearchField() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_MANUFACTURER_TEXT_FIELD_XPATH).getAttribute("value");
	}
	
	public static String getTextFromMakeSearchField() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_MAKE_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromModelSearchField() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_MODEL_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromTrimSearchField() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_TRIM_TEXT_FIELD_XPATH).getAttribute("value");
	}

	public static String getTextFromOrderIDSearchField() throws Exception {
		return BrowserAction.getElement(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_ORDERID_TEXT_FIELD_XPATH).getAttribute("value");
	}
	
	/**This method clicks on the Export button.
	 * @lastModifiedBy shisingh
	 */
	public static void clickOnExportButton(String fileName) throws Exception {
		OrderingSummaryPage.clickExportButton("WIP", fileName);
	}
	
	/**
	 * This method verifies correct search fields are present and displayed
	 * @throws Exception 
	 * @lastModifiedBy vikumar
	 */
	public static void verifySearchFieldsArePresent() throws Exception {
		List<String> expectedSearchFieldNames = Arrays.asList(CommonPage.getTestData("CustomColumn2").split("\\|"));
		ArrayList<String> actualSearchFieldsNames=new ArrayList<>();
		List<WebElement> searchFieldElementList=BrowserAccess.getElements(OrderingMyOrdersPageEnum.ORDERING_MYORDERS_SEARCH_FIELDS_LIST_XPATH);
		for(WebElement e: searchFieldElementList) {
			BrowserWait.waitUntilElementIsDisplayed(e);
			actualSearchFieldsNames.add(e.getText().trim());	
		}
		expectedSearchFieldNames.sort(Comparator.naturalOrder());
		actualSearchFieldsNames.sort(Comparator.naturalOrder());
		Assert.assertTrue(expectedSearchFieldNames.equals(actualSearchFieldsNames), "Search Fields are not matching");
	}
	
 }