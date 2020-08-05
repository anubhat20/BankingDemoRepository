package com.qa.banking.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.banking.base.Base;
import com.qa.banking.util.TestUtil;

/**
 * 
 * @author Anuradha
 * New Savings Account Page class
 * Elements and Actions for New Savings Account page
 *
 */
public class NewSavingsAccountPage extends Base {
	
	//Page Factory -- OR
	
	@FindBy(xpath = "//div//strong[text()='New Savings Account']")
	WebElement newAccountsPageHeader;
	
	@FindBy(name = "accountType")
	List<WebElement> accountTypeRadio;
	
	@FindBy(name = "ownershipType")
	List<WebElement> ownershipTypeRadio;
	
	@FindBy(id = "name")
	WebElement accountName;
	
	@FindBy(id = "openingBalance")
	WebElement initialDeposit;
	
	@FindBy(id = "newSavingsSubmit")
	WebElement newSavingsSubmitBtn;
	
	//Initialize Page factory to avoid NullPointerException
	public NewSavingsAccountPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	//check if new accounts page header is displayed
	public boolean isNewAccountPageHeaderDisplayed() {
		TestUtil.waitTillElementIsVisible(newAccountsPageHeader);
		return newAccountsPageHeader.isDisplayed();
	}
	
	//add new savings account
	public ViewSavingsAccountsPage addNewSavingsAccount(String accountType, String ownerShipType
														, String name, String openingBalance) {
		TestUtil.waitTillElementIsVisible(accountName);
		for(int i = 0; i < accountTypeRadio.size(); i++) {
			String accountTypeValue = accountTypeRadio.get(i).getAttribute("id");
			System.out.println("value = " + accountTypeValue + " excel string = " + accountType);
			if(accountTypeValue.contains((accountType))) {
				accountTypeRadio.get(i).click();
				break;
			}
		}
		for(int i = 0; i < ownershipTypeRadio.size(); i++) {
			String ownershipValue = ownershipTypeRadio.get(i).getAttribute("id");
			if(ownershipValue.contains(ownerShipType)) {
				ownershipTypeRadio.get(i).click();
				break;
			}
		}
		accountName.clear();
		accountName.sendKeys(name);
		initialDeposit.clear();
		initialDeposit.sendKeys(openingBalance);
		newSavingsSubmitBtn.click();
		return new ViewSavingsAccountsPage();
	}

}
