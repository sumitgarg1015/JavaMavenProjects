package rough;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class RoughTest {

	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static int rowCount;
	private static int colCount;
	private static Object[][] datatable;
	
	public static void main(String[] args) throws Throwable{
		
		String sheetName="calculateTax";
		
		
		workbook = new XSSFWorkbook("./src/test/resources/testdata/testdata.xlsx");
		sheet = workbook.getSheet(sheetName);
		
		rowCount = sheet.getPhysicalNumberOfRows();
		colCount = sheet.getRow(0).getLastCellNum();
		datatable = new Object[rowCount][colCount];
		
		for(int i=0;i<rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				System.out.print(sheet.getRow(i).getCell(j));
				System.out.print("\t");
				datatable[i][j]=sheet.getRow(i).getCell(j);
			}
			System.out.print("\n");
			
		}
		
		sheet=null;
		workbook.close();
		
		writeExcel(datatable);
	}
	
	public static void writeExcel(Object[][] data) throws Throwable{
		
		String sheetName="calculateTax";
		
		workbook = new XSSFWorkbook();
		FileOutputStream fileos = new FileOutputStream("./src/test/resources/testdata/results.xlsx");
		
		sheet = workbook.createSheet(sheetName);
		
		rowCount = data.length;
		
		colCount = data[0].length;
		
		
		for(int i=0;i<rowCount;i++) {
			
			XSSFRow row = sheet.createRow(i);
			for(int j=0;j<colCount;j++) {
				row.createCell(j).setCellValue(data[i][j].toString());
			}
			
		}
		
		
		sheet.getRow(0).createCell(colCount).setCellValue("Text_Rate");
		sheet.getRow(1).createCell(colCount).setCellValue("4.55%");
		sheet.getRow(2).createCell(colCount).setCellValue("6.5%");
		
		workbook.write(fileos);
		
		sheet=null;
		workbook.close();
		
	}
	

}
