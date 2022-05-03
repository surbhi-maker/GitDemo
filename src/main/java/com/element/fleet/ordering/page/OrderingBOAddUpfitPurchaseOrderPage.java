package com.element.fleet.ordering.page;

import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOAddUpfitPurchaseOrderPageEnum;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;

public class OrderingBOAddUpfitPurchaseOrderPage {
	
	public static void addUpfitPurchaseOrderPageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_BO_AUPO_ORDER_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_BO_AUPO_SAVEBTN_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_BO_AUPO_SAVEBTN_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_BO_AUPO_SAVEBTN_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_BO_AUPO_CANCELBTN_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_BO_AUPO_CANCELBTN_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_BO_AUPO_SUBSCREEN_TITLE_XPATH);
	}
	
	public static void addUpfitPurchaseOrderLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOAddUpfitPurchaseOrderPageEnum.ORDERING_BO_AUPO_SUBSCREEN_TITLE_XPATH), "Add Purchase Order", "Add Purchase Order heading did not match with the expected string");
	}

}