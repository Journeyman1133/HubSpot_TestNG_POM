package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {

	WebDriver driver;

	By header = By.xpath("//i18n-string[contains(text(),'User Guide')]");
	By accountName = By.cssSelector("span.account-name");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle() {
		return ElementUtil.getPageTitle(driver);
	}

	public String getHomePageHeader() {
		return ElementUtil.doGetText(header);
	}
	
	public String getUserAccountName() {
		ElementUtil.waitForElementPresent(accountName);
		return ElementUtil.doGetText(accountName);
	}
}
