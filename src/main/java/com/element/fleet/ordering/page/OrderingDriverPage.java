package com.element.fleet.ordering.page;

import static org.testng.Assert.assertEquals;
import java.util.Objects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.DbConnector;
import com.element.fleet.ordering.enums.OrderingDriverPageEnum;
import com.element.fleet.ordering.enums.OrderingStartHerePageEnum;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingDriverPage {
	
	/**
	 * This method waits for driver page to load.
	 * @throws Exception
	 */
	public static void waitForDriverPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID);
	}
	
	/**
	 * This method enters driver data on the driver page
	 * @throws Exception
	 */
	public static void enterDriverData() throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoValidationError();
		OrderingDriverPage.waitForDriverPage();
		OrderingDriverPage.selectDriver();
		OrderingCommonPage.checkAlertPopUp();
		OrderingDriverPage.waitForDriverData();
		OrderingDriverPage.validateDriverData();
		OrderingDriverPage.addDriverDeliveryAddress();
		OrderingDriverPage.getDetailsFromDriverPage(WebDriverAccess.getDriver());
		System.out.println("Order id: " + CommonPage.getElementOrderObject().getDriverTabObject().getOrderID());
	}

	/**
	 * This method enters drier name in the driver field and clicks the first displayed driver.
	 * @lastModifiedBY djawale
	 * @throws Exception
	 */
	public static void selectDriver() throws Exception {
		if(CommonPage.getTestData("UserRole").contains("External")) {
			CommonPage.updateClientDropdown();
		}
		BrowserWait.waitUntilElementIsDisplayed(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID);
		BrowserAction.clickandClear(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID);
		if((CommonPage.getTestData("DriverEmpId")==null)) {
			String driverEmployeeID = null;
			if(!(Objects.isNull(CommonPage.getTestData("DealerSelectionOption")))&&CommonPage.getTestData("DealerSelectionOption").equals("Recommended")) {
				driverEmployeeID = DbConnector.getActiveDriverWithActiveBreakdownOfSpecificState("TX", "FA", CommonPage.getTestData("ClientNumber"));
			} else {
				driverEmployeeID = DbConnector.getActiveDriverWithActiveBreakdown("FA", CommonPage.getTestData("ClientNumber"));
			}
			System.out.println("Driver Emp ID: " + driverEmployeeID);
			BrowserAction.enterFieldValue(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID, driverEmployeeID);
		} else {
			System.out.println("Driver Emp ID: " + CommonPage.getTestData("DriverName"));
			BrowserAction.enterFieldValue(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID, CommonPage.getTestData("DriverName"));
		}		
		BrowserWait.waitUntilElementIsDisplayed(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_SUGGESTION_FIRST_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_SUGGESTION_FIRST_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_SUGGESTION_FIRST_XPATH);
		BrowserAction.click(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_SUGGESTION_FIRST_XPATH);
	}

	/*
	 * @lastModifiedBY sagrawal
	 * @param driver name
	 * this method selects driver on driver page
	 */
	public static void selectDriver(String driverName) throws Exception {
			BrowserWait.waitUntilElementIsDisplayed(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID);
			BrowserAction.clickandClear(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID);
			BrowserAction.enterFieldValue(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID, driverName);
			System.out.println(driverName);
			BrowserWait.waitUntilElementIsDisplayed(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_SUGGESTION_FIRST_XPATH);
			BrowserAction.click(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_SUGGESTION_FIRST_XPATH);
	}

	/**
	 * This method will enter delivery address details only when checkbox is clicked.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void addDriverDeliveryAddress() throws Exception {
		if(OrderingDriverPage.clickAddDeliveryAddress()) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_FIRST_NAME_TEXTBOX_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_FIRST_NAME_TEXTBOX_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_FIRST_NAME_TEXTBOX_XPATH);			
			System.out.println("DA first name: "+CommonPage.getTestData("DeliveryAddressFirstName"));
			BrowserAction.clickandClear(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_FIRST_NAME_TEXTBOX_XPATH);
			BrowserAction.enterFieldValue(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_FIRST_NAME_TEXTBOX_XPATH, CommonPage.getTestData("DeliveryAddressFirstName"));			
			System.out.println("DA last name: "+CommonPage.getTestData("DeliveryAddressLastName"));
			BrowserAction.clickandClear(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_LAST_NAME_TEXTBOX_XPATH);
			BrowserAction.enterFieldValue(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_LAST_NAME_TEXTBOX_XPATH, CommonPage.getTestData("DeliveryAddressLastName"));			
			System.out.println("DA phone: "+CommonPage.getTestData("DeliveryAddressPhone"));
			BrowserAction.clickandClear(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_PHONE_TEXTBOX_XPATH);
			BrowserAction.enterFieldValue(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_PHONE_TEXTBOX_XPATH, CommonPage.getTestData("DeliveryAddressPhone"));			
			System.out.println("DA address1: "+CommonPage.getTestData("DeliveryAddressAddress1"));
			BrowserAction.clickandClear(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_ADDRESS1_TEXTBOX_XPATH);
			BrowserAction.enterFieldValue(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_ADDRESS1_TEXTBOX_XPATH, CommonPage.getTestData("DeliveryAddressAddress1"));			
			System.out.println("DA country: "+CommonPage.getTestData("DeliveryAddressCountry"));
			BrowserAction.clickandClear(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_COUNTRY_TEXTBOX_XPATH);
			BrowserAction.enterFieldValue(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_COUNTRY_TEXTBOX_XPATH, CommonPage.getTestData("DeliveryAddressCountry"));			
			System.out.println("DA zipcode: "+CommonPage.getTestData("DeliveryAddressZipcode"));
			BrowserAction.clickandClear(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_ZIPCODE_XPATH);
			BrowserAction.enterFieldValue(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_ZIPCODE_XPATH, CommonPage.getTestData("DeliveryAddressZipcode"));
			BrowserAction.click(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_FILL_CITY_STATE_COUNTY_BUTTON_ID);		
		}
	}

	/**
	 * This method clicks on the delivery address and return boolean true value only when DeliveryAddressFirstName value is provided in test data file.
	 * @lastModifiedBy shisingh
	 * @return
	 * @throws Exception
	 */
	public static boolean clickAddDeliveryAddress() throws Exception {
		if(CommonPage.getTestData("DeliveryAddressFirstName")==null) {
			return false;
		} else {
			BrowserWait.waitUntilElementIsDisplayed(OrderingDriverPageEnum.ORDERING_DRIVER_DELIVERY_ADDRESS_ID);
			BrowserVerify.verifyElementIsPresent(OrderingDriverPageEnum.ORDERING_DRIVER_DELIVERY_ADDRESS_ID);
			BrowserVerify.verifyElementEnabled(OrderingDriverPageEnum.ORDERING_DRIVER_DELIVERY_ADDRESS_ID);
			BrowserAction.click(OrderingDriverPageEnum.ORDERING_DRIVER_DELIVERY_ADDRESS_ID);
			return true;			
		}
	}	
	
	/**
	 * This method waits for driver data to be displayed.
	 * @throws Exception
	 */
	public static void waitForDriverData() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingDriverPageEnum.ORDERING_DRIVER_FIRST_NAME_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingDriverPageEnum.ORDERING_DRIVER_FIRST_NAME_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingDriverPageEnum.ORDERING_DRIVER_FIRST_NAME_XPATH);
	}
	
	/**
	 * This method verify weather the select driver data is displayed or not.
	 * @throws Exception
	 */
	public static void validateDriverData() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingDriverPageEnum.ORDERING_DRIVER_FIRST_NAME_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingDriverPageEnum.ORDERING_DRIVER_FIRST_NAME_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingDriverPageEnum.ORDERING_DRIVER_FIRST_NAME_XPATH);
		Assert.assertEquals(false, Objects.isNull(BrowserAccess.getElementText(OrderingDriverPageEnum.ORDERING_DRIVER_FIRST_NAME_XPATH)));
	}

	public static void getDetailsFromDriverPage(WebDriver driver) throws Exception {
		OrderingDriverPage.setOrderIdAndUnitNumber(driver);
		OrderingDriverPage.setDriverInformation(driver);
		OrderingDriverPage.setGaragingAndDeliveryAddress(driver);
	}
	
	public static void setOrderIdAndUnitNumber(WebDriver driver) throws Exception {
		CommonPage.getElementOrderObject().getDriverTabObject();
		String orderID = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_ORDER_ID_LABEL_ID).getText();
		CommonPage.getElementOrderObject().getDriverTabObject().setOrderID(orderID);
		if(CommonPage.getElementOrderObject().getStartHereTabObject().isAutoAssignNewUnitNumber()) {
			String newUnitNumber = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_LABEL_ID).getText();
			CommonPage.getElementOrderObject().getStartHereTabObject().setNewUnitNumber(newUnitNumber);
		}
	}

	public static void setDriverInformation(WebDriver driver) throws Exception {
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject();
		String firstName = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_FIRST_NAME_LABEL_XPATH).getText();
		String middleName = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_MIDDLE_NAME_LABEL_XPATH).getText();
		String lastName = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_LAST_NAME_LABEL_XPATH).getText();
		String employeeID = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_EMPLOYEE_ID_LABEL_XPATH).getText();
		String email = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_EMAIL_LABEL_XPATH).getText();
		String primaryPhone = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_PRIMARY_PHONE_LABEL_XPATH).getText();
		String breakdown = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_BREAKDOWN_LABEL_XPATH).getText();
		String address1 = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_ADDRESS1_LABEL_XPATH).getText();
		String address2 = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_ADDRESS2_LABEL_XPATH).getText();
		String county = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_COUNTY_LABEL_XPATH).getText();
		String country = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_COUNTRY_LABEL_XPATH).getText();
		String city = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_CITY_LABEL_XPATH).getText();
		String state = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_STATE_LABEL_XPATH).getText();
		String zipcode = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_ZIPCODE_LABEL_XPATH).getText();
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setFirstName(firstName);
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setMiddleName(middleName);
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setLastName(lastName);
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setEmployeeID(employeeID);
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setEmail(email);
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setPrimaryPhone(primaryPhone);
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setBreakdown(breakdown);
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setAddress1(address1);
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setAddress2(address2);
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setCounty(county);
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setCountry(country);
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setCity(city);
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setState(state);
		CommonPage.getElementOrderObject().getDriverTabObject().getDriverInformationSectionObject().setZipcode(zipcode);
	}

	public static void setGaragingAndDeliveryAddress(WebDriver driver) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		CommonPage.getElementOrderObject().getDriverTabObject().getGaragingAddressSectionObject();
		String address1 = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_GARAGING_ADDRESS_ADDRESS1_INPUT_XPATH));
		String address2 = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_GARAGING_ADDRESS_ADDRESS2_INPUT_XPATH));
		String county = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_GARAGING_ADDRESS_COUNTY_INPUT_XPATH));
		String country = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_GARAGING_ADDRESS_COUNTRY_INPUT_XPATH));
		String city = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_GARAGING_ADDRESS_CITY_INPUT_XPATH));
		String state = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_GARAGING_ADDRESS_STATE_DROPDOWN_XPATH));
		String zipcode = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_GARAGING_ADDRESS_ZIPCODE_INPUT_XPATH));
		boolean deliveryAddress = BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_CHECKBOX_XPATH).isSelected();
		CommonPage.getElementOrderObject().getDriverTabObject().getGaragingAddressSectionObject().setAddress1(address1);
		CommonPage.getElementOrderObject().getDriverTabObject().getGaragingAddressSectionObject().setAddress2(address2);
		CommonPage.getElementOrderObject().getDriverTabObject().getGaragingAddressSectionObject().setCounty(county);
		CommonPage.getElementOrderObject().getDriverTabObject().getGaragingAddressSectionObject().setCountry(country);
		CommonPage.getElementOrderObject().getDriverTabObject().getGaragingAddressSectionObject().setCity(city);
		CommonPage.getElementOrderObject().getDriverTabObject().getGaragingAddressSectionObject().setState(state);
		CommonPage.getElementOrderObject().getDriverTabObject().getGaragingAddressSectionObject().setZipcode(zipcode);
		if(deliveryAddress) {
			CommonPage.getElementOrderObject().getDriverTabObject().getGaragingAddressSectionObject().setDeliveryAddress(true);
			CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject();
			String deliveryFirstName = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_FIRST_NAME_TEXTBOX_XPATH));
			String deliveryMiddleName = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_MIDDLE_NAME_TEXTBOX_XPATH));
			String deliveryLastName = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_LAST_NAME_TEXTBOX_XPATH));
			String deliveryPhone = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_PHONE_TEXTBOX_XPATH));
			String deliveryExtension = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_EXTENSION_TEXTBOX_XPATH));
			String deliveryAddress1 = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_ADDRESS1_TEXTBOX_XPATH));
			String deliveryAddress2 = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_ADDRESS2_TEXTBOX_XPATH));
			String deliveryCounty = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_COUNTY_TEXTBOX_XPATH));
			String deliveryCountry = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_COUNTRY_TEXTBOX_XPATH));
			String deliveryCity = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_CITY_TEXTBOX_XPATH));
			String deliveryState = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_STATE_DROPDOWN_XPATH));
			String deliveryZipcode = (String)js.executeScript("return arguments[0].value", BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DELIVERY_ADDRESS_ZIPCODE_XPATH));
			CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().setFirstName(deliveryFirstName);
			CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().setMiddleName(deliveryMiddleName);
			CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().setLastName(deliveryLastName);
			CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().setPhone(deliveryPhone);
			CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().setExtension(deliveryExtension);
			CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().setAddress1(deliveryAddress1);
			CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().setAddress2(deliveryAddress2);
			CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().setCounty(deliveryCounty);
			CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().setCountry(deliveryCountry);
			CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().setCity(deliveryCity);
			CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().setState(deliveryState);
			CommonPage.getElementOrderObject().getDriverTabObject().getDeliveryAddressSectionObject().setZipcode(deliveryZipcode);
		}

	}

	/**
	 * This method clicks on the save and exit button
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickSaveAndExit() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_EXIT_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_EXIT_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_EXIT_BUTTON_ID);
		BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_EXIT_BUTTON_ID);
	}
	
	/**
	 * This method will verify Driver Tab Label as per fleet preferences
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDriverTabLabelAsPerFleetPreferences(String driverTabLabel) throws Exception{
		CommonPage.waitForElementToLoad(OrderingDriverPageEnum.ORDERING_DRIVER_TAB_LABEL_XPATH, CommonPage.getTestData("WaitTime"));
		assertEquals(BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_TAB_LABEL_XPATH).getText().trim(), 
				driverTabLabel, "Driver Tab Label is not as per fleet preferences");
	}	
	
	/**
	 * This method will verify Driver First Name as per fleet preferences
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDriverFirstNameAsPerFleetPreferences(String driverFirstName) throws Exception{
		CommonPage.waitForElementToLoad(OrderingDriverPageEnum.ORDERING_DRIVER_FIRST_NAME_LABEL_XPATH, CommonPage.getTestData("WaitTime"));
		assertEquals(BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_FIRST_NAME_LABEL_XPATH).getText().trim(), 
				driverFirstName, "Driver First Name Label is not as per fleet preferences");
	}
	
	/**
	 * This method will verify Driver Last Name as per fleet preferences
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDriverLastNameAsPerFleetPreferences(String driverLastName) throws Exception{
		CommonPage.waitForElementToLoad(OrderingDriverPageEnum.ORDERING_DRIVER_LAST_NAME_LABEL_XPATH, CommonPage.getTestData("WaitTime"));
		assertEquals(BrowserAction.getElement(OrderingDriverPageEnum.ORDERING_DRIVER_LAST_NAME_LABEL_XPATH).getText().trim(), 
				driverLastName, "Driver Last Name Label is not as per fleet preferences");
	}	
	
	/**
	 * This method will verify Driver Data as per fleet preferences
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDriverDataAsPerFleetPreferences() throws Exception{
		verifyDriverTabLabelAsPerFleetPreferences(CommonPage.getTestData("CustomColumn2"));
		verifyDriverFirstNameAsPerFleetPreferences(CommonPage.getTestData("DriverFirstName"));
		verifyDriverLastNameAsPerFleetPreferences(CommonPage.getTestData("DriverLastName"));
	}	
	
	/**
	 * This method will verify updated Driver Data as per fleet preferences
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyUpdatedDriverDataAsPerFleetPreferences() throws Exception{
		verifyDriverTabLabelAsPerFleetPreferences(CommonPage.getTestData("CustomColumn4"));
		verifyDriverFirstNameAsPerFleetPreferences(CommonPage.getTestData("CustomColumn5"));
		verifyDriverLastNameAsPerFleetPreferences(CommonPage.getTestData("CustomColumn6"));
	}	
	
	/**
	 * This method will click on Save and next button while creating new order
	 * @lastModifiedBy Deepika
	 * @throws Exception
	 */
	public static void clickSaveAndNext() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
			Thread.sleep(2000);
			BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Application is not able click save and next");
		}
	}
	
}