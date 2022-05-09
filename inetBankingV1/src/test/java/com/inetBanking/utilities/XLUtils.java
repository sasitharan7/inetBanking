package com.inetBanking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils
{
	public static FileInputStream FI;
	public static FileOutputStream FO;
	public static XSSFWorkbook WB;
	public static XSSFSheet SH;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static int getRowCount(String xlfile, String xlsheet) throws IOException
	{
		FI = new FileInputStream(xlfile);
		WB = new XSSFWorkbook(FI);
		SH = WB.getSheet(xlsheet);
		int rowCount = SH.getLastRowNum();
		WB.close();
		FI.close();
		return rowCount;
	}
	
	public static int getCellCount(String xlfile, String xlsheet,int rowNum) throws IOException
	{
		FI = new FileInputStream(xlfile);
		WB = new XSSFWorkbook(FI);
		SH = WB.getSheet(xlsheet);
		row = SH.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		WB.close();
		FI.close();
		return cellCount;
	}
	
	public static String getCellData(String xlfile, String xlsheet, int rowNum, int colNum) throws IOException
	{
		FI = new FileInputStream(xlfile);
		WB = new XSSFWorkbook(FI);
		SH = WB.getSheet(xlsheet);
		row = SH.getRow(rowNum);
		cell = row.getCell(colNum);
		String data;
		try
		{
		DataFormatter formatter = new DataFormatter();
		String cellData = formatter.formatCellValue(cell);
		return cellData;
		}
		catch(Exception e)
		{
			data="";
		}
		WB.close();
		FI.close();
		return data;	
	}
	
	public static void setCellData(String xlfile, String xlsheet, int rowNum, int colNum,String data) throws IOException
	{
		
		FI = new FileInputStream(xlfile);
		WB = new XSSFWorkbook(FI);
		SH = WB.getSheet(xlsheet);
		row = SH.getRow(rowNum);
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		FO = new FileOutputStream(xlfile);
		WB.write(FO);
		WB.close();
		FI.close();
		FO.close();
		
	}
	
	
}
