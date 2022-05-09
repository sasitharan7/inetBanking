package com.inetBanking.testCases;


import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 
{
	ReadConfig read = new ReadConfig();
	public String baseURL = read.getURL();
	public String username = read.getUsername();
	public String password = read.getPassword();
	public File finalDestination;
	public static WebDriver driver;
	public static Logger logger;
	@Parameters("browser")
	@BeforeClass
	public void setUp(String browser)
	{
		
		logger = Logger.getLogger(TestCase_V1_001.class);
		//logger = Logger.getLogger(TestCase_V1_001_DataXL.class);
		
		DOMConfigurator.configure("log4j.xml");
		//PropertyConfigurator.configure("log4j.properties");
		
		if(browser.equals("chrome"))
		{
		//System.setProperty("webdriver.chrome.driver", read.getChromePath());
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		else if(browser.equals("firefox"))
		{
			//System.setProperty("webdriver.gecko.driver", read.getFirefoxPath());
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.get(baseURL);
		
	}
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	
	
	

	public String getScreenShot(String screenshotName) throws Exception
	{
	String dateName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	//after execution, you could see a folder "FailedTestsScreenshots" under src folder
	String destination = System.getProperty("user.dir") + "/Screenshots/"+screenshotName+dateName+".png";
	finalDestination = new File(destination);
	FileHandler.copy(source, finalDestination);
	return destination;
	}
	

}
