package com.tutorialsninjas.qa.testcases;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.utils.UtilitiesMethods;
import com.tutorialsninjas.qa.base.Base;
import com.tuturialsninja.qa.pages.AccountPage;
import com.tuturialsninja.qa.pages.HomePage;
import com.tuturialsninja.qa.pages.LoginPage;

public class LoginTest extends Base {
public WebDriver driver;
	public LoginTest() { 
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();

		//		driver.findElement(By.xpath("//span[text()='My Account']")).click();     
		//		driver.findElement(By.xpath("//a[text()='Login']")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name="validCredentialsSupplier")
	public  Object [] [] suplyTestData() {
		Object  [] [] data=UtilitiesMethods.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority  = 1,dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredential(String email , String password) {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginbutton();

		//		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);
		//		driver.findElement(By.id("input-password")).sendKeys	(password);
		//		driver.findElement(By.xpath("//input[@type='submit']")).click();

		AccountPage accountPage=new AccountPage(driver);

		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your Account Information");
		//		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Edit your account information']")).isDisplayed(),("Edit your Account Information"));
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredential() {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(UtilitiesMethods.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginbutton();
		//		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(UtilitiesMethods.generateEmailWithTimeStamp());
		//		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		//		driver.findElement(By.xpath("//input[@type='submit']")).click();

		//		String actualWarningMessage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String actualWarningMessage=loginPage.retriveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPAsswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");

	}
	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword()  {

		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(UtilitiesMethods.generateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginbutton();
		//		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(UtilitiesMethods.generateEmailWithTimeStamp());
		//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		//		driver.findElement(By.xpath("//input[@type='submit']")).click();


		//		String actualWarningMessage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String actualWarningMessage=loginPage.retriveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPAsswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");


	}
	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInValidPassword() {

		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(prop.getProperty("validEmailID"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginbutton();	
		//		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(prop.getProperty("validEmailID"));
		//		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		//		driver.findElement(By.xpath("//input[@type='submit']")).click();

		//		String actualWarningMessage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String actualWarningMessage=loginPage.retriveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPAsswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");

	}
	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredencials() {

		LoginPage loginPage=new LoginPage(driver);
		loginPage.clickOnLoginbutton();	
		//*****instead of writting email id password without entering right thats why directly cliking on login button********
		//		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("");
		//		driver.findElement(By.id("input-password")).sendKeys("");
		//		driver.findElement(By.xpath("//input[@type='submit']")).click();

		//		String actualWarningMessage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String actualWarningMessage=loginPage.retriveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPAsswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");
	}
}

