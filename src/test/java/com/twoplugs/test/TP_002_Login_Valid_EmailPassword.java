package com.twoplugs.test;


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
import com.twoplugs.pageObjects.LoginPage;


public class TP_002_Login_Valid_EmailPassword extends BaseTest {

	// Created Extent Report Test Instance for Login
	ExtentTest loginTest;

	// Clear Data
	@BeforeMethod
	public void clearData() {

		// Go to login page
		driver.get("https://qatest.twoplugs.com/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Clear data with Login Page Object
		LoginPage login_page = new LoginPage(driver);
		login_page.clear_field();

	}

	// Test to Login Valid User
	@Test(dataProvider = "ExcelLoginData")
	public void loginValidUsername(String username, String password) throws InterruptedException, IOException {

		// Create a Login Test node for Extent Reports
		loginTest = extent.createTest("Verify Login Page").assignAuthor("Allen").assignCategory("Functional Test");

		// Get Login Page Object
		LoginPage login_page = new LoginPage(driver);

		String expectedErrorMessage = "Invalid Email/Password.";
		SoftAssert softassert = new SoftAssert();

		// Enter the incorrect username & password;
		login_page.input_username(username);
		loginTest.info("Entered Username " + username);
		login_page.input_password(password);
		loginTest.info("Entered Password " + password);
		login_page.click_login();
		loginTest.info("Clicked the Login Button");

		// Wait for it
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Check Error Message || Check Profile is Displayed
		try {
			Boolean loginErrorMessage_isVisible = driver
					.findElement(By.xpath("//div[@class=\"alert alert-danger text-center\"]/div")).isDisplayed() ? true
							: false;
			if (loginErrorMessage_isVisible) {
				String actualErrorMessage = driver
						.findElement(By.xpath("//div[@class=\"alert alert-danger text-center\"]/div")).getText();
				Boolean isErrorMessage = expectedErrorMessage.equalsIgnoreCase(actualErrorMessage);
				softassert.assertFalse(isErrorMessage);
				loginTest.fail("Failed Login ");
				ScreenshotClass.takeScreenshot(driver, "LoginTest", loginTest);
			}
		} catch (Exception e) {

			Boolean profileInformation_isVisible = driver.findElement(By.xpath("//div[@class='profile-information']"))
					.isDisplayed() ? true : false;
			softassert.assertTrue(profileInformation_isVisible);
			loginTest.pass("Logged In Sucessfully ");
			e.printStackTrace();

		}

		;
	}

	// Data Provider for Login credentials
	@DataProvider(name = "ValidLoginData")
	String[][] loginData_method() {
		String[][] data = { { "Linet", "twoplugs2" } };
		return data;
	}

	// ExcelData Provider for Login credentials
	@DataProvider(name = "ExcelLoginData")
	Object[][] loginXlData_method() {
		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath + "\\src\\test\\java\\com\\twoplugs\\testData\\TestData.xlsx";
		String sheetName = "Sheet1";

		Object[][] data = excelDataProvider.loginTestData(excelPath, sheetName);
		return data;
	}

}
