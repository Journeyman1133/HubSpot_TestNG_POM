package com.qa.hubspot.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.hubspot.base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author ammarunal
 *
 */
public class ElementUtil extends BasePage {

	public static WebDriver driver;
	public static WebDriverWait wait;
	JSUtil jsUtil;
	Properties prop;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME);
		jsUtil = new JSUtil(driver);
	}

	/**
	 * This method clears the box
	 * 
	 * @param locator
	 */
	public static void clearTheBox(By locator) {
		driver.findElement(locator).clear();
	}

	/**
	 * This method clicks on the element
	 * 
	 * @param driver
	 * @param locator
	 */
	public static void clickOn(WebDriver driver, By locator) {
		driver.findElement(locator).click();
	}

	/**
	 * This method dismisses the alert
	 * 
	 * @param driver
	 */
	public static void dismissAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	/**
	 * 
	 * @param locator
	 */
	public static void doClick(By locator) {
		try {
			getElement(driver, locator).click();
		} catch (Exception e) {
			System.out.println("some exception occured while clicking the element.." + locator);
		}
	}

	/**
	 * 
	 * @param title
	 * @return
	 */
	public static String doGetPageTitle(String title) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.titleContains(title));
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("some exception occured while getting the title..");
		}
		return null;
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public static String doGetText(By locator) {
		try {
			return getElement(driver, locator).getText();
		} catch (Exception e) {
			System.out.println("some exception occured while getting the text from page.." + locator);
		}
		return null;
	}

	public static boolean doIsDisplayed(By locator) {
		try {
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("some exception occured for isDisplayed.." + locator);
		}
		return false;
	}

	public static boolean doIsEnabled(By locator) {
		try {
			return getElement(driver, locator).isEnabled();
		} catch (Exception e) {
			System.out.println("some exception occured for isEnabled.." + locator);
		}
		return false;
	}

	public static boolean doIsSelected(By locator) {
		try {
			return getElement(driver, locator).isSelected();
		} catch (Exception e) {
			System.out.println("some exception occured for isSelected.." + locator);
		}
		return false;
	}

	public static void doSendKeys(By locator, String value) {
		try {
			WebElement element = getElement(driver, locator);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("some exception occured while sending the value.." + locator);
		}
	}

	/**
	 * This method is used to handle alert
	 * 
	 * @param driver
	 * @return
	 */
	public static String getAlertText(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		System.out.println(text);
		alert.accept();
		return text;
	}

	/**
	 * This method is used to handle alert and sends a message
	 * 
	 * @param driver
	 * @param message
	 * @return
	 */
	public static String getAlertTextWithSend(WebDriver driver, String message) {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.sendKeys(message);
		System.out.println(text);
		alert.accept();
		return text;
	}

	/**
	 * This method is used to get all options belonging to this tag
	 * 
	 * @param element
	 */
	public static void getAllOptions(WebElement element) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (int i = 0; i < options.size(); i++) {
			System.out.println(options.get(i).getText());
		}
	}

	/**
	 * This method gets the element
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 */
	public static WebElement getElement(WebDriver driver, By locator) {
		WebElement element = driver.findElement(locator);
		if (highlightElement) {
			JSUtil.flash(element, driver);
		}
		return element;
	}

	/**
	 * This method gets the element using custom wait
	 * 
	 * @param driver
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public static WebElement getElementCustomWait(WebDriver driver, By locator, int timeout) {
		waitForPresenceOfElement(driver, locator, timeout);
		WebElement element = driver.findElement(locator);
		return element;
	}

	/**
	 * This method finds the elements
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 */
	public static List<WebElement> getElements(WebDriver driver, By locator) {
		List<WebElement> userElements = driver.findElements(locator);
		return userElements;
	}

	/**
	 * This method gets the element using wait
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 */
	public static WebElement getElementWithWait(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		WebElement element = driver.findElement(locator);
		return element;
	}

	/**
	 * This method gets the page title
	 * 
	 * @param driver
	 * @return
	 */
	public static String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	/**
	 * This method gets text from an element
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 */
	public static String getText(WebDriver driver, By locator) {
		WebElement element = driver.findElement(locator);
		String text = element.getText();
		return text;
	}

	/**
	 * This method launches a browser
	 * 
	 * @param driver
	 * @param browserName
	 * @return
	 */
	public static WebDriver launchBrowser(WebDriver driver, String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver = new SafariDriver();
		} else {
			System.out.println("This browser is not available: " + browserName);
		}
		return driver;
	}

	/**
	 * This method launches a URL
	 * 
	 * @param driver
	 * @param url
	 */
	public static void launchURL(WebDriver driver, String url) {
		driver.get(url);
	}

	/**
	 * This method quits the browser
	 * 
	 * @param driver
	 */
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}

	/**
	 * This method selects the option at the given index
	 * 
	 * @param driver
	 * @param locator
	 * @param index
	 */
	public static void selectDropdownByIndex(WebDriver driver, By locator, int index) {
		WebElement element = getElement(driver, locator);
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * This method selects the option at the given index
	 * 
	 * @param element
	 * @param index
	 */
	public static void selectDropdownByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * This method selects the option at the given index and verifies
	 * 
	 * @param driver
	 * @param locator
	 * @param index
	 * @param text
	 */
	public static void selectDropdownByIndexVerify(WebDriver driver, By locator, int index, String text) {
		WebElement element = ElementUtil.getElement(driver, locator);
		Select select = new Select(element);
		if (select.getFirstSelectedOption().getText().equals(text)) {
			System.out.println("Correct Selection");
		} else {
			System.out.println("Incorrect Selection");
		}
	}

	/**
	 * This method selects all options that have a value
	 * 
	 * @param driver
	 * @param locator
	 * @param value
	 */
	public static void selectDropdownByValue(WebDriver driver, By locator, String value) {
		WebElement element = getElement(driver, locator);
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * This method selects all options that have a value
	 * 
	 * @param element
	 * @param value
	 */
	public static void selectDropdownByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * This method selects all options that display text
	 * 
	 * @param driver
	 * @param locator
	 * @param text
	 */
	public static void selectDropdownByVisibleText(WebDriver driver, By locator, String text) {
		WebElement element = getElement(driver, locator);
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * This method selects all options that display text
	 * 
	 * @param driver
	 * @param locator
	 * @param text
	 */
	public static void selectDropdownByVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * This method sends characters into a textbox
	 * 
	 * @param driver
	 * @param locator
	 * @param keys
	 */
	public static void sendKeys(WebDriver driver, By locator, CharSequence... keys) {
		driver.findElement(locator).sendKeys(keys);
	}

	/**
	 * This method is used to count number of tables
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 */
	public static int tableCount(WebDriver driver, By locator) {
		List<WebElement> rowList = driver.findElements(locator);
		System.out.println(rowList.size());
		return rowList.size();

	}

	/**
	 * This method takes screenshots
	 * 
	 * @param driver
	 * @param path
	 * @throws IOException
	 */
	public static void takeScreenshot(WebDriver driver, String path) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(path);
		FileUtils.copyFile(src, destinationFile);
	}

	/**
	 * This method checks the presence of the element using wait concept
	 * 
	 * @param locator
	 * @return
	 */
	public static boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}

	/**
	 * This method checks the presence and visibility of the element using wait
	 * concept
	 * 
	 * @param locator
	 * @return
	 */
	public static boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}

	/**
	 * This method checks the presence of the element using wait concept
	 * 
	 * @param driver
	 * @param locator
	 * @param timeout
	 */
	public static void waitForPresenceOfElement(WebDriver driver, By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * This method returns the title using wait concept
	 * 
	 * @param title
	 * @return
	 */
	public static boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}
}