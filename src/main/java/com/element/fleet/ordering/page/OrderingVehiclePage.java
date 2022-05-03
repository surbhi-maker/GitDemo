package com.element.fleet.ordering.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.DbConnector;
import com.element.fleet.ordering.enums.OrderingStartHerePageEnum;
import com.element.fleet.ordering.enums.OrderingVehiclePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.element.fleet.ordering.rest.OrderingRestAPI;
import com.element.fleet.rainbowplus.common.KeyPressEvents;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserAssert;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;

public class OrderingVehiclePage {

	/**
	 * This method enter vehicle details depending on test data on the vehicle page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterDetailsVehicleData() throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehiclePage.validateVehiclePageDefaultConditions();
		OrderingVehiclePage.selectVehicleSelectionType();
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehiclePage.waitForVehicleInformationPage();
		OrderingVehiclePage.selectInteriorExteriorColour();
		OrderingVehiclePage.verifyAndSearchDIO();
		OrderingVehiclePage.addDealerInstalledOptions();
		OrderingVehiclePage.addUpfitInformation();
		OrderingVehiclePage.getDetailsFromVehiclePage(WebDriverAccess.getDriver());
	}
	
	/**
	 * This method enter vehicle details depending on test data on the vehicle page with perform add delete Operations.
	 * @lastModifiedBy Mkhairnar
	 * @throws Exception
	 */
	public static void enterDetailsVehicleWithAddDeleteOperations() throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehiclePage.validateVehiclePageDefaultConditions();
		OrderingVehiclePage.selectVehicleSelectionType();
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehiclePage.waitForVehicleInformationPage();
		OrderingVehiclePage.selectInteriorExteriorColour();
		OrderingVehiclePage.addDeleteNewDIO();
		OrderingVehiclePage.addUpfitInformation();
		OrderingVehiclePage.getDetailsFromVehiclePage(WebDriverAccess.getDriver());
	}
	
	/**
	 * This method verifies added dio record row details,column heading,tooltip from BO present in FO
	 * @param Optioncode
	 * @param OptionDescription
	 * @throws Throwable 
	 * @lastModifiedBy skathule
	 */
	public static void verifyAndSearchDIO() throws Exception {
		if(!(Objects.isNull(CommonPage.getTestData("OptionCode")))) {
			OrderingVehiclePage.selectDIOTab();
			OrderingVehiclePage.waitForDIOTab();
			OrderingVehiclePage.verifyVehicleAndDIOInfo();
			OrderingVehiclePage.smartSearchDio("Option Code", CommonPage.getTestData("OptionCode"));
			OrderingVehiclePage.smartSearchDio("Option Description", CommonPage.getTestData("OptionDescription"));
			OrderingVehiclePage.verifyDIORowDetails();
			OrderingVehiclePage.verifyDioColumnHeading();
			OrderingVehiclePage.verifyMandetoryFields();
			OrderingVehiclePage.verifySupplierToolTip();
			OrderingVehiclePage.verifyNoResultFoundErrorMsg();
			OrderingVehiclePage.verifyEditableAndNonEditableFields();
			OrderingVehiclePage.deleteDioRecord();
			OrderingVehiclePage.editDIODetails();
		}
	}

	/**
	 * This method verifies the No result msg after entering invalid option code.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyNoResultFoundErrorMsg() throws Exception {
		System.out.println("Searching using option code description:: "+CommonPage.getTestData("CustomColumn3"));
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_NAME", OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_OPTION_CODE_DESCRIPTION_TEXTBOX_NAME.getValue()), CommonPage.getTestData("CustomColumn3"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_SUGGESION_ERROR_XPATH);
		BrowserAssert.assertEquals((BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_SUGGESION_ERROR_XPATH)).getText(),"No results found!");
	}

	/**
	 * This method deletes the dio record from Dio Tab in FO
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void deleteDioRecord() throws Exception {
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_LINE_ITEM_CHECKBOX_XPATH);
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_LINE_ITEM_OPTIONS_XPATH);
		CommonPage.javascriptScrollUntilElementIsVisible(BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_DELETE_BUTTON_XPATH));
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_DELETE_BUTTON_XPATH);
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_DELETE_POPUP_YES_BUTTON_XPATH);	
	}

	/**
	 * This method verifies All the Existing & New columns added.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyDioColumnHeading() throws Exception {
		String label;
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn1").split("\\|"));	
		ArrayList<String> tableColumns = new ArrayList<String>();
		List<WebElement> tableElements = BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_COLUMN_HEADER_CSS);
		for (WebElement element : tableElements) {
			label = element.getAttribute("innerText").trim();
			if (label != null && !label.equalsIgnoreCase("")) {
				if (label.contains("\n")) {
					label = label.replace("\n", " ");
				}
				tableColumns.add(label);
			}
		}
		Assert.assertTrue(expectedColumnNames.equals(tableColumns), "Table Coulmns are not matching with Test Data");
	}

	/**
	 * This method verifies supplier information icon & tooltip text.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifySupplierToolTip() throws Exception {
		BrowserAssert.assertElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_SUPPLIER_TO_SHIP_TOOL_TIP_XPATH);
		BrowserAssert.assertEquals((BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_SUPPLIER_TO_SHIP_TOOL_TIP_XPATH)).getAttribute("title"),"If toggled, the supplier is to ship this part to the dealer for installation. If untoggled, the dealer is to order and install this part");
	}

	/**
	 * This method verifies the mandetory fields of DIO
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyMandetoryFields() throws Exception {
		List<WebElement> requiredField = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_COLUMN_HEADER_CSS);
		for(WebElement reqColumns :requiredField) {
			if(reqColumns.getAttribute("innerText").contains("*")){	
				System.out.println("Required Columns:" +reqColumns.getAttribute("innerText"));
			}
		}
	}

	/**
	 * This method verifies DIO field label and text present inside the field
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyVehicleAndDIOInfo()  throws Exception {
		BrowserVerify.verifyElementIsDisplayed(BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_VEHICLE_INFO_XPATH));
		BrowserAssert.assertEquals((BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_OPTION_CODE_DESCRIPTION_LABEL_XPATH)).getText().trim(), "Search for Dealer Installed Options","DIO label text not matching");
		BrowserAssert.assertEquals((BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_OPTION_CODE_DESCRIPTION_TEXTBOX_NAME)).getAttribute("placeholder"), "Search by a Option Code, Option Description","DIO Option desc  text not matching");
	}

	/**
	 * This method searches the DIO by option code and option description
	 * @param OptionCode
	 * @param OptionDescription
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void smartSearchDio(String fieldName, String searchText) throws Exception {
		switch(fieldName) {
			case "Option Code":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_NAME", OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_OPTION_CODE_DESCRIPTION_TEXTBOX_NAME.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_NAME", OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_OPTION_CODE_DESCRIPTION_TEXTBOX_NAME.getValue()), searchText);
				BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_FIRST_SEARCH_RESULT_XPATH);
				CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_FIRST_SEARCH_RESULT_XPATH);
				break;
			case "Option Description":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_NAME", OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_OPTION_CODE_DESCRIPTION_TEXTBOX_NAME.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_NAME", OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_OPTION_CODE_DESCRIPTION_TEXTBOX_NAME.getValue()), searchText);
				BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_FIRST_SEARCH_RESULT_XPATH);
				CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_FIRST_SEARCH_RESULT_XPATH);
				break;
			default: throw new InvalidSwitchCaseException("Invalid Search Option");
		}
	}

	/**
	 * This method verifies the Row details
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyDIORowDetails( ) throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_FIRST_COLUMN_ROW_RESULT_CSS.getValue()), 2));
		BrowserAssert.assertEquals((BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ROW_OPTION_CODE_XPATH)).getAttribute("value"),CommonPage.getTestData("OptionCode"));
		BrowserAssert.assertEquals((BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ROW_OPTION_DESC_XPATH)).getAttribute("value"),CommonPage.getTestData("OptionDescription"));
	}

	/**
	 * This method deletes the dio record from Dio Tab in FO
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyEditableAndNonEditableFields() throws Exception {
		if(!(Objects.isNull(BrowserVerify.verifyElementDisabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ROW_OPTION_CODE_XPATH)))) {
			System.out.println(" editable");
		} else {
			System.out.println("non editable");
		}
	}

	/**
	 * This method edit dio record from Dio Tab in FO
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void editDIODetails() throws Exception {
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_QUANTITY_XPATH);
		System.out.println("Updated DIO quantity:: "+CommonPage.getTestData("DioQuantity"));
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_QUANTITY_XPATH,CommonPage.getTestData("DioQuantity"));
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_PRICE_NOT_TO_EXCEED_XPATH);
		System.out.println("Updated Adhoc DIO price:: "+CommonPage.getTestData("AdhocDioPrice"));
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_PRICE_NOT_TO_EXCEED_XPATH,CommonPage.getTestData("AdhocDioPrice"));
	}

	/**
	 * This method waits for the vehicle page to load.
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void waitForVehiclePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_RADIO_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_RADIO_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_RADIO_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_RADIO_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_RADIO_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_RADIO_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_RADIO_ID);
		if(CommonPage.getTestData("OrderType").equals("Stock")&&CommonPage.getTestData("WhoWillLocateVehicle").equals("Client")) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_RADIO_ID);
			BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_RADIO_ID);
		}
	}
	
	/**
	 * This method waits for the vehicle information page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForVehicleInformationPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_VEHICLE_INFORMATION_TAB_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_VEHICLE_INFORMATION_TAB_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_VEHICLE_INFORMATION_TAB_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOTAB_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOTAB_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOTAB_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_INFO_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_INFO_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_INFO_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_LEFT_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_LEFT_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_LEFT_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_RIGHT_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_RIGHT_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_RIGHT_CSS);
	}
	
	/**
	 * This method waits for the vehicle information page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForVehicleSectionInformationPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_LEFT_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_LEFT_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_LEFT_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_RIGHT_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_RIGHT_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_RIGHT_CSS);
	}
	
	/**
	 * This method is used to select the vehicle ordering option and enter the details depending on the selected option.
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
		
	public static void selectVehicleSelectionType() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor)WebDriverAccess.getDriver();
		if(Objects.isNull(CommonPage.getTestData("VehicleOrderingMethod"))) {
			throw new OrderingErrorOccured("VehicleOrderingMethod column in test data file is null");
		}
		switch(CommonPage.getTestData("VehicleOrderingMethod")) {
		case "Select Fleet Specification": 
			if(!(boolean)js.executeScript("return arguments[0].checked", BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_RADIO_ID))) {
				BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_RADIO_ID);
			}
			BrowserVerify.verifyElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_LIST_XPATH);
			OrderingVehiclePage.searchAndSelectSearchedFleet();
			break;
		case "Price & Config":
			if(!(boolean)js.executeScript("return arguments[0].checked", BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_RADIO_ID))) {
				BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_RADIO_ID);
			}
			BrowserVerify.verifyElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRICE_CONFIG_FIRST_RESULT_XPATH);
			OrderingVehiclePage.searchAndSelectPriceAndConfig();
			break;
		case "Build From Scratch":
			if(!(boolean)js.executeScript("return arguments[0].checked", BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_RADIO_ID))) {
				BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_RADIO_ID);
			}
			BrowserVerify.verifyElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_LIST_XPATH);
			OrderingVehiclePage.enterBuildFromScratchDetails();
			break;
		case "Built From VIN":
			if(CommonPage.getTestData("OrderType").equals("Stock")) {
				if(!(boolean)js.executeScript("return arguments[0].checked", BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_RADIO_ID))) {
					BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_RADIO_ID);
				}
				OrderingVehiclePage.selectVINSearch();
			} else {
				throw new OrderingErrorOccured("Built from VIN is available only for Stock orders.");
			}
			break;
		default : throw new InvalidSwitchCaseException(CommonPage.getTestData("VehicleOrderingMethod") + "is a invalid vehicle selection method provided");
		}
	}
	
	/**
	 * This method verifies searched fleet
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void verifySearchedFleet() throws Exception {
	JavascriptExecutor js = (JavascriptExecutor)WebDriverAccess.getDriver();
	if(Objects.isNull(CommonPage.getTestData("VehicleOrderingMethod"))) {
		throw new OrderingErrorOccured("VehicleOrderingMethod column in test data file is null");
	}
		if(!(boolean)js.executeScript("return arguments[0].checked", BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_RADIO_ID))) {
			BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_RADIO_ID);
		}
			BrowserVerify.verifyElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_LIST_XPATH);
			OrderingVehiclePage.verifyFleetNotAssociatedToOrderLogger(CommonPage.getTestData("CustomColumn1"));
			OrderingVehiclePage.verifyFleetNotAssociatedToOrderLoggerWithParialText(CommonPage.getTestData("CustomColumn1"));
			OrderingVehiclePage.verifyFleetAssociatedToOrderLogger(CommonPage.getTestData("CustomColumn2"));
			OrderingVehiclePage.verifyFleetAssociatedToOrderLoggerWithPartialText(CommonPage.getTestData("CustomColumn2"));
}
	
	/**
	 * This method enters fleet id in the search text field of vehicle page and click on the single displayed option.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void searchAndSelectSearchedFleet() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		System.out.println("Fleet spec search: " + CommonPage.getTestData("FleetSpecSearch"));
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS, CommonPage.getTestData("FleetSpecSearch"));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_FIRST_RESULT_CSS.getValue()), 1));
		CommonPage.assertLabelHighlight(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "5"))), CommonPage.getTestData("FleetSpecSearch"), "Fleet id not matched");
		CommonPage.assertLabelHighlight(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "2"))), "Client Approved", "Fleet status not matched");
		CommonPage.getElementOrderObject().getVehicleTabObject().setYear(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "8"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setMake(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "9"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setModel(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "10"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setTrim(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "11"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setModelCode(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "12"))).getText().trim());
		CommonPage.clickHighlight(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "2"))));
	}
	
	/**
	 * This method enters fleet id in the search text field of vehicle page and click on the single displayed option.
	 * @lastModifiedBy akshay kandkonde
	 * @throws Exception
	 */
	public static void searchAndSelectSearchedFleetSpec() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		System.out.println("Fleet spec search: " + CommonPage.getTestData("FleetSpecSearch"));
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS, CommonPage.getTestData("FleetSpecSearch"));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_FIRST_RESULT_CSS.getValue()), 1));
		CommonPage.clickHighlight(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "2"))));
	}
	
	/**
	 * This method enters price & config id in the search text field of vehicle page and click on the single displayed option.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void searchAndSelectPriceAndConfig() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRINCE_CONFIG_SEARCH_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRINCE_CONFIG_SEARCH_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRINCE_CONFIG_SEARCH_CSS);
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRINCE_CONFIG_SEARCH_CSS);
		System.out.println("Price & Config search: " + CommonPage.getTestData("PriceAndConfigSearch"));
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRINCE_CONFIG_SEARCH_CSS, CommonPage.getTestData("PriceAndConfigSearch"));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRINCE_CONFIG_FIRST_RESULT_CSS.getValue()), 1));
		CommonPage.assertLabelHighlight(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_PRINCE_CONFIG_SECTION_XPATH.getValue().replaceAll("position", "5"))), CommonPage.getTestData("PriceAndConfigSearch"), "Fleet id not matched");
		CommonPage.assertLabelHighlight(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_PRINCE_CONFIG_SECTION_XPATH.getValue().replaceAll("position", "2"))), "Client Approved", "Fleet status not matched");
		CommonPage.getElementOrderObject().getVehicleTabObject().setYear(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_PRINCE_CONFIG_SECTION_XPATH.getValue().replaceAll("position", "8"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setMake(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_PRINCE_CONFIG_SECTION_XPATH.getValue().replaceAll("position", "9"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setModel(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_PRINCE_CONFIG_SECTION_XPATH.getValue().replaceAll("position", "10"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setTrim(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_PRINCE_CONFIG_SECTION_XPATH.getValue().replaceAll("position", "11"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setModelCode(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_PRINCE_CONFIG_SECTION_XPATH.getValue().replaceAll("position", "12"))).getText().trim());
		CommonPage.clickHighlight(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_PRINCE_CONFIG_SECTION_XPATH.getValue().replaceAll("position", "2"))));
	}	
	
	/**
	 * This method enters build from scratch details i.e. year, make, model and the searches vehicle based on trim and then clicks on it.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterBuildFromScratchDetails() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_YEAR_DROPDOWN_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_YEAR_DROPDOWN_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_YEAR_DROPDOWN_ID);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_YEAR_DROPDOWN_ID)));
		System.out.println("Year: " + CommonPage.getTestData("Year"));
		BrowserAction.selectDropdownOptionByText(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_YEAR_DROPDOWN_ID, CommonPage.getTestData("Year"));
		OrderingBOQueuePage.waitUntilCompletePageLoad();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MAKE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MAKE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MAKE_TEXTBOX_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MAKE_TEXTBOX_XPATH)));
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MAKE_TEXTBOX_XPATH);
		System.out.println("Make: " + CommonPage.getTestData("Make"));
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MAKE_TEXTBOX_XPATH, CommonPage.getTestData("Make"));
		KeyPressEvents.pressEnter(1);
		OrderingBOQueuePage.waitUntilCompletePageLoad();		
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MODEL_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MODEL_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MODEL_TEXTBOX_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MODEL_TEXTBOX_XPATH)));
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MODEL_TEXTBOX_XPATH);
		System.out.println("Model: " + CommonPage.getTestData("Model"));
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MODEL_TEXTBOX_XPATH, CommonPage.getTestData("Model"));
		KeyPressEvents.pressEnter(1);
		OrderingBOQueuePage.waitUntilCompletePageLoad();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_FIRST_RESULT_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_FIRST_RESULT_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_FIRST_RESULT_CSS);
		OrderingBOQueuePage.waitUntilCompletePageLoad();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(WebDriverAccess.getDriver().findElement(By.cssSelector(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_FIRST_RESULT_CSS.getValue()))));
		List<WebElement> fleetRows = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_RESULT_ROWS_CSS);
		for(int i=0; i<fleetRows.size(); ++i) {
			WebElement row = new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(fleetRows.get(i)));
			if(row.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_ROWS_COLUMN_XPATH.getValue().replaceAll("position", "2"))).getText().trim().equals(CommonPage.getTestData("Year"))&&
				row.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_ROWS_COLUMN_XPATH.getValue().replaceAll("position", "3"))).getText().trim().equals(CommonPage.getTestData("Make"))&&
				row.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_ROWS_COLUMN_XPATH.getValue().replaceAll("position", "4"))).getText().trim().equals(CommonPage.getTestData("Model"))&&
				row.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_ROWS_COLUMN_XPATH.getValue().replaceAll("position", "5"))).getText().trim().equals(CommonPage.getTestData("Trim"))&&
				row.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_ROWS_COLUMN_XPATH.getValue().replaceAll("position", "6"))).getText().trim().equals(CommonPage.getTestData("ModelCode"))) {
				System.out.println("Trim: " + CommonPage.getTestData("Trim"));
				System.out.println("Modal code: " + CommonPage.getTestData("ModelCode"));				
				CommonPage.getElementOrderObject().getVehicleTabObject().setYear(CommonPage.getTestData("Year").trim());
				CommonPage.getElementOrderObject().getVehicleTabObject().setMake(CommonPage.getTestData("Make").trim());
				CommonPage.getElementOrderObject().getVehicleTabObject().setModel(CommonPage.getTestData("Model").trim());
				CommonPage.getElementOrderObject().getVehicleTabObject().setTrim(CommonPage.getTestData("Trim").trim());
				CommonPage.getElementOrderObject().getVehicleTabObject().setModelCode(CommonPage.getTestData("ModelCode").trim());
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_FIRST_RESULT_CSS.getValue())));
				CommonPage.javascriptClick(row);
				break;
			}
			if(i==fleetRows.size()-1) {
				throw new OrderingErrorOccured("No fleet matched with given details in build from scratch option");
			}
		}
	}
	
	/**
	 * This method select specific vehicle depending on year, make, model, trim and model code.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectVINSearch() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_BOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_BOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_BOX_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_BUTTON_ID);
		System.out.println("VIN number: " + CommonPage.getTestData("VinNumberSearch"));
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_BOX_ID);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_BOX_ID, CommonPage.getTestData("VinNumberSearch"));
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_BUTTON_ID);
		OrderingCommonPage.checkAlertPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_FIRST_RESULT_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_FIRST_RESULT_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_FIRST_RESULT_CSS);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_SECTION_CSS.getValue()), 1));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_ROWS_XPATH)));
		List<WebElement> vinVehiclesRows = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_ROWS_XPATH);
		WebElement vinVehicleRowSearch = null;
		for(WebElement vinVehicleRow : vinVehiclesRows) {
			if(vinVehicleRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_ROWS_COLUMN_XPATH.getValue().replaceAll("position", "1"))).getText().trim().equals(CommonPage.getTestData("Year"))&&
					vinVehicleRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_ROWS_COLUMN_XPATH.getValue().replaceAll("position", "2"))).getText().trim().equals(CommonPage.getTestData("Make"))&&
					vinVehicleRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_ROWS_COLUMN_XPATH.getValue().replaceAll("position", "3"))).getText().trim().equals(CommonPage.getTestData("Model"))&&
					vinVehicleRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_ROWS_COLUMN_XPATH.getValue().replaceAll("position", "4"))).getText().trim().equals(CommonPage.getTestData("Trim"))&&
					vinVehicleRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_ROWS_COLUMN_XPATH.getValue().replaceAll("position", "5"))).getText().trim().equals(CommonPage.getTestData("ModelCode"))) {
				System.out.println("Year: " + CommonPage.getTestData("Year"));
				System.out.println("Make: " + CommonPage.getTestData("Make"));
				System.out.println("Model: " + CommonPage.getTestData("Model"));
				System.out.println("Trim: " + CommonPage.getTestData("Trim"));
				System.out.println("Model code: " + CommonPage.getTestData("ModelCode"));				
				CommonPage.getElementOrderObject().getVehicleTabObject().setYear(CommonPage.getTestData("Year").trim());
				CommonPage.getElementOrderObject().getVehicleTabObject().setMake(CommonPage.getTestData("Make").trim());
				CommonPage.getElementOrderObject().getVehicleTabObject().setModel(CommonPage.getTestData("Model").trim());
				CommonPage.getElementOrderObject().getVehicleTabObject().setTrim(CommonPage.getTestData("Trim").trim());
				CommonPage.getElementOrderObject().getVehicleTabObject().setModelCode(CommonPage.getTestData("ModelCode").trim());
				vinVehicleRowSearch = vinVehicleRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_ROWS_COLUMN_XPATH.getValue().replaceAll("position", "6"))).findElement(By.xpath(".//button"));
				break;
			}
		}
		if(Objects.isNull(vinVehicleRowSearch)) {
			throw new OrderingErrorOccured("Year, Make, Model, Trim and Model code vehicle combination is not available for this VIN");
		} else {
			vinVehicleRowSearch.click();
		}
	}
	
	/**
	 * This method selects the exterior and interior colour
	 * Note: There might be cases where if you select any specific exterior colour it will it will uncheck selected interior colour. This condition
	 * is handled in this method
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectInteriorExteriorColour() throws Exception {
		List<WebElement> interiorColourRows = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_TABLE_INTERIOR_COLOURS_ROW_XPATH);
		List<WebElement> exteriorColourRows = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_TABLE_EXTERIOR_COLOURS_ROW_XPATH);
		int iterations = interiorColourRows.size()*exteriorColourRows.size();
		JavascriptExecutor jsExe = (JavascriptExecutor)WebDriverAccess.getDriver();		
		for(int i=0; !checkIfAnyInteriorAndExteriorColourIsSelected();++i) {
			if(!checkIfAnyInteriorColourIsSelected()) {
				WebElement interiorColourCheckBox = interiorColourRows.get(0).findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_CHECKED_RADIO_BUTTON_XPATH.getValue()));
				jsExe.executeScript("arguments[0].scrollIntoView(true);", interiorColourCheckBox);
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(interiorColourCheckBox));
				System.out.println("Clicked on " + i + " interior colour");
				interiorColourCheckBox.click();
				OrderingBOQueuePage.waitUntilCompletePageLoad();
				try {
					new WebDriverWait(WebDriverAccess.getDriver(), 5).until(ExpectedConditions.visibilityOf(BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_COLOR_ACCEPT_CSS)));
					new WebDriverWait(WebDriverAccess.getDriver(), 5).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_COLOR_ACCEPT_CSS)));
					System.out.println("Interior colour pop up accepted");
					WebDriverAccess.getElement(By.cssSelector(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_COLOR_ACCEPT_CSS.getValue())).click();
					OrderingBOQueuePage.waitUntilCompletePageLoad();
				} catch (TimeoutException|NoSuchElementException e) {
					System.out.println("No interior colour issue pop up displayed");
				}
				interiorColourRows.remove(0);
			}
			if(!checkIfAnyExteriorColourIsSelected()) {
				WebElement exteriorColourCheckBox = exteriorColourRows.get(0).findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_CHECKED_RADIO_BUTTON_XPATH.getValue()));
				jsExe.executeScript("arguments[0].scrollIntoView(true);", exteriorColourCheckBox);
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(exteriorColourCheckBox));
				System.out.println("Clicked on " + i + " exterior colour");
				exteriorColourCheckBox.click();
				OrderingBOQueuePage.waitUntilCompletePageLoad();
				try {
					new WebDriverWait(WebDriverAccess.getDriver(), 5).until(ExpectedConditions.visibilityOf(BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_COLOR_ACCEPT_CSS)));
					new WebDriverWait(WebDriverAccess.getDriver(), 5).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_COLOR_ACCEPT_CSS)));
					System.out.println("Exterior colour pop up accepted");
					WebDriverAccess.getElement(By.cssSelector(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_COLOR_ACCEPT_CSS.getValue())).click();
					OrderingBOQueuePage.waitUntilCompletePageLoad();
				} catch (TimeoutException|NoSuchElementException e) {
					System.out.println("No exterior colour issue pop up displayed");
				}
				exteriorColourRows.remove(0);
			}
			if(iterations==i) {
				break;
			}
		}
	}

	/**
	 * This method checks if any element in interior and exterior colour is checked or not and if checked it will print checked element in the console.
	 * @lastModifiedBy shisingh
	 * @return
	 * @throws Exception
	 */
	public static boolean checkIfAnyInteriorAndExteriorColourIsSelected() throws Exception {		
		if(OrderingVehiclePage.checkIfAnyInteriorColourIsSelected()&&OrderingVehiclePage.checkIfAnyExteriorColourIsSelected()) {
			JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
			List<WebElement> interiorColourRows = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_TABLE_INTERIOR_COLOURS_ROW_XPATH);
			List<WebElement> exteriorColourRows = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_TABLE_EXTERIOR_COLOURS_ROW_XPATH);
			boolean fleetSpecCheckStatus;
			String fleetSpecCheckedCode;
			String fleetSpecCheckedDescription;
			String fleetSpecCheckedInvoice;
			String fleetSpecCheckedMSRP;		
			for(WebElement interiorColourRow: interiorColourRows) {
				fleetSpecCheckStatus =  (boolean)js.executeScript("return arguments[0].checked", interiorColourRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_CHECKED_RADIO_BUTTON_XPATH.getValue())));
				if(fleetSpecCheckStatus) {
					fleetSpecCheckedCode = (String)js.executeScript("return arguments[0].textContent", interiorColourRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_CODE_LABEL_XPATH.getValue())));
					fleetSpecCheckedDescription = (String)js.executeScript("return arguments[0].textContent", interiorColourRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_DESCRIPTION_LABEL_XPATH.getValue())));
					fleetSpecCheckedInvoice = (String)js.executeScript("return arguments[0].textContent", interiorColourRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_INVOICE_LABEL_XPATH.getValue())));
					fleetSpecCheckedMSRP = (String)js.executeScript("return arguments[0].textContent", interiorColourRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_MSRP_LABEL_XPATH.getValue())));
					System.out.println("Interior colour code: " + fleetSpecCheckedCode.trim());
					System.out.println("Interior colour description: " + fleetSpecCheckedDescription.trim().split("\n")[0].trim());
					System.out.println("Interior colour invoice: " + fleetSpecCheckedInvoice.trim());
					System.out.println("Interior colour MSRP: " + fleetSpecCheckedMSRP.trim());
				}			
			}
			for(WebElement exteriorColourRow: exteriorColourRows) {
				fleetSpecCheckStatus =  (boolean)js.executeScript("return arguments[0].checked", exteriorColourRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_CHECKED_RADIO_BUTTON_XPATH.getValue())));
				if(fleetSpecCheckStatus) {
					fleetSpecCheckedCode = (String)js.executeScript("return arguments[0].textContent", exteriorColourRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_CODE_LABEL_XPATH.getValue())));
					fleetSpecCheckedDescription = (String)js.executeScript("return arguments[0].textContent", exteriorColourRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_DESCRIPTION_LABEL_XPATH.getValue())));
					fleetSpecCheckedInvoice = (String)js.executeScript("return arguments[0].textContent", exteriorColourRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_INVOICE_LABEL_XPATH.getValue())));
					fleetSpecCheckedMSRP = (String)js.executeScript("return arguments[0].textContent", exteriorColourRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_MSRP_LABEL_XPATH.getValue())));
					System.out.println("Exterior colour code: " + fleetSpecCheckedCode.trim());
					System.out.println("Exterior colour description: " + fleetSpecCheckedDescription.trim().split("\n")[0].trim());
					System.out.println("Exterior colour invoice: " + fleetSpecCheckedInvoice.trim());
					System.out.println("Exterior colour MSRP: " + fleetSpecCheckedMSRP.trim());
				}			
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method checks if any one of the checkboxes in interior colour section is selected or not.
	 * @lastModifiedBy shisingh
	 * @return
	 * @throws Exception
	 */
	public static boolean checkIfAnyInteriorColourIsSelected() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		List<WebElement> interiorColourRows = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_TABLE_INTERIOR_COLOURS_ROW_XPATH);
		boolean interiorColourCheckStatus;
		for(WebElement interiorColourRow: interiorColourRows) {
			interiorColourCheckStatus =  (boolean)js.executeScript("return arguments[0].checked", interiorColourRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_CHECKED_RADIO_BUTTON_XPATH.getValue())));
			if(interiorColourCheckStatus) {
				System.out.println("Yes interior colour is selected");
				return true;
			}
		}
		System.out.println("No interior colour is selected");
		return false;
	}
	
	/**
	 * This method checks if any one of the checkboxes in exterior colour section is selected or not.
	 * @lastModifiedBy shisingh
	 * @return
	 * @throws Exception
	 */
	public static boolean checkIfAnyExteriorColourIsSelected() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		List<WebElement> exteriorColourRows = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_TABLE_EXTERIOR_COLOURS_ROW_XPATH);
		boolean exteriorColourCheckStatus;
		for(WebElement exteriorColourRow: exteriorColourRows) {
			exteriorColourCheckStatus =  (boolean)js.executeScript("return arguments[0].checked", exteriorColourRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_CHECKED_RADIO_BUTTON_XPATH.getValue())));
			if(exteriorColourCheckStatus) {
				System.out.println("Yes exterior colour is selected");
				return true;
			}
		}
		System.out.println("No exterior colour is selected");
		return false;
	}

	/**
	 * This method add dio based on the option provided in the test data sheet.
	 * @lastModifiedBy shisingh 
	 * @throws Exception
	 */
	public static void addDealerInstalledOptions() throws Exception {
		if(!Objects.isNull(CommonPage.getTestData("DioAddMethod"))) {
			OrderingVehiclePage.selectDIOTab();
			OrderingVehiclePage.waitForDIOTab();
			String[] addDioMethods = CommonPage.getTestData("DioAddMethod").split("\\|");
			for(String addDioMethod:addDioMethods) {
				OrderingVehiclePage.addDealerInstalledOptionsMethod(addDioMethod);
			}
		}
	}
	
	/**
	 * This method adds dealer installed option based on the value provide to the.
	 * Option Code->Add dealer installed option using option code section
	 * Option Description->Add dealer installed option using option description section
	 * Adhoc DIO->Add adhoc dealer installed option by adding individual data
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void addDealerInstalledOptionsMethod(String addDioMethod) throws Exception {
		switch(addDioMethod) {
			case "Option Code":
				OrderingVehiclePage.searchAndAddDIO();
				OrderingVehiclePage.verifyDioNotEmpty();
				OrderingVehiclePage.editDIO();
				break;
			case "Option Description":
				OrderingVehiclePage.searchAndAddDIODescription();
				OrderingVehiclePage.verifyDioNotEmpty();
				OrderingVehiclePage.editDIO();
				break;
			case "Adhoc DIO":
				OrderingVehiclePage.addNewDIO();
				OrderingVehiclePage.verifyDioNotEmpty();
				OrderingVehiclePage.editDIO();
				break;
			default: throw new InvalidSwitchCaseException("Wrong dealer installed option entered");
		}
	}

	/**
	 * This method clicks on the dealer installed options tab
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectDIOTab() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOTAB_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOTAB_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOTAB_ID);
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOTAB_ID);
	}
	
	/**
	 * This method waits for DIO page to load properly
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForDIOTab() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONCODESEARCHBOX_NAME);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONCODESEARCHBOX_NAME);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONCODESEARCHBOX_NAME);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ADDNEW_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ADDNEW_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ADDNEW_ID);
	}

	/**
	 * This method adds new adhoc dio by clicking on Add new button and entering adhoc dio details.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void addNewDIO() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ADDNEW_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ADDNEW_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ADDNEW_ID);
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ADDNEW_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICEL_DIO_TABLE_XPATH);
		WebElement mytable= BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICEL_DIO_TABLE_XPATH);
		List<WebElement> rowsTable= mytable.findElements(By.tagName("tr"));
		for(int i=rowsTable.size()-1;i>=0;) {
			rowsTable.get(i).findElement(By.name("quantity")).clear();
			System.out.println("Quantity: " + CommonPage.getTestData("AdhocDioQty"));
			rowsTable.get(i).findElement(By.name("quantity")).sendKeys(CommonPage.getTestData("AdhocDioQty"));
			rowsTable.get(i).findElement(By.name("optionCode")).clear();
			System.out.println("Option code: " + CommonPage.getTestData("AdhocDioOptionCode"));
			rowsTable.get(i).findElement(By.name("optionCode")).sendKeys(CommonPage.getTestData("AdhocDioOptionCode"));
			System.out.println("Description: " + CommonPage.getTestData("AdhocDioDescription"));
			rowsTable.get(i).findElement(By.name("optionDesc")).clear();
			rowsTable.get(i).findElement(By.name("optionDesc")).sendKeys(CommonPage.getTestData("AdhocDioDescription"));
			System.out.println("Price: " + CommonPage.getTestData("AdhocDioPrice"));
			rowsTable.get(i).findElement(By.name("maxPriceAmt")).clear();
			rowsTable.get(i).findElement(By.name("maxPriceAmt")).sendKeys(CommonPage.getTestData("AdhocDioPrice"));
			break;		
		}
	}

	/**
	 * This method adds new adhoc dio by clicking on Add new button and entering adhoc dio details.
	 * @lastModifiedBy Mkhairnar
	 * @throws Exception 
	 */
	public static void addDeleteNewDIO() throws Exception{
		OrderingVehiclePage.selectDIOTab();
		OrderingVehiclePage.waitForDIOTab();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ADDNEW_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ADDNEW_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ADDNEW_ID);
		OrderingVehiclePage.verifyMandatoryFields();
		int countOfAddDIO = Integer.parseInt(CommonPage.getTestData("CustomColumn1"));
		int paginationCount = Integer.parseInt(CommonPage.getTestData("CustomColumn2"));	
		OrderingVehiclePage.addDIO(countOfAddDIO,paginationCount);
		OrderingVehiclePage.backgroundColorVerification();
		OrderingVehiclePage.multipleDeleteDIO();	   
		OrderingVehiclePage.listDeleteDIO(); 
	}
	
	/**
	 * This method Verify Mandatory Field by clicking Add button at top of list.
	 * @param Count :Number of record need to add in DIO option
	 * @param paginationRecordCount :this parameter for how many record to move pagination default it start from 5 record.
	 * @lastModifiedBy Mkhairnar 
	 * @throws Exception
	 */
	public static void addDIO(int count ,int paginationRecordCount) throws Exception{
		for(int record=1;record<=count;record++){
			CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ADDNEW_ID);
			BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_TOTAL_ADD_COUNT_ID);   
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICEL_DIO_TABLE_XPATH);
			WebElement mytable= BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICEL_DIO_TABLE_XPATH);
			List<WebElement> rows_table= mytable.findElements(By.tagName("tr"));
			for(int i=rows_table.size()-1;i>=0;) {	
				if (record == paginationRecordCount){
					CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_NEXT_PAGE_XPATH);
				}
				BrowserWait.waitUntilElementIsDisplayed(rows_table.get(i).findElement(By.name("quantity")));
				rows_table.get(i).findElement(By.name("quantity")).clear();
				System.out.println("Quantity: " + CommonPage.getTestData("AdhocDioQty"));
				rows_table.get(i).findElement(By.name("quantity")).sendKeys(CommonPage.getTestData("AdhocDioQty"));
				rows_table.get(i).findElement(By.name("optionCode")).clear();
				System.out.println("Option code: " + CommonPage.getTestData("AdhocDioOptionCode"));
				rows_table.get(i).findElement(By.name("optionCode")).sendKeys(String.valueOf(record));
				System.out.println("Description: " + CommonPage.getTestData("AdhocDioDescription"));
				rows_table.get(i).findElement(By.name("optionDesc")).clear();
				rows_table.get(i).findElement(By.name("optionDesc")).sendKeys(CommonPage.getTestData("AdhocDioDescription"));
				System.out.println("Price: " + CommonPage.getTestData("AdhocDioPrice"));
				rows_table.get(i).findElement(By.name("maxPriceAmt")).clear();
				rows_table.get(i).findElement(By.name("maxPriceAmt")).sendKeys(CommonPage.getTestData("AdhocDioPrice"));	
				break;
			}	
		}
	}
	
	/**
	 * This method Verify background Color of DIQ table.
	 * @lastModifiedBy Mkhairnar
	 * @throws Exception
	 */
	public static void backgroundColorVerification() throws Exception{
		List<WebElement> bcolors = WebDriverAccess.getElements(By.xpath("//table[@id='queue-table']//tbody//tr"));
		for(WebElement color:bcolors) {
			String backgroundColour = Color.fromString(color.getCssValue("background-color")).asHex();
			System.out.println(backgroundColour);
			if(backgroundColour.equals("#e8eaed")) 
				System.out.println("Background color odd row ");
			else if(backgroundColour.equals("#ffffff"))
				System.out.println("Background color even row ");
			else {
				throw new OrderingErrorOccured("Alternate Background color for row not present. Background color displayed is:: "+backgroundColour);
			}    
		}
	}
	
	/**
	 * This method Verify Mandatory Field by clicking Add button at top of list.
	 * @lastModifiedBy Mkhairnar
	 * @throws Exception
	 */
	public static void verifyMandatoryFields() throws Exception {
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ADDNEW_ID);
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_SAVE_ORDER_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ERROR_MSG1_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ERROR_MSG2_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ERROR_MSG1_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_ERROR_MSG2_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_LIST_DELETE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_LIST_DELETE_XPATH);
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_LIST_DELETE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_LIST_DELETE_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_LIST_DELETE_BUTTON_ID);
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_LIST_DELETE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_POPUP_CLASS);
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_POPUP_YESOPTION_XPATH);
	}
		
	/**
	 * This method perform the Bulk Delete action the with dio by clicking delete button at top of list
	 * depending on Dynamic xpath of addDeleteNewDIO method.
	 * @lastModifiedBy Mkhairnar
	 * @throws Exception
	 */
	public static void multipleDeleteDIO() throws Exception {
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_CHECKBOX_1_ID);
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_CHECKBOX_2_ID);
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DELETE_XPATH);
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_POPUP_YESOPTION_XPATH);
	}
	
	/**
	 * This method perform the single delete action with one record depending on Dynamic xpath of addDeleteNewDIO method.
	 * @lastModifiedBy Mkhairnar
	 * @throws Exception
	 */
	public static void listDeleteDIO() throws Exception {	
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_PREVIOUS_PAGE_XPATH);
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_LIST_DELETE_XPATH);
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_LIST_DELETE_BUTTON_ID);
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_POPUP_YESOPTION_XPATH);
	}
	
	/**
	 * This method enters the DIO by adding required value in option code field and clicks on the first displayed value.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void searchAndAddDIO() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONCODESEARCHBOX_NAME);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONCODESEARCHBOX_NAME);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONCODESEARCHBOX_NAME);
		if(Objects.isNull(DbConnector.getDesieredDIOId(CommonPage.getTestData("DioOptCode")))) {
			OrderingRestAPI.createDIO(CommonPage.getTestData("DioOptCode"));
		}
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONCODESEARCHBOX_NAME);
		System.out.println("Option code: " + CommonPage.getTestData("DioOptCode"));
		CommonPage.enterTextToInputField(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONCODESEARCHBOX_NAME, CommonPage.getTestData("DioOptCode"));
		OrderingVehiclePage.selectEnteredDio(CommonPage.getTestData("DioOptCode"));
	}
	
	/**
	 * This method is used to select entered dio from the suggested list.
	 * @lastModifiedBy shisingh
	 * @param optionCode
	 * @throws Exception
	 */
	public static void selectEnteredDio(String optionCode) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_SEARCHDIO_SUGGESTION_SECTION_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_SEARCHDIO_SUGGESTION_SECTION_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_SEARCHDIO_SUGGESTION_SECTION_CSS);
		List<WebElement> dioSuggestions = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_SEARCHDIO_SUGGESTION_CSS);
		if(dioSuggestions.get(0).getText().trim().equals("No results found!")) {
			throw new OrderingErrorOccured(optionCode+" dio is not present.");		
		} else {
			int counter=0;
			List<String> dioSuggestionsText = new ArrayList<>();
			for(WebElement dioSuggestion: dioSuggestions) {
				dioSuggestionsText.add(dioSuggestion.getText());
				if(dioSuggestion.getText().equals(optionCode)) {
					dioSuggestion.click();
					++counter;
					break;
				}
			}
			if(counter==0) {
				throw new OrderingErrorOccured("No dio matched\n"+"Highlighted Texts: "+dioSuggestionsText);
			}
		}		
	}
	
	/**
	 * This method enters the DIO by adding required value in option description field and clicks on the first displayed value.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void searchAndAddDIODescription() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONDESCRIPTIONSEARCHBOX_NAME);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONDESCRIPTIONSEARCHBOX_NAME);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONDESCRIPTIONSEARCHBOX_NAME);
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONDESCRIPTIONSEARCHBOX_NAME);
		System.out.println("Option description: " + CommonPage.getTestData("DioOptDescription"));
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONDESCRIPTIONSEARCHBOX_NAME, CommonPage.getTestData("DioOptDescription"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONCODE_FIRSTSEARCHRESULT_CLASS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONCODE_FIRSTSEARCHRESULT_CLASS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONCODE_FIRSTSEARCHRESULT_CLASS);
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIOSECTION_OPTIONCODE_FIRSTSEARCHRESULT_CLASS);
	}
	
	/**
	 * This method verify atleast single DIO is present.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyDioNotEmpty() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).withMessage("Atleast single DIO should be present").until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_TABLE_NOT_EMPTY_CSS.getValue()), 0));
	}

	/**
	 * This method adds upfit information based on the value provide under UpfitAddMethod in the test data file.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void addUpfitInformation() throws Exception {
		if(!Objects.isNull(CommonPage.getTestData("UpfitAddMethod"))) {
			String[] addUpfitMethods = CommonPage.getTestData("UpfitAddMethod").split("\\|");
			for(String addUpfitMethod:addUpfitMethods) {
				OrderingVehiclePage.selectUpfitTab();
				OrderingVehiclePage.waitForUpfitTab();
				OrderingVehiclePage.addUpfitMethod(addUpfitMethod);
			}
			OrderingVehiclePage.selectRandomMandatoryUpfitItems();
		}
	}
	
	/**
	 * This method adds upfit information based on the value provide.
	 * Upfit Specification->Add upfit using upfit specification code
	 * Upfit Purchase Order->Add upfit purchase order using upfit purchase order data from test data file
	 * @lastModifiedBy djawale
	 * @param addupfitMethod
	 * @throws Exception
	 */
	public static void addUpfitMethod(String addupfitMethod) throws Exception {
		switch(addupfitMethod) {
			case "Associated Upfit":
				OrderingVehiclePage.verifyAssociatedUpfit();
				break;
			case "Adhoc Upfit":
				OrderingVehiclePage.addAdhocUpfit();
				OrderingVehiclePage.verifyAdhocUpfit();
				OrderingVehiclePage.deleteAdhocUpfit();
				OrderingVehiclePage.undoDeleteAdhocUpfit();
				break;
			case "Line Item Upfit":
				OrderingVehiclePage.addLineItemUpfit();
				//OrderingVehiclePage.verifyLineItemUpfitAdded();
				OrderingVehiclePage.deleteLineItemUpfit();
				OrderingVehiclePage.undoDeleteLineItemUpfit();
				break;
			default: throw new InvalidSwitchCaseException(addupfitMethod + " is a wrong dealer upfit information method");
		}
	}
	
	/**
	 * This method clicks all the first item of associated and adhoc upfits
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectRandomMandatoryUpfitItems() throws Exception {
		List<WebElement> addedUpfits = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_ALL_ADDED_UPFITS_CSS);
		if(!(addedUpfits.isEmpty())) {
			for(WebElement addedUpfit: addedUpfits) {
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(addedUpfit));
				((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", addedUpfit.findElement(By.xpath(".//div[@class='panel-heading']")));
				addedUpfit.click();
				System.out.println("Selected upfit: " + addedUpfit.findElement(By.xpath(".//div[@class='panel-heading']")).getText());
				List<WebElement> tables = addedUpfit.findElements(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_ADDED_UPFIT_ITEM_TABLES_XPATH.getValue()));
				for(WebElement table: tables) {
					((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", table);
					table.isDisplayed();
					new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(table));
					List<WebElement> tableRows = table.findElements(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_ADDED_UPFIT_ITEM_TABLE_ROWS_XPATH.getValue()));
					for(WebElement tableRow: tableRows) {
						new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(tableRow));
						WebElement checkOrRadioElement = tableRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_ADDED_UPFIT_TABLE_ROW_CHECK_RADIO_XPATH.getValue()));
						checkOrRadioElement.isDisplayed();
						new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(checkOrRadioElement));
						checkOrRadioElement.click();
						String code = tableRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_ADDED_UPFIT_TABLE_ROW_DATA_CODE_COLUMNS_XPATH.getValue())).getText();
						String category = tableRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_ADDED_UPFIT_TABLE_ROW_DATA_CATEGORY_COLUMNS_XPATH.getValue())).getText();
						String descritption = tableRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_ADDED_UPFIT_TABLE_ROW_DATA_DESCRIPTION_COLUMNS_XPATH.getValue())).getText();
						String extendedDescription = tableRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_ADDED_UPFIT_TABLE_ROW_DATA_EXTENDED_DESCRIPTION_COLUMNS_XPATH.getValue())).getText();
						String quantity = tableRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_ADDED_UPFIT_TABLE_ROW_DATA_QUANTITY_COLUMNS_XPATH.getValue())).getText();
						String optionPrice = tableRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_ADDED_UPFIT_TABLE_ROW_DATA_OPTION_PRICE_COLUMNS_XPATH.getValue())).getText();
						String estimatedCost = tableRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_ADDED_UPFIT_TABLE_ROW_DATA_ESTIMATED_COST_COLUMNS_XPATH.getValue())).getText();
						System.out.println("Upfit Code: " + code);
						System.out.println("Upfit Category: " + category);
						System.out.println("Upfit Description: " + descritption);
						System.out.println("Upfit Extended Description: " + extendedDescription);
						System.out.println("Upfit Quantity: " + quantity);
						System.out.println("Upfit Option Price: " + optionPrice);
						System.out.println("Upfit Estimated Cost: " + estimatedCost);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * This method clicks on the upfit information section if the upfit addition type is mentioned in the test data file.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectUpfitTab() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_INFO_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_INFO_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_INFO_CSS);
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_INFO_CSS);
	}
	
	/**
	 * This method waits for upfit information to be loaded.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForUpfitTab() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ID);
	}
	
	/**
	 * This method adds adhoc upfit information using upfit specification search field and entering data from test data file.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void addAdhocUpfit() throws Exception {
//		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_SEARCH_INFO_ID);
//		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_SEARCH_INFO_ID);
//		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_SEARCH_INFO_ID);
//		System.out.println("Adhoc Upfit: " + CommonPage.getTestData("UpfitName"));
//		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_SEARCH_INFO_ID, CommonPage.getTestData("UpfitName"));
//		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_SEARCH_FIRST_RESULT_INFO_CSS);
//		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_SEARCH_FIRST_RESULT_INFO_CSS);
//		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_SEARCH_FIRST_RESULT_INFO_CSS);
//		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_SEARCH_FIRST_RESULT_INFO_CSS);
//		OrderingCommonPage.checkAlertPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ID);
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ID);
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_SUPPLIER_NAME_ID);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_SUPPLIER_NAME_ID, CommonPage.getTestData("UpfitAdhocName"));
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_SUPPLIER_QUOTE_ID);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_SUPPLIER_QUOTE_ID, CommonPage.getTestData("SupplierQuote"));
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ADDRESS_ID);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ADDRESS_ID, CommonPage.getTestData("UpfitAdhocAddress"));
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ZIP_ID);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ZIP_ID, CommonPage.getTestData("UpfitAdhocZipcode"));
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_AUTO_FILL_ID);	
        OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method verify atleast single associated upfit is present.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyAssociatedUpfit() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).withMessage("Atleast single associated upfit should be present").until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_AVAILABLE_ASSOCIATED_UPFIT_PANELS_DIV_XPATH.getValue()), 0));
	}
	
	/**
	 * This method verify atleast single adhoc upfit is present.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyAdhocUpfit() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).withMessage("Atleast single adhoc upfit should be present").until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_AVAILABLE_UPFIT_PANELS_DIV_XPATH.getValue()), 0));
	}
	
	/**
	 * This method enters line item upfit details.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void addLineItemUpfit() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ID);
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ID);
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_SUPPLIER_NAME_ID);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_SUPPLIER_NAME_ID, CommonPage.getTestData("UpfitAdhocName"));
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_SUPPLIER_QUOTE_ID);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_SUPPLIER_QUOTE_ID, CommonPage.getTestData("SupplierQuote"));
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ADDRESS_ID);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ADDRESS_ID, CommonPage.getTestData("UpfitAdhocAddress"));
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ZIP_ID);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_ZIP_ID, CommonPage.getTestData("UpfitAdhocZipcode"));
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADD_UPFIT_REQUEST_AUTO_FILL_ID);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADDUPFITPO_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADDUPFITPO_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADDUPFITPO_ID);
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADDUPFITPO_ID);
		System.out.println("Upfit line item description: " + CommonPage.getTestData("UpfitLineItemDesc"));
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_LINEITEM_DESC_NAME);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_LINEITEM_DESC_NAME, CommonPage.getTestData("UpfitLineItemDesc"));
		System.out.println("Upfit line item option price: " + CommonPage.getTestData("UpfitLineItemOptPrice"));
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_LINEITEM_OPTION_PRICE_NAME);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_LINEITEM_OPTION_PRICE_NAME, CommonPage.getTestData("UpfitLineItemOptPrice"));
	}
	
	/**
	 * This method verify atleast single line item upfit is present.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyLineItemUpfitAdded() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), 5).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingVehiclePageEnum.ORDERING_VEHICLE_UPFIT_TAB_ADHOCITEMPIECESECTION_ID.getValue()), 1));
	}
	
	
	public static void clickChangeModel() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_CHANGE_MAKE_XPATH);
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_CHANGE_MAKE_XPATH);
	}

	public static void getDetailsFromVehiclePage(WebDriver driver) throws Exception {
		OrderingVehiclePage.setVehicleInformation(driver);
		OrderingVehiclePage.setDIOInformation(driver);
		OrderingVehiclePage.setAvailableUpfitSpecificationInformation(driver);
	}

	public static void setVehicleInformation(WebDriver driver) throws Exception {		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		CommonPage.getElementOrderObject().getVehicleTabObject().getVehicleInformationSectionObject();
		List<WebElement> fleetSpecSections = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_TABLE_SECTIONS_XPATH);
		boolean fleetSpecCheckStatus;
		String fleetSpecCheckedCode;
		String fleetSpecCheckedDescription;
		String fleetSpecCheckedInvoice;
		String fleetSpecCheckedMSRP;		
		for(WebElement fleetSpecSection: fleetSpecSections) {
			List<WebElement>fleetSpecRows =  fleetSpecSection.findElements(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_TABLE_ROWS_XPATH.getValue()));
			for(WebElement fleetSpecRow: fleetSpecRows) {
				fleetSpecCheckStatus =  (boolean)js.executeScript("return arguments[0].checked", fleetSpecRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_CHECKED_RADIO_BUTTON_XPATH.getValue())));
				if(fleetSpecCheckStatus) {
					fleetSpecCheckedCode = (String)js.executeScript("return arguments[0].textContent", fleetSpecRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_CODE_LABEL_XPATH.getValue())));
					fleetSpecCheckedDescription = (String)js.executeScript("return arguments[0].textContent", fleetSpecRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_DESCRIPTION_LABEL_XPATH.getValue())));
					fleetSpecCheckedInvoice = (String)js.executeScript("return arguments[0].textContent", fleetSpecRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_INVOICE_LABEL_XPATH.getValue())));
					fleetSpecCheckedMSRP = (String)js.executeScript("return arguments[0].textContent", fleetSpecRow.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_INFORMATION_FLEET_SPEC_MSRP_LABEL_XPATH.getValue())));
					CommonPage.getElementOrderObject().getVehicleTabObject().getVehicleInformationSectionObject().addFleetSpec(fleetSpecCheckedCode.trim(), fleetSpecCheckedDescription.trim().split("\n")[0].trim(), fleetSpecCheckedInvoice.trim(), fleetSpecCheckedMSRP.trim());
				}				
			}			
		}
	}
	
	public static void setDIOInformation(WebDriver driver) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		CommonPage.getElementOrderObject().getVehicleTabObject().getDealerInstalledOptionsSectionObject();
		List<WebElement> dioSelections = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_TABLE_ROWS_XPATH);
		String quantity;
		String optionCode;
		String description;
		String priceNotToExceed;
		boolean postProductionOption;
		if(!(dioSelections.size()<=1)) {
			for(WebElement dioSelection: dioSelections) {
				quantity = (String)js.executeScript("return arguments[0].value", dioSelection.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_QUANTITY_TEXTBOX_XPATH.getValue())));
				optionCode = (String)js.executeScript("return arguments[0].value", dioSelection.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_OPTION_CODE_TEXTBOX_XPATH.getValue())));
				description = (String)js.executeScript("return arguments[0].value", dioSelection.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_DESCRIPTION_TEXTBOX_XPATH.getValue())));
				priceNotToExceed = (String)js.executeScript("return arguments[0].value", dioSelection.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_PRICE_NOT_TO_EXCEED_TEXTBOX_XPATH.getValue())));
				postProductionOption = (boolean)js.executeScript("return arguments[0].checked", dioSelection.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_DIO_POST_PRODUCTION_OPTION_CHECKBOX_XPATH.getValue())));
				CommonPage.getElementOrderObject().getVehicleTabObject().getDealerInstalledOptionsSectionObject().addDIO(quantity, optionCode, description, priceNotToExceed, postProductionOption);
			}
		}
	}
	
	public static void setAvailableUpfitSpecificationInformation(WebDriver driver) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		CommonPage.getElementOrderObject().getVehicleTabObject().getUpfitInformationSectionObject().newAvailableUpfitSpecification();
		List<WebElement> availableUpfitSpecifications = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_AVAILABLE_UPFIT_PANELS_DIV_XPATH);
		String upfitSpecificationName;
		for(WebElement availableUpfitSpecification: availableUpfitSpecifications) {
			upfitSpecificationName = (String)js.executeScript("return arguments[0].value", availableUpfitSpecification.findElement(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_AVAILABLE_UPFIT_PANEL_HEADING_DIV_XPATH.getValue())));
			CommonPage.getElementOrderObject().getVehicleTabObject().getUpfitInformationSectionObject().newAvailableUpfitSpecification().setUpfitSpecificationName(upfitSpecificationName);
		}
	}
	
	/**
	 * This method validates default conditions on Vehicle page.
	 * Ex. For stock and dealer order only we will get VIN radio button.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void validateVehiclePageDefaultConditions() throws Exception{
		JavascriptExecutor js = (JavascriptExecutor)WebDriverAccess.getDriver();		
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_RADIO_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_RADIO_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_RADIO_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_RADIO_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_RADIO_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_RADIO_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_RADIO_ID);
		if((CommonPage.getTestData("OrderType").equals("Stock")&&CommonPage.getTestData("WhoWillLocateVehicle").equals("Client"))||
				(CommonPage.getTestData("OrderType").equals("Dealer"))) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_RADIO_ID);
			BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_RADIO_ID);
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_BOX_ID);
			BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_BOX_ID);
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_BUTTON_ID);
			BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_SEARCH_BUTTON_ID);
			Assert.assertTrue((boolean)js.executeScript("return arguments[0].checked", BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_VIN_RADIO_ID)), "Built From VIN option should be selected by default for Stock/Dealer Order");
		} else {
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
			BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
			BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
			Assert.assertTrue((boolean)js.executeScript("return arguments[0].checked", BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_RADIO_ID)), "Select Fleet Specfication option should be selected by default for Factory Order");
		}
		
	}
	
	/**
	 * This method validates user is able to change the selection
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void changeVehicleSelection() throws Exception{
		if((Objects.isNull(CommonPage.getTestData("ChangeVehicleSelection")))?false:(CommonPage.getTestData("ChangeVehicleSelection").equals("Y")?true:false)){
			OrderingVehiclePage.clickChangeModel();
			OrderingCommonPage.checkAlertPopUp();
			OrderingVehiclePage.waitForVehiclePage();
			OrderingVehiclePage.selectVehicleSelectionType();
			OrderingCommonPage.checkAlertPopUp();
			OrderingVehiclePage.waitForVehicleInformationPage();
			OrderingVehiclePage.selectInteriorExteriorColour();
			OrderingVehiclePage.addDealerInstalledOptions();
			OrderingVehiclePage.addUpfitInformation();
			OrderingVehiclePage.getDetailsFromVehiclePage(WebDriverAccess.getDriver());
		}
	}
	
	/**
	 * This method edit the DIO selected
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void editDIO() throws Exception {
		if((Objects.isNull(CommonPage.getTestData("VerifyEditDIO")))?false:(CommonPage.getTestData("VerifyEditDIO").equals("Y")?true:false)){
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICEL_DIO_TABLE_XPATH);
			WebElement mytable= BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICEL_DIO_TABLE_XPATH);
			List<WebElement> rows_table= mytable.findElements(By.tagName("tr"));
			for(int i=rows_table.size()-1;i>=0;) {
				rows_table.get(i).findElement(By.name("quantity")).clear();
				System.out.println("Quantity: " + CommonPage.randomNumberInRange(10));
				rows_table.get(i).findElement(By.name("quantity")).sendKeys(String.valueOf(CommonPage.randomNumberInRange(10)));
				rows_table.get(i).findElement(By.name("optionCode")).clear();
				System.out.println("Option code: " + CommonPage.randomAlphaNumericString());
				rows_table.get(i).findElement(By.name("optionCode")).sendKeys(CommonPage.randomAlphaNumericString());
				System.out.println("Description: " + CommonPage.randomAlphaNumericString());
				rows_table.get(i).findElement(By.name("optionDesc")).clear();
				rows_table.get(i).findElement(By.name("optionDesc")).sendKeys(CommonPage.randomAlphaNumericString());
				System.out.println("Price: " + CommonPage.randomNumberInRange(100));
				rows_table.get(i).findElement(By.name("maxPriceAmt")).clear();
				rows_table.get(i).findElement(By.name("maxPriceAmt")).sendKeys(String.valueOf(CommonPage.randomNumberInRange(100)));
				break;
			}		
		}
	}
	
	/**
	 * This method deletes recently added adhoc upfit item and validates undo button and clicks on it
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void deleteAdhocUpfit() throws Exception {
		if((Objects.isNull(CommonPage.getTestData("VerifyDeleteUpfit")))?false:(CommonPage.getTestData("VerifyDeleteUpfit").equals("Y")?true:false)){
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DELETE_UPFIT_XPATH);
			BrowserVerify.verifyElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DELETE_UPFIT_XPATH);
			BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_DELETE_UPFIT_XPATH);
		}
	}
	
	/**
	 * This method undo the delete action performed on recently added adhoc upfit item
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void undoDeleteAdhocUpfit() throws Exception {
		if((Objects.isNull(CommonPage.getTestData("VerifyUndoDeleteUpfit")))?false:(CommonPage.getTestData("VerifyUndoDeleteUpfit").equals("Y")?true:false)){
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_UNDO_DELETE_UPFIT_XPATH);
			BrowserVerify.verifyElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_UNDO_DELETE_UPFIT_XPATH);
			BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_UNDO_DELETE_UPFIT_XPATH);	
		}
	}
	
	/**
	 * This method deletes recently added line item upfit and validates undo button and clicks on it
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void deleteLineItemUpfit() throws Exception {
		if((Objects.isNull(CommonPage.getTestData("VerifyDeleteUpfit")))?false:(CommonPage.getTestData("VerifyDeleteUpfit").equals("Y")?true:false)){
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DELETE_LINE_ITEM_UPFIT_XPATH);
			BrowserVerify.verifyElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_DELETE_LINE_ITEM_UPFIT_XPATH);
			CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_DELETE_LINE_ITEM_UPFIT_XPATH);
		}
	}
	
	/**
	 * This method undo delete performed on recently added line item upfit
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void undoDeleteLineItemUpfit() throws Exception {
		if((Objects.isNull(CommonPage.getTestData("VerifyUndoDeleteUpfit")))?false:(CommonPage.getTestData("VerifyUndoDeleteUpfit").equals("Y")?true:false)){
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_UNDO_DELETE_LINE_ITEM_UPFIT_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_UNDO_DELETE_LINE_ITEM_UPFIT_XPATH);
		CommonPage.clickElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_UNDO_DELETE_LINE_ITEM_UPFIT_XPATH);
		}
	}
	
	
	
	/**
	 * This method search fleet not associated to order logger with full text
	 * @lastModifiedBy sagrawal
	 * @param fleetAssociatedToOrderLogger
	 * @throws Exception
	 */
	public static void verifyFleetNotAssociatedToOrderLogger(String fleetNotAssociatedToOrderLogger) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		System.out.println("Fleet spec search: " + CommonPage.getTestData("CustomColumn1"));
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS, CommonPage.getTestData("CustomColumn1"));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_NO_RECORD_FOUND_XPATH.getValue()), 1));
		Assert.assertEquals(BrowserAction.getElementText(OrderingVehiclePageEnum.ORDERING_VEHICLE_NO_RECORD_FOUND_XPATH), "No matching records found", "Record is present");
	}
	
	/**
	 * This method search fleet not associated to order logger with partial text
	 * @lastModifiedBy sagrawal
	 * @param fleetNotAssociatedToOrderLogger
	 * @throws Exception
	 */
	public static void verifyFleetNotAssociatedToOrderLoggerWithParialText(String fleetNotAssociatedToOrderLogger) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		String nonAssociatedFleet=CommonPage.getTestData("CustomColumn1");
		String nonAssociatedFleetPartialText=nonAssociatedFleet.substring(0, 4);
		System.out.println("Fleet spec search: " +nonAssociatedFleetPartialText);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS, nonAssociatedFleetPartialText);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_NO_RECORD_FOUND_XPATH.getValue()), 1));
		Assert.assertEquals(BrowserAction.getElementText(OrderingVehiclePageEnum.ORDERING_VEHICLE_NO_RECORD_FOUND_XPATH), "No matching records found", "Record is present");
	}
	
	/**
	 * This method search fleet associated to order logger with Full text
	 * @lastModifiedBy sagrawal
	 * @param fleetAssociatedToOrderLogger
	 * @throws Exception
	 */
	public static void verifyFleetAssociatedToOrderLogger(String fleetAssociatedToOrderLogger) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		String AssociatedFleet=CommonPage.getTestData("CustomColumn2");
		System.out.println("Associated Fleet spec search: " + AssociatedFleet);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS, AssociatedFleet);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_FIRST_RESULT_CSS.getValue()), 1));
		CommonPage.assertLabelHighlight(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "2"))), "Client Approved", "Fleet status not matched");
	}

	/**
	 * This method search fleet associated to order logger with partial text
	 * @lastModifiedBy sagrawal
	 * @param fleetAssociatedToOrderLogger
	 * @throws Exception
	 */
	public static void verifyFleetAssociatedToOrderLoggerWithPartialText(String fleetAssociatedToOrderLogger) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS);
		System.out.println("Associated Fleet spec search: " + CommonPage.getTestData("CustomColumn2"));
		String AssociatedFleet=CommonPage.getTestData("CustomColumn2");
		String AssociatedFleetPartialText=AssociatedFleet.substring(0, 4);
		System.out.println("Associated Fleet spec search: " + AssociatedFleetPartialText);
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_SEARCH_CSS, AssociatedFleetPartialText);
	//	new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue()), 1));
		CommonPage.assertLabelHighlight(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "2"))), "Client Approved", "Fleet status not matched");
		CommonPage.getElementOrderObject().getVehicleTabObject().setYear(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "7"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setMake(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "8"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setModel(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "9"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setTrim(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "10"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setModelCode(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "11"))).getText().trim());
		CommonPage.clickHighlight(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehiclePageEnum.ORDERING_VEHICLE_FIRST_SEARCHED_FLEET_SPEC_SECTION_XPATH.getValue().replaceAll("position", "2"))));
	}
	
	/**
	 * This method enter vehicle details skipping get vehicle details and other optional validations.
	 * @lastModifiedBy Hector_Jimenez
	 * @throws Exception
	 */
	public static void enterDetailsVehicleDataNoGetDetails() throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehiclePage.validateVehiclePageDefaultConditions();
		OrderingVehiclePage.selectVehicleSelectionType();
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehiclePage.waitForVehicleInformationPage();
		OrderingVehiclePage.selectInteriorExteriorColour();
		OrderingVehiclePage.addDealerInstalledOptions();
		OrderingVehiclePage.addUpfitInformation();
	}
	
	/*
	 * This method selects exterior color
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void selectExteriorColor() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_EXTERIOR_COLOR_XPATH);
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_EXTERIOR_COLOR_XPATH);
	}

	/**
	 * This method selects confirms selected color
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void confirmColor() throws Exception {
		try {
			BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_COLOR_ACCEPT_CSS);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("NO CONFIRMATION POP UP FOR COLOR");
		}
	}

	/**
	 * This method selects interior color
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void selectInteriorColor() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_INTERIOR_SELCTOR_COLOR_ID);
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_INTERIOR_SELCTOR_COLOR_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_INTERIOR_COLOR_XPATH);
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_FLEET_SPEC_INTERIOR_COLOR_XPATH);
	}

	/**
	 * This method selects build from scratch radio
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void selectBuildFromScratchRadio() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_RADIO_ID);
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_RADIO_ID);
	}
	
	/**
	 * This method verify if the build from scratch table is displayed
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void waitForBuildFromScratchTable() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILDFROMSCRATCH_PICTURE_XPATH);
	}
	
	/**
	 * This method click on first vehicle from Build from scratch Table.
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void selectFirstVehicleFromBuildFromScratchTable() throws Exception {
		System.out.println("Select Make: " + CommonPage.getTestData("Make"));
		BrowserAccess.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MAKE_TEXT_BOX_XPATH).sendKeys(CommonPage.getTestData("Make"));
		KeyPressEvents.pressEnter(1);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_FIRST_VEHICLE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_FIRST_VEHICLE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_FIRST_VEHICLE_XPATH);
		try {
			BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_FIRST_VEHICLE_XPATH);	
		} catch (StaleElementReferenceException e) {
			System.out.println(e);
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_FIRST_VEHICLE_XPATH);
			BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_FIRST_VEHICLE_XPATH);
		}
	}
	
	public static void selectPriceAndConfigRadio() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_RADIO_ID);
		BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_RADIO_ID);
	}
	
	/**
	 * This method is used to select edit mode when spec is locked.
	 * @lastModifiedBy bshah
	 */
	public static void checklockingmessagevisible() throws Exception{
		List<WebElement> lockmessage = BrowserAction.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_CHANGE_LOCK_XPATH);
		if(lockmessage.size()==1) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_CHANGE_LOCK_EDITMODE_CONTINUE_BUTTON_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_CHANGE_LOCK_EDITMODE_CONTINUE_BUTTON_XPATH);
			BrowserAction.click(OrderingVehiclePageEnum.ORDERING_VEHICLE_CHANGE_LOCK_EDITMODE_CONTINUE_BUTTON_XPATH);
		}		
	}
	
	/**
	 * This method verify the header of vehicle spec details
	 * @lastModifiedBy lpadaliya
	 */
	public static void verifyVehicleSpecDetailsHeader() throws Exception{
		List<String> headerItems = new ArrayList<>(); 
		for(WebElement ele :BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_TABLE_HEADER_XPATH)) {
			headerItems.add(ele.getText().replaceAll("[\\t\\n\\r]+"," "));
		}
		List<String> expectedHeaderItems=new ArrayList<String>();
		expectedHeaderItems.add("Select");
		expectedHeaderItems.add("Option Type");
		expectedHeaderItems.add("Code");
		expectedHeaderItems.add("Description");
		expectedHeaderItems.add("Adjustment Type");
		expectedHeaderItems.add("Driver Price");
		expectedHeaderItems.add("Invoice");
		expectedHeaderItems.add("MSRP");
		Assert.assertTrue(expectedHeaderItems.containsAll(headerItems));
	}
	
	/**
	 * This method verify CPO and DPO option type is enabled on spec section
	 * @lastModifiedBy lpadaliya
	 */
	public static void verifyOptionTypeEnabledWithCheckBoxSelected() throws Exception{
		int index=0;
		for(WebElement ele:BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_CHECKBOXES_XPATH)) {
			String heading=ele.findElement(By.xpath(".//parent::li//parent::ul//parent::div//parent::li//parent::ul//parent::div/preceding-sibling::div[@class='col-sm-12 config_section_headers']")).getText();
			if(!heading.trim().contentEquals("INCENTIVES") && !heading.trim().contentEquals("INTERIOR COLORS") && !heading.trim().contentEquals("EXTERIOR COLORS")) {
				if(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_CHECKBOXES_XPATH).get(index).isSelected()) {
					Assert.assertTrue(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index).isEnabled());
				} else {
					CommonPage.jsGreenBorderHighlighter(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index));
					Assert.assertFalse(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index).isEnabled());
				}
			} else {
				Assert.assertFalse(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index).isEnabled());
			}
			index++;
		}
	}
	
	/**
	 * This method verify 3 option types and on selecting Driver paid, Adjustment type and driver price is enabled
	 * @lastModifiedBy lpadaliya
	 */
	public static void verifyOptionTypeAdjustmentTypeAndDriverPrice() throws Exception{
		int index=0;
		List<String> optionDropdown = new ArrayList<>();
		List<String> expectedOptionDropdown=new ArrayList<String>();
		expectedOptionDropdown.add("");
		expectedOptionDropdown.add("Driver Paid");
		expectedOptionDropdown.add("Client Paid");
		for(WebElement ele:BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_CHECKBOXES_XPATH)) {	
			String heading=ele.findElement(By.xpath(".//parent::li//parent::ul//parent::div//parent::li//parent::ul//parent::div/preceding-sibling::div[@class='col-sm-12 config_section_headers']")).getText();
			if(!heading.trim().contentEquals("INCENTIVES") && !heading.trim().contentEquals("INTERIOR COLORS") && !heading.trim().contentEquals("EXTERIOR COLORS")) {
				if(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_CHECKBOXES_XPATH).get(index).isSelected()) {
					Select sel=new Select(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index));
					optionDropdown.clear();
					for(int i=0;i<3;i++) {			
						optionDropdown.add(sel.getOptions().get(i).getText());
					}
					Assert.assertTrue(expectedOptionDropdown.containsAll(optionDropdown));
					((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index));
					new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(ElementNotInteractableException.class).until(ExpectedConditions.elementToBeClickable(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index)));
					sel.selectByVisibleText("Driver Paid");
					Assert.assertTrue(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_ADJ_TYPE_XPATH).get(index).isEnabled());
					Assert.assertTrue(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_ADJ_TYPE_VALUE_XPATH).get(index).isEnabled());
				} else {
					Assert.assertFalse(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index).isEnabled());
				}
			} else {
				Assert.assertFalse(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index).isEnabled());
			}
			index++;
		}
	}
	
	/**
	 * This method verify full invoice price is populated in Driver price
	 * @lastModifiedBy lpadaliya
	 */
	public static void verifyFullInvoicePriceIsPopulatedInDriverPrice() throws Exception{
		int index=0;
		for(WebElement ele:BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_CHECKBOXES_XPATH)) {
			String heading=ele.findElement(By.xpath(".//parent::li//parent::ul//parent::div//parent::li//parent::ul//parent::div/preceding-sibling::div[@class='col-sm-12 config_section_headers']")).getText();
			if(!heading.trim().contentEquals("INCENTIVES") && !heading.trim().contentEquals("INTERIOR COLORS") && !heading.trim().contentEquals("EXTERIOR COLORS")) {
				if(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_CHECKBOXES_XPATH).get(index).isSelected()) {
					Select sel=new Select(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index));
					if(!sel.getFirstSelectedOption().getText().contentEquals("Driver Paid")) {
						sel.selectByVisibleText("Driver Paid");
					}
					System.out.println((BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_INVOICE_PRICE_XPATH).get(index).getText().trim()
							+"  " +(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_DRIVER_PRICE_XPATH).get(index).getText().trim())));
					Assert.assertTrue(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_INVOICE_PRICE_XPATH).get(index).getText().replace(",", "").trim()
							.contentEquals(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_DRIVER_PRICE_XPATH).get(index).getText().replace(",", "").trim())||
							BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_INVOICE_PRICE_XPATH).get(index).getText().replace(",", "").trim().contentEquals("Included"));
				} else {
					Assert.assertTrue(!BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index).isEnabled());
				}
			} else {
				Assert.assertTrue(!BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index).isEnabled());
			}
			index++;
		}
	}
	
	/**
	 * This method verify full invoice price is populated in Driver price
	 * @lastModifiedBy lpadaliya
	 */
	public static void verifyDriverPriceAdjustment() throws Exception{
		int index=0;
		Select sel;
		for(WebElement ele:BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_CHECKBOXES_XPATH)) {
			String heading=ele.findElement(By.xpath(".//parent::li//parent::ul//parent::div//parent::li//parent::ul//parent::div/preceding-sibling::div[@class='col-sm-12 config_section_headers']")).getText();
			if(!heading.trim().contentEquals("INCENTIVES") && !heading.trim().contentEquals("INTERIOR COLORS") && !heading.trim().contentEquals("EXTERIOR COLORS")) {
				if(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_CHECKBOXES_XPATH).get(index).isSelected()) {
					sel=new Select(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index));
					if(!sel.getFirstSelectedOption().getText().contentEquals("Driver Paid")) {
						sel.selectByVisibleText("Driver Paid");
					}
					sel=new Select(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_ADJ_TYPE_XPATH).get(index));
					((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_ADJ_TYPE_XPATH).get(index));
					new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(ElementNotInteractableException.class).until(ExpectedConditions.elementToBeClickable(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_ADJ_TYPE_XPATH).get(index)));
					sel.selectByVisibleText("Percentage (%)");
					BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_ADJ_TYPE_VALUE_XPATH).get(index).clear();
					BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_ADJ_TYPE_VALUE_XPATH).get(index).sendKeys("50");
					if(!BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_INVOICE_PRICE_XPATH).get(index).getText().replace(",", "").trim().contentEquals("Included")) {
						System.out.println(Float.parseFloat(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_INVOICE_PRICE_XPATH).get(index).getText().replace("$", "").trim()));
						System.out.println(Float.parseFloat(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_DRIVER_PRICE_XPATH).get(index).getText().replace("$", "").trim())*2);
						float invoicePrice=Float.parseFloat(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_INVOICE_PRICE_XPATH).get(index).getText().replace("$", "").replace(",", "").trim());
						float twiceOfDriverPrice=Float.parseFloat(BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_DRIVER_PRICE_XPATH).get(index).getText().replace("$", "").replace(",", "").trim())*2;
						Assert.assertTrue(invoicePrice==twiceOfDriverPrice);
					}
				} else {
					Assert.assertTrue(!BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index).isEnabled());
				}
			} else {
				Assert.assertTrue(!BrowserAccess.getElements(OrderingVehiclePageEnum.ORDERING_VEHICLE_OPTION_LIST_OF_SPEC_OPTION_TYPE_XPATH).get(index).isEnabled());
			}
			index++;
		}
	}
	
	/**
	 * This method click clicks on Save and Next button while creating order in FO.
	 * @lastModifiedBy Deepika
	 * @throws Exception
	 */
	
	public static void clickSaveAndNext() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
			BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Application is not able click save and next");
		}
	}
}


	