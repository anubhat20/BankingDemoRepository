package com.qa.banking.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.banking.util.Constants;

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
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}

}
