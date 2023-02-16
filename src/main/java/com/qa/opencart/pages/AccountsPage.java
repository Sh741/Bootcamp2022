package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {
	
	
	private WebDriver driver;
	private ElementUtil elUtil;
	private By search = By.name("search");
	private By seachIcon = By.cssSelector("div#search button");
	
	private By logoutLink = By.linkText("Logout");
	private By accountSecHeader = By.cssSelector("div#content h2");
	
	public AccountsPage(WebDriver driver) {
		 this.driver=driver; // Constructor 
		 elUtil = new ElementUtil(driver);
		 
		 
	}

	//page action - method
	 public String getAccPageTitle() {
		// return driver.getTitle();
		 return elUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE,TimeUtil.DEFAULT_TIME_OUT);
	 }
	 
	 public String getAccPageUrl() {
		 //return driver.getCurrentUrl();
		 return elUtil.waitForUrlContains(AppConstants.ACCOUNTS_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME_OUT);
	 }
	 public boolean isSearchExist() {
		 //return driver.findElement(search).isDisplayed();
		 return elUtil.waitForElementVisible(search, TimeUtil.DEFAULT_TIME_OUT).isDisplayed();
	 }
	
	 
	 public boolean isLogoutExist() {
		// return driver.findElement(logoutLink).isDisplayed();
		 return elUtil.waitForElementVisible(logoutLink, TimeUtil.DEFAULT_TIME_OUT).isDisplayed();
	 }
	 
	 public List<String> getAccountsPageSectionsHeaders() {
	//	List<WebElement> secHeadersList= driver.findElements(accountSecHeader);
			List<WebElement> secHeadersList= elUtil.waitForElementsVisible(accountSecHeader, TimeUtil.DEFAULT_TIME_OUT);
		List<String> secHeaderValList = new ArrayList<String>();
		for(WebElement e : secHeadersList) {
			String text = e.getText();
			secHeaderValList.add(text);
		}
		return secHeaderValList;
		
	 }
// create the business logic
	public ResultsPage performSearch(String productName) {
		System.out.println("product search for : "+ productName);
		if (isSearchExist()) {
			elUtil.doSendKeys(search, productName);
			elUtil.doClick(seachIcon);
			return new ResultsPage(driver);
		}
		return null; //if search is unavailsble 
	}
	 
	 
}
