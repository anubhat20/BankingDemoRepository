package com.qa.banking.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.banking.base.Base;
import com.qa.banking.pages.HomePage;
import com.qa.banking.pages.LoginPage;
import com.qa.banking.pages.NewSavingsAccountPage;
import com.qa.banking.pages.ViewSavingsAccountsPage;
import com.qa.banking.util.Constants;
import com.qa.banking.util.TestUtil;

/**
 * 
 * @author Anuradha
 * New Savings Account Page Test class
 * Test cases for New Savings Account page
 *
 */
public class NewSavingsAccountPageTest extends Base {
	
	LoginPage loginPage;
	HomePage homePage;
	NewSavingsAccountPage newSavingsAccountPage;
	ViewSavingsAccountsPage viewSavingsAccountsPage;
	
	public NewSavingsAccountPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialize();
		loginPage = new LoginPage();
		homePage = loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
		newSavingsAccountPage = homePage.goToNewSavingsAccountPage();
	}
	
	@Test
	public void verifyIsNewAccountsPageHeaderDisplayesTest() {
		Assert.assertTrue(newSavingsAccountPage.isNewAccountPageHeaderDisplayed());
	}
	
	@DataProvider
	public Object[][] getNewSavingsTestData(){
		Object data [][] = TestUtil.getTestData(Constants.NEW_ACCOUNTS_PAGE_SHEET);
		return data;
	}
	
	@Test(dataProvider = "getNewSavingsTestData")
	public void verifyAddNewSavingsAccount(String accountType, String ownerShipType
											, String name, String openingBalance) {
		viewSavingsAccountsPage = newSavingsAccountPage.addNewSavingsAccount(accountType, ownerShipType, name, openingBalance);
		Assert.assertEquals(viewSavingsAccountsPage.getClass(), ViewSavingsAccountsPage.class);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
