package com.twoplugs.test;

import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TP001_Singup_Invalid_User {


	WebDriver driver;
	String rootPath;
	Logger log = LogManager.getLogger(InitialTest.class);
	
	//Function to launch the Browser
	@BeforeSuite 
	public void loadLoginPage()  {
		System.out.println("Test1");
		//Set root path
		rootPath = System.getProperty("user.dir");
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.get("https://qatest.twoplugs.com/login");
		driver.manage().window().maximize();
		log.info("Initiated The Suite");
	}
	  

	@Test(dataProvider = "signUpData")
	public void signupInvalidUsername(String username, String password) throws InterruptedException {
		
		try {
			System.out.println("SignUp Invalid Username");

			
			// Get the Username and Password element
			WebElement username_element = driver.findElement(By.xpath("//input[@name='email']"));
			WebElement password_element = driver.findElement(By.xpath("//input[@name='password']"));
			WebElement login_btn = driver.findElement(By.xpath("//span[contains(text(),'SIGN UP')]"));
			
			//Enter the incorrect password
			username_element.sendKeys(username);
			password_element.sendKeys(password);
			login_btn.click();
			Thread.sleep(2000);
				
		//Check Error Message is Displayed
			
			Boolean errorMessage_isVisible =  driver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).isDisplayed();
			assertTrue(errorMessage_isVisible);
			
		}
		catch (Exception e)
		{
			 Assert.fail("No Alert Message", e);
		}
		



	}

	//Data Provider for SignUp credentials
	@DataProvider(name = "signUpData")
	String[][] signUpData_method(){
		String[][]  data = { {"1234@123.com","12345"},{"!@##$%^","abcdefg"},{"123456","@#$%^^((&^("}};
		return data;
	}
	
	
	@AfterSuite
	public void closeApplication() throws InterruptedException {
		
		System.out.println("Close Browser");
		Thread.sleep(3000);
		driver.quit();
		
	}
	
}
