package com.qa.opencart.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtill; 
	
	private By searchPoductsCount = By.cssSelector( "div.product-layout");
	//private By 
	
	public ResultsPage(WebDriver driver) { //constructor
		this.driver = driver;
		eleUtill =new ElementUtil(driver);
		
	}
	public String getSearchPageTitle(String producName) {
		 return eleUtill.waitForTitleContains(producName, TimeUtil.DEFAULT_TIME_OUT);
	}
	
	public int getSearchProductsCount() { //what is the product count
		
	  int prodCount =  eleUtill.waitForElementsVisible(searchPoductsCount, TimeUtil.DEFAULT_TIME_OUT).size();
	  System.out.println("product search count: "+prodCount);
	  return prodCount;
	
	}
	public ProductInfoPage selectProd(String mainProdName) {
		System.out.println("main prod name :: "+mainProdName);
		eleUtill.doClick(By.linkText(mainProdName)); 
		return new ProductInfoPage(driver);
	}
}
