package com.element.fleet.ordering.page;

import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOInvoiceEntryPageEnum;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.element.fleet.ordering.rest.OrderingRestAPI;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.common.utils.SimpleStringCipher;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;
import com.ge.capital.rainbow.webdriver.WebDriverWaits;

public class OrderingBOInvoiceEntryPage {
	
	public static void addUpfitPurchaseOrderPageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_IE_ORDER_SEARCH_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_IE_SUBSCREEN_TITLE_XPATH);
	}
	
	/**
	 * This method will verify page title
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void invoiceEntryLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_IE_SUBSCREEN_TITLE_XPATH), 
				"Invoice Entry", "Invoice Entry heading did not match with the expected string");
	}
	
	/**
	 * This method will wait to load Invoice Entry page
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForInvoiceEntryPageToLoad()throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ORDER_SEARCH_TEXT_FIELD_ID, CommonPage.getTestData("WaitTime"));
	}

	/**
	 * This method will wait to load invoice entry page and verify page title
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForInvoiceEntryPageToLoadAndVerifyPageTitle()throws Exception {
		invoiceEntryLabelValidation();
		waitForInvoiceEntryPageToLoad();
	}
	
	/**
	 * This method will enter search text in order search text field 
	 * @lastModifiedBy Akshay Kandkonde
	 * @param searchText
	 * @throws Exception
	 */
	public static void enterTextInOrderSearch(String searchText)throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ORDER_SEARCH_TEXT_FIELD_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ORDER_SEARCH_TEXT_FIELD_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ORDER_SEARCH_TEXT_FIELD_ID);
		System.out.println("Order Search: " + searchText);
		CommonPage.enterTextToInputField(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ORDER_SEARCH_TEXT_FIELD_ID, searchText);
	}

	/**
	 * This method will select search text from order list 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectOrderFromOrderList()throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_LIST_LOG_NUMBER_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_LIST_LOG_NUMBER_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_LIST_LOG_NUMBER_XPATH);
		List<WebElement> list = BrowserAction.getElements(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_LIST_LOG_NUMBER_XPATH);
		for(WebElement ele:list) {
			ele.click();
			break;
		}
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method will select PO number from po drop down
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectPurchaseOrderNumber()throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_PO_NUMBER_DROP_DOWN_ID, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_PO_NUMBER_DROP_DOWN_ID);
		CommonPage.waitForElementToLoad(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_PO_NUMBER_DROP_DOWN_OPTION_XPATH, CommonPage.getTestData("WaitTime"));
		System.out.println("Purchase Order Number: " + BrowserAction.getElementText(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_PO_NUMBER_DROP_DOWN_OPTION_XPATH));
		BrowserAction.click(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_PO_NUMBER_DROP_DOWN_OPTION_XPATH);
		BrowserAction.click(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_PO_NUMBER_DROP_DOWN_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method will select invoice received date as current date
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectInvoiceReceivedDate()throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SELECT_INVOICE_RECIEVED_DATE_CSS, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SELECT_INVOICE_RECIEVED_DATE_CSS);
		CommonPage.waitForElementToLoad(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SELECT_TODAY_DATE_CSS, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SELECT_TODAY_DATE_CSS);
	}
	
	/**
	 * This method will enter invoice number
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterInvoiceNumber()throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_INVOICE_NUMBER_ID);
		BrowserAction.clickandClear(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_INVOICE_NUMBER_ID);
		CommonPage.loadXMLParameterToTestData("InvoiceNumber", CommonPage.generateRandomNumber());
		System.out.println("Invoice Number: " + CommonPage.getTestData("InvoiceNumber"));
		BrowserAction.enterFieldValue(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_INVOICE_NUMBER_ID, CommonPage.getTestData("InvoiceNumber"));
	}
	
	/**
	 * This method will select vendor invoice date from vendor invoice date
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectVendorInvoiceDate()throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SELECT_VENDOR_INVOICE_DATE_CSS, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SELECT_VENDOR_INVOICE_DATE_CSS);
		CommonPage.waitForElementToLoad(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SELECT_TODAY_DATE_CSS, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SELECT_TODAY_DATE_CSS);
	}
	
	/**
	 * This method will enter invoice amount in invoice amount text field
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterInvoiceAmount()throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_INVOICE_AMOUNT_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_INVOICE_AMOUNT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_INVOICE_AMOUNT_ID);
		BrowserAction.clickandClear(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_INVOICE_AMOUNT_ID);
		String amount = "20"+CommonPage.randomNumberInRange(8);
		System.out.println("Invoice Amount: " + amount);
		BrowserAction.enterFieldValue(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_INVOICE_AMOUNT_ID, amount);
	}
	
	/**
	 * This method will select current date as delivery date
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectDeliveryDate()throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SELECT_DELIVERY_DATE_CSS, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SELECT_DELIVERY_DATE_CSS);
		CommonPage.waitForElementToLoad(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SELECT_TODAY_DATE_CSS, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SELECT_TODAY_DATE_CSS);
	}
	
	/**
	 * This method will click on Save button
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnSaveButton()throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SAVE_BUTTON_XPATH);
		BrowserAction.click(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_SAVE_BUTTON_XPATH);
	}	

	/**
	 * This method will fill all invoice entry details
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void fillAllInvoiceEntryDetailsAndSave()throws Exception {
		OrderingBOInvoiceEntryPage.enterTextInOrderSearch(OrderingRestAPI.getLogNumberForEditOrder(OrderingRestAPI.getOauthTokenFromOrderingFO(OrderingRestAPI.getCookieFromOrderingFO(CommonPage.getTestData("Username"), SimpleStringCipher.decrypt(CommonPage.getCredetialsData(CommonPage.getTestData("Username")))))));
		OrderingBOInvoiceEntryPage.selectOrderFromOrderList();
		OrderingBOInvoiceEntryPage.selectPurchaseOrderNumber();
		OrderingBOInvoiceEntryPage.selectInvoiceReceivedDate();
		OrderingBOInvoiceEntryPage.enterInvoiceNumber();
		OrderingBOInvoiceEntryPage.selectVendorInvoiceDate();
		OrderingBOInvoiceEntryPage.enterInvoiceAmount();
		OrderingBOInvoiceEntryPage.selectDeliveryDate();
		OrderingBOInvoiceEntryPage.clickOnSaveButton();
	}	
	
	/**
	 * This method will verify new added invoice in table
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyInvoiceNumberInVendorInvoiceHistoryTable()throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_INVOICE_NUMBER_ID, CommonPage.getTestData("WaitTime"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_INVOICE_NUMBER_LIST_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_INVOICE_NUMBER_LIST_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_INVOICE_NUMBER_LIST_XPATH);
		List<WebElement> invoiceNolist = BrowserAction.getElements(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_INVOICE_NUMBER_LIST_XPATH);
		List<String> invoiceNumberList= new ArrayList<>();
		for(WebElement str:invoiceNolist) {
			invoiceNumberList.add(str.getText());
		}
		if(!(invoiceNumberList.contains(CommonPage.getTestData("InvoiceNumber"))))
			throw new OrderingErrorOccured("Unable to find Invoice No: " + CommonPage.getTestData("InvoiceNumber") + " in vendor invoice history table on Invoice Entry page");
	}
	
	/**
	 * This method will uploaded doc in for perticuler invoice and verify link after upload
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void uploadfileAndVerifyUploadedfile(String filePath)throws Exception {
		OrderingBOInvoiceEntryPage.clickOnAttachment();
		OrderingBOInvoiceEntryPage.provideDocumentName(filePath);
		OrderingBOInvoiceEntryPage.clickOnSentButton(filePath);
		OrderingBOInvoiceEntryPage.verifyUploadedFileName(filePath);
	}
	
	/**
	 * This method will click on attachment link
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnAttachment()throws Exception {
		Actions actions = new Actions(WebDriverAccess.getDriver());
		String invoiceNumber = OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_XPATH.getValue();
		invoiceNumber = invoiceNumber.replace("$InvoiceNumber#", CommonPage.getTestData("InvoiceNumber"));
		WebElement mainMenu = WebDriverAction.getElement(By.xpath(invoiceNumber));
		actions.moveToElement(mainMenu);
		WebDriverAction.click(By.xpath(invoiceNumber));
	}
	
	/**
	 * This method will provide doc and name path
	 * @lastModifiedBy Akshay Kandkonde
	 * @param filePath
	 * @throws Exception
	 */
	public static void provideDocumentName(String filePath)throws Exception {
		String invoiceNumber1 = OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_BROWSER_BUTTON_XPATH.getValue();
		invoiceNumber1 = invoiceNumber1.replace("$InvoiceNumber#", CommonPage.getTestData("InvoiceNumber"));
		String path = System.getProperty("user.dir")+"\\resources\\"+filePath;
		System.out.println("File path: " + path);
		WebDriverAction.getElement(By.xpath(invoiceNumber1)).sendKeys(path);
	}
	
	/**
	 * This method will click on send button and verify success pop up
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnSentButton(String filePath)throws Exception {
		String invoiceNumber2 = OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_SEND_BUTTON_XPATH.getValue();
		invoiceNumber2 = invoiceNumber2.replace("$InvoiceNumber#", CommonPage.getTestData("InvoiceNumber"));
		WebDriverAction.click(By.xpath(invoiceNumber2));
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method will verify uploaded file name
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyUploadedFileName(String filePath)throws Exception {
		String invoiceNumber = OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_LINK_XPATH.getValue();
		invoiceNumber = invoiceNumber.replace("$InvoiceNumber#", CommonPage.getTestData("InvoiceNumber"));
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(invoiceNumber));
		String uploadedFileName = WebDriverAction.getElement(By.xpath(invoiceNumber)).getText();
		assertEquals(uploadedFileName, filePath, "Unable to match actual file name and uploaded file name");
	}
	
	/**
	 * This method will download uploaded doc
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void downloadUploadedfile(String className)throws Exception {
		String invoiceNumber = OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_LINK_XPATH.getValue();
		invoiceNumber = invoiceNumber.replace("$InvoiceNumber#", CommonPage.getTestData("InvoiceNumber"));
		CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
		WebDriverAction.click(By.xpath(invoiceNumber));
		OrderingCommonPage.checkAlertPopUp();
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), "InvoiceEntry", className);
	}
	
	/**
	 * This method will download uploaded doc and verify downloaded doc
	 * @lastModifiedBy Akshay Kandkonde
	 * @param className
	 * @throws Exception
	 */
	public static void clickForDownloadAndVerify(String className)throws Exception {
		OrderingBOInvoiceEntryPage.downloadUploadedfile(className);
	}
	
	/**
	 * This method will click on Cancel button
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnCancelButton()throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_CANCEL_BUTTON_XPATH);
		BrowserAction.click(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_CANCEL_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	 
	/**
	 * This method will verify once cancel button clicked invoice entry should be cancelled
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyCancelfunctionality()throws Exception {
		if(!BrowserAction.getElement(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_PO_NUMBER_DROP_DOWN_ID).isDisplayed()) {
			BrowserAction.getElement(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ORDER_SEARCH_TEXT_FIELD_ID).isDisplayed();
		} else {
			throw new OrderingErrorOccured("Unable to Cancel Invoice Entry");
		}
	}
	
	/**
	 * This method will cancel and verify cancel button fuctionality
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyCancelButtonFunctionality()throws Exception {
		OrderingBOInvoiceEntryPage.clickOnCancelButton();
		OrderingBOInvoiceEntryPage.verifyCancelfunctionality();
	}
	
	/**
	 * This method will return the column number which will use for sorting
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static int columnNumber(String columnName)throws Exception {
		int columnCount=0;
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_LIST_COLUMNS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_LIST_COLUMNS_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_LIST_COLUMNS_XPATH);
		List<WebElement> listColumns = BrowserAction.getElements(OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_ATTACHMENT_LIST_COLUMNS_XPATH);
		for(WebElement str:listColumns) {
			columnCount++;
			if(str.getText().equals(columnName))
				break;
		}
		return columnCount;
	}
	
	/**
	 * This method will return collection of specified column data in collection in string format
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static ArrayList<String> getColumnData(String columnName)throws Exception {
		ArrayList<String> stringListColumnData= new ArrayList<String>();
		String xpathColumnList = OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_COLUMN_NAME_SORTING_XPATH.getValue();
		xpathColumnList = xpathColumnList.replace("$ColumnCount#", columnNumber(columnName)+"");
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(xpathColumnList));
		List<WebElement> listColumnsData = WebDriverAction.getElements(By.xpath(xpathColumnList));
		for(WebElement ele : listColumnsData) {
			if(!ele.getText().equals(""))
				stringListColumnData.add(ele.getText());
		}
		return stringListColumnData;
	}
	
	/**
	 * This method will click on specified column for sorting function
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnColumn(String columnName)throws Exception {
		String xpathColumnHeader = OrderingBOInvoiceEntryPageEnum.ORDERING_BO_INVOICE_ENTRY_COLUMN_NAME_XPATH.getValue();
		xpathColumnHeader = xpathColumnHeader.replace("$ColumnName#", columnName);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(xpathColumnHeader));
		WebDriverAction.hoverOverElement(By.xpath(xpathColumnHeader));
		WebDriverAction.click(By.xpath(xpathColumnHeader));
	}
	
}