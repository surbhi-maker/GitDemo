package com.element.fleet.ordering.page;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOMainframeBridgingQueuePageEnum;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAction;
import com.ge.capital.rainbow.webdriver.WebDriverVerify;
import com.ge.capital.rainbow.webdriver.WebDriverWaits;

public class OrderingBOMainframeBridgingQueuePage {
	
	/**
	 * This method will verify log number prsent or not
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyLogNumberPresent() throws Exception {
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOMainframeBridgingQueuePageEnum.ORDERING_MAINFRAME_BRIDGING_LOG_NUMBER_LIST_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOMainframeBridgingQueuePageEnum.ORDERING_MAINFRAME_BRIDGING_LOG_NUMBER_LIST_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOMainframeBridgingQueuePageEnum.ORDERING_MAINFRAME_BRIDGING_LOG_NUMBER_LIST_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOMainframeBridgingQueuePageEnum.ORDERING_MAINFRAME_BRIDGING_LOG_NUMBER_LIST_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOMainframeBridgingQueuePageEnum.ORDERING_MAINFRAME_BRIDGING_LOG_NUMBER_LIST_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOMainframeBridgingQueuePageEnum.ORDERING_MAINFRAME_BRIDGING_LOG_NUMBER_LIST_XPATH);
		List<WebElement> logNumbers = BrowserAction.getElements(OrderingBOMainframeBridgingQueuePageEnum.ORDERING_MAINFRAME_BRIDGING_LOG_NUMBER_LIST_XPATH);
		for(WebElement ele: logNumbers) {
			System.out.println("Log Number: "+ele.getText());
			if(!(ele.getText().trim().equals(CommonPage.getElementOrderObject().getLogNumber())))
				throw new OrderingErrorOccured("Order is not present in mainframe bridging queue");
			else
				System.out.println("Log number present");
		}
	}
	
	/**
	 * This method will search order as per passed parameter
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void searchBy(String searchBy, String searchText) throws Exception {
		String searchField = OrderingBOMainframeBridgingQueuePageEnum.ORDERING_MAINFRAME_BRIDGING_SEARCH_FIELD_XPATH.getValue();
		searchField = searchField.replace("$logNumber#", searchBy);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(searchField));
		WebDriverVerify.verifyElementIsPresent(By.xpath(searchField));
		WebDriverVerify.verifyElementEnabled(By.xpath(searchField));
		System.out.println("Searching the order using: "+searchBy+" with value:- "+searchText);
		WebDriverAction.enterFieldValue(By.xpath(searchField), searchText);
		BrowserAction.click(OrderingBOMainframeBridgingQueuePageEnum.ORDERING_MAINFRAME_BRIDGING_SEARCH_BUTTON_XPATH);
	}
	
	/**
	 * This method will go to mainframe bridging queue and search order as per passed parameter
	 * @throws Throwable 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToMainFrameBridgingQueueAndSearch() throws Throwable {
		OrderingBOOrdMaintPage.goBackToQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Mainframe Bridging");
		System.out.println("Lognumber: "+CommonPage.getElementOrderObject().getLogNumber());
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOMainframeBridgingQueuePageEnum.ORDERING_MAINFRAME_BRIDGING_QUEUE_XPATH), "Queues", "Queue label is not found");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOMainframeBridgingQueuePageEnum.ORDERING_MAINFRAME_BRIDGING_TITLE_XPATH), "- Mainframe Bridging", "Mainframe bridging queue label is not found");
		OrderingBOMainframeBridgingQueuePage.searchBy("Log Number", CommonPage.getElementOrderObject().getLogNumber());
	}
}