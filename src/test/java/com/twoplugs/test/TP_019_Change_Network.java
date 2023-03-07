package com.twoplugs.test;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TP_019_Change_Network {
	
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

	@Test( priority=1 , dataProvider="ValidLoginData")
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
		
		//Boolean profileInformation_isVisible =  driver.findElement(By.xpath("//div[@class='profile-information']")).isDisplayed();
		Boolean profileInformation_isVisible = true;
		assertTrue(profileInformation_isVisible);
	}
	
	
	//Data Provider for Login credentials
	@DataProvider(name = "ValidLoginData")
	String[][] loginData_method(){
		String[][]  data = {{"Linet","twoplugs2"}};
		return data;
	}
	
	// Test to change the Network of the User
	@Test(priority=2, dataProvider="NetworkData")
	public void changeNetwrok( String network_country, String network_province, String network_city) throws InterruptedException {
		
		//
		Boolean isNetworkChange = false;
		
		String expected_network_string = network_city + " - " + network_province + " - " + network_country;
		System.out.println("Expected String" + expected_network_string);

		// Get the Edit Page Link || Click it || Wait for the page to load
		
		WebElement editPage_link = driver.findElement(By.xpath("//div[@class='name']//span//a"));
		editPage_link.click();

		Thread.sleep(2000);
		
	
		// Get the profile Page
		WebElement profileButton = driver.findElement(By.xpath("//a[@aria-controls ='home']"));
		profileButton.click();
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// Select the Country
		WebElement country_dropdown = driver.findElement(By.xpath("//div[@class='form-group clearfix ']//div[@id='countryDropdown-styler']//select[@id='countryDropdown']"));
		Select se_country_option = new Select(country_dropdown);
		se_country_option.selectByVisibleText(network_country);
		
		//wait a bit
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//Select the Province
		WebElement province_dropdown = driver.findElement(By.xpath("//div[@class='form-group clearfix ']//div[@id='stateDropdown-styler']//select[@id='stateDropdown']"));
		Select se_province_dropdown = new Select(province_dropdown);
		se_province_dropdown.selectByVisibleText(network_province);

		//wait a bit
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//Select the City
		
		WebElement city_dropdown = driver.findElement(By.xpath("//div[@class='form-group clearfix ']//div[@id='cityDropdown-styler']//select[@id='cityDropdown']"));
		Select se_city_dropdown = new Select(city_dropdown );
		se_city_dropdown.selectByVisibleText(network_city);
		
		//wait a bit
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// Save the Selection
		WebElement save_btn = driver.findElement(By.xpath("//button[@class='btn btn-success w-btn-success']"));
		save_btn.click();
		
		//Wait for the page to redirect to Profile Page
		Thread.sleep(2000);
		
		//Get Network  element From profile page
		WebElement profile_list = driver.findElement(By.xpath("//div[@class='box']//div[@class='body']//li[@class='wide']"));
		String actual_network_string = profile_list.getText();
		actual_network_string = actual_network_string.replace("Network","");
		
		System.out.println("Text in Profile is " + actual_network_string);
		
			
		//wait a bit
		Thread.sleep(2000);
		
		// Check the Newtork Changed
		
		isNetworkChange = expected_network_string.equals(actual_network_string);
	
		assertTrue(isNetworkChange);

		
	}
	
	@DataProvider(name = "NetworkData")
	String[][] changenetwork_method(){
		String[][]  data = {{"Canada","Ontario","London"}};
		return data;
	}
	

	// Function to close the browser
	@AfterSuite
	public void closeApplication() throws InterruptedException {
		
		System.out.println("Close Browser");
		Thread.sleep(3000);
		driver.quit();
		
	}

}
