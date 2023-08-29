package com.tutorialsninjas.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.listeners.MyListeners;
import com.tutorialsninja.qa.utils.UtilitiesMethods;
import com.tutorialsninjas.qa.base.Base;
import com.tuturialsninja.qa.pages.AccountSuccessPage;
import com.tuturialsninja.qa.pages.HomePage;
import com.tuturialsninja.qa.pages.RegisterPage;

public class RegisterTest extends Base {
	public WebDriver driver;
	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectRegisterOption();
		//		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//		driver.findElement(By.linkText("Register")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields()   {
		RegisterPage registerPage=new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(UtilitiesMethods.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();


		//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		//		driver.findElement(By.id("input-email")).sendKeys(UtilitiesMethods.generateEmailWithTimeStamp());
		//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		//		driver.findElement(By.name("agree")).click();
		//		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		AccountSuccessPage accountSuccessPage=new AccountSuccessPage(driver);
		String actualSuccessHeading=accountSuccessPage.retriveAccountSuccessPageHeading();
		//		String actualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();   this is stored in framework-->accountsuccesspage 
		Assert.assertEquals(actualSuccessHeading,"Your Account Has Been Created!","Account Succuess page is not displayed");

	}


	@Test(priority = 2)
	public void verifyRegisteringAccounByProvidingAllFields()   {

		RegisterPage registerPage=new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(UtilitiesMethods.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesnewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		//		driver.findElement(By.id("input-email")).sendKeys(UtilitiesMethods.generateEmailWithTimeStamp());
		//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		//		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		//		driver.findElement(By.name("agree")).click();
		//		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		AccountSuccessPage accountSuccessPage=new AccountSuccessPage(driver);
		String actualSuccessHeading=accountSuccessPage.retriveAccountSuccessPageHeading();
		//		String actualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();   this is stored in framework-->accountsuccesspage 
		Assert.assertEquals(actualSuccessHeading,"Your Account Has Been Created!","Account Succuess page is not displayed");

	}	

	@Test(priority = 3)
	public void verifyRegisteringAnAccountWithExistingEmailAddress()   {

		RegisterPage registerPage=new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmailID"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesnewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmailID"));
		//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		//		driver.findElement(By.name("agree")).click();
		//		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualWarningMessage=registerPage.retriveDuplicateEmailAddressWarning();
		//		String actualWarningMessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage=dataProp.getProperty("duplicateEmailWarning");
		Assert.assertEquals(actualWarningMessage,expectedWarningMessage,"Warning message regarding duplicate email address is not displayed");

	}
	@Test(priority = 4)
	public void verifyRegisteringAnAccountWithoutFillingAnyDetails()   {

		RegisterPage registerPage=new RegisterPage(driver);
		registerPage.clickOnContinueButton();	
		//		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualPrivacyPolicyWarning= registerPage.retrivePrivacyPolicyWarning();
		//		String actualPrivacyPolicyWarning= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedPrivacyPolicyWarning=dataProp.getProperty("privacyPolicyWarning");
		Assert.assertEquals(actualPrivacyPolicyWarning, expectedPrivacyPolicyWarning,"Warning message ragarding privacy policy is not displayed");

		String actualFirstNameWarning=registerPage.retriveFirstNameWarning();
		//		String actualFirstNameWarning=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		String expectedFirstNameWarning=dataProp.getProperty("firstNameWarning");
		Assert.assertEquals(actualFirstNameWarning, expectedFirstNameWarning,"FirstName warning Message is not displayed");

		String actualLastNameWarning=registerPage.retriveLastNameWarning();
		//		String actualLastNameWarning=driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		String expectedLastNameWarning=dataProp.getProperty("lastNameWarning");
		Assert.assertEquals(actualLastNameWarning, expectedLastNameWarning,"LastName warning Message is not displayed");

		String actualEamilWarning=registerPage.retriveEmailWarning();
		//		String actualEamilWarning=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		String expectedEmailWarning=dataProp.getProperty("emailWarning");
		Assert.assertEquals(actualEamilWarning, expectedEmailWarning,"Email warning Message is not displayed");

		String actualTelephoneWarning=registerPage.retriveTelephoneWarning();
		//		String actualTelephoneWarning=driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		String expectedTelephoneWarning=dataProp.getProperty("telephoneWarning");
		Assert.assertEquals(actualTelephoneWarning, expectedTelephoneWarning,"Telephone warning Message is not displayed");

		String actualPasswordWarning=registerPage.retrivePasswordWarning();
		//		String actualPasswordWarning=driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		String expectedPasswordWarning=dataProp.getProperty("passwordWarning");
		Assert.assertEquals(actualPasswordWarning, expectedPasswordWarning,"Password warning Message is not displayed");

	}
}