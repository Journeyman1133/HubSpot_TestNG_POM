package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class LoginPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties properties;
	LoginPage loginPage;
	Credentials userCred;

	@BeforeTest()
	public void setup() {
		basePage = new BasePage();
		properties = basePage.init_properties();
		String browserName = properties.getProperty("browser");
		driver = basePage.init_driver(browserName);
		ElementUtil.launchURL(driver, properties.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(properties.getProperty("username"), properties.getProperty("password"));
	}

	@Test(priority = 1, description = "Get page title as HubSpot Login")
	public void verifyPageTitle() {
		String title = loginPage.getPageTitle();
		System.out.println("Login Page title is: " + title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2, description = "Sign up link is displayed or not")
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.checkSignUpLink());
	}

	@Test(priority = 3, description = "Checks the login to HubSpot")
	public void LoginTest() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("HubSpot"));
		HomePage homePage = loginPage.doLogin(userCred);
		String accountName = homePage.getUserAccountName();
		System.out.println("User account name is: " + accountName);
		// Assert.assertEquals(accountName, properties.getProperty(accountName));
	}

	@DataProvider
	public Object[][] getLoginInvalidData() {
		Object data[][] = { { "ammar@sample.com", "123test456" }, { "unal@sample.com", " " }, { " ", "123test456" },
				{ "yummy", "yummy" }, { " ", " " } };
		return data;
	}

	@Test(priority = 4, dataProvider = "getLoginInvalidData" , enabled = false)
	public void loginInvalidTetCase(String username, String pwd) {
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
	}

	@AfterTest
	public void tearDown() {
		ElementUtil.quitBrowser(driver);
	}
}
