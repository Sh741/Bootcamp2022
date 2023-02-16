package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	//Page Object model is classic example of encapsulation java -- private by (variables)loctors were used in 
	//public method
	//Things to maintain 
	//1. private By locators
	private ElementUtil eleUtil;
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBt = By.xpath("//input[@value = 'Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink =By.linkText("Register");
	
	
	
	
	//2. page constructor
	public LoginPage(WebDriver driver) {
		this.driver =driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	
	@Step("Getting login page title")
	//3.page actions 
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
		
	}
	@Step("Get login page URL")
	public String getLoginPageURl() {
		return eleUtil.waitForUrlContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME_OUT); 
		
	}
	@Step("checking forgot password link URL")
	public boolean isforgotPwd() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
		
	}
	@Step("Login with username: {0} and password:{1}")
	public AccountsPage doLogin(String un,String pwd) {
		System.out.println("Cred are: "+un+ ": "+pwd);
//		driver.findElement(emailID).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBt).click();
		//return driver.findElement(By.linkText("Logout")).isDisplayed();
		eleUtil.waitForElementVisible(emailID, TimeUtil.DEFAULT_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBt);
		
		return new AccountsPage(driver);
		 
	}
	@Step("navigating to register page")
	public RegPage navigateToRegisterationPage() {
		eleUtil.doClick(registerLink);
		return new RegPage(driver);
	}
	

}
