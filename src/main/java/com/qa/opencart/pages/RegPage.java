package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegPage {

	private WebDriver driver;
	
	private ElementUtil eleUtil;
	
	private By firstName =By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By telephone = By.id("input-telephone");
	
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	
	private By agreeCheckBox =By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value ='Continue']");
	private By subscribeYesButton = By.xpath("(//label[@class='radio-inline'])[1]/input[@type='radio']");
	private By subscribeNoButton = By.xpath("(//label[@class='radio-inline'])[2]/input[@type='radio']");
	
	private By registerSuccessMsg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink =By.linkText("Register");

	public  RegPage(WebDriver driver) {
		this.driver = driver;
		eleUtil =new ElementUtil(driver);
	}
	
	public boolean registerUser(String firstName, String lastName,String email,String teleph, String password,
			String subscribe) {
		 
	eleUtil.waitForElementVisible(this.firstName, TimeUtil.DEFAULT_TIME_OUT).sendKeys(firstName);	
	
	eleUtil.doSendKeys(this.lastName, lastName);
	eleUtil.doSendKeys(this.emailId, email);
	eleUtil.doSendKeys(this.telephone, teleph);
	eleUtil.doSendKeys(this.password, password);
	eleUtil.doSendKeys(this.confirmPassword, password);

	
	if(subscribe.equalsIgnoreCase("yes")) {
		eleUtil.doClick(subscribeYesButton);
	}
	else {
		eleUtil.doClick(subscribeNoButton);
	}

	eleUtil.doClick(agreeCheckBox);
	eleUtil.doClick(continueButton);
	
	String sucessMsg = eleUtil.waitForElementVisible(registerSuccessMsg, TimeUtil.DEFAULT_TIME_OUT).getText();
	System.out.println(sucessMsg);
	
	if(sucessMsg.contains( AppConstants.ACCOUNT_REGISTER_SUCCESS_MESG)) {
		
		eleUtil.doClick(logoutLink);
		eleUtil.doClick(registerLink);
		return true;
	}else {
		eleUtil.doClick(registerLink);
	}
	return false;
	
	}
	
	
	
	
}
