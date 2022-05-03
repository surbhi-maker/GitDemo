package com.element.fleet.ordering.page;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOHomePageEnum;
import com.element.fleet.ordering.enums.OrderingBOOrdMaintPageEnum;
import com.element.fleet.ordering.enums.OrderingElementDeliveringDealerAssignmentRulesPageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingBOHomePage {
	
	/**
	 * This method waits for the back office home page to be loaded.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void orderingBOHomePageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_WELCOME_MESSAGE1_LAST1_CHARACTER_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_WELCOME_MESSAGE1_LAST1_CHARACTER_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_WELCOME_MESSAGE1_LAST1_CHARACTER_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_WELCOME_MESSAGE2_LAST2_CHARACTER_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_WELCOME_MESSAGE1_LAST3_CHARACTER_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_WELCOME_MESSAGE2_LAST1_CHARACTER_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_WELCOME_MESSAGE2_LAST1_CHARACTER_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_WELCOME_MESSAGE2_LAST1_CHARACTER_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_WELCOME_MESSAGE2_LAST2_CHARACTER_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_WELCOME_MESSAGE2_LAST3_CHARACTER_CSS);
		System.out.println("BO home page loaded");
	}

	public static void orderingBOSideMenuLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BO_SIDE_MENU_ID);
	}

	/**
	 * This method clicks on the side menu option.
	 * @lastModifiedBy shisingh
	 * @param option
	 * @throws Exception
	 */
	public static void selectSideMenuOption(String option) throws Exception {
		Object element = null;
		switch(option) {
			case "Queues": element = OrderingBOHomePageEnum.ORDERING_BO_QUEUES_MENU_XPATH;
				break;
			case "Edit Order": element = OrderingBOHomePageEnum.ORDERING_BO_EDIT_ORDER_MENU_XPATH;
				break;
			case "Manager Order Preferences": element = OrderingBOHomePageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_MENU_XPATH;
				break;
			case "Codes Table": element = OrderingBOHomePageEnum.ORDERING_BO_CODES_TABLES_MENU_XPATH;
				break;
			case "Batch Processing": element = OrderingBOHomePageEnum.ORDERING_BO_BATCH_PROCESSING_MENU_XPATH;
				break;
			case "Business Maintained Tables": element = OrderingBOHomePageEnum.ORDERING_BO_BUSINESS_MAINTAINED_TABLES_MENU_XPATH;
				break;
			case "Change History": element = OrderingBOHomePageEnum.ORDERING_BO_CHANGE_HISTORY_MENU_XPATH;
				break;	
			default: throw new InvalidSwitchCaseException("Invalid side menu option selected");
		}
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(element)));
		System.out.println("BO Side Menu: " + option);
		BrowserAction.click(element);
		OrderingCommonPage.checkAlertPopUp();
	}

	public static void selectEditOrderOptions(String option) throws Exception {
		Object element = null;
		switch(option) {
			case "Order Maintenance": element = OrderingBOHomePageEnum.ORDERING_BO_EO_ORDER_MAINTENANCE_XPATH;
				break;
			case "Add Upfit Purchase Order": element = OrderingBOHomePageEnum.ORDERING_BO_EO_ADD_UPFIT_PURCHASE_ORDER_XPATH;
				break;
			case "Invoice Entry": element = OrderingBOHomePageEnum.ORDERING_BO_EO_INVOICE_ENTRY_XPATH;
				break;
			default: throw new InvalidSwitchCaseException("Invalid Edit Order option selected");
		}
		System.out.println("Edit Order: " + option);
		BrowserAction.click(element);	
		OrderingCommonPage.checkAlertPopUp();
	}

	public static void selectBatchProcessingOptions(String option) throws Exception {
		Object element = null;
		switch(option) {
			case "Mass Updates": element = OrderingBOHomePageEnum.ORDERING_BO_BP_MASS_UPDATES_XPATH;
				break;
			case "Batch Ordering": element = OrderingBOHomePageEnum.ORDERING_BO_BP_BATCH_ORDERING_XPATH;
				break;
			case "Create Driver Group": element = OrderingBOHomePageEnum.ORDERING_BO_BP_CREATE_DRIVER_GROUP_XPATH;
				break;
			case "Driver Group Maintenance": element = OrderingBOHomePageEnum.ORDERING_BO_BP_DRIVER_GROUP_MAINTENANCE_XPATH;
				break;
			default: throw new InvalidSwitchCaseException(option + "is a invalid Batch Processing option");
		}
		System.out.println("Batch Processing: " + option);
		BrowserAction.click(element);
		OrderingCommonPage.checkAlertPopUp();	
	}
	
	/**
	 * This method select required options for business maintained tables
	 * @latsModifiedBy sagrawal
	 * @throws Exception
     */
	public static void selectBusinessMaintainedTableOptions(String option) throws Exception {
		Object element = null;
		switch(option) {
			case "Element Delivering Dealer Assignment Rules": element =BrowserAccess.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SIDE_MENU_XPATH);
				break;
			default: throw new InvalidSwitchCaseException(option + "is a invalid Batch Processing option");
		}
		System.out.println("Business Maintained Tables: " + option);
		BrowserAction.click(element);
		OrderingCommonPage.checkAlertPopUp();	
	}

	/**
	 * This method verifies the logout functionality.
	 * @throws Exception
	 */
	public static void verifyLogOutFunctionality() throws Exception {
		OrderingBOHomePage.clickOnLogoutButton();
		OrderingBOHomePage.verifyLogoutSucessful();
		XcelerateHomePage.navigateToXcelerateWindow();
		XcelerateHomePage.signOutFromXcelerate();
	}
	
	/**
	 * This method clicks on the logout button.
	 * @latsModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnLogoutButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_LOGOUT_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_LOGOUT_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_LOGOUT_BUTTON_XPATH);		
		BrowserAction.click(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_LOGOUT_BUTTON_XPATH);
	}
	
	/**
	 * This method clicks on the logout button.
	 * @latsModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyLogoutSucessful() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_LOGOUT_MESSAGE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_LOGOUT_MESSAGE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_LOGOUT_MESSAGE_XPATH);		
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_LOGOUT_MESSAGE_XPATH), "You have been logged out of Ordering Maintenance", "Back office log off message not verified");
	}
	
	/**
	 * This method validates the labels of section in the side menu.
	 * If validation is successful it will be highlighted with green color if not then it will highlight the border in red color.
	 * @lastModified shisingh
	 * @throws Exception
	 */
	public static void boHomePageLabelValidation() throws Exception {
		/*
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_WELCOME_MESSAGE1_XPATH), "Internal Order Maintenance", "BO welcome message label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BACK_OFFICE_WELCOME_MESSAGE2_XPATH), "An Element Application", "BO welcome message label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_QUEUES_MENU_XPATH), "Queues", "Side menu Queues label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_EDIT_ORDER_MENU_XPATH), "Edit Order", "Side menu Edit Order label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_MENU_XPATH), "Manager Order Preferences", "Side menu Manager Order Preferences label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_CODES_TABLES_MENU_XPATH), "Codes Table".replaceAll(" ", "\n"), "Side menu Codes Tables label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_BATCH_PROCESSING_MENU_XPATH), "Batch Processing", "Side menu Batch Processing label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_BUSINESS_MAINTAINED_TABLES_MENU_XPATH), "Business Maintained Tables".replaceAll(" ", "\n"), "Side menu Business Maintained Tables label not matched");
		*/
	}
	
	public static void batchProcessingSideMenuOptionsLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BO_BATCH_PROCESSING_MENU_HEADING_CLASS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BO_BP_DRIVER_GROUP_MAINTENANCE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOHomePageEnum.ORDERING_BO_BP_DRIVER_GROUP_MAINTENANCE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOHomePageEnum.ORDERING_BO_BP_DRIVER_GROUP_MAINTENANCE_XPATH);
	}
	
	public static void batchProcessingSideMenuLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_BATCH_PROCESSING_MENU_HEADING_CLASS), "Batch Processing", "Batch Processing title in side menu did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_BP_BATCH_ORDERING_XPATH), "Batch Ordering", "Batch Ordering side menu option did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_BP_CREATE_DRIVER_GROUP_XPATH), "Create Driver Group", "Create Driver Group side menu option did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_BP_DRIVER_GROUP_MAINTENANCE_XPATH), "Driver Group Maintenance", "Driver Group Maintenance side menu option did not match with the expected string");
	}
	
	public static void editOrderSideMenuOptionsLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BO_EDIT_ORDER_MENU_HEADING_CLASS);
		BrowserVerify.verifyElementEnabled(OrderingBOHomePageEnum.ORDERING_BO_EDIT_ORDER_MENU_HEADING_CLASS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BO_EDIT_ORDER_MENU_HEADING_CLASS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BO_EO_ORDER_MAINTENANCE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BO_EO_ADD_UPFIT_PURCHASE_ORDER_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOHomePageEnum.ORDERING_BO_EO_INVOICE_ENTRY_XPATH);
	}
	
	public static void editOrderSideMenuLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_EDIT_ORDER_MENU_HEADING_CLASS), "Edit Order", "Edit Order title in side menu did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_EO_ORDER_MAINTENANCE_XPATH), "Order Maintenance", "Order Maintenance side menu option did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_EO_ADD_UPFIT_PURCHASE_ORDER_XPATH), "Add Upfit Purchase Order", "Add Upfit Purchase Order side menu option did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOHomePageEnum.ORDERING_BO_EO_INVOICE_ENTRY_XPATH), "Invoice Entry", "Invoice Entry side menu option did not match with the expected string");
	}

	/**
	 * This method approves all the order.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void approveAllTheOrder(String className) throws Throwable {
		int singleOrderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		for(int j=1; j<=singleOrderCount; ++j) {
			CommonPage.getElementOrderObject().setLogNumber(CommonPage.getTestData("LoggerLogNumber"+j));
			OrderingBOQueuePage.gotoCreatedOrder();
			OrderingBOOnOrderQueuePage.resolveSoftErrors();
			OrderingBOOnOrderQueuePage.approveOrderAndDownloadPDF(className);
			PDFReporter.takeExtraScreenshot();
			OrderingBOOrdMaintPage.goBackToQueuesPage();
		}
	}
	
	/**
	 * This method search and navigate to order which has status as MSO Received
	 * @throws Throwable 
	 * @lastModifiedBy djawale
	 */
	public static void searchAndNavigateToMSOReceivedOrder() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.gotoQueue("On-Order");
		OrderingBOOnOrderQueuePage.waitForOnOrderQueuePage();
		OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("logNumber");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method clicks on yes button of warning popup in BO.
	 * @throws Throwable 
	 * @lastModifiedBy skathule
	 */
	public static void warningPopUp() throws Exception {
		CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_WARNING_POP_UP_YES_BUTTON_XPATH);
	}
	
	/**
	 * This method waits for the back office home page to be loaded.
	 * @lastModifiedBy ksharma
	 * @throws Exception
	 */
	public static void orderingBOHomePageMinimise() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_MINUS);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_MINUS);
	}
	
	/**
	 * This method waits for the back office home page to be loaded.
	 * @lastModifiedBy ksharma
	 * @throws Exception
	 */	
	public static void navigateToPage() {
		String url=WebDriverAccess.getDriver().getCurrentUrl();
		try
		{
			URL url1 = new URL(url);
			String baseUrl = url1.getProtocol() + "://" + url1.getHost();
			String pageURL= baseUrl +"#vehicleConfig/fleetSpec/search";
			WebDriverAccess.getDriver().get(pageURL);
		}
		catch (MalformedURLException e)
		{
			System.out.println("Page not found");
		}        
	}
}
