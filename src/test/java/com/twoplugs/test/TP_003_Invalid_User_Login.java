package com.twoplugs.test;

import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TP_003_Invalid_User_Login {


	WebDriver driver;
	String rootPath;
	Logger log = LogManager.getLogger(InitialTest.class);
	
	@BeforeSuite 
	public void loadLoginPage()  {
		System.out.println("Test1");
		//Set root path
		rootPath = System.getProperty("user.dir");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://qatest.twoplugs.com/login");
		driver.manage().window().maximize();
		log.info("Initiated The Suite");

		

	}
	  

	@Test(dataProvider = "InvalidLoginData")
	@Parameters ({"username","password"})
	public void signupInvalidUsername(String username, String password) throws InterruptedException {
		System.out.println("SignUp Invalid Username");
		

		
		//Expected Error Message
		//String expectedErrorMessage = "INVALID EMAIL/PASSWORD.";

		
		// Get the Username and Password element
		WebElement username_element = driver.findElement(By.xpath("//input[@name='email']"));
		WebElement password_element = driver.findElement(By.xpath("//input[@name='password']"));
		WebElement login_btn = driver.findElement(By.xpath("//span[contains(text(),'LOG IN')]"));
		
		//Enter the incorrect password
		username_element.sendKeys(username);
		password_element.sendKeys(password);
		login_btn.click();
		Thread.sleep(5000);
		
		
	//Check Error Message is Displayed
		
		Boolean invalid_alert =  driver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).isDisplayed();
		assertTrue(invalid_alert);
	}
	
	//Data Provider for Login credentials
	@DataProvider(name = "InvalidLoginData")
	String[][] loginData_method(){
		String[][]  data = {{"1234@123.com","12345"}};
		return data;
	}

	@AfterSuite
	public void closeApplication() throws InterruptedException {
		
		System.out.println("Close Browser");
		Thread.sleep(3000);
		driver.quit();
		
	}
	
}
