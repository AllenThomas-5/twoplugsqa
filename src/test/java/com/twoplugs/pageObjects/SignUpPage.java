package com.twoplugs.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
	WebDriver driver;
	
	public SignUpPage(WebDriver class_drvier) {
		this.driver = class_drvier;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='email']")
	WebElement username;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//span[contains(text(),'SIGN UP')]")
	WebElement signUp_btn;
	
	
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
	
	public void click_btn() {
		signUp_btn.click();
	}
	
	
	
	
}
