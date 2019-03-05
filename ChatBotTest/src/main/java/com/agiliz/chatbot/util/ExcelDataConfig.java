package com.agiliz.chatbot.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {

	public static XSSFWorkbook wb;
	public static XSSFSheet sheet1;
	public static XSSFSheet sheet;
	public static MultiMap dic = new MultiValueMap();
	public ExcelDataConfig(String excelPath)
	{
		try
		{
			File src=new File(excelPath);
			FileInputStream fis=new FileInputStream(src);
			wb=new XSSFWorkbook(fis);

		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void loadSheet(String sheetName){
		sheet=wb.getSheet(sheetName);
	}
	public void loadData(){
		for(int i=1;i<=sheet.getLastRowNum();i++){
			dic.put(sheet.getRow(i).getCell(0).getStringCellValue(),sheet.getRow(i).getCell(1).getStringCellValue());
			dic.put(sheet.getRow(i).getCell(0).getStringCellValue(),sheet.getRow(i).getCell(2).getStringCellValue());
		}
	}

	public String getMessage(String key){
		List<String> list = (List<String>) dic.get(key);
		return(list.get(0));
	}

	public String getExpTxt(String key){
		List<String> list = (List<String>) dic.get(key);
		return(list.get(1));
	}

	/*	public String getData(int sheetNumber, int row, int column)
	{
	sheet1=wb.getSheetAt(sheetNumber);
	String data=sheet1.getRow(row).getCell(column).getStringCellValue();
	return data;

	}
	 */
}
