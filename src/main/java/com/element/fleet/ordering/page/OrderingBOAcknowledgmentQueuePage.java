package com.element.fleet.ordering.page;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOAcknowledgmentQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingBOOnOrderQueuePageEnum;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;

public class OrderingBOAcknowledgmentQueuePage {
	
	/** 
	 * This method will verify acknowledgement queue labels
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyAcknowledgmentQueueLabels() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_LABEL1_XPATH), "Acknowledgment", "Unable to match Acknowledgment label");
	}
	
	/** 
	 * This method will click on first record from Acknowledgment queue tables if record is present and verify application is navigated to maintenance page
	 * @throws Throwable 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnFirstRecordIfPresentAndVerifyNavigatedToMaintenancePage() throws Throwable {
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Acknowledgment");
		CommonPage.takeExtraScreenshot();
		WebDriverAccess.getDriver().navigate().back();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/** 
	 * This method will click on first link from Acknowledgment queue tables if record is present
	 * @throws Throwable 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static boolean clickOnTheFirstLinkOfListIfAvailable() throws Exception {
		boolean optionPresent = false;
		List<WebElement> tableRows = BrowserAction.getElements(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH);
		List<WebElement> links = BrowserAction.getElements(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_TABLE_LINKS_COLUMNS_LIST_XPATH);
		if((tableRows.size()==1)&&tableRows.get(0).getText().contains("No data available in table")) {
			System.out.println("No element available to click\n"+BrowserAccess.getElementText(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH));
		} else {
			links.get(0).click();
			System.out.println("Clicked on first link from list");
			OrderingCommonPage.checkAlertPopUp();
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			optionPresent = true;
		}
		return optionPresent;
	} 
	
	/** 
	 * This method will highlight the labels as per passed enum value and field label
	 * @param enumValue
	 * @param fieldLabel
	 * @throws Throwable 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyLabel(String enumValue, String fieldLabel) throws Throwable {
		String xpath = enumValue.replace("$fieldLabel#", fieldLabel);
		CommonPage.highlightElement(WebDriverAction.getElement(By.xpath(xpath)));
	}
	
	/** 
	 * This method will verify element label and page title from Order Acknowledgment page
	 * @throws Throwable 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyAllLabelsFromOrderAcknowledgmentPage() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_LABEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_LABEL_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_LABEL_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_LABEL_XPATH), "Order Acknowledgement", "Unable to match Order Acknowledgement label");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ERROR_MESSAGE_LABEL_XPATH), "Error Messages", "Unable to match Error Messages label");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Corp:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Log Number:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Fleet:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Unit:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Vin:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Estimated Ship Date:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Primary Target Production Date:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Secondary Target Production Date:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Priority Code:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Factory Order Number:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Factory Order Date:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Fin/Fan:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Element Fin/Fan");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"DAN:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Ordering Dealer Code:");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Delivering Dealer Code:");	
	}
	
	/** 
	 * This method will verify Order Acknowledgment page table headers
	 * @throws Throwable 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyTableHeadersFromOrderAcknowledgmentPage() throws Throwable {
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Option Code");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"Description");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"To OEM");
		verifyLabel(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ALL_FIELDS_LABEL_XPATH.getValue(),"From OEM");
	}
	
	/** 
	 * This method will verify button from Order Acknowledgment page
	 * @throws Throwable 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyButtonsFromOrderAcknowledgmentPage() throws Throwable {
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_ACKNOWLEDGMENT_BUTTON_ID), "Acknowledgmemt button not found");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_PDF_BUTTON_ID), "PDF button not found");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_SIGNOUT_BUTTON_XPATH), "Sign Out button not found");
	}

	
	/** 
	 * This method will verify Order Acknowledgment pdf downloaded
	 * @throws Throwable 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyPdfDownload() throws Throwable {
		CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
		BrowserAction.click(OrderingBOAcknowledgmentQueuePageEnum.ORDERING_ACK_QUEUE_ORDER_ACKNOWLEDGEMENT_PDF_BUTTON_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\");
		CommonPage.returnLatestPDFFileName("IOT");
	}
	
	/** 
	 * This method will verify Order Acknowledgment page labels, Column headers, button from newly opened tab
	 * @throws Throwable 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyOrderAcknowledgmentPageLabel() throws Throwable {
		String childWindow = null;
		String parentWindow = WebDriverAccess.getDriver().getWindowHandle();
		Set<String> list = WebDriverAccess.getDriver().getWindowHandles();
		Iterator<String> itr= list.iterator();
		while(itr.hasNext()) {
			childWindow=itr.next();
			if(!parentWindow.equals(childWindow)) {
				WebDriverAccess.getDriver().switchTo().window(childWindow);
				verifyAllLabelsFromOrderAcknowledgmentPage();
				verifyTableHeadersFromOrderAcknowledgmentPage();
				verifyButtonsFromOrderAcknowledgmentPage();
				verifyPdfDownload();
				CommonPage.takeExtraScreenshot();
				WebDriverAccess.getDriver().close();
			}
		}
		WebDriverAccess.getDriver().switchTo().window(parentWindow);
	}
}