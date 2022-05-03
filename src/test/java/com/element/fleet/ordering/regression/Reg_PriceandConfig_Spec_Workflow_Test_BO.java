package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingFOFleetSpecDetailsPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingVehicleConfigFleetSpecsPage;
import com.element.fleet.ordering.page.OrderingVehiclePage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

/**
 * @lastModifiedBy lpadaliya
*/

public class Reg_PriceandConfig_Spec_Workflow_Test_BO extends BaseWebDriver {
	@BeforeClass
	@Parameters({ "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Throwable {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
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
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor)WebDriverAccess.getDriver());
		OrderingBOHomePage.orderingBOHomePageLoaded();
		CommonPage.initializeElementOrderObject();
	}

	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Click on Vehicle cofig and click on Add Fleet Specs Link", expected = "User should land on the ADD fleet specs page")
	public void goToAddPriceandConfigSpecsPage() throws Exception {
		OrderingHomePage.clickVehicleConfigurationLink();
		OrderingHomePage.clickBOVehicleConfigurationAddPriceandConfigSpecsLink();
	}

	@Test(dependsOnMethods = "goToAddPriceandConfigSpecsPage")
	@Documentation(step = "Enter vehicle make and model name in serach criteria", expected = "User should be able to enter vehicle make name and model name")
	public void goToViewPriceandConfigSpecsPage() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.waitForViewFleetSpecsPage();
		OrderingVehicleConfigFleetSpecsPage.enterValueInYearTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInClientTextBox();
		OrderingVehicleConfigFleetSpecsPage.enterValueInMakeTextBox();
	}

	@Test(dependsOnMethods = "goToViewPriceandConfigSpecsPage")
	@Documentation(step = "Select first searched vehicle and verify retail only flag", expected = "User should be able see retail only flag and select first searched vehicle after that vehicle details page should be loaded")
	public void selectFirstSearchedvehicle() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.selectFirstVehicleAfterSearch();
		OrderingVehiclePage.waitForVehicleSectionInformationPage();
	}

	@Test(dependsOnMethods = "selectFirstSearchedvehicle")
	@Documentation(step = "Click on Save button,Enter fleet spec name on the POP Up and click on save", expected = "Fleet spec search screen should be visible")
	public void SaveCraetedPriceandConfigspec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnSaveButton();
		CommonPage.getElementOrderObject()
				.getVehicleTabObject()
				.setFleetSpecName(OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameAndNotes("Test" + CommonPage.randomAlphaNumericString()));
	}

	@Test(dependsOnMethods = "SaveCraetedPriceandConfigspec")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void SearchCraetedPriceandConfigspec() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}

	@Test(dependsOnMethods = "SearchCraetedPriceandConfigspec")
	@Documentation(step = "search previously created fleet spec with name/id and verify on hold", expected = "Search should result in previously created fleet spec/s and change it to on hold and then remove on hold") 
	public void moveToHoldAndRemoveHold() throws Exception {
		 OrderingVehicleConfigFleetSpecsPage.placeSpecOnHold();
		 OrderingVehicleConfigFleetSpecsPage.selectActionFromList(1,"Remove Hold");
		 OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	  	  
	@Test(dependsOnMethods = "moveToHoldAndRemoveHold")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void MoveStatustoPendingClientApproval() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.movetoPendingClientApproval();
	}

	@Test(dependsOnMethods = "MoveStatustoPendingClientApproval")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void MoveStatustoClientDeclined() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.movetoClientDeclined();
	}

	@Test(dependsOnMethods = "MoveStatustoClientDeclined")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void MoveStatustoPendingClientApprovalforapproval() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.movetoPendingClientApproval();
	}

	@Test(dependsOnMethods = "MoveStatustoPendingClientApprovalforapproval")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void MoveStatustoClientApproved() throws Exception {
		OrderingFOFleetSpecDetailsPage.clientapprovefleetspec();
		OrderingVehicleConfigFleetSpecsPage.enterFleetSpecsNameInSearchBoxAndCickOnSearch();
	}

	@Test(dependsOnMethods = "MoveStatustoClientApproved")
	@Documentation(step = "search previously created fleet spec with name/id", expected = "Search should result in previously created fleet spec/s")
	public void MoveStatustoArchived() throws Exception {
		OrderingVehicleConfigFleetSpecsPage.clickOnFleetSpecAction();
		OrderingVehicleConfigFleetSpecsPage.movetoArchived();
	}

	@Test(alwaysRun = true, dependsOnMethods = "MoveStatustoArchived")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
}