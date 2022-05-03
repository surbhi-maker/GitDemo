package com.element.fleet.ordering.page;

import org.testng.Assert;

import com.element.fleet.ordering.enums.OrderingBOBizzMaintTablesCommonEnum;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;

public class OrderingBOBizzMiantCommonPage {
	
	public static void clickOnFirstRecord() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_ADD_NEW_ENTRY_BUTTON_XPATH);
		BrowserAction.click(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_FIRST_RECORD_XPATH);
	}
	
	public static void saveBtnValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_ADD_NEW_CLOSE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_ADD_NEW_CLOSE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_ADD_NEW_CLOSE_BUTTON_XPATH);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_ADD_NEW_CLOSE_BUTTON_XPATH).trim(), "Save", "Both values matched");
	}
	
	public static void deleteBtnValidation() throws Exception {
		BrowserVerify.verifyElementIsDisplayed(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_UPDATE_DELETE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_UPDATE_DELETE_BUTTON_XPATH);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_UPDATE_DELETE_BUTTON_XPATH).trim(), "Delete", "Both values matched");
	}
	
	public static void addNewBtnValidation() throws Exception {
		BrowserVerify.verifyElementIsDisplayed(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_UPDATE_ADD_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_UPDATE_ADD_BUTTON_XPATH);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_UPDATE_ADD_BUTTON_XPATH).trim(), "Add", "Both values matched");
	}
	
	public static void searchBtnValidation() throws Exception {
		BrowserVerify.verifyElementIsDisplayed(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_UPDATE_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_UPDATE_SEARCH_BUTTON_XPATH);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOBizzMaintTablesCommonEnum.ORDERING_BO_BMT_COMMON_UPDATE_SEARCH_BUTTON_XPATH).trim(), "Search", "Both values matched");
	}

}
