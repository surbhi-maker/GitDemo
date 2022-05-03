package com.element.fleet.ordering.regression;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingCommonPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class Reg_Add_AssociateUpfit extends BaseWebDriver{
	
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
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void testLaunchBOURL() throws Exception {
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Exception {
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor) WebDriverAccess.getDriver());
		OrderingBOHomePage.orderingBOHomePageLoaded();
		
	}
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Navigates to Vehicle configution", expected = "The application home page should be displayed after successful login")
	public static void navigateToPage() {
        String url=WebDriverAccess.getDriver().getCurrentUrl();
        try
        {
          URL url1 = new URL(url);
          String baseUrl = url1.getProtocol() + "://" + url1.getHost();
          String pageURL= baseUrl +"#vehicleConfig/fleetSpec/search";
          WebDriverAccess.getDriver().get(pageURL);
        }
        catch (MalformedURLException e)
        {
            System.out.println("Page not found");
        }       
    }
    @Test(dependsOnMethods = "navigateToPage")
	@Documentation(step = "Search for UpfitPending", expected ="Upfit pending status to be choose") 
     public void selectStatusUpfitPending() throws Throwable {
	  OrderingBOQueuePage.selectStatusUpfitPending();
	  OrderingBOQueuePage.searchUpfitPending();
	   }
    
    @Test(dependsOnMethods = "selectStatusUpfitPending")
   	@Documentation(step = "Select an Upfit Pending Fleetspec", expected ="Upfit pending Fleetspec needs to be selected") 
     public void selectFleetSpecUpfitPending() throws Throwable {
   	  OrderingBOQueuePage.selectUpfitPendingFleetSpec();
   	  OrderingBOQueuePage.clickUpfittingTab();
   	  OrderingBOQueuePage.validateAssociatedTab();
   	  OrderingBOQueuePage.validateAccordionMessage();
   	   }
    @Test(dependsOnMethods = "selectFleetSpecUpfitPending")
   	@Documentation(step = "Select an Upfit Pending Fleetspec", expected ="Upfit pending Fleetspec needs to be selected") 
     public void addAssociationFunctionality() throws Throwable {
   	  OrderingBOQueuePage.addAssociationClick();
   	  OrderingBOQueuePage.toValidateToggleButton();
   	  Thread.sleep(4000);
   	  OrderingBOQueuePage.validateBGColorToggleButton();
   	  OrderingBOQueuePage.selectAssociationClick();
   	  OrderingBOQueuePage.quoteSelectedSave();
   	  OrderingBOQueuePage.validateNotificationMessage();
   	  BrowserAction.refresh();
   	  }
    @Test(dependsOnMethods = "addAssociationFunctionality")
   	@Documentation(step = "Select an Upfit Pending Fleetspec", expected ="Upfit pending Fleetspec needs to be selected") 
     public void validateAssociatedUpfitAdded() throws Throwable {
   	  OrderingBOQueuePage.selectStatusUpfitPending();
   	  OrderingBOQueuePage.searchUpfitPending();
   	  OrderingCommonPage.checkGlobalSpinnerPopUp();
   	  OrderingBOQueuePage.selectUpfitPendingFleetSpec();
 	  OrderingBOQueuePage.clickUpfittingTab();
 	  OrderingBOQueuePage.toValidateQuoteName();
 	  OrderingBOQueuePage.validateBGColorToggleButton();
   	  }
    
    
	 }

