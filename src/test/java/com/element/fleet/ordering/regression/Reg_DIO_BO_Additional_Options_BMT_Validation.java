package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOAdditionalOptionsPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy Damodhar
 */
public class Reg_DIO_BO_Additional_Options_BMT_Validation extends BaseWebDriver {

	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Throwable {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}

	@Test(alwaysRun=true)
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void testLaunchBOURL() throws Throwable {
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Throwable {
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Navigate to DIO Page and validate columns", expected = "Telematics Flag column should be present and its position should be as expected")
	public void validateColumnsOnAdditionalOptionsPage() throws Throwable {
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Additional Options");
		OrderingBOAdditionalOptionsPage.validateColumnsOnAdditionalOptionsPage();
	}
	
	@Test(dependsOnMethods = "validateColumnsOnAdditionalOptionsPage")
	@Documentation(step = "validate columns in Toggle column list", expected = "Telematics Flag column should be present and its position should be as expected")
	public void validateColumnsInToggleColumnList() throws Throwable {
		OrderingBOAdditionalOptionsPage.validateToggleColumnsOnAdditionalOptionsPage();
	}
	
	@Test(dependsOnMethods = "validateColumnsInToggleColumnList")
	@Documentation(step = "validate columns in exported CSV", expected = "Telematics Flag column should be present and its position should be as expected")
	public void validateColumnsInExportedCSVFile() throws Throwable {
		OrderingBOAdditionalOptionsPage.validateColumnsInExportedCSVOnAdditionalOptionsPage(this.getClass().getSimpleName());
	}
	
	@Test(dependsOnMethods = "validateColumnsInExportedCSVFile")	
	@Documentation(step = "Validate Telematics flag is mandatory on Add Additional Options", expected = "Telematics Flag should be mandatory on Add Additional Options")
	public void validateTelematicsFlagIsMandatory() throws Throwable {
		OrderingBOAdditionalOptionsPage.clickAddNewAdditionalOption();
		OrderingBOAdditionalOptionsPage.validateTelematicsFlagIsMandatory();
	}
	
	@Test(dependsOnMethods = "validateTelematicsFlagIsMandatory")
	@Documentation(step = "Validate DIO is set to false by default and Brand/Provider & Supplier To ship are not displayed", expected = "DIO should be set to false by default and Brand/Provider & Supplier To ship should not be displayed")
	public void validateDIOBrandProviderSupplierToShip() throws Throwable {
		OrderingBOAdditionalOptionsPage.validateDIOAndBrandProviderSupplierToShip();
	}
	
	@Test(dependsOnMethods = "validateDIOBrandProviderSupplierToShip")
	@Documentation(step = "Toggle DIO indicator and validate Brand/Provider & Supplier To ship are displayed", expected = "Brand/Provider & Supplier To ship should be displayed")
	public void toggleDIOValidateBrandProviderSupplierToShip() throws Throwable {
		OrderingBOAdditionalOptionsPage.toggleDIOValidateBrandProviderSupplierToShip();
	}
	
	@Test(dependsOnMethods = "toggleDIOValidateBrandProviderSupplierToShip")
	@Documentation(step = "Validate brandProvider & supplier to ship values are not retained if changes are not saved", expected = "Brand/Provider & supplier to ship values should not be retained if changes are not saved")
	public void setResetDIOToggleValidateBrandProviderSupplierToShip() throws Throwable {
		OrderingBOAdditionalOptionsPage.addBrandProvider();
		OrderingBOAdditionalOptionsPage.setResetDIO();
		OrderingBOAdditionalOptionsPage.validateBrandProviderSupplierToShipAreNotRetained();
	}
	
	@Test(dependsOnMethods = "setResetDIOToggleValidateBrandProviderSupplierToShip")
	@Documentation(step = "Validate brandProvider & supplier to ship values are retained if changes are saved", expected = "BrandProvider & supplier to ship values should be retained if changes are saved")
	public void validateBrandProviderSupplierToShipAreRetainedOnSavingChanges() throws Throwable {
		OrderingBOAdditionalOptionsPage.addAdditionalOption(true, false);
		OrderingBOAdditionalOptionsPage.searchAndNavigateToAdditionalOption();
		OrderingBOAdditionalOptionsPage.setResetDIO();
		OrderingBOAdditionalOptionsPage.validateBrandProviderSupplierToShipAreNotRetained();
		OrderingBOAdditionalOptionsPage.backToQueueView();
		OrderingBOAdditionalOptionsPage.searchAndNavigateToAdditionalOption();
		OrderingBOAdditionalOptionsPage.validateBrandProviderSupplierToShipAreRetainedOnSavingChanges();
		OrderingBOAdditionalOptionsPage.deleteAdditionalOption();
	}
	
	@Test(dependsOnMethods = "validateBrandProviderSupplierToShipAreRetainedOnSavingChanges")
	@Documentation(step = "Validate user is able to add AO with DIO false and Telematics true", expected = "User should be able to add AO with DIO false and Telematics true")
	public void validateUserIsAbleToAddDIOWithDIOFalseAndTelematicsTrue() throws Throwable {
		OrderingBOAdditionalOptionsPage.clickAddNewAdditionalOption();
		OrderingBOAdditionalOptionsPage.addAdditionalOption(false, true);
	}
	
	@Test(dependsOnMethods = "validateUserIsAbleToAddDIOWithDIOFalseAndTelematicsTrue")
	@Documentation(step = "Validate user is able to edit DIO with DIO false and additional option true", expected = "User should be able to edit DIO with DIO false and additional option true")
	public void validateUserIsAbleToEditExistingAOWithDIOFalseAndTelematicsTrue() throws Throwable {
		OrderingBOAdditionalOptionsPage.searchAndNavigateToAdditionalOption();
		OrderingBOAdditionalOptionsPage.editAdditionalOption();
		OrderingBOAdditionalOptionsPage.searchAndNavigateToAdditionalOption();
		OrderingBOAdditionalOptionsPage.deleteAdditionalOption();
	}
	
	@Test(dependsOnMethods = "validateUserIsAbleToEditExistingAOWithDIOFalseAndTelematicsTrue")
	@Documentation(step = "Validate user is able to add AO with DIO true and Telematics true", expected = "User should be able to add AO with DIO true and Telematics true")
	public void validateUserIsAbleToAddDIOWithDIOTrueAndTelematicsTrue() throws Throwable {
		OrderingBOAdditionalOptionsPage.clickAddNewAdditionalOption();
		OrderingBOAdditionalOptionsPage.addAdditionalOption(true, true);
		OrderingBOAdditionalOptionsPage.searchAndNavigateToAdditionalOption();
		OrderingBOAdditionalOptionsPage.deleteAdditionalOption();
	}
	
	@Test(dependsOnMethods = "validateUserIsAbleToAddDIOWithDIOTrueAndTelematicsTrue")
	@Documentation(step = "Validate user is able to add AO with DIO false and Telematics false", expected = "User should be able to add AO with DIO false and Telematics false")
	public void validateUserIsAbleToAddDIOWithDIOFalseAndTelematicsFalse() throws Throwable {
		OrderingBOAdditionalOptionsPage.clickAddNewAdditionalOption();
		OrderingBOAdditionalOptionsPage.addAdditionalOption(false, false);
		OrderingBOAdditionalOptionsPage.searchAndNavigateToAdditionalOption();
		OrderingBOAdditionalOptionsPage.deleteAdditionalOption();
	}
	
	@Test(dependsOnMethods = "validateUserIsAbleToAddDIOWithDIOFalseAndTelematicsFalse")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Exception {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Throwable {
		CommonPage.testEnded();
	}
	
}