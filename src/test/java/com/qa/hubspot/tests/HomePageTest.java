package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class HomePageTest {

	WebDriver driver;
	BasePage basePage;
	Properties properties;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;

	@BeforeTest
	public void setup() throws InterruptedException {
		basePage = new BasePage();
		properties = basePage.init_properties();
		String browserName = properties.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(properties.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(properties.getProperty("username"), properties.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
	}

	@Test(priority = 1, description = "Home page title is User Guide | HubSpot")
	public void verifyHomePageTitle() {
		String title = homePage.getHomePageTitle();
		System.out.println("HomePage Page title is: " + title);
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}

	@Test(priority = 2, description = "Home page header is User Guide")
	public void verifyHomeHeaderTest() {
		String header = homePage.getHomePageHeader();
		System.out.println(header);
		Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
	}

	@Test(priority = 3, description = "User account is Teecs", enabled = false)
	public void verifyUserAccountName() {
		String accountName = homePage.getUserAccountName();
		System.out.println(accountName);
		Assert.assertEquals(accountName, "Teecs");
	}

	@AfterTest
	public void tearDown() {
		ElementUtil.quitBrowser(driver);
	}
}
