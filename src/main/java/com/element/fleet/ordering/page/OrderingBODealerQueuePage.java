package com.element.fleet.ordering.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBODealerQueuePageEnum;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.google.common.collect.Ordering;

public class OrderingBODealerQueuePage {
	
	/**
	 * This method enters value in the manual dealer text box.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterValueInManualDealerSearchBoxQueuePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_Q_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_Q_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_Q_XPATH);
		BrowserAction.clickandClear(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_Q_XPATH);
		if(Objects.nonNull(CommonPage.getTestData("ManualDealerAssignment"))) {
			System.out.println("Manual Dealer: "+CommonPage.getTestData("ManualDealerAssignment"));
			BrowserAction.enterFieldValue(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_Q_XPATH, CommonPage.getTestData("ManualDealerAssignment"));
			BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_FIRST_SEARCHED_DEALER_CSS);
			BrowserVerify.verifyElementIsPresent(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_FIRST_SEARCHED_DEALER_CSS);
			BrowserVerify.verifyElementEnabled(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_FIRST_SEARCHED_DEALER_CSS);
			BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_FIRST_SEARCHED_DEALER_CSS);
		} else {
			throw new OrderingErrorOccured("ManualDealerAssignment column in your test data file is blank.");
		}		
	}

	/**
	 * This method clicks on the search button.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnManualDealerSearchButttonQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_BUTTON_Q_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_BUTTON_Q_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_BUTTON_Q_XPATH);
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_BUTTON_Q_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.stalenessOf(BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DEALER_TABLE_ROW_XPATH)));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DEALER_TABLE_ROW_XPATH);
	}
	
	/**
	 * This method verify that only single dealer row is displayed.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifySingleDealerRowDisplayed() throws Exception{
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SELCTION_ROW_XPATH.getValue()), 1));
	}
	
	/**
	 * This method select delivering dealer and ordering dealer while resolving manual soft error.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void selectDeleveringAndOrderingDealer() throws Throwable {
		OrderingBODealerQueuePage.clickDeleveringDealerRadioButton();
		OrderingBODealerQueuePage.clickOnManualDealerSearchButttonQueuePage();
		OrderingBODealerQueuePage.enterRadius();
		OrderingBODealerQueuePage.selectOnFirstDispalyedDeliveringDealer();
		OrderingBODealerQueuePage.clickOnSaveButtonManualDealerQueuePage();
		OrderingBODealerQueuePage.clickOrderingDealerRadioButton();
		OrderingBODealerQueuePage.clickOnManualDealerSearchButttonQueuePage();
		OrderingBODealerQueuePage.selectOnFirstDispalyedOrderingDealer();
		OrderingBODealerQueuePage.clickOnSaveButtonManualDealerQueuePage();
	}
	
	/**
	 * This method enters radius for dealer search
	 * Note: Dealer radius search is hardcoded as its value needs to be added for all individual test cases
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterRadius() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID);
		System.out.println("Radius: "+"9999");
		BrowserAction.clickandClear(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID);
		BrowserAction.enterFieldValue(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID, "9999");
	}
	
	/**
	 * This method clicks on the delivering dealer radio button
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickDeleveringDealerRadioButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DELIVERING_DEALER_RADIO_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DELIVERING_DEALER_RADIO_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DELIVERING_DEALER_RADIO_BUTTON_XPATH);
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DELIVERING_DEALER_RADIO_BUTTON_XPATH);
	}
	
	/**
	 * This method clicks on the ordering dealer radio button
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOrderingDealerRadioButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ORDERING_DEALER_RADIO_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ORDERING_DEALER_RADIO_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ORDERING_DEALER_RADIO_BUTTON_XPATH);
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ORDERING_DEALER_RADIO_BUTTON_XPATH);
	}
	
	/**
	 * This method clicks on the first delivering dealer radio button
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void selectOnFirstDispalyedDeliveringDealer() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DEALER_TABLE_ROW_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DEALER_TABLE_ROW_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DEALER_TABLE_ROW_XPATH);
		System.out.println("Selected dealer: " + BrowserAction.getElementText(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DEALER_TABLE_ROW_XPATH).trim());
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DEALER_TABLE_ROW_XPATH);
		PDFReporter.takeExtraScreenshot();
	}
	
	/**
	 * This method clicks on the first ordering dealer radio button
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void selectOnFirstDispalyedOrderingDealer() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DEALER_TABLE_ROW_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DEALER_TABLE_ROW_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DEALER_TABLE_ROW_XPATH);
		System.out.println("Selected dealer: " + BrowserAction.getElementText(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DEALER_TABLE_ROW_XPATH).trim());
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_DEALER_TABLE_ROW_XPATH);
		PDFReporter.takeExtraScreenshot();
	}	

	/**
	 * This method clicks on the first searched dealer row.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectManualDealerQueuePage() throws Exception{
		((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SELCTION_Q_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SELCTION_Q_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SELCTION_Q_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SELCTION_Q_XPATH);
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SELCTION_Q_XPATH);
	}
	
	/**
	 * This method clicks on the save.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnSaveButtonManualDealerQueuePage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SAVE_BUTTON_Q_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SAVE_BUTTON_Q_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SAVE_BUTTON_Q_XPATH);
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SAVE_BUTTON_Q_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method verify the popup message and color.
	 * @lastModifiedBy shisingh  
	 */
	public static void verifyDealerSaveMessage() {
		WebElement savePopUp = new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.noty_bar")));
		String popupColour = Color.fromString(savePopUp.getCssValue("background-color")).asHex();
		String popupText = savePopUp.getText();
		if(popupColour.equals("#80bd00")||popupColour.equals("#74af39")||popupColour.equals("#82c341")||popupColour.equals("#4cb4e7")) {
			System.out.println("Success pop up: " + popupText);
			Assert.assertEquals(popupText.trim(), "Dealer assignment saved successfully", "Dealer assignment sucess message is incorrect");
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 0));
		} else {
			System.err.println("Pop up message: " + popupText);
			System.err.println("Pop up colour: " + popupColour);
			throw new OrderingErrorOccured(popupText);
		}
	}
	
	/**
	 * This method clicks on close button.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void closeManualDealerAssignmentPage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_CLOSE_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_CLOSE_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_CLOSE_BUTTON_CSS);
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_CLOSE_BUTTON_CSS);
	}

	/**
	 * This method waits for the manual dealer page to load
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForManualDealerAssignmentPage() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_XPATH);
	}
	
	public static void verifyManualDealerAssignmentPageHeading() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_XPATH);
		String actualManualDealerHeading = BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_XPATH).getText();
		Assert.assertEquals(actualManualDealerHeading.trim(), "Manual Dealer Assignment","Unable to load Manual Dealer assignment page");
	}
	
	/**
	 * This method clicks on the first manual button displayed in dealer queue
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void clickManualButtonOnQueue() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_MANUAL_BUTTON_XPATH);
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_MANUAL_BUTTON_XPATH);
		System.out.println("Manual Button Clicked, Order: " + BrowserAction.getElementText(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_LOGNUMBER_XPATH));
	}
	
	/**
	 * Verify Position of Manual Dealer Assignment Header
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyMDAHeaderPosition() throws Exception{
		int headerPosition = BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_XPATH).getLocation().getY();
		int wrapperPosition = BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_INFO_WRAPPER_XPATH).getLocation().getY();
		Assert.assertEquals(wrapperPosition > headerPosition, true);	
	}
	
	
	/**
	 * Verify Manual Dealer Assignment Buttons
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyMDAButtons() throws Exception{
		BrowserVerify.verifyElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_SEARCH_BUTTON_CLASS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_CLOSE_BUTTON_CSS);
	}
	
	/**
	 * Verify values in select dealer length dropdown
	 * @param expectedValues
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifySelectLengthValues(String expectedValues) throws Exception {
		ArrayList<String> actualValues = new ArrayList<>();
		CommonPage.scrollToBottomOfPage();
		if (!BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_TABLE_LENGTH_SELECT_NAME).isEmpty()) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_TABLE_LENGTH_SELECT_NAME);
			CommonPage.javascriptScrollUntilElementIsVisible(BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_TABLE_LENGTH_SELECT_NAME));
			OrderingCommonPage.verifyNoAlertPopUpDispalyed();
			Select tableLengthDropdown = new Select(BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_TABLE_LENGTH_SELECT_NAME));
			for (WebElement dropdownOption : tableLengthDropdown.getOptions()) {
				System.out.println(dropdownOption.getText());
				actualValues.add(dropdownOption.getText());
			}
			System.out.println("Values found in dropdown: " + actualValues.toString());
			System.out.println("Expected values: " + expectedValues);
			Assert.assertEquals(actualValues.toString().contains(expectedValues), true);
		}else {
			System.out.println("Length dropdown not found on screen");
		}
		CommonPage.javascriptScrollUntilElementIsVisible(BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_PAGE_XPATH));
	}
	
	/**
	 * Verify dealer queue table headers
	 * @param expectedHeaders
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyTableHeadersDealerQueue(String [] expectedHeaders) throws Exception {
		if (BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_LAST_ELEMENT_TABLE_XPATH).size() > 0) {
		Arrays.asList(expectedHeaders);
		ArrayList<String> actualHeaders = new ArrayList<>();
		WebElement lastElementInTable = BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_LAST_ELEMENT_TABLE_XPATH);
		List <WebElement> headers = BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_HEADERS_TABLE_XPATH);
		for (int i = 0; i < headers.size(); i++) {
			if (!headers.get(i).getText().isEmpty()   ) {
				actualHeaders.add(headers.get(i).getText());
			}else {
				CommonPage.javascriptScrollUntilElementIsVisible(lastElementInTable);
				actualHeaders.add(	headers.get(i).getText());
			}
		}
		System.out.println("actual Headers: " + actualHeaders);
		System.out.println("expected headers: " + Arrays.asList(expectedHeaders));
		Assert.assertEquals(actualHeaders.containsAll(Arrays.asList(expectedHeaders)), true);
		WebElement firstElementInTable = BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_FIRST_ELEMENT_TABLE_XPATH);
		CommonPage.javascriptScrollUntilElementIsVisible(firstElementInTable);
		}else {
				System.err.println("Dealer Table was not shown on screen");
		}
	}
	
	/**
	 * Verify dealer queue table headers
	 * @param expectedHeaders
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyTableHeadersOrderingDealer(String [] expectedHeaders) throws Exception {
		if (BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_LAST_ELEMENT_TABLE_XPATH).size() > 0) {
		Arrays.asList(expectedHeaders);
		ArrayList<String> actualHeaders = new ArrayList<>();
		List <WebElement> headers = BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_HEADERS_TABLE_XPATH);
		for (int i = 0; i < headers.size(); i++) {
			if (!headers.get(i).getText().isEmpty()   ) {
				actualHeaders.add(headers.get(i).getText());
			}
		}
		System.out.println("actual Headers: " + actualHeaders);
		System.out.println("expected headers: " + Arrays.asList(expectedHeaders));
		Assert.assertEquals(actualHeaders.containsAll(Arrays.asList(expectedHeaders)), true);
		}else {
			System.err.println("Dealer Table was not shown on screen");
		}
	}
	
	/**
	 * Click Ordering Dealer Radio and search 
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void searchOrderingDealer() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_ORDERING_DEALER_RADIO_XPATH);
		if (!BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_ORDERING_DEALER_RADIO_XPATH).isSelected()) {
			BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_ORDERING_DEALER_RADIO_XPATH).click();
		}
		BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_SEARCH_BUTTON_CLASS).click();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * Click Delivering Dealer Radio and search 
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void searchDeliveringDealer() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DELIVERING_DEALER_RADIO_XPATH);
		if (!BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DELIVERING_DEALER_RADIO_XPATH).isSelected()) {
			BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DELIVERING_DEALER_RADIO_XPATH).click();
		}
		BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_SEARCH_BUTTON_CLASS).click();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * Verify Default sorting is by distance for delivering dealer
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyDeliveringDealerDefaultSorting() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_DISTANCE_CELL_XPATH);
		WebElement distanceCell = BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_DISTANCE_CELL_XPATH);
		CommonPage.javascriptScrollUntilElementIsVisible(distanceCell);
		List <WebElement> distances = BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_DISTANCE_CELL_XPATH);
		List <Double> distanceValues = new ArrayList<>();
		for (WebElement distance : distances) {
			if (!distance.getText().isEmpty()) {
				distanceValues.add(Double.valueOf(distance.getText()));
			}else {
				break;
			}
		}
		System.out.println("Distance values: " + distanceValues);
		Assert.assertEquals(Ordering.natural().isOrdered(distanceValues), true, "Dealers are not default sorted by distance");
	}
	
	/**
	 * Verify Default sorting is by dealer name for ordering dealer
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyOrderingDealerDefaultSorting() throws Exception{
		if (BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DEALERNAME_CELL_XPATH).size() > 0) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DEALERNAME_CELL_XPATH);
			List <WebElement> dealerNames = BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DEALERNAME_CELL_XPATH);
			List <String> dealerNamesDisplayed = new ArrayList<>();
			for (WebElement dealerName : dealerNames) {
				dealerNamesDisplayed.add(dealerName.getText());
			}
			System.out.println("Dealer Names Displayed: " + dealerNamesDisplayed);
			Assert.assertEquals(Ordering.natural().isOrdered(dealerNamesDisplayed), true, "Ordering Dealers are not default sorted by dealer name" + dealerNamesDisplayed);
		}
	}
	
	/**
	 * Verify Active Switch 
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyActiveSwitch() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_ACTIVEDEALER_SWITCH_XPATH);
		String colorSwitchEnabled = BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_ACTIVEDEALER_SWITCH_XPATH).getCssValue("background-color");
		Assert.assertEquals(Color.fromString(colorSwitchEnabled).asHex().equals("#819b66") || Color.fromString(colorSwitchEnabled).asHex().equals("#82c341"), true, "Color Found: " + Color.fromString(colorSwitchEnabled).asHex());
		BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_ACTIVEDEALER_SWITCH_XPATH).click();
		OrderingCommonPage.verifyNoValidationError();
		String colorSwitchDisabled = BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_ACTIVEDEALER_SWITCH_XPATH).getCssValue("background-color");
		Assert.assertEquals(Color.fromString(colorSwitchDisabled).asHex().equals("#808080") || Color.fromString(colorSwitchDisabled).asHex().equals("#80827e"), true, "Color found: " + Color.fromString(colorSwitchDisabled).asHex() );
		BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_ACTIVEDEALER_SWITCH_XPATH).click();
		OrderingCommonPage.verifyNoValidationError();
	}
	
	/**
	 * Close Manual Dealer Assignment screen and wait for Dealer Queue screen to load
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void navigateBackToDealerQueue() throws Exception{
		OrderingBODealerQueuePage.closeManualDealerAssignmentPage();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * Enter data in given input field
	 * @param inputFieldName (inspect element and enter name tag)
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void enterDataInInputField(String inputFieldName, String dataToSend) throws Exception{
		WebDriverAccess.getDriver().findElement(By.name(inputFieldName)).sendKeys(Keys.chord(Keys.CONTROL, "a"), dataToSend);
		System.out.println("Data to send to text field:" + dataToSend);
	}
	
	/**
	 * Click Search Button on Dealer Screen
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void clickSearchButton() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_BUTTON_XPATH);
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_BUTTON_XPATH);
	}
	
	/**
	 * Search for given order 
	 * @param  inputFieldName dataToSend
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void searchWithParamsDealerQueue(String inputFieldName, String dataToSend) throws Exception{
		OrderingBODealerQueuePage.enterDataInInputField(inputFieldName, dataToSend);
		OrderingBODealerQueuePage.clickSearchButton();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * Select first order if there are results on table 
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void selectFirstOrderIfResults() throws Exception{
		if (BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_MANUAL_BUTTON_XPATH).size() > 0) {
			OrderingBODealerQueuePage.clickManualButtonOnQueue();	
		}else {
			System.out.println("Given Search showed no results");
		}
	}

	/**
	 * Verify Radius
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyRadius() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_LABEL_XPATH);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_LABEL_XPATH).trim(), "Radius");
		if (BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_INFO_LABELS_XPATH).get(1).getText().equalsIgnoreCase("FA")) {
			Assert.assertEquals(BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID).getAttribute("value").trim(), "99");
		}
		else if (BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_INFO_LABELS_XPATH).get(1).getText().equalsIgnoreCase("CA")) {
			Assert.assertEquals(BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID).getAttribute("value").trim(), "150");
		}
	}
	

	/**
	 * Verify Radius Input is not present
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyRadiusInputNotPresent() throws Exception{
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_LABEL_XPATH);
	}
	
	/**
	 * Verify Radius value 
	 * @param int maximumValue 
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyRadiusMaximumValue(String maximumValue) throws Exception{
		CommonPage.forceClearTextField(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID);
		System.out.println("Text To send to input: " + maximumValue);
		CommonPage.enterTextToInputField(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID, maximumValue);
		Assert.assertEquals(BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID).getAttribute("value").trim(), "1000");
	}
	
	/**
	 * Verify search results after modifying radius value
	 * @param distance in miles
	 * @lastModifiedBy dpatil
	 * @throws Exception
	 */
	public static void verifySearchAfterModifyingRadiusValue(String distanceToSearch) throws Exception{
		System.out.println("Text To send to input: " + distanceToSearch);
		CommonPage.forceClearTextField(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID);
		CommonPage.enterTextToInputField(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID, distanceToSearch);
		OrderingBODealerQueuePage.clickSearchButtonMDAScreen();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoValidationError();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_DISTANCE_CELL_XPATH);
		CommonPage.javascriptScrollUntilElementIsVisible(BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_DISTANCE_CELL_HEADER_XPATH));
		List <WebElement> distances = BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_DISTANCE_CELL_XPATH);
		OrderingCommonPage.verifyNoValidationError();
		LinkedList <Double> distanceValues = new LinkedList<>();
		for (WebElement distance : distances) {																																	
			if (!distance.getText().isEmpty()) {
				distanceValues.add(Double.valueOf(distance.getText()));
				Assert.assertEquals(distanceValues.getLast() <= 9999, true, "Dealers should be in a Radius of 9999");
			}
		}
		CommonPage.forceClearTextField(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID);
	}
	
	/**
	 * Click Search Button on manual dealer assignment screen
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void clickSearchButtonMDAScreen() throws Exception{
		BrowserAction.getElement(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_SEARCH_BUTTON_CLASS).click();
	}

	/**
	 * Verify Dealer Search 
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyDealerSearch() throws Exception{
		if(BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DEALERNAME_CELL_XPATH).size() > 0) {
		String dealerName = BrowserAction.getElementText(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DEALERNAME_CELL_XPATH).replaceAll("\\(.*\\)", "").trim();
		String dealerCode = BrowserAction.getElementText(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DEALERCODE_CELL_XPATH).replaceAll("\\(.*\\)", "").trim();
		System.out.println("Text to send to field: " + dealerName);
		CommonPage.enterTextToInputField(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_Q_XPATH, dealerName);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_FIRST_SEARCHED_DEALER_CSS);
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_FIRST_SEARCHED_DEALER_CSS);
		CommonPage.forceClearTextField(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_Q_XPATH);
		OrderingBODealerQueuePage.verifySingleDealerRowDisplayed();
		System.out.println("Text to send to field: " + dealerCode);
		CommonPage.enterTextToInputField(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_Q_XPATH, dealerCode);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_FIRST_SEARCHED_DEALER_CSS);
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_FIRST_SEARCHED_DEALER_CSS);
		OrderingBODealerQueuePage.verifySingleDealerRowDisplayed();
		CommonPage.forceClearTextField(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_Q_XPATH);
		}
	}

	/**
	 * Search by Zip Code
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void searchByZipCode() throws Exception{
		OrderingBODealerQueuePage.enterDataInInputField("supplier-postcode", CommonPage.getTestData("ZipCode"));
		OrderingBODealerQueuePage.verifySearchAfterModifyingRadiusValue(CommonPage.getTestData("Radius"));
	}
	
	/**
	 * Search by State
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void searchByState() throws Exception{
		CommonPage.forceClearTextField(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID);
		CommonPage.forceClearTextField(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_ZIPCODE_TEXTFIELD_NAME);
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_STATE_DROPDOWN_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_STATE_OPTION_XPATH);
		BrowserAction.click(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_STATE_OPTION_XPATH);
		OrderingBODealerQueuePage.clickSearchButtonMDAScreen();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		Assert.assertEquals(BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SELCTION_ROW_XPATH).size() > 0,true);
	}
	
	/**
	 * Verify Dealer Field is ignored if searching by state
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyDealerFieldIsIgnoredSearchByState() throws Exception{
		ArrayList<String> dealerNamesList = new ArrayList<>();
		ArrayList<String> dealerNamesgnoreDealerField = new ArrayList<>();
		List <WebElement> dealerNames = BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DEALERNAME_CELL_XPATH);
		for (WebElement dealerName : dealerNames) {
			dealerNamesList.add(dealerName.getText());
		}
		System.out.println("Text to send to field: " + "invalid data");
		CommonPage.forceClearTextField(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_Q_XPATH);
		CommonPage.enterTextToInputField(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_Q_XPATH, "invalid data");
		OrderingBODealerQueuePage.searchByState();
		List <WebElement> dealerNamesAfterSearch = BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DEALERNAME_CELL_XPATH);
		for (WebElement dealerName : dealerNamesAfterSearch) {
			dealerNamesgnoreDealerField.add(dealerName.getText());
		}
		Assert.assertEquals(dealerNamesList, dealerNamesgnoreDealerField, "Search should ignore Dealer Field");
	}
	
	/**
	 * Verify Dealer Field is ignored if searching by state
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyDealerFieldIsIgnoredSearchByZipCode() throws Exception{
		CommonPage.forceClearTextField(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DD_RADIUS_TEXTFIELD_ID);
		CommonPage.forceClearTextField(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_ZIPCODE_TEXTFIELD_NAME);
		OrderingBODealerQueuePage.searchByZipCode();
		List <WebElement> dealerNames = BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DEALERNAME_CELL_XPATH);
		ArrayList<String> dealerNamesList = new ArrayList<>();
		ArrayList<String> dealerNamesgnoreDealerField = new ArrayList<>();
		for (WebElement dealerName : dealerNames) {
			dealerNamesList.add(dealerName.getText());
		}
		System.out.println("Text To send to input: " + "invalid data");
		CommonPage.forceClearTextField(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_Q_XPATH);
		CommonPage.enterTextToInputField(OrderingBODealerQueuePageEnum.ORDERING_BO_DEALER_SEARCH_Q_XPATH, "invalid data");
		OrderingBODealerQueuePage.searchByZipCode();
		List <WebElement> dealerNamesAfterSearch = BrowserAction.getElements(OrderingBODealerQueuePageEnum.ORDERING_BO_MANUAL_DEALER_ASSIGNMENT_DEALERNAME_CELL_XPATH);
		for (WebElement dealerName : dealerNamesAfterSearch) {
			dealerNamesgnoreDealerField.add(dealerName.getText());
		}
		Assert.assertEquals(dealerNamesList, dealerNamesgnoreDealerField, "Search should ignore Dealer Field");
	}
}
