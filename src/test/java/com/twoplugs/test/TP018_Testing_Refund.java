package com.twoplugs.test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TP018_Testing_Refund {
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
	//Test to Login Valid User

	@Test(priority=1 , dataProvider="ValidLoginData")
	public void loginValidUsername(String username, String password) throws InterruptedException {
		System.out.println("Login Valid User function");
		
		// Get the Username Password and Login button element
		WebElement username_element = driver.findElement(By.xpath("//input[@name='email']"));
		WebElement password_element = driver.findElement(By.xpath("//input[@name='password']"));
		WebElement login_btn = driver.findElement(By.xpath("//span[contains(text(),'LOG IN')]"));
		
		//Enter the incorrect password
		username_element.sendKeys(username);
		password_element.sendKeys(password);
		login_btn.click();
		Thread.sleep(5000);
		
		//Check Profile is Displayed
		
		Boolean profileInformation_isVisible =  driver.findElement(By.xpath("//div[@class='profile-information']")).isDisplayed();
		assertTrue(profileInformation_isVisible);
	}
	
	
	//Data Provider for Login credentials
	@DataProvider(name = "ValidLoginData")
	String[][] loginData_method(){
		String[][]  data = {{"Linet","twoplugs2"}};
		return data;
	}
	
	//Goto Transaction Page 
	
	// Test to search for services after logging in
	@Test(priority=2)
	public void goToTransactionPage() {
		
		WebElement transaction_link_menubar = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']//li[2]"));
		transaction_link_menubar.click();
		//Wait For It
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	// Check Refund Button
	@Test
	public void refundButton() {
		
		//Select Refund Button
		Boolean reund_button_present = driver.findElements(By.xpath("//table[@class='table-Colorful color-3 stacktable large-only']//tr[1]/td[7]/a[@class='servicerefund default']")).size() != 0;
		
		//Wait For It
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		// Check for Refund Button
		if (reund_button_present) {
			assertTrue(reund_button_present, "Refund button Exists");
			WebElement refund_button = driver.findElement(By.xpath("//table[@class='table-Colorful color-3 stacktable large-only']//tr[1]/td[7]/a[@class='servicerefund default']"));
			refund_button.click();
		}
		else {
			assertFalse(reund_button_present, "Refund button Does not Exist");
		}
	
	}
	
	// Function to close the browser
	@AfterSuite
	public void closeApplication() throws InterruptedException {
		
		System.out.println("Close Browser");
		Thread.sleep(3000);
		driver.quit();
		
	}
}
