package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOBusinessMaintainedTablesPage;
import com.element.fleet.ordering.page.OrderingBODealerInstalledOptionsPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy Damodhar
 */
public class Reg_DIO_BO_Add_Edit_Delete_DIO_WithAndWithout_Brand_Provider_Name extends BaseWebDriver {

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
	@Documentation(step = "Navigate to DIO Page and validate filters", expected = "Filters present on the DIO Page should be as expected")
	public void validateFiltersOnDIOPage() throws Throwable {
		OrderingBOHomePage.selectSideMenuOption("Business Maintained Tables");
		OrderingBOBusinessMaintainedTablesPage.selectBusinessMaintainedTableOption("Dealer Installed Options");
		OrderingBODealerInstalledOptionsPage.dealerInstalledOptionsPageLoaded();
		OrderingBODealerInstalledOptionsPage.validateFiltersOnDealerInstalledOptionsPage();
	}
	
	@Test(dependsOnMethods = "validateFiltersOnDIOPage")
	@Documentation(step = "Validate columns on DIO grid", expected = "Colummns should be as expected on DIO grid")
	public void validateColumnsOnDIOGrid() throws Throwable {
		OrderingBODealerInstalledOptionsPage.validateColumnsOnDealerInstalledOptionsPage();
	}
	
	@Test(dependsOnMethods = "validateColumnsOnDIOGrid")
	@Documentation(step = "validate columns in Toggle column list", expected = "Colummns should be as expected on TOggle Column list")
	public void validateColumnsInToggleColumnList() throws Throwable {
		OrderingBODealerInstalledOptionsPage.validateToggleColumnsOnDealerInstalledOptionsPage();
	}
	
	@Test(dependsOnMethods = "validateColumnsInToggleColumnList")	
	@Documentation(step = "Validate user is able to add DIO with Brand/Provider Name", expected = "User should be able to add DIO with Brand/Provider name")
	public void addDIOWithBrandProviderName() throws Throwable {
		OrderingBODealerInstalledOptionsPage.addDIOWithBrandProviderName(true);
	}
	
	@Test(dependsOnMethods = "addDIOWithBrandProviderName")
	@Documentation(step = "Validate user is able to search DIO with Brand/Provider Name", expected = "User should be able to search DIO with Brand/Provider name")
	public void searchDIOWithBrandProviderName() throws Throwable {
		OrderingBODealerInstalledOptionsPage.searchDIOUsingBrandProvider();
	}
	
	@Test(dependsOnMethods = "searchDIOWithBrandProviderName")
	@Documentation(step = "Validate user is able to edit DIO with Brand/Provider Name", expected = "User should be able to edit DIO with Brand/Provider name")
	public void editDIOWithBrandProviderName() throws Throwable {
		OrderingBODealerInstalledOptionsPage.editDIOWithBrandProviderName();
	}
	
	@Test(dependsOnMethods = "editDIOWithBrandProviderName")
	@Documentation(step = "Validate user is able to delete DIO with Brand/Provider Name", expected = "User should be able to delete DIO with Brand/Provider name")
	public void deleteDIOWithBrandProviderName() throws Throwable {
		OrderingBODealerInstalledOptionsPage.searchDIOUsingBrandProvider();
		OrderingBODealerInstalledOptionsPage.deleteDIO();
	}
	
	@Test(dependsOnMethods = "deleteDIOWithBrandProviderName")	
	@Documentation(step = "Validate user is able to add DIO without Brand/Provider Name", expected = "User should be able to add DIO without Brand/Provider name")
	public void addDIOWithoutBrandProviderName() throws Throwable {
		OrderingBODealerInstalledOptionsPage.addDIOWithBrandProviderName(false);
	}
	@Test(dependsOnMethods = "addDIOWithoutBrandProviderName")
	@Documentation(step = "Validate user is able to search DIO without Brand/Provider Name", expected = "User should be able to search DIO without Brand/Provider name")
	public void searchDIOWithOptionCode() throws Throwable {
		OrderingBODealerInstalledOptionsPage.searchDIOUsingOptionCode();
	}
	
	@Test(dependsOnMethods = "searchDIOWithOptionCode")
	@Documentation(step = "Validate user is able to edit DIO without Brand/Provider Name", expected = "User should be able to edit DIO without Brand/Provider name")
	public void editDIOWithoutBrandProvider() throws Throwable {
		OrderingBODealerInstalledOptionsPage.editDIOWithoutBrandProviderName();
	}
	
	@Test(dependsOnMethods = "editDIOWithoutBrandProvider")
	@Documentation(step = "Validate user is able to delete DIO without Brand/Provider Name", expected = "User should be able to delete DIO without Brand/Provider name")
	public void deleteDIOWithoutBrandProviderName() throws Throwable {
		OrderingBODealerInstalledOptionsPage.searchDIOUsingOptionCode();
		OrderingBODealerInstalledOptionsPage.deleteDIO();
	}
	
	@Test(dependsOnMethods = "deleteDIOWithoutBrandProviderName")
	@Documentation(step = "click on Logout button", expected = "Application should be logged out")
	public void verifyBOLogOutFunctionality() throws Throwable {
		OrderingBOHomePage.verifyLogOutFunctionality();
	}
	
	@Test(dependsOnMethods = "verifyBOLogOutFunctionality", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Throwable {
		CommonPage.testEnded();
	}
	
}