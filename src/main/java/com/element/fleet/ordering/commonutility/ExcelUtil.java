package com.element.fleet.ordering.commonutility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.ge.capital.rainbow.webdriver.BaseWebDriver;

public class ExcelUtil extends BaseWebDriver {
	private static XSSFSheet excelWorkSheet;
	private static XSSFWorkbook excelWorkBook;
	private static XSSFCell excelCell;
	private static Map<String, ArrayList<String>> backOfficeOOQData = new LinkedHashMap<>();
	
	
	/*
	 * This method reads csv file
	 * This method also reads excel file
	 * This method gets the row data from the downloaded csv 
	 * @lastmodified sagrawal

	 */
	public static List<String> getCSVRows(String relativeFilePath) throws IOException {
		List<String> csvRows = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(relativeFilePath))){
			String line;
			while ((line = br.readLine()) != null) {
				csvRows.add(line);
			}
		}
		return csvRows;
	}
	
	/**
	 * This method will store csv data to map 
	 * @param fileRelativePath
	 * @throws IOException 
	 * @lastmodified akandkonde
	 */
	public static void setCSVToMap(String fileRelativePath) throws IOException {
		String columnPattern = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
		String columnValuesPattern = "\"";
		List<String> csvRows = ExcelUtil.getCSVRows(System.getProperty("user.dir")+fileRelativePath);
		int csvRowsCount = csvRows.size();
		if(csvRowsCount<=1) {
			System.out.println("Table is have only zero or one row. So no sorting is needed.");
		} else {
			String[] columnValues;
			int columnIterations;			
			ArrayList<List<String>> csvColumns = new ArrayList<>();
			Arrays.stream(csvRows.stream().findFirst().toString().split(columnPattern)).forEach(e->csvColumns.add(new ArrayList<String>()));
			for(String csvRow: csvRows) {
				columnValues = csvRow.split(columnPattern);
				columnIterations = columnValues.length;
				for(int i=0;i<columnIterations;++i) {
					csvColumns.get(i).add(columnValues[i].trim().replaceAll(columnValuesPattern, "").replaceAll("'", ""));
				}
			}
			csvColumns.stream().forEach(e->backOfficeOOQData.put(e.get(0).replaceAll(columnValuesPattern, ""), new ArrayList<String>(e.subList(1, e.size()))));
		}
	}
	
	/*
	 * This method gets the heading from excel
	 * @lastModifiedBy SAgrawal
	 */
	public static List<String> getHeadingsFromExcel(String fileName, String sheetName) throws IOException {
		try {
			FileInputStream excelFile;
			List<String> rowList = new ArrayList<>();
			String USER_DIRECTORY = "user.dir";
			excelFile = new FileInputStream(System.getProperty(USER_DIRECTORY) + fileName);
			excelWorkBook = new XSSFWorkbook(excelFile);
			excelWorkSheet = excelWorkBook.getSheet(sheetName);
			int[] subHeadingColumn = {1,7,9};
			for(org.apache.poi.ss.usermodel.Row row : excelWorkSheet) {
				excelCell = (XSSFCell) row.getCell(4);
				if(excelCell!=null) {
					XSSFCellStyle cs = excelCell.getCellStyle();
					XSSFFont font = cs.getFont();
					if(excelCell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING) {
						if(!excelCell.getStringCellValue().equals("")&&font.getFontHeightInPoints() == 26) {
							rowList.add(row.getCell(4).getStringCellValue());
						}
					}					
				}
			}

			for(org.apache.poi.ss.usermodel.Row row : excelWorkSheet) {
				for(int cellPos : subHeadingColumn) {
					excelCell = (XSSFCell) row.getCell(cellPos);
					if(excelCell!=null) {
						XSSFCellStyle cs = excelCell.getCellStyle();
						XSSFFont font = cs.getFont();
						if(excelCell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING) {
							if(!excelCell.getStringCellValue().equals("")&&font.getFontHeightInPoints() == 16) {
								rowList.add(row.getCell(cellPos).getStringCellValue());
							}
						}					
					}
				}
			}
			return rowList;
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to retrieve headings from mock excel");
			return null;
		}
	}
	
	public static Map<String, ArrayList<String>> getCSVMapData() {
		return backOfficeOOQData;
	}

	/**
	 * This method fetches andprints the cell values
	 * @param file path of downloaded result file
	 * @lastModifiedBy sagrawal
	 * @throws Exception
	 */
	public static void fetchFromResultFile(String filePath) throws Throwable {		
		FileInputStream fs = new FileInputStream(filePath);
		//Creating a workbook
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(0);
		System.out.println(sheet.getRow(0).getCell(0)+ " is " +sheet.getRow(1).getCell(0));
		System.out.println(sheet.getRow(0).getCell(1)+ " is " +sheet.getRow(1).getCell(1));
		System.out.println(sheet.getRow(0).getCell(2)+ " is " +sheet.getRow(1).getCell(2));
		System.out.println(sheet.getRow(0).getCell(3)+ " is " +sheet.getRow(1).getCell(3));
		System.out.println(sheet.getRow(0).getCell(4)+ " is " +sheet.getRow(1).getCell(4));
		System.out.println(sheet.getRow(0).getCell(5)+ " is " +sheet.getRow(1).getCell(5));
		System.out.println(sheet.getRow(0).getCell(6)+ " is " +sheet.getRow(1).getCell(6));
		System.out.println(sheet.getRow(0).getCell(7)+ " is " +sheet.getRow(1).getCell(7));
		System.out.println(sheet.getRow(0).getCell(8)+ " is " +sheet.getRow(1).getCell(8));
 	}	
}
