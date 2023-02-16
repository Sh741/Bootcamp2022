package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil elUtil;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By produImages = By.cssSelector("a.thumbnail");
	private By prodMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]//li");
	private By prodPricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]//li");

	private Map<String, String> productMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elUtil =new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		return  elUtil.doGetElementText(productHeader);
		
	}
	
	public int getProdImageCounts() {
		int imagCounts =   elUtil.waitForElementsVisible(produImages, TimeUtil.DEFAULT_TIME_OUT).size(); 
		System.out.println("Image Count -->"+imagCounts);
		return imagCounts;
	}
	//Encapsulation 
	public Map<String, String> getProdCompleteInfo() {
		productMap = new HashMap<String, String>();
		getProductMetaData();
		getProductPriceData();
		System.out.println(productMap);
		return productMap;
	}
	
	
	private void getProductMetaData() {
		List<WebElement> metaDataList =  elUtil.getElements(prodMetaData);
		System.out.println("Product meta Data count---> "+metaDataList.size());
		//productMap =new HashMap<String, String>(); non arrange pattern == store the data randomly
		productMap =new LinkedHashMap<String, String>(); //with an arrange pattern 
//insertion order is maintain using linnkedhashmap
//insertion order should be in alphabetical order  -- TreeMap		
		//productMap =new TreeMap<String, String>();
		for(WebElement e : metaDataList) {
		 String meta = e.getText();
		 String metaData[] = meta.split(":");
		 String metaKey = metaData[0].trim();
		 String metaValue = metaData[1].trim();
		 productMap.put(metaKey, metaValue);}
		}
		private void getProductPriceData() {
			List<WebElement> metaPriceList =  elUtil.getElements(prodPricingData);
			System.out.println("Product Price count---> "+metaPriceList.size());
			String price = metaPriceList.get(0).getText().trim();
			String ExTaxprice = metaPriceList.get(1).getText().trim();
			
			productMap.put("actualPrice", price);
			productMap.put("actualExTAxPrice", ExTaxprice);
	}
}
