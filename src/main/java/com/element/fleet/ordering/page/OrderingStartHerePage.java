package com.element.fleet.ordering.page;

import static org.testng.Assert.assertEquals;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.DbConnector;
import com.element.fleet.ordering.enums.OrderingStartHerePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.NoIfElseBlockMatchedException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingStartHerePage {

	/**
	 * This method verifies that start here page is loaded.
	 * @throws Exception
	 */
	public static void waitForStartHerepage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_TYPE_SECTION_ID);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_TYPE_SECTION_ID);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_TYPE_SECTION_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_INFORMATION_SECTION_ID);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_INFORMATION_SECTION_ID);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_INFORMATION_SECTION_ID);
	}
	
	/**
	 * This method enters start here page details depending on test data.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void moveToCreateOrderPage() throws Exception {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.selectOrderingMenuOption("Create Order");
		OrderingStartHerePage.enterStartHerePageDetails(CommonPage.getTestData("ApplicationURL"));
	}
	
	/**
	 * This method enters start here page details depending on test data.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterStartHerePageDetails(String foUrl) throws Exception {
		CommonPage.initializeElementOrderObject();
		OrderingCommonPage.checkAlertPopUp();
		OrderingStartHerePage.waitForStartHerepage();
		OrderingStartHerePage.enterOrderUnitNumber();
		OrderingStartHerePage.selectOrderType();
		//OrderingStartHerePage.addUsedUnit(foUrl);
		OrderingStartHerePage.getDetailsFromStartHerePage(WebDriverAccess.getDriver());
	}
	
	/**
	 * This method enters unit number.
	 * If test data file contains:
	 * A->Auto unit number is checked
	 * Blank->Random unit number is entered
	 * Random text->Provided random text is entered
	 * @throws Exception
	 * @modified by Mkhairnar
	 */
	public static void enterOrderUnitNumber() throws Exception {
		if(Objects.isNull(CommonPage.getTestData("UnitNumber"))) {
			OrderingStartHerePage.enterUnitNumber();
		} else if(CommonPage.getTestData("UnitNumber").equals("A")) {
			OrderingStartHerePage.autoUnitYes();
			System.out.println("Unit Number: Auto");
		} else if(!Objects.isNull(CommonPage.getTestData("UnitNumber"))) {
			BrowserAction.clickandClear(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_TEXTBOX_CSS);
			System.out.println("Unit Number: "+CommonPage.getTestData("UnitNumber"));
			BrowserAction.enterFieldValue(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_TEXTBOX_CSS, CommonPage.getTestData("UnitNumber"));
		}	else 
				throw new NoIfElseBlockMatchedException();
	}

	/**
	 * This method enters unit number on the start here page
	 * @throws Exception
	 */
	public static void enterUnitNumber() throws Exception {
		String unitNumber = new SimpleDateFormat("ddssSSS").format(new Date());
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_TEXTBOX_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_TEXTBOX_CSS);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_TEXTBOX_CSS);
		BrowserAction.clickandClear(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_TEXTBOX_CSS);
		System.out.println("Unit Number: "+"A"+unitNumber);
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_TEXTBOX_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_TEXTBOX_CSS);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_TEXTBOX_CSS);
		BrowserAction.enterFieldValue(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_TEXTBOX_CSS,"A"+unitNumber);
	}

	/**
	 * This method select the auto unit check box
	 * @throws Exception
	 */
	public static void autoUnitYes() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_AUTO_CSS);
		BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_AUTO_CSS);
	}

	/**
	 * This method clicks on the cancel button on order pahe.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnCancelButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_CANCEL_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_CANCEL_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_CANCEL_BUTTON_XPATH);
		BrowserAction.click(OrderingStartHerePageEnum.ORDERING_CANCEL_BUTTON_XPATH);
	}
	
	public static void selectOnCancelPopUpOkButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_CANCEL_POP_UP_OK_BUTTON_XPATH);
		BrowserAction.click(OrderingStartHerePageEnum.ORDERING_CANCEL_POP_UP_OK_BUTTON_XPATH);
	}
	
	/**
	 * This method selects the order type as provided in the test data sheet.
	 * Available options are:
	 * Factory->For Factory Order
	 * Dealer->For Dealer Order
	 * Stock->For Stock Order
	 * @throws Exception
	 */
	public static void selectOrderType() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_TYPE_FACTORY_ORDER_RADIO_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_TYPE_FACTORY_ORDER_RADIO_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_TYPE_FACTORY_ORDER_RADIO_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_TYPE_DEALER_ORDER_RADIO_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_TYPE_STOCK_ORDER_RADIO_BUTTON_ID);
		switch(CommonPage.getTestData("OrderType")) {
			case "Factory": 
				System.out.println("Order type: "+ CommonPage.getTestData("OrderType"));
				BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_TYPE_FACTORY_ORDER_RADIO_BUTTON_ID);
				OrderingStartHerePage.selectPoolOrderOption();
				break;
			case "Dealer" :
				System.out.println("Order type: "+ CommonPage.getTestData("OrderType"));
				BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_TYPE_DEALER_ORDER_RADIO_BUTTON_ID);
				OrderingStartHerePage.selectPoolOrderOption();
				OrderingStartHerePage.enterClientContactInformation();
				break;
			case "Stock" : 
				System.out.println("Order type: "+ CommonPage.getTestData("OrderType"));
				BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_TYPE_STOCK_ORDER_RADIO_BUTTON_ID);
				OrderingStartHerePage.selectWhoWillLocateVehicle();
				OrderingStartHerePage.enterCustomerPreApprovedPrice();
				OrderingStartHerePage.enterClientContactInformation();
				break;
			default: throw new InvalidSwitchCaseException("Invalid order type selected");
		}
	}
	
	/**
	 * This method enters the who will locate vehicle data
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectWhoWillLocateVehicle() throws Exception {
		String whoWillLocateVehicle = CommonPage.getTestData("WhoWillLocateVehicle");
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_LOCATE_VEHICLE_DROPDOWN_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_LOCATE_VEHICLE_DROPDOWN_CSS);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_LOCATE_VEHICLE_DROPDOWN_CSS);
		switch(whoWillLocateVehicle) {
			case "Element" : 
				CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_LOCATE_VEHICLE_DROPDOWN_CSS), "Locate Vehicle dropdown is not displayed");
				System.out.println("Who will locate vehicle: "+whoWillLocateVehicle);
				BrowserAction.selectDropdownOptionByText(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_LOCATE_VEHICLE_DROPDOWN_CSS, whoWillLocateVehicle);
				break;
			case "Client" : 
				CommonPage.assertElementHighlight(BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_LOCATE_VEHICLE_DROPDOWN_CSS), "Locate Vehicle dropdown is not displayed");
				System.out.println("Who will locate vehicle: "+whoWillLocateVehicle);
				BrowserAction.selectDropdownOptionByText(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_LOCATE_VEHICLE_DROPDOWN_CSS, whoWillLocateVehicle);
				break;
			default: throw new InvalidSwitchCaseException("Invalid who will be locating the vehicle option selected");
		}
	}
	
	/**
	 * This method enters customer pre approved price
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterCustomerPreApprovedPrice() throws Exception {		
		if(!Objects.isNull(CommonPage.getTestData("CustomerPreApprovedPrice"))) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_CUSTOMER_PRE_APPROVED_PRICE_ID);
			BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_CUSTOMER_PRE_APPROVED_PRICE_ID);
			BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_CUSTOMER_PRE_APPROVED_PRICE_ID);
			System.out.println("Customer pre approved price: " + CommonPage.getTestData("CustomerPreApprovedPrice"));
			BrowserAction.enterFieldValue(OrderingStartHerePageEnum.ORDERING_START_HERE_CUSTOMER_PRE_APPROVED_PRICE_ID, CommonPage.getTestData("CustomerPreApprovedPrice"));
		}		
	}

	public static void selectPoolOrderOption() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_YES_RADIO_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_YES_RADIO_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_YES_RADIO_BUTTON_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_NO_RADIO_BUTTON_ID);
		if(CommonPage.getTestData("PoolOrderOption")==null) {
			BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_NO_RADIO_BUTTON_ID);
		} else {
			BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_YES_RADIO_BUTTON_ID);
			OrderingStartHerePage.selectPoolType();
		}
	}

	public static void selectPoolType() throws Exception {
		String poolType = CommonPage.getTestData("PoolOrderOption");
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_DROPDOWN_ID);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_DROPDOWN_ID);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_DROPDOWN_ID);
		CommonPage.assertElementHighlight(BrowserAccess.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_DROPDOWN_ID), "Pool Type dropdown is not displayed");
		System.out.println("Pool order option: "+ poolType);
		List<String> expectedPoolTypes = Arrays.asList("Select one...","Bailment", "OEM", "Vendor");
		List<String> actualPoolTypes = new ArrayList<>();
		Select options = new Select(BrowserAccess.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_DROPDOWN_ID));
		List<WebElement> optionsElement = options.getOptions();
		for(WebElement e: optionsElement){
			actualPoolTypes.add(e.getText());
		}
		assertEquals(actualPoolTypes, expectedPoolTypes, "Pool types options are not equal");
		BrowserAction.selectDropdownOptionByText(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_DROPDOWN_ID, poolType);	
	}

	public static void enterClientContactInformation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_FIRST_NAME_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_FIRST_NAME_CSS);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_FIRST_NAME_CSS);
		String firstName = "TEST"+RandomStringUtils.randomAlphabetic(6);
		System.out.println("CCI First name: "+firstName);
		BrowserAction.enterFieldValue(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_FIRST_NAME_CSS, firstName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_LAST_NAME_CSS);
		String lasttName = "TEST"+RandomStringUtils.randomAlphabetic(6);
		System.out.println("CCI Last name: "+"TEST"+lasttName);
		BrowserAction.enterFieldValue(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_LAST_NAME_CSS, firstName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_PHONE_ID);
		String phoneNumber = RandomStringUtils.randomNumeric(10);
		System.out.println("CCI Phone number: "+phoneNumber);
		BrowserAction.enterFieldValue(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_PHONE_ID, phoneNumber);
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_EMAIL_CSS);
		String emailId = RandomStringUtils.randomAlphabetic(6) + "@testmail.com";
		System.out.println("CCI EmailId: "+emailId);
		BrowserAction.enterFieldValue(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_EMAIL_CSS, emailId);
	}
	
	/**
	 * This methods clicks on used unit as yes
	 * @throws Exception
	 */
	public static void selectAddUsedUnitYes() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_YES_RADIO_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_YES_RADIO_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_YES_RADIO_BUTTON_ID);
		BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_YES_RADIO_BUTTON_ID);
	}
	
	
	/**
	 * This method adds used unit number on the start here page.
	 * If test data file contains:
	 * Blank->No need to enter 
	 * A->Get value from DB
	 * Random String->Random String will be entered
	 * @param url
	 * @throws Exception
	 */
	public static void addUsedUnit(String foUrl) throws Exception {
		if(CommonPage.getTestData("UsedUnitNumber")==null) {
			System.out.println("Used unit: NA");
		} else if(CommonPage.getTestData("UsedUnitNumber").equals("A")) {
			OrderingStartHerePage.selectAddUsedUnitYes();
			OrderingStartHerePage.inputUsedUnit(foUrl);
			OrderingStartHerePage.selectWhoToSellUsedUnit();
		} else if(!CommonPage.getTestData("UsedUnitNumber").equals("A")) {
			OrderingStartHerePage.selectAddUsedUnitYes();
			OrderingStartHerePage.inputUsedUnit(foUrl);
			OrderingStartHerePage.selectWhoToSellUsedUnit();
		} else {
			throw new NoIfElseBlockMatchedException();
		}		
	}
	
	/**
	 * This method adds used unit number on the start here page.
	 * Note: FA value is hard coded currently as this variable has to be passed from all the scripts.
	 * @lastModifiedBy mkhairnar
	 * @param foUrl
	 * @throws Exception
	 */
	public static void inputUsedUnit(String foUrl) throws Exception {
		if(CommonPage.getTestData("UserRole").contains("External Full Access"))
			CommonPage.updateClientDropdown();
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_SEARCH_TEXTBOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_SEARCH_TEXTBOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_SEARCH_TEXTBOX_ID);
		BrowserAction.clickandClear(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_SEARCH_TEXTBOX_ID);
		String usedUnitNumber = DbConnector.getActiveUsedUnit("FA", CommonPage.getTestData("ClientNumber"));
		System.out.println("Used unit number: "+usedUnitNumber);
		BrowserAction.enterFieldValue(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_SEARCH_TEXTBOX_ID, usedUnitNumber);
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_SEARCH_SUGGESTED_RESULT_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_SEARCH_SUGGESTED_RESULT_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_SEARCH_SUGGESTED_RESULT_XPATH);
		BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_SEARCH_SUGGESTED_RESULT_XPATH);
	}
	
	/**
	 * This method clicks on Save and next button.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickSaveAndNext() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
		BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
	}
	
	/**
	 * This method will skip click action on Save and next button if order type is Stock and Who to locate is Element.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickSaveAndNextForStockWithElementLocate() throws Exception {
		if(!(CommonPage.getTestData("OrderType").equals("Stock")&&CommonPage.getTestData("WhoWillLocateVehicle").equals("Element"))) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
			BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
			BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
			BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
		} else {
			BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
			BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
			BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
			BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
		}
	}

	/**
	 * This method select the used unit provided in the test data file.
	 * @throws Exception
	 */
	public static void selectWhoToSellUsedUnit() throws Exception {
		if(!Objects.isNull(CommonPage.getTestData("UsedUnitWhoToSell"))) {
			switch(CommonPage.getTestData("UsedUnitWhoToSell")) {
			case "Element":
				OrderingStartHerePage.selectWhoToSellUsedUnit("Element");
				break;
			case "Notify Competitor":
				OrderingStartHerePage.selectWhoToSellUsedUnit("Notify Competitor");
				break;
			case "Reference Only":
				OrderingStartHerePage.selectWhoToSellUsedUnit("Reference Only");
				break;
			default: throw new InvalidSwitchCaseException("Invalid who to sell used unit value provided in the test data file.");
			}
		}
	}
	
	/**
	 * This method selects the provided used unit value.
	 * @lastModifiedBy shisingh
	 * @param whoToSellUsedUnit
	 * @throws Exception
	 */
	public static void selectWhoToSellUsedUnit(String whoToSellUsedUnit) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_WHO_TO_SELL_USED_UNIT_DROPDOWN_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_WHO_TO_SELL_USED_UNIT_DROPDOWN_CSS);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_WHO_TO_SELL_USED_UNIT_DROPDOWN_CSS);
		System.out.println("Who to sell used unit: " + whoToSellUsedUnit);
		BrowserAction.selectDropdownOptionByText(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_WHO_TO_SELL_USED_UNIT_DROPDOWN_CSS, whoToSellUsedUnit);
	}	

	public static void getDetailsFromStartHerePage(WebDriver driver) throws Exception {
		OrderingStartHerePage.setOrderBasicDetails(driver);
		OrderingStartHerePage.setUnitNumber(driver);
		OrderingStartHerePage.setOrderTypeDetails(driver);
		OrderingStartHerePage.setUsedUnitDetails(driver);
	}
	
	public static void setOrderBasicDetails(WebDriver driver) throws Exception {		
		String corpCode = BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_CORP_CODE_ID).getText();
		String clientName = BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_CLIENT_NAME_CSS).getText();
		CommonPage.getElementOrderObject().getStartHereTabObject().setCorpCode(corpCode);
		CommonPage.getElementOrderObject().getStartHereTabObject().setClientName(clientName.replaceAll("\n", ""));
	}
	
	public static void setUnitNumber(WebDriver driver) throws Exception {
		boolean autoAssignCheckbox = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if(!BrowserAccess.getElements(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_AUTO_NAME).isEmpty()) {
			autoAssignCheckbox = (boolean)js.executeScript("return arguments[0].checked", BrowserAccess.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_AUTO_NAME));
		}
		String unitNumber = (String)js.executeScript("return arguments[0].value",BrowserAccess.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_TEXTBOX_CSS));
		if(autoAssignCheckbox) {			
			CommonPage.getElementOrderObject().getStartHereTabObject().setAutoAssignNewUnitNumber(true);
		} else {
			CommonPage.getElementOrderObject().getStartHereTabObject().setNewUnitNumber((unitNumber==null)?"":unitNumber);
		}		
	}
	
	public static void setOrderTypeDetails(WebDriver driver) throws Exception {		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		boolean factoryOrder = (boolean)js.executeScript("return arguments[0].checked", BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_TYPE_FACTORY_ORDER_RADIO_BUTTON_ID));
		boolean dealerOrder = (boolean)js.executeScript("return arguments[0].checked", BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_ORDER_TYPE_DEALER_ORDER_RADIO_BUTTON_ID));
		if(factoryOrder) {
			CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsFactoryOrder();
			boolean poolOrder = (boolean)js.executeScript("return arguments[0].checked", BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_YES_RADIO_BUTTON_ID));
			if(poolOrder) {
				String poolType = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_DROPDOWN_ID));
				switch(poolType) {
				case "Bailment":
					CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsFactoryOrder().setPoolType(poolType);
					break;
				case "OEM":
					CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsFactoryOrder().setPoolType(poolType);
					break;
				case "Vendor":
					CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsFactoryOrder().setPoolType(poolType);
					break;
				default:
					throw new  InvalidSwitchCaseException(poolType+" is a invalid Pool Type");
				}
			}
		} else if(dealerOrder) {
			CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder();
			boolean poolOrder = (boolean)js.executeScript("return arguments[0].checked", BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_YES_RADIO_BUTTON_ID));
			if(poolOrder) {
				String poolType = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_DROPDOWN_ID));
				switch(poolType) {
				case "Bailment":
					CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().setPoolType(poolType);
					break;
				case "OEM":
					CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().setPoolType(poolType);
					break;
				case "Vendor":
					CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().setPoolType(poolType);
					break;
				default:
					throw new  InvalidSwitchCaseException(poolType+" is a invalid Pool Type");
				}
			}
			String cciFirstName = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_FIRST_NAME_CSS));
			String cciLastName = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_LAST_NAME_CSS));
			String cciPhone = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_PHONE_ID));
			String cciPhoneExt = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_PHONE_EXT_NAME));
			String cciEmail = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_EMAIL_CSS));
			CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getClientContactInformationObject().setFirstName(cciFirstName);
			CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getClientContactInformationObject().setLastName(cciLastName);
			CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getClientContactInformationObject().setPhone(cciPhone);
			CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getClientContactInformationObject().setPhoneExt(cciPhoneExt);
			CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getClientContactInformationObject().setEmail(cciEmail);
		} else {
			CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsStockOrder();
			Select select = new Select(BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_DEALER_ORDER_WHO_WILL_LOCATE_VEHICLE_XPATH));
			String whoLocateVehicle = select.getFirstSelectedOption().getText().trim();
			switch(whoLocateVehicle) {
			case "Client":
				CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsStockOrder().setWhoWillBeLocatingVehicleTo(whoLocateVehicle);
				break;
			case "Element":
				CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsStockOrder().setWhoWillBeLocatingVehicleTo(whoLocateVehicle);
				break;
			default:
				throw new  InvalidSwitchCaseException(whoLocateVehicle+" is a invalid Who will locate vehicle");
			}
			String cciFirstName = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_FIRST_NAME_CSS));
			String cciLastName = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_LAST_NAME_CSS));
			String cciPhone = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_PHONE_ID));
			String cciPhoneExt = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_PHONE_EXT_NAME));
			String cciEmail = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_STOCK_ORDER_EMAIL_CSS));
			CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsStockOrder().getClientContactInformationObject().setFirstName(cciFirstName);
			CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsStockOrder().getClientContactInformationObject().setLastName(cciLastName);
			CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsStockOrder().getClientContactInformationObject().setPhone(cciPhone);
			CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsStockOrder().getClientContactInformationObject().setPhoneExt(cciPhoneExt);
			CommonPage.getElementOrderObject().getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsStockOrder().getClientContactInformationObject().setEmail(cciEmail);
		}
		
	}
	
	public static void setUsedUnitDetails(WebDriver driver) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		boolean factoryOrder = (boolean)js.executeScript("return arguments[0].checked", BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_YES_RADIO_BUTTON_ID));
		if(factoryOrder) {
			CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setAddUsedUnit(true);
			String fleetName = BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_FLEET_NAME_CSS).getText();
			String unitNumber = BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_UNIT_NUMBER_CSS).getText();
			String year = BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_YEAR_CSS).getText();
			String make = BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_MAKE_CSS).getText();
			String model = BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_MODAL_CSS).getText();
			String trim = BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_TRIM_CSS).getText();
			String vin = BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_VIN_CSS).getText();
			String whoToSellUsedUnit = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_WHO_TO_SELL_USED_UNIT_DROPDOWN_CSS));
			CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setFleetName(fleetName);
			CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setUnitNumber(unitNumber);
			CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setYear(year);
			CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setMake(make);
			CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setModel(model);
			CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setTrim(trim);
			CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setVin(vin);			
			switch(whoToSellUsedUnit) {
			case "Element":
				CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setWhoToSellUsedUnit(whoToSellUsedUnit);
				break;
			case "Notify Competitor":
				CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setWhoToSellUsedUnit(whoToSellUsedUnit);
				String competitorList = (String)js.executeScript("return arguments[0].value",BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_COMPETITOR_LIST_DROPDOWN_CSS));
				switch(competitorList) {
					case "0":
						CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setCompetitorList(competitorList);
						break;
					case "ARI":
						CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setCompetitorList(competitorList);
						break;
					case "BBL Fleet Services":
						CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setCompetitorList(competitorList);
						break;
					case "DONLEN":
						CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setCompetitorList(competitorList);
						break;
					case "EMKAY":
						CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setCompetitorList(competitorList);
						break;
					case "ENTERPRISE":
						CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setCompetitorList(competitorList);
						break;
					case "LEASEPLAN":
						CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setCompetitorList(competitorList);
						break;
					case "MIKE ALBERT LEASING":
						CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setCompetitorList(competitorList);
						break;
					case "MRC RAIL":
						CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setCompetitorList(competitorList);
						break;
					case "MERCHANTS":
						CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setCompetitorList(competitorList);
						break;
					case "WHEELS":
						CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setCompetitorList(competitorList);
						break;
					default:
						throw new  InvalidSwitchCaseException(competitorList+" is a invalid competitor list");
				}
				break;
			case "Reference Only":
				CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setWhoToSellUsedUnit(whoToSellUsedUnit);
				break;
			default:
				throw new  InvalidSwitchCaseException(whoToSellUsedUnit+" is a invalid who to sell used unit");
			}
		boolean copyDriveAndBillingDataToNewUnit = (boolean)js.executeScript("return arguments[0].checked", BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_COPY_USED_UNIT_DATA_YES_XPATH));					
		if(copyDriveAndBillingDataToNewUnit)
			CommonPage.getElementOrderObject().getStartHereTabObject().getUsedUnitInformationSectionObject().setCopyDriveAndBillingDataToNewUnit(true);
		}
	}
	
	/**
	 * This method will verify auto unit check box displayed or not as per parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyAutoUnit(String radioButtonInput) throws Exception {
		switch(radioButtonInput) {
			case "Yes":
				if(BrowserAccess.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_AUTO_CSS).isDisplayed() == true)
					System.out.println("Auto unit check box displayed");
				else
					throw new OrderingErrorOccured("Auto unit check box not displayed");
				break;
			case "No":
				BrowserVerify.verifyElementIsNotPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_UNIT_NUMBER_AUTO_CSS);
				break;
			default: throw new InvalidSwitchCaseException(radioButtonInput+" is a invalid option");
		}
	}
	
	/**
	 * This method will verify who to sell drop down option as per fleet preference
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyWhoToSellAsPerFleetPreference() throws Exception {
		OrderingStartHerePage.selectAddUsedUnitYes();
		OrderingHomePage.changeClientFromDropDown();
		OrderingStartHerePage.inputUsedUnit(CommonPage.getTestData("ApplicationURL"));
		CommonPage.verifyDropDownSelectedValue(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_WHO_TO_SELL_USED_UNIT_DROPDOWN_CSS, OrderingFOFleetPreferencesPage.whoToSellListProvided().get(1), "Who To Sell");
		CommonPage.verifyDropDownSelectedValue(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_COMPETITOR_LIST_DROPDOWN_CSS, CommonPage.getTestData("CompetitorToNotify"), "Notify Competitor");
		selectUsedUnitNo();
	}
	
	/**
	 * This method will verify updated who to sell drop down option as per fleet preference
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyUpdatedWhoToSellAsPerFleetPreference() throws Exception {
		OrderingStartHerePage.selectAddUsedUnitYes();
		OrderingHomePage.changeClientFromDropDown();
		OrderingStartHerePage.inputUsedUnit(CommonPage.getTestData("ApplicationURL"));
		CommonPage.verifyDropDownSelectedValue(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_WHO_TO_SELL_USED_UNIT_DROPDOWN_CSS, OrderingFOFleetPreferencesPage.whoToSellListProvided().get(2), "Who To Sell");
		selectUsedUnitNo();
	}
	
	/**
	 * This method will select used unit number on start here page
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectUsedUnitNo() throws Exception {
		CommonPage.waitForElementToLoad(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_NO_XPATH, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_NO_XPATH);
	}
	
	/**
	 * This method selects build from scratch radio
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void selectPoolOrderNo() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_NO_RADIO_BUTTON_ID);
		BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_POOL_ORDER_NO_RADIO_BUTTON_ID);
	}
	
	/**
	 * This method selects add used unit as No
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void selectAddUsedUnitNo() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_NO_RADIO_BUTTON_ID);
		BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_USED_UNIT_NO_RADIO_BUTTON_ID);
	}
	
}