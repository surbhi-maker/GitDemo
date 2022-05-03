package com.element.fleet.ordering.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOCreditQueuePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.ge.capital.rainbow.browser.BrowserAccess;
import com.ge.capital.rainbow.browser.BrowserWait;

public class OrderingBOCreditQueuePage {

	/**
	 * This method verifies correct search fields are present and displayed
	 * @param searchType Search Type, Advanced/Standard
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void verifySearchFieldsArePresent(String searchType) throws Exception {
		List<String> expectedSearchFieldNames = null;
		List<WebElement> searchFieldElementList = null;
		switch (searchType) {
			case "Standard":
				expectedSearchFieldNames = Arrays.asList(CommonPage.getTestData("CustomColumn2").split("\\|"));
				searchFieldElementList = BrowserAccess.getElements(OrderingBOCreditQueuePageEnum.ORDERING_BO_BILLING_STANDARD_SEARCH_FIELDS_XPATH);
				break;
			case "Advanced":
				expectedSearchFieldNames = Arrays.asList(CommonPage.getTestData("CustomColumn1").split("\\|"));
				searchFieldElementList = BrowserAccess.getElements(OrderingBOCreditQueuePageEnum.ORDERING_BO_BILLING_ADVANCED_SEARCH_FIELDS_XPATH);
				break;
			default:
				throw new InvalidSwitchCaseException("Invalid section name entered");
		}
		ArrayList<String> actualSearchFieldsNames = new ArrayList<>();
		for (WebElement e : searchFieldElementList) {
			BrowserWait.waitUntilElementIsDisplayed(e);
			actualSearchFieldsNames.add(e.getText().trim());
		}
		expectedSearchFieldNames.sort(Comparator.naturalOrder());
		actualSearchFieldsNames.sort(Comparator.naturalOrder());
		Assert.assertTrue(expectedSearchFieldNames.equals(actualSearchFieldsNames), "Search Fileds are not matching");
	}
	
}