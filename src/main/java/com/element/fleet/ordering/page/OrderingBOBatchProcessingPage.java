package com.element.fleet.ordering.page;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.enums.OrderingBOBatchProcessingPageEnum;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingBOBatchProcessingPage {
	private static String downloadedFileName;
	private static XSSFWorkbook wb;
	private static XSSFSheet sheet;
	private static List<String> actualErrors=new ArrayList<String>();
	private static List<String> expectedErrors=new ArrayList<String>();
	
	/**
	 * This method waits for the batch ordering page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void batchOrderingPageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BO_UPLOAD_FILES_TABLE_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BO_LABEL_TITLE_XPATH);
	}
	
	/**
	 * This method asserts the title label.
	 * If validation is successful it will be highlighted with green color if not then it will highlight the border in red color.
	 * @throws Exception
	 */
	public static void batchOrderingPageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BO_LABEL_TITLE_XPATH), "List of Uploaded Batch Ordering Files", "Title label List of Uploaded Batch Ordering Files did not match with the expected string");
	}
	
	/**
	 * This method waits for the create driver group page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void createDriverGroupPageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BO_UPLOAD_FILES_TABLE_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BO_LABEL_TITLE_XPATH);
	}
	
	/**
	 * This method asserts the title label.
	 * If validation is successful it will be highlighted with green color if not then it will highlight the border in red color.
	 * @throws Exception
	 */
	public static void createDriverGroupPageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BO_LABEL_TITLE_XPATH), "Uploaded Driver Group Files", "Title label List of Uploaded Driver Group Files did not match with the expected string");
	}
	
	/**
	 * This method waits for the driver maintenance group page to load.
	 * @lastModifiedBy shisingh
	 * @throws Exception
	 */
	public static void driverGroupMaintenancePageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BO_UPLOAD_FILES_TABLE_ID);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BO_LABEL_TITLE_XPATH);
	}
	
	/**
	 * This method asserts the title label.
	 * If validation is successful it will be highlighted with green color if not then it will highlight the border in red color.
	 * @throws Exception
	 */
	public static void driverGroupMaintenancePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BO_LABEL_TITLE_XPATH), "Uploaded Driver Group Files", "Title label List of Uploaded Driver Group Files did not match with the expected string");
	}
	
	/**
	 * This method loads the batch template xlsx file
	 * @param filePath
	 * @lastModifiedBy djawale
	 * @throws Throwable
	 */
	public static XSSFWorkbook loadBachTemplate(String filePath) throws Throwable{
		FileInputStream excelFile = new FileInputStream(filePath);
        XSSFWorkbook excelWorkBook = new XSSFWorkbook(excelFile);
        return excelWorkBook;
	}
	
	/**
	 * This method uploads batch data to process
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void uploadBatchData(String filePath) throws Throwable {
		CommonPage.selectDropdownValue(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BO_TYPE_OF_UPLOAD_NAME, CommonPage.getTestData("TemplateName"));
		String selectedOptionFromDropdown = CommonPage.getSelectedvaluefronDropdown(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BO_TYPE_OF_UPLOAD_NAME);
		System.out.println("DropDown option selected: " + selectedOptionFromDropdown);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BATCH_UPLOAD_LABEL_ID);
		WebDriverAccess.getDriver().findElement(By.id("upload-file")).sendKeys(filePath);
		String uploadedFilename = BrowserAction.getElementText(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BATCH_UPLOAD_LABEL_ID);
		System.out.println("uploadedFilename: " + uploadedFilename);
		Assert.assertTrue(filePath.endsWith(uploadedFilename),"ASSERTION ERROR: Path->"+filePath+" should end with->"+uploadedFilename);
		CommonPage.clickElement(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BO_SEND_CSS);
		OrderingBOBatchProcessingPage.checkConfimationMessage();
		OrderingCommonPage.verifyNoValidationError();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method make the script wait until batch is processed
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void waitUntilBatchIsProcessed() throws Throwable {
		while(true) {
			String orderStatus=BrowserAction.getElement(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BATCH_STATUS_XPATH).getText().trim();
			if(orderStatus.equals("Completed"))
				break;
		}
	}
	
	/**
	 * This method downloads result file
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static void downloadResultFile(String className) throws Throwable {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingPageEnum.ORDERING_BO_BATCH_PROCESSING_FILE_XPATH);
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		CommonPage.clickElement(OrderingBOBatchProcessingPageEnum.ORDERING_BO_RESULT_FILE_DOWNLOAD_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		downloadedFileName=className+System.currentTimeMillis();
		System.out.println("Result file downloaded: " + downloadedFileName);
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"), downloadedFileName);
	}
	
	/**
	 * This method uploads the batch file, which already exists in resources folder, to Back Office Application
	 * @throws Throwable 
	 * @lastModifiedBy hjimenez
	 */
	public static void processBatchOrderingUploadExistingFileFromResources(String className) throws Throwable {
		if((!(CommonPage.getTestData("FileToBeProcessed")==null))){
			OrderingBOBatchProcessingPage.moveFileToResourcesFolder(CommonPage.getTestData("FileToBeProcessed"));
			if(className.contains("Factory")) {
				OrderingBOBatchProcessingPage.updateExcelFileUnitNumber(3,2);
			}
			OrderingBOBatchProcessingPage.uploadBatchData(System.getProperty("user.dir") + "\\target\\"  + downloadedFileName +".xlsx");
		}
	}
	
	/**
	 * This method copies test data file from resources to target folder without updating the unit numbers
	 * @lastModifiedBy hjimenez
	 * @throws Exception
	 */
	public static void moveFileToResourcesFolder(String fileName) throws Throwable {
		downloadedFileName=fileName+System.currentTimeMillis();
		System.out.println("file name to be uploaded: " + downloadedFileName);
		CommonPage.copyFileToTargetFolder(System.getProperty("user.dir") + "\\resources\\"+fileName + ".xlsx", downloadedFileName);
	}
	
	/**
	 * This method reads error columns
	 * @lastModifiedBy hjimenez
	 * @throws Throwable
	 */
	public static void getActualErrorColumn() throws Throwable{
		System.out.println("File to be read: " + downloadedFileName);
		wb=OrderingBOBatchProcessingPage.loadBachTemplate((System.getProperty("user.dir") + "\\target\\") + downloadedFileName+".xlsx");
		sheet=wb.getSheetAt(0);
		for (Row row : sheet) { 
		    Cell cell = row.getCell(0); 
		    actualErrors.add(cell.toString().trim());
		}
		System.out.println("Actual errors displayed in error file: " + actualErrors);
	}
	
	/**
	 * This method reads error columns
	 * @lastModifiedBy hjimenez
	 * @throws Throwable
	 */
	public static void getExpectedErrorColumn() throws Throwable{
		System.out.println("File to be read: " + downloadedFileName);
		wb=OrderingBOBatchProcessingPage.loadBachTemplate((System.getProperty("user.dir") + "\\target\\") + downloadedFileName+".xlsx");  
		sheet=wb.getSheetAt(0);
		for (Row row : sheet) { 
		    Cell cell = row.getCell(0); 
		    if (!(cell == null)) {
		    	expectedErrors.add(cell.toString().trim());
			}
		    
		}
		System.out.println("Expec errors displayed in upload file: " + expectedErrors);
	}

	/**
	 * This method compares the actual errors in file against the expected errors 
	 * @lastModifiedBy hjimenez
	 * @throws Throwable
	 */
	public static void verifyErrorResults() throws Throwable{
		Assert.assertEquals(actualErrors, expectedErrors);
	}
	
	/**
	 * This method moves the file with the expected errors from resources to target
	 * @throws Throwable 
	 * @lastModifiedBy hjimenez
	 */
	public static void moveErrorMessagesFileToTarget(String className) throws Throwable {
		if((!(CommonPage.getTestData("FileWithExpectedErrors")== null))){
			OrderingBOBatchProcessingPage.moveFileToResourcesFolder(CommonPage.getTestData("FileWithExpectedErrors"));
		}
	}
	
	/**
	 * This method method updates unit numbers 
	 * @param startRowNumber (index of row), int cellNumber (index of cell in row)
	 * @throws Throwable 
	 * @lastModifiedBy hjimenez
	 */
	public static void updateExcelFileUnitNumber(int startRowNumber, int cellNumber) throws Throwable {
		FileInputStream excelFile = new FileInputStream(System.getProperty("user.dir") + "\\target\\"  + downloadedFileName +".xlsx");
		XSSFWorkbook excelWorkBook = new XSSFWorkbook(excelFile);
		XSSFSheet sheet = excelWorkBook.getSheetAt(0);
		int lastRowIndex = sheet.getLastRowNum();
		System.out.println("Number of rows:" + lastRowIndex);
		for (int i = startRowNumber; i <= lastRowIndex; i++) {
			if (sheet.getRow(i).getCell(cellNumber) == null || sheet.getRow(i).getCell(cellNumber).getStringCellValue().equalsIgnoreCase("@")) {
				System.out.println("skip sending unit number");
			} else {
				sheet.getRow(i).getCell(cellNumber).setCellValue("B" + new SimpleDateFormat("ddssSSS").format(new Date()));
			}
		}
		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\target\\"  + downloadedFileName +".xlsx");
		excelWorkBook.write(fos);
		fos.close();
	}
	
	/**
	 * This method verify alert pop up message.
	 * @lastModifiedBy UshaNaidu
	 * @throws Exception
	 */
	public static void checkConfimationMessage() throws Exception {
		new WebDriverWait(WebDriverAccess.getDriver(), new Long(10)).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.noty_bar"), 1));
		String alertPopUpText = new WebDriverWait(WebDriverAccess.getDriver(), new Long(10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.noty_bar"))).getText();
		Assert.assertEquals(alertPopUpText, "The document has been uploaded successfully", "The Document Upload message is incorrect");
	}

}
