package com.element.fleet.ordering.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.ExcelUtil;
import com.element.fleet.ordering.enums.OrderingBOAdditionalOptionsPageEnum;
import com.element.fleet.ordering.enums.OrderingBOBusinessMaintainedTablesEnum;
import com.element.fleet.ordering.enums.OrderingBODealerInstalledOptionsPageEnum;
import com.element.fleet.ordering.enums.OrderingBOOnOrderQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingBOQueuePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingBOAdditionalOptionsPage {

	private static String enteredBrandProviderValue, fetchedBrandProviderValue, currentOptionCode;

	/**
	 * This method validated the columns present on Additional Options Page
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateColumnsOnAdditionalOptionsPage() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TOGGLE_COLUMN_BUTTON_XPATH);
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn2").split("\\|"));
		ArrayList<String> actualColumnNames = new ArrayList<>();
		List<WebElement> columnElementList = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TABLE_COLUMN_NAMES_XPATH);
		for (WebElement e : columnElementList) {
			actualColumnNames.add(e.getAttribute("innerText").trim().replaceAll("\\r\\n|\\r|\\n", " "));
		}
		Assert.assertEquals(expectedColumnNames, actualColumnNames);
	}

	/**
	 * This method validated the columns present in Toggle Column list on Additional Options Page
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateToggleColumnsOnAdditionalOptionsPage() throws Throwable {
		OrderingBOQueuePage.clickToggleColumnButton();
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn2").split("\\|"));
		ArrayList<String> actualColumnNames = new ArrayList<>();
		List<WebElement> elementslist = BrowserAccess.getElements(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_TOGGLE_ACTIVE_COLUMN_LABEL_XPATH);
		for (WebElement element : elementslist) {
			actualColumnNames.add(element.getAttribute("innerText").trim().replaceAll("\\r\\n|\\r|\\n", " "));
		}
		Assert.assertEquals(expectedColumnNames, actualColumnNames);
		OrderingBOQueuePage.closeToggleColumn();
	}

	/**
	 * This method exports the additional options grid and validates the columns present in it 
	 * @lastModifiedBy djawale
	 * @param className
	 * @throws Exception
	 */
	public static void validateColumnsInExportedCSVOnAdditionalOptionsPage(String className) throws Throwable {
		CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_EXPORT_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		String downloadedFileName=className+System.currentTimeMillis();
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"), downloadedFileName);
		OrderingBOOnOrderQueuePage.setCSVToMap(downloadedFileName);
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn2").split("\\|"));
		List<String> actualColumnNames = new ArrayList<>(ExcelUtil.getCSVMapData().keySet());
		Assert.assertEquals(expectedColumnNames, actualColumnNames);
	}

	/**
	 * This method clicks on Add New button on Additional options page 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickAddNewAdditionalOption() throws Throwable {
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_XPATH);
	}

	/**
	 * This method validates that telematics flag is mandatory 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateTelematicsFlagIsMandatory() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_TELEMATICS_FLAG_CSS);
		WebElement element=BrowserAction.getElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_TELEMATICS_FLAG_LABEL_CSS);
		Assert.assertTrue(element.getAttribute("innerText").trim().contains("*"));
	}

	/**
	 * This method validates that DIO flag is set to false by default and Brand/Provider and Supplier to Ship fields are not displayed 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateDIOAndBrandProviderSupplierToShip() throws Throwable {
		Assert.assertFalse(OrderingBOAdditionalOptionsPage.isToggleButtonSelected("DIO"), "DIO flag is set to True by default");
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_BRAND_PROVIDER_ID);
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_SUPPLIER_TO_SHIP_CSS);
	}

	/**
	 * This method validates whether given toggle button is selected or not
	 * @param toggleButton
	 * @return boolean
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static boolean isToggleButtonSelected(String toggleButton) throws Throwable {
		boolean status=false;
		Object element;
		switch(toggleButton) {
			case "DIO": 
				element = OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_DIO_INDICATOR_CSS;
				System.out.println("Button selected: " + toggleButton);
				break;
			case "Supplier To Ship": 
				element = OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_SUPPLIER_TO_SHIP_CSS;
				System.out.println("Button selected: " + toggleButton);
				break;
			case "Post Production": 
				element = OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_POST_PRODUCTION_INDICATOR_CSS;
				System.out.println("Button selected: " + toggleButton);
				break;
			case "Transmit Flag": 
				element = OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_TRANSMIT_FLAG_CSS;
				System.out.println("Button selected: " + toggleButton);
				break;
			case "Telematics Flag": 
				element = OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_TELEMATICS_FLAG_CSS;
				System.out.println("Button selected: " + toggleButton);
				break;
			default: throw new InvalidSwitchCaseException("Invalid Toggle Button name");
		}
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(element)));
		WebElement ele=BrowserAction.getElement(element);
		if(Color.fromString(ele.getCssValue("background-color")).asHex().equals("#82c341")) 
			status=true;
		return status;
	}

	/**
	 * This method validates that on setting DIO flag to true, Brand/Provider and Supplier to Ship fields are displayed 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void toggleDIOValidateBrandProviderSupplierToShip() throws Throwable {
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_DIO_INDICATOR_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_SUPPLIER_TO_SHIP_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_SUPPLIER_TO_SHIP_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_BRAND_PROVIDER_ID);
	}

	/**
	 * This method sets brand provider value 
	 * @param brandProviderValue
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void setBrandProvider(String brandProvider) throws Throwable {
		enteredBrandProviderValue=brandProvider;
		System.out.println("Brand Provider entered:: "+enteredBrandProviderValue);
		CommonPage.enterTextToInputField(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_BRAND_PROVIDER_ID, enteredBrandProviderValue);
	}

	/**
	 * This method returns brand provider value 
	 * @return brandProviderValue
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static String getBrandProvider() throws Throwable {
		fetchedBrandProviderValue=BrowserAction.getElementAttributeValue(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_BRAND_PROVIDER_ID, "value");
		return fetchedBrandProviderValue;
	}

	/**
	 * This method add Brand Provider and set/reset DIO 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void addBrandProvider() throws Throwable {
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_SUPPLIER_TO_SHIP_CSS);
		OrderingBOAdditionalOptionsPage.setBrandProvider(CommonPage.randomAlphaNumericString());
	}
	
	/**
	 * This method validates Brand/Provider and Supplier to Ship field values are not retained 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateBrandProviderSupplierToShipAreNotRetained() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_SUPPLIER_TO_SHIP_CSS);
		Assert.assertFalse(OrderingBOAdditionalOptionsPage.isToggleButtonSelected("Supplier To Ship"), "Supplier to ship value retained");
		Assert.assertFalse(OrderingBOAdditionalOptionsPage.getBrandProvider().equals(enteredBrandProviderValue), "Brand/Provider name retained");
	}
	
	/**
	 * This method set and Reset DIO 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void setResetDIO() throws Throwable {
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_DIO_INDICATOR_CSS);
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_DIO_INDICATOR_CSS);
	}

	/**
	 * This method validates that on setting/resetting DIO flag and saving changes, Brand/Provider and Supplier to Ship field values are retained 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void validateBrandProviderSupplierToShipAreRetainedOnSavingChanges() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_SUPPLIER_TO_SHIP_CSS);
		Assert.assertTrue(OrderingBOAdditionalOptionsPage.isToggleButtonSelected("Supplier To Ship"), "Supplier to ship value retained");
		Assert.assertTrue(OrderingBOAdditionalOptionsPage.getBrandProvider().equals(enteredBrandProviderValue), "Brand/Provider name retained");
	}
	
	/**
	 * This method validates that on setting/resetting DIO flag and saving changes,
	 * Brand/Provider and Supplier to Ship field values are retained
	 * @param whetherToAddDIO in additional Option
	 * @param whetherToEnable Telematics flag in additional option
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void addAdditionalOption(boolean addDIO, boolean addTelematicsFlag) throws Throwable {
		System.out.println("Corp code selected:: " + CommonPage.getTestData("CorpCode"));
		CommonPage.selectDropdownValue(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_CORPCODE_ID,
				CommonPage.getTestData("CorpCode"));
		System.out.println("Client selected:: " + CommonPage.getTestData("ClientNumber"));
		CommonPage.enterTextToInputField(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_FLEET_ID,
				CommonPage.getTestData("ClientNumber"));
		currentOptionCode = CommonPage.randomAlphaNumericString();
		System.out.println("Option Code entered:: " + currentOptionCode);
		CommonPage.enterTextToInputField(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_OPTION_CODE_ID,
				currentOptionCode);
		String currentMFROptionCode = CommonPage.randomAlphaNumericString();
		System.out.println("MFR Option Code entered:: " + currentMFROptionCode);
		CommonPage.enterTextToInputField(
				OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_MFR_OPTION_CODE_ID, currentMFROptionCode);
		String optionDescription = CommonPage.randomAlphaNumericString();
		System.out.println("Option Description entered:: " + optionDescription);
		CommonPage.enterTextToInputField(
				OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_OPTION_DESCRIPTION_ID, optionDescription);
		String extendedOptionDescription = CommonPage.randomAlphaNumericString();
		System.out.println("Extended option description entered:: " + extendedOptionDescription);
		CommonPage.enterTextToInputField(
				OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_EXTENDED_OPTION_DESCRIPTION_ID,
				extendedOptionDescription);
		System.out.println("Option price entered:: "+CommonPage.getTestData("CustomColumn5"));
		CommonPage.enterTextToInputField(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_OPTION_PRICE_ID,
				CommonPage.getTestData("CustomColumn5"));
		System.out.println("Estimated/Actual price selected:: "+CommonPage.getTestData("CustomColumn6"));
		CommonPage.selectDropdownValue(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_EST_ACT_PRICE_ID,
				CommonPage.getTestData("CustomColumn6"));
		System.out.println("Option MSRP entered:: "+CommonPage.getTestData("CustomColumn5"));
		CommonPage.enterTextToInputField(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_OPTION_MSRP_ID,
				CommonPage.getTestData("CustomColumn5"));
		List<String> ECCodes = Arrays.asList(CommonPage.getTestData("CustomColumn4").split("\\|"));
		String ecCode = ECCodes.get(CommonPage.randomNumberInRange(ECCodes.size() - 1));
		System.out.println("EC Code selected:: " + ecCode);
		CommonPage.selectDropdownValue(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_EC_CODE_ID,
				ecCode);
		if (addDIO) {
			if (!OrderingBOAdditionalOptionsPage.isToggleButtonSelected("DIO"))
				CommonPage
						.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_DIO_INDICATOR_CSS);
			OrderingBOAdditionalOptionsPage.addBrandProvider();
		}
		if (addTelematicsFlag)
			CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_TELEMATICS_FLAG_CSS);
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_SAVE_ID);
		BrowserWait.waitUntilElementIsDisplayed(
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
		Assert.assertEquals(
				BrowserAction
						.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS),
				" Data has been successfully saved");
		BrowserWait.waitUntilElementIsNotDisplayed(
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
	}
	
	/**
	 * This method navigates back to Additional Options queue page 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void backToQueueView() throws Throwable {
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_DD_AO_BACK_TO_QUEUE_VIEW_ID);
	}
	
	/**
	 * This method navigates back to Additional Options queue page 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void searchAndNavigateToAdditionalOption() throws Throwable {
		System.out.println("Searching using option code:: "+currentOptionCode);
		CommonPage.enterTextToInputField(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_OPTION_CODE_NAME, currentOptionCode);
		CommonPage.clickElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHICON_CSS);
		OrderingCommonPage.checkAlertPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBODealerInstalledOptionsPageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH.getValue()), 1));
		CommonPage.clickElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_XPATH);
	}
	
	/**
	 * This method validates that user is able to edit additional option 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void editAdditionalOption() throws Throwable {
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_ADD_AO_DIO_INDICATOR_CSS);
		OrderingBOAdditionalOptionsPage.addBrandProvider();
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_SAVE_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS), "Data has been successfully updated");
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
	}
	
	/**
	 * This method clicks on Add button on AO details page 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickAddButtonOnAODetailsScreen() throws Throwable {
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_ADD_ID);
	}
	
	/**
	 * This method delete additional option 
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void deleteAdditionalOption() throws Throwable {
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_DIO_ADD_AO_DELETE_ID);
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_ADD_AO_CONFIRM_DELETE_AO_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS), " Record was successfully deleted");
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
	}
	
	/**
	 * This method exports the additional options grid and validates the columns present in it 
	 * @lastModifiedBy mkhairnar
	 * @param className
	 * @throws Exception
	 */
	public static void validateColumnsInExportedCSVOnCDDAPage(String className) throws Throwable {
		CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_EXPORT_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		String downloadedFileName=className+System.currentTimeMillis();
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"), downloadedFileName);
		OrderingBOOnOrderQueuePage.setCSVToMap(downloadedFileName);
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn3").split("\\|"));
		List<String> actualColumnNames = new ArrayList<>(ExcelUtil.getCSVMapData().keySet());
		Assert.assertEquals(expectedColumnNames, actualColumnNames);
	}	
	
	/**
	 * This method exports the additional options grid and validates the columns present in it 
	 * @lastModifiedBy mkhairnar
	 * @param className
	 * @throws Exception
	 */
	public static void validateColumnsInExportedCSVOnOrderPage(String className) throws Throwable {
		CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
		CommonPage.clickElement(OrderingBOAdditionalOptionsPageEnum.ORDERING_BO_BMT_AO_EXPORT_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		String downloadedFileName=className+System.currentTimeMillis();
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"), downloadedFileName);
		OrderingBOOnOrderQueuePage.setCSVToMap(downloadedFileName);
	}	
	
}
