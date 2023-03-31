package com.twoplugs.pageObjects;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	 
	public LoginPage(WebDriver class_drvier) {
		this.driver = class_drvier;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='email']")
	WebElement username;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//span[contains(text(),'LOG IN')]")
	WebElement login_btn;
	
	
	public void input_username(String usernameInput) {
		username.sendKeys(usernameInput);
	}
	
	public void input_password(String passwordInput) {
		password.sendKeys(passwordInput);
	}
	
	public void clear_field() {
		username.clear();
		password.clear();
	}
	
	public void click_login() {
		login_btn.click();
	}
	
	public void login_with_valid_data() {
		username.sendKeys("Linet");
		password.sendKeys("twoplugs2");
		login_btn.click();
		
	}
}
