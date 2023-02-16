package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic - 100: Design Login page for open cart shopping application")

@Story("User Story - 101: Create Login page functionallity for open Cart login page")
public class LoginPageTest extends BaseTest{

	
	@Description("login page title test")
	@Severity(SeverityLevel.TRIVIAL)
	@Test
	public void loginPageTitleTest()
	{
		String actualTitle = logInPage.getLoginPageTitle();
		System.out.println("Login page title:: "+actualTitle);
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE , AppError.NO_TITLE_MATCHED);
	}	
	
	@Description("login page URL test")
	@Severity(SeverityLevel.NORMAL) //not that major
	@Test 
	public void loginPageURLTest() {
		
		String actualURL = logInPage.getLoginPageURl(); 
		System.out.println("Login page title:: "+actualURL);
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppError.NO_URL_MATCHED);
	}
	
	@Description("login page forgot password test")
	@Severity(SeverityLevel.CRITICAL)
	
	@Test 
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(logInPage.isforgotPwd());
	}
	
	
	@Description("user is able to login and land on login page test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void loginTest() {
		accPage= logInPage.doLogin(propt.getProperty("username"),propt.getProperty("password"));
	Assert.assertTrue(accPage.isLogoutExist(), AppError.LOGIN_UNSUCCESSFUL );
	}
}
