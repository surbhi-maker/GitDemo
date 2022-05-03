package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOManagerOrderPreferencesPage;
import com.element.fleet.ordering.page.OrderingHomePage;
import com.element.fleet.ordering.page.OrderingManagerApprovalMaintenancePage;
import com.element.fleet.ordering.page.OrderingManagerApprovalQueuePage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/*
 * PreCondition: Client 1750 should exist
 * Year: 2020
 * Make: Ford
 * Manufacturer: Ford
 * Approval Type: Sequential
 * Note: Approval rule Ford_2020
 * @lastModifiedBy Hector_Jimenez 
 */
public class Reg_MLO_MAM_Approver_Validation_Sequential_Non_Sequential extends BaseWebDriver {
	
	@BeforeClass
	@Parameters({ "xcelerateURL", "applicationURL", "applicationBOURL", "username", "orderingTestDataFilePath",
			"orderingCredentialDataFilePath", "waitTime" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String username,
			String orderingTestDataFilePath, String orderingCredentialDataFilePath, String waitTime,
			ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath, this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("ApplicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("ApplicationBOURL", applicationBOURL);
		CommonPage.loadXMLParameterToTestData("Username", username);
		CommonPage.loadXMLParameterToTestData("WaitTime", waitTime);
	}
	
	@Test(alwaysRun = true)
	@Documentation(step = "Create order with logger user", expected = "Orders with logger user is created and selects client from CSV file")
	public void createOrdersWithLoggerUser() throws Throwable {
		OrderingBOManagerOrderPreferencesPage.createOrderSkipBilling(this.getClass().getSimpleName());
	}

	@Test(dependsOnMethods = "createOrdersWithLoggerUser")
	@Documentation(step = "Go to Manager Approval Maintenance screen", expected = "Manager Approval Maintenance should display")
	public void closeSummaryPageAndGoToMyMAM() throws Throwable {
		OrderingHomePage.selectSideMenuOption("Ordering");
		OrderingManagerApprovalQueuePage.orderingSideMenuOptionLoadedExternalUser();
		OrderingHomePage.selectOrderingMenuOption("Manager Approval Maintenance");
		OrderingManagerApprovalQueuePage.waitForManagerApprovalQueueToLoad();
	}

	@Test(dependsOnMethods = "closeSummaryPageAndGoToMyMAM")
	@Documentation(step = "Verifies table columns are present and spelled correclty", expected ="All headers from MAM queue screen should be present and spelled correctly ORD-22154 and ORD-22155") 
	public void verifyTableheadersAndLogNumberInMAMQueueScreen() throws Throwable {
	  OrderingManagerApprovalMaintenancePage.verifyTableHeadersTextFromMAMQueueTable();
	  OrderingManagerApprovalMaintenancePage.enterLogNumberOnMAMQueueScreen(CommonPage.getTestData("LoggerLogNumber" + 1));
	  OrderingManagerApprovalMaintenancePage.clickSearchButtonInMAMQueueScreen();
	  OrderingManagerApprovalMaintenancePage.verifyLogNumberInMAMQueueScreen(CommonPage.getTestData("LoggerLogNumber" + 1)); 
	  }

	@Test(dependsOnMethods = "verifyTableheadersAndLogNumberInMAMQueueScreen")
	@Documentation(step = "Verify user is able to navigate to Manager Approval Maintenance Screen, ORD-22167, ORD-22128", expected = "Manager Approval Maintenance should display")
	public void verifyUserCanNavigateToMAMApproversScreen() throws Throwable {
		OrderingManagerApprovalMaintenancePage.clickOnTheFirstRecord();
	}

	@Test(dependsOnMethods = "verifyUserCanNavigateToMAMApproversScreen")
	@Documentation(step = "Verify order is not saved when leaving without saving, ORD-22121", expected = "Approval type and status should not update")
	public void verifyLeaveWithoutSaving() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyValuesDidNotUpdate(CommonPage.getTestData("LoggerLogNumber" + 1));
	}

	@Test(dependsOnMethods = "verifyLeaveWithoutSaving")
	@Documentation(step = "Verifies Priority is sequential, it also verifies for non duplicates, ORD-22166, ORD-22164, ORD-22144, ORD-22119, ORD-22179", expected = "Priority should be displayed in seq order  1,2,3,4...")
	public void verifyPriorityInSequentialOrder() throws Throwable {
		OrderingManagerApprovalMaintenancePage.changeApprovalType("Sequential");
		OrderingManagerApprovalMaintenancePage.verifyIfPriorityDisplayInSequentialOrder();
	}

	@Test(dependsOnMethods = "verifyPriorityInSequentialOrder")
	@Documentation(step = "Verifies status when approval type is sequential, ORD-22171, , ORD-22179", expected = "First approval status should be Active and rest Pending")
	public void verifyFirstRecordIsActiveAndRestPending() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyApproveStatusInSequentialOrder();
	}

	@Test(dependsOnMethods = "verifyFirstRecordIsActiveAndRestPending")
	@Documentation(step = "Verify for Non-Sequential Approvers, System should not be display pending as Status, ORD-22169, , ORD-22179", expected = "All status should be active")
	public void verifyNonSequentialApproversStatus() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyApproveStatusForNonSequentialApprovers();
	}

	@Test(dependsOnMethods = "verifyNonSequentialApproversStatus")
	@Documentation(step = "Verify for Non-Sequential Approvers, Priority is always 1, ORD-22163, , ORD-22179", expected = "Priority should always be 1")
	public void verifyNonSequentialApproversPriority() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyPriorityForNonSequentialApprovers();
	}

	@Test(dependsOnMethods = "verifyNonSequentialApproversPriority")
	@Documentation(step = "Verify details on MAM approvers page, ORD-22158, ORD-22137", expected = "Page contains expected labels, buttons and table headers")
	public void verifyDetailsOfMAMScreen() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyDetailsOfMAMApproversScreen();
	}

	@Test(dependsOnMethods = "verifyDetailsOfMAMScreen")
	@Documentation(step = "Verify when user selected order for edit  then the fields - Log number/Corp/Fleet/Unit cant be changed/non-editable, ORD-22182", expected = "Log number/Corp/Fleet/Unit should be non-editable")
	public void verifyMAMApproverScreenLabelsNotEditable() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyLabelsAreNotEditable();
	}

	@Test(dependsOnMethods = "verifyMAMApproverScreenLabelsNotEditable")
	@Documentation(step = "Verifies bulk action does not contain a remove/delete action, ORD-22177", expected = "there should not be a remove/delete action in the bulk action area")
	public void verifyBulkSectionAreaNotContainsDeleteAction() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyThereIsNoDeleteActionInBulkSection();
	}

	@Test(dependsOnMethods = "verifyBulkSectionAreaNotContainsDeleteAction")
	@Documentation(step = "Verifies multiple approval is not posible if it is not sequential, ORD-22139", expected = "Multiple approval should be sequential only")
	public void verifyMultipleApprovalIsSequential() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyMultipleApprovalFailsIfSelectionIsNotSequential();
	}

	@Test(dependsOnMethods = "verifyMultipleApprovalIsSequential")
	@Documentation(step = "Verifies the values for state dropdown, ORD-22109", expected = "Active, Pending and Cancelled should be the 3 values")
	public void verifyValuesInStatusDropDownMAMScreen() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyDropdown3Values();
	}

	@Test(dependsOnMethods = "verifyValuesInStatusDropDownMAMScreen")
	@Documentation(step = "Verifies subscription/internal switch are read only, ORD-22150", expected = "Switches should be read-only")
	public void verifySwitchesAreReadOnly() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifySwitchesAreReadOnly();
	}

	@Test(dependsOnMethods = "verifySwitchesAreReadOnly")
	@Documentation(step = "Verifies the type of users, ORD-22143", expected = "Users should be external or internal")
	public void verifyUserType() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyIfUserIsExternalOrInternal();
	}
	
	@Test(dependsOnMethods = "verifyUserType")
	@Documentation(step = "Verifies if subscription switch is green color when on, ORD-22143", expected = "subscription switch should be green color")
	public void verifySubscriptionSwitch() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyIfSubscriptionIsOnOff();
	}

	@Test(dependsOnMethods = "verifySubscriptionSwitch")
	@Documentation(step = "Verifies users after changing approval type, ORD-22116", expected = "Users should display without changes regardless the approving type")
	public void verifyUsersDisplayCorrectlyAfterChangingApprovalType() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyUsersAreDisplayedAfterChangingApprovalType();
	}
	
	@Test(dependsOnMethods = "verifyUsersDisplayCorrectlyAfterChangingApprovalType")
	@Documentation(step ="Verifies after completing first approval, next approver status gets active, ORD-22138", expected ="Next approver status should change from pending to active after first approver is completed" )
	public void verifyStatusChangesFromPendingToActive() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyApproveCompletedNextApprovalIsActive(); 
	}

	@Test(dependsOnMethods = "verifyStatusChangesFromPendingToActive")
	@Documentation(step = "Verifies user cannot select a date shorter than 3 days from now, ORD-22126", expected = "3 days from now should be disabled")
	public void verifyUserCanSelectAValideDateFromNow() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyUserCanSelectDateAfter3DaysFromCurrentDate();
	}

	@Test(dependsOnMethods = "verifyUserCanSelectAValideDateFromNow")
	@Documentation(step = "Verifies user can change the date of second approver and see the changes, ORD-22136", expected = "User should be able to change date")
	public void verifyUserCanChangeApprovaDate() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyUserCanChangeApprovalDate();
	}

	@Test(dependsOnMethods = "verifyUserCanChangeApprovaDate")
	@Documentation(step = "This methods verifies that if user selects 'NO', in the confirmation popup, records are not approved, ORD-22117", expected = "Records should not get approved after rejecting confirmation popup")
	public static void verifyUserCanDeclineApprovingConfirmation() throws Throwable {
		OrderingManagerApprovalMaintenancePage.verifyRecordsAreNotApproved();
	}

	@Test(dependsOnMethods = "verifyUserCanDeclineApprovingConfirmation")
	@Documentation(step = "This methods verifies that after approval has been completed user cannot edit the fields, ORD-22159", expected = "User should not be able to edit after completion")
	public static void verifyUserNotAbleToEditAfterCompleteApproval() throws Throwable {
		OrderingManagerApprovalMaintenancePage.approveAllExpectedApprovers();
		OrderingManagerApprovalMaintenancePage.verifyUserIsNotAbleToEditDetailsAfterCompletedApproval();
	}

	@Test(dependsOnMethods = "verifyUserNotAbleToEditAfterCompleteApproval")
	@Documentation(step = "This methods saves approvals and verifies it was saved, ORD-22160, ORD-22202", expected = "System should save the changes done, Log number should match")
	public static void verifySystemSaveChanges() throws Exception {
		OrderingManagerApprovalMaintenancePage.verifySystemSaveChanges(CommonPage.getTestData("LoggerLogNumber" + 1));
	}

	@Test(dependsOnMethods = "verifySystemSaveChanges")
	@Documentation(step = "Click on LogOut button and verify logout message", expected = "Application should be logged out properly")
	public void verifySuperUserLogOut() throws Exception {
		OrderingManagerApprovalMaintenancePage.clickOnBackButton();
		OrderingHomePage.verifyLogOutFunctionality();
	}

	@Test (alwaysRun = true, dependsOnMethods = "verifySuperUserLogOut" )
	@Documentation(step = "This Method ends the test", expected = "Test should be ended")
	public static void endTest() throws Exception {
		CommonPage.testEnded();
	}
	
}