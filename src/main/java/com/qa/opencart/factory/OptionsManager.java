package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions Co;
	private FirefoxOptions fox;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		Co =new ChromeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) { 
		System.out.println("Running the test in headless Mode----");
			Co.setHeadless(true);
			
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) { 
			System.out.println("Running the test in Incognito Mode----");
			Co.addArguments("--incognito");
			
		}
		return Co;
		
	}
	public FirefoxOptions getFireFoxOptions() {
		fox =new FirefoxOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) { 
			System.out.println("Running the test in headless Mode----");
			fox.setHeadless(true);
			
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) { 
			System.out.println("Running the test in Incognito Mode----");
			fox.addArguments("--incognito");
			
		}
		return fox;
		
	}
	
		

}
