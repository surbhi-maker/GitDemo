package com.element.fleet.ordering.page;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.XcelerateHomePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.common.utils.SimpleStringCipher;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class XcelerateHomePage {
	
	private static String parentWindowHandle;
	
	/**
	 * This method waits for the xcelerate login page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForXcelerateLoginPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(XcelerateHomePageEnum.XCELERATE_LOGIN_USERNAME_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(XcelerateHomePageEnum.XCELERATE_LOGIN_USERNAME_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(XcelerateHomePageEnum.XCELERATE_LOGIN_USERNAME_TEXTFIELD_ID);
		BrowserWait.waitUntilElementIsDisplayed(XcelerateHomePageEnum.XCELERATE_LOGIN_PASSWORD_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(XcelerateHomePageEnum.XCELERATE_LOGIN_PASSWORD_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(XcelerateHomePageEnum.XCELERATE_LOGIN_PASSWORD_TEXTFIELD_ID);
	}
	
	/**
	 * This method enters username on the login page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterUsername(String username) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(XcelerateHomePageEnum.XCELERATE_LOGIN_USERNAME_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(XcelerateHomePageEnum.XCELERATE_LOGIN_USERNAME_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(XcelerateHomePageEnum.XCELERATE_LOGIN_USERNAME_TEXTFIELD_ID);
		BrowserAction.clickandClear(XcelerateHomePageEnum.XCELERATE_LOGIN_USERNAME_TEXTFIELD_ID);
		System.out.println("Username: "+ username);
		BrowserAction.enterFieldValue(XcelerateHomePageEnum.XCELERATE_LOGIN_USERNAME_TEXTFIELD_ID, username);
	}

	/**
	 * This method enters password on the login page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterPassword(String password) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(XcelerateHomePageEnum.XCELERATE_LOGIN_PASSWORD_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(XcelerateHomePageEnum.XCELERATE_LOGIN_PASSWORD_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(XcelerateHomePageEnum.XCELERATE_LOGIN_PASSWORD_TEXTFIELD_ID);
		BrowserAction.clickandClear(XcelerateHomePageEnum.XCELERATE_LOGIN_PASSWORD_TEXTFIELD_ID);
		System.out.println("Password: "+ password);
		BrowserAction.enterFieldValue(XcelerateHomePageEnum.XCELERATE_LOGIN_PASSWORD_TEXTFIELD_ID, SimpleStringCipher.decrypt(password));
	}
	
	/**
	 * This method clicks xcelerate login button.
	 * @throws Exception 
	 * @lastModifiedBy shisingh
	 */
	public static void clickLoginBtn() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(XcelerateHomePageEnum.XCELERATE_LOGIN_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(XcelerateHomePageEnum.XCELERATE_LOGIN_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(XcelerateHomePageEnum.XCELERATE_LOGIN_BUTTON_ID);
		BrowserAction.click(XcelerateHomePageEnum.XCELERATE_LOGIN_BUTTON_ID);
	}
	
	/**
	 * This method waits for the global-spinner-wrapper element to be zero in the DOM
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void checkHomePageWidgetsSpinner() {
		try {
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime")))
			.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_WIDGETS_SPINNER_CSS.getValue()), 0));
		} catch(NoSuchElementException e) {
			System.out.println("No global spinner found move ahead");
		} catch(TimeoutException e) {
			System.err.println("Global spinner is still present on the page");
			e.printStackTrace();
			throw new TimeoutException();
		}
	}
	
	/**
	 * This method waits for the excelerate login page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForXcelerateHomePage() throws Exception {
		XcelerateHomePage.checkHomePageWidgetsSpinner();
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).
		until(ExpectedConditions.and(ExpectedConditions.numberOfElementsToBe(By.cssSelector(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_WIDGETS_CSS.getValue()), 7),
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_WIDGETS_CSS.getValue()))));
	}
	
	/**
	 * This method clicks on Xcelerate side menu options.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void openSideMenu(String sideMenuOption) throws Exception {
		XcelerateHomePage.checkHomePageWidgetsSpinner();
		BrowserWait.waitUntilElementIsDisplayed(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_NAVBAR_CSS);
		BrowserVerify.verifyElementIsPresent(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_NAVBAR_CSS);
		BrowserVerify.verifyElementEnabled(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_NAVBAR_CSS);
		switch(sideMenuOption) {
			case "Vehicles":
				BrowserWait.waitUntilElementIsDisplayed(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_VEHICLES_ICON_CSS);
				BrowserVerify.verifyElementIsPresent(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_VEHICLES_ICON_CSS);
				BrowserVerify.verifyElementEnabled(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_VEHICLES_ICON_CSS);
				BrowserAction.click(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_VEHICLES_ICON_CSS);
				break;
			default: throw new InvalidSwitchCaseException(sideMenuOption + "is a inavlid option");		
		}
	}
	
	/**
	 * This method selects ordering appliction depending on the option provided on Xcelerate side menu.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void openOrderingApplication(String orderingOption) throws Exception {
		XcelerateHomePage.checkHomePageWidgetsSpinner();
		BrowserWait.waitUntilElementIsDisplayed(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_VEHICLES_SIDE_MENU_CSS);
		BrowserVerify.verifyElementIsPresent(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_VEHICLES_SIDE_MENU_CSS);
		BrowserVerify.verifyElementEnabled(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_VEHICLES_SIDE_MENU_CSS);
		if(CommonPage.getTestData("UserRole").contains("Ordering Super User")) {
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime")))
			.until(ExpectedConditions.elementToBeClickable(By
					.cssSelector(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_CSS.getValue()))).click();
		}
		switch(orderingOption) {
			case "FO":
				BrowserWait.waitUntilElementIsDisplayed(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_FO_LINK_CSS);
				BrowserVerify.verifyElementIsPresent(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_FO_LINK_CSS);
				BrowserVerify.verifyElementEnabled(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_FO_LINK_CSS);
				BrowserAction.click(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_FO_LINK_CSS);
				break;
			case "BO":
				BrowserWait.waitUntilElementIsDisplayed(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_BO_LINK_CSS);
				BrowserVerify.verifyElementIsPresent(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_BO_LINK_CSS);
				BrowserVerify.verifyElementEnabled(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_BO_LINK_CSS);
				BrowserAction.click(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_BO_LINK_CSS);
				break;
			default: throw new InvalidSwitchCaseException(orderingOption + "is a inavlid option");		
		}
	}
	
	/**
	 * This method shifts the control to ordering window
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
//	public static void navigateToOrderingWindow() throws Exception {
//		parentWindowHandle=WebDriverAccess.getDriver().getWindowHandle();
//		Set <String> allWindowHandles=WebDriverAccess.getDriver().getWindowHandles();
//		for(String s:allWindowHandles) {
//			if(!(s.equals(parentWindowHandle)))
//				WebDriverAccess.getDriver().switchTo().window(s);
//		}
//	}
	
	public static void navigateToOrderingWindow() throws Exception {
        parentWindowHandle=WebDriverAccess.getDriver().getWindowHandle();
        new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfWindowsToBe(2));
        Set <String> allWindowHandles=WebDriverAccess.getDriver().getWindowHandles();
        for(String s:allWindowHandles) {
            if(!(s.equals(parentWindowHandle)))
                WebDriverAccess.getDriver().switchTo().window(s);
        }
    }
	
	/**
	 * This method switch control to xcelerate window
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void navigateToXcelerateWindow() throws Exception {
		WebDriverAccess.getDriver().close();
		WebDriverAccess.getDriver().switchTo().window(parentWindowHandle);
		WebDriverAccess.getDriver().navigate().refresh();
		XcelerateHomePage.waitForXcelerateHomePage();
	}
	
	/**
	 * This method signs out from xcelerate application
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void signOutFromXcelerate() throws Exception {
		XcelerateHomePage.clickSettingsButton();
		XcelerateHomePage.clickSignoutButton();
		XcelerateHomePage.waitForXcelerateLoginPage();
	}
	
	/**
	 * This method clicks on settings button
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickSettingsButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_SETTINGS_BUTTON_CSS);
		BrowserVerify.verifyElementIsPresent(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_SETTINGS_BUTTON_CSS);
		BrowserVerify.verifyElementEnabled(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_SETTINGS_BUTTON_CSS);
		BrowserAction.click(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_SETTINGS_BUTTON_CSS);		
	}
	
	/**
	 * This method clicks on sign out button
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void clickSignoutButton() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_SIGNOUT_BUTTON_ID);
		BrowserVerify.verifyElementIsPresent(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_SIGNOUT_BUTTON_ID);
		BrowserVerify.verifyElementEnabled(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_SIGNOUT_BUTTON_ID);
		BrowserAction.click(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_SIGNOUT_BUTTON_ID);		
	}
	
	/**
	 * This method closes the take a tour window in case it is displayed
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void closeTakeATourScreen() throws Exception {
	 if (BrowserAction.getElements(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_TAKE_A_TOUR_CLOSE_ICON_XPATH).size()>0) {
		 BrowserAction.getElement(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_TAKE_A_TOUR_CLOSE_ICON_XPATH).click();
	 	}
	}
	
	/**
	 * This method clicks the order vehicle link
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void clickOrderVehiclesLink() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_FO_LINK_CSS);
		BrowserAction.getElement(XcelerateHomePageEnum.XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_FO_LINK_CSS).click();
	}
}
