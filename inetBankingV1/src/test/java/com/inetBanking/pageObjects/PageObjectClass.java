package com.inetBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectClass
{
	WebDriver driver;
	public PageObjectClass(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="uid") WebElement userName;
	@FindBy(name="password") WebElement passWord;
	@FindBy(name="btnLogin") WebElement btnlogin;
	@FindBy(xpath="/html/body/div[3]/div/ul/li[15]/a") WebElement Logout;
	
	public void setUserName(String username)
	{
		userName.sendKeys(username);
		System.out.println(username);
	}
	public void setPassword(String password)
	{
		passWord.sendKeys(password);
		System.out.println(password);
	}
	public void click_btn()
	{
		btnlogin.click();
		System.out.println("@@@@@@");
	}
	public void click_logout()
	{
		Logout.click();
	}
}
