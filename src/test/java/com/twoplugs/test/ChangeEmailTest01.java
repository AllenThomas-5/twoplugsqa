package com.twoplugs.test;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ChangeEmailTest01 {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        driver = new FirefoxDriver();
//        WebDriverManager.edgedriver().setup();
//        System.setProperty("webdriver.edge.driver", "C:\\juan\\Tools\\BrowsersExeForSelenium\\Edge\\edgedriver_win64\\msedgedriver.exe");
//        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test(dataProvider="VariousEmailAddress")
    public void ChangeEmailAction01(String NewEmailAddress, String ConfirmEmailAddress) throws InterruptedException {
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
        NewEmail.sendKeys(NewEmailAddress);

        WebElement ConfirmEmail = driver.findElement(By.name("confirm_email"));
        ConfirmEmail.sendKeys(ConfirmEmailAddress);
        Thread.sleep(1000);

        driver.quit();
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

}


