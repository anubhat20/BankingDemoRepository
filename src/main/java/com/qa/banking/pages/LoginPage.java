package com.qa.banking.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.banking.base.Base;
import com.qa.banking.util.Constants;
import com.qa.banking.util.TestUtil;

/**
 * 
 * @author Anuradha
 * Login Page class
 * Elements and Actions for Login page
 *
 */
public class LoginPage extends Base {
	
	//PageFactory - ObjectRepository (OR)
	
	@FindBy(xpath = "//img[@class = 'align-content']")
	WebElement bankLogo;
	
	@FindBy(id = "username")
	WebElement username;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "submit")
	WebElement signInBtn;
	
	@FindBy(linkText = "Sign Up Here")
	WebElement signUpLink;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//gets login page title
	public String getLoginPageTitle() {
		return TestUtil.getPageTitle(Constants.LOGIN_PAGE_TITLE);
	}
	
	//check if bank logo is displayed
	public boolean isBankLogoDisplayed() {
		return bankLogo.isDisplayed();
	}
	
	//Sign up
	public SignUpPage signUp() {
		if(signUpLink.isDisplayed()) {
			signUpLink.click();
			return new SignUpPage();
		}
		return null;
	}
	
	//Sign in
	public HomePage signIn(String user, String pass) {
		username.clear();
		username.sendKeys(user);
		password.clear();
		password.sendKeys(pass);
		signInBtn.click();
		return new HomePage();
	}

}
