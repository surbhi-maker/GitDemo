package com.element.fleet.ordering.commonutility;

import org.testng.Assert;
import org.yaml.snakeyaml.Yaml;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.element.fleet.ordering.enums.OrderingHomePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.NoIfElseBlockMatchedException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.element.fleet.ordering.rest.OrderingRestAPI;
import com.element.fleet.ordering.verification.ElementOrder;
import com.element.fleet.ordering.verification.SummaryTab;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserAssert;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.testng.PDFReporter;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class CommonPage extends PDFReporter {

	private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static ElementOrder elementOrderDetails;
	private static Map<String, String> testDataMap;
	private static Map<String, String> credentialsDataMap;
	private static TreeMap<String, Object[]> loadDurationTreeMap = new TreeMap<>();
	private static Random random = new Random();
	private static String ele;
	private static String pdfText;

	public CommonPage() throws Throwable {
		super();
	}

	public static void getBrowserInfo(WebDriver driver) {
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = caps.getBrowserName();
		String browserVersion = caps.getVersion();
		System.out.println("<Browser-Info>" + browserName + " " + browserVersion + "</Browser-Info>");
	}
	
	public static ChromeDriver starDebugMode(String port) throws Exception {
		WebDriverAccess.getDriver().quit();		
		System.setProperty("webdriver.chrome.driver", "C:\\rainbow\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		System.out.println("IP Address: "+InetAddress.getLocalHost().getHostAddress()+":"+port);
		options.setExperimentalOption("debuggerAddress", "localhost:"+port);
		options.addArguments("disable-infobars");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-extensions");
		return new ChromeDriver(options);
	}

	/**
	 * This method generates random single digit number
	 * @lastModifiedBy skathule
	 * @throws IOException
	 */
	public static int randomSingleDigitInteger() throws Exception {
		Random rand = new Random();
		int value = rand.nextInt(10);
		return value;
	} 

	public static int randomNumberInRange(int i) {
		int start = 1;
		int end = i;
		return random.nextInt((end - start) + 1) + start;
	}


	public static boolean existsElement(String id, WebDriver driver) {
		try {
			driver.findElement(By.id(id));
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	/**
	 * This method clean the Download folder
	 * @lastModifiedBy shisingh
	 * @throws IOException
	 */
	public static void clearDownloadFolder(String filePath) throws IOException {
		FileUtils.cleanDirectory(new File(filePath));
		System.out.println("Downloads folder is cleaned");
	}

	/**
	 * This method returns the absolute file path of the first file name in the given folder
	 * @lastModifiedBy shisngh
	 * @return
	 */
	public static String getAbsoluteFilePath(String filePath) {
		System.out.println("Checking for file download at " + filePath);
		try {
			new FluentWait<WebDriver>(WebDriverAccess.getDriver())
			.ignoring(TimeoutException.class)
			.pollingEvery(Duration.ofSeconds(1))
			.withTimeout(Duration.ofSeconds(new Long(CommonPage.getTestData("WaitTime"))))
			.until(x->{if((new File(filePath).listFiles().length==1)&&!(new File(filePath).list()[0].endsWith(".crdownload"))){System.out.println("File name " + new File(filePath).listFiles()[0].getAbsolutePath());return true;}return false;});
			return new File(filePath).listFiles()[0].getAbsolutePath();
		} catch(TimeoutException e) {
			throw new OrderingErrorOccured("File not avalilable at "+filePath+" location for "+CommonPage.getTestData("WaitTime")+" seconds");
		}
	}

	public static String returnLatestPDFFileName(String fileNameStartsWith) {
		Optional<File> mostRecentFileOrFolder = null;
		try {
			Predicate<File> orderFilter = null;
			switch (fileNameStartsWith) {
				case "ORPO":
					orderFilter = f -> f.getName().startsWith("ORPO");
					break;
				case "SOPO":
					orderFilter = f -> f.getName().startsWith("SOPO");
					break;
				case "fleet":
					orderFilter = f -> f.getName().startsWith("fleet");
					break;
				case "price":
					orderFilter = f -> f.getName().startsWith("price");
					break;
				case "Upfit Spec-queue":
					orderFilter = f -> f.getName().startsWith("Upfit Spec-queue");
					break;
				case "IOT":
					orderFilter = f -> f.getName().startsWith("IOT");
					break;
				default: throw new InvalidSwitchCaseException(fileNameStartsWith +  " is a invalid option");
			}
			Path parentFolder = Paths.get(System.getProperty("user.home") + "\\Downloads\\");
			mostRecentFileOrFolder =
					Arrays.stream(parentFolder.toFile().listFiles())
							.filter(f -> f.getName().endsWith(".pdf"))
							.filter(orderFilter)
							.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new OrderingErrorOccured("File not downloaded");
	}
		return mostRecentFileOrFolder.get().toString();
	}
	
	 	/**
		 * This method checks the downloaded csv file.	
		 * @lastModifiedBy akandkonde	
		 * @throws Exception	
		 */	
		public static String returnLatestCSVFileName(String fileNameStartsWith) {	
			Optional<File> mostRecentFileOrFolder = null;	
			try {	
				Predicate<File> orderFilter = null;	
				switch (fileNameStartsWith) {
					case "Master Purchase Order Status":	
						orderFilter = f -> f.getName().startsWith("Master Purchase Order Status-Table");	
						break;
					case "PrelimFinalOutputData":	
						orderFilter = f -> f.getName().startsWith("PrelimFinalOutputData");	
						break;	
					case "Project-queue":	
						orderFilter = f -> f.getName().startsWith("Project-queue");	
						break;	
					case "On Order-queue":	
						orderFilter = f -> f.getName().startsWith("On Order-queue");	
						break;	
					case "My Orders Queue-queue":	
						orderFilter = f -> f.getName().startsWith("My Orders Queue-queue");	
						break;	
					case "comments_table":	
						orderFilter = f -> f.getName().startsWith("comments_table");	
						break;	
					case "Work In Progress Queue-queue":	
						orderFilter = f -> f.getName().startsWith("Work In Progress Queue-queue");	
						break;	
					case "specs_table":	
						orderFilter = f -> f.getName().startsWith("specs_table");	
						break;	
					case "Upfit Spec-queue":	
						orderFilter = f -> f.getName().startsWith("Upfit Spec-queue");	
						break;	
					case "Billing-queue":	
						orderFilter = f -> f.getName().startsWith("Billing-queue");	
						break;	
					case "My Orders Queue":	
						orderFilter = f -> f.getName().startsWith("My Orders Queue");	
						break;	
					default: throw new InvalidSwitchCaseException(fileNameStartsWith + " is a invalid file name");	
				}	
				Path parentFolder = Paths.get(System.getProperty("user.home") + "\\Downloads\\");	
				mostRecentFileOrFolder =	
						Arrays.stream(parentFolder.toFile().listFiles())	
								.filter(f -> f.getName().endsWith(".csv"))	
								.filter(orderFilter)	
								.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));	
			} catch (NullPointerException e) {	
				e.printStackTrace();	
				throw new OrderingErrorOccured("CSV File not downloaded");	
			}	
			return mostRecentFileOrFolder.get().toString();	
		}

	/**
	 * This method moves the downloaded file to the target folder with passed name.
	 * @lastModifiedBy shisingh
	 * @param srcPathFile
	 * @param fileName
	 * @throws Exception
	 */
	public static void moveDownloadedFile(String srcPathFile, String fileName) throws Exception {
		if (srcPathFile != null) {
			File source = new File(srcPathFile);
			File dest = new File(System.getProperty("user.dir") + "\\target\\" + fileName + source.getName().substring(source.getName().lastIndexOf('.')));
			if (dest.exists()) {
				dest.delete();
			}
			if (source.renameTo(new File(System.getProperty("user.dir") + "\\target\\" + fileName + source.getName().substring(source.getName().lastIndexOf('.'))))) {
				System.out.println("File moved to Location:" + System.getProperty("user.dir") + "\\target\\" + fileName + source.getName().substring(source.getName().lastIndexOf('.')));
				System.out.println("File is moved successful!");
				source.delete();
			} else {
				System.out.println("File is not moved successfully!");
			}
		} else {
			throw new OrderingErrorOccured("Error in moving file");
		}
	}

	public static void moveDownloadedFile(String srcPathFile, String markerPreorPost, String testNumber) {
		if (srcPathFile != null) {
			File source = new File(srcPathFile);
			File dest = new File(System.getProperty("user.dir") + "\\target\\" + testNumber + "_" + markerPreorPost + "_" + source.getName());
			if (dest.exists()) {
				dest.delete();
			}
			if (source.renameTo(new File(System.getProperty("user.dir") + "\\target\\" + testNumber + "_" + markerPreorPost + "_" + source.getName()))) {
				System.out.println("File moved location: " + System.getProperty("user.dir") + "\\target\\" + testNumber + "_" + markerPreorPost + "_" + source.getName());
				source.delete();
			} else {
				System.err.println("File is not moved successfully!");
			}
		} else {
			throw new OrderingErrorOccured("Error in moving file");
		}
	}

	public static String randomAlphaNumericString() {
		return RandomStringUtils.randomAlphanumeric(5);
	}

	public static void pressEnter() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	public static String generateRandomNumber() {
		int rand = ThreadLocalRandom.current().nextInt(1, 10000);
		return Integer.toString(rand);
	}

	public static void testStarted() {
		System.out.println("==========================================");
		System.out.println("Execution Started at" + " [" + sdfDate.format(new Date()) + "] ");
		System.out.println("==========================================");
	}

	public static void testEnded() {
		System.out.println("========================================");
		System.out.println("Execution Ended at" + " [" + sdfDate.format(new Date()) + "] ");
		System.out.println("========================================");
	}

	/**
	 * This method gets the duration tree map.
	 * @lastModifiedBy shisingh
	 * @return
	 */
	public static TreeMap<String, Object[]> getPageLoadMapEndDuration() {
		return loadDurationTreeMap;
	}

	public static void initializeElementOrderObject() {
		if (elementOrderDetails == null) {
			elementOrderDetails = new ElementOrder();
		}
	}

	public static ElementOrder getElementOrderObject() {
		return elementOrderDetails;
	}

	public static void loadCSVWithHeading(String fileRelativePath, String testCaseName) throws IOException {
		Map<String, Map<String, String>> completeDataMap = new Yaml().load(new FileInputStream(new File(System.getProperty("user.dir") + fileRelativePath)));
		if(!completeDataMap.containsKey(testCaseName)) {
			throw new OrderingErrorOccured("Test data file does not have " + testCaseName);
		}
		testDataMap = completeDataMap.get(testCaseName);
		if (Objects.isNull(testDataMap)) {
			testDataMap = new HashMap<>();
		}
		testDataMap.put("TestCaseName", testCaseName);
	}

	public static void loadCSVWithHeading(String fileRelativePath) throws IOException {
		final String pattern = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
		List<String> csvRows = ExcelUtil.getCSVRows(System.getProperty("user.dir") + fileRelativePath);
		int csvRowsCount = csvRows.size();
		credentialsDataMap = new HashMap<>();
		for (int i = 1; i < csvRowsCount; ++i) {
			String[] csvRow = csvRows.get(i).split(pattern, -1);
			credentialsDataMap.put(csvRow[0], (csvRow[1].equals("") ? null : csvRow[1]));
		}
		if (Objects.isNull(credentialsDataMap)) {
			throw new OrderingErrorOccured("Credentials file does not have any credentials");
		}
	}

	public static void loadXMLParameterToTestData(String key, String value) {
		testDataMap.put(key, value);
	}

	public static String getTestData(String key) {
		return testDataMap.get(key);
	}

	public static String getCredetialsData(String key) {
		return credentialsDataMap.get(key);
	}

	public static void jsGreenBorderHighlighter(WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid green;');", webElement);
	}

	private static void jsRedBorderHighlighter(WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", webElement);
	}

	public static void assertLabelHighlight(WebElement webElement, String expectedString, String message) {
		try {
			Assert.assertEquals(webElement.getText().trim(), expectedString, message);
			CommonPage.jsGreenBorderHighlighter(webElement);
		} catch (AssertionError e) {
			CommonPage.jsRedBorderHighlighter(webElement);
			e.printStackTrace();
			throw new AssertionError();
		}
	}

	public static void clickHighlight(WebElement webElement) {
		try {
			CommonPage.jsGreenBorderHighlighter(webElement);
			webElement.click();
		} catch (AssertionError e) {
			CommonPage.jsRedBorderHighlighter(webElement);
			e.printStackTrace();
			throw new OrderingErrorOccured("Unable to click on the element");
		}
	}

	public static void assertElementHighlight(WebElement webElement, String message) {
		try {
			Assert.assertTrue(webElement.isDisplayed(), message);
			CommonPage.jsGreenBorderHighlighter(webElement);
		} catch (AssertionError e) {
			CommonPage.jsRedBorderHighlighter(webElement);
			e.printStackTrace();
			throw new AssertionError();
		}
	}

	/**
	 * This method enters the text in the element field
	 * @lastModifiedBy djawale
	 * @param fieldtLocatorEnum
	 * @param fieldInput
	 * @throws Exception
	 */
	public static void enterTextToInputField(Enum<?> fieldtLocatorEnum, String fieldInput) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		BrowserVerify.verifyElementIsDisplayed(fieldtLocatorEnum);
		BrowserAction.clickandClear(fieldtLocatorEnum);
		BrowserAction.enterFieldValue(fieldtLocatorEnum, fieldInput);
	}

	/**
	 * This method returns the element text
	 * @lastModifiedBy djawale
	 * @param fieldtLocatorEnum
	 * @return field text
	 * @throws Exception
	 */
	public static String getFieldValue(Enum<?> fieldtLocatorEnum) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		BrowserVerify.verifyElementIsDisplayed(fieldtLocatorEnum);
		return BrowserAction.getElement(fieldtLocatorEnum).getText().trim();
	}

	/**
	 * This method selects the Dropdown value of element reading the Key from csv file
	 * @lastModifiedBy sbhosale
	 * @param fieldtLocatorEnum
	 * @return void
	 * @throws Exception
	 */
	public static void selectDropdownValue(Enum<?> fieldtLocatorEnum, String key) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		BrowserVerify.verifyElementIsPresent(fieldtLocatorEnum);
		BrowserVerify.verifyElementEnabled(fieldtLocatorEnum);
		BrowserAction.selectDropdownOptionByText(fieldtLocatorEnum, key);
	}

	/**
	 * This method returns current value of Dropdown
	 * @lastModifiedBy sbhosale
	 * @param fieldtLocatorEnum
	 * @return text
	 * @throws Exception
	 */
	public static String getSelectedvaluefronDropdown(Enum<?> fieldtLocatorEnum) throws Exception {
		String currentvalue = new Select(BrowserAction.getElement(fieldtLocatorEnum)).getFirstSelectedOption().getText();
		System.out.println("SelectedOption is " + currentvalue);
		return currentvalue;
	}

	/**
	 * This method will verify input text value with expected value
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyInputTextFieldValue(Enum<?> fieldtLocatorEnum, String expectedValue, String fieldName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		BrowserVerify.verifyElementIsPresent(fieldtLocatorEnum);
		BrowserVerify.verifyElementEnabled(fieldtLocatorEnum);
		String actualValue = BrowserAction.getElementAttributeValue(fieldtLocatorEnum, "value").trim();
		Assert.assertEquals(actualValue, expectedValue, "Actual and excpected value not matching for " + fieldName);
	}

	public static String getCurrentDataIn(String... format) {
		if (format.length == 1) {
			return new SimpleDateFormat(format[0]).format(new Date());
		}
		if (format.length == 2) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, Integer.parseInt(format[1]));
			return new SimpleDateFormat(format[0]).format(cal.getTime());
		} else
			throw new OrderingErrorOccured("Invalid parameter list");
	}
	
	/**
	 *This method will verify drop down selected value
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyDropDownSelectedValue (Enum<?> fieldtLocatorEnum, String expectedValue, String fieldName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		Select slt = new Select(BrowserAction.getElement(fieldtLocatorEnum));
		String actualValue = slt.getFirstSelectedOption().getText().trim();
		Assert.assertEquals(actualValue, expectedValue, "Actual and Expected not matching for " + fieldName + " drop down");
	}

	/**
	 *This method will verify drop down selected value
	 * @lastModifiedBy shisingh
	 */
	public static void selectDropDownValue (Enum < ? > fieldtLocatorEnum, String option, String fieldName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		BrowserVerify.verifyElementIsPresent(fieldtLocatorEnum);
		BrowserVerify.verifyElementEnabled(fieldtLocatorEnum);
		System.out.println(fieldName + ": " + option);
		BrowserAction.selectDropdownOptionByText(fieldtLocatorEnum, option);
	}

	/**
	 * This method will wait to load element
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void waitForElementToLoad (Enum < ? > fieldtLocatorEnum, String waitTime) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(fieldtLocatorEnum);
		BrowserVerify.verifyElementIsPresent(fieldtLocatorEnum);
		BrowserVerify.verifyElementEnabled(fieldtLocatorEnum);
		if (BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.xpath"))
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(waitTime)).until(ExpectedConditions.numberOfElementsToBe(By.xpath(fieldtLocatorEnum.toString()), 1));
		else if (BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.id"))
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(waitTime)).until(ExpectedConditions.numberOfElementsToBe(By.id(fieldtLocatorEnum.toString()), 1));
		else if (BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.name"))
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(waitTime)).until(ExpectedConditions.numberOfElementsToBe(By.name(fieldtLocatorEnum.toString()), 1));
		else if (BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.cssSelector"))
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(waitTime)).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(fieldtLocatorEnum.toString()), 1));
		else if (BrowserAction.getLocator(fieldtLocatorEnum).toString().contains("By.className"))
			new WebDriverWait(WebDriverAccess.getDriver(), new Long(waitTime)).until(ExpectedConditions.numberOfElementsToBe(By.className(fieldtLocatorEnum.toString()), 1));
		else 
			throw new NoIfElseBlockMatchedException();
	}

	/**
	 * This method will mark green if element present otherwise it mark element red
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void highlightElement (WebElement webElement){
		try {
			CommonPage.jsGreenBorderHighlighter(webElement);
		} catch (AssertionError e) {
			CommonPage.jsRedBorderHighlighter(webElement);
			e.printStackTrace();
			throw new AssertionError();
		}
	}

	/**
	 * This method will click on the element using javascript Executor.
	 * @lastModifiedBy Sidheshwar Kathule
	 */
	public static void javascriptClick(WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		js.executeScript("arguments[0].click();", webElement);
	}

	/**
	 * This method clicks on element
	 * @lastModifiedBy djawale
	 * @param elementLocatorEnum
	 * @throws Exception
	 */
	public static void clickElement(Enum<?> elementLocatorEnum) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(elementLocatorEnum);
		BrowserVerify.verifyElementIsDisplayed(elementLocatorEnum);
		BrowserVerify.verifyElementEnabled(elementLocatorEnum);
		BrowserAction.click(elementLocatorEnum);
	}

	/**
	 * This method scroll to the bottom of the page
	 * @lastModifiedBy skathule
	 **/
	public static void scrollToBottomOfPage() {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	/**
	 * This method waits and then verifies the element is displayed and enabled
	 * @param locator locator of the element
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void waitAndVerifyElementDisplayedAndEnabled(final Object... locator) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(locator);
		BrowserVerify.verifyElementIsDisplayed(locator);
		BrowserVerify.verifyElementEnabled(locator);
	}

	/**
	 * This method waits and then verifies the element is displayed and disabled
	 * @param locator locator of the element
	 * @throws Exception
	 * @lastModifiedBy vikumar
	 */
	public static void waitAndVerifyElementDisplayedAndDisabled(final Object... locator) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(locator);
		BrowserVerify.verifyElementIsDisplayed(locator);
		BrowserVerify.verifyElementDisabled(locator);
	}

	/**
	 * This method  verifies the element is displayed and asserted.
	 * @param locator of the element
	 * @throws Exception
	 * @lastModifiedBy skathule
	 */
	public static void verifyElementDisplayedAndAsserted(Enum<?> elementLocatorEnum) throws Exception {
		if(BrowserVerify.verifyElementIsDisplayed(elementLocatorEnum)==null ) {
			BrowserAssert.assertElementIsDisplayed(elementLocatorEnum);		 
		}
		else 
			System.out.println("Element not Present on the Page");
	}

	/**
	 * This method  verifies the element text is displayed.
	 * @param locator  of the element
	 * @throws Exception
	 * @lastModifiedBy skathule
	 */
	public static String verifyElementTextPresent(Enum<?> elementLocatorEnum) throws Exception {
		String elementText="";
		if(BrowserVerify.verifyElementIsDisplayed(elementLocatorEnum)==null ) {
			elementText=BrowserAction.getElement(elementLocatorEnum).getText();
		}
		else {
			System.out.println("element text not Present");
		}
		return elementText;
	}	

	/**
	 * This method update the client drop down when logged in as External user
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void updateClientDropdown() throws Exception{
		BrowserWait.waitUntilElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_CHANGE_CLIENT_DROPDOWN_XPATH);
		BrowserVerify.verifyElementIsDisplayed(OrderingHomePageEnum.ORDEING_HOME_CHANGE_CLIENT_DROPDOWN_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingHomePageEnum.ORDEING_HOME_CHANGE_CLIENT_DROPDOWN_XPATH);
		Select clientDropDown = new Select(BrowserAction.getElement(OrderingHomePageEnum.ORDEING_HOME_CHANGE_CLIENT_DROPDOWN_XPATH));			
		List<WebElement> clientOptions = clientDropDown.getOptions();
		int counter = 0;
		for(WebElement clientOption: clientOptions) {
			if (clientOption.getAttribute("data-client-number").equals(CommonPage.getTestData("ClientNumber"))){
				System.out.println("Client: "+ CommonPage.getTestData("ClientNumber"));
				clientDropDown.selectByVisibleText(clientOption.getText());
	    		++counter;
	    		break;
	    	}
		}
	    if(counter!=1) {
	    	throw new OrderingErrorOccured(CommonPage.getTestData("ClientNumber")+" client is not available in the dropdown list");
	    }
	 }

	/**
	 * This method gets the role of logged in User
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void getUserRole(String username) throws Exception {

		String userRole = "Ordering Super User";//OrderingRestAPI.getUserRoleFromOrderingFO(username);

		CommonPage.loadXMLParameterToTestData("Username", username);
	//	String userRole = OrderingRestAPI.getUserRoleFromOrderingFO(username);

		CommonPage.loadXMLParameterToTestData("UserRole", userRole);
		System.out.println("UserRole: "+userRole);
	   }
	
	/**
	 * This method scrolls the webpage untill the element is visible.
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void javascriptScrollUntilElementIsVisible(WebElement ele) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) WebDriverAccess.getDriver();
		js.executeScript("arguments[0].scrollIntoView()",ele);
	}

	/**
	 * This method verify the element is present or not and it is in bold or not
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static String verifyElementIsPresentInBold(Enum<?> elementLocatorEnum) throws Exception {
		if(Objects.isNull(BrowserVerify.verifyElementIsDisplayed(elementLocatorEnum))) {
			ele=BrowserAction.getElement(elementLocatorEnum).getTagName();
			if(ele.matches("strong")) {
				System.out.println("Element text present in Bold");}
			else {
				throw new OrderingErrorOccured("Text Not in Bold" +ele);
			}		 
		}
		else {
			System.out.println("Element not Present");
		}
		return ele ;
	}
	
	/**
	 * This method convert the PDF into the text file
	 * @param pdfFilePath
	 * @lastModifiedBy skathule
	 * @throws Exception
	 */
	public static void getPDFText(String pdfPath, WebDriver driver) throws Exception {
		SummaryTab.PDF pdfData = CommonPage.getElementOrderObject().getSummaryTabObject().getpdfObject();
		try(PDDocument document = PDDocument.load(new File(pdfPath))) {
			document.getClass();
			if (!document.isEncrypted()) {
				PDFTextStripper tStripper = new PDFTextStripper();
				pdfText = tStripper.getText(document);
				pdfData.setpdfText(pdfText);
			}
		}
	}
	
	/**
	 * This method compares two dates, returns true if date 1 is bigger than date 2
	 * @param date1, dat2
	 * @lastModifiedBy hector_jimenez
	 * @throws Exception
	 */
	public static boolean isFirstDateGreaterThanSecondDate(String date1, String date2) throws Exception {
		 SimpleDateFormat sdFormat = new SimpleDateFormat("MM/dd/yyyy");
		 Date d1 = sdFormat.parse(date1);
		 Date d2 = sdFormat.parse(date2);
		 return d1.before(d2);
	}
	/**
	 * This method forces textfield to clear when clear() method does not work
	 * @lastModifiedBy hjimenez
	 * @param fieldtLocatorEnum
	 * @throws Exception
	 */
	public static void forceClearTextField(Enum<?> fieldtLocatorEnum) throws Exception {
		BrowserAction.getElement(fieldtLocatorEnum).sendKeys(Keys.CONTROL + "a");
		BrowserAction.getElement(fieldtLocatorEnum).sendKeys(Keys.DELETE);
	}
	
	/**
	 * This method copies given file to to the target folder with passed name.
	 * @lastModifiedBy djawale
	 * @param srcPathFile
	 * @param fileName
	 * @throws Throwable 
	 * @throws Exception
	 */
	public static void copyFileToTargetFolder(String srcPathFile, String fileName) throws Throwable {
		if (srcPathFile != null) {
			File source = new File(srcPathFile);
			File dest = new File(System.getProperty("user.dir") + "\\target\\" + fileName + source.getName().substring(source.getName().lastIndexOf('.')));
			if (dest.exists()) {
				dest.delete();
			}
			FileUtils.copyFile(source, dest);
			if(dest.exists()) {
				System.out.println("File copied to Location:" + System.getProperty("user.dir") + "\\target\\" + fileName + source.getName().substring(source.getName().lastIndexOf('.')));
				System.out.println("File is copied successful!");
			} else {
				System.out.println("File is not copied successfully!");
			}
		} else {
			throw new OrderingErrorOccured("Error in copying file");
		}
	}
}
