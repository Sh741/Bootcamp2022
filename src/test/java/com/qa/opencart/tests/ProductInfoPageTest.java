package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	
	@BeforeClass
	public void prodInfoSetup() {
		accPage= logInPage.doLogin(propt.getProperty("username"),propt.getProperty("password"));
	}
	//now to will work for different combinations i.e 6 combinations
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"Macbook", "MacBook Air"},
		//	{"Macbook","MacBook"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Apple", "Apple Cinema 30\""}
				
			};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void productHeaderTest(String searchkey, String mainProductName) {
		resultPage = accPage.performSearch(searchkey);
		prodInfoPage = resultPage.selectProd(mainProductName);
		String actHeader = 	prodInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, mainProductName);
	}
	
	@DataProvider
	public Object[][] getProductImageTestData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro", 4},
			{"Macbook", "MacBook Air", 4},
		//	{"Macbook","MacBook"},
			{"iMac", "iMac", 3},
			
			{"Apple", "Apple Cinema 30\"", 6},
			{"Samsung", "Samsung SyncMaster 941BW", 1}
				
			};
	}
	
	@Test(dataProvider = "getProductImageTestData")
	public void productImagesTest(String searchkey, String mainProductName,int imageCounts) {
		resultPage = accPage.performSearch(searchkey);
		prodInfoPage = resultPage.selectProd(mainProductName);
		int actualImagesCount = 	prodInfoPage.getProdImageCounts();
		Assert.assertEquals(actualImagesCount, imageCounts);
		
	}
	
	@Test
	public void productMetaDataTest() { 
		resultPage = accPage.performSearch("Macbook");
		prodInfoPage = resultPage.selectProd("MacBook Pro");
		Map<String, String> actProdInfo =  prodInfoPage.getProdCompleteInfo();
		//hard Assertion -- used only for single assertion to be made
		//hard ass. doesnot let the further assertion to execute if the first assertion failed
//		Assert.assertEquals(actProdInfo.get("Brand"), "Apple");
//		Assert.assertEquals(actProdInfo.get("Availability"), "In Stock");
//		Assert.assertEquals(actProdInfo.get("Reward Points"), "800");
		
		//Soft Assertion
//execute all the assertion  even if any of the assertions fails  		
		softAssert.assertEquals(actProdInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actProdInfo.get("Availability"), "In Stock");
		softAssert.assertEquals(actProdInfo.get("Reward Points"), "800");
		softAssert.assertAll(); //this gives info about which one is passed or failed assertions

	}
	
	
}
