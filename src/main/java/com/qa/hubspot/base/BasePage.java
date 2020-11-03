package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JSUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	WebDriver driver;
	Properties properties;
	protected static ElementUtil elementUtil;
	public static boolean highlightElement;

	public WebDriver init_driver(String browserName) {

		highlightElement = properties.getProperty("highlight").equals("yes") ? true : false;

		System.out.println("Browser name is " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			if (properties.getProperty("headless").equalsIgnoreCase("yes")) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				driver = new ChromeDriver(chromeOptions);
			} else {
				driver = new ChromeDriver();
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (properties.getProperty("headless").equalsIgnoreCase("yes")) {
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--headless");
				driver = new FirefoxDriver(firefoxOptions);
			} else {
				driver = new FirefoxDriver();
			}
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

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		return driver;
	}

	public Properties init_properties() {

		properties = new Properties();
		String path = "/Users/ammarunal/Documents/workspace/HubSpot_TestNG_POM/"
				+ "src/main/java/com/qa/hubspot/config/config.properties";

		try {
			FileInputStream ip = new FileInputStream(path);
			properties.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("Some issues occurred with config properties..Check the file");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
