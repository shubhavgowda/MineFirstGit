package com.tutorialsninjas.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninjas.qa.base.Base;
import com.tuturialsninja.qa.pages.HomePage;
import com.tuturialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	public WebDriver driver;
	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		HomePage homePage=new  HomePage(driver);
		homePage.enterProductIntoSearchBoxTextField(dataProp.getProperty("validProduct"));
		homePage.clickOnSearchButton();
		//		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		//		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		SearchPage searchPage=new SearchPage(driver);
		searchPage.displayStatusOfHpValidProduct();
		//		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed()); 
	}
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		HomePage homePage=new HomePage(driver);
		homePage.enterProductIntoSearchBoxTextField(dataProp.getProperty("invalidProduct"));
		homePage.clickOnSearchButton();
		//		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
		//		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();

		SearchPage searchPage=new SearchPage(driver);
		String actualSearchMessage=searchPage.retriveNoProductMessageText();
		//		String actualSearchMessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		String expextedSearchMessage=dataProp.getProperty("noProductTextInSearchResults!!");
		//		Assert.assertEquals(actualSearchMessage,expextedSearchMessage,"abcd ");
		Assert.assertEquals(actualSearchMessage,expextedSearchMessage,"No product Message in Search result is not displayed");
	}
	@Test(priority = 3,dependsOnMethods = {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		HomePage homePage=new HomePage(driver);
		homePage.clickOnSearchButton();
		//		driver.findElement(By.name("search")).sendKeys("");
		//		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();

		SearchPage searchPage=new SearchPage(driver);
		String actualSearchMessage=searchPage.retriveNoProductMessageText();
		//		String actualSearchMessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		String expextedSearchMessage=dataProp.getProperty("noProductTextInSearchResults");
		Assert.assertEquals(actualSearchMessage,expextedSearchMessage,"No product Message in Search result is not displayed");
	}
}

