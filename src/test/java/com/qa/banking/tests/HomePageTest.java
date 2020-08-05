package com.qa.banking.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.banking.base.Base;
import com.qa.banking.pages.HomePage;
import com.qa.banking.pages.LoginPage;
import com.qa.banking.pages.NewSavingsAccountPage;
import com.qa.banking.pages.ViewSavingsAccountsPage;

/**
 * 
 * @author Anuradha
 * Home Page Test class
 * Test cases for Home page
 *
 */
public class HomePageTest extends Base {
	
	HomePage homePage;
	LoginPage loginPage;
	NewSavingsAccountPage newSavingsAccountPage;
	ViewSavingsAccountsPage viewSavingsAccountsPage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialize();
		loginPage = new LoginPage();
		homePage = loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void verifyBankLogoIsDisplayedTest() {
		Assert.assertTrue(homePage.isBankLogoDisplayed());
	}
	
	@Test
	public void verifyWelcomeUserHeadingTest() {
		String userHeading = homePage.getWelcomeUserHeadingText();
		Assert.assertEquals(userHeading, "Welcome " + prop.getProperty("accountName"));
	}
	
	@Test
	public void verifyChartsDisplayed() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(homePage.isAccountSummaryDisplayed(), "Account summary not displayed");
		softAssert.assertTrue(homePage.isDepositVsWithdrawDisplayed(), "Deposit Vs Withdraw not displayed");
		softAssert.assertTrue(homePage.isWithdrawByCategoryDisplayed(), "Withdraw by category not displayed");
		softAssert.assertTrue(homePage.isDepositVsWithdrawDisplayed(), "Deposit by category not displayed");
		softAssert.assertAll();
	}
	
	@Test
	public void verifyNewSavingsAccountsPageTest() {
		newSavingsAccountPage = homePage.goToNewSavingsAccountPage();
		Assert.assertEquals(newSavingsAccountPage.getClass(), NewSavingsAccountPage.class);
	}
	
	@Test
	public void verifyViewSavingsAccountsPageTest() {
		viewSavingsAccountsPage = homePage.goToViewSavingsAccountPage();
		Assert.assertEquals(viewSavingsAccountsPage.getClass(), ViewSavingsAccountsPage.class);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
