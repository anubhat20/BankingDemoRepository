package com.qa.banking.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.banking.base.Base;

/**
 * 
 * @author Anuradha
 * SignUp Page class
 * Elements and Actions for SignUp page
 *
 */
public class SignUpPage extends Base {
	
	//Page Factory -- OR
	
	@FindBy(id = "title")
	WebElement title;
	
	@FindBy(id = "firstName")
	WebElement firstName;
	
	@FindBy(id = "lastName")
	WebElement lastName;
	
	@FindBy(id = "gender")
	List<WebElement> genderRadios;
	
	@FindBy(id = "dob")
	WebElement dob;
	
	@FindBy(id = "ssn")
	WebElement ssn;
	
	@FindBy(id = "emailAddress")
	WebElement emailAddress;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "confirmPassword")
	WebElement confirmPassword;
	
	@FindBy(xpath = "//button[text()= 'Next']")
	WebElement nextBtn;
	
	@FindBy(linkText = "Sign in")
	WebElement signInLink;
	
	public SignUpPage() {
		PageFactory.initElements(driver, this);
	}

	public SignUpPage addUserInfoSignUp(String titleText, String fName, String lName, String genderText
										, String dateOfBirth, String ssnText, String email, String pass) {
		Select select = new Select(title);
		select.selectByVisibleText(titleText);
		
		firstName.clear();
		firstName.sendKeys(fName);
		
		lastName.clear();
		lastName.sendKeys(lName);
		
		//select gender radio button
		for(int i = 0; i < genderRadios.size(); i++) {
			String genderValue = genderRadios.get(i).getAttribute("value");
			if(genderValue.equalsIgnoreCase(genderText)) {
				genderRadios.get(i).click();
				break;
			}
		}
		
		dob.clear();
		dob.sendKeys(dateOfBirth);
		
		ssn.clear();
		ssn.sendKeys(ssnText);
		
		emailAddress.clear();
		emailAddress.sendKeys(email);
		
		password.clear();
		password.sendKeys(pass);
		
		confirmPassword.clear();
		confirmPassword.sendKeys(pass);
		
		nextBtn.click();
		
		return new SignUpPage();
		
	}
	
	//user already has account click on sign in link
	public LoginPage backToSignInLink() {
		if(signInLink.isDisplayed()) {
			signInLink.click();
			return new LoginPage();
		}
		return null;
	}
}	
