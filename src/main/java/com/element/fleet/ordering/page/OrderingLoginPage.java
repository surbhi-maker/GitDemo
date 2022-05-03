package com.element.fleet.ordering.page;

import java.util.Objects;
import org.openqa.selenium.JavascriptExecutor;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingLoginPageEnum;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.common.utils.SimpleStringCipher;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingLoginPage {
	
	/**
	 * This method opens front office application and enters user name and password.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void openFOApplication() throws Exception {
		CommonPage.getUserRole(CommonPage.getTestData("Username"));
		WebDriverAccess.getDriver().get(CommonPage.getTestData("XcelerateURL"));
		WebDriverAccess.getDriver().manage().window().maximize();
		XcelerateHomePage.waitForXcelerateLoginPage();
		XcelerateHomePage.enterUsername(CommonPage.getTestData("Username"));
		XcelerateHomePage.enterPassword(CommonPage.getCredetialsData(CommonPage.getTestData("Username")));
		XcelerateHomePage.clickLoginBtn();
		XcelerateHomePage.waitForXcelerateHomePage();
		XcelerateHomePage.openSideMenu("Vehicles");
		XcelerateHomePage.openOrderingApplication("FO");
		XcelerateHomePage.navigateToOrderingWindow();
	}
	
	/**
	 * This method opens back office application and enters user name and password.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void openBOApplication() throws Exception {
		if(Objects.isNull(CommonPage.getTestData("BoUserName"))) {
			CommonPage.loadXMLParameterToTestData("BoUserName", CommonPage.getTestData("Username"));
		}
		CommonPage.getUserRole(CommonPage.getTestData("BoUserName"));
		WebDriverAccess.getDriver().get(CommonPage.getTestData("XcelerateURL"));
		WebDriverAccess.getDriver().manage().window().maximize();
		XcelerateHomePage.waitForXcelerateLoginPage();
		XcelerateHomePage.enterUsername(CommonPage.getTestData("BoUserName"));
		XcelerateHomePage.enterPassword(CommonPage.getCredetialsData(CommonPage.getTestData("BoUserName")));
		XcelerateHomePage.clickLoginBtn();
		XcelerateHomePage.waitForXcelerateHomePage();
		XcelerateHomePage.openSideMenu("Vehicles");
		XcelerateHomePage.openOrderingApplication("BO");
		XcelerateHomePage.navigateToOrderingWindow();		
	}
	
	/**
	 * This method opens front office application with runtime provided user id.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void openCustomFOApplication(String loginId) throws Exception {
		CommonPage.getUserRole(loginId);
		WebDriverAccess.getDriver().get(CommonPage.getTestData("XcelerateURL"));
		WebDriverAccess.getDriver().manage().window().maximize();
		XcelerateHomePage.waitForXcelerateLoginPage();
		XcelerateHomePage.enterUsername(CommonPage.getTestData(loginId));
		XcelerateHomePage.enterPassword(CommonPage.getCredetialsData(CommonPage.getTestData(loginId)));
		XcelerateHomePage.clickLoginBtn();
		XcelerateHomePage.waitForXcelerateHomePage();
		XcelerateHomePage.openSideMenu("Vehicles");
		XcelerateHomePage.openOrderingApplication("FO");
		XcelerateHomePage.navigateToOrderingWindow();
	}

	/**
	 * This method opens back office application with runtime provided user id.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void openCustomBOApplication(String loginId) throws Exception {
		CommonPage.getUserRole(CommonPage.getTestData(loginId));
		WebDriverAccess.getDriver().get(CommonPage.getTestData("XcelerateURL"));
		WebDriverAccess.getDriver().manage().window().maximize();
		XcelerateHomePage.waitForXcelerateLoginPage();
		XcelerateHomePage.enterUsername(CommonPage.getTestData(loginId));
		XcelerateHomePage.enterPassword(CommonPage.getCredetialsData(CommonPage.getTestData(loginId)));
		XcelerateHomePage.clickLoginBtn();
		XcelerateHomePage.waitForXcelerateHomePage();
		XcelerateHomePage.openSideMenu("Vehicles");
		XcelerateHomePage.openOrderingApplication("BO");
		XcelerateHomePage.navigateToOrderingWindow();
	}
	
	/**
	 * This method waits for the login page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForUserLoginPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_TEXTFIELD_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_TEXTFIELD_ID);
	}

	/**
	 * This method enters username on the login page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterUsername(String username) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_TEXTFIELD_ID);
		BrowserAction.clickandClear(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_TEXTFIELD_ID);
		System.out.println("Username: "+ username);
		BrowserAction.enterFieldValue(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_TEXTFIELD_ID, username);
	}

	/**
	 * This method enters password on the login page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterPassword(String password) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_TEXTFIELD_ID);
		BrowserAction.clickandClear(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_TEXTFIELD_ID);
		System.out.println("Password: "+ password);
		BrowserAction.enterFieldValue(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_TEXTFIELD_ID, SimpleStringCipher.decrypt(password));
	}

	/**
	 * This method waits for the bo login page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void waitForBOUserLoginPage() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_BO_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_BO_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_BO_TEXTFIELD_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_BO_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_BO_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_BO_TEXTFIELD_ID);
	}

	/**
	 * This method enters username on the login page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterBOUsername(String username) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_BO_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_BO_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_BO_TEXTFIELD_ID);
		BrowserAction.clickandClear(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_BO_TEXTFIELD_ID);
		System.out.println("Entered username : " + username);
		BrowserAction.enterFieldValue(OrderingLoginPageEnum.ORDEING_LOGIN_USERNAME_BO_TEXTFIELD_ID, username);
	}

	/**
	 * This method enters password on the login page.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void enterBOPassword(String password) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_BO_TEXTFIELD_ID);
		BrowserVerify.verifyElementIsPresent(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_BO_TEXTFIELD_ID);
		BrowserVerify.verifyElementEnabled(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_BO_TEXTFIELD_ID);
		BrowserAction.clickandClear(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_BO_TEXTFIELD_ID);
		System.out.println("Password: " + password);
		BrowserAction.enterFieldValue(OrderingLoginPageEnum.ORDEING_LOGIN_PASSWORD_BO_TEXTFIELD_ID, SimpleStringCipher.decrypt(password));
	}


	public static void clickLoginBtn(JavascriptExecutor js) {			
		js.executeScript("document.getElementById('login').click();\r\n" + 
				"console.log('Clicked');\r\n" + 
				"setTimeout(function(){\r\n" + 
				"    if(document.getElementById('login')==null){\r\n" + 
				"		console.log('Element not present');\r\n" + 
				"	} else {\r\n" + 
				"		console.log('Element present');\r\n" + 
				"		document.getElementById('login').click();\r\n" + 
				"		console.log('Clicked');\r\n" + 
				"	}\r\n" + 
				"}, 10000);");
	}
	
	/**
	 * This method opens front office application with runtime provided user id and opens ordering site
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void openOrderingWithExternalUser(String loginId) throws Exception {
		WebDriverAccess.getDriver().get(CommonPage.getTestData("XcelerateURL"));
		WebDriverAccess.getDriver().manage().window().maximize();
		XcelerateHomePage.waitForXcelerateLoginPage();
		XcelerateHomePage.enterUsername(loginId);
		XcelerateHomePage.enterPassword(CommonPage.getCredetialsData(loginId));
		XcelerateHomePage.clickLoginBtn();
		XcelerateHomePage.closeTakeATourScreen();
		XcelerateHomePage.waitForXcelerateHomePage();
		XcelerateHomePage.openSideMenu("Vehicles");
		XcelerateHomePage.clickOrderVehiclesLink();
		XcelerateHomePage.navigateToOrderingWindow();
	}
}
