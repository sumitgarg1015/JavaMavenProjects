package com.moneycontrol.utils;

import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelOps {

	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static int rowCount;
	private static int colCount;
	private static Object[][] datatable;
	private HashMap<String, String> map;
	
	@DataProvider(name="dp")
	public Object[][] readExcel(Method m) throws Throwable {

		String sheetName = m.getName();
		
		workbook = new XSSFWorkbook("./src/test/resources/testdata/testdata.xlsx");
		sheet = workbook.getSheet(sheetName);

		rowCount = sheet.getPhysicalNumberOfRows();
		colCount = sheet.getRow(0).getLastCellNum();
		
		datatable = new Object[rowCount-1][1]; //Reduce Rowcount by 1 for column Names

		for (int i = 1; i < rowCount; i++) {
			map = new HashMap<String, String>();
			
			for (int j = 0; j < colCount; j++) {
				//Storing values in the form of key value pairs where key is columnName and value is columnValue
				map.put(getCellData(sheet,0,j), getCellData(sheet,i,j)); 
			}
			
			datatable[i-1][0]=map;

		}

		sheet = null;
		workbook.close();
		
		return datatable;
	}

	public static void writeExcel(Object[][] data) throws Throwable {

		String sheetName = "calculateTax";

		workbook = new XSSFWorkbook();
		FileOutputStream fileos = new FileOutputStream("./src/test/resources/testdata/results.xlsx");

		sheet = workbook.createSheet(sheetName);

		rowCount = data.length;

		colCount = data[0].length;

		for (int i = 0; i < rowCount; i++) {

			XSSFRow row = sheet.createRow(i);
			for (int j = 0; j < colCount; j++) {
				row.createCell(j).setCellValue(data[i][j].toString());
			}

		}

		sheet.getRow(0).createCell(colCount).setCellValue("Text_Rate");
		sheet.getRow(1).createCell(colCount).setCellValue("4.55%");
		sheet.getRow(2).createCell(colCount).setCellValue("6.5%");

		workbook.write(fileos);

		sheet = null;
		workbook.close();

	}
	
	public String getCellData(XSSFSheet sheet, int row, int col) {
		
		String data="";
		data = sheet.getRow(row).getCell(col).toString();
		return data;
		
	}

}
