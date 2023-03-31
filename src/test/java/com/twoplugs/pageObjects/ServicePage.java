package com.twoplugs.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

// Service Page Object Model using method Chaining

public class ServicePage {
	WebDriver driver;
	 
	public ServicePage(WebDriver class_drvier) {
		this.driver = class_drvier;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='name']")
	WebElement service_title;
	
	@FindBy(xpath="//textarea[@name='description']")
	WebElement service_description;
	
	@FindBy(xpath="//select[@name='category_id']")
	WebElement service_category_dropdown;
	
	@FindBy(xpath="//select[@name='subcategory_id']")
	WebElement service_sub_category_dropdown;
	
	@FindBy(xpath="//input[@name='price']")
	WebElement service_price;
	
	@FindBy(xpath="//input[@name='refund_valid']")
	WebElement service_refund_valid;
	
	@FindBy(xpath="//input[@name='videourl']")
	WebElement service_video_url;
	
	@FindBy(xpath="//div[@class='activatePanel clearfix']//button[@class='btn btn-success w-btn-success']")
	WebElement service_create_button;
	
	
	public ServicePage input_title(String serviceTitle) {
		service_title.sendKeys(serviceTitle);
		return this;
	}
	
	public ServicePage input_service_description(String serviceDescription) {
		service_description.sendKeys(serviceDescription);
		return this;
	}
	
	public ServicePage input_service_category(String serviceCategory) {
		Select se_category_dropdown = new Select(service_category_dropdown );
		se_category_dropdown.selectByVisibleText(serviceCategory);
		return this;
	}
	
	public ServicePage input_service_sub_category(String serviceSubCategory) {
		Select se_sub_category_dropdown = new Select(service_sub_category_dropdown );
		se_sub_category_dropdown.selectByVisibleText(serviceSubCategory);
		return this;
	}
	
	public ServicePage input_service_price(String servicePrice) {
		service_price.sendKeys(servicePrice);
		return this;
	}
	
	public ServicePage input_service_refund_valid(String serviceRfund) {
		service_refund_valid.sendKeys(serviceRfund);
		return this;
	}
	
	public ServicePage input_service_video_url(String serviceVideoUrl) {
		service_video_url.sendKeys(serviceVideoUrl);
		return this;
	}
	
	public ServicePage click_create_service() {
		service_create_button.click();
		return this;
	}
	
	
	
}
