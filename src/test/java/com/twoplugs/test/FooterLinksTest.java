package com.twoplugs.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FooterLinksTest {
    WebDriver driver;

    @BeforeTest
    public void setup (){
        System.setProperty("webdriver.chrome.driver", "C:\\juan\\Tools\\BrowsersExeForSelenium\\Chrome\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    
    public void FooterLinksChecking() throws InterruptedException {
        driver.get("https://qatest.twoplugs.com/login");
        Thread.sleep(1000);
        WebElement UserName = driver.findElement(By.id("signInEmail"));
        UserName.sendKeys("Linet");

        WebElement Password = driver.findElement(By.id("signInPassword"));
        Password.sendKeys("twoplugs2");

        WebElement LoginButton = driver.findElement(By.className("w-btn-success"));
        LoginButton.click();

        Thread.sleep(1000);

        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        Thread.sleep(1000);

        WebElement FeedbackFooterLink=driver.findElement(By.xpath("/html/body/footer/div/ul/li[1]/a"));
        FeedbackFooterLink.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://qatest.twoplugs.com/contactus/feedback", "FooterLink FEEDBACK validation");
        Thread.sleep(1000);
        driver.navigate().back();

        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        Thread.sleep(2000);
        WebElement SupportFooterLink =driver.findElement(By.xpath("/html/body/footer/div/ul/li[2]/a"));
        SupportFooterLink.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://qatest.twoplugs.com/help", "FooterLink SUPPORT validation");
        Thread.sleep(2000);
        driver.navigate().back();

        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/footer/div/ul/li[3]/a")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://qatest.twoplugs.com/terms#privacy", "FooterLink Privacy policy validation");
        Thread.sleep(2000);
        driver.navigate().back();

        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/footer/div/ul/li[4]/a")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://qatest.twoplugs.com/terms", "FooterLink Terms of use validation");
        Thread.sleep(2000);
        driver.navigate().back();

        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        Thread.sleep(3000);
        driver.findElement(By.className("download-btn")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://apps.apple.com/ca/app/twoplugs/id1257831625", "FooterLink App Store validation");
        Thread.sleep(2000);
        driver.navigate().back();

        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        Thread.sleep(3000);
        driver.findElement(By.className("download-btn2")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://play.google.com/store/apps/details?id=com.twoplugs.twoplugs", "FooterLink Google Play validation");
        Thread.sleep(2000);
        driver.navigate().back();


        driver.quit();


    }
}
