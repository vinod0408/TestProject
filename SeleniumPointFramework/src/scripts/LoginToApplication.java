package scripts;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import org.openqa.selenium.By;
import commonFunctions.FrameworkFunctions;
import driver.Driver;
import objectRepository.LoginPage;

public class LoginToApplication extends Driver{
	
	
	public static void loginToPulse(String[] data)
	
	{
		try{
			
			testSuiteName="LoginToApplication";
			testCaseName="loginToPulse";
			
			/*driver.findElement(By.id(LoginPage.UserName)).sendKeys(data[0]);
			driver.findElement(By.id(LoginPage.Password)).sendKeys(data[1]);
			driver.findElement(By.id(LoginPage.LoginButton)).click();*/
			
			Thread.sleep(20000);
			
			String titel=driver.getTitle();
			
			
			//Assert and write Report to File
			
			if(titel.equalsIgnoreCase("Viewics . Pulse"))
			{
				Result="Pass";
				
			}else{
				
				Result="Fail";
				
			}
			
			FrameworkFunctions.reportGeneration(testSuiteName, testCaseName, Result);
			FrameworkFunctions.screenShot(testCaseName);
			
			
		}catch(Exception e){
			
			System.out.println(e);
			try {
				FrameworkFunctions.screenShot("Error"+testCaseName);
			} catch (HeadlessException | IOException | AWTException e1) {
				e1.printStackTrace();
			}
		}
	}
	

}
