package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppError;

public class AccountsPageTest extends BaseTest {

	@BeforeTest
	public void accSetUp() {
		
		accPage= logInPage.doLogin(propt.getProperty("username"),propt.getProperty("password"));
		
	}
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		System.out.println("acc page title : "+actTitle);
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
		
	}
	@Test 
	public void accPageURLTest() {
		String actURL = accPage.getAccPageUrl();
		System.out.println("Account page Url : "+actURL);
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNTS_PAGE_FRACTION_URL),AppError.NO_URL_MATCHED);
	}
	
	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	@Test
	public void LogoutExistTest() {
		Assert.assertTrue(accPage.isLogoutExist());
	}
	@Test
	public void accPageHeadersTest() {
	List<String> actHeadersList = 	accPage.getAccountsPageSectionsHeaders();
	Assert.assertEquals(actHeadersList, AppConstants.EXPECTED_ACC_HEADERS_LIST);
	}
	//We need to provid data for the hardcoded value i.e, productname 
	@DataProvider
	public Object[][] getProductName() {
		 return new Object[][] {
			{"Macbook"},
			{"iPad"},
			{"Samsung"}
		};
	}
	
	
	//TDD approach 
	//we are not creating the page class/method instead we are starting form the page test class and add the 
	//method into page class
	//first test and then according to the requirement keep creating the page methods
	@Test (dataProvider = "getProductName")
	public void productSearchTest(String productName) {
		// String productName = "MacBook";
		resultPage =  accPage.performSearch(productName);
		String actTitle = resultPage.getSearchPageTitle(productName);
		System.out.println("search page title : "+actTitle);
		softAssert.assertEquals(actTitle, AppConstants.SEARCH_PAGE_TITLE +" "+	productName); //soft assert
		Assert.assertTrue(resultPage.getSearchProductsCount()>0); //hard assert
	}
	private ResultsPage resultPage() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
