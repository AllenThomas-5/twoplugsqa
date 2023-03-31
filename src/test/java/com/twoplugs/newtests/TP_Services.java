package com.twoplugs.newtests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.testplugs.utils.JsonDataProvider;
import com.testplugs.utils.ScreenshotClass;
import com.testplugs.utils.excelDataProvider;
import com.twoplugs.pageObjects.LoginPage;
import com.twoplugs.pageObjects.ProfilePage;
import com.twoplugs.pageObjects.ServicePage;
import com.twoplugs.pageObjects.SignUpPage;

public class TP_Services extends BaseTest {
	
	// Created Extent Report Test Instance for Login
		ExtentTest serviceTest;
		

		
		//Test to Login Valid User

		@Test(priority=1)
		public void loginValidUsername() {
			
			driver.get("https://qatest.twoplugs.com/login");
			driver.manage().window().maximize();
			// Login to the Website
			LoginPage login_page = new LoginPage(driver);
			login_page.login_with_valid_data();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


		}
		
		
		// Test to search for services after logging in
		@Test(priority=2)
		public void goToProfilePage() {
			
			//Go To Profile Page
			ProfilePage profile_page = new ProfilePage(driver);
			profile_page.goto_profile_page();
			//Wait For It
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		
		@Test(priority=3)
		public void creatNewService() {
			
			// Create a Service Test node for Extent Reports
			serviceTest = extent.createTest(baseBrowser + " Verify Adding Service")
								.assignAuthor("Allen")
								.assignCategory("Functional Test");

			
			//Intiate Page Objects
			ProfilePage profile_page = new ProfilePage(driver);
			ServicePage service_page = new ServicePage(driver);
			
			SoftAssert softassert = new SoftAssert();

			
			//Click Create new Service Button
			profile_page.create_new_service();

			//Wait For It
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			
			//Create Service Page
			
			//Title || Description || Category || Sub Category ||Price
			service_page.input_title("")
						.input_service_description("Lessons to Rock and Roll")
						.input_service_category("Misc")
						.input_service_sub_category("Misc")
						.input_service_price("100")
						.input_service_refund_valid("0")
						.input_service_video_url("https://www.youtube.com/watch?v=8fWHk7OZ12o")
						.click_create_service();
			
			serviceTest.info("Entered Service Details ");
			//Wait For It
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));		
			
			// Check Error Message || Check Service is Created
			try {
				Boolean serviceErrorMessage_isVisible = driver
						.findElement(By.xpath("//div[@class=\"alert alert-danger\"]/ul")).isDisplayed() ? true
								: false;
				if (serviceErrorMessage_isVisible) {
			
					softassert.assertFalse(false);
					serviceTest.fail("Error in Creating Service Page ");
					ScreenshotClass.takeScreenshot(driver, "Create Service", serviceTest);
				}
			} catch (Exception e) {

				Boolean serviceInformation_isVisible = driver.findElement(By.xpath("//div[@class=\"caption\"]"))
						.isDisplayed() ? true : false;
				softassert.assertTrue(serviceInformation_isVisible);
				serviceTest.pass("Service Created Sucessfully ");
				e.printStackTrace();

			}
			
	}
		
		// ExcelData Provider for Login credentials
		@DataProvider(name = "JSONData")
		Object[] createService_method() throws IOException, ParseException {
		

			Object[] data = JsonDataProvider.jsonDataProviderService();
			return data;
		}
		

}
