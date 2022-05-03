package com.element.fleet.ordering.regression;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.page.OrderingBOCodesTablesPage;
import com.element.fleet.ordering.page.OrderingLoginPage;
import com.ge.capital.rainbow.testng.Documentation;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

/**
 * @lastModifiedBy akandkonde
 */
public class Reg_Codes_Tables_Page_Add_Search_Sort_Edit extends BaseWebDriver {

	@BeforeClass
	@Parameters({"xcelerateURL", "applicationURL", "applicationBOURL", "orderingTestDataFilePath", "orderingCredentialDataFilePath" })
	public void instantiateVariables(String xcelerateURL, String applicationURL, String applicationBOURL, String orderingTestDataFilePath, String orderingCredentialDataFilePath,ITestContext context) throws Exception {
		CommonPage.loadCSVWithHeading(orderingTestDataFilePath,this.getClass().getSimpleName());
		CommonPage.loadCSVWithHeading(orderingCredentialDataFilePath);
		CommonPage.loadXMLParameterToTestData("XcelerateURL", xcelerateURL);
		CommonPage.loadXMLParameterToTestData("applicationURL", applicationURL);
		CommonPage.loadXMLParameterToTestData("applicationBOURL", applicationBOURL);
	}

	@Test(alwaysRun = true)
	@Parameters({ "applicationBOURL", "username" })
	@Documentation(step = "Open Back office page", expected = "Back office page should be opened")
	public void testLaunchBOURL(String applicationBOURL, String username) throws Exception {
		driver.get(applicationBOURL);
		driver.manage().window().maximize();
		OrderingLoginPage.waitForBOUserLoginPage();
		OrderingLoginPage.enterBOUsername(username);
		OrderingLoginPage.enterBOPassword(CommonPage.getCredetialsData(username));
	}

	@Test(dependsOnMethods = "testLaunchBOURL")
	@Documentation(step = "Click on Login button and verify code table title", expected = "Codes Tables page should be loaded")
	public void verifyCodesTablesPage() throws Exception {
		OrderingBOCodesTablesPage.clickOnCodesTables();
		OrderingBOCodesTablesPage.verifyCodesTablesTitleName();
	}
	
	@Test(dependsOnMethods = "verifyCodesTablesPage")
	@Documentation(step = "Click on Add New Type button and Fill all mandatory data", expected = "Application should be able to fill all mandatory data")
	public void verifyAddNewTypePageAndFillData() throws Exception {
		OrderingBOCodesTablesPage.clickOnAddNewTypeLink();
		OrderingBOCodesTablesPage.verifyAddNewTypeTitleName();
		OrderingBOCodesTablesPage.enterCode("S"+CommonPage.randomNumberInRange(999));
		OrderingBOCodesTablesPage.enterName("Name"+CommonPage.randomAlphaNumericString());
		OrderingBOCodesTablesPage.selectTypeSource("Title & License");
		OrderingBOCodesTablesPage.selectBusinessObject("Project");
		OrderingBOCodesTablesPage.clickOnDialects();
		OrderingBOCodesTablesPage.selectDialectsType("English");
		OrderingBOCodesTablesPage.enterInternalLabel("Internal"+CommonPage.randomAlphaNumericString());
		OrderingBOCodesTablesPage.enterExternalLabel("External"+CommonPage.randomAlphaNumericString());
	}
	
	@Test(dependsOnMethods = "verifyAddNewTypePageAndFillData")
	@Documentation(step = "Enter code, name, Type and Object and verify Type added properly", expected = "Application New Type data should be added properly")
	public void verifyDataForAddNewTypeWithSearchField() throws Exception {
		OrderingBOCodesTablesPage.clickOnSaveAndClose();
		OrderingBOCodesTablesPage.verifySearchedDataForColumn("Code");
		OrderingBOCodesTablesPage.verifySearchedDataForColumn("Name");
		OrderingBOCodesTablesPage.verifySearchedDataForColumn("Type Source");
		OrderingBOCodesTablesPage.verifySearchedDataForColumn("Business Object");
	}
	
	@Test(dependsOnMethods = "verifyDataForAddNewTypeWithSearchField")
	@Documentation(step = "Click on Edit link and update code name", expected = "Code name should be updated properly")
	public void verifyEditAddNewTypeWithEditLink() throws Exception {
		OrderingBOCodesTablesPage.EnterCodeInSearchAndclickOnEditLink();
		OrderingBOCodesTablesPage.enterName("Updated");
	}
	
	@Test(dependsOnMethods = "verifyEditAddNewTypeWithEditLink")
	@Documentation(step = "Click on Save button and Verify updated data with search field", expected = "Updated code name should be verified properly")
	public void verifyEditedDataWithSearchField() throws Exception {
		OrderingBOCodesTablesPage.clickOnSaveAndClose();
		OrderingBOCodesTablesPage.EnterNameInSearchAndVerifyUpdatedCodeName();
	}
	
	@Test(dependsOnMethods = "verifyEditedDataWithSearchField")
	@Documentation(step = "Click on each column header and verify column sorting", expected = "Ascending and Descending column sorting should be working properly")
	public void verifyAscAndDescOrderForAllColumn() throws Exception {
		OrderingBOCodesTablesPage.verifyColumnSortingFunction("Code", "Asc");
		OrderingBOCodesTablesPage.verifyColumnSortingFunction("Code", "Desc");
		OrderingBOCodesTablesPage.verifyColumnSortingFunction("Name", "Asc");
		OrderingBOCodesTablesPage.verifyColumnSortingFunction("Name", "Desc");
		OrderingBOCodesTablesPage.verifyColumnSortingFunction("TypeSource", "Asc");
		OrderingBOCodesTablesPage.verifyColumnSortingFunction("TypeSource", "Desc");
		OrderingBOCodesTablesPage.verifyColumnSortingFunction("BusinessObject", "Asc");
		OrderingBOCodesTablesPage.verifyColumnSortingFunction("BusinessObject", "Desc");
	}
	
	@Test(dependsOnMethods = "verifyAscAndDescOrderForAllColumn")
	@Documentation(step = "Click on plus symbol and verify Codes values table page", expected = "Codes values table should be loaded")
	public void verifyCodesValuesTablePage() throws Exception {
		OrderingBOCodesTablesPage.EnterCodeInSearchAndclickOnPlusSymbol();
		OrderingBOCodesTablesPage.waitForCodesValuesTable();
	}
	
	@Test(dependsOnMethods = "verifyCodesValuesTablePage")
	@Documentation(step = "Click on Add New Value link and fill all mandatory data", expected = "All mandatory data should be filled")
	public void verifyAddCodeValues() throws Exception {
		OrderingBOCodesTablesPage.clickOnAddNewValueLink();
		OrderingBOCodesTablesPage.enterSubCode("S"+CommonPage.randomNumberInRange(999));
		OrderingBOCodesTablesPage.enterSubName("SubN"+CommonPage.randomAlphaNumericString());
		OrderingBOCodesTablesPage.enterDescription();
		OrderingBOCodesTablesPage.selectEffectiveDate();
		OrderingBOCodesTablesPage.clickOnDialects();
		OrderingBOCodesTablesPage.selectDialectsSubType("French");
		OrderingBOCodesTablesPage.enterSubInternalLabel("Int"+CommonPage.randomAlphaNumericString());
		OrderingBOCodesTablesPage.enterSubExternalLabel("Ext"+CommonPage.randomAlphaNumericString());
		OrderingBOCodesTablesPage.clickOnSaveAndClose();
		OrderingBOCodesTablesPage.EnterCodeInSearchAndclickOnPlusSymbol();
		OrderingBOCodesTablesPage.waitForCodesValuesTable();
		OrderingBOCodesTablesPage.clickOnAddNewValueLink();
		OrderingBOCodesTablesPage.enterSubCode("S"+CommonPage.randomNumberInRange(999));
		OrderingBOCodesTablesPage.enterSubName("SubN"+CommonPage.randomAlphaNumericString());
		OrderingBOCodesTablesPage.enterDescription();
		OrderingBOCodesTablesPage.selectEffectiveDate();
		OrderingBOCodesTablesPage.clickOnDialects();
		OrderingBOCodesTablesPage.selectDialectsSubType("French");
		OrderingBOCodesTablesPage.enterSubInternalLabel("Int"+CommonPage.randomAlphaNumericString());
		OrderingBOCodesTablesPage.enterSubExternalLabel("Ext"+CommonPage.randomAlphaNumericString());
	}
	
	@Test(dependsOnMethods = "verifyAddCodeValues")
	@Documentation(step = "Verify new added code values in application", expected = "Code values should be verified properly")
	public void verifyAddCodeValuesWithSearchField() throws Exception {
		OrderingBOCodesTablesPage.clickOnSaveAndClose();
		OrderingBOCodesTablesPage.EnterCodeInSearchAndclickOnPlusSymbol();
		OrderingBOCodesTablesPage.verifySearchedDataForColumnForSubType("Code");
	}
	
	@Test(dependsOnMethods = "verifyAddCodeValuesWithSearchField")
	@Documentation(step = "Click on Edit link and update code value data", expected = "Application should be able to update code value data")
	public void verifyEditLinkValuesDataForCodeValues() throws Exception {
		OrderingBOCodesTablesPage.EnterCodeInSearchAndclickOnEditLinkSubType();
		OrderingBOCodesTablesPage.updateSubTypeName("Updated");
		OrderingBOCodesTablesPage.selectEffectiveDate();
	}
	
	@Test(dependsOnMethods = "verifyEditLinkValuesDataForCodeValues")
	@Documentation(step = "Verify edited data in code value data in code value table", expected = "Edited data should be verified in code value table")
	public void verifyEditedValuesDataWithSearchField() throws Exception {
		OrderingBOCodesTablesPage.clickOnSaveAndClose();
		OrderingBOCodesTablesPage.EnterCodeInSearchAndclickOnPlusSymbol();
		OrderingBOCodesTablesPage.EnterNameInSearchAndVerifyUpdatedCodeNameSubType();
	}
	
	@Test(dependsOnMethods = "verifyEditedValuesDataWithSearchField")
	@Documentation(step = "Verify ascending and descending column sorting for code value table", expected = "Ascending and Descending column sorting should be worked")
	public void verifyColumnSortingForCodeValuesTable() throws Exception {
		OrderingBOCodesTablesPage.EnterCodeInSearchAndclickOnPlusSymbol();
		OrderingBOCodesTablesPage.verifyColumnSortingFunctionForSubType("Code", "Asc");
		OrderingBOCodesTablesPage.verifyColumnSortingFunctionForSubType("Code", "Desc");
	}
}