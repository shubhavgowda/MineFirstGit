package com.tutorialsninja.qa.utils;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

public class UtilitiesMethods {

	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_WAIT_TIME=5;
	public  static String  generateEmailWithTimeStamp() {
		Date date=new Date();
		String timestamp= date.toString().replace(" " , "_").replace(":" , "_");
		return "shubhasindhu877"+timestamp+"@gmail.com";
	}
	public static Object [][] getTestDataFromExcel(String sheetName) {
		File excelFile=new File( System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\TutorialsNinjaTestdata..xlsx");
		XSSFWorkbook workbook=null;
		try {
			FileInputStream fisExcel=new FileInputStream(excelFile);
			workbook=new XSSFWorkbook(fisExcel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet=workbook.getSheet(sheetName);

		int row=sheet.getLastRowNum();
		int colm=sheet.getRow(0).getLastCellNum();

		Object [][] data=new Object[row][colm];
		for(int i=0;i<row;i++) {
			XSSFRow rows =sheet.getRow(i+1);

			for(int j=0;j<colm;j++) {
				XSSFCell cell=rows.getCell(j);
				CellType cellType = cell.getCellType();

				switch(cellType) {
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;

				case NUMERIC:
					data[i][j]= Integer .toString((int)cell.getNumericCellValue());
					break;
				}
			}
		}
		return data;
	}	
}