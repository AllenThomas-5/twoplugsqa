package com.twoplugs.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {

	WebDriver driver;
	 
	public ProfilePage(WebDriver class_drvier) {
		this.driver = class_drvier;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@class='nav navbar-nav']//li[4]")
	WebElement profile_link_menubar;
	
	@FindBy(xpath="//ul[@class='nav navbar-nav']//li[4]/ul/li[1]/a")
	WebElement profile_link;
	
	
	
	@FindBy(xpath="//div[@class='text-center']//a[@href='/addservices/1']")
	WebElement new_service_btn;
	
	
	public void goto_profile_page() {
		profile_link_menubar.click();
		profile_link.click();
	}
	
	public void create_new_service() {
		new_service_btn.click();
	}
}



