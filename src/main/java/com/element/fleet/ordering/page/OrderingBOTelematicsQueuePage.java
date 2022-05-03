package com.element.fleet.ordering.page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOTelematicsPageEnum;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingBOTelematicsQueuePage {
	
	/**
	 * This method waits until telematics queue page is loaded
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void waitForTelematicsPageToLoad() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOTelematicsPageEnum.ORERING_BO_TELEMATICS_LOGNUMBER_XPATH);
	}
	
	/**
	 * This method verifies that searched order is present in telematics queue
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void verifyOrderPresentInTelematicsQueue() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOTelematicsPageEnum.ORERING_BO_TELEMATICS_LOGNUMBER_XPATH);
		System.out.println("Entering log Number"+CommonPage.getTestData("LogNumber"));
		CommonPage.enterTextToInputField(OrderingBOTelematicsPageEnum.ORERING_BO_TELEMATICS_LOGNUMBER_XPATH, CommonPage.getTestData("LogNumber"));
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOTelematicsPageEnum.ORERING_BO_TELEMATICS_SEARCH_BUTTON_XPATH);
		BrowserAction.click(OrderingBOTelematicsPageEnum.ORERING_BO_TELEMATICS_SEARCH_BUTTON_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).
		until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOTelematicsPageEnum.ORERING_BO_TELEMATICS_FIRST_OPTION_XPATH.getValue()), 1));
	}
	
	/**
	 * This method verifies that searched order is not present in the queue.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void VerifyOrderNotPresentInTelematicsQueue() throws Exception {
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOTelematicsPageEnum.ORERING_BO_TELEMATICS_LOGNUMBER_XPATH);
		System.out.println("Entering log Number"+CommonPage.getTestData("LogNumber"));
		CommonPage.enterTextToInputField(OrderingBOTelematicsPageEnum.ORERING_BO_TELEMATICS_LOGNUMBER_XPATH, CommonPage.getTestData("LogNumber"));
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingBOTelematicsPageEnum.ORERING_BO_TELEMATICS_SEARCH_BUTTON_XPATH);
		BrowserAction.click(OrderingBOTelematicsPageEnum.ORERING_BO_TELEMATICS_SEARCH_BUTTON_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).
		until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOTelematicsPageEnum.ORERING_BO_TELEMATICS_FIRST_OPTION_XPATH.getValue()), 0));
	}
	
	/**
	 * This method release the order from telematics queue.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void releaseOrder() throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		verifyOrderPresentInTelematicsQueue();
		BrowserAction.click(OrderingBOTelematicsPageEnum.ORERING_BO_TELEMATICS_FIRST_OPTION_XPATH);
		BrowserAction.click(OrderingBOTelematicsPageEnum.ORERING_BO_TELEMATICS_RELEASE_BUTTON_XPATH);
		VerifyOrderNotPresentInTelematicsQueue();	
	}

}
