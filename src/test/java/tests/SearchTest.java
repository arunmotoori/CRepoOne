package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.LandingPage;
import pages.SearchPage;
//Comment added TO Search Test
public class SearchTest extends Base {
	
	public WebDriver driver;
	LandingPage landingPage;
	SearchPage searchPage;
	
	@BeforeMethod
	public void setup() {
		
		driver = openApplicationURLIntheBrowser(prop.getProperty("browser"));
		landingPage = new LandingPage(driver);
	
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithExistingProduct() {
		
		landingPage.enterSearhTerm(prop.getProperty("validProductSearchKey"));
		searchPage = landingPage.clickOnSearchButton();
		
		Assert.assertTrue(!searchPage.isValidProductDisplayed());
		
	}
	
	@Test(priority=2,dependsOnMethods="verifySearchWithExistingProduct")
	public void verifySearchWithNonExistingProduct() {
		
		landingPage.enterSearhTerm(prop.getProperty("nonExisitingProductSearchKey"));
		searchPage = landingPage.clickOnSearchButton();
		
		String expectedNoProductMessage = "There is no product that matches the search criteria.";
		Assert.assertEquals(searchPage.getNoProductSearchMessage(),expectedNoProductMessage);
		
	}
	
	@Test(priority=3)
	public void verifySearchWithoutEnteringAnyProduct() {
		
		int i=5;
		
		if(i==5) {
			throw new SkipException("Test got skipped as the value of i got 5");
		}
	
		searchPage = landingPage.clickOnSearchButton();
		
		SearchPage searchPage = new SearchPage(driver);
		String expectedNoProductMessage = "There is no product that matches the search criteria.";
		Assert.assertEquals(searchPage.getNoProductSearchMessage(),expectedNoProductMessage);
		
	}

}
