package com.twoplugs.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignOutTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setup (){
        System.setProperty("webdriver.chrome.driver", "C:\\juan\\Tools\\BrowsersExeForSelenium\\Chrome\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void SignOutAction() throws InterruptedException {
        driver.get("https://qatest.twoplugs.com/login");
        Thread.sleep(1000);
        WebElement UserName = driver.findElement(By.id("signInEmail"));
        UserName.sendKeys("Linet");

        WebElement Password = driver.findElement(By.id("signInPassword"));
        Password.sendKeys("twoplugs2");

        WebElement LoginButton = driver.findElement(By.className("w-btn-success"));
        LoginButton.click();

        Actions action = new Actions(driver);

        WebElement HiCustomer = driver.findElement(By.partialLinkText("Hi"));
        action.clickAndHold(HiCustomer).perform();

        Thread.sleep(1000);

        WebElement SignOut =driver.findElement(By.partialLinkText("Sign"));

        SignOut.click();

        driver.quit();

    }
}
