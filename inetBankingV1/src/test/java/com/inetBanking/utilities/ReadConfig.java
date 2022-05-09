package com.inetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig 
{
	Properties prop;
	public ReadConfig()
	{
		File src = new File("./Configuration/config.properties");
		try
		{
		FileInputStream fis = new FileInputStream(src);
		prop = new Properties();
		prop.load(fis);
		}
		catch(Exception e)
		{
			System.out.println("exception "+ e.getMessage());
		}
	}
	
	public String getURL()
	{
		String url = prop.getProperty("baseURL");
		return url;
	}
	
	public String getUsername()
	{
		String username = prop.getProperty("username");
		return username;
	}
	public String getPassword()
	{
		String password = prop.getProperty("password");
		return password;
	}
	
	public String getChromePath()
	{
		String chromePath = prop.getProperty("chromepath");
		return chromePath;
	}
	public String getFirefoxPath()
	{
		String firefoxPath = prop.getProperty("firefoxpath");
		return firefoxPath;
	}
	

}
