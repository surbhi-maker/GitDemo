package com.element.fleet.ordering.page;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingCommonPage {
	 	
	/**
	 * This method checks the alert pop based on colour.
	 * #d05050->Red->Halts the execution and logs displayed error.
	 * #337ab7->Green->Logs the notification message.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void checkAlertPopUp() {
		try {
			new WebDriverWait(WebDriverAccess.getDriver(), 3).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 1));
			String popUpBackgroundColour = Color.fromString(WebDriverAccess.getDriver().findElement(By.cssSelector("div.noty_bar")).getCssValue("background-color")).asHex();
			if(popUpBackgroundColour.equals("#d05050")||popUpBackgroundColour.equals("#bf2e2e")) {
				System.err.println("Error pop up: " + WebDriverAccess.getDriver().findElement(By.cssSelector("div.noty_bar")).getText());
				throw new OrderingErrorOccured(WebDriverAccess.getDriver().findElement(By.cssSelector("div.noty_bar")).getText());
			} else if(popUpBackgroundColour.equals("#e57220")) {
				System.err.println("Warning pop up: " + WebDriverAccess.getDriver().findElement(By.cssSelector("div.noty_bar")).getText());
				throw new OrderingErrorOccured(WebDriverAccess.getDriver().findElement(By.cssSelector("div.noty_bar")).getText());
			} else if(popUpBackgroundColour.equals("#337ab7")) {
				System.out.println("Notification pop up: " + WebDriverAccess.getDriver().findElement(By.cssSelector("div.noty_bar")).getText());
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 0));
			} else if(popUpBackgroundColour.equals("#80bd00")||popUpBackgroundColour.equals("#74af39")||popUpBackgroundColour.equals("#82c341")||popUpBackgroundColour.equals("#4cb4e7")) {
				System.out.println("Success pop up: " + WebDriverAccess.getDriver().findElement(By.cssSelector("div.noty_bar")).getText());
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 0));
			} else {
				System.err.println("Pop up message: " + WebDriverAccess.getDriver().findElement(By.cssSelector("div.noty_bar")).getText());
				System.err.println("Pop up colour: " + popUpBackgroundColour);
				throw new OrderingErrorOccured(WebDriverAccess.getDriver().findElement(By.cssSelector("div.noty_bar")).getText());
			}			
		} catch(TimeoutException|NoSuchElementException e) {
			System.out.println("No alert pop up found move ahead");
		}
	}
	
	/**
	 * This method wait until there is no alert pop up.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyNoAlertPopUpDispalyed() {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 0));
	}

	/**
	 * This method waits for the global-spinner-wrapper element to be zero in the DOM
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void checkGlobalSpinnerPopUp() {
		try {
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime"))).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.global-spinner-wrapper"), 0));
		} catch(NoSuchElementException e) {
			System.out.println("No global spinner found move ahead");
		} catch(TimeoutException e) {
			System.err.println("Global spinner is still present on the page");
			e.printStackTrace();
			throw new TimeoutException();
		}
	}
	
	/**
	 * This method verify that no validation error is present.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void verifyNoValidationError() throws Exception {
		List<WebElement> validationErrors = WebDriverAccess.getElements(By.cssSelector("div.validation-error-wrapper"));
		if(validationErrors.isEmpty()) {
			System.out.println("No validation error found move ahead");
		} else {
			for(WebElement validationError:validationErrors) {
				System.err.println("Validation Error: " + validationError.getText().trim());
			}
			throw new OrderingErrorOccured("Validation Errors are present");
		}
	}
		
}