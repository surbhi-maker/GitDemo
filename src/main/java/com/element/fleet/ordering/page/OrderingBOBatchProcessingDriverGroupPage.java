package com.element.fleet.ordering.page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.ExcelUtil;
import com.element.fleet.ordering.enums.OrderingBOBatchProcessingDriverGroupPageEnum;
import com.element.fleet.ordering.enums.OrderingBOHomePageEnum;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.webdriver.WebDriverAccess;

public class OrderingBOBatchProcessingDriverGroupPage {
	
	private static String[] driverEmployeeId;
	private static String[] driverFirstName; 
	private static String[] driverLastName;
	private static String[] driverGroupAction;
	private static String corpCode;
	private static String clientNumber;
	private static String groupType;
	private static Map<Integer, Object[]> dataToWrite = new TreeMap<>();
	private static String downloadedFileName;
	static String destFile;
	static String destErrorFile;
	static String currentPath;
	static String currentPathForErrorTemplate;
	private static String downloadedErrorFileName;
	private static String invalidRecordType;
	private static String invalidErrorType;
	

	/**
	 * This method validates Create driver group batch processing page loaded.
	 * @lastModifiesBy rmakhija
	 * @throws Exception
	 */
	public static void batchProcessingCreateDriverGroupPageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_SELECT_UPLOAD_TEMPLATE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_SELECT_DOWNLOAD_TEMPLATE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_LABEL_TITLE_XPATH);
	}
	
	/**
	 * This method validates Create driver group batch processing page label.
	 * @lastModifiesBy rmakhija
	 * @throws Exception
	 */
	public static void batchProcessingCreateDriverGroupPageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_LABEL_TITLE_XPATH), "Uploaded Driver Group Files", "Title label List of Uploaded Driver Group Files did not match with the expected string");
	}
	
	/**
	 * This method downloads create driver group template.
	 * @lastModifiesBy shisingh
	 * @throws Exception
	 */
	public static void downloadCreateDriverGroupBatchTemplate(String templateName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_SELECT_DOWNLOAD_TEMPLATE_DROPDOWN_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_SELECT_DOWNLOAD_TEMPLATE_DROPDOWN_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_SELECT_DOWNLOAD_TEMPLATE_DROPDOWN_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_SELECT_DOWNLOAD_TEMPLATE_DROPDOWN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_TEMPLATE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_TEMPLATE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_TEMPLATE_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_TEMPLATE_XPATH);
		CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_DOWNLOAD_TEMPLATE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_DOWNLOAD_TEMPLATE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_DOWNLOAD_TEMPLATE_BUTTON_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_DOWNLOAD_TEMPLATE_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		String fileName = OrderingBOBatchProcessingDriverGroupPage.checkIfDriverGroupBatchExcelTemplateDownloaded(templateName);
		CommonPage.moveDownloadedFile(fileName, "BatchTemplate", "DriverGroup");	
	}
	
	/**
	 * This method downloads create driver group template.
	 * @lastModifiesBy sagrawal
	 * @throws Exception
	 */
	public static void downloadDriverGroupMassUpdateTemplate() throws Exception {
		OrderingBOBatchProcessingDriverGroupPage.createDriverDataMap();
		OrderingBOBatchProcessingDriverGroupPage.writeDataToMassUpdateExcel(System.getProperty("user.dir")+"\\target\\", downloadedFileName, "Sheet1");
	}
			
	/**
	 * This method verifies if the create driver group tamplate is downloaded.
	 * @lastModifiesBy rmakhija
	 * @throws Exception
	 */
	public static String checkIfDriverGroupBatchExcelTemplateDownloaded(String templatename) {
		String filename = null;	
		Path filePath = Paths.get(new File(System.getProperty("user.home")+"\\Downloads\\")+ "\\"+ templatename+".xlsx");
		for(int i=1; i<=30; ++i) {
			if (Files.exists(filePath)) {
				System.out.println("Filename: " + filePath.toString());
				System.out.println("Downloaded Template exists in Downloads location");
				filename = filePath.toString();
				return filename;
			} else {
				System.out.println("Waiting for file to download");
				new WebDriverWait(WebDriverAccess.getDriver(), new Long(CommonPage.getTestData("WaitTime")));
			}
		}
		return filename;		
	}
	
	/**
	 * This method adds data to downloaded template.
	 * @lastModifiesBy mkaricharla
	 * @throws Exception
	 */
	public static Map<Integer, Object[]> createDriverDataMap(){
		driverEmployeeId = CommonPage.getTestData("DriverEmpIds").split("\\|");
		driverFirstName = CommonPage.getTestData("DriverFirstName").split("\\|");
		driverLastName = CommonPage.getTestData("DriverLastName").split("\\|");
		driverGroupAction = CommonPage.getTestData("DriverGroupAction").split("\\|");
		corpCode = CommonPage.getTestData("CorpCode");
		clientNumber = CommonPage.getTestData("ClientNumber");
		groupType = CommonPage.getTestData("DriverGroupType");
		CommonPage.loadXMLParameterToTestData("DriverGroupName", "AT"+new SimpleDateFormat("ddssSSS").format(new Date()));
		for(int i =0 ;i<driverGroupAction.length; i++) {
			if(driverGroupAction[i].equals("C")) {
				dataToWrite.put(i, new Object[] {corpCode,clientNumber,groupType,CommonPage.getTestData("DriverGroupName"),driverEmployeeId[i],driverLastName[i],driverFirstName[i]});
			}
			
		}
		return dataToWrite;
	}
	
	/**
	 * This method adds data to downloaded template.
	 * @lastModifiesBy rmakhija
	 * @throws Exception
	 */
	public static Map<Integer, Object[]> updateDriverDataMap(){
		driverEmployeeId = CommonPage.getTestData("DriverEmpIds").split("\\|");
		driverFirstName = CommonPage.getTestData("DriverFirstName").split("\\|");
		driverLastName = CommonPage.getTestData("DriverLastName").split("\\|");
		corpCode = CommonPage.getTestData("CorpCode");
		clientNumber = CommonPage.getTestData("ClientNumber");
		driverGroupAction = CommonPage.getTestData("DriverGroupAction").split("\\|");
		groupType = CommonPage.getTestData("DriverGroupType");
		dataToWrite.clear();
		for(int i =0 ;i<driverGroupAction.length; i++) {
			if(!driverGroupAction[i].equals("C")) {
				dataToWrite.put(i, new Object[] {corpCode,clientNumber,groupType,CommonPage.getTestData("DriverGroupID"),CommonPage.getTestData("driverGroupName"),driverGroupAction[i],driverEmployeeId[i-1],driverLastName[i-1],driverFirstName[i-1]});
			}	
		}
		return dataToWrite;
	}
	
	/**
	 * This method adds data to downloaded template.
	 * @throws IOException 
	 * @lastModifiesBy sagrawal
	 * @throws Exception
	 */
	public static void writeDataToMassUpdateExcel(String filePath, String massUpdatefilename, String sheetName) throws IOException {
		System.out.println("Adding data in excel template");
		System.out.println("massUpdatefilename:" + massUpdatefilename+ ".xlsx");
		System.out.println("filepath:" +filePath);
		File file = new File(filePath + "\\" + massUpdatefilename+ ".xlsx");		
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = null;
		XSSFRow newRow;
		wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = (XSSFSheet) wb.getSheet(sheetName);
		int nonEmptyCellCount = OrderingBOBatchProcessingDriverGroupPage.getNumberOfNonEmptyCells(sheet, 0);
		int rowCount = nonEmptyCellCount-1;
		Set<Integer> keys = dataToWrite.keySet();
		for(Integer key : keys) {
			newRow = sheet.createRow(rowCount++);
			Object[] objs = dataToWrite.get(key);
			int cellId = 0;
			for(Object obj : objs ) {
				Cell cell = newRow.createCell(cellId++); 
				cell.setCellValue((String)obj);
			}
		}
		inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(file);
		wb.write(outputStream);
		outputStream.close();
		System.out.println("Data added successfully in excel template");
	}
	
	/**
	 * This method adds data to downloaded template.
	 * @throws IOException 
	 * @lastModifiesBy rmakhija
	 * @throws Exception
	 */
	public static void writeDataToExcel(String filePath, String fileName, String sheetName) throws IOException {
		System.out.println("Adding data in excel template");
		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = null;
		XSSFRow newRow;
		wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = (XSSFSheet) wb.getSheet(sheetName);
		int nonEmptyCellCount = OrderingBOBatchProcessingDriverGroupPage.getNumberOfNonEmptyCells(sheet, 0);
		int rowCount = nonEmptyCellCount-1;
		Set<Integer> keys = dataToWrite.keySet();
		for(Integer key : keys) {
			newRow = sheet.createRow(rowCount++);
			Object[] objs = dataToWrite.get(key);
			int cellId = 0;
			for(Object obj : objs ) {
				Cell cell = newRow.createCell(cellId++); 
				cell.setCellValue((String)obj);
			}
		}
		inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(file);
		wb.write(outputStream);
		outputStream.close();
		System.out.println("Data added successfully in excel template");
	}
	
	/**
	 * This method returns number of nonempty cells.
	 * @lastModifiesBy rmakhija
	 * @throws Exception
	 */
	public static int getNumberOfNonEmptyCells(XSSFSheet sheet, int columnIndex) {
	    int numberOfNonEmptyCells = 0;
	    for (int i = 0; i <= sheet.getLastRowNum(); i++) {
	        XSSFRow row = sheet.getRow(i);
	        if (row != null) {
	            XSSFCell cell = row.getCell(columnIndex);
	            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK && !cell.getRawValue().trim().isEmpty()) {
	                numberOfNonEmptyCells++;
	            }
	        }
	    }
	    return numberOfNonEmptyCells;
	}
	
	/**
	 * This method adds uploads create driver group template
	 * @lastModifiesBy rmakhija
	 * @throws Exception
	 */
	public static void uploadCreateDriverGroupBatchTemplate(WebDriver driver) throws Exception {		
		System.out.println("Starting upload of Create Driver Group Batch template");
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_SELECT_UPLOAD_TEMPLATE_DROPDOWN_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUPS_ADD_TEMPLATE_XPATH);
		String uploadFilePath = System.getProperty("user.dir")+"\\target\\"+"DriverGroup_BatchTemplate_BatchDriverGroupAddDGATemplate.xlsx";
		System.out.println(" UPLOAD FILE NAME AND PATH: " +uploadFilePath);
		driver.findElement(By.xpath("//input[@id='upload-file']")).sendKeys(uploadFilePath);
		BrowserVerify.verifyElementEnabled(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUPS_SEND_BUTTON_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUPS_SEND_BUTTON_XPATH);
		new WebDriverWait(driver, new Long(CommonPage.getTestData("WaitTime")));
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	
	/**
	 * This method adds uploads create driver group template
	 * @lastModifiesBy rmakhija
	 * @throws Exception
	 */
	public static void batchProcessingDriverGroupMaintenancePageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_UPLOAD_TEMPLATE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_DOWNLOAD_TEMPLATE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_LABEL_TITLE_XPATH);
	}
	
	
	/**
	 * This method adds uploads create driver group template
	 * @lastModifiesBy rmakhija
	 * @throws Exception
	 */
	public static void batchProcessingDriverGroupMaintenancePageLabelValidation() throws Exception {
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_LABEL_TITLE_XPATH), "Uploaded Driver Group Files", "Title label List of Uploaded Driver Group Files did not match with the expected string");
	}
	
	/**
	 * This method downloads update driver group template
	 * @lastModifiesBy rmakhija
	 * @throws Exception
	 */
	public static void downloadUpdateDriverGroupBatchTemplate(String templateName) throws Exception {
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_DOWNLOAD_TEMPLATE_DROPDOWN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_TEMPLATE_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_TEMPLATE_XPATH);
		CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_DOWNLOAD_TEMPLATE_BUTTON_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_DOWNLOAD_TEMPLATE_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		String fileName = OrderingBOBatchProcessingDriverGroupPage.checkIfDriverGroupBatchExcelTemplateDownloaded(templateName);
		CommonPage.moveDownloadedFile(fileName, "BatchTemplate", "DriverGroup");
	}
	
	/**
	 * This method uploads update driver group template
	 * @lastModifiesBy rmakhija
	 * @throws Exception
	 */
	public static void uploadUpdateDriverGroupBatchTemplate(WebDriver driver) throws Exception {		
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_UPLOAD_TEMPLATE_DROPDOWN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_MASS_UPDATE_TEMPLATE_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_MASS_UPDATE_TEMPLATE_XPATH);
		String uploadUpdateFilePath = System.getProperty("user.dir")+"\\target\\"+"DriverGroup_BatchTemplate_BatchDriverGroupMassUpdateDGMUTemplate.xlsx";
		System.out.println("UPLOAD FILE NAME AND PATH: " +uploadUpdateFilePath);
		driver.findElement(By.xpath("//input[@id='upload-file']")).sendKeys(uploadUpdateFilePath);
		BrowserVerify.verifyElementEnabled(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SEND_BUTTON_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SEND_BUTTON_XPATH);
		new WebDriverWait(driver, new Long(CommonPage.getTestData("WaitTime")));
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	/**
	 * This method validates Create driver group maintenance batch processing page loaded.
	 * @lastModifiesBy sagrawal
	 * @throws Exception
	 */
	public static void batchProcessingDriverGroupMaintenanceLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_DOWNLOAD_TEMPLATE_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_LABEL_TITLE_XPATH);
	}
	/**
	 * This method verifies "Driver Groups- Mass Update" option in uploads driver group dropdown
	 * @lastModifiesBy sagrawal
	 * @throws Exception
	 */
	public static void batchProcessingDriverGroupMaintenanceUploadOptionlValidation() throws Exception {
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_UPLOAD_TEMPLATE_DROPDOWN_XPATH);
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_MASS_UPDATE_TEMPLATE_XPATH), "Driver Groups- Mass Update", "Upload dropdown option did not match");
	}
	
	/**
	 * This method downloads Driver Group Maintenance template.
	 * @lastModifiesBy sagrawal
	 * @param templateName
	 * @throws Exception
	 */
	public static void downloadDriverGroupMaintenanceTemplate(String templateName) throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_DOWNLOAD_TEMPLATE_DROPDOWN_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_DOWNLOAD_TEMPLATE_DROPDOWN_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_DOWNLOAD_TEMPLATE_DROPDOWN_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_DOWNLOAD_TEMPLATE_DROPDOWN_XPATH);
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_TEMPLATE_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_TEMPLATE_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_TEMPLATE_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_TEMPLATE_XPATH);
		CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_DOWNLOAD_TEMPLATE_BUTTON_XPATH);
		BrowserVerify.verifyElementIsPresent(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_DOWNLOAD_TEMPLATE_BUTTON_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_DOWNLOAD_TEMPLATE_BUTTON_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_DOWNLOAD_TEMPLATE_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingBOBatchProcessingDriverGroupPage.checkIfDriverGroupBatchExcelTemplateDownloaded(templateName);			
	}
	
	/**
	 * This method downloads Driver Group Error template.
	 * @lastModifiesBy sagrawal
	 * * @param templateName
	 * @throws Exception
	 */
	public static void downloadDriverGroupErrorTemplate(String templateName) throws Exception {
		CommonPage.clearDownloadFolder(System.getProperty("user.home")+"\\Downloads\\");
		BrowserVerify.verifyElementIsPresent(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_ERROR_TEMPLATE_DOWNLOAD_XPATH);
		BrowserVerify.verifyElementEnabled(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_ERROR_TEMPLATE_DOWNLOAD_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_ERROR_TEMPLATE_DOWNLOAD_XPATH);
		OrderingCommonPage.checkAlertPopUp();	
	}
	
	/**
	 * This method uploads driver group mass update template
	 * @lastModifiesBy sagrawal
	 * @param driver
	 * @throws Exception
	 */
	public static void uploadDriverGroupMaintenanceTemplate(WebDriver driver) throws Exception {		
		System.out.println("Starting upload of driver group maintenance template");
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_UPLOAD_TEMPLATE_DROPDOWN_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_MASS_UPDATE_TEMPLATE_XPATH);
		String uploadFilePath = System.getProperty("user.dir")+"\\target\\"+downloadedFileName+ ".xlsx";
		System.out.println("UPLOAD FILE NAME AND PATH: " +uploadFilePath);
		BrowserAction.getElement(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_UPLOAD_FILE_NAME_AND_PATH_XPATH).sendKeys(uploadFilePath);
		BrowserVerify.verifyElementEnabled(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SEND_BUTTON_XPATH);
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SEND_BUTTON_XPATH);
		new WebDriverWait(driver, new Long(CommonPage.getTestData("WaitTime")));
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
	}
	
	
	/**
	 * This method clicks on the export button on the BO driver group management page.
	 * @lastModifiedBy sagrawal
	 * @param batch
	 * @param className
	 * @throws Exception
	 */
	public static void clickExportButton(String batch, String className) throws Exception {
		WebDriverAccess.getElement(By.xpath(String.format(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_DOWNLOAD_TEMPLATE_XPATH.toString()))).isDisplayed();
		WebDriverAccess.getElement(By.xpath(String.format(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_DOWNLOAD_TEMPLATE_XPATH.toString()))).isEnabled();
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		WebDriverAccess.getElement(By.xpath(String.format(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_SELECT_DOWNLOAD_TEMPLATE_XPATH.toString()))).click();
		OrderingCommonPage.checkAlertPopUp();
	}
	
	/**
	 * This method verifies results for downloaded template
	 * @lastModifiedBy sagrawal
	 * @param className
	 * @throws Throwable 
	 */
	public static void downloadResult(String className) throws Throwable {
		OrderingCommonPage.checkAlertPopUp();
		downloadedFileName=className+System.currentTimeMillis();
		currentPath = moveDownloadedFileAndReturnFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"), downloadedFileName);
	    System.out.println("currentPath:" +currentPath);
	    ExcelUtil.fetchFromResultFile(currentPath);
}
	
	/**
	 * This method verifies results for downloaded error template
	 * @lastModifiedBy sagrawal
	 * @param className
	 * @throws Throwable 
	 */
	public static void downloadErrorTemplateResult(String className) throws Throwable {
		OrderingCommonPage.checkAlertPopUp();
		downloadedErrorFileName=className+System.currentTimeMillis();
		currentPathForErrorTemplate = moveDownloadedErrorFileAndReturnFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"), downloadedErrorFileName);
		System.out.println("Current Path For Error Template:" +currentPathForErrorTemplate);
		ExcelUtil.fetchFromResultFile(currentPathForErrorTemplate);
		Thread.sleep(3000);
}
	
	/**
	 * This method moves the downloaded file to the target folder and return file path
	 * @lastModifiedBy sagrawal
	 * @param srcPathFile
	 * @param fileName
	 * @return 
	 * @throws Exception
	 */
	public static String moveDownloadedFileAndReturnFile(String srcPathFile, String fileName) throws Exception {
		System.out.println("Source Path File:" +srcPathFile+ "fileName:" +fileName);
		if (srcPathFile != null) {
			File source = new File(srcPathFile);
			File dest = new File(System.getProperty("user.dir") + "\\target\\" + fileName + source.getName().substring(source.getName().lastIndexOf(".")));
			if (dest.exists()) {
				dest.delete();
			}
			if (source.renameTo(new File(System.getProperty("user.dir") + "\\target\\" + fileName + source.getName().substring(source.getName().lastIndexOf("."))))) {
				System.out.println("File moved to Location:" + System.getProperty("user.dir") + "\\target\\" + fileName + source.getName().substring(source.getName().lastIndexOf(".")));
				System.out.println("File is moved successful!");
				destFile=System.getProperty("user.dir") + "\\target\\" + fileName + source.getName().substring(source.getName().lastIndexOf("."));
				source.delete();
				return destFile;
			} else {
				System.out.println("File is not moved successfully!");
			}
		} else {
			throw new Exception("Error in moving file");
		}
		return destFile;
	}
	
	/**
	 * This method moves the downloaded file to the target folder and return file path
	 * @lastModifiedBy sagrawal
	 * @param srcPathFile
	 * @param destErrorFile
	 * @return 
	 * @throws Exception
	 */
	public static String moveDownloadedErrorFileAndReturnFile(String srcPathFile, String fileName) throws Exception {
		System.out.println("Source Path File:" +srcPathFile+ "fileName:" +fileName);
		if (srcPathFile != null) {
			File source = new File(srcPathFile);
			File dest = new File(System.getProperty("user.dir") + "\\target\\" + fileName + source.getName().substring(source.getName().lastIndexOf(".")));
			if (dest.exists()) {
				dest.delete();
			}
			if (source.renameTo(new File(System.getProperty("user.dir") + "\\target\\" + fileName + source.getName().substring(source.getName().lastIndexOf("."))))) {
				System.out.println("File moved to Location:" + System.getProperty("user.dir") + "\\target\\" + fileName + source.getName().substring(source.getName().lastIndexOf(".")));
				System.out.println("File is moved successful!");
				destErrorFile=System.getProperty("user.dir") + "\\target\\" + fileName + source.getName().substring(source.getName().lastIndexOf("."));
				source.delete();
				return destErrorFile;
			} else {
				System.out.println("File is not moved successfully!");
			}
		} else {
			throw new Exception("Error in moving file");
		}
		return destFile;
	}


	/**
	 * This method clicks on the export button on the BO driver group management page.
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void clickOnExportButton() throws Exception {
		try {
			BrowserWait.waitUntilElementIsDisplayed(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_DOWNLOAD_TEMPLATE_BUTTON_XPATH);
			BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_CREATE_DRIVER_GROUP_DOWNLOAD_TEMPLATE_BUTTON_XPATH);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Export Functionality is not working");
		}
	}
	
	/**
	 * This method converts excel file data into map data.
	 * @lastModifiedBy sagrawal 
	 * @param fileNameStartsWith
	 * @throws IOException
	 */
	public static void setExcelToMap(String fileNameStartsWith) throws IOException {
		ExcelUtil.setCSVToMap("\\target\\"+ fileNameStartsWith+".xlsx");
 	}
	
	/**
	 * This method verifies the CSV Column Heading Values with Grid Column Heading Values.
	 * @lastModifiedBy SAgrawal
	 * @param fileNameStartWith
	 * @throws Exception
	 */
	public static void verifyCSVData(String fileNameStartsWith, String batch) throws Exception {
		List<String> csvRows = ExcelUtil.getCSVRows(System.getProperty("user.dir") + "\\target\\" + fileNameStartsWith + ".csv");
		System.out.println("CSV Rows List : " + csvRows);
		int csvRowsCount = csvRows.size();
		if (csvRowsCount == 0) {
			System.out.println("Table does not contains any row.");
		} else {
			String csvColumnHeadingRow = csvRows.get(0);
			String[] csvColumnHeadingSplittedRowValues;
			csvColumnHeadingSplittedRowValues = csvColumnHeadingRow.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			List<String> csvColumnHeadingList = Arrays.asList(csvColumnHeadingSplittedRowValues);
			System.out.println("CSV Column Heading Values :" + csvColumnHeadingList);
		}
		List<String> csvColumns =ExcelUtil.getHeadingsFromExcel((CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\")),"Sheet1");
		System.out.println("CSV Columns List : " + csvColumns);
			int csvColumnCount = csvColumns.size();
			if (csvColumnCount == 0) {
				System.out.println("Table does not contains any row.");
			} else {
				String csvColumnHeadingColumn = csvColumns.get(0);
				String[] csvColumnHeadingSplittedColumnValues;
				csvColumnHeadingSplittedColumnValues = csvColumnHeadingColumn.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				List<String> csvColumnsHeadingList = Arrays.asList(csvColumnHeadingSplittedColumnValues);
				System.out.println("CSV Columns Heading Values :" + csvColumnsHeadingList);
		}	
	}
	
	/**
	 * This method downloads result file
	 * @lastModifiedBy sagrawal
	 * @param className
	 * @throws Exception
	 */
	public static void downloadResultFile(String className) throws Throwable {
		CommonPage.clearDownloadFolder(System.getProperty("user.home") + "\\Downloads\\");
		CommonPage.clickElement(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_DRIVER_GROUP_MAINTENANCE_DOWNLOAD_TEMPLATE_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		downloadedFileName=className+System.currentTimeMillis();
		CommonPage.moveDownloadedFile(CommonPage.getAbsoluteFilePath(System.getProperty("user.home") + "\\Downloads\\"), downloadedFileName);
	}
	
	/** 
	 * This method searches and select the Driver Group.
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void searchAndSelectOnTheDriverGroup() throws Exception {
		BrowserAction.enterFieldValue(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_NAME_SEARCH_FIELD_XPATH, CommonPage.getTestData("driverGroupName"));
		BrowserAction.click(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_NAME_SEARCH_BUTTON_XPATH);
		OrderingCommonPage.checkAlertPopUp();
		OrderingCommonPage.checkGlobalSpinnerPopUp();
		OrderingCommonPage.verifyNoAlertPopUpDispalyed();
		if(!(invalidRecordType=="0" && invalidErrorType=="0")){
			CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_MANAGER_ORDER_PREFERENCES_DRIVER_GROUP_TABLE_DATA_XPATH), "No data available in table", "data should not be there due to invalid data entry in excel");
		}
		else {
			OrderingBOManagerOrderPreferencesPage.selectFirstRowOfTableSection("section-driver-groups");
		}
	}

	/** 
	 * This method downloads error template and verify it
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void batchProcessingResultVerification() throws Exception {
		String invalidRecordType=CommonPage.getFieldValue(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_INVALID_RECORDS_COUNT_XPATH);
		System.out.println("Invalid Record Type: " +invalidRecordType);
		String invalidErrorType=CommonPage.getFieldValue(OrderingBOBatchProcessingDriverGroupPageEnum.ORDERING_BO_BATCH_PROCESSING_ERROR_RECORDS_COUNT_XPATH);
		System.out.println("Invalid Error Type: " +invalidErrorType);
		if(!(invalidRecordType=="0" && invalidErrorType=="0"))
		{
			OrderingBOBatchProcessingDriverGroupPage.downloadDriverGroupErrorTemplate("errorFile");
		}
	}

	/** 
	 * This method verifies that external user do not have access to batch processing
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void verifyExternalUserCannotAccessBatchProcessing() throws Exception {
		BrowserVerify.verifyElementIsNotDisplayed(OrderingBOHomePageEnum.ORDERING_BO_BATCH_PROCESSING_MENU_XPATH);
		System.out.println("Batch Processing can't be access by external user");
		}
}
