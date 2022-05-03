package com.element.fleet.ordering.page;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOQueuesCommonXpathEnum;
import com.element.fleet.ordering.enums.OrderingBOUpfitProjectPageEnum;
import com.element.fleet.ordering.enums.OrderingVehicleConfigFleetSpecsPageEnum;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;


public class OrderingBOUpfitProjectPage {
	
	public static String RFQNumber;
	public static String CopyRFQNumber;
	
	
	public static void waitForUpfitProjectPage() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_HEADING_CSS);
			BrowserVerify.verifyElementIsPresent(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_HEADING_CSS);
			} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Application is not able to load Upfit/Project page");
		}	
	}
	
	public static void upfitProjectPageHeadingLabelValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_HEADING_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_HEADING_CSS),"Upfit / Project", "Upfit / Project label did not match with the expected string");
	}
	
	public static void upfitProjectPageBasicComponentsValidation() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_REQUEST_QUEUE_LABEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_LABEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_REQUEST_QUEUE_SEARCH_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_REQUEST_QUEUE_SEARCH_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_REQUEST_QUEUE_CLEARFILTER_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_REQUEST_QUEUE_CLEARFILTER_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_REQUEST_ADVANCED_SEARCH_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_REQUEST_ADVANCED_SEARCH_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_SEARCH_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_SEARCH_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_CLEARFILTER_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_CLEARFILTER_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_ADVANCED_SEARCH_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_ADVANCED_SEARCH_XPATH);	
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_REQUEST_BULK_ACTIONS_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_BULK_ACTIONS_XPATH);
	}
	
	public static void gotoCreateRfqPage() throws Exception {
	 try{
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_CLEARFILTER_XPATH));
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_CLEARFILTER_XPATH);
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_CLEARFILTER_XPATH));
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_CLIENT_FILTER_XPATH,CommonPage.getTestData("ClientNumber"));
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_PROJECT_ID_FILTER_XPATH,CommonPage.getTestData("ProjectId"));
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_SEARCH_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_FIRST_ROW_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_CREATE_RFQ_XPATH);
	     }catch(org.openqa.selenium.StaleElementReferenceException ex){
			BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_PROJECT_QUEUE_FIRST_ROW_XPATH);
			BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_CREATE_RFQ_XPATH);
	        }
	   }
		
	
	public static void fillRfqPageDetails() throws Exception {
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_CLIENT_INFORMATION_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_CLIENT_INFORMATION_SELECT_ALL_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_CLIENT_INFORMATION_XPATH);
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UNIT_NUMBER_XPATH));
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UNIT_NUMBER_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UNIT_NUMBER_SELECT_ALL_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UNIT_NUMBER_XPATH);
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_ASSIGN_UPFITTER_SMART_SEARCH_XPATH,CommonPage.getTestData("AssignUpfitter"));
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_SMART_SEARCH_RESULT_XPATH);
		//BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_DEALER_NUMBER_XPATH,"ABC");
		//BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_NAME_XPATH,"ABC");
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_EMAIL_XPATH,CommonPage.getTestData("UpfitterEmail"));
		BrowserAction.selectDropdownOptionByText(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_SHIPPING_INTENT_XPATH,CommonPage.getTestData("ShippingIntent"));
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_ASSIGN_FLEET_SPEC_SMART_SEARCH_XPATH));
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_ASSIGN_FLEET_SPEC_SMART_SEARCH_XPATH,CommonPage.getTestData("FleetSpec"));
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_SMART_SEARCH_RESULT_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_ADD_LINE_ITEM_XPATH);
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_INFORMATION_DESCRIPTION_XPATH,CommonPage.getTestData("InformationDescription"));
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_INFORMATION_QUANTITY_XPATH,CommonPage.getTestData("InformationQuantity"));
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_INSTRUCTION_TO_UPFITTER_XPATH,CommonPage.getTestData("Instruction"));
	}
	
	public static void saveAndSendRfq() throws Exception {
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_FOOTER_SAVE_XPATH);
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_RFQ_NAME_XPATH,CommonPage.getTestData("RfqName"));
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_RFQ_DESCRIPTION_XPATH,CommonPage.getTestData("RfqDescription"));
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_RFQ_POP_UP_SAVE_BUTTON_XPATH);
		String saveNotification = BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_NOTIFICATION_XPATH).getText();
		System.out.println(saveNotification);
		String[] array1=saveNotification.split("RFQ");
        String[] array2=array1[1].split("has");
        RFQNumber=array2[0].trim();
        System.out.println(RFQNumber);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_NOTIFICATION_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_FOOTER_SEND_XPATH);
		String growlMsg = BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_NOTIFICATION_XPATH).getText();
		System.out.println(growlMsg);
		Assert.assertEquals(growlMsg, "Email sent successfully");
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_NOTIFICATION_XPATH);
	}
	public static void validatingMandatoryFields() throws Exception {
		//BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_FOOTER_SAVE_XPATH);
		//BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_FOOTER_SAVE_XPATH);
		String growlError = BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_NOTIFICATION_XPATH).getText();
		System.out.println(growlError);
		Assert.assertEquals(growlError, "Quote cannot be saved. Please check the highlighted error");
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_NOTIFICATION_XPATH);
		String manClientError = BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_CLIENT_MANDATORY_FIELDS_XPATH).getText();
		System.out.println(manClientError);
		Assert.assertEquals(manClientError, "Please select a client");
		List<WebElement>mandatoryFields = BrowserAccess.getElements(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_MANDATORY_FIELDS_XPATH);
		for(WebElement mandatoryField : mandatoryFields)
        {
          String manFieldName=mandatoryField.getText().trim();
          System.out.println(manFieldName);
          Assert.assertEquals(manFieldName, "Field required","mandatory error message is not present" );
        }
	  }
		
	public static void copyRfq() throws Exception {	
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_PROJECT_MANAGMENT_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_RFQ_DROPDOWN_XPATH);
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_RFQ_UPFIT_TABLE_XPATH));
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_RFQ_ID_HEADER_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_RFQ_ID_HEADER_XPATH);
		String firstRecord = BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_FIRST_RECORD_RFQ_ID_XPATH).getText();
		System.out.println(firstRecord);
		Assert.assertEquals(firstRecord, RFQNumber);
		String firstRecordStatus = BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_FIRST_RECORD_RFQ_STATUS_XPATH).getText();
		System.out.println(firstRecordStatus);
		Assert.assertEquals(firstRecordStatus, "Request for a Quote Sent");
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_FIRST_RECORD_ROW_ACTION_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_FIRST_RECORD_ROW_ACTION_COPY_XPATH);
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_ASSIGN_UPFITTER_SMART_SEARCH_XPATH));
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_ASSIGN_UPFITTER_SMART_SEARCH_XPATH,CommonPage.getTestData("AssignUpfitter"));
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_SMART_SEARCH_RESULT_XPATH);
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_UPFITTER_EMAIL_XPATH,CommonPage.getTestData("UpfitterEmail"));
        BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_FOOTER_SAVE_XPATH);
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_RFQ_NAME_XPATH,CommonPage.getTestData("RfqName"));
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_RFQ_DESCRIPTION_XPATH,CommonPage.getTestData("RfqDescription"));
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_RFQ_POP_UP_SAVE_BUTTON_XPATH);
		String saveCopyRfqNotification = BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_NOTIFICATION_XPATH).getText();
		System.out.println(saveCopyRfqNotification);
		String[] array1=saveCopyRfqNotification.split("RFQ");
        String[] array2=array1[1].split("has");
        CopyRFQNumber=array2[0].trim();
        System.out.println(CopyRFQNumber);
        BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_NOTIFICATION_XPATH);
        BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_FOOTER_SEND_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_NOTIFICATION_XPATH);
		
	  }
	
	public static void cancelRfq() throws Exception {
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_FOOTER_CANCEL_XPATH);
		BrowserWait.waitUntilElementIsNotDisplayed(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_NOTIFICATION_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_CREATE_RFQ_PROJECT_MANAGMENT_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.OORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_RFQ_DROPDOWN_XPATH);
		JavascriptExecutor js=(JavascriptExecutor)WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView();",BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_RFQ_UPFIT_TABLE_XPATH));
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_RFQ_ID_HEADER_XPATH);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_RFQ_ID_HEADER_XPATH);
		String firstCopyRecord = BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_FIRST_RECORD_RFQ_ID_XPATH).getText();
		System.out.println(firstCopyRecord);
		Assert.assertEquals(firstCopyRecord, CopyRFQNumber);
		String firstCopyRecordStatus = BrowserAction.getElement(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PROJECT_DETAIL_FIRST_RECORD_RFQ_STATUS_XPATH).getText();
		System.out.println(firstCopyRecordStatus);
		Assert.assertEquals(firstCopyRecordStatus, "Closed");
	  }
	
	public static void checkRfqQueue() throws Exception {
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_UPFIT_PROJECT_PAGE_QUEUE_XPATH);
		OrderingBOQueuePage.gotoQueue("RFQ");
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_RFQ_CLEAR_FILTER_BUTTON_XPATH);
		BrowserAction.enterFieldValue(OrderingBOUpfitProjectPageEnum.ORDERING_BO_RFQ_RFQ_ID_FILTER_XPATH,RFQNumber);
		BrowserAction.click(OrderingBOUpfitProjectPageEnum.ORDERING_BO_RFQ_SEARCH_BUTTON_XPATH);
	  }
	
  }
	
	
	