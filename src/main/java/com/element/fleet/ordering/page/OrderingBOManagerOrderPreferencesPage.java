package com.element.fleet.ordering.page;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.DbConnector;
import com.element.fleet.ordering.commonutility.ExcelUtil;
import com.element.fleet.ordering.enums.OrderingBOManagerOrderPreferencesPageEnum;
import com.element.fleet.ordering.enums.OrderingDriverPageEnum;
import com.element.fleet.ordering.enums.OrderingHomePageEnum;
import com.element.fleet.ordering.enums.OrderingSummaryPageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingBOManagerOrderPreferencesPage {
	
	private static WebDriver jsWebdriver = WebDriverAccess.getDriver();
	private static JavascriptExecutor jsExec = (JavascriptExecutor) jsWebdriver;
	
	/**
	 * This method waits for the Manager Order Prefrences page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForManagerOrderPreferencesPageToLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_FLEET_SPEC_GROUPS_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_FLEET_SPEC_GROUPS_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_FLEET_SPEC_GROUPS_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_DRIVER_GROUPS_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_ASSOCIATIONS_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_APPROVAL_RULES_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_APPROVER_COMMUNICATIONS_RULES_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CORP_DROPDOWN_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CORP_DROPDOWN_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CORP_DROPDOWN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_TEXTFILED_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_TEXTFILED_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_TEXTFILED_XPATH);		
	}
	

	/**
	 * This method validates Manager Order Prefrences page headings.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void validateManagerOrderPreferencesPageHeadings() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_FLEET_SPEC_GROUPS_CSS), "Fleet Spec Groups", "Fleet Spec Groups heading validation failed");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_DRIVER_GROUPS_CSS), "Driver Groups", "Driver Groups heading validation failed");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_ASSOCIATIONS_CSS), "Associations", "Associations heading validation failed");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_APPROVAL_RULES_CSS), "Approval Rules", "Approval Rules heading validation failed");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_APPROVER_COMMUNICATIONS_RULES_CSS), "Approver Communications", "Approver Communications heading validation failed");
	}
	
	/**
	 * This method enter the corp code and client and selects the requested value.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterCorpAndClientAndSelectClient() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CORP_DROPDOWN_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CORP_DROPDOWN_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CORP_DROPDOWN_XPATH);
		System.out.println("Corp code: " + "FA");
		BrowserAction.selectDropdownOptionByText(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CORP_DROPDOWN_XPATH, "FA");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_TEXTFILED_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_TEXTFILED_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_TEXTFILED_XPATH);
		BrowserAction.clickandClear(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_TEXTFILED_XPATH);
		if(Objects.isNull(CommonPage.getTestData("ClientNumber"))) {
			throw new NullPointerException("ClientNumber can't be null");
		} else {
			System.out.println("Client: " + CommonPage.getTestData("ClientNumber"));
			BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_TEXTFILED_XPATH, CommonPage.getTestData("ClientNumber"));
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_SUGGESTION_CSS);
			BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_SUGGESTION_CSS);
			BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_SUGGESTION_CSS);
			Assert.assertTrue(BrowserAction.getElementText(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_SUGGESTION_CSS).contains(CommonPage.getTestData("ClientNumber")));
			BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CLIENT_SEARCH_SUGGESTION_CSS);
		}
	}
	
	/**
	 * This method waits for the Fleets Spec Group section.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void fleetSpecGroupsSectionValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_SPECS_GROUP_LABEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_SPECS_GROUP_LABEL_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_SPECS_GROUP_LABEL_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_SPECS_GROUP_LABEL_XPATH), "Fleet Spec Groups", "Fleet Spec Groups label validation failed");
	}
	
	/**
	 * This method waits for the Driver Group section.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void driverGroupsSectionValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_LABEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_LABEL_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_LABEL_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_LABEL_XPATH), "Driver Groups", "Driver Groups label validation failed");
	}
	
	/**
	 * This method waits for the Association section.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void associationsSectionValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_LABEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_LABEL_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_LABEL_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_LABEL_XPATH), "Associations", "Associations label validation failed");
	}
	
	/**
	 * This method waits for the Approval Rules section.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void approvalRulesSectionValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_LABEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_LABEL_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_LABEL_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_LABEL_XPATH), "Approval Rules", "Approval Rules label validation failed");
	}
	
	/**
	 * This method waits for the Approvar Communications section.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void approvarCommunicationsSectionValidation() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_COMMUNICATIONS_LABEL_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_COMMUNICATIONS_LABEL_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_COMMUNICATIONS_LABEL_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_COMMUNICATIONS_LABEL_XPATH), "Approver Communications", "Approver Communications label validation failed");
	}
	
	/**
	 * Thsi method moves to each section verifys section element loaded or not.
	 * @lastModifiedBy shisingh
	 * @param sectionName
	 * @throws Exception
	 */
	public static void moveToSection(String sectionName) throws Exception {
		switch(sectionName) {
			case "Fleet Spec Groups":
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_FLEET_SPEC_GROUPS_CSS);
				OrderingBOManagerOrderPreferencesPage.fleetSpecGroupsSectionValidation();
				break;
			case "Driver Groups":
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_DRIVER_GROUPS_CSS);
				OrderingBOManagerOrderPreferencesPage.driverGroupsSectionValidation();
				break;
			case "Associations":
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_ASSOCIATIONS_CSS);
				OrderingBOManagerOrderPreferencesPage.associationsSectionValidation();
				break;
			case "Approval Rules":
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_APPROVAL_RULES_CSS);
				OrderingBOManagerOrderPreferencesPage.approvalRulesSectionValidation();
				break;
			case "Approver Communications":
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_NAV_BAR_APPROVER_COMMUNICATIONS_RULES_CSS);
				OrderingBOManagerOrderPreferencesPage.approvarCommunicationsSectionValidation();
				break;
			default: throw new InvalidSwitchCaseException(sectionName + "is an invalid switch case");
		}
	}
	
	/**
	 * This method waits for the Maager Approver page to be loaded after with all details and all sections.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void validateManagerApprovalSectionWithDetails() throws Exception {
		OrderingBOManagerOrderPreferencesPage.waitForManagerOrderPreferencesPageToLoaded();
		OrderingBOManagerOrderPreferencesPage.validateManagerOrderPreferencesPageHeadings();
		OrderingBOManagerOrderPreferencesPage.moveToSection("Fleet Spec Groups");
		OrderingBOManagerOrderPreferencesPage.moveToSection("Driver Groups");
		OrderingBOManagerOrderPreferencesPage.moveToSection("Associations");
		OrderingBOManagerOrderPreferencesPage.moveToSection("Approval Rules");
		OrderingBOManagerOrderPreferencesPage.moveToSection("Approver Communications");
	}
	
	/**
	 * This method match the particular section and accordingly delete the existing rule/group of that section.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void verifyDeleteRuleFromMaintenancePage(String groupOption) throws Exception {
		switch (groupOption) {
			case "Fleet Spec Groups":
				OrderingBOHomePage.selectSideMenuOption("Manager Order Preferences");
				OrderingBOManagerOrderPreferencesPage.clickSaveAndClose();
				OrderingCommonPage.checkAlertPopUp();
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingBOManagerOrderPreferencesPage.waitForManagerOrderPreferencesPageToLoaded();
				OrderingBOManagerOrderPreferencesPage.searchAndClickFleetSpec();
				OrderingBOManagerOrderPreferencesPage.addFleetSpecGroupPageLoaded();
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_DELETE_BUTTON_ID);
				OrderingBOManagerOrderPreferencesPage.verifyPopUpMessage("Fleet Spec - Delete From Maintenance View");
				OrderingBOManagerOrderPreferencesPage.popUpAction("Yes");
				OrderingBOManagerOrderPreferencesPage.checkAlertPopUpConfimationMessage("Fleet Spec Group - Delete From Maintenance View");
				OrderingBOManagerOrderPreferencesPage.verifyFletSpecDeleted();
				break;   
			case "Driver Groups":
				OrderingBOManagerOrderPreferencesPage.clickOnAnyExistingRule("driver-groups-queue", groupOption);
				OrderingBOManagerOrderPreferencesPage.clickOnDeleteButtonFromMaintenancePage();
				break;
			case "Associations":
				OrderingBOManagerOrderPreferencesPage.searchAndClickAssociation("associations-queue");
				OrderingBOManagerOrderPreferencesPage.clickOnDeleteButtonFromMaintenancePage();
				OrderingBOManagerOrderPreferencesPage.verifyPopUpMessageMaintenance("Association - Delete From Maintenance View");
				OrderingBOManagerOrderPreferencesPage.popUpAction("Yes");
				OrderingBOManagerOrderPreferencesPage.checkAlertPopUpConfimationMessage("Association - Delete From Maintenance View");
				OrderingBOManagerOrderPreferencesPage.verifyAssociationDeleted("associations-queue");
				break;
			case "Approval Rules":
				OrderingBOManagerOrderPreferencesPage.clickOnAnyExistingRule("section-approval-rules", groupOption);
				OrderingBOManagerOrderPreferencesPage.clickOnDeleteButtonFromMaintenancePage();
				OrderingBOManagerOrderPreferencesPage.verifyPopUpMessage("Approval Rules - Delete From Maintenance View");
				OrderingBOManagerOrderPreferencesPage.popUpAction("Yes");
				OrderingBOManagerOrderPreferencesPage.checkAlertPopUpConfimationMessage("Approval Rules - Delete From Maintenance View");
				break;   
			default: throw new InvalidSwitchCaseException(groupOption + " is invalid Delete Rule from Maintenance page");
		}
	}
	
	/**
	 * This method match the particular section and accordingly update the existing rule/group of that section.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void verifyUpdateRuleFromMaintenancePage(String groupOption) throws Exception {
		switch (groupOption) {
			case "Approval Rules":
				OrderingBOManagerOrderPreferencesPage.clickOnAnyExistingRule("section-approval-rules", groupOption);
				OrderingBOManagerOrderPreferencesPage.enterRuleName();
				OrderingBOManagerOrderPreferencesPage.enterStartDate();
				OrderingBOManagerOrderPreferencesPage.enterEndDate();
				break;   
			default: throw new InvalidSwitchCaseException(groupOption + " is invalid Delete Rule from Maintenance page");
		}
	}
	
	/**
	 * This method match the particular section and accordingly click on the existing rule/group of that section.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void clickOnAnyExistingRule(String divId, String groupOption) throws Exception {
		switch (groupOption) {
			case "Fleet Spec Groups": WebDriverAccess.getDriver().findElement(By.xpath(String.format("//div[@id='%s']//table[@id='queue-table']//parent::tbody/tr[1]", divId))).click();
				break;
			case "Driver Groups": WebDriverAccess.getDriver().findElement(By.xpath(String.format("//div[@id='%s']//table[@id='queue-table']//parent::tbody/tr[1]", divId))).click();
				break;
			case "Associations": WebDriverAccess.getDriver().findElement(By.xpath(String.format("//div[@id='%s']//table[@id='queue-table']//parent::tbody/tr[1]", divId))).click();
				break;
			case "Approval Rules": WebDriverAccess.getDriver().findElement(By.xpath(String.format("//div[@id='%s']//table[@id='queue-table']//parent::tbody/tr[1]", divId))).click();
				break;
			default: throw new InvalidSwitchCaseException(groupOption + " is a invalid rule option");
		}
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method clicks on the Delete button on the Maintenance page
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void clickOnDeleteButtonFromMaintenancePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_MAINTENANCE_PAGE_DELETE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_MAINTENANCE_PAGE_DELETE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_MAINTENANCE_PAGE_DELETE_BUTTON_XPATH);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_MAINTENANCE_PAGE_DELETE_BUTTON_XPATH);
	}
	
	/**
	 * This method checks whether the AddRule Approval Rule page is loaded or not.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void waitForAddRuleApprovaRulePageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_APPROVAL_RULES_HEADING_XPATH);
	}
	
	/**
	 * This method verifies all the elements of Approval Rule is displayed or not.
	 * @lastModifiedBy Usha Naidu 
	 * @throws Exception
	 */
	public static void addApprovalRulesPageLoaded() throws Exception {
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_APPROVAL_RULES_HEADING_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_RULE_NAME_CSS);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_START_DATE_LABEL_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_END_DATE_LABEL_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_START_DATE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_END_DATE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_LIST_OF_AVAILABLE_RULES_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_SEQUENTIAL_RADIO_BUTTON_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_NON_EQUENTIAL_RADIO_BUTTON_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_BUTTON_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DENOTES_DELEGATE_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_SIGN_OUT_BUTTON_XPATH);
	}
	
	/** 
	 * This method enters Aprroval Rule name.
	 * @lastModifiedBy Usha Naidu 
	 * @throws Exception
	 */
	public static void enterRuleName() throws Exception {
		String approvalRuleName = "AT" + CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("ApprovalRuleName", approvalRuleName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_RULE_NAME_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_RULE_NAME_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_RULE_NAME_CSS);
		BrowserAction.clickandClear(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_RULE_NAME_CSS);
		System.out.println("Approval Rule Name: " + CommonPage.getTestData("ApprovalRuleName") );
		BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_RULE_NAME_CSS, CommonPage.getTestData("ApprovalRuleName"));
	}
	
	/**
	 * This method verifies whether the StartDate entered in the StartDate field or not in Add Rule page.
	 * @lastModifiedBy Usha Naidu 
	 * @throws Exception
	 */
	public static void enterStartDate() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_START_DATE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_START_DATE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_START_DATE_TEXTBOX_XPATH);
		BrowserAction.clickandClear(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_START_DATE_TEXTBOX_XPATH);
		System.out.println("Start Date: " + CommonPage.getCurrentDataIn("MM/dd/yyyy"));
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_START_DATE_XPATH);
	}
	
	/**
	 * This method verifies whether the EndDate entered in the EndDate field or not in Add Rule page.
	 * @author Usha Naidu 
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void enterEndDate() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_END_DATE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_END_DATE_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_END_DATE_TEXTBOX_XPATH);
		BrowserAction.clickandClear(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_END_DATE_TEXTBOX_XPATH);
		System.out.println("End Date: " + CommonPage.getCurrentDataIn("MM/dd/yyyy","2"));
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_NEXTMONTH_PICKER_XPATH);
		BrowserAction.getElements(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DAYS_CALENDAR_XPATH).get(1).click();
		
	}
	
	/**
	 * This method iterates over multiple rules.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectRules() throws Exception {
		if(!(CommonPage.getTestData("ApprovalRule")==null)) {
			String[] rules = CommonPage.getTestData("ApprovalRule").split("\\|");
			for(String rule: rules) {
				OrderingBOManagerOrderPreferencesPage.selectRule(rule);
			}
		}
	}
	
	/**
	 * This method verifies selects the make rule.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void selectRule(String rule) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_LIST_OF_AVAILABLE_RULES_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_LIST_OF_AVAILABLE_RULES_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_LIST_OF_AVAILABLE_RULES_XPATH);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_LIST_OF_AVAILABLE_RULES_XPATH);
		switch(rule) {
			case "Make":
				OrderingBOManagerOrderPreferencesPage.checkApprovalRule(rule);
				OrderingBOManagerOrderPreferencesPage.searchForApprovalRuleDetails(rule);
				break;
			default: throw new InvalidSwitchCaseException(rule + " rule is not yet implemened in the test framework");
		}
	}
	
	/**
	 * This method selects desiered approval rule.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void checkApprovalRule(String rule) throws Exception {
		WebElement ruleOptionWebElment = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_SELECTOPTION_XPATH.name(), String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_SELECTOPTION_XPATH.toString(), CommonPage.getTestData("ApprovalRule"))));
		BrowserWait.waitUntilElementIsDisplayed(ruleOptionWebElment);
		BrowserVerify.verifyElementIsPresent(ruleOptionWebElment);
		BrowserVerify.verifyElementEnabled(ruleOptionWebElment);
		System.out.println("Rule: " + rule);
		ruleOptionWebElment.click();		
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_LIST_OF_AVAILABLE_RULES_XPATH);
	}
	
	/**
	 * This method iterates over multiple search rule details.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void searchForApprovalRuleDetails(String rule) throws Exception {
		if(!(CommonPage.getTestData("ApprovalRuleSearchDetails")==null)) {
			String[] ruleDetails = CommonPage.getTestData("ApprovalRuleSearchDetails").split("\\|");
			for(String ruleDetail: ruleDetails) {
				OrderingBOManagerOrderPreferencesPage.searchForApprovalRuleDetail(rule, ruleDetail);
			}
		}
	}
	
	/**
	 * This method searches approval rule option.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void searchForApprovalRuleDetail(String rule, String ruleDetail) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_MAKE_SEARCH_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_MAKE_SEARCH_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_MAKE_SEARCH_TEXTBOX_XPATH);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_MAKE_SEARCH_TEXTBOX_XPATH);
		BrowserAction.clickandClear(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_MAKE_SEARCH_TEXTBOX_XPATH);
		System.out.println(rule + ": " + ruleDetail);
		BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_MAKE_SEARCH_TEXTBOX_XPATH, ruleDetail);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_DROPDOWN_MAKE_SEARCH_TEXTBOX_HOVER_XPATH);
	}
	
	/**
	 * This method adds approval rule send request to list.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void addApprovalRuleSendRequestTo() throws Exception {
		if(!(CommonPage.getTestData("ApproverUserIDs")==null)) {
			String[] approvalUserIDs = CommonPage.getTestData("ApproverUserIDs").split("\\|");
			OrderingBOManagerOrderPreferencesPage.approvalRuleSendRequestToOrder(approvalUserIDs[0]);
			for(int i=1; i<approvalUserIDs.length; ++i) {
				OrderingBOManagerOrderPreferencesPage.clickAddContactSButton();
				OrderingBOManagerOrderPreferencesPage.orderApproverID(approvalUserIDs[i], i);
				CommonPage.loadXMLParameterToTestData("ApproverID"+i, approvalUserIDs[i]);
			}
		}
	}
	
	/**
	 * This method loads approver data in test data map.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void loadApproverData() throws Exception {
		if(!(CommonPage.getTestData("ApproverUserIDs")==null)) {
			String[] approvalUserIDs = CommonPage.getTestData("ApproverUserIDs").split("\\|");
			for(int i=1; i<approvalUserIDs.length; ++i) {
				CommonPage.loadXMLParameterToTestData("ApproverID"+i, approvalUserIDs[i]);
			}
		}
	}
	
	/**
	 * This method clicks on the Sequential radio button in Add Rule page.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void approvalRuleSendRequestToOrder(String approvalRuleSendRequestToOrder) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_SEQUENTIAL_RADIO_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_SEQUENTIAL_RADIO_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_SEQUENTIAL_RADIO_BUTTON_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_NON_EQUENTIAL_RADIO_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_NON_EQUENTIAL_RADIO_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_NON_EQUENTIAL_RADIO_BUTTON_XPATH);
		if(approvalRuleSendRequestToOrder.equals("S")) {
			BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_SEQUENTIAL_RADIO_BUTTON_XPATH);
		} else if(approvalRuleSendRequestToOrder.equals("N")) {
			BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_NON_EQUENTIAL_RADIO_BUTTON_XPATH);
		} else {
			throw new OrderingErrorOccured(approvalRuleSendRequestToOrder + " is a invalid condition");
		}
		
	}
	
	/**
	 * This method verify alert pop up message.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void checkAlertPopUpConfimationMessage(String section) throws Exception {
		List<WebElement> errorElement = new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 1));
		String alertPopUpText = errorElement.get(0).getText();
		switch(section) {
			case "Approval Rules - Save From Maintenance View": Assert.assertEquals(alertPopUpText, "Rule has been successfully saved", "Approval Rule save from maintenance view pop up message is incorrect");
				break;
			case "Delete From List View": Assert.assertEquals(alertPopUpText, "Delete request has been queued.", "Approval Rule delete from list view pop up message is incorrect");
				break;
			case "Delete From Maintenance View": Assert.assertEquals(alertPopUpText, "Rule has been successfully deleted", "Approval Rule delete from maintenance view pop up message is incorrect");
				break;
			case "Approval Rules - Update From Maintenance View": Assert.assertEquals(alertPopUpText, "Rule has been successfully updated", "Approval Rule update from maintenance view pop up message is incorrect");
				break;
			case "Association - Delete From Maintenance View": Assert.assertEquals(alertPopUpText, "Association has been successfully deleted", "Association delete from maintenance view pop up message is incorrect");
				break;
			case "Driver Group - Save From Maintenance View": Assert.assertEquals(alertPopUpText, "Data has been successfully saved", "Driver Group save from maintenance view pop up message is incorrect");
				break;
			case "Driver Group - Delete From Maintenance View": Assert.assertEquals(alertPopUpText, "Group has been successfully deleted", "Driver Group delete from maintenance view pop up message is incorrect");
				break;
			case "Fleet Spec Group - Delete From Maintenance View": Assert.assertEquals(alertPopUpText, "Group has been successfully deleted", "Fleet Spec Group delete from maintenance view pop up message is incorrect");
				break;
			default: throw new InvalidSwitchCaseException(section + " is a invalid section");
		}
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method clicks on the Add Rule button of Approval Rule section.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnAddRuleButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_BUTTON_XPATH);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_BUTTON_XPATH);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method clicks on the Add Contact button.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickAddContactSButton() throws Exception {		
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_BUTTON_XPATH);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_BUTTON_XPATH);
	}
	
	/**
	 * This methods is to enter the LastName in text field and select the first displayed option.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void orderApproverID(String orderApproverID, int count) throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_USER_ID_XPATH.getValue().replaceAll("position", Integer.toString(count))), 1));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_USER_ID_XPATH.getValue().replaceAll("position", Integer.toString(count))))).clear();
		System.out.println("Order Approver ID: " + orderApproverID);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_USER_ID_XPATH.getValue().replaceAll("position", Integer.toString(count))))).sendKeys(orderApproverID);
		List<WebElement> approverIDs = WebDriverAccess.getDriver().findElements(By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_USER_ID_HOVER_XPATH.getValue()));
		int counter = 0;
		for(WebElement approverID: approverIDs) {
			if(approverID.getText().replaceAll("\\n", "").trim().equals(orderApproverID)) {
				approverID.click();
				++counter;
				break;
			}
		}
		if(counter==0) {
			throw new OrderingErrorOccured("No approver ID matched with given [" + orderApproverID + "] id");
		}
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADDED_CONTACT_USER_ID_XPATH.getValue().replaceAll("position", Integer.toString(count))), orderApproverID));
		
	}
	
	/**
	 * This method clicks on the Save button in Add Rule page to save the new rule.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void clickSaveButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_SAVE_BUTTON_XPATH);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_ADD_RULE_ADD_CONTACT_SAVE_BUTTON_XPATH);
	}
	
	/**
	 * This methods verifies popUpMessage.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyPopUpMessage(String section) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_CSS);
		switch(section) {
			case "Approval Rules - Save": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "Are you sure you want to inactivate the selected rules?", "Approval Rule save pop up message is incorrect");
				break;
			case "Delete From Maintenance View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "By clicking Yes you will be deleting the record. Are you sure you want to Proceed?", "Delete pop up message is incorrect");
				break;
			case "Delete From List View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "Are you sure you want to delete this entry?", "Delete pop up message is incorrect");
				break;
			case "Approval Rules - Delete From Maintenance View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "Are you sure you want to delete this rule?", "Approval Rule delete pop up message is incorrect");
				break;
			case "Approval Rules - Update From Maintenance View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "Are you sure you want to inactivate the selected rules?", "Approval Rule update pop up message is incorrect");
				break;
			case "Association - Delete From Maintenance View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_MAINTENANCE_PAGE_CSS), "Are you sure you want to delete this association?", "Association delete pop up message is incorrect");
				break;
			case "Driver Group - Delete From Maintenance View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "Are you sure you want to delete this group?", "Driver Group delete pop up message is incorrect");
				break;
			case "Fleet Spec - Delete From Maintenance View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "Are you sure you want to delete this group?", "Fleet Spec Group delete pop up message is incorrect");
				break;
			default: throw new InvalidSwitchCaseException(section + " is a invalid section");
		}
	}
	
	public static void verifyPopUpMessageMaintenance(String section) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_MAINTENANCE_PAGE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_MAINTENANCE_PAGE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_MAINTENANCE_PAGE_CSS);
		switch(section) {
			case "Approval Rules - Save": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "Are you sure you want to inactivate the selected rules?", "Approval Rule save pop up message is incorrect");
				break;
			case "Delete From Maintenance View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "By clicking Yes you will be deleting the record. Are you sure you want to Proceed?", "Delete pop up message is incorrect");
				break;
			case "Delete From List View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "Are you sure you want to delete this entry?", "Delete pop up message is incorrect");
				break;
			case "Approval Rules - Delete From Maintenance View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "Are you sure you want to delete this rule?", "Approval Rule delete pop up message is incorrect");
				break;
			case "Approval Rules - Update From Maintenance View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "Are you sure you want to inactivate the selected rules?", "Approval Rule update pop up message is incorrect");
				break;
			case "Association - Delete From Maintenance View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_MAINTENANCE_PAGE_CSS), "Are you sure you want to delete this association?", "Association delete pop up message is incorrect");
				break;
			case "Driver Group - Delete From Maintenance View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "Are you sure you want to delete this group?", "Driver Group delete pop up message is incorrect");
				break;
			case "Fleet Spec - Delete From Maintenance View": CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_BODY_CSS), "Are you sure you want to delete this group?", "Fleet Spec Group delete pop up message is incorrect");
				break;
			default: throw new InvalidSwitchCaseException(section + " is a invalid section");
		}
	}
	
	/**
	 * This methods verifies popUpMessage.
	 * @lastModifiedBy Shivam Srivastava
	 * @throws Exception
	 */
	public static void popUpAction(String action) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_CSS);
		switch(action) {
			case "Yes": BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_YES_XPATH);
				break;
			case "No": BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_NO_XPATH);
				break;
			default: throw new InvalidSwitchCaseException(action + " is a invalid action");
		}
	}
	
	/**
	 * This methods verifies popUpMessage.
	 * @lastModifiedBy Shivam Srivastava
	 * @throws Exception
	 */
	public static void popUpActionOnMaintenancePage(String action) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_MAINTENANCE_PAGE_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_MAINTENANCE_PAGE_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_MAINTENANCE_PAGE_CSS);
		switch(action) {
			case "Yes": BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_YES_MAINTENANCE_PAGE_XPATH);
				break;
			case "No": BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ALERT_POP_UP_NO_MAINTENANCE_PAGE_XPATH);
				break;
			default: throw new InvalidSwitchCaseException(action + " is a invalid action");
		}
	}
	
	/**
	 * This method enter Approval Rule details.
	 * @lastModifiedBy Usha Naidu 
	 * @throws Exception
	 */
	public static void enterApprovalRuleDetails() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterRuleName();
		OrderingBOManagerOrderPreferencesPage.enterStartDate();
		OrderingBOManagerOrderPreferencesPage.enterEndDate();
		OrderingBOManagerOrderPreferencesPage.selectRules();
		OrderingBOManagerOrderPreferencesPage.addApprovalRuleSendRequestTo();
	}
	
	/**
	 * This method searches and selects specific approval rule
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */	
	public static void clickOnSearch(String sectionName) throws Exception {
		WebElement searchBtn = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_XPATH.name(),
				String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_XPATH.toString(), sectionName)));
		BrowserWait.waitUntilElementIsDisplayed(searchBtn);
		BrowserVerify.verifyElementIsPresent(searchBtn);
		BrowserVerify.verifyElementEnabled(searchBtn);
		searchBtn.click();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method searches specific approval rule
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */	
	public static void searchRule(String sectionName, String value) throws Exception {
		WebElement ruleNameInp = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_RULE_NAME_INPUTFLD_XPATH.name(),
		String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_RULE_NAME_INPUTFLD_XPATH.toString(), sectionName)));
		BrowserWait.waitUntilElementIsDisplayed(ruleNameInp);
		BrowserVerify.verifyElementIsPresent(ruleNameInp);
		BrowserVerify.verifyElementEnabled(ruleNameInp);
		System.out.println("Search MLO Rule: " + CommonPage.getTestData("ApprovalRuleName"));
		ruleNameInp.sendKeys(CommonPage.getTestData("ApprovalRuleName"));
		OrderingBOManagerOrderPreferencesPage.clickOnSearch(sectionName);
	}
	
	/**
	 * This method searches and selects specific approval rule
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */	
	public static void searchAndSelectApprovalRule(String sectionName, String value) throws Exception {
		OrderingBOManagerOrderPreferencesPage.searchRule(sectionName, value);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_SEARCHED_CHECKBOX_XPATH.getValue()), 1));
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_SEARCHED_CHECKBOX_XPATH);
	}
	
	/**
	 * This method verifys approval rule is deleated
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */	
	public static void verifyApprovalRuleIsDeleated(String sectionName, String value) throws Exception {
		OrderingBOManagerOrderPreferencesPage.searchRule(sectionName, value);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_SEARCHED_CHECKBOX_XPATH.getValue()), 0));
	}
	
	/**
	 * This method match the particular section and accordingly click on the Delete button of that section.
	 * @author Usha Naidu 
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void clickOnDeleteButton(String deleteButton) throws Exception {
		switch (deleteButton) {
			case "Fleet Spec Groups":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_DELETE_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_DELETE_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_DELETE_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_DELETE_XPATH);
				break;
			case "Driver Groups":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_DELETE_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_DELETE_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_DELETE_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_DELETE_XPATH);
				break;
			case "Associations":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_DELETE_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_DELETE_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_DELETE_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_DELETE_XPATH);
				break;
			case "Approval Rules":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_DELETE_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_DELETE_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_DELETE_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_DELETE_XPATH);
				break;
			default: throw new InvalidSwitchCaseException(deleteButton + " is invalid delete option");
		}
	}
	
	/**
	 * This method match the particular section and accordingly verifies the search textboxes are cleared of that section.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void verifyClearFilters(String groupOption) throws Exception {
		switch (groupOption) {
			case "Fleet Spec Groups":
				OrderingBOManagerOrderPreferencesPage.enterAllSearchTextFieldsToClear("Group ID", "id", "Group Name", "name", groupOption);
				OrderingBOManagerOrderPreferencesPage.clickOnClearFiltersButton(groupOption);
				OrderingBOManagerOrderPreferencesPage.verifyBlankSearchTextBoxes("Group ID", "id", "Group Name", "name", groupOption);
				break;
			case "Driver Groups":
				OrderingBOManagerOrderPreferencesPage.enterAllSearchTextFieldsToClear("Group ID", "driverGroupId", "Group Name", "driverGroupName", groupOption);
				OrderingBOManagerOrderPreferencesPage.clickOnClearFiltersButton(groupOption);
				OrderingBOManagerOrderPreferencesPage.verifyBlankSearchTextBoxes("Group ID", "driverGroupId", "Group Name", "driverGroupName", groupOption);
				break;
			case "Associations":
				OrderingBOManagerOrderPreferencesPage.enterAllSearchTextFieldsToClear();
				OrderingBOManagerOrderPreferencesPage.clickOnClearFiltersButton(groupOption);
				OrderingBOManagerOrderPreferencesPage.verifyClearFilters();
				break;
			case "Approval Rules":
				OrderingBOManagerOrderPreferencesPage.enterAllSearchTextFieldsToClear("Template Name", "ruleName", "Start Date", "effectiveStartDate",groupOption);
				OrderingBOManagerOrderPreferencesPage.clickOnClearFiltersButton(groupOption);
				OrderingBOManagerOrderPreferencesPage.verifyBlankSearchTextBoxes("Template Name", "ruleName", "Start Date", "effectiveStartDate", groupOption);
				break;
			case "Approval Communications":
				OrderingBOManagerOrderPreferencesPage.enterAllSearchTextFieldsToClear("Rule Name", "ruleName", "Rule ID", "ruleGroupId", groupOption);
				OrderingBOManagerOrderPreferencesPage.clickOnClearFiltersButton(groupOption);
				OrderingBOManagerOrderPreferencesPage.verifyBlankSearchTextBoxes("Rule Name", "ruleName", "Rule ID", "ruleGroupId", groupOption);
				break;
			default: throw new InvalidSwitchCaseException(groupOption + " is a invalid group option");
		}
	}
	
	/**
	 * This method match the particular section and accordingly enters the values in search textboxes on that section.	 
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void enterAllSearchTextFieldsToClear(String label1, String name1, String label2, String name2, String groupOption) throws Exception {
		switch (groupOption) {
			case "Fleet Spec Groups":
				System.out.println(label1 + ": " + CommonPage.getTestData("FleetGroupID"));
				WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label1, name1))).sendKeys(CommonPage.getTestData("FleetGroupID"));
				System.out.println(label2 + ": " + CommonPage.getTestData("FleetGroupName"));
				WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label2, name2))).sendKeys(CommonPage.getTestData("FleetGroupName"));
				break;
			case "Driver Groups":
				System.out.println(label1 + ": " + CommonPage.getTestData("DriverGroupID"));
				WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label1, name1))).sendKeys(CommonPage.getTestData("DriverGroupID"));
				System.out.println(label2 + ": " + CommonPage.getTestData("DriverGroupName"));
				WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label2, name2))).sendKeys(CommonPage.getTestData("DriverGroupName"));
				break;
			case "Approval Rules":
				System.out.println(label1 + ": " + CommonPage.getTestData("ApprovalRuleName"));
				WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label1, name1))).sendKeys(CommonPage.getTestData("ApprovalRuleName"));
				String startDate = CommonPage.getCurrentDataIn("MM/dd/yyyy");
				System.out.println(label2 + ": " + startDate);
				WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label2, name2))).sendKeys(startDate);
				break;
			case "Approval Communications":
				System.out.println(label1 + ": " + CommonPage.getTestData("RuleName"));
				WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label1, name1))).sendKeys(CommonPage.getTestData("RuleID"));
				System.out.println(label2 + ": " + CommonPage.getTestData("RuleID"));
				WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label2, name2))).sendKeys(CommonPage.getTestData("RuleName"));
				break;
			default: throw new InvalidSwitchCaseException(groupOption + " is a invalid group option");
		}
	}
	
	/**
	 * This method match the particular section and accordingly click on the Clear Filters button of that section.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void clickOnClearFiltersButton(String clearFiltersButton) throws Exception {
		switch (clearFiltersButton) {
			case "Fleet Spec Groups":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_CLEAR_FILTERS_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_CLEAR_FILTERS_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_CLEAR_FILTERS_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_CLEAR_FILTERS_XPATH);
				break;
			case "Driver Groups":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_CLEAR_FILTERS_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_CLEAR_FILTERS_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_CLEAR_FILTERS_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUPS_CLEAR_FILTERS_XPATH);
				break;
			case "Associations":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_CLEAR_FILTERS_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_CLEAR_FILTERS_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_CLEAR_FILTERS_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_CLEAR_FILTERS_XPATH);
				break;
			case "Approval Rules":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_CLEAR_FILTERS_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_CLEAR_FILTERS_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_CLEAR_FILTERS_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_CLEAR_FILTERS_XPATH);
				break;
			case "Approval Communications":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_CLEAR_FILTERS_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_CLEAR_FILTERS_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_CLEAR_FILTERS_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_CLEAR_FILTERS_XPATH);
				break;
			default: throw new InvalidSwitchCaseException(clearFiltersButton + " is a invalid clear button option");
		}
	}
	
	/**
	 * This method match the particular section and accordingly verifies the search textboxes are blank for that section.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void verifyBlankSearchTextBoxes(String label1, String name1, String label2, String name2, String groupOption) throws Exception {
		switch (groupOption) {
			case "Fleet Spec Groups":
				Assert.assertEquals(WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label1, name1))).getText(), "", "Fleet Spec Group ID should be blank");
				Assert.assertEquals(WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label2, name2))).getText(), "", "Fleet Spec Group Name should be blank");
				break;
			case "Driver Groups":
				Assert.assertEquals(WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label1, name1))).getText(), "", "Driver Groups Group ID should be blank");
				Assert.assertEquals(WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label2, name2))).getText(), "", "Driver Groups Group Name should be blank");
				break;
			case "Approval Rules":
				Assert.assertEquals(WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label1, name1))).getText(), "", "Approval Rules Template Name should be blank");
				Assert.assertEquals(WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label2, name2))).getText(), "", "Approval Rules Start Date should be blank");
				break;
			case "Approval Communications":
				Assert.assertEquals(WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label1, name1))).getText(), "", "Approval Communications Rule Name should be blank");
				Assert.assertEquals(WebDriverAccess.getDriver().findElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELDS_XPATH.getValue(), label2, name2))).getText(), "", "Approval Communications Rule ID should be blank");
				break;
			default: throw new InvalidSwitchCaseException(groupOption + "is invalid section");
		}
	}
	
	/**
	 * This method verifies the Association Name & Association ID are entered in search textboxes to clear in Association section.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void enterAllSearchTextFieldsToClear() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ID_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ID_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ID_XPATH);
		BrowserAction.clickandClear(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ID_XPATH);
		System.out.println("Association ID: " + CommonPage.getTestData("AssociationID"));
		BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ID_XPATH, CommonPage.getTestData("AssociationID"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH);
		BrowserAction.clickandClear(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH);
		System.out.println("Association Name: " + CommonPage.getTestData("AssociationName"));
		BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH, CommonPage.getTestData("AssociationName"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ORDER_LOGGER_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ORDER_LOGGER_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ORDER_LOGGER_XPATH);
		BrowserAction.clickandClear(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ORDER_LOGGER_XPATH);
		System.out.println("Order Logger: " + CommonPage.getTestData("OrderLogger"));
		BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ORDER_LOGGER_XPATH, CommonPage.getTestData("OrderLogger"));
	}
	
	/**
	 * This method verifies the all the search textbox fields gets cleared after click on Clear filters button in Association section.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void verifyClearFilters() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ID_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ID_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ID_XPATH);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ID_XPATH), "", "Association ID should be blank");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH), "", "Association Name should be blank");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ORDER_LOGGER_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ORDER_LOGGER_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ORDER_LOGGER_XPATH);
		Assert.assertEquals(BrowserAction.getElementText(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_ORDER_LOGGER_XPATH), "", "Order Logger should be blank");
	}
	
	/**
	 * This method match the particular section and accordingly click on the Toggle button of that section.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void clickOnToggleButton(String toggleButton) throws Exception {
		switch (toggleButton) {
			case "Fleet Spec Groups":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_TOGGLE_COLUMNS_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_TOGGLE_COLUMNS_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_TOGGLE_COLUMNS_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_TOGGLE_COLUMNS_XPATH);
				break;
			case "Driver Groups":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCE_DRIVER_GROUPS_TOGGLE_COLUMNS_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCE_DRIVER_GROUPS_TOGGLE_COLUMNS_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCE_DRIVER_GROUPS_TOGGLE_COLUMNS_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCE_DRIVER_GROUPS_TOGGLE_COLUMNS_XPATH);
				break;
			case "Associations":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_TOGGLE_COLUMNS_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_TOGGLE_COLUMNS_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_TOGGLE_COLUMNS_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_TOGGLE_COLUMNS_XPATH);
				break;
			case "Approval Rules":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_TOGGLE_COLUMNS_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_TOGGLE_COLUMNS_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_TOGGLE_COLUMNS_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_TOGGLE_COLUMNS_XPATH);
				break;
			case "Approval Communications":
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_TOGGLE_COLUMNS_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_TOGGLE_COLUMNS_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_TOGGLE_COLUMNS_XPATH);
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_TOGGLE_COLUMNS_XPATH);
				break;
			default: throw new InvalidSwitchCaseException(toggleButton + "is invalid toggle button option");
		}
	}
	
	/**
	 * This method comparing all checked toggle checkboxes labels with all visible table column headings
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void compareAllToggleCheckedboxesWithVisibleTableColumns() throws Exception {
		List<String> checkedColumnsList;
		List<String> tableHeadersList;
		checkedColumnsList = BrowserAccess.getElementsText(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CHECKED_XPATH);
		tableHeadersList = BrowserAction.getElementsText(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_GRID_VIEW_LIST_TABLE_HEADING_XPATH);
		tableHeadersList.remove(0);
		for (int i = 0; i < checkedColumnsList.size(); i++) {
			String checkedColumnText = checkedColumnsList.get(i);
			String tableHeading = tableHeadersList.get(i);
			String ignoringSpacesIncheckedColumnText = checkedColumnText.replaceAll("\\s+", "");
			String ignoringSpacesIntableHeading = tableHeading.replaceAll("\\s+", "");
			Assert.assertEquals(ignoringSpacesIncheckedColumnText, ignoringSpacesIntableHeading);
		}
	}
	
	/**
	 * This method unchecking few toggle checkboxes from toggle button.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void uncheckFewToggleCheckboxes() throws Exception{
		List <WebElement> deselectedCheckboxesList = BrowserAccess.getElements(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCE_FLEET_SPEC_GROUP_TOGGLE_CHECKBOX_XPATH);
		for(int i=0;i<deselectedCheckboxesList.size()-3;i++){
			deselectedCheckboxesList.get(i).click();
		}
	}
	
	/**
	 * This method comparing checked toggle checkboxes labels with visible table column headings.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void compareToggleCheckedboxesWithVisibleTableHeadings() throws Exception {
		List<String> checkedColumnsList;
		List<String> tableHeadersList;
		checkedColumnsList = BrowserAccess.getElementsText(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_CHECKED_XPATH);
		tableHeadersList = BrowserAction.getElementsText(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_GROUP_SECTION_GRID_VIEW_LIST_TABLE_HEADING_XPATH);
		tableHeadersList.remove(0);
		for (int i = 0; i < checkedColumnsList.size(); i++) {
			String checkedColumnText = checkedColumnsList.get(i);
			String tableHeading = tableHeadersList.get(i);
			String ignoringSpacesIncheckedColumnText = checkedColumnText.replaceAll("\\s+", "");
			String ignoringSpacesIntableHeading = tableHeading.replaceAll("\\s+", "");
			Assert.assertEquals(ignoringSpacesIncheckedColumnText, ignoringSpacesIntableHeading);
		}
	}
	
	/**
	 * This method verifies the export functionality.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void verifyExportCSV(String mloSection, String className) throws Exception {
		OrderingBOManagerOrderPreferencesPage.clickExportButton(mloSection, className);
		OrderingBOManagerOrderPreferencesPage.verifyCSVData(className, mloSection);
	}
	
	
	/**
	 * This method clicks on the export button on the BO ManagerOrderPreferences page.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void clickExportButton(String mloSection, String className) throws Exception {
		WebDriverAccess.getElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_GENERIC_EXPORT_XPATH.toString(), mloSection))).isDisplayed();
		WebDriverAccess.getElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_GENERIC_EXPORT_XPATH.toString(), mloSection))).isEnabled();
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		WebDriverAccess.getElement(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_GENERIC_EXPORT_XPATH.toString(), mloSection))).click();
		OrderingCommonPage.checkAlertPopUp();
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"), className);
	}
	
	/**
	 * This method verifies the CSV Column Heading Values with Grid Column Heading Values.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void verifyCSVData(String fileNameStartsWith, String mloSection) throws Exception {
		List<String> csvRows = ExcelUtil.getCSVRows(System.getProperty("user.dir") + "\\target\\" + fileNameStartsWith + ".csv");
		System.out.println("CSV Rows List : " + csvRows);
		List<String> gridColumnHeadingNamesList = getColumnHeadingList(mloSection);
		int csvRowsCount = csvRows.size();
		if (csvRowsCount == 0) {
			System.out.println("Table does not contains any row.");
		} else {
			String csvColumnHeadingRow = csvRows.get(0);
			String[] csvColumnHeadingSplittedRowValues;
			csvColumnHeadingSplittedRowValues = csvColumnHeadingRow.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			List<String> csvColumnHeadingList = Arrays.asList(csvColumnHeadingSplittedRowValues);
			System.out.println("CSV Column Heading Values :" + csvColumnHeadingList);
			System.out.println("Grid Column Heading Values :" + gridColumnHeadingNamesList);
			csvColumnHeadingList.containsAll(gridColumnHeadingNamesList);
		}
	}
	
	/**
	 * This method returns all the column headings of the table.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static List<String> getColumnHeadingList(String mloSection) throws Exception {
		List<String> gridColumnHeadingList = null;
		gridColumnHeadingList = WebDriverAccess.getElementsText(By.xpath(String.format(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_GENERIC_GRID_VIEW_LIST_TABLE_HEADING_XPATH.toString(), mloSection)));
		gridColumnHeadingList.remove(0);
		System.out.println("Column Names:" + gridColumnHeadingList);
		return gridColumnHeadingList;
	} 
	
	/**
	 * This method searches and selects specific approval rule
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */	
	public static void searchAndClickApproverCommRule(String sectionName, String value) throws Exception {
		OrderingBOManagerOrderPreferencesPage.searchRule(sectionName, value);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATION_FIRST_ROW_XPATH.getValue()), 1));
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATION_FIRST_ROW_XPATH);
	}
	
	/**
	 * This method searches and selects specific approval rule
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */	
	public static void addAssociation() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_ADD_ASSOCIATION_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_ADD_ASSOCIATION_BUTTON_XPATH);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_ADD_ASSOCIATION_BUTTON_XPATH);
		OrderingBOManagerOrderPreferencesPage.enterDetailsOfAssociationAndSave();
	}
	
	/** 
	 * This method verifies whether the new Rule Name for Approval Rule is generated or not.
	 * @lastModifiedBy Usha Naidu 
	 * @throws Exception
	 */
	public static void entrAssociationName() throws Exception {
		String associationName = "Associa" + CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("AssociationName", associationName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_ASSOCIATION_NAME_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_ASSOCIATION_NAME_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_ASSOCIATION_NAME_TEXTBOX_XPATH);
		System.out.println("AssociationName Name: " + CommonPage.getTestData("AssociationName") );
		BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_ASSOCIATION_NAME_TEXTBOX_XPATH, CommonPage.getTestData("AssociationName"));
	}
	
	/**
	 * This method searches and selects specific Association
	 * @lastModifiedBy rmakhija
	 * @throws Exception
	 */	
	public static void searchAndSelectAssociation(String sectionName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH);
		BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH, CommonPage.getTestData("AssociationName"));
		OrderingBOManagerOrderPreferencesPage.clickOnSearch(sectionName);
		new WebDriverWait(WebDriverAccess.getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SEARCHED_DRIVER_GROUP_CHECKBOX_XPATH.getValue())));
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SEARCHED_DRIVER_GROUP_CHECKBOX_XPATH);
	}
	
	/**
	 * This method searches and clicks on  specific Association
	 * @lastModifiedBy rmakhija
	 * @throws Exception
	 */	
	public static void searchAndClickAssociation(String sectionName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH);
		BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH, CommonPage.getTestData("AssociationName"));
		OrderingBOManagerOrderPreferencesPage.clickOnSearch(sectionName);
		new WebDriverWait(WebDriverAccess.getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SEARCHED_ROW_XPATH.getValue())));
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SEARCHED_ROW_XPATH);
	}
	
	/**
	 * This method clicks on toggle switch for approver communication preferences
	 * @lastModifiedBy rmakhija
	 * @throws Exception
	 */	
	public static void clickPreferenceToggleButton(String preferecnce) throws Exception {
		switch (preferecnce) {
			case "Approved Orders Daily":
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_MAINTENANCE_RULE_APPROVE_DAILY_XPATH);
				break;
			default: throw new OrderingErrorOccured(preferecnce + " is invalid preference option");
		}
	}
	
	/**
	 * This method clicks on Back Button of approver communication maintenance
	 * @lastModifiedBy rmakhija
	 * @throws Exception
	 */	
	public static void clickApproverCommBackButton() throws Exception {
        BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMMUNICATIONS_MAINTENANCE_BACK_BUTTON_XPATH);
        BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVER_COMM_SAVEANDCLOSE_XPATH);
	}
	
	/**
	 * This method match the particular section and accordingly verifies the add rule/group of that section.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void verifyAddFromMaintenancePage(String groupOption) throws Exception {
		switch (groupOption) {
			case "Fleet Spec Groups":
				OrderingBOManagerOrderPreferencesPage.searchAndClickFleetSpec();
				OrderingBOManagerOrderPreferencesPage.addFleetSpecGroupPageLoaded();
				OrderingBOManagerOrderPreferencesPage.clickOnAddButtonFromMaintenancePage("Fleet Spec Groups");
				OrderingBOManagerOrderPreferencesPage.clickSaveAndClose();
				OrderingCommonPage.checkAlertPopUp();
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingBOManagerOrderPreferencesPage.addFleetSpecGroupPageLoaded();
				OrderingBOManagerOrderPreferencesPage.enterFleetSpecDetailsAndSave();
				OrderingCommonPage.checkAlertPopUp();
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.verifyNoAlertPopUpDispalyed();
				break;
			case "Associations":
				OrderingBOManagerOrderPreferencesPage.clickOnAnyExistingRule("associations-queue", groupOption);
				OrderingBOManagerOrderPreferencesPage.clickOnAddButtonFromMaintenancePage(groupOption);
				OrderingBOManagerOrderPreferencesPage.enterDetailsOfAssociationAndSave();
				break;
			default: throw new InvalidSwitchCaseException(groupOption + " is a invalid option");
		}
	}
	

	/**
	 * This method match the particular section and accordingly verifies the add rule/group of that section.
	 * @lastModifiedBy Usha Naidu
	 * @throws Exception
	 */
	public static void clickOnAddButtonFromMaintenancePage(String groupOption) throws Exception {
		switch (groupOption) {
			case "Fleet Spec Groups": new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(
						By.id(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_BUTTON_ID.getValue()))).click();
				break;
			case "Driver Groups": new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(
						By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_ADD_GROUP_BUTTON_XPATH.getValue()))).click();
				break;
			case "Associations": new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(
						By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_ADD_GROUP_BUTTON_XPATH.getValue()))).click();
				break;
			default: throw new InvalidSwitchCaseException(groupOption + " is a invalid option");
		}
	}
	
    /**		
	 * This method is to create with order with order logger.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void createOrdersWithLoggerUser(String className) throws Throwable {
		OrderingLoginPage.openCustomFOApplication(CommonPage.getTestData("OrderLogger"));
		
		System.out.println(CommonPage.getTestData("Username"));
		if(CommonPage.getTestData("OrderLogger").equalsIgnoreCase("Orderint4")) {
			OrderingHomePage.waitForHomePageMLO();
		}
		else {
		OrderingHomePage.waitForHomePage();
		}
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
		OrderingBOManagerOrderPreferencesPage.createOrderWithLoggedInOrder(className);
	}
	
	/**		
	 * This method is to create with order with order logger.
	 * @throws Throwable 
	 * @lastModifiedBy ssrivastava
	 */
	public static void loginWithLoggerUser(String className) throws Throwable {
		OrderingLoginPage.openCustomFOApplication(CommonPage.getTestData("OrderLogger"));
		
		System.out.println(CommonPage.getTestData("Username"));
		if(CommonPage.getTestData("OrderLogger").equalsIgnoreCase("Orderint4")){
			OrderingHomePage.waitForHomePageMLO();
		}
		else {
		OrderingHomePage.waitForHomePage();
		}
	}
	
	/**
	 * This method performs create order functionality with logged in user.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void createOrderWithLoggedInOrder(String className) throws Throwable {
		int orderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		for(int i=1; i<=orderCount; ++i) {
			OrderingHomePage.selectSideMenuOption("Ordering");
			OrderingCommonPage.checkGlobalSpinnerPopUp();
			OrderingHomePage.selectOrderingMenuOption("Create Order");
			OrderingStartHerePage.enterStartHerePageDetails(CommonPage.getTestData("ApplicationURL"));
			OrderingStartHerePage.clickSaveAndNext();
			OrderingDriverPage.enterDriverData();
			OrderingStartHerePage.clickSaveAndNext();
			OrderingVehiclePage.enterDetailsVehicleData();
			OrderingStartHerePage.clickSaveAndNext();
			OrderingBillingAndRegistrationPage.enterBillingAndRegistrationDetails();
			OrderingStartHerePage.clickSaveAndNext();
			OrderingDealerPage.selectDealer();
			OrderingStartHerePage.clickSaveAndNext();
			OrderingSummaryPage.preSummaryPageActions(className);
			OrderingSummaryPage.clickSubmit();
			OrderingSummaryPage.confirmSubmit();
			OrderingCommonPage.checkAlertPopUp();
			OrderingSummaryPage.waitForPopUpResultBox();
			OrderingBOManagerOrderPreferencesPage.validateManagerApprovalSubmissionMessage();
			OrderingSummaryPage.verifySuccessfulSubmissionPopUp();
			OrderingSummaryPage.waitForSummaryPage();
			OrderingBOManagerOrderPreferencesPage.validateOrderStatus();
			OrderingSummaryPage.clickExportButton("Post", className);
			OrderingHomePage.clickOnCloseSummaryPage();
			CommonPage.loadXMLParameterToTestData("LoggerLogNumber"+i , CommonPage.getElementOrderObject().getLogNumber());
			PDFReporter.takeExtraScreenshot();
		}
	}
	
	/**
	 * This method resubmit all the order with logged in user.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void resubmitAllOrdersWithLoggedInOrder(String className) throws Throwable {
		int singleOrderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		for(int j=1; j<=singleOrderCount; ++j) {
			OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+j));
			OrderingMyOrdersPage.clickOnSearch();
			OrderingMyOrdersPage.verifySingleSearchedOrderDisplayed();
			OrderingMyOrdersPage.verifySingleSearchedOrderRowColour();
			OrderingMyOrdersPage.clickSingleSearchedOrderDisplayed();
			OrderingBOManagerOrderPreferencesPage.resubmitOrderWithLoggedInOrder(className);			
		}
	}
	
	/**
	 * This method resubmit the order with logged in user.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void resubmitOrderWithLoggedInOrder(String className) throws Throwable {
		CommonPage.initializeElementOrderObject();
		OrderingCommonPage.checkAlertPopUp();
		OrderingStartHerePage.waitForStartHerepage();		
		OrderingStartHerePage.clickSaveAndNext();
		OrderingCommonPage.checkAlertPopUp();
		OrderingDriverPage.waitForDriverPage();		
		OrderingStartHerePage.clickSaveAndNext();
		OrderingCommonPage.checkAlertPopUp();
		OrderingVehiclePage.waitForVehiclePage();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingCommonPage.checkAlertPopUp();
		OrderingBillingAndRegistrationPage.waitForBillingAndRegistrationPage();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingCommonPage.checkAlertPopUp();
		OrderingDealerPage.selectDealer();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingSummaryPage.preSummaryPageActions(className);
		OrderingSummaryPage.clickSubmit();
		OrderingSummaryPage.confirmSubmit();
		OrderingCommonPage.checkAlertPopUp();
		OrderingSummaryPage.waitForPopUpResultBox();
		OrderingBOManagerOrderPreferencesPage.validateManagerApprovalSubmissionMessage();
		OrderingSummaryPage.verifySuccessfulSubmissionPopUp();
		OrderingSummaryPage.waitForSummaryPage();
		OrderingBOManagerOrderPreferencesPage.validateOrderStatus();
		OrderingSummaryPage.clickExportButton("Post", className);
		OrderingHomePage.clickOnCloseSummaryPage();
	}
	
	/**
	 * This method verifies the order status as Saved- Manager Approvals Pending.
	 * @lastModifiedBy unaidu
	 * @throws Exception
	 */
	public static void validateOrderStatus() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_GENERAL_INFO_STAUS_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_GENERAL_INFO_STAUS_CSS);
		BrowserVerify.verifyElementEnabled(OrderingSummaryPageEnum.ORDERING_SUMMARY_GENERAL_INFO_STAUS_CSS);
		//CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_GENERAL_INFO_STAUS_CSS), "Saved- Manager Approvals Pending", "Incorrect order status");
	}

	/**
	 * This method verifies weather the approval message is displayed or not.
	 * @lastModifiedBy unaidu
	 * @throws Exception
	 */
	public static void validateManagerApprovalSubmissionMessage() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMISSION_POP_UP_CSS.getValue()), 1));
		BrowserWait.waitUntilElementIsDisplayed(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMISSION_POP_UP_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMISSION_POP_UP_CSS);
		BrowserVerify.verifyElementEnabled(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMISSION_POP_UP_CSS);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingSummaryPageEnum.ORDERING_SUMMARY_SUBMIT_CONFIRMATION_POPUP_MESSAGE_CSS), "Your Order has been Submitted for Approval.", "Order pop up message is incorrect");
	}

	/**
	 * This method verifies that order logs are not present logger users My Order section.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void verifyOrdersNotDisplayedInMyOrderSection() throws Throwable {
		int orderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		for(int i=1; i<=orderCount; ++i) {
			OrderingMyOrdersPage.enterTextInLogNumberSearchFieldFO(CommonPage.getTestData("LoggerLogNumber"+i));
			OrderingMyOrdersPage.clickOnSearch();
			PDFReporter.takeExtraScreenshot();
			OrderingMyOrdersPage.verifyNoSearchedOrderDisplayed();
		}
	}
	
	/**
	 * This method selects group on Associations maintenance page
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */	
	public static void selectGroupFromAssocMaintanancePage(String groupOption) throws Exception {
		switch (groupOption) {
			case "Fleet Spec Groups":
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(
						By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_FLEETSPEC_ADD_GROUP_BUTTON_XPATH.getValue()))).click();
				OrderingBOManagerOrderPreferencesPage.selectFirstRowAssocMaintananceFleetSpec();
				//OrderingBOManagerOrderPreferencesPage.clickSaveButton();
				//OrderingCommonPage.checkAlertPopUp();
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				break;
			case "Driver Groups": 
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_DRIVERGRP_ADD_GROUP_BUTTON_XPATH);
				OrderingBOManagerOrderPreferencesPage.selectFirstRowAssocMaintananceDriver();
				OrderingBOManagerOrderPreferencesPage.clickSaveButton();
				OrderingCommonPage.checkAlertPopUp();
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				break;
			default: throw new InvalidSwitchCaseException(groupOption + " is a invalid option");
		}
	}
	
	/**
	 * This method verifies that order logs are not present in super users On-Order queues section.
	 * @throws Throwable 
	 * @lastModifiedBy shisingh
	 */
	public static void verifyOrdersNotDisplayedInOnOrderQueues() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("On-Order");
		OrderingBOOnOrderQueuePage.waitForOnOrderQueuePage();
		int orderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		for(int i=1; i<=orderCount; ++i) {
			OrderingBOOnOrderQueuePage.searchBy("logNumber", CommonPage.getTestData("LoggerLogNumber"+i));
			PDFReporter.takeExtraScreenshot();
		}
	}
	
	/**
	 * This method selects first row of fleet spec group on Association maintenance page
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */	
	public static void selectFirstRowAssocMaintananceFleetSpec() throws Exception {
		if(BrowserAccess.getElementText(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_FLEETSPEC_NDA_XPATH).equals("No data available in table")) {
			throw new OrderingErrorOccured("No data available in table");
		} else {
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(
					By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_FLEETSPEC_FIRSTROW_CHECKBOX_XPATH.getValue()))).click();

		}
	}
	
	/**
	 * This method selects first row of Driver group on Association maintenance page
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */	
	public static void selectFirstRowAssocMaintananceDriver() throws Exception {
		if(BrowserAccess.getElementText(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_DRIVERGRP_NDA_XPATH).equals("No data available in table")) {
			throw new Exception("No data available in table");
		} else {
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(
					By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_DRIVERGRP_FIRSTROW_CHECKBOX_XPATH.getValue()))).click();

		}
	}
	
	/**
	 * This method enters data for association and clicks on save
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */	
	public static void enterDetailsOfAssociationAndSave() throws Exception {
		OrderingBOManagerOrderPreferencesPage.entrAssociationName();
		BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_ORDERLOGGER_TEXTBOX_XPATH,CommonPage.getTestData("OrderLogger"));
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_ORDERLOGGER_SELECTION_XPATH);
		WebElement element = WebDriverAccess.getElement(BrowserAccess.getLocator(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_FLEETSPEC_FIRSTROW_CHECKBOX_XPATH.name(),OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_FLEETSPEC_FIRSTROW_CHECKBOX_XPATH.toString()));
		jsExec.executeScript("arguments[0].scrollIntoView(true);", element);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_FLEETSPEC_FIRSTROW_CHECKBOX_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_FLEETSPEC_FIRSTROW_CHECKBOX_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_DRIVERGRP_FIRSTROW_CHECKBOX_XPATH);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_DRIVERGRP_FIRSTROW_CHECKBOX_XPATH);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_SAVE_GROUP_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method deletes group on Associations maintenance page
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */	
	public static void deleteGroupFromAssocMaintanancePage(String groupOption) throws Exception {
		switch (groupOption) {
			case "Fleet Spec Groups":
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(
						By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_FLEETSPEC_DELETE_XPATH.getValue()))).click();
				OrderingBOManagerOrderPreferencesPage.clickSaveButton();
				OrderingCommonPage.checkAlertPopUp();
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				break;
			case "Driver Groups": 
				BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_MAINTENANCE_PAGE_DRIVERGRP_DELETE_XPATH);
				OrderingBOManagerOrderPreferencesPage.clickSaveButton();
				OrderingCommonPage.checkAlertPopUp();
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				break;
			default: throw new InvalidSwitchCaseException(groupOption + " is a invalid option");
		}
	}
	
	/**
	 * This method searches and selects specific Association
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */	
	public static void verifyAssociationDeleted(String sectionName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH);
		BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SECTION_ASSOCIATION_NAME_XPATH, CommonPage.getTestData("AssociationName"));
		OrderingBOManagerOrderPreferencesPage.clickOnSearch(sectionName);
		new WebDriverWait(WebDriverAccess.getDriver(), 5).until(ExpectedConditions.numberOfElementsToBe(By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_ASSOCIATIONS_SEARCHED_DRIVER_GROUP_CHECKBOX_XPATH.getValue()),0));
	}
	
	/**
	 * This method clicks on the Add Rule button of Driver Group section.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickOnAddGroupButton() throws Exception {
		OrderingBOManagerOrderPreferencesPage.clickOnButtonOfSection("section-driver-groups", "btn green btn-shared addDriverGroup");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method verifies all the elements of Driver Group is displayed or not.
	 * @lastModifiedBy shisingh 
	 * @throws Exception
	 */
	public static void addDriverGroupPageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_HEADING_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_HEADING_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_GROUP_NAME_TEXTBOX_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_ADD_DRIVERS_BUTTON_ID);		
	}
	
	/**
	 * This method enters new Driver Group Details.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterDriverGroupDetails() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterGroupName();
		OrderingBOManagerOrderPreferencesPage.enterEmployeeID();
		OrderingBOManagerOrderPreferencesPage.clickOnSearchButton();
		OrderingBOManagerOrderPreferencesPage.selectSingleRowInNewDriverGroupTable();
	}
	
	/** 
	 * This method enters Driver Group name.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterGroupName() throws Exception {
		String driverGroupName = "AT" + CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("DriverGroupName", driverGroupName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_GROUP_NAME_TEXTBOX_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_GROUP_NAME_TEXTBOX_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_GROUP_NAME_TEXTBOX_ID);
		BrowserAction.clickandClear(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_GROUP_NAME_TEXTBOX_ID);
		System.out.println("Driver Group Name: " + CommonPage.getTestData("DriverGroupName"));
		BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_GROUP_NAME_TEXTBOX_ID, CommonPage.getTestData("DriverGroupName"));
	}
	
	/** 
	 * This method enters driver employee id.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterEmployeeID() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_EMPLOYEE_ID_TEXTBOX_NAME);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_EMPLOYEE_ID_TEXTBOX_NAME);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_EMPLOYEE_ID_TEXTBOX_NAME);
		if((CommonPage.getTestData("DriverEmpId")==null)) {
			String driverEmployeeID = DbConnector.getActiveDriverWithActiveBreakdown("FA", CommonPage.getTestData("ClientNumber"));
			BrowserAction.clickandClear(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_EMPLOYEE_ID_TEXTBOX_NAME);
			System.out.println("Driver Emp ID: " + driverEmployeeID);
			BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_EMPLOYEE_ID_TEXTBOX_NAME, driverEmployeeID);
		} else {
			System.out.println("Driver Emp ID: " + CommonPage.getTestData("DriverName"));
			BrowserAction.clickandClear(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_EMPLOYEE_ID_TEXTBOX_NAME);
			BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_RULE_EMPLOYEE_ID_TEXTBOX_NAME, CommonPage.getTestData("DriverName"));
		}
	}
	
	/** 
	 * This method clicks on search button on Driver Group maintenance page.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void clickOnSearchButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_SEARCH_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_SEARCH_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_SEARCH_BUTTON_CSS);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_SEARCH_BUTTON_CSS);
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/** 
	 * This method clicks on clear filter button on Driver Group.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void clickOnClearFilterButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_CLEAR_FILTER_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_CLEAR_FILTER_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_CLEAR_FILTER_BUTTON_CSS);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_CLEAR_FILTER_BUTTON_CSS);
	}
	
	/** 
	 * This method verifys that single row is dispalyed on Driver Group and checks that driver.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void selectSingleRowInNewDriverGroupTable() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_TABLE_ROWS_CSS.getValue()), 1));
		BrowserAction.getElement(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_TABLE_ROWS_CSS).findElement(By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_TABLE_ROWS_CHECKBOX_XPATH.getValue())).click();
	}
	
	/** 
	 * This method verifys that single row is dispalyed on Driver Group.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void clickOnSaveDriverGroupDetails() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_SAVE_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_SAVE_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_SAVE_BUTTON_ID);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_SAVE_BUTTON_ID);
	}
	
	/** 
	 * This method searchs and select the Driver Group.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void searchAndSelectOnTheDriverGroup() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterDetailInFieldOfSection("section-driver-groups", "driverGroupName", "DriverGroupName", CommonPage.getTestData("DriverGroupName"));
		OrderingBOManagerOrderPreferencesPage.clickOnButtonOfSection("section-driver-groups", "btn green btn-shared search");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingBOManagerOrderPreferencesPage.selectFirstRowOfTableSection("section-driver-groups");
	}
	
	/** 
	 * This method searche, select and click on the specific column of Driver Group.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void searchSelectClickOnTheDriverGroup() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterDetailInFieldOfSection("section-driver-groups", "driverGroupName", "DriverGroupName", CommonPage.getTestData("DriverGroupName"));
		OrderingBOManagerOrderPreferencesPage.clickOnButtonOfSection("section-driver-groups", "btn green btn-shared search");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingBOManagerOrderPreferencesPage.selectFirstRowOfTableSection("section-driver-groups");
		OrderingBOManagerOrderPreferencesPage.clickFirstRowColumnOfTableSection("section-driver-groups", "driverGroupName");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/** 
	 * This method verifys that no row is dispalyed on Driver Group.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void verifyNoDriverGroupPresent() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterDetailInFieldOfSection("section-driver-groups", "driverGroupName", "DriverGroupName", CommonPage.getTestData("DriverGroupName"));
		OrderingBOManagerOrderPreferencesPage.clickOnButtonOfSection("section-driver-groups", "btn green btn-shared search");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingBOManagerOrderPreferencesPage.verifyNoRowOfTableSection("section-driver-groups");
	}
	
	/** 
	 * This method enter text in the given text field of the given section.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void enterDetailInFieldOfSection(String section, String field, String key, String testData) throws Exception {
		String xpathOfField = OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_FIELD_XPATH.getValue().replaceAll("section", section).replaceAll("textfield", field);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfField))).click();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfField))).clear();
		System.out.println(key + ": " + testData);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfField))).sendKeys(testData);
	}
	
	/** 
	 * This method clicks on the given button of the given section.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void clickOnButtonOfSection(String section, String buttonClass) throws Exception {
		String xpathOfButton = OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_SEARCH_BUTTON_XPATH.getValue().replaceAll("section", section).replaceAll("buttonClass", buttonClass);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfButton))).click();
	}
	
	/** 
	 * This method clicks on the first row of given section.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void selectFirstRowOfTableSection(String section) throws Exception {
		String xpathOfFirstRow = OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FIRST_ROW_TABEL_XPATH.getValue().replaceAll("section", section);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(xpathOfFirstRow), 1));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfFirstRow))).click();
	}
	
	/** 
	 * This method clicks on the first row of given section.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void clickFirstRowColumnOfTableSection(String section, String column) throws Exception {
		String xpathOfFirstRow = OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FIRST_ROW_COLUMN_TABEL_XPATH.getValue().replaceAll("section", section).replaceAll("column", column);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(xpathOfFirstRow), 1));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfFirstRow))).click();
	}
	
	/** 
	 * This method verify no row of given section is present.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void verifyNoRowOfTableSection(String section) throws Exception {
		String xpathOfFirstRow = OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FIRST_ROW_TABEL_XPATH.getValue().replaceAll("section", section);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(xpathOfFirstRow), 0));
	}
	
	/** 
	 * This method clicks on search button on Driver Group.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void clickOnDeleteDriverGroupButton() throws Exception {
		OrderingBOManagerOrderPreferencesPage.clickOnButtonOfSection("section-driver-groups", "btn custom-action green btn-shared bulkDelete");
	}
	
	/** 
	 * This method clicks on add button on Driver Group maintenance page.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void clickAddButtonFromDriverGroupMaintenencePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_BUTTON_ID);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_ADD_BUTTON_ID);
	}
	
	/** 
	 * This method clicks on search button on Driver Group maintenance page.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void clickDeleteButtonFromDriverGroupMaintenencePage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_DELETE_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_DELETE_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_DELETE_BUTTON_ID);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_DELETE_BUTTON_ID);
	}
	
	/** 
	 * This method checks side menu option is loaded for order logger.
	 * @lastModifiedBy shsingh
	 * @throws Exception
	 */
	public static void orderingSideMenuOptionLoadedForOrderLogger() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_TITLE_LABEL_CLASS);
		BrowserVerify.verifyElementIsPresent(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_TITLE_LABEL_CLASS);
		BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_ORDERING_TITLE_LABEL_CLASS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_SIDEMENU_CREATE_ORDER_LINK_CSS);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_BATCH_TEMPLATE_DOWNLOAD_HYPERLINK_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_WORK_IN_PROCESS_ORDERS_HYPERLINK_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_MY_ORDERS_HYPERLINK_XPATH);
	}
	
	/**
	 * This method clicks on the Add Rule button of Fleet Spec section.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void clickOnFleetSpecAddGroupButton() throws Exception {
		OrderingBOManagerOrderPreferencesPage.clickOnButtonOfSection("section-fleet-spec-groups", "btn green btn-shared addFleetSpecGroup");
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/**
	 * This method verifies all the elements of Fleet Spec Group is displayed or not.
	 * @lastModifiedBy mkaricharla 
	 * @throws Exception
	 */
	public static void addFleetSpecGroupPageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_GROUP_ADD_GROUP_HEADING_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_GROUP_ADD_GROUP_HEADING_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_GROUP_ADD_GROUP_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_GROUP_NAME_TEXTBOX_XPATH);	
	}
	
	/** 
	 * This method enters Fleet Spec Group name.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void enterFleetSpecGroupName() throws Exception {
		String fleetGroupName = "ATFleet" + CommonPage.randomAlphaNumericString();
		CommonPage.loadXMLParameterToTestData("FleetGroupName", fleetGroupName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_GROUP_NAME_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_GROUP_NAME_TEXTBOX_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_GROUP_NAME_TEXTBOX_XPATH);
		BrowserAction.clickandClear(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_GROUP_NAME_TEXTBOX_XPATH);
		System.out.println("Fleet Spec Group Name: " + CommonPage.getTestData("FleetGroupName"));
		BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_GROUP_NAME_TEXTBOX_XPATH, CommonPage.getTestData("FleetGroupName"));
	}
	
	/** 
	 * This method enters Fleet Spec Id.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void enterAndSelectFleetSpecId() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_SPEC_ID_TEXTBOX_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_SPEC_ID_TEXTBOX_XPATH);
		BrowserAction.clickandClear(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_SPEC_ID_TEXTBOX_XPATH);
		System.out.println("Fleet Spec Id: " + CommonPage.getTestData("FleetSpecSearch"));
		BrowserAction.enterFieldValue(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_SPEC_ID_TEXTBOX_XPATH, CommonPage.getTestData("FleetSpecSearch"));
		CommonPage.pressEnter();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(By.xpath(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_SPEC_SELECT_CHECKBOX_XPATH.getValue()))).click();		
	}
	
	/** 
	 * This method enters Fleet Spec Id.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void clickOnFleetSpecSave() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_SPEC_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_SPEC_SAVE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_SPEC_SAVE_BUTTON_XPATH);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEETSPEC_SPEC_SAVE_BUTTON_XPATH);
	}
	
	/** 
	 * This method enters Fleet Spec Id.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void enterFleetSpecDetailsAndSave() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterFleetSpecGroupName();
		OrderingBOManagerOrderPreferencesPage.enterAndSelectFleetSpecId();
		OrderingBOManagerOrderPreferencesPage.clickOnFleetSpecSave();
	    OrderingCommonPage.checkAlertPopUp();
	}
	
	/** 
	 * This method enters searches and selects Fleet Spec.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void searchAndSelectFleetSpec() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterDetailInFieldOfSection("section-fleet-spec-groups", "name", "FleetGroupName", CommonPage.getTestData("FleetGroupName"));
		OrderingBOManagerOrderPreferencesPage.clickOnSearch("fleet-spec-groups-queue");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingBOManagerOrderPreferencesPage.selectFirstRowOfTableSection("section-fleet-spec-groups");
	}
	
	/** 
	 * This method enters deletes the selected fleet spec.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void fleetSpecDelete() throws Exception {
		OrderingBOManagerOrderPreferencesPage.clickOnDeleteButton("Fleet Spec Groups");
		OrderingBOManagerOrderPreferencesPage.verifyPopUpMessage("Delete From List View");
		OrderingBOManagerOrderPreferencesPage.popUpAction("Yes");
		OrderingBOManagerOrderPreferencesPage.checkAlertPopUpConfimationMessage("Delete From List View");
	}
	
	/** 
	 * This method enters searches and selects Fleet Spec.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void verifyFletSpecDeleted() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterDetailInFieldOfSection("section-fleet-spec-groups", "name", "FleetGroupName", CommonPage.getTestData("FleetGroupName"));
		OrderingBOManagerOrderPreferencesPage.clickOnSearch("fleet-spec-groups-queue");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		String xpathOfFirstRow = OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FIRST_ROW_TABEL_XPATH.getValue().replaceAll("section", "section-fleet-spec-groups");
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.xpath(xpathOfFirstRow), 0));
	}
	
	public static void clickSaveAndClose() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_SPEC_GROUP_ADD_RULE_ALERT_POP_UP_SAVEANDCLOSE_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_SPEC_GROUP_ADD_RULE_ALERT_POP_UP_SAVEANDCLOSE_XPATH);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_FLEET_SPEC_GROUP_ADD_RULE_ALERT_POP_UP_SAVEANDCLOSE_XPATH);
	}
	
	
	/** 
	 * This method enters searches and selects Fleet Spec.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void searchAndClickFleetSpec() throws Exception {
		OrderingBOManagerOrderPreferencesPage.enterDetailInFieldOfSection("section-fleet-spec-groups", "name", "FleetGroupName", CommonPage.getTestData("FleetGroupName"));
		OrderingBOManagerOrderPreferencesPage.clickOnSearch("fleet-spec-groups-queue");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		OrderingBOManagerOrderPreferencesPage.selectFirstRowOfTableSection("section-fleet-spec-groups");
		OrderingBOManagerOrderPreferencesPage.clickFirstRowColumnOfTableSection("section-fleet-spec-groups", "name");
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
	}
	
	/** 
	 * This method creates fleet spec from maintenance page
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void verifyCreatedDriverGroup(String noOfDrivers) throws Exception {	
		new WebDriverWait(WebDriverAccess.getDriver(), 2).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='section-driver-groups']//td[3]")));
		String searchedGroupName = WebDriverAccess.getDriver().findElement(By.xpath("//div[@id='section-driver-groups']//td[3]")).getText();
		Assert.assertEquals(searchedGroupName,CommonPage.getTestData("DriverGroupName"), "Driver group name dosen't match with the searched driver group");
		String numberOfDrivers = WebDriverAccess.getDriver().findElement(By.xpath("//div[@id='section-driver-groups']//td[4]")).getText();
		Assert.assertEquals(numberOfDrivers, noOfDrivers , "No of drivers is not same as the added drivers");
		System.out.println("Verified that the Driver Group created via Batch Upload has" + noOfDrivers + "Drivers");
		String driverGroupId = WebDriverAccess.getDriver().findElement(By.xpath("//div[@id='section-driver-groups']//td[2]")).getText();
		CommonPage.loadXMLParameterToTestData("DriverGroupID", driverGroupId);	
	}	
	
	/** 
	 * This method verifies that the drivers added in a particular association are visible on FO Driver page
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void verifyAssocDriversPresentOnFODriverPage() throws Exception {	
		String[] drivers = CommonPage.getTestData("DriverEmpIds").split("\\|");
		System.out.println("Driver Emp ID: " + Arrays.toString(drivers));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.visibilityOfElementLocated(By.id(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID.getValue())));
		for(String driver : drivers) {
			BrowserAction.click(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID);
			BrowserAction.enterFieldValue(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_ID, driver);
			System.out.println("Driver: " + driver);
			WebElement ele = WebDriverAccess.getElement(By.xpath(OrderingDriverPageEnum.ORDERING_DRIVER_UNIT_NUMBER_TEXTBOX_SUGGESTION_FIRST_XPATH.getValue()));
			if(ele.isDisplayed()) {
				Assert.assertTrue(ele.isDisplayed(), driver + ": Driver not present");
				ele.click();
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_EMPLOYEE_ID_LABEL_XPATH.getValue())));
				Assert.assertEquals(BrowserAccess.getElementText(OrderingDriverPageEnum.ORDERING_DRIVER_INFORMATION_EMPLOYEE_ID_LABEL_XPATH).trim(), driver, "Driver emp id dosent match with the netered emp id.");
			} else {
				Assert.assertFalse(ele.isDisplayed(),driver + ": Driver present");
			}
		}
	}
	
	/** 
	 * This method verifies that the FleetSpec added in a particular association are visible on FO Driver page
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void verifyAssocFleetSpecPresentOnFODriverPage() throws Exception {	
		String[] fleetSpecs = CommonPage.getTestData("FleetSpecSearch").split("\\|");
		System.out.println("Fleet spec ID: " + Arrays.toString(fleetSpecs));
		List<WebElement> eles = BrowserAccess.getElements(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_FLEET_SPEC_SPEC_ID_XPATH);
		for(int i =0 ;i<fleetSpecs.length;i++) {
			System.out.println("Fleet spec id: " + fleetSpecs[i]);
			Assert.assertEquals(eles.get(i).getText().trim(), fleetSpecs[i],"Fleet spec id dosenot match with the added fleet spec id");
		}		
	}
	
	/** 
	 * This method moves to create order page for orderlogger login
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void moveToCreateOrderPage() throws Exception {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.selectOrderingMenuOption("Create Order");
		OrderingCommonPage.checkAlertPopUp();
		OrderingStartHerePage.waitForStartHerepage();
		OrderingStartHerePage.enterOrderUnitNumber();
		OrderingStartHerePage.selectOrderType();
	}
	
	/** 
	 * This method selects and deletes all approval rules 
	 * @lastModifiedBy hectorJimenez
	 * @throws Exception
	 */
	public static void deleteAllApprovallRules() throws Exception {
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_CHECKALL_CHECKBOX_XPATH);
		BrowserAction.click(OrderingBOManagerOrderPreferencesPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_APPROVAL_RULES_DELETERULES_BUTTON_XPATH);
		OrderingBOManagerOrderPreferencesPage.verifyPopUpMessage("Delete From List View");
		OrderingBOManagerOrderPreferencesPage.popUpAction("Yes");
		OrderingBOManagerOrderPreferencesPage.checkAlertPopUpConfimationMessage("Delete From List View");	
	}
	
	/**
	 * This method performs create order functionality with logged in user, skipping Billing and Registration
	 * @lastModifiedBy Hector_Jimenez
	 * @param className
	 * @throws Throwable 
	 */
	public static void createOrderSkipBilling(String className) throws Throwable {
		OrderingLoginPage.openCustomFOApplication(CommonPage.getTestData("OrderLogger"));
		
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
		int orderCount = Integer.parseInt(CommonPage.getTestData("OrderLoggerOrderCount"));
		System.out.println("Number of orders to be created:" + orderCount);
		for(int i=1; i<=orderCount; ++i) {
			OrderingHomePage.selectSideMenuOption("Ordering");
			OrderingHomePage.selectOrderingMenuOption("Create Order");
			OrderingStartHerePage.enterStartHerePageDetails(CommonPage.getTestData("ApplicationURL"));
			OrderingStartHerePage.clickSaveAndNext();
			OrderingDriverPage.enterDriverData();
			OrderingStartHerePage.clickSaveAndNext();
			OrderingVehiclePage.enterDetailsVehicleDataNoGetDetails();
			OrderingStartHerePage.clickSaveAndNext();
			OrderingCommonPage.checkAlertPopUp();
			OrderingBOQueuePage.waitUntilCompletePageLoad();
			if(WebDriverAccess.getElements(By.id("plateType")).size() > 0 ) {
				BrowserWait.waitUntilElementIsDisplayed(WebDriverAccess.getElement(By.id("plateType")));
				Select plateType = new Select (WebDriverAccess.getElement(By.id("plateType")));
				plateType.selectByVisibleText("Combination");
				Select whoToTile = new Select (WebDriverAccess.getElement(By.id("whoToTitle")));
				whoToTile.selectByVisibleText("Client");
			}
			OrderingStartHerePage.clickSaveAndNext();
			OrderingCommonPage.checkAlertPopUp();
			OrderingDealerPage.waitForDealerPage();
			OrderingStartHerePage.clickSaveAndNext();
			OrderingSummaryPage.clickSubmit();
			OrderingSummaryPage.confirmSubmit();
			OrderingCommonPage.checkAlertPopUp();
			OrderingSummaryPage.waitForPopUpResultBox();
			OrderingBOManagerOrderPreferencesPage.validateManagerApprovalSubmissionMessage();
			OrderingSummaryPage.verifySuccessfulSubmissionPopUp();
			OrderingSummaryPage.waitForSummaryPage();
			OrderingHomePage.clickOnCloseSummaryPage();
			CommonPage.loadXMLParameterToTestData("LoggerLogNumber"+i , CommonPage.getElementOrderObject().getLogNumber());
			PDFReporter.takeExtraScreenshot();
		}
	}
	
}
