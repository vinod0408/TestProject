package com.agiliz.chatbot.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Chatbot {
	public WebDriver driver;
	String expTxt;
	WebDriverWait wait; 
	WebElement locator;
	
	
	
	@BeforeClass
	public void setUp() throws Exception{
		System.setProperty("webdriver.gecko.driver", "F:\\Jar files\\geckodriver.exe");
		driver=new FirefoxDriver();
//		wait=new WebDriverWait(driver, 60);
//		WebElement wait1= driver.findElement(By.xpath("//button[@type='button' and contains(text(),'Activate Card')]"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get("http://localhost:3000/login?un=d946c3f7-1c98-4524-a5c9-f60c180b220f&pwd= WSL4HRzmRdhA&id=de71ce13-9855-43f8-bbf6-c44c78d8197a");
		
		//wait.until(ExpectedConditions.visibilityOf(wait1));
	//	WebElement element = wait
		//		.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='WebDriver']")));
 
		Thread.sleep(12000);
		System.out.println(driver.getCurrentUrl());
	}
	   
	@Test(priority=1)
	public void firstMessage() throws Exception
    {
		
		driver.findElement(By.xpath("//button[@type='button' and contains(text(),'Activate Card')]")).click();
			expTxt="Please enter the last 4 digits of your SSN or Account Security Code";
			methodSend("credit card",expTxt);
    }
	
	@Test(priority=2)
	public void secondMessage() throws Exception {
			expTxt="Please input your new card number";
			methodSend("success",expTxt);
	}
	
	@Test(priority=3)
		public void thirdMessage() throws Exception {	
			expTxt="Please input the security code associated with the card";
			methodSend("success",expTxt);
		}
	@Test(priority=4)
	public void fourthMessage() throws Exception {	
		expTxt="Your card has been activated. Would you like to be sent a confirmation?";
		methodSend("success",expTxt);
	}
	
	@Test(priority=5)
	public void fifthMessage() throws Exception {	
		expTxt="Send Confirmation";
		methodSend("yes",expTxt);
	}
		
	@Test(priority=6)
	public void sixthMessage() throws Exception {	
		expTxt="Is there anything else I can help you with today?";
		methodSend("mail",expTxt);
	}
		
		
		/*expTxt="I didn't understand. You can try rephrasing.";
		methodSend("Hi",expTxt);
		
		expTxt="Can you reword your statement? I'm not understanding.";
		methodSend("Hi",expTxt);

		expTxt="I didn't get your meaning.";
		methodSend("Hi",expTxt);*/
		
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
    
		public void methodSend(String msg,String expTxt) throws Exception {
			String a;
			driver.findElement(By.xpath("//input[@name='chatInput']")).sendKeys(msg);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@class='input-group-btn']")).click();
			Thread.sleep(3000);
			//clickOn(driver,locator,30);
			a= driver.findElement(By.xpath("(//p[contains(@class,'ng-tns-c7')])[last()]")).getText();
		    System.out.println("Display Response Text from IBM watson: " +a);
		    Assert.assertTrue(expTxt.equals(a.trim()));
		    //clickOn(driver,locator,30);
		    Thread.sleep(8000);
		}

		public static void clickOn(WebDriver driver, WebElement locator, int timeout){
			new WebDriverWait(driver,timeout).ignoring(StaleElementReferenceException.class).
			until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[contains(@class,'ng-tns-c7')])[last()]")));
			locator.click();
		}
		
		public boolean verifySubmitButton(){
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='input-group-btn']")));

			WebElement SubmitButton=driver.findElement(By.xpath("//*[@class='input-group-btn']"));
			boolean isDisplayed = SubmitButton.isDisplayed();

			return isDisplayed;


}
}
