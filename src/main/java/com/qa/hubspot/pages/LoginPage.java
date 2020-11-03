package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JSUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	JSUtil jsUtil;

	By emailID = By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("loginBtn");
	By signUp = By.linkText("Sign up");
	By loginErrorText = By.xpath("//h5[contains(text(),'That email')]");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JSUtil(driver);
	}

	public String getPageTitle() {
		ElementUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
		return ElementUtil.getPageTitle(driver);
	}

	public boolean checkSignUpLink() {
		ElementUtil.waitForElementVisible(signUp);
		return ElementUtil.doIsDisplayed(signUp);
	}
	
	public HomePage doLogin(Credentials userCred) {
		WebElement userFlash = ElementUtil.getElement(driver, emailID);
		JSUtil.flash(userFlash, driver);
		WebElement passFlash = ElementUtil.getElement(driver, emailID);
		JSUtil.flash(passFlash, driver);
		
		ElementUtil.waitForElementPresent(emailID);
		ElementUtil.doSendKeys(emailID, userCred.getAppUsername());
		ElementUtil.waitForElementPresent(password);
		ElementUtil.doSendKeys(password, userCred.getAppPassword());
		ElementUtil.doClick(loginBtn);
		
		return new HomePage(driver);
	}
	
	public boolean checkLoginErrorMessage() {
		return ElementUtil.doIsDisplayed(loginErrorText);
	}	
}