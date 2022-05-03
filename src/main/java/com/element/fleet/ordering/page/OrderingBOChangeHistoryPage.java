package com.element.fleet.ordering.page;

import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOChangeHistoryPageEnum;
import com.element.fleet.ordering.enums.OrderingBOQueuePageEnum;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;

public class OrderingBOChangeHistoryPage {
	
	public static String businessKeyCnst="Business Key";
	public static String userIDCnst="User ID";
	public static String startDateCnst="Start Date";
	public static String endDateCnst="End Date";
	public static String detailHistoryLinkTextCnst="Detail History";
	public static int count =100;
	
	/**
	 * This method verifies change history page is loaded 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyChangeHistoryPageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_PAGE_TITLE_XPATH,
				OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_EXPORT_BUTTON_XPATH,
				OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SCROLL_BODY_XPATH,
				OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_TABLE_ROW_XPATH,
				OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_BUTTON_XPATH,
				OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_CLEAR_FILTER_XPATH
				);
	}

	/**
	 * This method verifies the title of the history page
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyChangeHistoryPageTitle() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_PAGE_TITLE_XPATH), "History", "History Page Title is not matching");
	}
	
	/**
	 * This method verifies that the insert date is in descending order
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void checkInsertDateAreInDescendingOrder() throws Exception {
		SimpleDateFormat formattr= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		List<String> dateInString = BrowserAction.getElements(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_TIMESTAMP_XPATH)
				.stream().map( e -> {return e.getText().trim();} ).collect(Collectors.toCollection(ArrayList::new));
		List<Date> dateObjectsUI= new ArrayList<>();
		List<Date> dateObjectsSorted= new ArrayList<>();
		Date date;
		for(String s: dateInString ) {
			date=formattr.parse(s);
			dateObjectsUI.add(date);
		}
		dateObjectsSorted = dateObjectsUI.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		Assert.assertEquals(dateObjectsUI, dateObjectsSorted, "values are not in sorted order");
	}
	
	/**
	 * This method searches for the records as per the given search parameters 
	 * 
	 * -The parameter can be passed as null if a user don't want include it as one of the search paramter 
	 * -startDate and endDate is dependent, both has to be entered in case user is searching by date
	 * @param businessObj Business Object type: Order/Fleet Spec/ Price and Config
	 * @param businessKey Unique identification value, orderId for order, Fleetspec ID for Fleetspec and Price and Config ID for Price and Config
	 * @param userID ID of the user who made the changes 
	 * @param startDate Starting date range
	 * @param endDate ending date range
	 * @param change type of change
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchByParameters(String businessObj, String businessKey, String userID, String startDate, String endDate, String change) throws Exception {
		if(businessObj!=null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_BUSINESS_OBJECT_DROPDOWN_TOGGLE_XPATH);
			BrowserAction.click(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_BUSINESS_OBJECT_DROPDOWN_TOGGLE_XPATH);
			WebElement businessObjEle=WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_DROPDOWN_TOGGLE_ITEMS_XPATH.name(),String.format(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_DROPDOWN_TOGGLE_ITEMS_XPATH.toString(),businessObj)));
			BrowserWait.waitUntilElementIsDisplayed(businessObjEle);
			businessObjEle.click();                //BrowserAction.click was giving null pointer
		}
		if(businessKey!=null) {
			WebElement businessKeyEle=WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.toString(),businessKeyCnst)));
			BrowserWait.waitUntilElementIsDisplayed(businessKeyEle);
			WebDriverAction.enterFieldValue(businessKeyEle,businessKey);
		}
		if(userID!=null) {
			WebElement userIDEle=WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.toString(),userIDCnst)));
			BrowserWait.waitUntilElementIsDisplayed(userIDEle);
			WebDriverAction.enterFieldValue(userIDEle,userID);
		}
		if(startDate!=null) {
			WebElement userIDEle=WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.toString(),startDateCnst)));
			BrowserWait.waitUntilElementIsDisplayed(userIDEle);
			WebDriverAction.enterFieldValue(userIDEle,startDate);
		}
		if(endDate!=null) {
			WebElement userIDEle=WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.toString(),endDateCnst)));
			BrowserWait.waitUntilElementIsDisplayed(userIDEle);
			WebDriverAction.enterFieldValue(userIDEle,endDate);
		}
		if(change!=null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_CHANGE_DROPDOWN_TOGGLE_XPATH);
			BrowserAction.click(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_CHANGE_DROPDOWN_TOGGLE_XPATH);
			WebElement businessObjEle=WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_DROPDOWN_TOGGLE_ITEMS_XPATH.name(),String.format(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_DROPDOWN_TOGGLE_ITEMS_XPATH.toString(),change)));
			BrowserWait.waitUntilElementIsDisplayed(businessObjEle);
			businessObjEle.click(); 
			BrowserAction.click(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_CHANGE_DROPDOWN_TOGGLE_XPATH);
		}
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_BUTTON_XPATH);
		BrowserAction.click(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_BUTTON_XPATH);
		OrderingBOQueuePage.waitUntilCompletePageLoad();
	}
	
	/**
	 * This method verifies search result
	 * 
	 * -The parameter can be passed as null if a user don't include it as one of the search paramter 
	 * -startDate and endDate is dependent, both has to be entered in case user is searching by date
	 * @param businessObj Business Object type: Order/Fleet Spec/ Price and Config
	 * @param businessKey Unique identification value, orderId for order, Fleetspec ID for Fleetspec and Price and Config ID for Price and Config
	 * @param userID ID of the user who made the changes 
	 * @param startDate Starting date range
	 * @param endDate ending date range
	 * @param change type of change
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifySearchResults(String businessObj, String businessKey, String userID, String startDate, String endDate, String change) throws Exception {
		if(businessObj!=null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_BUSINESS_OBJECT_COLUMN_LIST_XPATH);
			List<WebElement> elementslist = BrowserAction.getElements(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_BUSINESS_OBJECT_COLUMN_LIST_XPATH);
			for (WebElement element : elementslist) {
				Assert.assertEquals(element.getText().trim(), businessObj.trim(), "Searched record is not matching");
			}      
		}
		if(businessKey!=null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_BUSINESS_KEY_COLUMN_LIST_XPATH);
			List<WebElement> elementslist = BrowserAction.getElements(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_BUSINESS_KEY_COLUMN_LIST_XPATH);
			for (WebElement element : elementslist) {
				Assert.assertEquals(element.getText().trim(), businessKey.trim(), "Searched record is not matching");
			}      
		}
		if(userID!=null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_USERID_COLUMN_LIST_XPATH);
			List<WebElement> elementslist = BrowserAction.getElements(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_USERID_COLUMN_LIST_XPATH);
			for (WebElement element : elementslist) {
				Assert.assertEquals(element.getText().trim(), userID.trim(), "Searched record is not matching");
			} 
		}
		if(startDate!=null && endDate!=null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_TIMESTAMP_COLUMN_LIST_XPATH);
			List<WebElement> elementslist = BrowserAction.getElements(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_TIMESTAMP_COLUMN_LIST_XPATH);
			for (WebElement element : elementslist) {
				Assert.assertTrue((element.getText().trim().contains(startDate)||element.getText().trim().contains(endDate)), "Searched record is not matching");
			} 
		}
		if(change!=null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_CHANGE_COLUMN_LIST_XPATH);
			List<WebElement> elementslist = BrowserAction.getElements(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_CHANGE_COLUMN_LIST_XPATH);
			for (WebElement element : elementslist) {
				Assert.assertEquals(element.getText().trim(), change.trim(), "Searched record is not matching");
			} 
		}
		
	}
	/**
	 * This method verifies clear filter functionality 
	 * 
	 * -The parameter can be passed as null if a user didn't include it as one of the search parameter 
	 * -startDate and endDate is dependent, both has to be entered in case user is searching by date
	 * @param businessObj Business Object type: Order/Fleet Spec/ Price and Config
	 * @param businessKey Unique identification value, orderId for order, Fleetspec ID for Fleetspec and Price and Config ID for Price and Config
	 * @param userID ID of the user who made the changes 
	 * @param startDate Starting date range
	 * @param endDate ending date range
	 * @param change type of change
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyClearFilterFunctionality(String businessObj, String businessKey, String userID, String startDate, String endDate, String change) throws Exception {
		String value=null;
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_CLEAR_FILTER_XPATH);
		BrowserAction.click(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_CLEAR_FILTER_XPATH);
		OrderingBOQueuePage.waitUntilCompletePageLoad();
		if(businessObj!=null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_BUSINESS_OBJECT_DROPDOWN_TOGGLE_XPATH);
			BrowserAction.click(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_BUSINESS_OBJECT_DROPDOWN_TOGGLE_XPATH);
			WebElement businessObjEle=WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_DROPDOWN_TOGGLE_ITEMS_XPATH.name(),String.format(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_DROPDOWN_TOGGLE_ITEMS_XPATH.toString(),businessObj)));
			BrowserWait.waitUntilElementIsDisplayed(businessObjEle);
			Assert.assertTrue(!businessObjEle.isSelected(),"Bussiness Object is not Deselected");                //BrowserAction.click was giving null pointer
		}
		if(businessKey!=null) {
			WebElement businessKeyEle=WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.toString(),businessKeyCnst)));
			BrowserWait.waitUntilElementIsDisplayed(businessKeyEle);
			value = businessKeyEle.getAttribute("value");
			assertTrue(value.equals(""), "Search Field is not cleared ");
		}
		if(userID!=null) {
			WebElement userIDEle=WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.toString(),userIDCnst)));
			BrowserWait.waitUntilElementIsDisplayed(userIDEle);
			value = userIDEle.getAttribute("value");
			assertTrue(value.equals(""), "Search Field is not cleared ");
		}
		if(startDate!=null) {
			WebElement startDateEle=WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.toString(),startDateCnst)));
			BrowserWait.waitUntilElementIsDisplayed(startDateEle);
			value = startDateEle.getAttribute("value");
			assertTrue(value.equals(""), "Search Field is not cleared ");
		}
		if(endDate!=null) {
			WebElement endDateEle=WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_SEARCH_FIELDS_VALUE_XPATH.toString(),endDateCnst)));
			BrowserWait.waitUntilElementIsDisplayed(endDateEle);
			value = endDateEle.getAttribute("value");
			assertTrue(value.equals(""), "Search Field is not cleared ");
		}
		if(change!=null) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_CHANGE_DROPDOWN_TOGGLE_XPATH);
			BrowserAction.click(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_CHANGE_DROPDOWN_TOGGLE_XPATH);
			WebElement businessObjEle=WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_DROPDOWN_TOGGLE_ITEMS_XPATH.name(),String.format(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_DROPDOWN_TOGGLE_ITEMS_XPATH.toString(),change)));
			BrowserWait.waitUntilElementIsDisplayed(businessObjEle);
			Assert.assertTrue(!businessObjEle.isSelected(),"Change Option is not deselected");   	
		}
	}
	
	/**
	 * This method verifies the pagination functionality 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyPagenation() throws Exception {
		int pagenateOfNum = Integer.parseInt(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEOFTXT_XPATH).replace("/", "").trim());
		int pagenatePageNum = Integer.parseInt(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim());
		if (pagenateOfNum == pagenatePageNum) {
			Assert.assertEquals(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim(),BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEOFTXT_XPATH).replace("/", "").trim(),"More pages are available");
		} else if (pagenateOfNum == 0) {
			System.out.println("No data avialable to display");
		} else if (pagenateOfNum > pagenatePageNum) {
			BrowserAction.click(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_LAST_PAGINATE_BUTTON_XPATH);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			OrderingCommonPage.checkAlertPopUp();
			Assert.assertEquals(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEOFTXT_XPATH).replace("/", "").trim(),BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim(),"Pagenation to last  page failed");
			BrowserAction.click(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_FIRST_PAGINATE_BUTTON_XPATH);
			OrderingBOQueuePage.waitUntilCompletePageLoad();
			for (int i = 1; i < 2;) {
				++i;
				BrowserAction.click(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_NEXT_PAGINATE_BUTTON_XPATH);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.checkAlertPopUp();
				Assert.assertEquals(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim(),Integer.toString(i), "Pagenation to next page failed");
			}
			for (int i = 2; i > 1;) {
				--i;
				BrowserAction.click(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_PRE_PAGINATE_BUTTON_XPATH);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.checkAlertPopUp();
				Assert.assertEquals(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim(),Integer.toString(i), "Pagenation to next page failed");
			}
		}
		List<WebElement> recordSize = BrowserAccess.getElements(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_PAGE_ITEMS_OPTIONS_LIST_XPATH);
		Assert.assertEquals(recordSize.get(0).getText().trim(), "10", "Value in record size dropdown is not 10");
		Assert.assertEquals(recordSize.get(1).getText().trim(), "25", "Value in record size dropdown is not 25");
		Assert.assertEquals(recordSize.get(2).getText().trim(), "50", "Value in record size dropdown is not 50");
		Assert.assertEquals(recordSize.get(3).getText().trim(), "100", "Value in record size dropdown is not 100");
	}
	
	/**
	 * This method verifies correct Columns are present in the table after landing
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTableColumnsOnLanding() throws Exception {
		String label;
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn1").split("\\|"));
		ArrayList<String> tableColumns = new ArrayList<String>();
		List<WebElement> tableElements = BrowserAccess.getElements(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_PAGE_COLUMN_HEADING_LIST_XPATH);
		for (WebElement element : tableElements) {
			label = element.getAttribute("innerText").trim();
			if (label != null && !label.equalsIgnoreCase("")) {
				if (label.contains("\n")) {
					label = label.replace("\n", " ");
				}
				tableColumns.add(label);
			}
		}
		assertTrue(expectedColumnNames.equals(tableColumns), "Table Columns are not matching with Test Data");
	}
	/**
	 * This method verifies that detail history link is present for all records in the table
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyLinkPresentInHistoryColumn() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_HIST_COLUMN_LIST_XPATH);
		List<WebElement> elementslist = BrowserAction.getElements(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_HIST_COLUMN_LIST_XPATH);
			for (WebElement element : elementslist) {
				Assert.assertEquals(element.getText().trim(), detailHistoryLinkTextCnst, "link text is not matching");
			} 
	}


	/**
	 * This method searches, verifies the search results and then verifies the search functionality 
	 * 
	 * @param businessObj Business Object type: Order/Fleet Spec/ Price and Config
	 * @param businessKey Unique identification value, orderId for order, Fleetspec ID for Fleetspec and Price and Config ID for Price and Config
	 * @param userID ID of the user who made the changes 
	 * @param startDate Starting date range
	 * @param endDate ending date range
	 * @param change type of change
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifySearchandClearFunctionality(String businessObj, String businessKey, String userID, String startDate, String endDate, String change) throws Exception {
	 		OrderingBOChangeHistoryPage.searchByParameters(businessObj, businessKey, userID, startDate, endDate, change);
	 		OrderingBOChangeHistoryPage.verifySearchResults(businessObj, businessKey, userID, startDate, endDate, change);
	 		OrderingBOChangeHistoryPage.verifyClearFilterFunctionality(businessObj, businessKey, userID, startDate, endDate, change);
	}
	
	/**
	 * This method return todays date in first index and yesterday's date in second index
	 * @return List of dates(Today's date in first index and Tomorrow's date in second index)
	 * @lastModifiedBy vikumar
	 */
	public static List<String> getTodaysAndYesterdaysDate() {
		List<String> list=new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		   DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		   list.add(dateFormat.format(cal.getTime()));
		   cal.add(Calendar.DATE, -1);
		   list.add(dateFormat.format(cal.getTime()));
		return list;				
	}
	
}
