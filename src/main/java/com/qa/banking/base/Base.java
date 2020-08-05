package com.qa.banking.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.banking.util.Constants;
import com.qa.banking.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Anuradha
 * Base class for Banking Demo Project
 * Contains initialization for driver and properties file
 *
 */
public class Base {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public static EventFiringWebDriver eventFireDriver;
	public static WebEventListener eventListener;
	
	public Base() {
		//initialize properties
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("D:\\Selenium_workspace\\BankingDemoProject\\"
								+ "src\\main\\java\\com\\qa\\banking\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialize() {
		//initialize driver
		String browser = prop.getProperty("browser");
		if("chrome".equals(browser)) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if("firefox".equals(browser)) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		// For Web Driver Fire events 
		eventFireDriver = new EventFiringWebDriver(driver);
		//Now create object of EventListener to register to EvenFiringWebDriver
		eventListener = new WebEventListener();
		eventFireDriver.register(eventListener);
		driver = eventFireDriver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}

}
