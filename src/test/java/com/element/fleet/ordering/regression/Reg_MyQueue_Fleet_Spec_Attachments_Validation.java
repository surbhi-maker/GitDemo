package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.element.fleet.ordering.page.OrderingVehicleConfigPriceAndConfigPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy shiamsr
 */
public class Reg_MyQueue_Fleet_Spec_Attachments_Validation  extends BaseWebDriver {
	
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
	@Documentation(step = "log in to FO and change client", expected = "FO Home page should display")
	public void logInToFO() throws Throwable {
		OrderingLoginPage.openFOApplication();
		OrderingHomePage.waitForHomePage();
		OrderingHomePage.clickClientBreakdown(); 
		OrderingHomePage.changeClient();
	}
	
	@Test(dependsOnMethods = "logInToFO")
	@Documentation(step = "Create Fleet Spec with a pending approval status", expected = "Fleet Spec Should be Created")
	public void createFleetSpec() throws Throwable {
		int specCount = Integer.parseInt(CommonPage.getTestData("SpecCount"));
		OrderingVehicleConfigPriceAndConfigPage.addNewSpec("Add Fleet Specification", specCount);
		OrderingVehicleConfigPriceAndConfigPage.changeFleetSpecsToPendingApproval("Fleet Spec Name");
	}
	
	@Test(dependsOnMethods = "createFleetSpec")
	@Documentation(step = "Validate Maximum number of attachments", expected = "Error should be displayed when selecting more than 10 attachments")
	public void validateMaxNumberOfAttachments() throws Throwable {
		OrderingHomePage.goBackToHomePage();
		OrderingHomePage.clickFleetSpecTab();
		OrderingHomePage.searchFleetSpecName(CommonPage.getTestData("SpecName"));
		OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
		OrderingHomePage.verfiyMaxNumberOfAttachments();
	}
	
	@Test(dependsOnMethods = "validateMaxNumberOfAttachments")
	@Documentation(step = "Validate Modal shows error message with invalid file", expected = "Error should be displayed when selecting an invalid file")
	public void validateInvalidAttachmentFile() throws Throwable {
		OrderingHomePage.searchFleetSpecName(CommonPage.getTestData("SpecName"));
		OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
		OrderingHomePage.verifyInvalidFilesErrorInModal();
	}
	
	@Test(dependsOnMethods = "validateInvalidAttachmentFile")
	@Documentation(step = "Validate Attachment can be deleted from modal", expected = "Attachment should be removed from modal")
	public void validateAttachmentCanBeRemoved() throws Throwable {
		OrderingHomePage.searchFleetSpecName(CommonPage.getTestData("SpecName"));
		OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
		OrderingHomePage.verifyAttachmentCanBeRemovedFromModal();
	}
	
	@Test(dependsOnMethods = "validateAttachmentCanBeRemoved")
	@Documentation(step = "Decline Created Spec without an attachment", expected = "fleet spec should be declined")
	public void declineFleetSpecWithoutAttachment() throws Throwable {
		OrderingHomePage.clickFleetSpecTab();
		OrderingHomePage.searchFleetSpecName(CommonPage.getTestData("SpecName"));
		OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
		OrderingHomePage.rejectSingleSpecWithoutAttachment(); 
		OrderingHomePage.verifyFleetSpecIsNotDisplayed(CommonPage.getTestData("SpecName"));
	}
	
	@Test(dependsOnMethods = "declineFleetSpecWithoutAttachment")
	@Documentation(step = "Decline Fleet Spec With Multiple Attachments", expected = "Fleet Spec Should be declined")
	public void declineFleetSpecWithMultipleAttachments() throws Throwable {
		int specCount = Integer.parseInt(CommonPage.getTestData("SpecCount"));
		OrderingVehicleConfigPriceAndConfigPage.addNewSpec("Add Fleet Specification", specCount);
		OrderingVehicleConfigPriceAndConfigPage.changeFleetSpecsToPendingApproval("Fleet Spec Name");
		OrderingHomePage.goBackToHomePage();
		OrderingHomePage.clickFleetSpecTab();
		OrderingHomePage.searchFleetSpecName(CommonPage.getTestData("SpecName"));
		OrderingVehicleConfigPriceAndConfigPage.clickOnSpecAction();
		OrderingHomePage.rejectSpecWithMultipleAttachments();
		OrderingHomePage.verifyFleetSpecIsNotDisplayed(CommonPage.getTestData("SpecName"));
	}
	
	@Test (alwaysRun = true, dependsOnMethods = "declineFleetSpecWithMultipleAttachments" )
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		OrderingHomePage.verifyLogOutFunctionality();
		CommonPage.testEnded();
	}
}
