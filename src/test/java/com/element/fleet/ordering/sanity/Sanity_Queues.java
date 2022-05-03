package com.element.fleet.ordering.sanity;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOOnOrderQueuePage;
import com.element.fleet.ordering.page.OrderingBOOrdMaintPage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy shisingh
 */
public class Sanity_Queues extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}

	@Test(alwaysRun = true)
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void testLaunchBOURL() throws Exception {
		CommonPage.testStarted();
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Exception {
		OrderingBOHomePage.orderingBOHomePageLoaded();
		OrderingBOHomePage.boHomePageLabelValidation();
	}

	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Verify the Queues labels.", expected = "Queues labels are verified and should be displayed as expected.")
	public void verifyLabelQueuesPage() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.queuePageLabelValidation();
	}

	@Test(dependsOnMethods = "verifyLabelQueuesPage")
	@Documentation(step = "Go to On Order Queue page", expected = "Application should load On Order Queue page")
	public void goToOnOrderQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("On-Order");
		OrderingBOOnOrderQueuePage.waitForOnOrderQueuePage();
		OrderingBOOnOrderQueuePage.onOrderQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("On-Order");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}
	
	@Test(dependsOnMethods = "goToOnOrderQueuePage")
	@Documentation(step = "Go to Acknowledgement Queue page", expected = "Application should load Acknowledgement Queue page")
	public void goToAcknowledgementQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Acknowledgment");
		OrderingBOOnOrderQueuePage.waitForAcknowledgementQueuePage();
		OrderingBOOnOrderQueuePage.acknowledgementQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Acknowledgment");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}
	
	@Test(dependsOnMethods = "goToAcknowledgementQueuePage")
	@Documentation(step = "Go to Billing Queue page", expected = "Application should load Billing Queue page")
	public void goToBillingQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Billing");
		OrderingBOOnOrderQueuePage.waitForBillingQueuePage();
		OrderingBOOnOrderQueuePage.billingQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Billing");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}

	@Test(dependsOnMethods = "goToBillingQueuePage")
	@Documentation(step = "Go to Credit Queue page", expected = "Application should load Credit Queue page")
	public void goToCreditQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Credit");
		OrderingBOOnOrderQueuePage.waitForCreditQueuePage();
		OrderingBOOnOrderQueuePage.creditQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Credit");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}

	@Test(dependsOnMethods = "goToCreditQueuePage")
	@Documentation(step = "Go to Dealer Queue page", expected = "Application should load Dealer Queue page")
	public void goToDealerQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Dealer");
		OrderingBOOnOrderQueuePage.waitForDealerQueuePage();
		OrderingBOOnOrderQueuePage.dealerQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Dealer");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}

	@Test(dependsOnMethods = "goToDealerQueuePage")
	@Documentation(step = "Go to DIO Queue page", expected = "Application should load DIO Queue page")
	public void goToDIOQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("DIO");
		OrderingBOOnOrderQueuePage.waitForDIOQueuePage();
		OrderingBOOnOrderQueuePage.dioQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("DIO");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}

	@Test(dependsOnMethods = "goToDIOQueuePage")
	@Documentation(step = "Go to Driver Change Queue page", expected = "Application should load Driver Change Queue page")
	public void goToDriverChangeQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Driver Change");
		OrderingBOOnOrderQueuePage.waitForDriverChangeQueuePage();
		OrderingBOOnOrderQueuePage.driverChangeQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Driver Change");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}

	@Test(dependsOnMethods = "goToDriverChangeQueuePage")
	@Documentation(step = "Go to Insurance Queue page", expected = "Application should load Insurance Queue page")
	public void goToInsuranceQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Insurance");
		OrderingBOOnOrderQueuePage.waitForInsuranceQueuePage();
		OrderingBOOnOrderQueuePage.insuranceQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Insurance");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}
	
	@Test(dependsOnMethods = "goToInsuranceQueuePage")
	@Documentation(step = "Go to Mainframe Bridging Queue page", expected = "Application should load Mainframe Bridging Queue page")
	public void goToMainframeBridgingQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Mainframe Bridging");
		OrderingBOOnOrderQueuePage.waitForMainframeBridgingQueuePage();
		OrderingBOOnOrderQueuePage.mainframeBridgingQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Mainframe Bridging");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}
	
	@Test(dependsOnMethods = "goToMainframeBridgingQueuePage")
	@Documentation(step = "Go to Order Transmission Queue page", expected = "Application should load Order Transmission Queue page")
	public void goToOrderTransmissionQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Order Transmission");
		OrderingBOOnOrderQueuePage.waitForOrderTransmissionQueuePage();
		OrderingBOOnOrderQueuePage.orderTransmissionQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Order Transmission");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}

	@Test(dependsOnMethods = "goToOrderTransmissionQueuePage")
	@Documentation(step = "Go to Project Queue page", expected = "Application should load Project Queue page")
	public void goToProjectQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Project");
		OrderingBOOnOrderQueuePage.waitForProjectQueuePage();
		OrderingBOOnOrderQueuePage.projectQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Project");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}

	@Test(dependsOnMethods = "goToProjectQueuePage")
	@Documentation(step = "Go to Quote Queue page", expected = "Application should load Quote Queue page")
	public void goToQuoteQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Quote");
		OrderingBOOnOrderQueuePage.waitForQuoteQueuePage();
		OrderingBOOnOrderQueuePage.quoteQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Quote");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}

	@Test(dependsOnMethods = "goToQuoteQueuePage")
	@Documentation(step = "Go to RFQ Queue page", expected = "Application should load RFQ Queue page")
	public void goToRFQQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("RFQ");
		OrderingBOOnOrderQueuePage.waitForRFQQueuePage();
		OrderingBOOnOrderQueuePage.rfqQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}
	
	@Test(dependsOnMethods = "goToRFQQueuePage")
	@Documentation(step = "Go to Stock Queue page", expected = "Application should load Stock Queue page")
	public void goToStockQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Stock");
		OrderingBOOnOrderQueuePage.waitForStockQueuePage();
		OrderingBOOnOrderQueuePage.stockQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Stock");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}

	@Test(dependsOnMethods = "goToStockQueuePage")
	@Documentation(step = "Go to Sweeper Admin Queue page", expected ="Application should load Sweeper Admin Queue page")
	public void goToSweeperAdminQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Sweeper Results");
		OrderingBOOnOrderQueuePage.waitForSweeperAdminQueuePage();
		OrderingBOOnOrderQueuePage.sweeperAdminQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Sweeper Results");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage(); 
	}
	
	@Test(dependsOnMethods = "goToSweeperAdminQueuePage")
	@Documentation(step = "Go to Title & Reg Queue page", expected = "Application should load Title & Reg Queue page")
	public void goToTitleAndRegQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Title & Reg");
		OrderingBOOnOrderQueuePage.waitForTitleAndRegQueuePage();
		OrderingBOOnOrderQueuePage.titleAndRegQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Title & Reg");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}
	
	@Test(dependsOnMethods = "goToTitleAndRegQueuePage")
	@Documentation(step = "Go to Upfit Queue page", expected = "Application should load Upfit Queue page")
	public void goToUpfitQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Upfit");
		OrderingBOOnOrderQueuePage.waitForUpfitQueuePage();
		OrderingBOOnOrderQueuePage.upfitQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Upfit");
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}

	@Test(dependsOnMethods = "goToUpfitQueuePage")
	@Documentation(step = "Go to Upfit Spec Queue page", expected = "Application should load Upfit Spec Queue page")
	public void goToUpfitSpecQueuePage() throws Throwable {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Upfit Spec");
		OrderingBOOnOrderQueuePage.waitForUpfitSpecQueuePage();
		OrderingBOOnOrderQueuePage.upfitSpecQueuePageLabelValidation();
		PDFReporter.takeExtraScreenshot();
		OrderingBOOnOrderQueuePage.clickOnTheFirstOptionOfListIfAvailable("Upfit Spec");
		PDFReporter.takeExtraScreenshot();
		OrderingBOOrdMaintPage.goBackToQueuesPage();
	}

	@Test(alwaysRun = true, dependsOnMethods = "goToUpfitSpecQueuePage")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}

}