package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBillingAndRegistrationPage;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingDealerPage;
import com.element.fleet.ordering.page.OrderingDriverPage;
import com.element.fleet.ordering.page.OrderingFOFleetPreferencesPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingStartHerePage;
import com.element.fleet.ordering.page.OrderingVehiclePage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy ADhawale
 */
public class Reg_Fleet_Preferences extends BaseWebDriver {

	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "username", "boUserName" ,"orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username,String boUserName,String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("BoUserName",boUserName.equals("-")?username:boUserName);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}

	@Test(alwaysRun = true)
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void testLaunchURL() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openCustomFOApplication(CommonPage.getTestData("InternalUser"));
	}

	@Test(dependsOnMethods = "testLaunchURL")
	@Documentation(step = "Enter the required client number to the application",expected = "The application display the required client")
	public void changeClientBreakdown() throws Exception {
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
	}

	@Test(dependsOnMethods = "changeClientBreakdown")
	@Documentation(step = "Click on fleet preferences link and validate page loaded",expected = "Fleet prferences page should be loaded") 
	public void goToFleetPreferences() throws Exception {
		OrderingHomePage.selectSideMenuOption("Fleet Preferences");
		OrderingCommonPage.checkAlertPopUp();
		OrderingHomePage.fleetPrefrencesPageLoaded();
		OrderingFOFleetPreferencesPage.fleetPreferencesLabelValidation();
	}

	@Test(dependsOnMethods = "goToFleetPreferences")
	@Documentation(step = "Edit all information,save and verify",expected = "All edited information should be saved and verifiied") 
	public void fillGeneralInformationSaveAndVerify() throws Exception {
		OrderingFOFleetPreferencesPage.fillInformationAndVerify();
		OrderingFOFleetPreferencesPage.verifyClientCommunicationsYesNoRadioButtonsFunctionality();
		OrderingFOFleetPreferencesPage.selectDataForCommunications();
		OrderingFOFleetPreferencesPage.fillDataForBilling();
		OrderingFOFleetPreferencesPage.verifyDeleteAndUndoFunctionalityForBilling();
		OrderingFOFleetPreferencesPage.SaveAndProceed();
	}

	@Test(dependsOnMethods = "fillGeneralInformationSaveAndVerify")
	@Documentation(step = "Login with external user",expected = "External user should be able to login to FO application") 
	public void changeClientBreakdownForExternalUser() throws Exception {
		OrderingLoginPage.openCustomFOApplication(CommonPage.getTestData("ExternalUser"));
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.changeClientBreakDownFromDropDown();
	}

	@Test(dependsOnMethods = "changeClientBreakdownForExternalUser")
	@Documentation(step = "navigate to fleet preferences page and verify dealer tab's radio buttons",expected = "The dealer tab's yes and no buttons should be in enabled state") 
	public void verifyYesFunctionalityOfDealerSectionForExternalUser() throws Exception {
		OrderingHomePage.selectSideMenuOption("Fleet Preferences");
		OrderingCommonPage.checkAlertPopUp();
		OrderingHomePage.fleetPrefrencesPageLoaded();
		OrderingFOFleetPreferencesPage.fleetPreferencesLabelValidation();
		OrderingFOFleetPreferencesPage.verifyDealerTabButtons();
		OrderingFOFleetPreferencesPage.SaveAndProceed();
		OrderingFOFleetPreferencesPage.closeFleetPreferencePage();
	}

	@Test(dependsOnMethods = "verifyYesFunctionalityOfDealerSectionForExternalUser")@Documentation(step = "Navigate to Create Order", expected = "The application should load Create Order page")
	public void navigateToCreateOrderForExternalUser() throws Exception {
	   OrderingHomePage.selectSideMenuOption("Ordering");
	   OrderingHomePage.orderingSideMenuOptionLoaded();
	   OrderingHomePage.orderingSideSectionLabelValidation();
	   OrderingHomePage.selectOrderingMenuOption("Create Order");
	   OrderingStartHerePage.waitForStartHerepage();
	   OrderingStartHerePage.verifyAutoUnit("Yes");
	}

	@Test(dependsOnMethods = "navigateToCreateOrderForExternalUser")
	@Documentation(step = "Provide input data to Start Here page", expected = "The application should accept input data to Start Here page")
	public void inputValuesForStartHerePageForExternalUser() throws Exception {
		OrderingStartHerePage.enterStartHerePageDetails(CommonPage.getTestData("ApplicationURL"));
		OrderingStartHerePage.selectOrderType();
		OrderingStartHerePage.selectPoolOrderNo();
		OrderingStartHerePage.selectAddUsedUnitNo();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDriverPage.waitForDriverPage();
	}

	@Test(dependsOnMethods = "inputValuesForStartHerePageForExternalUser")
	@Documentation(step = "Select driver", expected = "The application should select driver")
	public void selectDriverForExternalUser() throws Exception {
		OrderingDriverPage.selectDriver();
		OrderingDriverPage.verifyDriverDataAsPerFleetPreferences();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingVehiclePage.waitForVehiclePage();
	}

	@Test(dependsOnMethods = "selectDriverForExternalUser")
	@Documentation(step = "Select approved vehicle spec", expected = "The application should select approved vehicle spec")
	public void selectVehicleForOrderingForExternalUser() throws Exception {
		OrderingVehiclePage.searchAndSelectSearchedFleet();
		OrderingVehiclePage.selectExteriorColor();
		OrderingVehiclePage.selectInteriorColor();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingBillingAndRegistrationPage.waitForBillingAndRegistrationPage();
	}

	@Test(dependsOnMethods = "selectVehicleForOrderingForExternalUser")
	@Documentation(step = "Select billing and registration details", expected = "The application should select billing and registration details")
	public void billingAndRegistrationForExternalUser() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDealerPage.waitForDealerPage();
	}

	@Test(dependsOnMethods = "billingAndRegistrationForExternalUser")
	@Documentation(step = "To select dealer", expected = "Request a New Dealer link should be displayed and enabled")
	public void selectDealerForExternalUser() throws Exception {
		OrderingFOFleetPreferencesPage.verifyRequestDealerLink();		
	    OrderingStartHerePage.clickOnCancelButton();
	    OrderingStartHerePage.selectOnCancelPopUpOkButton();
	    OrderingHomePage.waitForHomePage();
	}

	@Test(dependsOnMethods = "selectDealerForExternalUser")
	@Documentation(step = "Click on fleet preferences link and validate page loaded",expected = "Fleet prferences page should be loaded") 
	public void goToFleetPreferencesForExternalUser() throws Exception {
		OrderingHomePage.selectSideMenuOption("Fleet Preferences");
		OrderingCommonPage.checkAlertPopUp();
		OrderingHomePage.fleetPrefrencesPageLoaded();
		OrderingFOFleetPreferencesPage.fleetPreferencesLabelValidation();
	}

	@Test(dependsOnMethods = "goToFleetPreferencesForExternalUser")
	@Documentation(step = "Navigate to dealer tab with external user logged in",expected = "Delivering dealer 'No' radio button should be enabled")
	public void verifyNoFunctionalityOfDealerSectionForExternalUser() throws Exception {
		OrderingFOFleetPreferencesPage.clickDealerTabNoRadioButton();
		OrderingFOFleetPreferencesPage.SaveAndProceed();
		OrderingFOFleetPreferencesPage.closeFleetPreferencePage();
	}

	@Test(dependsOnMethods = "verifyNoFunctionalityOfDealerSectionForExternalUser")
	@Documentation(step = "Navigate to Create Order", expected ="Dealer tab should not be displayed") 
	public void VerifyDealerTabIsNotDisplayedForExternalUser() throws Exception {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingHomePage.orderingSideMenuOptionLoaded();
		OrderingHomePage.orderingSideSectionLabelValidation();
		OrderingHomePage.selectOrderingMenuOption("Create Order");
		OrderingStartHerePage.waitForStartHerepage();
		OrderingStartHerePage.enterStartHerePageDetails(CommonPage.getTestData("ApplicationURL"));
		OrderingStartHerePage.selectOrderType();
		OrderingStartHerePage.selectPoolOrderNo();
		OrderingStartHerePage.selectAddUsedUnitNo();
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDriverPage.waitForDriverPage();
		OrderingFOFleetPreferencesPage.verifyDealerTabIsNotPresent();
		OrderingStartHerePage.clickOnCancelButton();
		OrderingStartHerePage.selectOnCancelPopUpOkButton();
		OrderingHomePage.verifyLogOutFunctionality();
	}

	@Test(dependsOnMethods = "VerifyDealerTabIsNotDisplayedForExternalUser")
	@Documentation(step = "Open URL and log in with super user and go to fleet preferences page",expected = "Application logged in with super user and fleet preferences page should be loaded") 
	public void goToFleetPreferenceForUpdate() throws Exception {
		OrderingLoginPage.openCustomFOApplication(CommonPage.getTestData("InternalUser"));
		
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
		OrderingHomePage.selectSideMenuOption("Fleet Preferences");
		OrderingCommonPage.checkAlertPopUp();
		OrderingHomePage.fleetPrefrencesPageLoaded();
	}

	@Test(dependsOnMethods = "goToFleetPreferenceForUpdate")
	@Documentation(step = "Update information from fleet preferences and save",expected = "Fleet preferences information should be updated") 
	public void updateInformation() throws Exception {
		OrderingFOFleetPreferencesPage.updateInformationAndVerify();
	}
	
	@Test(dependsOnMethods = "updateInformation")
	@Documentation(step = "Open url and log in with external user and go to create order",expected = "Application logged in with external user and moved to create order page") 
	public void goToCreateOrderPage() throws Exception {
		OrderingFOFleetPreferencesPage.closeFleetPreferencePage();
		OrderingHomePage.verifyLogOutFunctionality();
		OrderingLoginPage.openCustomFOApplication(CommonPage.getTestData("ExternalUser"));
		
		OrderingHomePage.changeClientBreakDownFromDropDown();
		OrderingHomePage.goToCreateOrder();
	}
	
	@Test(dependsOnMethods = "goToCreateOrderPage")
	@Documentation(step = "Verify auto unit check box and used unit information as per fleet preferences",expected = "Auto Unit check box and used unit information should be as per fleet preferences") 
	public void verifyUpdatedDetailsOnStartHere() throws Exception {
		OrderingStartHerePage.verifyAutoUnit("No");
		OrderingStartHerePage.enterUnitNumber();
		OrderingStartHerePage.verifyUpdatedWhoToSellAsPerFleetPreference();
	}

	@Test(dependsOnMethods = "verifyUpdatedDetailsOnStartHere")
	@Documentation(step = "Enter Driver data on create order page and verify driver label,first name and last label", expected = "Driver data should be provided on create order page and driver label, first name and last name should be verified") 
	public void verifyDriverData() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDriverPage.selectDriver();
		OrderingDriverPage.verifyUpdatedDriverDataAsPerFleetPreferences();
	}

	@Test(dependsOnMethods = "verifyDriverData", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}
	
}
