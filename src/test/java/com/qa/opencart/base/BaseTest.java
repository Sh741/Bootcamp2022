package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {

	//all the page references are present here
	DriverFactory Df;
	WebDriver driver;
	protected LoginPage logInPage;
	protected AccountsPage accPage;
	protected ResultsPage resultPage;
	
	protected SoftAssert softAssert;
	protected ProductInfoPage prodInfoPage;
	
	protected Properties propt;
	protected RegPage regPage;
	
	
	
	@BeforeTest
	public void setup() {
		//this has all the driverfactory class has like initializing the brwoser of our choice 
	
		Df =new DriverFactory();
		propt = Df.initPro();
		driver = Df.initDriver(propt); //call by reference
		logInPage = new LoginPage(driver);
		softAssert =new SoftAssert();
		
	}
	
	@AfterTest 
	public void tearDown() {
		driver.quit();
	}
}
