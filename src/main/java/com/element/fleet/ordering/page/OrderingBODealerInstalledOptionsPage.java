package com.element.fleet.ordering.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOBusinessMaintainedTablesEnum;
import com.element.fleet.ordering.enums.OrderingBODealerInstalledOptionsPageEnum;
import com.element.fleet.ordering.enums.OrderingBOOnOrderQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingBOOrdMaintPageEnum;
import com.element.fleet.ordering.enums.OrderingBOQueuePageEnum;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;

public class OrderingBODealerInstalledOptionsPage {
	
	private static String currentDIOOptionCode, currentBrandProvider, currentOptionDescription, currentExtendedDescription, currentECCode, currentMaxPrice;
	
	/**
	 * This method waits until dealer installed option page is loaded
	 * @lastModifiedBy djawale
	 * @throws Throwable
	 */
	public static void dealerInstalledOptionsPageLoaded() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_SEARCH_FIELD_ID);
	}
	
	/**
	 * This method validated the filters present on Dealer Installed Options Page
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateFiltersOnDealerInstalledOptionsPage() throws Throwable {
		List<String> expectedSearchFieldNames = Arrays.asList(CommonPage.getTestData("CustomColumn2").split("\\|"));
		ArrayList<String> actualSearchFieldsNames = new ArrayList<>();
		List<WebElement> searchFieldElementList = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_SEARCH_FIELDS_ELEMENT_LIST_XPATH);
		for (WebElement e : searchFieldElementList) {
			actualSearchFieldsNames.add(e.getText().trim());
		}
		expectedSearchFieldNames.sort(Comparator.naturalOrder());
		actualSearchFieldsNames.sort(Comparator.naturalOrder());
		Assert.assertEquals(actualSearchFieldsNames, expectedSearchFieldNames);
	}
	
	/**
	 * This method validated the columns present on Dealer Installed Options Page
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateColumnsOnDealerInstalledOptionsPage() throws Throwable {
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn3").split("\\|"));
		ArrayList<String> actualColumnNames = new ArrayList<>();
		List<WebElement> columnElementList = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TABLE_COLUMN_NAMES_XPATH);
		for (WebElement e : columnElementList) {
			actualColumnNames.add(e.getText().trim().replaceAll("\\r\\n|\\r|\\n", " "));
		}
		Assert.assertEquals(expectedColumnNames, actualColumnNames);
	}
	
	/**
	 * This method validated the columns present in Toggle Column list on Dealer Installed Options Page
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateToggleColumnsOnDealerInstalledOptionsPage() throws Throwable {
		OrderingBOQueuePage.clickToggleColumnButton();
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn3").split("\\|"));
		ArrayList<String> actualColumnNames = new ArrayList<>();
		List<WebElement> elementslist = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TOGGLE_ACTIVE_COLUMN_LABEL_XPATH);
		for (WebElement element : elementslist) {
			actualColumnNames.add(element.getText().trim().replaceAll("\\r\\n|\\r|\\n", " "));
		}
		Assert.assertEquals(expectedColumnNames, actualColumnNames);
		OrderingBOQueuePage.closeToggleColumn();
	}
	
	/**
	 * This method adds DIO
	 * @param addBrandProvider
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void addDIOWithBrandProviderName(boolean addBrandProvider) throws Throwable {
		CommonPage.clickElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_XPATH);
		currentDIOOptionCode=CommonPage.randomAlphaNumericString();
		currentOptionDescription=CommonPage.randomAlphaNumericString();
		currentExtendedDescription=CommonPage.randomAlphaNumericString();
		List<String> ecCodes=Arrays.asList(CommonPage.getTestData("CustomColumn4").split("\\|"));
		currentECCode=ecCodes.get(CommonPage.randomNumberInRange(ecCodes.size()-1));
		currentMaxPrice=Integer.toString((CommonPage.randomNumberInRange(100)));
		if(addBrandProvider){
			currentBrandProvider=CommonPage.randomAlphaNumericString();
			System.out.println("Brand Provider entered:: "+currentBrandProvider);
			CommonPage.enterTextToInputField(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_BRAND_PROVIDER_ID, currentBrandProvider);
		}
		System.out.println("Otpion code entered:: "+currentDIOOptionCode);
		CommonPage.enterTextToInputField(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_OPTION_CODE_ID, currentDIOOptionCode);
		CommonPage.loadXMLParameterToTestData("OptionCode", currentDIOOptionCode);
		System.out.println("Option Description entered:: "+currentOptionDescription);
		CommonPage.enterTextToInputField(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_OPTION_DESCRIPTION_ID, currentOptionDescription);
		CommonPage.loadXMLParameterToTestData("OptionDescription", currentOptionDescription);
		System.out.println("Extended Description entered:: "+currentExtendedDescription);
		CommonPage.enterTextToInputField(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_EXTENDED_DESCRIPTION_ID, currentExtendedDescription);
		System.out.println("Max Price entered:: "+currentMaxPrice);
		CommonPage.enterTextToInputField(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_MAX_PRICE_AMOUNT_ID, currentMaxPrice);
		System.out.println("EC Code selected:: "+currentECCode);
		BrowserAction.selectDropdownOptionByText(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_EC_CODE_XPATH,currentECCode);
		CommonPage.clickElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_SUPPLIER_TO_SHIP_IND_CSS);
		CommonPage.clickElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_SAVE_ID);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
	}
	
	/**
	 * This method search DIO using Brand/Provider name
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void searchDIOUsingBrandProvider() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_OPTION_CODE_FILTER_NAME);
		BrowserAction.clickandClear(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_OPTION_CODE_FILTER_NAME);
		System.out.println("Searching using brand provider:: "+currentBrandProvider);
		CommonPage.enterTextToInputField(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_BRAND_PROVIDER_FILTER_NAME, currentBrandProvider);
		CommonPage.clickElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHICON_CSS);
		OrderingCommonPage.checkAlertPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH.getValue()), 1));
	}
	
	/**
	 * This method search DIO using option code
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void searchDIOUsingOptionCode() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_BRAND_PROVIDER_FILTER_NAME);
		BrowserAction.clickandClear(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_BRAND_PROVIDER_FILTER_NAME);
		System.out.println("Searching using option code:: "+currentDIOOptionCode);
		CommonPage.enterTextToInputField(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_OPTION_CODE_FILTER_NAME, currentDIOOptionCode);
		CommonPage.clickElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHICON_CSS);
		OrderingCommonPage.checkAlertPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH.getValue()), 1));
	}
	
	/**
	 * This method valdiates that user is able to edit the DIO which has Brand Provider Name
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void editDIOWithBrandProviderName() throws Throwable {
		CommonPage.clickElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_XPATH);
		currentBrandProvider=CommonPage.randomAlphaNumericString();
		System.out.println("Updated Brand Provider:: "+currentBrandProvider);
		CommonPage.enterTextToInputField(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_BRAND_PROVIDER_ID, currentBrandProvider);
		CommonPage.clickElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_SAVE_ID);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
	}
	
	/**
	 * This method valdiates that user is able to edit the DIO which does not have Brand Provider Name
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void editDIOWithoutBrandProviderName() throws Throwable {
		CommonPage.clickElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_XPATH);
		currentDIOOptionCode=CommonPage.randomAlphaNumericString();
		currentBrandProvider=CommonPage.randomAlphaNumericString();
		System.out.println("Updated DIO option code:: "+currentDIOOptionCode);
		CommonPage.enterTextToInputField(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_OPTION_CODE_ID, currentDIOOptionCode);
		System.out.println("Updated brnad provider:: "+currentBrandProvider);
		CommonPage.enterTextToInputField(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_BRAND_PROVIDER_ID, currentBrandProvider);
		CommonPage.clickElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_SAVE_ID);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
	}
	
	/**
	 * This method valdiates that user is able to delete the DIO 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void deleteDIO() throws Throwable {
		CommonPage.clickElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_XPATH);
		CommonPage.clickElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_DIO_DELETE_ID);
		CommonPage.clickElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_ADD_DIO_CONFIRM_DELETE_DIO_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH.getValue()), 1));
		String rowValue = BrowserAction.getElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH).findElement(By.xpath(".//td")).getText();
		if(!rowValue.equals("No data available in table")) {
			throw new OrderingErrorOccured("DIO is not deleted");
		}
	}
	
	/**
	 * This method valdiates DIO warning message 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateDIOWarningMessage() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_NVAC_WARNING_CSS);
		String expectedMessage="DIOs cannot be added or modified on this order because the MSO paperwork has already been sent out to the dealer.If still required, please notify the dealer by email or phone.";
		String actualMessage=BrowserAction.getElementText(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_NVAC_WARNING_CSS).trim();
		Assert.assertEquals(expectedMessage, actualMessage);
	}
	
	/**
	 * This method clicks on DIO search box 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickDIOSearch() throws Throwable {
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_DIO_SEARCH_ID);
	}
	
	/**
	 * This method validates that DIO warning message is closed after clicking on close or Ok button 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateDIOWarningMessageIsClosed() throws Throwable {
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_BMT_NVAC_WARNING_CSS);
	}
	
	/**
	 * This method clicks on cancel button on warning popup 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickCancelWarningPopup() throws Throwable {
		CommonPage.clickElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_CLOSE_WARNING_CSS);
	}
	
	/**
	 * This method clicks on Ok button on warning popup 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickOkWarningPopup() throws Throwable {
		CommonPage.clickElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_OK_WARNING_CSS);
	}
	
	/**
	 * This method adds DIO from BO 
	 * @param row number where DIO data needs to be added
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void addDIOFromBO(int rowNumber) throws Throwable {
		System.out.println("DIO quantity:: "+CommonPage.getTestData("AdhocDioQty"));
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_QUANTITY_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_QUANTITY_XPATH.toString(),
						rowNumber)), CommonPage.getTestData("AdhocDioQty"));
		String dioOptionCode=CommonPage.randomAlphaNumericString();
		System.out.println("Updated DIO Option Code:: "+dioOptionCode);
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_OPTION_CODE_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_OPTION_CODE_XPATH.toString(),
						rowNumber)), dioOptionCode);
		System.out.println("DIO option description:: "+CommonPage.getTestData("AdhocDioDescription"));
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_OPTION_DESCRIPTION_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_OPTION_DESCRIPTION_XPATH.toString(),
						rowNumber)), CommonPage.getTestData("AdhocDioDescription"));
		System.out.println("DIO option price:: "+CommonPage.getTestData("AdhocDioPrice"));
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_PRICE_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_PRICE_XPATH.toString(),
						rowNumber)), CommonPage.getTestData("AdhocDioPrice"));
		CommonPage.clickElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_SAVE_CSS);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method delete DIO from BO 
	 * @param row no of DIO which needs to be deleted
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void deleteDIOFromBO(int rowNumber) throws Throwable {
		WebDriverAction.click(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_CHECKBOX_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_CHECKBOX_XPATH.toString(),
						rowNumber)));
		WebDriverAction.click(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_LINE_OPTION_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_LINE_OPTION_XPATH.toString(),
						rowNumber)));
		WebDriverAction.click(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_DELETE_SINGLE_DIO_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_DELETE_SINGLE_DIO_XPATH.toString(),
						rowNumber)));
		CommonPage.clickElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_CONFIRM_DELETE_DIO_XPATH);
		CommonPage.clickElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_SAVE_CSS);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method edit DIO from BO 
	 * @param row no for which DIO data needs to be updated
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void editDIOFromBO(int rowNumber) throws Throwable {
		int updatedDIOQuantity=CommonPage.randomSingleDigitInteger();
		System.out.println("Updated DIO Quantity:: "+updatedDIOQuantity);
		WebDriverAction.clickAndClear(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_QUANTITY_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_QUANTITY_XPATH.toString(),
						rowNumber)));
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_QUANTITY_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_QUANTITY_XPATH.toString(),
						rowNumber)),String.valueOf(updatedDIOQuantity));
		String updatedOptionCode=CommonPage.randomAlphaNumericString();
		System.out.println("Updated DIO Option Code:: "+updatedOptionCode);
		WebDriverAction.clickAndClear(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_OPTION_CODE_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_OPTION_CODE_XPATH.toString(),
						rowNumber)));
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_OPTION_CODE_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_OPTION_CODE_XPATH.toString(),
						rowNumber)), updatedOptionCode);
		String updatedOptionDescription=CommonPage.randomAlphaNumericString();
		System.out.println("Updated DIO Option Code:: "+updatedOptionDescription);
		WebDriverAction.clickAndClear(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_OPTION_DESCRIPTION_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_OPTION_DESCRIPTION_XPATH.toString(),
						rowNumber)));
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_OPTION_DESCRIPTION_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_OPTION_DESCRIPTION_XPATH.toString(),
						rowNumber)), updatedOptionDescription);
		int updatedDIOOptionPrice=CommonPage.randomNumberInRange(100);
		System.out.println("Updated DIO option price:: "+updatedDIOOptionPrice);
		WebDriverAction.clickAndClear(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_PRICE_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_PRICE_XPATH.toString(),
						rowNumber)));
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(
				OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_PRICE_XPATH.name(),
				String.format(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_ADD_DIO_PRICE_XPATH.toString(),
						rowNumber)), String.valueOf(updatedDIOOptionPrice));
		CommonPage.clickElement(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_DIO_SAVE_CSS);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}
}
