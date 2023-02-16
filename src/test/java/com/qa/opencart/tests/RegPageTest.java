package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtils;

public class RegPageTest extends BaseTest{

	@BeforeTest
	public void regPageSetup() {
		regPage =	logInPage.navigateToRegisterationPage();
		
	}
	public String getRandomEmail() {
		Random random = new Random();
		String email ="septautmation"+random.nextInt(5000)+"@gmail.com";
		return email;
	}
	
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object regData[][]= ExcelUtils.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	
	
	@Test(dataProvider ="getRegTestData")
	public void registerUserTest(String firstName,String lastName,String telephone, String password,String subscribe) {
		boolean flag =  regPage.registerUser(firstName, lastName, getRandomEmail(), telephone, password, subscribe);
				//regPage.registerUser("Tom","Hillman","HillTom@gmail.com","465487546","User@123", "yes");
		Assert.assertTrue(flag);
	}
	
	
	
}
