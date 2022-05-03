package com.element.fleet.ordering.page;

import com.ge.capital.rainbow.testng.PDFReporter;
import org.testng.Assert;
import com.element.fleet.f3270.BaseMainframeDriver;
import com.element.fleet.f3270.MainframeAction;
import com.element.fleet.ordering.commonutility.CommonPage;

public class OrderingMainframePage extends BaseMainframeDriver {

	private MainframeAction mainframeAction = BaseMainframeDriver.mainframeAction;

	/**
	 * This method validates that mainframe connection is successful
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public void validateMainframeConnection() {
		mainframeAction.assertText("ENTER THE CHARACTER OF YOUR SELECTION AND PRESS ENTER");
		mainframeAction.pause();
	}

	/**
	 * This method enters the option for env selection on Mainframe
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public void enterCharacterForSelectionOfEnv() {
		mainframeAction.type(CommonPage.getTestData("MFEnv"));
		mainframeAction.enter();
		mainframeAction.pause();
	}
	
	/**
	 * This method enters username and password on login screen of Mainframe.
	 * @lastModifiedBy mkaricharla
	 */
	public void enterLoginInformation() {
		mainframeAction.enterfieldValue("LOGONID: ===>", CommonPage.getTestData("MFUsername"));
		mainframeAction.enterfieldValue("PASSWORD: ===>", CommonPage.getCredetialsData(CommonPage.getTestData("MFUsername")));
		mainframeAction.enter();
		mainframeAction.pause();
	}

	/**
	 * This method validates login is successful.
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public void verifyLoggedInPage() {
		mainframeAction.assertText("Signon OK");
		mainframeAction.pause();
	}

	/**
	 * This method enters IPHH on the login successful screen
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public void enterIPHH() {
		mainframeAction.type("IPHH");
		mainframeAction.enter();
	}

	/**
	 * This method enters IPHH on the login successful screen
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public void enterScreenOption(String screenOption) {
		mainframeAction.type(screenOption);
		mainframeAction.enter();
		mainframeAction.pause();
	}

	/**
	 * This method goes to AAMI screen
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public void goToAAMIScreen() {
		enterScreenOption(CommonPage.getTestData("MFScreenOption"));
		mainframeAction.type("AAMI");
		mainframeAction.enter();
		mainframeAction.pause();
	}

	/**
	 * This method enters enters lognumber on AAMI screen
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public void enterLogNumberOnAAMIScreen() {
		mainframeAction.enterfieldValue("Dealer Assign Number (DAN):", CommonPage.getTestData("LogNumber"));
		mainframeAction.enter();
		System.out.println(mainframeAction.getScreenText());
		mainframeAction.pause();	
	}

	/**
	 * This method enters validates the status of order
	 * @lastModifiedBy mkaricharla
	 */
	public void validateOrderStatus() {
		String status = mainframeAction.readField("Status:",1);
		Assert.assertEquals(status, "1", "MF status Validation failed, Status found is:"+ status);
	}

	/**
	 * This method checks the mainframe status is 1
	 * @throws Throwable 
	 * @lastModifiedBy mkaricharla
	 */
	public void mainframeStatusValidation() throws Throwable {
		validateMainframeConnection();
		enterCharacterForSelectionOfEnv();
		PDFReporter.takeExtraScreenshot();
		enterLoginInformation();
		enterIPHH();
		goToAAMIScreen();
		enterLogNumberOnAAMIScreen();
		validateOrderStatus();
		PDFReporter.takeExtraScreenshot();
	}
}
