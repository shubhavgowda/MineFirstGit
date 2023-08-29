package com.tuturialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	@FindBy(xpath="//span[text()='My Account']")
	private	WebElement myAccountDropDown;

	@FindBy(xpath="//a[text()='Login']")
	private WebElement loginOption;

	@FindBy(linkText="Register")
	private	WebElement registerOptions;

	@FindBy(name="search")
	private WebElement searchBoxField;

	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchButton;

	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public void clickOnMyAccount() {
		myAccountDropDown.click();
	}
	public void selectLoginOption() {
		loginOption.click();

	}
	public void selectRegisterOption() {
		registerOptions.click();
	}
	public void enterProductIntoSearchBoxTextField(String validProduct) {
		searchBoxField.sendKeys(validProduct);
	}
	public void clickOnSearchButton() {
		searchButton.click();
	}

}
