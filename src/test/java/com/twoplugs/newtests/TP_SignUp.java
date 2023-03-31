package com.twoplugs.newtests;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.testplugs.utils.ScreenshotClass;
import com.testplugs.utils.excelDataProvider;
import com.twoplugs.newtests.BaseTest;

import com.twoplugs.pageObjects.SignUpPage;

/* 
 * Test for
 * TP_022 
 * TP_23 
 * TP_25 
 * TP_26 
 * TP_27
 * TP_28
 * TP_29
 * TP_30
 * TP_31
 * TP_32
 * 
 * */


public class TP_SignUp extends BaseTest {


	// Created Extent Report Test Instance for Login
	ExtentTest signUpTest;

	// Clear Data
	@BeforeMethod
	public void clearData() {

		// Go to Sign Up page
		driver.get("https://qatest.twoplugs.com/signup");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Clear data with Login Page Object
		SignUpPage signup_page = new SignUpPage(driver);
		signup_page.clear_field();

	}

	

	@Test(dataProvider = "ExcelSignUpData")
	public void signupInvalidUsername(String username, String password) throws InterruptedException, IOException {
		SoftAssert softassert = new SoftAssert();
		try {
			System.out.println("SignUp Invalid Username");

			//  SignUp Page Object
			SignUpPage signup_page = new SignUpPage(driver);
			
			// Create a Signup Test node for Extent Reports
			signUpTest = extent.createTest(baseBrowser + " Verify Signup Page").assignAuthor("Allen").assignCategory("Functional Test");
			
			
			
			// Enter the incorrect username & password;
			signup_page.input_username(username);
			signUpTest.info("Entered Username " + username);
			signup_page.input_password(password);
			signUpTest.info("Entered Password " + password);
			signup_page.click_btn();
			signUpTest.info("Clicked the SignUp Button");
			
			// Wait for it
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

				
		//Check Error Message is Displayed
			
			Boolean errorMessage_isVisible =  driver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).isDisplayed();
			//assertTrue(errorMessage_isVisible);
			softassert.assertTrue(errorMessage_isVisible);
			
		}
		catch (Exception e)
		{
			 //Assert.fail("No Alert Message", e);
				softassert.assertFalse(false);
				signUpTest.fail("No Error Message");
				ScreenshotClass.takeScreenshot(driver, "SignUp Test", signUpTest);
		}
		



	}
	
	
	//Data Provider for SignUp credentials
	@DataProvider(name = "signUpData")
	String[][] signUpData_method(){
		String[][]  data = { {"1234@123.com","12345"},{"!@##$%^","abcdefg"},{"123456","@#$%^^((&^("}};
		return data;
	}
	
	
	// ExcelData Provider for SignUp credentials
	@DataProvider(name = "ExcelSignUpData")
	Object[][] signUpXlData_method() {
		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath + "\\src\\test\\java\\com\\twoplugs\\testData\\TestData.xlsx";
		String sheetName = "SignUp";

		Object[][] data = excelDataProvider.signUpTestData(excelPath, sheetName);
		return data;
	}
	
}
