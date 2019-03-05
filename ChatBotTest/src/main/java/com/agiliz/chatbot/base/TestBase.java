package com.agiliz.chatbot.base;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import com.agiliz.chatbot.pages.ActiveCardPage;
import com.agiliz.chatbot.util.ExcelDataConfig;
import com.agiliz.chatbot.util.TestUtil;
import com.agiliz.chatbot.util.WebEventListener;


public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static ExcelDataConfig excelRead;
	public static Logger log;
	public static ActiveCardPage activeCdPage;
	//log = Logger.getLogger(TestBase.class);

	// Create object of WebDriverWait class and it will wait max of 20 seconds.
	// By default it will accepts in Seconds
	//WebDriverWait wait = new WebDriverWait(driver, 20);

	/*	public TestBase(){
		prop=new Properties();

		try {
			prop=new Properties();
			//InputStream lp=new FileInputStream(System.getProperty("user.dir")+ "/chatbotui/src/com/chatbotui/config/config.Properties");
			InputStream lp= new FileInputStream("D:\\Pic\\ChatBotTest\\src\\main\\java\\com\\agiliz\\chatbot\\config\\config.Properties");
			prop.load(lp);
				} catch (Exception e) {

			e.printStackTrace();
		}

	}
	 */	
	static {
		prop=new Properties();
		try {
			prop=new Properties();
			//InputStream lp=new FileInputStream(System.getProperty("user.dir")+ "/chatbotui/src/com/chatbotui/config/config.Properties");
			InputStream lp= new FileInputStream("D:\\Pic\\ChatBotTest\\src\\main\\java\\com\\agiliz\\chatbot\\config\\config.Properties");
			prop.load(lp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@BeforeSuite(alwaysRun=true)
	public void preRequisite(){
		excelRead = new ExcelDataConfig("D:\\Pic\\ChatBotTest\\src\\main\\java\\com\\agiliz\\chatbot\\testdata\\chat1.xlsx");
	}
	@BeforeSuite(alwaysRun=true)
	public static void initialization(){
		String browserName = prop.getProperty("browser");

		if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "F:/Jar files/geckodriver.exe");	
			//System.setProperty("webdriver.gecko.driver", "F:\\Jar files\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "F:/Jar files/chromedriver.exe");	
			driver = new ChromeDriver(); 
		}

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		//log.info("entered url");
		wait=new WebDriverWait(driver,TestUtil.explicitWait);
		activeCdPage=new ActiveCardPage(driver);
	}

	public String dateAndTime(){
		DateTimeFormatter dateFormatter;
		dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime dateAndtime = LocalDateTime.now();
		return(dateFormatter.format(dateAndtime));
	}

	// Selection Actions :
	public void clickOnEle(WebElement ele){
		waitEleClickable(ele);
		ele.click();
		Reporter.log("[ "+dateAndTime()+" ]"+"Clicked on the Expected Element:\t");
	}

	public void inputBoxPopulate(WebElement ele,String msg){
		waitEleVisibility(ele);
		ele.sendKeys(msg);
		Reporter.log("[ "+dateAndTime()+" ]"+"Populated the input box with the given message :\t"+msg);
	}


	// Wait methods :
	public static void waitEleVisibility(String xpath){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public static void waitEleVisibility(WebElement ele){
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public static void waitEleClickable(String xpath){
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}

	public static void waitEleClickable(WebElement ele){
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public static void waitInvisibility(WebElement ele){
		WebDriverWait ww=new WebDriverWait(driver,10);
		ww.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//i[contains(@class,'fa-spinner')]")));
	}


	// Assertions :
	public void vAssertEquals(String expTxt,String actTxt){
		Assert.assertTrue(expTxt.equals(actTxt.trim()),"Actual Text is not matching with Expected Text :\t"+actTxt);
		Reporter.log("[ "+dateAndTime()+" ]"+"Verified, Acutal Text is matching with Expected text :\t"+actTxt);

	}

	public void vAssertEquals(WebElement ele,String expTxt){
		String actutalTxt=ele.getText().trim();
		Assert.assertTrue(expTxt.equals(actutalTxt.trim()),"Actual Text is not matching with Expected Text :\t"+actutalTxt);
		Reporter.log("[ "+dateAndTime()+" ]"+"Verified, Acutal Text is matching with Expected text :\t"+actutalTxt);

	}

	// Tear Down method.
	@AfterClass
	public void tearDown(){
		driver.quit();
	}


}

