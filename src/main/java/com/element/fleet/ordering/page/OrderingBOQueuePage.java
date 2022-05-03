package com.element.fleet.ordering.page;

import static org.testng.Assert.assertTrue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.ExcelUtil;
import com.element.fleet.ordering.enums.OrderingBOCreditQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingBOQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingBOQueuesCommonXpathEnum;
import com.element.fleet.ordering.enums.OrderingBOUpfitProjectPageEnum;
import com.element.fleet.ordering.enums.OrderingBOUpfitSpecPageEnum;
import com.element.fleet.ordering.enums.OrderingHomePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.NoIfElseBlockMatchedException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserAssert;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;

public class OrderingBOQueuePage {
	
	private static LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
	private static WebDriver jsWebdriver = WebDriverAccess.getDriver();
	private static WebDriverWait jsWait = new WebDriverWait(jsWebdriver, Long.parseLong(CommonPage.getTestData("WaitTime")));
	private static JavascriptExecutor jsExec = (JavascriptExecutor) jsWebdriver;
	public static String QuoteName;
	

	/**
	 * This method goes to current created order.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void gotoCreatedOrder() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("On-Order");
		OrderingBOOnOrderQueuePage.waitForOnOrderQueuePage();
		OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("logNumber");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}

	/**
	 * This method clicks on the queue label
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void gotoQueuesPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_SIDE_MENU_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_SIDE_MENU_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_SIDE_MENU_ID);
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_MENU_CLASS);
	}

	/**
	 * This method verifies the queues options
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyQueuesPageOptions() throws Exception {
		BrowserAssert.assertEquals(false, CommonPage.existsElement("queues", jsWebdriver),"Logged in user is not External User");		
	}
	
	/**
	 * This method waits for the queue page to load
	 * @lastModifiedBy
	 * @throws Exception
	 */
	public static void queuePageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ON_ORDER_OPTION_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ON_ORDER_OPTION_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ON_ORDER_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ACKNOWLEDGMENT_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BILLING_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CREDIT_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_DEALER_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_DIO_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_DRIVER_CHANGE_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_INSURANCE_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_MAINFRAME_BRIDGING_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ORDER_TRANSMISSION_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_QUOTE_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_RFQ_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_STOCK_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SWEEPER_ADMIN_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TITLE_AND_REGISTRATION_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFIT_OPTION_XPATH);
	}

	public static void queuePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_HEADING_ID), "Queues","Queues queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ON_ORDER_OPTION_XPATH), "On-Order","On-Order queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ACKNOWLEDGMENT_OPTION_XPATH),"Acknowledgment", "Acknowledgment queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BILLING_OPTION_XPATH), "Billing","Billing queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CREDIT_OPTION_XPATH), "Credit","Credit queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_DEALER_OPTION_XPATH), "Dealer","Dealer queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_DIO_OPTION_XPATH), "DIO","DIO queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_DRIVER_CHANGE_OPTION_XPATH),"Driver Change", "Driver Change queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_INSURANCE_OPTION_XPATH),"Insurance", "Insurance queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_MAINFRAME_BRIDGING_OPTION_XPATH),"Mainframe Bridging", "Mainframe Bridging queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ORDER_TRANSMISSION_OPTION_XPATH),"Order Transmission", "Mainframe Bridging queue label did not match with the expected string");
		//CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_PROJECT_OPTION_XPATH), "Project","Project queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_QUOTE_OPTION_XPATH), "Quote","Quote queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_RFQ_OPTION_XPATH), "RFQ","RFQ queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_STOCK_OPTION_XPATH), "Stock","Stock queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SWEEPER_ADMIN_OPTION_XPATH),"Sweeper Results", "Sweeper Results queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TITLE_AND_REGISTRATION_OPTION_XPATH),"Title & Reg", "Title & Reg queue label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFIT_OPTION_XPATH), "Upfit","Upfit queue label did not match with the expected string");
	}

	/**
	 * This method clicks on the provided queue on the queue page
	 * @lastModifiedBy
	 * @param queueName
	 * @throws Exception
	 */
	public static void gotoQueue(String queueName) throws Exception {
		Object queueElement = null;
		switch (queueName) {
			case "On-Order":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ON_ORDER_OPTION_XPATH;
				break;
			case "Acknowledgment":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ACKNOWLEDGMENT_OPTION_XPATH;
				break;
			case "Billing":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BILLING_OPTION_XPATH;
				break;
			case "Credit":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CREDIT_OPTION_XPATH;
				break;
			case "Dealer":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_DEALER_OPTION_XPATH;
				break;
			case "Dealer Install":
			case "DIO":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_DIO_OPTION_XPATH;
				break;
			case "Driver Change":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_DRIVER_CHANGE_OPTION_XPATH;
				break;
			case "Insurance":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_INSURANCE_OPTION_XPATH;
				break;
			case "Mainframe Bridging":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_MAINFRAME_BRIDGING_OPTION_XPATH;
				break;
			case "Order Transmission":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ORDER_TRANSMISSION_OPTION_XPATH;
				break;
			case "Project":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_PROJECT_OPTION_XPATH;
				break;
			case "Quote":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_QUOTE_OPTION_XPATH;
				break;
			case "RFQ":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_RFQ_OPTION_XPATH;
				break;
			case "Stock":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_STOCK_OPTION_XPATH;
				break;
			case "Sweeper Results":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SWEEPER_ADMIN_OPTION_XPATH;
				break;
			case "Title":
			case "Title & Reg":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TITLE_AND_REGISTRATION_OPTION_XPATH;
				break;
			case "Upfit":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFIT_OPTION_XPATH;
				break;
			case "Telematics":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TELEMATICS_XPATH;
				break;
			case "Upfit_Project":
				queueElement = OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFIT_PROJECT_XPATH;
				break;
			default: throw new OrderingErrorOccured("Invalid Queue option");
		}
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(queueElement)));
		System.out.println("Queues: " + queueName);
		BrowserAction.click(queueElement);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}

	public static void searchAndVerifyRecords(String searchType, String columnName, String searchText) throws Exception {
		if (searchType.contains("Standard")) {
			searchType = "standard";
		} else if (searchType.contains("Advanced")) {
			searchType = "advanced";
		} else {
			throw new NoIfElseBlockMatchedException();
		}
		searchOrderByColumnName(searchType, columnName, searchText);
		verifySearchedRecords(searchType, columnName, searchText);
	}

	/**
	 * This method Searches the record according to search option field on Queue pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void searchOrderByColumnName(String searchType, String columnName, String searchText)	throws Exception {
		System.out.println(columnName + ": " + searchText);
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_VALUE_XPATH.toString(), searchType, columnName)),searchText);
		clickSearchButton();
		waitUntilCompletePageLoad();
		WebDriverAction.clear(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_VALUE_XPATH.toString(), searchType, columnName)));
	}

	public static void verifySearchedRecords(String searchType, String columnName, String searchText) throws Exception {
		if (result.isEmpty()) {
			buildSearchOptionData(searchType);
		}
		List<WebElement> elementslist = WebDriverAccess.getElements(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_SEARCHED_RECORDS_RESULT_XPATH.name(),String.format(OrderingBOQueuePageEnum.ORDERING_BO_SEARCHED_RECORDS_RESULT_XPATH.toString(),result.get(columnName))));
		for (WebElement element : elementslist) {
			Assert.assertEquals(element.getText().trim(), searchText.trim(), "Searched record is not matching");
		}
	}

	public static void waitForQueuePageLoad() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_SEARCHED_ROCORDS_ROW_XPATH);
	}

	/**
	 * This method Clicks on Search Option Button on Queue pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickSearchButton() throws Exception {
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_SEARCHICON_CSS);
	}

	/**
	 * This method collects the attribute information used to get table data dynamically on Queue pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void buildSearchOptionData(String searchType) throws Exception {
		String label;
		String attribute;
		List<WebElement> elementslist = WebDriverAccess.getElements(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_LABEL_XPATH.name(), String.format(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_LABEL_XPATH.toString(), searchType)));
		for (WebElement element : elementslist) {
			label = element.getText().trim();
			if (label != null && !label.equalsIgnoreCase("")) {
				attribute = WebDriverAccess.getElementAttributeValue(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_VALUE_XPATH.toString(),searchType, label)),"name");
				result.put(label, attribute);
			}
		}
	}

	/**
	 * This method Verifies Release button is disable or enable on Queue pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifyReleaseButton(boolean isDisplayed) throws Exception {
		if (isDisplayed) {
			BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_RELEASE_BUTTON_DISABLED_XPATH);
		} else {
			BrowserVerify.verifyElementIsNotPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_RELEASE_BUTTON_DISABLED_XPATH);
		}
	}

	/**
	 * This method Clicks on Clear Filter button on Queue pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickClearFilterButton() throws Exception {
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLEAR_FILTER_BUTTON_CSS);
	}

	/**
	 * This method Verifies Search fields are cleared on Queue pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifyClearFilterFunctionality() throws Exception {
		clickClearFilterButton();
		waitUntilCompletePageLoad();
		String label;
		String value;
		List<WebElement> elementslist = WebDriverAccess.getElements(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_LABEL_XPATH.name(), String.format(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_LABEL_XPATH.toString(), "standard")));
		for (WebElement element : elementslist) {
			label = element.getText().trim();
			if (label != null && !label.equalsIgnoreCase("")) {
				WebElement ele = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SEARCH_FIELD_XPATH.name(), String.format(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SEARCH_FIELD_XPATH.toString(), label)));
				value = ele.getAttribute("value");
				assertTrue(value.equals(""), "Search Field " + label + " is not cleared ");
			}
		}
	}

	/**
	 * This method Clicks on Toggle Column dropdown button on Queue pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void clickToggleColumnButton() throws Exception {
		WebElement togglElement = BrowserAccess.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TOGGLE_COLUMN_BUTTON_XPATH);
		String isExpanded = togglElement.getAttribute("aria-expanded");
		if (isExpanded == null || "false".equalsIgnoreCase(isExpanded)) {
			BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TOGGLE_COLUMN_BUTTON_XPATH);
		}
	}

	/**
	 * This method closes on Toggle Column dropdown button on Queue pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void closeToggleColumn() throws Exception {
		WebElement togglElement = BrowserAccess.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TOGGLE_COLUMN_BUTTON_XPATH);
		String isExpanded = togglElement.getAttribute("aria-expanded");
		if ("true".equalsIgnoreCase(isExpanded)) {
			BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TOGGLE_COLUMN_BUTTON_XPATH);
		}
	}

	/**
	 * This method Verifies Unchecks(Removes) the columns from Toggle Column
	 * dropdown on Queue pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void removeColumnsFromQueueTable(String... toggleColumns) throws Exception {
		if (toggleColumns.length != 0) {
			clickToggleColumnButton();
			for (int i = 0; i < toggleColumns.length; i++) {
				WebElement element = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_QUESE_ADD_REMOVE_COMLUMN_XPATH.name(),String.format(OrderingBOQueuePageEnum.ORDERING_BO_QUESE_ADD_REMOVE_COMLUMN_XPATH.toString(),toggleColumns[i])));
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
	 * This method Verifies checks(Adds) the columns from Toggle Column dropdown on Queue pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void addColumnInQueueTable(String... toggleColumns) throws Exception {
		if (toggleColumns.length != 0) {
			clickToggleColumnButton();
			for (int i = 0; i < toggleColumns.length; i++) {
				WebElement element = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_QUESE_ADD_REMOVE_COMLUMN_XPATH.name(),String.format(OrderingBOQueuePageEnum.ORDERING_BO_QUESE_ADD_REMOVE_COMLUMN_XPATH.toString(),toggleColumns[i])));
				if (toggleColumns[i].equalsIgnoreCase(element.getText().trim())) {
					String value = element.getAttribute("class");
					if ("".equalsIgnoreCase(value))
					element.click();
				}
			}
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			closeToggleColumn();
		}
	}

	/**
	 * This method Verifies Column names of Queue table from Queue pages
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifyQueueTableColumns() throws Exception {
		clickToggleColumnButton();
		String label;
		ArrayList<String> toggleColumns = new ArrayList<>();
		List<WebElement> elementslist = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TOGGLE_ACTIVE_COLUMN_LABEL_XPATH);
		for (WebElement element : elementslist) {
			label = element.getAttribute("innerText").trim();
			toggleColumns.add(label);
		}
		ArrayList<String> tableColumns = new ArrayList<>();
		List<WebElement> tableElements = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TABLE_COLUMN_NAMES_XPATH);
		for (WebElement element : tableElements) {
			label = element.getAttribute("innerText").trim();
			if (label != null && !label.equalsIgnoreCase("")) {
				if (label.contains("\n")) {
					label = label.replace("\n", " ");
				}
				tableColumns.add(label);
			}
		}
		if (tableColumns.contains("Order ID"))
			tableColumns.remove("Order ID");
		if (tableColumns.contains("Actions"))
			tableColumns.remove("Actions");
		closeToggleColumn();
		assertTrue(toggleColumns.equals(tableColumns), "Table Coulmns are not matching with Toggle Column selection");
	}

	/**
	 * This method verifies the Export functionality of data of queue table on Queue pages
	 * @lastModifiedBy akandkonde
	 * @throws Exception
	 */
	public static void verifyExportFuntionality(String className) throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORT_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORT_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORT_BUTTON_CSS);
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORT_BUTTON_CSS);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), className);
	}

	/**
	 * This method verifies the Export functionality of data of queue table on Queue pages
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyExportCSV(String className) throws Throwable {
		OrderingSummaryPage.clickExportButton("Quote-Queue", className);
	}

	/**
	 * This method verifies the Pagination of data present in queue tables on Queue pages
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void verifyPagenation() throws Exception {
		OrderingBOQueuePage.waitForQueuePageLoad();
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
		} else {
			throw new NoIfElseBlockMatchedException();
		}
		List<WebElement> recordSize = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_RECORDSIZE_DRPDWN_XPATH);
		Assert.assertEquals(recordSize.get(0).getText().trim(), "100", "Value in record size dropdown is not 100");
		Assert.assertEquals(recordSize.get(1).getText().trim(), "500", "Value in record size dropdown is not 500");
	}

	/**
	 * This method waits for all the Ajaxs calls to complete
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void waitUntilCompletePageLoad() {
		ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) jsWebdriver).executeScript("return jQuery.active") == 0);
		boolean bool = (boolean) jsExec.executeScript("return jQuery.active==0");
		if (!bool) {
			jsWait.until(jQueryLoad);
		}
	}

	/**
	 * This method Clicks on Add\Remove dropdown button on Queue pages
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickAddRemoveButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ADDREMOVEBTN_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ADDREMOVEBTN_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ADDREMOVEBTN_XPATH);
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ADDREMOVEBTN_XPATH);
	}

	/**
	 * This method Verifies Unchecks(Removes) the columns from Add\Remove dropdown on Queue pages
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void removeColumnsFromAddRemoveDrpDwn(String... removeColumns) throws Exception {
		if (removeColumns.length != 0) {
			OrderingBOQueuePage.clickAddRemoveButton();
			boolean isChecked = (boolean) jsExec.executeScript("return document.getElementById('selectAllColumn').checked");
			if (isChecked) {
				for (int i = 0; i < removeColumns.length; i++) {
					WebElement element = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADDREMOVEOPTIONSELECT_XPATH.name(),String.format(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADDREMOVEOPTIONSELECT_XPATH.toString(),removeColumns[i])));
					String script = "return document.querySelector(\"input[value='?']\").checked".replace("?",element.getText().trim());
					boolean isSelected = (boolean) jsExec.executeScript(script);
					if (removeColumns[i].equalsIgnoreCase(element.getText().trim()) && isSelected) {
						element.click();
					}
				}
			} else {
				throw new OrderingErrorOccured("Clear all checkbox is unchecked,hence not able to remove columns");
			}
			BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLOSECROSS_XPATH);
		}
	}

	/**
	 * This method verifies checks(adds) the columns from Add\Remove dropdown onQueue pages
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void addColumnsFromAddRemoveDrpDwn(String... addColumns) throws Exception {
		if (addColumns.length != 0) {
			OrderingBOQueuePage.clickAddRemoveButton();
			for (int i = 0; i < addColumns.length; i++) {
				WebElement element = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADDREMOVEOPTIONSELECT_XPATH.name(),String.format(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_ADDREMOVEOPTIONSELECT_XPATH.toString(),addColumns[i])));
				String script = "return document.querySelector(\"input[value='?']\").checked".replace("?",element.getText().trim());
				boolean isSelected = (boolean) jsExec.executeScript(script);
				if (addColumns[i].equalsIgnoreCase(element.getText().trim()) && !isSelected) {
					element.click();
				}
			}
			BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLOSECROSS_XPATH);
		}
	}

	/**
	 * This method verifies the columns from Add\Remove dropdown are displayed in table of Queue pages
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void verifyTableColumns() throws Exception {
		OrderingBOQueuePage.clickAddRemoveButton();
		ArrayList<String> addRemoveColumns = new ArrayList<>();
		List<WebElement> elements = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ADDREMOVELIST_XPATH);
		for (WebElement element : elements) {
			if (!element.getText().trim().equalsIgnoreCase("Clear All")) {
				String script = "return document.querySelector(\"input[value='?']\").checked".replace("?",element.getText().trim());
				boolean isSelected = (boolean) jsExec.executeScript(script);
				if (isSelected) {
					addRemoveColumns.add(element.getText().trim().replace(" ", ""));
				}
			}
		}
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLOSE_XPATH);
		ArrayList<String> tableColumns = new ArrayList<>();
		List<WebElement> tableElements = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TABLECOLUMNLISTNAME_XPATH);
		for (WebElement tableElement : tableElements) {
			tableColumns.add(WebDriverAccess.getElementText(tableElement).trim().replace("\n", ""));
		}
		if (addRemoveColumns.contains("Clear All"))
			addRemoveColumns.remove("Clear All");
		Assert.assertTrue(addRemoveColumns.equals(tableColumns),"Add Removed columns dosen't match with the table columns dispalyed.");
	}

	/**
	 * This method clicks on Controls button in Queue pages.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnControls() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CONTROLSLINK_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CONTROLSLINK_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CONTROLSLINK_XPATH);
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CONTROLSLINK_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}

	/**
	 * This method waits for Controls section is loaded.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForControlsSectionIsLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ARIAMULTISELECTABLE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ARIAMULTISELECTABLE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ARIAMULTISELECTABLE_XPATH);
	}

	/**
	 * This method selects and applies filter for controls on Queue pages
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void addControls(String controlName, String controlData) throws Exception {
		OrderingBOQueuePage.clickOnControls();
		OrderingBOQueuePage.waitForControlsSectionIsLoaded();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		String isExpaned = BrowserAccess.getElementAttributeValue(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ARIAMULTISELECTABLE_XPATH, "aria-multiselectable").trim();
		if (isExpaned.equalsIgnoreCase("true")) {
			WebElement element = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_OPTIONS_XPATH.name(),String.format(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_OPTIONS_XPATH.toString(), controlName)));
			WebElement elementInput = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_OPTIONSEARCH_XPATH.name(), String.format(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_OPTIONSEARCH_XPATH.toString(), controlName)));
			if (element.isDisplayed() && element.isEnabled()) {
				element.click();
				System.out.println(controlName + ": " + controlData);
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(elementInput));
				WebDriverAction.enterFieldValue(elementInput, controlData);
			} else {
				jsExec.executeScript("arguments[0].scrollIntoView(true);", element);
				element.isDisplayed();
				element.click();
				System.out.println(elementInput + ": " + controlData);
				WebDriverAction.enterFieldValue(elementInput, controlData);
			}
			WebElement applyElement = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CONTROLS_APPLY_XPATH.name(),OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CONTROLS_APPLY_XPATH.toString()));
			jsExec.executeScript("arguments[0].click();", applyElement);
			OrderingCommonPage.checkAlertPopUp();
			OrderingCommonPage.checkGlobalSpinnerPopUp();
		}
	}

	/**
	 * This method verifies filtered data is displayed on Queue pages
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyDataOfControlsFilter(String controlName, String controlData) throws Exception {
		List<WebElement> columnDataElements = WebDriverAccess.getElements(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_SEARCHED_RECORDS_RESULT_XPATH.name(), String.format(OrderingBOQueuePageEnum.ORDERING_BO_SEARCHED_RECORDS_RESULT_XPATH.toString(), controlName)));
		for (WebElement columnDataElement : columnDataElements) {
			Assert.assertEquals(columnDataElement.getAttribute("data-val").trim(), controlData.trim());
		}
	}

	/**
	 * This method saves custom view Queue pages
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void saveCustomView(String... removeColumns) throws Exception {
		String viewName = CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("ViewName", viewName);
		OrderingBOQueuePage.removeColumnsFromAddRemoveDrpDwn(removeColumns);
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SAVEVIEW_XPATH);
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SAVEAS_XPATH);
		System.out.println("View name: " + CommonPage.getTestData("ViewName"));
		BrowserAction.enterFieldValue(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_INPUTNAME_XPATH, viewName);
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SAVEASOK_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.id(OrderingBOUpfitSpecPageEnum.ORDERING_BO_UPFIT_SPEC_PAGE_SAVE_POP_UP_ID.getValue()), 0));
	}

	/**
	 * This method verifies saved custom view is displayed in the view dropdown and select it Queue pages
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void selectAndVerifyCustomView() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_VIEWDRPDWN_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_VIEWDRPDWN_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_VIEWDRPDWN_XPATH);
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_VIEWDRPDWN_XPATH);
		String isExpanded = BrowserAccess.getElementAttributeValue(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_VIEWDRPDWN_XPATH, "aria-expanded").trim();
		if (isExpanded.equalsIgnoreCase("true")) {
			List<WebElement> viewListElements = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_VIEWLIST_XPATH);
			for (WebElement viewListElement : viewListElements) {
				if (viewListElement.getText().trim().equalsIgnoreCase(CommonPage.getTestData("ViewName"))) {
					viewListElement.click();
					waitUntilCompletePageLoad();
				}
			}
		}
		OrderingBOQueuePage.verifyTableColumns();
	}

	/**
	 * This method verifies correct search fields are present and displayed
	 * @throws Exception
	 * @lastModifiedBy Mkhairanar
	 */
	public static void verifySearchFieldsArePresent() throws Exception {
		List<String> expectedSearchFieldNames = Arrays.asList(CommonPage.getTestData("CustomColumn2").split("\\|"));
		ArrayList<String> actualSearchFieldsNames = new ArrayList<>();
		List<WebElement> searchFieldElementList = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SEARCH_FIELDS_ELEMENT_LIST_XPATH);
		for (WebElement e : searchFieldElementList) {
			BrowserWait.waitUntilElementIsDisplayed(e);
			actualSearchFieldsNames.add(e.getText().trim());
			CommonPage.assertElementHighlight(e,e.getText()+ "Element heighlighted");
		}
		expectedSearchFieldNames.sort(Comparator.naturalOrder());
		actualSearchFieldsNames.sort(Comparator.naturalOrder());
		Assert.assertEquals(actualSearchFieldsNames, expectedSearchFieldNames, "Search Fields are not matching");
	}

	/**
	 * This method verifies correct Columns are present in the table after landing
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTableColumnsOnLanding() throws Exception {
		String label;
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn3").split("\\|"));
		ArrayList<String> tableColumns = new ArrayList<>();
		List<WebElement> tableElements = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TABLE_COLUMN_NAMES_XPATH);
		for (WebElement element : tableElements) {
			label = element.getAttribute("innerText").trim();
			if (label != null && !label.equalsIgnoreCase("")) {
				if (label.contains("\n")) {
					label = label.replace("\n", " ");
				}
				tableColumns.add(label);
			}
		}
		if (tableColumns.contains("Order ID"))
			tableColumns.remove("Order ID");
		if (tableColumns.contains("Actions"))
			tableColumns.remove("Actions");
		System.out.println("Test Data Columns Name:-"+expectedColumnNames);
		System.out.println("Table Columns Name:-"+tableColumns);
		Assert.assertEquals(expectedColumnNames, tableColumns, "Table Columns are not matching with Test Data");
	}

	/**
	 * This method compares the ui column values with csV column values.	 *
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void compareCSVDataWithUIData(String... columnName) throws Exception {		
		List<String> uiColumnData = new ArrayList<>();
		for (int i = 0; i < columnName.length; i++) {
			WebElement element = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_COLUMNNAME_XPATH.name(),
							String.format(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_COLUMNNAME_XPATH.toString(),columnName[i])));
			String dataName = element.getAttribute("data-name").trim();
			int pagenateOfNum = Integer.parseInt(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEOFTXT_XPATH).replace("/", "").trim());
			uiColumnData.clear();
			for (int j = 1; j <= pagenateOfNum; j++) {
				List<WebElement> dataEles = WebDriverAccess.getElements(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_SEARCHED_RECORDS_RESULT_XPATH.name(),
						String.format(OrderingBOQueuePageEnum.ORDERING_BO_SEARCHED_RECORDS_RESULT_XPATH.toString(),dataName)));
				for (WebElement dataEle : dataEles) {
					uiColumnData.add(dataEle.getAttribute("data-val").trim());
				}
				if (j != pagenateOfNum) {
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_NEXTPAGINATEBTN_XPATH);
					OrderingCommonPage.checkGlobalSpinnerPopUp();
					waitUntilCompletePageLoad();
				}
			}
			BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_FIRSTPAGINATEBTN_XPATH);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			List<String> csvColumnData = ExcelUtil.getCSVMapData().get(columnName[i]);
			System.out.println(csvColumnData.size());
			Collections.sort(csvColumnData);
			Collections.sort(uiColumnData);
			Assert.assertTrue(uiColumnData.containsAll(csvColumnData),"Column data dosen't match with CSV " + columnName[i]);
		}
	}
	
	/**
	 * This method verify the label present on Insurance queue. 
	 * @lastModifiedBy mkhairanar
	 * @throws Exception 
	 */
	public static void verifyInsuranceQueueButtton() throws Exception {
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TITLE1_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SUBTITLE_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BUTTON_TOGGLE_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BUTTON_SEARCH_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BUTTON_CLEAR_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BUTTON_EXPORT_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SUBTITLE_XPATH), "Insurance", "Insurance should display");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BUTTON_TOGGLE_XPATH), "Toggle Columns", "Toggle Column buttton should display");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BUTTON_CLEAR_XPATH), "Clear Filters", "Clear Filter buttton should display");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BUTTON_RELEASEGRAY_XPATH), "Release", "Release button Should display");
	}
	
	/**
	 * This method verifies correct standard and advanced search fields are present and displayed
	 * @param searchOption Search Type, Advanced/Standard
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 * 
	 */
	public static void verifyStandardOrAdvancedSearchFieldsArePresent(String searchOption) throws Exception {
		List<String> expectedSearchFieldNames = null;
		List<WebElement> searchFieldElementList = null;
		switch (searchOption) {
			case "Standard":
				expectedSearchFieldNames = Arrays.asList(CommonPage.getTestData("CustomColumn2").split("\\|"));
				searchFieldElementList = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_STANDARD_SEARCH_FIELDS_XPATH);
				break;
			case "Advanced":
				expectedSearchFieldNames = Arrays.asList(CommonPage.getTestData("CustomColumn1").split("\\|"));
				searchFieldElementList = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ADVANCED_SEARCH_FIELDS_XPATH);
				break;
			default:
				throw new InvalidSwitchCaseException("Invalid section option entered");
		}
		ArrayList<String> actualSearchFieldsNames = new ArrayList<>();
		for (WebElement e : searchFieldElementList) {
			BrowserWait.waitUntilElementIsDisplayed(e);
			actualSearchFieldsNames.add(e.getText().trim());
		}
		expectedSearchFieldNames.sort(Comparator.naturalOrder());
		actualSearchFieldsNames.sort(Comparator.naturalOrder());
		Assert.assertEquals(expectedSearchFieldNames, actualSearchFieldsNames, "The Searched fields are not matching with Test Data");
	}
	
	/**
	 * This method changes the search type
	 * @param searchType Search Type to be changed to
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void changeSearchType(String searchType) throws Exception {
		BrowserAction.hoverOverElement(OrderingBOCreditQueuePageEnum.ORDERING_BO_BILLING_SEARCH_OVERLAY_XPATH);
		switch (searchType) {
		case "Standard":
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCreditQueuePageEnum.ORDERING_BO_BILLING_SEARCH_OPTION_STANDARD_XPATH);
			BrowserAction.click(OrderingBOCreditQueuePageEnum.ORDERING_BO_BILLING_SEARCH_OPTION_STANDARD_XPATH);
			break;
		case "Advanced":
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCreditQueuePageEnum.ORDERING_BO_BILLING_SEARCH_OPTION_ADVANCED_XPATH);
			BrowserAction.click(OrderingBOCreditQueuePageEnum.ORDERING_BO_BILLING_SEARCH_OPTION_ADVANCED_XPATH);
			break;
		default: throw new InvalidSwitchCaseException("Invalid section name entered");
		}
		OrderingBOQueuePage.waitUntilCompletePageLoad();
	}
	
	/**
	 * This method will click on business maintained table link in BO
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToBusinessMaintainedTable() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOQueuePageEnum.ORDERING_BO_BUSINESS_MAINTAINED_TABLE_XPATH, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_BUSINESS_MAINTAINED_TABLE_XPATH);
	}

	/**
	 * This method changes size of queue table records on Queue pages
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void changePageRecordSize(String recordChunkSize) throws Exception {
		System.out.println("Page size dropdown selected:: "+recordChunkSize);
		BrowserAction.selectDropdownOptionByText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGESIZE_DRPDWN_XPATH, recordChunkSize);
		waitUntilCompletePageLoad();
	}
	
	/**
	 * This method verifies that only on order queue is visible to capping users
	 * @throws Throwable
	 * @lastModifiedBy djawale
	 */
	public static void verifyOnlyOnOrderQueueIsVisibleToCappingUser() throws Throwable{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ON_ORDER_OPTION_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ON_ORDER_OPTION_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ON_ORDER_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ACKNOWLEDGMENT_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BILLING_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CREDIT_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_DEALER_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_DIO_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_DRIVER_CHANGE_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_INSURANCE_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_MAINFRAME_BRIDGING_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_ORDER_TRANSMISSION_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_PROJECT_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_QUOTE_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_RFQ_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_STOCK_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SWEEPER_ADMIN_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TITLE_AND_REGISTRATION_OPTION_XPATH);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFIT_OPTION_XPATH);
	}
	
	/**
	 * This method verifies capping user is able to search order and navigate to it
	 * @throws Throwable
	 * @lastModifiedBy djawale
	 */
	public static void verifyCappingUserIsAbleToSearchOrderAndNavigateToIt() throws Throwable {
		OrderingBOQueuePage.gotoQueue("On-Order");
		OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("logNumber");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOOnOrderQueuePage.waitForOrderDetailsPage();
	}

	/**
	 * This method verifies all ordering side menu options.
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void verifySideMenuOptions() throws Exception {
		List<WebElement> orderingSideMenuOptions = BrowserAction.getElements(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_ALL_OPTIONS_XPATH);
		System.out.println("ordering side menu options are:");
		for (WebElement options : orderingSideMenuOptions) {
			String myText = options.getText();
			System.out.println(myText);
		}
	}
	
	/**
	 * This method verifies all ordering side menu options.
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void verifyQueueApprovalStatusValues() throws Exception {
		List<WebElement> queueApprovalStatusValues = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_APPROVAL_STATUS_COLUMN_VALUES_XPATH);
		for (WebElement values : queueApprovalStatusValues) {
			String myText = values.getText();
				if(!(myText.contains("Saved- Manager Approvals Pending"))){
				System.out.println("Queue Approval Status is not valid");
			}
		}
		System.out.println("No other status than Saved- Manager Approvals Pending");
	}
	
	/**
	 * This method verify the label present on Customer Delivering Dealer Assignment  Rules. 
	 * @lastModifiedBy mkhairanar
	 * @throws Exception 
	 */
	public static void verifyCDDARButtton() throws Exception {
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TITLE1_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SUBTITLE_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BUTTON_TOGGLE_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BUTTON_SEARCH_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BUTTON_CLEAR_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BUTTON_EXPORT_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SUBTITLE_XPATH), "Customer Delivering Dealer Assignment Rules", "Customer Delivering Dealer Assignment Rules should display");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BUTTON_TOGGLE_XPATH), "Toggle Columns", "Toggle Column buttton should display");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_BUTTON_CLEAR_XPATH), "Clear Filters", "Clear Filter buttton should display");
	}		
	
	/**
	 * This method clicks on the upfit/project option
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void gotoUpfitProjectPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFIT_PROJECT_OPTION_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFIT_PROJECT_OPTION_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFIT_PROJECT_OPTION_XPATH);
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFIT_PROJECT_OPTION_XPATH);
		
	}
	
	/**
	 * This method validates that upfit tab is present in queue section in BO
	 * @lastModifiedBy Deepika
	 */
	public static void upfitRequestQueueLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_TITLE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_TITLE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_TITLE_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_TITLE_XPATH),"Request Queue","Not Matched");
	}
	
	/**
	 * This method will validate if newly created order from FO is shown in BO or not
	 * @lastModifiedBy DBhagat
	 * @throws Exception
	 */
	public static void validateLogNumber() throws Exception {
		BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_LOG_XPATH);
		BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_LOG_XPATH,OrderingSummaryPage.logNo);
		System.out.printf("Log Number:", OrderingSummaryPage.logNo);
		//BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_LOG_XPATH,CommonPage.getTestData("LogNumber"));
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHBUTTON_XPATH),"Search", "Search feild is not matched");
		BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHBUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ADVANCEDSEARCH_XPATH);
		
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ADVANCEDSEARCH_XPATH ));
		BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_LOG_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_NEWREQUEST_LABEL_XPATH);
	}	
	
	/**
	 * This method will validate if background color of the newly created record is as per design
	 * @lastModifiedBy DBhagat
	 * @throws Exception
	 */
	public static void validateBGColor() throws Exception {
		String bgColor = BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHRECORD_XPATH).getCssValue("background-color");
		String hexaColor = Color.fromString(bgColor).asHex();
		Assert.assertEquals(hexaColor,"#f7f9d9" ,"Background color does not match");
	
	}
	
	/**
	 * This method will validate if font of the newly created record is bold or not
	 * @lastModifiedBy DBhagat
	 * @throws Exception
	 */
	public static void validateBold() throws Exception {
		String font = BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHRECORD_XPATH).getCssValue("font-weight");
		Assert.assertTrue(font.equals("bold") || font.equals("700"), "Text is not bold");
	}
	
	/**
	 * This method will validate the background color and font of the newly created record changes after user clicks on it
	 * @lastModifiedBy DBhagat
	 * @throws Exception
	 */
	/*public static void clickOnRecord() throws Exception {
		BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHRECORD_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CANCELBUTTON_XPATH);
		BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_BREADCRUMREQUEST_XPATH);
		String bgColor = BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHRECORD_XPATH).getCssValue("background-color");
		String hexaColor = Color.fromString(bgColor).asHex();
		Assert.assertEquals(hexaColor,"#fff" ,"Background color does not match");
	}*/
	
	/**
	 * This method will click on clear filter button in queues page in BO
	 * @lastModifiedBy DBhagat
	 */
	public static void validateClearFilters() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS_XPATH),"Clear Filters", "Clear Filter is not matched");
		BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS_XPATH);
		List<WebElement> textFields = BrowserAccess.getElements(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHFIELDSTEXT_XPATH);
		for(WebElement textField : textFields) {
			{
				String textfeilds=textField.getText();
                Assert.assertEquals(textfeilds, "", "Value in the search fields is there");
            }
        }
	}
	
	/**
	 * This method will hide the filter fields in Request queue page in BO
	 * @lastModifiedBy Deepika
	 */
	public static void verifyFilterHide() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_FILTERICON_XPATH),"Filters", "Filter icon is present");
		BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_FILTERICON_XPATH);
		boolean logPresence = BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_LOGFIELD_XPATH ).isDisplayed();
		Assert.assertFalse(logPresence==false, "Basic Search fields are not hidden");		
	}
	
	/**
	 * This method will show the filter fields in Request queue page in BO
	 * @lastModifiedBy Deepika
	 */			
	public static void verifyFilterShow() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_FILTERICON_XPATH),"Filters", "Filter icon is present");
		BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_FILTERICON_XPATH);
		boolean logPresence = BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_LOGFIELD_XPATH ).isDisplayed();
		Assert.assertTrue(logPresence==true, "Basic Search fields are displayed");
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ADVANCEDSEARCH_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ADVANCEDSEARCH_XPATH);
			} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Not able to find Advance Search button");
		}
	} 
	
	/**
	 * This method will verify that all Search fields are present in Request Queue filter in BO
	 * @lastModifiedBy Deepika
	 */	
	public static void verifySearchFieldsHeadings() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ADVANCEDSEARCH_XPATH),"Advanced Search", "Search field is not matched");
		BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ADVANCEDSEARCH_XPATH);
		System.out.println("User provided column: " + CommonPage.getTestData("CustomColumn1"));
		List<String> userProvidedSearchName = Arrays.asList(CommonPage.getTestData("CustomColumn1").split("\\|"));
		List<String> uiSearchName = new ArrayList<String>();
		BrowserAction.getElements(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHFIELDS_XPATH).stream().forEach(e->uiSearchName.add(e.getText()));
		List<WebElement> tableElements = BrowserAccess.getElements(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHFIELDS_XPATH);
		 for(WebElement tableElement : tableElements) {
			 String feildvalue=tableElement.getText();
			 System.out.println(feildvalue);
			 CommonPage.assertLabelHighlight(tableElement,feildvalue, "Request queue field did not match with the expected string");
		 }
		System.out.println("UI provided column: " + uiSearchName);
        Assert.assertEquals(uiSearchName, userProvidedSearchName, "UI heading list not matched with provided heading list");
        CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHFIELDS_XPATH),"The elements does not get highlighted");
	}

	/**
	 * This method will verify that all column headings are present in Request Queue table in BO
	 * @lastModifiedBy Deepika
	 */	
	public static void validateAllColumns() throws Exception{ 
	  System.out.println("User provided column: " + CommonPage.getTestData("CustomColumn2"));
	  List<String> userProvidedSearchName = Arrays.asList(CommonPage.getTestData("CustomColumn2").split("\\|"));
	  JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
	  js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ADVANCEDSEARCH_XPATH )); 
	  List<WebElement>tableColumns = BrowserAccess.getElements(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_COLUMNS_XPATH); 
	  List<String> uiSearchName = new ArrayList<String>();
	  
	  int count=0;
	  for(WebElement tableColumn : tableColumns) { 
		{
		  String columnName=tableColumn.getText().replace("\\n"," ");
		  uiSearchName.add(columnName);
		  System.out.println(columnName);
		  count++;
		  if(count==12)
			  BrowserAction.hoverOverElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_COLUMNDATASTART_XPATH);
		  if(count==24)
			  BrowserAction.hoverOverElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_COLUMNDATAMIDDLE_XPATH);
		  if(count==33)
			  BrowserAction.hoverOverElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_COLUMNDATALAST_XPATH);
		  }
	  }
		System.out.println(uiSearchName);
	    Assert.assertEquals(uiSearchName, userProvidedSearchName, "UI heading list not matched with provided heading list");
	  }
	
	/**
	 * This method will verify filter in Request Queue table in BO
	 * @lastModifiedBy Deepika
	 */		
	public static void validateFiltersWorking() throws Exception {
		BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CORPCODETEXT_XPATH);
		BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CORPCODETEXT_XPATH,CommonPage.getTestData("CorpCode"));
		BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLIENTNUMBERTEXT_XPATH);
		BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLIENTNUMBERTEXT_XPATH,CommonPage.getTestData("ClientNumber"));
		BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ORIGINTEXT_XPATH);
		BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ORIGINTEXT_XPATH,CommonPage.getTestData("Origin"));
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHBUTTON_XPATH),"Search", "Search feild is not matched");
		BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHBUTTON_XPATH);
		String CorpCode=CommonPage.getTestData("CorpCode");
		String ClientNumber=CommonPage.getTestData("ClientNumber");
		String Origin=CommonPage.getTestData("Origin");
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ADVANCEDSEARCH_XPATH ));
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_TABLEDATACORPCODE_XPATH),CorpCode,"Not Matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_TABLEDATACLINTNUMBER_XPATH),ClientNumber,"Not Matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_TABLEDATAORIGIN_XPATH),Origin,"Not Matched");
		
	}
		/**
		 * This method will scroll down to Request Queue
		 * @lastModifiedBy 
		 */
		public static void scrollDownToRequestQueue() throws Exception {			
		    JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
			js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_TITLE_XPATH));

	}
		/**
		 * This method will Search by origin in Request Queue
		 * @lastModifiedBy 
		 */
	
		public static void searchOrigin()throws Exception {
			// TODO Auto-generated method stub
	    	BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ORIGINTEXT_XPATH);
	    	BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ORIGINTEXT_XPATH,"Factory");		
	    	BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHBUTTON_XPATH);
		}
		
		public static void searchOrigin1()throws Exception {
			// TODO Auto-generated method stub
	    	BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ORIGINTEXT_XPATH);
	    	BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ORIGINTEXT_XPATH,"Fleet spec");		
	    	BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHBUTTON_XPATH);
		}
		
		public static void searchOrigin2()throws Exception {
			// TODO Auto-generated method stub
	    	BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ORIGINTEXT_XPATH);
	    	BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ORIGINTEXT_XPATH,"Advanced");		
	    	BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHBUTTON_XPATH);
		}
		/**
		 * This method will validate the tabs present in Request Queue detail page
		 * @lastModifiedBy 
		 */
		public static void validateRequestDetailPage()throws Exception {
			// TODO Auto-generated method stub
			
			String LogNumber_Queue=BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_LOGNUMBER_COLUMN_VALUE_XPATH).getText();
	         System.out.println(LogNumber_Queue);
	          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_LOGNUMBER_COLUMN_VALUE_XPATH);               
	          BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_REQUEST_QUEUE_HISTORY_BUTTON_XPATH);
	          //Thread.sleep(3000);
		}
		
		/**
		 * This method will scroll down to Project Queue
		 * @lastModifiedBy 
		 */
		public static void scrollDownToProjectQueue() throws Exception {
			
			    JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
				js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_BUTTON_XPATH ));
		
		}
		
		/**
		 * This method will navigate to Project Screen
		 * @lastModifiedBy 
		 */
		public static void navigateToCreateProjectScreen() throws Exception {			
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_BUTTON_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_BUTTON_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_BUTTON_XPATH);
			
		    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_BUTTON_XPATH);

	}
		public static void enterDetailsOnCreateProjectPop_up() throws Exception {
			//ProjectName
		    BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_PROJECT_NAME_XPATH);
		    BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_PROJECT_NAME_XPATH);
		    BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_PROJECT_NAME_XPATH);
		
		    BrowserAction.clickandClear(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_PROJECT_NAME_XPATH); 
		    BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_PROJECT_NAME_XPATH, CommonPage.getTestData("ProjectName"));
	        //Description
		    BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_DESCRIPTION_XPATH);
		    BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_DESCRIPTION_XPATH);
		    BrowserAction.clickandClear(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_DESCRIPTION_XPATH); 
		    BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_DESCRIPTION_XPATH, CommonPage.getTestData("ProjectDescription"));
		    //TaskType
		    
		    BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_TASK_TYPE_DROPDOWN_XPATH);
		    BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_TASK_TYPE_DROPDOWN_XPATH);
		    BrowserAction.selectDropdownOptionByText(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_TASK_TYPE_DROPDOWN_XPATH, CommonPage.getTestData("TaskType"));
		    
		     
		    
		    //Client 
		    
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_CLIENT_SMART_SEARCH_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_CLIENT_SMART_SEARCH_XPATH);
		    BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_CLIENT_SMART_SEARCH_XPATH);
			BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_CLIENT_SMART_SEARCH_XPATH, CommonPage.getTestData("ClientNumber"));
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_SUGGESTED_SMART_SEARCH_CLIENT_XPATH);
			BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_SUGGESTED_SMART_SEARCH_CLIENT_XPATH);
					
		    //UnitNumber
			BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_UNIT_NUMBER_XPATH);
		    BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_UNIT_NUMBER_XPATH);
		    BrowserAction.clickandClear(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_UNIT_NUMBER_XPATH); 
		    BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_UNIT_NUMBER_XPATH, CommonPage.getTestData("UnitNumber"));
		    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_ADD_BUTTON_XPATH);
		    
	        

	    }
		
		 public static void saveClientProjectDetails() throws Exception {
		    	
			    BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_SAVE_BUTTON_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_SAVE_BUTTON_XPATH);
				BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_SAVE_BUTTON_XPATH);

			}
			    public static void searchProject() throws Exception {
			    	JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
					js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CLEAR_FILTERS_BUTTON_XPATH ));
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CLEAR_FILTERS_BUTTON_XPATH);
					//Thread.sleep(5000);
			    	BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CLEAR_FILTERS_BUTTON_XPATH);
					BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_SEARCH_PROJECT_NAME_INPUT_FIELD_XPATH,CommonPage.getTestData("ProjectName"));
					BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_SEARCH_BUTTON_XPATH);					
				}
			    
			    public static void searchByClientProjectNumber() throws Exception {
			    	BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_PROJECTQUEUE_CLIENTTEXT_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_PROJECTQUEUE_CLIENTTEXT_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_PROJECTQUEUE_CLIENTTEXT_XPATH);
					BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_PROJECTQUEUE_CLIENTTEXT_XPATH,CommonPage.getTestData("ClientNumber"));
					BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_PROJECT_ID_FILTER_XPATH,CommonPage.getTestData("ProjectId"));
			    }

			    
			    public static void validatingMandatoryFieldsOnCreateProject() throws Exception {
					
			    	BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_BUTTON_XPATH);
			    	BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREATE_PROJECT_SAVE_BUTTON_XPATH);
				
			    	System.out.println("Validating Mandatory fields error on Create Project Pop-Up");
		            BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREAT_PROJECT_PROJECT_NAME_MANDATORY_ERROR_MESSAGE_XPATH);
		            String ProjectNameError=BrowserAction.getElementText(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREAT_PROJECT_PROJECT_NAME_MANDATORY_ERROR_MESSAGE_XPATH);
		            System.out.println("Mandatory fields error : " + ProjectNameError);
		            Assert.assertEquals(ProjectNameError.contains("Field required"), true);
		            
		            String DueDateError=BrowserAction.getElementText(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREAT_PROJECT_DUE_DATE_MANDATORY_ERROR_MESSAGE_XPATH);
		            System.out.println("Mandatory fields error : " + DueDateError);
		            Assert.assertEquals(DueDateError.contains("Incorrect format"), true);
		            
		            String TaskTypeError=BrowserAction.getElementText(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_PROJECT_QUEUE_CREAT_PROJECT_TASK_TYPE_MANDATORY_ERROR_MESSAGE_XPATH);
		            System.out.println("Mandatory fields error : " + TaskTypeError);
		            Assert.assertEquals(TaskTypeError.contains("Field required"), true);	    			    
			    }

			    /**
				 * This method clicks on the vehicle configuration label
				 * @lastModifiedBy Shruti
				 * @throws Exception
				 */
				public static void selectStatusUpfitPending() throws Exception {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLIENTNUMBER_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLIENTNUMBER_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLIENTNUMBER_XPATH);
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLIENTNUMBER_XPATH);
					BrowserAction.clear(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLIENTNUMBER_XPATH);
					BrowserAction.enterFieldValue(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLIENTNUMBER_XPATH,CommonPage.getTestData("ClientNumber"));
					
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECTSTATUS_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECTSTATUS_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECTSTATUS_XPATH);
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECTSTATUS_XPATH);
					
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECTALL_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECTALL_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECTALL_XPATH);
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECTALL_XPATH);
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECTALL_XPATH);
					
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECT_UPFITPENDING_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECT_UPFITPENDING_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECT_UPFITPENDING_XPATH);
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECT_UPFITPENDING_XPATH);
					}
				

				/**
				 * This method clicks on the vehicle configuration label
				 * @lastModifiedBy Shruti
				 * @throws Exception
				 */
				public static void searchUpfitPending() throws Exception {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SEARCH_UPFITPENDING_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SEARCH_UPFITPENDING_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SEARCH_UPFITPENDING_XPATH);
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SEARCH_UPFITPENDING_XPATH);
		
                    }
				
				/**
				 * This method clicks on the vehicle configuration label
				 * @lastModifiedBy Shruti
				 * @throws Exception
				 */
				public static void selectUpfitPendingFleetSpec() throws Exception {
					JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
					js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_SCROLL_BULKACTION_XPATH));
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECTED_ROW_UPFITPENDING_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECTED_ROW_UPFITPENDING_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECTED_ROW_UPFITPENDING_XPATH);
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SELECTED_ROW_UPFITPENDING_XPATH);
		
                    }
				
				/**
				 * This method clicks on the vehicle configuration label
				 * @lastModifiedBy Shruti
				 * @throws Exception
				 */
				public static void clickUpfittingTab() throws Exception {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFITTING_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFITTING_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFITTING_XPATH);
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UPFITTING_XPATH);
		
                    }
				/**
				 * This method clicks on the vehicle configuration label
				 * @lastModifiedBy Shruti
				 * @throws Exception
				 */
				public static void validateAssociatedTab() throws Exception {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_ASSOCIATEUPFIT_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_ASSOCIATEUPFIT_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_ASSOCIATEUPFIT_XPATH);
					}
				/**
				 * This method clicks on the vehicle configuration label
				 * @lastModifiedBy Shruti
				 * @throws Exception
				 */
				public static void addAssociationClick() throws Exception {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_ADDASSOCIATION_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_ADDASSOCIATION_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_ADDASSOCIATION_XPATH);
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_ADDASSOCIATION_XPATH);
					}
				/**
				 * This method clicks on the vehicle configuration label
				 * @lastModifiedBy Shruti
				 * @throws Exception
				 */
				public static void selectAssociationClick() throws Exception {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_SELECTASSOCIATION_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_SELECTASSOCIATION_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_SELECTASSOCIATION_XPATH);
					Select listbox = new Select(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_SELECTASSOCIATION_XPATH));
					listbox.selectByIndex(1);
					}
				/**
				 * This method clicks on the vehicle configuration label
				 * @lastModifiedBy Shruti
				 * @throws Exception
				 */
				public static void validateAccordionMessage() throws Exception {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_ACCORDIONMESSAGE_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_ACCORDIONMESSAGE_XPATH);
					String Message=BrowserAction.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_ACCORDIONMESSAGE_XPATH).trim();
		            System.out.println("Accordion Message : " + Message);
		            Assert.assertEquals(Message.contains("Add Association"), true);
					}
				

				/**
				 * This method clicks on the vehicle configuration label
				 * @lastModifiedBy Shruti
				 * @throws Exception
				 */
				public static void validateNotificationMessage() throws Exception {
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_NOTIFICATION_MESSAGE_XPATH);
					String Message=BrowserAction.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_NOTIFICATION_MESSAGE_XPATH).trim();
		            System.out.println("Notification Message : " + Message);
		            Assert.assertEquals(Message.contains("Upfit Association successful"), true);
		            BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_NOTIFICATION_XPATH);
		            
			    }
				/**
				 * This method clicks on the vehicle configuration label
				 * @lastModifiedBy Shruti
				 * @throws Exception
				 */
				public static void quoteSelectedSave() throws Exception {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_CHKBOX_QUOTE_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_CHKBOX_QUOTE_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_CHKBOX_QUOTE_XPATH);
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_CHKBOX_QUOTE_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_SAVE_QUOTE_XPATH);
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_SAVE_QUOTE_XPATH);
					QuoteName=BrowserAction.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_QUOTENAME_SELECTED_XPATH);
					System.out.println(QuoteName);
					}
				/**
				 * This method clicks on the vehicle configuration label
				 * @lastModifiedBy Shruti
				 * @throws Exception
				 */
				public static void toValidateQuoteName() throws Exception {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_QUOTENAME_SELECTED_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_QUOTENAME_SELECTED_XPATH);
					String QuoteNameSaved=BrowserAction.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_QUOTENAME_SELECTED_XPATH);
					System.out.println(QuoteName);
					Assert.assertEquals(QuoteName.equals(QuoteNameSaved),true);
										
					}
				/**
				 * This method clicks on the vehicle configuration label
				 * @lastModifiedBy Shruti
				 * @throws Exception
				 */
				public static void toValidateToggleButton() throws Exception {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_REQUIREDSLIDER_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_REQUIREDSLIDER_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_REQUIREDSLIDER_XPATH);
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_REQUIREDSLIDER_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_REQUIREDSLIDER_XPATH);
					BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_REQUIREDSLIDER_XPATH);
					
					}
				/**
				 * This method will validate if background color of the newly created record is as per design
				 * @lastModifiedBy SSubhadarsini
				 * @throws Exception
				 */
				public static void validateBGColorToggleButton() throws Exception {
					String bgColor = BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_VEHICLECONFIG_REQUIREDSLIDER_XPATH).getCssValue("background-color");
					String hexaColor = Color.fromString(bgColor).asHex();
					Assert.assertEquals(hexaColor,CommonPage.getTestData("ToggleButton") ,"Background color does not match");
				
				}

				public static void validateRequestDetailPageOEMOrder() throws Exception{
					// TODO Auto-generated method stub
				//	String LogNumber_Queue=BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_LOGNUMBER_COLUMN_VALUE_XPATH).getText();
			     //    System.out.println(LogNumber_Queue);
			     //     BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_LOGNUMBER_COLUMN_VALUE_XPATH);               
			          CommonPage.getElementOrderObject().setLogNumber(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_LOGNUMBER_COLUMN_VALUE_XPATH).getText());
					 
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_LOGNUMBER_COLUMN_VALUE_XPATH);               
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_APPROVAL_QUOTE_XPATH);               

			          CommonPage.getElementOrderObject().getLogNumber();
			          
				}

				public static void validateRequestDetailPageFleetSpec() throws Exception{
					// TODO Auto-generated method stub
					CommonPage.getElementOrderObject().setClientNumber(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_LOGNUMBER_COLUMN_VALUE_XPATH).getText());
					 
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_CLIENTNUMBER_COLUMN_VALUE_XPATH);              
			          CommonPage.getElementOrderObject().getClientNumber();
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_VEHICLE_TAB_XPATH);               
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_STANDARD_EQIP_XPATH);               
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_FEES_VALUE_XPATH);               
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_LINKED_CLIENT_VALUE_XPATH);      
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_GEO_LOC_VALUE_XPATH);              
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_CANCEL_VALUE_XPATH);
					  BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_POPUP_SCREEN_XPATH);
					  
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_POPUP_SCREEN_XPATH); 
			          Thread.sleep(3000);
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_POPUP_SCREEN_XPATH); 

			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_FLEET_POPUP_2_SCREEN_XPATH); 




					
				}

				public static void validateRequestDetailPageAdvanceWorkflow() throws Exception {
					// TODO Auto-generated method stub
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_LOGNUMBER_COLUMN_VALUE_XPATH); 
			          Thread.sleep(5000);

			         CommonPage.getElementOrderObject().setLogNumber(BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_LOGNUMBER_COLUMN_VALUE_XPATH).getText());
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_LOGNUMBER_COLUMN_VALUE_XPATH);              

			          CommonPage.getElementOrderObject().getLogNumber();
			       JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
						js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUEST_ADVANCE_ORDERSUMMARY_SCREEN_XPATH));

				}
				

				public static void popupPage() throws Exception {
					// TODO Auto-generated method stub
			          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_POPUP_YES_BUTTON_VALUE_XPATH);               

				}

			public static void rowAction() throws Exception {
					// TODO Auto-generated method stub
				
					JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
					Thread.sleep(3000);
					js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_PROJECT_ID_COLUMN_XPATH));
          		    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_PROJECT_ID_COLUMN_XPATH); 
				    Thread.sleep(5000);
		            js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_LOGNUMBER_COLUMN_VALUE_XPATH));
			  
				    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_CHECKBOX_XPATH); 

				
				
				}

			public static void assignProject()throws Exception {
				// TODO Auto-generated method stub
				
			       BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_THREE_DOTS_XPATH);
			       BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_XPATH); 
				   BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_PROJECT_NAME_XPATH); 
				   Thread.sleep(5000);
			       BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_PROJECT_NAME_XPATH,"Test");		
			       BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_TASK_TYPE_XPATH); 
			       BrowserAction.selectDropdownOptionByValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_TASK_TYPE_XPATH,"CON");	
			       BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_DESCRIPTION_XPATH); 
			       BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_DESCRIPTION_XPATH,"Test");		
				   BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_DELETE_ACTION_XPATH);
			       BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_LOGNUMBER_XPATH);
				   BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_CLIENT_XPATH);
				   BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_UNIT_XPATH);
		           BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_SAVE_XPATH); 
				 //  BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_SAVE_MESSAGE_XPATH);
			       BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_SAVE_MESSAGE_XPATH);

			}

			public static void assignUser()throws Exception {
				// TODO Auto-generated method stub
				BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_THREE_DOTS_XPATH);
			    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGNUSER_XPATH);
			    BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEWASSIGNUSER_FIELD_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEWASSIGNUSER_FIELD_XPATH);
			    BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEWASSIGNUSER_FIELD_XPATH);
				BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEWASSIGNUSER_FIELD_XPATH, "SCOTT");
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEW_USER_FIELD_XPATH);
				BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEW_USER_FIELD_XPATH);
				BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEW_USER_SAVE_BUTTON_XPATH);
                Thread.sleep(5000);
				
			}    	
			
			public static void rowactionComment()throws Exception {
				// TODO Auto-generated method stub
			    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_CHECKBOX_XPATH); 
				BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_THREE_DOTS_XPATH);
			    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_COMMENT_XPATH);
			   // BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_COMMENT_TYPE_XPATH); 
			 //   BrowserAction.selectDropdownOptionByValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_COMMENT_TYPE_XPATH,"External");	
			 //   BrowserAction.selectDropdownOptionByValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_COMMENT_SOURCE_XPATH,"Order");	
			    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_XPATH); 
			    Thread.sleep(3000);
				BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_POPUP_XPATH);
		/*	    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_TYPE_XPATH); 
			    BrowserAction.selectDropdownOptionByValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_TYPE_XPATH,"External");
			    BrowserAction.selectMultiple(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_DESTINATION_XPATH,"Order Summary");
		*/	    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_SECTION_XPATH);
		        BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_SECTION_XPATH,"test test test");
			    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ADD_COMMENT_SAVE_XPATH); 


			   }

			public static void rowactionSendBack()throws Exception  {
				// TODO Auto-generated method stub
			/*	BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_CLEARFILTERS_XPATH);
				BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ORIGINTEXT_XPATH);
		    	BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_ORIGINTEXT_XPATH,"Factory");		
		    	BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_QUEUES_REQUESTQUEUE_SEARCHBUTTON_XPATH);
				BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_CHECKBOX_XPATH); 
			*/	BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_THREE_DOTS_XPATH);
			    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ROWACTION_SENDBACK_XPATH);
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ROWACTION_SENDBACK_POPUP_XPATH);
				BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_SENDBACK_COMMENT_SECTION_XPATH); 
	            BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_SENDBACK_COMMENT_SECTION_XPATH,"test test test");
				BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_SENDBACK_SEND_BUTTON_SECTION_XPATH); 
                Thread.sleep(5000);
			}
			

			public static void standardView() throws Exception{
				// TODO Auto-generated method stub
				  BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_DEFAULT_VIEW_BUTTON_XPATH); 
		          BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_STANDARD_VIEW_BUTTON_XPATH); 
	
				
			}

			public static void rowactionChangeDueDate() throws Exception{
				// TODO Auto-generated method stub
				BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_THREE_DOTS_XPATH);
			    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ROWACTION_CHANGE_DUE_DATE_XPATH);
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ROWACTION_CHANGE_DUE_DATE_POPUP_XPATH);
			    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_CHANGE_DUE_DATE_CALENDER_BUTTON_XPATH);
			    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_CHANGE_DUE_DATE_SAVE_BUTTON_XPATH);

			}

			public static void bulkAction()throws Exception {
				// TODO Auto-generated method stub
			    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_CHECKBOX_XPATH); 
			    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_CHECKBOX1_XPATH); 

			}

			public static void bulkActionRelease()throws Exception {
				// TODO Auto-generated method stub
				BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_BULKACTION_RELEASE_XPATH); 
			}

			public static void bulkActionAssignUser()throws Exception {
				// TODO Auto-generated method stub
				    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_BULKACTION_ASSIGNUSER_XPATH);
				    BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEWASSIGNUSER_FIELD_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEWASSIGNUSER_FIELD_XPATH);
				    BrowserVerify.verifyElementEnabled(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEWASSIGNUSER_FIELD_XPATH);
					BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEWASSIGNUSER_FIELD_XPATH, "SCOTT");
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEW_USER_FIELD_XPATH);
					BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEW_USER_FIELD_XPATH);
					BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_NEW_USER_SAVE_BUTTON_XPATH);
			}

			public static void bulkActionSendBack()throws Exception {
				// TODO Auto-generated method stub
			    	 BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_BULKACTION_SENDBACK_XPATH);
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ROWACTION_SENDBACK_POPUP_XPATH);
					BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_SENDBACK_COMMENT_SECTION_XPATH); 
		            BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_SENDBACK_COMMENT_SECTION_XPATH,"test test test");
					BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_SENDBACK_SEND_BUTTON_SECTION_XPATH); 
			}

			public static void bulkActionAssignProject()throws Exception {
				// TODO Auto-generated method stub
			    	BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_BULKACTION_ASSIGN_PROJECT_XPATH); 
				   BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_PROJECT_NAME_XPATH); 
				   Thread.sleep(5000);
			       BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_PROJECT_NAME_XPATH,"Test");		
			       BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_TASK_TYPE_XPATH); 
			       BrowserAction.selectDropdownOptionByValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_TASK_TYPE_XPATH,"CON");	
			       BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_DESCRIPTION_XPATH); 
			       BrowserAction.enterFieldValue(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_DESCRIPTION_XPATH,"Test");		
				   BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_DELETE_ACTION_XPATH);
			       BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_LOGNUMBER_XPATH);
				   BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_CLIENT_XPATH);
				   BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_UNIT_XPATH);
		           BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_SAVE_XPATH); 
				 //  BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_SAVE_MESSAGE_XPATH);
			       BrowserVerify.verifyElementIsPresent(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ASSIGN_PROJECT_SAVE_MESSAGE_XPATH);

			}

			public static void bulkActionChangeDueDate()throws Exception {
				// TODO Auto-generated method stub
				BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_BULKACTION_CHANGE_DUE_DATE_XPATH);
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_ROWACTION_CHANGE_DUE_DATE_POPUP_XPATH);
			    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_CHANGE_DUE_DATE_CALENDER_BUTTON_XPATH);
			    BrowserAction.click(OrderingBOQueuesCommonXpathEnum.ORDERING_BO_UPFIT_REQUEST_QUEUE_CHANGE_DUE_DATE_SAVE_BUTTON_XPATH);
			}    	
			
			

				


	/**
	 * This method will verify multiple data set searched record i.e. Client:9990,1791
	 * @param searchType
	 * @param columnName
	 * @param searchText : Enter data with comma separated ex. "9990,1791"
	 * @lastModifiedBy akandkonde
	 * @throws Exception 
	 */
	public static void verifySearchedRecordsForMultipleDataSet(String searchType, String columnName, String searchText) throws Exception {
		if (result.isEmpty()) {
			buildSearchOptionData(searchType);
		}
		List<String> columnData = Arrays.asList(searchText.split("\\,"));
		List<WebElement> elementslist = WebDriverAccess.getElements(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_SEARCHED_RECORDS_RESULT_XPATH.name(),String.format(OrderingBOQueuePageEnum.ORDERING_BO_SEARCHED_RECORDS_RESULT_XPATH.toString(),result.get(columnName))));
		for (WebElement element : elementslist) {
			if(!(columnData.contains(element.getText().trim())))
				throw new OrderingErrorOccured("Searched Result is not matching");
		}
	}
	
	/**
	 * This method will search and verify multiple data set searched record i.e. Client:9990,1791
	 * @param searchType
	 * @param columnName
	 * @param searchText : Enter data with comma separated ex. "9990,1791"
	 * @lastModifiedBy akandkonde
	 * @throws Exception 
	 */
	public static void searchWithMultipleDataSetAndVerifyRecord(String searchType, String columnName, String searchText) throws Exception {
		if (searchType.contains("Standard")) {
			searchType = "standard";
		} else if (searchType.contains("Advanced")) {
			searchType = "advanced";
		} else {
			throw new NoIfElseBlockMatchedException();
		}
		searchOrderByColumnName(searchType, columnName, searchText);
		verifySearchedRecordsForMultipleDataSet(searchType, columnName, searchText);
	}
	
	/**
	 * This method will return true if data is in ascending order otherwise return false
	 * @param a
	 * @lastModifiedBy akandkonde
	 * @throws Exception 
	 */
	public static boolean isSorted(ArrayList<Integer> a) {
        for (int i = 0; i < a.size() - 1; i++) {
            if (a.get(i) > a.get(i+1)) {
                return false;
            }
        }
        return true;
    }
	
	/**
	 * This method will verify days aging column default ascending order
	 * @lastModifiedBy akandkonde
	 * @throws Exception 
	 */
	public static void verifyDaysAgingDataDefaultOrder() throws Exception {
		List<WebElement> columnData = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_DAYS_AGING_COLUMN_DATA_LIST_XPATH);
		ArrayList<Integer> daysAginglist = new ArrayList<Integer>();
		for(WebElement ele : columnData)
			daysAginglist.add(Integer.parseInt(ele.getText().trim()));
		if(!isSorted(daysAginglist))
			throw new OrderingErrorOccured("Days Aging is not in asceding order after search");
	}
	
	/**
	 * This method will verify the Days Aging label from toggle column section. 
	 * @lastModifiedBy akandkonde
	 * @throws Exception 
	 */
	public static void verifyDaysAgingLabelInToggleColumn() throws Exception {
		clickToggleColumnButton();
		Assert.assertEquals(BrowserAction.getElement(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_AGING_DAYS_LABEL_IN_TOGGLE_COLUMN_XPATH).getText().trim(), "Days Aging", "Days Aging label is not matching from toggle column section.");
		clickToggleColumnButton();
	}
	
	/** This method will return most recent file path from download folder
	 * @param fileName
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static String getMostRecentFile(String fileName) throws Exception {
		Predicate<File> orderFilter = f -> f.getName().startsWith(fileName);
		Path parentFolder = Paths.get(System.getProperty("user.home") + "\\Downloads\\");	
		Optional<File> mostRecentFileOrFolder =	
				Arrays.stream(parentFolder.toFile().listFiles())	
						.filter(f -> f.getName().endsWith(".csv"))	
						.filter(orderFilter)	
						.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));
		return mostRecentFileOrFolder.get().toString();
	}
	
	/** This method will return csv headers
	 * @param relativeFilePath
	 * @throws IOException
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
	
	/** This method will verify expected headers orders from exported csv
	 * @param fileName
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static void verifyCSVHeadersOrder(String fileName) throws Exception {
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORT_BUTTON_CSS);
		OrderingCommonPage.checkAlertPopUp();
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn3").split("\\|"));
		String CSVColumn = getCSVHeaders(getMostRecentFile(fileName));
		List<String> CSVColumnName = Arrays.asList(CSVColumn.split("\\,"));
		System.out.println("CSV Headers:-"+CSVColumnName);
		System.out.println("Expected Column Headers:-"+expectedColumnNames);
		for(int i=0; i<expectedColumnNames.size(); i++)
			Assert.assertEquals(CSVColumnName.get(i).trim(), expectedColumnNames.get(i).trim(),"Columns orders is not matched");
	}
	
	/** This method will return column name count from csv headers
	 * @param columnName
	 * @throws Exception 
	 * @lastModifiedBy akandkonde
	 */
	public static int getColumnCountFromCSV(String columnName) throws Exception {
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn3").split("\\|"));
		int count = 0;
		for(String data : expectedColumnNames) {
			if(data.equals(columnName))
				break;
			count++;
		}
		return count;
	}
	
	/**
	 * This method will verify client number and unit number appended with ' if client number and unit number start with zeros
	 * @param columnName
	 * @param fileName
	 * @lastModifiedBy akandkonde
	 * @throws Exception 
	 */
	public static void verifyClientAndUnitNumberFromCSVIfStartWithZero(String columnName, String fileName) throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		List<String> list = ExcelUtil.getCSVRows(getMostRecentFile(fileName));
		ArrayList<String> data = new ArrayList<String>();
		int count = getColumnCountFromCSV(columnName);
		switch(columnName) {
			case "Client":
				CommonPage.enterTextToInputField(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLIENT_SEARCH_XPATH, CommonPage.getTestData("CustomColumn1"));
				clickSearchButton();
				waitUntilCompletePageLoad();
				List<WebElement> clientList = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLIENT_COLUMN_DATA_LIST_XPATH);
				for(String li : list) {
					List<String> CSVColumnName = Arrays.asList(li.split("\\,"));
					data.add(CSVColumnName.get(count));
				}
				for(WebElement ele: clientList) {
					if(ele.getText().startsWith("0") || ele.getText().startsWith("00"))
						if(!(data.contains("'"+ele.getText().trim())))
							throw new OrderingErrorOccured("Client or Unit number not appended with ' which are started with zeroes");
				}
				break;
			case "Unit":
				CommonPage.enterTextToInputField(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UNIT_SEARCH_XPATH, CommonPage.getTestData("CustomColumn1"));
				clickSearchButton();
				waitUntilCompletePageLoad();
				List<WebElement> unitList = BrowserAction.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_UNIT_COLUMN_DATA_LIST_XPATH);
				for(String li : list) {
					List<String> CSVColumnName = Arrays.asList(li.split("\\,"));
					data.add(CSVColumnName.get(count));
				}
				for(WebElement ele: unitList) {
					if(ele.getText().startsWith("0") || ele.getText().startsWith("00"))
						if(!(data.contains("'"+ele.getText().trim())))
							throw new OrderingErrorOccured("Client or Unit number not appended with ' which are started with zeroes");
				}
				break;
			default: throw new OrderingErrorOccured("Invalid choice");
		}
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_CLEAR_FILTER_BUTTON_CSS);
		OrderingCommonPage.checkAlertPopUp();
	}
	
}

