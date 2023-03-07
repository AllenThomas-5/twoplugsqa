package com.twoplugs.test;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

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

public class TP_011_Want_Button{
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
	
	// Test to serch for services after logging in
	@Test(priority=2, dataProvider="ServiceData")
	public void searchForServices( String serviceName) {
		// Get the search elements and click on the search button
		WebElement searchField = driver.findElement(By.xpath("//input[@id='exampleInputAmount']"));
		WebElement search_btn = driver.findElement(By.xpath("//button[@class='btn-search']"));
		searchField.sendKeys(serviceName);
		search_btn.click();
		
		// Check if the search table exists
		boolean isResultTable = driver.findElement(By.xpath("//table[@class='result-table']")).isDisplayed();
		assertTrue(isResultTable);
	}
	
	//Data Provider for Service
	@DataProvider(name = "ServiceData")
	String[][] serviceData_method(){
		String[][]  data = {{"Dental"}};
		return data;
	}
	
	@Test(priority=3)
	public void clickOnService() throws InterruptedException {
		
		Boolean isServiceDetails = true;
		
		//Select the Services Tab
		WebElement service_tab_element = driver.findElement(By.xpath("//div[@class='menu-new']//ul//li[@id='tabs']"));
		service_tab_element.click();
		
		Thread.sleep(5000);
		
		//Select the Link
		WebElement service_link = driver.findElement(By.xpath("//table//div//div[@class='fixWrapper']//div//a"));
		service_link.click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Check the service Page is Loaded || Get the Title of the Section
		WebElement service_detail_element = driver.findElement(By.xpath("//section[@class='viewService detailsSect serviceTheme']//div//div[@class='row box-group']//div//div//div//div[@class='help clearfix']"));
		String service_page_heading = service_detail_element.getText();
		System.out.println("The Heading is " + service_page_heading );
		
		
		assertTrue(isServiceDetails);
		
	}
	
	
	@Test(priority=4)
	public void clickOnWant() throws InterruptedException {
		
		Boolean isNegotiate = false;
		
		String expected_title = "Buy Service";
		
		//Select the Negotiate Button
		WebElement negotiate_button_element = driver.findElement(By.xpath("//section[@class='viewService detailsSect serviceTheme']//div//div[@class='text-right row']//div//a[@id='buy_button']"));
		negotiate_button_element.click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Check the Bid Modal Title
		WebElement bid_modal_element = driver.findElement(By.xpath("//div[@id='buyservicemodal']//div//div//div//h4[@class='modal-title']"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		String bid_modal_title = bid_modal_element.getAttribute("innerHTML");
		System.out.println("The Modal Heading is " + bid_modal_title );
		
		isNegotiate = expected_title.equals(bid_modal_title);
		
		assertTrue(isNegotiate);
		
	}


	// Function to close the browser
	@AfterSuite
	public void closeApplication() throws InterruptedException {
		
		System.out.println("Close Browser");
		Thread.sleep(3000);
		driver.quit();
		
	}
}
