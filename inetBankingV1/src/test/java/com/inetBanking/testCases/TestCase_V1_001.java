package com.inetBanking.testCases;

import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.PageObjectClass;

import junit.framework.Assert;

public class TestCase_V1_001 extends BaseClass
{
	@Test
	public void login_testcase() throws Exception
	{
		PageObjectClass POC = new PageObjectClass(driver);
		//Logger logger =  Logger.getLogger(TestCase_V1_001.class);
		logger.info("getting URL");
		//driver.get(baseURL);
		logger.info("message 1");
		
		logger.info("Sending username");
		POC.setUserName(username);
		logger.debug("sending pwd ");
		POC.setPassword(password);
		logger.info("login button click ");
		Thread.sleep(2000);
		POC.click_btn();
		
		System.out.println(driver.getTitle());
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage123"))
		{
			Assert.assertTrue(true);
			logger.info("Pass");
		}
		else
		{
			getScreenShot("login_testcase");
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			Assert.assertTrue(false);
			logger.info("Fail");
			
				
				
			
		}
			
	}
		
}


