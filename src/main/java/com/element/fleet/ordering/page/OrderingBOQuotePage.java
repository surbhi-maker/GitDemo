package com.element.fleet.ordering.page;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOOnOrderProjectPageEnum;
import com.element.fleet.ordering.enums.OrderingBOQuotePageEnum;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;


public class OrderingBOQuotePage {
	
	public static void waitForQuoteDetailsPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_HEADING_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_HEADING_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_HEADING_CSS);	
	}
	
	public static void quoteDetailsPageHeadingLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_HEADING_CSS), "Edit Upfit Quote", "Edit Upfit Quote label did not match with the expected string");
	}

	public static void waitForEditQuotesPageToLoad() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_SUPPLIERS_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_LINE_ITEMS_ID);
	}

	public static void waitCancelPopUpIsLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_MESSAGE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_STAY_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_YES_BUTTON_XPATH);
	}

	public static void clickOKPopUpOfEditUpfitQuotePage() throws Exception {
		String errorMessage = BrowserAction.getElementText(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_MESSAGE_XPATH);
		if(!errorMessage.contains("You are about to leave this page. Your information will be lost."))
			throw new OrderingErrorOccured("Leave page popup error message not verified");
		BrowserAction.click(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CANCEL_POP_UP_BOX_YES_BUTTON_XPATH);			
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOnOrderProjectPageEnum.ORDERING_BO_PROJECTQ_STANDARD_VIEW_ID);
	}

	public static void closeEditUpfitQuotePage() throws Exception {
		BrowserAction.click(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CLOSE_BUTTON_CSS);
	}

	public static void labelCheckQuoteDetailsPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_EDITUPFITQUOTE_LABEL_XPATH);
		assertEquals(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CONVERTTOUPFITSPEC_LABEL_VALUE.getValue().trim(),BrowserAction.getElementText(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CONVERTTOUPFITSPEC_LABEL_XPATH).trim());
		assertEquals(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_SAVE_LABEL_VALUE.getValue().trim(),BrowserAction.getElementText(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_SAVE_LABEL_XPATH).trim());
		assertEquals(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_EDITUPFITQUOTE_LABEL_VALUE.getValue().trim(),BrowserAction.getElementText(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_EDITUPFITQUOTE_LABEL_XPATH).trim());
		assertEquals(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_SUPPLIERS_LABEL_VALUE.getValue().trim(),BrowserAction.getElementText(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_SUPPLIERS_LABEL_XPATH).trim());
		assertEquals(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CHASSISINFO_LABEL_VALUE.getValue().trim(),BrowserAction.getElementText(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_CHASSISINFO_LABEL_XPATH).trim());
		assertEquals(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_LINEITEMS_LABEL_VALUE.getValue().trim(),BrowserAction.getElementText(OrderingBOQuotePageEnum.ORDERING_BO_EDIT_QUOTE_PAGE_LINEITEMS_LABEL_XPATH).trim());
	}
	
	public static void openQuoteFromQuoteQueuePage() throws Exception {
		String element = String.format(OrderingBOQuotePageEnum.ORDERING_BO_QUOTES_QUEUE_PAGE_QUOTEID_XPATH.toString(), CommonPage.getTestData("QuoteId"));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.visibilityOf(WebDriverAccess.getDriver().findElement(By.xpath(element))));
		WebDriverAccess.getDriver().findElement(By.xpath(element)).click();
	}
	
	
	/**
	 * This method converts Quote to UpfitSpec.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void convertToUpfitSpec(String controlName) throws Exception {
		OrderingBOQuotePage.clickonSelectedQuote(controlName);
		OrderingBOOnProjectQueuePage.clickOnSaveButton();
		OrderingBOOnProjectQueuePage.goToConvertToUpfitFleetSpecPage();
		OrderingBOOnProjectQueuePage.enterUpfitFleetSpecName();
		OrderingBOOnProjectQueuePage.enterUpfitFleetSpecDescription();
		OrderingBOOnProjectQueuePage.clickOnConvertUpfitSpecOkButton();
		OrderingBOOnProjectQueuePage.clickOnElementApproveLink();
		OrderingBOOnProjectQueuePage.clickOnClientApproveLink();
		OrderingBOOnProjectQueuePage.clickOnCloseUpfitSpecMaintenancePage();
		OrderingBOOnProjectQueuePage.clickOnCloseEditUpfitQuotePage();
	}
	
	public static void convertToUpfitSpecs(String controlName) throws Exception {
		OrderingBOQueuePage.addControls(controlName, CommonPage.getTestData("QuoteId"));
		OrderingBOQuotePage.openQuoteFromQuoteQueuePage();
		OrderingBOOnProjectQueuePage.clickOnSaveButton();
		OrderingBOOnProjectQueuePage.goToConvertToUpfitFleetSpecPage();
		OrderingBOOnProjectQueuePage.enterUpfitFleetSpecName();
		OrderingBOOnProjectQueuePage.enterUpfitFleetSpecDescription();
	}

	/**
	 * This method clicks on selected quote.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void clickonSelectedQuote(String controlName) throws Exception {
		OrderingBOQueuePage.addControls(controlName, CommonPage.getTestData("QuoteId"));
		OrderingBOQuotePage.openQuoteFromQuoteQueuePage();
	}

}

