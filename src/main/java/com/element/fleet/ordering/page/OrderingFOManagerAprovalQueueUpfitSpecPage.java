package com.element.fleet.ordering.page;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.element.fleet.ordering.enums.OrderingFOManagerAprovalQueueUpfitSpecPageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.ge.capital.rainbow.browser.BrowserAction;

public class OrderingFOManagerAprovalQueueUpfitSpecPage {
	private static List<String> checkedColumnsList;
	private static List<String> visibleColumnsList;
	
	public static void setVisibleColumnHeadingsFromTable() throws Exception {
		List<WebElement> columnHeadingsFromWIPTable = BrowserAction.getElements(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TABLE_HEADING_ROWS_XPATH);
		visibleColumnsList = new ArrayList<>();
		columnHeadingsFromWIPTable.stream().filter(e->!e.getText().equals("")).forEach(e->visibleColumnsList.add(e.getText().replaceAll("\n", " ")));
	}
	
	public static void compareSelectedAndVisibleColumns() throws Exception {
		Assert.assertTrue(visibleColumnsList.containsAll(checkedColumnsList), "Selected and visible columns are not same");
	}
	
	public static void setCheckedColumns() throws Exception {
		List<WebElement> columnsList = BrowserAction.getElements(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTIONS_XPATH);
		checkedColumnsList = new ArrayList<>();
		for(WebElement columnsOption: columnsList) {
			if(columnsOption.getAttribute("class").equals("active")?true:false){
				checkedColumnsList.add(columnsOption.getText());
			}
		}
	}
	
	public static void clickToggleColumnsButton() throws Exception {
		BrowserAction.getElement(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_BUTTON_CSS).click();
	}
	
	public static void clickRequiredColumnOption(String column) throws Exception {
		WebElement containerWebElement = BrowserAction.getElement(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTIONS_CONTAINER_UL_XPATH);
		switch(column) {
			case "Upfit Spec ID":containerWebElement.findElement(By.xpath(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_UPFITSPEC_ID_XPATH.getValue())).click();
				break;
			case "Spec Name":containerWebElement.findElement(By.xpath(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_SPECNAME_XPATH.getValue())).click();
				break;
			case "Client Number":containerWebElement.findElement(By.xpath(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_CLIENT_XPATH.getValue())).click();
				break;
			case "Spec Type Name":containerWebElement.findElement(By.xpath(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_SPECTYPENAME_XPATH.getValue())).click();
				break;
			case "Internal Ind":containerWebElement.findElement(By.xpath(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_INTERNALIND_XPATH.getValue())).click();
				break;
			case "Auto Ind":containerWebElement.findElement(By.xpath(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_AUTOIND_XPATH.getValue())).click();
				break;
			case "priority":containerWebElement.findElement(By.xpath(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_PRIORITY_XPATH.getValue())).click();
				break;
			case "Expect Date":containerWebElement.findElement(By.xpath(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_EXPECTDATE_XPATH.getValue())).click();
				break;
			case "Created by User":containerWebElement.findElement(By.xpath(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_CREATEDBYUSER_XPATH.getValue())).click();
				break;
			case "Created by Programfacturer":containerWebElement.findElement(By.xpath(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_CREATEBYPROG_XPATH.getValue())).click();
				break;
			case "Created on Date":containerWebElement.findElement(By.xpath(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_CREATEBYDATE_XPATH.getValue())).click();
				break;
			case "Updated by User":containerWebElement.findElement(By.xpath(OrderingFOManagerAprovalQueueUpfitSpecPageEnum.ORDERING_MANAGER_APPROVAL_UPFITSPEC_TOGGLE_OPTION_UPDATEDBYUSER_XPATH.getValue())).click();
				break;	
			default: throw new InvalidSwitchCaseException(column+" is a invalid option");
		}
	}
}
