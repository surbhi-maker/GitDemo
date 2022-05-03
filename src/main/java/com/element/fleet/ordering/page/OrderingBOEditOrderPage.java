package com.element.fleet.ordering.page;

import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOEditOrderPageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.rest.OrderingRestAPI;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.common.utils.SimpleStringCipher;

public class OrderingBOEditOrderPage {
	
	/**
	 * This method will click on Edit Orders link in BO
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnEditOrder() throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDER_XPATH, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDER_XPATH);
	}
	
	/**
	 * This method will click on mentioned Edit Orders options 
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void goToEditOrdersOption(String editOrderOption) throws Exception {
		switch(editOrderOption) {
			case "Order Maintenance":
				CommonPage.waitForElementToLoad(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDER_MAINTENANCE_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDER_MAINTENANCE_XPATH);
				break;
			case "Add Upfit Purchase Order":
				CommonPage.waitForElementToLoad(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_ADD_UPFIT_PURCHASE_ORDER_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_ADD_UPFIT_PURCHASE_ORDER_XPATH);
				break;
			case "Invoice Entry":
				CommonPage.waitForElementToLoad(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_INVOICE_ENTRY_XPATH, CommonPage.getTestData("WaitTime"));
				BrowserAction.click(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_INVOICE_ENTRY_XPATH);
				break;
			default :
				throw new InvalidSwitchCaseException("wrong edit order option parameter passed");
		}
	}
	
	/**
	 * This method will fill all details in add upfit po page
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void searchAndSelectOrder(String searchText) throws Exception {
		CommonPage.waitForElementToLoad(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_ADD_PO_SEARCH_ORDER_ID, CommonPage.getTestData("WaitTime"));
		System.out.println("Order Search: " + searchText);
		CommonPage.enterTextToInputField(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_ADD_PO_SEARCH_ORDER_ID, searchText);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_ADD_PO_SEARCH_ORDER_OPTION_XPATH);
		BrowserAction.click(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_ADD_PO_SEARCH_ORDER_OPTION_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method will fill all details in add upfit po page
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void fillAddUpfitPODetailsAndClickOnSave() throws Exception {
		OrderingBOEditOrderPage.searchAndSelectOrder(OrderingRestAPI.getLogNumberForEditOrder(OrderingRestAPI.getOauthTokenFromOrderingFO(OrderingRestAPI.getCookieFromOrderingFO(CommonPage.getTestData("Username"), SimpleStringCipher.decrypt(CommonPage.getCredetialsData(CommonPage.getTestData("Username")))))));
		OrderingBOOrdMaintPage.searchAndSelectUpfitSpec();
		OrderingBOOrdMaintPage.searchAndSelectUpfitter();
		OrderingBOOrdMaintPage.searchAndSelectInstaller();
		OrderingBOOrdMaintPage.selectWorkType();
		OrderingBOOrdMaintPage.selectShipTo();
		OrderingBOOrdMaintPage.searchAndSelectDropShip();
		OrderingBOOrdMaintPage.clickOnSave();
	}
	
	/**
	 * This method will verify data for which you have search from search field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifySearchData() throws Exception {
		CommonPage.initializeElementOrderObject();
		OrderingBOEditOrderPage.collectSearchedDetails();
	}
	
	/**
	 * This method will store all search order details
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void collectSearchedDetails() throws Exception {
		String logNumber = BrowserAction.getElement(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_ADD_PO_LOG_NUMBER_XPATH).getText().trim();
		String fleet = BrowserAction.getElement(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_ADD_PO_FLEET_XPATH).getText().trim();
		String corp = BrowserAction.getElement(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_ADD_PO_CORP_XPATH).getText().trim();
		String unit = BrowserAction.getElement(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_ADD_PO_UNIT_XPATH).getText().trim();
		String vin = BrowserAction.getElement(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_ADD_PO_VIN_XPATH).getText().trim();
		String supplierId = BrowserAction.getElement(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_SUPLLIER_ID_ID).getText().trim();
		String supplierName = BrowserAction.getElement(OrderingBOEditOrderPageEnum.ORDERING_BO_EDIT_ORDERS_SUPLLIER_NAME_ID).getText().trim();
		CommonPage.getElementOrderObject().setLogNumber(logNumber);
		CommonPage.getElementOrderObject().getVehicleTabObject().setClientNumber(fleet);
		CommonPage.getElementOrderObject().getStartHereTabObject().setCorpCode(corp);
		CommonPage.getElementOrderObject().getStartHereTabObject().setNewUnitNumber(unit);
		CommonPage.getElementOrderObject().getVehicleTabObject().setVIN(vin);
		CommonPage.getElementOrderObject().getVehicleTabObject().getUpfitInformationSectionObject().newAvailableUpfitSpecification().setSupplierId(supplierId);
		CommonPage.getElementOrderObject().getVehicleTabObject().getUpfitInformationSectionObject().newAvailableUpfitSpecification().setSupplierName(supplierName);
		CommonPage.getElementOrderObject().getVehicleTabObject().getUpfitInformationSectionObject().addAvailableUpfitSpecification();
	}
	
	/**
	 * This method goes to current created order.
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void gotoCreatedOrder() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("On-Order");
		OrderingBOOnOrderQueuePage.waitForOnOrderQueuePage();
		OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getElementOrderObject().getLogNumber());
		OrderingBOOrdMaintPage.clickOnSingleSearchResult("logNumber");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
	}
	
}
