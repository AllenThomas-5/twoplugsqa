package com.twoplugs.test;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	 
	WebDriver driver;
	String rootPath;
	Properties prop;
	ExtentSparkReporter sparkReport;
	ExtentReports extent;
	
	@BeforeSuite
	public void setup() {
		System.out.println("Before Suite ");
		// Setup Extent Reports
		sparkReport = new ExtentSparkReporter("extentReports/loginReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReport);

		// Set root path
		rootPath = System.getProperty("user.dir");
		
		
	}
	
	@BeforeClass
	public void setupDriver() {
		try {
			//Get the configuration properties
			FileInputStream fis = new FileInputStream(rootPath+ "\\src\\test\\java\\com\\testplugs\\utils\\config.properties");
			prop = new Properties();
			prop.load(fis);
			
			String browser = prop.getProperty("browser");
			String url = prop.getProperty("testurl");
			
			// Chrome Browser
			if(browser.equalsIgnoreCase("CH")) {
				
			System.out.println(browser);	
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			// Go to Website
			driver.get(url);
			driver.manage().window().maximize();
			
				
			}
			
			//FireFox
			if(browser.equalsIgnoreCase("FF")) {
				
				System.out.println(browser);	
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				// Go to Website
				driver.get(url);
				driver.manage().window().maximize();
						}
			
			//Edge
			if(browser.equalsIgnoreCase("ED")) {
				
				System.out.println(browser);	
				WebDriverManager.edgedriver().setup();
				driver =  new EdgeDriver();
				// Go to Website
				driver.get(url);
				driver.manage().window().maximize();
				
					}
				
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@AfterMethod
	public void flushReport() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		extent.flush();
	}
	
	@AfterSuite
	public void teardown() {
		System.out.println("Close Browser");
		driver.quit();

		
	}
}
