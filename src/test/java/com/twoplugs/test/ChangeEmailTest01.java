package com.twoplugs.test;


import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ChangeEmailTest01 {
    WebDriver driver;
    Logger log = LogManager.getLogger(ChangeEmailTest01.class);
	

    @BeforeTest
    public void setup() {
 
        
        WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.get("https://qatest.twoplugs.com/login");
		driver.manage().window().maximize();
		log.info("Initiated The Suite");
		
        driver.manage().window().maximize();
    }

    
	@Test(priority=1)
	public void loginValidUsername() throws InterruptedException {
		System.out.println("Login Valid User function");
		
    	driver.get("https://qatest.twoplugs.com/login");
        Thread.sleep(1000);
        WebElement UserName = driver.findElement(By.id("signInEmail"));
        UserName.sendKeys("Linet");

        WebElement Password = driver.findElement(By.id("signInPassword"));
        Password.sendKeys("twoplugs2");

        WebElement LoginButton = driver.findElement(By.className("w-btn-success"));
        LoginButton.click();
	}
	
    
    
    
    
    
    
    @Test(priority=2,dataProvider="VariousEmailAddress")
    public void ChangeEmailAction01(String NewEmailAddress, String ConfirmEmailAddress) throws InterruptedException {
           	
     
    	//NewEmailAddress
    	System.out.println("The New Email Address" + NewEmailAddress);
    	
    	//ConfirmEmailAddress
    	System.out.println("The Confirm Email Address" + ConfirmEmailAddress);
    	System.out.println("------------------------------------------");
    	


        Actions action = new Actions(driver);

        //Select Hi and Select Settings
        WebElement HiCustomer = driver.findElement(By.partialLinkText("Hi"));
        action.clickAndHold(HiCustomer).perform();
        
        WebElement SettingsMenu = driver.findElement(By.linkText("Settings"));
        SettingsMenu.click();

        //Select the email option
        WebElement Email = driver.findElement(By.linkText("Email"));
        Email.click();

        Thread.sleep(1000);
        
        // Enter the e-mail details

        WebElement NewEmail = driver.findElement(By.name("new_email"));
        NewEmail.sendKeys(NewEmailAddress);

        WebElement ConfirmEmail = driver.findElement(By.name("confirm_email"));
        ConfirmEmail.sendKeys(ConfirmEmailAddress);
        
        
        //Click on the Save Button
        WebElement Save_btn = driver.findElement(By.xpath("//button[@class='btn btn-success w-btn-success']"));
        Save_btn.click();
        
        //Check New Email Required Message Exists
        Boolean newEmailErrorMessage = 
        			driver.findElements(By.xpath("/html/body/div[7]/div/form/div[1]/div/div[2]/div[2]/div[1]/div/p")).size() != 0;
        
    	//Assert Message
		if (newEmailErrorMessage) {
			assertTrue(newEmailErrorMessage, "New Email Error Exists");
		}
		else {
			assertFalse(newEmailErrorMessage, "Refund button Does not Exist");
		}
        
		 //Check Confirmed Email Required Message Exists
        Boolean confirmedEmailErrorMessage = 
        		driver.findElements(By.xpath("/html/body/div[7]/div/form/div[1]/div/div[2]/div[2]/div[2]/div/p")).size() != 0;
        
    	if (confirmedEmailErrorMessage) {
			assertTrue(confirmedEmailErrorMessage, "New Email Error Exists");
		}
		else {
			assertFalse(confirmedEmailErrorMessage, "Refund button Does not Exist");
		}
        
    	 //Check Password Required Message Exists
        Boolean passwordErrorMessage =
        		driver.findElements(By.xpath("/html/body/div[7]/div/form/div[1]/div/div[2]/div[4]/div/div/p")).size() != 0;
        		
	
     	if (passwordErrorMessage) {
			assertTrue(passwordErrorMessage, "New Email Error Exists");
		}
		else {
			assertFalse(passwordErrorMessage, "Refund button Does not Exist");
		}
        
     	
        Thread.sleep(2000);
        
        //Click Email Again
        WebElement Email2 = driver.findElement(By.linkText("Email"));
        Email2.click();
        
        
        
  

    }
    //          @AfterTest
//          public void tearDown(){
//        driver.quit();
//          }
    @DataProvider(name="VariousEmailAddress")
    public Object[][] VariousEmailAddress() {
        return new Object[][]{
                {"juanxiong2019@outlook.com", "juanxiong2019@outlook.com"},
                {"juanxiong2019@outlook.com", "1234@outlook.com"},
                {"juanxiong2019@outlook.com", " "},
                {" ", "1234@outlook.com"}
        };
    }
    
	
	@AfterSuite
	public void closeApplication() throws InterruptedException {
		
		System.out.println("Close Browser");
		Thread.sleep(3000);
		driver.quit();
		
	}

}

