package com.testplugs.utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtility {

	static String projectPath;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	//Creating the constructor
	public excelUtility(String excelPath, String sheetName) {
		projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		try {
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
	
	}

	// Function to get row counts
	public static int getRowCount() {
		int rowCount =0;
		try {
			 rowCount = sheet.getPhysicalNumberOfRows();
			System.out.println("No of Rows " + rowCount);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return rowCount;
	}

	// Function to get Column Counts

	public static int getColCount() {
		int colCount = 0;

		try {

			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("No of Columns " + colCount);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return colCount;
	}

	public static String  getCellDataString(int rowNum, int colNum) {
		String cellData ="";
		try {
//		//	workbook = new XSSFWorkbook(
//					"E:\\eclipse-workspace\\twoplugs-automation\\src\\test\\java\\com\\twoplugs\\testData\\TestData.xlsx");
//			sheet = workbook.getSheet("Sheet1");
			// Cell Data
			 cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			//System.out.println(cellData);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		
		return cellData;
	}

	// Function to get Number Cell Value
	public static double getCellDataNumber(int rowNum, int colNum) {
		double cellData = 0;
		try {
//			workbook = new XSSFWorkbook(
//					"E:\\eclipse-workspace\\twoplugs-automation\\src\\test\\java\\com\\twoplugs\\testData\\TestData.xlsx");
//			sheet = workbook.getSheet("Sheet1");
			// Cell Data
			 cellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
			System.out.println(cellData);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return cellData;
	}

}
