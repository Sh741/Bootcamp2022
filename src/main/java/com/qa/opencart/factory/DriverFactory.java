package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	
	 public static ThreadLocal<WebDriver> tlDriver =new ThreadLocal<WebDriver>();
	
	
	//initialize the webbrowser 
	public WebDriver driver;
	public Properties prop;
	
	public OptionsManager optionManager;
	
	public static String highlight;
	
	//public WebDriver initDriver(String browserName) we have initialized the browName and calling it 
	public WebDriver initDriver(Properties prop) // here from the properties file we are calling the features to
	// be here

	{
		
		String browserName =prop.getProperty("browser").trim();
		
		System.out.println("Browser Name:  "+browserName);
		
		//cross browser logic
		
		highlight =prop.getProperty("highlight");

		optionManager = new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			//driver =new ChromeDriver(optionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
		//	driver = new FirefoxDriver(optionManager.getFireFoxOptions());
			
			tlDriver.set(new FirefoxDriver(optionManager.getFireFoxOptions()));
		}
		else if (browserName.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		}
		else {
			System.out.println("Please enter the correct browser name .."+browserName);
		}
		
		//driver.manage().deleteAllCookies();
		getThreadDriver().manage().deleteAllCookies();
		//driver.manage().window().maximize();
		getThreadDriver().manage().window().maximize();
		//driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		//driver.get(prop.getProperty("url"));
		getThreadDriver().get(prop.getProperty("url"));
		return getThreadDriver();
		
	}
	//get the local thread copy of the driver
	public synchronized static WebDriver getThreadDriver() {
		
	return 	tlDriver.get();
		
	}
	
	
	//help me to read the data from the config.properties
	public Properties initPro()
	{
		 prop = new Properties();
		 try {
			FileInputStream Ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(Ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return prop;
		 
	}
	
	public static String getScreenshot() {
		File srcfile = ((TakesScreenshot) getThreadDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		
		try {
			org.openqa.selenium.io.FileHandler.copy(srcfile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
		
	}
	
	}
	
