package com.element.fleet.ordering.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingDealerPageEnum;
import com.element.fleet.ordering.enums.OrderingStartHerePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.NoIfElseBlockMatchedException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.element.fleet.ordering.rest.OrderingRestAPI;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingDealerPage {

	/**
	 * This method waits for dealer page to load.
	 * @throws Exception
	 */
	public static void waitForDealerPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DELIVERING_DEALER_ID);
		BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DELIVERING_DEALER_ID);
		BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DELIVERING_DEALER_ID);
	}
	
	/**
	 * This method selects the dealer based on the method defined in the test data file under DealerSelectionOption column.
	 * Recommended->For Recommended delayed
	 * Select a Different Dealer-> For different dealer selection
	 * Request a New Dealer->For new dealer selection
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void selectDealer() throws Exception {
		if(CommonPage.getTestData("OrderType").equals("Stock")&&CommonPage.getTestData("WhoWillLocateVehicle").equals("Element")) {
			WebElement deliveryDealerText = WebDriverAccess.getDriver().findElement(By.xpath("//span[@class='noDealerText']"));
			Assert.assertEquals(deliveryDealerText.getText(), "Element will select the dealer within network. Please select save and next to submit your order.");
		} else if((CommonPage.getTestData("OrderType").equals("Stock")&&CommonPage.getTestData("WhoWillLocateVehicle").equals("Client"))||
				(CommonPage.getTestData("OrderType").equals("Dealer"))) {
			//OrderingDealerPage.clickSelectADifferentDealer();
			OrderingDealerPage.searchAndSelectDealer();
		} else {
			OrderingCommonPage.checkAlertPopUp();
			OrderingDealerPage.waitForDealerPage();
			System.out.println("Dealer Option : " + CommonPage.getTestData("DealerSelectionOption"));
			switch(CommonPage.getTestData("DealerSelectionOption")) {
			case "Recommended" : 
				OrderingDealerPage.getElementRecommendedDealerNameifAssigned();
				break;
			case "Select a Different Dealer" : 
				OrderingDealerPage.clickSelectADifferentDealer();
				OrderingDealerPage.verifyBackToDealerChoice();
				OrderingDealerPage.validateSelectADifferentDealerPageLabels();
				OrderingDealerPage.searchAndSelectDealer();
				break;
			case "Request a New Dealer" : 
				OrderingDealerPage.clickRequestANewDealer();
				OrderingDealerPage.verifyBackToDealerChoice();
				OrderingDealerPage.validateRequestANewDealerPageLabels();
				OrderingDealerPage.enterRequestedDealerInfo();
				break;
			default: throw new InvalidSwitchCaseException("Invalid dealer selection");
			}
			OrderingDealerPage.getDetailsFromDealerPage(WebDriverAccess.getDriver());
		}		
	}

	/**
	 * This method retrieves the recommended dealer name on dealer page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void getElementRecommendedDealerNameifAssigned() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_CSS);
		BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_CSS);
		String dealerText=BrowserAccess.getElementText(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_CSS);
		System.out.println("Recommended dealer: " + dealerText );
	}
	
	/**
	 * This method clicks on request a new dealer option on the dealer page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickRequestANewDealer() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_REQUESTED_DEALER_ID);
		BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_REQUESTED_DEALER_ID);
		BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_REQUESTED_DEALER_ID);
		BrowserAction.click(OrderingDealerPageEnum.ORDERING_REQUESTED_DEALER_ID);
	}
	
	/**
	 * This method clicks on select a different dealer option on the dealer page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickSelectADifferentDealer() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_ID);
		BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_ID);
		BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_ID);
		BrowserAction.click(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_ID);
	}

	/**
	 * This method selects the desired use state as mentioned in the test data sheet.
	 * Note: This feature is available in only for internal users and for Factory order.
	 * @author shisingh
	 * @throws Exception
	 */
	public static void selectDesiredUseState() throws Exception {
		if(CommonPage.getTestData("UserRole").contains("Ordering Super User")&&CommonPage.getTestData("OrderType").equals("Factory")) {
			if(Objects.nonNull(CommonPage.getTestData("UseState"))&&(CommonPage.getTestData("UseState").equals("On"))) {
				BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME);
				BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME);
				BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME);
				if(!BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME).isSelected()) {
					BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME);
					BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME);
					BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME);
					BrowserAction.click(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME);					
				}
			}
		}
	}
	
	/**
	 * This method gets the current use state value.
	 * @return
	 * @throws Exception
	 * @lastModifiedBy UshaNaidu
	 */
	public static boolean isUseStateChecked() throws Exception {
		boolean state;
		if(CommonPage.getTestData("UserRole").contains("Ordering Super User") || CommonPage.getTestData("UserRole").contains("Ordering NVAC Team- U. S.")) {
			if(CommonPage.getTestData("OrderType").equals("Factory")) {
				BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME);
				BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME);
				BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME);
				state = BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME).isSelected();
				System.out.println("Use State: " + (state?"Checked":"Unchecked"));
			} else {				
				System.out.println("Use state checkbox not present for External Full Access");
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.name(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME.getValue()), 0));
				state = true;
			}
		} else if(CommonPage.getTestData("UserRole").contains("External Full Access")) {
			System.out.println("Use state checkbox not present for External Full Access");
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.name(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_USE_STATE_CHECKBOX_NAME.getValue()), 0));
			state = true;
		} else {
			throw new OrderingErrorOccured("Scenario for "+CommonPage.getTestData("UserRole")+" not available");
		}
		return state;		
	}
	
	/**
	 * This method gets the current active dealer value.
	 * Note: For external users we don't have Active dealer checkbox
	 * @return
	 * @throws Exception
	 * @lastModifiedBy UshaNaidu
	 */
	public static boolean isActiveDealerChecked() throws Exception {
		boolean state;
		if(CommonPage.getTestData("UserRole").contains("Ordering Super User") || CommonPage.getTestData("UserRole").contains("Ordering NVAC Team- U. S.")) {
			if(CommonPage.getTestData("OrderType").equals("Factory")) {
				BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_ACTIVE_DEALER_CHECKBOX_NAME);
				BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_ACTIVE_DEALER_CHECKBOX_NAME);
				BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_ACTIVE_DEALER_CHECKBOX_NAME);
				state = BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_ACTIVE_DEALER_CHECKBOX_NAME).isSelected();
				System.out.println("Active Dealer: " + (state?"Checked":"Unchecked"));	
			} else {				
				System.out.println("Active dealer checkbox should not be displayed for Stock orders");
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.name(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_ACTIVE_DEALER_CHECKBOX_NAME.getValue()), 0));
				state = true;
			}
		} else if(CommonPage.getTestData("UserRole").contains("External Full Access")) {			
			System.out.println("Active dealer checkbox not present for External Full Access");
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.name(OrderingDealerPageEnum.ORDERING_DIFFERENT_DEALER_ACTIVE_DEALER_CHECKBOX_NAME.getValue()), 0));
			state = true;
		} else {
			throw new OrderingErrorOccured("Scenario for "+CommonPage.getTestData("UserRole")+" not available");
		}
		return state;		
	}
	
	/**
	 * This method returns the dealer state.
	 * Note: For external users we don't have Use State checkbox
	 * @return String
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static String getDealerState() throws Exception {		
		if(Objects.isNull(CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject())?false:true) {
			System.out.println("Delivering dealer state: "+CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().getState());
			return CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().getState();
		} else if(Objects.isNull(CommonPage.getElementOrderObject().getDriverTabObject().getGaragingAddressSectionObject().getState())?false:true) {
			System.out.println("Garaging dealer state: "+CommonPage.getElementOrderObject().getDriverTabObject().getGaragingAddressSectionObject().getState());
			return CommonPage.getElementOrderObject().getDriverTabObject().getGaragingAddressSectionObject().getState();
		} else {
			System.out.println("Driver dealer state: "+CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().getState());
			return CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().getState();
		}
	}
	
	/**
	 * This method enters dealer name and click on the searched dealer on dealer page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void searchAndSelectDealer() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_BOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_BOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_BOX_ID);
		OrderingDealerPage.selectDesiredUseState();
		if(Objects.isNull(CommonPage.getTestData("ManualDealer"))) {
			String dealerName = OrderingRestAPI.getDealerFromOrderingFO(OrderingDealerPage.isActiveDealerChecked(), OrderingDealerPage.isUseStateChecked(), "FA", OrderingDealerPage.getDealerState(), CommonPage.getElementOrderObject().getVehicleTabObject().getMake()).path("response.docs[0].supplier_legal_nm").toString().replace("  ", " ");			
			System.out.println("Select a different dealer: " + dealerName);
			BrowserAction.enterFieldValue(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_BOX_ID, dealerName);
			BrowserAction.click(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_BOX_ID);
			BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_RESULT_CONTAINER_CSS);
			BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_RESULT_CONTAINER_CSS);
			BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_RESULT_CONTAINER_CSS);
			List<WebElement> srchDealerLists = BrowserAccess.getElements(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_RESULT_SET_CSS);
			List<String> srchDealerNameLists = new ArrayList<>();
			int i = 0;
			for(WebElement srchDealerList: srchDealerLists) {
				String srchDealerName = srchDealerList.getText();
				srchDealerNameLists.add(srchDealerName);
				if(srchDealerName.contains(dealerName)) {
					srchDealerList.click();
					++i;
					break;
				}
			}
			if(i==0) {
				throw new OrderingErrorOccured("Searched dealer is not present in list " + srchDealerNameLists);
			}
		} else {
			System.out.println("Select a different dealer: " + CommonPage.getTestData("ManualDealer"));
			BrowserAction.enterFieldValue(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_BOX_ID, CommonPage.getTestData("ManualDealer"));
			BrowserAction.click(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_BOX_ID);
			BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_RESULT_CONTAINER_CSS);
			BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_RESULT_CONTAINER_CSS);
			BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_RESULT_CONTAINER_CSS);
			List<WebElement> srchDealerLists = BrowserAccess.getElements(OrderingDealerPageEnum.ORDERING_SEARCH_DEALER_RESULT_SET_CSS);
			List<String> srchDealerNameLists = new ArrayList<>();
			int i = 0;
			for(WebElement srchDealerList: srchDealerLists) {
				String srchDealerName = srchDealerList.getText();
				srchDealerNameLists.add(srchDealerName);
				if(srchDealerName.contains(CommonPage.getTestData("ManualDealer"))) {
					srchDealerList.click();
					++i;
					break;
				}
			}
			if(i==0) {
				throw new OrderingErrorOccured("Searched dealer is not present in list " + srchDealerNameLists);
			}
		}
		if((CommonPage.getTestData("OrderType").equals("Stock")&&CommonPage.getTestData("WhoWillLocateVehicle").equals("Client"))||
				(CommonPage.getTestData("OrderType").equals("Dealer"))) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_CONTACT_NAME_XPATH);
			BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_PHONE_NUMBER_XPATH);
			BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_EMAIL_XPATH);
			BrowserAction.clickandClear(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_CONTACT_NAME_XPATH);
			System.out.println("Dealer contact name: "+CommonPage.getTestData("RequestedDealerContactName"));
			BrowserAction.enterFieldValue(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_CONTACT_NAME_XPATH, CommonPage.getTestData("RequestedDealerContactName"));
			BrowserAction.clickandClear(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_PHONE_NUMBER_XPATH);
			System.out.println("Dealer phone number: "+CommonPage.getTestData("RequestedDealerPhoneNumber"));
			BrowserAction.enterFieldValue(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_PHONE_NUMBER_XPATH, CommonPage.getTestData("RequestedDealerPhoneNumber"));
			BrowserAction.clickandClear(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_EMAIL_XPATH);
			System.out.println("Dealer email: "+CommonPage.getTestData("RequestedDealerEmail"));
			BrowserAction.enterFieldValue(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_EMAIL_XPATH, CommonPage.getTestData("RequestedDealerEmail"));
		}
	}
	
	/**
	 * This method enter new dealer details on the dealer page.
	 * @throws Exception
	 */
	public static void enterRequestedDealerInfo() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_NAME_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_NAME_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_NAME_XPATH);
		System.out.println("Requested dealer name: " + CommonPage.getTestData("RequstedDealerName"));
		BrowserAction.enterFieldValue(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_NAME_XPATH, CommonPage.getTestData("RequstedDealerName"));
		System.out.println("Requested dealer address: " + CommonPage.getTestData("RequestedDealerStAddress"));
		BrowserAction.enterFieldValue(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_ADDRESS_XPATH, CommonPage.getTestData("RequestedDealerStAddress"));		
		System.out.println("Requested dealer zipcode: " + CommonPage.getTestData("RequestedDealerZip"));
		BrowserAction.enterFieldValue(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_ZIPCODE_XPATH, CommonPage.getTestData("RequestedDealerZip"));		
		System.out.println("Requested dealer contact name: " + CommonPage.getTestData("RequestedDealerContactName"));
		BrowserAction.enterFieldValue(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_CONTACT_NAME_XPATH, CommonPage.getTestData("RequestedDealerContactName"));
		System.out.println("Requested dealer phone number: " + CommonPage.getTestData("RequestedDealerPhoneNumber"));
		BrowserAction.enterFieldValue(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_PHONE_NUMBER_XPATH, CommonPage.getTestData("RequestedDealerPhoneNumber"));
		System.out.println("Requested dealer email id: " + CommonPage.getTestData("RequestedDealerEmail"));
		BrowserAction.enterFieldValue(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_EMAIL_XPATH, CommonPage.getTestData("RequestedDealerEmail"));
		BrowserAction.click(OrderingDealerPageEnum.ORDERING_REQUESTED_DEALER_FILL_CITY_XPATH);
	}

	/**
	 * This method gets details from dealer page 
	 * @param driver driver object 
	 * @throws Exception
	 * @lastModifiedBy mkhairanar
	 */
	public static void getDetailsFromDealerPage(WebDriver driver) throws Exception {
		String dealerType;
		String recommendedDealer= "Recomended Dealer";
		String differentDealer= "Select a Different Dealer";
		String newDealer= "Request a New Dealer";
		String backDealerChoice = "Back to Dealer Choice";
		List<WebElement> dealerOptionsElements = BrowserAction.getElements(OrderingDealerPageEnum.ORDERING_DEALER_DEALER_OPTIONS_CSS);
		StringBuilder sb = new StringBuilder();
		for(WebElement dealerOption: dealerOptionsElements) {
			sb.append(new StringBuilder(dealerOption.getText()));
		}
		dealerType = sb.toString();		
		if(dealerType.contains(newDealer)&&dealerType.contains(differentDealer)) {
			dealerType = recommendedDealer;
		} else if(dealerType.contains(newDealer)&&dealerType.contains(backDealerChoice)) {
			dealerType = differentDealer;
		} else if(dealerType.contains(backDealerChoice)&&dealerType.contains(differentDealer)) {
			dealerType = newDealer;
		} else {
			throw new NoIfElseBlockMatchedException();
		}
		switch(dealerType) {
			case "Recomended Dealer":
				if(CommonPage.getTestData("UserRole").contains("External")) {
					break;
				}else {
					setRecomendedDealer(driver);
				}
				break;
			case "Request a New Dealer":
				setRequestNewDealer(driver);
				break;
			case "Select a Different Dealer":
				setSelectDifferentDealer(driver);
				break;
			default: throw new InvalidSwitchCaseException(dealerType+" is a invalid Dealer Type");
		}
	}

	public static void setRecomendedDealer(WebDriver driver) throws Exception {		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRecommendedDealerObject();
		boolean checked;
		List<WebElement> tableRows = BrowserAction.getElements(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_TABLE_ROWS_XPATH);
		for(WebElement tableRow: tableRows) {
			checked = (boolean)js.executeScript("return arguments[0].checked", tableRow.findElement(By.xpath(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_RADIO_BUTTON_XPATH.getValue())));
			if(checked) {
				String tierNumber = tableRow.findElement(By.xpath(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_TIER_NUMBER_LABEL_XPATH.getValue())).getText();
				String dealerName = BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_DEALER_NAME_LABEL_XPATH).getText();
				String city = tableRow.findElement(By.xpath(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_CITY_LABEL_XPATH.getValue())).getText();
				String state = tableRow.findElement(By.xpath(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_STATE_LABEL_XPATH.getValue())).getText();
				String zipcode = tableRow.findElement(By.xpath(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_ZIP_CODE_LABEL_XPATH.getValue())).getText();
				String courtesyDeliveringFee = tableRow.findElement(By.xpath(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_COURTESY_DELIVERY_FEE_LABEL_XPATH.getValue())).getText();
				BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_DRIVING_DISTANCE_LABEL_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_DRIVING_DISTANCE_LABEL_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_DRIVING_DISTANCE_LABEL_XPATH);
				String drivingDistance = tableRow.findElement(By.xpath(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_DRIVING_DISTANCE_LABEL_XPATH.getValue())).getText();
				String manufacturerDealerCode = BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_MANUFACTURER_DEALER_CODE_LABEL_XPATH).getText();
				String maximumDeliveryDistance = BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_MAXIMUM_DELIVERY_DISTANCE_LABEL_XPATH).getText();
				String address = BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_RECOMMENDED_DEALER_ADDRESS_LABEL_XPATH).getText();
				CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRecommendedDealerObject().setTierNumber(tierNumber);
				CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRecommendedDealerObject().setDealerName(dealerName);
				CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRecommendedDealerObject().setCity(city);
				CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRecommendedDealerObject().setState(state);
				CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRecommendedDealerObject().setZipcode(zipcode);
				CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRecommendedDealerObject().setCourtesyDeliveringFee(courtesyDeliveringFee);
				CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRecommendedDealerObject().setDrivingDistance(drivingDistance);
				CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRecommendedDealerObject().setManufacturerDealerCode(manufacturerDealerCode);
				CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRecommendedDealerObject().setMaximumDeliveryDistance(maximumDeliveryDistance);
				CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRecommendedDealerObject().setAddress(address);
			}
		}
	   
	}

	public static void setSelectDifferentDealer(WebDriver driver) throws Exception {		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRequestNewDealerObject();
		String dealerName = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_NAME_XPATH));
		String city = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_CITY_XPATH));
		String state = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_STATE_XPATH));			
		String zipcode = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_ZIPCODE_XPATH));
		String address = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_ADDRESS_XPATH));
		String county = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_COUNTY_XPATH));
		//String country = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_COUNTRY_XPATH));
		String manufacturerDealerCode = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_MANUFACTURER_DEALER_CODE_XPATH));
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getSlectDifferentDealerObject().setDealerName(dealerName);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getSlectDifferentDealerObject().setCity(city);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getSlectDifferentDealerObject().setState(state);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getSlectDifferentDealerObject().setZipcode(zipcode);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getSlectDifferentDealerObject().setAddress(address);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getSlectDifferentDealerObject().setCounty(county);
		//CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getSlectDifferentDealerObject().setCountry(country);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getSlectDifferentDealerObject().setManufacturerDealerCode(manufacturerDealerCode);		
	}

	public static void setRequestNewDealer(WebDriver driver) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getSlectDifferentDealerObject();
		String dealerName = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_NAME_XPATH));
		String city = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_CITY_XPATH));
		String state = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_STATE_XPATH));
		String zipcode = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_ZIPCODE_XPATH));
		String address = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_ADDRESS_XPATH));
		String county = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_COUNTY_XPATH));
		String dealerContactName = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_CONTACT_NAME_XPATH));
		String phoneNumber = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_PHONE_NUMBER_XPATH));
		String email = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_EMAIL_XPATH));
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRequestNewDealerObject().setDealerName(dealerName);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRequestNewDealerObject().setCity(city);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRequestNewDealerObject().setState(state);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRequestNewDealerObject().setZipcode(zipcode);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRequestNewDealerObject().setAddress(address);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRequestNewDealerObject().setCounty(county);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRequestNewDealerObject().setDealerContactName(dealerContactName);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRequestNewDealerObject().setPhoneNumber(phoneNumber);
		CommonPage.getElementOrderObject().getDeliveringDealerTabObject().getRequestNewDealerObject().setEmail(email);
	}
	
	/**
     * This methods validates request a new dealer page
     * @lastModifiesBy sagrawal
     * @throws Exception
     */
    public static void validateRequestANewDealerPageLabels() throws Exception {
        if(CommonPage.getTestData("OrderType").equals("Dealer")){
        	BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_NAME_LABEL_CSS);
        	CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_NAME_LABEL_CSS),"Dealer Name *", "Dealer Name label not matched");
        	CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_ADDRESS_LABEL_CSS),"Address", "Address label not matched");
        	CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_COUNTY_LABEL_CSS),"County", "County label not matched");
        	CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_CITY_LABEL_CSS),"City *", "City label not matched");
        	CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_STATE_LABEL_CSS),"State *", "State label not matched");
        	CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_ZIPCODE_LABEL_CSS),"ZIP Code *", "ZIP Code label not matched");
        	CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_DEALER_CONTACT_NAME_LABEL_CSS),"Dealer Contact Name *", "Dealer Contact Name label not matched");
        	CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_PHONE_NUMBER_LABEL_CSS),"Phone Number *", "Phone Number label not matched");
        	CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_EMAIL_LABEL_CSS),"Email *", "Email label not matched");
        } else if(CommonPage.getTestData("OrderType").equals("Factory")) {
            BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_NAME_LABEL_CSS);
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_NAME_LABEL_CSS),"Dealer Name *", "Dealer Name label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_ADDRESS_LABEL_CSS),"Address", "Address label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_COUNTY_LABEL_CSS),"County", "County label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_CITY_LABEL_CSS),"City *", "City label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_STATE_LABEL_CSS),"State *", "State label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_ZIPCODE_LABEL_CSS),"ZIP Code *", "ZIP Code label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_DEALER_CONTACT_NAME_LABEL_CSS),"Dealer Contact Name", "Dealer Contact Name label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_PHONE_NUMBER_LABEL_CSS),"Phone Number", "Phone Number label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_EMAIL_LABEL_CSS),"Email", "Email label not matched");
        } else if(CommonPage.getTestData("OrderType").equals("Stock")&&CommonPage.getTestData("WhoWillLocateVehicle").equals("Client")) {
        	BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_NAME_LABEL_CSS);
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_NAME_LABEL_CSS),"Dealer Name *", "Dealer Name label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_ADDRESS_LABEL_CSS),"Address", "Address label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_COUNTY_LABEL_CSS),"County", "County label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_CITY_LABEL_CSS),"City *", "City label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_STATE_LABEL_CSS),"State *", "State label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_ZIPCODE_LABEL_CSS),"ZIP Code *", "ZIP Code label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_DEALER_DEALER_CONTACT_NAME_LABEL_CSS),"Dealer Contact Name", "Dealer Contact Name label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_PHONE_NUMBER_LABEL_CSS),"Phone Number", "Phone Number label not matched");
            CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_EMAIL_LABEL_CSS),"Email", "Email label not matched");
        } else {
        	System.out.println("Selected Order is Stock order and Element will locate the vehicle");
        }
    }

	/**
	 * This methods validates select a different dealer page
	 * @lastModifiesBy sagrawal
	 * @throws Exception
	 */
	public static void validateSelectADifferentDealerPageLabels() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_NAME_LABEL_CSS),"Dealer Name", "Dealer Name label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_ADDRESS_LABEL_CSS),"Address", "Address label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_COUNTY_LABEL_CSS),"County", "County label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_CITY_LABEL_CSS),"City", "City label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_STATE_LABEL_CSS),"State", "State label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_ZIPCODE_LABEL_CSS),"ZIP Code", "ZIP Code label not matched");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_MANUFACTURER_DEALER_CODE_LABEL_CSS),"Manufacturer Dealer Code", "Manufacturer Dealer Code label not matched");
	}
	
	/**
	 * This methods clicks on back to dealer's choice
	 * @lastModifiesBy sagrawal
	 * @throws Exception
	 */
	public static void clickBackToDealerChoice() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_BACK_TO_DEALER_CHOICE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DEALER_BACK_TO_DEALER_CHOICE_XPATH);
		BrowserAction.hoverOverElement(OrderingDealerPageEnum.ORDERING_DEALER_BACK_TO_DEALER_CHOICE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DEALER_BACK_TO_DEALER_CHOICE_XPATH);
		BrowserAction.click(OrderingDealerPageEnum.ORDERING_DEALER_BACK_TO_DEALER_CHOICE_XPATH);
	}
	
	/**
	 * This methods validates select a different dealer page
	 * @lastModifiesBy sagrawal
	 * @throws Exception
	 */
	public static void verifyBackToDealerChoice() throws Exception {
		if((Objects.isNull(CommonPage.getTestData("BackToDealerChoice")))?false:(CommonPage.getTestData("BackToDealerChoice").equals("Y")?true:false)){
			switch(CommonPage.getTestData("DealerSelectionOption")) {
				case "Request a New Dealer" : 
					OrderingDealerPage.clickBackToDealerChoice();
					BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_XPATH);
					BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_XPATH);
					OrderingDealerPage.clickRequestANewDealer();
					break;
				case "Select a Different Dealer" : 
					OrderingDealerPage.clickBackToDealerChoice();
					BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DEALER_REQUEST_A_NEW_DEALER_XPATH);
					BrowserWait.waitUntilElementIsDisplayed(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingDealerPageEnum.ORDERING_DEALER_SELECT_A_DIFFERENT_DEALER_XPATH);
					OrderingDealerPage.clickSelectADifferentDealer();
					break;
				default: throw new InvalidSwitchCaseException("Invalid dealer selection");
			}
		}
	}
	
	/**
	 * This method will verify Dealer tab present as per parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyAllowDeliverDealerIsYesOrNo(String allowYesOrNo) throws Exception {
		switch(allowYesOrNo) {
			case "Yes":
				if(BrowserAccess.getElement(OrderingDealerPageEnum.ORDERING_REQUESTED_DEALER_ID).isDisplayed() == true)
					System.out.println("Allow Delivery Dealer is displayed");
				else
					throw new OrderingErrorOccured("Allow Delivery Dealer is not displayed");
				break;
			case "No":
				BrowserVerify.verifyElementIsNotPresent(OrderingDealerPageEnum.ORDERING_REQUESTED_DEALER_ID);
				break;
			default: throw new InvalidSwitchCaseException(allowYesOrNo+" is a invalid option");
		}
	}
	
	/**
	 * This method will click on Save and Next button while craeting new order in FO
	 * @lastModifiedBy Deepika
	 * @throws Exception
	 */
	public static void clickSaveAndNext() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
			BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Application is not able click save and next");
		}
	}
}