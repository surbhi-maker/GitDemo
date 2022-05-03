package com.element.fleet.ordering.page;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.DbConnector;
import com.element.fleet.ordering.enums.OrderingBOBusinessMaintainedTablesEnum;
import com.element.fleet.ordering.enums.OrderingBOHomePageEnum;
import com.element.fleet.ordering.enums.OrderingFeatureToggleEnum;
import com.element.fleet.ordering.enums.OrderingHomePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;
import com.ge.capital.rainbow.webdriver.WebDriverAction;

public class OrderingBOFeatureToggle {
	public static void selectSideMenuBMTOption(String option) throws Exception {
		Object element = null;
		switch (option) {
			case "Business Maintained Tables":
				element = OrderingBOHomePageEnum.ORDERING_BO_BUSINESS_MAINTAINED_TABLES_MENU_XPATH;
				break;
			default: throw new InvalidSwitchCaseException("Invalid side menu option selected");
		}
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.elementToBeClickable(BrowserAction.getElement(element)));
		BrowserAction.click(element);
	}
	
	public static void businessMaintainedTablePageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_OPTION_XPATH);
	}
	
	public static void selectBusinessMaintatinedTableOption(String option) throws Exception {
		Object element = null;
		switch (option) {
			case "Feature Toggle":
				element = OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_FEATURE_TOGGLE_OPTION_XPATH;
				break;
			default: throw new InvalidSwitchCaseException("Invalid Business Maintained Tables option selected");
		}
		BrowserAction.click(element);
	}
	
	public static void featureTogglesPageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_HEADING_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBusinessMaintainedTablesEnum.ORDERING_BO_BMT_AO_SEARCH_FIELD_ID);
	}
	
	public static void clickOnExportButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOGGLE_EXPORT_XPATH);
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOGGLE_EXPORT_XPATH);
	}
		
	public static void clickOnAddToggle() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_ADD_NEW_XPATH);
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_ADD_NEW_XPATH);
	}
	
	public static void deleteEntryFeatureToggle() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_DELETEBTN_XPATH);
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_DELETEBTN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_DELETE_YES_BUTTON_XPATH);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.visibilityOfElementLocated(BrowserAccess.getLocator(OrderingFeatureToggleEnum.FEATURE_TOGGLE_DELETE_YES_BUTTON_XPATH)));
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_DELETE_YES_BUTTON_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_DELETION_MESSAGE_XPATH);
	}
	
	/**
	 * This method adds feature values
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void addFeatureValues() throws Exception {
		OrderingBOFeatureToggle.enterToggleName();
		OrderingBOFeatureToggle.enterToggleDescription();
		OrderingBOFeatureToggle.selectFeatureType();
		OrderingBOFeatureToggle.enterToggleValue();
	}
	
	/**
	 * This method clicks on Add Toggle button
	 * @throws Exception 
	 * @lastModifiedBy shisingh
	 */
	public static void clickAddToggleButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_ADD_TOGGLE_XPATH);
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_ADD_TOGGLE_XPATH);
	}
	
	/**
	 * This method enters feature toggle name
	 * @throws Exception 
	 * @lastModifiedBy shisingh
	 */
	public static void enterToggleName() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_FEATURE_NAME_CSS);
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_FEATURE_NAME_CSS);
		String featureName = "AT_" + CommonPage.randomAlphaNumericString();
		System.out.println("Feature Name: " + featureName);
		CommonPage.loadXMLParameterToTestData("FeatureName", featureName);
		BrowserAction.enterFieldValue(OrderingFeatureToggleEnum.FEATURE_TOGGLE_FEATURE_NAME_CSS, featureName);
	}
	
	/**
	 * This method enters feature toggle description
	 * @throws Exception 
	 * @lastModifiedBy shisingh
	 */
	public static void enterToggleDescription() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_FEATURE_DESCRIPTION_CSS);
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_FEATURE_DESCRIPTION_CSS);
		System.out.println("Feature Description: " + CommonPage.getTestData("FeatureDesc"));
		BrowserAction.enterFieldValue(OrderingFeatureToggleEnum.FEATURE_TOGGLE_FEATURE_DESCRIPTION_CSS, CommonPage.getTestData("FeatureDesc"));
	}
	
	/**
	 * This method selects feature toggle type
	 * @throws Exception 
	 * @lastModifiedBy shisingh
	 */
	public static void selectFeatureType() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_FEATURE_DESCRIPTION_CSS);
		System.out.println("Feature Type: " + CommonPage.getTestData("FeatureType"));
		BrowserAction.selectDropdownOptionByText(OrderingFeatureToggleEnum.FEATURE_TOGGLE_FEATURE_TYPE_XPATH, CommonPage.getTestData("FeatureType"));
	}
	
	/**
	 * This method enters feature toggle value
	 * @throws Exception 
	 * @lastModifiedBy shisingh
	 */
	public static void enterToggleValue() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_FEATURE_VALUE_CSS);
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_FEATURE_VALUE_CSS);
		String featureValue;
		switch(CommonPage.getTestData("FeatureType")) {
			case "UI":
				featureValue = OrderingBOFeatureToggle.enterToggleValueUI();
				break;
			case "API":
				featureValue = OrderingBOFeatureToggle.enterToggleValueAPI();
				break;
			default: throw new InvalidSwitchCaseException(CommonPage.getTestData("FeatureType") + " is a invalid option");
		}
		System.out.println("Feature Value: " + featureValue);
		BrowserAction.enterFieldValue(OrderingFeatureToggleEnum.FEATURE_TOGGLE_FEATURE_VALUE_CSS, featureValue);
	}
	
	/**
	 * This method returns feature toggle value is UI
	 * @throws Exception
	 * @lastModifiedBy shisingh
	 */
	public static String enterToggleValueUI() throws Exception {
		return "{" + "\"pageName\":" + "\"" + CommonPage.getTestData("PageName/EndPointUrl") + "\",\"" + "locatorType\"" + ":\"" + CommonPage.getTestData("LocatorType")+ "\",\"" + "locationValue\":\"" + CommonPage.getTestData("LocationValue") + "\",\"" + "applicationName\":\"" + CommonPage.getTestData("ApplicationName")+ "\"}";
	}
	
	/**
	 * This method returns feature toggle value is API
	 * @throws Exception
	 * @lastModifiedBy shisingh
	 */
	public static String enterToggleValueAPI() throws Exception {
		return "{" + "\"endpointUrl\":" + "\"" + CommonPage.getTestData("PageName/EndPointUrl") + "\",\"" + "defaultResponse\"" + ":"+ CommonPage.getTestData("DefaultResponse").replaceAll("\"\"", "\"").replace("\"{", "{").replace("}\"","}") + "}";
	}
	
	public static String getrandomnalphanumericvalue() {
		int lowerLimit = 48;
		int upperLimit = 122;
		Random random = new Random();
		StringBuffer r = new StringBuffer(6);
		for (int i = 0; i < 6; i++) {
			int nextRandomChar = lowerLimit + (int) (random.nextFloat() * (upperLimit - lowerLimit + 1));
			r.append((char) nextRandomChar);
		}
		return r.toString();
	}
	
	/**
	 * This method adds values to the toggle values
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void addToogleValues() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOOGLE_ACTION_XPATH);
		System.out.println("Toggle Action: " + CommonPage.getTestData("ToggleAction"));
		BrowserAction.selectDropdownOptionByText(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOOGLE_ACTION_XPATH, CommonPage.getTestData("ToggleAction"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOGGLE_NAME_CSS);
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOGGLE_NAME_CSS);
		String toggleName = CommonPage.getTestData("ToggleName") + CommonPage.generateRandomNumber();
		System.out.println("Toggle Name: " + toggleName);
		BrowserAction.enterFieldValue(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOGGLE_NAME_CSS, toggleName);
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOGGLE_NAME_CSS);
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOGGLE_DESCRIPTION_CSS);
		System.out.println("Toggle Description: " + CommonPage.getTestData("ToggleDesc"));
		BrowserAction.enterFieldValue(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOGGLE_DESCRIPTION_CSS, CommonPage.getTestData("ToggleDesc"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOGGLE_TYPE_XPATH);
		System.out.println("Toggle Type: " + CommonPage.getTestData("ToggleType"));
		BrowserAction.selectDropdownOptionByText(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOGGLE_TYPE_XPATH, CommonPage.getTestData("ToggleType"));
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOGGLE_VALUE_CSS);
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOGGLE_VALUE_CSS);
		String toggleValue = CommonPage.getTestData("ToggleValue").replaceAll("\"\"", "\"").replace("\"{", "{").replace("}\"", "}");
		System.out.println("Toggle Value: " + toggleValue);
		BrowserAction.enterFieldValue(OrderingFeatureToggleEnum.FEATURE_TOGGLE_TOGGLE_VALUE_CSS, toggleValue);
	}
	
	public static void turnONandOffToggle() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_ACTIVE_TOGGLE_XPATH);
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_ACTIVE_TOGGLE_XPATH);
	}
	
	/**
	 * This method clicks on Feature Active button
	 * @throws Exception
	 * @lastModifiedBy shisingh
	 */
	public static void turnONandOffFeature() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_ACTIVE_FEATURE_XPATH);
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_ACTIVE_FEATURE_XPATH);
	}
	
	/**
	 * This method clicks on save feature toggl button button
	 * @throws Exception
	 * @lastModifiedBy shisingh
	 */
	public static void savingFeatureToggle() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_FEATURE_SAVE_XPATH);
		BrowserAction.click(OrderingFeatureToggleEnum.FEATURE_TOGGLE_FEATURE_SAVE_XPATH);
	}
	
	public static void openNewWindow() throws Exception {
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_T);
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfWindowsToBe(2));
		rb.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	public static void windowHandleSwitch() throws Exception {
		String currentWindowHandle = WebDriverAction.getWindowHandle();
		Set<String> windowHandles = WebDriverAction.getWindowHandles();
		for (String handle : windowHandles) {
			if (!handle.equals(currentWindowHandle)) {
				WebDriverAction.switchTo(handle);
			}
		}
	}
	
	public static void verifyDisable(WebDriver driver) throws Exception {
		if(WebDriverAccess.getDriver().findElement(By.id(CommonPage.getTestData("LocationValue"))).isEnabled() != true)
			throw new OrderingErrorOccured ("Check feature/toggle values again, feature toggle not working");
	}
	
	/**
	 * This method verify that the element is hidden
	 * @lastModifiedBy shisingh
	 * @param driver
	 * @throws Exception
	 */
	public static void verifyHide(WebDriver driver) throws Exception {
		boolean findloc = true;
		switch (CommonPage.getTestData("LocatorType")) {
			case "ID":
				findloc = driver.findElement(By.id(CommonPage.getTestData("LocationValue"))).isDisplayed();
				break;
			case "XPATH":
				findloc = driver.findElement(By.xpath(CommonPage.getTestData("LocationValue"))).isDisplayed();
				break;
			case "jquerySelector":
				findloc = driver.findElement(By.cssSelector(CommonPage.getTestData("LocationValue"))).isDisplayed();
				break;
			case "CLASSNAME":
				findloc = driver.findElement(By.className(CommonPage.getTestData("LocationValue"))).isDisplayed();
				break;
			case "NAME":
				findloc = driver.findElement(By.name(CommonPage.getTestData("LocationValue"))).isDisplayed();
				break;
			default: throw new InvalidSwitchCaseException(CommonPage.getTestData("LocatorType") + " is a invalid locator");
		}			
		if (findloc) 
			throw new OrderingErrorOccured("Check feature/toggle values again, feature toggle not working"); 
	}
	
	public static void reachPage(String applicationURL) throws Exception {
		WebDriverAccess.getDriver().get(applicationURL + "/#" + CommonPage.getTestData("PageName/EndPointUrl"));
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.visibilityOfElementLocated(BrowserAccess.getLocator(OrderingHomePageEnum.ORDEING_HOME_ORDERING_LOGO_ID)));
	}
	
	/**
	 * This method verify weather element is displayed or not
	 * @lastModifiedBy shisingh
	 * @param driver
	 * @throws Exception
	 */
	public static void verifyMaintScr(WebDriver driver) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingFeatureToggleEnum.FEATURE_TOGGLE_MAINT_SCR_FO_XPATH);
		String maintlocator = "\"//p[text()='" + CommonPage.getTestData("FeatureDesc") + "']\"";
		if (!BrowserAction.getElement(maintlocator).isDisplayed())
			throw new OrderingErrorOccured("Popup not displayed");
	}
	
	/**
	 * This method adds checks the toggles
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void checkToggles() throws Exception {
		if (CommonPage.getTestData("IsFeatureActive").equals("Y")) {
			OrderingBOFeatureToggle.turnONandOffFeature();
		}
		if (CommonPage.getTestData("IsToggleActive").equals("Y")) {
			OrderingBOFeatureToggle.turnONandOffToggle();
		}
	}
	
	public static void checkToggleAppName()throws Exception {
		if (CommonPage.getTestData("ApplicationName").equals("ordering")) {
			OrderingBOFeatureToggle.openOrderingAndVerify(CommonPage.getTestData("ApplicationURL"), WebDriverAccess.getDriver());
		} else if (CommonPage.getTestData("ApplicationName").equals("backOffice")) {
			OrderingBOFeatureToggle.openBOAndVerify(CommonPage.getTestData("ApplicationBOURL"), WebDriverAccess.getDriver());
		}
	}
	
	/**
	 * This method opens ordering bo application on new window
	 * @param applicationURL
	 * @param driver
	 * @throws Exception
	 */
	private static void openBOAndVerify(String applicationBOURL, WebDriver driver) throws Exception {
		WebDriverAccess.getDriver().get(applicationBOURL);
		OrderingLoginPage.waitForBOUserLoginPage();
		OrderingLoginPage.enterBOUsername(CommonPage.getTestData("Useridtologinwith"));
		OrderingLoginPage.enterBOPassword(CommonPage.getCredetialsData(CommonPage.getTestData("Useridtologinwith")));
		if (CommonPage.getTestData("MaintScnToggle").equals("Y")) {
			OrderingBOFeatureToggle.verifyMaintScr(driver);
		} else {
			OrderingLoginPage.clickLoginBtn((JavascriptExecutor) WebDriverAccess.getDriver());
			OrderingBOHomePage.orderingBOHomePageLoaded();
			reachPage(applicationBOURL);
		}
	}
	
	/**
	 * This method opens ordering fo application on new window
	 * @param applicationURL
	 * @param driver
	 * @throws Exception
	 */
	private static void openOrderingAndVerify(String applicationURL, WebDriver driver) throws Exception {
		WebDriverAccess.getDriver().get(applicationURL);
		OrderingLoginPage.waitForUserLoginPage();
		OrderingLoginPage.enterUsername(CommonPage.getTestData("Useridtologinwith"));
		OrderingLoginPage.enterPassword(CommonPage.getCredetialsData(CommonPage.getTestData("Useridtologinwith")));
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor) WebDriverAccess.getDriver());
		if (CommonPage.getTestData("MaintScnToggle").equals("Y")) {
			OrderingBOFeatureToggle.verifyMaintScr(driver);
		} else {
			BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_ORDERING_LOGO_ID);
		}
	}
	
	public static void checkToggleAction() throws Exception {
		if (!CommonPage.getTestData("MaintScnToggle").equals("Y")) {
			if (CommonPage.getTestData("ToggleAction").equals("Disable")) {
				OrderingBOFeatureToggle.verifyDisable(WebDriverAccess.getDriver());
			}
			if (CommonPage.getTestData("ToggleAction").equals("Hide")) {
				OrderingBOFeatureToggle.verifyHide(WebDriverAccess.getDriver());
			}
		}
	}
	
	/**
	 * This method checks toggle needs to be disabled or not and take action accordingly.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void checkIfWantToDisableToggle() throws Exception {
		if(CommonPage.getTestData("DisableToggle").equals("Y")) {
			OrderingBOFeatureToggle.windowHandleSwitch();
			OrderingBOFeatureToggle.searchFeatureToggel();
			OrderingBOFeatureToggle.turnONandOffFeature();
			OrderingBOFeatureToggle.savingFeatureToggle();
		}
	}

	public static void searchFeatureToggel() throws Exception {
		OrderingBOFeatureToggle.featureTogglesPageLoaded();
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_F);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		StringSelection featureName = new StringSelection(CommonPage.getTestData("FeatureName"));
		Clipboard tool = Toolkit.getDefaultToolkit().getSystemClipboard();
		tool.setContents(featureName, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		WebElement element = WebDriverAccess.getElement(
				BrowserAccess.getLocator(OrderingFeatureToggleEnum.FEATURE_TOGGLE_SEARCH_FEATURE_TOGGLE_XPATH.name(),
				String.format(OrderingFeatureToggleEnum.FEATURE_TOGGLE_SEARCH_FEATURE_TOGGLE_XPATH.toString(), CommonPage.getTestData("FeatureName"))));
		element.click();
	}
	
	public static void verifyElementIsRelocated(boolean isDisplayed) throws Exception {
		OrderingBOFeatureToggle.windowHandleSwitch();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.visibilityOfElementLocated(BrowserAccess.getLocator(OrderingHomePageEnum.ORDEING_HOME_ORDERING_LOGO_ID)));
		BrowserAction.refresh();
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_ORDERING_LOGO_ID);
		if(isDisplayed) {
			BrowserVerify.verifyElementIsPresent(OrderingFeatureToggleEnum.FEATURE_TOGGLE_SETTING_ICON_XPATH);
		} else {
			BrowserVerify.verifyElementIsNotPresent(OrderingFeatureToggleEnum.FEATURE_TOGGLE_SETTING_ICON_XPATH);
		}
	}
	
	/**
	 * This method delete the create feature toggle using Rest API calls
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void deleteFeatureToggle() throws Exception {
		if(!Objects.isNull(CommonPage.getTestData("FeatureName"))) {
			DbConnector.deleteFeatureToggleByName(CommonPage.getTestData("FeatureName"));
		}
	}
}