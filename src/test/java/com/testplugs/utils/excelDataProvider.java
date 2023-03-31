package com.testplugs.utils;

public class excelDataProvider {
	static String projectPath;
	
	public static void main(String[] args) {
		projectPath = System.getProperty("user.dir");
		String excelPath = projectPath + "\\src\\test\\java\\com\\twoplugs\\testData\\TestData.xlsx";
		String sheetName = "Sheet1";
		
		loginTestData(excelPath,sheetName);
	}
	
	// Login Test
	public static Object[][] loginTestData(String excelPath, String sheetName) {
		
		excelUtility testDataExcel = new excelUtility(excelPath, sheetName);
		
		int rowCount = testDataExcel.getRowCount();
		int colCount = testDataExcel.getColCount();
		
		//Object Data Array
		Object data[][] = new Object[rowCount-1][colCount];
		
		for(int i =1 ;i<rowCount;i++) {
			for(int j=0; j<colCount;j++) {
				String xlData = testDataExcel.getCellDataString(i, j);
				System.out.print(xlData+"|");
				data[i-1][j] = xlData;
			}
			System.out.println();
		}
		
		return data;
	}
	
	
	// SignUp Test
	public static Object[][] signUpTestData(String excelPath, String sheetName) {
		
		excelUtility testDataExcel = new excelUtility(excelPath, sheetName);
		
		int rowCount = testDataExcel.getRowCount();
		int colCount = testDataExcel.getColCount();
		
		//Object Data Array
		Object data[][] = new Object[rowCount-1][colCount];
		
		for(int i =1 ;i<rowCount;i++) {
			for(int j=0; j<colCount;j++) {
				String loginData = testDataExcel.getCellDataString(i, j);
				System.out.print(loginData+"|");
				data[i-1][j] = loginData;
			}
			System.out.println();
		}
		
		return data;
	}
	
	
	
}
 