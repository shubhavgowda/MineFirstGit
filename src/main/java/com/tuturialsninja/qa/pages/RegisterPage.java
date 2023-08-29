package com.tuturialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;

	@FindBy(id="input-firstname")
	private	WebElement firstNameField;

	@FindBy(id="input-lastname")
	private	WebElement lastNameField;

	@FindBy(id="input-email")
	private WebElement emailIdField;

	@FindBy(id="input-telephone")
	private	WebElement telephoneField;

	@FindBy(id="input-password")
	private	WebElement passwordField;

	@FindBy(id="input-confirm")
	private	WebElement confirmPaswordField;

	@FindBy(name="agree")
	private	WebElement privacyPolicyField;

	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private 	WebElement yesNewsletterOption;

	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarningMessage;

	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private  WebElement privacyPolicyWarning;

	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;

	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephonWarning;

	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;


	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}
	public void enterLastName(String lastName)  {
		lastNameField.sendKeys(lastName);
	}
	public void enterEmailAddress(String validEmailID)  {
		emailIdField.sendKeys(validEmailID);
	}
	public void enterTelephoneNumber(String telephoneNumber) {

		telephoneField.sendKeys(telephoneNumber);
	}
	public void enterPassword(String validPassword) {
		passwordField.sendKeys(validPassword);
	}
	public void enterConfirmPassword(String validPassword) {
		confirmPaswordField.sendKeys(validPassword);
	}
	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}
	public void clickOnContinueButton() {
		continueButton.click();
	}
	public void selectYesnewsletterOption() {
		yesNewsletterOption.click();
	}
	public String retriveDuplicateEmailAddressWarning() {
		String duplicateEmailWarningText=duplicateEmailAddressWarningMessage.getText();
		return duplicateEmailWarningText;
	}
	public String retrivePrivacyPolicyWarning() {
		String  privacyPolicyWarningText=privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	public String retriveFirstNameWarning() {
		String firstNameWarningText=firstNameWarning.getText();
		return firstNameWarningText;
	}
	public String retriveLastNameWarning() {
		String lastNameWarningText=lastNameWarning.getText();
		return lastNameWarningText;
	}
	public String retriveEmailWarning() {
		String 	emailWarningText=emailWarning.getText();
		return emailWarningText;
	}
	public String retriveTelephoneWarning() {
		String  telephonWarningText=telephonWarning.getText();
		return telephonWarningText;
	}
	public String retrivePasswordWarning() {
		String passwordWarningText=passwordWarning.getText();
		return passwordWarningText;
	}



}