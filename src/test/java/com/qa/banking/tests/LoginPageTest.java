package com.qa.banking.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.banking.base.Base;
import com.qa.banking.pages.HomePage;
import com.qa.banking.pages.LoginPage;
import com.qa.banking.pages.SignUpPage;
import com.qa.banking.util.Constants;
import com.qa.banking.util.TestUtil;

/**
 * 
 * @author Anuradha
 * Login Page Test class
 * Test cases for Login page
 *
 */
public class LoginPageTest extends Base {
	
	LoginPage loginPage;
	HomePage homePage;
	SignUpPage signUpPage;
	
	//ensures properties file is loaded and avoids NullPointerException
	public LoginPageTest() {
		super();	//calls Base constructor
	}
	
	@BeforeMethod
	public void setUp() {
		initialize();
		loginPage = new LoginPage();
	}
	
	@Test
	public void verifyLoginPageTitleTest() {
		String loginPageTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(loginPageTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void verifyBankLogoTest() {
		boolean isLogoDisplayed = loginPage.isBankLogoDisplayed();
		Assert.assertTrue(isLogoDisplayed);
	}
	
	@Test
	public void verifySignUpTest() {
		signUpPage = loginPage.signUp();
		if(signUpPage == null) {
			TestUtil.takeScreenshot();
			Assert.assertNull(signUpPage, "Sign up page is not displayed");
		} else {
			Assert.assertEquals(signUpPage.getClass(), SignUpPage.class);
		}
			
	}
	
	@Test
	public void verifySignInTest() {
		homePage = loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homePage.getClass(), HomePage.class);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
