package driver;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Automation Framework Driver class
 * @author harshal.shewale
 *
 */

public class Driver {
	
	private static Logger logger= Logger.getLogger(Driver.class);
	
	public static WebDriver driver;
	public static String browserName ="";
	public static String TestType="";
	public static String QA_ENVIRONMENT="";
	public static String DEV_ENVIRONMENT="";
	public static String PRODUCTION_ENVIRONMENT="";
	public static String testSuiteName="";
	public static String testCaseName="";
	public static String Result="";
	public static String testData="";
	public static String assertions="";
	public static int numberOfParams=0;
	
	public static String TestScenariosExcelPath="";
	public static String GeckoDriverPath="";
	public static String ChromeDriverPath="";
	public static String EdgeDriverPath="";
	
	public static String TestReportsPath="";
	public static String AppScrinShotsPath="";
	
	public static void main(String args[])
	{
		PropertyConfigurator.configure("log4j.properties");
		logger.info("*******************************************************");
		logger.info("------------WEL COME TO AUTOMATION FRAMEWORK-----------");
		logger.info("*******************************************************");
		logger.info("------------------Its a MAIN Class---------------------");
		
		//Read data From TestEnvironments.properties file
		try {
		FileReader reader;
		reader = new FileReader("Configuration.properties");
		Properties properties =new Properties();
		properties.load(reader);
		QA_ENVIRONMENT=properties.getProperty("Configuration.QA_ENVIRONMENT");
		DEV_ENVIRONMENT=properties.getProperty("Configuration.DEV_ENVIRONMENT");
		PRODUCTION_ENVIRONMENT=properties.getProperty("Configuration.PRODUCTION_ENVIRONMENT");
		TestScenariosExcelPath=properties.getProperty("Configuration.TestScenariosExcelPath");
		GeckoDriverPath=properties.getProperty("Configuration.GeckoDriverPath");
		EdgeDriverPath=properties.getProperty("Configuration.EdgeDriverPath");
		ChromeDriverPath=properties.getProperty("Configuration.EdgeDriverPath");
		
		TestReportsPath=properties.getProperty("Configuration.TestReportsPath");
		AppScrinShotsPath=properties.getProperty("Configuration.AppScrinShotsPath");
	
		
		
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		
		//Initialize browser for application under test
		browserInitialization();
		
		//Read execution scenarios from excel file and trigger automation test cases to run
		executeScenarios();
		
	}
	
	
	/*
	 * method:browserInitialization
	 * description:Read browser name to launch from excel file and initialize the same.
	 * 
	 */
	
	
	public static void browserInitialization() {
		
		try{
			
			logger.info("---------Browser Initialization Started----------");
			
			String excelPath=TestScenariosExcelPath;
			FileInputStream input = new FileInputStream(excelPath);
			XSSFWorkbook workbook=new XSSFWorkbook(input);
			XSSFSheet browserSheet = workbook.getSheet("browser");
			int numberOfRows=browserSheet.getLastRowNum();
			
			for(int i=0;i<=numberOfRows;i++)
			{
				XSSFRow row=browserSheet.getRow(i);
				if(row!=null)
				{
					if(row.getCell(2).getStringCellValue().equalsIgnoreCase("yes"))
					{
						browserName=row.getCell(1).getStringCellValue().toString();
						logger.info("---------Selected Browser for AUT is : "+browserName +"----------");
						
						if(browserName.equalsIgnoreCase("FireFox"))
						{
							System.setProperty("webdriver.gecko.driver",GeckoDriverPath);
							driver=new FirefoxDriver();
							System.out.println("Firefox Selected");
							
							
						}else if(browserName.equalsIgnoreCase("Chrome")){
							
							driver=new ChromeDriver();
							
						}else if(browserName.equalsIgnoreCase("InternetExplorer")){
							
							driver=new InternetExplorerDriver();
							
						}else if(browserName.equalsIgnoreCase("MicrosoftEdge")){
							
							System.setProperty("webdriver.edge.driver",EdgeDriverPath);
							driver=new EdgeDriver();
							
						}else if(browserName.equalsIgnoreCase("Safari")){
							
							driver=new SafariDriver();
							
						}
										
					}else{
						
						//System.out.println("There should be YES in front of browser ");
					}
					
				}else{
					System.out.println("There is no data in excel file");
					
				}
			}
			
			workbook.close();

			} catch (IOException e) {
				     e.printStackTrace();
				}

	}
	
	
	/*
	 * method:executeScenarios
	 * description:It calls the method or test case to run if it found "yes" in excel.
	 */
		
		
	public static void executeScenarios() {
		
		try {
			
			
			logger.info("---------Executing Test Scenarios Started----------");
			
			String excelPath=TestScenariosExcelPath;
			FileInputStream input = new FileInputStream(excelPath);
			XSSFWorkbook workbook=new XSSFWorkbook(input);
			
			//GetTestEnvironmentToRun
			XSSFSheet EnvironmentSheet = workbook.getSheet("TestType");
			int numberOfRowsinEnvironmentSheet=EnvironmentSheet.getLastRowNum();
			for(int i=0;i<=numberOfRowsinEnvironmentSheet;i++)
			{
				XSSFRow EnvironmentSheetRow=EnvironmentSheet.getRow(i);
				if(EnvironmentSheetRow!=null)
				{
					if(EnvironmentSheetRow.getCell(2).getStringCellValue().equalsIgnoreCase("yes"))
					{
						TestType=EnvironmentSheetRow.getCell(1).getStringCellValue().toString().trim();
						logger.info("---------Executing Test Type : "+TestType+"----------");
						System.out.println("TestType :"+TestType);
					}
				}
			}
			
			//Go to TestType Sheet
			
			XSSFSheet Sheet = workbook.getSheet(TestType);
			int numberOfRows=Sheet.getLastRowNum();
		
			for(int i=0;i<=numberOfRows;i++)
			{
				XSSFRow row=Sheet.getRow(i);
				if(row!=null)
				{
					if(row.getCell(3).getStringCellValue().equalsIgnoreCase("yes"))
					{
						testSuiteName=row.getCell(1).getStringCellValue().toString().trim();
						testCaseName=row.getCell(2).getStringCellValue().toString().trim();
						
						testData=row.getCell(4).getStringCellValue().toString().trim();
						String DataArray[]=testData.split(",");
						
						assertions=row.getCell(5).getStringCellValue().toString().trim();
						String AssertionsArray[]=assertions.split(",");
						
						numberOfParams = testData .replaceAll("[^,]","").length();  
						
						System.out.println("testSuiteName: "+testSuiteName);
						System.out.println("testCaseName: "+testCaseName);
						System.out.println("numberOfParams: "+numberOfParams);
						System.out.println("testData: "+testData); 
						System.out.println("assertions: "+assertions); 
						
						logger.info("---------Running Test Suite :"+testSuiteName+"||"+" Running Test Case :" +testCaseName+ "-------------");
						
						//call to testCase using java reflection
						Class<?> clazz = Class.forName("scripts."+testSuiteName);
						System.out.println("Class Name : "+clazz);
						
						//int countParams = 2;
						/*Class[] arrayOfVariables = new Class[numberOfParams];
						for(int ji=0; ji<numberOfParams; ji++){
							arrayOfVariables[ji]=String.class;
						}*/
						
						if(numberOfParams==0)
						{
							Method m=clazz.getDeclaredMethod(testCaseName);
							System.out.println("Method Name : "+m);
							Object t = clazz.newInstance();
							Object o= m.invoke(t);
							
						} else
						
						{
							//Method m=clazz.getDeclaredMethod(testCaseName, new Class[] {String[].class, String[].class});--> for Key(locators) and value (Data)
							Method m=clazz.getDeclaredMethod(testCaseName, new Class[] {String[].class,String[].class});
							System.out.println("Method Name : "+m);
							Object t = clazz.newInstance();
							Object o= m.invoke(t,new Object[]{DataArray,AssertionsArray});
						}
						
						
					}
					
				}else{
					System.out.println("File is empty");
				}
			}
			
			
			workbook.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
	}

}
