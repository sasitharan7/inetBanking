package com.inetBanking.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.inetBanking.testCases.BaseClass;

public class ScreenShotClass extends BaseClass
{
	
			
			public File finalDestination;

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


