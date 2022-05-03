package com.element.fleet.ordering.page;

import com.element.fleet.ordering.verification.BillingAndRegistrationTab;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.element.fleet.ordering.enums.OrderingBOChangeHistoryPageEnum;
import com.element.fleet.ordering.enums.OrderingBOOnOrderProjectPageEnum;
import com.element.fleet.ordering.enums.OrderingBOOrdMaintPageEnum;
import com.element.fleet.ordering.enums.OrderingBOQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingBillingAndRegistrationPageEnum;
import com.element.fleet.ordering.enums.OrderingHomePageEnum;
import com.element.fleet.ordering.enums.OrderingMyOrdersPageEnum;
import com.element.fleet.ordering.enums.OrderingSummaryPageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.element.fleet.ordering.verification.BillingAndRegistrationTab.Registration;
import com.element.fleet.ordering.verification.ElementOrder;
import com.element.fleet.ordering.verification.SummaryTab;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserAssert;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingSummaryPage {
	
	static String logNo = "";
	
	/**
	 * This method waits for the summary page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForSummaryPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_GENERAL_ORDER_SECTION_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_GENERAL_ORDER_SECTION_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingSummaryPageEnum.ORDERING_SUMMARY_GENERAL_ORDER_SECTION_XPATH);
	}
	
	/**
	 * This verifies that all the section is present on the summary page.
	 * Note: It just verifies the heading of the section.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyAllSectionPresentOnSummaryPage() throws Exception {		
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_GENERAL_ORDER_HEADING_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_GENERAL_ORDER_HEADING_CSS), "General Order", "General Order section heading mismatched");
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_DRIVER_HEADING_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_DRIVER_HEADING_CSS), "Driver", "Driver section heading mismatched");
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_NEW_VEHICLE_SPECIFICATION_HEADING_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_NEW_VEHICLE_SPECIFICATION_HEADING_CSS), "New Vehicle Specification", "New Vehicle Specification section heading mismatched");
		/*
		if(Objects.isNull(CommonPage.getTestData("DealerSelectionOption"))) {
			System.out.println("Dealer selection was skipped. Hence section on summary page.");
		} else if(CommonPage.getTestData("OrderType").equals("Factory")&&!(CommonPage.getTestData("DealerSelectionOption").equals("Request a New Dealer"))) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_DELIVERING_DEALER_HEADING_CSS);
			CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_DELIVERING_DEALER_HEADING_CSS), "Delivering Dealer", "Delivering Dealer section heading mismatched");
		} else {
			BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_DELIVERING_DEALER_HEADING_CSS);
			CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_DELIVERING_DEALER_HEADING_CSS), "Requested Delivering Dealer", "Requested Delivering Dealer section heading mismatched");
		}
		*/
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_BILLING_HEADING_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_BILLING_HEADING_CSS), "Billing", "Billing section heading mismatched");
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_LICENSE_HEADING_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_LICENSE_HEADING_CSS), "Title & Registration", "Title & Registration section heading mismatched");
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_INSURANCE_HEADING_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_INSURANCE_HEADING_CSS), "Insurance", "Insurance section heading mismatched");
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_STANDARD_EQUIPMENT_HEADING_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_STANDARD_EQUIPMENT_HEADING_CSS), "Standard Equipment", "Standard Equipment section heading mismatched");
	}
	
	/**
	 * This method performs pre summary page actions summary page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void preSummaryPageActions(String className) throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoValidationError();
		OrderingSummaryPage.waitForSummaryPage();
		OrderingSummaryPage.verifyAllSectionPresentOnSummaryPage();
		OrderingSummaryPage.clickExportButton("Pre", className);
	}
	
	/**
	 * This method performs post summary page actions summary page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void postSummaryPageActions(String className) throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		OrderingSummaryPage.waitForPopUpResultBox();
		OrderingSummaryPage.verifySuccessfulSubmissionPopUp();
		OrderingSummaryPage.waitForSummaryPage();
		OrderingSummaryPage.verifyAllSectionPresentOnSummaryPage();
		OrderingSummaryPage.clickExportButton("Post", className);
	}

	/**
	 * This method clicks on the submit button.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickSubmit() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_BUTTON_ID);
		BrowserAction.click(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_BUTTON_ID);
	}

	/**
	 * This method click on ok button on the confirm submission pop up.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void confirmSubmit() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_OK_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_OK_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_OK_XPATH);
		BrowserAction.click(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_OK_XPATH);
	}

	public static void verifyTitleTabDisplayed() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLEREGISTRATION_TAB_CSS);
		BrowserAction.click(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLEREGISTRATION_TAB_CSS);
		BrowserAction.hoverOverElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_STANDARD_EQUPMENT_TAB_ID);
	}

	/**
	 * This method clicks on the export pdf button on the summary page when Y is mentioned on the pre or post pdfF column.
	 * Y->Download PDF
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void clickExportButton(String section, String className) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String dateFormat = "-" + LocalDate.now().format(formatter);
		switch(section) {
			case "Pre":				
				if((Objects.isNull(CommonPage.getTestData("PrePDF")))?false:(CommonPage.getTestData("PrePDF").equals("Y")?true:false)) {
					BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_PRE_EXPORT_PDF_CSS);
					BrowserVerify.verifyElementIsPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_PRE_EXPORT_PDF_CSS);
					BrowserVerify.verifyElementEnabled(OrderingSummaryPageEnum.ORDERING_SUMMARY_PRE_EXPORT_PDF_CSS);
					CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
					BrowserAction.click(OrderingSummaryPageEnum.ORDERING_SUMMARY_PRE_EXPORT_PDF_CSS);
					OrderingCommonPage.checkAlertPopUp();
					CommonPage.getPDFText(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), WebDriverAccess.getDriver());
					CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), section, className);
					OrderingCommonPage.verifyNoAlertPopUpDispalyed();
				} else {
					System.out.println("Pre PDF not required");
				}
				break;
			case "Post":
				if((Objects.isNull(CommonPage.getTestData("PostPDF")))?false:(CommonPage.getTestData("PostPDF").equals("Y")?true:false)) {
					BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_POST_EXPORT_PDF_CSS);
					BrowserVerify.verifyElementIsPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_POST_EXPORT_PDF_CSS);
					BrowserVerify.verifyElementEnabled(OrderingSummaryPageEnum.ORDERING_SUMMARY_POST_EXPORT_PDF_CSS);
					CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
					BrowserAction.click(OrderingSummaryPageEnum.ORDERING_SUMMARY_POST_EXPORT_PDF_CSS);
					OrderingCommonPage.checkAlertPopUp();
					CommonPage.getPDFText(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), WebDriverAccess.getDriver());
					CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), section, className);
					OrderingCommonPage.verifyNoAlertPopUpDispalyed();
				} else {
					System.out.println("Post PDF not required");
				}
				break;
			case "PO":
				if((Objects.isNull(CommonPage.getTestData("PoPDF")))?false:(CommonPage.getTestData("PoPDF").equals("Y")?true:false)) {
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_PDF_BTN_ID);
					BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_PDF_BTN_ID);
					BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_PDF_BTN_ID);
					CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
					BrowserAction.click(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_PDF_BTN_ID);
					OrderingCommonPage.checkAlertPopUp();
					CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), section, className);
					OrderingCommonPage.verifyNoAlertPopUpDispalyed();
				} else {
					System.out.println("PO PDF not required");
				}
				break;
			case "Upfit":
				if((Objects.isNull(CommonPage.getTestData("UpfitPDF")))?false:(CommonPage.getTestData("UpfitPDF").equals("Y")?true:false)) {
					OrderingBOOnOrderQueuePage.orderDetailsPageSectionClick("Upfit");
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFITS_CONTAINER_ID);
					BrowserVerify.verifyElementIsPresent(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFITS_CONTAINER_ID);
					BrowserVerify.verifyElementEnabled(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFITS_CONTAINER_ID);
					List<WebElement> addedUpfits = BrowserAction.getElements(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFITS_LIST_CSS);
					String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
			                							+ "var elementTop = arguments[0].getBoundingClientRect().top;"
			                							+ "window.scrollBy(0, elementTop-(viewPortHeight/2));";
					for(WebElement addedUpfit: addedUpfits) {
						((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript(scrollElementIntoMiddle, addedUpfit);
						CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
						new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(addedUpfit)).click();
						OrderingBOOrdMaintPage.moveToUpfitWindow();
						//new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(addedUpfit.findElement(By.xpath(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFITS_LIST_BODY_XPATH.getValue())).findElement(By.xpath(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFITTAB_ADDED_UPFITS_LIST_PDF_BUTTON_XPATH.getValue())))).click();
						CommonPage.clickElement(OrderingBOOrdMaintPageEnum.ORDERING_BO_ORD_MAINT_UPFIT_PO_CSS);
						OrderingCommonPage.checkAlertPopUp();
						CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), section, className);
						OrderingCommonPage.verifyNoAlertPopUpDispalyed();
						OrderingBOOrdMaintPage.navigateToParentWindow();
					}
					
				} else {
					System.out.println("Upfit PDF not required");
				}
				break;
			case "FleetSpec":
				if((Objects.isNull(CommonPage.getTestData("FleetSpecPDF")))?false:(CommonPage.getTestData("FleetSpecPDF").equals("Y")?true:false)) {
					OrderingVehicleConfigFleetSpecsPage.waitForFleetSpecActionOptions();
					CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
					OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecActionOptions("Export to PDF/Print");	
					OrderingVehicleConfigFleetSpecsPage.waitForFleetSpecDownloadPDFPopUp();
					OrderingVehicleConfigFleetSpecsPage.verifyFleetSpecPDFCompleted();
					CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), section, className);
				} else {
					System.out.println("Fleet Spec PDF not required");
				}
				break;
			case "WIP":
				BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_EXPORT_BUTTON_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_EXPORT_BUTTON_CSS);
				BrowserVerify.verifyElementEnabled(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_EXPORT_BUTTON_CSS);
				CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
				BrowserAction.click(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_EXPORT_BUTTON_CSS);
				OrderingCommonPage.checkAlertPopUp();
				CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Work In Process Orders-queue");
				break;
			case "Quote-Queue":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORTBUTTON_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORTBUTTON_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORTBUTTON_XPATH);
				CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
				BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORTBUTTON_XPATH);
				OrderingCommonPage.checkAlertPopUp();
				CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Quote-queue" + dateFormat);
				break;
			case "UpfitSpec":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_UPFIT_SPEC_EXPORT_BUTTON_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_UPFIT_SPEC_EXPORT_BUTTON_CSS);
				BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_UPFIT_SPEC_EXPORT_BUTTON_CSS);
				CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
				BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_UPFIT_SPEC_EXPORT_BUTTON_CSS);
				OrderingCommonPage.checkAlertPopUp();
				CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Upfit Spec-Queue" + dateFormat);
				break;
			case "Project Queue":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_EXPORT_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_EXPORT_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_EXPORT_XPATH);
				CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
				BrowserAction.click(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECT_FILTER_BUTTON_PROJECT_EXPORT_XPATH);
				OrderingCommonPage.checkAlertPopUp();
				CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Project-queue" + dateFormat);
				break;
			case "MyOrders":
				BrowserWait.waitUntilElementIsDisplayed(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_EXPORT_BUTTON_CSS);
				BrowserVerify.verifyElementIsPresent(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_EXPORT_BUTTON_CSS);
				BrowserVerify.verifyElementEnabled(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_EXPORT_BUTTON_CSS);
				CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
				BrowserAction.click(OrderingMyOrdersPageEnum.ORDERING_MY_ORDERS_EXPORT_BUTTON_CSS);
				OrderingCommonPage.checkAlertPopUp();
				CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), section, className);
				break;
			case "BillingQueue":
				exportAndMoveCSV(section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Billing-queue" + dateFormat);
				break;
			case "AcknowledgmentQueue":
				exportAndMoveCSV(section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Acknowledgment-queue" + dateFormat);
				break;
			case "CreditQueue":
				exportAndMoveCSV(section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Credit-queue" + dateFormat);
				break;
			case "DIOQueue":
				exportAndMoveCSV(section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Dealer Installed Options-Queue" + dateFormat);
				break;
			case "DealerQueue":
				exportAndMoveCSV(section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Dealer-queue" + dateFormat);
				break;
			case "QuoteQueue":
				exportAndMoveCSV(section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Quote-queue" + dateFormat);
				break;
			case "TitleRegQueue":
				exportAndMoveCSV(section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Title & Registration-queue" + dateFormat);
				break;
			case "UpfitQueue":
				exportAndMoveCSV(section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Upfit-Queue" + dateFormat);
				break;
			case "InsuranceQueue":
				exportAndMoveCSV(section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Insurance-queue" + dateFormat);
				break;
			case "ChangeHistory":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_PAGE_EXPORT_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_PAGE_EXPORT_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_PAGE_EXPORT_XPATH);
				CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
				BrowserAction.click(OrderingBOChangeHistoryPageEnum.ORDERING_BO_CHANGE_HISTORY_PAGE_EXPORT_XPATH);
				OrderingCommonPage.checkAlertPopUp();
				CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "history-queue");
				break;
			case "Batch":
				BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDERING_HOME_SIDEMENU_BATCH_SELECT_TEMPLATE_DOWNLOAD_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDERING_HOME_SIDEMENU_BATCH_SELECT_TEMPLATE_DOWNLOAD_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDERING_HOME_SIDEMENU_BATCH_SELECT_TEMPLATE_DOWNLOAD_XPATH);
				CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
				BrowserAction.click(OrderingHomePageEnum.ORDERING_HOME_SIDEMENU_BATCH_SELECT_TEMPLATE_DOWNLOAD_XPATH);
				OrderingCommonPage.checkAlertPopUp();
				CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\");
				break;
			case "CustomerDeliveringDealerAssignmentRules":
				exportAndMoveCSV(section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "Customer Delivering Dealer Assignment Rules-queue");
				break;
			case "OnOrderQueue":
				exportAndMoveCSV(section, className);
				OrderingBOOnOrderQueuePage.setCSVToMap(className + "_" + section + "_" + "On Order-queue");
				break;	
			default : throw new InvalidSwitchCaseException(section + " invalid option entered.");
		}
		
	}

	/**
	 * This method waits for pop up box to be displayed.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForPopUpResultBox() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), 5).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMISSION_POP_UP_CSS.getValue()), 1));
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMISSION_POP_UP_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMISSION_POP_UP_CSS);
		BrowserVerify.verifyElementEnabled(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMISSION_POP_UP_CSS);
	}
	
	/**
	 * This method verify the weather the order is successfully submitted or not.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifySuccessfulSubmissionPopUp() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMISSION_POP_UP_CSS.getValue()), 1));
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMISSION_POP_UP_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMISSION_POP_UP_CSS);
		BrowserVerify.verifyElementEnabled(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMISSION_POP_UP_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMISSION_POP_UP_HEADER_CSS), "Successfully Submitted", "Unable to submit error");
		System.out.println("Log number: " + BrowserAccess.getElementText(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_CONFIRMATION_POP_LOG_NUMBER_ID));
		CommonPage.getElementOrderObject().setLogNumber(BrowserAccess.getElementText(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_CONFIRMATION_POP_LOG_NUMBER_ID));
		BrowserAction.click(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_CONFIRMATION_CLOSE_ID);
	}

	public static void closeOrderSummary() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_POST_CLOSE_CSS);
		BrowserAction.click(OrderingSummaryPageEnum.ORDERING_SUMMARY_POST_CLOSE_CSS);
	}
	
	public static void verifyTitleRegistrationAndLeinholder() throws Exception {
		BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_XPATH)
		.findElement(By.cssSelector(OrderingSummaryPageEnum.ORDERING_SUMMARY_SECTION_EXPAND_BUTTON_CSS.getValue()))
		.click();
		
		String titleOwnerName = BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_XPATH)
				.findElement(By.xpath(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_OWNER_NAME_XPATH.getValue()))
				.getText();
		String titleOwnerAddressLine = BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_XPATH)
				.findElement(By.xpath(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_OWNER_ADDRESS_LINE_XPATH.getValue()))
				.getText();
		String registeredOwnerName = BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_XPATH)
				.findElement(By.xpath(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTERED_OWNER_NAME_XPATH.getValue()))
				.getText();
		String registeredOwnerAddressLine = BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_XPATH)
				.findElement(By.xpath(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTERED_OWNER_ADDRESS_LINE_XPATH.getValue()))
				.getText();
		String leinholderName = BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_XPATH)
				.findElement(By.xpath(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_LEINHOLDER_NAME_XPATH.getValue()))
				.getText();
		String leinholderAddressLine = BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_XPATH)
				.findElement(By.xpath(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_LEINHOLDER_ADDRESS_LINE_XPATH.getValue()))
				.getText();
		Assert.assertEquals(CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getRegistrationSectionObject().getTitleOwnerName(), titleOwnerName);
		Assert.assertEquals(CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getRegistrationSectionObject().getTitleOwnerAddressLine1()
				+" "+CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getRegistrationSectionObject().getTitleOwnerAddressLine2(), titleOwnerAddressLine);
		Assert.assertEquals(CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getRegistrationSectionObject().getRegisteredOwnerName(), registeredOwnerName);
		Assert.assertEquals(CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getRegistrationSectionObject().getRegisteredOwnerAddressLine1()
				+" "+CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getRegistrationSectionObject().getRegisteredOwnerAddressLine2(), registeredOwnerAddressLine);
		Assert.assertEquals(CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getRegistrationSectionObject().getLeinholderName(), leinholderName);
		Assert.assertEquals(CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getRegistrationSectionObject().getLeinholderAddressLine1()
				+" "+CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getRegistrationSectionObject().getLeinholderAddressLine2(), leinholderAddressLine);
		
	}
	
	/**
	 * This method asserts summary page TitleName section matches to Title Section on Billing Registration Page.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyTitleDetailswithCodes(ElementOrder orderTabsData) throws Exception  {
		verifyTitleTabDisplayed();
		BillingAndRegistrationTab.TitleOwner titleOwner = orderTabsData.getBillingAndRegistrationTabObject().getTitleOwnerObject();
		Assert.assertEquals(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_NAME_XPATH).getText().trim(),titleOwner.getTitleName());
		Assert.assertEquals(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_OWNER_XPATH).getText().trim(),titleOwner.getTitleOwner().trim());
		Assert.assertEquals(CommonPage.verifyElementTextPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_FEDERALID_XPATH).trim(),titleOwner.getTitleFederalID());
		Assert.assertEquals(CommonPage.verifyElementTextPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_STATEID_XPATH).trim(),titleOwner.getTitlestateID());
		Assert.assertEquals(CommonPage.verifyElementTextPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_TAXEXEMPT_XPATH).trim(),titleOwner.getTitleTaxExempt());
	}

	/**
	 * This method asserts summary page RegistrationName section matches to Registration Section on Billing Registration Page.
	 * @lastModifiedBy skahtule
	 * @throws Exception
	 */
	public static void verifyRegistrationDetailswithCodes(ElementOrder orderTabsData) throws Exception {
		BillingAndRegistrationTab.RegisteredOwner registeredOwner = orderTabsData.getBillingAndRegistrationTabObject().getRegisteredOwnerObject();
		Assert.assertEquals(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTRATION_NAME_XPATH).getText().trim(),registeredOwner.getRegisteredName());
		Assert.assertEquals(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTRATION_OWNER_XPATH).getText().trim(),registeredOwner.getRegisteredOwner().trim());
		Assert.assertEquals(CommonPage.verifyElementTextPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTRATION_FEDERALID_XPATH).trim(),registeredOwner.getRegisteredFederalID());
		Assert.assertEquals(CommonPage.verifyElementTextPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTRATION_STATEID_XPATH).trim(),registeredOwner.getRegisteredStateID());

	}

	/**
	 * This method asserts summary page Lienholder section matches to Lienholder Section on Billing Registration Page.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyLienholderDetailswithCodes(ElementOrder orderTabsData) throws Exception {
		BillingAndRegistrationTab.LienHolder lienHolder = orderTabsData.getBillingAndRegistrationTabObject().getLienHolderObject();
		Assert.assertEquals(CommonPage.verifyElementTextPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_LIENHOLDER_FEDERALID_XPATH).trim(),lienHolder.getLienHolderFederalID());
		Assert.assertEquals(CommonPage.verifyElementTextPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_LIENHOLDER_STATEID_XPATH).trim(),lienHolder.getLienHolderStateID());
		Assert.assertEquals(CommonPage.verifyElementTextPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_LIENHOLDER_ADDRESS_XPATH).trim(),lienHolder.getLienHolderAddress().trim());
	}

    /**
     * This method asserts summary page Vehicle Text matches to Vehicle Text on Billing Registration Page.
     * @lastModifiedBy skathule
     * @throws Exception
     */
    public static void verifyVehicleRegisteredTextmatcheswithBillingPageText(ElementOrder orderTabsData) throws Exception  {
    	Registration registrationDetails = orderTabsData.getBillingAndRegistrationTabObject().getRegistrationSectionObject();
    	Assert.assertEquals(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_VEHICLETEXT_XPATH).getText().trim(),registrationDetails.getVehicleRegisteredText().trim());
    }
    
    /**
     * This method verifies the Lienholders code labels are present in Bold or not
     * @lastModifiedBy skathule
     * @throws Exception
     */
    public static void verifyLienHolderCodesAreInBold() throws Exception {
		CommonPage.verifyElementIsPresentInBold(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_LIENHOLDER_SECTION_LIENHOLDER_FEDERALID_LABEL_XPATH);
		CommonPage.verifyElementIsPresentInBold(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_LIENHOLDER_SECTION_LIENHOLDER_STATEID_LABEL_XPATH);
	}
	
    /**
     * This method verifies the Registration code labels are present in Bold or not
     * @lastModifiedBy skathule
     * @throws Exception
     */
	public static void verifyRegistrationCodesAreInBold() throws Exception {
		CommonPage.verifyElementIsPresentInBold(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTRATION_FEDERALID_LABEL_XPATH);
		CommonPage.verifyElementIsPresentInBold(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_REGISTRATION_STATEID_LABEL_XPATH);
	}
	
	/**
     * This method verifies the Title code labels are present in Bold or not
     * @lastModifiedBy skathule
     * @throws Exception
     */
	public static void verifyTitleCodesAreInBold() throws Exception {
		CommonPage.verifyElementIsPresentInBold(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_FEDERALID_LABEL_XPATH);
		CommonPage.verifyElementIsPresentInBold(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_STATEID_LABEL_XPATH);
		CommonPage.verifyElementIsPresentInBold(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_REGISTRATION_SECTION_TITLE_TAXEXEMPT_LABEL_XPATH);
	}

    /**
     * This method will submit order & wait on Summary page.
     * @lastModifiedBy sbhosale
     * @throws Exception
     */
    public static void submitOrder() throws Exception {
    	clickSubmit();
    	confirmSubmit();
    	verifySuccessfulSubmissionPopUp();
    	waitForSummaryPage();
    }
	
	/**
	 * This method verifies all the details from title and registration Page.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyAllTheDetailsOfTitleRegistrationPage() throws Exception {
		verifyTitleDetailswithCodes(CommonPage.getElementOrderObject());
		verifyRegistrationDetailswithCodes(CommonPage.getElementOrderObject());
		verifyVehicleRegisteredTextmatcheswithBillingPageText(CommonPage.getElementOrderObject());
		verifyTitleCodesAreInBold();
		verifyRegistrationCodesAreInBold();
		List<WebElement> tLRSections = BrowserAction.getElements(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TLANDR_SECTIONS_HEADER_XPATH);
		for(WebElement tLRSection: tLRSections) {
			String tLRSectionText = tLRSection.getText();
			if(tLRSectionText.equals("Lienholder Information")) {
				verifyLienholderDetailswithCodes(CommonPage.getElementOrderObject());
				verifyLienHolderCodesAreInBold();
			}
		}
	}
	
	/**
	 * This method performs post summary page actions summary page to load.
	 * @lastModifiedBy akshay kandkonde
	 * @throws Exception
	 */
	public static void postSummaryPageAction() throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		OrderingSummaryPage.waitForPopUpResultBox();
		OrderingSummaryPage.verifySuccessfulSubmissionPopUp();
		OrderingSummaryPage.waitForSummaryPage();
	}

	/**
	 * Common method to export and move the export to target folder
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void exportAndMoveCSV(String section, String className) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORT_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORT_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORT_BUTTON_CSS);
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		BrowserAction.click(OrderingBOQueuePageEnum.ORDERING_BO_QUEUES_EXPORT_BUTTON_CSS);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home")+"\\Downloads\\"), section, className);
	}
	
	/**
	 * This method clicks cancel button
	 * @lastModifiedBy Hector Jimenez
	 * @throws Exception
	 */
	public static void clickCancelButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_CANCEL_BUTTON_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_CANCEL_BUTTON_XPATH);
		BrowserAction.click(OrderingSummaryPageEnum.ORDERING_SUMMARY_CANCEL_BUTTON_XPATH);
	}
	

	/**
	 * This method verifies DIO details on summary page
	 * @throws Exception
	 * @lastModifiedBy skathule
	 */
	public static void verifyDIODetailsOnSummaryPage() throws Exception {
		OrderingSummaryPage.summaryPageDIOSection();
		System.out.println(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_DIO_SECTION_OPTIONCODE_XPATH).getText());
		Assert.assertEquals(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_DIO_SECTION_OPTIONCODE_XPATH).getText(), CommonPage.getTestData("OptionCode"));
		Assert.assertEquals(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_DIO_SECTION_DESCRIPTION_XPATH).getText(), CommonPage.getTestData("OptionDescription"));
		Assert.assertEquals(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_DIO_SECTION_MAXPRICE_XPATH).getText(), CommonPage.getTestData("AdhocDioPrice"));
	}
	
	/**
	 * This method verifies DIO details after post summary page
	 * @throws Exception
	 * @lastModifiedBy skathule
	 */
	public static void verifyDIODetailsOnPostSummaryPage() throws Exception {
	    OrderingSummaryPage.summaryPageDIOSection();
		Assert.assertEquals(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_DIO_SECTION_OPTIONCODE_XPATH).getText(), CommonPage.getTestData("OptionCode"));
		Assert.assertEquals(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_DIO_SECTION_DESCRIPTION_XPATH).getText(), CommonPage.getTestData("OptionDescription"));
	}
	
	/**
	 * This method navigate to the summary page DIO section.
	 * @throws Exception
	 * @lastModifiedBy skathule
	 */
	public static void summaryPageDIOSection() throws Exception {
		CommonPage.clickElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_DIO_SECTION_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_DIO_SECTION_TABLE_XPATH);
	}	
	
	/**
	 * This method validate the T&L Section information in PDF
	 * @throws Exception
	 * @lastModifiedBy skathule
	 */
	public static void verifyTAndLDetailsInPDF() throws Exception {
		SummaryTab.PDF pdfData = CommonPage.getElementOrderObject().getSummaryTabObject().getpdfObject();
		String [] pdf=pdfData.getpdfText().split("Title & License");
		String [] TLSectionPDF= pdf[1].split("Insurance");
		System.out.println("Title & License " +TLSectionPDF[0]);
		List<WebElement> sectionEle = BrowserAction.getElements(OrderingSummaryPageEnum.ORDERING_SUMMARY_TITLE_AND_LICENSE_SECTIONS_XPATH);
		for(WebElement TL : sectionEle) {
			String TLDetails=TL.getAttribute("textContent").trim().replaceAll(",$", "");
			if(!(TLDetails.isEmpty())) {
				if(TLSectionPDF[0].contains(TLDetails)) {
					System.out.println(TLDetails+ "present in pdf file");
				}
				else {
					throw new OrderingErrorOccured(TLDetails +"is not Present in The PDF");
				}
			}
		}
	}

	/**
	 * This method verifies the log number present or not in Pre PDF
	 * @throws Exception
	 * @lastModifiedBy skathule
	 */
	public static void verifyLogNumberInPrePDF() throws Exception {
		SummaryTab.PDF pdfData = CommonPage.getElementOrderObject().getSummaryTabObject().getpdfObject();
		String [] pdf=pdfData.getpdfText().split("Title & License");
		Assert.assertFalse(pdf[0].contains("Log Number"));
		System.out.println("Log number is not present in the Pre Summary PDF");
	}

	/**
	 * This method verifies the log number present or not  in Post summary PDF
	 * @throws Exception
	 * @lastModifiedBy skathule
	 */
	public static void verifyLogNumberInPostPDF() throws Exception {
		SummaryTab.PDF pdfData = CommonPage.getElementOrderObject().getSummaryTabObject().getpdfObject();
		String [] pdf=pdfData.getpdfText().split("Title & License");
		Assert.assertTrue(pdf[0].contains("Log Number"));
		System.out.println("Log number is present in the Post Summary PDF");
	}
	
	/**
	 * This method opens new vehicle specification tab details on summary page
	 * @throws Exception
	 * @lastModifiedBy ksharma
	 */
	public static void verifyVehicleSpecificationOnSummaryPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_NEW_VEHICLE_SPECIFICATION_HEADING_CSS);
		BrowserAssert.assertElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_NEW_VEHICLE_SPECIFICATION_HEADING_CSS); 
		BrowserAction.click(OrderingSummaryPageEnum.ORDERING_SUMMARY_NEW_VEHICLE_SPECIFICATION_HEADING_CSS);		
		((JavascriptExecutor)WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", BrowserAccess.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_NEW_VEHICLE_SPECIFICATION_TABLE_ID));
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_NEW_VEHICLE_SPECIFICATION_TABLE_ID);
	}
	
	/**
	 * This method verifies headings list of new vehicle specification tab in summary page 
	 * @lastModifiedBy ksharma
	 * @param colName
	 * @throws Exception
	 */
	public static void verifyColumnHeadings() throws Exception {
		List<WebElement> columnName = BrowserAccess.getElements(OrderingSummaryPageEnum.ORDERING_SUMMARY_NEW_VEHICLE_SPECIFICATION_TABLE_HEADER_XPATH);
		ArrayList<String> columnNameList = new ArrayList<String>();
		for (WebElement tdElement : columnName ) {
			columnNameList.add(tdElement.getText().trim());
			StringBuilder sbString = new StringBuilder("");
			for(String language : columnNameList){               
				//append ArrayList element followed by comma
				sbString.append(language).append(",");
			}
			String strList = sbString.toString();
			if( strList.length() > 0 )
				strList = strList.substring(0, strList.length() - 1);
			((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", tdElement);
			System.out.println("columnName is = " +strList);
			BrowserWait.waitUntilElementIsDisplayed(tdElement);
		}
	}

	/**
	 * This method verifies driver price if dpo is selected in summary page 
	 * @lastModifiedBy ksharma
	 * @param colName
	 * @return 
	 * @throws Exception
	 */
	public static void verifyDriverPrice() throws Exception {			
		WebElement table = BrowserAccess.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_NEW_VEHICLE_SPECIFICATION_TABLE_BODY_XPATH);  
		List<WebElement> allRows = table.findElements(By.tagName("tr"));  
		for (WebElement row : allRows) { 
			List<WebElement> cells = row.findElements(By.tagName("td")); 
			for (WebElement cell : cells) { 
				System.out.println(cell.getText());
				if(cell.getText().trim().contentEquals("Driver Paid")) {					
					System.out.println("DPO is selected");
				} 
			}
			
	}
}
	
	/**
	 * This method reads and stores log number that is displayed after order is submitted
	 * @lastModifiedBy DBhagat
	 * @throws Exception
	 */
	public static String getLogNumber() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_CONFIRMATION_POP_LOG_NUMBER_ID);
		logNo = BrowserAccess.getElementText(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_CONFIRMATION_POP_LOG_NUMBER_ID);
		System.out.println("\n------------------------\nlog number = " + logNo + "\n ----------------------------");
		if (!logNo.equals(null))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
			return logNo;
	} 
}