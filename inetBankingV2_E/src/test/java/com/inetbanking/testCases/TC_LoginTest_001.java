package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass
{
	@Test
	public void loginTest() throws InterruptedException, IOException
	{
						
		logger.info("URL is opened"); //logger msg
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User name provided"); //logger msg
		
		lp.setPassword(password);
		logger.info("Password provided"); //logger msg
		lp.clickSubmit();
		logger.info("Validaion started");
		
		Thread.sleep(5000);
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			logger.info("Login test passed");
			Assert.assertTrue(true);
			
		}
		else
		{
			captureScreen(driver,"loginTest"); // Call this method on failure to capture screenshot
			logger.info("Login test failed");
			Assert.assertTrue(false);
			
		}
	}
		
	
}
