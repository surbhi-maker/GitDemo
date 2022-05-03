package com.element.fleet.ordering.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOBusinessMaintainedTablesEnum;
import com.element.fleet.ordering.enums.OrderingElementDeliveringDealerAssignmentRulesPageEnum;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingBOBMTCustDeliveryDealerAssigmentPage {
	
	/** 
	 * This method enters rule name
	 * @lastModifiedBy Mkhaiarnar
	 * @throws Exception
	 */
	public static void clickAddRule() throws Exception {
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_NEW_BUTTON_XPATH);
	}
    
	/** 
	 * This method enters rule name
	 * @lastModifiedBy Mkhaiarnar
	 * @throws Exception
	 */
	public static void clickAddRuleFromMaintenance() throws Exception {
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_ADD_RULE_XPATH);
	}
	
	/** 
	 * This method enter Details For AddRule
	 * @lastModifiedBy Mkhaiarnar
	 * @throws Exception
	 */
	public static void enterDetailsForAddRule() throws Exception {
		OrderingBOBMTCustDeliveryDealerAssigmentPage.searchFleetAndSelect();
		OrderingBOElementDeliveringDealerAssignmentRulesPage.enterRuleName();	
		BrowserAction.enterFieldValue(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_MAX_COURTESY_DELIVERY_FEE_XPATH, CommonPage.getTestData("CustomColumn6"));
		BrowserAction.enterFieldValue(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_MAX_DELIVERY_DISTANCE_XPATH, CommonPage.getTestData("CustomColumn6"));
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_START_DATE_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_START_DATE_SELECTED_DATE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECT_STATE_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECT_STATE_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECTED_STATE_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECT_STATE_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECT_COUNTRY_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECT_COUNTRY_POPUP_HEADING_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECTED_COUNTRY_FROM_POPUP_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SELECT_COUNTRY_POPUP_OK_BUTTON_XPATH);
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		js.executeScript("window.scrollBy(10,document.body.scrollHeight)");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_CDDAR_ADD_RULE_MAKE_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_CDDAR_ADD_RULE_MAKE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_CDDAR_ADD_RULE_MAKE2_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_CDDAR_ADD_RULE_MAKE2_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_CDDAR_ADD_RULE_MAKE3_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDA_SEARCH_DEALER_XPATH);
		BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDA_SEARCH_DEALER_XPATH,CommonPage.getTestData("CustomColumn7"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDA_SEARCH_DEALER_XPATH);	
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SAVE_BUTTON_XPATH);		
		System.out.println("save button clicked");
	}

	/** 
	 * This method for search Fleet And Select from  suggested list.
	 * @lastModifiedBy Mkhaiarnar
	 * @throws Exception
	 */
	private static void searchFleetAndSelect() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDA_SEARCH_CLIENT_XPATH);
		BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDA_SEARCH_CLIENT_XPATH,CommonPage.getTestData("ClientNumber"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDA_SEARCH_CLIENT_XPATH);	
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDA_SEARCH_CLIENT_FIRST_SUGGESTION_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDA_SEARCH_CLIENT_FIRST_SUGGESTION_XPATH);	
	}

	/** 
	 * This method validate the Save Rule
	 * @lastModifiedBy Mkhaiarnar
	 * @throws Exception
	 */
	public static void validateSaveRule() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_SAVE_POPUP_MESSAGE_XPATH);
	}

	/**
	 * This method validates whether rule has been added successfully
	 * @lastModifiedBy Mkhaiarnar
	 * @throws Exception
	 */
	public static void validateRuleCreated() throws Exception {
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_ADD_RULE_BACK_TO_QUEUE_VIEW_XPATH);
		BrowserAction.enterFieldValue(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDA_RULE_LABEL_XPATH, CommonPage.getTestData("RuleName"));
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDA_SEARCH_BUTTON_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SEARCHED_RULE_QUEUE_VIEW_XPATH.getValue()), 1));
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SEARCHED_RULE_QUEUE_VIEW_XPATH), CommonPage.getTestData("RuleName") , "Approver Communications heading validation failed");
	}
	
	/**
	 * This method validates rule has been deleted successfully
	 * @lastModifiedBy Mkhaiarnar
	 * @throws Exception
	 */
	public static void deleteRule() throws Exception {
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SEARCHED_RULE_CHECKBOX_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_DELETE_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_DELETE_CONFIRMATION_POPUP_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_DELETE_CONFIRMATION_POPUP_XPATH), "Are you sure you want to delete this entry?" , "wrong message");
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_DELETE_CONFIRMATION_POPUP_XPATH);
		BrowserAction.click(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_DELETE_CONFIRMATION_POPUP_OK_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SUCCESSFUL_DELETION_CONFIRMATION_XPATH);
		String deleteConfirmationMessage= BrowserAction.getElementText(OrderingElementDeliveringDealerAssignmentRulesPageEnum.ORDERING_BO_EDDAR_SUCCESSFUL_DELETION_CONFIRMATION_XPATH);
		System.out.println("Delete rule confirmation message:" + deleteConfirmationMessage);
	}
	
	/**
	 * This method opens back office application and enters user name and password.
	 * @lastModifiedBy Mkhairanar
	 * @throws Exception
	 */
	public static void verifyAlert() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_ALERT_TEXT2_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_ALERT_TEXT1_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_ALERT_TEXT2_XPATH);
		BrowserAction.click(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_CDDAR_ALERT_TEXT1_XPATH);
	}
}
