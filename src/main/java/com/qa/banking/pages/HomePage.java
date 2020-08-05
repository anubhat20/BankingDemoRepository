package com.qa.banking.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.banking.base.Base;
import com.qa.banking.util.TestUtil;

/**
 * 
 * @author Anuradha
 * Home Page class
 * Elements and Actions for Home page
 *
 */
public class HomePage extends Base {
	
	//Page Factory -- OR
	
	@FindBy(xpath = "//a[@class='navbar-brand']//img")
	WebElement bankLogoImage;
	
	@FindBy(xpath = "//div[@id='right-panel']//li")
	WebElement welcomeUserHeading;
	
	@FindBy(xpath = "//*[@id='right-panel']/div[2]/div/div/div[1]/div/div/h4")
	WebElement accountSummary;
	
	@FindBy(xpath = "//*[@id='right-panel']/div[2]/div/div/div[2]/div/div/h4")
	WebElement depositVsWithdraw;
	
	@FindBy(xpath = "//*[@id='right-panel']/div[2]/div/div/div[3]/div/div/h4")
	WebElement withdrawByCategory;
	
	@FindBy(xpath = "//*[@id='right-panel']/div[2]/div/div/div[4]/div/div/h4")
	WebElement depositByCategory;
	
	@FindBy(id = "savings-menu")
	WebElement savingsMainMenu;
	
	@FindBy(id = "new-savings-menu-option")
	WebElement newSavingsMenu;
	
	@FindBy(linkText = "View Savings")
	WebElement viewSavingsMenu;
	
	//initiliaze page factory to avoid NullPointerException
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	//check if logo image is visible
	public boolean isBankLogoDisplayed() {
		System.out.println("banklogo" + bankLogoImage);
		return bankLogoImage.isDisplayed();
	}
	
	//check if Welcome User name is visible
	public String getWelcomeUserHeadingText() {
		TestUtil.waitTillElementIsVisible(welcomeUserHeading);
		return welcomeUserHeading.getText();
	}
	
	//check if account summary is displayed
	public boolean isAccountSummaryDisplayed() {
		return accountSummary.isDisplayed();
	}
	
	//check if deposit vs withdraw is displayed
	public boolean isDepositVsWithdrawDisplayed() {
		return depositVsWithdraw.isDisplayed();
	}
	
	//check if withdraw by category is displayed
	public boolean isWithdrawByCategoryDisplayed() {
		return withdrawByCategory.isDisplayed();
	}
	
	//check if deposit by category is displayed
	public boolean isDepositByCategoryDisplayed() {
		return depositByCategory.isDisplayed();
	}
	
	//go to new savings account page
	public NewSavingsAccountPage goToNewSavingsAccountPage() {
		TestUtil.waitTillElementIsVisible(savingsMainMenu);
		savingsMainMenu.click();
		TestUtil.waitTillElementIsVisible(newSavingsMenu);
		newSavingsMenu.click();
		return new NewSavingsAccountPage();
	}
	
	//go to view savings account page
	public ViewSavingsAccountsPage goToViewSavingsAccountPage() {
		TestUtil.waitTillElementIsVisible(savingsMainMenu);
		savingsMainMenu.click();
		TestUtil.waitTillElementIsVisible(viewSavingsMenu);
		viewSavingsMenu.click();
		return new ViewSavingsAccountsPage();
	}
	
	
}
