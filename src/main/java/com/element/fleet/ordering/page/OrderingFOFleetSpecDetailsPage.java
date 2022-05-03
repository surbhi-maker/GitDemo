package com.element.fleet.ordering.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingFOFleetSpecDetailsPageEnum;
import com.element.fleet.ordering.enums.OrderingVehicleConfigFleetSpecsPageEnum;
import com.element.fleet.ordering.enums.OrderingVehiclePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.element.fleet.rainbowplus.common.KeyPressEvents;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingFOFleetSpecDetailsPage {

	/**
	 * This method verifies Retail only flag on Fleet details page. 
	 * @lastModifiedBy bshah
	 */
	public static void verifyretailonlyflagondetailspage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_MAKE_MODEL_TRIM_DATA_XPATH);
		List<WebElement> results = BrowserAction.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_MAKE_MODEL_TRIM_DATA_XPATH);
		if (results.get(1).getText().contains("(Retail Only)")) {
			CommonPage.getElementOrderObject().getVehicleTabObject().setretailonlyflag(true);
		} else {
			CommonPage.getElementOrderObject().getVehicleTabObject().setretailonlyflag(false);
		}
		System.out.println(
				"Retail Only Flag:" + CommonPage.getElementOrderObject().getVehicleTabObject().getretailonlyflag());
		assert CommonPage.getElementOrderObject().getVehicleTabObject().getretailonlyflag();
		CommonPage.jsGreenBorderHighlighter(results.get(1));
	}
	
	/**
	 * This method verifies spec header on spec details page while creating a spec. 
	 * @lastModifiedBy bshah
	 */
	public static void verifyFleetspecdetailheader() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_CLIENT_NO_ID);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_CLIENT_NO_ID);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_CLIENT_NO_ID));
		if (Objects.isNull(CommonPage.getTestData("ClientNumber"))) {
			throw new OrderingErrorOccured("Client number can't be null");
		} else {
			assert CommonPage.getTestData("ClientNumber").equals(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_CLIENT_NO_ID).getText());
		}
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_CLIENT_NAME_ID);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_CLIENT_NAME_ID);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_CLIENT_NAME_ID));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_INITIAL_ODERR_DATE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_INITIAL_ODERR_DATE_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_INITIAL_ODERR_DATE_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_START_OF_PRODUCTION_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_START_OF_PRODUCTION_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_START_OF_PRODUCTION_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_ORDERS_DUE_AT_ELEMENT_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_ORDERS_DUE_AT_ELEMENT_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_ORDERS_DUE_AT_ELEMENT_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_LAST_DAY_TRANSMIT_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_LAST_DAY_TRANSMIT_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_LAST_DAY_TRANSMIT_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_END_OF_PRODUCTION_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_END_OF_PRODUCTION_XPATH);	
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_END_OF_PRODUCTION_XPATH));
	}
	
	/**
	 * This method verifies spec header on spec details page on existing spec. 
	 * @lastModifiedBy bshah
	 */
	public static void verifyFleetspecdetailheaderexistingspec() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_PRIMARY_CLIENT_NO_CLASS);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_PRIMARY_CLIENT_NO_CLASS);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_PRIMARY_CLIENT_NO_CLASS));
		if(Objects.isNull(CommonPage.getTestData("ClientNumber"))) {
			throw new OrderingErrorOccured("Client number can't be null");
		} 
		else { 
			assert CommonPage.getTestData("ClientNumber").equals(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_HEADER_PRIMARY_CLIENT_NO_CLASS).getText());
		}
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_FLEETSPEC_ID_NAME_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_FLEETSPEC_ID_NAME_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_FLEETSPEC_ID_NAME_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_FLEETSPEC_STATUS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_FLEETSPEC_STATUS_XPATH);
		BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_FLEETSPEC_STATUS_XPATH).getText().contains("Work in Progress");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_FLEETSPEC_STATUS_XPATH));
	}
	
	/**
	 * This method verifies summary widget various elements on spec details page 
	 * @lastModifiedBy bshah
	 */
	public static void verifyavailabilityofelementonSummaryWidget(Object locator, int index) throws Exception {
		List<WebElement> results = BrowserAction.getElements(locator);
		assert results.get(index).isDisplayed();
		CommonPage.jsGreenBorderHighlighter(results.get(index));
	}

	/**
	 * This method verifies summary widget on spec details page 
	 * @lastModifiedBy bshah
	 */
	public static void verifySummarywidgetondetailspage(int index) throws Exception {
		OrderingFOFleetSpecDetailsPage.verifyavailabilityofelementonSummaryWidget(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_BASE_PRICE_LABEL_XPATH,index);
		OrderingFOFleetSpecDetailsPage.verifyavailabilityofelementonSummaryWidget(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_DESTINATION_FEE_LABEL_XPATH,index);
		OrderingFOFleetSpecDetailsPage.verifyavailabilityofelementonSummaryWidget(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_OPTION_COST_LABEL_XPATH,index);
		OrderingFOFleetSpecDetailsPage.verifyavailabilityofelementonSummaryWidget(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_SUB_TOTAL_1_PRICE_LABEL_XPATH,index);
		OrderingFOFleetSpecDetailsPage.verifyavailabilityofelementonSummaryWidget(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_TOTAL_PRICE_LABEL_CSS,index);
	}

	/**
	 * This method verifies summary widget invoice tab verification on spec details page. 
	 * @lastModifiedBy bshah
	 */
	public static void verifyInvoiceTabSummaryWidget() throws Exception {
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_INVOICE_TAB_ID));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_INVOICE_TAB_ID);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_INVOICE_TAB_ID);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_BASE_INVOICE_PRICE_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_OPTION_COST_INVOICE_PRICE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_OPTION_COST_INVOICE_PRICE_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_OPTION_COST_INVOICE_PRICE_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_SUB_TOTAL_1_INVOICE_PRICE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_SUB_TOTAL_1_INVOICE_PRICE_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_SUB_TOTAL_1_INVOICE_PRICE_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_TOTAL_INVOICE_PRICE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_TOTAL_INVOICE_PRICE_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_TOTAL_INVOICE_PRICE_XPATH));
		OrderingFOFleetSpecDetailsPage.verifySummarywidgetondetailspage(1);
	}

	/**
	 * This method verifies summary widget MSRP tab verification on spec details page. 
	 * @lastModifiedBy bshah
	 */
	public static void verifyMSRPtabsummarywidget() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_MSRP_TAB_ID);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_MSRP_TAB_ID);
		BrowserAction.click(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_MSRP_TAB_ID);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_MSRP_TAB_ID));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_OPTION_COST_MSRP_PRICE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_OPTION_COST_MSRP_PRICE_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_OPTION_COST_MSRP_PRICE_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_SUB_TOTAL_1_MSRP_PRICE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_SUB_TOTAL_1_MSRP_PRICE_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_SUB_TOTAL_1_MSRP_PRICE_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_TOTAL_MSRP_PRICE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_TOTAL_MSRP_PRICE_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_TOTAL_MSRP_PRICE_XPATH));
		OrderingFOFleetSpecDetailsPage.verifySummarywidgetondetailspage(2);
		BrowserAction.click(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_INVOICE_TAB_ID);
	}
	
	/**
	 * This method verifies click movement on geolocation tab on spec details page. 
	 * @lastModifiedBy bshah
	 */
	public static void clickOnGEOLocationtab() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_TAB_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_TAB_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_TAB_XPATH));
		BrowserAction.click(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_TAB_XPATH);
	}

	/**
	 * This method verifies geolocation tab verification on spec details page. 
	 * @lastModifiedBy bshah
	 */
	public static void geoLocationTabVerification() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_CHECK_ALL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_CHECK_ALL_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_CHECK_ALL_XPATH));
		BrowserAction.click(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_CHECK_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_CHECK_ALL_XPATH);
		BrowserAction.click(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_CHECK_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_SELECTED_LOCATIONS_XPATH);
		List<WebElement> results = BrowserAction.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_SELECTED_LOCATIONS_XPATH);
		assert results.size()==56;
		assert BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_SELECTED_ITEM_GEOGRAPHIC_AVAILABILITY_XPATH).getText().equals("56 Selected");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_SELECTED_ITEM_GEOGRAPHIC_AVAILABILITY_XPATH));
		assert BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEOGRAPHIC_AVAILABILITY_WORLDWIDE_XPATH).getText().equals("Nationwide");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEOGRAPHIC_AVAILABILITY_WORLDWIDE_XPATH));
	}

	/**
	 * This method verifies no selection on geolocation tab on spec details page. 
	 * @lastModifiedBy bshah
	 */
	public static void nogeolocationselection() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_CHECK_ALL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_CHECK_ALL_XPATH);		
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_CHECK_ALL_XPATH));
		BrowserAction.click(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATION_CHECK_ALL_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_SELECTED_ITEM_GEOGRAPHIC_AVAILABILITY_XPATH);
		assert BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_SELECTED_ITEM_GEOGRAPHIC_AVAILABILITY_XPATH).getText().equals("0 Selected");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_SELECTED_ITEM_GEOGRAPHIC_AVAILABILITY_XPATH));
	}

	
	/**
	 * This method verifies alert on save of spec when no selection on geolocation tab on spec details page. 
	 * @lastModifiedBy bshah
	 */
	public static void alertverificationfornogeolocation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_CAN_NOT_BE_SAVED_MODEL_WINDOW_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_CAN_NOT_BE_SAVED_MODEL_WINDOW_XPATH);
		assert BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_CAN_NOT_BE_SAVED_MODEL_WINDOW_XPATH).getText().contains("Geo-Location selection required...");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_CAN_NOT_BE_SAVED_MODEL_WINDOW_XPATH));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_CAN_NOT_BE_SAVED_MODEL_WINDOW_CONTINUE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_CAN_NOT_BE_SAVED_MODEL_WINDOW_CONTINUE_BUTTON_XPATH);			
		BrowserAction.click(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_CAN_NOT_BE_SAVED_MODEL_WINDOW_CONTINUE_BUTTON_XPATH);	
	}

	/**
	 * This method selects 3 geo locations on spec details page 
	 * @lastModifiedBy bshah
	 */
	public static void selectGEOLocatinons() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATIONS_XPATH);
		List<WebElement> results = BrowserAction.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_GEO_LOCATIONS_XPATH);
		int i=0;
		while(i<3){
			results.get(i).click();
			i++;
		}
		assert BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_SELECTED_ITEM_GEOGRAPHIC_AVAILABILITY_XPATH).getText().equals("3 Selected");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_SELECTED_ITEM_GEOGRAPHIC_AVAILABILITY_XPATH));
		System.out.println("geo locations are selected successfully");
	}

	/**
	 * This method selected geo location details on existing spec
	 * @lastModifiedBy bshah
	 */
	public static void verifygeolocationonexistingspec() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_SELECTED_ITEM_GEOGRAPHIC_AVAILABILITY_XPATH);
		assert BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_SELECTED_ITEM_GEOGRAPHIC_AVAILABILITY_XPATH).getText().equals("3 Selected");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_SELECTED_ITEM_GEOGRAPHIC_AVAILABILITY_XPATH));
	}
	
	/**
	 * This method clicks on export to pdf from details page
	 * @lastModifiedBy bshah
	 */
	public static void exporttopdf() throws Exception	{
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_PDF_ID);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_PDF_ID);			
		BrowserAction.click(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_PDF_ID);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_PDF_LINK_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_PDF_LINK_XPATH);			
		BrowserAction.click(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_PDF_LINK_XPATH);	
	}
	
	/**
	 * This method moves client approved status of fleet spec from details page
	 * @lastModifiedBy bshah
	 */
	public static void clientapprovefleetspec() throws Exception	{
		List<WebElement> tableRows = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_TABLE_ROW_XPATH);
		if((tableRows.size()==1)&&(tableRows.get(0).getText().contains("There are currently no Fleet Specification Saved for this Client")||tableRows.get(0).getText().contains("There are currently no Price & Configurations Saved for this Client")))
			System.out.println("No element available to click\n"+BrowserAction.getElementText(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_TABLE_ROW_XPATH));
		else {
			tableRows.get(0).click();
			OrderingVehiclePage.checklockingmessagevisible();
			OrderingVehiclePage.waitForVehicleSectionInformationPage();
			BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_APPROVE_BUTTON_ID);
			BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_APPROVE_BUTTON_ID);			
			BrowserAction.click(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_APPROVE_BUTTON_ID);
		}
	}
	
	/** 
	* This method clicks on fleet spec at given row
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void selectFleetSpecAtRow(int nthFleetSpec) throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_TABLE_XPATH).get(nthFleetSpec-1));
			BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_TABLE_XPATH).get(nthFleetSpec-1).click();
		}
		catch(StaleElementReferenceException e) {
			BrowserWait.waitUntilElementIsDisplayed(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_TABLE_XPATH).get(nthFleetSpec-1));
			BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_TABLE_XPATH).get(nthFleetSpec-1).click();	
		}
	}


	/** 
	* This method verify Driver paid for interior/exterior color when invoice amount is greater than 0$ 
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void verifyDriverPaidForIntExtColor(String colorType, String optTypeValue, String OptTypeValueToSelect, String indicatorValue) throws Exception {
		int row = 0;
		boolean optFlag = false;
		double invoiceAmount;

		switch(colorType){

		case "Exterior":
			for(WebElement ele: BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXTERIOR_COLORS_INVOICE_XPATH)) {
				row++;
				BrowserWait.waitUntilElementIsDisplayed(ele);
				if(ele.getText().contains(",")) {
					invoiceAmount= Float.parseFloat(ele.getText().replace("$", "").replace(",", "").trim());
				} else {
					invoiceAmount= Float.parseFloat(ele.getText().replace("$", "").trim());
				}
				System.out.println("Exterior: "+invoiceAmount);
				if(invoiceAmount>0.00) {
					optFlag=true;
					break;
				}
			}
			if(optFlag==true) {
				Select optType= new Select(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXTERIOR_COLORS_OPTIONTYPE_XPATH).get(row-1));	
				Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXTERIOR_COLORS_SHOWHIDE_XPATH).get(row-1).getText().contains(indicatorValue));
				System.out.println(optType.getFirstSelectedOption().getText());
				List<WebElement> elements =  optType.getOptions();
				List<String> selectionOptions = new ArrayList<String>();
				for(WebElement element : elements) {
					selectionOptions.add(element.getText().trim());
				}
				Assert.assertTrue(selectionOptions.contains(optTypeValue), "Expected value: " + optTypeValue + " Actual Value: "+optType.getFirstSelectedOption().getText());
				optType.selectByVisibleText(OptTypeValueToSelect);
				BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXTERIOR_COLORS_OPTIONTYPE_CHANGE_WARNING_XPATH);
				BrowserVerify.verifyElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXTERIOR_COLORS_OPTIONTYPE_CHANGE_WARNING_XPATH);
				BrowserAccess.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXTERIOR_COLORS_OPTIONTYPE_CHANGE_WARNING_CLOSE_XPATH).click();
			}
			break;

		case "Interior" :
			for(WebElement ele: BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_INTERIOR_COLORS_INVOICE_XPATH)) {
				row++;
				BrowserWait.waitUntilElementIsDisplayed(ele);
				if(ele.getText().contains(",")) {
					invoiceAmount= Float.parseFloat(ele.getText().replace("$", "").replace(",", "").trim());
				} else {
					invoiceAmount= Float.parseFloat(ele.getText().replace("$", "").trim());
				}
				System.out.println("Interior: "+invoiceAmount);
				if(invoiceAmount>0.00) {
					optFlag=true;
					break;
				}
			}
			if(optFlag==true) {
				Select optType= new Select(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_INTERIOR_COLORS_OPTIONTYPE_XPATH).get(row-1));	
				Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_INTERIOR_COLORS_SHOWHIDE_XPATH).get(row-1).getText().contains(indicatorValue));
				System.out.println(optType.getFirstSelectedOption().getText());
				List<WebElement> elements =  optType.getOptions();
				List<String> selectionOptions = new ArrayList<String>();
				for(WebElement element : elements) {
					selectionOptions.add(element.getText().trim());
				}
				Assert.assertTrue(optType.getFirstSelectedOption().getText().contentEquals(optTypeValue), "Expected value: " + optTypeValue + " Actual Value: "+optType.getFirstSelectedOption().getText());
				optType.selectByVisibleText(OptTypeValueToSelect);
				BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXTERIOR_COLORS_OPTIONTYPE_CHANGE_WARNING_XPATH);
				BrowserVerify.verifyElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXTERIOR_COLORS_OPTIONTYPE_CHANGE_WARNING_XPATH);
				BrowserAccess.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_INTERIOR_COLORS_OPTIONTYPE_CHANGE_WARNING_CLOSE_XPATH).click();
			}
			break;
        default: throw new InvalidSwitchCaseException("Incorrect parameter passed");
		}
	}

	/** 
	* This method verify DPO calculator is present
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void verifyDPOCalculatorIsDisplayed() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_DPO_CALCULATOR_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_DPO_CALCULATOR_XPATH);
		Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_DPO_CALCULATOR_THRESHOLD_BOX_XPATH).get(0).getText().contentEquals("Driver Paid Option Threshold"));
		Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_DPO_CALCULATOR_THRESHOLD_BOX_XPATH).get(1).getText().contentEquals("$"));
		Assert.assertFalse(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_DPO_CALCULATOR_THRESHOLD_BOX_XPATH).get(2).getText().contentEquals(""));
		Assert.assertTrue(BrowserAccess.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_DPO_CALCULATOR_XPATH).findElement(By.tagName("h6")).getText().contentEquals("DPO Threshold Calculator"));
	}
	
	/** 
	* This method selects Make, Model and Trim at spec page
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void searchOnSpecPage(String value) throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_SEARCH_FILTER_XPATH)));
		BrowserWait.waitUntilElementIsDisplayed(BrowserAccess.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_SEARCH_FILTER_XPATH));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_SEARCH_FILTER_XPATH)));
		BrowserAccess.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_SEARCH_FILTER_XPATH).sendKeys(value);
	}
	
	/** 
	* This method selects DPO Option and verifies cost and option type update
	* @lastModifiedBy bshaah 
	* @throws Exception 
	*/
	public static void dpoVerification() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_OPTIONS_STATE_lABEL_XPATH);
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_OPTIONS_STATE_lABEL_XPATH  ));
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_OPTIONS_STATE_lABEL_XPATH).getText().contains("Available");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_OPTIONS_STATE_lABEL_XPATH));
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_OPTIONS_TYPE_lABEL_XPATH);
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_OPTIONS_TYPE_SELECT_XPATH);
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_OPTIONS_TYPE_lABEL_XPATH));
		List<WebElement> results = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_MODEL_POPUP_XPATH);
		if(results.size()>0){
			BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_MODEL_POPUP_CLOSE_BUTTON_XPATH);
			}
		BrowserVerify.verifyElementEnabled(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_ADJUSTMENT_TYPE_XPATH));
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_ADJUSTMENT_TYPE_XPATH));
		BrowserVerify.verifyElementEnabled(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_INVOICE_PRICE_XPATH));
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_INVOICE_PRICE_XPATH));
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_TOTAL_INVOICE_XPATH).getText().contains(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_INVOICE_PRICE_XPATH).getAttribute("data-invoice"));
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_TOTAL_INVOICE_XPATH));
		CommonPage.getElementOrderObject().getVehicleTabObject().setdriverpayprice(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_INVOICE_PRICE_XPATH).getAttribute("data-invoice"));
	}
	
	/** 
	* This method verifies standard equipment section
	* @lastModifiedBy bshaah 
	* @throws Exception 
	*/
	public static void standardEquipmentTabVerification()throws Exception {
		BrowserAction.click(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_STD_EQUIPMENT_TAB_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_STD_EQUIPMENT_SECTIONS_XPATH);
		List<WebElement> results = BrowserAction.getElements(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_STD_EQUIPMENT_SECTIONS_XPATH);
		assert results.size()==8;
		assert results.get(0).getText().contains("Powertrain");
		CommonPage.jsGreenBorderHighlighter(results.get(0));
		assert results.get(1).getText().contains("Steering and Suspension");
		CommonPage.jsGreenBorderHighlighter(results.get(1));
		assert results.get(2).getText().contains("Safety");
		CommonPage.jsGreenBorderHighlighter(results.get(2));
		assert results.get(3).getText().contains("Comfort and Convenience Features");
		CommonPage.jsGreenBorderHighlighter(results.get(3));
		assert results.get(4).getText().contains("Seating and Interior");
		CommonPage.jsGreenBorderHighlighter(results.get(4));
		assert results.get(5).getText().contains("Exterior Features");
		CommonPage.jsGreenBorderHighlighter(results.get(5));
		assert results.get(6).getText().contains("Warranty");
		CommonPage.jsGreenBorderHighlighter(results.get(6));
		assert results.get(7).getText().contains("Dimensions and Capacities");
		CommonPage.jsGreenBorderHighlighter(results.get(7));
		results.get(0).click();
		results.get(0).click();
	}
	
	/** 
	* This method verifies DPO Price in existing spec
	* @lastModifiedBy bshaah 
	* @throws Exception 
	*/
	public static void existingDpoVerification()throws Exception {
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_INVOICE_PRICE_XPATH));
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_OPTIONS_STATE_lABEL_XPATH).getText().contains("Available");
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_INVOICE_PRICE_XPATH).getAttribute("data-invoice").equals(CommonPage.getElementOrderObject().getVehicleTabObject().getdriverpayprice());
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_TOTAL_INVOICE_XPATH));
	}

	/**
	 * This method clicks on export to excel from details page
	 * @lastModifiedBy ksharma
	 */
	public static void exportToExcel(String section, String className) throws Exception	{
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_PDF_ID);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_PDF_ID);			
		BrowserAction.click(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_PDF_ID);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_EXCEL_LINK_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_EXCEL_LINK_XPATH);			
		BrowserAction.click(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_EXPORT_TO_EXCEL_LINK_XPATH);	
		OrderingVehicleConfigFleetSpecsPage.excelExportVerification(section,className);
	}
	
	/**
	 * This method selects Make on vehicle filter
	 * @lastModifiedBy lpadaliya
	 */
	public static void selectMake() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MAKE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MAKE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MAKE_TEXTBOX_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MAKE_TEXTBOX_XPATH)));
		BrowserAction.clickandClear(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MAKE_TEXTBOX_XPATH);
		System.out.println("Make: " + CommonPage.getTestData("Make"));
		BrowserAction.enterFieldValue(OrderingVehiclePageEnum.ORDERING_VEHICLE_BUILD_FROM_SCRATCH_MAKE_TEXTBOX_XPATH, CommonPage.getTestData("Make"));
		KeyPressEvents.pressEnter(1);
		OrderingBOQueuePage.waitUntilCompletePageLoad();
	}

	/** 
	* This method verifies option type for incentive
	*  @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void incentiveVerification(String OptState, String AlternateOptState) throws Exception {
		int numberOfIncentives=BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_INCENTIVE_OPTIONSTATE_XPATH).size();
		System.out.println("No. of Incentives: "+numberOfIncentives);
		int requiredCount=0;
		int availableCount=0;
		Select incentiveType;
		for(int i=0;i<numberOfIncentives;i++) {
			incentiveType= new Select(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_INCENTIVE_OPTIONSTATE_XPATH).get(i));
			if(incentiveType.getFirstSelectedOption().getText().contentEquals(AlternateOptState)) {
				availableCount++;
			} else if(incentiveType.getFirstSelectedOption().getText().contentEquals(OptState)) {
				requiredCount++;
			}
		}
		if (numberOfIncentives==1) {
			System.out.println("Required Incentive count: " + requiredCount + "; Available incentive count: "+availableCount);
			Assert.assertTrue(requiredCount==1 && availableCount==0);
		} else if (numberOfIncentives>1) {
			System.out.println("Required Incentive count: " + requiredCount + "; Available incentive count: "+availableCount);
			Assert.assertTrue(requiredCount==1 && availableCount==(numberOfIncentives-1));
		}
	}

	/**
	 * This method verifies summary widget invoice tab for price protection on Fleet details page.
	 * @lastModifiedBy lpadaliya
	 */	
	public static void verifyInvoiceTabSummaryWidgetPriceProtection() throws Exception{
		((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_INVOICE_TAB_ID));
		BrowserWait.waitUntilElementIsDisplayed(BrowserAction.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_PRICE_PROTECTION_IMAGE_XPATH).get(0));
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_PRICE_PROTECTION_IMAGE_XPATH).get(0));
		Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_PRICE_PROTECTION_IMAGE_XPATH).get(0).isDisplayed());
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_BASE_INVOICE_PRICE_XPATH));
		Assert.assertTrue(BrowserAccess.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_BASE_INVOICE_PRICE_XPATH).isDisplayed());
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_DESTINATION_FEE_PRICE_XPATH).get(1));
		Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_DESTINATION_FEE_PRICE_XPATH).get(1).isDisplayed());
	}
	
	/**
	 * This method verifies summary widget MSRP tab for price protection on Fleet details page.
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void verifyMSRPTabSummaryWidgetPriceProtection() throws Exception {
		((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_MSRP_TAB_ID));
		BrowserAccess.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_MSRP_TAB_ID).click();
		BrowserWait.waitUntilElementIsDisplayed(BrowserAction.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_PRICE_PROTECTION_IMAGE_XPATH).get(1));
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_PRICE_PROTECTION_IMAGE_XPATH).get(1));
		Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_PRICE_PROTECTION_IMAGE_XPATH).get(1).isDisplayed());
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_BASE_MSRP_PRICE_XPATH));
		Assert.assertTrue(BrowserAccess.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_BASE_MSRP_PRICE_XPATH).isDisplayed());
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_DESTINATION_FEE_PRICE_XPATH).get(2));
		Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_DESTINATION_FEE_PRICE_XPATH).get(2).isDisplayed());
		}

	/**
	 * This method verifies color of Base price and destination fee .
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void verifyColorForBaseAndDestinationFeeLabels(String colorCode) throws Exception {
		BrowserAccess.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_INVOICE_TAB_ID).click();
		((JavascriptExecutor) WebDriverAccess.getDriver()).executeScript("arguments[0].scrollIntoView(true);", BrowserAction.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_INVOICE_TAB_ID));
		BrowserWait.waitUntilElementIsDisplayed(BrowserAction.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_PRICE_PROTECTION_IMAGE_XPATH).get(0));
		String colorInvBaseLabel =BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_BASE_PRICE_LABEL_XPATH).get(1).getCssValue("color");
		Assert.assertTrue(Color.fromString(colorInvBaseLabel).asHex().equalsIgnoreCase(colorCode));
		String colorInvDestFeeLabel =BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_DESTINATION_FEE_LABEL_XPATH).get(1).getCssValue("color");
		Assert.assertTrue(Color.fromString(colorInvDestFeeLabel).asHex().equalsIgnoreCase(colorCode));
		BrowserAccess.getElement(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_MSRP_TAB_ID).click();
		BrowserWait.waitUntilElementIsDisplayed(BrowserAction.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_PRICE_PROTECTION_IMAGE_XPATH).get(1));
		String colorMSRPBaseLabel =BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_BASE_PRICE_LABEL_XPATH).get(2).getCssValue("color");
		Assert.assertTrue(Color.fromString(colorMSRPBaseLabel).asHex().equalsIgnoreCase(colorCode));
		String colorMSRPDestFeeLabel =BrowserAccess.getElements(OrderingFOFleetSpecDetailsPageEnum.FLEET_SPEC_DETAILS_PAGE_SUMMARY_WIDGET_DESTINATION_FEE_LABEL_XPATH).get(2).getCssValue("color");
		Assert.assertTrue(Color.fromString(colorMSRPDestFeeLabel).asHex().equalsIgnoreCase(colorCode));
	}
	
	/** 
	* This method updates DPO Price in existing spec
	* @lastModifiedBy lpadaliya 
	* @throws Exception 
	*/
	public static void existingDpoUpdate()throws Exception {
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_INVOICE_PRICE_XPATH));
		assert BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_OPTIONS_STATE_lABEL_XPATH).getText().contains("Available");
		BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_INVOICE_PRICE_XPATH).clear();
		BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_INVOICE_PRICE_XPATH).sendKeys(Integer.toString(Integer.parseInt(CommonPage.getElementOrderObject().getVehicleTabObject().getdriverpayprice())-100));
		System.out.println("Updated DPO value: "+Integer.toString(Integer.parseInt(CommonPage.getElementOrderObject().getVehicleTabObject().getdriverpayprice())-100));
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigFleetSpecsPageEnum.ORDERING_VEHICLE_SPEC_DETAILS_DPO_TOTAL_INVOICE_XPATH));
	}
}
