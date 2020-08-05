package com.qa.banking.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.banking.base.Base;

/**
 * 
 * @author Anuradha
 * Element utility class
 * Contains all utility methods for Banking demo project
 *
 */
public class TestUtil extends Base {
	
	public static String TEST_DATA_EXCEL_PATH = "D:\\Selenium_workspace\\BankingDemoProject\\"
										+ "src\\main\\java\\com\\qa\\banking\\testdata\\BankingDemoTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	//wait till page title is as parameter passed
	public static String getPageTitle(String title) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.IMPLICIT_WAIT);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	
	//wait till element is visible
	public static WebElement waitTillElementIsVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.IMPLICIT_WAIT);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	//take a screenshot and store to screenshots dir
	public static void takeScreenshot() {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		try {
			FileUtils.copyFile(screenshot, 
							new File(currentDir + "\\screenshots\\BankingDemo_" + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//get test data from excel file using sheet name
	public static Object[][] getTestData(String sheetName) {
		Object data[][] = null;
		
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_EXCEL_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
			//initialize data to max row number and last column number
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i = 0; i < sheet.getLastRowNum(); i++) {
				for(int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
