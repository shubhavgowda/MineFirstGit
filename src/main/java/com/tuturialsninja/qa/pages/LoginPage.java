package com.tuturialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailAddressField;

	@FindBy(id="input-password")
	private WebElement passwordField;

	@FindBy(xpath="//input[@type='submit']")
	private	WebElement loginButton;

	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private	WebElement emailPasswordNotMatchingWarning; 

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public void enterEmailAddress(String email) {
		emailAddressField.sendKeys(email);
	}
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	public void clickOnLoginbutton() {
		loginButton.click();
	}
	public String retriveEmailPasswordNotMatchingWarningMessageText() {
		String warningText=emailPasswordNotMatchingWarning.getText();
		return warningText;








	}
}

