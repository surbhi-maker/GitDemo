package com.element.fleet.ordering.page;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.HashMap;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.ExcelUtil;
import com.element.fleet.ordering.enums.OrderingBOBusinessMaintainedTablesEnum;
import com.element.fleet.ordering.enums.OrderingBOOnOrderQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingBOQueuePageEnum;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.NoIfElseBlockMatchedException;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserAssert;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;
import com.ge.capital.rainbow.webdriver.WebDriverVerify;
import com.ge.capital.rainbow.webdriver.WebDriverWaits;
import com.google.common.collect.Ordering;

public class OrderingBOBusinessMaintainedTablesPage {
	
	static HashMap<String, String> ruleDetails = new HashMap<>();
	
	/**
	 * This method clicks on the Business Maintained Table options.
	 * @lastModifiedBy MKhairanar
	 * @param option
	 * @throws Exception
	 */
	public static void selectBusinessMaintainedTableOption(String option) throws Exception {
		Object element = null;
		switch(option) {
			case "Additional Options": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADDITIONAL_OPTIONS_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Asset Code": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Dealer Installed Options": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DEALER_INSTALLED_OPTIONS_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Element FIN/FAN Codes": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_CODES_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Emissions": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISSIONS_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Factory Order Range": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Feature Toggle": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "FIN/FAN": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FINFAN_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Client Assignment": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMNET_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Hard-coded Instructions": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_HARD_CODED_INSTRUCTION_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Incentives": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVES_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Master Purchase Order Status": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MASTER_PURCHASE_ORDER_STATUS_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Order Hold": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Price Protection": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Scheduler": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Status Tracker Setting": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_STATUS_TRACKER_SETTING_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Sweeper Results": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SWEEPER_RESULTS_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Transporters": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_TRANSPORTERS_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Upfitter Preferences": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_UPFITTER_PREFERENCES_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Year Make Model": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_YEAR_MAKE_MODEL_OPTION_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Capping Rules": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_XPATH;
				System.out.println("BMT: " + option);
			    break;
			case "Customer Delivering Dealer Assignment Rules": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDD_ASSIGNMENT_RULE_XPATH;
			    System.out.println("BMT: " + option);
		        break;	
			case "Legal Document and Upload": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Customer Ordering Dealer Assignment Rules": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ASSIGNMENT_RULE_XPATH;
			    System.out.println("BMT: " + option);
			    break;	
			case "Element Ordering Dealer Assignment Rules": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EOD_EODAR_LINK_XPATH;
				System.out.println("BMT: " + option);
				break;
			case "Element Delivering Dealer Assignment Rules": element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_EDDAR_SIDE_MENU_XPATH;
				System.out.println("BMT: " + option);
				break;
			default: throw new OrderingErrorOccured("Invalid Business Maintained Tables option selected");
		}
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(element)));
		System.out.println("Business Maintained Tables: " + option);
		BrowserAction.click(element);
		OrderingCommonPage.checkAlertPopUp();
	}
	
	public static void clickOnTheFirstOptionOfListIfAvailable(String section) throws Throwable {
		System.out.println(section + " Maintenance");
		switch(section) {
			case "Additional Options":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.additionalOptionsPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.additionalOptionsPageInEditModePageValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.additionalOptionsPageLoaded();
				}
				break;
			case "Asset Code":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.assetCodePageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.assetCodePageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.assetCodePageLoaded();
				}
				break;
			case "Dealer Installed Options":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.dealerInstalledOptionsPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.dealerInstalledOptionsPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.dealerInstalledOptionsPageLoaded();
				}
				break;
			case "Element FIN/FAN Codes":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.elementFINFANPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.elementFINFANPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.elementFINFANCodesPageLoaded();
				}
				break;
			case "Emissions":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.emissionsPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.emissionsPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.emissionsPageLoaded();
				}
				break;
			case "Factory Order Range":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.factoryOrderRangeTablePageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.factoryOrderRangeTablePageInEditValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.factoryOrderNumberRangeTablePageLoaded();
				}
				break;
			case "Feature Toggle":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.featureTogglesPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.featureTogglesPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.featureTogglesPageLoaded();
				}
				break;
			case "FIN/FAN":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.finfanCodesPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.finfanCodesPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.finfanCodesPageLoaded();
				}
				break;
			case "Client Assignment":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstRecordFromViewTable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.fleetAssignmentPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.fleetAssignmentPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.fleetAssignmentPageLoaded();
				}
				break;
			case "Hard-coded Instructions":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.hardCodedInstructionsPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.hardCodedInstructionsPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.hardcodedInstructionsPageLoaded();
				}
				break;
			case "Incentives":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.incentivesPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.incentivesPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.incentivesPageLoaded();
				}
				break;
			case "Master Purchase Order Status":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.masterPurchaseOrderStatusPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.masterPurchaseOrderStatusPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.masterPurchaseOrderStatusPageLoaded();
				}
				break;
			case "Order Hold":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.orderHoldPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.orderHoldPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.orderHoldPageLoaded();
				}
				break;
			case "Price Protection":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.priceProtectionPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.priceProtectionPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToPriceProtectionQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.priceProtectionPageLoaded();
				}
				break;
			case "Scheduler":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.schedulerPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.schedulerPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.schedulerPageLoaded();
				}
				break;
			case "Sweeper Results":
				OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable();
				break;
			case "Transporters":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.transporterPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.transporterPageInEditModeValidation();
					System.out.println(section + " Maintenance");
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.transportersPageLoaded();
				}
				break;
			case "Upfitter Preferences":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.upfitterPreferencesPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.upfitterPreferencesPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
					OrderingBOBusinessMaintainedTablesPage.clickOnBackToQueueViewLink();
					OrderingBOBusinessMaintainedTablesPage.upfitterPreferencesPageLoaded();
				}
				break;
			case "Year Make Model":
				if(OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable()) {
					OrderingCommonPage.checkAlertPopUp();
					OrderingBOBusinessMaintainedTablesPage.yearMakeModelPageInEditModeLoaded();
					OrderingBOBusinessMaintainedTablesPage.yearMakeModelPageInEditModeValidation();
					PDFReporter.takeExtraScreenshot();
				}
				break;
			default: throw new InvalidSwitchCaseException("Invalid Business Maintained Tables option selected");
		}
	}
	
	/**
	 * This method waits for all the sections of BMT to be loaded
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void businessMaintainedTablePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_HEADING_XPATH), "Business Maintained Tables", "Business Maintained Tables page heading not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADDITIONAL_OPTIONS_OPTION_XPATH), "Additional Options","Unable to match Additional Options option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_OPTION_XPATH), "Asset Code", "Unable to match Asset Code option ");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DEALER_INSTALLED_OPTIONS_OPTION_XPATH), "Dealer Installed Options", "Unable to match Dealer Installed Options option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_CODES_OPTION_XPATH), "Element FIN/FAN Codes", "Unable to match Element FIN/FAN Codes option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISSIONS_OPTION_XPATH), "Emissions", "Unable to match Emmisions option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_OPTION_XPATH), "Factory Order Range", "Unable to match Factory Order Range option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_OPTION_XPATH), "Feature Toggle", "Unable to match Feature Toggle option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FINFAN_OPTION_XPATH), "FIN/FAN Codes", "Unable to match FIN/FAN Codes option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMNET_OPTION_XPATH), "Client Assignment", "Unable to match Fleet Assignment option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_HARD_CODED_INSTRUCTION_OPTION_XPATH), "Hard-coded Instructions", "Unable to match Hard-coded Instructions option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVES_OPTION_XPATH), "Incentives", "Unable to match Incentives option");			
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MASTER_PURCHASE_ORDER_STATUS_OPTION_XPATH), "Master Purchase Order Status", "Unable to match Master Purchase Order Status option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_OPTION_XPATH), "Order Hold", "Unable to match Order Hold option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_OPTION_XPATH), "Price Protection", "Unable to match Price Protection option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_OPTION_XPATH), "Scheduler", "Unable to match Scheduler option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SWEEPER_RESULTS_OPTION_XPATH), "Sweeper Results", "Unable to match Sweeper Results option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_TRANSPORTERS_OPTION_XPATH), "Transporters", "Unable to match Transporters option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_UPFITTER_PREFERENCES_OPTION_XPATH), "Upfitter Preferences", "Unable to match Upfitter Preferences option");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_YEAR_MAKE_MODEL_OPTION_XPATH), "Year Make Model", "Unable to match Year Make Model option"); 
    }
	
	public static void businessMaintainedTablePageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADDITIONAL_OPTIONS_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DEALER_INSTALLED_OPTIONS_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_CODES_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FINFAN_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMNET_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_HARD_CODED_INSTRUCTION_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVES_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MASTER_PURCHASE_ORDER_STATUS_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SWEEPER_RESULTS_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_TRANSPORTERS_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_UPFITTER_PREFERENCES_OPTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_YEAR_MAKE_MODEL_OPTION_XPATH);
    }
	
	public static void additionalOptionsPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void additionalOptionsPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Additional Options", "Additional Options title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
	}
	
	public static void assetCodePageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void assetCodePageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Asset Code", "Asset Code title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
	}
	
	public static void dealerInstalledOptionsPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void dealerInstalledOptionsPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Dealer Installed Options", "Dealer Installed Options title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
	}
	
	public static void elementFINFANCodesPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void elementFINFANCodesPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Element FIN/FAN Codes", "Element FIN/FAN Codes title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
	}
	
	/**
	 * This method waits for the emissions page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void emissionsPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	/**
	 * This method validates the label and buttons for emissions page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void emissionsPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Emissions", "Emissions title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageDeleteButton();
	}
	
	public static void factoryOrderNumberRangeTablePageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void factoryOrderNumberRangeTablePageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Factory Order Range", "Factory Order Range did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
	}
	
	public static void featureTogglesPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void featureTogglesPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Feature Toggles", "Feature Toggles title did not match with the expected string");
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_TOGGLECOLUMNDROPDOWN_XPATH, OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_EXPORTBTN_XPATH, OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_ADDNEWENTRYBTN_XPATH);
	}
	
	public static void finfanCodesPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void finfanCodesPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "FIN/FAN Codes", "FIN/FAN Codes title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
	}
	
	public static void fleetAssignmentPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FILTER_SEARCH_FIELD_XPATH);
	}
	
	public static void fleetAssignmentPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Client Assignment", "Client Assignment title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyButtonsOnLduPage();
	}
	
	public static void hardcodedInstructionsPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void hardcodedInstructionsPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Hard-coded Instructions", "Hard-coded Instructions title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageDeleteButton();
	}
	
	public static void incentivesPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void incentivesPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Incentives", "Incentives title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
	}
	
	public static void masterPurchaseOrderStatusPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void masterPurchaseOrderStatusPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Master Purchase Order Status", "Master Purchase Order Status title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
	}
	
	public static void orderHoldPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void orderHoldPagelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Order Hold", "Order Hold title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageDeleteButton();
	}
	
	public static void priceProtectionPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void priceProtectionPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Price Protection", "Price Protection title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
	}
	
	public static void schedulerPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void schedulerPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Scheduler", "Scheduler title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_RELEASE_XPATH, OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_HOLD_XPATH);
	}
	
	public static void sweeperResultsPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void sweeperResultsPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Sweeper Results", "Sweeper Results title did not match with the expected string");
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_TOGGLECOLUMNDROPDOWN_XPATH,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_CLEARFILTERBTN_XPATH,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_EXPORTBTN_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_RERUN_BTN_XPATH);
	}
	
	public static void transportersPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void transportersPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Transporters", "Transporters title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
	}
	
	public static void upfitterPreferencesPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void upfitterPreferencesPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Upfitter Preferences", "Upfitter Preferences title did not match with the expected string");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtPageButtons();
	}
	
	public static void yearMakeModelPageLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void yearMakeModelPageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH), "Year Make Model", "Year Make Model title did not match with the expected string");
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_TOGGLECOLUMNDROPDOWN_XPATH,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_CLEARFILTERBTN_XPATH,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_EXPORTBTN_XPATH);
	}
	
	public static void additionalOptionsPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS,OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_ENTITY_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID,OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_ADD_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID,OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);

	}
	
	public static void additionalOptionsPageInEditModePageValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Additional Option Maintenance", "Additional Option Maintenance label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	public static void assetCodePageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void assetCodePageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Asset Code Maintenance", "Asset Code Maintenance label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	public static void dealerInstalledOptionsPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void dealerInstalledOptionsPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Dealer Installed Option Maintenance", "Dealer Installed Option Maintenance label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	public static void elementFINFANPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void elementFINFANPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Element FIN/FAN Codes", "Element FIN/FAN Codes label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	/**
	 * This method waits for the emissions page to open in edit mode.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void emissionsPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	/**
	 * This method validate emissions page labels and buttons.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void emissionsPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Emission Details", "Emission Details label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	public static void factoryOrderRangeTablePageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void factoryOrderRangeTablePageInEditValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Factory Order Range Table", "Factory Order Range Table label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	
	public static void featureTogglesPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void featureTogglesPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Feature Toggles - Maintenance", "Feature Toggles label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	public static void finfanCodesPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void finfanCodesPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "FIN/FAN", "FIN/FAN label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	public static void fleetAssignmentPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void fleetAssignmentPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Client Assignment Maintenance", "Client Assignment Maintenance label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	public static void hardCodedInstructionsPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void hardCodedInstructionsPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Hard-coded Instructions", "Fleet Assignment Maintenance label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_CANCEL_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SIGNOUTBTN_XPATH);
	}
	
	public static void incentivesPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void incentivesPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Incentive Table", "Incentive Table label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	public static void masterPurchaseOrderStatusPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void masterPurchaseOrderStatusPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Master Purchase Order Status", "Master Purchase Order Status label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	public static void orderHoldPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void orderHoldPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Order Hold Maintenance", "Order Hold label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	public static void priceProtectionPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_INSURANCE_SECTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PRICE_PROTECTION_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_PRICE_PROTECTION_QUEUE_VIEW_LINK_XPATH);
	}
	
	public static void priceProtectionPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PRICE_PROTECTION_HEADING_XPATH), "Edit Price Protection", "Price Protection label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_PRICE_PROTECTION_QUEUE_VIEW_LINK_XPATH), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PRICE_PROTECTION_PAGE_SAVE_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PRICE_PROTECTION_PAGE_DELETE_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PRICE_PROTECTION_PAGE_CANCEL_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SIGNOUTBTN_XPATH);
	}
	
	public static void schedulerPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void schedulerPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Scheduler", "Scheduler label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	public static void sweeperResultsPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void sweeperResultsPageInEditModeLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Sweeper Results", "Sweeper Results label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
	}
	
	public static void transporterPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void transporterPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Transporter Maintenance", "Transporter Maintenance label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		OrderingBOBusinessMaintainedTablesPage.verifyBmtEditPageButtons();
	}
	
	public static void upfitterPreferencesPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void upfitterPreferencesPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Upfitter Preferences Maintenance", "Upfitter Preferences Maintenance label did not match with the expected value");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID), "Back to Queue View", "Back to Queue View label did not match with the expected value");
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_CANCEL_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SIGNOUTBTN_XPATH);
	}
	
	public static void yearMakeModelPageInEditModeLoaded() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_HEADING_CSS);
	}
	
	public static void yearMakeModelPageInEditModeValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SUBSCREENSUBTITLE_XPATH), "Custom Year Make Model - Edit Record", "Custom Year Make Model - Edit Record label did not match with the expected value");
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_CANCEL_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SIGNOUTBTN_XPATH);
	}
	
	public static void clickOnBackToQueueViewLink() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
	}
	
	public static void clickOnBackToPriceProtectionQueueViewLink() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_PRICE_PROTECTION_QUEUE_VIEW_LINK_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_PRICE_PROTECTION_QUEUE_VIEW_LINK_XPATH);
	}
	
	/**
	 * This method search the scheduler
	 * @lastModifiedBy djawale
	 * @param schedulerName
	 * @throws Exception
	 */
	public static void searchScheduler(String schedulerName) throws Exception {
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_PROCESS_NAME_XPATH, schedulerName);
		System.out.println("Searched scheduler name: "+schedulerName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
	}
	
	/**
	 * This method holds Searched Scheduler if its released
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void holdScheduler() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_STATUS_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_STATUS_XPATH.toString()), 1));
		String schedulerStatus=CommonPage.getFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_STATUS_XPATH);
		if(!(schedulerStatus.equals("InActive"))) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_CHECKBOX_NAME);
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_CHECKBOX_NAME);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_HOLD_XPATH);
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_HOLD_XPATH);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_HOLD_BUTTON_DISABLED_CSS);
		}
	}

	/**
	 * This method verifies the buttons in Edit BMT pages BMT are displayed and enabled
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyBmtEditPageButtons() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_ADD_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_CANCEL_BUTTON_ID,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SIGNOUTBTN_XPATH);
	}

	/**
	 * This method verifies the buttons in BMT pages are displayed and enabled
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyBmtPageButtons() throws Exception{
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_TOGGLECOLUMNDROPDOWN_XPATH,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_CLEARFILTERBTN_XPATH,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_EXPORTBTN_XPATH,
				OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_ADDNEWENTRYBTN_XPATH);
	}

	/**This method verifies the presence of button and it's state in BMT page
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyBmtPageDeleteButton() throws Exception{
		CommonPage.waitAndVerifyElementDisplayedAndDisabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_ADDNEWENTRYBTN_XPATH);
	}

	/**
	 * This method searches on BMT Year Make Model (Search pre condition: Enter at least two filter including corp)
	 * @param corpValue corp value to be entered in corp search field
	 * @param searchField search filed other than corp
	 * @param searchText text to be entered in search filed other than corp
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchOnYearMakeModelPage(String corpValue, String searchField, String searchText) throws Exception {
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_VALUE_XPATH.toString(),"standard", "Corp")),corpValue);
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_VALUE_XPATH.name(),String.format(OrderingBOQueuePageEnum.ORDERING_BO_ORDER_SEARCH_FIELDS_VALUE_XPATH.toString(),"standard", searchField)),searchText);
		OrderingBOQueuePage.clickSearchButton();
		OrderingBOQueuePage.waitUntilCompletePageLoad();
	}
	
	/**
	 * This method will return true if process name status is Active
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static boolean processStatus(String processName) throws Exception {
		String processStatus = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_STATUS_XPATH.getValue();
		processStatus = processStatus.replace("$processName#", processName);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(processStatus), 1));
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(processStatus));
		WebDriverVerify.verifyElementIsPresent(By.xpath(processStatus));
		WebDriverVerify.verifyElementEnabled(By.xpath(processStatus));
		if(WebDriverAction.getElement(By.xpath(processStatus)).getText().trim().equals("Active"))
			return true;
		else
			return false;
	}
	
	/**
	 * This method will click on check box as per process name
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectProcessNameCheckBox(String processName) throws Exception {
		String processNameCheckBox = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_NAME_CHECKBOX_XPATH.getValue();
		processNameCheckBox = processNameCheckBox.replace("$processName#", processName);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(processNameCheckBox));
		WebDriverAction.click(By.xpath(processNameCheckBox));
	}
	
	/**
	 * This method will click on hold button
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnHold() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_HOLD_BUTTON_XPATH, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_HOLD_BUTTON_XPATH);
	}
	
	/**
	 * This method will click on Release button
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnRelease() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_RELEASE_BUTTON_XPATH, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_RELEASE_BUTTON_XPATH);
	}
	
	/**
	 * This method will change process to status to InActive
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void changeProcessStatusToInActive() throws Exception {
		if(processStatus(CommonPage.getTestData("ProcessNameOEM")) == true)
			clickOnHold();
		if(processStatus(CommonPage.getTestData("ProcessNameOEM")) == true)
			throw new OrderingErrorOccured("Process Status not changed to InActive");
	}
	
	/**
	 * This method will change process to status to InActive
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void changeProcessStatusToActive() throws Exception {
		if(processStatus(CommonPage.getTestData("ProcessNameOEM")) == false)
			clickOnRelease();
		if(processStatus(CommonPage.getTestData("ProcessNameOEM")) == false)
			throw new OrderingErrorOccured("Process Status not changed to Active");
		// Need this thread as we are activating scheduler from BMT
		Thread.sleep(100_000); 
	}

	
	/**
	 * This method will change ACK file process to status to Active
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void changeACKProcessStatusToActive() throws Exception {
		if(processStatus(CommonPage.getTestData("ACKProcessName")) == false)
			clickOnRelease();
		if(processStatus(CommonPage.getTestData("ACKProcessName")) == false)
			throw new OrderingErrorOccured("ACKProcess Status not changed to Active");
		// Need this thread as we are activating scheduler from BMT
		Thread.sleep(100_000);
	}
	
	/**
	 * This method will search as per passed parameter
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void searchBy(String searchBy, String searchText) throws Exception {
		String searchByName = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_SEARCH_WITH_XPATH.getValue();
		searchByName = searchByName.replace("$searchBy#", searchBy);
		System.out.println("Searching using:- " +searchBy+" with keyword:- "+searchText);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(searchByName));
		WebDriverAction.clickAndClear(By.xpath(searchByName));
		WebDriverAction.enterFieldValue(By.xpath(searchByName), searchText);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_SEARCH_BUTTON_XPATH);
	}
	
	/**
	 * This method will click on Scheduler
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnScheduler(String processName) throws Exception {
		String link = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_NAME_LINK_XPATH.getValue();
		link = link.replace("$processName#", processName);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(link));
		WebDriverVerify.verifyElementIsPresent(By.xpath(link));
		WebDriverVerify.verifyElementEnabled(By.xpath(link));
		WebDriverAction.click(By.xpath(link));
	}
	
	/**
	 * This method will enter cron in cron text box
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void changeCron(String cron) throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_CRON_EXPRESSION_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_CRON_EXPRESSION_ID, cron);
		System.out.println("Cron Pattern:- "+cron);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_ENDPONT_ID);
	}
	
	/**
	 * This method will click on Save button
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnSave() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_SAVE_ID, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_SAVE_ID);
	}
	
	/**
	 * This method will click on Cancel button
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnCancel() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_CANCEL_ID, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_CANCEL_ID);
	}
	
	/**
	 * This method will search process and check the checkbox
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void searchProcessAndSelectCheckbox() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.searchBy("Process Name", CommonPage.getTestData("ProcessNameOEM"));
		OrderingBOBusinessMaintainedTablesPage.selectProcessNameCheckBox(CommonPage.getTestData("ProcessNameOEM"));
	}
	
	/**
	 * This method will go to scheduler BMT
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToSchedulerBMT() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.goToBusinessMaintainedTable();
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Scheduler");
	}
	
	/**
	 * This method will go to scheduler and change cron time
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToSchedulerAndChangeCron() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.searchBy("Process Name", CommonPage.getTestData("ProcessNameOEM"));
		OrderingBOBusinessMaintainedTablesPage.clickOnScheduler(CommonPage.getTestData("ProcessNameOEM"));
		OrderingBOBusinessMaintainedTablesPage.changeCron("* * * * * ?");
		OrderingBOBusinessMaintainedTablesPage.clickOnSave();
	}
	
	/**
	 * This method will go to scheduler and change ACK cron time
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToSchedulerAndChangeACKCron() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.searchBy("Process Name", CommonPage.getTestData("ACKProcessName"));
		OrderingBOBusinessMaintainedTablesPage.clickOnScheduler(CommonPage.getTestData("ACKProcessName"));		
		OrderingBOBusinessMaintainedTablesPage.changeCron("* * * * * ?");
		OrderingBOBusinessMaintainedTablesPage.clickOnSave();
	}
	
	/**
	 * This method will go to scheduler search process and select
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void searchProcessAndSelect() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.searchBy("Process Name", CommonPage.getTestData("ProcessNameOEM"));
		OrderingBOBusinessMaintainedTablesPage.selectProcessNameCheckBox(CommonPage.getTestData("ProcessNameOEM"));
	}
	
	/**
	 * This method will verify Scheduler title
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifySchedulerTitle() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_TITLE_XPATH, CommonPage.getTestData("WaitTime"));
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_TITLE_XPATH), "Scheduler", "Scheduler title is not matched");
	}

	/** This method updates the upfitter communication preference to desired
	 * @lastModifiedBy djawale
	 * @param upfitterName
	 * @param desiredCommunicationPreference
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void updateUpfitterPreference(String upfitterName, String desiredCommunicationPreference) throws Exception {
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_UPFITTER_PREF_UPFITTERCODE_XPATH, upfitterName);
		System.out.println("Upfiiter preference for:- "+upfitterName+" is updated to:-"+desiredCommunicationPreference);
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_UPFITTER_PREF_COMMUNICATION_XPATH, "Purchase Orders");
		System.out.println("Searching communication preference for Purchase Order");
		CommonPage.clickElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		CommonPage.clickElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_XPATH);
		CommonPage.selectDropdownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_UPFITTER_PREF_COMMUNICATION_PREF_CSS, desiredCommunicationPreference);
		CommonPage.clickElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
	}

	/**
	 * click on add new button 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void clickOnAddNewBtn() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_ADDNEWENTRYBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_ADDNEWENTRYBTN_XPATH);
	}
	
	
	/**
	 * Search and delete if additional option exists 
	 * @param corpCode corp code
	 * @param fleet client number
	 * @param optionCode option code
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchAndDeleteIfAdditionalOptionExists(String corpCode, String fleet, String optionCode) throws Exception {
		searchCreatedAdditionalOption(corpCode, fleet, optionCode);
		try {
			verifyNoEntryIsDisplayed();
		}
		catch(OrderingErrorOccured e) {
			deleteAdditionalOption(corpCode, fleet, optionCode);
		}
	}
	
	/**
	 * This method waits and enter text in the textbox
	 * @param locator locator value 
	 * @param text text to be entered 
	 * @throws Exception 
	 * @lastModifiedBy vikumar
	 */
	public static void waitAndEnterFieldValue(Object locator, String text) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(locator);
		WebDriverAction.clear(BrowserAccess.getLocator(locator));
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(locator), text);
	}
	
	/**
	 * This method waits and select drop down by text
	 * @param locator locator value
	 * @param text text to be entered 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void waitAndSelectDropDownOptionByText(Object locator, String text) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(locator);
		BrowserAction.selectDropdownOptionByText(locator, text);
	}
	
	/**
	 * Creates additional option 
	 * @param corpCode corp code
	 * @param fleet client number
	 * @param optionCode option code
	 * @param optionDesc option description
	 * @param optionPrice option price
	 * @param estActPrice EST/ACT price
	 * @param ecCode EC code
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createAdditionalOption(String corp, String fleet, String optionCode, String optionDesc, String optionPrice,  String estActPrice, String ecCode) throws Exception {
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_CORP_CODE_DRP_DWN_XPATH, corp);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_FLEET_INPUT_XPATH, fleet);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_OPTN_CODE_INPUT_XPATH, optionCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_OPTN_DESC_INPUT_XPATH, optionDesc);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_OPTN_PRICE_INPUT_XPATH, optionPrice);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_EST_ACT_PRICE_SELECT_XPATH, estActPrice);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_EC_CODE_SELECT_XPATH, ecCode);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}
	/**
	 * Click on back to queue link 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void clickOnBackToQueueLink() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * Click on back to queue link for price protection 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void clickOnBackToQueueLinkPriceProtection() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_BACK_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_BACK_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * Search created additional option 
	 * @param corpCode corp code
	 * @param fleet client number
	 * @param optionCode option code
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchCreatedAdditionalOption(String corpCode, String fleet, String optionCode) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_CORP_CODE_SEARCH_INPUT_XPATH, corpCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_FLEET_SEARCH_INPUT_XPATH, fleet);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_OPTION_CODE_SEARCH_INPUT_XPATH, optionCode);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method waits and asserts drop down value 
	 * @param locator locator
	 * @param text dropdown text 
	 * @param message assertion message
	 * @throws Exception
	 */
	public static void waitAndAssertDropDownValue(Object locator, String text, String message) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(locator);
		Assert.assertTrue(new Select(BrowserAction.getElement(locator)).getFirstSelectedOption().getText().equalsIgnoreCase(text), message);
	}
	
	/**
	 * This method waits and asserts text 
	 * @param locator locator
	 * @param text element text 
	 * @param message assertion message
	 * @throws Exception
	 */
	public static void waitAndAssertText(Object locator, String text, String message) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(locator);
		Assert.assertTrue(BrowserAction.getElement(locator).getText().equalsIgnoreCase(text), message);
	}
	
	/**
	 * This method waits and asserts text 
	 * @param locator locator
	 * @param text element text 
	 * @param message assertion message
	 * @throws Exception
	 */
	public static void waitAndAssertTextBoxValue(Object locator, String text, String message) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(locator);
		Assert.assertTrue(BrowserAction.getElement(locator).getAttribute("value").equalsIgnoreCase(text), message);
	}
	
	/**
	 * Verifies the values of record created 
	 * @param corpCode corp code
	 * @param fleet client number
	 * @param optionCode option code
	 * @param optionDesc option description
	 * @param optionPrice option price
	 * @param estActPrice EST/ACT price
	 * @param ecCode EC code 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTheValuesOfRecordsCreated(String corpCode, String fleet, String optionCode, String optionDesc, String optionPrice,  String estActPrice, String ecCode) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("corpCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_CORP_CODE_DRP_DWN_XPATH, corpCode, "Corp is not matching");
		waitAndAssertText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_FLEET_SPAN_XPATH, fleet, "Fleet is not matching");
		waitAndAssertText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_OPTN_CODE_SPAN_XPATH, optionCode, "Option code is not matching");
		waitAndAssertText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_OPTN_DESC_SPAN_XPATH, optionDesc, "Option description is not matching");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_OPTN_PRICE_INPUT_XPATH);
		Assert.assertTrue(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_OPTN_PRICE_INPUT_XPATH).getAttribute("value").replaceAll("\\.?0*$", "").equalsIgnoreCase(optionPrice), "Option price is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_EST_ACT_PRICE_SELECT_XPATH, estActPrice, "Est/Act price is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_EC_CODE_SELECT_XPATH, ecCode, "EC Code price is not macthing");
	}
	
	/**
	 * verifies duplicate entry is added as new row 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDuplicateEntryIsAddedAsNewRow() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH);
		List<WebElement> searchResultColumns = BrowserAction.getElements(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH);
		Assert.assertTrue(searchResultColumns.size()==2, "Two entries are not created");
	}
		
	/**
	 * @param estActPrice EST/ACT price
	 * @param corpCode corp code
	 * @param fleet client number
	 * @param optionCode option code 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void updateAdditionalOption(String estActPrice, String corpCode, String fleet, String optionCode) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("corpCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN_EST_ACT_PRICE_SELECT_XPATH, estActPrice);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedAdditionalOption(corpCode, fleet, optionCode);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN__PRICE_IND_TABLE_VALUE_XPATH);
		Assert.assertTrue(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN__PRICE_IND_TABLE_VALUE_XPATH).getText().equalsIgnoreCase(estActPrice), "Option price is not matching");
	}
	
	/**
	 * Deletes additional option 
	 * @param corpCode corp code
	 * @param fleet client number
	 * @param optionCode option code 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteAdditionalOption(String corpCode, String fleet, String optionCode) throws Exception {
		List<WebElement> searchResultColumns = BrowserAction.getElements(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH);
		for(int i=0; i<searchResultColumns.size(); i++ ) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN__PRICE_IND_TABLE_VALUE_XPATH);
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADTNL_OPTN__PRICE_IND_TABLE_VALUE_XPATH);	
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			OrderingCommonPage.checkAlertPopUp();
			searchCreatedAdditionalOption(corpCode, fleet, optionCode);
		}
		verifyNoEntryIsDisplayed();
	}
	
	/**
	 * Verifies no entry is displayed in the table
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyNoEntryIsDisplayed() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCH_TABLE_ROWS_NO_DATA_PRESENT_XPATH.getValue()), 0));
		String rowValue = BrowserAction.getElement(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCH_TABLE_ROWS_NO_DATA_PRESENT_XPATH).getText();
		if(!rowValue.equals("No data available in table")) {
			throw new OrderingErrorOccured("Entry not deleted");
		}
	}
	
	/**
	 * searches and deletes if asset code exists 
	 * @param assetCode asset code 
	 * @param description description 
	 * @param productClass product class
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchAndDeleteIfAssetCodeExists(String assetCode, String description, String productClass) throws Exception {
		searchCreatedAssetCode(assetCode, description, productClass);
		try {
			verifyNoEntryIsDisplayed();
		} catch(OrderingErrorOccured e) {
			deleteAssetCode(assetCode, description, productClass);
		}
	}
	
	/**
	 * Creates asset code
	 * @param assetCode asset code 
	 * @param description description 
	 * @param productClass product class 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createAssetCode(String assetCode, String description, String productClass) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_ASSET_CODE_INPUT_XPATH, assetCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_DESC_INPUT_XPATH, description);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_PRODUCT_CLASS_DROPDOWN_XPATH, productClass);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * Verifies the values of record created 
	 * @param assetCode asset code 
	 * @param description description 
	 * @param productClass product class 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTheValuesOfRecordCreatedAssetCode(String assetCode, String description, String productClass) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("assetCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndAssertText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_ASSET_CODE_SPAN_XPATH, assetCode, "Asset code is not matching");
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_DESC_INPUT_XPATH, description, "Asset code description is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_PRODUCT_CLASS_DROPDOWN_XPATH, productClass, "Product class is not macthing");
	}
	
	/**
	 * Search created asset code 
	 * @param assetCode asset code 
	 * @param description description 
	 * @param productClass product class 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchCreatedAssetCode(String assetCode, String description, String productClass) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_SEARCH_INPUT_XPATH, assetCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_DESCRIPTION_SEARCH_INPUT_XPATH, description);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_PRODUCT_CLASS_SEARCH_INPUT_XPATH, productClass);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * verifies duplicate asset code cannot be added 
	 * @param assetCode asset code 
	 * @param description description 
	 * @param productClass product class 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void duplicateAssetCodeCannotBeAdded(String assetCode, String description, String productClass) throws Exception {
		boolean flag=false;
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_ASSET_CODE_INPUT_XPATH, assetCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_DESC_INPUT_XPATH, description);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_PRODUCT_CLASS_DROPDOWN_XPATH, productClass);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Duplicate error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		clickOnBackToQueueLink();
	}
	
	/**
	 * Updates asset code 
	 * @param assetCode asset code 
	 * @param description description 
	 * @param newProductClass product class 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void updateAssetCode(String assetCode, String description, String newProductClass) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("assetCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_PRODUCT_CLASS_DROPDOWN_XPATH, newProductClass);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedAssetCode(assetCode, description, newProductClass);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_PRODUCT_CLASS_TABLE_VALUE_XPATH);
		Assert.assertTrue(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_PRODUCT_CLASS_TABLE_VALUE_XPATH).getText().equalsIgnoreCase(newProductClass), "Product Class is not matching");
	}
	
	/**
	 * Deletes asset code 
	 * @param assetCode asset code 
	 * @param description description 
	 * @param productClass product class 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteAssetCode(String assetCode, String description, String productClass) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_PRODUCT_CLASS_TABLE_VALUE_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ASSET_CODE_PRODUCT_CLASS_TABLE_VALUE_XPATH);	
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedAssetCode(assetCode, description, productClass);
		verifyNoEntryIsDisplayed();
	}
	
	/**
	 * Searches and deletes if DIO exists 
	 * @param optionCode option code 
	 * @param brandProvider brand provider name 
	 * @param description description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchAndDeleteIfDIOExists(String optionCode, String brandProvider, String description) throws Exception {
		searchCreatedDIO(optionCode, brandProvider, description);
		try {
			verifyNoEntryIsDisplayed();
		} catch(OrderingErrorOccured e) {
			deleteDIO(optionCode, brandProvider, description);
		}
	}
	
	/**
	 * Creates DIO
	 * @param optionCode option code 
	 * @param brandProvider brand provider name 
	 * @param description description 
	 * @param ecCode EC code
	 * @param maximumPrice maximum price
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createDIO(String optionCode, String brandProvider, String description, String ecCode, String maximumPrice) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_OPTION_CODE_INPUT_XPATH, optionCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_BRAND_PROVIDER_INPUT_XPATH, brandProvider);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_OPTION_DESC_INPUT_XPATH, description);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_EC_CODE_INPUT_XPATH, ecCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_MAX_PRICE_INPUT_XPATH, maximumPrice);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * verifies the value of record created DIO
	 * @param optionCode option code 
	 * @param brandProvider brand provider name 
	 * @param description description 
	 * @param ecCode EC code
	 * @param maximumPrice maximum price
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTheValuesOfRecordCreatedDIO(String optionCode, String brandProvider, String description, String ecCode, String maximumPrice) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("optionCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_OPTION_CODE_INPUT_XPATH, optionCode, "Option code is not macthing");
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_BRAND_PROVIDER_INPUT_XPATH, brandProvider, "Brand Provider is not macthing");
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_OPTION_DESC_INPUT_XPATH, description, "Option code description is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_EC_CODE_INPUT_XPATH, ecCode, "EC code is not macthing");
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_MAX_PRICE_INPUT_XPATH, maximumPrice, "Max price is not macthing");
	}
	
	/**
	 * Searches created DIO
	 * @param optionCode option code 
	 * @param brandProvider brand provider name 
	 * @param description description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchCreatedDIO(String optionCode, String brandProvider, String description) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_OPTION_CODE_SEARCH_XPATH, optionCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_OPTION_CODE_DESC_SEARCH_XPATH, description);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_BRAND_PROVIDER_SEARCH_XPATH, brandProvider);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * Verifies duplicate DIO cannot be added 
	 * @param optionCode option code 
	 * @param brandProvider brand provider name 
	 * @param description description 
	 * @param ecCode EC code
	 * @param maximumPrice maximum price
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDuplicatDIOCannotBeAdded(String optionCode, String brandProvider, String description, String ecCode, String maximumPrice) throws Exception {
		boolean flag=false;
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_OPTION_CODE_INPUT_XPATH, optionCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_BRAND_PROVIDER_INPUT_XPATH, brandProvider);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_OPTION_DESC_INPUT_XPATH, description);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_EC_CODE_INPUT_XPATH, ecCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_MAX_PRICE_INPUT_XPATH, maximumPrice);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Duplicate error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		clickOnBackToQueueLink();
	}
	
	/**
	 * Updates DIO
	 * @param optionCode option code 
	 * @param brandProvider brand provider name 
	 * @param description description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void updateDIO(String optionCode, String brandProvider, String description) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("optionCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_BRAND_PROVIDER_INPUT_XPATH, brandProvider);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedDIO(optionCode, brandProvider, description);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_BRAND_PROVIDER_TABLE_VALUE_XPATH);
		Assert.assertTrue(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_BRAND_PROVIDER_TABLE_VALUE_XPATH).getText().equalsIgnoreCase(brandProvider), "Brand provider is not matching");
	}
	
	/**
	 * Deletes DIO
	 * @param optionCode option code 
	 * @param brandProvider brand provider name 
	 * @param description description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteDIO(String optionCode, String brandProvider, String description) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_BRAND_PROVIDER_TABLE_VALUE_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_BRAND_PROVIDER_TABLE_VALUE_XPATH);	
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedDIO(optionCode, brandProvider, description);
		verifyNoEntryIsDisplayed();
	}
	
	/**
	 * Searches and deletes if BMT fin fan exists 
	 * @param corpCode corp code 
	 * @param maufacturer manufacturer name 
	 * @param finFan fin fan value 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchAndDeleteIfBMTFinFanExists(String corpCode, String maufacturer, String finFan) throws Exception {
		searchCreatedBMTFinFan(corpCode, maufacturer, finFan);
		try {
			verifyNoEntryIsDisplayed();
		} catch(OrderingErrorOccured e) {
			deleteBMTFinFan(corpCode, maufacturer, finFan);
		}
	}
	
	/**
	 * Creates BMT fin fan
	 * @param corpCode corp code 
	 * @param maufacturer manufacturer name 
	 * @param finFan fin fan value 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createBMTFinFan(String corpCode, String maufacturer, String finFan) throws Exception {
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_CORP_CODE_DROPDOWN_XPATH, corpCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_BMT_FINFAN_INPUT_XPATH, finFan);
		BrowserWait.waitUntilCountOfElementsPresent(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_MANUFACTURER_DROPDOWN_XPATH,1);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_MANUFACTURER_DROPDOWN_XPATH);
		BrowserAction.selectDropdownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_MANUFACTURER_DROPDOWN_XPATH, maufacturer);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * verifies the value of record created 
	 * @param corpCode corp code 
	 * @param maufacturer manufacturer name 
	 * @param finFan fin fan value 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTheValuesOfRecordCreatedBMTFinFan(String corpCode, String maufacturer, String finFan) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("corpCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();	
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_CORP_CODE_DROPDOWN_XPATH, corpCode, "Corp code is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_MANUFACTURER_DROPDOWN_XPATH, maufacturer, "Manufacturer is not macthing");	
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_BMT_FINFAN_INPUT_XPATH, finFan, "Fin Fan code is not macthing");
	}
	
	/**
	 * Search created BMT fin fan value 
	 * @param corpCode corp code 
	 * @param maufacturer manufacturer name 
	 * @param finFan fin fan value 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchCreatedBMTFinFan(String corpCode, String maufacturer, String finFan) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_CORP_CODE_SEARCH_XPATH, corpCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_MANUFACTURER_SEARCH_XPATH, maufacturer);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_SEARCH_XPATH, finFan);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * Verifies duplicate BMT fin fan cannpt be added 
	 * @param corpCode corp code 
	 * @param maufacturer manufacturer name 
	 * @param finFan fin fan value 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDuplicateBMTFinFanCannotBeAdded(String corpCode, String maufacturer, String finFan) throws Exception {
		boolean flag=false;
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_CORP_CODE_DROPDOWN_XPATH, corpCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_BMT_FINFAN_INPUT_XPATH, finFan);
		BrowserWait.waitUntilCountOfElementsPresent(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_MANUFACTURER_DROPDOWN_XPATH,1);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_MANUFACTURER_DROPDOWN_XPATH, maufacturer);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Duplicate error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		clickOnBackToQueueLink();
	}
	
	/**
	 * Updates BMT fin fan 
	 * @param corpCode corp code 
	 * @param maufacturer manufacturer name 
	 * @param finFan fin fan value 
	 * @param rentalIndicator rental indicator
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void updateBMTFinFan(String corpCode, String maufacturer, String finFan, String rentalIndicator) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("corpCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_RENTAL_INDICATOR_TOGGLE_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_RENTAL_INDICATOR_TOGGLE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedBMTFinFan(corpCode, maufacturer, finFan);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_RENTAL_INDICATOR_TABLE_VAL_XPATH);
		Assert.assertTrue(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_RENTAL_INDICATOR_TABLE_VAL_XPATH).getText().equalsIgnoreCase(rentalIndicator), "Rental Indicator is not matching");
	}
	
	/**
	 * Deletes BMT fin fan 
	 * @param corpCode corp code 
	 * @param maufacturer manufacturer name 
	 * @param finFan fin fan value 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteBMTFinFan(String corpCode, String maufacturer, String finFan) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_RENTAL_INDICATOR_TABLE_VAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_RENTAL_INDICATOR_TABLE_VAL_XPATH);	
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedBMTFinFan(corpCode, maufacturer, finFan);
		verifyNoEntryIsDisplayed();
	}
	
	/**
	 * Search and delete if emission exists 
	 * @param corpCode corp code
	 * @param year year 
	 * @param manufacturer manufacturer name
	 * @param make maker name
	 * @param model model name
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchAndDeleteIfEmissionExists(String corpCode, String year, String manufacturer, String make, String model) throws Exception {
		searchCreatedEmission(corpCode, year, manufacturer, make, model);
		try {
			verifyNoEntryIsDisplayed();
		} catch(OrderingErrorOccured e) {
			deleteEmission(corpCode, year, manufacturer, make, model);
		}
	}
	
	/**
	 * Creates emission 
	 * @param corpCode corp code
	 * @param year year 
	 * @param manufacturer manufacturer name
	 * @param make maker name
	 * @param model model name
	 * @param emissionCode emission code
	 * @param emissionReigon emission reigon
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createEmissions(String corpCode, String year, String manufacturer, String make, String model, String emissionCode, String emissionReigon) throws Exception {
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CORP_CODE_DROPDOWN_XPATH, corpCode);
		BrowserWait.waitUntilCountOfElementsPresent(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_YEAR_DROPDOWN_XPATH,1);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_YEAR_DROPDOWN_XPATH, year);
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MANUFACTURER_DROPDOWN_XPATH, manufacturer);
		} catch(StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MANUFACTURER_DROPDOWN_XPATH, manufacturer);
		}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MAKE_DROPDOWN_XPATH, make);
		} catch(StaleElementReferenceException e){
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MAKE_DROPDOWN_XPATH, make);
		}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MODEL_DROPDOWN_XPATH, model);
		} catch(StaleElementReferenceException e){
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MODEL_DROPDOWN_XPATH, model);
		}
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CODE_INPUT_XPATH, emissionCode);
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_REIGON_DROPDOWN_XPATH, emissionReigon);
		} catch(StaleElementReferenceException e){
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_REIGON_DROPDOWN_XPATH, emissionReigon);
		}
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
	}
	
	/**
	 * checks the alert
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void checkAlert() throws Exception {
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * verifies the value of record created 
	 * @param corpCode corp code
	 * @param year year 
	 * @param manufacturer manufacturer name
	 * @param make maker name
	 * @param model model name
	 * @param emissionCode emission code
	 * @param emissionReigon emission reigon
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTheValuesOfRecordCreatedEmission(String corpCode, String year, String manufacturer, String make, String model,String emissionCode, String emissionReigon) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CODE_TABLE_VAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CODE_TABLE_VAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CORP_CODE_DROPDOWN_XPATH, corpCode, "Corp code is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_YEAR_DROPDOWN_XPATH, year, "Year is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MANUFACTURER_DROPDOWN_XPATH, manufacturer, "Manufacturer is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MAKE_DROPDOWN_XPATH, make, "Make is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MODEL_DROPDOWN_XPATH, model, "Model is not macthing");	
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CODE_INPUT_XPATH, emissionCode, "Emission code is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_REIGON_DROPDOWN_XPATH, emissionReigon, "Trim is not macthing");
	}
	
	/**
	 * Searches created emission 
	 * @param corpCode corp code
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param model model name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchCreatedEmission(String corpCode, String year, String manufacturer, String make, String model) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CORP_SEARCH_XPATH, corpCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_YEAR_SEARCH_XPATH, year);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MANUFACTURER_SEARCH_XPATH, manufacturer);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MODEL_SEARCH_XPATH, model);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MAKE_SEARCH_XPATH, make);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * verifies duplicate emission cannot be added 
	 * @param corpCode corp code
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param model model name 
	 * @param emissionCode emission code
	 * @param emissionReigon emission reigon
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDuplicateEmissionCannotBeAdded(String corpCode, String year, String manufacturer, String make, String model, String emissionCode, String emissionReigon) throws Exception {
		boolean flag=false;
		createEmissions(corpCode, year, manufacturer, make, model, emissionCode, emissionReigon);
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(Exception e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Duplicate error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		clickOnBackToQueueLink();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NO_MODAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NO_MODAL_XPATH);
	}
	
	/**
	 * updates existing emission 
	 * @param corpCode corp code
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param model model name 
	 * @param updatedEmissionCode emission code
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void updateEmission(String corpCode, String year, String manufacturer, String make, String model, String updatedEmissionCode) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CODE_TABLE_VAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CODE_TABLE_VAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CODE_INPUT_XPATH, updatedEmissionCode);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedEmission(corpCode, year, manufacturer, make, model);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CODE_TABLE_VAL_XPATH);
		Assert.assertTrue(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CODE_TABLE_VAL_XPATH).getText().equalsIgnoreCase(updatedEmissionCode), "Emission Code is not matching");
	}
	
	/**
	 * Deletes emission 
	 * @param corpCode corp code
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param model model name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteEmission(String corpCode, String year, String manufacturer, String make, String model) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CODE_TABLE_VAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_CODE_TABLE_VAL_XPATH);	
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedEmission(corpCode, year, manufacturer, make, model);
		verifyNoEntryIsDisplayed();
	}
	
	/**
	 * Searches and deletes if factory order range exists 
	 * @param corpCode corp code
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param modelCode model code
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchAndDeleteIfFActoryOrderRangeExists(String corpCode, String manufacturer, String make, String modelCode) throws Exception {
		searchCreatedFactoryOrderRange(corpCode, manufacturer, make, modelCode);
		try {
			verifyNoEntryIsDisplayed();
		} catch(OrderingErrorOccured e) {
			deleteFactoryOrderRange(corpCode, manufacturer, make, modelCode);
		}
	}
	
	/**
	 * Creates factory order range 
	 * @param corpCode corp code
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param modelCode model code
	 * @param rangeStart range start number
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createFactoryOrderRange(String corpCode, String manufacturer, String make, String modelCode, String rangeStart) throws Exception {
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_CORP_DROPDOWN_XPATH, corpCode);
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MANUFACTURER_DROPDOWN_XPATH, manufacturer);
		} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MANUFACTURER_DROPDOWN_XPATH, manufacturer);
		}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MAKE_DROPDOWN_XPATH, make);
		} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MAKE_DROPDOWN_XPATH, make);
		}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_MODEL_CODE_DROPDOWN_XPATH, modelCode);
		} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_MODEL_CODE_DROPDOWN_XPATH, modelCode);
		}
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_START_NUMBER_INPUT_XPATH, rangeStart);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
	}
	
	/**
	 * Verifies the value created factory order range 
	 * @param corpCode corp code
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param modelCode model code
	 * @param rangeStart range start number 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTheValuesOfRecordCreatedFactoryOrderRange(String corpCode, String manufacturer, String make, String modelCode, String rangeStart) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_START_NUMBER_TABLE_VALUE_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_START_NUMBER_TABLE_VALUE_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();	
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_CORP_DROPDOWN_XPATH, corpCode, "Corp code is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MANUFACTURER_DROPDOWN_XPATH, manufacturer, "Manufacturer is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MAKE_DROPDOWN_XPATH, make, "Make is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_MODEL_CODE_DROPDOWN_XPATH, modelCode, "Model Code is not macthing");	
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_START_NUMBER_INPUT_XPATH, rangeStart, "Range start is not macthing");
	}
	
	/**
	 * Searches the created factory order range 
	 * @param corpCode corp code
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param modelCode model code
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchCreatedFactoryOrderRange(String corpCode, String manufacturer, String make, String modelCode) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_CORP_CODE_SEARCH_XPATH, corpCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ELEMENT_FINFAN_MANUFACTURER_SEARCH_XPATH, manufacturer);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MAKE_SEARCH_XPATH, make);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_MODEL_CODE_INPUT_XPATH, modelCode);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * verifies duplicate factory order cannot be added 
	 * @param corpCode corp code
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param modelCode model code
	 * @param rangeStart range start number 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDuplicateFactoryOrderRangeCannotBeAdded(String corpCode, String manufacturer, String make, String modelCode, String rangeStart) throws Exception {
		boolean flag=false;
		createFactoryOrderRange(corpCode, manufacturer, make, modelCode, rangeStart);
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Duplicate error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		clickOnBackToQueueLink();
	}
	
	/**
	 * updates factory order range 
	 * @param corpCode corp code
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param modelCode model code
	 * @param updatedRangeStart range start number
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void updateFactoryOrderRange(String corpCode, String manufacturer, String make, String modelCode, String updatedRangeStart) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_START_NUMBER_TABLE_VALUE_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_START_NUMBER_TABLE_VALUE_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_START_NUMBER_INPUT_XPATH, updatedRangeStart);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedFactoryOrderRange(corpCode, manufacturer, make, modelCode);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_START_NUMBER_TABLE_VALUE_XPATH);
		Assert.assertTrue(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_START_NUMBER_TABLE_VALUE_XPATH).getText().equalsIgnoreCase(updatedRangeStart), "Range Start is not matching");
	}
	
	/**
	 * Deletes factory order range 
	 * @param corpCode corp code
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param modelCode model code
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteFactoryOrderRange(String corpCode, String manufacturer, String make, String modelCode) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_START_NUMBER_TABLE_VALUE_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FACTORY_ORDER_RANGE_START_NUMBER_TABLE_VALUE_XPATH);	
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedFactoryOrderRange(corpCode, manufacturer, make, modelCode);
		verifyNoEntryIsDisplayed();
	}
	
	/**
	 * creates the feature toggle 
	 * @param featureName feature name 
	 * @param featureDescription feature description
	 * @param featureType feature type
	 * @param featureValue feature value 
	 * @param parentGroup parent group 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createFeatureToggles(String featureName, String featureDescription, String featureType, String featureValue, String parentGroup) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_NAME_INPUT_XPATH, featureName);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_DESCRIPTION_INPUT_XPATH, featureDescription);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_TYPE_SELECT_XPATH, featureType);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_VALUE_INPUT_XPATH, featureValue);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_PARENT_GROUP_XPATH, parentGroup);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
	}
	
	/**
	 * Verifies the value of record created 
	 * @param featureName feature name 
	 * @param featureDescription feature description
	 * @param featureType feature type
	 * @param parentGroup parent group 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTheValuesOfRecordCreatedFeatureToggles(String featureName, String featureDescription, String featureType, String parentGroup) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_NAME_LIST_XPATH);
		List<WebElement> featureNameList = BrowserAccess.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_NAME_LIST_XPATH);
		for(WebElement e:featureNameList) {
			if(e.getText().trim().equalsIgnoreCase(featureName)) {
				e.click();
				break;
			}
		}
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_NAME_INPUT_XPATH, featureName, "Feature name is not macthing");
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_DESCRIPTION_INPUT_XPATH, featureDescription, "Feature description is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_TYPE_SELECT_XPATH, featureType, "Feature Type is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_PARENT_GROUP_XPATH, parentGroup, "parent group is not macthing");
	}
	
	/**
	 * Verifies duplicate feature toggle cannot be added 
	 * @param featureName feature name 
	 * @param featureDescription feature description
	 * @param featureType feature type
	 * @param featureValue feature value 
	 * @param parentGroup parent group 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDuplicateFeatureToggleCannotBeAdded(String featureName, String featureDescription, String featureType, String featureValue, String parentGroup) throws Exception {
		boolean flag=false;
		createFeatureToggles(featureName, featureDescription, featureType, featureValue, parentGroup);
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Duplicate error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		clickOnBackToQueueLink();
	}

	/**
	 * Updates feature toggle 
	 * @param featureName feature name 
	 * @param updatedFeatureDescription feature description
	 * @param featureType feature type
	 * @param featureValue feature value 
	 * @param parentGroup parent group 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void updateFeatureToggle(String featureName, String updatedFeatureDescription, String featureType, String featureValue, String parentGroup) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_NAME_LIST_XPATH);
		List<WebElement> featureNameList = BrowserAccess.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_NAME_LIST_XPATH);
		for(WebElement e:featureNameList) {
			if(e.getText().trim().equalsIgnoreCase(featureName)) {
				e.click();
				break;
			}
		}
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_DESCRIPTION_INPUT_XPATH, updatedFeatureDescription);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * Searches for the feature toggle and deletes it if it exists 
	 * @param featureName feature name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteFeatureToggle(String featureName) throws Exception {
		boolean flag=true;
		boolean exists=false;
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_NAME_LIST_XPATH);
		List<WebElement> featureNameList = BrowserAccess.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_NAME_LIST_XPATH);
		for(WebElement e:featureNameList) {
			if(e.getText().trim().equalsIgnoreCase(featureName)) {
				e.click();
				exists=true;
				break;
			}
		}
		if(exists) {
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			OrderingCommonPage.checkAlertPopUp();
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_NAME_LIST_XPATH);
			List<WebElement> featureNameListAfterDelete = BrowserAccess.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_FEATURE_NAME_LIST_XPATH);
			for(WebElement e:featureNameListAfterDelete) {
				if(e.getText().trim().equalsIgnoreCase(featureName)) {
					flag=false;
					break;
				}
			}
			Assert.assertTrue(flag==true, "Feature toggle is not deleted");
			} else {
				System.out.println("Feature toggle doesn't exist");
			}
	}
	
	/**
	 * Searches and deletes if fleet assignment exists 
	 * @param corpCode corp code
	 * @param fleet client number 
	 * @param role role of the primary user 
	 * @param primaryUser primary user name
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchAndDeleteIfFleetAssignmentExists(String corpCode, String fleet, String role, String primaryUser) throws Exception {
		searchCreatedFleetAssignment(corpCode, fleet, role, primaryUser);
		try {
			verifyNoEntryIsDisplayed();
		}
		catch(OrderingErrorOccured e) {
			deleteFleetAsignment(corpCode, fleet, role, primaryUser);
		}
	}
	
	/**
	 * Creates fleet assignment 
	 * @param corpCode corp code
	 * @param fleet client number 
	 * @param role role of the primary user 
	 * @param primaryUser primary user name
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createFleetAssignment(String corpCode, String fleet, String role, String primaryUser) throws Exception {
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_CORP_CODE_DROPDOWN_XPATH, corpCode);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_ROLE_ID_XPATH, role);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_PRIMARYID_INPUT_XPATH, primaryUser);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_FLEET_INPUT_XPATH, fleet);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
	}
	
	/**
	 * Verifies the values of record created 
	 * @param corpCode corp code
	 * @param fleet client number 
	 * @param role role of the primary user 
	 * @param primaryUser primary user name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTheValuesOfRecordCreatedFleetAssignment(String corpCode, String fleet, String role, String primaryUser) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("corpCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_CORP_CODE_DROPDOWN_XPATH, corpCode, "Corp code is not macthing");	
		waitAndAssertText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_FLEET_SPAN_XPATH, fleet, "Fleet is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_ROLE_ID_XPATH, role, "Role is not macthing");		
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_PRIMARYID_INPUT_XPATH, primaryUser,  "Primary User is not macthing");
	}
	
	/**
	 * Search created fleet assignment 
	 * @param corpCode corp code
	 * @param fleet client number
	 * @param role role of the primary user 
	 * @param primaryUser primary user name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchCreatedFleetAssignment(String corpCode, String fleet, String role, String primaryUser) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_CORP_CODE_SEARCH_XPATH, corpCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_CLIENT_NUMBER_SEARCH_XPATH, fleet);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_ROLE_NAME_SEARCH_XPATH, role);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_PRIMARY_USER_SEARCH_XPATH, primaryUser);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * Verifies duplicate fleet assignment cannot be addded 
	 * @param corpCode corp code 
	 * @param fleet client number 
	 * @param role role of primary user
	 * @param primaryUser primary user name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDuplicateFleetAssignmentCannotBeAdded(String corpCode, String fleet, String role, String primaryUser) throws Exception {
		boolean flag=false;
		createFleetAssignment(corpCode, fleet, role, primaryUser);
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Duplicate error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		clickOnBackToQueueLink();
	}
	
	/**
	 * Delete fleet assignment 
	 * @param corpCode corp code
	 * @param fleet client number
	 * @param role role of the user
	 * @param primaryUser primary user 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteFleetAsignment(String corpCode, String fleet, String role, String primaryUser) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_PRIMARY_USER_TABLE_VAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_PRIMARY_USER_TABLE_VAL_XPATH);	
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedFleetAssignment(corpCode, fleet, role, primaryUser);
		verifyNoEntryIsDisplayed();
	}
	
	/**
	 * Searches and deletes incentive if it exists 
	 * @param corpCode corp code
	 * @param fleet client number 
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param incentiveCode incentive code 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchAndDeleteIfIncentiveExists(String corpCode, String fleet, String year, String manufacturer, String incentiveCode) throws Exception {
		searchCreatedIncentive(corpCode, fleet, year, manufacturer, incentiveCode);
		try {
			verifyNoEntryIsDisplayed();
		} catch(OrderingErrorOccured e) {
			deleteIncentive(corpCode, fleet, year, manufacturer, incentiveCode);
		}
	}
	
	/**
	 * Creates the incentive 
	 * @param corpCode corp code 
	 * @param fleet client number 
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param incentiveCode incentive code 
	 * @param description description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createIncentive(String corpCode, String fleet, String year, String manufacturer, String make, String incentiveCode, String description) throws Exception {
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_CORP_CODE_DROPDOWN_XPATH, corpCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_FLEET_INPUT_XPATH, fleet);
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_YEAR_DROPDOWN_XPATH, year);
		} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_YEAR_DROPDOWN_XPATH, year);
		}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MANUFACTURER_DROPDOWN_XPATH, manufacturer);
		} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MANUFACTURER_DROPDOWN_XPATH, manufacturer);
		}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MAKE_DROPDOWN_XPATH, make);
		} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MAKE_DROPDOWN_XPATH, make);
		}
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_ID_INPUT_XPATH, incentiveCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_DESCRIPTION_INPUT_XPATH, description);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
	}
	
	/**
	 * Verifies the values of record created 
	 * @param corpCode corp code
	 * @param fleet client number 
	 * @param year year 
	 * @param manufacturer manufacturer 
	 * @param incentiveCode incentive code 
	 * @param description description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTheValuesOfRecordCreatedIncentive(String corpCode, String fleet, String year, String manufacturer, String incentiveCode, String description) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("corpCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_CORP_CODE_DROPDOWN_XPATH, corpCode, "Corp code is not macthing");
		waitAndAssertText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_FLEET_SPAN_XPATH, fleet, "Fleet is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_YEAR_DROPDOWN_XPATH, year, "Year dropdown is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EMISION_MANUFACTURER_DROPDOWN_XPATH, manufacturer, "Manufacturer dropdown is not macthing");
		waitAndAssertText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_ID_SPAN_XPATH, incentiveCode, "Incentive Code is not macthing");
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_DESCRIPTION_INPUT_XPATH, description, "Description is not macthing");
	}
	
	/**
	 * Searches created incentive 
	 * @param corpCode corp code
	 * @param fleet client number 
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param incentiveCode incentive code 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchCreatedIncentive(String corpCode, String fleet, String year, String manufacturer, String incentiveCode) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_CORP_CODE_SEARCH_XPATH, corpCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_FLEET_SEARCH_XPATH, fleet);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_YEAR_SEARCH_XPATH, year);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_MANUFACTURER_SEARCH_XPATH, manufacturer);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_CODE_SEARCH_XPATH, incentiveCode);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * Verifies duplicate incentive code cannot be added 
	 * @param corpCode corp code 
	 * @param fleet client number 
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param incentiveCode incentive code 
	 * @param description description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDuplicateIncentiveCannotBeAdded(String corpCode, String fleet, String year, String manufacturer, String make, String incentiveCode, String description) throws Exception {
		boolean flag=false;
		createIncentive(corpCode, fleet, year, manufacturer, make, incentiveCode, description);
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Duplicate error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		clickOnBackToQueueLink();
	}
	
	/**
	 * Updates incentive and verifies the updated value
	 * @param corpCode corp code
	 * @param fleet client number 
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param incentiveCode incentive code 
	 * @param updatedDescription updated description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void updateIncentive(String corpCode, String fleet, String year, String manufacturer, String incentiveCode, String updatedDescription) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("corpCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_DESCRIPTION_INPUT_XPATH, updatedDescription);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedIncentive(corpCode, fleet, year, manufacturer, incentiveCode);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_DESCRIPTION_TABLE_VAL_XPATH);
		Assert.assertTrue(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_DESCRIPTION_TABLE_VAL_XPATH).getText().equalsIgnoreCase(updatedDescription), "Incentive description is not matching");
	
	}
	
	/**
	 * Deletes incentive 
	 * @param corpCode corp code 
	 * @param fleet client number 
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param incentiveCode incentive code 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteIncentive(String corpCode, String fleet, String year, String manufacturer, String incentiveCode) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_DESCRIPTION_TABLE_VAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_INCENTIVE_DESCRIPTION_TABLE_VAL_XPATH);	
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedIncentive(corpCode, fleet, year, manufacturer, incentiveCode);
		verifyNoEntryIsDisplayed();
	}
	
	/**
	 * Searches and delete if master purchase order status exists 
	 * @param corpCode corp code 
	 * @param manufacturer manufacturer code 
	 * @param make maker code 
	 * @param majorCode major code 
	 * @param description description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchAndDeleteIfMPOSExists(String corpCode, String manufacturer, String make, String majorCode, String description) throws Exception {
		searchCreatedMPOS(corpCode, manufacturer, make, majorCode, description);
		try {
			verifyNoEntryIsDisplayed();
		} catch(OrderingErrorOccured e) {
			deleteMPOS(corpCode, manufacturer, make, majorCode, description);
		}
	}
	
	/**
	 * Creates master purchase order status 
	 * @param corpCode corp code 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param majorCode major code 
	 * @param description description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createMPOS(String corpCode, String manufacturer, String make, String majorCode, String description) throws Exception {
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_CORP_CODE_DROPDOWN_XPATH, corpCode);
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_MANUFACTURER_DROPDOWN_XPATH, manufacturer);
		} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_MANUFACTURER_DROPDOWN_XPATH, manufacturer);
		}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_MAKE_DROPDOWN_XPATH, make);
		} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_MAKE_DROPDOWN_XPATH, make);
		}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_MAJOR_CODE_DROPDOWN_XPATH, majorCode);
		} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_MAJOR_CODE_DROPDOWN_XPATH, majorCode);
		}
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_DESC_INPUT_XPATH, description);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
	}
	
	/**
	 * Verifies the record created master purchase order status 
	 * @param corpCode corp code 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param majorCode major code
	 * @param description description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTheValuesOfRecordCreatedMPOS(String corpCode, String manufacturer, String make, String majorCode, String description) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("corpCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_CORP_CODE_DROPDOWN_XPATH, corpCode, "Corp code is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_MANUFACTURER_DROPDOWN_XPATH, manufacturer, "Manufacturer is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_MAKE_DROPDOWN_XPATH, make, "Make is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_MAJOR_CODE_DROPDOWN_XPATH, majorCode, "Major code is not macthing");
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_DESC_INPUT_XPATH, description, "Description is not macthing");
	}
	
	/**
	 * Searches created master purchase order status 
	 * @param corpCode corp code 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param majorCode major code 
	 * @param description description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchCreatedMPOS(String corpCode, String manufacturer, String make, String majorCode, String description) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_CORP_CODE_SEARCH_XPATH, corpCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_MANUFACTURER_SEARCH_XPATH, manufacturer);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_MAKE_SEARCH_XPATH, make);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_MAJOR_CODE_SEARCH_XPATH, majorCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_DESCRIPTION_SEARCH_XPATH, description);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * Verifies duplicate master purchase order status cannot be added 
	 * @param corpCode corp code
	 * @param manufacturer manufacturer name
	 * @param make maker name 
	 * @param majorCode major code 
	 * @param description description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDuplicateMPOSCannotBeAdded(String corpCode, String manufacturer, String make, String majorCode, String description) throws Exception {
		boolean flag=false;
		createMPOS(corpCode, manufacturer, make, majorCode, description);
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Duplicate error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		clickOnBackToQueueLink();
	}
	
	/**
	 * updates master purchase order status 
	 * @param corpCode corp code
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param majorCode major code value 
	 * @param updatedDescription description value 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void updateMPOS(String corpCode, String manufacturer, String make, String majorCode, String updatedDescription) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_DESCRIPTION_TABLE_VAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_DESCRIPTION_TABLE_VAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_DESCRIPTION_SEARCH_XPATH, updatedDescription);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchCreatedMPOS(corpCode, manufacturer, make, majorCode, updatedDescription);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_DESCRIPTION_TABLE_VAL_XPATH);
		Assert.assertTrue(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_DESCRIPTION_TABLE_VAL_XPATH).getText().equalsIgnoreCase(updatedDescription), "Incentive description is not matching");
	}
	
	/**
	 * Deletes master purchase order status created
	 * @param corpCode corp code 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param majorCode major code value 
	 * @param description description 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteMPOS(String corpCode, String manufacturer, String make, String majorCode, String description) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_DESCRIPTION_TABLE_VAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MPO_DESCRIPTION_TABLE_VAL_XPATH);	
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		searchCreatedMPOS(corpCode, manufacturer, make, majorCode, description);
		verifyNoEntryIsDisplayed();
	}
	
	/**
	 * Searches and deletes order hold if it exists 
	 * @param corpCode corp code
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param model model name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchAndDeleteIfOrderHoldExists(String corpCode, String year, String manufacturer, String make, String model) throws Exception {
		searchOrderHold(corpCode, year, manufacturer, make, model);
		try {
			verifyNoEntryIsDisplayed();
		} catch(OrderingErrorOccured e) {
			deleteOrderHold(corpCode, year, manufacturer, make, model);
		}
	}
	
	/**
	 * Creates order hold
	 * @param corpCode corp code
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param model model name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createOrderHold(String corpCode, String year, String manufacturer, String make, String model) throws Exception {
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_CORP_CODE_DROPDOWN_XPATH, corpCode);
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_YEAR_DROPDOWN_XPATH, year);
			} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_YEAR_DROPDOWN_XPATH, year);
			}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MANUFACTURER_DROPDOWN_XPATH, manufacturer);
			} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MANUFACTURER_DROPDOWN_XPATH, manufacturer);		
			}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MAKE_DROPDOWN_XPATH, make);
			} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MAKE_DROPDOWN_XPATH, make);
			}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MODEL_DROPDOWN_XPATH, model);
			} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MODEL_DROPDOWN_XPATH, model);		
			}	
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
	}
	
	/**
	 * Verifies the values of created order hold
	 * @param corpCode corp code
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param model model name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTheValuesOfRecordCreatedOrderHold(String corpCode, String year, String manufacturer, String make, String model) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MODEL_TABLE_VAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MODEL_TABLE_VAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();	
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FLEET_ASSIGNMENT_CORP_CODE_DROPDOWN_XPATH, corpCode, "Corp code is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_YEAR_DROPDOWN_XPATH, year,  "Year is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MANUFACTURER_DROPDOWN_XPATH, manufacturer,  "Manufacturer is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MAKE_DROPDOWN_XPATH, make,  "Make is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MODEL_DROPDOWN_XPATH, model,  "Model is not macthing");
	}
	
	/**
	 * Searches the creates order hold 
	 * @param corpCode corp code
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param model model name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchOrderHold(String corpCode, String year, String manufacturer, String make, String model) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_CORP_SEARCH_XPATH, corpCode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_YEAR_SEARCH_XPATH, year);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MANUFACTURER_SEARCH_XPATH, manufacturer);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MAKE_SEARCH_XPATH, make);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MODEL_SEARCH_XPATH, model);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * Verifies duplicate order hold can be added 
	 * @param corpCode corp code
	 * @param year year 
	 * @param manufacturer manufacturer name
	 * @param make maker name 
	 * @param model model name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDuplicateOrderHoldCanBeAdded(String corpCode, String year, String manufacturer, String make, String model) throws Exception {
		boolean flag=false;
		createOrderHold(corpCode, year, manufacturer, make, model);
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==false, "Duplicate error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * Deletes order hold
	 * @param corpCode corp code
	 * @param year year 
	 * @param manufacturer manufacturer name
	 * @param make maker name 
	 * @param model model name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteOrderHold(String corpCode, String year, String manufacturer, String make, String model) throws Exception {
		List<WebElement> searchResultColumns = BrowserAction.getElements(OrderingBOOnOrderQueuePageEnum.ORDERING_BO_OOQ_SEARCHRESULT_TABLE_ROWS_XPATH);
		for(int i=0; i<searchResultColumns.size(); i++ ) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MODEL_TABLE_VAL_XPATH);
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MODEL_TABLE_VAL_XPATH);	
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			OrderingCommonPage.checkAlertPopUp();
			searchOrderHold(corpCode, year, manufacturer, make, model);
		}
		verifyNoEntryIsDisplayed();
	}
	
	/**
	 * searches and deletes price protection if exists 
	 * @param corp corp code
	 * @param client client code
	 * @param year year 
	 * @param manufacturer manufacturer name
	 * @param make maker name
	 * @param model model name
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchAndDeleteIfPriceProtectionExists(String corp, String client, String year, String manufacturer, String make, String model) throws Exception {
		searchPriceProtection(corp, client, year, manufacturer, make, model);
		try {
			verifyNoEntryIsDisplayed();
		} catch(OrderingErrorOccured e) {
			deletePriceProtection(corp, client, year, manufacturer, make, model);
		}
	}
	
	/**
	 * creates price protection 
	 * @param corp corp code
	 * @param client client number 
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param model model name 
	 * @param trim trim name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createPriceProtection(String corp, String client, String year, String manufacturer, String make, String model, String trim) throws Exception {
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_CORP_CODE_DROPDOWN_XPATH, corp);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_CLIENT_NUMBER_INPUT_XPATH, client);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_CLIENT_NUMBER_SUGGESTION_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_CLIENT_NUMBER_SUGGESTION_XPATH);
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_YEAR_DROPDOWN_XPATH, year);
			} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_YEAR_DROPDOWN_XPATH, year);
			}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_MANUFACTURER_DROPDOWN_XPATH, manufacturer);
			} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_MANUFACTURER_DROPDOWN_XPATH, manufacturer);
			}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_MAKE_DROPDOWN_XPATH, make);
			} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_MAKE_DROPDOWN_XPATH, make);
			}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_MODEL_DROPDOWN_XPATH, model);
			} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_MODEL_DROPDOWN_XPATH, model);
			}
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_TRIM_DROPDOWN_XPATH, trim);
			} catch (StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_TRIM_DROPDOWN_XPATH, trim);
			}
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_PRICING_INVOICE_CHECKBOX_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_PRICING_INVOICE_CHECKBOX_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_SAVE_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_SAVE_BTN_XPATH);
	}
	
	/**
	 * Verifies the values of price protection created 
	 * @param corp corp code
	 * @param client client number 
	 * @param year year 
	 * @param manufacturer manufacturer name 
	 * @param make maker name 
	 * @param model model name 
	 * @param trim trim name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTheValuesOfRecordCreatedPriceProtection(String corp, String client, String year, String manufacturer, String make, String model, String trim) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("corpCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_CORP_CODE_DROPDOWN_XPATH, corp, "Corp code is not macthing");
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_CLIENT_NUMBER_INPUT_XPATH, client, "Client is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_YEAR_DROPDOWN_XPATH, year, "Year is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_MANUFACTURER_DROPDOWN_XPATH, manufacturer, "Manufacturer is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_MAKE_DROPDOWN_XPATH, make, "Make is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_HOLD_MODEL_DROPDOWN_XPATH, model, "Model is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_TRIM_DROPDOWN_XPATH, trim, "Trim is not macthing");
	}
	
	/**
	 * This method searches for the price protection created
	 * @param corp corp code
	 * @param client client number 
	 * @param year year 
	 * @param manufacturer manufacturer name
	 * @param make maker name 
	 * @param model model name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchPriceProtection(String corp, String client, String year, String manufacturer, String make, String model) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_CORP_SEARCH_XPATH, corp);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_FLEET_SEARCH_XPATH, client);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_YEAR_SEARCH_XPATH, year);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_MAKE_SEARCH_XPATH, make);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_MODEL_SEARCH_XPATH, model);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifies that duplicate price protection cannot be added 
	 * @param corp corp code
	 * @param client client number
	 * @param year year 
	 * @param manufacturer manufacturer name
	 * @param make maker name 
	 * @param model model name
	 * @param trim trim name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDuplicatePriceProtectionCannotBeAdded(String corp, String client, String year, String manufacturer, String make, String model, String trim) throws Exception {
		boolean flag=false;
		createPriceProtection(corp, client, year, manufacturer, make, model, trim);
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Duplicate error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		clickOnBackToQueueLinkPriceProtection();
	}
	
	/**
	 * This method updates price protection 
	 * @param corp corp code
	 * @param client client number
	 * @param year year
	 * @param manufacturer manufacture name
	 * @param make maker name
	 * @param model model name
	 * @param pricingScheme expected pricing scheme value after update
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void updatePriceProtection(String corp, String client, String year, String manufacturer, String make, String model, String pricingScheme) throws Exception {
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("corpCode");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_PRICING_MSRP_CHECKBOX_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_PRICING_MSRP_CHECKBOX_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_SAVE_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_SAVE_BTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchPriceProtection(corp, client, year, manufacturer, make, model);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_PRICING_SCHEME_TABLE_VAL_XPATH);
		Assert.assertTrue(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_PRICING_SCHEME_TABLE_VAL_XPATH).getText().equalsIgnoreCase(pricingScheme), "Incentive description is not matching");
	}
	
	/**
	 * This method deletes price protection 
	 * @param corp corp code
	 * @param client client number
	 * @param year year
	 * @param manufacturer manufacturer name
	 * @param make maker name
	 * @param model model name
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deletePriceProtection(String corp, String client, String year, String manufacturer, String make, String model) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_PRICING_SCHEME_TABLE_VAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_PRICING_SCHEME_TABLE_VAL_XPATH);	
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_DELETE_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_DELETE_BTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_DELETE_OK_MODAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRICE_PROTECTION_DELETE_OK_MODAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchPriceProtection(corp, client, year, manufacturer, make, model);
		verifyNoEntryIsDisplayed();
	}
	
	/**
	 *This method checks if a particular scheduler exists and deletes them if they do
	 * @param processName process name 
	 * @param schedulerType scheduler type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchAndDeleteIfSchedulerExists(String processName, String schedulerType) throws Exception {
		searchScheduler(processName, schedulerType);
		try {
			verifyNoEntryIsDisplayed();
		} catch(OrderingErrorOccured e) {
			deleteScheduler(processName, schedulerType);
		}
	}
	
	/**
	 * This method creates a scheduler 
	 * @param runType run type
	 * @param processName process name
	 * @param cornExpression corn expression 
	 * @param method method type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createScheduler(String runType, String processName, String cornExpression, String method) throws Exception {
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_RUN_TYPE_DROPDOWN_XPATH, runType);
		try {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_NAME_DROPDOWN_XPATH, processName);
		} catch(StaleElementReferenceException e) {
			waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_NAME_DROPDOWN_XPATH, processName);
		}
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_CRON_INPUT_XPATH);
		String id=OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_CRON_INPUT_ID.getValue();
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		js.executeScript("document.getElementById('"+id+"').value='"+cornExpression+"';");
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_REQUEST_TYPE_DROPDOWN_XPATH, method);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
	}
	
	/**
	 * This method verifies the value of the scheduler created
	 * @param runType run type
	 * @param processName process name
	 * @param cornExpression corn expression 
	 * @param method method type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTheValuesOfRecordCreatedScheduler(String runType, String processName, String cornExpression, String method) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_FREQUENCY_TABLE_VAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_FREQUENCY_TABLE_VAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_RUN_TYPE_DROPDOWN_XPATH, runType, "Run type is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_NAME_DROPDOWN_XPATH, processName, "Process name is not macthing");
		waitAndAssertTextBoxValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_CRON_INPUT_XPATH, cornExpression, "Corn expression is not macthing");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_REQUEST_TYPE_DROPDOWN_XPATH);
		Assert.assertTrue(new Select(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_REQUEST_TYPE_DROPDOWN_XPATH)).getFirstSelectedOption().getText().equalsIgnoreCase(method), "Method is not macthing");
		waitAndAssertDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_REQUEST_TYPE_DROPDOWN_XPATH, method,  "Method is not macthing");
	}
	
	/**
	 * This method searches for the scheduler 
	 * @param processName process name 
	 * @param schedulerType scheduler type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchScheduler(String processName, String schedulerType) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_NAME_INPUT_XPATH);
		WebDriverAction.clear(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_NAME_INPUT_XPATH));
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_NAME_INPUT_XPATH),processName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_TYPE_INPUT_XPATH);
		WebDriverAction.clear(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_TYPE_INPUT_XPATH));
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_TYPE_INPUT_XPATH),schedulerType);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifies that the duplicate scheduler cannot be added
	 * @param runType run type
	 * @param processName process name
	 * @param cornExpression corn expression 
	 * @param method method type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDuplicateSchedulerCannotBeAdded(String runType, String processName, String cornExpression, String method) throws Exception {
		boolean flag=false;
		createScheduler(runType, processName, cornExpression, method);
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Duplicate error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		clickOnBackToQueueLink();
	}
	
	/**
	 * This method updates the existing scheduler and verifies the updated value
	 * @param processName process name of the scheduler
	 * @param schedulerType scheduler type
	 * @param updatedCornExpression corn expression to be updated
	 * @param frequency expected frequency after updating corn expression
	 * @lastModifiedBy vikumar
	 * @throws Exception
	 */
	public static void updateScheduler(String processName, String schedulerType, String updatedCornExpression, String frequency) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_FREQUENCY_TABLE_VAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_FREQUENCY_TABLE_VAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_CRON_INPUT_XPATH);
		WebDriverAction.clear(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_CRON_INPUT_XPATH));
		String id=OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_PROCESS_CRON_INPUT_ID.getValue();
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		js.executeScript("document.getElementById('"+id+"').value='"+updatedCornExpression+"';");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_SAVE_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchScheduler(processName, schedulerType);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_FREQUENCY_TABLE_VAL_XPATH);
		Assert.assertTrue(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_FREQUENCY_TABLE_VAL_XPATH).getText().equalsIgnoreCase(frequency), "Frequency is not matching");
	}
	
	/**
	 * This method deletes the scheduler 
	 * @param processName process name of the scheduler 
	 * @param schedulerType scheduler type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteScheduler(String processName, String schedulerType) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_FREQUENCY_TABLE_VAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_FREQUENCY_TABLE_VAL_XPATH);	
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_OK_MODAL_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		searchScheduler(processName, schedulerType);
		verifyNoEntryIsDisplayed();
	}
		
	/**
	 * This method add the NEW DIO
	 * @throws Exception
	 * @lastModifiedBy mkhairanar
	 */
	public static void addNewDIO() throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_ADD_XPATH);
		String dioOptionCode=CommonPage.randomAlphaNumericString();
		System.out.println("Entered DIO Option Code:: "+dioOptionCode);
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_OPTION_CODE_ID,dioOptionCode);
		String dioOptionDescription=CommonPage.randomAlphaNumericString();
		System.out.println("Entered DIO option description:: "+dioOptionDescription);
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_OPTION_DESC_ID,dioOptionDescription);
		String dioOptionPrice=String.valueOf(CommonPage.randomNumberInRange(100));
		System.out.println("Entered DIO option price:: "+dioOptionPrice);
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_OPTION_MAX_PRICE_ID, dioOptionPrice);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_SAVE_BUTTON_ID);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
	}
	
	/**
	 * This method waits for BMT dio page.
	 * @throws Exception
	 * @lastModifiedBy skathule
	 */
	public static void waitForDIOMaintainancePage() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_OPTIONCODE_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_OPTION_DESCRIPTION_XPATH);
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_BRAND_PROVIDER_XPATH);
	}

	/**
	 * This method search BMT dio
	 * @throws Exception
	 * @param OptionCode
	 * @param OptionDescription
	 * @lastModifiedBy skathule
	 */
	public static void searchBMTDIO(String searchSection, String searchvalue) throws Exception {
		OrderingBOBusinessMaintainedTablesPage.enterOrSelectFieldValue(searchSection, searchvalue);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}

	/**
	 * This method enters the value in search field
	 * @throws Exception
	 * @param OptionCode
	 * @lastModifiedBy skathule
	 */
	public static void enterOrSelectFieldValue(String fieldName, String searchText) throws Exception {
		WebDriverAction.clickAndClear(BrowserAccess.getLocator("_XPATH", OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_SEARCH_OPTIONCODE_XPATH.getValue()));
		System.out.println(fieldName + ": " + searchText);
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator("_XPATH", OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_SEARCH_OPTIONCODE_XPATH.getValue()), searchText);
		CommonPage.pressEnter();
	}

	/**
	 * This method verifies edited DIo details in BO
	 * @throws Exception
	 * @lastModifiedBy skathule
	 */
	public static void verifyEditedDioDetailsInBO() throws Exception {
		System.out.println(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_TABLE_MAXPRICE_XPATH).getText());
		System.out.println( CommonPage.getTestData("DioPrice"));
		BrowserAssert.assertNotEquals((BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DIO_TABLE_MAXPRICE_XPATH)).getText(),CommonPage.getTestData("AdhocDioPrice"));
		System.out.println("details should not matched");
	} 

	/**
	 * This method waits for the Business Maintained Table  page to load.
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void waitForBusinessMaintainedTableOption() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_TITLE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_ACTIVE_RULES_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_ACTIVE_RULES_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_ACTIVE_RULES_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_ARCHIVED_RULES_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_SUBTITLE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_SUBTITLE_XPATH);
	}
	
	/**
	 * This method will verify Capping Rules titles and labels
	 * @throws Exception
	 * @lastModifiedBy sagrawal
	 */
	public static void verifyCappingRulesTitlesAndLabels() throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_TITLE_XPATH, CommonPage.getTestData("WaitTime"));
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_TITLE_XPATH), "Capping Rules", "Capping Rules title is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_SUBTITLE_XPATH), "Capping Smart Rules", "Capping Smart Rules sub title is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_RULE_ID_XPATH), "Rule ID", "Capping Smart Rules Rule ID is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_RULE_NAME_XPATH), "Rule Name", "Capping Smart Rules Rule Name is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_CORP_CODE_XPATH), "Corp Code", "Capping Smart Rules Corp Code is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_CLIENT_XPATH), "Client", "Capping Smart Rules Client is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_BREAKDOWN_XPATH), "Breakdown", "Capping Smart Rules Breakdown is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_EFFECTIVE_DATE_XPATH), "Effective Date", "Capping Smart Rules Effective Date is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_EFFECTIVE_UNTIL_XPATH), "Effective Until", "Capping Smart Rules Effective Until is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_CONTRACT_TYPE_XPATH), "Contract Type", "Capping Smart Rules Contract Type is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_ORDER_TYPE_XPATH), "Order Type", "Capping Smart Rules Rule ID is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_PRODUCT_CLASS_XPATH), "Product Class", "Capping Smart Rules Product Class is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_YEAR_XPATH), "Year", "Capping Smart Rules Year is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_MAKE_XPATH), "Make", "Capping Smart Rules Make is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_MODEL_XPATH), "Model", "Capping Smart Rules Model is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_STATE_PROVINSE_XPATH), "State/Province", "Capping Smart Rules State/Province is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_VISIBLE_TO_CLIENT_XPATH), "Visible to Client", "Capping Smart Rules Visible to Client is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_APPLIED_TO_INFLIGHT_ORDERS_XPATH), "Applied to In-Flight Orders", "Capping Smart Rules Applied to In-Flight Orders is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_CLEAR_FILTER_BUTTON_XPATH), "Clear Filters", "Capping Smart Rules clear filters is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_SEARCH_BUTTON_XPATH), "Search", "Capping Smart Rules Search is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_BULK_ACTIONS_BUTTON_XPATH), "BULK ACTIONS", "Capping Smart Rules Bulk Actions is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_ADD_NEW_BUTTON_XPATH), "Add New", "Capping Smart Rules Add New is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULES_EXPORT_BUTTON_XPATH), "Export", "Capping Smart Rules Bulk Actions is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_SUBTITLE_XPATH), "Capping Rules", "Capping Rules sub title is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_RULE_ID_XPATH), "Rule ID", "Capping Smart Rules Rule ID is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_RULE_NAME_XPATH), "Rule Name", "Capping Smart Rules Rule Name is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CORP_CODE_XPATH), "Corp Code", "Capping Smart Rules Corp Code is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CLIENT_XPATH), "Client", "Capping Smart Rules Client is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_BREAKDOWN_XPATH), "Breakdown", "Capping Smart Rules Breakdown is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_EFFECTIVE_DATE_XPATH), "Effective Date", "Capping Smart Rules Effective Date is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_EFFECTIVE_UNTIL_XPATH), "Effective Until", "Capping Smart Rules Effective Until is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CONTRACT_TYPE_XPATH), "Contract Type", "Capping Smart Rules Contract Type is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_ORDER_TYPE_XPATH), "Order Type", "Capping Smart Rules Rule ID is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_PRODUCT_CLASS_XPATH), "Product Class", "Capping Smart Rules Product Class is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_YEAR_XPATH), "Year", "Capping Smart Rules Year is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MAKE_XPATH), "Make", "Capping Smart Rules Make is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MODEL_XPATH), "Model", "Capping Smart Rules Model is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_STATE_PROVINSE_XPATH), "State/Province", "Capping Smart Rules State/Province is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_VISIBLE_TO_CLIENT_XPATH), "Visible to Client", "Capping Smart Rules Visible to Client is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_APPLIED_TO_INFLIGHT_ORDERS_XPATH), "Applied to In-Flight Orders", "Capping Smart Rules Applied to In-Flight Orders is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CLEAR_FILTER_BUTTON_XPATH), "Clear Filters", "Capping Smart Rules Applied to In-Flight Orders is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SEARCH_BUTTON_XPATH), "Search", "Capping Smart Rules Applied to In-Flight Orders is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_BULK_ACTIONS_BUTTON_XPATH), "BULK ACTIONS", "Capping Smart Rules Bulk Actions is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_ADD_NEW_BUTTON_XPATH), "Add New", "Capping Smart Rules Add New is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_EXPORT_BUTTON_XPATH), "Export", "Capping Smart Rules Bulk Actions is not matched");
	}

	/**
	 * This method will click on Add new button as per rule passed as parameter
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnAddNewButton(String rule) throws Exception {
		switch(rule) {
			case "Capping Smart Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SMART_ADD_NEW_RULE_BUTTON_ID, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SMART_ADD_NEW_RULE_BUTTON_ID);
				break;
			case "Capping Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_BUTTON_ID, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_BUTTON_ID);
				break;
			default: throw new OrderingErrorOccured("Invalid rule passed");
		}
	}
	
	/**
	 * This method will click on Cancel button of Add Rule page
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void clickOnCancelButton() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_CANCEL_ID, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SCHEDULER_CANCEL_ID);
	}
	
	/**
	 * This method will verify all dropdown default values an there all options
	 * @param element element enum
	 * @param defaultValue drop down default value
	 * @param options drop down all options as array 
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyDropDownDefaultValueAndOptions(WebElement element, String defaultValue, String... options) {
		Select slt = new Select(element);
		if(!(slt.getFirstSelectedOption().getText().trim().equals(defaultValue)))
			Assert.assertEquals(slt.getFirstSelectedOption().getText(), defaultValue, "Drop-down default value is not matched");
		List<WebElement> list = slt.getOptions();
		ArrayList<String> listString = new ArrayList<>();
		list.stream().forEach(e->listString.add(e.getText()));
		ArrayList<String> optionsString = new ArrayList<>(Arrays.asList(options));
		if(!(listString.equals(optionsString)))
			Assert.assertEquals(listString, optionsString, "Drop-down options are not matched");
	}
	
	/**
	 * This method will verify all fields available on add new rule page
	 * @param rule rule name
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyAllFieldsFromAddNewRule(String rule) throws Exception {
		switch(rule) {
			case "Capping Smart Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PAGE_TITLE_ID, CommonPage.getTestData("WaitTime"));
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PAGE_TITLE_ID), "ADD CAPPING SMART RULE", "Add Capping Smart Rule title is not matched");
				CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SMART_RULE_CONTAINER_XPATH), "Smart Rule container is not displayed");
				break;
			case "Capping Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PAGE_TITLE_ID, CommonPage.getTestData("WaitTime"));
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PAGE_TITLE_ID), "ADD CAPPING RULE", "Add Capping Rule title is not matched");
				CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_TEXT_ID), "Capping Rule text is not displayed");
				CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CLEAR_CAPPING_RULE_ID), "Clear Capping Rule text is not displayed");
				break;
			default: throw new OrderingErrorOccured("Invalid rule passed");
		}
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_START_DATE_ID), "Start date input is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_START_DATE_SYMBOL_XPATH), "Start date Symbol is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_EFFECTIVE_DATE_ID), "End date input is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_EFFECTIVE_DATE_SYMBOL_XPATH), "End date Symbol is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CORP_ID), "Corp drop down is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CONTRACT_TYPE_NAME), "Contract type is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_ORDER_TYPE_NAME), "Order Type is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PRODUCT_CLASS_NAME), "Prodcuct Class is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_YEAR_ID), "Year is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MAKE_ID), "Make is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MODEL_ID), "Model is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_STATE_ID), "state drop down is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CLIENT_SEARCH_ID), "client Search input is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_VEHICLE_BREAKDOWN_NAME), "vehicle breakdown is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_DRIVER_BREAKDOWN_NAME), "Driver breakdown is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_APPROVE_FOR_CLIENT_ID), "Approve for client view is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_APPLY_INFLIGHT_ORDERS_ID), "Inflight Orders not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CAPPING_NOTES_TEXT_ID), "Capping note text is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CAPPING_NOTES_CLEAR_ID), "Capping notes clear is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SAVE_BUTTON_ID), "Save button is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CANCEL_BUTTON_ID), "Cancel button is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_NAVIGATION_ID), "Rule navigation not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SIGNOUT_XPATH), "Sign out button is not displayed");
		verifyDropDownDefaultValueAndOptions(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CORP_ID),"FA", "FA", "CA");
		verifyDropDownDefaultValueAndOptions(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_ORDER_TYPE_NAME),"All Order Types", "All Order Types","Factory","Dealer", "Stock");
		verifyDropDownDefaultValueAndOptions(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CONTRACT_TYPE_NAME),"All Contract Types", "All Contract Types", "Caribbean Lease", "Company Owned", "Lease", "Loan");
	}
	
	/**
	 * This method will click on select smart rule or enter capping rule as per passed paramter
	 * @param rule rule name
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectSmartRuleOrEnterCappingRule(String rule) throws Exception {
		switch(rule) {
			case "Capping Smart Rule":
				String smartRule = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SMART_RULE_SELECTION_XPATH.getValue();
				smartRule = smartRule.replace("$SmartRule#", CommonPage.getTestData("RuleName"));
				WebDriverAction.click(By.xpath(smartRule));
				break;
			case "Capping Rule":
				BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_TEXT_ID, CommonPage.getTestData("RuleName"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CLEAR_CAPPING_RULE_ID);
				BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_TEXT_ID, CommonPage.getTestData("RuleName"));
				System.out.println("Rule:-"+CommonPage.getTestData("RuleName"));
				break;
			default: throw new OrderingErrorOccured("Invalid rule passed");
		}
	}
	
	/**
	 * This method changes the client i.e. enters the client id on the search box and clicks on the first displayed client.
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void changeClient(String clientNumber) throws Exception {
		WebDriverAction.getElement(By.id(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CLIENT_SEARCH_FIELD_ID.getValue())).sendKeys(Keys.CONTROL,"a");
		WebDriverAction.getElement(By.id(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CLIENT_SEARCH_FIELD_ID.getValue())).sendKeys(Keys.BACK_SPACE);
		BrowserAction.clickandClear(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CLIENT_SEARCH_FIELD_ID);
		BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CLIENT_SEARCH_FIELD_ID, clientNumber);
		System.out.println("Client Number:-"+clientNumber);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CLIENT_SEARCH_OPTION_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CLIENT_SEARCH_OPTION_XPATH);
	}
	
	/**
	 * This method will select start date
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectStartDate() throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_START_DATE_SYMBOL_XPATH);
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_START_DATE_CSS, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_START_DATE_CSS);
		String copy = Keys.chord(Keys.CONTROL,Keys.chord("c"));
		WebDriverAction.getElement(By.id(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_START_DATE_ID.getValue())).sendKeys(Keys.CONTROL+"a");
		WebDriverAction.getElement(By.id(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_START_DATE_ID.getValue())).sendKeys(copy);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		String startDate = (String) contents.getTransferData(DataFlavor.stringFlavor);
		System.out.println("Start Date:-"+startDate);
		String startDateMMDDYY = startDate.substring(5, 7)+"-"+startDate.substring(8, 10)+"-"+startDate.substring(0, 4);;
		System.out.println("Start Date MMDDYY:-"+startDateMMDDYY);
		ruleDetails.put("StartDate", startDate);
		ruleDetails.put("StartDateMMDDYY", startDateMMDDYY);
	}
	
	/**
	 * This method will select end date
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectEndDate() throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_EFFECTIVE_DATE_SYMBOL_XPATH);
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_EFFECTIVE_DATE_CSS, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_EFFECTIVE_DATE_CSS);
		String copy = Keys.chord(Keys.CONTROL,Keys.chord("c"));
		WebDriverAction.getElement(By.id(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_EFFECTIVE_DATE_ID.getValue())).sendKeys(Keys.CONTROL+"a");
		WebDriverAction.getElement(By.id(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_EFFECTIVE_DATE_ID.getValue())).sendKeys(copy);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		String endDate = (String) contents.getTransferData(DataFlavor.stringFlavor);
		System.out.println("End Date:-"+endDate);
		String endDateMMDDYY = endDate.substring(5, 7)+"-"+endDate.substring(8, 10)+"-"+endDate.substring(0, 4);
		System.out.println("End Date MMDDYY:-"+endDateMMDDYY);
		ruleDetails.put("EndDate", endDate);
		ruleDetails.put("EndDateMMDDYY", endDateMMDDYY);
	}
	
	/**
	 * This method deselect selected client
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void deselectSelectedClient(String clientNumber) throws Exception {
		String client = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_CLIENT_XPATH.getValue().replace("$ClientNumber#", clientNumber);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(client));
		WebDriverAction.click(By.xpath(client));
	}
	
	/**
	 * This method will enter and select all value for fields on add rule page
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectAllDetails() throws Exception {
		selectStartDate();
		BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CAPPING_NOTES_TEXT_ID, CommonPage.getTestData("RuleNote"));
		OrderingBOBusinessMaintainedTablesPage.changeClient(CommonPage.getTestData("ClientNumber"));
		OrderingBOBusinessMaintainedTablesPage.changeClient(CommonPage.getTestData("CustomColumn1"));
		selectEndDate();
		deselectSelectedClient(CommonPage.getTestData("CustomColumn1"));
		ruleDetails.put("ClientNumber",CommonPage.getTestData("ClientNumber"));
		CommonPage.selectDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CORP_ID, CommonPage.getTestData("CorpCode"), "Corp Code");
		CommonPage.selectDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CONTRACT_TYPE_NAME, CommonPage.getTestData("ContractType"), "Contract Type");
		CommonPage.selectDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_ORDER_TYPE_NAME, CommonPage.getTestData("OrderType"), "Order Type");
		CommonPage.selectDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PRODUCT_CLASS_NAME, CommonPage.getTestData("ProductClass"), "Product Class");
		CommonPage.selectDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_YEAR_ID, CommonPage.getTestData("Year"), "Year");
		CommonPage.selectDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MAKE_ID, CommonPage.getTestData("Make"), "Make");
		CommonPage.selectDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MODEL_ID, CommonPage.getTestData("Model"), "Model");
		CommonPage.selectDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_STATE_ID, CommonPage.getTestData("TitleState"), "State");
		if(CommonPage.getTestData("ApproveForClientView").equals("Y"))
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_APPROVE_FOR_CLIENT_ID);
		if(CommonPage.getTestData("InFlightOrder").equals("Y"))
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_APPLY_INFLIGHT_ORDERS_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CAPPING_NOTES_CLEAR_ID);
		BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CAPPING_NOTES_TEXT_ID, CommonPage.getTestData("RuleNote"));
		System.out.println("Rule Note:-"+CommonPage.getTestData("RuleNote"));
		storeRuleDetails();
	}
	
	/**
	 * This method will store all rule details
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void storeRuleDetails() throws Exception {
		Select slt = new Select(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PRODUCT_CLASS_NAME));
		ruleDetails.put("ProductClass", slt.getFirstSelectedOption().getAttribute("value").trim());
		slt = new Select(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_ORDER_TYPE_NAME));
		ruleDetails.put("OrderType", slt.getFirstSelectedOption().getAttribute("value").trim());
		slt = new Select(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CONTRACT_TYPE_NAME));
		ruleDetails.put("ContractType", slt.getFirstSelectedOption().getAttribute("value").trim());
		ruleDetails.put("RuleNote", CommonPage.getTestData("RuleNote"));
	}
	
	/**
	 * This method will click on Save button
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnSaveButton() throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SAVE_BUTTON_ID);
	}
	
	/**
	 * This method will store rule id in map
	 * @param rule rule name
	 * @param action add, copy
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void getRuleId(String rule, String action) throws Exception {
		String ruleIdXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_RULE_ID_COLUMN_DATA1_XPATH.getValue().replace("$Rule#", rule);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(ruleIdXpath));
		WebDriverVerify.verifyElementIsPresent(By.xpath(ruleIdXpath));
		WebDriverVerify.verifyElementEnabled(By.xpath(ruleIdXpath));
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		switch(action) {
			case "Add":
				ruleDetails.put("RuleId", WebDriverAction.getElement(By.xpath(ruleIdXpath)).getText().trim());
				System.out.println("Rule id:-"+ruleDetails.get("RuleId"));
				break;
			case "Copy":
				ruleDetails.put("CopyRuleId", WebDriverAction.getElement(By.xpath(ruleIdXpath)).getText().trim());
				System.out.println("Copy Rule id:-"+ruleDetails.get("CopyRuleId"));
				break;
			default: throw new OrderingErrorOccured("Invalid action please provide Add or Copy");
		}
	}
	
	/**
	 * This method verify all value selected while adding new rule in rule table in archived rule tab
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyArchivedRuleInTable() throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_RULE_COLUMN_DATA_XPATH.getValue(),CommonPage.getTestData("RuleName"),"Rule Name");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_RULE_ID_COLUMN_DATA_ARCHIVED_XPATH.getValue(),ruleDetails.get("CopyRuleId"),"Rule Id");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ARCHIVED_DATE_COLUMN_DATA_ARCHIVED_XPATH.getValue(),ruleDetails.get("StartDateMMDDYY"),"Archievd Date");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CORP_CODE_COLUMN_DATA_XPATH.getValue(),CommonPage.getTestData("CorpCode"),"Corp Code");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_COLUMN_XPATH.getValue(),ruleDetails.get("CopyClientNumber"),"Client Number");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_VIEW_XPATH.getValue(),CommonPage.getTestData("ApproveForClientView"),"Client View");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_IN_FLIGHT_ORDER_COLUMN_XPATH.getValue(),CommonPage.getTestData("InFlightOrder"),"Inflight Order");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CONTRACT_TYPE_COLUMN_XPATH.getValue(),ruleDetails.get("ContractType"),"Contract Type");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_TYPE_XPATH.getValue(),ruleDetails.get("OrderType"),"Order Type");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRODUCT_CLASS_COLUMN_XPATH.getValue(),ruleDetails.get("ProductClass"),"Product Class");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_YEAR_CULUMN_XPATH.getValue(),CommonPage.getTestData("Year"),"Year");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MAKE_COLUMN_XPATH.getValue(),CommonPage.getTestData("Make"),"Make");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MODEL_COLUMN_XPATH.getValue(),CommonPage.getTestData("Model"),"Model");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_EFFECTIVE_DATE_COLUMN_XPATH.getValue(),ruleDetails.get("StartDateMMDDYY"),"Start Date");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EFFECTIVE_UNTIL_DATE_XPATH.getValue(),ruleDetails.get("EndDateMMDDYY"),"End Date");
	}
	
	/**
	 * This method verify all value selected while adding new rule in rule table from Active and Archived rule tab
	 * @param action add, copy
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyRuleValueFromTable(String action) throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		switch(action) {
			case "Add":
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_RULE_COLUMN_DATA_XPATH.getValue(),CommonPage.getTestData("RuleName"),"Rule Name");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_RULE_ID_COLUMN_DATA_XPATH.getValue(),ruleDetails.get("RuleId"),"Rule Id");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CORP_CODE_COLUMN_DATA_XPATH.getValue(),CommonPage.getTestData("CorpCode"),"Corp Code");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_COLUMN_XPATH.getValue(),ruleDetails.get("ClientNumber"),"Client Number");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_VIEW_XPATH.getValue(),CommonPage.getTestData("ApproveForClientView"),"Client View");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_IN_FLIGHT_ORDER_COLUMN_XPATH.getValue(),CommonPage.getTestData("InFlightOrder"),"Inflight Order");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CONTRACT_TYPE_COLUMN_XPATH.getValue(),ruleDetails.get("ContractType"),"Contract Type");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_TYPE_XPATH.getValue(),ruleDetails.get("OrderType"),"Order Type");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRODUCT_CLASS_COLUMN_XPATH.getValue(),ruleDetails.get("ProductClass"),"Product Class");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_STATE_COLUMN_XPATH.getValue(),CommonPage.getTestData("TitleState"),"State");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_YEAR_CULUMN_XPATH.getValue(),CommonPage.getTestData("Year"),"Year");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MAKE_COLUMN_XPATH.getValue(),CommonPage.getTestData("Make"),"Make");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MODEL_COLUMN_XPATH.getValue(),CommonPage.getTestData("Model"),"Model");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_EFFECTIVE_DATE_COLUMN_XPATH.getValue(),ruleDetails.get("StartDateMMDDYY"),"Start Date");
				verifyfieldValueForAdd(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EFFECTIVE_UNTIL_DATE_XPATH.getValue(),ruleDetails.get("EndDateMMDDYY"),"End Date");
				break;
			case "Copy":
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_RULE_COLUMN_DATA_XPATH.getValue(),CommonPage.getTestData("RuleName"),"Rule Name");
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_RULE_ID_COLUMN_DATA_XPATH.getValue(),ruleDetails.get("CopyRuleId"),"Rule Id");
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CORP_CODE_COLUMN_DATA_XPATH.getValue(),CommonPage.getTestData("CorpCode"),"Corp Code");
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_COLUMN_XPATH.getValue(),ruleDetails.get("CopyClientNumber"),"Client Number");
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_VIEW_XPATH.getValue(),CommonPage.getTestData("ApproveForClientView"),"Client View");
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_IN_FLIGHT_ORDER_COLUMN_XPATH.getValue(),CommonPage.getTestData("InFlightOrder"),"Inflight Order");
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CONTRACT_TYPE_COLUMN_XPATH.getValue(),ruleDetails.get("ContractType"),"Contract Type");
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_TYPE_XPATH.getValue(),ruleDetails.get("OrderType"),"Order Type");
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRODUCT_CLASS_COLUMN_XPATH.getValue(),ruleDetails.get("ProductClass"),"Product Class");
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_YEAR_CULUMN_XPATH.getValue(),CommonPage.getTestData("Year"),"Year");
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MAKE_COLUMN_XPATH.getValue(),CommonPage.getTestData("Make"),"Make");
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MODEL_COLUMN_XPATH.getValue(),CommonPage.getTestData("Model"),"Model");
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_EFFECTIVE_DATE_COLUMN_XPATH.getValue(),ruleDetails.get("StartDateMMDDYY"),"Start Date");
				verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EFFECTIVE_UNTIL_DATE_XPATH.getValue(),ruleDetails.get("EndDateMMDDYY"),"End Date");
				break;
			default: throw new OrderingErrorOccured("Invalid action pass Add or Copy");
		}
		
	}
	
	/**
	 * This method verify all fields value which are selected while adding new rule in rule table in Archived Rule tab
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyUnarchivedRuleValueFromTable() throws Exception {
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_RULE_COLUMN_DATA_XPATH.getValue(),CommonPage.getTestData("RuleName"),"Rule Name");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_RULE_ID_COLUMN_DATA_XPATH.getValue(),ruleDetails.get("CopyRuleId"),"Rule Id");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CORP_CODE_COLUMN_DATA_XPATH.getValue(),CommonPage.getTestData("CorpCode"),"Corp Code");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_COLUMN_XPATH.getValue(),ruleDetails.get("CopyClientNumber"),"Client Number");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_VIEW_XPATH.getValue(),CommonPage.getTestData("ApproveForClientView"),"Client View");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_IN_FLIGHT_ORDER_COLUMN_XPATH.getValue(),CommonPage.getTestData("InFlightOrder"),"Inflight Order");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CONTRACT_TYPE_COLUMN_XPATH.getValue(),ruleDetails.get("ContractType"),"Contract Type");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_TYPE_XPATH.getValue(),ruleDetails.get("OrderType"),"Order Type");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRODUCT_CLASS_COLUMN_XPATH.getValue(),ruleDetails.get("ProductClass"),"Product Class");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_YEAR_CULUMN_XPATH.getValue(),CommonPage.getTestData("Year"),"Year");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MAKE_COLUMN_XPATH.getValue(),CommonPage.getTestData("Make"),"Make");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MODEL_COLUMN_XPATH.getValue(),CommonPage.getTestData("Model"),"Model");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_EFFECTIVE_DATE_COLUMN_XPATH.getValue(),ruleDetails.get("StartDateMMDDYY"),"Start Date");
		verifyfieldValueForCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EFFECTIVE_UNTIL_DATE_XPATH.getValue(),"","End Date");
	}
	
	/**
	 * This method will click on row action link (three verticle dots) for Active and Archived rule tab
	 * @param ruleId rule id
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnRowAction(String ruleId) throws Exception {
		String rowActionXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ROW_ACTIONS_XPATH.getValue().replace("$RuleId#", ruleId);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(rowActionXpath));
		WebDriverAction.click(By.xpath(rowActionXpath));
	}
	
	/**
	 * This method will click on row action option which will be visible after click on row action
	 * @param ruleId rule id
	 * @param actionOption View, Edit, Copy, Archive, Unarchive
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnRowActionOption(String ruleId, String actionOption) throws Exception {
		String rowActionOptionXpath = null;
		switch(actionOption) {
			case "View": rowActionOptionXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ROW_ACTION_VIEW_XPATH.getValue().replace("$RuleId#", ruleId);
				break;
			case "Edit": rowActionOptionXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ROW_ACTION_EDIT_XPATH.getValue().replace("$RuleId#", ruleId);
				break;
			case "Copy": rowActionOptionXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ROW_ACTION_COPY_XPATH.getValue().replace("$RuleId#", ruleId);
				break; 
			case "Archive": rowActionOptionXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ROW_ACTION_ARCHIVE_XPATH.getValue().replace("$RuleId#", ruleId);
				break;
			case "Unarchive": rowActionOptionXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ROW_ACTION_UNARCHIVE_XPATH.getValue().replace("$RuleId#", ruleId);
				break;
			default: throw new OrderingErrorOccured("Invalid row action option");
		}
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(rowActionOptionXpath));
		WebDriverVerify.verifyElementIsPresent(By.xpath(rowActionOptionXpath));
		WebDriverVerify.verifyElementEnabled(By.xpath(rowActionOptionXpath));
		WebDriverAction.hoverOverElement(By.xpath(rowActionOptionXpath));
		WebDriverAction.click(By.xpath(rowActionOptionXpath));
	}
	
	/**
	 * This method will verify element is disabled
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDisableElement(Enum < ? > fieldtLocatorEnum, String fieldName) throws Exception {
		if(!(BrowserAction.getElementAttributeValue(fieldtLocatorEnum, "disabled").equals("true")))
			throw new OrderingErrorOccured(fieldName+" is not disabled");
	}
	
	/**
	 * This method will verify all fields are disabled on View rule page for active rule tab
	 * @param ruleType rule type
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyViewRulePage(String ruleType) throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CORP_ID, CommonPage.getTestData("WaitTime"));
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CORP_ID, "Corp Code");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_START_DATE_ID, "Start Date");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_EFFECTIVE_DATE_ID, "End Dtae");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CONTRACT_TYPE_NAME, "Contract Type");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_ORDER_TYPE_NAME, "Order Type");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PRODUCT_CLASS_NAME, "Product Class");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_YEAR_ID, "Year");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MAKE_ID, "Make");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MODEL_ID, "Model");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_STATE_ID, "State");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_APPROVE_FOR_CLIENT_ID, "Approve for Client");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_APPLY_INFLIGHT_ORDERS_ID, "InFlight Orders");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CAPPING_NOTES_TEXT_ID, "Capping Note");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CLIENT_SEARCH_ID, "Client Search");
		switch(ruleType) {
			case "Capping Smart Rule":
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PAGE_TITLE_ID), "VIEW CAPPING SMART RULE", "View Capping Smart Rule title is not matched");
				String ruleXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SMART_RULE_XPATH.getValue().replace("$Rule#", CommonPage.getTestData("RuleName"));
				if(!(WebDriverAction.getElementAttributeValue(By.xpath(ruleXpath), "disabled").equals("true")))
					throw new OrderingErrorOccured("Rule is not disabled");
				break;
			case "Capping Rule":
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PAGE_TITLE_ID), "VIEW CAPPING RULE", "View Capping Smart Rule title is not matched");
				if(!(BrowserAction.getElementAttributeValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_TEXT_ID, "disabled").equals("true")))
					throw new OrderingErrorOccured("Client search is not disabled");
				break;
			default: throw new OrderingErrorOccured("Invalid Rule Type");
		}
	}
	
	/**
	 * This method will verify all fields value from View rule page which are selected while adding rule for Active Rule Tab
	 * @param ruleType rule type
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDataOnViewRulePage(String ruleType) throws Exception {
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CORP_ID, CommonPage.getTestData("CorpCode"), "corp code");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CONTRACT_TYPE_NAME, CommonPage.getTestData("ContractType"), "Contract Type");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_ORDER_TYPE_NAME, CommonPage.getTestData("OrderType"), "Order Type");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PRODUCT_CLASS_NAME, CommonPage.getTestData("ProductClass"), "Product Class");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_YEAR_ID, CommonPage.getTestData("Year"), "Year");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MAKE_ID, CommonPage.getTestData("Make"), "Make");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MODEL_ID, CommonPage.getTestData("Model"), "Model");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_STATE_ID, CommonPage.getTestData("TitleState"), "State");
		List<WebElement> ele = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_SELECTED_XPATH);
		for(WebElement elem : ele)
			if(!(elem.getText().contains(CommonPage.getTestData("ClientNumber"))))
				throw new OrderingErrorOccured("Client number is not matched");
		switch(ruleType) {
			case "Capping Smart Rule":
				String ruleXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SMART_RULE_XPATH.getValue().replace("$Rule#", CommonPage.getTestData("RuleName"));
				if(!(WebDriverAction.getElementAttributeValue(By.xpath(ruleXpath), "disabled").equals("true")))
					throw new OrderingErrorOccured("Rule is not disabled");
				break;
			case "Capping Rule":
				System.out.println("Rule :-"+BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_TEXT_ID).getText());
				break;
			default: throw new OrderingErrorOccured("Invalid Rule Type");
		}
	}
	
	/**
	 * This method will verify all fields value from View rule page which are selected while adding rule for Archived Rule Tab
	 * @param ruleType rule type
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDataOnViewRulePageForUnArchive(String ruleType) throws Exception {
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CORP_ID, CommonPage.getTestData("CorpCode"), "corp code");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CONTRACT_TYPE_NAME, CommonPage.getTestData("ContractType"), "Contract Type");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_ORDER_TYPE_NAME, ruleDetails.get("CopyOrderType"), "Order Type");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PRODUCT_CLASS_NAME, ruleDetails.get("CopyProductClass"), "Product Class");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_YEAR_ID, CommonPage.getTestData("Year"), "Year");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MAKE_ID, CommonPage.getTestData("Make"), "Make");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MODEL_ID, CommonPage.getTestData("Model"), "Model");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_STATE_ID, CommonPage.getTestData("TitleState"), "State");
		List<WebElement> ele = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_SELECTED_XPATH);
		for(WebElement elem : ele)
			if(!(elem.getText().contains(ruleDetails.get("CopyClientNumber"))))
				throw new OrderingErrorOccured("Client number is not matched");
		switch(ruleType) {
			case "Capping Smart Rule":
				String ruleXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SMART_RULE_XPATH.getValue().replace("$Rule#", CommonPage.getTestData("RuleName"));
				if(!(WebDriverAction.getElementAttributeValue(By.xpath(ruleXpath), "disabled").equals("true")))
					throw new OrderingErrorOccured("Rule is not disabled");
				break;
			case "Capping Rule":
				System.out.println("Rule :-"+BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_TEXT_ID).getText());
				break;
			default: throw new OrderingErrorOccured("Invalid Rule Type");
		}
	}
	
	/**
	 * This method will click on Capping rule link from navigation
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goBackToCappingRulePage() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_GO_BACK_TO_CAPPING_RULE_XPATH, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_GO_BACK_TO_CAPPING_RULE_XPATH);
	}
	
	/**
	 * This method will verify edit page all fields value and then update some rule details
	 * @UpdatedFields OrderType, product Class, Rule Note
	 * @param ruleType rule type 
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyEditPageAndUpdateSomeDetails(String ruleType) throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CORP_ID, CommonPage.getTestData("WaitTime"));
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CORP_ID, "Corp Code");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_APPLY_INFLIGHT_ORDERS_ID, "InFlight Orders");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CLIENT_SEARCH_ID, "Client Search");
		List<WebElement> ele = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_SELECTED_XPATH);
		for(WebElement elem : ele)
			if(!(elem.getText().contains(CommonPage.getTestData("ClientNumber"))))
				throw new OrderingErrorOccured("Client number is not matched");
		switch(ruleType) {
			case "Capping Smart Rule":
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PAGE_TITLE_ID), "EDIT CAPPING SMART RULE", "Edit Capping Smart Rule title is not matched");
				String ruleXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SMART_RULE_XPATH.getValue().replace("$Rule#", CommonPage.getTestData("RuleName"));
				if(!(WebDriverAction.getElementAttributeValue(By.xpath(ruleXpath), "disabled").equals("true")))
					throw new OrderingErrorOccured("Rule is not disabled");
				break;
			case "Capping Rule":
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PAGE_TITLE_ID), "EDIT CAPPING RULE", "Edit Capping Smart Rule title is not matched");
				if(!(BrowserAction.getElementAttributeValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_TEXT_ID, "disabled").equals("true")))
					throw new OrderingErrorOccured("Client search is not disabled");
				break;
			default: throw new OrderingErrorOccured("Invalid Rule Type");
		}
		CommonPage.selectDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_ORDER_TYPE_NAME, "Stock", "Order Type");
		CommonPage.selectDropDownValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PRODUCT_CLASS_NAME, "Equipment", "Product Class");
		BrowserAction.clickandClear(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CAPPING_NOTES_TEXT_ID);
		BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CAPPING_NOTES_TEXT_ID, "Updated Test Rule");
		ruleDetails.put("UpdatedRuleNote", CommonPage.getTestData("CustomColumn3"));
		ruleDetails.put("CopyOrderType", CommonPage.getTestData("CustomColumn4"));
		ruleDetails.put("CopyProductClass", CommonPage.getTestData("CustomColumn5"));
		storeRuleDetails();
		System.out.println("Updated Rule Note:-"+ruleDetails.get("UpdatedRuleNote"));
	}
	
	/**
	 * This method will click on Yes or No button for all pop up
	 * @param editOrCopyYesNo Yes or No
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickEditOrCopyYesNo(String editOrCopyYesNo) throws Exception {
		String editYesNoXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_OR_COPY_ARCHIVE_POP_UP_YES_NO_XPATH.getValue().replace("$EditOrCopyYesNo#", editOrCopyYesNo);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(editYesNoXpath));
		WebDriverAction.click(By.xpath(editYesNoXpath));
	}
	
	/**
	 * This method will click on archive pop cancel button
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickArchivePopUpCancelButton() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ARCHIVE_POP_UP_CANCEL_XPATH, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ARCHIVE_POP_UP_CANCEL_XPATH);
	}
	
	/**
	 * This method will verify pop up title and confirmation text as per passed popup name
	 * @param popName pop up name
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyPopUp(String popName) throws Exception {
		switch(popName) {
			case "Edit":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_COPY_ARCHIVE_POP_UP_TITLE_XPATH, CommonPage.getTestData("WaitTime"));
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_COPY_ARCHIVE_POP_UP_TITLE_XPATH), "Edit Rule", "Edit Rule pop up title not matched");
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_POP_UP_CONFIRMATION_TEXT_XPATH), "You are editing a rule. Are you sure you want to proceed?", "Edit Rule pop up confirmation text not matched");
				break;
			case "Copy":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_COPY_ARCHIVE_POP_UP_TITLE_XPATH, CommonPage.getTestData("WaitTime"));
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_COPY_ARCHIVE_POP_UP_TITLE_XPATH), "Copy Rules", "Copy Rules pop up title not matched");
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COPY_ARCHIVE_POP_UP_CONFIRMATION_TEXT1_XPATH), "You are copying rules to selected client(s).", "Copy Rules pop up confirmation text 1st not matched");
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COPY_ARCHIVE_POP_UP_CONFIRMATION_TEXT2_XPATH), "Are you sure you want to proceed?", "Copy Rules pop up confirmation text 2nd not matched");
				break;
			case "Archive":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_COPY_ARCHIVE_POP_UP_TITLE_XPATH, CommonPage.getTestData("WaitTime"));
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_COPY_ARCHIVE_POP_UP_TITLE_XPATH), "Archive Rules", "Archive Rules pop up title not matched");
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COPY_ARCHIVE_POP_UP_CONFIRMATION_TEXT1_XPATH), "The selected rules will be removed from future orders.", "Archive Rule pop up confirmation text 1st not matched");
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ARCHIVE_POP_UP_CONFIRMATION_TEXT2_XPATH), "Notes", "Archive Rules pop up confirmation text 2nd not matched");
				CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ARCHIVE_POP_UP_NOTE_TEXT_ID), "Archive note textarea is not present");
				break;
			case "Unarchive":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_COPY_ARCHIVE_POP_UP_TITLE_XPATH, CommonPage.getTestData("WaitTime"));
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_COPY_ARCHIVE_POP_UP_TITLE_XPATH), "Reactivate Rules", "UnArchive Rules pop up title not matched");
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COPY_ARCHIVE_POP_UP_CONFIRMATION_TEXT1_XPATH), "You are reactivating rules which will have todays date as Effective Date and no Effective Until date.", "UnArchive Rule pop up confirmation text 1st not matched");
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ARCHIVE_POP_UP_CONFIRMATION_TEXT2_XPATH), "To add an Effective Until Date, you will need to edit the rule after reactivating.", "UnArchive Rules pop up confirmation text 2nd not matched");
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COPY_ARCHIVE_POP_UP_CONFIRMATION_TEXT2_XPATH), "Do you want to proceed with reactivating the rules selected?", "UnArchive Rules pop up confirmation text 3rd not matched");
				break;
			case "Unable to copy":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_UNABLE_TO_COPY_RULE_TITLE_XPATH, CommonPage.getTestData("WaitTime"));
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_UNABLE_TO_COPY_RULE_TITLE_XPATH), "Unable to copy rule", "Unable to copy rule pop up title not matched");
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_UNABLE_TO_COPY_POP_UP_CONFIRMATION_XPATH), "Your selections are invalid. Please review your entries and copy the rule again.", "Unable to copy rule pop up confirmation text  not matched");
				break;
			default: throw new OrderingErrorOccured("Invoice Action. plz pass Edit, Copy, Archive and Unarchive");
		}
	}
	
	/**
	 * This method will verify all fields value from Edit rule page. 
	 * @param ruleType rule type
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDataOnEditRulePage(String ruleType) throws Exception {
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CORP_ID, CommonPage.getTestData("CorpCode"), "corp code");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CONTRACT_TYPE_NAME, CommonPage.getTestData("ContractType"), "Contract Type");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_ORDER_TYPE_NAME, CommonPage.getTestData("OrderType"), "Order Type");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PRODUCT_CLASS_NAME, CommonPage.getTestData("ProductClass"), "Product Class");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_YEAR_ID, CommonPage.getTestData("Year"), "Year");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MAKE_ID, CommonPage.getTestData("Make"), "Make");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MODEL_ID, CommonPage.getTestData("Model"), "Model");
		CommonPage.verifyDropDownSelectedValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_STATE_ID, CommonPage.getTestData("TitleState"), "State");
		List<WebElement> ele = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_SELECTED_XPATH);
		for(WebElement elem : ele)
			if(!(elem.getText().contains(CommonPage.getTestData("ClientNumber"))))
				throw new OrderingErrorOccured("Client number is not matched");
		System.out.println("Note Text :-"+BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CAPPING_NOTES_TEXT_ID).getText());
		System.out.println("Rule Note:-"+ruleDetails.get("RuleNote"));
		switch(ruleType) {
			case "Capping Smart Rule":
				String ruleXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SMART_RULE_XPATH.getValue().replace("$Rule#", CommonPage.getTestData("RuleName"));
				if(!(WebDriverAction.getElementAttributeValue(By.xpath(ruleXpath), "disabled").equals("true")))
					throw new OrderingErrorOccured("Rule is not disabled");
				break;
			case "Capping Rule":
				if(!(BrowserAction.getElementAttributeValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_TEXT_ID, "disabled").equals("true")))
					throw new OrderingErrorOccured("Rule name field is not disabled");
				break;
			default: throw new OrderingErrorOccured("Invalid Rule Type");
		}
	}
	
	/**
	 * This method will verify all fields available on Copy rule page
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyAllFieldsFromCopyRulePage() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COPY_PAGE_TITLE_XPATH, CommonPage.getTestData("WaitTime"));
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COPY_PAGE_TITLE_XPATH), "COPY RULE", "Copy Rule title is not matched");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CLIENT_SEARCH_ID), "client Search input is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_VEHICLE_BREAKDOWN_NAME), "vehicle breakdown is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_DRIVER_BREAKDOWN_NAME), "Driver breakdown is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COPY_IN_FLIGHT_ORDERS_ID), "Copy Inflight Orders not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SAVE_BUTTON_ID), "Save button is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CANCEL_BUTTON_ID), "Cancel button is not displayed");
		CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_NAVIGATION_ID), "Rule navigation not displayed");
	}
	
	/**
	 * This method will enter and select all value for fields on copy rule page
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void selectAllDetailsOnCopyRulePage(String clientName, String clientKey) throws Exception {
		OrderingBOBusinessMaintainedTablesPage.changeClient(clientName);
		ruleDetails.put(clientKey,clientName);
		if(CommonPage.getTestData("InFlightOrder").equals("Y"))
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COPY_IN_FLIGHT_ORDERS_ID);
	}
	
	/**
	 * This method will verify field value with expected value
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyfieldValueForAdd(String fieldtLocatorEnumWithGetValue, String expectedValue, String fieldName) throws Exception {
		String xpath = fieldtLocatorEnumWithGetValue.replace("$RuleID#", ruleDetails.get("RuleId"));
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(xpath));
		Assert.assertEquals(WebDriverAction.getElement(By.xpath(xpath)).getText().trim(),expectedValue,"Unable to match "+fieldName+" in table");
	}
	
	/**
	 * This method will verify field value with expected value
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyfieldValueForCopy(String fieldtLocatorEnumWithGetValue, String expectedValue, String fieldName) throws Exception {
		String xpath = fieldtLocatorEnumWithGetValue.replace("$RuleID#", ruleDetails.get("CopyRuleId"));
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(xpath));
		WebDriverVerify.verifyElementIsPresent(By.xpath(xpath));
		WebDriverVerify.verifyElementEnabled(By.xpath(xpath));
		WebDriverAction.hoverOverElement(By.xpath(xpath));
		Assert.assertEquals(WebDriverAction.getElement(By.xpath(xpath)).getText().trim(),expectedValue,"Unable to match "+fieldName+" in table");
	}
	
	/**
	 * This method will enter archive note on archive rule pop
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void enterArchiveNote() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ARCHIVE_POP_UP_NOTE_TEXT_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ARCHIVE_POP_UP_NOTE_TEXT_ID, "Test Archiving");
		System.out.println("Archiving Note"+"Test Archiving");
	}
	
	/**
	 * This method will click on row action options
	 * @param ruleType rule type
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyViewRulePageForArchive(String ruleType) throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CORP_ID, CommonPage.getTestData("WaitTime"));
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CORP_ID, "Corp Code");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CONTRACT_TYPE_NAME, "Contract Type");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_ORDER_TYPE_NAME, "Order Type");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PRODUCT_CLASS_NAME, "Product Class");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_YEAR_ID, "Year");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MAKE_ID, "Make");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_MODEL_ID, "Model");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_STATE_ID, "State");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_APPROVE_FOR_CLIENT_ID, "Approve for Client");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_APPLY_INFLIGHT_ORDERS_ID, "InFlight Orders");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CAPPING_NOTES_TEXT_ID, "Capping Note");
		verifyDisableElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CLIENT_SEARCH_ID, "Client Search");
		switch(ruleType) {
			case "Capping Smart Rule":
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PAGE_TITLE_ID), "VIEW ARCHIVED CAPPING SMART RULE", "View Archived apping Smart Rule title is not matched");
				String ruleXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SMART_RULE_XPATH.getValue().replace("$Rule#", CommonPage.getTestData("RuleName"));
				if(!(WebDriverAction.getElementAttributeValue(By.xpath(ruleXpath), "disabled").equals("true")))
					throw new OrderingErrorOccured("Rule is not disabled");
				break;
			case "Capping Rule":
				CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_PAGE_TITLE_ID), "VIEW ARCHIVED CAPPING RULE", "View Archived Capping Smart Rule title is not matched");
				if(!(BrowserAction.getElementAttributeValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_TEXT_ID, "disabled").equals("true")))
					throw new OrderingErrorOccured("Client search is not disabled");
				break;
			default: throw new OrderingErrorOccured("Invalid Rule Type");
		}
	}
	
	/**
	 * This method will click on edit Yes or NO or Archive button
	 * @param activeOrArchive rule section active or Archive
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnRuleTab(String activeOrArchive) throws Exception {
		String editYesNoXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ARCHIVE_OR_ACTIVE_RULE_TAB_XPATH.getValue().replace("$RuleTab#", activeOrArchive);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(editYesNoXpath));
		WebDriverAction.click(By.xpath(editYesNoXpath));
	}
	
	/**
	 * This method will verify bulk copy 
	 * @param ruleType rule type
	 * @param enableDisable action
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyVisibilityOfBulkCopy(String ruleType,String enableDisable) throws Exception {
		switch(ruleType) {
			case "Capping Smart Rule":
				if(enableDisable.equals("Enable")) {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_COPY_ENABLED_XPATH);
					BrowserAction.hoverOverElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_COPY_ENABLED_XPATH);
				}
				else {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_COPY_DISABLED_XPATH);
					BrowserAction.hoverOverElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_COPY_DISABLED_XPATH);
				}
				break;
			case "Capping Rule":
				if(enableDisable.equals("Enable")) {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_COPY_ENABLED_XPATH);
					BrowserAction.hoverOverElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_COPY_ENABLED_XPATH);
				}
				else {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_COPY_DISABLED_XPATH);
					BrowserAction.hoverOverElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_COPY_DISABLED_XPATH);
				}
				break;
			default: throw new OrderingErrorOccured("Invalid Order Type");
		}
	}
	
	/**
	 * This method will click/select check box for rule id for bulk operation for Active tab
	 * @param ruleType rule type
	 * @param ruleId rule id
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectRuleCheckBoxActiveTab(String ruleType, String ruleId) throws Exception {
		String checkBoxXpath = null;
		switch(ruleType) {
			case "Capping Smart Rule":
				checkBoxXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_SMART_BULK_SELECTION_CHECKBOX_XPATH.getValue().replace("$RuleId#", ruleId);
				break;
			case "Capping Rule":
				checkBoxXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_SELECTION_CHECKBOX_XPATH.getValue().replace("$RuleId#", ruleId);
				break;
			default: throw new OrderingErrorOccured("Invalid Order Type");
		}
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(checkBoxXpath));
		WebDriverAction.hoverOverElement(By.xpath(checkBoxXpath));
		WebDriverAction.click(By.xpath(checkBoxXpath));
	}
	
	/**
	 * This method will click/select check box for rule id for bulk operation for Archive tab
	 * @param ruleType rule type
	 * @param ruleId rule id
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectRuleCheckBoxArchiveTab(String ruleType, String ruleId) throws Exception {
		String checkBoxXpath = null;
		switch(ruleType) {
			case "Capping Smart Rule":
				checkBoxXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_SMART_BULK_SELECTION_ARCHIVE_XPATH.getValue().replace("$RuleId#", ruleId);
				break;
			case "Capping Rule":
				checkBoxXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_SELECTION_ARCHIVE_XPATH.getValue().replace("$RuleId#", ruleId);
				break;
			default: throw new OrderingErrorOccured("Invalid Order Type");
		}
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(checkBoxXpath));
		WebDriverAction.hoverOverElement(By.xpath(checkBoxXpath));
		WebDriverAction.click(By.xpath(checkBoxXpath));
	}

/**
	 * This method will click/select check box for rule id for bulk operation
	 * @param ruleId rule id
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectRuleCheckBox(String ruleId) throws Exception {
		String checkBoxXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_SELECTION_COPY_XPATH.getValue().replace("$RuleId#", ruleId);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(checkBoxXpath));
		WebDriverAction.hoverOverElement(By.xpath(checkBoxXpath));
		WebDriverAction.click(By.xpath(checkBoxXpath));
	}
	
	/**
	 * This method will click bulk copy link
	 * @param ruleType rule type
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnBulkCopy(String ruleType) throws Exception {
		switch(ruleType) {
			case "Capping Smart Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_COPY_ENABLED_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_COPY_ENABLED_XPATH);
				break;
			case "Capping Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_COPY_ENABLED_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_COPY_ENABLED_XPATH);
				break;
			default: throw new OrderingErrorOccured("Invalid Order Type");
		}
	}
	
	/**
	 * This method will click bulk Archive link
	 * @param ruleType rule Type
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnBulkArchive(String ruleType) throws Exception {
		switch(ruleType) {
			case "Capping Smart Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_ARCHIVE_ENABLED_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_ARCHIVE_ENABLED_XPATH);
				break;
			case "Capping Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_ARCHIVE_ENABLED_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_ARCHIVE_ENABLED_XPATH);
				break;
			default: throw new OrderingErrorOccured("Invalid Order Type");
		}
	}
	
	/**
	 * This method will click bulk Unarchive link
	 * @param ruleType rule Type
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnBulkUnarchive(String ruleType) throws Exception {
		switch(ruleType) {
			case "Capping Smart Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_UNARCHIVE_ENABLED_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_UNARCHIVE_ENABLED_XPATH);
				break;
			case "Capping Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_UNARCHIVE_ENABLED_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_UNARCHIVE_ENABLED_XPATH);
				break;
			default: throw new OrderingErrorOccured("Invalid Order Type");
		}
	}
	
	/**
	 * This method will verify rule id in copy rule page
	 * @param ruleId array of rule id
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifySelectedRulesId(String... ruleId) throws Exception {
		List<WebElement> list = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BULK_COPY_RULE_ID_LIST_XPATH);
		ArrayList<String> rules = new ArrayList<String>();
		for(WebElement ele : list) {
			rules.add(ele.getText().trim());
		}
		for(String listRuleId : ruleId) {
			if(!(rules.contains(listRuleId)))
				throw new OrderingErrorOccured("Selected rulesare not in table");
		}
	}
	
	/**
	 * This method will click/select check box for rule id for bulk operation on copy rule page
	 * @param ruleId rule id
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectRuleCheckBoxCopyPage(String ruleId) throws Exception {
		String checkBoxXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COPY_RULE_BULK_SELECTION_CHECKBOX_XPATH.getValue().replace("$RuleId#", ruleId);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(checkBoxXpath));
		WebDriverAction.click(By.xpath(checkBoxXpath));
	}
	
	/**
	 * This method will store rule id in map
	 * @param rule rule name
	 * @param count count of row
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void getBulkRuleIdAndVerifyDataInTable(String rule, int count) throws Exception {
		for(int i = 1 ; i<= count; i++) {
			String ruleIdXpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_RULE_IDS_BULK_ACTION_XPATH.getValue();
			ruleIdXpath = ruleIdXpath.replace("$Rule#", rule);
			ruleIdXpath = ruleIdXpath.replace("$count#", i+"");
			WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(ruleIdXpath));
			WebDriverVerify.verifyElementIsPresent(By.xpath(ruleIdXpath));
			WebDriverVerify.verifyElementEnabled(By.xpath(ruleIdXpath));
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			ruleDetails.put("BulkRuleId"+i, WebDriverAction.getElement(By.xpath(ruleIdXpath)).getText().trim());
			System.out.println("Bulk rule id:-"+ruleDetails.get("BulkRuleId"+i));
			verifyBulkCopiedDataInTable(ruleDetails.get("BulkRuleId"+i));
		}
	}
	
	/**
	 * This method will click/select check box for rule id for bulk operation on copy rule page
	 * @param ruleId rule id
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyBulkCopiedDataInTable(String ruleId) throws Exception {
		verifyfieldValueForBulkCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_RULE_COLUMN_DATA_XPATH.getValue(),CommonPage.getTestData("RuleName"), ruleId, "Rule Name");
		verifyfieldValueForBulkCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CORP_CODE_COLUMN_DATA_XPATH.getValue(),CommonPage.getTestData("CorpCode"), ruleId,"Corp Code");
		verifyfieldValueForBulkCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_COLUMN_BULK_ACTION_XPATH.getValue(),CommonPage.getTestData("CustomColumn2"), ruleId,"Client Number");
		verifyfieldValueForBulkCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CLIENT_VIEW_XPATH.getValue(),CommonPage.getTestData("ApproveForClientView"), ruleId,"Client View");
		verifyfieldValueForBulkCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_IN_FLIGHT_ORDER_COLUMN_XPATH.getValue(),CommonPage.getTestData("InFlightOrder"), ruleId,"Inflight Order");
		verifyfieldValueForBulkCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CONTRACT_TYPE_COLUMN_XPATH.getValue(),ruleDetails.get("ContractType"), ruleId,"Contract Type");
		verifyfieldValueForBulkCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ORDER_TYPE_XPATH.getValue(),ruleDetails.get("OrderType"), ruleId,"Order Type");
		verifyfieldValueForBulkCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_PRODUCT_CLASS_COLUMN_XPATH.getValue(),ruleDetails.get("ProductClass"), ruleId,"Product Class");
		verifyfieldValueForBulkCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_YEAR_CULUMN_XPATH.getValue(),CommonPage.getTestData("Year"), ruleId,"Year");
		verifyfieldValueForBulkCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MAKE_COLUMN_XPATH.getValue(),CommonPage.getTestData("Make"), ruleId,"Make");
		verifyfieldValueForBulkCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_MODEL_COLUMN_XPATH.getValue(),CommonPage.getTestData("Model"), ruleId,"Model");
		verifyfieldValueForBulkCopy(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_EFFECTIVE_DATE_COLUMN_XPATH.getValue(),ruleDetails.get("StartDateMMDDYY"),ruleId, "Start Date");
	}
	
	/**
	 * This method will verify field value with expected value
	 * @param fieldtLocatorEnumWithGetValue enum value
	 * @param expectedValue expected value of element
	 * @param fieldName field name
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyfieldValueForBulkCopy(String fieldtLocatorEnumWithGetValue, String expectedValue, String ruleID, String fieldName) throws Exception {
		String xpath = fieldtLocatorEnumWithGetValue.replace("$RuleID#", ruleID);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(xpath));
		Assert.assertEquals(WebDriverAction.getElement(By.xpath(xpath)).getText().trim(),expectedValue,"Unable to match "+fieldName+" in table");
	}
	
	/**
	 * This method will verify bulk copy 
	 * @param ruleType rule type
	 * @param enableDisable action
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyVisibilityOfBulkUnArchive(String ruleType,String enableDisable) throws Exception {
		switch(ruleType) {
			case "Capping Smart Rule":
				if(enableDisable.equals("Enable")) {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_UNARCHIVE_ENABLED_XPATH);
					BrowserAction.hoverOverElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_UNARCHIVE_ENABLED_XPATH);
				}
				else {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_UNARCHIVE_DISABLED_XPATH);
					BrowserAction.hoverOverElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_BULK_UNARCHIVE_DISABLED_XPATH);
				}
				break;
			case "Capping Rule":
				if(enableDisable.equals("Enable")) {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_UNARCHIVE_ENABLED_XPATH);
					BrowserAction.hoverOverElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_UNARCHIVE_ENABLED_XPATH);
				}
				else {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_UNARCHIVE_DISABLED_XPATH);
					BrowserAction.hoverOverElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_BULK_UNARCHIVE_DISABLED_XPATH);
				}
				break;
			default: throw new OrderingErrorOccured("Invalid Order Type");
		}
	}
	
	/**
	 * This method will search rule by name on Archived tab
	 * @param ruleType rule type
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void searchByRuleNameOnArchived(String ruleType) throws Exception {
		switch(ruleType) {
			case "Capping Smart Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_RULE_NAME_ARCHIVED_XPATH, CommonPage.getTestData("WaitTime"));
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_RULE_NAME_ARCHIVED_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.clickandClear(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_RULE_NAME_ARCHIVED_XPATH);
				BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_RULE_NAME_ARCHIVED_XPATH, CommonPage.getTestData("RuleName"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_SEARCH_ARCHIVED_XPATH);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_TOGGLE_FILTER_ARCHIVED_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_TOGGLE_FILTER_ARCHIVED_XPATH);
				OrderingCommonPage.checkAlertPopUp();
				clickOnRuleIdColumn(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_CAPPING_ARCHIVE_SMART_RULE_ID_COLUMN_XPATH.getValue());
				break;
			case "Capping Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_RULE_NAME_ARCHIVED_XPATH, CommonPage.getTestData("WaitTime"));
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_RULE_NAME_ARCHIVED_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.clickandClear(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_RULE_NAME_ARCHIVED_XPATH);
				BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_RULE_NAME_ARCHIVED_XPATH, CommonPage.getTestData("RuleName"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_SEARCH_ARCHIVED_XPATH);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_TOGGLE_FILTER_ARCHIVED_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_TOGGLE_FILTER_ARCHIVED_XPATH);
				OrderingCommonPage.checkAlertPopUp();
				clickOnRuleIdColumn(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_CAPPING_ARCHIVE_RULE_ID_COLUMN_XPATH.getValue());
				break;
			default: throw new OrderingErrorOccured("Invalid Order Type");
		}
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method will search rule by name om Active tab 
	 * @param ruleType rule type
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void searchByRuleName(String ruleType) throws Exception {
		switch(ruleType) {
			case "Capping Smart Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_RULE_NAME_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_RULE_NAME_XPATH, CommonPage.getTestData("RuleName"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_SEARCH_XPATH);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_TOGGLE_FILTER_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_TOGGLE_FILTER_XPATH);
				OrderingCommonPage.checkAlertPopUp();
				clickOnRuleIdColumn(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_CAPPING_SMART_RULE_ID_COLUMN_XPATH.getValue());
				break;
			case "Capping Rule":
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_RULE_NAME_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_RULE_NAME_XPATH, CommonPage.getTestData("RuleName"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_SEARCH_XPATH);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_TOGGLE_FILTER_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_TOGGLE_FILTER_XPATH);
				OrderingCommonPage.checkAlertPopUp();
				clickOnRuleIdColumn(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_CAPPING_RULE_ID_COLUMN_XPATH.getValue());
				break;
			default: throw new OrderingErrorOccured("Invalid Order Type");
		}
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method will click on Add new button and fill all details  
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnAddButtonAndFillAllDetails() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewButton(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.clickOnCancelButton();
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewButton(CommonPage.getTestData("RuleType"));
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOBusinessMaintainedTablesPage.verifyAllFieldsFromAddNewRule(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.selectSmartRuleOrEnterCappingRule(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.selectAllDetails();
	}
	
	/**
	 * This method will go to View page and verify the data  
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goToViewPageAndVerifyData() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.searchByRuleName(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.getRuleId(CommonPage.getTestData("RuleName"), "Add");
		OrderingBOBusinessMaintainedTablesPage.verifyRuleValueFromTable("Add");
		OrderingBOBusinessMaintainedTablesPage.clickOnRowAction(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("RuleId"));
		OrderingBOBusinessMaintainedTablesPage.clickOnRowActionOption(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("RuleId"), "View");
		OrderingBOBusinessMaintainedTablesPage.verifyViewRulePage(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.verifyDataOnViewRulePage(CommonPage.getTestData("RuleType"));
	}
	
	/**
	 * This method will go to edit page and update some data 
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goToEditPageAndUpdateRuleData() throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.searchByRuleName(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.clickOnRowAction(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("RuleId"));
		OrderingBOBusinessMaintainedTablesPage.clickOnRowActionOption(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("RuleId"), "Edit");
		OrderingBOBusinessMaintainedTablesPage.verifyDataOnEditRulePage(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.verifyEditPageAndUpdateSomeDetails(CommonPage.getTestData("RuleType"));
	}
	
	/**
	 * This method will go to capping rule page and verify data from table 
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goToCappingRulePageAndVerifyData() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("No");
		OrderingBOBusinessMaintainedTablesPage.clickOnSaveButton();
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("Yes");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.searchByRuleName(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.verifyRuleValueFromTable("Add");
	}
	
	/**
	 * This method will verify checkbox is not displayed for single rule copy 
	 * @param ruleId rule id
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyCheckBoxIsNotDisplayedForSingleRuleCopy(String ruleId) throws Exception {
		String xpath = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COPY_RULE_BULK_SELECTION_CHECKBOX_XPATH.getValue().replace("$RuleId#", ruleId);
		try {
			if(WebDriverAction.getElement(By.xpath(xpath)).isDisplayed())
				throw new OrderingErrorOccured("Checkbox is displayed for single copy on copy rule page");
			else
				System.out.println("Checkbox is not displayed for single copy on copy rule page as exepcted");
		}catch(Exception e) {
			System.out.println("Checkbox is not displayed for single copy on copy rule page as exepcted");
		}
	}
	
	/**
	 * This method will verify vehicle breakdown is not displayed for multiple breakdown selection 
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyVehicleBreakDownIsNotDisplayedForMuitpleClientSelection() throws Exception {
		try {
			if(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_VEHICLE_BREAKDOWN_NAME).isDisplayed())
				throw new OrderingErrorOccured("Vehicle break down is displayed when we select mutliple client");
			else
				System.out.println("Vehicle break down is not displayed when we select mutliple client as exepcted");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Vehicle break down is not displayed when we select mutliple client as exepcted");
		}
	}
	
	/**
	 * This method will verify driver breakdown is not displayed for multiple breakdown selection 
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDriverBreakDownIsNotDisplayedForMuitpleClientSelection() throws Exception {
		try {
			if(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_DRIVER_BREAKDOWN_NAME).isDisplayed())
				throw new OrderingErrorOccured("Driver break down is displayed when we select mutliple client");
			else
				System.out.println("Driver break down is not displayed when we select mutliple client as exepcted");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Driver break down is not displayed when we select mutliple client as exepcted");
		}
	}
	
	/**
	 * This method will go to Copy rule page and add details 
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goToCopyRulePageAndAddDetails() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxActiveTab(CommonPage.getTestData("RuleType"),OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("RuleId"));
		OrderingBOBusinessMaintainedTablesPage.clickOnRowAction(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("RuleId"));
		OrderingBOBusinessMaintainedTablesPage.clickOnRowActionOption(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("RuleId"), "Copy");
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOBusinessMaintainedTablesPage.verifyAllFieldsFromCopyRulePage();
		OrderingBOBusinessMaintainedTablesPage.verifyRuleValueFromTable("Add");
		verifyCheckBoxIsNotDisplayedForSingleRuleCopy(ruleDetails.get("RuleId"));
		OrderingBOBusinessMaintainedTablesPage.selectAllDetailsOnCopyRulePage(CommonPage.getTestData("CustomColumn1"), "CopyClientNumber");
	}
	
	/**
	 * This method will go to Capping rule page and verify copied data 
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goToCappingRulePageAndVerifyCopiedData() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("No");
		OrderingBOBusinessMaintainedTablesPage.clickOnSaveButton();
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("Yes");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.searchByRuleName(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.getRuleId(CommonPage.getTestData("RuleName"), "Copy");
		OrderingBOBusinessMaintainedTablesPage.verifyRuleValueFromTable("Copy");
	}
	
	/**
	 * This method will click on row action and verify archive pop up 
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnRowActionAndVeriyArchivePopUp() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxActiveTab(CommonPage.getTestData("RuleType"),OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"));
		OrderingBOBusinessMaintainedTablesPage.clickOnRowAction(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"));
		OrderingBOBusinessMaintainedTablesPage.clickOnRowActionOption(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"), "Archive");
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOBusinessMaintainedTablesPage.verifyPopUp("Archive");
		OrderingBOBusinessMaintainedTablesPage.clickArchivePopUpCancelButton();
		OrderingBOBusinessMaintainedTablesPage.clickOnRowAction(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"));
		OrderingBOBusinessMaintainedTablesPage.clickOnRowActionOption(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"), "Archive");
		OrderingBOBusinessMaintainedTablesPage.enterArchiveNote();
	}
	
	/**
	 * This method will go to archived rule tab and verify data in table
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goToArchivedRulePageAndVerifyDataInTable() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("Archive");
		BrowserAction.refresh();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.clickOnRuleTab("Archived Rules");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.searchByRuleNameOnArchived(CommonPage.getTestData("RuleType"));
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.verifyArchivedRuleInTable();
	}
	
	/**
	 * This method will click on row action and go to view page for archived rule
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnRowActionAndGoToArchivedRuleViewPage() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxArchiveTab(CommonPage.getTestData("RuleType"),OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"));
		OrderingBOBusinessMaintainedTablesPage.clickOnRowAction(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"));
		OrderingBOBusinessMaintainedTablesPage.clickOnRowActionOption(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"), "View");
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOBusinessMaintainedTablesPage.verifyViewRulePageForArchive(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.verifyDataOnViewRulePageForUnArchive(CommonPage.getTestData("RuleType"));
	}

	/**
	 * This method will verify unarchived pop up
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyUnarchivePopUp() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.searchByRuleNameOnArchived(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxArchiveTab(CommonPage.getTestData("RuleType"),OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"));
		OrderingBOBusinessMaintainedTablesPage.clickOnRowAction(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"));
		OrderingBOBusinessMaintainedTablesPage.clickOnRowActionOption(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"), "Unarchive");
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOBusinessMaintainedTablesPage.verifyPopUp("Unarchive");
	}
	
	/**
	 * This method will verify Capping Rules title
	 * @throws Exception
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyCappingRulesTitleAndSubTitle() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_TITLE_XPATH, CommonPage.getTestData("WaitTime"));
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_TITLE_XPATH), "Capping Rules", "Capping Rules title is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_SMART_RULE_SUBTITLE_XPATH), "Capping Smart Rules", "Capping Smart Rules sub title is not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_SUBTITLE_XPATH), "Capping Rules", "Capping Rules sub title is not matched");
	}
	
	/**
	 * This method will verify unarchived data in Active rule tab
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyUnarchiveDataInActiveRuleTab() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("No");
		OrderingBOBusinessMaintainedTablesPage.clickOnRowAction(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"));
		OrderingBOBusinessMaintainedTablesPage.clickOnRowActionOption(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"), "Unarchive");
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("Yes");
		BrowserAction.refresh();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.searchByRuleName(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.verifyUnarchivedRuleValueFromTable();
	}
	
	/**
	 * This method will select multiple rules from capping rule page
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goToCappingRulePageAndSelectMultipleRules() throws Exception {
		OrderingBOQueuePage.goToBusinessMaintainedTable();
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Capping Rules");
		OrderingBOBusinessMaintainedTablesPage.verifyCappingRulesTitleAndSubTitle();
		OrderingBOBusinessMaintainedTablesPage.searchByRuleName(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.verifyVisibilityOfBulkCopy(CommonPage.getTestData("RuleType"),"Disable");
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxActiveTab(CommonPage.getTestData("RuleType"),OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"));
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxActiveTab(CommonPage.getTestData("RuleType"),OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("RuleId"));
	}
	
	/**
	 * This method will click on bulk copy button and verify all fields from copy rule page
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnBulkCopyAndVerifyAllFieldsFromCopyRulePage() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnBulkCopy(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.verifyAllFieldsFromCopyRulePage();
	}
	
	/**
	 * This method will fill all details from bulk copy rule page
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void fillAllDetailsBulkCopyRulePage() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.verifyUnarchivedRuleValueFromTable();
		OrderingBOBusinessMaintainedTablesPage.verifySelectedRulesId(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"), OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("RuleId"));
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxCopyPage(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"));
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxCopyPage(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("RuleId"));
		OrderingBOBusinessMaintainedTablesPage.selectAllDetailsOnCopyRulePage(CommonPage.getTestData("CustomColumn2"), "BulkCopyClientNumber");
	}
	
	/**
	 * This method will select copied multiple rule checkbox
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goToCappingRulePageAndSelectCopiedMultipleRules() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("No");
		OrderingBOBusinessMaintainedTablesPage.clickOnSaveButton();
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("Yes");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserAction.refresh();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.searchByRuleName(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.getBulkRuleIdAndVerifyDataInTable(CommonPage.getTestData("RuleName"), 2);
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxActiveTab(CommonPage.getTestData("RuleType"),OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("BulkRuleId1"));
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxActiveTab(CommonPage.getTestData("RuleType"),OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("BulkRuleId2"));
	}

	/**
	 * This method will verify archived data in archived tab
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goToArchivedTabAndVerifyArchivedDataInTable() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickArchivePopUpCancelButton();
		OrderingBOBusinessMaintainedTablesPage.clickOnBulkArchive(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.enterArchiveNote();
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("Archive");
		OrderingCommonPage.checkAlertPopUp();
		BrowserAction.refresh();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.clickOnRuleTab("Archived Rules");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.searchByRuleNameOnArchived(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.verifyVisibilityOfBulkUnArchive(CommonPage.getTestData("RuleType"),"Disable");
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxArchiveTab(CommonPage.getTestData("RuleType"),OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("BulkRuleId1"));
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxArchiveTab(CommonPage.getTestData("RuleType"),OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("BulkRuleId2"));
		OrderingBOBusinessMaintainedTablesPage.verifyVisibilityOfBulkUnArchive(CommonPage.getTestData("RuleType"),"Enable");
	}

	/**
	 * This method will verify unarchived data in Active tab
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goToActiveTabAndVerifyUnarchivedDataInTable() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("No");
		OrderingBOBusinessMaintainedTablesPage.clickOnBulkUnarchive(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("Yes");
		OrderingCommonPage.checkAlertPopUp();
		BrowserAction.refresh();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.searchByRuleName(CommonPage.getTestData("RuleType"));
		OrderingBOBusinessMaintainedTablesPage.verifyBulkCopiedDataInTable(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("BulkRuleId1"));
		OrderingBOBusinessMaintainedTablesPage.verifyBulkCopiedDataInTable(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("BulkRuleId2"));
	}
	
	/**
	 * This method will verify Unable to copy pop up when we are try to copy multiple rule with multiple client
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyWarningPopWhenCopyMultipleRuleWithMultipleClient() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxActiveTab(CommonPage.getTestData("RuleType"),OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"));
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxActiveTab(CommonPage.getTestData("RuleType"),OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("RuleId"));
		OrderingBOBusinessMaintainedTablesPage.clickOnBulkCopy(CommonPage.getTestData("RuleType"));
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.verifySelectedRulesId(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"), OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("RuleId"));
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxCopyPage(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("CopyRuleId"));
		OrderingBOBusinessMaintainedTablesPage.selectRuleCheckBoxCopyPage(OrderingBOBusinessMaintainedTablesPage.ruleDetails.get("RuleId"));
		OrderingBOBusinessMaintainedTablesPage.selectAllDetailsOnCopyRulePage(CommonPage.getTestData("CustomColumn2"), "BulkCopyClientNumber1");
		OrderingBOBusinessMaintainedTablesPage.selectAllDetailsOnCopyRulePage(CommonPage.getTestData("CustomColumn1"), "BulkCopyClientNumber2");
		OrderingBOBusinessMaintainedTablesPage.clickOnSaveButton();
		OrderingBOBusinessMaintainedTablesPage.clickEditOrCopyYesNo("Yes");
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOBusinessMaintainedTablesPage.verifyPopUp("Unable to copy");
	}
			
	/**
	 * This method performs search as per the passed parameters
	 * @param ruleType    rule type
	 * @param fieldName   filed name
	 * @param searchValue search value
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchWithParameters(String ruleType, String fieldName, String searchValue) throws Exception {
		String cappingType = null;
		switch (ruleType) {
			case "Capping Smart Rule":
				cappingType = "capping-smart-rules-summary";
				break;
			case "Capping Rule":
				cappingType = "capping-rules-summary";
				WebElement scrollArea = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SROLL_XPATH);
				((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);",scrollArea);
				break;
			case "Archive Capping Smart Rule":
				cappingType = "archive-capping-smart-rules-summary";
				break;
			case "Archive Capping Rule":
				cappingType = "archive-capping-rules-summary";
				WebElement scrollAreaArchived = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SROLL_XPATH);
				((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);",scrollAreaArchived);
				break;
			default: throw new InvalidSwitchCaseException(ruleType + " is invalid rule type");
		}
		if (fieldName.equals("Visible to Client") || fieldName.equals("Applied to In-Flight Orders")|| fieldName.equals("Corp Level Rules")) {
			WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SEARCH_SELECT_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SEARCH_SELECT_XPATH.toString(),cappingType, fieldName)));
			WebElement e = WebDriverAction.getElement(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SEARCH_SELECT_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SEARCH_SELECT_XPATH.toString(),cappingType, fieldName)));
			BrowserWait.waitUntilElementIsDisplayed(e);
			Select select = new Select(e);
			select.selectByVisibleText(searchValue);
		} else {
			WebDriverAction.enterFieldValue(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SEARCH_FIELDS_INPUT_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SEARCH_FIELDS_INPUT_XPATH.toString(),cappingType, fieldName)),searchValue);
		}
		System.out.println(cappingType);
		WebDriverAction.getElement(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SEARCH_BTN_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SEARCH_BTN_XPATH.toString(), cappingType))).click();
		OrderingBOQueuePage.waitUntilCompletePageLoad();
	}
	
	/**
	 * This method verifies the search results in the table 
	 * @param ruleType rule type
	 * @param fieldName search field name
	 * @param searchValue search value 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifySearchResults(String ruleType, String fieldName, String searchValue ) throws Exception {
		OrderingBOQueuePage.waitUntilCompletePageLoad();
		String cappingType=null;
		String dataName=null;
		switch(ruleType){
			case "Capping Smart Rule":
				cappingType="capping-smart-rules-summary";
				break;
			case "Capping Rule":
				cappingType="capping-rules-summary";
				WebElement scrollArea = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SROLL_XPATH); 
				((JavascriptExecutor)WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scrollArea);
				break;
			case "Archive Capping Smart Rule":
				cappingType="archive-capping-smart-rules-summary";
				break;
			case "Archive Capping Rule":
				cappingType="archive-capping-rules-summary";
				WebElement scrollAreaArchived = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SROLL_XPATH); 
				((JavascriptExecutor)WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scrollAreaArchived);
				break;
			default: throw new InvalidSwitchCaseException(ruleType + " is invalid rule type");
		}
		switch(fieldName){
			case "Rule Name":
				dataName="rule";
				break;
			case "Rule ID":
				dataName="ruleGroupID";
				break;
			case "Corp Code":
				dataName="corpCode";
				break;
			case "Client":
				dataName="clientNumber";
				break;		
			case "Breakdown":
				dataName="breakDown";
				break;
			case "Effective Date":
				dataName="effDate";
				break;
			case "Effective Until":
				dataName="endDate";
				break;
			case "Contract Type":
				dataName="contractType";
				break;
			case "Order Type":
				dataName="orderType";
				break;
			case "Product Class":
				dataName="productClass";
				break;
			case "Year":
				dataName="year";
				break;
			case "Make":
				dataName="make";
				break;
			case "State/Province":
				dataName="stateProvience";
				break;
			case "Visible to Client":
				dataName="clientView";
				break;
			case "Applied to In-Flight Orders":
				dataName="inFlightOrders";
				break;
			default: throw new InvalidSwitchCaseException(fieldName + " is invalid field type");
		}
		List<WebElement> elementList=WebDriverAction.getElements(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_COLUMN_LIST_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_COLUMN_LIST_XPATH.toString(), cappingType, dataName)));
		for(WebElement e:elementList) {
			Assert.assertTrue(e.getAttribute("data-val").equalsIgnoreCase(searchValue)||e.getText().trim().equalsIgnoreCase("All"), "The table data is not macthing search value");
		}
	}
	
	/**
	 * This method verifies the clear filter functionality 
	 * @param ruleType rule type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyClearFilterFunctionality(String ruleType) throws Exception {
		String cappingType=null;
		switch(ruleType){
			case "Capping Smart Rule":
				cappingType="capping-smart-rules-summary";
				break;
			case "Capping Rule":
				cappingType="capping-rules-summary";
				WebElement scrollArea = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SROLL_XPATH); 
				((JavascriptExecutor)WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scrollArea);
				break;
			case "Archive Capping Smart Rule":
				cappingType="archive-capping-smart-rules-summary";
				break;
			case "Archive Capping Rule":
				cappingType="archive-capping-rules-summary";
				WebElement scrollAreaArchived = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SROLL_XPATH); 
				((JavascriptExecutor)WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scrollAreaArchived);
				break;
			default: throw new InvalidSwitchCaseException(ruleType + " is invalid rule type");
		}
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CLEAR_FILTER_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CLEAR_FILTER_XPATH.toString(), cappingType)));
		WebDriverAction.getElement(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CLEAR_FILTER_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CLEAR_FILTER_XPATH.toString(), cappingType))).click();
        OrderingBOQueuePage.waitUntilCompletePageLoad();
		String value;
		List<WebElement> elementslist = WebDriverAccess.getElements(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_INPUT_FIELDS_LIST_XPATH.name(), String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_INPUT_FIELDS_LIST_XPATH.toString(), cappingType)));
		for (WebElement element : elementslist) {	
			value = element.getAttribute("value");
			Assert.assertTrue(value.equals(""), "Search Field " + element.getText()+ "is not cleared ");
		}
		List<WebElement> selectlist = WebDriverAccess.getElements(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SELECT_LIST_XPATH.name(), String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SELECT_LIST_XPATH.toString(), cappingType)));
		for (WebElement element : selectlist) {	
			Select select=new Select(element);
			Assert.assertTrue(	select.getFirstSelectedOption().getText().equals("Select"), "Search Field " + element.getText()+ "is not cleared ");
		}
	} 
	
	/**
	 * This method creates the view 
	 * @param ruleType rule type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void createView(String ruleType) throws Exception {
		String cappingType=null;
		switch(ruleType){
			case "Capping Smart Rule":
				cappingType="capping-smart-rules-summary";
				break;
			case "Capping Rule":
				cappingType="capping-rules-summary";
				WebElement scrollArea = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SROLL_XPATH); 
				((JavascriptExecutor)WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scrollArea);
				break;
			case "Archive Capping Smart Rule":
				cappingType="archive-capping-smart-rules-summary";
				break;
			case "Archive Capping Rule":
				cappingType="archive-capping-rules-summary";
				WebElement scrollAreaArchived = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SROLL_XPATH); 
				((JavascriptExecutor)WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scrollAreaArchived);
				break;
			default: throw new InvalidSwitchCaseException(ruleType + " is invalid rule type");
		}
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DEFAULT_VIEW_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DEFAULT_VIEW_XPATH.toString(), cappingType)));
		WebDriverAction.getElement(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DEFAULT_VIEW_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DEFAULT_VIEW_XPATH.toString(), cappingType))).click();		
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MANAGE_VIEW_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MANAGE_VIEW_XPATH.toString(), cappingType)));
		WebDriverAction.getElement(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MANAGE_VIEW_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MANAGE_VIEW_XPATH.toString(), cappingType))).click();		
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CREATE_VIEW_RADIO_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CREATE_VIEW_RADIO_BTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CREATE_VIEW_INPUT_XPATH);
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CREATE_VIEW_INPUT_XPATH),CommonPage.getTestData("CustomColumn2"));
		OrderingBOBusinessMaintainedTablesPage.validateMandatoryColumns();
		List<String> selectedCoulumns=returnSelectedColumnsList();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CREATE_VIEW_SAVE_DEFAULT_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CREATE_VIEW_SAVE_DEFAULT_BTN_XPATH);
		OrderingBOQueuePage.waitUntilCompletePageLoad();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.verifyCreatedViewIsSetAsDefault(ruleType);
		List<String> TableCoulumns=getTableColumns(ruleType);
		Assert.assertTrue(selectedCoulumns.equals(TableCoulumns), "Table columns are not as per the view created");
	}
	
	/**
	 * This method return a list of column names present on the table
	 * @param ruleType rule type
	 * @return
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static List<String> getTableColumns(String ruleType) throws Exception{
		String cappingType=null;
		switch(ruleType){
			case "Capping Smart Rule":
				cappingType="capping-smart-rules-summary";
				break;
			case "Capping Rule":
				cappingType="capping-rules-summary";
				break;
			case "Archive Capping Smart Rule":
				cappingType="archive-capping-smart-rules-summary";
				break;
			case "Archive Capping Rule":
				cappingType="archive-capping-rules-summary";
				break;
			default: throw new InvalidSwitchCaseException(ruleType + " is invalid rule type");
		}
		List<String> tableColumns= new ArrayList<String>();
		List<WebElement> tableColumnslist = WebDriverAccess.getElements(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_HEADER_LIST_XPATH.name(), String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_HEADER_LIST_XPATH.toString(), cappingType)));
		for(WebElement e:tableColumnslist) {
			tableColumns.add(e.getAttribute("innerText").replaceAll("[^a-zA-Z0-9]+", "").trim());
		}
		return tableColumns;
	}
	
	/**
	 * This method verifies that the created view is set as default 
	 * @param ruleType rule type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyCreatedViewIsSetAsDefault(String ruleType) throws Exception {
		String cappingType=null;
		switch(ruleType){
			case "Capping Smart Rule":
				cappingType="capping-smart-rules-summary";
				break;
			case "Capping Rule":
				cappingType="capping-rules-summary";
				break;
			case "Archive Capping Smart Rule":
				cappingType="archive-capping-smart-rules-summary";
				break;
			case "Archive Capping Rule":
				cappingType="archive-capping-rules-summary";
				break;
			default: throw new InvalidSwitchCaseException(ruleType + " is invalid rule type");
		}
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DEFAULT_VIEW_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DEFAULT_VIEW_XPATH.toString(), cappingType)));
		Assert.assertTrue(WebDriverAction.getElement(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DEFAULT_VIEW_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DEFAULT_VIEW_XPATH.toString(), cappingType))).getText().trim().equalsIgnoreCase(CommonPage.getTestData("CustomColumn2")), "Created view is not default");		
	}
	
	/**
	 * This method deletes created view 
	 * @param ruleType rule type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteCreatedView(String ruleType) throws Exception {
		String cappingType=null;
		switch(ruleType){
			case "Capping Smart Rule":
				cappingType="capping-smart-rules-summary";
				break;
			case "Capping Rule":
				cappingType="capping-rules-summary";
				WebElement scrollArea = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SROLL_XPATH); 
				((JavascriptExecutor)WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scrollArea);
				break;
			case "Archive Capping Smart Rule":
				cappingType="archive-capping-smart-rules-summary";
				break;
			case "Archive Capping Rule":
				cappingType="archive-capping-rules-summary";
				WebElement scrollAreaArchived = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SROLL_XPATH); 
				((JavascriptExecutor)WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scrollAreaArchived);
				break;
			default: throw new InvalidSwitchCaseException(ruleType + " is invalid rule type");
		}
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DEFAULT_VIEW_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DEFAULT_VIEW_XPATH.toString(), cappingType)));
		WebDriverAction.getElement(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DEFAULT_VIEW_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DEFAULT_VIEW_XPATH.toString(), cappingType))).click();		
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MANAGE_VIEW_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MANAGE_VIEW_XPATH.toString(), cappingType)));
		WebDriverAction.getElement(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MANAGE_VIEW_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MANAGE_VIEW_XPATH.toString(), cappingType))).click();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MANAGE_VIEW_SELECT_XPATH);
		Select select=new Select(BrowserAccess.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MANAGE_VIEW_SELECT_XPATH));
		select.selectByVisibleText(CommonPage.getTestData("CustomColumn2"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MANAGE_VIEW_DELETE_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_MANAGE_VIEW_DELETE_XPATH);
		OrderingBOQueuePage.waitUntilCompletePageLoad();
		OrderingBOBusinessMaintainedTablesPage.waitUntilAlertPopUpIsGone();
	}
	
	/**
	 * This method waits until alert popup has disappeared 
	 * @lastModifiedBy vikumar
	 */
	public static void waitUntilAlertPopUpIsGone() {
		try {
			new WebDriverWait(WebDriverAccess.getDriver(), 6).until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("div.noty_bar"), 1));
		} catch(TimeoutException|NoSuchElementException e) {
			System.out.println("Alert popup Gone");
			return;
		}
	}
	
	/**
	 * This method clicks on archived tab 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void clickOnArchivedTab() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_ARCHIVE_TAB_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_ARCHIVE_TAB_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_ARCHIVE_TAB_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_ARCHIVE_TAB_XPATH);
		OrderingBOQueuePage.waitUntilCompletePageLoad();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method return list of columns selected while creating view 
	 * @return selected column list 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static List<String> returnSelectedColumnsList() throws Exception{
		List<String> selectedColumns= new ArrayList<String>();
		List<WebElement> selectlist = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CHECKBOX_PARENT_LIST_XPATH);
		for(WebElement e:selectlist) {
			if(e.findElement(By.xpath(".//input")).isSelected()) {
				e.findElement(By.xpath(".//label"));
				selectedColumns.add(e.findElement(By.xpath(".//label")).getText().replaceAll("[^a-zA-Z0-9]+", "").trim());
			}
		}
		return selectedColumns;	
	}
	
	/**
	 * This method verifies mandatory columns are disabled
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void validateMandatoryColumns() throws Exception {
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CHECKBOX_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CHECKBOX_XPATH.toString(), "Rule")));
		Assert.assertFalse(WebDriverAction.getElement(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CHECKBOX_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CHECKBOX_XPATH.toString(), "Rule"))).isEnabled());	
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CHECKBOX_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CHECKBOX_XPATH.toString(), "Rule ID")));
		Assert.assertFalse(WebDriverAction.getElement(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CHECKBOX_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CHECKBOX_XPATH.toString(), "Rule ID"))).isEnabled());	
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CHECKBOX_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CHECKBOX_XPATH.toString(), "Corp Code")));
		Assert.assertFalse(WebDriverAction.getElement(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CHECKBOX_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_CHECKBOX_XPATH.toString(), "Corp Code"))).isEnabled());	
	
	}
	
	/**
	 * This method verifies vehicle breakdown
	 * @param ruleType rule type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyVehicleBreakdown(String ruleType) throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewButton(ruleType);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.selectSmartRuleOrEnterCappingRule(ruleType);
		OrderingBOBusinessMaintainedTablesPage.changeClient(CommonPage.getTestData("ClientNumber"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_VEHICLE_BREAKDOWN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_VEHICLE_BREAKDOWN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_VEHICLE_BREAKDOWN_TITLE_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_VEHICLE_BREAKDOWN_TITLE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_VEHICLE_BREAKDOWN_COMPANY_INFORMATION_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_VEHICLE_BREAKDOWN_COMPANY_INFORMATION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_VEHICLE_BREAKDOWN_INFORMATION_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_VEHICLE_BREAKDOWN_INFORMATION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_BREAKDOWN_CLOSE_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_BREAKDOWN_CLOSE_XPATH);
		OrderingBOBusinessMaintainedTablesPage.changeClient(CommonPage.getTestData("CustomColumn1"));
		Assert.assertTrue(!BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_VEHICLE_BREAKDOWN_XPATH).isDisplayed() , "Vehicle Breakdown should be not visible after selecting multiple client");
		OrderingBOBusinessMaintainedTablesPage.deselectSelectedClient(CommonPage.getTestData("CustomColumn1"));
	}
	
	/**
	 * This method verifies driver breakdown
	 * @param ruleType rule type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDriverBreakDown(String ruleType)  throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DRIVER_BREAKDOWN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DRIVER_BREAKDOWN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DRIVER_BREAKDOWN_TITLE_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DRIVER_BREAKDOWN_TITLE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DRIVER_BREAKDOWN_COMPANY_INFORMATION_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DRIVER_BREAKDOWN_COMPANY_INFORMATION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DRIVER_BREAKDOWN_INFORMATION_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DRIVER_BREAKDOWN_INFORMATION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DRIVER_BREAKDOWN_CLOSE_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DRIVER_BREAKDOWN_CLOSE_XPATH);
		OrderingBOBusinessMaintainedTablesPage.changeClient(CommonPage.getTestData("CustomColumn1"));
		Assert.assertTrue(!BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_DRIVER_BREAKDOWN_XPATH).isDisplayed() , "Driver Breakdown should be not visible after selecting multiple client");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_ADD_RULE_CLOSE_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_ADD_RULE_CLOSE_XPATH);
		OrderingBOQueuePage.waitUntilCompletePageLoad();	
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method adds rules with multiple client 
	 * @param ruleType rule type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void addRuleWithMultipleClient(String ruleType) throws Exception{
		OrderingBOBusinessMaintainedTablesPage.clickOnAddNewButton(ruleType);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingBOBusinessMaintainedTablesPage.selectSmartRuleOrEnterCappingRule(ruleType);
		OrderingBOBusinessMaintainedTablesPage.changeClient(CommonPage.getTestData("ClientNumber"));
		OrderingBOBusinessMaintainedTablesPage.changeClient(CommonPage.getTestData("CustomColumn1"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_EFFECTIVE_DATE_SYMBOL_XPATH);
		CommonPage.waitForElementToLoad(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_EFFECTIVE_DATE_CSS, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_EFFECTIVE_DATE_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_ADD_RULE_SAVE_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_ADD_RULE_SAVE_XPATH);	
		OrderingBOQueuePage.waitUntilCompletePageLoad();	
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifies client numbers of rule cretead 
	 * @param ruleType
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyClientValues(String ruleType) throws Exception {
		String cappingType=null;
		switch(ruleType){
			case "Capping Smart Rule":
				cappingType="capping-smart-rules-summary";
				break;
			case "Capping Rule":
				cappingType="capping-rules-summary";
				WebElement scrollArea = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULES_SROLL_XPATH); 
				((JavascriptExecutor)WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scrollArea);
				break;
			default: throw new InvalidSwitchCaseException(ruleType + " is invalid rule type");
		}
		WebElement firstEle=WebDriverAction.getElement(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_FIRST_ROW_CLIENT_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_FIRST_ROW_CLIENT_XPATH.toString(), cappingType, CommonPage.getTestData("RuleName"))));
		Assert.assertTrue(firstEle.getText().trim().equalsIgnoreCase(CommonPage.getTestData("ClientNumber"))||firstEle.getText().trim().equalsIgnoreCase(CommonPage.getTestData("CustomColumn1")), "The row data is not macthing client value");
		WebElement secondEle=WebDriverAction.getElement(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_SECOND_ROW_CLIENT_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CAPPING_RULE_SECOND_ROW_CLIENT_XPATH.toString(), cappingType, CommonPage.getTestData("RuleName"))));
		Assert.assertTrue(secondEle.getText().trim().equalsIgnoreCase(CommonPage.getTestData("CustomColumn1"))||secondEle.getText().trim().equalsIgnoreCase(CommonPage.getTestData("ClientNumber")), "The row data is not macthing client value");
	}
	
	/**
	 * This method verifies rule grouping 
	 * @param ruleType rule type
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyGrouping(String ruleType) throws Exception{
		OrderingBOBusinessMaintainedTablesPage.addRuleWithMultipleClient(ruleType);
		OrderingBOBusinessMaintainedTablesPage.verifyClientValues(ruleType);
	}
	
	/**
	 * This method verifies the buttons on Legal document and upload page 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyButtonsOnLduPage() throws Exception{
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_SIGNOUT_BUTTON_XPATH,
		 OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_CLEAR_FILTER_BUTTON_XPATH,
		 OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_SEARCH_BUTTON_XPATH,
		 OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_EXPORT_BUTTON_XPATH);
	}
	
	/**
	 * This method verifies the Pagination of data present
	 * @lastModifiedBy vikumar
	 * @throws Exception
	 */
	public static void verifyPagenation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_TABLE_ROW_XPATH);
		int pagenateOfNum = Integer.parseInt(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEOFTXT_XPATH).replace("/", "").trim());
		int pagenatePageNum = Integer.parseInt(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim());
		if (pagenateOfNum == pagenatePageNum) {
			Assert.assertEquals(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim(),BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEOFTXT_XPATH).replace("/", "").trim(),"More pages are available");
		} else if (pagenateOfNum == 0) {
			System.out.println("No data avialable to display");
		} else if (pagenateOfNum > pagenatePageNum) {
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_PAGE_LAST_XPATH);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			OrderingCommonPage.checkAlertPopUp();
			Assert.assertEquals(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEOFTXT_XPATH).replace("/", "").trim(),BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim(),"Pagenation to last  page failed");
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_PAGE_FIRST_XPATH);
			OrderingBOQueuePage.waitUntilCompletePageLoad();
			for (int i = 1; i < 2;) {
				++i;
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_PAGE_NEXT_XPATH);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.checkAlertPopUp();
				Assert.assertEquals(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim(),Integer.toString(i), "Pagenation to next page failed");
			}
			for (int i = 2; i > 1;) {
				--i;
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_PAGE_PREV_XPATH);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.checkAlertPopUp();
				Assert.assertEquals(BrowserAccess.getElementText(OrderingBOQueuePageEnum.ORDERING_BO_OOQ_PAGINATEPAGETXT_XPATH).trim(),Integer.toString(i), "Pagenation to next page failed");
			}
		} else {
			throw new NoIfElseBlockMatchedException();
		}	
	}
	
	/**
	 * This method verifies the legal document and upload page columns 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyTableColumnsLegalDocumentAndUploadPage() throws Exception {
		String label;
		List<String> expectedColumnNames = Arrays.asList(CommonPage.getTestData("CustomColumn3").split("\\|"));
		ArrayList<String> tableColumns = new ArrayList<>();
		List<WebElement> tableElements = BrowserAccess.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_COLUMN_NAME_XPATH);
		for (WebElement element : tableElements) {
			label = element.getAttribute("innerText").trim();
			if (label != null && !label.equalsIgnoreCase("")) {
				if (label.contains("\n")) {
					label = label.replace("\n", " ");
				}
				tableColumns.add(label);
			}
		}
		System.out.println("Test Data Columns Name:-"+expectedColumnNames);
		System.out.println("Table Columns Name:-"+tableColumns);
		Assert.assertEquals(expectedColumnNames, tableColumns, "Table Columns are not matching with Test Data");
	}
	
	/**
	 * This method verifies that the insert date is in descending order
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void checkLastUpdatedDateAreInDescendingOrder() throws Exception {
		SimpleDateFormat formattr= new SimpleDateFormat("MM/dd/yyyy HH:mm");
		List<String> dateInString = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_LAST_UPDATED_DATE_XPATH)
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
	 * This method searches and verifies the records 
	 * @param columnName Name of the column 
	 * @param searchText Search text 
	 * @lastModifiedBy vikumar
	 * @throws Exception
	 */
	public static void searchAndVerifyRecords(String columnName, String searchText) throws Exception {
		OrderingBOBusinessMaintainedTablesPage.searchOrderByColumnName(columnName, searchText);
		OrderingBOBusinessMaintainedTablesPage.verifySearchedRecords(columnName, searchText);
	}

	/**
	 * This method Searches the record according to search option 
	 * @lastModifiedBy vikumar
	 * @throws Exception
	 */
	public static void searchOrderByColumnName(String columnName, String searchText)	throws Exception {
		System.out.println(columnName + ": " + searchText);
		WebDriverAction.enterFieldValue(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_SEARCH_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_SEARCH_XPATH.toString(), columnName)),searchText);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_SEARCH_BUTTON_XPATH);
		OrderingBOQueuePage.waitUntilCompletePageLoad();
		WebDriverAction.clear(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_SEARCH_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_SEARCH_XPATH.toString(), columnName)));
	}

	/**
	 * This method verifies the searched records 
	 * @param columnName name of the column 
	 * @param searchText searched text 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifySearchedRecords(String columnName, String searchText) throws Exception {
		String column = null;
		if(columnName.equalsIgnoreCase("Corp ID")) {
			column="corpCD";
		}
		List<WebElement> elementslist = WebDriverAccess.getElements(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_TABLE_XPATH.name(),String.format(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_TABLE_XPATH.toString(),column)));
		for (WebElement element : elementslist) {
			Assert.assertEquals(element.getText().trim(), searchText.trim(), "Searched record is not matching");
		}
	}
	
	/**
	 * Verifies the Document Type and Document category errors while creating records 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyAddNewRecordDocumentTypeandCategoryErrorMessages() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_ADD_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_ADD_BTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_SAVE_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_SAVE_BTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_DOCUMENT_CATEGORY_ERROR_XPATH);
		Assert.assertEquals( BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_DOCUMENT_CATEGORY_ERROR_XPATH).getText(), "Field required", "Error is not matching");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_DOCUMENT_TYPE_ERROR_XPATH);
		Assert.assertEquals( BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_DOCUMENT_TYPE_ERROR_XPATH).getText(), "Field required", "Error is not matching");
	}
	
	/**
	 * Verifies the end date error message while creating records 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyAddNewPageEndDateErrorMessage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_ARCHIVE_DATE_PICKER_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_ARCHIVE_DATE_PICKER_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_ARCHIVE_DATE_PICKER_TODAY_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_ARCHIVE_DATE_PICKER_TODAY_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_DOCUMENT_DATE_ERROR_XPATH);
		Assert.assertEquals( BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_DOCUMENT_DATE_ERROR_XPATH).getText(), "End Date must be greater than Start Date");
		WebDriverAction.clear(BrowserAccess.getLocator(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_ARCHIVE_DATE_INPUT_XPATH));
	}
	
	/**
	 * Verifies the corp code error while creating records 
	 * @param clientNumber Client number 
	 * @param documentType Document Type
	 * @param documentCategory Document category 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyCorpCodeError(String clientNumber, String documentType, String documentCategory) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_CLIENT_NUMBER_INPUT_XPATH, clientNumber);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_DOCUMENT_CATEGORY_SELECT_XPATH, documentCategory);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_DOCUMENT_TYPE_SELECT_XPATH, documentType);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_SAVE_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_SAVE_BTN_XPATH);
		boolean flag=false;
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Corp Code error is not thrown");
	}
	
	/**
	 * verifies document upload error while creating error
	 * @param corpCode corp code
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyDocumentUploadError(String corpCode) throws Exception {
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_LDU_CORP_CODE_SELECT_XPATH, corpCode);
		boolean flag=false;
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Document upload error is not thrown");
	}

	/**
	 * This method verifies reset filter after exiting A business maintained screen
	 * @param tableName
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyResetFunctionalityWhenExitingBMTScreen(String tableName) throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_PRICE_PROTECTION_QUEUE_VIEW_LINK_XPATH);
		OrderingBOBusinessMaintainedTablesPage.businessMaintainedTablePageLoaded();
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption(tableName);
		int numberOfRows = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TABLE_BODY_ROW_XPATH).size();
		Assert.assertEquals(numberOfRows >= 1, true);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TOGGLE_COLUMNS_BUTTON_XPATH);
		List <WebElement> checkBoxesToggle = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TOGGLE_COLUMNS_CHECKBOXES_XPATH);
		for (WebElement checkBox : checkBoxesToggle) {
			Assert.assertEquals(checkBox.isSelected(), true, "All checkboxes should be selected in the toggle column filter");
		}
	}
	
	/**
	 * This method adds a new rule for CODAR
	 * Customer Ordering Dealer Assignment Rules
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void addNewCODARRule() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.fillRequiredInfoCodarRule();
		OrderingBOBusinessMaintainedTablesPage.saveNewRuleAfterFillingDetails();
	}
	
	/**
	 * This method clicks saves button and verifies save success message
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void saveNewRuleAfterFillingDetails() throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SAVE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_NOTIFICATION_MESSAGE_LABEL_CSS);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_NOTIFICATION_MESSAGE_LABEL_CSS).trim(), "Data has been successfully saved");
		OrderingCommonPage.checkAlertPopUp();
		System.out.println("New rule created");
	}
	
	/**
	 * This method fill required info for create new rule screen if client number is required
	 * Customer Ordering Dealer Assignment Rules
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void fillRequiredInfoCodarRule() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickAddNewRuleButton();
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_CLIENT_NUMBER_SELECT_ID, CommonPage.getTestData("ClientNumber"));
		System.out.println("Client Number: " + CommonPage.getTestData("ClientNumber"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DIVERSE_SWITCH_XPATH);
		BrowserAction.selectDropdownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_MAKE_SELECT_ID, CommonPage.getTestData("Make"));
		System.out.println("Make:" + CommonPage.getTestData("Make"));
	}
	
	/**
	 * This method verifies error message is displayed when leaving diverse switch Off and client number is present
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyErrorIsShownWithMissingData() throws Exception {
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_CLIENT_NUMBER_SELECT_ID, CommonPage.getTestData("ClientNumber"));
		System.out.println("Client Number:" + CommonPage.getTestData("ClientNumber"));
		BrowserAction.selectDropdownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_MAKE_SELECT_ID, CommonPage.getTestData("Make"));
		OrderingBOBusinessMaintainedTablesPage.clickOnSaveButton();
		OrderingBOBusinessMaintainedTablesPage.verifyErrorMessageIsDisplayedCodarScreen();
	}
	
	/**
	 * This method clicks the ADD new Rule button
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void clickAddNewRuleButton() throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ADD_NEW_RULE_BUTTON_XPATH);
	}
	
	/**
	 * This method waits for CODAR Add New Rule screen to load 
	 * Customer Ordering Dealer Assignment Rules
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void waitForAddNewRuleScreen() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_CORP_CODE_SELECT_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_CLIENT_NUMBER_SELECT_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ALWAYS_USE_SELECT_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DIVERSE_SWITCH_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_MAKE_SELECT_ID);
	}
	
	/**
	 * This method searches the CODAR rule creating for specified client and make
	 * Customer Ordering Dealer Assignment Rules
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void searchCODARRule() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_FLEET_INPUT_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_MAKE_INPUT_XPATH);
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_FLEET_INPUT_XPATH, CommonPage.getTestData("ClientNumber"));
		System.out.println("Client Number:" + CommonPage.getTestData("ClientNumber"));
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_MAKE_INPUT_XPATH, CommonPage.getTestData("Make"));
		System.out.println("Make:" + CommonPage.getTestData("Make"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifies the delete button is disabled when no rule is selected
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyDeleteButtonIsDisabled() throws Exception {
		String deleteButtonState = BrowserAction.getElementAttributeValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DELETE_BUTTON_XPATH, "outerHTML");
		Assert.assertEquals(deleteButtonState.contains("disabled"), true);
	}
	
	/**
	 * This method verifies the delete button gets enabled after selecting rule
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyDeleteButtonGetsEnabled() throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ORDER_CHECKBOX_XPATH);
		String deleteButtonState = BrowserAction.getElementAttributeValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DELETE_BUTTON_XPATH, "outerHTML");
		Assert.assertEquals(deleteButtonState.contains("disabled"), false);
	}
	
	/**
	 * This method cancels the delete operation
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void cancelDeleteOperation(String fromScreen) throws Exception {
		String actualRuleID = BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BULK_COPY_RULE_ID_LIST_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DELETE_BUTTON_XPATH);
	switch (fromScreen) {
		case "From Main Screen":
			OrderingBOBusinessMaintainedTablesPage.handleDeleteWarningPopup("No - From Main Screen");
			break;
		case "From Edit Rule Screen":
			OrderingBOBusinessMaintainedTablesPage.handleDeleteWarningPopup("No");
			break;
		default: throw new InvalidSwitchCaseException("Invalid Screen option selected");
	}
		OrderingBOBusinessMaintainedTablesPage.searchByRuleId(actualRuleID);
		int numberOfRows = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TABLE_BODY_ROW_XPATH).size();
		Assert.assertEquals(numberOfRows >= 1, true, "Rule should not have been deleted");
	}
	
	/**
	 * This method deletes the selected rule
	 * Ordering Dealer Assignment Rules
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void deleteSelectedRule(String fromScreen) throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ORDER_CHECKBOX_XPATH);
		String actualRuleID = BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BULK_COPY_RULE_ID_LIST_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DELETE_BUTTON_XPATH);
		switch (fromScreen) {
		case "From Main Screen":
			OrderingBOBusinessMaintainedTablesPage.handleDeleteWarningPopup("Yes - From Main Screen");
			break;
		case "From Edit Rule Screen":
			OrderingBOBusinessMaintainedTablesPage.handleDeleteWarningPopup("Yes");
			break;
		default: throw new InvalidSwitchCaseException("Invalid Screen option selected");
		}
		
		OrderingBOBusinessMaintainedTablesPage.searchByRuleId(actualRuleID);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_NODATA_RESULT_LABEL_XPATH), "No data available in table");
	}
	
	/**
	 * This method handles the delete warning popup 
	 * Ordering Dealer Assignment Rules
	 * @param action
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void handleDeleteWarningPopup(String action) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DELETE_WARNING_MODAL_XPATH);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DELETE_WARNING_MODAL_XPATH).trim(), "Are you sure you want to delete this entry?");
		switch (action) {
		case "Yes":
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_CONFIRM_DELETE_MODAL_BUTTON_XPATH);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
			break;
		case "No":
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_CANCEL_DELETE_MODAL_BUTTON_XPATH);
			break;
		case "Yes - From Main Screen":
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_OK_BTN_XPATH);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
			break;
		case "No - From Main Screen":
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_CANCEL_BTN_XPATH);
			break;
		default: throw new InvalidSwitchCaseException("Invalid Case Was Selected");
		}
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method searches rule by rule ID
	 * Ordering Dealer Assignment Rules
	 * @param ruleId
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void searchByRuleId(String ruleId) throws Exception {
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_RULEID_INPUT_XPATH, ruleId);
		System.out.println("Rule id to be searched: " + ruleId);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method enters rule edit view screen of first record for CODAR screen
	 * Customer Ordering Dealer Assignment Rules
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void enterEditViewFirstRecord() throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BULK_COPY_RULE_ID_LIST_XPATH);
		OrderingBOBusinessMaintainedTablesPage.waitForAddNewRuleScreen();
	}
	
	/**
	 * This method cancels the delete rule operation from edit view screen
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void cancelDeleteRuleFromEditViewScreen() throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		OrderingBOBusinessMaintainedTablesPage.handleDeleteWarningPopup("No - From Main Screen");
	}
	
	/**
	 * This method deletes rule from edit view screen and verifies it was deleted
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void deleteRuleFromEditViewScreen() throws Exception {
		String ruleId = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_RULEID_INPUT_XPATH).getAttribute("value");
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_DELETE_BUTTON_ID);
		OrderingBOBusinessMaintainedTablesPage.handleDeleteWarningPopup("Yes - From Main Screen");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_RULEID_INPUT_XPATH);
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_RULEID_INPUT_XPATH, ruleId);
		System.out.println("Deleted Rule: " + ruleId);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_NODATA_RESULT_LABEL_XPATH);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_NODATA_RESULT_LABEL_XPATH), "No data available in table");
	}
	
	/**
	 * This method edits Rule ID and verifies error message shown
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyErrorWhenEditingRuleId() throws Exception {
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_RULEID_INPUT_XPATH, CommonPage.getTestData("RuleID"));
		System.out.println("Invalid Rule to send to input:" + CommonPage.getTestData("RuleID"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_SAVE_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS).trim(), "Invalid Rule ID or Rule Name");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method picks a date according to user preference 
	 * @param dateStatus
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void selectDate(String dateStatus) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ENDDATE_INPUT_ID);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ENDDATE_INPUT_ID);
		switch (dateStatus) {
		case "valid":
			BrowserAction.clickandClear(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ENDDATE_INPUT_ID);
			BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ENDDATE_INPUT_ID, CommonPage.getCurrentDataIn("MM/dd/yyyy", "" + 1 + ""));
			System.out.println("Date to send to input:" + CommonPage.getCurrentDataIn("MM/dd/yyyy", "" + 1 + ""));
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ENDDATE_LABEL_XPATH);
			break;
		case "invalid":
			BrowserAction.clickandClear(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ENDDATE_INPUT_ID);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NEWRULE_TODAY_CALENDAR_XPATH);
			String todaysDate = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NEWRULE_TODAY_CALENDAR_XPATH).getText();
			int invalidDate = Integer.parseInt(todaysDate) - 1;
			System.out.println("Calendar date to send: " + invalidDate);
			WebDriverAccess.getDriver().findElement(By.linkText(String.valueOf(invalidDate))).click();
			System.out.println("Date to send to input:" + CommonPage.getCurrentDataIn("MM/dd/yyyy", "" + -1 + ""));
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ENDDATE_LABEL_XPATH);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ENDDATE_INVALID_MESSAGE_XPATH);
			Assert.assertEquals(BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ENDDATE_INVALID_MESSAGE_XPATH), "Effective End Date must be greater than Effective Start Date");
			break;
		default: throw new OrderingErrorOccured("Invalid date status was sent");
		}
	}
	
	/**
	 * This method verifies an error message is shown
	 * "Rule must be configured as diverse dealer or always/never use dealer rule"
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyErrorMessageIsDisplayedCodarScreen() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS).trim(), "Rule must be configured as diverse dealer or always/never use dealer rule");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifies Data has been successfully updated message
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifySuccessMessageIsDisplayedCodarScreen() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS).trim(), "Data has been successfully updated");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method edits Cancels any Edit operation in edit view screen
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void cancelEditOperation() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.handleCancelButtonActionAfterEditingRule("Yes");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TOGGLE_COLUMNS_BUTTON_XPATH);
	}
	
	/**
	 * This method handles the modal displayed when clicking canceling in CODAR screen and changes have been made
	 * Customer Ordering Dealer Assignment Rules
	 * @throws Exception
	 * @param action 
	 * @lastModifiedBy hjimenez
	 */
	public static void handleCancelButtonActionAfterEditingRule(String action) throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CANCEL_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DELETE_WARNING_MODAL_XPATH);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DELETE_WARNING_MODAL_XPATH).trim(), "Are you sure you want to cancel without saving the changes?");
		switch (action) {
		case "Yes":
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_OK_BTN_XPATH);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			break;
		case "No":
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_CANCEL_BTN_XPATH);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TOGGLE_COLUMNS_BUTTON_XPATH);
			break;
		default: throw new InvalidSwitchCaseException("Invalid option selected");
		}
	}
	
	/**
	 * This method verifies that 'always' is selected by default in always/never field
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyAlwaysIsSelectedByDefault() throws Exception {
		Select select = new Select(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ALWAYS_USE_SELECT_ID));
		String defaultOption = select.getFirstSelectedOption().getText();
		Assert.assertEquals(defaultOption, "Always Use");
	}
	
	/**
	 * This method clicks the export button and keeps the file in downloads folder
	 * @lastModifiedBy Hector Jimenez
	 * @throws Exception
	 */
	public static void verifyExportFuntionality() throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_EXPORT_BUTTON_XPATH);
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_EXPORT_BUTTON_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method verifies the exported CSV contains the expected headers
	 * @lastModifiedBy Hector Jimenez
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
	 * This method verifies columns are listed are per the requirement 
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyColumnHeadersValues(String [] expectedColumns) throws Exception {
		int j = 0;
		for (int i = 2; i < BrowserAccess.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TABLE_HEADER_XPATH).size(); i++) {
			if (WebDriverAccess.getDriver().findElements(By.xpath("//th[@data-name][" + i + "]")).size() > 0) {
				if (!WebDriverAccess.getDriver().findElement(By.xpath("//th[@data-name][" + i + "]")).getText().isEmpty() ) {	
					String columnHeaderText = WebDriverAccess.getDriver().findElement(By.xpath("//th[@data-name][" + i + "]")).getText().replace("\n", " ");
					System.out.println(columnHeaderText);
					Assert.assertEquals(columnHeaderText, expectedColumns[j], "Header value does not match or is in wrong order..." + columnHeaderText );
					j++;
				}
			}else {
				break;
			}
		}
	}
	/**
	 * This method verifies columns are sorted as per Corp column in ascending order
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyDefaultSortEodarScreen() throws Exception {
		List<String> sortedCorpCodeValues = new ArrayList<String>();
		List <WebElement> corpCodeRowValues =  BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EOD_CORPCODE_ROW_LABEL_XPATH);
		for (WebElement corpCodeRowValue : corpCodeRowValues) {
			sortedCorpCodeValues.add(corpCodeRowValue.getText());
		}
		boolean isColumnSorted = Ordering.natural().isOrdered(sortedCorpCodeValues);
		Assert.assertEquals(isColumnSorted, true);
	}
	
	/**
	 * This method waits for EODAR Add New Rule screen to load 
	 * Customer Ordering Dealer Assignment Rules
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void waitForAddNewRuleScreenEODAR() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_CORP_CODE_SELECT_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ALWAYS_USE_SELECT_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DIVERSE_SWITCH_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_MAKE_SELECT_ID);
	}
	/**
	 * This method clicks first rule shown on screen
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void clickFirstRuleDisplayedOnQueue() throws Exception {
		if (BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BULK_COPY_RULE_ID_LIST_XPATH).size() > 0) {
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BULK_COPY_RULE_ID_LIST_XPATH);
		}else {
			OrderingBOBusinessMaintainedTablesPage.clickAddNewRuleButton();
			OrderingBOBusinessMaintainedTablesPage.waitForAddNewRuleScreenEODAR();
			OrderingBOBusinessMaintainedTablesPage.fillRequiredInfoEodarRule();
			OrderingBOBusinessMaintainedTablesPage.saveNewRuleAfterFillingDetails();
			OrderingCommonPage.checkAlertPopUp();
			BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BULK_COPY_RULE_ID_LIST_XPATH);
		}	
	}
	
	/**
	 * This method fill required info for create new rule screen, diverse switch and make
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void fillRequiredInfoEodarRule() throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DIVERSE_SWITCH_XPATH);
		BrowserAction.selectDropdownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_MAKE_SELECT_ID, CommonPage.getTestData("Make"));
		System.out.println("Make:" + CommonPage.getTestData("Make"));
	}
	
	/**
	 * This method verifies diverse dealer Switch can be selected and deselected in edit view screen
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyDiverseDealerSwitch() throws Exception {
		BrowserVerify.verifyElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DIVERSE_SWITCH_XPATH);
		Assert.assertEquals(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EOD_DIVERSE_SWITCH_ID).isSelected() , false);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DIVERSE_SWITCH_XPATH);
		Assert.assertEquals(BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EOD_DIVERSE_SWITCH_ID).isSelected() , true);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DIVERSE_SWITCH_XPATH);
	}
	
	/**
	 * This method verifies error message is displayed when leaving diverse switch Off
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyErrorIsShownWithMissingDataEODAR() throws Exception {
		BrowserAction.selectDropdownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_MAKE_SELECT_ID, CommonPage.getTestData("Make"));
		OrderingBOBusinessMaintainedTablesPage.clickOnSaveButton();
		OrderingBOBusinessMaintainedTablesPage.verifyErrorMessageIsDisplayedCodarScreen();
	}
	
	/**
	 * This method searches the EODAR rule creating for specified client and make
	 * Element Ordering Dealer Assignment Rules
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void searchEODARRule() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_MAKE_INPUT_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EOD_STARTDATE_INPUT_XPATH);
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_MAKE_INPUT_XPATH, CommonPage.getTestData("Make"));
		CommonPage.enterTextToInputField(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EOD_STARTDATE_INPUT_XPATH, CommonPage.getCurrentDataIn("MM/dd/yyyy"));
		System.out.println("Make:" + CommonPage.getTestData("Make"));
		System.out.println("Date:" + CommonPage.getCurrentDataIn("MM/dd/yyyy"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		Assert.assertEquals(BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BULK_COPY_RULE_ID_LIST_XPATH).size() >= 1, true);
	}
	
	/**
	 * This method verifies an error is shown when end date input is less than start date input
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyErrorMessageWhenEditingDate() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_NOTIFICATION_POPUP_CSS).trim(), "Effective End Date must be greater than Current date");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	/**
	 * This method verifies verifies if end date is less than current date and returns true or false
	 * Customer Ordering Dealer Assignment Rules
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static boolean isEndDateValid() throws Exception {
		String date1 = BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ENDDATE_INPUT_ID).getAttribute("value");
		System.out.println("date 1 " + date1);
		String date2 = CommonPage.getCurrentDataIn("MM/dd/yyyy");
		System.out.println("date 2 " + date2);
		return CommonPage.isFirstDateGreaterThanSecondDate(date1, date2);
	}
	/**
	 * This method verifies the different errors displayed when saving without changes
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyMessageWhenSavingWitoutChanges() throws Exception {
		OrderingBOBusinessMaintainedTablesPage.clickOnSaveButton();
		if (!BrowserAction.getElement(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_ENDDATE_INPUT_ID).getAttribute("value").isEmpty()) {
			if (OrderingBOBusinessMaintainedTablesPage.isEndDateValid()) {
				OrderingBOBusinessMaintainedTablesPage.verifyErrorMessageWhenEditingDate();
				BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_RULE_CANCEL_BUTTON_ID);
				OrderingCommonPage.checkGlobalSpinnerPopUp();
			}else {
			OrderingBOBusinessMaintainedTablesPage.verifySuccessMessageIsDisplayedCodarScreen();
			}
		} 
	}

	/**
	 * This method verifies toggle columns values
	 * @param expectedValues, elementToggleText
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyToggleValues(String [] expectedValues, String[] elementToggleText) throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TOGGLE_COLUMNS_BUTTON_XPATH);
		for (int i = 0; i < expectedValues.length; i++) {
			String actualToggleOption = WebDriverAccess.getDriver().findElement(By.xpath("//label[@title='"+ elementToggleText[i] +"']")).getText();
			Assert.assertEquals(expectedValues[i], actualToggleOption, "Label must be missing...");
		}
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TOGGLE_COLUMNS_BUTTON_XPATH);
	}
	
	/**
	 * This method clicks filters one by one in CODAR page
	 * Customer Ordering Dealer Assignment Rules
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void clickOnEachFilter() throws Exception{
		for (int i = 2; i < BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TABLE_HEADER_XPATH).size(); i++) {
			if (WebDriverAccess.getDriver().findElements(By.xpath("//th[@data-name][" + i + "]")).size() > 0) {
				if (!WebDriverAccess.getDriver().findElement(By.xpath("//th[@data-name][" + i + "]")).getText().isEmpty()) {
					// this is the way to send a param in the xpath is not possible with BrowserAction
					WebDriverAccess.getDriver().findElement(By.xpath("//th[@data-name][" + i + "]")).click();
					System.out.println("Clicked: " + WebDriverAccess.getDriver().findElement(By.xpath("//th[@data-name][" + i + "]")).getText());
					OrderingCommonPage.checkGlobalSpinnerPopUp();
					OrderingCommonPage.verifyNoAlertPopUpDispalyed();
				}
			}else {
				break;
			}
		}
	}
	
	/**
	 * This method searches a rule using the info of first record 
	 * @param ruleIdColumnNumber 
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifySearchRuleFunctionality(int ruleIdColumnNumber) throws Exception {
		List <WebElement> searchValues = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_FIRSTRECORD_VALUES_XPATH);
		List <String> searchData = new ArrayList<>();
		List <WebElement> textfields = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_INPUT_FIELDS_XPATH);
		for (WebElement value : searchValues) {
			searchData.add(value.getText());
		}
		System.out.println(searchData);
		int i = 0;
		for (WebElement textfield : textfields) {
			textfield.sendKeys(searchData.get(i));
			System.out.println("Text send to input:" + searchData.get(i));
			i++;
		}
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		String actualRuleID = BrowserAction.getElementText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BULK_COPY_RULE_ID_LIST_XPATH);
		Assert.assertEquals(searchData.get(ruleIdColumnNumber).trim(), actualRuleID.trim());
	}
	
	/**
	 * This method verifies that after user enters to rule view screen and goes back, filter should still be active
	 * Customer Ordering Dealer Assignment Rules
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyFilterIsActiveWhenEnteringEditView() throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BULK_COPY_RULE_ID_LIST_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_DIV_RULE_ID);
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_SEARCHBTN_XPATH);
		int numberOfRowsFilterActivated = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TABLE_BODY_ROW_XPATH).size();
		System.out.println("Number of Rows Displayed: " + numberOfRowsFilterActivated);
		Assert.assertEquals(numberOfRowsFilterActivated, 1, "After entering edit view and click back, filter should remain active");
	}
	
	/**
	 * This method verifies the clear filter functionality
	 * Customer Ordering Dealer Assignment Rules
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyClearFilterFunctionality() throws Exception {
		int numberOfRowsFilterActivated = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TABLE_BODY_ROW_XPATH).size();
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COMMON_CLEARFILTERBTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		int numberOfRowsAfterClearedFilter = BrowserAction.getElements(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TABLE_BODY_ROW_XPATH).size();
		System.out.println("Number of rows after filter is cleared: " + numberOfRowsAfterClearedFilter);
		Assert.assertEquals(numberOfRowsAfterClearedFilter >= numberOfRowsFilterActivated, true, "After search is cleared, table should display all records");
	}
	
	/**
	 * This method verifies toggle columns functionality
	 * @param columnValues,toggleOptions 
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void verifyToggleColumnsFunctionality(String [] columnValues, String [] toggleOptions) throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_COD_TOGGLE_COLUMNS_BUTTON_XPATH);
		for (int i = 0; i < columnValues.length; i++) {
			if(i == columnValues.length -1) {
				WebDriverAccess.getDriver().findElement(By.xpath("//label[@title='"+ toggleOptions[i - 1] +"']")).click();
			}
			WebDriverAccess.getDriver().findElement(By.xpath("//label[@title='"+ toggleOptions[i] +"']")).click();
			int afterToggleFilter = WebDriverAccess.getDriver().findElements(By.xpath("//th[@data-name='" + columnValues[i] + "']")).size();
			Assert.assertEquals(afterToggleFilter, 0, "toggle column must be removed after activating toggle filter");
		}
	}
	
	/**
	 * adds new customer delivering dealer 
	 * @param corp corp code
	 * @param fleetID fleet id 
	 * @param ruleName rule name 
	 * @param maxDeliveringFee max delivering fee
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void addNewCustomerDeliveringDARule(String corp, String fleetID, String ruleName, String maxDeliveringFee) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_ADD_NEW_BTN_XPATH);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_CORP_SELECT_XPATH, corp);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_FLEETID_INPUT_XPATH, fleetID);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_SUGGESTION_FLEET_XPATH);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_RULE_NAME_INPUT_XPATH, ruleName);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_COURSTESY_DELIVERY_INPUT_XPATH, maxDeliveringFee);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SAVE_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SAVE_BTN_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		waitUntilAlertPopUpIsGone();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BACK_TO_QUEUE_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BACK_TO_QUEUE_BTN_XPATH);
	}
	
	/**
	 * searches customer delivering DA rule
	 * @param corpcode corp code 
	 * @param fleetID fleet id
	 * @param rulename rule name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchCustomerDeliveringDaRule(String corpcode, String fleetID, String rulename) throws Exception {
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_CORP_SEARCH_XPATH, corpcode);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_FLEET_ID_SEARCH_XPATH, fleetID);
		waitAndEnterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_RULE_NAME_SEARCH_XPATH, rulename);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SEARCH_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SEARCH_XPATH);
	}
	
	/**
	 * Verifies customer delivering DA rule exists
	 * @param corpcode corp code
	 * @param fleetID fleet id
	 * @param rulename rule name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyCustomerDeliveryDaRuleExists(String corpcode, String fleetID, String rulename) throws Exception {
		searchCustomerDeliveringDaRule(corpcode, fleetID, rulename);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitAndAssertText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_RULE_NAME_TABLE_XPATH, rulename, "Rule name is not macthing");
	}
	
	/**
	 * verifies rule cannot be added with existing rule name
	 * @param corp corp code
	 * @param fleetID fleet id
	 * @param ruleName rule name
	 * @param maxDeliveringFee maximum delivering fee
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifyRuleCannotBeAddedWithExistingRuleName(String corp, String fleetID, String ruleName, String maxDeliveringFee) throws Exception {
		boolean flag=false;
		try{
			addNewCustomerDeliveringDARule(corp, fleetID, ruleName, maxDeliveringFee);
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Duplicate rule name error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BACK_TO_QUEUE_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BACK_TO_QUEUE_BTN_XPATH);
	}
	
	/**
	 * verifies that the duplicate rule name exists error while changing corp code
	 * @param corp corp code
	 * @param fleetID fleet ID
	 * @param ruleName rule name 
	 * @param secondCorp second corp code
	 * @throws Exception
	 */
	public static void verifyDuplicateRuleNameWhileChangingCorpCode(String corp, String fleetID, String ruleName, String secondCorp) throws Exception {
		searchCustomerDeliveringDaRule(corp, fleetID, ruleName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_RULE_NAME_TABLE_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_RULE_NAME_TABLE_XPATH);
		waitAndSelectDropDownOptionByText(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_CORP_SELECT_XPATH, secondCorp);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SAVE_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_SAVE_BTN_XPATH);
		boolean flag=false;
		try{
			OrderingCommonPage.checkAlertPopUp();
			flag=false;
		} catch(OrderingErrorOccured e){
			flag=true;
		}
		Assert.assertTrue(flag==true, "Duplicate rule name error is not thrown");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BACK_TO_QUEUE_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_BACK_TO_QUEUE_BTN_XPATH);
	}
	
	/**
	 * deletes customer delivery DA rule if it exists 
	 * @param corp corp code
	 * @param fleetID fleet ID
	 * @param ruleName rule name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void deleteCustomerDeliveryDaRule(String corp, String fleetID, String ruleName) throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_RULE_NAME_TABLE_XPATH.getValue()), 0));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_RULE_NAME_TABLE_XPATH);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_DELETE_BTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_OK_BTN_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_OK_BTN_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		waitUntilAlertPopUpIsGone();
		searchCustomerDeliveringDaRule(corp, fleetID, ruleName);
		verifyNoEntryIsDisplayed();
	}
	
	/**
	 * Searches and deletes customer delivery DA rule if it exists 
	 * @param corp corp code
	 * @param fleetID fleet id 
	 * @param ruleName rule name 
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void searchAndDeleteIfCustomerDeliveryDaRuleExists(String corp, String fleetID, String ruleName) throws Exception {
		searchCustomerDeliveringDaRule(corp, fleetID, ruleName);
		try {
			verifyNoEntryIsDisplayed();
		} catch(OrderingErrorOccured e) {
			deleteCustomerDeliveryDaRule(corp, fleetID, ruleName);
		}
	}
	
	/**
	 * Clicks the link go back to queue view
	 * @throws Exception
	 * @lastModifiedBy hjimenez
	 */
	public static void goBackToQueueView() throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_EDIT_BMT_PAGE_BACK_TO_QUEUE_VIEW_LINK_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method click on element as per paased element
	 * @throws Exception
	 * @lastModifiedBy akandkonde
	 */
	public static void clickOnRuleIdColumn(String element) throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(element)));
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(element));
		WebDriverVerify.verifyElementIsPresent(By.xpath(element));
		WebDriverVerify.verifyElementEnabled(By.xpath(element));
		WebDriverAction.click(By.xpath(element));
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(element)));
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(element));
		WebDriverVerify.verifyElementIsPresent(By.xpath(element));
		WebDriverVerify.verifyElementEnabled(By.xpath(element));
		WebDriverAction.click(By.xpath(element));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(element)));
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(element));
		WebDriverVerify.verifyElementIsPresent(By.xpath(element));
		WebDriverVerify.verifyElementEnabled(By.xpath(element));
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
}
