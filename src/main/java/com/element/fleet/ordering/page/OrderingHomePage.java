package com.element.fleet.ordering.page;

import org.testng.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.ExcelUtil;
import com.element.fleet.ordering.enums.OrderingFOFleetPreferencesPageEnum;
import com.element.fleet.ordering.enums.OrderingHomePageEnum;
import com.element.fleet.ordering.enums.OrderingVehicleConfigFleetSpecsPageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;
import com.ge.capital.rainbow.webdriver.WebDriverWaits;
import com.google.common.collect.Ordering;

/**
 * This class contains all the actions related to Front Office ordering home page
 */
public class OrderingHomePage {	

	/**
	 * This method waits for the Home page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForHomePage() throws Exception {
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_BREAKDOWN_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_ORDERING_LOGO_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_LOGOUT_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_PENDING_ACTIONS_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_PENDING_ACTIONS_FLEET_SPEC_TAB_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_PENDING_ACTIONS_PRICE_AND_CONFIG_TAB_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_TAB_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_PENDING_ACTIONS_FLEET_SPEC_QUEUE_FILTERS_ID);
	}
	
	/**
	 * This method waits for the Home page to load.
	 * @lastModifiedBy ssrivastava
	 * @throws Exception
	 */
	public static void waitForHomePageMLO() throws Exception {
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_BREAKDOWN_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_ORDERING_LOGO_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_LOGOUT_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ID);
	}

	/**
	 * This method clicks on the client breakdown at home page in front office.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickClientBreakdown() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_BREAKDOWN_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_BREAKDOWN_CSS);
		BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_BREAKDOWN_CSS);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_BREAKDOWN_CSS);
	}

	/**
	 * This method changes the client i.e. enters the client id on the search box and clicks on the first displayed client.
	 * @lastModifiedBy mkhairanar
	 * @throws Exception
	 */
	public static void changeClient() throws Exception {
		if(CommonPage.getTestData("UserRole").contains("External Full Access")) {
			OrderingHomePage.changeClientForExternalUser();
		} else {
			BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEBAR_CLIENT_SEARCH_TEXTBOX_ID);
			BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_SIDEBAR_CLIENT_SEARCH_TEXTBOX_ID);
			BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_SIDEBAR_CLIENT_SEARCH_TEXTBOX_ID);
			BrowserAction.clickandClear(OrderingHomePageEnum.ORDEING_HOME_SIDEBAR_CLIENT_SEARCH_TEXTBOX_ID);
			System.out.println("Client: "+ CommonPage.getTestData("ClientNumber"));
			if(Objects.isNull(CommonPage.getTestData("ClientNumber"))) {
				throw new OrderingErrorOccured("Client number can't be null");
			} else {
				BrowserAction.enterFieldValue(OrderingHomePageEnum.ORDEING_HOME_SIDEBAR_CLIENT_SEARCH_TEXTBOX_ID, CommonPage.getTestData("ClientNumber"));
				BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEBAR_CLIENT_RESULT_SUGGESTED_TEXTBOX_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_SIDEBAR_CLIENT_RESULT_SUGGESTED_TEXTBOX_CSS);
				BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_SIDEBAR_CLIENT_RESULT_SUGGESTED_TEXTBOX_CSS);
				BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEBAR_CLIENT_RESULT_SUGGESTED_TEXTBOX_CSS);
				BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEBAR_CLOSE_BUTTON_ID);
			}
		}
	}

	/**
	 * This method clicks on the cancel button and handles the popup on the order page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickCancelOrderPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_ORDER_CANCEL_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_ORDER_CANCEL_CSS);
		BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_ORDER_CANCEL_CSS);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_ORDER_CANCEL_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_ORDER_CANCEL_OK_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_ORDER_CANCEL_OK_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_ORDER_CANCEL_OK_BUTTON_XPATH);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_ORDER_CANCEL_OK_BUTTON_XPATH);
	}

	public static void waitForBatchTemplateDownloadPageToLoad() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_BATCH_TEMPLATE_DOWNLOAD_HEADING_CSS);
	}

	public static void batchTemplatePresent() throws Exception {
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_SIDEMENU_BATCH_SELECT_TEMPLATE_XPATH);
		List<String> batchTemplates=BrowserAccess.getElementsText(OrderingHomePageEnum.ORDERING_HOME_SIDEMENU_BATCH_SELECT_TEMPLATE_DETAILS_XPATH);
		List<String> batchProcessing=new ArrayList<>();
		for(String batchName:batchTemplates) {
			batchProcessing.add(batchName);
		}
		List<String> batchTemplateName = new ArrayList<>();
		String batchTemplateNames[]= {"Factory Orders- No Upfit","Factory Orders- Upfit","Dealer Orders- No Upfit","Dealer Orders- Upfit","Stock Orders- No Upfit","Stock Orders- Upfit"};
		for(String templateNames:batchTemplateNames) {
			batchTemplateName.add(templateNames);
		}
		Assert.assertTrue(batchTemplateName.equals(batchProcessing));
	}

	public static void validateBatchProcessingExcelDownload(String className) throws Exception {
		List<WebElement> batchTypes=BrowserAccess.getElements(OrderingHomePageEnum.ORDERING_HOME_SIDEMENU_BATCH_SELECT_TEMPLATE_DETAILS_XPATH);
		for (WebElement batchTypeName :batchTypes) {
			batchTypeName.click();
			OrderingSummaryPage.clickExportButton("Batch", className);
		}
	}

	/**
	 * This method waits for the Fleet Preferences page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void fleetPrefrencesPageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_FLEET_PREFRENCES_HEADING_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_FLEET_PREFRENCES_HEADING_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_FLEET_PREFRENCES_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_COMMUNICATIONS_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_PROACTIVE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_DEALER_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_TITLE_AND_REGISTRATION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_BILLING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_DRIVER_XPATH);	
	}

	/**
	 * This method verifies that logout is successful
	 * @throws Exception
	 */
	public static void verifyLogOutFunctionality() throws Exception {
		OrderingHomePage.clickOnLogoutButton();
		OrderingHomePage.verifyLogOutSuccessful();
		XcelerateHomePage.navigateToXcelerateWindow();
		XcelerateHomePage.signOutFromXcelerate();
	}

	/**
	 * This method clicks on the close summary page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnCloseSummaryPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_FRONT_OFFICE_CLOSE_SUMMARY_PAGE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDERING_FRONT_OFFICE_CLOSE_SUMMARY_PAGE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDERING_FRONT_OFFICE_CLOSE_SUMMARY_PAGE_XPATH);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_FRONT_OFFICE_CLOSE_SUMMARY_PAGE_XPATH);
	}

	/**
	 * This method clicks on the logout button.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnLogoutButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_FRONT_OFFICE_LOGOUT_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDERING_FRONT_OFFICE_LOGOUT_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDERING_FRONT_OFFICE_LOGOUT_BUTTON_ID);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_FRONT_OFFICE_LOGOUT_BUTTON_ID);
	}

	/**
	 * This method verifies logout is successful.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyLogOutSuccessful() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_FRONT_OFFICE_LOGOUT_MESSAGE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDERING_FRONT_OFFICE_LOGOUT_MESSAGE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDERING_FRONT_OFFICE_LOGOUT_MESSAGE_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_FRONT_OFFICE_LOGOUT_MESSAGE_XPATH), "You have been logged out of Ordering", "Logout message not verified");
	}

	/**
	 * This method validates the home page labels.
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void orderingHomePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_PENDING_ACTIONS_HEADING_CSS), "My Pending Actions", "FO My Pending Actions message not matched");
	}
	
	/**
	 * This method waits for side menu option of front office application and then selects it
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void selectSideMenuOption(String option) throws Exception {
		Object element = null;
		switch(option) {
			case "Ordering": element = OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_OPTION_CSS;
				break;
			case "Vehicle Configuration": element = OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_OPTION_CSS;
				break;
			case "Fleet Preferences": element = OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_FLEET_PREFERENCES_OPTION_CSS;
				break;
			default: throw new InvalidSwitchCaseException("Invalid side menu option selected");
		}
		System.out.println("FO Side Menu: " + option);
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(element);
		BrowserAction.click(element);
	}

	/**
	 * This method validates that Order side menu options are loaded
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void orderingSideMenuOptionLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_TITLE_LABEL_CLASS);
		BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_TITLE_LABEL_CLASS);
		BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_TITLE_LABEL_CLASS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_CREATE_ORDER_LINK_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_BATCH_TEMPLATE_DOWNLOAD_HYPERLINK_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_WORK_IN_PROCESS_ORDERS_HYPERLINK_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_MY_ORDERS_HYPERLINK_XPATH);
		if(CommonPage.getTestData("UserRole").equals("Ordering Super User")) { 
			BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_MANAGER_APPROVAL_MAINTENANCE_HYPERLINK_XPATH);
		}
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_MANAGER_APPROVAL_QUEUE_HYPERLINK_XPATH);
	}

	/**
	 *  This method validates that  ordering side section labels are loaded
	 *  @lastModifiedBy adhawale
	 * @throws Exception
	 */
	public static void orderingSideSectionLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_TITLE_LABEL_CLASS), "Ordering", "Side menu Ordering title label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_CREATE_ORDER_LINK_CSS), "Create Order", "Ordering section Create Order label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_BATCH_TEMPLATE_DOWNLOAD_HYPERLINK_ID), "Batch Template Download", "Ordering section Batch Template Download label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_WORK_IN_PROCESS_ORDERS_HYPERLINK_ID), "Work In Process Orders", "Ordering section Work In Process Orders label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_MY_ORDERS_HYPERLINK_XPATH), "My Orders", "Ordering section My Orders label not matched");
		if(CommonPage.getTestData("UserRole").equals("Ordering Super User")) {
			CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_MANAGER_APPROVAL_MAINTENANCE_HYPERLINK_XPATH), "Manager Approval Maintenance", "Ordering section Manager Approval Maintenance label not matched");
		}
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_MANAGER_APPROVAL_QUEUE_HYPERLINK_XPATH), "Manager Approval Queue", "Ordering section Manager Approval Queue label not matched");
	}

	/**
	 * This method clicks on provide side menu options
	 * @lastModifiedBy usha naidu
	 * @param option
	 * @throws Exception
	 */
	public static void selectOrderingMenuOption(String option) throws Exception {
		System.out.println("Ordering Links: " + option);
		switch(option) {
			case "Create Order": 
				BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_CREATE_ORDER_LINK_CSS);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.checkAlertPopUp();
				OrderingCommonPage.verifyNoAlertPopUpDispalyed();
				break;
			case "Batch Template Download": 
				BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_BATCH_TEMPLATE_DOWNLOAD_HYPERLINK_ID);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.checkAlertPopUp();
				OrderingCommonPage.verifyNoAlertPopUpDispalyed();
				break;
			case "Work In Process Orders": 
				BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_WORK_IN_PROCESS_ORDERS_HYPERLINK_ID);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.checkAlertPopUp();
				OrderingCommonPage.verifyNoAlertPopUpDispalyed();
				break;
			case "My Orders": 
				BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_MY_ORDERS_HYPERLINK_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_MY_ORDERS_HYPERLINK_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_MY_ORDERS_HYPERLINK_XPATH);		
				BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_MY_ORDERS_HYPERLINK_XPATH);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.checkAlertPopUp();
				OrderingCommonPage.verifyNoAlertPopUpDispalyed();
				break;
			case "Manager Approval Maintenance": 
				BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_MANAGER_APPROVAL_MAINTENANCE_HYPERLINK_XPATH);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.checkAlertPopUp();
				OrderingCommonPage.verifyNoAlertPopUpDispalyed();
				break;
			case "Manager Approval Queue": 
				BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_MANAGER_APPROVAL_QUEUE_HYPERLINK_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_MANAGER_APPROVAL_QUEUE_HYPERLINK_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_MANAGER_APPROVAL_QUEUE_HYPERLINK_XPATH);
				BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_MANAGER_APPROVAL_QUEUE_HYPERLINK_XPATH);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.checkAlertPopUp();
				OrderingCommonPage.verifyNoAlertPopUpDispalyed();
				break;
			default: throw new InvalidSwitchCaseException("Invalid ordering menu option selected");
		}
	}

	/**
	 * This method validates vehicle configuration label
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void vehicleConfigurationSideSectionLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_TITLE_LABEL_CLASS), "Vehicle Configuration", "Side menu Vehicle Configuration title label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_FLEET_SPECS_LINK_XPATH), "Fleet Specification", "Vehicle Configuration section Fleet Specs label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_PRICE_AND_CONFIGURATION_LINK_XPATH), "Price & Configuration", "Vehicle Configuration section Price & Configuration label not matched");
	}

	public static void vehicleConfigurationSideSectionIsLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_TITLE_LABEL_CLASS);
		BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_TITLE_LABEL_CLASS);
		BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_TITLE_LABEL_CLASS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_FLEET_SPECS_LINK_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_PRICE_AND_CONFIGURATION_LINK_XPATH);
	}

	public static void selectVehicleConfigurationMenuOption(String option) throws Throwable {
		System.out.println("Vehicle Configuration: " + option);
		switch(option) {
			case "Fleet Specification": 
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_FLEET_SPECS_LINK_XPATH), "Fleet Specification", "Fleet Specification label not matched");
				CommonPage.clickHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_FLEET_SPECS_LINK_XPATH));
				BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FLEET_SPECS_LINK_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FLEET_SPECS_LINK_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FLEET_SPECS_LINK_XPATH);
				break;
			case "Price & Configuration": 
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_PRICE_AND_CONFIGURATION_LINK_XPATH), "Price & Configuration", "Price & Configuration label not matched");
				CommonPage.clickHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_PRICE_AND_CONFIGURATION_LINK_XPATH));
				BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FOR_SAVED_PRICE_AND_CONFIG_LINK_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FOR_SAVED_PRICE_AND_CONFIG_LINK_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FOR_SAVED_PRICE_AND_CONFIG_LINK_XPATH);	
				break;
			case "Search for Fleet Specifications": 
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FLEET_SPECS_LINK_XPATH), "Search for Fleet Specifications", "Search for Fleet Specifications label not matched");
				PDFReporter.takeExtraScreenshot();
				CommonPage.clickHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FLEET_SPECS_LINK_XPATH));
				break;
			case "Search for Saved Price & Configuration": 
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FOR_SAVED_PRICE_AND_CONFIG_LINK_XPATH), "Search for Saved Price & Configuration", "Search for Saved Price & Configuration label not matched");
				PDFReporter.takeExtraScreenshot();
				CommonPage.clickHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FOR_SAVED_PRICE_AND_CONFIG_LINK_XPATH));
				break;
			case "Add Price and Configuration": 
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_PRICE_AND_CONFIG_LINK_XPATH), "Add Price and Configuration", "Add Price and Configuration label not matched");
				PDFReporter.takeExtraScreenshot();
				CommonPage.clickHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_PRICE_AND_CONFIG_LINK_XPATH));
				break;
			case "Add Fleet Specification": 
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_FLEET_SPEC_LINK_XPATH), "Add Fleet Specification", "Add Fleet Specification label not matched");
				PDFReporter.takeExtraScreenshot();
				CommonPage.clickHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_FLEET_SPEC_LINK_XPATH));
				break;
			default: throw new InvalidSwitchCaseException("Invalid vehicle configuration menu option selected");
		}
		OrderingCommonPage.checkAlertPopUp();
	}	

	
	/**
	 * This method closes change client  breakdown page
	 *  @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void closeChangeClient() throws Exception {
		WebElement changeClientCloseButton=BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEBAR_CLOSE_BUTTON_ID);
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("arguments[0].click();", changeClientCloseButton);
	}

	/**
	 * This method will select client from drop down
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void changeClientFromDropDown() throws Exception {
		CommonPage.waitForElementToLoad(OrderingHomePageEnum.ORDEING_HOME_CHANGE_CLIENT_DROPDOWN_XPATH, CommonPage.getTestData("WaitTime"));
		Select slt = new Select(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_CHANGE_CLIENT_DROPDOWN_XPATH));
		if(!slt.getFirstSelectedOption().equals(CommonPage.getTestData("CustomColumn1")))
			CommonPage.selectDropDownValue(OrderingHomePageEnum.ORDEING_HOME_CHANGE_CLIENT_DROPDOWN_XPATH, CommonPage.getTestData("CustomColumn1"), "Client Name");
	}

	/**
	 * This method will go to create order page
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goToCreateOrder() throws Exception {
		OrderingHomePage.selectSideMenuOption("Ordering");
		CommonPage.waitForElementToLoad(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_CREATE_ORDER_LINK_CSS, CommonPage.getTestData("WaitTime"));
		OrderingHomePage.selectOrderingMenuOption("Create Order");
	}

	/**
	 * This  method will change client from drop down for external user
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void changeClientBreakDownFromDropDown() throws Exception {
		OrderingHomePage.clickClientBreakdown();
		OrderingHomePage.changeClientFromDropDown();
		OrderingHomePage.closeChangeClient();
	}

	/**
	 * This method will click on Fleet Policies link
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnFleetPolicies() throws Exception {
		CommonPage.waitForElementToLoad(OrderingHomePageEnum.ORDERING_HOME_SIDEMENU_FLEET_POLICIES_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_SIDEMENU_FLEET_POLICIES_ID), "Fleet Policies link not present");
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_SIDEMENU_FLEET_POLICIES_ID);
	}

	/**
	 * This method will verify fleet policies sub list title
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyFleetPoliciesOptionListTitle() throws Exception {
		CommonPage.waitForElementToLoad(OrderingHomePageEnum.ORDERING_HOME_FLEET_POLICIES_OPTION_LIST_TITLE_XPATH, CommonPage.getTestData("WaitTime"));
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_FLEET_POLICIES_OPTION_LIST_TITLE_XPATH), "Fleet Policies", "Fleet Policies sub list title not present");
	}

	/**
	 * This method will click on Capping Rule link and verify the titles and sub titles from capping rule page
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goToFOCappingRule() throws Exception {
		OrderingHomePage.clickOnCappingRule();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingHomePage.verifyCappingRulesTitleAndSubTitle();
	}

	/**
	 * This method will click on Capping Rule link in FO
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnCappingRule() throws Exception {
		CommonPage.waitForElementToLoad(OrderingHomePageEnum.ORDERING_HOME_FLEET_POLICIES_CAPPING_RULE_XPATH, CommonPage.getTestData("WaitTime"));
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_FLEET_POLICIES_CAPPING_RULE_XPATH), "Fleet Policies link not present");
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_FLEET_POLICIES_CAPPING_RULE_XPATH);
	}

	/**
	 * This method will verify Capping Rules title
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyCappingRulesTitleAndSubTitle() throws Exception {
		CommonPage.waitForElementToLoad(OrderingHomePageEnum.ORDERING_HOME_FLEET_POLICIES_CAPPING_SMART_RULE_TITLE_XPATH, CommonPage.getTestData("WaitTime"));
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_FLEET_POLICIES_CAPPING_SMART_RULE_TITLE_XPATH), "Capping Smart Rules", "Capping Smart Rules sub title is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_FLEET_POLICIES_CAPPING_RULE_TITLE_XPATH), "Capping Rules", "Capping Rules sub title is not matched");
	}

	/**
	 * This method will click on rule id in FO
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnRule(String ruleId) throws Exception {
		String ruleIdXpath = OrderingHomePageEnum.ORDERING_HOME_FLEET_POLICIES_CAPPING_RULE_ID_XPATH.getValue().replace("$RuleId#", ruleId);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(ruleIdXpath));
		WebDriverAction.click(By.xpath(ruleIdXpath));
	}

	/**
	 * This method will verify capping rule details page title in FO
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyCappingRulesPageTitles() throws Exception {
		CommonPage.waitForElementToLoad(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_DETAILS_TITLE_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_DETAILS_TITLE_ID), "Capping Rule Details", "Capping Rule Details title is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_BACK_TO_CAPPING_RULE_PAGE_ID), "Back to Capping Rules", "Back to Capping Rules title is not matched");
	}

	/**
	 * This method will verify all fields available on Capping Rule Details Page
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyAllFieldsFromCappingRuleDetailsPage(String rule) throws Exception {
		switch(rule) {
			case "Capping Smart Rule":
				CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_SMART_RULE_NAME_XPATH), "Smart Rule container is not displayed");
				break;
			case "Capping Rule":
				CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_TEXTAREA_XPATH), "Capping Rule textarea is not displayed");
				break;
			default: throw new OrderingErrorOccured("Invalid rule passed");
		}
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_EFFECTIVE_DATE_ID), "Capping Rule effective date is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_EFFECTIVE_UNTIL_DATE_NAME), "Capping Rule effective until date is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_CORP_NAME), "Capping Rule corp is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_CONTRACT_TYPE_NAME), "Capping Rule Contract type is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_ORDER_TYPE_NAME), "Capping Rule Order Type is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_PRODUCT_CLASS_NAME), "Capping Rule Product class is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_YEAR_NAME), "Capping Rule Year is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_MAKE_NAME), "Capping Rule Make is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_MODEL_NAME), "Capping Rule Model is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_STATE_NAME), "Capping Rule State is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_CLIENT_NUMBER_ID), "Capping Rule Client Number is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_INFLIGHT_CHECKBOX_NAME), "Capping Rule Inflight is not displayed");
	}

	/**
	 * This method will verify all fields data available on Capping Rule Details Page
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyAllFieldsDataFromCappingRuleDetailsPage(String rule) throws Exception {
		switch(rule) {
			case "Capping Smart Rule":
				Assert.assertEquals(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_SMART_RULE_NAME_XPATH).getText(), CommonPage.getTestData("RuleName"), "Rule Name is not matched");
				break;
			case "Capping Rule":
				Assert.assertEquals(BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_TEXTAREA_XPATH).getText(), CommonPage.getTestData("RuleName"), "Rule Name is not matched");
				break;
			default: throw new OrderingErrorOccured("Invalid rule passed");
		}
		CommonPage.verifyInputTextFieldValue(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_EFFECTIVE_DATE_ID, OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("StartDate"), "Effective Date");
		CommonPage.verifyInputTextFieldValue(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_EFFECTIVE_UNTIL_DATE_NAME, "", "Effective Until Date");
		CommonPage.verifyInputTextFieldValue(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_CORP_NAME, CommonPage.getTestData("CorpCode"), "Corp Code");
		CommonPage.verifyInputTextFieldValue(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_CONTRACT_TYPE_NAME, OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("ContractType"), "Contract Type");
		CommonPage.verifyInputTextFieldValue(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_ORDER_TYPE_NAME, OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("OrderType"), "Order Type");
		CommonPage.verifyInputTextFieldValue(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_PRODUCT_CLASS_NAME, OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("ProductClass"), "Product Class");
		CommonPage.verifyInputTextFieldValue(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_YEAR_NAME, CommonPage.getTestData("Year"), "Year");
		CommonPage.verifyInputTextFieldValue(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_MAKE_NAME, CommonPage.getTestData("Make"), "Make");
		CommonPage.verifyInputTextFieldValue(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_MODEL_NAME, CommonPage.getTestData("Model"), "Model");
		CommonPage.verifyInputTextFieldValue(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_STATE_NAME, CommonPage.getTestData("TitleState"), "State");
		List<WebElement> ele = BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_CLIENT_NUMBER_ID);
		for(WebElement elem : ele)
			if(!(elem.getText().contains(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyClientNumber"))))
				throw new OrderingErrorOccured("Client number is not matched");
		OrderingBOBusinessMaintainedTablesPage.verifyDisableElement(OrderingHomePageEnum.ORDERING_HOME_CAPPING_RULE_INFLIGHT_CHECKBOX_NAME, "InFlight Orders");
	}

	/**
	 * This method will click on Back to Capping rule page link
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnBackToCappingRuleLink() throws Exception {
		CommonPage.waitForElementToLoad(OrderingHomePageEnum.ORDERING_HOME_BACK_TO_CAPPING_RULE_PAGE_ID, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_BACK_TO_CAPPING_RULE_PAGE_ID);
	}

	/**
	 * This method willgo to capping rule page in FO and verify titles and rule data
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyTitlesAndDataInTablesInFo() throws Exception {
		OrderingHomePage.clickOnCappingRule();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingHomePage.verifyCappingRulesTitleAndSubTitle();
		OrderingBOBusinessMaintainedTablesPage.searchByRuleName(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.verifyUnarchivedRuleValueFromTable();
	}

	/**
	 * This method will click on rule go to rule view page and verify all fields and fields data
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnRuleFromTableAndGoToViewPageAndVerifyAllFieldsAndData() throws Exception {
		OrderingHomePage.clickOnRule(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"));
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingHomePage.verifyCappingRulesPageTitles();
		OrderingHomePage.verifyAllFieldsFromCappingRuleDetailsPage(CommonPage.getTestData("RuleType"));
		OrderingHomePage.verifyAllFieldsDataFromCappingRuleDetailsPage(CommonPage.getTestData("RuleType"));
	}

	/**
	 * This method changes the client for external user
	 * @lastModifiedBy mkhairnar
	 * @throws Exception
	 */
	public static void changeClientForExternalUser() throws Exception {
		CommonPage.updateClientDropdown();
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEBAR_CLOSE_BUTTON_ID);
	}

	/**
	 * This method verifies Manager Approval Queue is not present in FO Ordering
	 * @lastModifiedBy Sweety Agrawal
	 * @throws Exception
	 */
	public static void verifyManagerApprovalQueueNotDisplayed() throws Exception {
		BrowserVerify.verifyElementIsNotDisplayed(OrderingHomePageEnum.ORDEING_HOME_MANAGER_APPROVAL_QUEUE_HYPERLINK_XPATH);
	}
	
	/**
	 * This method vehicle config link
	 * @lastModifiedBy ksharma
	 */
	public static void clickVehicleConfigurationLink() throws Exception {
		try {
			JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
			js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_LINK_XPATH));
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_LINK_XPATH)));
			BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_LINK_XPATH);
			BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_LINK_XPATH);
		} catch (Exception e) {
			JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
			js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_LINK_CSS));
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_LINK_CSS);
			BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_LINK_CSS);
		}
	}

	/**
	 * This method vehicle config link with search fleet spec
	 * @lastModifiedBy bshah
	 */
	public static void clickVehicleConfigurationFleetSpecsAndSearchFleetSpecsLink() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_FLEET_SPECS_LINK_XPATH);
			CommonPage.waitForElementToLoad(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_FLEET_SPECS_LINK_XPATH, CommonPage.getTestData("WaitTime"));
			BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_FLEET_SPECS_LINK_XPATH);
			BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FLEET_SPECS_LINK_XPATH);
			CommonPage.waitForElementToLoad(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FLEET_SPECS_LINK_XPATH, CommonPage.getTestData("WaitTime"));
			BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FLEET_SPECS_LINK_XPATH);
		}catch (ElementClickInterceptedException e) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FLEET_SPECS_LINK_XPATH);
			CommonPage.waitForElementToLoad(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FLEET_SPECS_LINK_XPATH, CommonPage.getTestData("WaitTime"));
			BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FLEET_SPECS_LINK_XPATH);;
		}     
	}

	/**
	 * This method vehicle config link with add fleet spec
	 * @lastModifiedBy lpadaliya
	 */
	public static void clickVehicleConfigurationAddFleetSpecsLink() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_FLEET_SPECS_LINK_XPATH)));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_FLEET_SPECS_LINK_XPATH)));
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_FLEET_SPECS_LINK_XPATH);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_FLEET_SPECS_LINK_XPATH);
	}

	/**
	 * This method vehicle config link with search price and config spec
	 * @lastModifiedBy bshah
	 */
	public static void clickVehicleConfigurationsSavedPriceAndConfigurationLink() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(ElementClickInterceptedException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_PRICE_AND_CONFIGURATION_LINK_XPATH)));
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_PRICE_AND_CONFIGURATION_LINK_XPATH);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_PRICE_AND_CONFIGURATION_LINK_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FOR_SAVED_PRICE_AND_CONFIG_LINK_XPATH);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FOR_SAVED_PRICE_AND_CONFIG_LINK_XPATH);
	}

	/**
	 * This method vehicle config link with add price and config spec
	 * @lastModifiedBy KSharma
	 */	
	public static void clickVehicleConfigurationAddPriceandConfigSpecsLink()throws Exception { 
		int count = 0;
		while(count < 4) {
			try {
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(ElementClickInterceptedException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_PRICE_AND_CONFIGURATION_LINK_XPATH)));
				BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_PRICE_AND_CONFIGURATION_LINK_XPATH);
				BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_PRICE_AND_CONFIGURATION_LINK_XPATH);
				BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_PRICE_AND_CONFIG_SPECS_LINK_XPATH);
				BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_PRICE_AND_CONFIG_SPECS_LINK_XPATH);
				count = count + 4;
			} catch (StaleElementReferenceException e) {
				count = count + 1;
			}
		}
	}

	/**
	 * This method verify ordering fleet preference label
	 * @lastModifiedBy lpadaliya
	 */	
	public static void verifyOrderingFleetPreferencesLabel() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_FLEET_PREFERENCES_LABEL_XPATH);
		String orderingActualLabel = BrowserAction.getElement(OrderingHomePageEnum.ORDERING_FLEET_PREFERENCES_LABEL_XPATH).getText();
		Assert.assertEquals(orderingActualLabel, OrderingHomePageEnum.ORDERING_FLEET_PREFERENCES_VALUE.getValue(), "Ordering Fleet Preferences not match");				
	}

	/**
	 * This method click fleet preference icon
	 * @lastModifiedBy lpadaliya
	 */
	public static void clickFleetPrefrencesIcon() throws Exception {
		verifyOrderingFleetPreferencesLabel();
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_FLEET_PREFRENCES_LOGO_XPATH);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_FLEET_PREFRENCES_LOGO_XPATH);
	}
	
	/**
	 * This method creates a fleet spec order with basic info and validations
	 * @throws Throwable 
	 * @lastModifiedBy hjimenez
	 */
	public static void createOrder() throws Throwable {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingHomePage.selectOrderingMenuOption("Create Order");
		OrderingCommonPage.checkAlertPopUp();
		OrderingStartHerePage.waitForStartHerepage();
		OrderingStartHerePage.enterOrderUnitNumber();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDriverPage.enterDriverData();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingVehiclePage.enterDetailsVehicleData();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingBillingAndRegistrationPage.selectLeasingTermIfDisplayed();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingCommonPage.checkAlertPopUp();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingCommonPage.checkAlertPopUp();
		OrderingSummaryPage.waitForSummaryPage();
		OrderingSummaryPage.clickSubmit();
		OrderingSummaryPage.confirmSubmit();
		OrderingCommonPage.checkAlertPopUp();
		OrderingSummaryPage.waitForPopUpResultBox();
		OrderingSummaryPage.verifySuccessfulSubmissionPopUp();
		OrderingSummaryPage.waitForSummaryPage();
		OrderingHomePage.clickOnCloseSummaryPage();
		CommonPage.loadXMLParameterToTestData("LoggerLogNumber" + 1, CommonPage.getElementOrderObject().getLogNumber());
		PDFReporter.takeExtraScreenshot();
	}
	
	/**
	 * This method click On Hold Specs Tab
	 * @lastModifiedBy hjimenez
	 */
	public static void clickOnHoldSpecsTab() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_TAB_CSS);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_TAB_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_SPECID_INPUT_ID);
	}
	
	/**
	 * This method click Price and Configuration Tab
	 * @lastModifiedBy hjimenez
	 */
	public static void clickPriceAndConfigTab() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_PENDING_ACTIONS_PRICE_AND_CONFIG_TAB_CSS);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_PENDING_ACTIONS_PRICE_AND_CONFIG_TAB_CSS);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method click Fleet Spec Tab
	 * @lastModifiedBy hjimenez
	 */
	public static void clickFleetSpecTab() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_PENDING_ACTIONS_FLEET_SPEC_TAB_CSS);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_PENDING_ACTIONS_FLEET_SPEC_TAB_CSS);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method click advanced search button
	 * @lastModifiedBy hjimenez
	 */
	public static void clickOnHoldSpecsAdvancedSearch() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_ADVANCED_SEARCH_BUTTON_ID);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_ADVANCED_SEARCH_BUTTON_ID);
	}
	
	/**
	 * This method verifies columns are listed are per the requirement 
	 * @param String [] expectedColumns, int columnIndex, int lastColumnIndex
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyColumnHeadersValues(String[] expectedColumns, int columnIndex, int lastColumnIndex) throws Exception {
		int csvIndex = 0;
		if (BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_TABLE_HEADERS_XPATH).size() > 0) {
		while (!BrowserAccess.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_TABLE_HEADERS_XPATH).get(columnIndex).getText().isEmpty()) {
			String columnHeaderText = BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_TABLE_HEADERS_XPATH).get(columnIndex).getText().replace("\n", " ");
			System.out.println(columnHeaderText);
			Assert.assertEquals(columnHeaderText, expectedColumns[csvIndex], "Header value does not match or is in wrong order..." + columnHeaderText);
			columnIndex++;
			csvIndex++;
			if (BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_TABLE_HEADERS_XPATH).get(columnIndex).getText().equalsIgnoreCase("Trim")) {
				WebElement lastColumn = BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_TABLE_HEADERS_XPATH).get(lastColumnIndex); 
				CommonPage.javascriptScrollUntilElementIsVisible(lastColumn);
			}
		}
		}else {
			System.out.println("No Data Was Displayed ");
		}
	}
	
	/**
	 * This method verifies columns are sorted by Pending on Hold Approval column
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyDefaultSorting() throws Exception {
		List <WebElement> pendingOnHoldValues = BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_PENDINGONHOLD_DATA_ROW_XPATH);
		List <Integer> values = new ArrayList<>();
		if (pendingOnHoldValues.size() > 0) {
		for (WebElement pendingOnHoldValue : pendingOnHoldValues) {
			values.add(Integer.valueOf(pendingOnHoldValue.getText()));
		}
		System.out.println(values);
		Assert.assertEquals(Ordering.natural().reverse().isOrdered(values), true, "Values are not default sorted by pending on Hold value");
		}else {
			System.out.println("No pending hold value on table");}
		}
	
	/**
	 * This method verifies expected search inputs are displayed on price and config tab
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifySearchInputsOnHoldSpecsTab() throws Exception {
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_SPECID_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_PREVIOUS_SPEC_STATUS_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_ONHOLD_REASON_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_ONHOLD_SUBREASON_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_PRICEPROTECT_INDICATOR_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_MANUFACTURER_INPUT_ID);
		OrderingHomePage.verifyCommonSearchInputsPendingActions();
	}
	
	/**
	 * This method verifies expected search inputs are displayed on fleet spec tab
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifySearchInputsOnFleetSpecTab() {
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_FLEET_SPEC_PRICE_INCREASE_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_FLEET_SPEC_REVISION_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_FLEET_SPEC_FLEETSPECID_INPUT_ID);
		OrderingHomePage.verifyCommonSearchInputsPendingActions();
	}
	
	public static void verifyCommonSearchInputsPendingActions() {
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_SPECNAME_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_SPECSTATUS_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_MODELYEAR_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_MAKE_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_MODEL_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_TRIM_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_UPFITINDICATOR_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_MODELCODE_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_PENDINGAPPROVAL_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_LASTUPDATED_DATE_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_LASTUPDATED_BY_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_FLEETSPECSOURCE_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_CLIENTBREAKDOWN_INPUT_ID);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_GEORESTRICTION_DATE_INPUT_ID);
	}
	
	/**
	 * This method verifies the search functionality for on hold specs tab
	 * @param valuesToRemove (number of values to remove from list)
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifySearchFunctionalityOnHoldSpecs(int valuesToRemove) throws Exception {
		List <WebElement> firstRowData = BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_FIRSTDATA_ROW_XPATH);
		List <String> searchValues = new ArrayList<>();
		List <WebElement> inputFields = BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_INPUTS_XPATH);
		for (WebElement data : firstRowData) {
			searchValues.add(data.getText());
		}
		System.out.println(searchValues);
		for (int i = 0; i < valuesToRemove; i++) {
			searchValues.remove(0);
		}
		System.out.println(searchValues);
		int i = 0;
		for (String searchValue : searchValues) {
			inputFields.get(i).sendKeys(searchValue.replaceAll("/n", " ").trim());
			System.out.println("Text send to input:" + searchValue);
			i++;
		}
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SEARCH_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		List <WebElement> firstRow = BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_FIRSTDATA_ROW_XPATH);
		Assert.assertEquals(firstRow.size() > 1, true, "Search did not show results");
	}
	
	/**
	 * This method clicks the export button and keeps the file in downloads folder
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyExportFuntionality() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_EXPORT_BUTTON_ID);
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_EXPORT_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifies the exported CSV contains the expected headers
	 * @lastModifiedBy hjimenez
	 * @param expectedFileName, expectedCSVHeaders
	 * @throws Exception
	 */
	public static void verifyHeadersFromExportCSV(String expectedFileName, String [] expectedCSVHeaders) throws Throwable {
		String fileName = System.getProperty("user.home") + "\\Downloads\\" + expectedFileName;
		String [] actualCSVHeaders = ExcelUtil.getCSVRows(fileName).get(0).split(",");
		System.out.println("Actual headers in CSV file: " + ExcelUtil.getCSVRows(fileName).get(0));
		System.out.println("Expected headers: " + Arrays.toString(expectedCSVHeaders));
		Assert.assertEquals(expectedCSVHeaders, actualCSVHeaders, "Actual and expected headers differ");
	}
	
	/**
	 * This method searches by price and config name 
	 * @param priceConfigSpecName
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void searchPriceConfigName(String priceAndConfigName) throws Throwable {
		CommonPage.enterTextToInputField(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_SPECS_SPECNAME_INPUT_ID, priceAndConfigName);
		System.out.println(priceAndConfigName);
		validateSearchResults();
	
	}
	
	/**
	 * This method searches by fleet name on Fleet spec tab
	 * @param fleetSpecName
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void searchFleetSpecName(String fleetName) throws Throwable {
		CommonPage.enterTextToInputField(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_SPECNAME_INPUT_ID, fleetName);
		System.out.println(fleetName);
		validateSearchResults();
	}
	
	/**
	 * This method validates search show results on pending action tables
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void validateSearchResults() throws Throwable {
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SEARCH_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		List <WebElement> firstRow = BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_FIRSTDATA_ROW_XPATH);
		Assert.assertEquals(firstRow.size() > 1, true, "Search did not show results");
	}
	
	/**
	 * This method searches by price and config name and validates spec is not displayed
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyPriceAndConfigSpecIsNotDisplayed(String priceAndConfigName) throws Throwable {
		CommonPage.enterTextToInputField(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_SPECS_SPECNAME_INPUT_ID, priceAndConfigName);
		System.out.println("Spec to verify that is not shown: " + priceAndConfigName);
		verifyNoResultsAreDisplayed();
	}
	
	/**
	 *This method searches fleet spec name and validates spec is not displayed on fleet spec tab
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyFleetSpecIsNotDisplayed(String specName) throws Throwable {
		CommonPage.enterTextToInputField(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_SPECNAME_INPUT_ID, specName);
		System.out.println("Spec to verify that is not shown: " + specName);
		verifyNoResultsAreDisplayed();
	}
	
	/**
	 * This method clicks search button and verifies no results are shown
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyNoResultsAreDisplayed() throws Throwable {
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SEARCH_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		List <WebElement> firstRow = BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_ON_HOLD_SPECS_FIRSTDATA_ROW_XPATH);
		Assert.assertEquals(firstRow.size() == 0, true, "Search did not show results");
	}
	
	/**
	 * This method moves to home page by pressing the home logo
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void goBackToHomePage() throws Throwable {
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_HOME_LOGO_ID);
		OrderingHomePage.waitForHomePage();
	}
	
	/**
	 * This method approves price and config spec from price and config tab 
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void approveSingleSpec() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_APPROVE_BUTTON_ID);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_APPROVE_BUTTON_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method rejects a spec either price and config or fleet spec
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void rejectSingleSpec() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_REJECT_BUTTON_ID);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_REJECT_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CONFIRM_BUTTON_ID);
		OrderingHomePage.uploadFileToRejectModal(CommonPage.getTestData("FileNameValid"));
		if (BrowserAction.getElements(OrderingHomePageEnum.ORDEING_HOME_MY_PENDING_ACTIONS_REJECT_MODAL_REASON_TEXTAREA_ID).size()>0) {
			CommonPage.enterTextToInputField(OrderingHomePageEnum.ORDEING_HOME_MY_PENDING_ACTIONS_REJECT_MODAL_REASON_TEXTAREA_ID, "Reject Test Message");
		}
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CONFIRM_BUTTON_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method rejects spec without uploading an attachment 
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void rejectSingleSpecWithoutAttachment() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_REJECT_BUTTON_ID);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_REJECT_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CONFIRM_BUTTON_ID);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CONFIRM_BUTTON_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifies invalid files are not uploaded to reject modal 
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyInvalidFilesErrorInModal() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_REJECT_BUTTON_ID);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_REJECT_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CONFIRM_BUTTON_ID);
		OrderingHomePage.uploadFileToRejectModal(CommonPage.getTestData("FileNameInvalid"));
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_INVALID_FILE_XPATH);
		String errorMessage = BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_INVALID_FILE_XPATH).getText();
		System.out.println("Error Message: " + errorMessage);
		Assert.assertEquals(errorMessage.trim().contains("Invalid file format and/or file size (20MB limit)"), true, "Wrong message displayed");
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CANCEL_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();	
	}
	
	/**
	 * This method verifies uploaded file can be removed from modal by clicking the trash can icon
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verifyAttachmentCanBeRemovedFromModal() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_REJECT_BUTTON_ID);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_REJECT_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CONFIRM_BUTTON_ID);
		OrderingHomePage.uploadFileToRejectModal(CommonPage.getTestData("FileNameValid"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_TRASH_ICON_XPATH);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_TRASH_ICON_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_TRASH_ICON_XPATH);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CANCEL_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method uploads a file in the reject popup confirmation
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void uploadFileToRejectModal(String fileName) throws Throwable {
		String filePath = System.getProperty("user.dir") + "\\resources\\SampleFilesPendingActionsModal\\"  + fileName;
		System.out.println("File Name: " + fileName);
		WebDriverAccess.getDriver().findElement(By.className("browse-file")).sendKeys(filePath);
	}
	
	/**
	 * This method rejects modal and uploads multiple attachments
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void rejectSpecWithMultipleAttachments() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_REJECT_BUTTON_ID);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_REJECT_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CONFIRM_BUTTON_ID);
		String [] fileNames = CommonPage.getTestData("FileNames").split("\\|");
		OrderingHomePage.uploadMultipleFilesToRejectModal(fileNames);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CONFIRM_BUTTON_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifies maximum number of attachments
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void verfiyMaxNumberOfAttachments() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_REJECT_BUTTON_ID);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_ACTIONMENU_REJECT_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CONFIRM_BUTTON_ID);
		String [] fileNames = CommonPage.getTestData("MaxFileNames").split("\\|");
		OrderingHomePage.uploadMultipleFilesToRejectModal(fileNames);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_INVALID_FILE_XPATH);
		String errorMessage = BrowserAction.getElement(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_INVALID_FILE_XPATH).getText();
		System.out.println("Error Message:" + errorMessage);
		Assert.assertEquals(errorMessage.trim().contains("Number of files exceeds the maximum limit of 10"), true, "Wrong message displayed");
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CANCEL_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method uploads multiple files in the reject popup confirmation
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void uploadMultipleFilesToRejectModal(String [] fileNames) throws Throwable {
		for (String fileName : fileNames) {
			System.out.println("File Name: " + fileName);
			String filePath = System.getProperty("user.dir") + "\\resources\\SampleFilesPendingActionsModal\\"  + fileName;
			WebDriverAccess.getDriver().findElement(By.className("browse-file")).sendKeys(filePath);
		}
	}
	
	/**
	 * This method sorts price and confg table by price and config id, newest on top
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void sortPriceAndConfigTableByID() throws Throwable {
		BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_TABLE_HEADERS_XPATH).get(1).click();;
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_TABLE_HEADERS_XPATH).get(1).click();;
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method clicks select all button on price and config screen
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void clickSelectAllButton() throws Throwable {
		List <WebElement> selectCheckboxes =	BrowserAction.getElements(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_SELECT_ALL_CLASS);
		((JavascriptExecutor)WebDriverAccess.getDriver()).executeScript("arguments[0].click();", selectCheckboxes.get(0));
	}
	
	/**
	 * This method clicks the approve in any of the 3 tabs for pending actions
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void approveBulkTab() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_BULK_APPROVE_BUTTON_ID);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_BULK_APPROVE_BUTTON_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method rejects a spec in bulk mode
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void rejectBulkSpecTab() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_BULK_REJECT_BUTTON_ID);
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_BULK_REJECT_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CONFIRM_BUTTON_ID);
		OrderingHomePage.uploadFileToRejectModal(CommonPage.getTestData("FileNameValid"));
		if (BrowserAction.getElements(OrderingHomePageEnum.ORDEING_HOME_MY_PENDING_ACTIONS_REJECT_MODAL_REASON_TEXTAREA_ID).size()>0) {
			CommonPage.enterTextToInputField(OrderingHomePageEnum.ORDEING_HOME_MY_PENDING_ACTIONS_REJECT_MODAL_REASON_TEXTAREA_ID, "Reject Test Message");
		}
		BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_PENDING_ACTIONS_PRICE_CONFIG_MODAL_CONFIRM_BUTTON_ID);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}

	/**
	 * This method vehicle config link with add price and config spec
	 * @lastModifiedBy lpadaliya
	 */	
	public static void clickBOVehicleConfigurationAddPriceandConfigSpecsLink()throws Exception {
		try {
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(ElementClickInterceptedException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_PRICE_AND_CONFIG_SPECS_LINK_BO_XPATH)));
			BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_PRICE_AND_CONFIG_SPECS_LINK_BO_XPATH);
			BrowserAccess.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_PRICE_AND_CONFIG_SPECS_LINK_BO_XPATH).click();
		} catch (ElementClickInterceptedException e) {
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(ElementClickInterceptedException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_PRICE_AND_CONFIG_SPECS_LINK_BO_XPATH)));
			BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_PRICE_AND_CONFIG_SPECS_LINK_BO_XPATH);
			BrowserAccess.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_PRICE_AND_CONFIG_SPECS_LINK_BO_XPATH).click();
		}
	}

	/**
	 * This method vehicle config link with search price and config spec
	 * @lastModifiedBy lpadaliya
	 */	
	public static void clickBOVehicleConfigurationSearchPriceandConfigSpecsLink()throws Exception {
		try {
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(ElementClickInterceptedException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FOR_SAVED_PRICE_AND_CONFIG_LINK_XPATH)));
			BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FOR_SAVED_PRICE_AND_CONFIG_LINK_XPATH);
			BrowserAccess.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FOR_SAVED_PRICE_AND_CONFIG_LINK_XPATH).click();
		} catch (ElementClickInterceptedException e) {
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(ElementClickInterceptedException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FOR_SAVED_PRICE_AND_CONFIG_LINK_XPATH)));
			BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FOR_SAVED_PRICE_AND_CONFIG_LINK_XPATH);
			BrowserAccess.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_SEARCH_FOR_SAVED_PRICE_AND_CONFIG_LINK_XPATH).click();
		}
	}

	/**
	 * This method vehicle config link with add fleet spec
	 * @lastModifiedBy KSharma
	 */	
	public static void clickBOVehicleConfigurationAddFleetSpecsLink()throws Exception { 
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(ElementClickInterceptedException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_FLEET_SPECS_LINK_XPATH)));
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_FLEET_SPECS_LINK_XPATH);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_FLEET_SPECS_LINK_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(ElementClickInterceptedException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_FLEET_SPECS_LINK_XPATH)));
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_FLEET_SPECS_LINK_XPATH);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_VEHICLE_CONFIGURATION_ADD_FLEET_SPECS_LINK_XPATH);
	}
	
	/**
	 * This method clear filters in my pending action tab
	 * @lastModifiedBy hjimenez
	 */	
	public static void clearFilters()throws Exception { 
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_MY_PENDING_ACTIONS_ID);
		BrowserAction.click(OrderingHomePageEnum.ORDEING_HOME_MY_PENDING_ACTIONS_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}	
}