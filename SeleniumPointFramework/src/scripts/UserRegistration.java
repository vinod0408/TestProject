package scripts;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

import org.openqa.selenium.By;

import commonFunctions.FrameworkFunctions;
import driver.Driver;
import objectRepository.LoginPage;

public class UserRegistration extends Driver {

	public static void registerNewUser(String[] data,String[] assertion) throws InterruptedException {

	try{
			
		
		testSuiteName = "UserRegistration";
		testCaseName = "registerNewUser";
		
		System.out.println("In registerNewUser");
		driver.findElement(By.id(LoginPage.r_name)).sendKeys(data[0]);
		driver.findElement(By.id(LoginPage.r_mobile)).sendKeys(data[1]);
		driver.findElement(By.id(LoginPage.r_email)).sendKeys(data[2]);
		driver.findElement(By.id(LoginPage.r_password)).sendKeys(data[3]);
		driver.findElement(By.name(LoginPage.r_termsChekBox)).click();
		driver.findElement(By.id(LoginPage.r_submitBtn)).click();
		
		Thread.sleep(4000);
		
		System.out.println("assertion is "+assertion[0]);
		
		if(driver.getPageSource().contains(assertion[0]))
		{
			System.out.println("Result=Pass");
			Result="Pass";
			
		}else{
			
			Result="Fail";
		}

	
		FrameworkFunctions.reportGeneration(testSuiteName, testCaseName, Result);
		FrameworkFunctions.screenShot(testCaseName);
		
	}catch(Exception e){System.out.println(e);
	try {
		FrameworkFunctions.screenShot("Error"+testCaseName);
	} catch (HeadlessException | IOException | AWTException e1) {
		e1.printStackTrace();
	}}

	}
	
	

	public static void registration_002() {

		System.out.println("Test case registration_002 started");

	}
	
	

	public static void registration_003(String[] data) throws InterruptedException {

	try{
		
		System.out.println("Test case registration_003 started");
		testSuiteName = "userRegistartion";
		testCaseName = "registration_003";

		System.out.println("in method registration_003 ");
		driver.get("https://www.google.com");
		Thread.sleep(3000);

		driver.findElement(By.id("gs_htif0")).sendKeys(data[0]);
		driver.findElement(By.id("gs_htif0")).sendKeys(data[1]);
		
		FrameworkFunctions.screenShot(testCaseName);
		FrameworkFunctions.reportGeneration(testSuiteName, testCaseName, "Pass");
		
		
	}catch(Exception e){
		try {
			FrameworkFunctions.screenShot("ErrorPage_"+testCaseName);
		} catch (HeadlessException | IOException | AWTException e1) {
			e1.printStackTrace();
		}
		System.out.println(e);}

	}

}
