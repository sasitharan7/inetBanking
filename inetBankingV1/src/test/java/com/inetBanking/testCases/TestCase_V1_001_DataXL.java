package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.PageObjectClass;
import com.inetBanking.utilities.XLUtils;

public class TestCase_V1_001_DataXL  extends BaseClass
{

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd)
	{
		PageObjectClass POC = new PageObjectClass(driver);
		POC.setUserName(user);
		POC.setPassword(pwd);
		POC.click_btn();
		if(isAlertPresent()==true) // invalid credentials
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		
		else
		{
			Assert.assertTrue(true);
			POC.click_logout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();//focus on login
		}
		
		
	}
	
	public boolean isAlertPresent()//user defined method for checking alert is present or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException
	{
		
		String XLpath = System.getProperty("user.dir")+"/src/test/java/com/inetBanking/testData/ExcelData.xlsx";
		int rowCount = XLUtils.getRowCount(XLpath, "Sheet1");
		int colCount = XLUtils.getCellCount(XLpath, "Sheet1", 1);
	String loginData [][] = new String [rowCount][colCount];
	for(int i=1;i<=rowCount; i++)
	{
		for(int j=0;j<colCount;j++)
		{
			loginData[i-1][j] = XLUtils.getCellData(XLpath, "Sheet1", i, j);//1,0 
		}
	}
	return loginData;
		
	}
}
