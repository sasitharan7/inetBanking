package com.inetBanking.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerClass extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	public String timeStamp;
	public String destination;
	
	public void onStart(ITestContext testContext)
	{
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-"+timeStamp+".html";
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		htmlReporter.config().setDocumentTitle("inetBanking Test Project");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "sheela");
		
		System.out.println("Test is Started*************");
	}
	public void onTestSuccess(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		System.out.println("Success -----------");
	}
	public void  onTestFailure(ITestResult tr)
	{
		
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		String timeStamp1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String screenShotPath = System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+timeStamp1+".png";
		System.out.println("SCREENSHOTPATH    +" + screenShotPath);
		String screenShotName = tr.getName();
		
		ScreenShotClass screenShot = new ScreenShotClass();
		try {
			 destination = screenShot.getScreenShot(screenShotName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("##################"+tr.getName()+timeStamp);
		
		if(screenShot.finalDestination.exists())
		{ 
			try
			{
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				logger.fail("ScreenShot is below:" + logger.addScreenCaptureFromPath(destination));
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//System.out.println("Failure @@@@@@@@@@@@@@@@@@@");
	}
	public void onTestSkipped(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		System.out.println("Skipped %%%%%%%%%%%%%%%%%%%%");
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

}
