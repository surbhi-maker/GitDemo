package com.element.fleet.ordering.regression;

import org.openqa.selenium.Point;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.f3270.BaseMainframeDriver;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOrdMaintPage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingBillingAndRegistrationPage;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingDealerPage;
import com.element.fleet.ordering.page.OrderingDriverPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingMFStatusValidation;
import com.element.fleet.ordering.page.OrderingMainframePage;
import com.element.fleet.ordering.page.OrderingStartHerePage;
import com.element.fleet.ordering.page.OrderingSummaryPage;
import com.element.fleet.ordering.page.OrderingVehiclePage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy mounika
 */
public class Reg_MF_Status_Upfit_Add_Remove_Dealer_Order extends BaseWebDriver {

	OrderingMainframePage ordMainframe ;
	
	@BeforeClass
	@Parameters({ "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}

	@Test(alwaysRun = true)
	@Documentation(step = "Open browser and enter application url", expected = "Login page of the application should get displayed") 
	public void testLaunchURL() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openFOApplication();
	}
	
	@Test(dependsOnMethods = "testLaunchURL")
	@Documentation(step = "Enter the required client number to the application",expected = "The application display the required client")
	public void changeClientBreakdown() throws Exception {
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
	}

	@Test(dependsOnMethods = "changeClientBreakdown")
	@Documentation(step = "Select order type on create order page", expected = "Order type should be Factory Order") 
	public void selectOrderType() throws Exception {
		OrderingStartHerePage.moveToCreateOrderPage();
	}

	@Test(dependsOnMethods = "selectOrderType")
	@Documentation(step = "Enter Driver data on create order page", expected = "Driver data should be provided on create order page.") 
	public void inputDriverData() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDriverPage.enterDriverData();
	}

	@Test(dependsOnMethods = "inputDriverData")
	@Documentation(step = "Select vehicle through fleet spec", expected = "Vehicle should be selected from fleet spec") 
	public void selectVehicleFleetSpec() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingVehiclePage.enterDetailsVehicleData();
	}

	@Test(dependsOnMethods = "selectVehicleFleetSpec") 
	@Documentation(step = "Select Lease as contracting option", expected = "The application should be able to select leasing as contracting option")
	public void selectBillingLease() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingBillingAndRegistrationPage.enterBillingAndRegistrationDetails();
	}

	@Test(dependsOnMethods = "selectBillingLease")
	@Documentation(step = "Select from displayed dealer", expected = "Dealer should be select from displayed option")
	public void selectDealer() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
		OrderingDealerPage.selectDealer();
	}

	@Test(dependsOnMethods = "selectDealer")
	@Documentation(step = "Validate the summary page ", expected = "Summary Page details should be validated")
	public void validateSummaryPage() throws Exception {
		OrderingStartHerePage.clickSaveAndNext();
	}

	@Test(dependsOnMethods="validateSummaryPage")
	@Documentation(step = "Submit the Order ", expected = "The Order should be submitted") 
	public void submitOrder() throws Exception {
		OrderingSummaryPage.clickSubmit();
		OrderingCommonPage.checkAlertPopUp();
		OrderingSummaryPage.confirmSubmit();
		OrderingSummaryPage.postSummaryPageActions(this.getClass().getSimpleName());
		CommonPage.loadXMLParameterToTestData("LogNumber", CommonPage.getElementOrderObject().getLogNumber());
	}
	
	@Test(dependsOnMethods="submitOrder")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly") 
	public void verifyLogOutFunctionality() throws Exception {
		OrderingHomePage.clickOnCloseSummaryPage();
		OrderingHomePage.verifyLogOutFunctionality();
	} 

	@Test(dependsOnMethods="verifyLogOutFunctionality")
	@Documentation(step = "validate bridging table of Ordering DB", expected = "Bridging table should be updated with the expected column values")
	public void bridgingDBValidation() throws Exception {
		OrderingMFStatusValidation.addBridgingTableDataValidation();
		driver.manage().window().setPosition(new Point(0,-2000));
	}
	
public class mf extends BaseMainframeDriver{
		
		@Test(alwaysRun = true)
		@Documentation(step = "Validates mainframe for status of new order created", expected = "MF status should be validated in mainframe")
		public void mainframeStatusValidation() throws Throwable {
			ordMainframe = new OrderingMainframePage();
			ordMainframe.mainframeStatusValidation();
			driver.manage().window().maximize();
		}
	}
	
	public class backOffcCancelOrder extends BaseWebDriver{ 

		@Test(alwaysRun = true)
		@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
		public void testLaunchBOURL() throws Exception {
			OrderingLoginPage.openBOApplication();
		}

		@Test(dependsOnMethods = "testLaunchBOURL")
		@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
		public void testLoginBOApplication() throws Exception {
			OrderingBOHomePage.orderingBOHomePageLoaded();
		}
		
		@Test(dependsOnMethods = "testLoginBOApplication")
		@Documentation(step = "Go to On Order Queue page", expected = "Application should load On Order Queue page")
		public void goToOnOrderQueuePage() throws Throwable {
			OrderingBOQueuePage.gotoCreatedOrder();
		}
		
		@Test(dependsOnMethods = "goToOnOrderQueuePage")
		@Documentation(step = "Go to On Order Queue page", expected = "Application should load On Order Queue page")
		public void cancelOrder() throws Throwable {
			OrderingBOOrdMaintPage.cancelOrder();
		}
		
		@Test(dependsOnMethods = "cancelOrder")
		@Documentation(step = "validate bridging table of Ordering DB", expected = "Bridging table should be updated with the expected column values")
		public void bridgingDBValidationCancelOrder() throws Exception {
			OrderingMFStatusValidation.cancelOrderBridgingTableDataValidation();
		}
		
		@Test(dependsOnMethods="bridgingDBValidationCancelOrder",alwaysRun = true)
		@Documentation(step = "Test ended", expected = "Test ended")
		public void testEnded() throws Exception {
			CommonPage.testEnded();
		}
	}
	
	@Test(dependsOnMethods = "bridgingDBValidation", alwaysRun = true)
	@Documentation(step = "Test ended", expected = "Test ended")
	public void testEnded() throws Exception {
		CommonPage.testEnded();
	}

}