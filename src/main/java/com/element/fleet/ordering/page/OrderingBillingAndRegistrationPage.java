package com.element.fleet.ordering.page;

import static org.testng.Assert.assertTrue;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBillingAndRegistrationPageEnum;
import com.element.fleet.ordering.enums.OrderingStartHerePageEnum;
import com.element.fleet.ordering.verification.BillingAndRegistrationTab.Billing;
import com.element.fleet.ordering.verification.BillingAndRegistrationTab.LienHolder;
import com.element.fleet.ordering.verification.BillingAndRegistrationTab.RegisteredOwner;
import com.element.fleet.ordering.verification.BillingAndRegistrationTab.Registration;
import com.element.fleet.ordering.verification.BillingAndRegistrationTab.TitleOwner;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserAssert;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingBillingAndRegistrationPage {

	/**
	 * This method waits for billing section to load.
	 * @throws Exception
	 * @lastModifiedBy shisingh
	 */
	public static void waitForBillingSectionToLoad() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_WHO_TO_TITLE_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_WHO_TO_TITLE_ID);
		BrowserVerify.verifyElementEnabled(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_WHO_TO_TITLE_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_PLATE_TYPE_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_PLATE_TYPE_ID);
		BrowserVerify.verifyElementEnabled(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_PLATE_TYPE_ID);
	}

	/**
	 * This method select who to title option as available in the test data file.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectWhoToTitle() throws Exception {
		if (!(CommonPage.getTestData("WhoToTitleOption") == null)) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_WHO_TO_TITLE_ID);
			BrowserVerify.verifyElementIsPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_WHO_TO_TITLE_ID);
			BrowserVerify.verifyElementEnabled(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_WHO_TO_TITLE_ID);
			System.out.println("Who to title: " + CommonPage.getTestData("WhoToTitleOption"));
			BrowserAction.selectDropdownOptionByText(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_WHO_TO_TITLE_ID, CommonPage.getTestData("WhoToTitleOption"));
		}
	}

	/**
	 * This method selects plate type as available in the test data file.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectPlateType() throws Exception {
		if(!(CommonPage.getTestData("PlateType")==null)) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_PLATE_TYPE_ID);
			BrowserVerify.verifyElementIsPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_PLATE_TYPE_ID);
			BrowserVerify.verifyElementEnabled(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_PLATE_TYPE_ID);
			System.out.println("Plate type: " + CommonPage.getTestData("PlateType"));
			BrowserAction.selectDropdownOptionByText(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_PLATE_TYPE_ID, CommonPage.getTestData("PlateType"));
		}
	}

	/**
	 * This method enters billing and registration details from test data sheet.
	 * @lastModifiedBy skathule
	 */
	public static void enterBillingAndRegistrationDetails() throws Exception {
		OrderingCommonPage.checkAlertPopUp();
		OrderingBillingAndRegistrationPage.waitForBillingAndRegistrationPage();
		OrderingBillingAndRegistrationPage.enterAndSearchClientBreakdownValue();
		OrderingBillingAndRegistrationPage.verifyContTypeDrpDwnValues();
		OrderingBillingAndRegistrationPage.selectContType();
		OrderingBillingAndRegistrationPage.selectLeaseTerm();
		OrderingBillingAndRegistrationPage.verifyTLAndRSections();
		OrderingBillingAndRegistrationPage.enterAssetId();
		OrderingBillingAndRegistrationPage.enterClientPurchaseOrderNumber();
		OrderingCommonPage.checkAlertPopUp();
		OrderingBillingAndRegistrationPage.waitForBillingSectionToLoad();
		OrderingBillingAndRegistrationPage.selectWhoToTitle();
		OrderingBillingAndRegistrationPage.selectPlateType();
		OrderingBillingAndRegistrationPage.getDetailsFromOrderingBillingAndRegistrationPage(WebDriverAccess.getDriver());
	}
	
	/**
	 * This method validates that contract type methods displayed are as expected
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyContTypeDrpDwnValues() throws Exception {
		BrowserVerify.verifyElementIsPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_CONTRACT_TYPE_DROPDOWN_XPATH);
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		Select contractTypeDropDown = new Select(BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_CONTRACT_TYPE_DROPDOWN_XPATH));
		List<WebElement> contractTypeOptions = contractTypeDropDown.getOptions();
		List<String> expectedContractTypeValues = Arrays.asList("Select one...", "Company Owned", "Lease", "Loan");
		for(WebElement contractTypeOption: contractTypeOptions) {
			String contractTypeOptionText = (String) js.executeScript("return arguments[0].text", contractTypeOption);
			if(!expectedContractTypeValues.contains(contractTypeOptionText)) {
				throw new AssertionError(contractTypeOptionText + " is not available in expected the Contract Type list i.e. " + expectedContractTypeValues);
			}
		}
	}

	/**
	 * This method validates that who to title displayed as expected
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifyWhoToTitleDrpDwnValues() throws Exception {
		Select whoToTitle=new Select(BrowserAccess.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_WHO_TO_TITLE_ID));
		List<String> expectedWhoToTitleValues=Arrays.asList("Select one...","Client", "Dealer", "Element", "Transporter");
		List<WebElement> whoToTitleDropdown=whoToTitle.getOptions();
		for(WebElement e: whoToTitleDropdown){
			assertTrue(expectedWhoToTitleValues.contains(e.getText().trim()),
					"Who to Title Drop Down list doesnt contain : " + e.getText().trim());
		}
	}

	/**
	 * This method validates that plate type values are displayed as expected
	 * @lastModifiedBy pdhole
	 * @throws Exception
	 */
	public static void verifyPlateTypeDrpDwnValues() throws Exception {
		Select plateType=new Select(BrowserAccess.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_PLATE_TYPE_ID));
		List<String> expectedPlateTypeValues=Arrays.asList("Select one...", "Passenger", "Commercial", "Trailer", "IRP", "Combination");
		List<WebElement> plateTypeDropdown=plateType.getOptions();
		for(WebElement e:plateTypeDropdown){
			assertTrue(expectedPlateTypeValues.contains(e.getText().trim()),
					"Plate type Drop Down list doesnt contain : " + e.getText().trim());
		}
	}

	/**
	 * This method waits for billing and registration page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForBillingAndRegistrationPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_SECTION_ID);
		BrowserVerify.verifyElementIsPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_SECTION_ID);
		BrowserVerify.verifyElementEnabled(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_SECTION_ID);
	}

	/**
	 * This method enters breakdown value clicks on search button on billing and registration page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterAndSearchClientBreakdownValue() throws Exception {
		if(!(CommonPage.getTestData("NewVehicleBreakdown")==null)) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_NEW_VEHICLE_BREAKDOWN_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_NEW_VEHICLE_BREAKDOWN_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_NEW_VEHICLE_BREAKDOWN_XPATH);
			BrowserAction.clickandClear(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_NEW_VEHICLE_BREAKDOWN_XPATH);
			System.out.println("New client breakdown: " + CommonPage.getTestData("NewVehicleBreakdown"));
			BrowserAction.enterFieldValue(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_NEW_VEHICLE_BREAKDOWN_XPATH, CommonPage.getTestData("NewVehicleBreakdown"));
			BrowserAction.click(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_NEW_VEHICLE_BREAKDOWN_SEARCH_BUTTON_ID);
		}
	}

	/**
	 * This method selects contract type on billing and registration page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectContType() throws Exception {
		if(!(Objects.isNull(CommonPage.getTestData("ContractType")))) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_CONTRACT_TYPE_DROPDOWN_ID);
			BrowserVerify.verifyElementIsPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_CONTRACT_TYPE_DROPDOWN_ID);
			BrowserVerify.verifyElementEnabled(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_CONTRACT_TYPE_DROPDOWN_ID);
			Select contractTypeDropDown = new Select(BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_CONTRACT_TYPE_DROPDOWN_ID));
			String contractType = contractTypeDropDown.getFirstSelectedOption().getText();
			System.out.println("First contract type option is: "+contractType);
			if (contractType.equals("Select one...")) {
				BrowserAction.selectDropdownOptionByText(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_CONTRACT_TYPE_DROPDOWN_ID,CommonPage.getTestData("ContractType"));
			}
			else {
				Assert.assertTrue(contractType.equals("Lease")||contractType.equals("Company Owned"),"More than one options is present in drop down");
			}
		}
	}

	/**
	 * This method selects contract type as lease and verify whether TLAndR sections are populating accordingly
	 * Note: This verification only applicable for Ordering Super User access role
	 * @lastModifiedBy Shivam Srivastava
	 * @throws Exception
	 */
	public static void verifyTLAndRSections() throws Exception {
		if(CommonPage.getTestData("UserRole").contains("Ordering Super User")) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TLANDR_SECTIONS_HEADER_XPATH);
			List<String> expectedTLRSections = Arrays.asList("Title Information","Lienholder Information", "Registration Information");
			List<WebElement> tLRSections = BrowserAction.getElements(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TLANDR_SECTIONS_HEADER_XPATH);
			for(WebElement tLRSection: tLRSections) {
				String tLRSectionText = tLRSection.getText();
				if(!expectedTLRSections.contains(tLRSectionText)) {
					throw new AssertionError(tLRSectionText + " is not available in expected the Contract Type list i.e. " + expectedTLRSections);
				}
			}
		}
	}

	/**
	 * This method selects lease term on billing and registration page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void selectLeaseTerm() throws Exception {
		if(!(CommonPage.getTestData("LeaseTerm")==null)) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_LEASE_TERM_DROPDOWN_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_LEASE_TERM_DROPDOWN_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_LEASE_TERM_DROPDOWN_XPATH);
			System.out.println("Lease term: " + CommonPage.getTestData("LeaseTerm"));
			BrowserAction.selectDropdownOptionByText(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_LEASE_TERM_DROPDOWN_XPATH, CommonPage.getTestData("LeaseTerm"));
		}
	}

	/**
	 * This method enters asset id on billing and registration page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterAssetId() throws Exception {
		if(!(CommonPage.getTestData("AssetID")==null)) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_ASSET_ID_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_ASSET_ID_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_ASSET_ID_XPATH);
			System.out.println("Asset ID: " + CommonPage.getTestData("AssetID"));
			BrowserAction.clickandClear(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_ASSET_ID_XPATH);
			BrowserAction.enterFieldValue(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_ASSET_ID_XPATH, CommonPage.getTestData("AssetID"));
		}
	}

	/**
	 * This method enters client purchase order number on billing and registration page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterClientPurchaseOrderNumber() throws Exception {
		if(!(CommonPage.getTestData("ClientPurchaseOrderNumber")==null)) {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_CLIENT_PURCHASE_ORDER_NUMBER_XPATH);
			BrowserVerify.verifyElementIsPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_CLIENT_PURCHASE_ORDER_NUMBER_XPATH);
			BrowserVerify.verifyElementEnabled(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_CLIENT_PURCHASE_ORDER_NUMBER_XPATH);
			BrowserAction.clickandClear(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_CLIENT_PURCHASE_ORDER_NUMBER_XPATH);
			System.out.println("Client purchase order id: " + CommonPage.getTestData("ClientPurchaseOrderNumber"));
			BrowserAction.enterFieldValue(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_BILLING_CLIENT_PURCHASE_ORDER_NUMBER_XPATH, CommonPage.getTestData("ClientPurchaseOrderNumber"));
		}
	}

	public static void getDetailsFromOrderingBillingAndRegistrationPage(WebDriver driver) throws Exception {
		OrderingBillingAndRegistrationPage.setBillingAndRegistration(driver);
	}

	public static void setBillingAndRegistration(WebDriver driver) throws Exception {
		String label;
		String value;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		LinkedHashMap<String, String> result = new LinkedHashMap<>();
		List<WebElement> elementslist = BrowserAction.getElements(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_SECTION_LABEL_XPATH);
		for (WebElement element : elementslist) {
			label = element.getText().trim();
			if (label != null && !label.equalsIgnoreCase("")) {
				if(label.contains("*"))
					label = label.split("\\*")[0].trim();
				value = WebDriverAccess.getElementText(BrowserAccess.getLocator(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_SECTION_VALUE_XPATH.name(),
						String.format(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_SECTION_VALUE_XPATH.toString(), label))).trim();
				result.put(label, value);
			}
		}

		Billing billingData = CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getBillingSectionObject();
		billingData.setContractType(result.get("Contract Type").split(" ")[0].trim());
		Registration resgistrationData = CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getRegistrationSectionObject();
		String whoToTitle = (String) js.executeScript("return arguments[0].options[arguments[0].selectedIndex].text", BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TLANDR_WHO_TO_TITLE_XPATH));
		String plateType = (String) js.executeScript("return arguments[0].options[arguments[0].selectedIndex].text", BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TLANDR_PLATE_TYPE_XPATH));
		resgistrationData.setWhoToTitle(whoToTitle);
		resgistrationData.setPlateType(plateType);
		
		//This validation is specific to Title and Registration test cases
		if(CommonPage.getTestData("TestCaseName").startsWith("Reg_TitleReg_")) {
			TitleOwner titleOwnerData = CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getTitleOwnerObject();
			RegisteredOwner registeredOwnerData = CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getRegisteredOwnerObject();
			LienHolder lienHolderData = CommonPage.getElementOrderObject().getBillingAndRegistrationTabObject().getLienHolderObject();						
			List<WebElement> tLRSections = BrowserAction.getElements(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TLANDR_SECTIONS_HEADER_XPATH);
			for(WebElement tLRSection: tLRSections) {
				String tLRSectionText = tLRSection.getText();
				if(tLRSectionText.equals("Lienholder Information")) {
					String leinholderName = BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_NAME_XPATH).getText();
					String leinholdername = CommonPage.verifyElementTextPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_NAME_XPATH);
					String leinholderaddress = CommonPage.verifyElementTextPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_ADDRESS_XPATH);
					String leinholdercitystatezip = CommonPage.verifyElementTextPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_CITYSTATEZIP_XPATH);
					String leinholderfederalId = CommonPage.verifyElementTextPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_FEDERALID_XPATH);
					String leinholderStateId = CommonPage.verifyElementTextPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_STATEID_XPATH);
					resgistrationData.setLeinholderName(leinholderName);
					lienHolderData.setLienHolderName(leinholdername);
					lienHolderData.setLienHolderAddress(leinholderaddress);
					lienHolderData.setlienHolderCityStateZIP(leinholdercitystatezip);
					lienHolderData.setLienHolderFederalID(leinholderfederalId);
					lienHolderData.setLienHolderStateID(leinholderStateId);
				}
			}
			String vehicleText = BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_VEHICLETEXT_XPATH).getText();
			String titleOwnerName = BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_OWNER_XPATH).getText();
			String registeredOwnerName = BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_OWNER_XPATH).getText();
			String titlename = BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_NAME_XPATH).getText();
			String titleowner = BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_OWNER_XPATH).getText();
			String titleaddress = BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_ADDRESS_XPATH).getText();
			String titlecitystatezip = BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_CITYSTATEZIP_XPATH).getText();
			String titleFederalId = CommonPage.verifyElementTextPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_FEDERALID_XPATH);
			String titleStateId = CommonPage.verifyElementTextPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_STATEID_XPATH);
			String titleTaxExemptNo = CommonPage.verifyElementTextPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_TAX_EXEMPTION_NUMBER_XPATH);
			String registeredname = BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_NAME_XPATH).getText();
			String registeredowner = BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_OWNER_XPATH).getText();
			String registeredaddress = BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_ADDRESS_XPATH).getText();
			String registeredcitystatezip = BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_CITYSTATEZIP_XPATH).getText();
			String registeredFederalId = CommonPage.verifyElementTextPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_FEDERALID_XPATH);
			String registeredStateId = CommonPage.verifyElementTextPresent(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_STATEID_XPATH);
			resgistrationData.setVehicleRegisteredText(vehicleText);
			resgistrationData.setTitleOwnerName(titleOwnerName);
			resgistrationData.setRegisteredOwnerName(registeredOwnerName);
			titleOwnerData.setTitleName(titlename);
			titleOwnerData.setTitleOwner(titleowner);
			titleOwnerData.setTitleAddress(titleaddress);
			titleOwnerData.settitleCityStateZIP(titlecitystatezip);
			titleOwnerData.setTitleFederalID(titleFederalId);
			titleOwnerData.setTitleStateID(titleStateId);
			titleOwnerData.setTitleTaxExempt(titleTaxExemptNo);
			registeredOwnerData.setRegisteredName(registeredname);
			registeredOwnerData.setRegisteredOwner(registeredowner);
			registeredOwnerData.setRegisteredAddress(registeredaddress);
			registeredOwnerData.setregisteredCityStateZIP(registeredcitystatezip);
			registeredOwnerData.setRegisteredFederalID(registeredFederalId);
			registeredOwnerData.setRegisteredStateID(registeredStateId);
		}
	}

	/**
	 * This method asserts All Sections are displayed on Billing Registration Page.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void assertAllTitleRegisrtationLabelsAreDisplayed() throws Exception {
		BrowserAssert.assertElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_NAME_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_NAME_XPATH);
		CommonPage.verifyElementDisplayedAndAsserted(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_NAME_XPATH);
	}

	/**
	 * This method asserts TitleName section is displayed on Billing Registration Page.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void assertAllTitleOwnerDetailswithCodesAreDisplayed() throws Exception {
		BrowserAssert.assertElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_NAME_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_OWNER_XPATH);
		CommonPage.verifyElementDisplayedAndAsserted(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_FEDERALID_XPATH);
		CommonPage.verifyElementDisplayedAndAsserted(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_STATEID_XPATH);
		CommonPage.verifyElementDisplayedAndAsserted(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_TAX_EXEMPTION_NUMBER_XPATH);
	}

	/**
	 * This method asserts RegistrationName section is displayed on on Billing Registration Page.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void assertAllRegisrtationDetailswithCodesAreDisplayed() throws Exception {
		BrowserAssert.assertElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_NAME_XPATH);
		BrowserAssert.assertElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_OWNER_XPATH);
		CommonPage.verifyElementDisplayedAndAsserted(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_FEDERALID_XPATH);
		CommonPage.verifyElementDisplayedAndAsserted(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_STATEID_XPATH);
		CommonPage.verifyElementDisplayedAndAsserted(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_TAX_EXEMPTION_NUMBER_XPATH);
	}

	/**
	 * This method asserts Lienholder section is displayed on Billing Registration Page.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void assertAllLienHolderDetailswithCodesAreDisplayed() throws Exception {
		CommonPage.verifyElementDisplayedAndAsserted(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_ADDRESS_XPATH);
		CommonPage.verifyElementDisplayedAndAsserted(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_CITYSTATEZIP_XPATH);
		CommonPage.verifyElementDisplayedAndAsserted(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_FEDERALID_XPATH);
		CommonPage.verifyElementDisplayedAndAsserted(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_STATEID_XPATH);
		CommonPage.verifyElementDisplayedAndAsserted(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_TAX_EXEMPTION_NUMBER_XPATH);
	}

	/**
	 * This method verifies the Lienholder codes labels are in bold on Billing & Reg Page
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyLienHoldercodesAreInBold() throws Exception {
		CommonPage.verifyElementIsPresentInBold(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_FEDERALID_LABEL_XPATH);
		CommonPage.verifyElementIsPresentInBold(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_STATEID_LABEL_XPATH);
		CommonPage.verifyElementIsPresentInBold(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_LIENHOLDER_TAX_EXEMPTION_NUMBER_LABEL_XPATH);
	}
	
	/**
	 * This method verifies the Registration codes labels are in bold on Billing & Reg Page
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyRegistrationcodesAreInBold() throws Exception {
		CommonPage.verifyElementIsPresentInBold(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_FEDERALID_LABEL_XPATH);
		CommonPage.verifyElementIsPresentInBold(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_STATEID_LABEL_XPATH);
		CommonPage.verifyElementIsPresentInBold(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_REGISTRATION_TAX_EXEMPTION_NUMBER_LABEL_XPATH);
	}
	
	/**
	 * This method verifies the title codes labels are in bold on Billing & Reg Page
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyTitlecodesAreInBold() throws Exception {
		CommonPage.verifyElementIsPresentInBold(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_FEDERALID_LABEL_XPATH);
		CommonPage.verifyElementIsPresentInBold(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_STATEID_LABEL_XPATH);
		CommonPage.verifyElementIsPresentInBold(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_TITLE_TAX_EXEMPTION_NUMBER_LABEL_XPATH);
	}
	
	/**
	 * This method asserts Vehicle Text is displayed on Vehicle Text on Billing Registration Page.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void assertVehicleWillBeRegisteredTextIsDisplayed() throws Exception {
		BrowserAssert.assertElementIsDisplayed(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_VEHICLETEXT_XPATH);
	}

	/**
	 * This method asserts the heading of Title and License Page section
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void verifyTitleLicenseSectionHeading() throws Exception {
		Assert.assertEquals(BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_TANDL_HEADER_XPATH).getText().trim(),"Title & License");
	}

	/**
	 * This method asserts all Title and Registration Page Details.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void assertAllTitleAndLicenseDetails() throws Exception {
		CommonPage.scrollToBottomOfPage();
		assertAllTitleRegisrtationLabelsAreDisplayed();
		assertAllTitleOwnerDetailswithCodesAreDisplayed();
		assertAllRegisrtationDetailswithCodesAreDisplayed();
		assertAllLienHolderDetailswithCodesAreDisplayed();
		assertVehicleWillBeRegisteredTextIsDisplayed();
		verifyTitleLicenseSectionHeading();
		verifyTitlecodesAreInBold();
		verifyRegistrationcodesAreInBold();
		verifyLienHoldercodesAreInBold();
	}
	
	/**
	 * This method will select leasing term option only when displayed
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void selectLeasingTermIfDisplayed() throws Exception {
		OrderingBillingAndRegistrationPage.waitForBillingAndRegistrationPage();
		OrderingCommonPage.checkAlertPopUp();
		if(BrowserAction.getElements(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_LEASE_TERM_DROPDOWN_NAME).size() > 0 ) {
			Select leaseTerm = new Select (BrowserAction.getElement(OrderingBillingAndRegistrationPageEnum.ORDERING_BILLING_AND_REGISTRATION_LEASE_TERM_DROPDOWN_NAME));
			leaseTerm.selectByIndex(1);
		}else {
			System.out.println("No leasing term dropdown displayed");
		}
	}

	/**
	 * This method clicks on Save and next button while creating order in FO.
	 * @lastModifiedBy Deepika
	 * @throws Exception
	 */
	public static void clickSaveAndNext() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
			BrowserAction.click(OrderingStartHerePageEnum.ORDERING_START_HERE_SAVE_AND_NEXT_BUTTON_ID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Application is not able click save and next");
		}
	}
}