package com.qa.banking.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.banking.base.Base;
import com.qa.banking.pages.LoginPage;
import com.qa.banking.pages.SignUpPage;
import com.qa.banking.util.Constants;
import com.qa.banking.util.TestUtil;

/**
 * 
 * @author Anuradha
 * Sign Up Page Test class
 * Test cases for Sign Up page
 *
 */
public class SignUpPageTest extends Base {
	
	SignUpPage signUpPage;
	LoginPage loginPage;
	
	public SignUpPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialize();
		loginPage = new LoginPage();
		signUpPage = loginPage.signUp();
	}
	
	@DataProvider
	private Object[][] getBankingTestData() {
		Object data[][] = TestUtil.getTestData(Constants.SIGNUP_SHEET_NAME);
		return data;
	}
	
	@Test(dataProvider = "getBankingTestData")
	public void verifyaddUserInfoSignUpTest(String titleText, String fName, String lName, String genderText
											, String dateOfBirth, String ssn, String email, String pass) {
		signUpPage = signUpPage.addUserInfoSignUp(titleText, fName, lName, genderText, dateOfBirth, ssn, email, pass);
		Assert.assertEquals(signUpPage.getClass(), SignUpPage.class);
	}
	
	@Test
	public void verifyBackToSignInLinkTest() {
		loginPage = signUpPage.backToSignInLink();
		Assert.assertEquals(loginPage.getClass(), LoginPage.class);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
