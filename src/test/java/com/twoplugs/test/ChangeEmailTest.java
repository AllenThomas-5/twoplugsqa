package com.twoplugs.test;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Scanner;

public class ChangeEmailTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\juan\\Tools\\BrowsersExeForSelenium\\Chrome\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void ChangeEmailAction() throws InterruptedException {
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


        WebElement SettingsMenu = driver.findElement(By.linkText("Settings"));
        SettingsMenu.click();


        WebElement Email = driver.findElement(By.linkText("Email"));
        Email.click();

        Thread.sleep(1000);

        WebElement NewEmail = driver.findElement(By.name("new_email"));
        NewEmail.sendKeys("juanxiong2019@outlook.com");

        WebElement ConfirmEmail = driver.findElement(By.name("confirm_email"));
        ConfirmEmail.sendKeys("juanxiong2019@outlook.com");
        Thread.sleep(1000);

        //  SaveChangesButton.click();
        driver.quit();

    }

}


