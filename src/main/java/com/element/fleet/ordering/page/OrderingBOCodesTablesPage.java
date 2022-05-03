package com.element.fleet.ordering.page;

import com.element.fleet.ordering.commonutility.CommonPage;
import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebElement;
import com.element.fleet.ordering.enums.OrderingBOCodesTablesPageEnum;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserWait;

public class OrderingBOCodesTablesPage {
	static String codeNumber;
	static String codeNameStr;
	static String internalLabelStr;
	static String externalLabelStr;
	static String subCodeNameStr;
	static String subCodeNumber;
	static String intLabelStr;
	static String extLabelStr;
	
	public static void codesTablesPageLoaded() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_CODE_ID);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_NAME_ID);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_SOURCE_ID);
		}catch(Exception e) {
			throw new Exception("Unable to load Codes Tables page");
		}
	}
	
	public static void codesTablesPageLabelValidation() throws Exception {
		try {
			CommonPage.assertLabelHighlight(BrowserAccess.getElement(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_HEADING_XPATH), "Codes Table", "Manage Codes heading not matched with the expected string");
		}catch(Exception e) {
			throw new Exception("Unable to load Codes Tables page");
		}
	}

	public static void clickOnCodesTables() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_XPATH);
			BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_XPATH);
		}catch(Exception e) {
			throw new Exception("Unable to click on Codes Tables link");
		}
	}
	
	public static void verifyCodesTablesTitleName() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_TITLE_MESSAGE_XPATH);
			String titleHeading = BrowserAction.getElementText(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_TITLE_MESSAGE_XPATH).trim();
			assertEquals(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_TITLE_MESSAGE_VALUE.getValue().trim(), titleHeading, "Unable to match codes tables title message");
		}catch(Exception e) {
			throw new Exception("Unable to match codes tables title message");
		}
	}
	
	public static void clickOnAddNewTypeLink() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_XPATH);
			BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_XPATH);
		}catch(Exception e) {
			throw new Exception("Unable to click on Codes Tables link");
		}
	}
	
	public static void verifyAddNewTypeTitleName() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_TITLE_XPATH);
			String addNewTypeHeading = BrowserAction.getElementText(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_TITLE_XPATH).trim();
			assertEquals(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_TITLE_VALUE.getValue().trim(), addNewTypeHeading, "Unable to match add new type title message");
		}catch(Exception e) {
			throw new Exception("Unable to match add new type title message");
		}
	}

	public static void enterCode(String code) throws Exception {
		try {
			codeNumber = code;
			BrowserAction.enterFieldValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_CODE_ID, code);
			Thread.sleep(2000);
		}catch(Exception e) {
			throw new Exception("Unable to enter code in Code text field");
		}
	}
	
	public static void enterName(String codeName) throws Exception {
		try {
			codeNameStr = codeName; 
			BrowserAction.enterFieldValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_NAME_ID, codeName);
			codeNameStr = BrowserAction.getElementAttributeValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_NAME_ID, "value");
		}catch(Exception e) {
			throw new Exception("Unable to enter name in name text field");
		}
	}
	
	public static void selectTypeSource(String typeSource) throws Exception {
		try {
			BrowserAction.selectDropdownOptionByText(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_SOURCE_ID, typeSource);
		}catch(Exception e) {
			throw new Exception("Unable to select source type");
		}
	}
	
	public static void selectBusinessObject(String businessObject) throws Exception {
		try {
			BrowserAction.selectDropdownOptionByText(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_BUSINESS_OBJECT_ID, businessObject);
		}catch(Exception e) {
			throw new Exception("Unable to select business object");
		}
	}
	
	public static void clickOnDialects() throws Exception {
		try {
			BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_DIALECTS_XPATH);
		}catch(Exception e) {
			throw new Exception("Unable to click on dialects plus symbol");
		}
	}
	
	public static void selectDialectsType(String dailectsType) throws Exception {
		try {
			Thread.sleep(2000);
			BrowserAction.selectDropdownOptionByText(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_DIALECTS_ID, dailectsType);
		}catch(Exception e) {
			throw new Exception("Unable to select dialects type");
		}
	}
	
	public static void enterInternalLabel(String internalLabel) throws Exception {
		try {
			internalLabelStr = internalLabel;
			BrowserAction.enterFieldValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_INTERNAL_LABEL_ID, internalLabel);
		}catch(Exception e) {
			throw new Exception("Unable to enter internal label");
		}
	}
	
	public static void enterExternalLabel(String externalLabel) throws Exception {
		try {
			externalLabelStr = externalLabel;
			BrowserAction.enterFieldValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_EXTERNAL_LABEL_ID, externalLabel);
		}catch(Exception e) {
			throw new Exception("Unable to enter external label");
		}
	}
	
	public static void clickOnSaveAndClose() throws Exception {
		try {
			BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_TYPE_SAVE_CLOSE_XPATH);
			Thread.sleep(3000);
		}catch(Exception e) {
			throw new Exception("Unable to click on Save and Close");
		}
	}
	
	public static void enterSearchDataInSearchBox(String searchData) throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCH_XPATH);
			BrowserAction.clickandClear(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCH_XPATH);
			BrowserAction.enterFieldValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCH_XPATH, searchData);
			Thread.sleep(2000);
		}catch(Exception e) {
			throw new Exception("Unable to enter code in search text field");
		}
	}
	
	public static void verifySearchedDataForColumn(String column) throws Exception {
		try {
			List<WebElement> searchList = null;
			switch(column) {
			case "Code" :
				enterSearchDataInSearchBox(codeNumber);
				searchList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_CODE_LIST_XPATH);
				for(WebElement list : searchList) {
					if(!list.getText().equals(codeNumber))
						throw new Exception("Unable to find searched code in code column");
				}
				enterSearchDataInSearchBox("");
				break;
			case "Name" :
				enterSearchDataInSearchBox(codeNameStr);
				searchList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_NAME_LIST_XPATH);
				for(WebElement list : searchList) {
					if(!list.getText().equals(codeNameStr))
						throw new Exception("Unable to find searched name in name column");
				}
				enterSearchDataInSearchBox("");
				break;
			case "Type Source" :
				enterSearchDataInSearchBox("Title & License");
				searchList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_TYPE_SOURCE_LIST_XPATH);
				for(WebElement list : searchList) {
					if(!list.getText().equals("Title & License"))
						throw new Exception("Unable to find searched type source in Type Source column");
				}
				enterSearchDataInSearchBox("");
				break;
			case "Business Object" :
				enterSearchDataInSearchBox("Project");
				searchList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_BUSINESS_OBJECT_LIST_XPATH);
				for(WebElement list : searchList) {
					if(!(list.getText().equals("Project") || list.getText().equals("Workflow")))
						throw new Exception("Unable to find searched project in Project column");
				}
				enterSearchDataInSearchBox("");
				break;
			}
		}catch(Exception e) {
			throw new Exception("Unable to find searched data in "+column);
		}
	}
	
	public static void verifyColumnSortingFunction(String column, String order) throws Exception {
		switch(column) {
		case "Code" :
			sortingForCodeColumn(column, order);
			break;
		case "Name" :
			sortingForNameColumn(column, order);
			break;
		case "TypeSource" :
			sortingForTypeSourceColumn(column, order);
			break;
		case "BusinessObject" :
			sortingForBusinessObjectColumn(column, order);
			break;
		}
	}
	
	public static void sortingForCodeColumn(String column, String order) throws Exception {
		List<WebElement> columnDataList = null;
		ArrayList<String> beforeList = null;
		ArrayList<String> afterList = null;
		try {
			switch(order) {
			case "Asc":
				beforeList = new ArrayList<String>();
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_CODE_LIST_XPATH);
				for(WebElement list : columnDataList)
					beforeList.add(list.getText());
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_CODE_LIST_XPATH);
				afterList = new ArrayList<String>();
				for(WebElement list : columnDataList)
					afterList.add(list.getText());
				if(!afterList.equals(beforeList))
					throw new Exception("Columns data not matching");
				break;
			case "Desc":
				beforeList = new ArrayList<String>();
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_CODE_LIST_XPATH);
				for(WebElement list : columnDataList)
					beforeList.add(list.getText());
				Collections.reverse(beforeList);
				BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_CODE_COLUMN_XPATH);
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_CODE_LIST_XPATH);
				afterList = new ArrayList<String>();
				for(WebElement list : columnDataList)
					afterList.add(list.getText());
				if(!afterList.equals(beforeList))
					throw new Exception("Columns data not matching");
				break;
			}
		}catch(Exception e) {
			throw new Exception(order+" Sorting functionality is not working for "+column);
		}
	}
	
	public static void sortingForNameColumn(String column, String order) throws Exception {
		List<WebElement> columnDataList = null;
		ArrayList<String> beforeList = null;
		ArrayList<String> afterList = null;
		try {
			switch(order) {
			case "Asc":
				beforeList = new ArrayList<String>();
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_NAME_LIST_XPATH);
				for(WebElement list : columnDataList)
					beforeList.add(list.getText());
				Collections.sort(beforeList);
				BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_NAME_COLUMN_XPATH);
				Thread.sleep(2000);
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_NAME_LIST_XPATH);
				afterList = new ArrayList<String>();
				for(WebElement list : columnDataList)
					afterList.add(list.getText());
				Collections.sort(afterList);
				if(!afterList.equals(beforeList))
					throw new Exception("Columns data not matching");
				break;
			case "Desc":
				beforeList = new ArrayList<String>();
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_NAME_LIST_XPATH);
				for(WebElement list : columnDataList)
					beforeList.add(list.getText());
				Collections.reverse(beforeList);
				BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_NAME_COLUMN_XPATH);
				Thread.sleep(2000);
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_NAME_LIST_XPATH);
				afterList = new ArrayList<String>();
				for(WebElement list : columnDataList)
					afterList.add(list.getText());
				if(!afterList.equals(beforeList))
					throw new Exception("Columns data not matching");
				break;
			}
		}catch(Exception e) {
			throw new Exception(order+" Sorting functionality is not working for "+column);
		}
	}

	public static void sortingForTypeSourceColumn(String column, String order) throws Exception {
		List<WebElement> columnDataList = null;
		ArrayList<String> beforeList = null;
		ArrayList<String> afterList = null;
		try {
			switch(order) {
			case "Asc":
				beforeList = new ArrayList<String>();
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_TYPE_SOURCE_LIST_XPATH);
				for(WebElement list : columnDataList)
					beforeList.add(list.getText());
				Collections.sort(beforeList);
				BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_TYPE_SOURCE_COLUMN_XPATH);
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_TYPE_SOURCE_LIST_XPATH);
				afterList = new ArrayList<String>();
				for(WebElement list : columnDataList)
					afterList.add(list.getText());
				if(!afterList.equals(beforeList))
					throw new Exception("Columns data not matching");
				break;
			case "Desc":
				beforeList = new ArrayList<String>();
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_TYPE_SOURCE_LIST_XPATH);
				for(WebElement list : columnDataList)
					beforeList.add(list.getText());
				Collections.reverse(beforeList);
				BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_TYPE_SOURCE_COLUMN_XPATH);
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_TYPE_SOURCE_LIST_XPATH);
				afterList = new ArrayList<String>();
				for(WebElement list : columnDataList)
					afterList.add(list.getText());
				if(!afterList.equals(beforeList))
					throw new Exception("Columns data not matching");
				break;
			}
		}catch(Exception e) {
			throw new Exception(order+" Sorting functionality is not working for "+column);
		}
	}

	public static void sortingForBusinessObjectColumn(String column, String order) throws Exception {
		List<WebElement> columnDataList = null;
		ArrayList<String> beforeList = null;
		ArrayList<String> afterList = null;
		try {
			switch(order) {
			case "Asc":
				beforeList = new ArrayList<String>();
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_BUSINESS_OBJECT_LIST_XPATH);
				for(WebElement list : columnDataList)
					beforeList.add(list.getText());
				Collections.sort(beforeList);
				BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_BUSINESS_OBJECT_COLUMN_XPATH);
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_BUSINESS_OBJECT_LIST_XPATH);
				afterList = new ArrayList<String>();
				for(WebElement list : columnDataList)
					afterList.add(list.getText());
				if(!afterList.equals(beforeList))
					throw new Exception("Columns data not matching");
				break;
			case "Desc":
				beforeList = new ArrayList<String>();
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_BUSINESS_OBJECT_LIST_XPATH);
				for(WebElement list : columnDataList)
					beforeList.add(list.getText());
				Collections.reverse(beforeList);
				BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_BUSINESS_OBJECT_COLUMN_XPATH);
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_BUSINESS_OBJECT_LIST_XPATH);
				afterList = new ArrayList<String>();
				for(WebElement list : columnDataList)
					afterList.add(list.getText());
				if(!afterList.equals(beforeList))
					throw new Exception("Columns data not matching");
				break;
			}
		}catch(Exception e) {
			throw new Exception(order+" Sorting functionality is not working for "+column);
		}
	}
	
	public static void EnterCodeInSearchAndclickOnEditLink() throws Exception {
		try {
			enterSearchDataInSearchBox(codeNumber);
			BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_EDIT_LINK_XPATH);
		}catch(Exception e) {
			throw new Exception("Unable to click on Edit link");
		}
	}
	
	public static void EnterNameInSearchAndVerifyUpdatedCodeName() throws Exception {
		try {
			enterSearchDataInSearchBox(codeNameStr);
			List<WebElement> list = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_NAME_LIST_XPATH);
			for(WebElement str : list) {
				if(!str.getText().trim().equals(codeNameStr.trim()))
					throw new Exception("Unable to match updated code name");
			}
			enterSearchDataInSearchBox("Project");
		}catch(Exception e) {
			throw new Exception("Unable to match updated code name");
		}
	}
	
	public static void EnterCodeInSearchAndclickOnPlusSymbol() throws Exception {
		try {
			enterSearchDataInSearchBox(codeNumber);
			BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_PLUS_SYMBOL_XPATH);
			Thread.sleep(5000);
		}catch(Exception e) {
			throw new Exception("Unable to click on Plus Symbol");
		}
	}
	
	public static void waitForCodesValuesTable() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_CODE_VALUES_TABLE_XPATH);
			Thread.sleep(3000);
		}catch(Exception e) {
			throw new Exception("Unable to load Code Value Table");
		}
	}
	
	public static void clickOnAddNewValueLink() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_VALUE_XPATH);
			BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_ADD_NEW_VALUE_XPATH);
			Thread.sleep(3000);
		}catch(Exception e) {
			throw new Exception("Unable to click on Add New Value link");
		}
	}
	
	public static void enterSubCode(String subCode) throws Exception {
		try {
			subCodeNumber = subCode;
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_ADD_NEW_VALUE_CODE_XPATH);
			BrowserAction.enterFieldValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_ADD_NEW_VALUE_CODE_XPATH, subCode);
			Thread.sleep(2000);
		}catch(Exception e) {
			throw new Exception("Unable to enter sub code in Code text field");
		}
	}
	
	public static void enterSubName(String subCodeName) throws Exception {
		try {
			subCodeNameStr = subCodeName; 
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_ADD_NEW_VALUE_NAME_XPATH);
			BrowserAction.enterFieldValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_ADD_NEW_VALUE_NAME_XPATH, subCodeName);
			subCodeNameStr = BrowserAction.getElementAttributeValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_ADD_NEW_VALUE_NAME_XPATH, "value");
		}catch(Exception e) {
			throw new Exception("Unable to enter name in name text field");
		}
	}
	
	public static void selectEffectiveDate() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_VALUE_EFFECTIVE_DATE_CAL_XPATH);
			BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_VALUE_EFFECTIVE_DATE_CAL_XPATH);
			Thread.sleep(3000);
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_VALUE_EFFECTIVE_DATE_XPATH);
			BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_VALUE_EFFECTIVE_DATE_XPATH);
		}catch(Exception e) {
			throw new Exception("Unable to select sub type effective date");
		}
	}
	
	public static void enterDescription() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_VALUE_DESCRIPTION_ID);
			BrowserAction.enterFieldValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_VALUE_DESCRIPTION_ID, "Testing Code Value");
		}catch(Exception e) {
			throw new Exception("Unable to enter sub type description");
		}
	}
	
	public static void selectDialectsSubType(String dailectsType) throws Exception {
		try {
			Thread.sleep(2000);
			BrowserAction.selectDropdownOptionByText(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_TYPE_DIALECTS_SUB_TYPE_XPATH, dailectsType);
		}catch(Exception e) {
			throw new Exception("Unable to select dialects sub type");
		}
	}
	
	public static void enterSubInternalLabel(String internalLabel) throws Exception {
		try {
			intLabelStr = internalLabel;
			BrowserAction.enterFieldValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_TYPE_SUB_INTERNAL_LABEL_XPATH, internalLabel);
		}catch(Exception e) {
			throw new Exception("Unable to enter sub type internal label");
		}
	}
	
	public static void enterSubExternalLabel(String externalLabel) throws Exception {
		try {
			extLabelStr = externalLabel;
			BrowserAction.enterFieldValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_TYPE_SUB_EXTERNAL_LABEL_XPATH, externalLabel);
		}catch(Exception e) {
			throw new Exception("Unable to enter sub type external label");
		}
	}
	
	public static void enterSubSearchDataInSearchBox(String searchData) throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_TYPE_SUB_SEARCH_XPATH);
			BrowserAction.clickandClear(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_TYPE_SUB_SEARCH_XPATH);
			BrowserAction.enterFieldValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_TYPE_SUB_SEARCH_XPATH, searchData);
			Thread.sleep(3000);
		}catch(Exception e) {
			throw new Exception("Unable to enter code in sub type search text field");
		}
	}
	
	public static void verifySearchedDataForColumnForSubType(String column) throws Exception {
		try {
			List<WebElement> searchList = null;
			switch(column) {
			case "Code" :
				enterSubSearchDataInSearchBox(subCodeNumber);
				searchList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_CODE_LIST_SUB_TYPE_XPATH);
				for(WebElement list : searchList) {
					if(!(list.getText().equals(subCodeNumber) || list.getText().equals("")))
						throw new Exception("Unable to find searched code in code column for sub type");
				}
				Thread.sleep(3000);
				break;
			}
		}catch(Exception e) {
			throw new Exception("Unable to find searched data in "+column+" for sub type");
		}
	}
	
	public static void EnterCodeInSearchAndclickOnEditLinkSubType() throws Exception {
		try {
			BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_EDIT_LINK_SUB_TYPE_XPATH);
			Thread.sleep(3000);
		}catch(Exception e) {
			throw new Exception("Unable to click on Edit link for sub type");
		}
	}
	
	public static void updateSubTypeName(String codeName) throws Exception {
		try {
			subCodeNameStr = codeName; 
			BrowserAction.enterFieldValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_ADD_NEW_VALUE_NAME_XPATH, codeName);
			subCodeNameStr = BrowserAction.getElementAttributeValue(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_ADD_NEW_VALUE_NAME_XPATH, "value");
			Thread.sleep(3000);
		}catch(Exception e) {
			throw new Exception("Unable to enter name in name text field for sub type");
		}
	}
	
	public static void EnterNameInSearchAndVerifyUpdatedCodeNameSubType() throws Exception {
		try {
			enterSubSearchDataInSearchBox(subCodeNameStr);
			List<WebElement> list = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_NAME_LIST_SUB_TYPE_XPATH);
			for(WebElement str : list) {
				if(!(str.getText().trim().equals(subCodeNameStr.trim()) || str.getText().equals("")))
					throw new Exception("Unable to match updated code name for sub type");
			}
			BrowserAction.refresh();
			Thread.sleep(3000);
		}catch(Exception e) {
			throw new Exception("Unable to match updated code name for sub type");
		}
	}
	
	public static void verifyColumnSortingFunctionForSubType(String column, String order) throws Exception {
		switch(column) {
		case "Code" :
			sortingForCodeColumnForSubType(column, order);
			break;
		}
	}
	
	public static void sortingForCodeColumnForSubType(String column, String order) throws Exception {
		List<WebElement> columnDataList = null;
		ArrayList<String> beforeList = null;
		ArrayList<String> afterList = null;
		try {
			switch(order) {
			case "Asc":
				beforeList = new ArrayList<String>();
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_CODE_LIST_SUB_TYPE_XPATH);
				for(WebElement list : columnDataList)
					beforeList.add(list.getText());
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_CODE_LIST_SUB_TYPE_XPATH);
				afterList = new ArrayList<String>();
				for(WebElement list : columnDataList)
					afterList.add(list.getText());
				if(!afterList.equals(beforeList))
					throw new Exception("Columns data not matching for sub type");
				break;
			case "Desc":
				beforeList = new ArrayList<String>();
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_CODE_LIST_SUB_TYPE_XPATH);
				for(WebElement list : columnDataList)
					beforeList.add(list.getText());
				Collections.reverse(beforeList);
				BrowserAction.click(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_CODE_HEADER_SUB_TYPE_XPATH);
				columnDataList = BrowserAction.getElements(OrderingBOCodesTablesPageEnum.ORDERING_BO_CT_CODES_TABLE_SEARCHED_CODE_LIST_SUB_TYPE_XPATH);
				afterList = new ArrayList<String>();
				for(WebElement list : columnDataList)
					afterList.add(list.getText());
				if(!afterList.equals(beforeList))
					throw new Exception("Columns data not matching for sub type");
				break;
			}
		}catch(Exception e) {
			throw new Exception(order+" Sorting functionality is not working for "+column+" for sub type");
		}
	}
}
