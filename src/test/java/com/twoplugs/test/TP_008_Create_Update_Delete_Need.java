package com.twoplugs.test;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TP_008_Create_Update_Delete_Need {
	WebDriver driver;
	String rootPath;
	Logger log = LogManager.getLogger(InitialTest.class);
	
	
	//A method to click Profile in menubar
	public void goToProfilePageMethod() {
		
		WebElement profile_link_menubar = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']//li[4]"));
		profile_link_menubar.click();
		
		WebElement profile_link = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']//li[4]/ul/li[1]/a"));
		profile_link.click();
		
	}
	
	
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

	@Test(priority=1, dataProvider="ValidLoginData")
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
	
	// Test to search for services after logging in
	@Test(priority=2)
	public void goToProfilePage() {
		
		WebElement profile_link_menubar = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']//li[4]"));
		profile_link_menubar.click();
		
		WebElement profile_link = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']//li[4]/ul/li[1]/a"));
		profile_link.click();
		
	}
	
	// Test to Create New Service
	@Test(priority=3)
	public void creatNewNeed() throws InterruptedException {
		
		Boolean isCreated = true;
		
		//Get Create new Need Button
		WebElement new_need_btn = driver.findElement(By.xpath("//div[@class='text-center']//a/span[contains(text(),'New NEED')]"));
		new_need_btn.click();
		
		//Wait For It
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//At Create Need Page
		
		//Title
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Drum Tutor");
		//Description
		driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Need to Learn Drums");
		
		//Category
		WebElement category_dropdown = driver.findElement(By.xpath("//select[@name='category_id']"));
		Select se_category_dropdown = new Select(category_dropdown );
		se_category_dropdown.selectByVisibleText("Misc");
		//SubCategory
		WebElement sub_category_dropdown = driver.findElement(By.xpath("//select[@name='subcategory_id']"));
		Select se_sub_category_dropdown = new Select(sub_category_dropdown );
		se_sub_category_dropdown.selectByVisibleText("Misc");
		
		//Youtube
		driver.findElement(By.xpath("//input[@name='videourl']")).sendKeys("https://www.youtube.com/watch?v=8fWHk7OZ12o");
		//Create Button
		driver.findElement(By.xpath("//div[@class=\"activatePanel clearfix\"]//ul//li[2]//button")).click();
	
		//Wait For It
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		//Go To Profile page
		
		goToProfilePageMethod();
		
		Thread.sleep(2000);
		
		assertTrue(isCreated);
	}

	// Test to edit service
	@Test(priority=4)
	public void editNeed() {
		
		Boolean isUpdated = true;
		
		//Click on Edit Link
		driver.findElement(By.xpath("//div[@class='box theme-1']//table//ul//li//a")).click();
		
		// Change the Title
		driver.findElement(By.xpath("//input[@name='name']")).clear();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Guitar Tutor");
		
		// Click on Save
		driver.findElement(By.xpath("//ul[@class='line-btn pull-right']//li[2]//button")).click();
		
		//Check the title
		
		String newTitle = driver.findElement(By.xpath("//div[@class=\"catering\"]//div[@class=\"caption\"]")).getText();
		System.out.println("The Change Title " + newTitle);
		
		//Wait For It
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Go To Profile page
		goToProfilePageMethod();
		
		assertTrue(isUpdated);
		
	}
	
	
	// Test to delete service
	@Test(priority=5, enabled=true)
	public void deleteNeed() {
		
		String expected_message = "Need has been deleted";
		// Got To Profile Page
		WebElement profile_link_menubar = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']//li[4]"));
		profile_link_menubar.click();
		
		WebElement profile_link = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']//li[4]/ul/li[1]/a"));
		profile_link.click();
		
		//Wait For It
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Click on Delete Link
		driver.findElement(By.xpath("//div[@class='box theme-1']//table//ul//li[2]")).click();
		
		//Wait For It
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// Click on Delete button from Alert
		driver.findElement(By.xpath("//div[@id='deleteservice']//div//div//ul//li[2]//a[@id='btn_deleteService']")).click();		
		
		//Wait For It
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//Check Delete Message Modal
		String delete_message = driver.findElement(By.xpath("/html/body/div[7]/div/div")).getText();
		System.out.println("The deleted message is "+delete_message);
		
		Boolean isDeleted = expected_message.equals(delete_message);
		
		assertTrue(isDeleted);
	}




	// Function to close the browser
	@AfterSuite
	public void closeApplication() throws InterruptedException {
		
		System.out.println("Close Browser");
		Thread.sleep(3000);
		driver.quit();
		
	}
}
