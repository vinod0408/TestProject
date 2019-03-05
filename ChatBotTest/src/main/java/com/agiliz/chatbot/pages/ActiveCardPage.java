package com.agiliz.chatbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

import com.agiliz.chatbot.base.TestBase;


public class ActiveCardPage extends TestBase {


	public ActiveCardPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	public ActiveCardPage(){
		// Default Constructor, to provide the inheritance of the page.
	}

	@FindBy(xpath="//button[@type='button' and contains(text(),'Activate Card')]")
	//@CacheLookup
	public static WebElement clickActivecardBtn;

	@FindBy(xpath="//p[@class='ng-tns-c7-2']")
	public static WebElement titleDisplay;

	@FindBy(xpath="//input[@name='chatInput']")
	public static WebElement typeTxtBox;

	@FindBy(xpath="//*[@class='input-group-btn']")
	public static WebElement clickBtn;

	@FindBy(xpath="(//p[contains(@class,'ng-tns-c7')])[last()]")
	public static WebElement expTxtDisplay;

	@FindBy(xpath="//i[contains(@class,'fa-spinner')]")
	public static WebElement spinner;

	@FindBy(xpath="//p[contains(text(),'Which card')]")
	public static WebElement whichCardEle;
	
	@FindBy(xpath="//button[@type='button' and contains(text(),'Debit Card')]")
	public static WebElement debitCardPresent;
	
	@FindBy(xpath="//button[@type='button' and contains(text(),'Credit Card')]")
	public static WebElement creditCardPresent;
	
	@FindBy(xpath="//button[@type='button' and contains(text(),'Rewards Card')]")
	public static WebElement rewardsPresent;
	
	
	public void vBtnsDisplay()
	{
		debitCardPresent.isDisplayed();
		creditCardPresent.isDisplayed();
		rewardsPresent.isDisplayed();
	}
	

	public  void clkActiveBtn() throws Exception{
		waitEleVisibility(clickActivecardBtn);
		clickOnEle(clickActivecardBtn);
//		clickActivecardBtn.click();
		waitEleVisibility(whichCardEle);
		vBtnsDisplay();
	}

	public void titleActiveDisplay(){
		String display=titleDisplay.getText();
		System.out.println("Display the title Page: "+display);
	}

/*	public void methodSend(String msg,String expTxt) throws Exception {
		String actTxt;
		typeTxtBox.sendKeys(msg);
		clickBtn.click();
		waitInvisibility(spinner);
		waitEleVisibility(expTxtDisplay);
		actTxt=expTxtDisplay.getText();
//		Assert.assertTrue(expTxt.equals(actTxt.trim()),"Actual Text is not matching with Expected Text :\t"+actTxt);
//		Reporter.log("Verified, Acutal Text is matching with Expected text :\t"+actTxt);
	}
*/	

	public void vSend(String msg,String expTxt) throws Exception {
	inputBoxPopulate(typeTxtBox,msg);
	clickOnEle(clickBtn);
	waitInvisibility(spinner);
	vAssertEquals(expTxtDisplay,expTxt);
}


	
	
}
