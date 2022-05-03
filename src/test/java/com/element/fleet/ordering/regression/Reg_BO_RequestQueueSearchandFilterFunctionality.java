package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingBOUpfitProjectPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class Reg_BO_RequestQueueSearchandFilterFunctionality extends BaseWebDriver {
	
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
	}

	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Navigate and verify Queues page", expected = "Queues page should be displayed")
	public void navigateToQueuesPage() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.queuePageLabelValidation();
	}
	
	@Test(dependsOnMethods = "navigateToQueuesPage")
	@Documentation(step = "Navigate and verify upfit/ project page", expected = "Upfit/Project page should be displayed.")
	public void navigateToUpfitProjectPage() throws Exception {
		OrderingBOQueuePage.gotoUpfitProjectPage();
		OrderingBOUpfitProjectPage.waitForUpfitProjectPage();
		Thread.sleep(5000);
		OrderingBOUpfitProjectPage.upfitProjectPageHeadingLabelValidation();
		OrderingBOUpfitProjectPage.upfitProjectPageBasicComponentsValidation();
	}
	
	
	@Test(dependsOnMethods = "navigateToQueuesPage")
	@Documentation(step = "Navigate and verify upfit/ project page", expected = "Upfit/Project page should be displayed.")
	public void toVerifyRequestQueue() throws Exception {
		OrderingBOQueuePage.upfitRequestQueueLabelValidation();
		}
	
	//Filter click >> Hide the fields
	@Test(dependsOnMethods = "toVerifyRequestQueue")
	@Documentation(step = "To validate filter can hide Basic Search fields in RequestQueue", expected = "All the basic search fields should be Hidden.")
	public void toValidateFilterHide() throws Exception {
		OrderingBOQueuePage.verifyFilterHide();
		}
	
	//Filter click >> Show the fields
	@Test(dependsOnMethods = "toValidateFilterHide")
	@Documentation(step = "To validate filter can hide Basic Search fields in RequestQueue", expected = "All the basic search fields should be displayed.")
	public void ToValidateFilterShow() throws Exception {
		OrderingBOQueuePage.verifyFilterShow();
		}
	
	@Test(dependsOnMethods = "ToValidateFilterShow")
    @Documentation(step = "To validate Search fields in RequestQueue", expected = "All the basic and advanced search fields should be displayed.")
    public void ToValidateSearchFields() throws Exception {
        OrderingBOQueuePage.verifySearchFieldsHeadings();
        }
	
	@Test(dependsOnMethods = "ToValidateSearchFields")
	@Documentation(step = "To validate Columns in RequestQueue", expected = "All the basic and advanced search fields should be displayed.")
	public void ToValidateColumns() throws Exception {
		OrderingBOQueuePage.validateAllColumns();
		}
	
	
	
	@Test(dependsOnMethods = "ToValidateColumns")
	@Documentation(step = "To validate Clear Filters in RequestQueue", expected = "All the basic and advanced search fields should be cleared.")
	public void ToValidateClearFilters() throws Exception {
		OrderingBOQueuePage.validateClearFilters();
		}
	
	@Test(dependsOnMethods = "ToValidateClearFilters")
	@Documentation(step = "To validate filters are working in RequestQueue", expected = "Fields should be displayed.")
	public void validateFiltersWorking() throws Exception {
		OrderingBOQueuePage.validateFiltersWorking();
		}
	
	
	@Test(alwaysRun = true, dependsOnMethods = "validateFiltersWorking")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}

}
