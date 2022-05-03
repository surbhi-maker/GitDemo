package com.element.fleet.ordering.page;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.ExcelUtil;
import com.element.fleet.ordering.enums.OrderingHomePageEnum;
import com.element.fleet.ordering.enums.OrderingVehicleConfigFleetSpecsPageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.element.fleet.rainbowplus.common.KeyPressEvents;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserAssert;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;

public class OrderingVehicleConfigFleetSpecsPage {
	private static List <String> fleetSpecList = new ArrayList<>();
	
	public static List<String> getPriceConfigSpecList() {
		return fleetSpecList;
	}

	public static void setPriceConfigSpecList(List<String> priceConfigSpecList) {
		OrderingVehicleConfigFleetSpecsPage.fleetSpecList = priceConfigSpecList;
	}
	
	/**
	 * This method waits for the fleet spec page to load.
	 * Note: This same method can be used for Price & Config page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForVehicleConfigFleetSpecsPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_ID);		
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLIENT_NUMBER_TEXTFIELD_ID);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_SEARCH_ID);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_CSS);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_ID);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_YEAR_DROPDOWN_CSS);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_MAKE_ID);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_MODEL_ID);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_MODEL_CODE_ID);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_TRIM_ID);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLEAR_FILTERS_XPATH);
	}
	
	public static void fleetSpecsPageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_ID), "Fleet Specs", "Fleet Specs heading label not matched");
	}
	
	/**
	 * This method verifys that selected client id is by default displayed in the client search field.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyClientIdDisplayedInClientSearchField() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		String clientIdTextField = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLIENT_NUMBER_TEXTFIELD_ID));
		Assert.assertEquals(clientIdTextField.trim(), CommonPage.getTestData("ClientNumber"), "Client id in the search field not matched with selected client field");
	}
	
	/**
	 * This method compares UI provided headings list with user provided heading list
	 * @lastModifiedBy shisingh
	 * @param colName
	 * @throws Exception
	 */
	public static void verifyColumnHeadings() throws Exception {
		System.out.println("User provided column: " + CommonPage.getTestData("CustomColumn1"));
		List<String> userProvidedColName = Arrays.asList(CommonPage.getTestData("CustomColumn1").split("\\|"));
		List<String> uiColName = new ArrayList<String>();
		new WebDriverWait(WebDriverAccess.getDriver(),new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_HEADINGS_CSS.getValue()), userProvidedColName.size()));
		BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_HEADINGS_CSS).stream().forEach(e->uiColName.add(e.getAttribute("innerText")));
		System.out.println("UI provided column: " + uiColName);
		Assert.assertEquals(uiColName, userProvidedColName, "UI heading list not matched with provided heading list");	
	}
	
	/**
	 * This clicks on toggle column button
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnToggleColumnButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_CONFIG_TOGGLE_IMAGE_BUTTON_VALUE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_CONFIG_TOGGLE_IMAGE_BUTTON_VALUE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_CONFIG_TOGGLE_IMAGE_BUTTON_VALUE_CSS);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_CONFIG_TOGGLE_IMAGE_BUTTON_VALUE_CSS);
	}
	
	/**
	 * This method verifies Image column is hidden
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyImageColumnIsNotVisible() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(),10).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICEL_CONFIG_PRICE_CONFIG_IMAGE_COLUMN_XPATH.getValue()), 0));
	}
	
	/**
	 * This method verifies Image column is visible
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyImageColumnIsVisible() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(),10).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICEL_CONFIG_PRICE_CONFIG_IMAGE_COLUMN_XPATH.getValue()), 1));
	}
	
	/**
	 * This method validates the Price and configuration page heading
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void priceAndConfigurationPageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_ID), "Price & Configuration", "Price & Configuration heading label not matched");
	}
	
	/**
	 * This method add new Fleet spec with Work In Progress as status
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void addNewFleetSpec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnAddNewLink();
		OrderingCommonPage.checkAlertPopUp();
		CommonPage.initializeElementOrderObject();
		OrderingVehicleConfigFleetSpecsPage.enterValuesInMakeTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValuesInModelTextBox();
		OrderingVehicleConfigFleetSpecsPage.selectVehicleFromSearchedResult();
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		OrderingVehicleConfigFleetSpecsPage.enterSavePopUpFleetSpecDetails();
		OrderingVehicleConfigFleetSpecsPage.fleetSpecSavePopUpActions("Cancel");
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		OrderingVehicleConfigFleetSpecsPage.enterSavePopUpFleetSpecDetails();
		OrderingVehicleConfigFleetSpecsPage.fleetSpecSavePopUpActions("Accept");
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method searches Fleet Spec.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void searchFleetSpec(String searchSection, String searchvalue) throws Exception {
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue(searchSection, searchvalue);
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifys the newly created Fleet Spec status.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyFleetSpecSingleRowDetails(String columnName, String searchText) throws Exception {
		try {
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_ROW_CSS.getValue()), 1));
			List<WebElement> columnElementList = WebDriverAccess.getDriver().findElements(BrowserAccess.getLocator("_CSS", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_CSS.getValue().replaceAll("number", OrderingVehicleConfigFleetSpecsPage.getColumnPostion(columnName))));
			if(!columnElementList.get(0).getText().trim().equals(searchText)) {
				throw new OrderingErrorOccured(columnName + " does not have " + searchText + " status");
			}
		} catch(StaleElementReferenceException e) {
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_ROW_CSS.getValue()), 1));
			List<WebElement> columnElementList = WebDriverAccess.getDriver().findElements(BrowserAccess.getLocator("_CSS", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_CSS.getValue().replaceAll("number", OrderingVehicleConfigFleetSpecsPage.getColumnPostion(columnName))));
			if(!columnElementList.get(0).getText().trim().equals(searchText)) {
				throw new OrderingErrorOccured(columnName + " does not have " + searchText + " status");
			}
		}
	}
	
	/**
	 * This method clicks on the Add New link.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnAddNewLink() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_ADD_NEW_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_ADD_NEW_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_ADD_NEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_ADD_NEW_BUTTON_XPATH);
	}

	/**
	 * This method waits for Fleet spec page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForViewFleetSpecsPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH);
	}
	
	public static void selectFirstVehicleAfterSearch() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SELECT_VEHICLE_FIRST_SEARCH_CSS)));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SELECT_VEHICLE_FIRST_SEARCH_CSS);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SELECT_VEHICLE_FIRST_SEARCH_CSS);
	}

	/**
	 * This method clicks on Save button on new added Fleet Spec from drop down.
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void clickOnSaveButton() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SPEC_SAVE_DROPDOWN_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SPEC_SAVE_DROPDOWN_ID); 		
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_BUTTON_XPATH); 		
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}

	public static void clickOnExportButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_CONFIG_EXPORT_BUTTON_VALUE_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_CONFIG_EXPORT_BUTTON_VALUE_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	public static void enterFixFleetSpecsNameInSearchBoxAndCickOnSearch() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ID_SEARCH_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ID_SEARCH_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ID_SEARCH_ID);
		BrowserAction.clickandClear(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ID_SEARCH_ID);
		System.out.println("FleetSpec ID: " + CommonPage.getTestData("FleetSpecSearch"));
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ID_SEARCH_ID, CommonPage.getTestData("FleetSpecSearch"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);			
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method clicks checkbox of the first displayed Fleet spec 
	 * @throws Exception
	 */
	public static void selectFirstSearchVehicleForApproval() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ROWS_CSS.getValue()), 1));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SELECT_FIRST_AFTER_SEARCH_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SELECT_FIRST_AFTER_SEARCH_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SELECT_FIRST_AFTER_SEARCH_CSS);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SELECT_FIRST_AFTER_SEARCH_CSS);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		CommonPage.initializeElementOrderObject();
		CommonPage.getElementOrderObject().getVehicleTabObject().setYear(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ROW_COLUMNS_XPATH.getValue().replaceAll("position", "14"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setMake(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ROW_COLUMNS_XPATH.getValue().replaceAll("position", "15"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setModel(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ROW_COLUMNS_XPATH.getValue().replaceAll("position", "16"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setTrim(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ROW_COLUMNS_XPATH.getValue().replaceAll("position", "17"))).getText().trim());
		CommonPage.getElementOrderObject().getVehicleTabObject().setModelCode(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ROW_COLUMNS_XPATH.getValue().replaceAll("position", "18"))).getText().trim());		
	}
	
	/**
	 * This method clicks checkbox of the first displayed Fleet spec 
	 * @throws Exception
	 */
	public static void verifyFleetStatusIsWIP() throws Exception {
		CommonPage.assertLabelHighlight(WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("__XPATH", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ROW_COLUMNS_XPATH.getValue().replaceAll("position", "3")+"/div[@class='js-fleet-status']")), "Work In Progress", "Status of Fleet Spec should be Work In Progress");
	}
	
	/**
	 * This method waits for Element Approve, Client Approve, Archive and Un-Archive.
	 * @throws Exception
	 */
	public static void waitForElementApproveClientApproveArchiveUnarchiveOptions() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.waitForElementApproveOption();
		OrderingVehicleConfigFleetSpecsPage.waitForClientApproveOption();
		OrderingVehicleConfigFleetSpecsPage.waitForArchiveOption();
		OrderingVehicleConfigFleetSpecsPage.waitForUnarchiveOption();
	}
	
	/**
	 * This method waits for Archive and Un-Archive.
	 * @throws Exception
	 */
	public static void waitForArchiveUnarchiveOptions() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.waitForArchiveOption();
		OrderingVehicleConfigFleetSpecsPage.waitForUnarchiveOption();
	}
	
	/**
	 * This method waits for Element Approve button.
	 * @throws Exception
	 */
	public static void waitForElementApproveOption() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ELEMENT_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ELEMENT_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ELEMENT_APPROVE_BUTTON_ID);	
	}
	
	/**
	 * This method waits for Client Approve button.
	 * @throws Exception
	 */
	public static void waitForClientApproveOption() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CLIENT_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CLIENT_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CLIENT_APPROVE_BUTTON_ID);		
	}
	
	/**
	 * This method waits for Archive button.
	 * @throws Exception
	 */
	public static void waitForArchiveOption() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_ARCHIEVE_BTN_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_ARCHIEVE_BTN_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_ARCHIEVE_BTN_ID);		
	}
	
	/**
	 * This method waits for Un-Archive button.
	 * @throws Exception
	 */
	public static void waitForUnarchiveOption() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_UNARCHIEVE_BTN_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_UNARCHIEVE_BTN_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_UNARCHIEVE_BTN_ID);		
	}
	
	public static void clickOnClientApproveButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CLIENT_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CLIENT_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CLIENT_APPROVE_BUTTON_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CLIENT_APPROVE_BUTTON_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}

	public static void clickOnElementApproveButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ELEMENT_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ELEMENT_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ELEMENT_APPROVE_BUTTON_ID);			
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ELEMENT_APPROVE_BUTTON_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}

	public static void changeFleetStatusToUpfitPending() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACTION_LINK_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACTION_LINK_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACTION_LINK_XPATH);			
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACTION_LINK_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_UPFIT_PENDING_STATUS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_UPFIT_PENDING_STATUS_XPATH);
		if(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_UPFIT_PENDING_STATUS_XPATH).getAttribute("class").contains("context-menu-disabled")) {
			System.out.println("Currently the vehicle is in Upfit Pending status. Hence no action needed");
		}else {
			BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_UPFIT_PENDING_STATUS_XPATH);
			OrderingCommonPage.checkAlertPopUp();
		}
	}
	
	public static void clickOnTheFirstFleetRecordIfAvailable() throws Exception {
		List<WebElement> tableRows = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_TABLE_ROW_XPATH);
		if((tableRows.size()==1)&&(tableRows.get(0).getText().contains("There are currently no Fleet Specification Saved for this Client")||tableRows.get(0).getText().contains("There are currently no Price & Configurations Saved for this Client")))
			System.out.println("No element available to click\n"+BrowserAction.getElementText(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_TABLE_ROW_XPATH));
		else {
			tableRows.get(0).click();
			OrderingCommonPage.checkAlertPopUp();
			OrderingVehiclePage.waitForVehicleSectionInformationPage();
			OrderingVehicleConfigFleetSpecsPage.clickCancelOnVehicleConfigurationPage();
		}
	}
	
	public static void clickCancelOnVehicleConfigurationPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_CSS);		
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CONFIRM_CANCEL_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CONFIRM_CANCEL_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CONFIRM_CANCEL_BUTTON_CSS);		
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CONFIRM_CANCEL_BUTTON_CSS);
	}

	/**
	 * This method clicks on the single Action via new 3 dot implementation 
	 * @lastModifiedBy ksharma
	 */
	public static void clickOnFleetSpecAction() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_ACTION_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_ACTION_XPATH);
			BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_ACTION_XPATH);
		} catch (ElementClickInterceptedException e) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_ACTION_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_ACTION_XPATH);
			BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_ACTION_XPATH);
			}
	}
	
	/**
	 * This method wait for all 5 options of Fleet Spec action to be displayed.
	 * Below are the options:
	 * Edit
	 * Convert to Order
	 * Copy Fleet Spec
	 * Upfit Pending
	 * Export to PDF/Print
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForFleetSpecActionOptions() {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_OPTIONS_CSS.getValue()), 5));
	}
	
	/**
	 * This method clicks on the enabled action options of the Fleet Spec.
	 * @lastModifiedBy shisingh
	 * @param option
	 * @throws Exception
	 */
	public static void clickOnFleetSpecActionOptions(String option) throws Exception {
		System.out.println("Action: " + option);
		List<WebElement> actionOptions = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_OPTIONS_CSS);
		WebElement desieredOption = actionOptions.stream().filter(e->e.getText().trim().equals(option)).collect(Collectors.toList()).get(0);
		desieredOption.click();
	}
	
	/**
	 * This method perform Edit of Fleet Spec option.
	 * @lastModifiedBy shisingh
	 * @param option
	 * @throws Exception
	 */
	public static void performEditFleetSpecOption() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecActionOptions("Edit");
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		OrderingVehicleConfigFleetSpecsPage.enterSavePopUpFleetSpecDetails();
		OrderingVehicleConfigFleetSpecsPage.fleetSpecSavePopUpActions("Cancel");
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		OrderingVehicleConfigFleetSpecsPage.enterSavePopUpFleetSpecDetails();
		OrderingVehicleConfigFleetSpecsPage.fleetSpecSavePopUpActions("Accept");
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method perform Edit of Price & Config option.
	 * @lastModifiedBy shisingh
	 * @param option
	 * @throws Exception
	 */
	public static void performEditPriceConfigOption() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecActionOptions("Edit");
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		OrderingVehicleConfigFleetSpecsPage.enterSavePopUpPriceConfigDetails();
		OrderingVehicleConfigFleetSpecsPage.fleetSpecSavePopUpActions("Cancel");
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		OrderingVehicleConfigFleetSpecsPage.enterSavePopUpPriceConfigDetails();
		OrderingVehicleConfigFleetSpecsPage.fleetSpecSavePopUpActions("Accept");
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method perform copy of Fleet Spec action.
	 * @lastModifiedBy shisingh
	 * @param option
	 * @throws Exception
	 */
	public static void performCopyFleetSpecOption() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecActionOptions("Copy Fleet Spec");
		OrderingVehicleConfigFleetSpecsPage.enterCopyFleetSpecsIDNameAndNotes();
		OrderingVehicleConfigFleetSpecsPage.fleetSpecSavePopUpActions("Accept");
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method perform copy of Price & Config action.
	 * @lastModifiedBy shisingh
	 * @param option
	 * @throws Exception
	 */
	public static void performCopyPriceConfigOption() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecActionOptions("Copy Price & Config");
		OrderingVehicleConfigFleetSpecsPage.enterCopyPriceConfigIDNameAndNotes();
		OrderingVehicleConfigFleetSpecsPage.fleetSpecSavePopUpActions("Accept");
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method perform copy of Upfit pending action.
	 * @lastModifiedBy shisingh
	 * @param option
	 * @throws Exception
	 */
	public static void performUpfitPendingOption() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecActionOptions("Upfit Pending");
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehicleConfigFleetSpecsPage.verifyFleetSpecSingleRowDetails("Fleet Specs Status", "Upfit Pending");
	}
	
	/**
	 * This method perform Convert To FleetSpec action.
	 * @lastModifiedBy shisingh
	 * @param option
	 * @throws Throwable 
	 */
	public static void performConvertToFleetSpecOption() throws Throwable {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecActionOptions("Convert To Fleet Spec");
		OrderingVehicleConfigFleetSpecsPage.enterConvertToFleetSpecPopUpPriceConfigDetails();
		PDFReporter.takeExtraScreenshot();
		OrderingVehicleConfigFleetSpecsPage.fleetSpecSavePopUpActions("Accept");
	}
	
	/**
	 * This method perform Export to PDF action.
	 * @lastModifiedBy shisingh
	 * @param className
	 * @throws Exception
	 */
	public static void performFleetSpecExportToPDFOption(String className) throws Exception {
		OrderingSummaryPage.clickExportButton("FleetSpec", className);
	}
	
	/**
	 * This method waits for all the file to be downloaded.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyFleetSpecPDFCompleted() throws Exception {
		WebElement currentPdfCountElement = BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DOWNLOAD_PDF_POP_UP_CURRENT_COUNT_ID);
		String totalPdfCount = BrowserAction.getElementText(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DOWNLOAD_PDF_POP_UP_TOTAL_COUNT_ID);
		try {
			new FluentWait<WebElement>(currentPdfCountElement).pollingEvery(Duration.ofSeconds(1)).withTimeout(Duration.ofSeconds(new Long(CommonPage.getTestData("WaitTime")))).until(ele -> {if(ele.getText().trim().equals(totalPdfCount))return true;else return false;});			
		} catch (TimeoutException e) {
			System.out.println("Current downloaded pdf count does not matches total pdf count " + currentPdfCountElement.getText().trim() + " != " + totalPdfCount);
		}
	}
	
	/**
	 * This method waits for the pop up to be displayed.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForFleetSpecDownloadPDFPopUp() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DOWNLOAD_PDF_POP_UP_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DOWNLOAD_PDF_POP_UP_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DOWNLOAD_PDF_POP_UP_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DOWNLOAD_PDF_POP_UP_CURRENT_COUNT_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DOWNLOAD_PDF_POP_UP_CURRENT_COUNT_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DOWNLOAD_PDF_POP_UP_CURRENT_COUNT_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DOWNLOAD_PDF_POP_UP_TOTAL_COUNT_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DOWNLOAD_PDF_POP_UP_TOTAL_COUNT_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DOWNLOAD_PDF_POP_UP_TOTAL_COUNT_ID);
	}
	
	/**
	 * This method enters Client, Fleet Spec Name and Copy Note in Save pop.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */	
	public static void enterCopyFleetSpecsIDNameAndNotes() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID);
		System.out.println("Client: " + CommonPage.getTestData("ClientNumber"));
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID, CommonPage.getTestData("ClientNumber"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		String fletSpecName = "ATFleetSpec" + CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("CopiedFleetSpecName", fletSpecName);
		System.out.println("Copied Fleet spec name: " + CommonPage.getTestData("CopiedFleetSpecName"));		
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID, fletSpecName);
		String fletSpecNote = "ATFleetSpecNote"+CommonPage.randomAlphaNumericString();
		System.out.println("Copied Fleet spec note: " + fletSpecNote);		
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID, fletSpecNote);
	}
	
	/**
	 * This method enters Client, Fleet Spec Name and Copy Note in Save pop.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */	
	public static void enterCopyPriceConfigIDNameAndNotes() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID);
		System.out.println("Client: " + CommonPage.getTestData("ClientNumber"));
		BrowserAction.clickandClear(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID, CommonPage.getTestData("ClientNumber"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		String priceConfigName = "ATPriceConfig" + CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("CopiedPriceConfigName", priceConfigName);
		System.out.println("Copied Price config name: " + CommonPage.getTestData("CopiedPriceConfigName"));
		BrowserAction.clickandClear(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID, priceConfigName);
		String priceConfigNote = "ATPriceConfigNote"+CommonPage.randomAlphaNumericString();
		System.out.println("Copied Price config note: " + priceConfigNote);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID);
		BrowserAction.clickandClear(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID, priceConfigNote);
	}
	
	/**
	 * This method enters Fleet Name and Fleet note in Save pop.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterSavePopUpFleetSpecDetails() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH);
		String fletSpecName = "ATFleetSpec"+CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("FleetSpecName", fletSpecName);
		System.out.println("Fleet spec name: " + CommonPage.getTestData("FleetSpecName"));
		BrowserAction.clickandClear(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH, CommonPage.getTestData("FleetSpecName"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_COPYNOTE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_COPYNOTE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_COPYNOTE_XPATH);
		String fletSpecNote = "ATFleetSpecNote"+CommonPage.randomAlphaNumericString();
		System.out.println("Fleet spec note: " + fletSpecNote);
		BrowserAction.clickandClear(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_COPYNOTE_XPATH);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_COPYNOTE_XPATH, fletSpecNote);
	}
	
	/**
	 * This method enters Price & Config and Fleet note in Save pop.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterSavePopUpPriceConfigDetails() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH);
		String priceConfigName = "ATPriceConfig"+CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("PriceConfigName", priceConfigName);
		System.out.println("Price & config name: " + CommonPage.getTestData("PriceConfigName"));
		BrowserAction.clickandClear(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH, CommonPage.getTestData("PriceConfigName"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_COPYNOTE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_COPYNOTE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_COPYNOTE_XPATH);
		String priceConfigNote = "ATPriceConfigNote"+CommonPage.randomAlphaNumericString();
		System.out.println("Price & config name: " + priceConfigNote);
		BrowserAction.clickandClear(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_COPYNOTE_XPATH);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_COPYNOTE_XPATH, priceConfigNote);
	}
	
	/**
	 * This method enter Convert to Fleet Spec pop up Price & Config details.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterConvertToFleetSpecPopUpPriceConfigDetails() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID);
		System.out.println("Client: " + CommonPage.getTestData("ClientNumber"));
		js.executeScript("arguments[0].value=''",BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID));
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_COPY_PRICE_AND_CONFIG_CLIENT_NUMBER_ID, CommonPage.getTestData("ClientNumber"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		System.out.println("Price & config name: " + CommonPage.getTestData("PriceConfigName"));
		BrowserAction.clickandClear(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID, CommonPage.getTestData("PriceConfigName"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID);
		String convertfletSpecNote = "ATConvertFleetSpec"+CommonPage.randomAlphaNumericString();
		System.out.println("Copied Fleet spec note: " + convertfletSpecNote);		
		BrowserAction.clickandClear(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_FLEET_SPECS_NOTES_TEXTAREA_ID, convertfletSpecNote);
	}
	
	/**
	 * This method clicks performs Accept or Cancel option on the Fleet Spec save pop up.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void fleetSpecSavePopUpActions(String action) throws Exception {
		switch(action) {
			case "Accept":
				BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_XPATH);
				BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_XPATH);
				break;
			case "Cancel":
				BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_ID);
				BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_ID);
				BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_ID);
				BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_ID);
				break;
			default: throw new InvalidSwitchCaseException(action + " is a invalid fleet spec save pop up action");
		}
	}

	/** 
	* This method enters data in the search field
	* @lastModifiedBy shisingh 
	* @throws Exception 
	*/
	public static void enterInSingleSearchFieldAndVerifyResult(String fieldName, String searchText) throws Exception{
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue(fieldName, searchText);
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingVehicleConfigFleetSpecsPage.verifySeachedAvalableInList(fieldName, searchText);
	}
	
	/** 
	* This method enters data in the search field of Fleet Spec page
	* @lastModifiedBy shisingh 
	* @throws Exception 
	*/
	public static void enterInMultipleSearchFieldAndVerifyResultInFleetSpecPage() throws Exception{
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Fleet Spec Status", CommonPage.getTestData("OrderingStatus"));
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Fleet Spec Id", CommonPage.getTestData("FleetSpecSearch"));
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(),10).until(ExpectedConditions.numberOfElementsToBe(BrowserAccess.getLocator("_CSS", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ROWS_CSS.getValue()), 1));
		OrderingVehicleConfigFleetSpecsPage.verifySeachedAvalableInList("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.verifySeachedAvalableInList("Fleet Spec Status", CommonPage.getTestData("OrderingStatus"));
		OrderingVehicleConfigFleetSpecsPage.verifySeachedAvalableInList("Fleet Spec Id", CommonPage.getTestData("FleetSpecSearch"));
	}
	
	/** 
	* This method enters data in the search field of Price & Config page
	* @lastModifiedBy shisingh 
	* @throws Exception 
	*/
	public static void enterInMultipleSearchFieldAndVerifyResultInPriceConfigPage() throws Exception{
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Price & Config Status", CommonPage.getTestData("OrderingStatus"));
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Price & Config Id", CommonPage.getTestData("PriceAndConfigSearch"));
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(),10).until(ExpectedConditions.numberOfElementsToBe(BrowserAccess.getLocator("_CSS", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ROWS_CSS.getValue()), 1));
		OrderingVehicleConfigFleetSpecsPage.verifySeachedAvalableInList("Client Number", CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.verifySeachedAvalableInList("Price & Config Status", CommonPage.getTestData("OrderingStatus"));
		OrderingVehicleConfigFleetSpecsPage.verifySeachedAvalableInList("Price & Config Id", CommonPage.getTestData("PriceAndConfigSearch"));
	}
	
	/**
	 * This method retrieves the specified column value and compares each value with searched value
	 * @param columnName
	 * @param searchText
	 * @throws Exception
	 */
	public static void verifySeachedAvalableInList(String columnName, String searchText) throws Exception {
		List<WebElement> columnElementList = WebDriverAccess.getDriver().findElements(BrowserAccess.getLocator("_CSS", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_CSS.getValue().replaceAll("number", OrderingVehicleConfigFleetSpecsPage.getColumnPostion(columnName))));
		if(columnElementList.size()==0) {
			throw new OrderingErrorOccured("Unable to retrive value from the list");
		}
		for(WebElement rowElelemnt : columnElementList) {
			if(!rowElelemnt.getText().trim().equals(searchText)) {
				throw new OrderingErrorOccured(searchText + " != " + rowElelemnt.getText() + " in column " + columnName);
			}
		}
	}
	
	public static String getColumnPostion(String columnName) {
		String columnPostion = null;
		switch(columnName) {
			case "Actions":
				columnPostion = "2";
				break;
			case "Fleet Spec Status":
			case "Fleet Specs Status":
			case "Price & Config Status":
				columnPostion = "4";
				break;
			case "Client Number":
				columnPostion = "5";
				break;
			case "Fleet Spec Id":
			case "Price & Config Id":
				columnPostion = "6";
				break;
			case "Revision Number":
				columnPostion = "7";
				break;
			case "Fleet Spec Name":
				columnPostion = "8";
				break;
			case "Year":
				columnPostion = "9";
				break;
			case "Make":
				columnPostion = "10";
				break;
			case "Model":
				columnPostion = "11";
				break;
			case "Trim":
				columnPostion = "12";
				break;
			case "Model Code":
				columnPostion = "13";
				break;
			case "Last Updated Date":
				columnPostion = "14";
				break;
			case "Last Updated By":
				columnPostion = "15";
				break;
			default: throw new InvalidSwitchCaseException(columnName + " is invalid column");
		}
		return columnPostion;
	}
	
	/**
	 * This method enters data in the given search field
	 * @lastModifiedBy shisingh
	 * @param fieldName
	 * @param searchText
	 * @throws Exception
	 */
	public static void enterOrSelectFieldValue(String fieldName, String searchText) throws Exception {
		switch(fieldName) {
			case "Client Number":
			case "Created By":				
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLIENT_NUMBER_TEXTFIELD_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLIENT_NUMBER_TEXTFIELD_ID.getValue()), searchText);
				break;
			case "Fleet Spec Name":
			case "Price & Config Name":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_SEARCH_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_SEARCH_ID.getValue()), searchText);
				break;
			case "Fleet Spec Status":
			case "Price & Config Status":
				BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_CSS);
				BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_SEARCH_FIELD_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_SEARCH_FIELD_CSS);
				BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_SEARCH_FIELD_CSS);
				BrowserAction.clickandClear(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_SEARCH_FIELD_CSS);
				System.out.println(fieldName + ": " + searchText);
				BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_SEARCH_FIELD_CSS, searchText);
				BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_SEARCH_FIELD_SELECT_DIPLAYED_CSS);
				break;
			case "Fleet Spec Id":
			case "Price & Config Id":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_ID.getValue()), searchText);
				break;
			case "Revision Number":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_REVISION_NUMBER_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_REVISION_NUMBER_ID.getValue()), searchText);
				break;
			case "Year":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_YEAR_DROPDOWN_CSS.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_YEAR_DROPDOWN_CSS.getValue()), searchText);
				break;
			case "Make":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_MAKE_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_MAKE_ID.getValue()), searchText);
				break;
			case "Model":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_MODEL_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_MODEL_ID.getValue()), searchText);
				break;
			case "Model Code":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_MODEL_CODE_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_MODEL_CODE_ID.getValue()), searchText);
				break;
			case "Trim":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_TRIM_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_TRIM_ID.getValue()), searchText);
				break;
			case "Option Code":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_OPTION_CODE_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_OPTION_CODE_ID.getValue()), searchText);
				break;
			case "Vehicle Option Description":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_VEHICLE_OPTION_DESCRIPTION_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_VEHICLE_OPTION_DESCRIPTION_ID.getValue()), searchText);
				break;
			case "Creation Date From":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CREATE_FROM_DATE_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CREATE_FROM_DATE_ID.getValue()), searchText);
				break;
			case "Creation Date To":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CREATE_TO_DATE_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CREATE_TO_DATE_ID.getValue()), searchText);
				break;
			case "Fleet Spec Status Date From":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_FROM_DATE_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_FROM_DATE_ID.getValue()), searchText);
				break;
			case "Fleet Spec Status Date To":
				WebDriverAction.clickAndClear(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_TO_DATE_ID.getValue()));
				System.out.println(fieldName + ": " + searchText);
				WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_ID", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_TO_DATE_ID.getValue()), searchText);
				break;
			default: throw new InvalidSwitchCaseException(fieldName + " is invalid search field requested");
		}
		
	}
	
	/** 
	* This method verify all fields are cleared.
	* @lastModifiedBy shisingh
	* @throws Exception 
	*/	
	public static void verifyFieldValuesCleared() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.waitForVehicleConfigFleetSpecsPage();
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLIENT_NUMBER_TEXTFIELD_ID).getText(), "", "Client number field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_SEARCH_ID).getText(), "", "Fleet spec name field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CREATED_BY_TEXTFIELD_ID).getText(), "", "Created by field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_CSS).getText(), "", "Status field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_ID).getText(), "", "Spec id field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_REVISION_NUMBER_ID).getText(), "", "Revision number field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_YEAR_DROPDOWN_CSS).getText(), "", "Year field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_MAKE_ID).getText(), "", "Make field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_MODEL_ID).getText(), "", "Model field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_MODEL_CODE_ID).getText(), "", "Model code field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_TRIM_ID).getText(), "", "Trim field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_OPTION_CODE_ID).getText(), "", "Option code field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_VEHICLE_OPTION_DESCRIPTION_ID).getText(), "", "Vehicle option field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CREATE_FROM_DATE_ID).getText(), "", "Create date from field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CREATE_TO_DATE_ID).getText(), "", "Create date to field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_FROM_DATE_ID).getText(), "", "Fleet spec status from field value not cleared" );
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_TO_DATE_ID).getText(), "", "Fleet spec status to field value not cleared" );
	}
	
	/**
	 * This method verifies that user provided columns are and csv columns are same.
	 * @throws IOException
	 */
	public static void verifyCSVHeadings() throws IOException {
		List<String> csvHeadingList = new ArrayList<>(ExcelUtil.getCSVMapData().keySet());
		List<String> userProvededHeading = Arrays.asList(CommonPage.getTestData("CustomColumn1").split("\\|"));
		System.out.println(ExcelUtil.getCSVMapData());
		System.out.println("CSV: "+csvHeadingList);
		System.out.println("User: "+userProvededHeading);
		Assert.assertEquals(csvHeadingList, userProvededHeading, "UI heading list not matched with provided heading list");
 	}	
	
	/** 
	* This method clicks on search button. 
	* @lastModifiedBy shisingh 
	* @throws Exception 
	*/	
	public static void clickOnSearch() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
	}
	
	/** 
	* This method clicks on clear filters.
	* @lastModifiedBy shisingh
	* @throws Exception 
	*/	
	public static void clickOnClearFilters() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLEAR_FILTERS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLEAR_FILTERS_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLEAR_FILTERS_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLEAR_FILTERS_XPATH);
	}
	
	/** 
	* This method clicks on Archive and Unarchive Fleet Spec. 
	* @lastModifiedBy shisingh 
	* @throws Exception 
	*/	
	public static void performArchiveUnarchiveActionForFleetSpec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.waitForArchiveOption();
		OrderingVehicleConfigFleetSpecsPage.clickOnArchive();
		OrderingVehicleConfigFleetSpecsPage.archiveUnarchivePopUp("Ok");
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehicleConfigFleetSpecsPage.verifyFleetSpecSingleRowDetails("Fleet Specs Status", "Archived");
		OrderingVehicleConfigFleetSpecsPage.selectFirstSearchVehicleForApproval();
		OrderingVehicleConfigFleetSpecsPage.waitForUnarchiveOption();
		OrderingVehicleConfigFleetSpecsPage.clickOnUnArchive();
		OrderingVehicleConfigFleetSpecsPage.archiveUnarchivePopUp("Ok");
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehicleConfigFleetSpecsPage.verifyFleetSpecSingleRowDetails("Fleet Specs Status", "Work In Progress");
	}
	
	/** 
	* This method clicks on Archive and Unarchive Price Config. 
	* @lastModifiedBy shisingh 
	* @throws Exception 
	*/	
	public static void performArchiveUnarchiveActionForPriceConfig() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.waitForArchiveOption();
		OrderingVehicleConfigFleetSpecsPage.clickOnArchive();
		OrderingVehicleConfigFleetSpecsPage.archiveUnarchivePopUp("Ok");
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehicleConfigFleetSpecsPage.verifyFleetSpecSingleRowDetails("Price & Config Status", "Archived");
		OrderingVehicleConfigFleetSpecsPage.selectFirstSearchVehicleForApproval();
		OrderingVehicleConfigFleetSpecsPage.waitForUnarchiveOption();
		OrderingVehicleConfigFleetSpecsPage.clickOnUnArchive();
		OrderingVehicleConfigFleetSpecsPage.archiveUnarchivePopUp("Ok");
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehicleConfigFleetSpecsPage.verifyFleetSpecSingleRowDetails("Price & Config Status", "Price Config Saved");
	}
	
	/** 
	* This method clicks on Archive and captures the Archived message. 
	* @lastModifiedBy shisingh 
	* @throws Exception 
	*/	
	public static void clickOnArchive() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_ARCHIEVE_BTN_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_ARCHIEVE_BTN_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_ARCHIEVE_BTN_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_ARCHIEVE_BTN_ID);
	}
	
	/** 
	* This method clicks on Unarchive and captures the Archived message. 
	* @lastModifiedBy shisingh 
	* @throws Exception 
	*/	
	public static void clickOnUnArchive() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_UNARCHIEVE_BTN_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_UNARCHIEVE_BTN_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_UNARCHIEVE_BTN_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_PRICE_AND_CONFIG_UNARCHIEVE_BTN_ID);
	}
	
	/** 
	* This method clicks on Client Approve button. 
	* @lastModifiedBy shisingh 
	* @throws Exception 
	*/	
	public static void clickOnClientApprove() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CLIENT_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CLIENT_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CLIENT_APPROVE_BUTTON_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CLIENT_APPROVE_BUTTON_ID);
	}
	
	/** 
	* This method clicks on Element Approve button. 
	* @lastModifiedBy shisingh 
	* @throws Exception 
	*/	
	public static void clickOnElementApprove() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ELEMENT_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ELEMENT_APPROVE_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ELEMENT_APPROVE_BUTTON_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ELEMENT_APPROVE_BUTTON_ID);
	}
	
	/** 
	* This method clicks archive and unarchive popup. 
	* @lastModifiedBy shisingh 
	* @throws Exception 
	*/	
	public static void archiveUnarchivePopUp(String action) throws Exception{
		switch(action) {
			case "Ok":
				BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACTIONS_OK_BUTTON_ID);
				BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACTIONS_OK_BUTTON_ID);
				BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACTIONS_OK_BUTTON_ID);
				BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACTIONS_OK_BUTTON_ID);
				break;
			case "Cancel":
				BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACTIONS_CANCEL_BUTTON_ID);
				BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACTIONS_CANCEL_BUTTON_ID);
				BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACTIONS_CANCEL_BUTTON_ID);
				BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACTIONS_CANCEL_BUTTON_ID);
				break;
			default: throw new InvalidSwitchCaseException(action + " is a invalid Archive Unarchive popup action");
		}
	}
	
	/**
	 * This method enters the Make,Model,Trim,Fuel type,Traction and select vehicle indicators
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void enterBuildFromScratchDetails(String section) throws Exception {
		switch (section) {
		case "FleetSpec":
			enterValuesInMakeTextBox();
			enterValuesInModelTextBox();
			enterValueInTrimTextBox();
			break;
		case "PriceConfig":
			enterValuesInMakeTextBox();
			enterValuesInModelTextBox();
			enterValueInTrimTextBox();
			enterValueInFuelTypeTextBox();
			enterValueInTractionTextBox();
			break;
		default:
			throw new InvalidSwitchCaseException("Wrong details  entered");
		}
	}

	/**
	 * This method enter the multiple values in make text box
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void enterValuesInMakeTextBox() throws Exception {
		System.out.println("Make: " + CommonPage.getTestData("Make"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH,CommonPage.getTestData("Make"));
		KeyPressEvents.pressEnter(1);
		if(CommonPage.getTestData("Make: " + "Make1") != null) {
			System.out.println(CommonPage.getTestData("Make1"));
			BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH,CommonPage.getTestData("Make1"));
			KeyPressEvents.pressEnter(1);
		}
	}

	/**
	 * This method enter the multiple values in model text box
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void enterValuesInModelTextBox() throws Exception {
		System.out.println("Model: " + CommonPage.getTestData("Model"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH,CommonPage.getTestData("Model"));
		KeyPressEvents.pressEnter(1);
		if(CommonPage.getTestData("Make1") != null) {
			System.out.println("Model: " + CommonPage.getTestData("Model1"));
			BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH,CommonPage.getTestData("Model1"));
			KeyPressEvents.pressEnter(1);
		}
	}

	/**
	 * This method enter the trim in text box
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void enterValueInTrimTextBox() throws Exception {
		System.out.println("Trim: " + CommonPage.getTestData("Trim"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_TRIM_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_TRIM_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_TRIM_TEXTBOX_XPATH);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_TRIM_TEXTBOX_XPATH,CommonPage.getTestData("Trim"));
		KeyPressEvents.pressEnter(1);
	}

	/**
	 * This method enter the Fuel Type in text box
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void enterValueInFuelTypeTextBox() throws Exception {
		System.out.println("Fuel: " + CommonPage.getTestData("Fuel"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_FUEL_TYPE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_FUEL_TYPE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_FUEL_TYPE_TEXTBOX_XPATH);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_FUEL_TYPE_TEXTBOX_XPATH,CommonPage.getTestData("Fuel"));
		KeyPressEvents.pressEnter(1);
	}

	/**
	 * This method enter the traction in text box
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void enterValueInTractionTextBox() throws Exception {
		System.out.println("Traction: " + CommonPage.getTestData("Traction"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_TRACTION_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_TRACTION_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_TRACTION_TEXTBOX_XPATH);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_TRACTION_TEXTBOX_XPATH,CommonPage.getTestData("Traction"));
		KeyPressEvents.pressEnter(1);
	}

	/**
	 * This method selects the vehicles as per search criteria
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void selectVehicleFromSearchedResult() {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_VEHICLE_TABLE_ID);
			WebDriverAction.click(BrowserAccess.getLocator(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_SELECT_SEARCHED_VEHICLE_XPATH.name(),
					String.format(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_SELECT_SEARCHED_VEHICLE_XPATH.toString(), 
							CommonPage.getTestData("Year"), CommonPage.getTestData("Make"),CommonPage.getTestData("Model"), CommonPage.getTestData("Trim"))));
		} catch (Exception e) {
			throw new OrderingErrorOccured("Searched Vehicle did not matched");
		}
	}
	
	/**
	 * This method verfies the save pop up details
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyFieldsOnSavePopUp() throws Exception {
		BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_PRICE_CONFIG_LABEL_XPATH).isDisplayed();
		BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_PRICE_CONFIG_LABEL_XPATH).isDisplayed();
		BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_SAVE_CLIENT_LABEL_XPATH).isDisplayed();
		BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_NOTES_LABEL_XPATH).isDisplayed();
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_ID),"Accept", "available on page");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_ID),"Cancel", " available on page");
	}

	/**
	 * This method verifies the vehicle details present on price and config page
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyVehiclePriceConfigDetails() throws Exception {
		BrowserAssert.assertAllElementsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_RED_INSTRUCTIONS_CLASS);
		System.out.println("All elements are present");
	}

	/**
	 * This method verifies save and cancel button on price and config page.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifySaveAndCancelButton() throws Exception {
		BrowserAssert.assertElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_BUTTON_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CANCEL_BUTTON_XPATH);
	}

	/**
	 * This method verifies vehicle notes on price and config page
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyVehiclePriceConfigDetailsnotes() throws Exception {
		Actions act = new Actions(WebDriverAccess.getDriver());
		act.moveToElement(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_OPTIONS_NOTES_CLASS)).build().perform();
		System.out.println(BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_NOTES1_XPATH).getText());
		}

	/**
	 * This method verifies the vehicle image and fleet details present on price and config page
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyVehicleImageAndFleetDetails() throws Exception {
		BrowserAssert.assertElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_FLEET_NAME_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_VEHICLE_IMAGE_XPATH);
	}

	/**
	 * This method verifies the details below vehicle image on price and config page
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyDetailsBelowVehicleImage() throws Exception {
		BrowserAssert.assertElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_BASE_INVOICE_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_BASE_MSRP_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_OPTIONS_INVOICE_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_OPTIONS_MSRP_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_INVOICE_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_MSRP_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DESTINATION_FEE_XPATH);
	}

	/**
	 * This method perform Archived operation
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void searchArchivedOrder() throws Exception {
		waitForVehicleConfigFleetSpecsPage();
		searchFleetSpec("Price & Config Status", CommonPage.getTestData("OrderingStatus"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_ACTION_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_ACTION_BUTTON_XPATH);
	}

	/**
	 * This method verifies the archived disabled options
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyArchivedDisableOptions() throws Exception {
		List<WebElement> disableOptions = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DISABLEOPTIONS_CLASS);
		for (WebElement archiveOptions : disableOptions) {
			if (!archiveOptions.isEnabled())
				System.out.println("Disabled option" + archiveOptions.getText());
		}
		BrowserAction.refresh();
	}

	/**
	 * This method wait until the archived disabled options displayed.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void waitForFleetSpecdisableActionOptions() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_OPTIONS_CSS.getValue()),2));
	}
	
	/**
	 * This clicks on advanced search button
	 * @lastModifiedBy bshah
	 */
	public static void clickonAdvancedSearchButton() throws Exception{
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ADVANCED_SEARCH_BUTTON_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ADVANCED_SEARCH_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ADVANCED_SEARCH_BUTTON_ID);
	}
		
	/**
	 * This method enters year data in filter
	 * @lastModifiedBy bshah
	*/
	public static void enterValueInYearTextBox() throws Exception {
	    BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_YEAR_DROPDOWN_BUTTON_XPATH);
	    BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_YEAR_DROPDOWN_BUTTON_XPATH);
	    BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_YEAR_DROPDOWN_BUTTON_XPATH);
	    BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_YEAR_DROPDOWN_BUTTON_XPATH);
	    BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_YEAR_DROPDOWN_XPATH);
	    List<WebElement> yearList = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_YEAR_DROPDOWN_XPATH);
		for (int i = 0; i < yearList.size(); i++) {
			if ((yearList.get(i).getText()).trim().equals(CommonPage.getTestData("Year"))) {
				if (yearList.get(i).isSelected()) {
					BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_YEAR_DROPDOWN_BUTTON_XPATH);
				} else {
					yearList.get(i).click();
				}
				if ((yearList.get(i).getText()).trim().equals(CommonPage.getTestData("Year"))) {
					yearList.get(i).click();
					BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_YEAR_DROPDOWN_BUTTON_XPATH);
				}
	    	}
	    }
	}
		
	/**
	 * This method enters Make data in filter
	 * @lastModifiedBy lpadaliya
	 */
	public static void enterValueInMakeTextBox() throws Exception {
		CommonPage.getElementOrderObject().getVehicleTabObject().setMake(CommonPage.getTestData("Make"));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH)));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH);
		System.out.println("Make Text: " + CommonPage.getElementOrderObject().getVehicleTabObject().getMake());
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH, CommonPage.getElementOrderObject().getVehicleTabObject().getMake());
		KeyPressEvents.pressEnter(1);
	}
		
	/**
	 * This method enters Model data in filter
	 * @lastModifiedBy lpadaliya
	 */
	public static void enterValueInModelTextBox() throws Exception {
		CommonPage.getElementOrderObject().getVehicleTabObject().setModel(CommonPage.getTestData("Model"));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH)));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH);
		System.out.println("Model Text: " + CommonPage.getElementOrderObject().getVehicleTabObject().getModel());
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH, CommonPage.getElementOrderObject().getVehicleTabObject().getModel());
		KeyPressEvents.pressEnter(1);
	}
		
	/**
	 * This method enters  fleet spec name
	 * @lastModifiedBy bshah
	*/
	public static String enterFleetSpecsNameAndNotes(String fleetSpecName) throws Exception {
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(fleetSpecName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		System.out.println("Spec name: " + CommonPage.getElementOrderObject().getVehicleTabObject().getFleetSpecName());
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID, CommonPage.getElementOrderObject().getVehicleTabObject().getFleetSpecName());
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_CLIENT_SEARCH_BOX_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_CLIENT_SEARCH_BOX_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_ID);
		return CommonPage.getElementOrderObject().getVehicleTabObject().getFleetSpecName();
	}
	
	/**
	 * This method enters  fleet spec name with DPO details
	 * @lastModifiedBy bshah
	*/
	public static String enterFleetSpecsNameAndNotesspec(String fleetSpecName) throws Exception {
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(fleetSpecName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		System.out.println("Spec name: " + CommonPage.getElementOrderObject().getVehicleTabObject().getFleetSpecName());
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID, CommonPage.getElementOrderObject().getVehicleTabObject().getFleetSpecName());
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_CLIENT_SEARCH_BOX_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_CLIENT_SEARCH_BOX_XPATH));
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_XPATH);
			BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_XPATH);		
		} catch (TimeoutException e) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_ACCEPT_BUTTON_XPATH);
			BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_ACCEPT_BUTTON_XPATH);		
		}
		return CommonPage.getElementOrderObject().getVehicleTabObject().getFleetSpecName();
	}
	
	/**
	 * This method enters  fleet spec name
	 * @lastModifiedBy ksharma
	*/	
	public static String enterFleetSpecsNameAndNotesNew(String fleetSpecName) throws Exception {
		CommonPage.getElementOrderObject().getVehicleTabObject().setFleetSpecName(fleetSpecName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		System.out.println("Spec name: " + CommonPage.getElementOrderObject().getVehicleTabObject().getFleetSpecName());
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID, CommonPage.getElementOrderObject().getVehicleTabObject().getFleetSpecName());
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_CLIENT_SEARCH_BOX_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_CLIENT_SEARCH_BOX_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_ID);
		return CommonPage.getElementOrderObject().getVehicleTabObject().getFleetSpecName();
	}
		
	/**
	 * This method searches fleet spec based on name
	 * @lastModifiedBy bshah
	 */	
	public static void enterFleetSpecsNameInSearchBoxAndCickOnSearch() throws Exception {
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_SEARCH_ID);
		BrowserAction.clear(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_SEARCH_ID);
		System.out.println("Spec name: " + CommonPage.getElementOrderObject().getVehicleTabObject().getFleetSpecName());
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_SEARCH_ID, CommonPage.getElementOrderObject().getVehicleTabObject().getFleetSpecName());
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLIENT_NUMBER_TEXTFIELD_ID);
		BrowserAction.clear(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLIENT_NUMBER_TEXTFIELD_ID);
		System.out.println("Client Name:  " + CommonPage.getTestData("ClientNumber"));
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLIENT_NUMBER_TEXTFIELD_ID, CommonPage.getTestData("ClientNumber"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);		
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
		
	/**
	 * This method clicks on edit action from single action list
	 * @lastModifiedBy bshah
	 * @throws Exception
	 */
	public static void clickOnEdit() throws Exception {
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_EDIT_XPATH);		
	}
		
	/**
	 * This method clicks on Save As button on new added Fleet Spec from drop down.
	 * @lastModifiedBy bshah
	 */
	public static void clickOnSaveAsButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SPEC_SAVE_DROPDOWN_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SPEC_SAVE_DROPDOWN_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SPEC_SAVE_DROPDOWN_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_AS_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_AS_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_AS_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_AS_BUTTON_XPATH);
	}
	
	/** 
	* This method selects n-th search result based on argument passed
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/	
	public static void selectSpecFromTable(int nthRecord) throws Exception {
		CommonPage.javascriptClick(BrowserAccess.getElements(
			OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_SELECT_CHECKBOXES_XPATH)
			.get(nthRecord - 1));	
	}
	
	/** 
	* This method clicks on checkbox to select all specs
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/	
	public static void selectAllSpecFromTable() throws Exception {
		CommonPage.javascriptClick(BrowserAccess.getElement(
				OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_SELECTALL_XPATH));
	}
	
	/** 
	* This method verify if bulk actions are enabled or disabled
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/	
	public static void verifyBulkActions(String status) throws Exception{
		switch(status) {
			case "enabled":
				Assert.assertTrue(BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_BULK_ACTIONS_XPATH).get(0).isDisplayed(), "Bulk options are not displayed/enabled");
				Assert.assertTrue(BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_BULK_ACTIONS_XPATH).get(0).isEnabled(), "Bulk options are not displayed/enabled");
				break;
			case "disabled":
				Assert.assertTrue(BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_BULK_ACTIONS_XPATH).get(0).isDisplayed(), "Bulk options are displayed/enabled");
				Assert.assertFalse(BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_BULK_ACTIONS_XPATH).get(0).isEnabled(), "Bulk options are displayed/enabled");
				break;
        default: throw new InvalidSwitchCaseException(status + "invalid argument passed");
		}
	}
	
	/** 
	* This method validates single/ Bulk actions label
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/	
	public static void validatesSingleBulkActionsLable(int nthRecord, String label) throws Exception {
		BrowserAccess
		.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_ACTIONS_DOTS_XPATH)
		.get(nthRecord - 1).click();
		if (label.equalsIgnoreCase("ROW ACTIONS")) {
			Assert.assertTrue(BrowserAccess.getElements(
					OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_ROW_ACTIONS_OPEN_MENU_ITEMS_XPATH)
					.get(0).getText().contentEquals(label), "Actions menu label does not match");
		} else if (label.equalsIgnoreCase("BULK ACTIONS")) {
			Assert.assertTrue(BrowserAccess.getElements(
					OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_BULK_ACTIONS_OPEN_MENU_ITEMS_XPATH)
					.get(0).getText().contentEquals(label), "Actions menu label does not match");
		}
	}
	
	/** 
	* This method inputs client number, Status and Spec ID and click on search
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void enterInMultipleSearchFieldsInFleetSpecPageAndSearch() throws Exception{
		System.out.println("Client Number: "+ CommonPage.getTestData("ClientNumber"));
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Client Number", CommonPage.getTestData("ClientNumber"));
		System.out.println("Fleet Spec Status: "+ CommonPage.getTestData("OrderingStatus"));
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue("Fleet Spec Status", CommonPage.getTestData("OrderingStatus"));
		OrderingVehicleConfigFleetSpecsPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}

	/** 
	* This method validates value of column for a provided row
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void validateColumnValue(int rowNo, String ColumnName, String Value) throws Exception {
		String records = BrowserAccess.getElementText(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_NO_OF_RECORDS_XPATH).split(" ")[0];
		if(!"0".equals(records)) {
			int column = 0;
			List<WebElement> headerColumns = BrowserAccess.getElements(
					OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_TABLE_HEADER_XPATH);
			for (WebElement col : headerColumns) {
				column++;
				if (col.getText().contains(ColumnName))
					break;
			}
			WebElement tableCell = WebDriverAccess.getDriver()
					.findElement(BrowserAccess.getLocator("_XPATH",
							OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_TABLE_CONTAINER_XPATH
									.getValue().replaceAll("rowNo", Integer.toString(rowNo))
									.replaceAll("column", Integer.toString(column))));
			Assert.assertTrue(tableCell.getText().contains(Value), "Column value mismatched. <<Expected: " + Value
					+ " Actual: " + tableCell.getText() + " at Row:" + rowNo + " And Col: " + column + " >>");
		} else {
			System.out.println("No Records present in the Vehicle Table");
		}
		
	}
	
	/** 
	* This method return value of column for a provided row
	* @lastModifiedBy spatnaik 
	* @throws Exception 
	*/
	public static String returnColumnValuePC(int rowNo, String ColumnName) throws Exception {
		int column=0;
		List<WebElement> headerColumns = BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_TABLE_HEADER_XPATH);
		for (WebElement col: headerColumns) {
			column++;
			if(col.getText().contains(ColumnName))
				break;
		}
		WebElement tableCell=WebDriverAccess.getDriver().findElement(By.xpath("//*[@id='table-container']/tbody/tr["+rowNo+"]/td["+column+"]"));
		return tableCell.getText();
	}
	
	public static void selectPCReviewFlag(String value) throws Exception {
		BrowserAction.selectDropdownOptionByText(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_CONFIG_REVIEW_FLAG_DROPDOWN_XPATH, value);	
	}
	
	/** 
	* This method return value of column for a provided row
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static String returnColumnValue(int rowNo, String ColumnName) throws Exception {
		String records = BrowserAccess.getElementText(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_NO_OF_RECORDS_XPATH).split(" ")[0];
		if(!"0".equals(records)) {
			int column=0;
			List<WebElement> headerColumns = BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_TABLE_HEADER_XPATH);
			for (WebElement col: headerColumns) {
				column++;
				if(col.getText().contains(ColumnName))
					break;
			}
			WebElement tableCell= WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("_XPATH", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_TABLE_CONTAINER_XPATH.getValue().replaceAll("rowNo", Integer.toString(rowNo)).replaceAll("column", Integer.toString(column))));
			System.out.println("Spec Id: " + tableCell.getText());
			return tableCell.getText();
		} else {
			return "";
		}
	}
	
	/** 
	* This method selects clears selected values from status dropdown
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void clearStatusDropdown() throws Exception{
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_SEARCH_FIELD_CSS);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_SEARCH_FIELD_CSS);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_SELECTALL_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_SELECTALL_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_STATUS_DROPDOWN_CSS);
	}
	
	/** 
	* This method selects action for given row
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void selectActionFromList(int nthRecord, String action) throws Exception {
		boolean actFlag=false;
		BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_ACTIONS_DOTS_XPATH).get(nthRecord-1).click();
		List<WebElement> actions=BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_ROW_ACTIONS_OPEN_MENU_ITEMS_XPATH);
		for(WebElement act :actions) {
			((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", act);
			if(act.getText().contains(action)) {
				act.click();
				actFlag=true;
				break;
			} 
		} 
		Assert.assertTrue(actFlag, "Incorrect value for action is passed");
	}

	/** 
	* This method verify the presence of Flagged for Review red bar when color of bar is provided as argument> "None" to validate record is not flagged for review
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void validateFlaggedForReviewStatusRedBar(int noOfColumnsInView, int nthRecord, String hexCodeOfColorUsed) throws Exception {
		WebElement firstColumn;
		firstColumn=BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_XPATH).get((noOfColumnsInView*(nthRecord-1)));
		System.out.println(firstColumn.getCssValue("border-left"));
		String leftBorderColorofFirstColumn = firstColumn.getCssValue("border-left");
		String rgb= leftBorderColorofFirstColumn.substring(leftBorderColorofFirstColumn.indexOf("(") + 1, leftBorderColorofFirstColumn.indexOf(")"));
		List<String> rgbTotList = Arrays.asList(rgb.split(","));
		String hex = String.format("#%02X%02X%02X", Integer.parseInt(rgbTotList.get(0).trim()), Integer.parseInt(rgbTotList.get(1).trim()), Integer.parseInt(rgbTotList.get(2).trim()));  
		if(!hexCodeOfColorUsed.contentEquals("None")) {  
			Assert.assertTrue(hexCodeOfColorUsed.contentEquals(hex), "Flagged for Review Bar color is not Red");
			System.out.println(nthRecord + " row has Flagged for Review status");
		}
		else {
			Assert.assertTrue(!hexCodeOfColorUsed.contentEquals(hex), "Flagged for Review Bar color is Red");
			System.out.println(nthRecord + " row doesnot has Flagged for Review status");
		}
	}

	/** 
	* This method selects fleetspec / row as per row number passed in argument
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void selectFleetSpec(int nthRecord) throws Exception {
		BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_TABLE_ROWS_XPATH).get(nthRecord-1).click();
	}
	
	/** 
	* This method validates presence of given option in action menu
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void validateActionPresentOnList(int nthRecord, String action) throws Exception {
		boolean flag = false;
		BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_ACTIONS_DOTS_XPATH).get(nthRecord-1).click();
		BrowserWait.waitUntilElementIsDisplayed(BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_ROW_ACTIONS_OPEN_MENU_ITEMS_XPATH));
		List<WebElement> actions=BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_ROW_ACTIONS_OPEN_MENU_ITEMS_XPATH);
		for(WebElement act :actions) {
			BrowserWait.waitUntilElementIsDisplayed(act);
			if(act.getText().contains(action)) {
				flag=true;
				break;
			}
		}
		Assert.assertTrue(flag, "Given option is not present in actions menu");
	}
	
	/** 
	* This method validates presence Decline /Approve buttons in Fleetspec page
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void validatePresenceofDeclineAndApproveButtonsForGivenFleetSpec(String FleetSpecID) throws Exception{
		try { 
			BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_IN_USE_POP_UP_EDITMODE_XPATH).click();
		} catch(ElementNotInteractableException e) {
			System.out.println("Fleet Spec is not in use and can be opened.");
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_ID_NAME_XPATH);
		}	
		Assert.assertTrue(BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_ID_NAME_XPATH).getText().contains(FleetSpecID));
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DECLINE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DECLINE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_APPROVE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_APPROVE_BUTTON_XPATH);
	}

	/** 
	* This method verify Decline pop-up elements
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void validateDeclinePopUp() {
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DECLINE_POPUP_HEADLINE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DECLINE_POPUP_CANCEL_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DECLINE_POPUP_SUBMIT_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DECLINE_POPUP_SELECT_FILE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DECLINE_COMMENT_BOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DECLINE_POPUP_CANCEL_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DECLINE_POPUP_SUBMIT_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DECLINE_POPUP_SELECT_FILE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DECLINE_COMMENT_BOX_XPATH);
	}
	
	public static void selectReviewFlag(String value) throws Exception {
		BrowserAction.selectDropdownOptionByText(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_REVIEW_FLAG_DROPDOWN_XPATH, value);
	}
	/** 
	* This method selects value for Fleet Spec Source
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void selectFleetSpecSource(String value) throws Exception{
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_SOURCE_DROPDOWN_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_SOURCE_DROPDOWN_XPATH);
		BrowserAction.selectDropdownOptionByValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_SOURCE_DROPDOWN_XPATH, value);
	}
	
	/**
	 * This method verifies Retail Only flag on vehicle search page .
	 * @lastModifiedBy bshah
	 */
	public static void verifyretailonlyflag() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(ElementNotInteractableException.class).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SELECT_VEHICLE_FIRST_SEARCH_CSS)));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SELECT_VEHICLE_FIRST_SEARCH_CSS);
		List<WebElement> results = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SELECT_VEHICLE_FIRST_SEARCH_CSS);
		if (results.get(0).getText().contains("(Retail Only)")) {
			CommonPage.getElementOrderObject().getVehicleTabObject().setretailonlyflag(true);
		} else {
			CommonPage.getElementOrderObject().getVehicleTabObject().setretailonlyflag(false);
		}
		System.out.println("Retail Only Flag:" + CommonPage.getElementOrderObject().getVehicleTabObject().getretailonlyflag());
		CommonPage.jsGreenBorderHighlighter(results.get(0));
		//assert CommonPage.getElementOrderObject().getVehicleTabObject().getretailonlyflag() == true;
	}
	
	/**
	 * This method clicks on Export PDF on vehicle search page .
	 * @lastModifiedBy ksharma
	 */
	public static void clickOnExporttoPDF() throws Exception {
		((JavascriptExecutor)WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_INDIVIDUAL_ACTION_MENU_ID));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_INDIVIDUAL_ACTION_MENU_ID);
		OrderingCommonPage.checkAlertPopUp();
		Actions action = new Actions(WebDriverAccess.getDriver());
		action.moveToElement(BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_INDIVIDUAL_ACTION_MENU_ID))
		.moveToElement(BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_EXPORT_TO_PDF_XPATH))
		.click().build().perform();
	}

	/**
	 * This method verifies client view on export pdf model pop up.
	 * @lastModifiedBy bshah
	 */
	public static void verifyclientviewonPDFModelPopUp() throws Exception	{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT_VIEW_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT_VIEW_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT_VIEW_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT__STD_NET_EQUIPMENT_CHECKBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT__STD_NET_EQUIPMENT_CHECKBOX_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT__STD_NET_EQUIPMENT_CHECKBOX_TEXT_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT_FEES_CHECKBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT_FEES_CHECKBOX_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT_FEES_CHECKBOX_TEXT_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT_INVOICE_CHECKBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT_INVOICE_CHECKBOX_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT_INVOICE_CHECKBOX_TEXT_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_BUTTON_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_BUTTON_XPATH));	
	}
	
	/**
	 * This method verifies export completion.
	 * @lastModifiedBy bshah
	 */
	public static void PDFExportverification(String section, String className) throws Exception{
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		clickonexport();		
		OrderingVehicleConfigFleetSpecsPage.exportcompleteverification();
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"), section, className);
	}
	
	/**
	 * This method clicks on export.
	 * @lastModifiedBy bshah
	 */
	public static void clickonexport() throws Exception	{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_BUTTON_XPATH);
	}
	
	/**
	 * This method verifies export completion POP UP.
	 * @lastModifiedBy bshah
	 */
	public static void exportcompleteverification() throws Exception	{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_COMPLETE_POPUP_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_COMPLETE_POPUP_XPATH);
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_COMPLETE_POPUP_XPATH).getText().equals("Exporting complete");
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_COMPLETE_POPUP_XPATH);
	}
	
	/**
	 * This method verifies pricing view on export pdf model pop up.
	 * @lastModifiedBy bshah
	 */
	public static void verifyPricingviewonPDFModelPopUp() throws Exception	{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_VIEW_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_VIEW_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_VIEW_XPATH));
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_VIEW_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING__STD_NET_EQUIPMENT_CHECKBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING__STD_NET_EQUIPMENT_CHECKBOX_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING__STD_NET_EQUIPMENT_CHECKBOX_TEXT_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_FEES_CHECKBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_FEES_CHECKBOX_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_FEES_CHECKBOX_TEXT_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_INVOICE_CHECKBOX_TEXT_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_INVOICE_CHECKBOX_TEXT_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_INVOICE_CHECKBOX_TEXT_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_MSRP_CHECKBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_MSRP_CHECKBOX_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_MSRP_CHECKBOX_TEXT_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_BUTTON_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_BUTTON_XPATH));	
	}
	
	/**
	 * This method verifies driver view on export pdf model pop up.
	 * @lastModifiedBy bshah
	 */	
	public static void verifyDriverviewonPDFModelPopUp() throws Exception	{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_VIEW_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_VIEW_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_VIEW_XPATH));
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_VIEW_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER__STD_NET_EQUIPMENT_CHECKBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER__STD_NET_EQUIPMENT_CHECKBOX_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER__STD_NET_EQUIPMENT_CHECKBOX_TEXT_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_INVOICE_CHECKBOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_INVOICE_CHECKBOX_ID);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_INVOICE_CHECKBOX_TEXT_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_MSRP_CHECKBOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_MSRP_CHECKBOX_ID);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_MSRP_CHECKBOX_TEXT_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_BUTTON_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_EXPORT_BUTTON_XPATH));
	}
	
	/**
	 * This method deselects options on client view
	 * @lastModifiedBy bshah
	 */	
	public static void selectcustomizeclientview() throws Exception
	{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT_VIEW_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT__STD_NET_EQUIPMENT_CHECKBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT__STD_NET_EQUIPMENT_CHECKBOX_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_CLIENT__STD_NET_EQUIPMENT_CHECKBOX_XPATH);
	}
	
	/**
	 * This method deselects options on driver view
	 * @lastModifiedBy bshah
	 */	
	public static void selectcustomizedriverview() throws Exception	{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_VIEW_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_MSRP_CHECKBOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_MSRP_CHECKBOX_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_DRIVER_MSRP_CHECKBOX_ID);
	}
	
	/**
	 * This method deselects options on pricing view
	 * @lastModifiedBy bshah
	 */	
	public static void selectcustomizepricingview() throws Exception	{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_MOEDL_POPUP_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING_VIEW_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING__STD_NET_EQUIPMENT_CHECKBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING__STD_NET_EQUIPMENT_CHECKBOX_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_EXPORT_PDF_PRICING__STD_NET_EQUIPMENT_CHECKBOX_XPATH);
	}
	
	/**
	 * This method verifies available options in client  view
	 * @lastModifiedBy bshah
	 */
	public static void verifyclientviewAvailabelalloptions() throws Exception	{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_AND_CONFIG_SPEC_EXPORT_PDF_CLIENT_VIEW_ALL_AVAILABEL_OPTIONS_CHECKBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_AND_CONFIG_SPEC_EXPORT_PDF_CLIENT_VIEW_ALL_AVAILABEL_OPTIONS_CHECKBOX_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_AND_CONFIG_SPEC_EXPORT_PDF_CLIENT_VIEW_ALL_AVAILABEL_OPTIONS_TEXT_XPATH));
	}
	
	/**
	 * This method verifies available options in pricing view
	 * @lastModifiedBy bshah
	 */
	public static void verifypricingviewAvailabelalloptions() throws Exception	{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_AND_CONFIG_SPEC_EXPORT_PDF_PRICING_VIEW_ALL_AVAILABEL_OPTIONS_CHECKBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_AND_CONFIG_SPEC_EXPORT_PDF_PRICING_VIEW_ALL_AVAILABEL_OPTIONS_CHECKBOX_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_PRICE_AND_CONFIG_SPEC_EXPORT_PDF_PRICING_VIEW_ALL_AVAILABEL_OPTIONS_TEXT_XPATH));
	}
	
	/**
	 * This method moves spec to Audit Pending status
	 * @lastModifiedBy bshah
	 */
	public static void movetoPendingAudit() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_AUDIT_STATUUS_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_AUDIT_STATUUS_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS);
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS).getText().equals("Audit Pending");
	}
	
	/**
	 * This method moves spec to ElementReview status
	 * @lastModifiedBy bshah
	 */
	public static void movetoElementReview() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_ELEMENT_REVIEW_STATUS_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_ELEMENT_REVIEW_STATUS_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS);
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS).getText().equals("Element Reviewed / Approved");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS));
	}
	
	/**
	 * This method moves spec to PendingClientApproval POP UP Verifiation
	 * @lastModifiedBy bshah
	 */
	public static void pendingClientApprovalPOPUpverification() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_SPECS_COUNT_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_SPECS_COUNT_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_SPECS_COUNT_XPATH));
		List<WebElement> results = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_HEADER1_XPATH);
		assert results.get(0).getText().contains("Client Name");
		CommonPage.jsGreenBorderHighlighter(results.get(0));
		assert results.get(1).getText().contains("Spec Name");
		CommonPage.jsGreenBorderHighlighter(results.get(1));
		List<WebElement> results1 = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_HEADER2_XPATH);
		assert results1.get(0).getText().contains("Client ID");
		CommonPage.jsGreenBorderHighlighter(results1.get(0));
		assert results1.get(1).getText().contains("Spec ID");
		CommonPage.jsGreenBorderHighlighter(results1.get(1));
		List<WebElement> specdata = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_SPECDATA_XPATH);
		CommonPage.jsGreenBorderHighlighter(specdata.get(0));
		CommonPage.jsGreenBorderHighlighter(specdata.get(1));
		List<WebElement> specdata2 = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_SPECDATA2_XPATH);
		CommonPage.jsGreenBorderHighlighter(specdata2.get(0));
		CommonPage.jsGreenBorderHighlighter(specdata2.get(1));
		//BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_FLAG_FOR_REVIEW_NEW_XPATH);
		//BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_FLAG_FOR_REVIEW_NEW_XPATH);
		//CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_FLAG_FOR_REVIEW_NEW_XPATH));
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_FLAG_FOR_REVIEW_TEXT_XPATH).getText().contains("Flag the review upon approval");
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_COMMENT_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_COMMENT_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_COMMENT_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_INTERNAL_APPROVAL_EMAIL_BOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_INTERNAL_APPROVAL_EMAIL_BOX_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_INTERNAL_APPROVAL_EMAIL_BOX_XPATH));
	}
	
	/**
	 * This method moves spec to PendingClientApproval status
	 * @lastModifiedBy bshah
	 */
	public static void movetoPendingClientApproval() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_STATUS_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_STATUS_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_XPATH);
		pendingClientApprovalPOPUpverification();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_ADD_INTERNAL_APPROVER_ID);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_ADD_INTERNAL_APPROVER_ID);
		BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_ADD_INTERNAL_APPROVER_ID).sendKeys("test");
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_ADD_INTERNAL_APPROVER_LIST_CSS);
		List<WebElement> results = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_ADD_INTERNAL_APPROVER_LIST_CSS);
		results.get(0).click();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_ADD_INTERNAL_APPROVER_ADD_BUTTON_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_ADD_INTERNAL_APPROVER_ADD_BUTTON_ID);
		String commentMsg=CommonPage.randomAlphaNumericString();
		BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_STATUS_COMMENT_BOX_PENDING_APPROVAL_XPATH).sendKeys(commentMsg);
		((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].click();", BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_STATUS_BROWSE_FILE_OPTION_XPATH));
		try {
			BrowserWait.waitUntilElementIsDisplayed(BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_STATUS_DELETE_FILE_OPTION_XPATH));
		} catch (NoSuchElementException e) {
			uploadFile(CommonPage.getTestData("RelativeFilePath"));
			BrowserWait.waitUntilElementIsDisplayed(BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_STATUS_DELETE_FILE_OPTION_XPATH));
			Assert.assertTrue(BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_STATUS_FILE_NAME_XPATH).getText().contentEquals(CommonPage.getTestData("FileName")));
		}
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_MODEL_POPUP_SUBMIT_BUTTON_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_MODEL_POPUP_SUBMIT_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS);
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS).getText().equals("Pending Customer Approval");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS));
		verifyComment(commentMsg);
	}
	
	/**
	 * This method moves spec to Client Declined status
	 * @lastModifiedBy bshah
	 */
	public static void movetoClientDeclined() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_CLIENT_DECLINED_STATUS_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_CLIENT_DECLINED_STATUS_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_CLIENT_DECLINED_MODEL_POPUP_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_CLIENT_DECLINED_MODEL_POPUP_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_CLIENT_DECLINED_MODEL_POPUP_COMMENT_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_CLIENT_DECLINED_MODEL_POPUP_COMMENT_XPATH);
		String commentMsg=CommonPage.randomAlphaNumericString();
		BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_CLIENT_DECLINED_MODEL_POPUP_COMMENT_XPATH).sendKeys(commentMsg);
		((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].click();", BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_STATUS_BROWSE_FILE_OPTION_XPATH));
		try {
			BrowserWait.waitUntilElementIsDisplayed(BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_STATUS_DELETE_FILE_OPTION_XPATH));
		} catch (NoSuchElementException e) {
			uploadFile(CommonPage.getTestData("RelativeFilePath"));
			BrowserWait.waitUntilElementIsDisplayed(BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_STATUS_DELETE_FILE_OPTION_XPATH));
			Assert.assertTrue(BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_STATUS_FILE_NAME_XPATH).getText().contentEquals(CommonPage.getTestData("FileName")));
		}
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_MODEL_POPUP_SUBMIT_BUTTON_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_MODEL_POPUP_SUBMIT_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS);
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS).getText().equals("Client Declined");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS));
		verifyComment(commentMsg);
	}
	
	/**
	 * This method verifies spec in Client approved status
	 * @lastModifiedBy bshah
	 */
	public static void verifyclientapprovedstatus() throws Exception {
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS);
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS).getText().equals("Client Approved");
	}
	
	/**
	 * This method moves and verifies spec in Archiveds status
	 * @lastModifiedBy ksharma
	 */
	public static void movetoArchived() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_ARCHIVE_STATUS_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_ARCHIVE_STATUS_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_ARCHIVE_STATUS_POPUP_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_ARCHIVE_STATUS_POPUP_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_MODEL_POPUP_SUBMIT_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLEAR_FILTERS_XPATH)));
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLEAR_FILTERS_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_CLEAR_FILTERS_XPATH);
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS);
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS).getText().equals("Archived");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS));
	}
	
	/**
	 * This method verifies growl message fleet spec creation
	 * @lastModifiedBy bshah
	 */
	public static void verifyGrowlMessageNewFleet() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH);
		System.out.println(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH).getText());
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH).getText().contains("Fleet specification");
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH).getText().contains("was successfully created.");
	}
	
	/**
	 * This method verifies growl message price and config spec creation
	 * @lastModifiedBy bshah
	 */
	public static void verifyGrowlMessageNewPriceAndConfig() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH);
		System.out.println(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH).getText());
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH).getText().contains("Price & Configuration");
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH).getText().contains("was successfully created.");
	}
	
	/**
	 * This method verifies growl message fleet spec update
	 * @lastModifiedBy bshah
	 */
	public static void verifyGrowlMessageUpdatetFleet() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH);
		System.out.println(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH).getText());
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH).getText().contains("Fleet specification");
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH).getText().contains("was successfully updated.");
	}
	
	/**
	 * This method verifies growl message price and config spec update
	 * @lastModifiedBy bshah
	 */
	public static void verifyGrowlMessageUpdatePriceAndConfigt() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH);
		System.out.println(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH).getText());
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH).getText().contains("Price & Configuration");
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_GROWL_MESSAGE_XPATH).getText().contains("was successfully updated.");
	}
	
	/** 
	* This method Fees and Adjustments elements
	* @lastModifiedBy SSubhadarsini 
	* @throws Exception 
	*/
	public static void validateFessAdjustment() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_FEESADJUSTMENT_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_FEESADJUSTMENT_XPATH));
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_FEESADJUSTMENT_XPATH);
	}
	
	/** 
	* This method Fees and Adjustments elements
	* @lastModifiedBy SSubhadarsini 
	* @throws Exception 
	*/
	public static void validateContarctType() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CONTRACTTYPE_XPATH);
		String ContractType=BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CONTRACTTYPE_XPATH).getText();
		Assert.assertEquals(ContractType, "Contract Type", "Its matching");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CONTRACTTYPE_XPATH));
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CONTRACTDROPDOWN_XPATH);
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CONTRACTTYPEBYDEFAULT_XPATH).getText().trim(),"Lease", "Its matching");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CONTRACTTYPEBYDEFAULT_XPATH));
		List<WebElement>DropDownValues = BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CONTRACTDROPDOWN_XPATH); 
		for(WebElement DropDownValue : DropDownValues) { 
			String columnName=DropDownValue.getText();
			CommonPage.jsGreenBorderHighlighter(DropDownValue);
			System.out.println(columnName);
		}
	}
	
	/** 
	* This method verify custom adjustments elements
	* @lastModifiedBy SSubhadarsini 
	* @throws Exception 
	*/
	public static void validateCustomAdjustment() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CUSTOMADJUSTMENT_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CUSTOMADJUSTMENT_XPATH));
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CUSTOMADJUSTMENT_XPATH).getText().trim(), "Custom Adjustment Fees", "Its matching");
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CUSTOMADJUSTMENTDESC_XPATH,CommonPage.getTestData("ProjectDescription"));
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CUSTOMADJUSTMENTFEES_XPATH,CommonPage.getTestData("CustomAmount"));
		Assert.assertEquals(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CUSTOMADJUSTMENTFEESREFLECTED_XPATH).getText().trim(),CommonPage.getTestData("CustomAmountFEES").trim(), "Its matching");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CUSTOMADJUSTMENTFEESREFLECTED_XPATH));
		BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CUSTOMADJUSTMENTFEES_XPATH).clear();
		BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CUSTOMADJUSTMENTFEES_XPATH).sendKeys("-80");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CUSTOMADJUSTMENTFEES_XPATH));
	}
	

	/** 
	* This method verify client breakdown elements
	* @lastModifiedBy SSubhadarsini 
	* @throws Exception 
	*/
	public static void validateClientBreakdown() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CLIENTBREAKDOWNRESULTS_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CLIENTBREAKDOWNRESULTS_XPATH));
		BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CLIENTBREAKDOWNRESULTS_XPATH).sendKeys(CommonPage.getTestData("ClientBreakDown"));
		BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CLIENTBREAKDOWNCHKBOX_XPATH).click();
	}
	
	/** 
	 * This method verify created messagee
	 * @lastModifiedBy SSubhadarsini 
	 * @throws Exception 
	 */
	public static void validateConfigCreatedMessage() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING__PRICE_AND_CONFIG_SPEC_CREATEDMESSAGE_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING__PRICE_AND_CONFIG_SPEC_CREATEDMESSAGE_XPATH));
		String Message=BrowserAction.getElementText(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING__PRICE_AND_CONFIG_SPEC_CREATEDMESSAGE_XPATH);
		if (Message.contains("successfully created")) {
			System.out.println(Message);
		}
	}

	/** 
	* This method verify custom adjustments elements
	* @lastModifiedBy SSubhadarsini 
	* @throws Exception 
	*/
	public static void validateClientBreakdownSaveAs() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CLIENTBREAKDOWNRESULTS_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_CLIENTBREAKDOWNRESULTS_XPATH));
	}
	
	/** 
	* This method verify copy functions
	* @lastModifiedBy SSubhadarsini 
	* @throws Exception 
	*/
	public static void clickOnCopy() throws Exception {
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIG_XPATH));
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIG_XPATH);
	}
	
	/** 
	* This method verify price and config save
	* @lastModifiedBy ksharma 
	* @throws Exception 
	*/
	public static void validateCopyPriceAndConfigPopUpSave() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUP_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUP_XPATH));
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPCLIENTNUMBER_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPCLIENTNUMBER_XPATH));
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPVEHICLETAB_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPVEHICLETAB_XPATH));
		//BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPUPFITTINGTAB_XPATH);
		//CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPUPFITTINGTAB_XPATH));
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPVEHICLETAB_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPSAVEBTN_XPATH);
		List<WebElement> results = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING__PRICE_AND_CONFIG_SPEC_COPY_DPO_POPUP_XPATH);
		if(results.size()>0){
			BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING__PRICE_AND_CONFIG_SPEC_COPY_DPO_BUTTON_XPATH);
			CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING__PRICE_AND_CONFIG_SPEC_COPY_DPO_BUTTON_XPATH));
			BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING__PRICE_AND_CONFIG_SPEC_COPY_DPO_BUTTON_XPATH);
		}
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/** 
	 * This method verify fees and adjustment amount
	 * @lastModifiedBy SSubhadarsini 
	 * @throws Exception 
	 */
	public static void validateFeesAmountReflected() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_FEESADJUSTMENT_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_FEESADJUSTMENT_XPATH));
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_FEESADJUSTMENT_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING__PRICE_AND_CONFIG_SPEC_AMOUNTREFLECTED_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING__PRICE_AND_CONFIG_SPEC_AMOUNTREFLECTED_XPATH));
		String AmountReflected=BrowserAction.getElementText(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING__PRICE_AND_CONFIG_SPEC_AMOUNTREFLECTED_XPATH);
		if(AmountReflected.equals("-$80.00")) {
			System.out.println("Amount is same as Expected");
		}
	}
	
	/** 
	* This method to enter copy priceandconfig name
	* @lastModifiedBy SSubhadarsini 
	* @throws Exception 
	*/
	public static String enterCopyFleetSpecsNameAndNotes(String fleetSpecName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_NAME_TEXTBOX_ID, fleetSpecName);
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("window.scrollBy(0,1000)");
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPSAVEBTN1_XPATH);
			CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPSAVEBTN1_XPATH));
			BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPSAVEBTN1_XPATH);
		} catch (TimeoutException e) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPSAVEBTN_XPATH);
			CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPSAVEBTN_XPATH));
			BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_SPEC_COPYPRICECONFIGPOPUPSAVEBTN_XPATH);
		}		
		return fleetSpecName;
	}
	
	/**
	 * This method is regarding navigation to Seacrh fleet Spec screen 
	 * @lastModifiedBy nmishra
	 * @throws Exception
	 */
	public static void navigateToSearchFleetSpecScreen() throws Exception {
		System.out.println("navigation to Search Fleet Spec screen");
		BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_LINK_XPATH);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_LINK_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_FLEET_SPEC_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_FLEET_SPEC_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_FLEET_SPEC_BUTTON_XPATH);
	}

	/**
	 * This method is regarding navigation to Manage view pop-up in Seacrh fleet Spec screen
	 * @lastModifiedBy nmishra
	 * @throws Exception
	 */
	public static void navigateToManageViewPopUpScreen() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
	}

	/**
	 * This method is regarding verification of standard view 
	 * @lastModifiedBy nmishra
	 * @throws Exception
	 */
	public static void verifyStandardView() throws Exception {
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_SPEC_VERSION_ID_CHECKBOX_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_SAVE_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_SAVE_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_CONFIRMATION_YES_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_CONFIRMATION_YES_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_CONFIRMATION_YES_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NOTIFICATION_ERROR_XPATH);
		String errorNotification = BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NOTIFICATION_ERROR_XPATH).getText();
		System.out.println(errorNotification);
		Assert.assertEquals(errorNotification, "Standard View cannot be deleted or updated or created");
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NOTIFICATION_ERROR_XPATH);
	}
	
	/**
	 * This method is regarding verification of standard view 
	 * @lastModifiedBy ksharma
	 * @throws Exception
	 */
	public static void verifyStandardViewBO() throws Exception {
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_SPEC_VERSION_ID_CHECKBOX_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_SAVE_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_SAVE_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NOTIFICATION_ERROR_XPATH);
		String errorNotification = BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NOTIFICATION_ERROR_XPATH).getText();
		System.out.println(errorNotification);
		Assert.assertEquals(errorNotification, "Standard View cannot be deleted or updated or created");
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NOTIFICATION_ERROR_XPATH);
	}

	/**
	 * This method is regarding verification of manage view 
	 * @lastModifiedBy nmishra
	 * @throws Exception
	 */
	public static void verifyManageView() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NEW_VIEW_NAME_RADIO_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NEW_VIEW_NAME_RADIO_BUTTON_XPATH);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NEW_VIEW_NAME_INPUT_BOX_XPATH,"Auto_Check");
		List<WebElement> checkBox = BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_POP_UP_CHECKBOX_XPATH);
		ArrayList<String> checkBoxNameList = new ArrayList<String>();
		for (WebElement tdElement : checkBox )
         {
			checkBoxNameList.add(tdElement.getText().trim());
            System.out.println("Check box field Name is = " +tdElement.getText());
            if(!tdElement.findElement(By.tagName("input")).isSelected())
        	    tdElement.findElement(By.tagName("input")).click();
         }
		int size=checkBox.size();
		System.out.println("size of checkbox= " + size);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_SAVE_AND_DEFAULT_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_SAVE_AND_DEFAULT_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NOTIFICATION_ERROR_XPATH);
		List<WebElement> columnName = BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_SEARCH_FLEET_SPEC_TABLE_HEADER_XPATH);
        ArrayList<String> columnNameList = new ArrayList<String>();
		for (WebElement tdElement : columnName )
         {
           columnNameList.add(tdElement.getText().trim());
           ((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", tdElement);
           System.out.println("columnName is = " +tdElement.getText());
           BrowserWait.waitUntilElementIsDisplayed(tdElement);
         }
		int sizeFs=columnName.size();
		System.out.println("size of column= " + sizeFs);
		Assert.assertEquals(sizeFs, size);
		columnNameList.equals(checkBoxNameList);
		checkBoxNameList.equals(columnNameList);
		}
	/**
	 * This method delete custom view in Search Fleet Spec screen
	 * @lastModifiedBy nmishra
	 * @throws Exception
	 */
    public static void deleteCustomView() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();", BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_CLEAR_FILTER_BUTTON_XPATH));
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_DROPDOWN_XPATH);
		BrowserAction.selectDropdownOptionByText(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_DROPDOWN_XPATH, "Auto_Check");
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_DELETE_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_DELETE_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NOTIFICATION_ERROR_XPATH);
	}
    
    /**
	 * This method delete custom view in Search Fleet Spec screen
	 * @lastModifiedBy ksharma
	 * @throws Exception
	 */
    public static void deleteCustomViewBO() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();", BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_CLEAR_FILTER_BUTTON_XPATH));
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_DROPDOWN_XPATH);
		BrowserAction.selectDropdownOptionByText(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_DROPDOWN_XPATH, "Auto_Check");
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_DELETE_BUTTON_BO_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_DELETE_BUTTON_BO_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NOTIFICATION_ERROR_XPATH);
	}

	/**
	 * This method is regarding navigation to Price and Config 
	 * @lastModifiedBy nmishra
	 * @throws Exception
	 */
	public static void navigateToSearchPriceandConfig() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_LINK_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_LINK_XPATH);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_LINK_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_PRICE_CONFIG_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_PRICE_CONFIG_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_PRICE_CONFIG_BUTTON_XPATH);
	}
     /**
	 * This method is regarding manageview of price and config
	 * @lastModifiedBy nmishra
	 * @throws Exception
	 */
	public static void verifyManageViewPriceConfig() throws Exception {
		// Custom View Verification
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NEW_VIEW_NAME_RADIO_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NEW_VIEW_NAME_RADIO_BUTTON_XPATH);
		BrowserAction.enterFieldValue(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NEW_VIEW_NAME_INPUT_BOX_XPATH,"Auto_Check");
		List<WebElement> checkBoxpc = BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_POP_UP_CHECKBOX_XPATH);
		ArrayList<String> checkBoxNameListPc = new ArrayList<String>();
		for (WebElement tdElement : checkBoxpc )
		{
			checkBoxNameListPc.add(tdElement.getText().trim());
			System.out.println("Check box field Name is = " +tdElement.getText());
			if(!tdElement.findElement(By.tagName("input")).isSelected())
				tdElement.findElement(By.tagName("input")).click();
		}
		int sizeP=checkBoxpc.size();
		System.out.println("size of checkbox= " + sizeP);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_SAVE_AND_DEFAULT_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_NOTIFICATION_ERROR_XPATH);
		List<WebElement> columnNamePc = BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_SEARCH_FLEET_SPEC_TABLE_HEADER_XPATH);
		ArrayList<String> columnNameListPc = new ArrayList<String>();
		for (WebElement tdElement : columnNamePc )
		{
			columnNameListPc.add(tdElement.getText().trim());
			((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", tdElement);
			System.out.println("columnName is = " +tdElement.getText());
			BrowserWait.waitUntilElementIsDisplayed(tdElement);
		}
		int sizePc=columnNamePc.size();
		System.out.println("size of column= " + sizePc);
		Assert.assertEquals(sizePc, sizeP);
		columnNameListPc.equals(checkBoxNameListPc);
		checkBoxNameListPc.equals(columnNameListPc);
	}

	/**
	 * This method is regarding deletion of manage view in price and config screen 
	 * @lastModifiedBy nmishra
	 * @throws Exception
	 */
	public static void deleteCustomViewPriceConfig() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();", BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_CLEAR_FILTER_BUTTON_XPATH));
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_DROPDOWN_XPATH);
		BrowserAction.selectDropdownOptionByText(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_DROPDOWN_XPATH, "Auto_Check");
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_DELETE_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_DELETE_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_DELETE_BUTTON_XPATH);
	}
	
	/**
	 * This method is regarding deletion of manage view in price and config screen 
	 * @lastModifiedBy ksharma
	 * @throws Exception
	 */
	public static void deleteCustomViewPriceConfigBO() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();", BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_CLEAR_FILTER_BUTTON_XPATH));
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_DROPDOWN_XPATH);
		BrowserAction.selectDropdownOptionByText(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_STANDARD_VIEW_DROPDOWN_XPATH, "Auto_Check");
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_DELETE_BUTTON_BO_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_DELETE_BUTTON_BO_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_MANAGE_VIEW_DELETE_BUTTON_BO_XPATH);
	}
	
	/**
	 * This method is triggering queue results export to excel format 
	 * @lastModifiedBy bshah
	 * @throws Exception
	 */
	public static void verifyExportToExcelSearchPage(String section, String className) throws Exception {
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_EXPORT_RESULTS_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_EXPORT_RESULTS_XPATH));	
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_EXPORT_RESULTS_XPATH);
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"), section, className);
	}
	
	/**
	 * This method is initiating export to excel of a spec from serach paget 
	 * @lastModifiedBy ksharma
	 * @throws Exception
	 */
	public static void verifyClickOnExportToExcelSearchPage(String section, String className) throws Exception {
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_INDIVIDUAL_ACTION_MENU_ID));
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserAction.hoverOverElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_INDIVIDUAL_ACTION_MENU_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_EXPORT_TO_EXCEL_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_EXPORT_TO_EXCEL_XPATH));
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_PRICE_AND_CONFIG_EXPORT_TO_EXCEL_XPATH);
		excelExportVerification(section,className);
	}
		
	/**
	 * This method verifies export completion.
	 * @lastModifiedBy ksharma
	 */
	public static void excelExportVerification(String section, String className) throws Exception{
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"), section, className);
	}
	
	/** 
	* This method verify the presence of Flagged for Review red bar for all the specs, when color of bar is provided as argument> "None" to validate record is not flagged for review
	* @param colorCode, columnCount
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void validateFlaggedForReviewForAllRows(int columnCount, String colorCode) throws Exception {
		String[] splitText = BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_NO_OF_RECORDS_XPATH).getText().split("\\s+");
		int noOfRows= Integer.parseInt(splitText[0].trim());
		System.out.println("Total rows: "+noOfRows);
		if(noOfRows>100) {
			noOfRows=100;
		}
		for (int i=1; i<= noOfRows; i ++) {
			OrderingVehicleConfigFleetSpecsPage.validateFlaggedForReviewStatusRedBar(columnCount,i,colorCode);
		}
	}
	
	/** 
	 * This method validates price protection icon is displayed on search page 
	 * @lastModifiedBy lpadaliya 
	 * @throws Exception 
	 */
	public static void verifyPriceProtectionTableCellIsDisplayed(int rowNo, String columnName) throws Exception {
		int column=0;
		boolean flag=false;
		List<WebElement> headerColumns = BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_TABLE_HEADER_XPATH);
		for (WebElement col: headerColumns) {
			((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", col);
			column++;
			if(col.getText().contains(columnName)) {
				flag=true;
				break;
			}
		}
		if(flag==true) {
			WebElement tableCell= WebDriverAccess.getDriver().findElement(BrowserAccess.getLocator("_XPATH", OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_TABLE_CONTAINER_XPATH.getValue().replaceAll("rowNo", Integer.toString(rowNo)).replaceAll("column", Integer.toString(column))));
			((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", tableCell);
			CommonPage.jsGreenBorderHighlighter(tableCell.findElement(By.tagName("img")));
			Assert.assertTrue(tableCell.findElement(By.tagName("img")).isDisplayed());
		} else {
			Assert.assertTrue(flag, "table cell is not displayed");
		}
	}
	
	/** 
	 * This method selects the required view
	 * @lastModifiedBy lpadaliya 
	 * @throws Exception 
	 */
	public static void selectView(String viewName) throws Exception {
		boolean viewAvailable=false;
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_SEARCH_SPEC_MANAGEVIEW_XPATH);
		for(WebElement view: BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_SEARCH_SPEC_MANAGEVIEW_VIEWS_LIST_XPATH)) {
			if(view.getText().equalsIgnoreCase(viewName)) {
				view.click();
				viewAvailable=true;
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				break;
			}
		}
		Assert.assertTrue(viewAvailable, "Incorrect view is passed in parameter");
	}
	
	/**
	 * This method enters Client data in filter
	 * @lastModifiedBy lpadaliya
	 */
	public static void enterValueInClientTextBox() throws Exception {
		CommonPage.getElementOrderObject().getVehicleTabObject().setModel(CommonPage.getTestData("Model"));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_CLIENT_TEXTBOX_XPATH)));
		System.out.println("Client Id: " + CommonPage.getTestData("ClientNumber"));
		BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_CLIENT_TEXTBOX_XPATH).clear();
		BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_CLIENT_TEXTBOX_XPATH).sendKeys(CommonPage.getTestData("ClientNumber"));
		BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_CLIENT_NUMBER_XPATH).click();
	}
	
	/**
	 * This method moves spec to PendingClientApproval status for ext user
	 * @lastModifiedBy lpadaliya
	 */
	public static void movetoPendingClientApprovalExtUser() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_STATUS_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_STATUS_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_POPUP_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_MODEL_POPUP_SUBMIT_BUTTON_ID);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_MODEL_POPUP_SUBMIT_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS);
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS).getText().equals("Pending Customer Approval");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS));
	}
	
	/**
	 * This method upload a file using Robot class
	 * @lastModifiedBy lpadaliya
	 * @param relativeFilePath
	 */
	public static void uploadFile(String relativeFilePath) throws Exception {
		String filePath= System.getProperty("user.dir")+ relativeFilePath;
		System.out.println("File Path: "+ filePath);
		StringSelection ss = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	

	/**
	 * This method select the first spec and verify the comment
	 * @lastModifiedBy lpadaliya
	 * @param comment
	 * @throws Exception 
	 */
	private static void verifyComment(String comment) throws Exception {
		selectFleetSpec(1);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		try{
			BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_COMMENTS_BUTTON_XPATH);
			BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_COMMENTS_BUTTON_XPATH).click();
		} catch (TimeoutException e) {
			selectFleetSpec(1);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_COMMENTS_BUTTON_XPATH).click();
		}
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_COMMENTS_XPATH);
		try{
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_COMMENTS_XPATH)));
			CommonPage.jsGreenBorderHighlighter(BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_COMMENTS_XPATH).get(0));
		} catch (TimeoutException e) {
			CommonPage.jsGreenBorderHighlighter(BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_COMMENTS_XPATH).get(0));	
		}
		Assert.assertTrue(BrowserAccess.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_COMMENTS_XPATH).get(0).getText().trim().contentEquals(comment));
		BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_COMMENTS_CLOSE_ICON_XPATH).click();
		BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_DETAIL_PAGE_CANCEL_XPATH).click();
		try {
			BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_LEAVE_WITHOUT_SAVE_XPATH).click();
			OrderingCommonPage.checkGlobalSpinnerPopUp();
		} catch (ElementNotInteractableException e) {
			OrderingCommonPage.checkGlobalSpinnerPopUp();
		}
	}

	/**
	 * This method moves a spec to on hold status
	 * @lastModifiedBy lpadaliya
	 * @param comment
	 * @throws Exception 
	 */
	public static void placeSpecOnHold() throws Exception {
		selectActionFromList(1,"Place On Hold");	
		BrowserWait.waitUntilElementIsDisplayed(BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_ONHOLD_MODAL_POPUP_XPATH));
		String commentMsg=CommonPage.randomAlphaNumericString();
		BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_STATUS_COMMENT_BOX_ONHOLD_XPATH).sendKeys(commentMsg);
		BrowserAccess.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_FLEET_SPEC_UPDATE_SPEC_STATUS_BUTTON_PLACE_ONHOLD_XPATH).click();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		verifyComment(commentMsg);
	}

}

