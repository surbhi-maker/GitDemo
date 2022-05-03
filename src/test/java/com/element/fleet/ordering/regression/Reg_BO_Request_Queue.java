package com.element.fleet.ordering.regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOHomePage;
import com.element.fleet.ordering.page.OrderingBOQueuePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class Reg_BO_Request_Queue extends BaseWebDriver{
	
	@BeforeClass
	@Parameters({"xcelerateURL", "username", "applicationURL", "applicationBOURL", "orderingTestDataFilePath", "orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String username, String applicationURL, String applicationBOURL, String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime, ITestContext context) throws Exception {
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
		OrderingLoginPage.openBOApplication();
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Enter the valid username and password to login to the application", expected = "The application home page should be displayed after successful login")
	public void testLoginBOApplication() throws Exception {
		OrderingLoginPage.clickLoginBtn((JavascriptExecutor) WebDriverAccess.getDriver());
		OrderingBOHomePage.orderingBOHomePageLoaded();
	}
	@Test(dependsOnMethods = "testLoginBOApplication")
	@Documentation(step = "Navigate    and verify Queues page", expected = "Queues page should be displayed")
	public void navigateToQueuesPage() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
	//	OrderingBOQueuePage.queuePageLoaded();
	}
	@Test(dependsOnMethods = "navigateToQueuesPage")
	@Documentation(step = "Navigate and verify upfit/ project page", expected = "Upfit/Project page should be displayed.")
	public void navigateToUpfitProjectPage() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.gotoUpfitProjectPage();
	}

	@Test(dependsOnMethods = "navigateToUpfitProjectPage")
	@Documentation(step = "Navigate to Request Quoue", expected = " Request Queue Screen should be displayed.")
	public void navigateToRequestQueueScreen1() throws Exception {
		OrderingBOQueuePage.scrollDownToRequestQueue();
	}
	
//Factory OEM Order search
	@Test(dependsOnMethods = "navigateToRequestQueueScreen1")
	@Documentation(step = "To validate Factory OEM Order Detail page ", expected = "user should be able to navigate to detail page .")
	public void validateRequestDetailPageOEMOrder() throws Exception {
		OrderingBOQueuePage.validateClearFilters();
		OrderingBOQueuePage.searchOrigin();
		OrderingBOQueuePage.scrollDownToRequestQueue();
		OrderingBOQueuePage.validateRequestDetailPageOEMOrder();

	}

/*	@Test(dependsOnMethods = "ToValidateClearFilters")
	@Documentation(step = "To validate Origin filters is working in RequestQueue", expected = "Fields should be displayed.")
	public void searchOrigin() throws Exception {
		OrderingBOQueuePage.searchOrigin();
	}
	
	@Test(dependsOnMethods = "searchOrigin")
	@Documentation(step = "Navigate to Request Quoue", expected = " Request Queue Screen should be displayed.")
	public void navigateToRequestQueueScreen() throws Exception {
		OrderingBOQueuePage.scrollDownToRequestQueue();
	}
	@Test(dependsOnMethods = "navigateToRequestQueueScreen")
	@Documentation(step = "To validate Request Detail Page", expected = "Fields should be displayed.")
	public void validateRequestDetailPageOEMOrder() throws Exception {
		OrderingBOQueuePage.validateRequestDetailPageOEMOrder();
	}
*/	
	// Fleet spec -origin 
	@Test(dependsOnMethods = "validateRequestDetailPageOEMOrder")
	@Documentation(step = "Navigate and verify upfit/ project page ", expected = "user should be able Navigate and verify upfit/ project page")
	public void navigateToUpfitProjectPage2() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.popupPage();	
		OrderingBOQueuePage.gotoUpfitProjectPage();
	}
	
	@Test(dependsOnMethods = "navigateToUpfitProjectPage2")
	@Documentation(step = "To validate Fleet spec Detail page", expected = " user should be able to navigate to detail page")
	public void validateRequestDetailPageFleetSpec() throws Exception {
		OrderingBOQueuePage.scrollDownToRequestQueue();
		OrderingBOQueuePage.validateClearFilters();
		OrderingBOQueuePage.searchOrigin1();
		OrderingBOQueuePage.scrollDownToRequestQueue();
		OrderingBOQueuePage.validateRequestDetailPageFleetSpec();

	}
	
	
/*	@Test(dependsOnMethods = "navigateToRequestQueueScreen2")
	@Documentation(step = "To validate Clear Filters in RequestQueue", expected = "All the basic and advanced search fields should be displayed.")
	public void ToValidateClearFilters2() throws Exception {
		OrderingBOQueuePage.validateClearFilters();
	}

	@Test(dependsOnMethods = "ToValidateClearFilters2")
	@Documentation(step = "To validate Origin filters is working in RequestQueue", expected = "Fields should be displayed.")
	public void searchOrigin1() throws Exception {
		OrderingBOQueuePage.searchOrigin1();
	}
	
	@Test(dependsOnMethods = "searchOrigin1")
	@Documentation(step = "Navigate to Request Quoue", expected = " Request Queue Screen should be displayed.")
	public void navigateToRequestQueueScreen21() throws Exception {
		OrderingBOQueuePage.scrollDownToRequestQueue();
	}
	
	@Test(dependsOnMethods = "navigateToRequestQueueScreen21")
	@Documentation(step = "To validate Request Detail Page", expected = "Fields should be displayed.")
	public void validateRequestDetailPageFleetSpec() throws Exception {
		OrderingBOQueuePage.validateRequestDetailPageFleetSpec();
	}
	*/
	//origin-advance workflow
	
	@Test(dependsOnMethods = "validateRequestDetailPageFleetSpec")
	@Documentation(step = "Navigate and verify Queues page", expected = "Queues page should be displayed")
	public void navigateToQueuesPage3() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		
		//OrderingBOQueuePage.queuePageLoaded();
	}
	@Test(dependsOnMethods = "navigateToQueuesPage3")
	@Documentation(step = "Navigate and verify upfit/ project page", expected = "Upfit/Project page should be displayed.")
	public void navigateToUpfitProjectPage3() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.gotoUpfitProjectPage();
	}
	
	
	@Test(dependsOnMethods = "navigateToUpfitProjectPage3")
	@Documentation(step = "To validate Clear Filters in RequestQueue", expected = "All the basic and advanced search fields should be displayed.")
	public void validateRequestDetailPageAdvanceWorkflow() throws Exception {
		OrderingBOQueuePage.validateClearFilters();
		OrderingBOQueuePage.searchOrigin2();
		OrderingBOQueuePage.scrollDownToRequestQueue();
		OrderingBOQueuePage.validateRequestDetailPageAdvanceWorkflow();

	}

/*	@Test(dependsOnMethods = "ToValidateClearFilters3")
	@Documentation(step = "To validate Origin filters is working in RequestQueue", expected = "Fields should be displayed.")
	public void searchOrigin2() throws Exception {
		OrderingBOQueuePage.searchOrigin2();
	}
	@Test(dependsOnMethods = "searchOrigin2")
	@Documentation(step = "Navigate to Request Quoue", expected = " Request Queue Screen should be displayed.")
	public void navigateToRequestQueue3() throws Exception {
		OrderingBOQueuePage.scrollDownToRequestQueue();
	}
	@Test(dependsOnMethods = "navigateToRequestQueue3")
	@Documentation(step = "To validate Request Detail Page", expected = "Fields should be displayed.")
	public void validateRequestDetailPageAdvanceWorkflow() throws Exception {
		OrderingBOQueuePage.validateRequestDetailPageAdvanceWorkflow();
	}
	*/
	//rowaction 
	
	@Test(dependsOnMethods = "validateRequestDetailPageAdvanceWorkflow")
	@Documentation(step = "Navigate and verify Queues page", expected = "Queues page should be displayed")
	public void navigateToQueuesPage4() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
	//	OrderingBOQueuePage.queuePageLoaded();
	}

	@Test(dependsOnMethods = "navigateToQueuesPage4")
	@Documentation(step = "Navigate and verify upfit/ project page", expected = "Upfit/Project page should be displayed.")
	public void navigateToUpfitProjectPage4() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.gotoUpfitProjectPage();
	}

	@Test(dependsOnMethods = "navigateToUpfitProjectPage4")
	@Documentation(step = "Navigate to Request Quoue", expected = " Request Queue Screen should be displayed.")
	public void navigateToRequestQueueScreen41() throws Exception {
		OrderingBOQueuePage.scrollDownToRequestQueue();
	}
	
	

	@Test(dependsOnMethods = "navigateToRequestQueueScreen41")
	@Documentation(step = "To validate Clear Filters in RequestQueue", expected = "All the basic and advanced search fields should be displayed.")
	public void ToValidateClearFilters4() throws Exception {
		OrderingBOQueuePage.validateClearFilters();
	}

	@Test(dependsOnMethods = "ToValidateClearFilters4")
	@Documentation(step = "To validate Origin filters is working in RequestQueue", expected = "Fields should be displayed.")
	public void searchOrigin4() throws Exception {
		OrderingBOQueuePage.searchOrigin();
	}
	
	@Test(dependsOnMethods = "searchOrigin4")
	@Documentation(step = "Navigate to Request Quoue", expected = " Request Queue Screen should be displayed.")
	public void navigateToRequestQueueScreen4() throws Exception {
		OrderingBOQueuePage.scrollDownToRequestQueue();
	}
	
	@Test(dependsOnMethods = "navigateToRequestQueueScreen4")
	@Documentation(step = "Navigate to Request Quoue", expected = " Request Queue Screen should be displayed.")
	public void rowAction() throws Exception {
		OrderingBOQueuePage.standardView() ;
		OrderingBOQueuePage.rowAction();
	}
/*	@Test(dependsOnMethods = "rowAction")
	@Documentation(step = "Navigate to Request Quoue", expected = " Request Queue Screen should be displayed.")
	public void assignProject() throws Exception {
		OrderingBOQueuePage.assignProject();
	}
	@Test(dependsOnMethods = "assignProject")
	@Documentation(step = "Navigate to Request Quoue", expected = " Request Queue Screen should be displayed.")
	public void assignUser() throws Exception {
		OrderingBOQueuePage.assignUser();
	}
	*/
	@Test(dependsOnMethods = "rowAction")
	@Documentation(step = "Navigate to Request Quoue", expected = " Request Queue Screen should be displayed.")
	public void rowactionComment() throws Exception {
		OrderingBOQueuePage.rowAction();
		OrderingBOQueuePage.rowactionComment();
	}
	@Test(dependsOnMethods = "rowactionComment")
	@Documentation(step = "Navigate to Request Quoue", expected = " Request Queue Screen should be displayed.")
	public void rowactionSendBack() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.gotoUpfitProjectPage();
		OrderingBOQueuePage.scrollDownToRequestQueue();
		OrderingBOQueuePage.validateClearFilters();
		OrderingBOQueuePage.standardView() ;
		OrderingBOQueuePage.searchOrigin();
		OrderingBOQueuePage.scrollDownToRequestQueue();
		
		OrderingBOQueuePage.rowAction();
		OrderingBOQueuePage.rowactionSendBack();
	}
/*	@Test(dependsOnMethods = "rowactionSendBack")
	@Documentation(step = "Navigate to Request Quoue", expected = " Request Queue Screen should be displayed.")
	public void rowactionChangeDueDate() throws Exception {
		OrderingBOQueuePage.rowAction();
		OrderingBOQueuePage.rowactionChangeDueDate();
	}
*/
	//bulkaction 
	
	@Test(dependsOnMethods = "rowactionSendBack")
	@Documentation(step = "bulkaction", expected = " user should select more than one order")
	public void bulkAction() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.gotoUpfitProjectPage();
		OrderingBOQueuePage.scrollDownToRequestQueue();
		OrderingBOQueuePage.validateClearFilters();
		OrderingBOQueuePage.standardView() ;
		OrderingBOQueuePage.searchOrigin();
		OrderingBOQueuePage.scrollDownToRequestQueue();
		OrderingBOQueuePage.bulkAction();
		OrderingBOQueuePage.bulkActionRelease();
		OrderingBOQueuePage.bulkAction();
		OrderingBOQueuePage.bulkActionAssignUser();
		OrderingBOQueuePage.bulkAction();
		OrderingBOQueuePage.bulkActionSendBack();
		OrderingBOQueuePage.bulkAction();
		OrderingBOQueuePage.bulkActionAssignProject();
		OrderingBOQueuePage.bulkAction();
		OrderingBOQueuePage.bulkActionChangeDueDate();
		
	}
	
	
	
	
	@Test(alwaysRun = true, dependsOnMethods = "bulkAction")
	@Documentation(step = "Test ends", expected = "Test has ended")
	public void testEnds() throws Exception {
		CommonPage.testEnded();
	}
}
