package com.element.fleet.ordering.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingDealerPageEnum;
import com.element.fleet.ordering.enums.OrderingFOFleetPreferencesPageEnum;
import com.element.fleet.ordering.enums.OrderingStartHerePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.NoIfElseBlockMatchedException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserAssert;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAction;
import com.ge.capital.rainbow.webdriver.WebDriverWaits;

public class OrderingFOFleetPreferencesPage {
	
	private static int rowNumber;
	/**
	 * This method validates Fleet Preferences page labels.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void fleetPreferencesLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_ORDERINGFLLETPREFERENCES_XPATH), "Ordering Fleet Preferences", "Title label Ordering Fleet Preferences did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_GENERAL_XPATH), "General", "Label General did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_COMMUNICATIONS_XPATH), "Communications", "Label Communications did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_PROACTIVE_XPATH), "Proactive Spec Maintenance", "Label Proactive Spec Maintenance did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_DEALER_XPATH), "Dealer", "Label Dealer did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_TITLE_AND_REGISTRATION_XPATH), "Title & Registration", "Label Title & Registration did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_BILLING_XPATH), "Billing", "Label Billing did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_DRIVER_XPATH), "Driver", "Label Driver did not match with the expected string");
	}
	
	/**
	 * This method validates general tab elements labels.
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void generalTabElementLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_AUTO_UNIT_NUMBER_ASSIGNMENT_LABEL_XPATH), "Auto-Unit Number Assignment:", "Aut Unit Number Assignment did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_START_UNIT_NUMBER_LABEL_XPATH), "Starting Unit Number:", "Start Unit Number did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_END_UNIT_NUMBER_LABEL_XPATH), "Ending Unit Number", "Ending Unit Number did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_LAST_UNIT_NUMBER_LABEL_XPATH), "Last Unit Number Assigned", "Last unit Number did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_WHO_TO_SELL_USED_UNIT_LABEL_XPATH), "Who to sell used unit?", "Who to sell used unit did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_IS_THIS_RENTAL_FLEET_LABEL_XPATH), "Is this a rental fleet", "Is this rental fleet did not match with the expected string");
	}
	
	/**
	 * This method validates Driver tab elements labels.
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void driverTabElementLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_TAB_LABEL_LABEL_XPATH), "Driver Tab Label", "Driver Tab Label did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_FIRST_NAME_LABEL_XPATH), "Driver First Name", "Driver First Name did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_LAST_NAME_LABEL_XPATH), "Driver Last Name", "Driver Last Name did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_PAID_OPTION_LABEL_XPATH), "Are driver paid options allowed?", "Are Driver Paid Options Allowed? did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_PAYMENT_OPTIONS_LABEL_XPATH), "Select DPO Payment Options", "Select DPO Payment Options did not match with the expected string");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEFAULT_DRIVER_AWARD_LABEL_XPATH), "Do you want to set a default for your driver award?", "Do you want to set a default for your driver award? did not match with the expected string");
	}
	
	/**
	 * This method will select auto unit number radio button as parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectAutoUnitNumber(String radioOption) throws Exception {
		switch(radioOption) {
			case "Yes": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_AUTO_UNIT_YES_ID, "Auto Unit Number");
				break;
			case "No": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_AUTO_UNIT_NO_ID, "Auto Unit Number");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}
	
	/**
	 * This method will verify auto unit number radio button as parameter
	 * @lastModifiedBy SShukla
	 * @throws Exception
	 */
	public static void verifyAutoUnitNumber(String radioOption) throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_AUTO_UNIT_YES_ID);
		switch(radioOption) {
			case "Yes": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_AUTO_UNIT_YES_ID, "Auto Unit Number");
				break;
			case "No": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_AUTO_UNIT_NO_ID, "Auto Unit Number");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}
	
	/**
	 * This method will click on OK button for save and Proceed
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnProceedOK() throws Exception {
		try {
			if(BrowserAccess.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_PROCEED_OK_XPATH).isDisplayed() == true)
				BrowserAction.click(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_PROCEED_OK_XPATH);	
		}catch(Exception e) {
			System.out.println("Proceed Ok button not displayed");
		}
	}
	
	/**
	 * This method will click on Save button
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void clickOnSave() throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_SAVE_XPATH, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_SAVE_XPATH);
	}
	
	/**
	 * This method will enter starting unit number in Starting unit number input field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void enterStartingUnitNumber(String autoUnitRadioOption) throws Exception {
		if(autoUnitRadioOption.equals("Yes")) {
			String startUnitNumber = CommonPage.getTestData("StartingUnitNumber")+CommonPage.generateRandomNumber();
			CommonPage.loadXMLParameterToTestData("StartUnitNumber", startUnitNumber);
			System.out.println("Start Unit Number: "+CommonPage.getTestData("StartUnitNumber"));
			CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_START_UNIT_NUMBER_ID, CommonPage.getTestData("WaitTime"));
			CommonPage.enterTextToInputField(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_START_UNIT_NUMBER_ID, CommonPage.getTestData("StartUnitNumber"));
		}
	}
	
	/**
	 * This method will verify starting unit number from Starting unit number input field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyStartingUnitNumber() throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_START_UNIT_NUMBER_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.verifyInputTextFieldValue(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_START_UNIT_NUMBER_ID, CommonPage.getTestData("StartUnitNumber"), "Starting Unit Number");
	}

	/**
	 * This method will enter ending unit number in Ending unit number input field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void enterEndingUnitNumber(String autoUnitRadioOption) throws Exception {
		if(autoUnitRadioOption.equals("Yes")) {
			CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_END_UNIT_NUMBER_ID, CommonPage.getTestData("WaitTime"));
			CommonPage.enterTextToInputField(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_END_UNIT_NUMBER_ID, CommonPage.getTestData("EndingUnitNumber"));
			System.out.println("End Unit Number: "+CommonPage.getTestData("EndingUnitNumber"));
		}
	}
	
	/**
	 * This method will verify ending unit number from Ending unit number input field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyEndingUnitNumber() throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_END_UNIT_NUMBER_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.verifyInputTextFieldValue(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_END_UNIT_NUMBER_ID, CommonPage.getTestData("EndingUnitNumber"), "Ending Unit Number");
	}
	
	/**
	 * This method will verify Last unit number from Last unit number input field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyLastUnitNumber(String radioButtonInput) throws Exception {
		if(radioButtonInput.equals("Yes")) {
			CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_LAST_UNIT_NUMBER_ID, CommonPage.getTestData("WaitTime"));
			CommonPage.verifyInputTextFieldValue(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_LAST_UNIT_NUMBER_ID, CommonPage.getTestData("StartUnitNumber"), "Last Unit Number");
		}
	}
	
	/**
	 * This method will compare who to sell actual drop down list and provided list
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyWhoToSellUsedUnitOptions() throws Exception {
		Assert.assertEquals(whoToSellUsedUnitList(), whoToSellListProvided(), "Who to Sell used unit list not matched with provided list");
	}
	
	/**
	 * This method will provide who to sell actual drop down list
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static List<String> whoToSellListProvided() throws Exception {
		return Arrays.asList(CommonPage.getTestData("WhoToSellUsedUnitOptions").split("\\|"));
	}
	
	/**
	 * This method will provide who to sell drop down option list
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static ArrayList<String> whoToSellUsedUnitList() throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_WHO_TO_SELL_USED_UNIT_ID, CommonPage.getTestData("WaitTime"));
		List<WebElement> list = BrowserAction.getDropdownOptions(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_WHO_TO_SELL_USED_UNIT_ID);
		ArrayList<String> listStr = new ArrayList<>();
		for(WebElement ele : list)
			listStr.add(ele.getText().trim());
		return listStr;
	}
	
	/**
	 * This method will select who to sell used unit option from drom down
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectWhoToSellUsedUnit(String dropDownOption) throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_WHO_TO_SELL_USED_UNIT_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.selectDropDownValue(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_WHO_TO_SELL_USED_UNIT_ID, dropDownOption, "Who To Sell Used Unit");
		if(dropDownOption.equals("Notify Competitor"))
			selectCompetitorToNotify();
	}
	
	/**
	 * This method will verify who to sell used unit option from drom down
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyWhoToSellUsedUnit(String dropDownOption) throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_WHO_TO_SELL_USED_UNIT_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.verifyDropDownSelectedValue(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_WHO_TO_SELL_USED_UNIT_ID, dropDownOption, "Who To Sell Used Unit");
		if(dropDownOption.equals("Notify Competitor"))
			verifyCompetitorToNotify();
	}

	/**
	 * This method will select Competitor To Notify option from drom down
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectCompetitorToNotify() throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_COMPETITOR_TO_NOTIFY_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.selectDropDownValue(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_COMPETITOR_TO_NOTIFY_ID, CommonPage.getTestData("CompetitorToNotify"), "Competitor To Notify");
	}
	
	/**
	 * This method will verify Competitor To Notify option from drom down
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyCompetitorToNotify() throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_COMPETITOR_TO_NOTIFY_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.verifyDropDownSelectedValue(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_COMPETITOR_TO_NOTIFY_ID, CommonPage.getTestData("CompetitorToNotify"), "Competitor To Notify");
	}
	
	/**
	 * This method will select Is this a rental fleet radio button as parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectIsRentalFleet(String radioOption) throws Exception {
		switch(radioOption) {
			case "Yes": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_IS_RENTAL_FLEET_YES_ID, "Is Rental Fleet");
				break;
			case "No": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_IS_RENTAL_FLEET_NO_ID, "Is Rental Fleet");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}
	
	/**
	 * This method will verify Is this a rental fleet radio button as parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyIsRentalFleet(String radioOption) throws Exception {
		switch(radioOption) {
			case "Yes": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_IS_RENTAL_FLEET_YES_ID, "Is Rental Fleet");
				break;
			case "No": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_IS_RENTAL_FLEET_NO_ID, "Is Rental Fleet");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}
	
	/**
	 * This method will select Requested Delivery Dealer radio button as parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectRequestedDeliveryDealer(String radioOption) throws Exception {
		switch(radioOption) {
			case "Yes": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_REQUESTED_DELIVERING_DEALER_YES_ID, "Requested Delivery Dealer");
				break;
			case "No": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_REQUESTED_DELIVERING_DEALER_NO_ID, "Requested Delivery Dealer");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}
	
	/**
	 * This method will verify Requested Delivery Dealer radio button as parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyRequestedDeliveryDealer(String radioOption) throws Exception {
		switch(radioOption) {
			case "Yes": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_REQUESTED_DELIVERING_DEALER_YES_ID, "Requested Delivery Dealer");
				break;
			case "No": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_REQUESTED_DELIVERING_DEALER_NO_ID, "Requested Delivery Dealer");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}

	/**
	 * This method will select radio button imput as per passed parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectRadioButtonInput(Enum<?> fieldtLocatorEnum, String radioButtonName) throws Exception {
		CommonPage.waitForElementToLoad(fieldtLocatorEnum, CommonPage.getTestData("WaitTime"));
		if(!BrowserAction.getElement(fieldtLocatorEnum).isSelected())
			BrowserAction.click(fieldtLocatorEnum);
	}
	
	/**
	 * This method will verify radio button input as per parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyRadioButtonInput(Enum<?> fieldtLocatorEnum, String radioButtonName) throws Exception {
		CommonPage.waitForElementToLoad(fieldtLocatorEnum, CommonPage.getTestData("WaitTime"));
		if(!BrowserAction.getElement(fieldtLocatorEnum).isSelected())
			throw new OrderingErrorOccured("Radio input wrong selected for: "+radioButtonName);
	}

	/**
	 * This method will fill all data in required field
	 * @lastModifiedBy SShukla
	 * @throws Exception
	 */
	public static void fillInformation(String radioButtonInput) throws Exception {
		selectAutoUnitNumber(radioButtonInput);
		OrderingFOFleetPreferencesPage.enterAlphaNumericInputUnit(radioButtonInput);
		OrderingFOFleetPreferencesPage.enterStartingUnitNumber(radioButtonInput);
		OrderingFOFleetPreferencesPage.enterEndingUnitNumber(radioButtonInput);
		selectIsRentalFleet(radioButtonInput);
		selectRequestedDeliveryDealer(radioButtonInput);
	}

	/**
	 * This method will verify all data in general section
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyInformation(String radioButtonInput) throws Exception {
		verifyAutoUnitNumber(radioButtonInput);
		OrderingFOFleetPreferencesPage.verifyStartingUnitNumber();
		OrderingFOFleetPreferencesPage.verifyEndingUnitNumber();
		OrderingFOFleetPreferencesPage.verifyLastUnitNumber(radioButtonInput);
		verifyIsRentalFleet(radioButtonInput);
		verifyRequestedDeliveryDealer(radioButtonInput);
	}

	/**
	 * This method will click on save and proceed ok
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void SaveAndProceed() throws Exception {
		OrderingFOFleetPreferencesPage.clickOnSave();
		OrderingFOFleetPreferencesPage.clickOnProceedOK();
	}
	
	/**
	 * This method will fill data and verify after saved
	 * @lastModifiedBy SShukla
	 * @throws Exception
	 */
	public static void fillInformationAndVerify() throws Exception {
		generalTabElementLabelValidation();
		selectWhoToSellUsedUnit(whoToSellListProvided().get(1));
		OrderingFOFleetPreferencesPage.fillInformation("Yes");
		OrderingFOFleetPreferencesPage.fillDataForDriver();
		driverTabElementLabelValidation();
		OrderingFOFleetPreferencesPage.SaveAndProceed();
		verifyWhoToSellUsedUnitOptions();
		verifyCorrectFleetPreferencePerClientBreakdown();
		verifyProactiveSpecLabel();
		verifyWhoToSellUsedUnit(whoToSellListProvided().get(1));
		OrderingFOFleetPreferencesPage.verifyInformation("Yes");
		OrderingFOFleetPreferencesPage.verifyFillDataForDriver();
	}

	/**
	 * This method will fill data and verify after saved
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void updateInformationAndVerify() throws Exception {
		selectWhoToSellUsedUnit(whoToSellListProvided().get(2));
		OrderingFOFleetPreferencesPage.fillInformation("No");
		OrderingFOFleetPreferencesPage.updateDataForDriver();
		OrderingFOFleetPreferencesPage.SaveAndProceed();
		verifyWhoToSellUsedUnit(whoToSellListProvided().get(2));
		OrderingFOFleetPreferencesPage.verifyInformation("No");
		verifyUpdatedDataForDriver();
	}
	
	/**
	 * This method will click the fleet preference page
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void closeFleetPreferencePage() throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLOSE_XPATH, CommonPage.getTestData("WaitTime"));
		BrowserAction.click(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLOSE_XPATH);
		try {
			if(BrowserAccess.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLOSE_OK_XPATH).isDisplayed() == true)
				BrowserAction.click(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLOSE_OK_XPATH);	
		}catch(Exception e) {
			System.out.println("Close OK button not displayed");
		}
	}
	
	/**
	 * This method will enter data in Driver Tab Label input field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void enterDriverTabLabel(String driverTabLabel) throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_TAB_LABEL_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.enterTextToInputField(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_TAB_LABEL_ID, driverTabLabel);
		System.out.println("Driver Tab Label: "+driverTabLabel);
	}
	
	/**
	 * This method will verify data in Driver Tab Label input field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDriverTabLabel(String driverTabLabel) throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_TAB_LABEL_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.verifyInputTextFieldValue(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_TAB_LABEL_ID, driverTabLabel, "Driver Tab Label");
	}
	
	/**
	 * This method will enter data in Driver First Name input field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void enterDriverFirstName(String driverFirstName) throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_FIRST_NAME_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.enterTextToInputField(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_FIRST_NAME_ID, driverFirstName);
		System.out.println("Driver First Name: "+driverFirstName);
	}
	
	/**
	 * This method will verify data in Driver First Name input field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDriverFirstName(String driverFirstName) throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_FIRST_NAME_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.verifyInputTextFieldValue(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_FIRST_NAME_ID, driverFirstName, "Driver First Name");
	}
	
	/**
	 * This method will enter data in Driver Last Name input field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void enterDriverLastName(String driverLastName) throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_LAST_NAME_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.enterTextToInputField(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_LAST_NAME_ID, driverLastName);
		System.out.println("Driver Last Name: "+driverLastName);
	}
	
	/**
	 * This method will verify data in Driver Last Name input field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDriverLastName(String driverLastName) throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_LAST_NAME_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.verifyInputTextFieldValue(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_LAST_NAME_ID, driverLastName, "Driver Last Name");
	}
	
	/**
	 * This method will select Final Order Summary radio button as parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectFinalOrderSummary(String radioOption) throws Exception {
		switch(radioOption) {
			case "Yes": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_FINAL_ORDDER_SUMMARY_YES_ID, "Final Order Summary");
				break;
			case "No": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_FINAL_ORDDER_SUMMARY_NO_ID, "Final Order Summary");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}
	
	/**
	 * This method will verify Final Order Summary radio button as parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyFinalOrderSummary(String radioOption) throws Exception {
		switch(radioOption) {
			case "Yes": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_FINAL_ORDDER_SUMMARY_YES_ID, "Final Order Summary");
				break;
			case "No": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_FINAL_ORDDER_SUMMARY_NO_ID, "Final Order Summary");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}
	
	/**
	 * This method will select Driver Paid Option radio button as parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectDriverPaidOption(String radioOption) throws Exception {
		switch(radioOption) {
			case "Yes": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_PAID_OPTION_YES_ID, "Driver Paid Option");
				break;
			case "No": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_PAID_OPTION_NO_ID, "Driver Paid Option");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}
	
	/**
	 * This method will verify Driver Paid Option radio button as parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDriverPaidOption(String radioOption) throws Exception {
		switch(radioOption) {
			case "Yes": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_PAID_OPTION_YES_ID, "Driver Paid Option");
				break;
			case "No": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_PAID_OPTION_NO_ID, "Driver Paid Option");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}
	
	/**
	 * This method will select Default Driver Award radio button as parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectDefaultDriverAward(String radioOption) throws Exception {
		switch(radioOption) {
			case "Yes": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEFAULT_FOR_DRIVER_AWARD_YES_ID, "Default Driver Award");
				break;
			case "No": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEFAULT_FOR_DRIVER_AWARD_YES_ID, "Default Driver Award");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}
	
	/**
	 * This method will verify Default Driver Award radio button as parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDefaultDriverAward(String radioOption) throws Exception {
		switch(radioOption) {
			case "Yes": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEFAULT_FOR_DRIVER_AWARD_YES_ID, "Default Driver Award");
				break;
			case "No": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEFAULT_FOR_DRIVER_AWARD_YES_ID, "Default Driver Award");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}
	
	/**
	 * This method will select Driver Award in terms of $/% radio button as parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectAwardValueInTerms(String radioOption) throws Exception {
		switch(radioOption) {
			case "$": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_DOLLER_ID, "Default Driver Award");
				break;
			case "%": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_PER_ID, "Default Driver Award");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}
	
	/**
	 * This method will verify Driver Award in terms of $/% radio button as parameter
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyAwardValueInTerms(String radioOption) throws Exception {
		switch(radioOption) {
			case "$": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_DOLLER_ID, "Default Driver Award");
				break;
			case "%": verifyRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_PER_ID, "Default Driver Award");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}

	/**
	 * This method will enter data in Driver Award value input field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void enterDriverAwardValue() throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_VALUE_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.enterTextToInputField(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_VALUE_ID, CommonPage.getTestData("CustomColumn3"));
		System.out.println("Driver Last Name: "+CommonPage.getTestData("CustomColumn3"));
	}
	
	/**
	 * This method will verify data in Driver Award value input field
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyDriverAwardValue() throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_VALUE_ID, CommonPage.getTestData("WaitTime"));
		CommonPage.verifyInputTextFieldValue(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_VALUE_ID, CommonPage.getTestData("CustomColumn3"), "Driver Award Value");
	}
	
	/**
	 * This method will Check/Uncheck payment options as per parameter passed
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void selectPaymentOptions(String mode, String... option) throws Exception {
		String ele = OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DPO_PAYMENT_OPTIONS_XPATH.getValue();
		switch(mode) {
			case "Check":
				for(String str : option) {
					ele = ele.replace("$PaymentOption#", str);
					WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(ele));
					if(!WebDriverAction.getElement(By.xpath(ele)).isSelected())
						WebDriverAction.click(By.xpath(ele));
				}
				break;
			case "Uncheck":
				for(String str : option) {
					ele = ele.replace("$PaymentOption#", str);
					WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(ele));
					if(WebDriverAction.getElement(By.xpath(ele)).isSelected())
						WebDriverAction.click(By.xpath(ele));
				}
				break;
			default: throw new InvalidSwitchCaseException(mode+" is invalid option");
		}
	}
	
	/**
	 * This method will verify Check/Uncheck payment options as per parameter passed
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyPaymentOptions(String mode, String... option) throws Exception {
		String ele = OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DPO_PAYMENT_OPTIONS_XPATH.getValue();
		switch(mode) {
			case "Check":
				for(String str : option) {
					ele = ele.replace("$PaymentOption#", str);
					if(!WebDriverAction.getElement(By.xpath(ele)).isSelected())
						throw new OrderingErrorOccured("Payment Option should be selected for:-"+str);
				}
				break;
			case "Uncheck":
				for(String str : option) {
					ele = ele.replace("$PaymentOption#", str);
					if(WebDriverAction.getElement(By.xpath(ele)).isSelected())
						throw new OrderingErrorOccured("Payment Option should not be selected for:-"+str);
				}
				break;
			default: throw new InvalidSwitchCaseException(mode+" is invalid option");
		}
	}
	
	/**
	 * This method will verify others fields if Driver paid option is yes
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyOtherFieldsIfDriverPaidOptionIsYes() throws Exception {
		CommonPage.highlightElement(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_PAYMENT_OPTION_LABEL_XPATH));
		BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DPO_PAYMENT_CREDIT_CARD_XPATH).isDisplayed();
		CommonPage.highlightElement(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DPO_PAYMENT_CREDIT_CARD_XPATH));
		BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DPO_PAYMENT_CREDIT_UNION_XPATH).isDisplayed();
		CommonPage.highlightElement(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DPO_PAYMENT_CREDIT_UNION_XPATH));
		BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DPO_PAYMENT_DEBIT_CARD_XPATH).isDisplayed();
		CommonPage.highlightElement(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DPO_PAYMENT_DEBIT_CARD_XPATH));
		BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DPO_PAYMENT_ECHECK_XPATH).isDisplayed();
		CommonPage.highlightElement(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DPO_PAYMENT_ECHECK_XPATH));
		BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DPO_PAYMENT_PAYROLL_DEDUCTION_XPATH).isDisplayed();
		CommonPage.highlightElement(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DPO_PAYMENT_PAYROLL_DEDUCTION_XPATH));
		BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEFAULT_FOR_DRIVER_AWARD_YES_ID).isDisplayed();
		CommonPage.highlightElement(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEFAULT_FOR_DRIVER_AWARD_YES_ID));
		BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEFAULT_FOR_DRIVER_AWARD_NO_ID).isDisplayed();
		CommonPage.highlightElement(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEFAULT_FOR_DRIVER_AWARD_NO_ID));
	}

	/**
	 * This method will verify others fields not visible if Driver paid option is No
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyOtherFieldsIfDriverPaidOptionIsNo() throws Exception {
		try {
			if(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_PAYMENT_OPTION_LABEL_XPATH).isDisplayed() == true)
				throw new OrderingErrorOccured("Payment options should not be visible when Driver paid option is no");
		}catch(Exception e) {
			System.out.println("Payment Options is not displayed");
		}
	}
	
	/**
	 * This method will verify others fields if Driver paid option is yes
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyOtherFieldsIfDefaultDriverAwardIsYes() throws Exception {
		BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_DOLLER_ID).isDisplayed();
		CommonPage.highlightElement(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_DOLLER_ID));
		BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_PER_ID).isDisplayed();
		CommonPage.highlightElement(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_PER_ID));
		BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_VALUE_ID).isDisplayed();
		CommonPage.highlightElement(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_VALUE_ID));
	}
	
	/**
	 * This method will verify others fields not visible if Driver paid option is No
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyOtherFieldsIfDefaultDriverAwardIsNo() throws Exception {
		try {
			if(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DRIVER_AWARD_DOLLER_ID).isDisplayed() == true)
				throw new OrderingErrorOccured("Default driver award options $/% should not be visible when default driver award option is no");
		}catch(Exception e) {
			System.out.println("Default driver award Options $/% is not displayed");
		}
	}
	
	/**
	 * This method will fill all data in Driver tab
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void fillDataForDriver() throws Exception {
		enterDriverTabLabel(CommonPage.getTestData("CustomColumn2"));
		enterDriverFirstName(CommonPage.getTestData("DriverFirstName"));
		enterDriverLastName(CommonPage.getTestData("DriverLastName"));
		selectDriverPaidOption("Yes");
		verifyOtherFieldsIfDriverPaidOptionIsYes();
		selectPaymentOptions("Check", "Credit Card","Credit Union","Debit Card","eCheck","Payroll Deduction");
		selectDefaultDriverAward("No");
		verifyOtherFieldsIfDefaultDriverAwardIsNo();
		selectDefaultDriverAward("Yes");
		verifyOtherFieldsIfDefaultDriverAwardIsYes();
		selectAwardValueInTerms("$");
		enterDriverAwardValue();
	}
	
	/**
	 * This method will verify all data in Driver tab
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyFillDataForDriver() throws Exception {
		verifyDriverTabLabel(CommonPage.getTestData("CustomColumn2"));
		verifyDriverFirstName(CommonPage.getTestData("DriverFirstName"));
		verifyDriverLastName(CommonPage.getTestData("DriverLastName"));
		verifyDriverPaidOption("Yes");
		verifyOtherFieldsIfDriverPaidOptionIsYes();
		verifyPaymentOptions("Check", "Credit Card","Credit Union","Debit Card","eCheck","Payroll Deduction");
		verifyDefaultDriverAward("Yes");
		verifyOtherFieldsIfDefaultDriverAwardIsYes();
		verifyAwardValueInTerms("$");
		verifyDriverAwardValue();
	}
	
	/**
	 * This method will fill update all data in Driver tab
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void updateDataForDriver() throws Exception {
		enterDriverTabLabel(CommonPage.getTestData("CustomColumn4"));
		enterDriverFirstName(CommonPage.getTestData("CustomColumn5"));
		enterDriverLastName(CommonPage.getTestData("CustomColumn6"));
		selectDriverPaidOption("No");
	}
	
	/**
	 * This method will verify all updated data in Driver tab
	 * @lastModifiedBy Akshay Kandkonde
	 * @throws Exception
	 */
	public static void verifyUpdatedDataForDriver() throws Exception {
		verifyDriverTabLabel(CommonPage.getTestData("CustomColumn4"));
		verifyDriverFirstName(CommonPage.getTestData("CustomColumn5"));
		verifyDriverLastName(CommonPage.getTestData("CustomColumn6"));
		verifyDriverPaidOption("No");
		verifyOtherFieldsIfDriverPaidOptionIsNo();
	}
	
	/**
	 * This method set int and ext color preference
	 * @param Option 
	 * @param intExtColor 
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void setAndVerifyColorPreference(String intExtColor, String option) throws Exception {
		if(intExtColor.contains("Exterior") && option.contains("No")) {
			BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_EXTERIOR_RADIO_XPATH).get(0).click();
			Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_EXTERIOR_RADIO_XPATH).get(0).isSelected());
		} else if(intExtColor.contains("Exterior") && option.contains("Yes")) {
			BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_EXTERIOR_RADIO_XPATH).get(1).click();
			Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_EXTERIOR_RADIO_XPATH).get(1).isSelected());
		} else if(intExtColor.contains("Interior") && option.contains("No")) {
			BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_INTERIOR_RADIO_XPATH).get(0).click();
			Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_INTERIOR_RADIO_XPATH).get(0).isSelected());
		} else if(intExtColor.contains("Interior") && option.contains("Yes")) {
			BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_INTERIOR_RADIO_XPATH).get(1).click();
			Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_INTERIOR_RADIO_XPATH).get(1).isSelected());
		} else {
			throw new NoIfElseBlockMatchedException("Input criteria doesnot match expected condition");
		}
	}

	/**
	 * This method set DPO for int and ext color preference
	 * @param Option 
	 * @param intExtColor 
	 * @lastModifiedBy lpadaliya
	 * @throws Exception
	 */
	public static void setAndVerifyColorPreferenceDPO(String intExtColor, String option) throws Exception {
		if(intExtColor.contains("Exterior") && option.contains("Yes")) {
			BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_INTERIOR_EXTERIOR_DRIVER_PAY_RADIO_OPTIONS_XPATH).get(0).click();
			Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_INTERIOR_EXTERIOR_DRIVER_PAY_RADIO_OPTIONS_XPATH).get(0).isSelected());
		} else if(intExtColor.contains("Exterior") && option.contains("No")) {
			BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_INTERIOR_EXTERIOR_DRIVER_PAY_RADIO_OPTIONS_XPATH).get(1).click();
			Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_INTERIOR_EXTERIOR_DRIVER_PAY_RADIO_OPTIONS_XPATH).get(1).isSelected());
		} else if(intExtColor.contains("Interior") && option.contains("Yes")) {
			BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_INTERIOR_EXTERIOR_DRIVER_PAY_RADIO_OPTIONS_XPATH).get(2).click();
			Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_INTERIOR_EXTERIOR_DRIVER_PAY_RADIO_OPTIONS_XPATH).get(2).isSelected());
		} else if(intExtColor.contains("Interior") && option.contains("No")) {
			BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_INTERIOR_EXTERIOR_DRIVER_PAY_RADIO_OPTIONS_XPATH).get(3).click();
			Assert.assertTrue(BrowserAccess.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_EXTRA_COST_INTERIOR_EXTERIOR_DRIVER_PAY_RADIO_OPTIONS_XPATH).get(3).isSelected());
		} else {
			throw new NoIfElseBlockMatchedException("Input criteria doesnot match expected condition");				
		}
	}
	
	/**
	 * This method enters a test data in billing tab
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void fillDataForBilling() throws Exception {
		CommonPage.javascriptScrollUntilElementIsVisible(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_BILLING_XPATH));
		BrowserAction.click(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_ADD_NEW_BUTTON_XPATH);
		rowNumber=BrowserAction.getElements(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_TABLE_ROWS_XPATH).size();
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_PRODUCT_CLASS_DROPDOWN_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_PRODUCT_CLASS_DROPDOWN_XPATH.toString(), rowNumber)));
		WebDriverAction.selectDropDownOptionByText(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_PRODUCT_CLASS_DROPDOWN_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_PRODUCT_CLASS_DROPDOWN_XPATH.toString(), rowNumber)), CommonPage.getTestData("ProductClass"));
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_CONTRACT_TYPE_DROPDOWN_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_CONTRACT_TYPE_DROPDOWN_XPATH.toString(), rowNumber)));
		WebDriverAction.selectDropDownOptionByText(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_CONTRACT_TYPE_DROPDOWN_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_CONTRACT_TYPE_DROPDOWN_XPATH.toString(), rowNumber)), CommonPage.getTestData("ContractType"));
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_LEASE_TERM_DROPDOWN_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_LEASE_TERM_DROPDOWN_XPATH.toString(), rowNumber)));
		WebDriverAction.selectDropDownOptionByText(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_LEASE_TERM_DROPDOWN_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_LEASE_TERM_DROPDOWN_XPATH.toString(), rowNumber)), CommonPage.getTestData("LeaseTerm"));
    }

	/**
	 * This method verifies functionality of delete and undo of billing tab
	 * @lastModifiedBy ADhawale  
	 * @throws Exception
	 */
	public static void verifyDeleteAndUndoFunctionalityForBilling() throws Exception {
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_FUNDING_TYPE_LABEL_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_FUNDING_TYPE_LABEL_XPATH.toString(), rowNumber)));
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_FUNDING_INDEX_LABEL_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_FUNDING_INDEX_LABEL_XPATH.toString(), rowNumber)));
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_INTEREST_ADDER_LABEL_XPATH.name(), 	String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_INTEREST_ADDER_LABEL_XPATH.toString(), rowNumber)));
		String fundingType=WebDriverAction.getElement(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_FUNDING_TYPE_LABEL_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_FUNDING_TYPE_LABEL_XPATH.toString(), rowNumber))).getText().toString();
		String fundingIndex=WebDriverAction.getElement(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_FUNDING_INDEX_LABEL_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_FUNDING_INDEX_LABEL_XPATH.toString(), rowNumber))).getText().toString();
		String interestAdder=WebDriverAction.getElement(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_INTEREST_ADDER_LABEL_XPATH.name(), 	String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_INTEREST_ADDER_LABEL_XPATH.toString(), rowNumber))).getText().toString();
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_ACTIONS_DELETE_LINK_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_ACTIONS_DELETE_LINK_XPATH.toString(), rowNumber)));
		WebDriverAction.getElement(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_ACTIONS_DELETE_LINK_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_ACTIONS_DELETE_LINK_XPATH.toString(), rowNumber))).click();
		WebDriverWaits.waitUntilElementIsDisplayed(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_ACTIONS_UNDO_LINK_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_ACTIONS_UNDO_LINK_XPATH.toString(), rowNumber)));
		WebDriverAction.getElement(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_ACTIONS_UNDO_LINK_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_ACTIONS_UNDO_LINK_XPATH.toString(), rowNumber))).click();
		BrowserAssert.assertEquals(new Select(WebDriverAction.getElement(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_PRODUCT_CLASS_DROPDOWN_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_PRODUCT_CLASS_DROPDOWN_XPATH.toString(), rowNumber)))).getFirstSelectedOption().getText(), CommonPage.getTestData("ProductClass"), "Product Class dropdown value mismatched");
		BrowserAssert.assertEquals(new Select(WebDriverAction.getElement(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_CONTRACT_TYPE_DROPDOWN_XPATH.name(),	String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_CONTRACT_TYPE_DROPDOWN_XPATH.toString(), rowNumber)))).getFirstSelectedOption().getText(), CommonPage.getTestData("ContractType"), "Contract Type dropdown value mismatched");
		BrowserAssert.assertEquals(new Select(WebDriverAction.getElement(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_LEASE_TERM_DROPDOWN_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_LEASE_TERM_DROPDOWN_XPATH.toString(), rowNumber)))).getFirstSelectedOption().getText(), CommonPage.getTestData("LeaseTerm"), "Lease term dropdown value mismatched");
		CommonPage.assertLabelHighlight(WebDriverAction.getElement(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_FUNDING_TYPE_LABEL_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_FUNDING_TYPE_LABEL_XPATH.toString(), rowNumber))), fundingType, "Funding type value mismatched");
	    CommonPage.assertLabelHighlight(WebDriverAction.getElement(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_FUNDING_INDEX_LABEL_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_FUNDING_INDEX_LABEL_XPATH.toString(), rowNumber))), fundingIndex, "Funding index value mismatched");
	    CommonPage.assertLabelHighlight(WebDriverAction.getElement(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_INTEREST_ADDER_LABEL_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_INTEREST_ADDER_LABEL_XPATH.toString(), rowNumber))), interestAdder, "Interest adder value mismatched ");
	    BrowserAssert.assertTrue(WebDriverAction.getElement(BrowserAccess.getLocator(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_ACTIONS_DELETE_LINK_XPATH.name(), String.format(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_BILLING_ACTIONS_DELETE_LINK_XPATH.toString(), rowNumber))).isDisplayed(), "Delete link was not displayed after undo operation");
	}

	/**
	 * This method verifies that whether yes radio button of dealer tab is selected by default and no radio button is not selected by navigating to dealer tab
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void verifyDealerTabButtons() throws Exception {
		  CommonPage.javascriptScrollUntilElementIsVisible(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_DEALER_XPATH));
		  CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEALER_DELIVERING_DELEAR_YES_ID);
		  CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEALER_DELIVERING_DELEAR_NO_ID);
		  if (!BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEALER_DELIVERING_DELEAR_YES_ID).getAttribute("outerHTML").contains("checked")) {
		    BrowserAction.click(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEALER_DELIVERING_DELEAR_YES_ID);
		  }
	}

	/**
	 * This method navigates to dealer tab of fleet preferences page and clicks on no radio button of dealer tab
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void clickDealerTabNoRadioButton() throws Exception {
		CommonPage.javascriptScrollUntilElementIsVisible(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_DEALER_XPATH));
		BrowserAction.click(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_DEALER_DELIVERING_DELEAR_NO_ID);
	}

	/**
	 * This method verifies that Request a new Dealer link is present on dealer page 
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void verifyRequestDealerLink() throws Exception {
		CommonPage.javascriptScrollUntilElementIsVisible(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_REQUEST_A_DEALER_LINK_XPATH));
		WebElement requestDealerLink=BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_REQUEST_A_DEALER_LINK_XPATH);
		BrowserAssert.assertEquals(requestDealerLink.getText().trim(), CommonPage.getTestData("RequestDealerLinkText"), "Request a Dealer Link text mismatched");
		BrowserAssert.assertTrue(requestDealerLink.isEnabled(), "Request a New Dealer is not in enabled state");
	}

	/**
	 * This method verifies that dealer tab is not present when external user logged in 
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void verifyDealerTabIsNotPresent() throws Exception {
		BrowserAssert.assertTrue(CommonPage.getTestData("UserRole").contains("External Full Access"), "External user was not logged in");
		BrowserAssert.assertFalse(BrowserAction.getElement(OrderingDealerPageEnum.ORDERING_DEALER_TAB_XPATH).isDisplayed(), "Dealer tab should not be displayed for external user if allow dealer toggle is off. Hence it is a defect ORD-46941");
	}
	
	/**
	 * This method verifies that client communications yes and no radio buttons functionality when super user logged in 
	 * @lastModifiedBy ADhawale
	 * @throws Exception
	 */
	public static void verifyClientCommunicationsYesNoRadioButtonsFunctionality() throws Exception {
		BrowserAssert.assertTrue(CommonPage.getTestData("UserRole").contains("Ordering Super User"), "Super user was not logged in");
		CommonPage.javascriptScrollUntilElementIsVisible(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_COMMUNICATIONS_XPATH));
		BrowserAssert.assertEquals(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLIENT_COMMUNICATIONS_LABEL_XPATH).getText(), CommonPage.getTestData("ClientCommunicationsLabel"), "client communications label text mismatched");
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLIENT_COMMUNICATIONS_YES_ID);
		CommonPage.waitAndVerifyElementDisplayedAndEnabled(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLIENT_COMMUNICATIONS_NO_ID);
		if(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLIENT_COMMUNICATIONS_YES_ID).getAttribute("outerHTML").contains("checked")) {
			BrowserAction.click(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLIENT_COMMUNICATIONS_NO_ID);
		}
		BrowserAction.click(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLIENT_COMMUNICATIONS_YES_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLIENT_COMMUNICATIONS_ADD_NEW_RECIPIENT_LINK_XPATH);
		BrowserAssert.assertTrue(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLIENT_COMMUNICATIONS_ADD_NEW_RECIPIENT_LINK_XPATH).isDisplayed(), "Add New Recipient link should be displayed. Hence it is a defect.");
		BrowserAssert.assertTrue(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLIENT_COMMUNICATIONS_ADD_NEW_RECIPIENT_LINK_XPATH).isEnabled(), "Add New Recipient link should be enabled. Hence it is a defect.");
		BrowserAction.click(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLIENT_COMMUNICATIONS_NO_ID);
		BrowserAssert.assertFalse(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLIENT_COMMUNICATIONS_ADD_NEW_RECIPIENT_LINK_XPATH).isDisplayed(), "Add New Recipient link should not be displayed. Hence it is a defect.");
	}

	/**
	 * This method will validate the pro-active spec maintenance label text
	 * @lastModifiedBy SShukla
	 * @throws Exception
	 */		
	private static void verifyProactiveSpecLabel() throws Exception {
		CommonPage.javascriptScrollUntilElementIsVisible(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_PROACTIVE_XPATH));
		BrowserAssert.assertEquals(BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_PROACTIVE_SPEC_MAINTENANCE_RECEIVE_CLIENT_COMMUNITCATIONS_XPATH).getText(), CommonPage.getTestData("ProactiveSpecMaintenanceLabel"), "Proactve Maintenance Spec Label Mis Match");
	}

	/**
	 * This method will enter Incorrect value (AlphaNumeric) in Starting unit number/Ending Unit Number input field, which accepts only Numberic values.
	 * Negative scenario, so we should not be allowed to enter the value and error is expected. 
	 * @lastModifiedBy SShukla
	 * @throws Exception
	 */
	public static void enterAlphaNumericInputUnit(String autoUnitRadioOption) throws Exception {
		if(autoUnitRadioOption.equals("Yes")) {
			String alphaNumbericUnitNumber = CommonPage.randomAlphaNumericString();
			CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_START_UNIT_NUMBER_ID, CommonPage.getTestData("WaitTime"));
			CommonPage.enterTextToInputField(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_START_UNIT_NUMBER_ID, alphaNumbericUnitNumber);			
			CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_END_UNIT_NUMBER_ID, CommonPage.getTestData("WaitTime"));
			CommonPage.enterTextToInputField(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_END_UNIT_NUMBER_ID, alphaNumbericUnitNumber);		
		}
	}

	/**
	 * This method will verify that the values for the Client Breakdown, Fleet Preferences Client Name and Client Number passed in YAML file are same.  
	 * @lastModifiedBy SShukla
	 * @throws Exception
	 */	
	private static void verifyCorrectFleetPreferencePerClientBreakdown() throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_GENERAL_CLIENT_NAME_XPATH, CommonPage.getTestData("WaitTime"));
		String clientValueFOFleetPreferences = BrowserAction.getElement(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_GENERAL_CLIENT_NAME_XPATH).getText().split("-")[0].trim();
		CommonPage.waitForElementToLoad(OrderingStartHerePageEnum.ORDERING_START_HERE_CLIENT_BREAKDOWN_TEXT_ID, CommonPage.getTestData("WaitTime"));
		String clientValueOrderingStartHerePage = BrowserAction.getElement(OrderingStartHerePageEnum.ORDERING_START_HERE_CLIENT_BREAKDOWN_TEXT_ID).getText().split(":")[0].trim();
		BrowserAssert.assertEquals(clientValueFOFleetPreferences, CommonPage.getTestData("ClientNumber"), "Client number in Fleet Preferences page is different");
		BrowserAssert.assertEquals(clientValueOrderingStartHerePage, CommonPage.getTestData("ClientNumber"), "Client number in Ordering Start Here page is different");
	}
	
	/**
	 * This method will select 
	 * @lastModifiedBy SShukla
	 * @throws Exception
	 */
	public static void verifyIsRentalFleetInBOAfterOrderCreate(String radioOption) throws Exception {
		OrderingLoginPage.openBOApplication();		
		OrderingBOHomePage.boHomePageLabelValidation();
	}

	/**
	 * This method will click the fleet preference page and select data for Communications sections
	 * @lastModifiedBy SShukla
	 * @throws Exception
	 */	
	public static void selectDataForCommunications() throws Exception {
		CommonPage.waitForElementToLoad(OrderingFOFleetPreferencesPageEnum.ORDEING_HOME_FLEET_PREFRENCES_COMMUNICATIONS_XPATH, CommonPage.getTestData("WaitTime"));
		selectReceiveCommunicationsOption("No");
	}

	/**
	 * This method will select Receive Communications Option radio button as parameter
	 * @lastModifiedBy SShukla
	 * @throws Exception
	 */
	public static void selectReceiveCommunicationsOption(String radioOption) throws Exception {
		switch(radioOption) {
			case "Yes": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLIENT_COMMUNICATIONS_YES_ID, "Receive Communication Option");
				break;
 			case "No": selectRadioButtonInput(OrderingFOFleetPreferencesPageEnum.ORDERING_FO_FLEETPREFERENCES_CLIENT_COMMUNICATIONS_NO_ID, "Receive Communication Option");
				break;
			default: throw new InvalidSwitchCaseException(radioOption+" is invalid option");
		}
	}
}