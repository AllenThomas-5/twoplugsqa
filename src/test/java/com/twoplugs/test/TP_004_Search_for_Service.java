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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TP_004_Search_for_Service {
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
		driver = new ChromeDriver();
		driver.get("https://qatest.twoplugs.com/login");
		driver.manage().window().maximize();
		log.info("Initiated The Suite");
	}
	  
	//Test to Login Valid User

	@Test(dataProvider="ValidLoginData")
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
		Thread.sleep(2000);
		
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
	
	// Test to serch for services after logging in
	@Test
	public void searchForServices() throws InterruptedException {
		// Get the search elements and click on the search button
		WebElement searchField = driver.findElement(By.xpath("//input[@id='exampleInputAmount']"));
		WebElement search_btn = driver.findElement(By.xpath("//button[@class='btn-search']"));
		searchField.sendKeys("Cleaning");
		Thread.sleep(2000);
		search_btn.click();
		Thread.sleep(2000);
		
		// Check if the search table exists
		boolean isResultTable = driver.findElement(By.xpath("//table[@class='result-table']")).isDisplayed();
		assertTrue(isResultTable);
	}

	// Function to close the browser
	@AfterSuite
	public void closeApplication() throws InterruptedException {
		
		System.out.println("Close Browser");
		Thread.sleep(3000);
		driver.quit();
		
	}
}
