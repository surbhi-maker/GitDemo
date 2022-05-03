package com.element.fleet.ordering.page;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingVehicleConfigPriceAndConfigPageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.rainbowplus.common.KeyPressEvents;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;

public class OrderingVehicleConfigPriceAndConfigPage {
	private static List <String> specList = new ArrayList<>();
	
	/**
	 * This method add new spec with Work In Progress as status
	 * @param addOption, specCount
	 * @throws Throwable 
	 * @lastModifiedBy hjimenez
	 */
	public static void addNewSpec(String addOption, int specCount) throws Throwable {
		System.out.println("Number of specs to be created:" + specCount);
		for(int i=0; i<specCount; i++) {
			OrderingHomePage.selectSideMenuOption("Vehicle Configuration");
			OrderingHomePage.selectVehicleConfigurationMenuOption(addOption);
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			CommonPage.initializeElementOrderObject();
			OrderingVehicleConfigPriceAndConfigPage.enterValuesInMakeTextBox();
			OrderingVehicleConfigPriceAndConfigPage.enterValuesInModelTextBox();
			OrderingVehicleConfigPriceAndConfigPage.selectVehicleFromSearchedResult();
			OrderingCommonPage.checkAlertPopUp();
			OrderingVehicleConfigPriceAndConfigPage.clickOnSaveButton();
			OrderingVehicleConfigPriceAndConfigPage.enterSavePopUpFleetSpecDetails();
			OrderingVehicleConfigPriceAndConfigPage.priceConfigSpecSavePopUpActions("Save");
			OrderingCommonPage.checkAlertPopUp();
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			System.out.println("Spec Created");
		}
		System.out.println("Specs in List: " + specList);
	}	
	
	/**
	 * This method searches Fleet Spec.
	 * @param searchSection, text
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void searchPriceAndConfigSpec(String searchSection, String text) throws Exception {
		OrderingVehicleConfigFleetSpecsPage.enterOrSelectFieldValue(searchSection, text);
		OrderingVehicleConfigPriceAndConfigPage.clickOnSearch();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method changes created fleet specs to pending client approval status
	 * @param searchSection
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void changeFleetSpecsToPendingApproval(String searchSection) throws Exception {
		System.out.println("change Fleet Spec To Pending Approval");
		for (String specName : specList) {
			OrderingVehicleConfigPriceAndConfigPage.searchPriceAndConfigSpec(searchSection, specName );
			OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
			OrderingVehicleConfigPriceAndConfigPage.movetoPendingAudit();
			OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
			OrderingVehicleConfigPriceAndConfigPage.movetoElementReview();
			OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
			OrderingVehicleConfigPriceAndConfigPage.movetoPendingClientApproval();
		}
		specList.clear();
	}
	
	/**
	 * This method changes created price and config specs to pending client approval status 
	 * @param searchSection
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void changeOrdersToPendingApproval(String searchSection) throws Exception {
		for (String priceConfigSpec : specList) {
			OrderingVehicleConfigPriceAndConfigPage.searchPriceAndConfigSpec(searchSection, priceConfigSpec);
			OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
			OrderingVehicleConfigPriceAndConfigPage.movetoPendingClientApproval();
		}
		specList.clear();
	}
	
	/**
	 * This method clicks on Save button on new added Fleet Spec from drop down.
	 * @lastModifiedBy bshah
	 * @throws Exception
	 */
	public static void clickOnSaveButton() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SPEC_SAVE_DROPDOWN_ID);
		BrowserAction.click(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SPEC_SAVE_DROPDOWN_ID); 		
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SAVE_BUTTON_XPATH); 		
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}

	/**
	 * This method clicks on the single Action via new 3 dot implementation 
	 * @lastModifiedBy hjimenez
	 */
	public static void clickOnSpecAction() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_ACTION_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_ACTION_XPATH);
		BrowserAction.click(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_TABLE_COLUMN_DATA_ACTION_XPATH);
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method enters spec Name in Save pop.
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void enterSavePopUpFleetSpecDetails() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH);
		String specName = "ATHECSpec" + CommonPage.randomAlphaNumericString()  + CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("SpecName", specName);
		BrowserAction.clickandClear(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH);
		CommonPage.enterTextToInputField(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_COPYFLEETSPECS_FLEETSPECNAME_XPATH,  specName);
		System.out.println("Spec Name Created: " + specName);
		specList.add(0,specName);
	}
	
	/**
	 * This method performs Accept or Cancel option on the Fleet Spec save pop up.
	 * @param action (Save/Cancel)
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void priceConfigSpecSavePopUpActions(String action) throws Exception {
		switch(action) {
			case "Save":
				BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_XPATH);
				BrowserAction.click(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_ACCEPT_BUTTON_XPATH);
				break;
			case "Cancel":
				BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_ID);
				BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_ID);
				BrowserVerify.verifyElementEnabled(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_ID);
				BrowserAction.click(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_CANCEL_BUTTON_ID);
				break;
			default: throw new InvalidSwitchCaseException(action + " is a invalid fleet spec save pop up action");
		}
	}
	
	/** 
	* This method clicks on search button. 
	* @lastModifiedBy hjimenez 
	* @throws Exception 
	*/	
	public static void clickOnSearch() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
		BrowserAction.click(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_SEARCH_BUTTON_XPATH);
	}
	
	/**
	 * This method enter the multiple values in make text box
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void enterValuesInMakeTextBox() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH);
		CommonPage.enterTextToInputField(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH,CommonPage.getTestData("Make"));
		System.out.println("Make: " + CommonPage.getTestData("Make"));
		KeyPressEvents.pressEnter(1);
		if(CommonPage.getTestData("Make: " + "Make1") != null) {
			CommonPage.enterTextToInputField(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MAKE_TEXTBOX_XPATH,CommonPage.getTestData("Make1"));
			System.out.println(CommonPage.getTestData("Make1"));
			KeyPressEvents.pressEnter(1);
		}
	}

	/**
	 * This method enter the multiple values in model text box
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void enterValuesInModelTextBox() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH);
		CommonPage.enterTextToInputField(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH,CommonPage.getTestData("Model"));
		System.out.println("Model: " + CommonPage.getTestData("Model"));
		KeyPressEvents.pressEnter(1);
		if(CommonPage.getTestData("Make1") != null) {
			CommonPage.enterTextToInputField(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPECS_PAGE_MODEL_TEXTBOX_XPATH,CommonPage.getTestData("Model1"));
			System.out.println("Model: " + CommonPage.getTestData("Model1"));
			KeyPressEvents.pressEnter(1);
		}
	}

	/**
	 * This method selects the vehicles as per search criteria
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void selectVehicleFromSearchedResult() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_VEHICLE_TABLE_ID);
		OrderingCommonPage.checkAlertPopUp();
		BrowserAction.getElements(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_FLEET_SPEC_SELECT_SEARCHED_VEHICLE_XPATH).get(0).click();
	}
	
	/**
	 * This method moves spec to Audit Pending status
	 * @lastModifiedBy bshah
	 */
	public static void movetoPendingAudit() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_AUDIT_STATUUS_ID);
		BrowserAction.click(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_AUDIT_STATUUS_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS);
		assert BrowserAction.getElement(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS).getText().equals("Audit Pending");
	}
	
	/**
	 * This method moves spec to ElementReview status
	 * @lastModifiedBy bshah
	 */
	public static void movetoElementReview() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_ELEMENT_REVIEW_STATUS_ID);
		BrowserAction.click(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_ELEMENT_REVIEW_STATUS_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS);
		assert BrowserAction.getElement(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS).getText().equals("Element Reviewed / Approved");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS));
	}
	
	/**
	 * This method moves spec to PendingClientApproval status
	 * @lastModifiedBy hjimenez
	 */
	public static void movetoPendingClientApproval() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_STATUS_ID);
		BrowserAction.click(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_STATUS_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_ADD_EXTERNAL_APPROVER_XPATH);
		List <WebElement> externalUsers = BrowserAction.getElements(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_PENDING_CLIENT_APPROVAL_ADD_EXTERNAL_APPROVER_XPATH); 
			for (WebElement externalUser : externalUsers) {
				String externalUserName = externalUser.getText();
				if(externalUserName.equalsIgnoreCase("ord_ext11 Tester")) {
					System.out.println("Selected External User: " + externalUserName);
					externalUser.click();
					break;
				}
			}
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_MODEL_POPUP_SUBMIT_BUTTON_ID);
		BrowserAction.click(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_INDIVIDUAL_MODEL_POPUP_SUBMIT_BUTTON_ID);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		BrowserWait.waitUntilElementIsDisplayed(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS);
		assert BrowserAction.getElement(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS).getText().equals("Pending Customer Approval");
		CommonPage.jsGreenBorderHighlighter(BrowserAction.getElement(OrderingVehicleConfigPriceAndConfigPageEnum.ORDERING_VEHICLE_CONFIG_SEARCH_SPEC_STATUS_CLASS));
	}
}
